/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Model;

/**
 *
 * @author Naty
 */
public class Jugada {
    String equipo;
    String nombre;
    Jugador jugador;
    String hora;
   
    public Jugada(String nombre, String equipo, Jugador jugador, String hora){
        this.nombre = nombre; 
        this.equipo = equipo;
        this.jugador = jugador; 
        this.hora = hora;
    }
  
    public void setNombre(String nombre){
       this.nombre = nombre;
   }
    
    public void setEquipo(String equipo){
       this.equipo = equipo;
   }
    
    public void setJugador(Jugador jugador){
       this.jugador = jugador;
   }
    
    public void setHora(String hora){
       this.hora = hora;
   }
    
    public String getNombre(){
       return nombre;
   }
    
     public String getEquipo(){
       return equipo;
   }
      public Jugador getJugador(){
       return jugador;
   }
       public String getHora(){
       return hora;
   }
   
 
}
   
   
   

                
   
   
    
