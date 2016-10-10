/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.SubirController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Controller.AbrirVistas;
import javafootballboard.Controller.ArchivoController.EscribirArchivos;
import javafootballboard.Model.Juego;
import javafootballboard.View.Subir;
import javax.swing.JOptionPane;

/**
 *
 * @author Mabel
 */
public class TabGuardar {
    Subir subir;
    
    public TabGuardar(Subir subir){
        this.subir = subir;
        
    }
    
    public void mostrarDatosD(){
        Juego juego = subir.SC.crearJuego();
        subir.getJLTitulo().setText(juego.getTitulo());
        subir.getJLScore().setText("[ "+juego.getPuntosA()+" - "+juego.getPuntosB()+" ]");
        
        if(juego.getPuntosA()>=juego.getPuntosB()){
               subir.getJLEquipoA().setText(juego.getEquipoA().getNombre());
               subir.getJLEquipoB().setText(juego.getEquipoB().getNombre());
        }else{
               subir.getJLEquipoB().setText(juego.getEquipoA().getNombre());
               subir.getJLEquipoA().setText(juego.getEquipoB().getNombre());
        }
        
        if(juego.getPuntosA()==juego.getPuntosB()){
            subir.getJ1().setText("Empate: ");
            subir.getJ2().setText("Empate: ");
        }
     
        subir.getJLLugar().setText(juego.getCiudad());
        subir.getJLFecha().setText(juego.getFecha());
        subir.getJLHoraInicio().setText(juego.getHoraInicio());
        subir.getJLHoraFin().setText(juego.getHoraFin());
        subir.getJLEstadio().setText(juego.getEstadio());
    }

    public void terminarProceso(){
        // Guardar partido en los archivos
        // Cargar nuevamente los arreglos de menu
        guardarDB();
        AbrirVistas.menu(subir);
    }
    
    public boolean guardarDB(){
        try {
            EscribirArchivos.archivoJuegos();
            EscribirArchivos.archivoJugadas();
            JOptionPane.showMessageDialog(subir, "Partido guardado correctamente");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Subir.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(subir, "Ha ocurrido un error al momento de guardar");
            return false;
        }
    }
}
