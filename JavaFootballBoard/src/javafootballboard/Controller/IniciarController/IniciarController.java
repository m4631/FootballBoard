/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.IniciarController;

import java.awt.Image;
import javafootballboard.Controller.AbrirVistas;
import javafootballboard.Controller.onlyDigitsListener;
import javafootballboard.Controller.onlyLettersListener;
import javafootballboard.Model.DataLocal;
import javafootballboard.Model.Equipo;
import javafootballboard.Model.Juego;
import javafootballboard.View.Iniciar;
import javafootballboard.View.Subir;
import javax.swing.ImageIcon;

public class IniciarController {
    Iniciar iniciar;
    
    public Juego juego;
    public Equipo equipoA;
    public Equipo equipoB;
    
    public TabEquipos tabEquipos;
    public TabJuegos tabJuegos;
    public TabJugadas tabJugadas;
    public TabGuardar tabGuardar;
    
    int puntA = 0;
    int puntB = 0;
    
    public IniciarController(Iniciar iniciar){
        equipoA = new Equipo();
        equipoB = new Equipo();
        this.iniciar = iniciar;
        asignarListeners();
        
        tabEquipos = new TabEquipos(iniciar);
        tabJuegos = new TabJuegos(iniciar);
        tabJugadas = new TabJugadas(iniciar);
        tabGuardar = new TabGuardar(iniciar);
        
        deshabilitarTabs();
    }
    
    public void activarBotonA(){
        // Si los equipos no son correctos el boton siguiente no se habilita
        if(tabEquipos.comprobarEquipos()){
            iniciar.getJSiguienteA().setEnabled(true);
            tabJuegos.cargarTabla();
            cambiarEstadoTab(1, true);     
        }else{
            iniciar.getJSiguienteA().setEnabled(false);
            cambiarEstadoTab(1, false);
        }
    }
    
    public void asignarListeners(){
        //Campos solo digitos
        iniciar.getJHora1().addKeyListener(new onlyDigitsListener(iniciar.getJHora1(),2));
        iniciar.getJMinuto1().addKeyListener(new onlyDigitsListener(iniciar.getJMinuto1(),2));
        iniciar.getJSegundo1().addKeyListener(new onlyDigitsListener(iniciar.getJSegundo1(),2));
        iniciar.getJHora2().addKeyListener(new onlyDigitsListener(iniciar.getJHora2(),2));
        iniciar.getJMinuto2().addKeyListener(new onlyDigitsListener(iniciar.getJMinuto2(),2));
        iniciar.getJSegundo2().addKeyListener(new onlyDigitsListener(iniciar.getJSegundo2(),2));
        
        //campos solo letras
        iniciar.getJLugar().addKeyListener(new onlyLettersListener(iniciar.getJLugar(), 40));
        iniciar.getJEstadio().addKeyListener(new onlyLettersListener(iniciar.getJEstadio(), 40));
        iniciar.getJArbitro().addKeyListener(new onlyLettersListener(iniciar.getJArbitro(), 40));
    }
    
    // Deshabilita inicialmente los tabs juego, jugada y guardar
    public void deshabilitarTabs(){
        for(int i = 1; i < 4; i++){
            cambiarEstadoTab(i, false);
        }
    }
    
    // Permite alterar el estado de un tab
    public void cambiarEstadoTab(int i, boolean n){
        iniciar.getJTabbedPane1().setEnabledAt(i, n);
    }
    
    //Asigna los valores al objeto Partido...
    public Juego crearJuego(){
        if(juego == null){
            juego = new Juego();
            juego.setArbitro(iniciar.getJArbitro().getText());
            juego.setEstadio(iniciar.getJEstadio().getText());
            juego.setCiudad(iniciar.getJLugar().getText());
            juego.setPuntosA(puntA);
            juego.setPuntosB(puntB);
            juego.setFecha(getFecha());

            juego.setEquipoA(DataLocal.getEquipo(iniciar.getJComboEquipoA().getSelectedItem().toString()));
            juego.setEquipoB(DataLocal.getEquipo(iniciar.getJComboEquipoB().getSelectedItem().toString()));

            juego.setHoraInicio(getHoraInicio());
            juego.setHoraFin(getHoraFin());

            juego.setTitulo();
            juego.setCode();
        }
        return juego;
    }
    
    public void cancelar(){
        if(juego!=null){
            DataLocal.juegos.remove(juego.getCode());
        }
        AbrirVistas.menu(iniciar);
    }
    
    public void tabSiguiente(){
        int cur = iniciar.getJTabbedPane1().getSelectedIndex();
        if(cur+1==1){
            equipoA = DataLocal.getEquipo(iniciar.getJComboEquipoA().getSelectedItem().toString());
            equipoB = DataLocal.getEquipo(iniciar.getJComboEquipoB().getSelectedItem().toString());
            tabJuegos.cargarTabla();
        }else if(cur+1==2){
            tabJugadas.iniciarTabJugadas();
        }else if(cur+1==3){
            
        }
        iniciar.getJTabbedPane1().setSelectedIndex(cur+1);
        cambiarEstadoTab(cur+1, true);
    }
    
    public void tabAnterior(){
        int cur = iniciar.getJTabbedPane1().getSelectedIndex();
        iniciar.getJTabbedPane1().setSelectedIndex(cur-1);
        cambiarEstadoTab(cur, false);
        cambiarEstadoTab(cur-1, true);
    }
    
    public String getHoraInicio(){
        return iniciar.getJHora1().getText()+":"+iniciar.getJMinuto1().getText()+":"+iniciar.getJSegundo1().getText();
    }
    
    public String getHoraFin(){
        return iniciar.getJHora2().getText()+":"+iniciar.getJMinuto2().getText()+":"+iniciar.getJSegundo2().getText();
    }
    
    public String getFecha(){
        return iniciar.getJFecha().getDate().toString();
    }
    
    public void cargarImagen(){
        iniciar.getJLImage().setIcon( new ImageIcon( new ImageIcon(getClass().getResource("/javafootballboard/Assets/canchaFutbol.png")).getImage().getScaledInstance(544, 175, Image.SCALE_DEFAULT) ) );
    }
}
