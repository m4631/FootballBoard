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
   private  void cargarJugadores()
   {
       for (String linea : ArchivoController.archivoController.jugadores) {
           if(linea.contains(this.nombre)){
               String[] datos = linea.split(",");
               Jugador jugador = new Jugador(datos[0],datos[1],datos[2]);
               agregarJugador(jugador);
           }
       }
    }
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
   
   
   

                
   
   
    
