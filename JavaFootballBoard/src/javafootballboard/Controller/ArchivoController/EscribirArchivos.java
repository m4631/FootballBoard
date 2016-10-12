/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.ArchivoController;

import javafootballboard.Controller.ArchivoController.CrearArchivos;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import javafootballboard.Model.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Mabel
 */
public class EscribirArchivos {
    
    private static ArrayList<String> convertirAEquiposCSV(){
        ArrayList<String> lineas = new ArrayList<>();
        lineas.add("Nombre");
        for(Map.Entry<String, Equipo> elemento : DataLocal.equipos.entrySet()){
            lineas.add(elemento.getValue().getNombre());
        }
        return lineas;
    }
    
    private static ArrayList<String> convertirAJugadoresCSV(){
        ArrayList<String> lineas = new ArrayList<>();
        lineas.add("Nombre,Apellido,Posicion,Equipo");
        for(Map.Entry<String, Jugador> elemento : DataLocal.jugadores.entrySet()){
            lineas.add(elemento.getValue().getNombre()+","+elemento.getValue().getApellido()+","+
                    elemento.getValue().getPosicion()+","+elemento.getValue().getEquipo().getNombre());
        }
        return lineas;
    }
    private static ArrayList<String> convertirAJuegosCSV(){
        ArrayList<String> lineas = new ArrayList<>();
        lineas.add("Cod,Titulo,EquipoA,EquipoB,Estadio,Ciudad,Fecha,Arbitro,HoraInicio,HoraFin,PuntuacionA,PuntuacionB");
        for(Map.Entry<String, Juego> elemento : DataLocal.juegos.entrySet()){
            lineas.add(elemento.getValue().getCode()+","+elemento.getValue().getTitulo()+","+
              elemento.getValue().getEquipoA().getNombre()+","+elemento.getValue().getEquipoB().getNombre()+","+
              elemento.getValue().getEstadio()+","+elemento.getValue().getCiudad()+","+elemento.getValue().getFecha()+","+
              elemento.getValue().getArbitro()+","+elemento.getValue().getHoraInicio()+","+elemento.getValue().getHoraFin()
              +","+elemento.getValue().getPuntosA()+","+elemento.getValue().getPuntosB()
            );
        }
        return lineas;
    }
    private static ArrayList<String> convertirAJugadasCSV(){
        ArrayList<String> lineas = new ArrayList<>();
        lineas.add("Cod. de juego,Nombre de jugada,Jugador,Equipo,Hora");
        for(Jugada jugada : DataLocal.jugadas){
            lineas.add(jugada.getJuego().getCode()+","+jugada.getNombre()+","+
                    jugada.getJugador().getNombre()+" "+jugada.getJugador().getApellido()+","+jugada.getEquipo().getNombre()
                    +","+jugada.getHora());
        }
        return lineas;
    }
    
    public static void archivoEquipos() throws IOException{
        ArrayList<String> equiposCSV = convertirAEquiposCSV();
        if(Files.exists(Paths.get(DataLocal.stringEquipos)))
        {
            try 
            { 
                File Equipos = new File(DataLocal.stringEquipos);   
                Equipos.delete();
                CrearArchivos.archivoEquipos();
                FileWriter actEquipos = new FileWriter(DataLocal.stringEquipos); 
                
                for (String linea : equiposCSV) {
                    actEquipos.append(linea + "\n");
                    actEquipos.flush();
                }
                actEquipos.close();
            }
            catch(SecurityException se){
                JOptionPane.showMessageDialog(null,"No se tiene los permisos necesarios para proceder");
            }
        }
        else
        {
            System.out.println("El archivo " + DataLocal.stringEquipos + " No existe.");
        }
    }
    public static void archivoJugadores() throws IOException{
        ArrayList<String> jugadoresCSV = convertirAJugadoresCSV();
        if(Files.exists(Paths.get(DataLocal.stringJugadores)))
        {
            try 
            { 
                File Jugadores = new File(DataLocal.stringJugadores);   
                Jugadores.delete();
                CrearArchivos.archivoJugadores();
                FileWriter actJugadores = new FileWriter(DataLocal.stringJugadores); 
                
                for (String linea : jugadoresCSV) {
                    actJugadores.append(linea + "\n");
                    actJugadores.flush();
                }
                actJugadores.close();
            }
            catch(SecurityException se){
                JOptionPane.showMessageDialog(null,"No se tiene los permisos necesarios para proceder");
            }
        }
        else
        {
            System.out.println("El archivo " + DataLocal.stringJugadores + " No existe.");
        }
        
    }
    public static void archivoJuegos() throws IOException{
        ArrayList<String> juegosCSV = convertirAJuegosCSV();
        if(Files.exists(Paths.get(DataLocal.stringJuegos)))
        {
            try 
            { 
                File Juegos = new File(DataLocal.stringJuegos);   
                Juegos.delete();
                CrearArchivos.archivoJuegos();
                FileWriter actJuegos = new FileWriter(DataLocal.stringJuegos); 
                
                for (String linea : juegosCSV) {
                    actJuegos.append(linea + "\n");
                    actJuegos.flush();
                }
                actJuegos.close();
            }
            catch(SecurityException se){
                JOptionPane.showMessageDialog(null,"No se tiene los permisos necesarios para proceder");
            }
        }
        else
        {
            System.out.println("El archivo " + DataLocal.stringJuegos + " No existe.");
        }
        
    }
    public static void archivoJugadas() throws IOException{
        ArrayList<String> jugadasCSV = convertirAJugadasCSV();
        if(Files.exists(Paths.get(DataLocal.stringJugadas)))
        {
            try 
            { 
                File Jugadas = new File(DataLocal.stringJugadas);   
                Jugadas.delete();
                archivoJugadas();
                FileWriter actJugadas = new FileWriter(DataLocal.stringJugadas); 
                
                for (String linea : jugadasCSV) {
                    actJugadas.append(linea + "\n");
                    actJugadas.flush();
                }
                actJugadas.close();
            }
            catch(SecurityException se){
                JOptionPane.showMessageDialog(null,"No se tiene los permisos necesarios para proceder");
            }
        }
        else
        {
            System.out.println("El archivo " + DataLocal.stringJugadas + " No existe.");
        }
    }
}
