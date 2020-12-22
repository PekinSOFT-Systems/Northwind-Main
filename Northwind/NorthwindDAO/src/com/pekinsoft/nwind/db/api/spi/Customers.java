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
 * Class      :   Customers.java
 * Author     :   Sean Carrick <sean at pekinsoft dot com>
 * Created    :   Dec 21, 2020 @ 12:24:48 PM
 * Modified   :   Dec 21, 2020
 *  
 * Purpose:
 * 	
 * Revision History:
 *  
 * WHEN          BY                  REASON
 * ----------    ------------------- -------------------------------------------
 * Dec 21, 2020    Sean Carrick             Initial creation.
 * *****************************************************************************
 */
package com.pekinsoft.nwind.db.api.spi;

import com.pekinsoft.nwind.db.api.dao.NorthwindDao;
import com.pekinsoft.nwind.db.api.enums.ComparisonMethods;
import com.pekinsoft.nwind.db.api.model.Customer;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Exceptions;

/**
 *
 * @author Sean Carrick &lt;sean at pekinsoft dot com&gt;
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class Customers {
    // Private Member Fields

    private ResultSet results;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private NorthwindDao dao;

    // Constructor(s)
    /**
     * Creates a default instance of the CustomerSPI class.
     */
    public Customers() {
        dao = new NorthwindDao();

        // Try to create the table.
        dao.createTable("CUSTOMERS", "COMPANY VARCHAR(30) NOT NULL, "
                + "STREET VARCHAR(25) NOT NULL, "
                + "SUITE VARCHAR(15), "
                + "CITY VARCHAR(30) NOT NULL,"
                + "STATE_OR_PROVINCE VARCHAR(4) NOT NULL, "
                + "POSTAL_CODE VARCHAR(10) NOT NULL, "
                + "CONTACT VARCHAR(40), "
                + "PHONE VARCHAR(14), "
                + "EMAIL VARCHAR(30), "
                + "NOTES LONG VARCHAR, "
                + "BROKER BOOLEAN NOT NULL DEFAULT FALSE)");

        // Populate our ResultSet with the entire table
        findAll();

        // Move to the first record in the ResultSet
        first();
    }

    private Customer buildNewCustomer() throws SQLException {
        return new Customer(results.getInt("ID"),
                results.getString("COMPANY"),
                results.getString("Street"),
                results.getString("SUITE"),
                results.getString("CITY"),
                results.getString("STATE_OR_PROVINCE"),
                results.getString("POSTAL_CODE"),
                results.getString("CONTACT"),
                results.getString("PHONE"),
                results.getString("EMAIL"),
                results.getString("NOTES"),
                results.getBoolean("BROKER"));
    }

    /**
     * Executes the SQL statement `SELECT * FROM [TABLE_NAME];` to retrieve all
     * records from the table of the Northwind Traders database.
     * <p>
     * Upon successful completion, `findAll` returns the first record from the
     * table to the calling procedure.</p>
     *
     * @return the first record from the table
     */
    public final Customer findAll() {
        String sql = "SELECT * FROM APP.CUSTOMERS";

        try {
            statement = dao.getConnection().createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            results = statement.executeQuery(sql);
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return first();
    }

    /**
     * Finds all matching records according to the provided comparison
     * operation.
     * <p>
     * The comparisons that are allowed are contained in the enumeration
     * `com.pekinsoft.nwind.db.api.enums.ComparisonMethods`. This enumeration
     * allows for all eight (8) SQL comparison operators:</p>
     * <ul><li>Equals (=): where the two values are equal</li>
     * <li>Not equal (!=): where the two values are not equal</li>
     * <li>Less than (<): where the field value is less than the comparison
     * value</li> <li>Less than or equal (<=); where the field value is less tha
     * n or equal to the comparison value</li> <li>Greater than (>): where the
     * field value is greater than the c omparison value</li>
     * <li>Greater than or equal to (>=): where the field value is greater than
     * or equal to the comparison value</li>
     * <li>BETWEEN: where the field value is between the two comparison values,
     * inclusive</li>
     * <li>LIKE: where the field value is similar to the comparison value
     * </li>
     * </ul>
     *
     * @param fieldName the name of the field of interest
     * @param value an array of `java.lang.String` values to compare to
     * @param method the comparison method to use
     * @return a `java.util.List` of the results of the search.
     */
    public final List<Customer> findBy(String fieldName, String[] value,
            ComparisonMethods method) {
        List<Customer> list = new ArrayList<>();
        String sql = null;
        ResultSet rs = null;
        String comparison = null;

        switch (method.toString()) {
            case " BETWEEN ":
                comparison = " AND ";
                break;
            default:
                comparison = "";
        }

        if (value.length > 1 && method == ComparisonMethods.BETWEEN) {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?" + comparison + "?";
        } else {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?";
        }

        try {
            preparedStatement = dao.getConnection().prepareStatement(sql);
            for (int x = 0; x < value.length; x++) {
                preparedStatement.setString(x, value[x]);
            }

            rs = preparedStatement.executeQuery();

            if (rs != null && rs.getRow() > 0) {
                rs.first();

                while (rs.next()) {
                    Customer c = new Customer(
                            rs.getInt("ID"),
                            rs.getString("Company"),
                            rs.getString("STREET"),
                            rs.getString("SUITE"),
                            rs.getString("CITY"),
                            rs.getString("STATE_OR_PROVINCE"),
                            rs.getString("POSTAL_CODE"),
                            rs.getString("CONTACT"),
                            rs.getString("PHONE"),
                            rs.getString("EMAIL"),
                            rs.getString("NOTES"),
                            rs.getBoolean("BROKER"));

                    list.add(c);
                }
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return list;
    }

    /**
     * Finds all matching records according to the provided comparison
     * operation.
     * <p>
     * The comparisons that are allowed are contained in the enumeration
     * `com.pekinsoft.nwind.db.api.enums.ComparisonMethods`. This enumeration
     * allows for all eight (8) SQL comparison operators:</p>
     * <ul><li>Equals (=): where the two values are equal</li>
     * <li>Not equal (!=): where the two values are not equal</li>
     * <li>Less than (<): where the field value is less than the comparison
     * value</li> <li>Less than or equal (<=); where the field value is less tha
     * n or equal to the comparison value</li> <li>Greater than (>): where the
     * field value is greater than the c omparison value</li>
     * <li>Greater than or equal to (>=): where the field value is greater than
     * or equal to the comparison value</li>
     * <li>BETWEEN: where the field value is between the two comparison values,
     * inclusive</li>
     * <li>LIKE: where the field value is similar to the comparison value
     * </li>
     * </ul>
     *
     * @param fieldName the name of the field of interest
     * @param value an array of `java.lang.Integer` values to compare to
     * @param method the comparison method to use
     * @return a `java.util.List` of the results of the search.
     */
    public List<Customer> findBy(String fieldName, Integer[] value,
            ComparisonMethods method) {
        List<Customer> list = new ArrayList<>();
        String sql = null;
        ResultSet rs = null;
        String comparison = null;

        switch (method.toString()) {
            case " BETWEEN ":
                comparison = " AND ";
                break;
            default:
                comparison = "";
        }

        if (value.length > 1 && method == ComparisonMethods.BETWEEN) {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?" + comparison + "?";
        } else {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?";
        }

        try {
            preparedStatement = dao.getConnection().prepareStatement(sql);
            for (int x = 0; x < value.length; x++) {
                preparedStatement.setInt(x, value[x]);
            }

            rs = preparedStatement.executeQuery();

            if (rs != null && rs.getRow() > 0) {
                rs.first();

                while (rs.next()) {
                    Customer c = new Customer(
                            rs.getInt("ID"),
                            rs.getString("Company"),
                            rs.getString("STREET"),
                            rs.getString("SUITE"),
                            rs.getString("CITY"),
                            rs.getString("STATE_OR_PROVINCE"),
                            rs.getString("POSTAL_CODE"),
                            rs.getString("CONTACT"),
                            rs.getString("PHONE"),
                            rs.getString("EMAIL"),
                            rs.getString("NOTES"),
                            rs.getBoolean("BROKER"));

                    list.add(c);
                }
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return list;
    }

    /**
     * Finds all matching records according to the provided comparison
     * operation.
     * <p>
     * The comparisons that are allowed are contained in the enumeration
     * `com.pekinsoft.nwind.db.api.enums.ComparisonMethods`. This enumeration
     * allows for all eight (8) SQL comparison operators:</p>
     * <ul><li>Equals (=): where the two values are equal</li>
     * <li>Not equal (!=): where the two values are not equal</li>
     * <li>Less than (<): where the field value is less than the comparison
     * value</li> <li>Less than or equal (<=); where the field value is less tha
     * n or equal to the comparison value</li> <li>Greater than (>): where the
     * field value is greater than the c omparison value</li>
     * <li>Greater than or equal to (>=): where the field value is greater than
     * or equal to the comparison value</li>
     * <li>BETWEEN: where the field value is between the two comparison values,
     * inclusive</li>
     * <li>LIKE: where the field value is similar to the comparison value
     * </li>
     * </ul>
     *
     * @param fieldName the name of the field of interest
     * @param value an array of `java.lang.Long` values to compare to
     * @param method the comparison method to use
     * @return a `java.util.List` of the results of the search.
     */
    public List<Customer> findBy(String fieldName, Long[] value,
            ComparisonMethods method) {
        List<Customer> list = new ArrayList<>();
        String sql = null;
        ResultSet rs = null;
        String comparison = null;

        switch (method.toString()) {
            case " BETWEEN ":
                comparison = " AND ";
                break;
            default:
                comparison = "";
        }

        if (value.length > 1 && method == ComparisonMethods.BETWEEN) {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?" + comparison + "?";
        } else {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?";
        }

        try {
            preparedStatement = dao.getConnection().prepareStatement(sql);
            for (int x = 0; x < value.length; x++) {
                preparedStatement.setLong(x, value[x]);
            }

            rs = preparedStatement.executeQuery();

            if (rs != null && rs.getRow() > 0) {
                rs.first();

                while (rs.next()) {
                    Customer c = new Customer(
                            rs.getInt("ID"),
                            rs.getString("Company"),
                            rs.getString("STREET"),
                            rs.getString("SUITE"),
                            rs.getString("CITY"),
                            rs.getString("STATE_OR_PROVINCE"),
                            rs.getString("POSTAL_CODE"),
                            rs.getString("CONTACT"),
                            rs.getString("PHONE"),
                            rs.getString("EMAIL"),
                            rs.getString("NOTES"),
                            rs.getBoolean("BROKER"));

                    list.add(c);
                }
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return list;
    }

    /**
     * Finds all matching records according to the provided comparison
     * operation.
     * <p>
     * The comparisons that are allowed are contained in the enumeration
     * `com.pekinsoft.nwind.db.api.enums.ComparisonMethods`. This enumeration
     * allows for all eight (8) SQL comparison operators:</p>
     * <ul><li>Equals (=): where the two values are equal</li>
     * <li>Not equal (!=): where the two values are not equal</li>
     * <li>Less than (<): where the field value is less than the comparison
     * value</li> <li>Less than or equal (<=); where the field value is less tha
     * n or equal to the comparison value</li> <li>Greater than (>): where the
     * field value is greater than the c omparison value</li>
     * <li>Greater than or equal to (>=): where the field value is greater than
     * or equal to the comparison value</li>
     * <li>BETWEEN: where the field value is between the two comparison values,
     * inclusive</li>
     * <li>LIKE: where the field value is similar to the comparison value
     * </li>
     * </ul>
     *
     * @param fieldName the name of the field of interest
     * @param value an array of `java.lang.Double` values to compare to
     * @param method the comparison method to use
     * @return a `java.util.List` of the results of the search.
     */
    public List<Customer> findBy(String fieldName, Double[] value,
            ComparisonMethods method) {
        List<Customer> list = new ArrayList<>();
        String sql = null;
        ResultSet rs = null;
        String comparison = null;

        switch (method.toString()) {
            case " BETWEEN ":
                comparison = " AND ";
                break;
            default:
                comparison = "";
        }

        if (value.length > 1 && method == ComparisonMethods.BETWEEN) {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?" + comparison + "?";
        } else {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?";
        }

        try {
            preparedStatement = dao.getConnection().prepareStatement(sql);
            for (int x = 0; x < value.length; x++) {
                preparedStatement.setDouble(x, value[x]);
            }

            rs = preparedStatement.executeQuery();

            if (rs != null && rs.getRow() > 0) {
                rs.first();

                while (rs.next()) {
                    Customer c = new Customer(
                            rs.getInt("ID"),
                            rs.getString("Company"),
                            rs.getString("STREET"),
                            rs.getString("SUITE"),
                            rs.getString("CITY"),
                            rs.getString("STATE_OR_PROVINCE"),
                            rs.getString("POSTAL_CODE"),
                            rs.getString("CONTACT"),
                            rs.getString("PHONE"),
                            rs.getString("EMAIL"),
                            rs.getString("NOTES"),
                            rs.getBoolean("BROKER"));

                    list.add(c);
                }
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return list;
    }

    /**
     * Finds all matching records according to the provided comparison
     * operation.
     * <p>
     * The comparisons that are allowed are contained in the enumeration
     * `com.pekinsoft.nwind.db.api.enums.ComparisonMethods`. This enumeration
     * allows for all eight (8) SQL comparison operators:</p>
     * <ul><li>Equals (=): where the two values are equal</li>
     * <li>Not equal (!=): where the two values are not equal</li>
     * <li>Less than (<): where the field value is less than the comparison
     * value</li> <li>Less than or equal (<=); where the field value is less tha
     * n or equal to the comparison value</li> <li>Greater than (>): where the
     * field value is greater than the c omparison value</li>
     * <li>Greater than or equal to (>=): where the field value is greater than
     * or equal to the comparison value</li>
     * <li>BETWEEN: where the field value is between the two comparison values,
     * inclusive</li>
     * <li>LIKE: where the field value is similar to the comparison value
     * </li>
     * </ul>
     *
     * @param fieldName the name of the field of interest
     * @param value an array of `java.lang.Float` values to compare to
     * @param method the comparison method to use
     * @return a `java.util.List` of the results of the search.
     */
    public List<Customer> findBy(String fieldName, Float[] value,
            ComparisonMethods method) {
        List<Customer> list = new ArrayList<>();
        String sql = null;
        ResultSet rs = null;
        String comparison = null;

        switch (method.toString()) {
            case " BETWEEN ":
                comparison = " AND ";
                break;
            default:
                comparison = "";
        }

        if (value.length > 1 && method == ComparisonMethods.BETWEEN) {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?" + comparison + "?";
        } else {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?";
        }

        try {
            preparedStatement = dao.getConnection().prepareStatement(sql);
            for (int x = 0; x < value.length; x++) {
                preparedStatement.setFloat(x, value[x]);
            }

            rs = preparedStatement.executeQuery();

            if (rs != null && rs.getRow() > 0) {
                rs.first();

                while (rs.next()) {
                    Customer c = new Customer(
                            rs.getInt("ID"),
                            rs.getString("Company"),
                            rs.getString("STREET"),
                            rs.getString("SUITE"),
                            rs.getString("CITY"),
                            rs.getString("STATE_OR_PROVINCE"),
                            rs.getString("POSTAL_CODE"),
                            rs.getString("CONTACT"),
                            rs.getString("PHONE"),
                            rs.getString("EMAIL"),
                            rs.getString("NOTES"),
                            rs.getBoolean("BROKER"));

                    list.add(c);
                }
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return list;
    }

    /**
     * Finds all matching records according to the provided comparison
     * operation.
     * <p>
     * The comparisons that are allowed are contained in the enumeration
     * `com.pekinsoft.nwind.db.api.enums.ComparisonMethods`. This enumeration
     * allows for all eight (8) SQL comparison operators:</p>
     * <ul><li>Equals (=): where the two values are equal</li>
     * <li>Not equal (!=): where the two values are not equal</li>
     * <li>Less than (<): where the field value is less than the comparison
     * value</li> <li>Less than or equal (<=); where the field value is less tha
     * n or equal to the comparison value</li> <li>Greater than (>): where the
     * field value is greater than the c omparison value</li>
     * <li>Greater than or equal to (>=): where the field value is greater than
     * or equal to the comparison value</li>
     * <li>BETWEEN: where the field value is between the two comparison values,
     * inclusive</li>
     * <li>LIKE: where the field value is similar to the comparison value
     * </li>
     * </ul>
     *
     * @param fieldName the name of the field of interest
     * @param value an array of `java.sql.Date` values to compare to
     * @param method the comparison method to use
     * @return a `java.util.List` of the results of the search.
     */
    public List<Customer> findBy(String fieldName, Date[] value,
            ComparisonMethods method) {
        List<Customer> list = new ArrayList<>();
        String sql = null;
        ResultSet rs = null;
        String comparison = null;

        switch (method.toString()) {
            case " BETWEEN ":
                comparison = " AND ";
                break;
            default:
                comparison = "";
        }

        if (value.length > 1 && method == ComparisonMethods.BETWEEN) {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?" + comparison + "?";
        } else {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?";
        }

        try {
            preparedStatement = dao.getConnection().prepareStatement(sql);
            for (int x = 0; x < value.length; x++) {
                preparedStatement.setDate(x, value[x]);
            }

            rs = preparedStatement.executeQuery();

            if (rs != null && rs.getRow() > 0) {
                rs.first();

                while (rs.next()) {
                    Customer c = new Customer(
                            rs.getInt("ID"),
                            rs.getString("Company"),
                            rs.getString("STREET"),
                            rs.getString("SUITE"),
                            rs.getString("CITY"),
                            rs.getString("STATE_OR_PROVINCE"),
                            rs.getString("POSTAL_CODE"),
                            rs.getString("CONTACT"),
                            rs.getString("PHONE"),
                            rs.getString("EMAIL"),
                            rs.getString("NOTES"),
                            rs.getBoolean("BROKER"));

                    list.add(c);
                }
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return list;
    }

    /**
     * Finds all matching records according to the provided comparison
     * operation.
     * <p>
     * The comparisons that are allowed are contained in the enumeration
     * `com.pekinsoft.nwind.db.api.enums.ComparisonMethods`. This enumeration
     * allows for all eight (8) SQL comparison operators:</p>
     * <ul><li>Equals (=): where the two values are equal</li>
     * <li>Not equal (!=): where the two values are not equal</li>
     * <li>Less than (<): where the field value is less than the comparison
     * value</li> <li>Less than or equal (<=); where the field value is less tha
     * n or equal to the comparison value</li> <li>Greater than (>): where the
     * field value is greater than the c omparison value</li>
     * <li>Greater than or equal to (>=): where the field value is greater than
     * or equal to the comparison value</li>
     * <li>BETWEEN: where the field value is between the two comparison values,
     * inclusive</li>
     * <li>LIKE: where the field value is similar to the comparison value
     * </li>
     * </ul>
     *
     * @param fieldName the name of the field of interest
     * @param value an array of `java.sql.Time` values to compare to
     * @param method the comparison method to use
     * @return a `java.util.List` of the results of the search.
     */
    public List<Customer> findBy(String fieldName, Time[] value,
            ComparisonMethods method) {
        List<Customer> list = new ArrayList<>();
        String sql = null;
        ResultSet rs = null;
        String comparison = null;

        switch (method.toString()) {
            case " BETWEEN ":
                comparison = " AND ";
                break;
            default:
                comparison = "";
        }

        if (value.length > 1 && method == ComparisonMethods.BETWEEN) {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?" + comparison + "?";
        } else {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?";
        }

        try {
            preparedStatement = dao.getConnection().prepareStatement(sql);
            for (int x = 0; x < value.length; x++) {
                preparedStatement.setTime(x, value[x]);
            }

            rs = preparedStatement.executeQuery();

            if (rs != null && rs.getRow() > 0) {
                rs.first();

                while (rs.next()) {
                    Customer c = new Customer(
                            rs.getInt("ID"),
                            rs.getString("Company"),
                            rs.getString("STREET"),
                            rs.getString("SUITE"),
                            rs.getString("CITY"),
                            rs.getString("STATE_OR_PROVINCE"),
                            rs.getString("POSTAL_CODE"),
                            rs.getString("CONTACT"),
                            rs.getString("PHONE"),
                            rs.getString("EMAIL"),
                            rs.getString("NOTES"),
                            rs.getBoolean("BROKER"));

                    list.add(c);
                }
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return list;
    }

    /**
     * Finds all matching records according to the provided comparison
     * operation.
     * <p>
     * The comparisons that are allowed are contained in the enumeration
     * `com.pekinsoft.nwind.db.api.enums.ComparisonMethods`. This enumeration
     * allows for all eight (8) SQL comparison operators:</p>
     * <ul><li>Equals (=): where the two values are equal</li>
     * <li>Not equal (!=): where the two values are not equal</li>
     * <li>Less than (<): where the field value is less than the comparison
     * value</li> <li>Less than or equal (<=); where the field value is less tha
     * n or equal to the comparison value</li> <li>Greater than (>): where the
     * field value is greater than the c omparison value</li>
     * <li>Greater than or equal to (>=): where the field value is greater than
     * or equal to the comparison value</li>
     * <li>BETWEEN: where the field value is between the two comparison values,
     * inclusive</li>
     * <li>LIKE: where the field value is similar to the comparison value
     * </li>
     * </ul>
     *
     * @param fieldName the name of the field of interest
     * @param value an array of `java.sql.Timestamp` values to compare to
     * @param method the comparison method to use
     * @return a `java.util.List` of the results of the search.
     */
    public List<Customer> findBy(String fieldName, Timestamp[] value,
            ComparisonMethods method) {
        List<Customer> list = new ArrayList<>();
        String sql = null;
        ResultSet rs = null;
        String comparison = null;

        switch (method.toString()) {
            case " BETWEEN ":
                comparison = " AND ";
                break;
            default:
                comparison = "";
        }

        if (value.length > 1 && method == ComparisonMethods.BETWEEN) {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?" + comparison + "?";
        } else {
            sql = "SELECT * FROM APP.CUSTOMERS WHERE " + fieldName
                    + method.toString() + "?";
        }

        try {
            preparedStatement = dao.getConnection().prepareStatement(sql);
            for (int x = 0; x < value.length; x++) {
                preparedStatement.setTimestamp(x, value[x]);
            }

            rs = preparedStatement.executeQuery();

            if (rs != null && rs.getRow() > 0) {
                rs.first();

                while (rs.next()) {
                    Customer c = new Customer(
                            rs.getInt("ID"),
                            rs.getString("Company"),
                            rs.getString("STREET"),
                            rs.getString("SUITE"),
                            rs.getString("CITY"),
                            rs.getString("STATE_OR_PROVINCE"),
                            rs.getString("POSTAL_CODE"),
                            rs.getString("CONTACT"),
                            rs.getString("PHONE"),
                            rs.getString("EMAIL"),
                            rs.getString("NOTES"),
                            rs.getBoolean("BROKER"));

                    list.add(c);
                }
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return list;
    }
    
    /**
     * Retrieves a list of all brokers from the `CUSTOMERS` table of the 
     * database. The list returned <strong><em>will not</em></strong> include
     * any regular customers in it, only brokers.
     * 
     * @return list of `Customer` objects
     */
    public List<Customer> getBrokers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM APP.CUSTOMERS WHERE BROKER = ?";
        ResultSet rs = null;
        

        try {
            preparedStatement = dao.getConnection().prepareStatement(sql);
            preparedStatement.setBoolean(0, true);

            rs = preparedStatement.executeQuery();

            if (rs != null && rs.getRow() > 0) {
                rs.first();

                while (rs.next()) {
                    Customer c = new Customer(
                            rs.getInt("ID"),
                            rs.getString("Company"),
                            rs.getString("STREET"),
                            rs.getString("SUITE"),
                            rs.getString("CITY"),
                            rs.getString("STATE_OR_PROVINCE"),
                            rs.getString("POSTAL_CODE"),
                            rs.getString("CONTACT"),
                            rs.getString("PHONE"),
                            rs.getString("EMAIL"),
                            rs.getString("NOTES"),
                            rs.getBoolean("BROKER"));

                    list.add(c);
                }
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return list;
    }

    /**
     * Retrieves a list of all customers from the `CUSTOMERS` table of the 
     * database. The list returned <strong><em>will not</em></strong> include
     * any brokers in it, only regular customers.
     * 
     * @return list of `Customer` objects
     */
    public List<Customer> getCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM APP.CUSTOMERS WHERE BROKER = ?";
        ResultSet rs = null;
        

        try {
            preparedStatement = dao.getConnection().prepareStatement(sql);
            preparedStatement.setBoolean(0, false);

            rs = preparedStatement.executeQuery();

            if (rs != null && rs.getRow() > 0) {
                rs.first();

                while (rs.next()) {
                    Customer c = new Customer(
                            rs.getInt("ID"),
                            rs.getString("Company"),
                            rs.getString("STREET"),
                            rs.getString("SUITE"),
                            rs.getString("CITY"),
                            rs.getString("STATE_OR_PROVINCE"),
                            rs.getString("POSTAL_CODE"),
                            rs.getString("CONTACT"),
                            rs.getString("PHONE"),
                            rs.getString("EMAIL"),
                            rs.getString("NOTES"),
                            rs.getBoolean("BROKER"));

                    list.add(c);
                }
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return list;
    }

    /**
     * Moves the record pointer to the first record in the table and returns
     * that record to the calling procedure.
     *
     * @return the first record in the table.
     */
    public final Customer first() {
        Customer c = null;

        try {
            results.first();

            // Build our return Customer object.
            c = buildNewCustomer();
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return c;
    }

    /**
     * Moves the record pointer to the previous record in the table and returns
     * that record to the calling procedure.
     *
     * @return the previous record in the table.
     */
    public final Customer previous() {
        Customer c = null;

        try {
            if (!results.isFirst()) {
                results.previous();
            }

            // Build our return Customer object.
            c = buildNewCustomer();
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return c;
    }

    /**
     * Moves the record pointer to the next record in the table and returns that
     * record to the calling procedure.
     *
     * @return the next record in the table.
     */
    public final Customer next() {
        Customer c = null;

        try {
            if (!results.isLast()) {
                results.next();
            }

            // Build our return Customer object.
            c = buildNewCustomer();
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return c;
    }

    /**
     * Moves the record pointer to the last record in the table and returns that
     * record to the calling procedure.
     *
     * @return the last record in the table.
     */
    public final Customer last() {
        Customer c = null;

        try {
            results.last();

            // Build our return Customer object.
            c = buildNewCustomer();
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }

        return c;
    }

    /**
     * Adds a new `Customer` object to the database.
     *
     * @param c the new `Customer` object to add
     * @return `true` on success; `false` on failure
     */
    public final boolean add(Customer c) {
        boolean success = false;    // Assume failure.

        String sql = "INSERT INTO APP.CUSTOMERS("
                + "COMPANY, STREET, SUITE, CITY, STATE_OR_PROVINCE, POSTAL_CODE,"
                + "CONTACT, PHONE, EMAIL, NOTES, BROKER) VALUES(?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = dao.getConnection().prepareStatement(sql);
            preparedStatement.setString(0, c.getCompany());
            preparedStatement.setString(1, c.getStreet());
            preparedStatement.setString(2, c.getSuite());
            preparedStatement.setString(3, c.getCity());
            preparedStatement.setString(4, c.getStateOrProvince());
            preparedStatement.setString(5, c.getPostalCode());
            preparedStatement.setString(6, c.getContact());
            preparedStatement.setString(7, c.getPhone());
            preparedStatement.setString(8, c.getEmail());
            preparedStatement.setString(9, c.getNotes());
            preparedStatement.setBoolean(10, c.isBroker());

            results.moveToInsertRow();

            preparedStatement.execute();

            results.insertRow();

            results = statement.executeQuery("SELECT * FROM APP.CUSTOMERS");

            success = true;
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);

            success = false;
        }

        return success;
    }

    /**
     * Updates the current record with the new data.
     *
     * @param c the new data to use for the update
     * @return `true` upon success; `false` upon failure
     */
    public final boolean update(Customer c) {
        boolean success = false;    // Assume failure.

        String sql = "UPDATE APP.CUSTOMERS SET "
                + "COMPANY = ?, STREET = ?, SUITE = ?, CITY = ?, "
                + "STATE_OR_PROVINCE = ?, POSTAL_CODE = ?, CONTACT = ?"
                + "PHONE = ?, EMAIL = ?, NOTES = ?, BROKER = ?) WHERE ID = ?";

        try {
            preparedStatement = dao.getConnection().prepareStatement(sql);
            preparedStatement.setString(0, c.getCompany());
            preparedStatement.setString(1, c.getStreet());
            preparedStatement.setString(2, c.getSuite());
            preparedStatement.setString(3, c.getCity());
            preparedStatement.setString(4, c.getStateOrProvince());
            preparedStatement.setString(5, c.getPostalCode());
            preparedStatement.setString(6, c.getContact());
            preparedStatement.setString(7, c.getPhone());
            preparedStatement.setString(8, c.getEmail());
            preparedStatement.setString(9, c.getNotes());
            preparedStatement.setBoolean(10, c.isBroker());
            preparedStatement.setLong(11, c.getId());

            preparedStatement.execute();

            results.updateRow();

            results = statement.executeQuery("SELECT * FROM APP.CUSTOMERS");

            success = true;
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);

            success = false;
        }

        return success;
    }

    public final boolean delete(Customer c) {
        boolean success = false;    // Assume failure.

        String sql = "DELETE FROM APP.CUSTOMERS SET WHERE ID = ?";

        try {
            preparedStatement = dao.getConnection().prepareStatement(sql);
            preparedStatement.setLong(0, c.getId());

            preparedStatement.execute();

            results = statement.executeQuery("SELECT * FROM APP.CUSTOMERS");

            success = true;
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);

            success = false;
        }

        return success;
    }

}
