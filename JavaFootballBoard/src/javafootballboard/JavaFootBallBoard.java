package javafootballboard;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Controller.ArchivoController.Archivos;
import javafootballboard.Model.*;
import javafootballboard.View.*;

public class JavaFootBallBoard {
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Archivos ac = Archivos.getInstance();
        Menu menu = new Menu();
        menu.setVisible(true);
    }
    
}