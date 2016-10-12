/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Model;

import java.util.ArrayList;
import javafootballboard.Controller.ArchivoController.Archivos;

/**
 *
 * @author Naty
 */
public class Juego {
   String code;
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
       inicializar();
   }

   public Juego(String[] parametros, Equipo equipoA, Equipo equipoB){
        inicializar();
        this.code = parametros[0];
        this.titulo = parametros[1];
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.estadio = parametros[4];
        this.ciudad = parametros[5];
        this.fecha = parametros[6];
        this.arbitro = parametros[7];
        this.horaInicio = parametros[8];
        this.horaFin = parametros[9];
        this.puntosA = convertToInt(parametros[10]);
        this.puntosB = convertToInt(parametros[11]);
    }
   
    public Juego(String cod){
        inicializar();
        this.code = cod;
        String partes[] = cod.split("->");
        this.fecha = partes[0];
        this.horaInicio = partes[1];
        this.titulo = partes[2];
        
        String partesTitulo[] = partes[2].split(" VS. ");
        this.equipoA = DataLocal.getEquipo(partesTitulo[0]);
        this.equipoB = DataLocal.getEquipo(partesTitulo[1]);
    }
   
    public void setCode(){
       this.code = this.fecha + "->" + this.horaInicio + "->" + this.titulo;
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
    public String getCode(){
       return code;
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
    public void eliminarJugada(int idx){
        jugadas.remove(idx);
   }
   public void SetEquipoA(Equipo equipoA){
       this.equipoA = equipoA;
   } 
     public void SetEquipoB(Equipo equipoB){
       this.equipoB = equipoB;
    }
    public ArrayList<Jugada> getJugadas() {
        return jugadas;
    }
    public void setJugadas (ArrayList<Jugada> jugadas) {
        this.jugadas = jugadas;
    }
    private int convertToInt(String numberSt){
        int number = 0;
        try{
            number = Integer.parseInt(numberSt);
        }catch(Exception e){
            number = 0;
        }
        return number;
    }
    private void inicializar(){
        String noData = "No data";
        this.code = noData;
        this.titulo = noData;
        this.equipoA = new Equipo("Sin equipo");
        this.equipoB = new Equipo("Sin equipo");
        this.estadio = noData;
        this.ciudad = noData;
        this.fecha = noData;
        this.arbitro = noData;
        this.horaInicio = noData;
        this.horaFin = noData;
        this.puntosA = 0;
        this.puntosB = 0;
        this.jugadas = new ArrayList<>();
    }
}
