/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.IniciarController;

import java.util.ArrayList;
import javafootballboard.Controller.dataArreglos;
import javafootballboard.Model.DataLocal;
import javafootballboard.Model.Equipo;
import javafootballboard.Model.Juego;
import javafootballboard.Model.Jugada;
import javafootballboard.View.Iniciar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class TabJugadas {
    Iniciar iniciar;
    DefaultTableModel modeloC;
    
    public TabJugadas(Iniciar iniciar){
        this.iniciar = iniciar;
    }
    
    public void acabarPartido(){
        iniciar.IC.tabSiguiente();
        iniciar.IC.tabGuardar.mostrarDatosD();
    }
    
    public void tiempoFuera(){
        if(iniciar.getTiempoFuera().getText().equals("Tiempo fuera")){
            iniciar.getGuardarTiempo().setEnabled(false);
            iniciar.getJComboEquipo().setEnabled(false);
            iniciar.getJComboJugador().setEnabled(false);
            iniciar.getJComboJugadas().setEnabled(false);
            iniciar.getRegistrarJugada().setEnabled(false);
            iniciar.getTiempoFuera().setText("Reanudar tiempo");
        }else{
            iniciar.getGuardarTiempo().setEnabled(true);
            iniciar.getTiempoFuera().setText("Tiempo fuera");
        }
    }
    
    public void iniciarTabJugadas(){
        cargarComboBoxEquipos();
        cargarComboBoxJugadas();
        cargarComboBoxJugadores();
        iniciarTablaJugadas();
    }
    
    public void iniciarTablaJugadas(){
        String[][] x = {};
        modeloC = new DefaultTableModel(x, new String[]{"Equipo","Jugador","Jugada","Tiempo"});
        iniciar.getTablaJugadas().setModel(modeloC);
    }
    
    public void activateFromEquipo(){
        if(iniciar.getJComboEquipo().getSelectedIndex()>0){
            iniciar.getJComboJugador().setEnabled(true);
        }
        else{
            iniciar.getJComboJugador().setEnabled(false);
            iniciar.getJComboJugadas().setEnabled(false);
            iniciar.getRegistrarJugada().setEnabled(false);
        }
    }
    
    public void activateFromJugador(){
        if(iniciar.getJComboJugador().getSelectedIndex()>0){
            iniciar.getJComboJugadas().setEnabled(true);
        }
        else{
            iniciar.getJComboJugadas().setEnabled(false);
            iniciar.getRegistrarJugada().setEnabled(false);
        }
    }
    
    public void activateFromJugadas(){
        if(iniciar.getJComboJugadas().getSelectedIndex()>0){
            iniciar.getRegistrarJugada().setEnabled(true);
        }
        else{
            iniciar.getRegistrarJugada().setEnabled(false);
        }
    }
    
    public void cargarComboBoxEquipos(){
        DefaultComboBoxModel modeloA = new DefaultComboBoxModel();
        modeloA.addElement("Seleccionar Equipo...");
        modeloA.addElement(iniciar.IC.equipoA.getNombre());
        modeloA.addElement(iniciar.IC.equipoB.getNombre());
        iniciar.getJComboEquipo().setModel(modeloA);
    }
    
    public void cargarComboBoxJugadores(){
        Equipo equipo = DataLocal.getEquipo(iniciar.getJComboEquipo().getSelectedItem().toString());
        ArrayList<String> temp = dataArreglos.dataJugadoresPosicion(equipo);
        temp.add(0, "Seleccionar Jugador...");
        DefaultComboBoxModel model = new DefaultComboBoxModel(temp.toArray());
        iniciar.getJComboJugador().setModel(model);
    }
    
    public void cargarComboBoxJugadas(){
        ArrayList<String> temp = DataLocal.diccionarioJugadas;
        temp.add(0, "Seleccionar Jugada...");
        DefaultComboBoxModel model = new DefaultComboBoxModel( temp.toArray() );
        iniciar.getJComboJugadas().setModel(model);
    }
    
    public void sumaPuntos(){
        if(iniciar.getJComboJugadas().getSelectedItem().toString().equals("Gol")){
            if(iniciar.getJComboEquipo().getSelectedIndex() == 0){
                iniciar.IC.puntA++;
            }else{
                iniciar.IC.puntB++;
            }
        }
    }
    
    public void registrarJugada(){
        agregarJugada();
        iniciar.getJComboEquipo().setEnabled(false);
        iniciar.getJComboJugador().setEnabled(false);
        iniciar.getJComboJugadas().setEnabled(false);
        iniciar.getRegistrarJugada().setEnabled(false);
    }
    
    public void acabarReanudar(){
        if(iniciar.getAcabarReanudar().getText().equals("Acabar tiempo")){
            iniciar.getAcabarReanudar().setText("Reanudar tiempo");
            iniciar.getGuardarTiempo().setEnabled(false);
            iniciar.getAcabarPartido().setEnabled(false);
            iniciar.getTiempoFuera().setEnabled(false);
        }else{
            iniciar.getAcabarReanudar().setText("Acabar tiempo");
            iniciar.getGuardarTiempo().setEnabled(true);
            iniciar.getTiempoFuera().setEnabled(true);
            iniciar.getAcabarPartido().setEnabled(true);
        }
    }
    
    public void agregarJugada(){
        int rows  = iniciar.getTablaJugadas().getRowCount();
        modeloC.insertRow(rows, new Object[]{});
        modeloC.setValueAt(iniciar.getJComboEquipo().getSelectedItem().toString(), rows, 0);
        modeloC.setValueAt(iniciar.getJComboJugador().getSelectedItem().toString(), rows, 1);
        modeloC.setValueAt(iniciar.getJComboJugadas().getSelectedItem().toString(), rows, 2);
        modeloC.setValueAt(iniciar.getJMinuto().getText() + ":" + iniciar.getJSegundo().getText(), rows, 3);
        iniciar.getTablaJugadas().setModel(modeloC);
        sumaPuntos();
        
        String jugador = getNombreCompletoJugador();
        Juego juego = iniciar.IC.crearJuego();
        
        Jugada jugada = new Jugada(DataLocal.getJuego(juego.getCode()), 
                iniciar.getJComboJugadas().getSelectedItem().toString(), 
                DataLocal.getJugador(jugador), 
                DataLocal.getEquipo(iniciar.getJComboEquipo().getSelectedItem().toString()),
                iniciar.getJMinuto().getText()+":"+iniciar.getJSegundo().getText());
        
        DataLocal.getJuego(juego.getCode()).agregarJugada(jugada);
        DataLocal.getJuego(juego.getCode()).setPuntosA(iniciar.IC.puntA);
        DataLocal.getJuego(juego.getCode()).setPuntosB(iniciar.IC.puntB);
        DataLocal.jugadas.add(jugada);
    }
    
    public void iniciarPartido(){
        iniciar.getIniciarPartido().setEnabled(false);
        iniciar.getAcabarReanudar().setEnabled(true);
        iniciar.getAcabarPartido().setEnabled(true);
        iniciar.getGuardarTiempo().setEnabled(true);
        iniciar.getTiempoFuera().setEnabled(true);
    }
    
    public void eliminarJugada(int i){
        if(i > 0){
            modeloC.removeRow(i);
            iniciar.getTablaJugadas().setModel(modeloC);
        }
        DataLocal.jugadas.get(i).getJuego().eliminarJugada(i);
        DataLocal.jugadas.remove(i);
    }
    
    public String getNombreCompletoJugador(){
        String jugador = iniciar.getJComboJugador().getSelectedItem().toString();
        String partesJugador[] = jugador.split(" ");
        
        String nombreJugador = "";
        for(String s : partesJugador){
            if(s.charAt(0) == '['){
                break;
            }
            if(nombreJugador.length() > 0) nombreJugador = nombreJugador.concat(" ");
            nombreJugador = nombreJugador.concat(s);
        }
        return nombreJugador;
    }
}
