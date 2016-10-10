/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.IniciarController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Controller.AbrirVistas;
import javafootballboard.Controller.ArchivoController.EscribirArchivos;
import javafootballboard.Model.Juego;
import javafootballboard.View.Iniciar;
import javafootballboard.View.Subir;
import javax.swing.JOptionPane;

public class TabGuardar {
    Iniciar iniciar;
    
    public TabGuardar(Iniciar iniciar){
        this.iniciar = iniciar;
    }
    
    public void mostrarDatosD(){
        Juego juego = iniciar.IC.crearJuego();
        iniciar.getJLTitulo().setText(juego.getTitulo());
        iniciar.getJLScore().setText("[ "+juego.getPuntosA()+" - "+juego.getPuntosB()+" ]");
        
        if(juego.getPuntosA()>=juego.getPuntosB()){
               iniciar.getJLEquipoA().setText(juego.getEquipoA().getNombre());
               iniciar.getJLEquipoB().setText(juego.getEquipoB().getNombre());
        }else{
               iniciar.getJLEquipoB().setText(juego.getEquipoA().getNombre());
               iniciar.getJLEquipoA().setText(juego.getEquipoB().getNombre());
        }
        
        if(juego.getPuntosA()==juego.getPuntosB()){
            iniciar.getJ1().setText("Empate: ");
            iniciar.getJ2().setText("Empate: ");
        }
     
        iniciar.getJLLugar().setText(juego.getCiudad());
        iniciar.getJLFecha().setText(juego.getFecha());
        iniciar.getJLHoraInicio().setText(juego.getHoraInicio());
        iniciar.getJLHoraFin().setText(juego.getHoraFin());
        iniciar.getJLEstadio().setText(juego.getEstadio());
    }

    public void terminarProceso(){
        // Guardar partido en los archivos
        // Cargar nuevamente los arreglos de menu
        guardarDB();
        AbrirVistas.menu(iniciar);
    }
    
    public boolean guardarDB(){
        try {
            EscribirArchivos.archivoJuegos();
            EscribirArchivos.archivoJugadas();
            JOptionPane.showMessageDialog(iniciar, "Partido guardado correctamente");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Subir.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(iniciar, "Ha ocurrido un error al momento de guardar");
            return false;
        }
    }
}
