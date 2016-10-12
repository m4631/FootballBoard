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
    private String nombre;
    private String apellido;
    private String posicion;
    private Equipo equipo;
    
    public Jugador(){
        inicializar();
    }
   
    public Jugador(String nombre, String apellido){
        inicializar();
        this.nombre = nombre;
        this.apellido = apellido; 
    }
    
    public Jugador(String nombre, String apellido,String posicion){
        inicializar();
        this.nombre = nombre;
        this.apellido = apellido; 
        this.posicion = posicion;
    }
    
    public Jugador(String nombre, String apellido, String posicion, Equipo equipo){
        inicializar();
        this.nombre = nombre;
        this.apellido = apellido; 
        this.posicion = posicion;
        this.equipo = equipo;
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
    public String getNombreCompleto(){
        return nombre + " " + apellido;
    }
    private void inicializar(){
        String noData = "No data";
        this.nombre = noData;
        this.apellido = noData; 
        this.posicion = "Banca";
        this.equipo = new Equipo();
    }
}
