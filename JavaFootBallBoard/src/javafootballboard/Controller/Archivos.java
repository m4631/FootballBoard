package javafootballboard.Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Model.Equipo;

import javax.swing.JOptionPane;
public class Archivos {
    public static ArrayList<String> jugadores;
    private final String stringFolder = System.getProperty("user.dir") + "\\Archivos";
    private final String stringJuegos = stringFolder + "\\Juegos.csv";
    private final String stringEquipos = stringFolder + "\\Equipos.csv";
    private final String stringJugadas = stringFolder + "\\Jugadas.csv";
    private final String stringJugadores = stringFolder + "\\Jugadores.csv";
    
    Path rutaFolder = Paths.get(stringFolder);
    
    public Archivos(){
        carpeta();
        archivoJuegos();
        archivoJugadas();
        archivoEquipos();
        archivoJugadores();
        cargarJugadores();
        Equipo e = new Equipo("Zazafras");
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
            try(FileWriter Juegos = new FileWriter(stringJuegos)) 
            { 
                Juegos.append("Cod,Titulo,Estadio,Ciudad,Arbitro,Fecha,PuntuacionA,PuntuacionB");
                Juegos.flush();
                System.out.println("Archivo Juegos creado");
                Juegos.close();
            }
            catch (IOException ex) 
            {
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
                Juegos.append("Nombre,Cant. Jugadores");
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
            catch (IOException ex) 
            {
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
            try(FileWriter Juegos = new FileWriter(stringJugadores)) 
            { 
                Juegos.append("Nombre,Apellido,Posicion,Equipo");
                Juegos.flush();
                System.out.println("Archivo Jugadores creado");
                Juegos.close();
            }
            catch (IOException ex) 
            {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            System.out.println("Ya existe el archivo " + stringJugadores);
        }
    }
    
    public  void  cargarJugadores(){
        jugadores = new ArrayList<>();
       
        try(BufferedReader lector = new BufferedReader(new FileReader(stringJugadores)))
        {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while((linea = lector.readLine()) != null){
                jugadores.add(linea);
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
