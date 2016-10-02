/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Naty
 */
public class Equipo {
   String nombre;
  ArrayList<Jugador> jugadores;
   int MAX_JUGADORES = 5;
  // Archivos archivo = new Archivos();

   
   public Equipo(String nombre){
       this.nombre = nombre; 
       jugadores = new ArrayList<>();
       cargarJugadores();
       
   }
   
   public void agregarJugador(Jugador jugador){
       if(jugadores.size() < MAX_JUGADORES){
           jugador.setEquipo(this.nombre);
       }
       
       else{
           JOptionPane.showMessageDialog(null, "Este equipo no puede tener mas jugadores");
       }
   }
   
   private  void cargarJugadores()
   {
       for (String linea : Archivos.jugadores) {
           if(linea.contains(this.nombre)){
               String[] datos = linea.split(",");
               Jugador jugador = new Jugador(datos[0],datos[1],datos[2]);
               jugador.setEquipo(this.nombre);
               
               jugadores.add(jugador);
               
           }
       }
      
          
      }
       
   }
   
   
   

                
   
   
    
