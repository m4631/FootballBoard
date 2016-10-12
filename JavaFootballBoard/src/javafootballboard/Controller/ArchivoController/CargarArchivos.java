/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.ArchivoController;

import javafootballboard.Controller.ArchivoController.Archivos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Model.*;

/**
 *
 * @author Mabel
 */
public final class CargarArchivos {
    
    CargarArchivos(){
        cargarEquipos();
        cargarJugadores();
        cargarJuegos();
        cargarJugadas();
    }
    
    private void cargarEquipos(){
        cargarEquipos(DataLocal.stringEquipos);
    }
    private void cargarJugadores(){
        cargarJugadores(DataLocal.stringJugadores);
    }
    private void cargarJuegos(){
        cargarJuegos(DataLocal.stringJuegos);
    }
    private void cargarJugadas(){
        cargarJugadas(DataLocal.stringJugadas);
    }
    
    public static void cargarEquipos(String path){
        try(BufferedReader lector = new BufferedReader(new FileReader(path)))
        {
            System.out.println("Cargando archivo Equipos...");
            String linea = lector.readLine(); //Leo el encabezado del archivo
            System.out.println("HEADLINE: " + linea);
            while((linea = lector.readLine()) != null){
                String columnas[] = linea.split(",");
                if(columnas.length < 1) continue;
                DataLocal.equipos.put(columnas[0], new Equipo(columnas[0]));
                System.out.println("EQUIPO: " + columnas[0]);
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void cargarJugadores(String path){
        try(BufferedReader lector = new BufferedReader(new FileReader(path)))
        {
            System.out.println("Cargando archivo Jugadores... " + path);
            String linea = lector.readLine(); //Leo el encabezado del archivo
            System.out.println("HEADLINE: " + linea);
            while((linea = lector.readLine()) != null){
                String columnas[] = linea.split(",");
                if(columnas.length < 4) continue;
                Jugador jugador = new Jugador(columnas[0], columnas[1], columnas[2], DataLocal.getEquipo(columnas[3]));
                DataLocal.equipos.get(columnas[3]).agregarJugador(jugador);
                
                DataLocal.jugadores.put(jugador.getNombreCompleto(), jugador);
                System.out.println("JUGADOR: " + jugador.getNombreCompleto());
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void cargarJugadores(String path, String nombreEquipo){
        try(BufferedReader lector = new BufferedReader(new FileReader(path)))
        {
            System.out.println("Cargando archivo Jugadores con equipo dado...");
            String linea = lector.readLine(); //Leo el encabezado del archivo
            System.out.println("HEADLINE: " + linea);
            while((linea = lector.readLine()) != null){
                String columnas[] = linea.split(",");
                if(columnas.length < 4) continue;
                Equipo equipo = DataLocal.getEquipo(nombreEquipo);
                Jugador jugador = new Jugador(columnas[0], columnas[1], columnas[2], equipo);
                equipo.agregarJugador(jugador);
                
                DataLocal.jugadores.put(jugador.getNombreCompleto(), jugador);
                System.out.println("JUGADOR: " + jugador.getNombreCompleto());
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void cargarJuegos(String path){
        try(BufferedReader lector = new BufferedReader(new FileReader(path)))
        {
            System.out.println("Cargando archivo Juegos...");
            String linea = lector.readLine(); //Leo el encabezado del archivo
            System.out.println("HEADLINE: " + linea);
            while((linea = lector.readLine()) != null){
                String columnas[] = linea.split(",");
                if(columnas.length < 12) continue;
                Juego juego = new Juego(columnas, DataLocal.getEquipo(columnas[2]), DataLocal.getEquipo(columnas[3]));
                DataLocal.juegos.put(juego.getCode(), juego);
                System.out.println("JUEGO: " + juego.getCode());
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void cargarJugadas(String path){
        try(BufferedReader lector = new BufferedReader(new FileReader(path)))
        {
            System.out.println("Cargando archivo Jugadas");
            String linea = lector.readLine(); //Leo el encabezado del archivo
            System.out.println("HEADLINE: " + linea);
            while((linea = lector.readLine()) != null){
                System.out.println(linea);
                String columnas[] = linea.split(",");
                if(columnas.length < 5) continue;
                /** Get previous info */
                Juego juego = DataLocal.getJuego(columnas[0]);
                Jugador jugador = DataLocal.getJugador(columnas[2]);
                Equipo equipo = DataLocal.getEquipo(columnas[3], jugador);
                
                Jugada jugada = new Jugada(juego, columnas[1], jugador, equipo, columnas[4]);
                juego.agregarJugada(jugada);
                DataLocal.jugadas.add(jugada);
                System.out.println("JUGADA: " + jugada.getNombre() + " por " + jugada.getJugador() + " en " + jugada.getJuego().getTitulo());
                
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int cargarJugadas(String path, Juego juego){
        int rows = 0;
        try(BufferedReader lector = new BufferedReader(new FileReader(path)))
        {
            System.out.println("Cargando archivo Jugadas");
            String linea = lector.readLine(); //Leo el encabezado del archivo
            System.out.println("HEADLINE: " + linea);
            while((linea = lector.readLine()) != null){
                String columnas[] = linea.split(",");
                if(columnas.length < 4) continue;
                /** Get previous info */
                Jugador jugador = DataLocal.getJugador(columnas[1]);
                Equipo equipo = DataLocal.getEquipo(columnas[2], jugador);
                
                Jugada jugada = new Jugada(juego, columnas[0], jugador, equipo, columnas[3]);
                juego.agregarJugada(jugada);
                DataLocal.jugadas.add(jugada);
                System.out.println("JUGADA: " + jugada.getNombre() + " por " + jugada.getJugador() + " en " + jugada.getJuego().getTitulo());
                rows++;
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rows;
    }
}
