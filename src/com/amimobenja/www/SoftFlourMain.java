/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amimobenja.www;

import com.amimobenja.www.helpers.ClearTable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author afro
 */
public class SoftFlourMain extends javax.swing.JFrame {
    public static java.sql.Connection connectDB = null;
    java.lang.String userName = null;
    com.amimobenja.www.helpers.DBObject dbObject;
    
    java.util.Calendar cal = java.util.Calendar.getInstance();
    java.util.Date todayDate = null;
    
    private int offset;
    private int limit;
    
    int generalReport = 1;
    

    /**
     * Creates new form SoftFlourMain1
     * @param connDb
     * @param pConnectDB
     * @param username
     */
    public SoftFlourMain(java.sql.Connection connDb, java.lang.String username) {
        connectDB = connDb;
        userName = username;
        
        dbObject = new com.amimobenja.www.helpers.DBObject();
        todayDate = cal.getTime();
        
        initComponents();
        
        setadmin();
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date todayDate = cal.getTime();
        dateChooser.setDate(todayDate);
        
        populateTextFields();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        
        offset =0;
        limit = 30;
        ClearTable.clearthisTable(tableValuesTbl);
        ShowVisibleQueryItems(offset);
        
        nextBtn.setEnabled(true);
    }
    
    private void ShowVisibleQueryItems(Integer offset){
     
     try {
         System.out.println("SELECT date, maize_bag_bought, total_cost_of_maize_bags, store_maize_bags, tins_sold, tin_sld_cash, "
                 + "posh_unit_start, posh_unit_end, ele_posh_cost, maint_item, maint_cost, profit  FROM soft_floor_main_tbl "
                + "ORDER BY date DESC LIMIT "+tableValuesTbl.getRowCount()+" OFFSET - "+offset+"");
         
         
        PreparedStatement pst = connectDB.prepareStatement("SELECT date, maize_bag_bought, total_cost_of_maize_bags, store_maize_bags, "
                + "tins_sold, tin_sld_cash, posh_unit_start, posh_unit_end, "
                + "ele_posh_cost, maint_item, maint_cost, profit  FROM soft_floor_main_tbl "
                + "ORDER BY date DESC LIMIT "+tableValuesTbl.getRowCount()+" OFFSET - "+offset+"");
            
            
            ResultSet rset = pst.executeQuery();
            int i=0;
            while(rset.next()){
               tableValuesTbl.setValueAt(rset.getObject(1), i, 0);
               tableValuesTbl.setValueAt(rset.getObject(2), i, 1);
               tableValuesTbl.setValueAt(rset.getObject(3), i, 2);
               tableValuesTbl.setValueAt(rset.getObject(4), i, 3);
               tableValuesTbl.setValueAt(rset.getObject(5), i, 4);
               tableValuesTbl.setValueAt(rset.getObject(6), i, 5);
               tableValuesTbl.setValueAt(rset.getObject(7), i, 6);
               tableValuesTbl.setValueAt(rset.getObject(8), i, 7);
               tableValuesTbl.setValueAt(rset.getObject(9), i, 8);
               tableValuesTbl.setValueAt(rset.getObject(10), i, 9);
               tableValuesTbl.setValueAt(rset.getObject(11), i, 10);
               tableValuesTbl.setValueAt(rset.getObject(12), i, 11);
               
               
               for (int a=1; a <= 12; a++) {
                   System.out.println("Values - "+rset.getObject(a));
               }
            
               i++;
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(EditMaizeBagBought.class.getName()).log(Level.SEVERE, null, ex);
        }   
     
     
     
     }
    
    
    public final void populateTextFields() {
        String no_mbg_bght = null, total_cost = null, rem_bags = null;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");
        
        Date today = new Date();
        try {

                System.out.println("About to Get the Maize Bags");

                java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT SUM(no_mbg_bght), SUM(total_cost), SUM(rem_bags) FROM maize_bags_tbl");
                java.sql.ResultSet rse = pst.executeQuery();

                System.out.println("To Get the Maize Bags");

                while (rse.next()) {
                    System.out.println("Getting the Maize Bags");
                    no_mbg_bght = dbObject.getDBObject(rse.getObject(1), "0");
                    total_cost = dbObject.getDBObject(rse.getObject(2), "0");
                    rem_bags = dbObject.getDBObject(rse.getObject(3), "0");

                }
                
                maizeBagsBoughtTxt.setText(no_mbg_bght);
                maizeBagTotalCostTxt.setText(new com.amimobenja.www.helpers.Format2Currency().Format2Currency(total_cost));
                maizeBagsTxt.setText(rem_bags);                
                
                houseUnitStartTxt.setText("0.00");
                houseUnitEndTxt.setText("0.00");
                houseUnitTxt.setText("0.00");
                                
                mainenanceItemNameTxt.setText(sdf.format(today)+" MC");
                mainenanceCostTxt.setText("0.00");
                
                codeMeaningTextArea.setText("MBB - Maize Bags Bought,                    TCMB - Total Cost of the Maize Bags,                    "
                        + "SMB - Store Maize Bags, "
                        + "\nT/S - Tins Sold,                                      TS/C - Tins Sold Cash,                                          PUS - Posho Mill Unit Start,           "
                        + " PUE - Posho Mill Unit End, "
                        + "\nEPC - Electricity Cost for Posho Mill,                                   MD - Maintenance Date MC - Maintenance Cost,"
                        + "                                     P(Ksh) - Profit(Ksh) ");
                
                System.out.println("The Maize Bags should be set!");

            } catch (SQLException es) {
                System.out.println(es);

            }
    }
    
    public void populateTinPriceField() {
        String price = "0", cash = "0"; 
        
        Double z = 0.00, value = 0.00;
        
        String est_tins = null, rembags = "0.00", rem_bags = null;
        
        try {

                System.out.println("About to Get the Maize Bags");

                java.sql.PreparedStatement pstOne = connectDB.prepareStatement("SELECT est_tins, rem_bags FROM maize_bags_tbl");
                java.sql.ResultSet rseOne = pstOne.executeQuery();

                System.out.println("To Get the Maize Bags");

                while (rseOne.next()) {
                    System.out.println("Getting the Maize Bags");
                    est_tins = dbObject.getDBObject(rseOne.getObject(1), "0");
                    rem_bags = dbObject.getDBObject(rseOne.getObject(2), "0");

                }
                System.out.println("Remaining Tins - "+est_tins);
                
                if (est_tins == null) {
                    z = 0.00;                    
                } else {
                    z = Double.valueOf(est_tins);
                }
                value = z/(Double.valueOf(maizeBagsBoughtTxt.getText()));
                
                rembags = Double.toString((Math.round((((Double.valueOf(rem_bags))-(Double.valueOf(tinsSoldTxt.getText())/value)))*1000))/1000.0);
                
                maizeBagsTxt.setText(rembags);
                
                if (Double.valueOf(maizeBagsTxt.getText()) <= 0.25) {
                    System.out.println("ADD MORE STOCK");
                    JOptionPane.showMessageDialog(null, "You cannot sell beyond the remaining stock of available maize tins. \n\nPlease Add more Stock!", 
                            "Minimum Stock Level!!!", JOptionPane.INFORMATION_MESSAGE);
                    
                    tinsSoldTxt.setText("");
                    tinsSoldCashRaisedTxt.setText("");
                    populateTextFields();
                }
                
                
                System.out.println("Remaining Tins - "+est_tins);
                System.out.println("Z - "+z);
                System.out.println("The Maize Tins should be set!");

            } catch (SQLException es) {
                System.out.println(es);

            }
        
        try {

                System.out.println("About to Get the Tin Price");

                java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT price FROM tin_prices_tbl");
                java.sql.ResultSet rse = pst.executeQuery();

                System.out.println("To Get the Tin Price");

                while (rse.next()) {
                    System.out.println("Getting the Tin Price");
                    price = dbObject.getDBObject(rse.getObject(1), "0");

                }
                cash = Double.toString(((Double.valueOf(tinsSoldTxt.getText()))*(Double.valueOf(price))));
                
                tinsSoldCashRaisedTxt.setText(new com.amimobenja.www.helpers.Format2Currency().Format2Currency(cash));
                
                System.out.println("The Tin Price should be set! - "+price);

            } catch (final Exception es) {
                System.out.println(es);

            }
        
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

        softFlourMillersTabbedPane = new javax.swing.JTabbedPane();
        mainPanel = new javax.swing.JPanel();
        labelPanel = new javax.swing.JPanel();
        inforLbl = new javax.swing.JLabel();
        previousMainPanel = new javax.swing.JPanel();
        dateLbl = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        maizeBagsLbl = new javax.swing.JLabel();
        maizeBagsBoughtTxt = new javax.swing.JTextField();
        maizeBagsTxt = new javax.swing.JTextField();
        tinsSoldLbl = new javax.swing.JLabel();
        tinsSoldTxt = new javax.swing.JTextField();
        tinsSoldCashRaisedTxt = new javax.swing.JTextField();
        maizeBagTotalCostTxt = new javax.swing.JTextField();
        electricityUsedPanel = new javax.swing.JPanel();
        houseElecUsedLbl = new javax.swing.JLabel();
        startLbl = new javax.swing.JLabel();
        poshMillElecUsedLbl = new javax.swing.JLabel();
        endLbl = new javax.swing.JLabel();
        poshMillUnitStartTxt = new javax.swing.JTextField();
        houseUnitStartTxt = new javax.swing.JTextField();
        poshMillUnitEndTxt = new javax.swing.JTextField();
        houseUnitTxt = new javax.swing.JTextField();
        houseUnitEndTxt = new javax.swing.JTextField();
        profitRaisedTxt = new javax.swing.JTextField();
        poshMillUnitTxt = new javax.swing.JTextField();
        btnPanel = new javax.swing.JPanel();
        saveBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        mainenanceItemNameTxt = new javax.swing.JTextField();
        mainenanceCostTxt = new javax.swing.JTextField();
        searchUserBtn = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        tableValuesScrollPane = new javax.swing.JScrollPane();
        tableValuesTbl = new com.amimobenja.www.helpers.JTable();
        codeMainingPanel = new javax.swing.JPanel();
        codeMeaningScrollPane = new javax.swing.JScrollPane();
        codeMeaningTextArea = new javax.swing.JTextArea();
        btnNavigationPanel = new javax.swing.JPanel();
        naviEditBtn = new javax.swing.JButton();
        navRestBtn = new javax.swing.JButton();
        previousBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        softflourMainBar = new javax.swing.JMenuBar();
        setUpMnu = new javax.swing.JMenu();
        tinPricesMnuItm = new javax.swing.JMenuItem();
        maizeBagMnuItm = new javax.swing.JMenuItem();
        userManagementMnu = new javax.swing.JMenu();
        userMnuItm = new javax.swing.JMenuItem();
        editUserMnuItm = new javax.swing.JMenuItem();
        removeUserMnItm = new javax.swing.JMenuItem();
        logOutMnuItm = new javax.swing.JMenuItem();
        exitMnuItm = new javax.swing.JMenuItem();
        editMnu = new javax.swing.JMenu();
        removeMnuItm = new javax.swing.JMenuItem();
        reportsMnu = new javax.swing.JMenu();
        periodicMnuItm = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SMOOTHFLOUR MILLERS MAIN");
        setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        mainPanel.setLayout(new java.awt.GridBagLayout());

        labelPanel.setLayout(new java.awt.GridBagLayout());

        inforLbl.setForeground(new java.awt.Color(0, 51, 255));
        inforLbl.setText("                      SmoothFlour Millers was Developed and Tested by Amimo Benja. You can contact him using the Email Address:- amimobenja08@gmail.com");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        labelPanel.add(inforLbl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        mainPanel.add(labelPanel, gridBagConstraints);

        previousMainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Todays Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 51, 204)));
        previousMainPanel.setLayout(new java.awt.GridBagLayout());

