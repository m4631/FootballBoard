/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Model;

import javafootballboard.Controller.Archivos;
import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import javafootballboard.Controller.ArchivoController;
import javax.swing.JOptionPane;

/**
 *
 * @author Naty
 */
public class Equipo {
    ArrayList<Jugador> jugadores;
    String nombre;
    
    public Equipo(String nombre){
        this.nombre = nombre; 
        this.jugadores = new ArrayList<>();
        
    }
  
    public void setNombre(String nombre){
       this.nombre = nombre;
   }
    
    public String getNombre(){
       return nombre;
   }
   
   public void agregarJugador(Jugador jugador){
        jugador.setEquipo(this);
        jugadores.add(jugador);
   }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
   
   
   

                
   
   
    
