/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfviewer;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.apache.pdfbox.PDFReader;
import org.apache.pdfbox.pdfviewer.PDFPagePanel;
import org.apache.pdfbox.pdfviewer.PageWrapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.action.type.PDActionGoTo;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageXYZDestination;

/**
 *
 * @author Randula
 */
public class PDFViewerSof extends PDFReader {

    private PDDocument document; //in-memory representation of PDF document.
    private JPanel panel;
    private PDFPagePanel pdfPanel;
    private JScrollPane sp;
    private File file;

    /**
     * Create a PDFViewerSof
     *
     * @param path the path of PDF
     * @throws java.io.IOException
     */
    public PDFViewerSof(String path) throws IOException {
        document = new PDDocument();
        file = new File(path);
        initComponents();

    }

    private void initComponents() throws IOException {

        panel = new JPanel();
        pdfPanel = new PDFPagePanel();

        GridLayout layout = new GridLayout(0, 1);

        setSize(670, 750);
        setTitle("Maze PDF Viewer");
        setLocationRelativeTo(null);

        panel.setLayout(layout);
        sp = new JScrollPane(panel);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    document.close();
                } catch (IOException ex) {
                    Logger.getLogger(PDFViewerSof.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        readPDF();

        add(pdfPanel);
        add(sp);
        setVisible(true);
    }

    private void readPDF() throws IOException {

        document = PDDocument.load(file);
        List<PDPage> allPages = document.getDocumentCatalog().getAllPages();
        for (PDPage page : allPages) {
            PageWrapper wrapper = new PageWrapper(this);
            wrapper.displayPage(page);
            panel.add(wrapper.getPanel());
        }

    }

    private void zoomPage() {
        PDPageXYZDestination dest = new PDPageXYZDestination();
        dest.setPageNumber(0);
        dest.setZoom(900);
        dest.setTop(new Float(PDPage.PAGE_SIZE_A4.getHeight()).intValue());
        PDActionGoTo action = new PDActionGoTo();
        action.setDestination(dest);
        document.getDocumentCatalog().setOpenAction(action);

    }

}