        dateLbl.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        previousMainPanel.add(dateLbl, gridBagConstraints);

        dateChooser.setForeground(new java.awt.Color(0, 0, 204));
        dateChooser.setToolTipText("Today's Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        previousMainPanel.add(dateChooser, gridBagConstraints);

        maizeBagsLbl.setText("Current Store Maize Bags");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        previousMainPanel.add(maizeBagsLbl, gridBagConstraints);

        maizeBagsBoughtTxt.setEditable(false);
        maizeBagsBoughtTxt.setToolTipText("Click to Update the Changes.");
        maizeBagsBoughtTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. of Maize Bags Bought", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 204)));
        maizeBagsBoughtTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maizeBagsBoughtTxtMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        previousMainPanel.add(maizeBagsBoughtTxt, gridBagConstraints);

        maizeBagsTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        previousMainPanel.add(maizeBagsTxt, gridBagConstraints);

        tinsSoldLbl.setText("Tins Sold");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        previousMainPanel.add(tinsSoldLbl, gridBagConstraints);

        tinsSoldTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tinsSoldTxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        previousMainPanel.add(tinsSoldTxt, gridBagConstraints);

        tinsSoldCashRaisedTxt.setEditable(false);
        tinsSoldCashRaisedTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tins Sold Cash Raised (KSH.)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 51, 255)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        previousMainPanel.add(tinsSoldCashRaisedTxt, gridBagConstraints);

