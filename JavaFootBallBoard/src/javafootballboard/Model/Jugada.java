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
    String jugador;
    String hora;
    String juego;
   
    public Jugada(String juego,String nombre, String equipo, String jugador, String hora){
        this.juego = juego;
        this.nombre = nombre; 
        this.equipo = equipo;
        this.jugador = jugador; 
        this.hora = hora;
    }
    
     public void setJuego(String juego){
       this.juego = juego;
   }
    public void setNombre(String nombre){
       this.nombre = nombre;
   }
    
    public void setEquipo(String equipo){
       this.equipo = equipo;
   }
    
    public void setJugador(String jugador){
       this.jugador = jugador;
   }
    
    public void setHora(String hora){
       this.hora = hora;
   }
    
    public String getJuego(){
       return juego;
   }
    public String getNombre(){
       return nombre;
   }
    
     public String getEquipo(){
       return equipo;
   }
      public String getJugador(){
       return jugador;
   }
       public String getHora(){
       return hora;
   }
   
 
}
   
   
   

                
   
   
    
