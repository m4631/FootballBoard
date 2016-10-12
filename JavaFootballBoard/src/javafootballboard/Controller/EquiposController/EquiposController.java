/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.EquiposController;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Controller.AbrirVistas;
import javafootballboard.Controller.ArchivoController.CargarArchivos;
import javafootballboard.Controller.ArchivoController.EscribirArchivos;
import javafootballboard.Controller.FiltroDeArchivos;
import javafootballboard.Controller.dataArreglos;
import javafootballboard.Model.*;
import javafootballboard.View.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mabel
 */
public class EquiposController {
    
    Equipos equipos;
    JFileChooser seleccionaArchivo;
    DefaultTableModel modelT;
    ArrayList<Jugador> paraBorrar;
    ArrayList<Jugador> paraEditar;
    
    public EquiposController(Equipos equipos){
        this.equipos = equipos;
        cargarEquipos();
        rePoblarTabla(equipos.getEquiposCargados().getSelectedItem().toString());
    }
    
    public void actualizarCampos(){
        cargarEquipos();
        salir();
    }
    
    public void cargarEquipos(){
        equipos.getEquiposCargados().setModel(new DefaultComboBoxModel(dataArreglos.dataEquiposNoHelp().toArray()));
    }
    
    public void seleccionarArchivo(){
        seleccionaArchivo = new JFileChooser ();
        seleccionaArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        seleccionaArchivo.setFileFilter(new FiltroDeArchivos());
        seleccionaArchivo.setMultiSelectionEnabled(false);
        
        int returnVal = seleccionaArchivo.showOpenDialog(equipos);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = seleccionaArchivo.getSelectedFile();
            equipos.getSeleccionado().setText(file.getName());
            //This is where a real application would open the file.
            System.out.println("Opening: " + file.getName() + ".");
        } else {
            System.out.println("Open command cancelled by user.");
        }
        equipos.getBotonSubir().setEnabled(true);
    }
    
    public void desactivarAutomatico(){
        equipos.getOpcionAutomatico().setSelected(false);
        equipos.getOpcionManual().setSelected(false);
        equipos.getManualMessage().setVisible(false);
        equipos.getAutomaticMessage().setVisible(false);
        equipos.getEquiposCargados().setEnabled(true);
        equipos.getBotonSeleccionar().setEnabled(false);
        equipos.getNombreEquipo().setEnabled(false);
        equipos.getBotonSubir().setEnabled(false);
        equipos.getVerFormato().setEnabled(false);
    }
    public void activarAutomatico(){
        equipos.getOpcionAutomatico().setSelected(true);
        equipos.getOpcionManual().setSelected(false);
        equipos.getManualMessage().setVisible(false);
        equipos.getAutomaticMessage().setVisible(true);
        equipos.getEquiposCargados().setEnabled(false);
        equipos.getBotonSeleccionar().setEnabled(true);
        equipos.getNombreEquipo().setEnabled(true);
        equipos.getBotonSubir().setEnabled(false);
        equipos.getVerFormato().setEnabled(true);
    }
    public void desactivarManual(){
        equipos.getOpcionManual().setSelected(false);
        equipos.getOpcionAutomatico().setSelected(false);
        equipos.getBotonSeleccionar().setEnabled(false);
        equipos.getNombreEquipo().setEnabled(false);
        equipos.getBotonSubir().setEnabled(false);
        equipos.getManualMessage().setVisible(true);
        equipos.getAutomaticMessage().setVisible(false);
        equipos.getEquiposCargados().setEnabled(true);
    }
    public void activarManual(){
        equipos.getOpcionManual().setSelected(false);
        equipos.getOpcionAutomatico().setSelected(false);
        equipos.getBotonSeleccionar().setEnabled(false);
        equipos.getNombreEquipo().setEnabled(false);
        equipos.getBotonSubir().setEnabled(false);
        equipos.getManualMessage().setVisible(false);
        equipos.getAutomaticMessage().setVisible(false);
        equipos.getEquiposCargados().setEnabled(false);
    }
    
    public void guardarCambios(boolean type){
        String nombreEquipo = equipos.getEquiposCargados().getSelectedItem().toString();
        Equipo equipo = DataLocal.getEquipo(nombreEquipo);
        if(equipos.historico!=null){
            if(!type){
                equipos.historico.getLabelEquipoA().setText(nombreEquipo);
                DataLocal.getJuego(equipos.historico.getJuegosComboBox().getSelectedItem().toString()).SetEquipoA(equipo);
            }else if(type){
                equipos.historico.getLabelEquipoB().setText(nombreEquipo);
                DataLocal.getJuego(equipos.historico.getJuegosComboBox().getSelectedItem().toString()).SetEquipoB(equipo);
            }
        }
        guardarCambios();
    }
    
    public void guardarCambios(){
        try {
            EscribirArchivos.archivoEquipos();
            EscribirArchivos.archivoJugadores();
        } catch (IOException ex) {
            Logger.getLogger(EquiposController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salir(){
        if(equipos.subir != null){
            equipos.subir.SC.tabEquipos.cargarComboBoxes();
            equipos.subir.setEnabled(true);
        }
        else if(equipos.iniciar != null){
            equipos.iniciar.setEnabled(true);
        }
        else if(equipos.historico != null){
            equipos.historico.setEnabled(true);
        }
        else{
            Menu menu = new Menu();
            menu.setVisible(true);
        }
        equipos.dispose();
    }
    
    public void rePoblarTabla(String nombreEquipo){
        String[][] x = {};
        String[] columnas = new String[]{"Nombre","Apellido","Posición"};
        modelT = new DefaultTableModel(x, columnas);
        
        Equipo e = DataLocal.equipos.get(nombreEquipo);
        int i = 0;
        for(Jugador jugador : e.getJugadores() ){
            modelT.insertRow(i, new Object[]{});
            modelT.setValueAt(jugador.getNombre(), i, 0);
            modelT.setValueAt(jugador.getApellido(), i, 1);
            modelT.setValueAt(jugador.getPosicion(), i, 2);
            i++;
        }
        equipos.getTablaJugadores().setModel(modelT);
    }
    
    public void subirArchivo(){
        ComprobarCampos comprobarCampos = new ComprobarCampos(equipos);
        File f = seleccionaArchivo.getSelectedFile();
        if(!comprobarCampos.ComprobarSubirAutomatico(f.getPath())){
            return;
        }
        
        CargarArchivos.cargarJugadores(f.getPath(), equipos.getNombreEquipo().getText());
        cargarEquipos(); 
        
        equipos.getAutomaticMessage().setText("El archivo ha sido subido exitosamente");
        equipos.getAutomaticMessage().setForeground(Color.green.darker());
        desactivarAutomatico();
        equipos.getAutomaticMessage().setVisible(true);
        equipos.getEquiposCargados().setModel(new DefaultComboBoxModel(dataArreglos.dataEquiposNoHelp().toArray()));
        try {
            EscribirArchivos.archivoEquipos();
        } catch (IOException ex) {
            Logger.getLogger(Equipos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desactivarMensajes(){
        equipos.getManualMessage().setVisible(false);
        equipos.getAutomaticMessage().setVisible(false);
    }
    
    public void setManualText(String s, Boolean b){
        equipos.getManualMessage().setVisible(false);
        equipos.getManualMessage().setText(s);
        if(b){
            equipos.getManualMessage().setForeground(Color.green.darker());
        }else{
            equipos.getManualMessage().setForeground(Color.red);
        }
        equipos.getManualMessage().setVisible(true);
    }
    
    public void borrarJugador(){
        if(equipos.getTablaJugadores().getSelectedRow() == -1){
            JOptionPane.showMessageDialog(equipos, "Seleccione una fila primero");
        }else{
            int sel = JOptionPane.showConfirmDialog(equipos, "¿Seguro que desea borrar el jugador?", 
                    "Borrar Jugador",JOptionPane.YES_NO_OPTION);
            if(sel == 0){
                modelT.removeRow(equipos.getTablaJugadores().getSelectedRow());
                Equipo equipo = DataLocal.getEquipo(equipos.getEquiposCargados().getSelectedItem().toString());
                equipo.getJugadores().remove(equipos.getTablaJugadores().getSelectedRow());
            }
        }
    }
    
    public void editarJugador(){
        if(equipos.getTablaJugadores().getSelectedRow() == -1){
            JOptionPane.showMessageDialog(equipos, "Seleccione una fila primero");
        }else{
            AbrirVistas.editJugadorNoClose(equipos, equipos.getTablaJugadores().getSelectedRow());
        }
    }
}
