/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.SubirController;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Controller.AbrirVistas;
import javafootballboard.Controller.ArchivoController.CargarArchivos;
import javafootballboard.Controller.ArchivoController.EscribirArchivos;
import javafootballboard.Controller.FiltroDeArchivos;
import javafootballboard.Controller.onlyDigitsListener;
import javafootballboard.Controller.onlyLettersListener;
import javafootballboard.Model.*;
import javafootballboard.View.Equipos;
import javafootballboard.View.Subir;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

/**
 *
 * @author Mabel
 */
public class SubirController {
    Subir subir;
    
    public Juego juego;
    public Equipo equipoA;
    public Equipo equipoB;
    
    public TabEquipos tabEquipos;
    public TabJuegos tabJuegos;
    public TabJugadas tabJugadas;
    public TabGuardar tabGuardar;
    
    public JFileChooser seleccionaArchivo;
    
    public SubirController(Subir subir){
        this.subir = subir;
        asignarListeners();
        tabEquipos = new TabEquipos(subir);
        tabJuegos = new TabJuegos(subir);
        tabJugadas = new TabJugadas(subir);
        tabGuardar = new TabGuardar(subir);
        deshabilitarTabs();
    }
    
    public void activarBotonA(){
        // Si los equipos no son correctos el boton siguiente no se habilita
        if(tabEquipos.comprobarEquipos()){
            subir.getJSiguienteA().setEnabled(true);
            tabJuegos.cargarTabla();
            cambiarEstadoTab(1, true);     
        }else{
            subir.getJSiguienteA().setEnabled(false);
            cambiarEstadoTab(1, false);
        }
    }
    
    public void asignarListeners(){
        //Campos solo digitos
        subir.getJHora1().addKeyListener(new onlyDigitsListener(subir.getJHora1(),2));
        subir.getJMinuto1().addKeyListener(new onlyDigitsListener(subir.getJMinuto1(),2));
        subir.getJSegundo1().addKeyListener(new onlyDigitsListener(subir.getJSegundo1(),2));
        subir.getJHora2().addKeyListener(new onlyDigitsListener(subir.getJHora2(),2));
        subir.getJMinuto2().addKeyListener(new onlyDigitsListener(subir.getJMinuto2(),2));
        subir.getJSegundo2().addKeyListener(new onlyDigitsListener(subir.getJSegundo2(),2));
        subir.getJMinuto3().addKeyListener(new onlyDigitsListener(subir.getJMinuto3(),2));
        subir.getJSegundo3().addKeyListener(new onlyDigitsListener(subir.getJSegundo3(),2));
        
        //campos solo letras
        subir.getJLugar().addKeyListener(new onlyLettersListener(subir.getJLugar(), 40));
        subir.getJEstadio().addKeyListener(new onlyLettersListener(subir.getJEstadio(), 40));
        subir.getJArbitro().addKeyListener(new onlyLettersListener(subir.getJArbitro(), 40));
    }
    
    // Deshabilita inicialmente los tabs juego, jugada y guardar
    public void deshabilitarTabs(){
        for(int i = 1; i < 4; i++){
            cambiarEstadoTab(i, false);
        }
    }
    
    // Permite alterar el estado de un tab
    public void cambiarEstadoTab(int i, boolean n){
        subir.getJTabbedPane1().setEnabledAt(i, n);
    }
    
    //Asigna los valores al objeto Partido...
    public Juego crearJuego(){
        if(juego == null){
            juego = new Juego();
            juego.setArbitro(subir.getJArbitro().getText());
            juego.setEstadio(subir.getJEstadio().getText());
            juego.setCiudad(subir.getJLugar().getText());
            juego.setPuntosA(Integer.parseInt(subir.getJPuntajeA().getText()));
            juego.setPuntosB(Integer.parseInt(subir.getJPuntajeB().getText()));
            juego.setFecha(getFecha());

            juego.setEquipoA(DataLocal.getEquipo(subir.getJComboEquipoA().getSelectedItem().toString()));
            juego.setEquipoB(DataLocal.getEquipo(subir.getJComboEquipoB().getSelectedItem().toString()));

            juego.setHoraInicio(getHoraInicio());
            juego.setHoraFin(getHoraFin());

            juego.setTitulo();
            juego.setCode();
        }
        return juego;
    }
    
    public void cancelar(){
        AbrirVistas.menu(subir);
    }
    
    public void tabSiguiente(){
        int cur = subir.getJTabbedPane1().getSelectedIndex();
        subir.getJTabbedPane1().setSelectedIndex(cur+1);
        if(cur+1==1){
            equipoA = DataLocal.getEquipo(subir.getJComboEquipoA().getSelectedItem().toString());
            equipoB = DataLocal.getEquipo(subir.getJComboEquipoB().getSelectedItem().toString());
        }else if(cur+1==2){
            crearJuego();
            tabJugadas.iniciarTabJugadas();
        }else if(cur+1==3){
            tabGuardar.mostrarDatosD();
        }
        cambiarEstadoTab(cur+1, true);
    }
    
    public void seleccionarArchivo(){
        seleccionaArchivo = new JFileChooser ();
        seleccionaArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        seleccionaArchivo.setFileFilter(new FiltroDeArchivos());
        seleccionaArchivo.setMultiSelectionEnabled(false);
        
        int returnVal = seleccionaArchivo.showOpenDialog(subir);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = seleccionaArchivo.getSelectedFile();
            //This is where a real application would open the file.
            System.out.println("Opening: " + file.getName() + ".");
        } else {
            System.out.println("Open command cancelled by user.");
            return;
        }
        subirArchivo();
    }
    
    public void cargarJugadas(int rows){
        DefaultListModel model = new DefaultListModel();
        System.out.println(rows);
        for(int i=DataLocal.jugadas.size() - rows; i<DataLocal.jugadas.size(); i++){
            Jugada jugada = DataLocal.jugadas.get(i);
            Jugador jugador = jugada.getJugador();
            Equipo equipo = jugada.getEquipo();
            model.addElement(equipo.getNombre()+": "+jugador.getNombreCompleto() 
                    + " ["+ jugador.getPosicion() + "] " + jugada.getNombre() + jugada.getHora());
        }
        subir.getJListJugadas().setModel(model);
    }
    
    public void subirArchivo(){
        File f = seleccionaArchivo.getSelectedFile();
        
        System.out.println(f.getPath());
        int rows = CargarArchivos.cargarJugadas(f.getPath(), juego);
        cargarJugadas(rows);
        
        try {
            EscribirArchivos.archivoJugadas();
        } catch (IOException ex) {
            Logger.getLogger(Equipos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tabAnterior(){
        int cur = subir.getJTabbedPane1().getSelectedIndex();
        subir.getJTabbedPane1().setSelectedIndex(cur-1);
        cambiarEstadoTab(cur, false);
        cambiarEstadoTab(cur-1, true);
    }
    
    public String getHoraInicio(){
        return subir.getJHora1().getText()+":"+subir.getJMinuto1().getText()+":"+subir.getJSegundo1().getText();
    }
    
    public String getHoraFin(){
        return subir.getJHora2().getText()+":"+subir.getJMinuto2().getText()+":"+subir.getJSegundo2().getText();
    }
    
    public String getFecha(){
        return subir.getJFecha().getDate().toString();
    }
    
}
