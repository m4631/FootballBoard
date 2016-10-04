/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Model;

/**
 *
 * @author Carlos
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class onlyDigitsListener implements KeyListener {

    private JTextField campo;
    private int maxLenght;

    public onlyDigitsListener(JTextField campo) {
        this.campo = campo;
        maxLenght = 255;
    }

    public onlyDigitsListener(JTextField campo, int maxLenght) {
        this.campo = campo;
        this.maxLenght = maxLenght;
    }

    @Override
    public void keyTyped(KeyEvent evt) {
        if (campo.getText().length() < maxLenght) {
            char s = evt.getKeyChar();
            if (!(Character.isDigit(s))) {
                evt.consume();
            }
        } else {
            evt.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

}
