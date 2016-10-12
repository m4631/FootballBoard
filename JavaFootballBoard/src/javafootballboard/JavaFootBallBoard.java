package javafootballboard;

import javafootballboard.Controller.ArchivoController.Archivos;
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
