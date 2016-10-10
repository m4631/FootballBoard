/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.HistoricoController;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Controller.ArchivoController.EscribirArchivos;
import javafootballboard.Controller.dataArreglos;
import javafootballboard.Model.DataLocal;
import javafootballboard.Model.Juego;
import javafootballboard.View.Historico;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Mabel
 */
public class HistoricoController {
    Historico historico;
    
    public HistoricoController(Historico historico){
        this.historico = historico;
        cargarJuegos();
        cargarDatos(0);
    }
    
    public void cargarJuegos(){
        historico.getJuegosComboBox().setModel(new DefaultComboBoxModel(dataArreglos.dataJuegosNoHelp().toArray()));
    }
    
    public void cargarDatos(int idx){
        Juego juegoSeleccionado = DataLocal.juegos.get(historico.getJuegosComboBox().getItemAt(idx).toString());
        System.out.println(juegoSeleccionado);
        historico.getLabelEstadio().setText(juegoSeleccionado.getEstadio());
        historico.getLabelCiudad().setText(juegoSeleccionado.getCiudad());
        historico.getLabelFecha().setText(juegoSeleccionado.getFecha());
        historico.getLabelArbitro().setText(juegoSeleccionado.getArbitro());
        historico.getLabelInicio().setText(juegoSeleccionado.getHoraInicio());
        historico.getLabelFin().setText(juegoSeleccionado.getHoraFin());
        historico.getLabelEquipoA().setText(juegoSeleccionado.getEquipoA().getNombre());
        historico.getLabelPuntA().setText(String.valueOf(juegoSeleccionado.getPuntosA()));
        historico.getLabelEquipoB().setText(juegoSeleccionado.getEquipoB().getNombre());
        historico.getLabelPuntB().setText(String.valueOf(juegoSeleccionado.getPuntosB()));
        cargarJugadas(idx);
    }
    
    public void escribirCambios(){
        try {
            EscribirArchivos.archivoJuegos();
        } catch (IOException ex) {
            Logger.getLogger(HistoricoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarJugadas(int idx){
        Juego juego = DataLocal.juegos.get(historico.getJuegosComboBox().getItemAt(idx).toString());
        historico.getTablaJugadas().setModel(dataArreglos.tablaJugadas(juego));
    }
    
    public void updateEstadio(){
        String s = JOptionPane.showInputDialog("Ingrese un valor para el campo "+historico.getEstadioLabel().getText());
        if(s.length() > 0){
            historico.getLabelEstadio().setText(s);
            DataLocal.getJuego(historico.getJuegosComboBox().getSelectedItem().toString()).setEstadio(s);
        }
    }
    
    public void updateCiudad(){
        String s = JOptionPane.showInputDialog("Ingrese un valor para el campo "+historico.getCiudadLabel().getText());
        if(s.length() > 0){
            historico.getLabelCiudad().setText(s);
            DataLocal.getJuego(historico.getJuegosComboBox().getSelectedItem().toString()).setCiudad(s);
        }
    }
    
    public void updateFecha(){
        String s = JOptionPane.showInputDialog("Ingrese un valor para el campo "+historico.getFechaLabel().getText());
        if(s.length() > 0){
            historico.getLabelFecha().setText(s);
            DataLocal.getJuego(historico.getJuegosComboBox().getSelectedItem().toString()).setFecha(s);
        }
    }
    
    public void updateArbitro(){
        String s = JOptionPane.showInputDialog("Ingrese un valor para el campo "+historico.getArbitroLabel().getText());
        if(s.length() > 0){
            historico.getLabelArbitro().setText(s);
            DataLocal.getJuego(historico.getJuegosComboBox().getSelectedItem().toString()).setArbitro(s);
        }
    }
    
    public void updateInicio(){
        String s = JOptionPane.showInputDialog("Ingrese un valor para el campo "+historico.getInicioLabel().getText());
        if(s.length() > 0){
            historico.getLabelInicio().setText(s);
            DataLocal.getJuego(historico.getJuegosComboBox().getSelectedItem().toString()).setHoraInicio(s);
        }
    }
    
    public void updateFin(){
        String s = JOptionPane.showInputDialog("Ingrese un valor para el campo "+historico.getFinLabel().getText());
        if(s.length() > 0){
            historico.getLabelFin().setText(s);
            DataLocal.getJuego(historico.getJuegosComboBox().getSelectedItem().toString()).setHoraFin(s);
        }
    }
    
    public void updatePuntA(){
        String s = JOptionPane.showInputDialog("Ingrese un valor para el campo "+historico.getLabelPuntA().getText());
        try{
            int puntA = Integer.parseInt(s);
            historico.getLabelPuntA().setText(s);
            DataLocal.getJuego(historico.getJuegosComboBox().getSelectedItem().toString()).setPuntosA(puntA);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(historico, "Solo puede poner numeros");
        }
    }
    
    public void updatePuntB(){
        String s = JOptionPane.showInputDialog("Ingrese un valor para el campo "+historico.getLabelPuntB().getText());
        try{
            int puntB = Integer.parseInt(s);
            historico.getLabelPuntB().setText(s);
            DataLocal.getJuego(historico.getJuegosComboBox().getSelectedItem().toString()).setPuntosB(puntB);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(historico, "Solo puede poner numeros");
        }
    }
    
}
