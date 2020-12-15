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
 * Class      :   CompanyPanel.java
 * Author     :   sean <sean at pekinsoft dot com>
 * Created    :   Dec 12, 2020 @ 10:22:29 AM
 * Modified   :   Dec 12, 2020
 *  
 * Purpose:
 * 	
 * Revision History:
 *  
 * WHEN          BY                  REASON
 * ----------    ------------------- -------------------------------------------
 * Dec 12, 2020    sean             Initial creation.
 * *****************************************************************************
 */
package com.pekinsoft.nwind.options;

import java.util.prefs.Preferences;

final class CompanyPanel extends javax.swing.JPanel {

    private final CompanyOptionsPanelController controller;

    CompanyPanel(CompanyOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
        
        dotLabel.setVisible(ownAuthorityField.isSelected());
        dotField.setVisible(ownAuthorityField.isSelected());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        instructionsLabel = new javax.swing.JLabel();
        companyLabel = new javax.swing.JLabel();
        companyField = new javax.swing.JTextField();
        contactLabel = new javax.swing.JLabel();
        contactField = new javax.swing.JTextField();
        phoneLabel = new javax.swing.JLabel();
        phoneField = new javax.swing.JFormattedTextField();
        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        streetLabel = new javax.swing.JLabel();
        streetField = new javax.swing.JTextField();
        suiteLabel = new javax.swing.JLabel();
        suiteField = new javax.swing.JTextField();
        cityLabel = new javax.swing.JLabel();
        cityField = new javax.swing.JTextField();
        stateLabel = new javax.swing.JLabel();
        stateField = new javax.swing.JTextField();
        zipLabel = new javax.swing.JLabel();
        zipField = new javax.swing.JTextField();
        ownAuthorityField = new javax.swing.JCheckBox();
        dotLabel = new javax.swing.JLabel();
        dotField = new javax.swing.JFormattedTextField();
        tipLabel = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(instructionsLabel, org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.instructionsLabel.text")); // NOI18N

        companyLabel.setDisplayedMnemonic('a');
        companyLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        companyLabel.setLabelFor(companyField);
        org.openide.awt.Mnemonics.setLocalizedText(companyLabel, org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.companyLabel.text")); // NOI18N

        companyField.setText(org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.company.text")); // NOI18N
        companyField.setName("company"); // NOI18N
        companyField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectText(evt);
            }
        });

        contactLabel.setDisplayedMnemonic('o');
        contactLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        contactLabel.setLabelFor(contactField);
        org.openide.awt.Mnemonics.setLocalizedText(contactLabel, org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.contactLabel.text")); // NOI18N

        contactField.setText(org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.contact.text")); // NOI18N
        contactField.setName("contact"); // NOI18N
        contactField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectText(evt);
            }
        });

