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
 * Class      :   EmailValidator.java
 * Author     :   Sean Carrick <sean at pekinsoft dot com>
 * Created    :   Dec 20, 2020 @ 11:27:40 AM
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
public class EmailValidator {
    // Public Static Constants
    
    
    // Private Static Constants
    
    
    // Private Member Fields
    
    
    // Constructor(s)
    /**
     * Creates a default instance of the EmailValidator class.
     */
    private EmailValidator () {}

    // Public Static Methods
    /**
     * Validates email address formats. This method provides validation of the
     * provided email address format <strong>only</strong>. This method <em>
     * <strong>does not</strong></em> in any way make sure that the email 
     * address is a valid address to which mail may be sent. Again, this method
     * <strong>only validates the <em>format</em> of the email address</strong>.
     * 
     * @param email the email address of which to validate the format
     * @return `true` if the email address format is valid; `false` otherwise.
     */
    public static boolean isValid(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+"
                + ")*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(email);
        
        return matcher.matches();
    }
    
}