        maizeBagTotalCostTxt.setEditable(false);
        maizeBagTotalCostTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Cost (KSH.)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        previousMainPanel.add(maizeBagTotalCostTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        mainPanel.add(previousMainPanel, gridBagConstraints);

        electricityUsedPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Electricity Used Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 51, 255)));
        electricityUsedPanel.setLayout(new java.awt.GridBagLayout());

        houseElecUsedLbl.setText("House");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        electricityUsedPanel.add(houseElecUsedLbl, gridBagConstraints);

        startLbl.setText("Unit Start");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        electricityUsedPanel.add(startLbl, gridBagConstraints);

        poshMillElecUsedLbl.setText("Posh-Mill");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        electricityUsedPanel.add(poshMillElecUsedLbl, gridBagConstraints);

        endLbl.setText("Unit End");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        electricityUsedPanel.add(endLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        electricityUsedPanel.add(poshMillUnitStartTxt, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        electricityUsedPanel.add(houseUnitStartTxt, gridBagConstraints);

        poshMillUnitEndTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                poshMillUnitEndTxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        electricityUsedPanel.add(poshMillUnitEndTxt, gridBagConstraints);

        houseUnitTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Elec. House Cost", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 51, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        electricityUsedPanel.add(houseUnitTxt, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        electricityUsedPanel.add(houseUnitEndTxt, gridBagConstraints);

        profitRaisedTxt.setEditable(false);
        profitRaisedTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Profit (+/-)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 51, 204)));
        profitRaisedTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profitRaisedTxtMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        electricityUsedPanel.add(profitRaisedTxt, gridBagConstraints);

        poshMillUnitTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Elec. Posh-Mill Cost", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 255)));
        poshMillUnitTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                poshMillUnitTxtCaretUpdate(evt);
            }
        });
        poshMillUnitTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                poshMillUnitTxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        electricityUsedPanel.add(poshMillUnitTxt, gridBagConstraints);

        btnPanel.setLayout(new java.awt.GridBagLayout());

        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        btnPanel.add(saveBtn, gridBagConstraints);

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
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        electricityUsedPanel.add(btnPanel, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Maintenance Cost Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        mainenanceItemNameTxt.setEditable(false);
        mainenanceItemNameTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Today's Maintenance Cost", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 255)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(mainenanceItemNameTxt, gridBagConstraints);

        mainenanceCostTxt.setEditable(false);
        mainenanceCostTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cost", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 255)));
        mainenanceCostTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mainenanceCostTxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(mainenanceCostTxt, gridBagConstraints);

        searchUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchUserBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(searchUserBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        electricityUsedPanel.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        mainPanel.add(electricityUsedPanel, gridBagConstraints);

        softFlourMillersTabbedPane.addTab("Daily Entries", mainPanel);

        tablePanel.setLayout(new java.awt.GridBagLayout());

        tableValuesTbl.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tableValuesTbl.setForeground(new java.awt.Color(0, 0, 255));
        tableValuesTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DATE", "MBB", "TCMB", "SMB", "T/S", "TS/C", "PUS", "PUE", "EPC", "MD", "MC", "P(Ksh)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableValuesTbl.setToolTipText("Right Click on the Table for Several Other Options.");
        tableValuesTbl.getTableHeader().setReorderingAllowed(false);
        tableValuesScrollPane.setViewportView(tableValuesTbl);
        if (tableValuesTbl.getColumnModel().getColumnCount() > 0) {
            tableValuesTbl.getColumnModel().getColumn(0).setMinWidth(120);
            tableValuesTbl.getColumnModel().getColumn(0).setPreferredWidth(120);
            tableValuesTbl.getColumnModel().getColumn(0).setMaxWidth(120);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        tablePanel.add(tableValuesScrollPane, gridBagConstraints);

        codeMainingPanel.setLayout(new java.awt.GridBagLayout());

        codeMeaningTextArea.setEditable(false);
        codeMeaningTextArea.setColumns(20);
        codeMeaningTextArea.setLineWrap(true);
        codeMeaningTextArea.setRows(5);
        codeMeaningTextArea.setWrapStyleWord(true);
        codeMeaningTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Table Code Meanings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 51, 255)));
        codeMeaningScrollPane.setViewportView(codeMeaningTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        codeMainingPanel.add(codeMeaningScrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tablePanel.add(codeMainingPanel, gridBagConstraints);

        btnNavigationPanel.setBackground(new java.awt.Color(223, 220, 217));
        btnNavigationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnNavigationPanel.setLayout(new java.awt.GridBagLayout());

        naviEditBtn.setText("   Edit Entries   ");
        naviEditBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        naviEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naviEditBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        btnNavigationPanel.add(naviEditBtn, gridBagConstraints);

        navRestBtn.setText("Reset Table");
        navRestBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        navRestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navRestBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        btnNavigationPanel.add(navRestBtn, gridBagConstraints);

        previousBtn.setBackground(new java.awt.Color(204, 204, 204));
        previousBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PREV1.GIF"))); // NOI18N
        previousBtn.setEnabled(false);
        previousBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        btnNavigationPanel.add(previousBtn, gridBagConstraints);

        nextBtn.setBackground(new java.awt.Color(204, 204, 204));
        nextBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NEXT1.GIF"))); // NOI18N
        nextBtn.setEnabled(false);
        nextBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nextBtn.setPreferredSize(new java.awt.Dimension(95, 25));
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        btnNavigationPanel.add(nextBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        tablePanel.add(btnNavigationPanel, gridBagConstraints);

        softFlourMillersTabbedPane.addTab("Record Table", tablePanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(softFlourMillersTabbedPane, gridBagConstraints);

        setUpMnu.setText("Set Up");

        tinPricesMnuItm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_16x16/Recycle Bin Empty 4.png"))); // NOI18N
        tinPricesMnuItm.setText("Tin Prices");
        tinPricesMnuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tinPricesMnuItmActionPerformed(evt);
            }
        });
        setUpMnu.add(tinPricesMnuItm);

        maizeBagMnuItm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_16x16/My Icons.png"))); // NOI18N
        maizeBagMnuItm.setText("Maize Bags");
        maizeBagMnuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maizeBagMnuItmActionPerformed(evt);
            }
        });
        setUpMnu.add(maizeBagMnuItm);

        userManagementMnu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_16x16/Users 1.png"))); // NOI18N
        userManagementMnu.setText("User Management");

        userMnuItm.setText("Add User");
        userMnuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userMnuItmActionPerformed(evt);
            }
        });
        userManagementMnu.add(userMnuItm);

        editUserMnuItm.setText("Edit User");
        editUserMnuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUserMnuItmActionPerformed(evt);
            }
        });
        userManagementMnu.add(editUserMnuItm);

        removeUserMnItm.setText("Remove User");
        removeUserMnItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeUserMnItmActionPerformed(evt);
            }
        });
        userManagementMnu.add(removeUserMnItm);

        setUpMnu.add(userManagementMnu);

        logOutMnuItm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_16x16/Download.png"))); // NOI18N
        logOutMnuItm.setText("Log Out");
        logOutMnuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutMnuItmActionPerformed(evt);
            }
        });
        setUpMnu.add(logOutMnuItm);

        exitMnuItm.setText("Exit");
        exitMnuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMnuItmActionPerformed(evt);
            }
        });
        setUpMnu.add(exitMnuItm);

        softflourMainBar.add(setUpMnu);

        editMnu.setText("Edit");

        removeMnuItm.setText("Maize Bags Details");
        removeMnuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMnuItmActionPerformed(evt);
            }
        });
        editMnu.add(removeMnuItm);

        softflourMainBar.add(editMnu);

        reportsMnu.setText("Reports");

        periodicMnuItm.setText("General Periodic Reports");
        periodicMnuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                periodicMnuItmActionPerformed(evt);
            }
        });
        reportsMnu.add(periodicMnuItm);

        softflourMainBar.add(reportsMnu);

        setJMenuBar(softflourMainBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tinsSoldTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tinsSoldTxtKeyReleased
        populateTinPriceField();
    }//GEN-LAST:event_tinsSoldTxtKeyReleased

    private void tinPricesMnuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tinPricesMnuItmActionPerformed
        com.amimobenja.www.TinPrices tinPrices = new com.amimobenja.www.TinPrices(connectDB, userName);
        tinPrices.setVisible(true);
    }//GEN-LAST:event_tinPricesMnuItmActionPerformed

    private void maizeBagMnuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maizeBagMnuItmActionPerformed
        com.amimobenja.www.MaizeBagBought maizeBagPrices = new com.amimobenja.www.MaizeBagBought(connectDB, userName);
        maizeBagPrices.setVisible(true);
    }//GEN-LAST:event_maizeBagMnuItmActionPerformed

    private void maizeBagsBoughtTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maizeBagsBoughtTxtMouseClicked
        populateTextFields();
    }//GEN-LAST:event_maizeBagsBoughtTxtMouseClicked

    private void poshMillUnitEndTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_poshMillUnitEndTxtKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_poshMillUnitEndTxtKeyReleased

    private void poshMillUnitTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_poshMillUnitTxtKeyReleased
        if ((!tinsSoldTxt.getText().equals("")) && tinsSoldCashRaisedTxt.getText().replaceAll(",", "").matches("^[0-9]+(\\.[0-9]{1,4})?$") && 
                poshMillUnitTxt.getText().replaceAll(",", "").matches("^[0-9]+(\\.[0-9]{1,4})?$")) {
            profitRaisedTxt.setText(new com.amimobenja.www.helpers.Format2Currency().Format2Currency
                    (Double.toString(Double.valueOf(tinsSoldCashRaisedTxt.getText().replaceAll(",", "")) - 
                            Double.valueOf(poshMillUnitTxt.getText().replaceAll(",", "")))));        
        } else {
            if (tinsSoldTxt.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Please, Enter the No. of Tins Sold!!!", "Empty Value!!!", JOptionPane.ERROR_MESSAGE);            
            } else if (!poshMillUnitTxt.getText().replaceAll(",", "").matches("^[0-9]+(\\.[0-9]{1,4})?$")){
                JOptionPane.showMessageDialog(rootPane, "Ensure that Elec. Posh-Mill Cost has legitmate values and NOT - '"+poshMillUnitTxt.getText()+"' ",
                        "Unknown Value!!!", JOptionPane.ERROR_MESSAGE);  
            }
            
        }
    }//GEN-LAST:event_poshMillUnitTxtKeyReleased

    private void poshMillUnitTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_poshMillUnitTxtCaretUpdate
        
    }//GEN-LAST:event_poshMillUnitTxtCaretUpdate

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        System.out.println("Save Button Clicked!!!");
        if (!(maizeBagsBoughtTxt.getText().equals("")) && (maizeBagsBoughtTxt.getText().matches("^[0-9]+(\\.[0-9]{1,4})?$")) && 
                !(maizeBagsTxt.getText().equals("")) && (maizeBagsTxt.getText().matches("^[0-9]+(\\.[0-9]{1,4})?$")) && 
                !(tinsSoldTxt.getText().equals("")) && (tinsSoldTxt.getText().matches("^[0-9]+(\\.[0-9]{1,4})?$")) && 
                !(poshMillUnitTxt.getText().equals("")) && (poshMillUnitTxt.getText().matches("^[0-9]+(\\.[0-9]{1,4})?$")&& 
                !(mainenanceCostTxt.getText().equals("")) && (mainenanceCostTxt.getText().matches("^[0-9]+(\\.[0-9]{1,4})?$")))) {
            System.out.print("About to insert the values.!!!");
            
            try {
                System.out.println("1 -- Code executed upto Here!");
                    connectDB.setAutoCommit(false);
                    
                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO soft_floor_main_tbl(date, maize_bag_bought, "
                            + "total_cost_of_maize_bags, store_maize_bags, tins_sold, tin_sld_cash, "
                            + "posh_unit_start, posh_unit_end, house_unit_start, house_unit_end, "
                            + "ele_posh_cost, ele_house_cost, maint_item, maint_cost, profit, user_name) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    
                    System.out.println("2 -- Code executed upto Here!");
                        pstmt.setDate(1, com.amimobenja.www.helpers.SQLDateFormat.getSQLDate(dateChooser.getDate()));
                        pstmt.setDouble(2, Double.valueOf(maizeBagsBoughtTxt.getText()));
                        pstmt.setDouble(3, Double.valueOf(maizeBagTotalCostTxt.getText().replace(",", "")));
                        pstmt.setDouble(4, Double.valueOf(maizeBagsTxt.getText()));
                        pstmt.setDouble(5, Double.valueOf(tinsSoldTxt.getText()));
                        pstmt.setDouble(6, Double.valueOf(tinsSoldCashRaisedTxt.getText().replace(",", "")));
                        pstmt.setDouble(7, Double.valueOf(poshMillUnitStartTxt.getText()));
                        pstmt.setDouble(8, Double.valueOf(poshMillUnitEndTxt.getText()));
                        pstmt.setDouble(9, Double.valueOf(houseUnitStartTxt.getText()));
                        pstmt.setDouble(10, Double.valueOf(houseUnitEndTxt.getText()));
                        pstmt.setDouble(11, Double.valueOf(poshMillUnitTxt.getText()));
                        pstmt.setDouble(12, Double.valueOf(houseUnitTxt.getText()));
                        pstmt.setString(13, mainenanceItemNameTxt.getText().trim());
                        pstmt.setDouble(14, Double.valueOf(mainenanceCostTxt.getText()));
                        pstmt.setDouble(15, Double.valueOf(profitRaisedTxt.getText().replace(",", "")));
                        pstmt.setString(16, userName);
                        
                        System.out.println("3 -- Code executed upto Here!");
                        pstmt.executeUpdate();                       
                        connectDB.commit();
                        connectDB.setAutoCommit(true);
                        
                        System.out.println("4 -- Code executed upto Here!");
                        
                        update();   
                        
                        System.out.println("5 -- Code executed upto Here!");
                                
                        clear();
                        
                        tableValuesTbl.setModel(com.amimobenja.www.helpers.TableModel.createTableVectors(connectDB, 
                            "SELECT date, maize_bag_bought, total_cost_of_maize_bags, store_maize_bags, tins_sold, tin_sld_cash, "
                                    + "posh_unit_start, posh_unit_end, ele_posh_cost, maint_item, maint_cost, profit  FROM soft_floor_main_tbl ORDER BY date"));
                                               
                        
                        System.out.print("\nSuccess!!! Todays Daily Soft Floor Details saved successfully");
                        JOptionPane.showMessageDialog(null, "Success!!! Today's Daily Soft Floor have been saved successfully. ", 
                            "Success!!! Daily Soft Floor saved successfully", JOptionPane.INFORMATION_MESSAGE);

                        
                    
                } catch(java.sql.SQLException sq){
                    
                    javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);

                    try {
                        connectDB.rollback();
                    }catch (java.sql.SQLException sql){
                        javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                    }

                }
            
        } else {            
            System.out.println("Something has been NOTED!!!");
            if (maizeBagsBoughtTxt.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "The Text Field No. of Maize Bag Bought is Empty.", "Empty Fields Noted! ", JOptionPane.ERROR_MESSAGE);
            } else if (maizeBagTotalCostTxt.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "The Total Cost is Empty.", "Empty Fields Noted! ", JOptionPane.ERROR_MESSAGE);
            } else if (maizeBagsTxt.getText().equals("")) {
                maizeBagsLbl.setForeground(Color.red);
                JOptionPane.showMessageDialog(rootPane, "The Text Field Current Store Maize Bags is Empty.", "Empty Fields Noted! ", JOptionPane.ERROR_MESSAGE);
            } else if (tinsSoldTxt.getText().equals("")) {
                tinsSoldLbl.setForeground(Color.red);
                JOptionPane.showMessageDialog(rootPane, "The Text Field Tins Sold is Empty.", "Empty Fields Noted! ", JOptionPane.ERROR_MESSAGE);
            }  else if (poshMillUnitTxt.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "The Text Field Elec. Posh-Mill Cost is Empty.", "Empty Fields Noted! ", JOptionPane.ERROR_MESSAGE);
            }   else if (profitRaisedTxt.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "The Text Field Profit(+/-) is Empty.", "Empty Fields Noted! ", JOptionPane.ERROR_MESSAGE);
                      
            } 
            
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clear();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
       exit();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void userMnuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userMnuItmActionPerformed
        com.amimobenja.www.UserSetUp userSetUp = new com.amimobenja.www.UserSetUp(connectDB, userName);
        userSetUp.setVisible(true);
    }//GEN-LAST:event_userMnuItmActionPerformed

    private void mainenanceCostTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mainenanceCostTxtKeyReleased
        if ((mainenanceCostTxt.getText().matches("^[0-9]+(\\.[0-9]{1,4})?$")) && (!mainenanceCostTxt.getText().equals(""))) {
            System.out.println("Match Made!!!");
            profitRaisedTxt.setText(new com.amimobenja.www.helpers.Format2Currency().Format2Currency
                    (Double.toString(Double.valueOf(tinsSoldCashRaisedTxt.getText().replaceAll(",", "")) - 
                            Double.valueOf(poshMillUnitTxt.getText().replaceAll(",", ""))  - Double.valueOf(mainenanceCostTxt.getText()))));        
        }
    }//GEN-LAST:event_mainenanceCostTxtKeyReleased

    private void removeUserMnItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeUserMnItmActionPerformed
        com.amimobenja.www.RemoveUser removeUser = new com.amimobenja.www.RemoveUser(connectDB, userName);
        removeUser.setVisible(true);
    }//GEN-LAST:event_removeUserMnItmActionPerformed

    private void editUserMnuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserMnuItmActionPerformed
        com.amimobenja.www.EditUser editUser = new com.amimobenja.www.EditUser(connectDB, userName);
        editUser.setVisible(true);
    }//GEN-LAST:event_editUserMnuItmActionPerformed

    private void logOutMnuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutMnuItmActionPerformed
        this.dispose();
        com.amimobenja.www.LoginForm loginForm = new com.amimobenja.www.LoginForm(connectDB);
        loginForm.setVisible(true);
        
    }//GEN-LAST:event_logOutMnuItmActionPerformed

    private void exitMnuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMnuItmActionPerformed
        exit();        
    }//GEN-LAST:event_exitMnuItmActionPerformed

    private void periodicMnuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_periodicMnuItmActionPerformed
        com.amimobenja.www.GeneralPeriodicReport genPRpt = new com.amimobenja.www.GeneralPeriodicReport(this, true, this.generalReport, connectDB, userName);

        genPRpt.setVisible(true);
    }//GEN-LAST:event_periodicMnuItmActionPerformed

    private void removeMnuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMnuItmActionPerformed
        com.amimobenja.www.EditMaizeBagBought editMzb = new com.amimobenja.www.EditMaizeBagBought(connectDB, userName);
        editMzb.setVisible(true);
    }//GEN-LAST:event_removeMnuItmActionPerformed

    private void naviEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naviEditBtnActionPerformed

        offset = 0;
        limit = 30;
        ClearTable.clearthisTable(tableValuesTbl);
        ShowVisibleQueryItems(offset);

        nextBtn.setEnabled(true);
    }//GEN-LAST:event_naviEditBtnActionPerformed

    private void navRestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_navRestBtnActionPerformed
        limit = 30;
        ClearTable.clearthisTable(tableValuesTbl);
        ShowVisibleQueryItems(offset);

        nextBtn.setEnabled(false);
        previousBtn.setEnabled(false);
    }//GEN-LAST:event_navRestBtnActionPerformed

    private void previousBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousBtnActionPerformed
        // TODO add your handling code here:
        if(offset>0){
            offset= offset-tableValuesTbl.getRowCount();

            ClearTable.clearthisTable(tableValuesTbl);
            ShowVisibleQueryItems(offset);}
        else{

            JOptionPane.showMessageDialog(rootPane, "The End of the File.", "End of Table List", JOptionPane.INFORMATION_MESSAGE);
            previousBtn.setEnabled(false);
        }
    }//GEN-LAST:event_previousBtnActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed

        offset= offset+tableValuesTbl.getRowCount();

        ClearTable.clearthisTable(tableValuesTbl);
        ShowVisibleQueryItems(offset);

        previousBtn.setEnabled(true);
    }//GEN-LAST:event_nextBtnActionPerformed

    private void searchUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchUserBtnActionPerformed
        com.amimobenja.www.MaintenanceItemsCost mainCost = new com.amimobenja.www.MaintenanceItemsCost(connectDB, userName);
        mainCost.setVisible(true);
    }//GEN-LAST:event_searchUserBtnActionPerformed

    private void profitRaisedTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profitRaisedTxtMouseClicked
        if ((mainenanceCostTxt.getText().matches("^[0-9]+(\\.[0-9]{1,4})?$")) && !(mainenanceCostTxt.getText().equals("")) 
                && !(tinsSoldCashRaisedTxt.getText().equals(""))) {
            System.out.println("Match Made!!!");
            profitRaisedTxt.setText(new com.amimobenja.www.helpers.Format2Currency().Format2Currency
                    (Double.toString(Double.valueOf(tinsSoldCashRaisedTxt.getText().replaceAll(",", "")) - 
                            Double.valueOf(poshMillUnitTxt.getText().replaceAll(",", ""))  - Double.valueOf(mainenanceCostTxt.getText()))));        
        } else {
            JOptionPane.showMessageDialog(null, "Ensure that some amount has been entered for the Cash Raised by the Solded Tins.", 
                    "Empty Values Noted!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_profitRaisedTxtMouseClicked
    
    private void clear() {
        populateTextFields();
        
        offset = 0;
        limit = 30;
        ClearTable.clearthisTable(tableValuesTbl);
        ShowVisibleQueryItems(offset);

        nextBtn.setEnabled(true);
        tinsSoldTxt.setText("");
        tinsSoldCashRaisedTxt.setText("");
        poshMillUnitStartTxt.setText("");
        poshMillUnitEndTxt.setText("");
        houseUnitStartTxt.setText("0.00");
        houseUnitEndTxt.setText("0.00");
        houseUnitTxt.setText("0.00");
        poshMillUnitTxt.setText("");
        mainenanceItemNameTxt.setText("NONE");
        mainenanceCostTxt.setText("0.00");
        profitRaisedTxt.setText("");
    }
    
    
    private void update() {
        try {
                connectDB.setAutoCommit(false);
                
                System.out.println("About to Get the Maize Bags Details");
                
                double no_mbg_bght = 0.00, cost_per_bag = 0.00, total_cost = 0.00, est_tins = 0.00, tins_per_bag = 0.00, new_est_tins;
                double new_total_cost = 0.00;
                

                java.sql.PreparedStatement pstOne = connectDB.prepareStatement("SELECT no_mbg_bght, cost_per_bag, total_cost, est_tins "
                        + "FROM maize_bags_tbl");
                java.sql.ResultSet rseOne = pstOne.executeQuery();

                System.out.println("To Get the Maize Bags");

                while (rseOne.next()) {
                    System.out.println("Getting the Maize Bags");
                    no_mbg_bght = Double.valueOf(dbObject.getDBObject(rseOne.getObject(1), "0"));
                    cost_per_bag = Double.valueOf(dbObject.getDBObject(rseOne.getObject(2), "0"));
                    total_cost = Double.valueOf(dbObject.getDBObject(rseOne.getObject(3), "0"));
                    est_tins = Double.valueOf(dbObject.getDBObject(rseOne.getObject(4), "0"));
                }
                
               tins_per_bag = (est_tins/no_mbg_bght);
               new_est_tins = tins_per_bag*Double.valueOf(maizeBagsTxt.getText());
               new_total_cost = cost_per_bag*Double.valueOf(maizeBagsTxt.getText());
               
               
               System.out.println("Date - "+todayDate);
               
               try {
                    connectDB.setAutoCommit(false);

                    java.sql.PreparedStatement pstmtOne = connectDB.prepareStatement("UPDATE maize_bags_tbl "
                        + "SET date = '"+com.amimobenja.www.helpers.SQLDateFormat.getSQLDate(todayDate)+"', "
                        + "total_cost= '"+new_total_cost+"', est_tins= '"+new_est_tins+"', rem_bags= '"+maizeBagsTxt.getText()+"', "
                        + "user_name= '"+userName+"' "
                             + "WHERE no_mbg_bght = '"+no_mbg_bght+"' AND cost_per_bag = '"+cost_per_bag+"' AND "
                             + "total_cost = '"+total_cost+"' AND est_tins = '"+est_tins+"'");
                    
                    
                     System.out.println("Date - "+com.amimobenja.www.helpers.SQLDateFormat.getSQLDate(todayDate));

                     pstmtOne.executeUpdate();

                     connectDB.commit();
                     connectDB.setAutoCommit(true);
                     


                    } catch (java.lang.Exception sq) {

                        try {
                            connectDB.rollback();
                        } catch (java.sql.SQLException sql) {
                            javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                        }
                        System.out.println(sq.getMessage());
                        javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error ---> ", javax.swing.JOptionPane.ERROR_MESSAGE);

                    }
               
                               
                System.out.println("Success Update has to have been done!!!");
                
               
            } catch (SQLException es) {
                System.out.println(es);

            }
    }
    
    private void setadmin() {
        Boolean admin = false;
        try {
            connectDB.setAutoCommit(false);
            
            java.sql.PreparedStatement pstm = connectDB.prepareStatement("SELECT admin FROM secure_access_tbl WHERE username = '"+userName+"'");
            java.sql.ResultSet rst = pstm.executeQuery();
            
            while (rst.next()) {
                admin = Boolean.valueOf(rst.getObject(1).toString());            
            }
            
            if (admin == true) {
                tinPricesMnuItm.setVisible(true);
                maizeBagMnuItm.setVisible(true);
                userManagementMnu.setVisible(true);
            } else {
                tinPricesMnuItm.setVisible(false);
                maizeBagMnuItm.setVisible(false);
                userManagementMnu.setVisible(false);
                
            }
        } catch (SQLException sql) {
            System.out.println("Error: "+sql);
        
        }
    }
    
    public void exit() {
        com.amimobenja.www.EditUser editUser = new com.amimobenja.www.EditUser(connectDB, userName);
        editUser.dispose();
        editUser.dispose();
        com.amimobenja.www.RemoveUser removeUser = new com.amimobenja.www.RemoveUser(connectDB, userName);
        removeUser.dispose();
        com.amimobenja.www.LoginForm loginForm = new com.amimobenja.www.LoginForm(connectDB);
        loginForm.dispose();
        com.amimobenja.www.MaizeBagBought maizeBags = new com.amimobenja.www.MaizeBagBought(connectDB, userName);
        maizeBags.dispose();
        com.amimobenja.www.UserSetUp userSetup = new com.amimobenja.www.UserSetUp(connectDB, userName);
        userSetup.dispose();
        com.amimobenja.www.TinPrices tinPrices = new com.amimobenja.www.TinPrices(connectDB, userName);
        tinPrices.dispose();
        this.dispose();
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnNavigationPanel;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JPanel codeMainingPanel;
    private javax.swing.JScrollPane codeMeaningScrollPane;
    private javax.swing.JTextArea codeMeaningTextArea;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel dateLbl;
    private javax.swing.JMenu editMnu;
    private javax.swing.JMenuItem editUserMnuItm;
    private javax.swing.JPanel electricityUsedPanel;
    private javax.swing.JLabel endLbl;
    private javax.swing.JMenuItem exitMnuItm;
    private javax.swing.JLabel houseElecUsedLbl;
    private javax.swing.JTextField houseUnitEndTxt;
    private javax.swing.JTextField houseUnitStartTxt;
    private javax.swing.JTextField houseUnitTxt;
    private javax.swing.JLabel inforLbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel labelPanel;
    private javax.swing.JMenuItem logOutMnuItm;
    private javax.swing.JPanel mainPanel;
    public static javax.swing.JTextField mainenanceCostTxt;
    private javax.swing.JTextField mainenanceItemNameTxt;
    private javax.swing.JMenuItem maizeBagMnuItm;
    private javax.swing.JTextField maizeBagTotalCostTxt;
    private javax.swing.JTextField maizeBagsBoughtTxt;
    private javax.swing.JLabel maizeBagsLbl;
    private javax.swing.JTextField maizeBagsTxt;
    private javax.swing.JButton navRestBtn;
    private javax.swing.JButton naviEditBtn;
    private javax.swing.JButton nextBtn;
    private javax.swing.JMenuItem periodicMnuItm;
    private javax.swing.JLabel poshMillElecUsedLbl;
    private javax.swing.JTextField poshMillUnitEndTxt;
    private javax.swing.JTextField poshMillUnitStartTxt;
    private javax.swing.JTextField poshMillUnitTxt;
    private javax.swing.JButton previousBtn;
    private javax.swing.JPanel previousMainPanel;
    private javax.swing.JTextField profitRaisedTxt;
    private javax.swing.JMenuItem removeMnuItm;
    private javax.swing.JMenuItem removeUserMnItm;
    private javax.swing.JMenu reportsMnu;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton searchUserBtn;
    private javax.swing.JMenu setUpMnu;
    private javax.swing.JTabbedPane softFlourMillersTabbedPane;
    private javax.swing.JMenuBar softflourMainBar;
    private javax.swing.JLabel startLbl;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JScrollPane tableValuesScrollPane;
    private javax.swing.JTable tableValuesTbl;
    private javax.swing.JMenuItem tinPricesMnuItm;
    private javax.swing.JTextField tinsSoldCashRaisedTxt;
    private javax.swing.JLabel tinsSoldLbl;
    private javax.swing.JTextField tinsSoldTxt;
    private javax.swing.JMenu userManagementMnu;
    private javax.swing.JMenuItem userMnuItm;
    // End of variables declaration//GEN-END:variables

    
}
