/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amimobenja.www;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author afro
 */
public class EditUser extends javax.swing.JFrame {
    public static java.sql.Connection connectDB = null;
    java.lang.String userName = null;
    com.amimobenja.www.DBObject dbObject;

    /**
     * Creates new form UserSetUp
     * @param connDb
     * @param username
     */
    public EditUser(java.sql.Connection connDb, java.lang.String username) {
        connectDB = connDb;
        userName = username;
        dbObject = new com.amimobenja.www.DBObject();
        
        initComponents();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        searchDialog = new javax.swing.JDialog();
        searchDialogPanel = new javax.swing.JPanel();
        searchNumbTxt = new javax.swing.JTextField();
        searchScrollPane = new javax.swing.JScrollPane();
        searchTable = new com.amimobenja.www.JTable();
        searchBtn = new javax.swing.JButton();
        removeUserPanel = new javax.swing.JPanel();
        idLbl = new javax.swing.JLabel();
        otherNameTxt = new javax.swing.JTextField();
        firstNameTxt = new javax.swing.JTextField();
        firstNameLbl = new javax.swing.JLabel();
        otherNameLbl = new javax.swing.JLabel();
        userNameTxt = new javax.swing.JTextField();
        userNameLbl = new javax.swing.JLabel();
        accessLevelCmBx = new javax.swing.JComboBox();
        accessLevelLbl = new javax.swing.JLabel();
        btnPanel = new javax.swing.JPanel();
        updateBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        searchPanel = new javax.swing.JPanel();
        idTxt = new javax.swing.JTextField();
        searchUserBtn = new javax.swing.JButton();
        passwordLbl = new javax.swing.JLabel();
        passwordFieldTxt = new javax.swing.JPasswordField();
        confPasswordLbl = new javax.swing.JLabel();
        confPasswordFieldTxt = new javax.swing.JPasswordField();
        newIDLbl = new javax.swing.JLabel();
        newIDTxt = new javax.swing.JTextField();

        searchDialog.setModal(true);
        searchDialog.setUndecorated(true);
        searchDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        searchDialogPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        searchDialogPanel.setLayout(new java.awt.GridBagLayout());

        searchNumbTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                searchNumbTxtCaretUpdate(evt);
            }
        });
        searchNumbTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchNumbTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        searchDialogPanel.add(searchNumbTxt, gridBagConstraints);

        searchTable.setToolTipText("Click on the target row to Select the Employee from the Search Table.");
        searchTable.setShowHorizontalLines(false);
        searchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchTableMouseClicked(evt);
            }
        });
        searchScrollPane.setViewportView(searchTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        searchDialogPanel.add(searchScrollPane, gridBagConstraints);

        searchBtn.setText("Dispose");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        searchDialogPanel.add(searchBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        searchDialog.getContentPane().add(searchDialogPanel, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Remove User.");
        setFont(new java.awt.Font("Ubuntu Light", 0, 12)); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        removeUserPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search User Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 0, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        removeUserPanel.setLayout(new java.awt.GridBagLayout());

        idLbl.setText("Current National ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(idLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(otherNameTxt, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(firstNameTxt, gridBagConstraints);

        firstNameLbl.setText("First Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(firstNameLbl, gridBagConstraints);

        otherNameLbl.setText("Other Names");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(otherNameLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(userNameTxt, gridBagConstraints);

        userNameLbl.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(userNameLbl, gridBagConstraints);

        accessLevelCmBx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "User" }));
        accessLevelCmBx.setSelectedIndex(1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(accessLevelCmBx, gridBagConstraints);

        accessLevelLbl.setText("Access Level");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(accessLevelLbl, gridBagConstraints);

        btnPanel.setLayout(new java.awt.GridBagLayout());

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        btnPanel.add(updateBtn, gridBagConstraints);

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        btnPanel.add(clearBtn, gridBagConstraints);

        closeBtn.setText("Exit");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        btnPanel.add(closeBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        removeUserPanel.add(btnPanel, gridBagConstraints);

        searchPanel.setLayout(new java.awt.GridBagLayout());

        idTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        searchPanel.add(idTxt, gridBagConstraints);

        searchUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchUserBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        searchPanel.add(searchUserBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(searchPanel, gridBagConstraints);

        passwordLbl.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(passwordLbl, gridBagConstraints);

        passwordFieldTxt.setText("123456");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(passwordFieldTxt, gridBagConstraints);

        confPasswordLbl.setText("Confirm Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(confPasswordLbl, gridBagConstraints);

        confPasswordFieldTxt.setText("789101");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(confPasswordFieldTxt, gridBagConstraints);

        newIDLbl.setText("National ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(newIDLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        removeUserPanel.add(newIDTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(removeUserPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        System.out.println("Save Button Clicked!!!");
        
        boolean admin;
        
        idLbl.setForeground(Color.BLACK);
        firstNameLbl.setForeground(Color.BLACK);
        otherNameLbl.setForeground(Color.BLACK);
        userNameLbl.setForeground(Color.BLACK);
        passwordLbl.setForeground(Color.BLACK);
        confPasswordLbl.setForeground(Color.BLACK); 
        
        char passsWord[] = this.passwordFieldTxt.getPassword();
        
        java.lang.String passWord = java.lang.String.copyValueOf(passsWord);
        
        char passsWordTwo[] = this.confPasswordFieldTxt.getPassword();
        
        java.lang.String passWordTwo = java.lang.String.copyValueOf(passsWordTwo);
        
        if (idTxt.getText().equals("") || firstNameTxt.getText().equals("")
                 || otherNameTxt.getText().equals("") || userNameTxt.getText().equals("") || !passWord.equals(passWordTwo)) {
                        
            if (idTxt.getText().equals("")) {
                idLbl.setForeground(Color.red);
            }  else if (firstNameTxt.getText().equals("")) {
                firstNameLbl.setForeground(Color.red);
            }  else if (otherNameTxt.getText().equals("")) {
                otherNameLbl.setForeground(Color.red);
            }  else if (userNameTxt.getText().equals("")) {
                userNameLbl.setForeground(Color.red);
            }  else if (!passWord.equals(passWordTwo)) {
                passwordLbl.setForeground(Color.RED);
                confPasswordLbl.setForeground(Color.RED);
                JOptionPane.showMessageDialog(null, "Password Do Not Match!!! ",
                    "Password Error!", JOptionPane.INFORMATION_MESSAGE);
            }  else {
                System.out.print("Sorry man Unknown Error!!!");
                JOptionPane.showMessageDialog(null, "Contact the Administrator -- Possibly missing entries. Unknown Error!!!. ", 
                    "Unknown Error!!!", JOptionPane.ERROR_MESSAGE);
            }
        
        } else {
            System.out.print("About to Update the values.!!!");  
            
            if (accessLevelCmBx.getSelectedIndex() == 0) {
                admin = true;            
            } else {
                admin = false;            
            }
            
            try {
                    connectDB.setAutoCommit(false);
                    
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("UPDATE secure_access_tbl SET id = '"+newIDTxt.getText()+"', "
                            + "first_name = '"+firstNameTxt.getText()+"', other_names = '"+otherNameTxt.getText()+"', "
                            + "username = '"+userNameTxt.getText()+"', password = '"+passWord+"', created_by = '"+userName+"', admin = '"+admin+"'"
                            + "WHERE id = '"+idTxt.getText()+"'");
                    
                        pstmt.executeUpdate();
                        connectDB.commit();
                        connectDB.setAutoCommit(true);
                        
                        clear();
                        
                        System.out.print("\nSuccess!!! Data successfully Updated.");
                        JOptionPane.showMessageDialog(null, "User has been Updated successfully. ", 
                            "Success!!! User Updated successfully", JOptionPane.INFORMATION_MESSAGE);
                        
                    
                } catch (java.lang.Exception sq) {

                    try {
                        connectDB.rollback();
                    } catch (java.sql.SQLException sql) {
                        javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                    System.out.println(sq.getMessage());
                    javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            
        }
        
    }//GEN-LAST:event_updateBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clear();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void searchNumbTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_searchNumbTxtCaretUpdate
        if (searchNumbTxt.getCaretPosition() < 2) {
            System.out.println("Nothing...");
        } else {
            System.out.println("Searching...");

            searchTable.setModel(com.amimobenja.www.TableModel.createTableVectors(connectDB,
                "SELECT id, initcap(first_name) || ' '|| initcap(other_names) AS employee_name, "
                + "username FROM secure_access_tbl WHERE id ILIKE '%"+searchNumbTxt.getText()+"%' "
                + "OR username ILIKE '%"+searchNumbTxt.getText()+"%' AND username NOT LIKE '"+userName+"' ORDER BY id"));

        System.out.println("Showing...");

        searchTable.setShowHorizontalLines(false);
        searchTable.getColumnModel().getColumn(0).setPreferredWidth(350);
        searchTable.getColumnModel().getColumn(1).setPreferredWidth(350);
        searchScrollPane.setViewportView(searchTable);
        }
    }//GEN-LAST:event_searchNumbTxtCaretUpdate

    private void searchNumbTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchNumbTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchNumbTxtActionPerformed

    private void searchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchTableMouseClicked

        idTxt.setText(searchTable.getValueAt(searchTable.getSelectedRow(), 0).toString());
        String title = "                           (" + searchTable.getValueAt(searchTable.getSelectedRow(), 0)
        .toString().concat("    ").
        concat(searchTable.getValueAt(searchTable.getSelectedRow(), 1).toString()).concat(")");

        this.setTitle(title);

        populateBioData(searchTable.getValueAt(searchTable.getSelectedRow(), 0).toString());

        this.searchDialog.dispose();
    }//GEN-LAST:event_searchTableMouseClicked

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        this.searchDialog.dispose();
    }//GEN-LAST:event_searchBtnActionPerformed

    private void searchUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchUserBtnActionPerformed
        searchButton();
    }//GEN-LAST:event_searchUserBtnActionPerformed

    private void clear() {
        idTxt.setText("");
        newIDTxt.setText("");
        firstNameTxt.setText("");
        otherNameTxt.setText("");
        userNameTxt.setText("");        
        passwordFieldTxt.setText("123456");
        confPasswordFieldTxt.setText("789101");
        accessLevelCmBx.setSelectedItem("User");
    }
    
    private void searchButton() {

        System.out.println("Showing Employee Search Dialog");
        
        java.awt.Point point = this.idTxt.getLocationOnScreen();

        searchDialog.setSize(500, 200);

        searchDialog.setLocation(point);

        searchDialog.setVisible(true);
        
    }
    
    private void populateBioData(String id) {
        String id_no = "0000", first_name = "Unknown", other_names = "Unknown", user_name = "Unknown";
        Boolean admin = false;
        try {
            connectDB.setAutoCommit(false);
            
            java.sql.PreparedStatement pstm = connectDB.prepareStatement("SELECT id, initcap(first_name), initcap(other_names), "
                    + "username, admin FROM secure_access_tbl WHERE id = '"+id+"'");
            java.sql.ResultSet rst = pstm.executeQuery();
            
            while(rst.next()) {
                id_no = rst.getObject(1).toString();
                first_name = rst.getObject(2).toString();
                other_names = rst.getObject(3).toString();
                user_name = rst.getObject(4).toString();
                admin = Boolean.valueOf(rst.getObject(5).toString());                
            }
            
            newIDTxt.setText(id_no);
            firstNameTxt.setText(first_name);
            otherNameTxt.setText(other_names);
            userNameTxt.setText(user_name);
            
            if (admin == true) {
                accessLevelCmBx.setSelectedItem("Admin");
            } else {
                accessLevelCmBx.setSelectedItem("User");
            }
            
        } catch(SQLException sqlExp) {
            System.out.println("SQL Error - "+sqlExp);
        
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox accessLevelCmBx;
    private javax.swing.JLabel accessLevelLbl;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JPasswordField confPasswordFieldTxt;
    private javax.swing.JLabel confPasswordLbl;
    private javax.swing.JLabel firstNameLbl;
    private javax.swing.JTextField firstNameTxt;
    private javax.swing.JLabel idLbl;
    private javax.swing.JTextField idTxt;
    private javax.swing.JLabel newIDLbl;
    private javax.swing.JTextField newIDTxt;
    private javax.swing.JLabel otherNameLbl;
    private javax.swing.JTextField otherNameTxt;
    private javax.swing.JPasswordField passwordFieldTxt;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JPanel removeUserPanel;
    private javax.swing.JButton searchBtn;
    private javax.swing.JDialog searchDialog;
    private javax.swing.JPanel searchDialogPanel;
    private javax.swing.JTextField searchNumbTxt;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JScrollPane searchScrollPane;
    private javax.swing.JTable searchTable;
    private javax.swing.JButton searchUserBtn;
    private javax.swing.JButton updateBtn;
    private javax.swing.JLabel userNameLbl;
    private javax.swing.JTextField userNameTxt;
    // End of variables declaration//GEN-END:variables

    
}
