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
 * Class      :   PostalCodeValidator.java
 * Author     :   Sean Carrick <sean at pekinsoft dot com>
 * Created    :   Dec 20, 2020 @ 2:13:15 PM
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

package com.pekinsoft.nwind.utils.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sean Carrick &lt;sean at pekinsoft dot com&gt;
 * 
 * @version 0.1.0
 * @since 0.1.0
 */
public class PostalCodeValidator {
    // Public Static Constants
    
    
    // Private Static Constants
    
    
    // Private Member Fields
    
    
    // Constructor(s)
    /**
     * Creates a default instance of the PostalCodeValidator class.
     */
    private PostalCodeValidator () {}

    private static boolean isUsZipCodeValid(String zip) {
        String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(zip);
        return matcher.matches();
    }
    
    private static boolean isCanadianPostalCodeValid(String postalCode) {
        String regex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }
    
    // Public Static Methods
    /**
     * Validates US Zip and Canadian Postal Codes. Provide the Zip or Postal
     * Code in question and `isValid` will determine if the provided
     * Postal Code is a valid US Zip or Canadian Postal Code.
     * 
     * <dl><dt>NOTE:</dt><dd>This utility is only good for validating US Zip and
     * Canadian Postal Codes. If attempting to validate a Postal Code from 
     * Europe, Asia, Africa, Central or South America, or Australia, this method
     * will not work.<br><br>If a developer creates a class or classes to validate
     * these other regions' Postal Codes, PekinSOFT Systems would very much
     * appreciate if that developer were to submit their class(es) to our
     * Northwind Traders repository.</dd></dl>
     * 
     * @param postalCode the Postal Code to check
     * @return `true` if the provided Postal Code is either a valid US Zip or
     *          Canadian Postal Code; `false` if it is neither.
     */
    public static boolean isValid(String postalCode) {
        return isUsZipCodeValid(postalCode) 
                || isCanadianPostalCodeValid(postalCode);
    }
    
}
