/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Randula
 */
public class PenDrive  {

    String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
    File[] drives = new File[letters.length];
    boolean[] isDrive = new boolean[letters.length];
    int totFiles = 0, totDirs = 0;
    boolean isApen = false;
    File pen = null;

    public PenDrive() {
        for (int i = 0; i < letters.length; ++i) {
            drives[i] = new File(letters[i] + ":/");
            isDrive[i] = drives[i].canRead();
        }

        System.out.println("FindDrive: waiting for devices {} …");
        readDirectoryNames();
        save("sdsdsd");
    }

    public void readDirectoryNames() {

        while (true) {
            for (int i = 0; i < letters.length; ++i) {
                boolean pluggedIn = drives[i].canRead();
                if (pluggedIn != isDrive[i]) {

                    if (pluggedIn) {
                        String path = letters[i] + "://";
                        File files = new File(path);
                        String allFiles[] = files.list();

                        for (int k = 0; k < allFiles.length; k++) {
                            File f = new File(path + "/" + allFiles[k]);
                            if (f.isDirectory()) {
                                totDirs++;
                                System.out.println(
                                        "Directory:" + allFiles[k]
                                );
                            } else {
                                totFiles++;
                                System.out.println("File:" + allFiles[k]);
                            }
                        }
                        System.out.println("Total Directories and Files :" + allFiles.length);
                        System.out.println("Total Directories :" + allFiles.length);
                        System.out.println("Total Files :" + allFiles.length);
                        if (allFiles.length > 0) {
                            System.out.println("A pen...");
                            isApen = true;
                            pen = files;
                        }
                    } else {
                        isApen = false;
                        System.out.println("Drive " + letters[i] + " has been unplugged");
                    }

                    isDrive[i] = pluggedIn;

                }
                
            }
        }
    }

    public void save(String text) {
        if (isApen) {
            System.out.println("In save");
            saveToPen(text, pen);
        }
    }

    private void saveToPen(String text, File file) {
        System.out.println("Saved");
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file+"/"+"test"+".HLML");
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(NotePadSof.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
