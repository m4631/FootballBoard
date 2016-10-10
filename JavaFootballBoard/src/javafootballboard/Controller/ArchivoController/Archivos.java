package javafootballboard.Controller.ArchivoController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Model.*;

import javax.swing.JOptionPane;
public class Archivos {
    
    private static Archivos instance = null;
    private CargarArchivos CA;
    
    protected Archivos() {
        CA = new CargarArchivos();
    }
    
    public static Archivos getInstance() {
        if(instance == null) {
            instance = new Archivos();
        }
        return instance;
    }

}
