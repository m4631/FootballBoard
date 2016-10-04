/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Model;

import java.util.ArrayList;
import javafootballboard.Controller.ArchivoController;

/**
 *
 * @author Naty
 */
public class Juego {
   String cod;
   String titulo;
   Equipo equipoA;
   Equipo equipoB;
   String estadio;
   String ciudad;
   String fecha;
   String arbitro;
   String horaInicio;
   String horaFin;
   int puntosA;
   int puntosB;
   ArrayList<Jugada> jugadas = new ArrayList<Jugada>(); 
   
   public Juego(){
   }
   
   public Juego(String[] parametros, Equipo equipoA, Equipo equipoB){
        this.cod = parametros[0];
        this.titulo = parametros[1];
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.estadio = parametros[4];
        this.ciudad = parametros[5];
        this.fecha = parametros[6];
        this.arbitro = parametros[7];
        this.horaInicio = parametros[8];
        this.horaFin = parametros[9];
        try{
            this.puntosA = Integer.parseInt(parametros[10]);
        }catch(Exception e){
            this.puntosA = 0;
        }
        try{
            this.puntosB = Integer.parseInt(parametros[11]);
        }catch(Exception e){
            this.puntosB = 0;
        }
   }
   
   public Juego(String estadio, String ciudad, String fecha, String arbitro, String horaFin, int puntosA, int puntosB){
       this.estadio = estadio;
       this.ciudad = ciudad;
       this.fecha = fecha;
       this.arbitro = arbitro;
       this.horaFin = horaFin;
       this.puntosA = puntosA;
       this.puntosB = puntosB;
    }
   
    public Juego(String cod){
        this.cod = cod;
        String partes[] = cod.split("->");
        this.fecha = partes[0];
        this.horaInicio = partes[1];
        this.titulo = partes[2];
        String partesTitulo[] = partes[2].split(" VS. ");
        if(ArchivoController.ac.equipos.containsKey(partesTitulo[0])){
            this.equipoA = ArchivoController.ac.equipos.get(partesTitulo[0]);
        }else{
            this.equipoA = new Equipo(partesTitulo[0]);
        }
    }
   
    private void setCod(){
       this.cod = this.fecha + "->" + this.horaInicio + "->" + this.titulo;
    }
    public void setTitulo(){
        this.titulo = this.equipoA.getNombre()+" VS. "+this.equipoB.getNombre();
    }
    public void setEstadio(String Estadio){
       this.estadio = Estadio;
    }
    public void setFecha(String fecha){
       this.fecha = fecha;
    }
    public void setArbitro(String arbitro){
       this.arbitro = arbitro;
    }
    public void setCiudad(String ciudad){
       this.ciudad = ciudad;
    }
    public void setEquipoA(Equipo equipoA){
       this.equipoA = equipoA;
    } 
    public void setEquipoB(Equipo equipoB){
       this.equipoB = equipoB;
    } 
    public void setPuntosA(int puntosA){
       this.puntosA = puntosA;
    }
    public void setPuntosB(int puntosB){
       this.puntosB = puntosB;
    }
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }  
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    
    public String getEstadio(){
       return estadio;
    }
    public String getFecha(){
       return fecha;
    }
    public String getArbitro(){
       return arbitro;
    }
    public String getCiudad(){
       return ciudad;
    }
    public int getPuntosA(){
       return puntosA;
    }
    public int getPuntosB(){
       return puntosB;
    }
    public String getTitulo(){
       return titulo;
    }
    public String getCod(){
       return cod;
    }
    public String getHoraInicio() {
        return horaInicio;
    }
    public String getHoraFin() {
        return horaFin;
    }
    public Equipo getEquipoA(){
       return equipoA;
    } 
    public Equipo getEquipoB(){
       return equipoB;
    } 
    
    public void agregarJugada(Jugada jugada){
        jugada.setJuego(this);
        jugadas.add(jugada);
   }
}
