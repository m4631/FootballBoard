/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Model;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javafootballboard.View.Equipos;

/**
 *
 * @author Carlos
 */

public class WindowCloseListener extends WindowAdapter{

    Equipos frame;
    
    public WindowCloseListener(Equipos frame){
        this.frame = frame;
    }

    @Override
     public void windowClosing(WindowEvent e) {
                frame.subir.cargarComboBoxes();
                frame.subir.activarBotonA();
                frame.subir.setEnabled(true);
            }


}