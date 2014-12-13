//Author Amimo Benja

//Made to test Java support for Threads.

//Revision : Ver 1.0b

//import java.lang.*;

package com.amimobenja.www.reports;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class GeneralPeriodReportPdf implements java.lang.Runnable {
    
    com.amimobenja.www.helpers.DBObject dbObject;
    public static java.sql.Connection connectDB = null; 
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
            
    public java.lang.String dbUserName = null;
     
    
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    
    public void GeneralPeriodReportDetailsPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String username) {
        
        dbObject = new com.amimobenja.www.helpers.DBObject();
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        
        dbUserName = username;
        
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generatePdf();
            
            try {
                
                System.out.println("Right, let's wait for task to complete or fail");
                
                java.lang.Thread.currentThread().sleep(200);
                
                System.out.println("It's time for us threads to get back to work after the nap");
                
            } catch(java.lang.InterruptedException IntExec) {
                
                System.out.println(IntExec.getMessage());
                
            }
            
            threadCheck = false;
            
            
            System.out.println("We shall be lucky to get back to start in one piece");
            
        }
        
        if (!threadCheck) {
            
            
            
            Thread.currentThread().stop();
            
        }
        
    }
    
    public java.lang.String getDateLable() {
        
        java.lang.String date_label = null;
        
        java.lang.String month_now_strs = null;
        
        java.lang.String date_now_strs = null;
        
        java.lang.String year_now_strs = null;
        
        java.lang.String minute_now_strs = null;
        
        java.lang.String hour_now_strs = null;
        
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        
        java.util.Calendar calinst = java.util.Calendar.getInstance();
        
        java.util.Date date_now = calinst.getTime();
        
        int date_now_str = date_now.getDate();
        
        int month_now_str = date_now.getMonth();
        
        int year_now_str = date_now.getYear();
        
        int hour_now_str = date_now.getHours();
        
        int minute_now_str = date_now.getMinutes();
        
        int year_now_abs = year_now_str - 100;
        
        if (year_now_abs < 10) {
            
            year_now_strs = "200"+year_now_abs;
            
        } else {
            
            year_now_strs = "20"+year_now_abs;
            
        }
        
        switch (month_now_str) {
            
            case 0 : month_now_strs = "JAN";
            
            break;
            
            case 1 : month_now_strs = "FEB";
            
            break;
            
            case 2 : month_now_strs = "MAR";
            
            break;
            
            case 3 : month_now_strs = "APR";
            
            break;
            
            case 4 : month_now_strs = "MAY";
            
            break;
            
            case 5 : month_now_strs = "JUN";
            
            break;
            
            case 6 : month_now_strs = "JUL";
            
            break;
            
            case 7 : month_now_strs = "AUG";
            
            break;
            
            case 8 : month_now_strs = "SEP";
            
            break;
            
            case 9 : month_now_strs = "OCT";
            
            break;
            
            case 10 : month_now_strs = "NOV";
            
            break;
            
            case 11 : month_now_strs = "DEC";
            
            break;
            
            default :         if (month_now_str < 10){
                
                month_now_strs = "0"+month_now_str;
                
            } else {
                
                month_now_strs = ""+month_now_str;
                
            }
            
        }
        
        if (date_now_str < 10) {
            
            date_now_strs = "0"+date_now_str;
            
        } else {
            
            date_now_strs = ""+date_now_str;
            
        }
        
        if (minute_now_str < 10) {
            
            minute_now_strs = "0"+minute_now_str;
            
        } else {
            
            minute_now_strs = ""+minute_now_str;
            
        }
        
        if (hour_now_str < 10) {
            
            hour_now_strs = "0"+hour_now_str;
            
        } else {
            
            hour_now_strs = ""+hour_now_str;
            
        }
        
        date_label = date_now_strs+month_now_strs+year_now_strs+"@"+hour_now_strs+minute_now_strs;
        
        return date_label;
        
    }
    
    
    public void generatePdf() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");
        try {
            
            java.io.File tempFile = java.io.File.createTempFile("REP"+this.getDateLable()+"_", ".pdf");
            
            tempFile.deleteOnExit();
            
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
//                    String compName = null;
//                    String date = null;
//                    try {
//                        
//                        java.sql.Statement st3 = connectDB.createStatement();
//                        java.sql.Statement st4 = connectDB.createStatement();
//                        
//                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
//                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
//                        while(rset2.next()){
//                            compName = rset2.getObject(1).toString();
//                        }
//                        while(rset4.next()){
//                            date = rset4.getObject(1).toString();
//                        }
//                        
//                    } catch(java.sql.SQLException SqlExec) {
//                        
//                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
//                        
//                    }
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {20, 20, 20, 20, 20, 20};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
//                        try {
//                            table.getDefaultCell().setBorder(Rectangle.BOX);
//                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                            table.getDefaultCell().setColspan(1);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                            table.addCell(Image.getInstance(System.getProperty("company.logo")));
//                            
//                            java.sql.PreparedStatement st321 = connectDB.prepareStatement("select header_name from pb_header");
//                            java.sql.ResultSet rset3 = st321.executeQuery();
//                            table.getDefaultCell().setBorder(Rectangle.BOX);
//                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                            Phrase phrase  = new Phrase("");
//                            while (rset3.next()) {
//                                table.getDefaultCell().setColspan(5);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                                phrase = new Phrase(" \n"+rset3.getObject(1).toString().toUpperCase(), pFontHeader);
//                                
//                            }
//                            table.addCell(phrase);
//                        } catch (java.sql.SQLException SqlExect) {
//
//                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExect.getMessage());
//
//                        }
                        
                        
                        table.getDefaultCell().setBorderColor(Color.WHITE);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        Phrase phrase = new Phrase("SMOOTHFLOUR MILLERS GENERAL REPORT\nFOR THE PERIOD BETWEEN "+sdf.format(beginDate)+" AND "+sdf.format(endDate),  pFontHeader);
                        table.addCell(phrase); 
                        
                        
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setBorderColor(Color.BLACK);
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("DATE", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("TINS SOLD", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("CASH RAISED", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("ELEC. COST", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("MAINT. COST", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                        phrase = new Phrase("PROFIT", pFontHeader);
                        table.addCell(phrase);
                        
                        double t_tin_sld = 0, t_tin_cash_raised = 0, elec_cost = 0, maint_cost = 0, profit = 0;
                        
                        try {
                            connectDB.setAutoCommit(false);
                            
                            java.sql.PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT date, SUM(tins_sold), SUM(tin_sld_cash), "
                                    + "SUM(ele_posh_cost),  SUM(maint_cost), SUM(profit) FROM soft_floor_main_tbl "
                                    + "WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' "
                                    + "GROUP BY date ORDER BY date;");
                            java.sql.ResultSet rst = pst.executeQuery();
                            
                            while(rst.next()) {
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                                phrase = new Phrase(dbObject.getDBObject(rst.getObject(1), " -- "), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(dbObject.getDBObject(rst.getObject(2), " -- "), pFontHeader1);
                                table.addCell(phrase);                                
                                t_tin_sld = t_tin_sld + Double.valueOf(dbObject.getDBObject(rst.getObject(2), ""));

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(new com.amimobenja.www.helpers.Format2Currency().Format2Currency(dbObject.getDBObject(rst.getObject(3), " -- ")),
                                        pFontHeader1);
                                table.addCell(phrase);
                                t_tin_cash_raised = t_tin_cash_raised + Double.valueOf(dbObject.getDBObject(rst.getObject(3), ""));

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(new com.amimobenja.www.helpers.Format2Currency().Format2Currency(dbObject.getDBObject(rst.getObject(4), " -- ")), 
                                        pFontHeader1);
                                table.addCell(phrase);
                                elec_cost = elec_cost + Double.valueOf(dbObject.getDBObject(rst.getObject(4), ""));

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(new com.amimobenja.www.helpers.Format2Currency().Format2Currency(dbObject.getDBObject(rst.getObject(5), " -- ")),
                                        pFontHeader1);
                                table.addCell(phrase);
                                maint_cost = maint_cost + Double.valueOf(dbObject.getDBObject(rst.getObject(5), ""));

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(new com.amimobenja.www.helpers.Format2Currency().Format2Currency(dbObject.getDBObject(rst.getObject(6), " -- ")), 
                                        pFontHeader);
                                table.addCell(phrase);
                                profit = profit + Double.valueOf(dbObject.getDBObject(rst.getObject(6), ""));
                                
                            }
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(Color.BLACK);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                            phrase = new Phrase("GROSS TOTAL ", pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                            phrase = new Phrase(Double.toString(t_tin_sld), pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                            phrase = new Phrase(new com.amimobenja.www.helpers.Format2Currency().Format2Currency(Double.toString(t_tin_cash_raised)), pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                            phrase = new Phrase(new com.amimobenja.www.helpers.Format2Currency().Format2Currency(Double.toString(elec_cost)), pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                            phrase = new Phrase(new com.amimobenja.www.helpers.Format2Currency().Format2Currency(Double.toString(maint_cost)), pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_MIDDLE);
                            phrase = new Phrase(new com.amimobenja.www.helpers.Format2Currency().Format2Currency(Double.toString(profit)), pFontHeader);
                            table.addCell(phrase);                            
                            
                        
                        } catch(SQLException sqlE) {
                            System.out.println("SQL Error -- "+sqlE);
                        
                        }
                        
                        
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        
                        try {                        
                            java.sql.PreparedStatement pstmtUser = connectDB.prepareStatement("SELECT (SELECT upper(first_name||' '||other_names) FROM secure_access_tbl WHERE username = '"+dbUserName+"' ORDER BY 1 LIMIT 1), "
                                    + "date_part('day', now()::date) ||'-'||date_part('month', now()::date) ||'-'||date_part('year', now()::date)");
                            java.sql.ResultSet rsetUser = pstmtUser.executeQuery();

                                while (rsetUser.next()) {
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Generated By : ".toUpperCase() + rsetUser.getString(1), pFontHeader);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(" " , pFontHeader);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Date : ".toUpperCase() + rsetUser.getString(2), pFontHeader);
                                    table.addCell(phrase);

                                }

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        
                        
                        docPdf.add(table);
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                } catch(java.io.FileNotFoundException fnfExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());
                    
                }
            } catch(com.lowagie.text.DocumentException lwDocexec) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());
                
            }
            
docPdf.close();  com.amimobenja.www.helpers.PDFRenderer.renderPDF(tempFile);
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
        
    
        
}





