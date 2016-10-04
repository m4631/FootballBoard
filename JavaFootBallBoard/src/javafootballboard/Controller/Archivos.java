package javafootballboard.Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Model.Equipo;
import javafootballboard.Model.Jugador;

import javax.swing.JOptionPane;
public class Archivos {
    public ArrayList<String> juegos;
    public ArrayList<String> equipos;
    public ArrayList<String> jugadores;
    public ArrayList<String> jugadas;
    
    private final String stringFolder = System.getProperty("user.dir") + "\\Archivos";
    private final String stringJuegos = stringFolder + "\\Juegos.csv";
    private final String stringEquipos = stringFolder + "\\Equipos.csv";
    private final String stringJugadas = stringFolder + "\\Jugadas.csv";
    private final String stringJugadores = stringFolder + "\\Jugadores.csv";
    
    Path rutaFolder = Paths.get(stringFolder);
    
    public Archivos(){
        juegos = new ArrayList<>();
        equipos = new ArrayList<>();
        jugadores = new ArrayList<>();
        jugadas = new ArrayList<>();
        
        carpeta();
        archivoJuegos();
        archivoJugadas();
        archivoEquipos();
        archivoJugadores();
        
        cargarJuegos();
        cargarEquipos();
        cargarJugadores();
        cargarJugadas();
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
    
    private  void  cargarJugadores(){
        
       
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
    
    private void  cargarJuegos(){
        
       
        try(BufferedReader lector = new BufferedReader(new FileReader(stringJuegos)))
        {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while((linea = lector.readLine()) != null){
                juegos.add(linea);
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    private void  cargarEquipos(){
        
       
        try(BufferedReader lector = new BufferedReader(new FileReader(stringEquipos)))
        {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while((linea = lector.readLine()) != null){
                equipos.add(linea);
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private  void  cargarJugadas(){
        
       
        try(BufferedReader lector = new BufferedReader(new FileReader(stringJugadas)))
        {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while((linea = lector.readLine()) != null){
                jugadas.add(linea);
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void actJuegos() throws IOException{
        if(Files.exists(Paths.get(stringJuegos)))
        {
            try 
            { 
                File Juegos = new File(stringJuegos);   
                Juegos.delete();
                archivoJuegos();
                FileWriter actJuegos = new FileWriter(stringJuegos); 
                
            for (String linea : juegos) {
                actJuegos.append(linea);
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
            System.out.println("Actualizado el archivo " + stringJuegos);
        }
        
    }
    
    public void actEquipos() throws IOException{
        if(Files.exists(Paths.get(stringEquipos)))
        {
            try 
            { 
                File Equipos = new File(stringEquipos);   
                Equipos.delete();
                archivoEquipos();
                FileWriter actEquipos = new FileWriter(stringEquipos); 
                
            for (String linea : equipos) {
                actEquipos.append(linea);
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
            System.out.println("Actualizado el archivo " + stringEquipos);
        }
        
    }
    
    public void actJugadores() throws IOException{
        if(Files.exists(Paths.get(stringJugadores)))
        {
            try 
            { 
                File Jugadores = new File(stringJugadores);   
                Jugadores.delete();
                archivoJugadores();
                FileWriter actJugadores = new FileWriter(stringJugadores); 
                
            for (String linea : jugadores) {
                actJugadores.append(linea);
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
            System.out.println("Actualizado el archivo " + stringJugadores);
        }
        
    }
    
     public void actJugadas() throws IOException{
        if(Files.exists(Paths.get(stringJugadas)))
        {
            try 
            { 
                File Jugadas = new File(stringJugadas);   
                Jugadas.delete();
                archivoJugadas();
                FileWriter actJugadas = new FileWriter(stringJugadas); 
                
            for (String linea : jugadas) {
                actJugadas.append(linea);
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
            System.out.println("Actualizado el archivo " + stringJugadas);
        }
        
    }
}
