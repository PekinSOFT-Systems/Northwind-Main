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
 * Class      :   Customer.java
 * Author     :   Sean Carrick <sean at pekinsoft dot com>
 * Created    :   Dec 21, 2020 @ 10:38:47 AM
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
package com.pekinsoft.nwind.db.api.model;

import com.pekinsoft.nwind.db.api.dao.NorthwindDao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.openide.util.Exceptions;

/**
 *
 * @author Sean Carrick &lt;sean at pekinsoft dot com&gt;
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class Customer {
    // Public Static Constants

    // Private Static Constants
    // Private Member Fields
    private long id;
    private String company;
    private String street;
    private String suite;
    private String city;
    private String stateOrProvince;
    private String postalCode;
    private String contact;
    private String phone;
    private String email;
    private String notes;
    private boolean broker;

    // Constructor(s)
    /**
     * Creates a default instance of the Customer class.
     */
    public Customer() {
        this(0, null, null, null, null, null, null, null, null, null, null, false);
    }

    /**
     * Creates a new `Customer` model object and connects to the database. In
     * the event that this is the first time a `Customer` model object has been
     * created since installing Northwind Traders, the `NorthwindDB.CUSTOMERS`
     * table will be created. Once the table has been created, it will not ever
     * be dropped or clobbered by recreating the table again.
     *
     * @param id customer ID - Auto-generated integer
     * @param company company name - <strong>required</strong>
     * @param street street address - <strong>required</strong>
     * @param suite suite number - <em>optional</em>
     * @param city city - <strong>required</strong>
     * @param stateOrProvince state or province - <strong>required</strong>
     * @param postalCode postal code - <strong>required</strong>
     * @param contact contact name - <em>optional</em>
     * @param phone phone number - <em>optional</em>
     * @param email email address - <em>optional</em>
     * @param notes notes - <em>optional</em>
     */
    public Customer(long id, String company, String street, String suite,
            String city, String stateOrProvince, String postalCode,
            String contact, String phone, String email, String notes,
            boolean broker) /*throws InvalidPostalCodeException, InvalidEmailException*/ {
        this.id = id;
        this.company = company;
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.stateOrProvince = stateOrProvince;

        //if (PostalCodeValidator.isValid(postalCode)) {
        this.postalCode = postalCode;
        //} else {
        //    throw new InvalidPostalCodeException("The provided postal code ("
        //            + postalCode + ") is not in a valid format.");
        //}

        this.contact = contact;
        this.phone = phone;

        //if ((email != null && !email.isEmpty()) && EmailValidator.isValid(email)) {
        this.email = email;
        //} else {
        //    throw new InvalidEmailException("The provided email address ("
        //            + email + ") is not in a valid format.");
        //}

        this.notes = notes;
        this.broker = broker;
    }

    /**
     * Retrieves the auto-generated ID for this customer object.
     *
     * @return unique ID
     */
    public long getId() {
        return id;
    }

    /**
     * Stores the unique, auto-generated ID from the database to this customer
     * object.
     *
     * @param id unique ID from the database
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Retrieves the company name for this customer object.
     *
     * @return company name
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the company name for this customer object.
     *
     * @param company company name
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Retrieves the street address for this customer object.
     *
     * @return the street address
     */
    public String getStreet() {
        return street;
    }

    /**
     * Stores the street address for this customer object.
     *
     * @param street street address
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Retrieves the suite number, if any for this company object.
     *
     * @return the suite number, if any, or `null` if not set.
     */
    public String getSuite() {
        if (suite != null && suite.isEmpty()) {
            return null;
        }

        return suite;
    }

    /**
     * Stores the suite number, if any, for this customer object. If there is no
     * suite number associated with this customer's address, set this to `null`.
     *
     * @param suite suite number or `null`
     */
    public void setSuite(String suite) {
        if (suite.isEmpty()) {
            suite = null;
        }

        this.suite = suite;
    }

    /**
     * Retrieves the city in which this customer is located.
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Stores the city in which this customer is located.
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Retrieves the State or Province in which this customer is lcoated.
     *
     * @return state or province
     */
    public String getStateOrProvince() {
        return stateOrProvince;
    }

    /**
     * Stores the State or Province in which this customer is located.
     *
     * @param stateOrProvince
     */
    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    /**
     * Retrieves the postal code for this customer's mailing address.
     *
     * @return postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Stores the postal code for this customer's mailing address. Postal Codes
     * provided to this method are validated for format. If the format of the
     * Postal Code is not valid, an `InvalidPostalCodeException` is thrown.
     * <p>
     * Currently, Northwind Traders validates US Zip and Canadian Postal Codes
     * only. In future versions, Northwind Traders will provide format
     * validation for European, Asian, African, South/Central American, and
     * Australian Postal Codes as well. For now, however, only North American
     * Postal Codes can be validated for format.</p>
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) /*throws InvalidPostalCodeException*/ {
        /*if (!PostalCodeValidator.isValid(postalCode)) {
            throw new InvalidPostalCodeException("The provided Postal Code ("
                    + postalCode + ") is not valid.");
        }
         */

        this.postalCode = postalCode;
    }

    /**
     * Retrieves the name of the contact person at this customer.
     *
     * @return contact person's name or `null` if not set
     */
    public String getContact() {
        if (contact != null && contact.isEmpty()) {
            return null;
        }

        return contact;
    }

    /**
     * Stores the name of the contact person for this customer.
     *
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Retrieves the phone number for this customer.
     *
     * @return company phone number or `null`, if not set
     */
    public String getPhone() {
        if (phone != null && phone.isEmpty()) {
            return null;
        }

        return phone;
    }

    /**
     * Stores the phone number for this customer.
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the email address for this customer.
     *
     * @return email address or `null` if not set
     */
    public String getEmail() {
        if (email != null && email.isEmpty()) {
            return null;
        }

        return email;
    }

    /**
     * Stores the email address for this customer. This method validates the
     * format of the email address. If the provided email address is not in a
     * valid format, an `InvalidEmailAddressException` is thrown.
     *
     * @param email
     */
    public void setEmail(String email) /*throws InvalidEmailAddressException*/ {
        /*if (email != null && !EmailValidator.isValid(email)) {
            throw new InvalidEmailAddressException("The provided email address ("
                    + email + ") is not in a valid format.");
        }
         */
        if (email != null && email.isEmpty()) {
            email = null;
        }

        this.email = email;
    }

    /**
     * Retrieves any notes regarding this customer, if any. If no notes exist,
     * then `null` is returned.
     *
     * @return notes or `null` if not set
     */
    public String getNotes() {
        if (notes != null && notes.isEmpty()) {
            return null;
        }

        return notes;
    }

    /**
     * Stores any notes regarding this customer, if any. If no notes are
     * provided, `null` is stored.
     *
     * @param notes
     */
    public void setNotes(String notes) {
        if (notes.isEmpty()) {
            notes = null;
        }

        this.notes = notes;
    }

    /**
     * Determines whether this `Customer` object represents a broker or not.
     *
     * @return `true` if this object represents a broker; `false` otherwise
     */
    public boolean isBroker() {
        return broker;
    }

    /**
     * Stores whether or not this `Customer` object represents a broker.
     *
     * @param broker `true` if so, `false` otherwise
     */
    public void setBroker(boolean broker) {
        this.broker = broker;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + company + " (" + city + ", " + stateOrProvince + ")";
    }

    @Override
    public int hashCode() {
        int hash = 73;
        hash += company.hashCode() * 3;
        hash += city.hashCode() * 3;
        hash += stateOrProvince.hashCode() * 3;
        hash += postalCode.hashCode() * 3;

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Customer)) {
            return false;
        }

        Customer c = (Customer) obj;

        return c.getCompany().equals(company)
                && c.getStreet().equals(street)
                && c.getSuite().equals(suite)
                && c.getCity().equals(city)
                && c.getStateOrProvince().equals(stateOrProvince)
                && c.getPostalCode().equals(postalCode)
                && c.hashCode() == hashCode();
    }

}
