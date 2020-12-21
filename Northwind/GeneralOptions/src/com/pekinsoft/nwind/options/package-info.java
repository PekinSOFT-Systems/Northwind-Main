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
 * Class      :   package-info.java
 * Author     :   Sean Carrick <sean at pekinsoft dot com>
 * Created    :   Dec 20, 2020 @ 10:45:10 AM
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
@OptionsPanelController.ContainerRegistration(id = "Nwind", categoryName = "#OptionsCategory_Name_Nwind", iconBase = "com/pekinsoft/nwind/options/northwind32.png", keywords = "#OptionsCategory_Keywords_Nwind", keywordsCategory = "Nwind")
@NbBundle.Messages(value = {"OptionsCategory_Name_Nwind=Northwind Traders", "OptionsCategory_Keywords_Nwind=Company, Northwind, Northwind Traders, General"})
package com.pekinsoft.nwind.options;

import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;
