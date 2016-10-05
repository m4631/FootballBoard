/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Mabel
 */
public class Cronometro implements Runnable {
    
    Thread hilo;
    boolean cronometroActivo;
    String min="", seg="";
    JLabel minutosLabel, segundosLabel;
  
    public void run(){
        Integer minutos = 0 , segundos = 0, milesimas = 0;
        //min es minutos, seg es segundos y mil es milesimas de segundo
        try
        {
            //Mientras cronometroActivo sea verdadero entonces seguira
            //aumentando el tiempo
            while( cronometroActivo )
            {
                Thread.sleep( 4 );
                //Incrementamos 4 milesimas de segundo
                milesimas += 4;
                 
                //Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                //y las milesimas de segundo de nuevo a 0
                if( milesimas == 1000 )
                {
                    milesimas = 0;
                    segundos += 1;
                    //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                    //y los segundos vuelven a 0
                    if( segundos == 60 )
                    {
                        segundos = 0;
                        minutos++;
                    }
                }
 
                //Esto solamente es estetica para que siempre este en formato
                //00:00:000
                if( minutos < 10 ) min = "0" + minutos;
                else min = minutos.toString();
                if( segundos < 10 ) seg = "0" + segundos;
                else seg = segundos.toString();     
                minutosLabel.setText(min);
                segundosLabel.setText(min);
            }
        }catch(Exception e){}
        //Cuando se reincie se coloca nuevamente en 00:00:000
        minutosLabel.setText("00");
        segundosLabel.setText("00");
    }
  
    //Iniciar el cronometro poniendo cronometroActivo 
    //en verdadero para que entre en el while
    public void iniciarCronometro(JLabel minutosLabel, JLabel segundosLabel) {
        this.minutosLabel = minutosLabel;
        this.segundosLabel = segundosLabel;
        cronometroActivo = true;
        hilo = new Thread( this );
        hilo.start();
    }
  
    //Esto es para parar el cronometro
    public void pararCronometro(){
        try {
            hilo.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Esto es para reanudar el cronometro
    public void reanudarCronometro(){
        hilo.run();
    }    
    
    public void detenerCronometro(){
        cronometroActivo = false;
    }    
}
