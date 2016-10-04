/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Model;

import java.util.ArrayList;
import javafootballboard.Controller.ArchivoController;
import javafootballboard.Controller.Archivos;

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
   ArrayList <Jugada> jugadas;
   int puntosA;
   int puntosB;
   
   public Juego(){
       
   }  
   
   public Juego(Equipo equipoA, Equipo equipoB, String estadio, String fecha,String arbitro, String ciudad){
       this.estadio = estadio;
       this.ciudad = ciudad;
       this.arbitro = arbitro;
       this.fecha = fecha;
       this.equipoA = equipoA;
       this.equipoB = equipoB;
       setTitulo();
       setCod();    
       cargarJugadas();
   }
   
    private void setCod(){
       this.cod = this.titulo + this.fecha;
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

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }  
        
    public void setTitulo(){
        this.titulo = this.equipoA.getNombre()+" vs "+this.equipoB.getNombre();
    }

     public Equipo GetEquipoA(){
       return equipoA;
   } 
     public Equipo GetEquipoB(){
       return equipoB;
   } 
     
   public void SetEquipoA(Equipo equipoA){
       this.equipoA = equipoA;
   } 
     public void SetEquipoB(Equipo equipoB){
       this.equipoB = equipoB;
   } 
     
    private  void cargarJugadas()
   {
       for (String linea : ArchivoController.archivoController.jugadas) {
           if(linea.contains(this.titulo)){
               String[] datos = linea.split(",");
               Jugada jugada = new Jugada(datos[0],datos[1],datos[2],datos[3],datos[4]);
               jugadas.add(jugada);
               
           }
       }
    }

    public ArrayList<Jugada> getJugadas() {
        return jugadas;
    }

    public void setJugadas (ArrayList<Jugada> jugadas) {
        this.jugadas = jugadas;
    } 
     
     
}
