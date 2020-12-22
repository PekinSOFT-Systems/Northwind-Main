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
 * Class      :   ComparisonMethods.java
 * Author     :   Sean Carrick <sean at pekinsoft dot com>
 * Created    :   Dec 21, 2020 @ 1:17:36 PM
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
package com.pekinsoft.nwind.db.api.enums;

/**
 *
 * @author Sean Carrick &lt;sean at pekinsoft dot com&gt;
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public enum ComparisonMethods {
    EQUALS(" = "),
    NOT_EQUALS(" != "),
    LESS_THAN(" < "),
    LESS_THAN_EQUAL_TO(" <= "),
    GREATER_THAN(" > "),
    GREATER_THAN_EQUAL_TO(" >= "),
    BETWEEN(" BETWEEN "),
    LIKE(" LIKE ");

    String status;

    ComparisonMethods(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
