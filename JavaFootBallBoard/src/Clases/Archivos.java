/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Naty
 */
import javax.swing.JOptionPane;
public class Archivos {
    File juegos;
    String stringFolder = System.getProperty("user.dir") + "\\Archivos";
    String stringJuegos = stringFolder + "\\Juegos.csv";
    String stringEquipos = stringFolder + "\\Equipos.csv";
    String stringJugadas = stringFolder + "\\Jugadas.csv";
    String stringJugadores = stringFolder + "\\Jugadores.csv";
    
    Path rutaFolder = Paths.get(stringFolder);
    
    public Archivos(){
    
    carpeta();
    archivoJuegos();
    archivoJugadas();
    archivoEquipos();
    archivoJugadores();
    

    
    }
    
    private void carpeta(){
        if(Files.notExists(rutaFolder))
    {
        File Archivos = new File(stringFolder);
        try{
            Archivos.mkdir();
            System.out.println("Folder Creado");
        }
        catch(SecurityException se){
            JOptionPane.showMessageDialog(null,"No se tiene los permisos necesarios para proceder");
        }
        
    }
        else
    {
        System.out.println("Ya existe el folder " + stringFolder);
    }
        
    }
    private void archivoJuegos(){
        if(Files.notExists(Paths.get(stringJuegos)))
    {
        
        try(FileWriter Juegos = new FileWriter(stringJuegos)) { 
            Juegos.append("Cod,Titulo,Estadio,Ciudad,Arbitro,Fecha,PuntuacionA,PuntuacionB");
            Juegos.flush();
            System.out.println("Archivo Juegos creado");
            Juegos.close();
        }
         catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
        else
    {
        System.out.println("Ya existe el archivo " + stringJuegos);
    }
        
    }
    private void archivoEquipos(){
        if(Files.notExists(Paths.get(stringEquipos)))
    {
        
        try(FileWriter Juegos = new FileWriter(stringEquipos)) { 
            Juegos.append("Nombre");
            Juegos.flush();
            System.out.println("Archivo Equipos creado");
            Juegos.close();
        }
         catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
        else
    {
        System.out.println("Ya existe el archivo " + stringEquipos);
    }
        
    }
    private void archivoJugadas(){
        if(Files.notExists(Paths.get(stringJugadas)))
    {
        
        try(FileWriter Juegos = new FileWriter(stringJugadas)) { 
            Juegos.append("Juego,Titulo,Jugador,Equipo,Hora");
            Juegos.flush();
            System.out.println("Archivo Jugadas creado");
            Juegos.close();
        }
         catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
        else
    {
        System.out.println("Ya existe el archivo " + stringJugadas);
    }
        
    }
    private void archivoJugadores(){
        if(Files.notExists(Paths.get(stringJugadores)))
    {
        
        try(FileWriter Juegos = new FileWriter(stringJugadores)) { 
            Juegos.append("Nombre,Apellido,Posicion,Equipo");
            Juegos.flush();
            System.out.println("Archivo Jugadores creado");
            Juegos.close();
        }
         catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
        else
    {
        System.out.println("Ya existe el archivo " + stringJugadores);
    }
        
    }
    
}
