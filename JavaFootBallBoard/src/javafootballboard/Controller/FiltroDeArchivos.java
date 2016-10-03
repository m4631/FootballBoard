/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller;

import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Mabel
 */
public class FiltroDeArchivos extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        
        if (f.getName().endsWith(".csv")){
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Por el momento solo estamos trabajando con archivos .csv";
    }
    
}
