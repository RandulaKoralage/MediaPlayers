/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;

/**
 *
 * @author Neon Tetras
 */
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Randula
 */
public class ImageViewerSof extends javax.swing.JFrame {

    private int position = 0;   //pointer to an image
    private String[] fileNames; //list of files in the folder
    private String folderPath; //path of the folder
    private JButton nextButton;//Button to go next image
    private JButton previousButton;//Button to go previous image
    private JLabel pictureLabel;//Label to show image

    /**
     * Create a MediaPlayerSof
     *
     * @param folderPath the path of folder with images
     */
    public ImageViewerSof(String folderPath) {
        this.folderPath = folderPath;
        initComponents();
        listFiles(folderPath);
        setLabelIcon(folderPath, fileNames[position]);
        previousButton.setEnabled(false);
        setVisible(true);
    }

    /**
     * initializes JFrame and sets other components
     */
    private void initComponents() {
        pictureLabel = new JLabel();
        nextButton = new JButton();
        previousButton = new JButton();
        Toolkit theKit = getToolkit();
        JPanel panel = new JPanel();
        JPanel backPanel = new JPanel();

        setTitle("Maze Image Viewer");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new java.awt.BorderLayout());
        setBackground(new java.awt.Color(51, 51, 51));

        backPanel.setBackground(new java.awt.Color(51, 51, 51));
        backPanel.setLayout(new java.awt.BorderLayout());
        pictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pictureLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        previousButton.setText("Previous");
        previousButton.setIconTextGap(10);
        previousButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            previousButtonPerformed(evt);
        });

        nextButton.setPreferredSize(previousButton.getPreferredSize());
        nextButton.setText("Next");
        nextButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        nextButton.setIconTextGap(10);
        nextButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            nextButtonActionPerformed(evt);
        });

        Box vbox = javax.swing.Box.createVerticalBox();
        vbox.add(javax.swing.Box.createVerticalStrut(30));
        vbox.add(pictureLabel);
        vbox.add(javax.swing.Box.createVerticalStrut(50));

        panel.setBackground(new java.awt.Color(51, 51, 51));
        FlowLayout flow = new java.awt.FlowLayout(FlowLayout.CENTER);
        flow.setHgap(30);
        panel.setLayout(flow);
        panel.add(previousButton);
        panel.add(nextButton);
        panel.setOpaque(false);
        vbox.add(panel);
        backPanel.add(vbox);
        add(backPanel);

        Dimension size = theKit.getScreenSize();
        setLocation(size.width / 5, size.height / 10);
        setMinimumSize(new java.awt.Dimension(900, 600));
    }

    private void listFiles(String folderPath) {
        File file = new File(folderPath);
        fileNames = file.list();
        for (String names : fileNames) {
            System.out.println(names);
        }
    }

    /**
     * Creates a Label to view images
     *
     * @param folderPath the path of folder with images
     * @param name the name of file
     */
    private void setLabelIcon(String folderPath, String name) {
        File file = new File(folderPath + "\\" + name);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException ie) {
            JOptionPane.showMessageDialog(this, "Error reading image file",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        ImageIcon icon = new ImageIcon(image);
        int width = 600;
        int height = 400;
        Image img = icon.getImage().getScaledInstance(
                width, height, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(img);

        String pictureName = file.getName();
        int pos = pictureName.lastIndexOf(".");
        String caption = pictureName.substring(0, pos);
        pictureLabel.setIcon(newIcon);
        pictureLabel.setText(caption);
        pictureLabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    }

    /**
     * Defines things do in action event on previousButton
     *
     * @param evt the action event
     */
    private void previousButtonPerformed(ActionEvent evt) {
        position--;
        if (!nextButton.isEnabled()) {
            nextButton.setEnabled(true);
        }
        if (position == 0) {
            previousButton.setEnabled(false);
        }
        setLabelIcon(folderPath, fileNames[position]);
    }

    /**
     * Defines things do in action event on nextButton
     *
     * @param evt the action event
     */
    private void nextButtonActionPerformed(ActionEvent evt) {
        position++;
        if (!previousButton.isEnabled()) {
            listFiles(folderPath);
            previousButton.setEnabled(true);
        }
        if (position == fileNames.length) {
            nextButton.setEnabled(false);
            position--;
            return;
        }
        setLabelIcon(folderPath, fileNames[position]);
    }
}
