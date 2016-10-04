/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Model;

import javax.swing.JLabel;

/**
 *
 * @author Carlos
 */
public class Cronometro extends Thread {
    private int minutos;
    private int segundos;
    private boolean pausa;
    private JLabel label;
    
    String aux1;
    String aux2;
    
    public Cronometro(JLabel label){
       this.label = label; 
       pausa = false;
    }
    
   public void run(){
       try{
            while(true){
                for(minutos=0; minutos<60;minutos++){
                    if(minutos<10){
                        aux1 = "0"+minutos;
                    }
                    for(segundos=0;segundos<60;segundos++){
                        if(segundos<10){
                            aux2 = "0"+segundos;
                        }                    
                        label.setText(aux1+":"+aux2);
                        delay();
                    }
                }
              
            }
        
       }catch(Exception e){     
       }
    }
    private void delay(){
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
        }
        if (pausa) {
            try {
                synchronized (this) {
                    this.wait();
                }
            } catch (Exception e) {

            }
        }
    }
    
    public void iniciar(){
        this.run();
    }
    
    public void pausar(){
        pausa = true;
    }
    
    public void reanudar(){
        pausa = false;
        seguir();
    }
    
    public void reiniciar(){
        minutos = 0;
        segundos = 0;
    }
    
    public void seguir(){
        synchronized(this){
            notifyAll();
        }
    }
}
