package javafootballboard;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Model.*;
import javafootballboard.View.*;
import javafootballboard.Controller.*;

public class JavaFootBallBoard {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            menu = new Menu();
            menu.setVisible(true);
            archivos = new Archivos();
        } catch (IOException ex) {
            Logger.getLogger(JavaFootBallBoard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
