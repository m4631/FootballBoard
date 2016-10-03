package javafootballboard;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Model.*;
import javafootballboard.View.*;

public class JavaFootBallBoard {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu;
        try {
            menu = new Menu();
            menu.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(JavaFootBallBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
