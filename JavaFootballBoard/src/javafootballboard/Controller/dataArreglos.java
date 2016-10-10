/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller;

import java.util.ArrayList;
import java.util.Map;
import javafootballboard.Model.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mabel
 */
public class dataArreglos {
    
    public static ArrayList<String> dataEquiposNoHelp(){
        ArrayList<String> data = new ArrayList<>();
        //data.add("Seleccionar equipo...");
        for( Map.Entry<String, Equipo> equipo : DataLocal.equipos.entrySet()){
            data.add(equipo.getKey());
        }
        return data;
    }
    
    public static ArrayList<String> dataEquipos(){
        ArrayList<String> data = new ArrayList<>();
        data.add("Seleccionar equipo...");
        for( Map.Entry<String, Equipo> equipo : DataLocal.equipos.entrySet()){
            data.add(equipo.getKey());
        }
        return data;
    }    
    
    public static ArrayList<String> dataJugadores(Equipo equipo){
        ArrayList<String> data = new ArrayList<>();
        //data.add("Seleccionar jugador...");
        for( Jugador jugador : equipo.getJugadores()){
            data.add(jugador.getNombreCompleto());
        }
        return data;
    }
    
    public static ArrayList<String> dataJugadoresPosicion(Equipo equipo){
        ArrayList<String> data = new ArrayList<>();
        //data.add("Seleccionar jugador...");
        for( Jugador jugador : equipo.getJugadores()){
            data.add(jugador.getNombreCompleto() + " [" + jugador.getPosicion() + "]");
        }
        return data;
    }
    
    public static ArrayList<String> dataJuegos(){
        ArrayList<String> data = new ArrayList<>();
        //data.add("Seleccionar juego...");
        for( Map.Entry<String, Juego> juego : DataLocal.juegos.entrySet()){
            data.add(juego.getKey());
        }
        return data;
    }
    
    public static ArrayList<String> dataJuegosNoHelp(){
        ArrayList<String> data = new ArrayList<>();
        for( Map.Entry<String, Juego> juego : DataLocal.juegos.entrySet()){
            data.add(juego.getKey());
        }
        return data;
    }
    
    public static ArrayList<String> dataJugadas(){
        ArrayList<String> data = new ArrayList<>();
        //data.add("Seleccionar jugada...");
        for( Jugada jugada : DataLocal.jugadas){
            data.add(jugada.getNombre() + " por " + jugada.getJugador().getNombreCompleto());
        }
        return data;
    }
    
    public static DefaultTableModel tablaJugadas(Juego juego){
        String [][]x ={};
        DefaultTableModel model = new DefaultTableModel(x, new String[]{"Nombre Jugada", "Jugador", "Equipo", "Hora"});
        int i = 0;
        for(Jugada jugada : juego.getJugadas()){
            model.insertRow(i, new Object[]{});
            model.setValueAt(jugada.getNombre(), i, 0);
            model.setValueAt(jugada.getJugador().getNombre()+ " " +jugada.getJugador().getApellido(), i, 1);
            model.setValueAt(jugada.getEquipo().getNombre(), i, 2);
            model.setValueAt(jugada.getHora(), i, 3);
            i++;
        }
        return model;
    }
}
