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
public class Jugador {
   String nombre;
   String apellido;
   String posicion;
   Equipo equipo;
   
   public Jugador(String nombre, String apellido,String posicion){
       this.posicion = posicion;
       this.nombre = nombre;
       this.apellido = apellido; 
   }
   
   public void setEquipo(Equipo equipo){
       this.equipo = equipo;
   }
  
    public void setNombre(String nombre){
       this.nombre = nombre;
   }
    public void setApellido(String apellido){
       this.apellido = apellido;
   }
    public void setPosicion(String posicion){
       this.posicion = posicion;
   }
    
   
     public Equipo getEquipo(){
       return equipo;
   }
     public String getNombre(){
       return nombre;
   }
     public String getApellido(){
       return apellido;
   }
     public String getPosicion(){
       return posicion;
   }
}
