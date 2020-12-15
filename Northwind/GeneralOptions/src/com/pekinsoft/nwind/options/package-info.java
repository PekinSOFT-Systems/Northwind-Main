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
 * Created    :   Dec 12, 2020 @ 10:19:16 AM
 * Modified   :   Dec 12, 2020
 *  
 * Purpose:
 * 	
 * Revision History:
 *  
 * WHEN          BY                  REASON
 * ----------    ------------------- -------------------------------------------
 * Dec 12, 2020    Sean Carrick             Initial creation.
 * *****************************************************************************
 */
@OptionsPanelController.ContainerRegistration(id = "Northwind", categoryName = "#OptionsCategory_Name_Northwind", iconBase = "com/pekinsoft/nwind/options/northwind32.png", keywords = "#OptionsCategory_Keywords_Northwind", keywordsCategory = "Northwind")
@NbBundle.Messages(value = {"OptionsCategory_Name_Northwind=Northwind", "OptionsCategory_Keywords_Northwind=Northwind, Company, Accounting, Defaults"})
package com.pekinsoft.nwind.options;

import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;
