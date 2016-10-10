/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.SubirController;

import java.util.ArrayList;
import javafootballboard.Controller.dataArreglos;
import javafootballboard.Model.DataLocal;
import javafootballboard.Model.Equipo;
import javafootballboard.Model.Juego;
import javafootballboard.Model.Jugada;
import javafootballboard.Model.Jugador;
import javafootballboard.View.Subir;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class TabJugadas {
    
    Subir subir;
    DefaultListModel modeloC;
    
    public TabJugadas(Subir subir){
        this.subir = subir;
    }
    
    public void iniciarTabJugadas(){
        modeloC = new DefaultListModel();
        cargarComboBoxEquipos();
        cargarComboBoxJugadores();
        cargarComboBoxJugadas();
        iniciarLista();
    }
    
    public void iniciarLista(){
        int start = DataLocal.jugadas.size() - modeloC.getSize() - 1;
        for(int i=start; i<DataLocal.jugadas.size(); i++){
            DataLocal.jugadas.remove(i);
        }
        modeloC = new DefaultListModel();
        modeloC.addElement("Inicio del partido " + subir.SC.getFecha() + " " + subir.SC.getHoraInicio());
        subir.getJListJugadas().setModel(modeloC);
    }
    
    public void cargarComboBoxEquipos(){
        DefaultComboBoxModel modeloA = new DefaultComboBoxModel();
        modeloA.addElement(subir.getJComboEquipoA().getSelectedItem().toString());
        modeloA.addElement(subir.getJComboEquipoB().getSelectedItem().toString());
        subir.getJComboEquipo().setModel(modeloA);
    }
    
    public void cargarComboBoxJugadores(){
        Equipo equipo = DataLocal.getEquipo(subir.getJComboEquipo().getSelectedItem().toString());
        DefaultComboBoxModel model = new DefaultComboBoxModel(dataArreglos.dataJugadoresPosicion(equipo).toArray());
        subir.getJComboJugador().setModel(model);
    }
    
    public void cargarComboBoxJugadas(){
        DefaultComboBoxModel model = new DefaultComboBoxModel( DataLocal.diccionarioJugadas.toArray() );
        subir.getJComboJugadas().setModel(model);
    }
    
    public boolean comprobarCamposC(){
        if(subir.getJMinuto3().getText().length() == 0 ||
                subir.getJSegundo3().getText().length() == 0 ){
            mostrarErrorC(1);
            return false;
        }
        mostrarErrorC(0);
        return true;
    }
    
    public void mostrarErrorC(int i){
        switch (i) {
            case 0:
                subir.getJErrorC().setVisible(false);
                break;
            case 1:
                subir.getJErrorC().setText("Error: Faltan campos por llenar");
                subir.getJErrorC().setVisible(true);
                break;
             case 2:
                subir.getJErrorC().setText("Error: Debe incluir jugadas a la lista");
                subir.getJErrorC().setVisible(true);
                break;
            default:
                break;
        }
    }
    
    public void botonAgregarJugada(){
        if(comprobarCamposC()){
            agregarJugada();
            subir.getJSiguienteC().setEnabled(true);
        }
    }
    
    public void iniciarCamposC(){
        subir.getJComboEquipo().setSelectedIndex(0);
        subir.getJComboJugador().setSelectedIndex(0);
        subir.getJComboJugadas().setSelectedIndex(0);
        subir.getJMinuto3().setText("");
        subir.getJSegundo3().setText("");
        subir.getJErrorC().setVisible(false);
    }
    
    public void agregarJugada(){
        modeloC.addElement(subir.getJComboEquipo().getSelectedItem().toString()
                +": "+subir.getJComboJugador().getSelectedItem().toString()
                +" "+subir.getJComboJugadas().getSelectedItem().toString()
                +" "+subir.getJMinuto3().getText()+":"+subir.getJSegundo3().getText());
        
        subir.getJListJugadas().setModel(modeloC);
        String jugador = getNombreCompletoJugador();
        Juego juego = subir.SC.crearJuego();
        
        Jugada jugada = new Jugada(DataLocal.getJuego(juego.getCode()), 
                subir.getJComboJugadas().getSelectedItem().toString(), 
                DataLocal.getJugador(jugador), 
                DataLocal.getEquipo(subir.getJComboEquipo().getSelectedItem().toString()),
                subir.getJMinuto3().getText()+":"+subir.getJSegundo3().getText());
        
        DataLocal.getJuego(juego.getCode()).agregarJugada(jugada);
        DataLocal.jugadas.add(jugada);
    }
    
    public void eliminarJugada(int i){
        if(i > 0){
            modeloC.remove(i);
            subir.getJListJugadas().setModel(modeloC);
        }
        DataLocal.jugadas.get(i).getJuego().eliminarJugada(i);
        DataLocal.jugadas.remove(i);
    }
    
    public void agregarFin(){
        modeloC.addElement("Fin del partido "+subir.SC.getFecha()+" "+subir.SC.getHoraFin());
        subir.getJListJugadas().setModel(modeloC);
    }
    
    public void quitarFin(){
        int i = modeloC.getSize()-1;
        if(i>0){
            modeloC.remove(i);
            subir.getJListJugadas().setModel(modeloC);
        }
    }
    
    public String getNombreCompletoJugador(){
        String jugador = subir.getJComboJugador().getSelectedItem().toString();
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
