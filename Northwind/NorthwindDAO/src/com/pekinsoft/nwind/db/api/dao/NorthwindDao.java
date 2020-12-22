/*
 * Copyright (C) 2020 PekinSOFT Systems
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * *****************************************************************************
 * Project    :   Northwind
 * Class      :   NorthwindDao.java
 * Author     :   Sean Carrick <sean at pekinsoft dot com>
 * Created    :   Dec 20, 2020 @ 7:57:20 PM
 * Modified   :   Dec 20, 2020
 *  
 * Purpose:
 * 	
 * Revision History:
 *  
 * WHEN          BY                  REASON
 * ----------    ------------------- -------------------------------------------
 * Dec 20, 2020    Sean Carrick             Initial creation.
 * *****************************************************************************
 */
package com.pekinsoft.nwind.db.api.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.openide.modules.Places;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;

/**
 *
 * @author Sean Carrick &lt;sean at pekinsoft dot com&gt;
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class NorthwindDao<T> {

    // Private Final Fields
    private final String DB_NAME = "NorthwindDB";
    private final String DB_DRIVER = NbPreferences.root().get(
            "northwind.db.driver", "org.apache.derby.jdbc.EmbeddedDriver");

    // Private Member Fields
    private Connection dbConnection = null;

    // Constructor(s)
    /**
     * Creates a default instance of the NorthwindDao class.
     */
    public NorthwindDao() {
        setDbSystemDir();
        loadDatabaseDriver();

        makeConnection();
    }

    // Private Member Fields
    private void makeConnection() {
        String dbUrl = "jdbc:derby:" + DB_NAME + "create=true;";

        try {
            dbConnection = DriverManager.getConnection(dbUrl);
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void loadDatabaseDriver() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void setDbSystemDir() {
        String dbLocation = Places.getUserDirectory().getAbsolutePath();

        // Make sure there is a trailing file separator character.
        if (!dbLocation.endsWith(File.separator)) {
            dbLocation += File.separator;
        }

        dbLocation += "data" + File.separator + "NorthwindDB";

        System.setProperty("derby.system.home", dbLocation);
    }

    // Public Methods
    /**
     * Retrieves the `java.sql.Connection` object that Northwind Traders uses
     * for connecting to the database. This connection object will need to be
     * used anytime that a module needs access to the database. Further, this
     * connection object allows modules to add their own custom table(s) if need
     * be.
     *
     * <dl><dt>DEVELOPER'S NOTE:</dt><dd>Any module that creates a table in the
     * Northwind Traders database needs to provide quality documentation about
     * the table, including: all fields' data types and whether or not the field
     * accepts `null` values; all fields' names; the name of the primary key
     * field; the names of any fields which are indices; the data length
     * restriction of any field; whether or not the table is in a relationship
     * with (an)other table(s); etc.</dd></dl>
     *
     * @return the connection object for the database
     */
    public Connection getConnection() {
        return this.dbConnection;
    }

    /**
     * Retrieves the name of the database used by Northwind Traders.
     *
     * @return the database name
     */
    public String getDbName() {
        return DB_NAME;
    }

    /**
     * Retrieves the database driver used by Northwind Traders.
     *
     * @return the database driver used
     */
    public String getDbDriver() {
        return DB_DRIVER;
    }

    public boolean doesTableExist(String tableName) {
        DatabaseMetaData md = null;
        boolean noTable = false;

        try {
            md = dbConnection.getMetaData();
            ResultSet tables = md.getTables(null, null, "%", null);

            while (tables.next()) {
                if (tables.getString(3).equalsIgnoreCase(tableName)) {
                    noTable = false;
                    break;
                } else {
                    noTable = true;
                }
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return noTable;
    }

    /**
     * Provides a method of creating a table in the database. This method
     * automatically adds a unique identifying field to the table, so one is not
     * needed to be included in the provided SQL statement. All that is needed
     * to be provided to this method is the table name, and the field
     * declarations, other than the primary key field.
     *
     * <dl><dt>Example:</dt>
     * <dd>```java NorthwindDao db = new NorthwindDao(); boolean success =
     * db.createTable("Customer", "company varchar(30) NOT NULL, city
     * varchar(40) NOT NULL, phone varchar(14) NOT NULL);"); if (success) {
     * System.out.println("Customer table successfully created!"); } else {
     * System.out.println("Uh-Oh! Something went wrong and the table was not
     * created."); } ```
     *
     * @param tableName the name to give this table.
     * @param fldDeclarations the field declarations in SQL format, without the
     * opening parenthesis, nor unique identifying field declaration
     *
     * @return `true` on success; `false` on failure
     */
    public boolean createTable(String tableName, String fldDeclarations) {
        boolean success = false;

        String sql = "CREATE TABLE " + tableName + "(";
        sql += "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY "
                + "(START WITH 1, INCREMENT BY 1), ";
        sql += fldDeclarations;

        Statement stmt = null;

        try {
            stmt = dbConnection.createStatement();
            stmt.execute(sql);
            success = true;
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return success;
    }

    /**
     * Provides a method of creating a table in the database. This method
     * requires all table creation syntax to be present. Typically, this method
     * will be used in the instance that a non-numeric field is going to be used
     * as the primary key in the table.
     *
     * <dl><dt>Example:</dt>
     * <dd>```java NorthwindDao db = new NorthwindDao(); boolean success =
     * db.createTable("CREATE TABLE customer(company(varchar(30) NOT NULL UNIQUE
     * PRIMARY KEY, city varchar(40) NOT NULL, phone varchar(14) NOT NULL);");
     * if (success) { System.out.println("Customer table successfully
     * created!"); } else { System.out.println("Uh-Oh! Something went wrong and
     * the table was not created."); } ```
     *
     * @param sql the SQL statement to use to create the table
     * @return `true` upon success; `false` otherwise
     */
    public boolean createTable(String sql) {
        boolean success = false;

        Statement stmt = null;

        try {
            stmt = dbConnection.createStatement();
            stmt.execute(sql);
            success = true;
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return success;
    }

    /**
     * Retrieves a `java.sql.ResultSet` object containing the records requested
     * in the SQL statement provided.
     *
     * @param sql the SQL statement to retrieve the desired records.
     * @return `java.sql.ResultSet` object, if records found; `null` otherwise
     */
    public ResultSet getResultSet(String sql) {
        ResultSet rs = null;
        Statement stmt = null;

        try {
            stmt = dbConnection.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            rs = null;
        }

        return rs;
    }

    /**
     * Retrieves all records in the given table. The first thing done inside
     * this method is to make sure the table exists in the database. If it does
     * not exist, then `null` is returned.
     *
     * @param tableName table from which to retrieve the records
     * @return `java.sql.ResultSet` object containing all records from the
     * table; `null` if the table is not found or an error occurs
     */
    public ResultSet getAllRecords(String tableName) {
        ResultSet rs = null;
        Statement stmt = null;
        String sql = "SELECT * FROM " + tableName;
        DatabaseMetaData md = null;

        try {
            md = dbConnection.getMetaData();
            ResultSet tables = md.getTables(null, null, "%", null);
            boolean noTable = false;

            while (tables.next()) {
                if (tables.getString(3).equalsIgnoreCase(tableName)) {
                    noTable = false;
                    break;
                } else {
                    noTable = true;
                }
            }

            if (noTable) {
                return null;
            } else {
                stmt = dbConnection.createStatement();
                rs = stmt.executeQuery(sql);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            rs = null;
        }

        return rs;
    }

}
