/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * A class that defines the structure of arrays in which data will be saved locally
 */
public class DataLocal {
    public static final String stringFolder = System.getProperty("user.dir") + "\\Archivos";
    public static final String stringJuegos = stringFolder + "\\Juegos.csv";
    public static final String stringEquipos = stringFolder + "\\Equipos.csv";
    public static final String stringJugadas = stringFolder + "\\Jugadas.csv";
    public static final String stringJugadores = stringFolder + "\\Jugadores.csv";
    
    public static final Path rutaFolder = Paths.get(stringFolder);
    
    public static ArrayList<String> diccionarioJugadas = new ArrayList<String>(){{
        add("Gol");
        add("Fuera de linea");
        add("Pase");
        add("Falta");
    }};
    
    public static Map<String, Equipo> equipos = new HashMap<>();
    public static Map<String, Jugador> jugadores = new HashMap<>();
    public static Map<String, Juego> juegos = new HashMap<>();
    public static ArrayList<Jugada> jugadas = new ArrayList<>();
    
    public static Equipo getEquipo(String nombre){
        if(!equipos.containsKey(nombre)){
            equipos.put(nombre, new Equipo(nombre));
        }
        return equipos.get(nombre);
    }
    public static Equipo getEquipo(String nombre, Jugador jugador){
        if(!equipos.containsKey(nombre)){
            equipos.put(nombre, new Equipo(nombre));
        }
        if( !equipos.get(nombre).jugadores.contains(jugador) ){
            equipos.get(nombre).agregarJugador(jugador);
        }
        return equipos.get(nombre);
    }
    public static Jugador getJugador(String nombreCompleto){
        String nombres[] = nombreCompleto.split(" ");
        return getJugador(nombres[0], nombres[1]);
    }
    public static Jugador getJugador(String nombre, String apellido){
        String nombreCompleto = nombre + " " + apellido;
        if(!jugadores.containsKey(nombreCompleto)){
            jugadores.put(nombreCompleto, new Jugador(nombre, apellido));
        }
        return jugadores.get(nombreCompleto);
    }
    public static Jugador getJugador(String nombre, String apellido, String posicion){
        String nombreCompleto = nombre + " " + apellido;
        if(!jugadores.containsKey(nombreCompleto)){
            jugadores.put(nombreCompleto, new Jugador(nombre, apellido, posicion));
        }
        return jugadores.get(nombreCompleto);
    }
    public static Juego getJuego(String code){
        if(!juegos.containsKey(code)){
            juegos.put(code, new Juego(code));
        }
        return juegos.get(code);
    }
}
