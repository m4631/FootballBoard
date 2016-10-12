/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.SubirController;

import javafootballboard.Controller.dataArreglos;
import javafootballboard.Model.DataLocal;
import javafootballboard.Model.Equipo;
import javafootballboard.Model.Juego;
import javafootballboard.Model.Jugada;
import javafootballboard.View.Subir;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class TabJugadas {
    
    Subir subir;
    DefaultListModel modeloC;
    
    public int puntajeA;
    public int puntajeB;
    
    public TabJugadas(Subir subir){
        this.subir = subir;
    }
    
    public void iniciarTabJugadas(){
        puntajeA = 0;
        puntajeB = 0;
        modeloC = new DefaultListModel();
        cargarComboBoxEquipos();
        cargarComboBoxJugadores();
        cargarComboBoxJugadas();
        iniciarLista();
        subir.getJNombreEquipoA().setText(subir.getJComboEquipoA().getSelectedItem().toString());
        subir.getJNombreEquipoB().setText(subir.getJComboEquipoB().getSelectedItem().toString());
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
        
        if (subir.getJComboJugadas().getSelectedItem().toString().equalsIgnoreCase("gol")) {
            if (subir.getJComboEquipo().getSelectedIndex() > 0) {
                puntajeB++;
            } else {
                puntajeA++;
            }
            actualizarTablaPuntaje();
            actualizarPuntajes(DataLocal.getJuego(juego.getCode()));
        }
        
    }
    
    public void eliminarJugada(int i){
        if(i > 0){
            if(DataLocal.jugadas.get(i-1).getNombre().equalsIgnoreCase("gol")){
            if(subir.getJComboEquipo().getSelectedIndex()>0){
                puntajeB--;
            }else{
                puntajeA--;
            }
            actualizarTablaPuntaje();
            actualizarPuntajes(DataLocal.jugadas.get(i-1).getJuego());
        }
            modeloC.remove(i);
            subir.getJListJugadas().setModel(modeloC);
        }
        DataLocal.jugadas.get(i-1).getJuego().eliminarJugada(i-1);
        DataLocal.jugadas.remove(i-1);
    }
    
    public void actualizarTablaPuntaje(){
        if(puntajeA<10){
            subir.getJPuntajeA().setText("0"+Integer.toString(puntajeA));
        }else{
            subir.getJPuntajeA().setText(Integer.toString(puntajeA));
        }
        
        if(puntajeB<10){
            subir.getJPuntajeB().setText("0"+Integer.toString(puntajeB));
        }else{
            subir.getJPuntajeB().setText(Integer.toString(puntajeB));
        }
    }
    
    public void actualizarPuntajes(Juego juego){
        juego.setPuntosA(puntajeA);
        juego.setPuntosB(puntajeB);
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
