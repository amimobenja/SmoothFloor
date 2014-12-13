/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amimobenja.www.helpers;

import static com.amimobenja.www.DisplayReport.softMainDesktopPane;

/**
 *
 * @author Amimo Benja
 */
public class PDFRenderer {

    public static void renderPDF(java.io.File fileName) {

        org.icepdf.ri.common.SwingController controller = new org.icepdf.ri.common.SwingController();

        org.icepdf.ri.common.SwingViewBuilder factory = new org.icepdf.ri.common.SwingViewBuilder(controller);

        javax.swing.JPanel viewerComponentPanel = factory.buildViewerPanel();
        

        org.icepdf.ri.common.ComponentKeyBinding.install(controller, viewerComponentPanel);

        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                controller.getDocumentViewController()));

        com.amimobenja.www.helpers.PDFReportViewer pdfViewer = new com.amimobenja.www.helpers.PDFReportViewer();

        pdfViewer.setLayout(new java.awt.GridBagLayout());

        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pdfViewer.getContentPane().add(viewerComponentPanel, gridBagConstraints);

        controller.openDocument(fileName.getPath());

        pdfViewer.setSize(647, 658);

        pdfViewer.setVisible(true);
        
        softMainDesktopPane.add(pdfViewer, javax.swing.JLayeredPane.DEFAULT_LAYER);

        try {

            pdfViewer.setSelected(true);

            System.out.println("Show PDF viewer...");
        } catch (java.beans.PropertyVetoException pvt) {
        }



    }
}