        phoneLabel.setDisplayedMnemonic('P');
        phoneLabel.setLabelFor(phoneField);
        org.openide.awt.Mnemonics.setLocalizedText(phoneLabel, org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.phoneLabel.text")); // NOI18N

        try {
            phoneField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###) ###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        phoneField.setName("phone"); // NOI18N
        phoneField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectText(evt);
            }
        });

        emailLabel.setDisplayedMnemonic('E');
        emailLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        emailLabel.setLabelFor(emailField);
        org.openide.awt.Mnemonics.setLocalizedText(emailLabel, org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.emailLabel.text")); // NOI18N

        emailField.setText(org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.email.text")); // NOI18N
        emailField.setName("email"); // NOI18N
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectText(evt);
            }
        });

        streetLabel.setDisplayedMnemonic('S');
        streetLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        streetLabel.setLabelFor(streetField);
        org.openide.awt.Mnemonics.setLocalizedText(streetLabel, org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.streetLabel.text")); // NOI18N

        streetField.setText(org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.street.text")); // NOI18N
        streetField.setName("street"); // NOI18N
        streetField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectText(evt);
            }
        });

        suiteLabel.setDisplayedMnemonic('u');
        suiteLabel.setLabelFor(suiteField);
        org.openide.awt.Mnemonics.setLocalizedText(suiteLabel, org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.suiteLabel.text")); // NOI18N

        suiteField.setText(org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.suite.text")); // NOI18N
        suiteField.setName("suite"); // NOI18N
        suiteField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectText(evt);
            }
        });

        cityLabel.setDisplayedMnemonic('C');
        cityLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        cityLabel.setLabelFor(cityField);
        org.openide.awt.Mnemonics.setLocalizedText(cityLabel, org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.cityLabel.text")); // NOI18N

        cityField.setText(org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.city.text")); // NOI18N
        cityField.setName("city"); // NOI18N
        cityField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectText(evt);
            }
        });

        stateLabel.setDisplayedMnemonic('t');
        stateLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        stateLabel.setLabelFor(stateField);
        org.openide.awt.Mnemonics.setLocalizedText(stateLabel, org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.stateLabel.text")); // NOI18N

        stateField.setText(org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.state.text")); // NOI18N
        stateField.setName("state"); // NOI18N
        stateField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectText(evt);
            }
        });

        zipLabel.setDisplayedMnemonic('d');
        zipLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        zipLabel.setLabelFor(zipField);
        org.openide.awt.Mnemonics.setLocalizedText(zipLabel, org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.zipLabel.text")); // NOI18N

        zipField.setText(org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.zip.text")); // NOI18N
        zipField.setName("zip"); // NOI18N
        zipField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectText(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(ownAuthorityField, org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.authority.text")); // NOI18N
        ownAuthorityField.setName("authority"); // NOI18N
        ownAuthorityField.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ownAuthorityFieldItemStateChanged(evt);
            }
        });

        dotLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(dotLabel, org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.dotLabel.text")); // NOI18N

        dotField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#00000"))));
        dotField.setText(org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.dot.text")); // NOI18N
        dotField.setName("dot"); // NOI18N
        dotField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectText(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(tipLabel, org.openide.util.NbBundle.getMessage(CompanyPanel.class, "CompanyPanel.tipLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(instructionsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(contactLabel)
                            .addComponent(companyLabel)
                            .addComponent(streetLabel)
                            .addComponent(cityLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(companyField)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(contactField, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(phoneLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(emailLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailField))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(streetField)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(ownAuthorityField)
                                                .addGap(18, 18, 18)
                                                .addComponent(dotLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(dotField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cityField)
                                                .addGap(18, 18, 18)
                                                .addComponent(stateLabel)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stateField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(zipLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(zipField))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(suiteLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(suiteField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instructionsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyLabel)
                    .addComponent(companyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactLabel)
                    .addComponent(contactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneLabel)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(streetLabel)
                    .addComponent(streetField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suiteLabel)
                    .addComponent(suiteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel)
                    .addComponent(cityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stateLabel)
                    .addComponent(stateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zipLabel)
                    .addComponent(zipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ownAuthorityField)
                    .addComponent(dotLabel)
                    .addComponent(dotField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tipLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ownAuthorityFieldItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ownAuthorityFieldItemStateChanged
        dotLabel.setVisible(ownAuthorityField.isSelected());
        dotField.setVisible(ownAuthorityField.isSelected());
    }//GEN-LAST:event_ownAuthorityFieldItemStateChanged

    private void selectText(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_selectText
        ((javax.swing.text.JTextComponent)evt.getSource()).selectAll();
        
        String tip = "<html>";
        
        switch ( ((javax.swing.text.JTextComponent) evt.getSource()).getName() ) {
            case "company":
                tip += "Company Name. Either your registered "
                        + "company name, or your DBA name. This field is "
                        + "<strong><em>required</em></strong>.";
                break;
            case "contact":
               tip += "Contact person's name. Typically, this would be your "
                       + "name, unless you have someone else act as your "
                       + "company's point of contact, such as your spouse. "
                       + "This field is <strong><em>required</em></strong>.";
               break;
            case "phone":
                tip += "Phone Number. Typically, this would be your cell phone "
                        + "number that you use in the course of your day-to-"
                        + "day business. This field is <em>optional</em>.";
                break;
            case "email":
                tip += "Email Address. This needs to be the same email address "
                        + "you used to register for your thirty-day free trial "
                        + "of Northwind Traders. This field is <strong><em>"
                        + "required</em></strong>.";
                break;
            case "street":
                tip += "Street Address. This is the physical location of your "
                        + "trucking company, which you use for receiving mail "
                        + "and filing your taxes. This field is <strong><em>"
                        + "required</em></strong>.";
                break;
            case "suite":
                tip += "Suite. This is the suite number of your physical "
                        + "location, or a PO Box, if any. This field is "
                        + "<em>optional</em>.";
                break;
            case "city":
                tip += "City. This is the city in which your company is "
                        + "physically located. This field is <strong><em>"
                        + "required</em></strong>.";
                break;
            case "state":
                tip += "State. This is the state in which your company is "
                        + "physically located. This field is <strong><em>"
                        + "required</em></strong>.";
                break;
            case "zip":
                tip += "Postal Code. This is the Postal/Zip Code used for your "
                        + "company's mailing address. This field is <strong>"
                        + "<em>required</em></strong>.";
                break;
            case "authority":
                tip += "This flag lets Northwind Traders know if your company "
                        + "operates on its own authority. If you are leased to "
                        + "a carrier, odds are great that you do not have your "
                        + "own authority, so leave this box <em>unchecked</em>. "
                        + "If, however, you do have your own authority to "
                        + "operate, then check this box and you will need to "
                        + "provide your DOT/ICC Number.";
                break;
            case "dot":
                tip += "If you have your own authority, you need to provide "
                        + "your DOT/ICC number in this field. This field is "
                        + "<em><strong>required</strong>, if you have your "
                        + "own authority</em>.";
                break;
            default:
                tip = "<html>";
                break;
        }
        
        tipLabel.setText(tip);
    }//GEN-LAST:event_selectText

    void load() {
        // TODO read settings and initialize GUI
        // Example:        
        // someCheckBox.setSelected(Preferences.userNodeForPackage(CompanyPanel.class).getBoolean("someFlag", false));
        // or for org.openide.util with API spec. version >= 7.4:
        // someCheckBox.setSelected(NbPreferences.forModule(CompanyPanel.class).getBoolean("someFlag", false));
        // or:
        // someTextField.setText(SomeSystemOption.getDefault().getSomeStringProperty());
        companyField.setText(Preferences.userNodeForPackage(CompanyPanel.class).get("options.company.name", "[NOT SET]"));
        contactField.setText(Preferences.userNodeForPackage(CompanyPanel.class).get("options.company.contact", "[NOT SET]"));
        phoneField.setText(Preferences.userNodeForPackage(CompanyPanel.class).get("options.company.phone", "0000000000"));
        emailField.setText(Preferences.userNodeForPackage(CompanyPanel.class).get("options.company.email", "[NOT SET]"));
        streetField.setText(Preferences.userNodeForPackage(CompanyPanel.class).get("options.company.street", "[NOT SET]"));
        suiteField.setText(Preferences.userNodeForPackage(CompanyPanel.class).get("options.company.suite", "[NOT SET]"));
        cityField.setText(Preferences.userNodeForPackage(CompanyPanel.class).get("options.company.city", "[NOT SET]"));
        stateField.setText(Preferences.userNodeForPackage(CompanyPanel.class).get("options.company.state", "[]"));
        zipField.setText(Preferences.userNodeForPackage(CompanyPanel.class).get("options.company.zip", "00000"));
        ownAuthorityField.setSelected(Preferences.userNodeForPackage(CompanyPanel.class).getBoolean("options.company.authority", false));
        dotField.setText(Preferences.userNodeForPackage(CompanyPanel.class).get("options.company.dot-number", "0000000"));
    }

    void store() {
        // TODO store modified settings
        // Example:
        // Preferences.userNodeForPackage(CompanyPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or for org.openide.util with API spec. version >= 7.4:
        // NbPreferences.forModule(CompanyPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or:
        // SomeSystemOption.getDefault().setSomeStringProperty(someTextField.getText());
        Preferences.userNodeForPackage(CompanyPanel.class).put("options.company.name", companyField.getText());
        Preferences.userNodeForPackage(CompanyPanel.class).put("options.company.contact", contactField.getText());
        Preferences.userNodeForPackage(CompanyPanel.class).put("options.company.phone", phoneField.getText());
        Preferences.userNodeForPackage(CompanyPanel.class).put("options.company.email", emailField.getText());
        Preferences.userNodeForPackage(CompanyPanel.class).put("options.company.street", streetField.getText());
        Preferences.userNodeForPackage(CompanyPanel.class).put("options.company.suite", suiteField.getText());
        Preferences.userNodeForPackage(CompanyPanel.class).put("options.company.city", cityField.getText());
        Preferences.userNodeForPackage(CompanyPanel.class).put("options.company.state", stateField.getText());
        Preferences.userNodeForPackage(CompanyPanel.class).put("options.company.zip", zipField.getText());
        Preferences.userNodeForPackage(CompanyPanel.class).putBoolean("options.company.authority", ownAuthorityField.isSelected());
        Preferences.userNodeForPackage(CompanyPanel.class).put("options.company.dot-number", dotField.getText());
    }

    boolean valid() {
        // Assume not all data is provided.
        boolean company = false;
        boolean contact = false;
        boolean email = false;
        boolean street = false;
        boolean city = false;
        boolean state = false;
        boolean zip = false;
        boolean dot = false;
        
        // The required fields on this option panel are: company, contact, email,
        //+ street, city, state, zip, and, if the own authority checkbox is 
        //+ selected, the DOT/ICC number.
        //+
        //+ we will check each field to be sure that it is:
        //+
        //+     A. Not null.
        //+     B. Not empty.
        //+     C. Not equal to the default value.
        company = companyField.getText() != null 
                && !companyField.getText().isEmpty()
                && !companyField.getText().equalsIgnoreCase("[NOT SET]");
        contact = contactField.getText() != null
                && !contactField.getText().isEmpty()
                && !contactField.getText().equalsIgnoreCase("[NOT SET]");
        email = emailField.getText() != null
                && !emailField.getText().isEmpty()
                && !emailField.getText().equalsIgnoreCase("[NOT SET]");
        street = streetField.getText() != null
                && !streetField.getText().isEmpty()
                && !streetField.getText().equalsIgnoreCase("[NOT SET]");
        city = cityField.getText() != null
                && !cityField.getText().isEmpty()
                && !cityField.getText().equalsIgnoreCase("[NOT SET]");
        state = stateField.getText() != null
                && !stateField.getText().isEmpty()
                && !stateField.getText().equalsIgnoreCase("[]");
        zip = zipField.getText() != null
                && !zipField.getText().isEmpty()
                && !zipField.getText().equals("00000");
        dot = dotField.getText() != null
                && !dotField.getText().isEmpty()
                && !dotField.getText().equals("0000000");
        
        if ( ownAuthorityField.isSelected() ) 
            return company && contact && email && street && city && state && zip
                    && dot;
        else
            return company && contact && email && street && city && state && zip;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cityField;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JTextField companyField;
    private javax.swing.JLabel companyLabel;
    private javax.swing.JTextField contactField;
    private javax.swing.JLabel contactLabel;
    private javax.swing.JFormattedTextField dotField;
    private javax.swing.JLabel dotLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel instructionsLabel;
    private javax.swing.JCheckBox ownAuthorityField;
    private javax.swing.JFormattedTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField stateField;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JTextField streetField;
    private javax.swing.JLabel streetLabel;
    private javax.swing.JTextField suiteField;
    private javax.swing.JLabel suiteLabel;
    private javax.swing.JLabel tipLabel;
    private javax.swing.JTextField zipField;
    private javax.swing.JLabel zipLabel;
    // End of variables declaration//GEN-END:variables
}