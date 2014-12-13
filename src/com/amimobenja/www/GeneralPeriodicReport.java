/*
 * DatePanel.java
 *
 * Created on 23rd November 2014, 05:56 PM
 */
package com.amimobenja.www;
/**
 *
 * @author  afro
 */
public class GeneralPeriodicReport extends javax.swing.JDialog {

    int reportName;
    java.sql.Connection connectDB = null;
    java.util.Vector dateStartEnd = null;
    javax.swing.JSpinner beginDateSpinner = null;
    javax.swing.JSpinner endDateSpinner = null;
    
    java.lang.String userName = null;
    
   
    
    public GeneralPeriodicReport(java.awt.Frame parent, 
            boolean modal, int repName, java.sql.Connection connectDb, java.lang.String username) {

        super(parent, modal);

        reportName = repName;
        connectDB = connectDb;
        userName = username;

        initComponents();
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date todayDate = cal.getTime();
        endDateChooser.setDate(todayDate);
        beginDateChooser.setDate(todayDate);
        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        specifyGroupRadioBtn = new javax.swing.ButtonGroup();
        buttonsPanel = new javax.swing.JPanel();
        cancelBtn = new javax.swing.JButton();
        previewBtn = new javax.swing.JButton();
        datePanel = new javax.swing.JPanel();
        wardNutNameBDLbl = new javax.swing.JLabel();
        wardNutNameEDLbl = new javax.swing.JLabel();
        endDateChooser = new com.toedter.calendar.JDateChooser();
        beginDateChooser = new com.toedter.calendar.JDateChooser();

        setTitle("General Periodic Report - Select Begin & End Date");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        buttonsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonsPanel.setLayout(new java.awt.GridBagLayout());

        cancelBtn.setMnemonic('n');
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonsPanel.add(cancelBtn, gridBagConstraints);

        previewBtn.setMnemonic('o');
        previewBtn.setText("Preview Report");
        previewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonsPanel.add(previewBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(buttonsPanel, gridBagConstraints);

        datePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Dates"));
        datePanel.setLayout(new java.awt.GridBagLayout());

        wardNutNameBDLbl.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        datePanel.add(wardNutNameBDLbl, gridBagConstraints);

        wardNutNameEDLbl.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        datePanel.add(wardNutNameEDLbl, gridBagConstraints);

        endDateChooser.setForeground(new java.awt.Color(0, 0, 204));
        endDateChooser.setToolTipText("Today's Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        datePanel.add(endDateChooser, gridBagConstraints);

        beginDateChooser.setForeground(new java.awt.Color(0, 0, 204));
        beginDateChooser.setToolTipText("Today's Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        datePanel.add(beginDateChooser, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(datePanel, gridBagConstraints);

        setSize(new java.awt.Dimension(652, 246));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        this.dispose();        // Add your handling code here:
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void previewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewBtnActionPerformed
        this.getReport(reportName);
        
    }//GEN-LAST:event_previewBtnActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
    }

    public java.util.Vector getBeginEndDates() {

        dateStartEnd = new java.util.Vector(1, 1);

        dateStartEnd.addElement(beginDateSpinner.getValue().toString());

        dateStartEnd.addElement(endDateSpinner.getValue().toString());

        return dateStartEnd;

    }

    public void getReport(int reportName) {

        switch (reportName) {
            case 1: {
                System.out.println("Both the Department and Section have been Selected.");
                    com.amimobenja.www.reports.GeneralPeriodReportPdf generalPdf = new com.amimobenja.www.reports.GeneralPeriodReportPdf();
                
                    generalPdf.GeneralPeriodReportDetailsPdf(connectDB, this.beginDateChooser.getDate(), 
                            this.endDateChooser.getDate(), userName);

                    this.dispose();
                    
                    com.amimobenja.www.DisplayReport genPRpt = new com.amimobenja.www.DisplayReport(connectDB, userName);
                    genPRpt.setVisible(true);
                
                
            }
        }
           
        
    }
    
    
    

    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser beginDateChooser;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JPanel datePanel;
    private com.toedter.calendar.JDateChooser endDateChooser;
    private javax.swing.JButton previewBtn;
    private javax.swing.ButtonGroup specifyGroupRadioBtn;
    private javax.swing.JLabel wardNutNameBDLbl;
    private javax.swing.JLabel wardNutNameEDLbl;
    // End of variables declaration//GEN-END:variables
}
