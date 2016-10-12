/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.IniciarController;

import java.util.ArrayList;
import javafootballboard.Controller.Cronometro;
import javafootballboard.Controller.dataArreglos;
import javafootballboard.Model.DataLocal;
import javafootballboard.Model.Equipo;
import javafootballboard.Model.Juego;
import javafootballboard.Model.Jugada;
import javafootballboard.View.Iniciar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class TabJugadas {

    Iniciar iniciar;
    DefaultTableModel modeloC;
    Cronometro cronometro;
    Thread hilo;
    String tiempo;
    String tiempoF;
    
    ImageIcon gifGol;
    ImageIcon gifPase;
    ImageIcon gifFalta;
    ImageIcon gifFueraLinea;
    ImageIcon defaultGif;
    
    public TabJugadas(Iniciar iniciar) {
        this.iniciar = iniciar;
    }

    public void acabarPartido() {
        if (iniciar.getAcabarPartido().getText().equals("Acabar partido")) {
            cronometro.pausar();
            tiempoF = iniciar.getJTiempo().getText();
            // Calcular tiempo de fin del partido
            iniciar.getAcabarPartido().setText("Cancelar fin");
            iniciar.getGuardarTiempo().setEnabled(false);
            iniciar.getJComboEquipo().setEnabled(false);
            iniciar.getJComboJugador().setEnabled(false);
            iniciar.getJComboJugadas().setEnabled(false);
            iniciar.getRegistrarJugada().setEnabled(false);
            iniciar.getTiempoFuera().setEnabled(false);
            iniciar.getAcabarReanudar().setEnabled(false);
            iniciar.IC.tabSiguiente();
            iniciar.IC.tabGuardar.mostrarDatosD();
        } else {
            iniciar.getTiempoFuera().setEnabled(true);
            iniciar.getAcabarReanudar().setEnabled(true);
            iniciar.getGuardarTiempo().setEnabled(true);
            iniciar.getAcabarPartido().setText("Acabar partido");
        }
    }

    public void tiempoFuera() {
        if (iniciar.getTiempoFuera().getText().equals("Tiempo fuera")) {
            iniciar.getGuardarTiempo().setEnabled(false);
            iniciar.getJComboEquipo().setEnabled(false);
            iniciar.getJComboJugador().setEnabled(false);
            iniciar.getJComboJugadas().setEnabled(false);
            iniciar.getRegistrarJugada().setEnabled(false);
            iniciar.getAcabarPartido().setEnabled(false);
            iniciar.getAcabarReanudar().setEnabled(false);
            iniciar.getTiempoFuera().setText("Reanudar tiempo");
            cronometro.pausar();
        } else {
                        iniciar.getAcabarPartido().setEnabled(true);
            iniciar.getAcabarReanudar().setEnabled(true);
            iniciar.getGuardarTiempo().setEnabled(true);
            iniciar.getTiempoFuera().setText("Tiempo fuera");
            cronometro.reanudar();
        }
    }

    public void iniciarTabJugadas() {
        cronometro = new Cronometro(iniciar.getJTiempo());
        hilo = new Thread(cronometro);
        tiempo = "";
        cargarTablaScore();
        cargarComboBoxEquipos();
        cargarComboBoxJugadas();
        cargarComboBoxJugadores();
        iniciarTablaJugadas();
        iniciarGifs();
    }
    public void cargarTablaScore(){
        iniciar.getJNombreEquipoA().setText(iniciar.getJComboEquipoA().getSelectedItem().toString());
        iniciar.getJNombreEquipoB().setText(iniciar.getJComboEquipoB().getSelectedItem().toString());
    }
    public void iniciarTablaJugadas() {
        String[][] x = {};
        modeloC = new DefaultTableModel(x, new String[]{"Equipo", "Jugador", "Jugada", "Tiempo"});
        iniciar.getTablaJugadas().setModel(modeloC);
    }

    public void activateFromEquipo() {
        if (iniciar.getJComboEquipo().getSelectedIndex() > 0) {
            iniciar.getJComboJugador().setEnabled(true);
        } else {
            iniciar.getJComboJugador().setEnabled(false);
            iniciar.getJComboJugadas().setEnabled(false);
            iniciar.getRegistrarJugada().setEnabled(false);
        }
    }

    public void activateFromJugador() {
        if (iniciar.getJComboJugador().getSelectedIndex() > 0) {
            iniciar.getJComboJugadas().setEnabled(true);
        } else {
            iniciar.getJComboJugadas().setEnabled(false);
            iniciar.getRegistrarJugada().setEnabled(false);
        }
    }

    public void activateFromJugadas() {
        if (iniciar.getJComboJugadas().getSelectedIndex() > 0) {
            iniciar.getRegistrarJugada().setEnabled(true);
        } else {
            iniciar.getRegistrarJugada().setEnabled(false);
        }
    }

    public void cargarComboBoxEquipos() {
        DefaultComboBoxModel modeloA = new DefaultComboBoxModel();
        modeloA.addElement("Seleccionar Equipo...");
        modeloA.addElement(iniciar.IC.equipoA.getNombre());
        modeloA.addElement(iniciar.IC.equipoB.getNombre());
        iniciar.getJComboEquipo().setModel(modeloA);
    }

    public void cargarComboBoxJugadores() {
        Equipo equipo = DataLocal.getEquipo(iniciar.getJComboEquipo().getSelectedItem().toString());
        ArrayList<String> temp = dataArreglos.dataJugadoresPosicion(equipo);
        temp.add(0, "Seleccionar Jugador...");
        DefaultComboBoxModel model = new DefaultComboBoxModel(temp.toArray());
        iniciar.getJComboJugador().setModel(model);
    }

    public void cargarComboBoxJugadas() {
        ArrayList<String> temp = DataLocal.diccionarioJugadas;
        temp.add(0, "Seleccionar Jugada...");
        DefaultComboBoxModel model = new DefaultComboBoxModel(temp.toArray());
        iniciar.getJComboJugadas().setModel(model);
    }

    public void sumaPuntos() {
        if (iniciar.getJComboJugadas().getSelectedItem().toString().equals("Gol")) {
            if (iniciar.getJComboEquipo().getSelectedIndex() == 1) {
                iniciar.IC.puntA++;
            } else {
                iniciar.IC.puntB++;
            }
        }
    }

    public void registrarJugada() {
        agregarJugada();
        animar();
        iniciar.getJComboEquipo().setEnabled(false);
        iniciar.getJComboJugador().setEnabled(false);
        iniciar.getJComboJugadas().setEnabled(false);
        iniciar.getRegistrarJugada().setEnabled(false);
    }

    public void acabarReanudar() {
        if (iniciar.getAcabarReanudar().getText().equals("Acabar tiempo")) {
            iniciar.getAcabarReanudar().setText("Reanudar tiempo");
            iniciar.getGuardarTiempo().setEnabled(false);
            iniciar.getAcabarPartido().setEnabled(false);
            iniciar.getTiempoFuera().setEnabled(false);
            cronometro.pausar();
        } else {
            iniciar.getAcabarReanudar().setText("Acabar tiempo");
            iniciar.getGuardarTiempo().setEnabled(true);
            iniciar.getTiempoFuera().setEnabled(true);
            iniciar.getAcabarPartido().setEnabled(true);
            cronometro.reanudar();
        }
    }

    public void agregarJugada() {
        int rows = iniciar.getTablaJugadas().getRowCount();
        modeloC.insertRow(rows, new Object[]{});
        modeloC.setValueAt(iniciar.getJComboEquipo().getSelectedItem().toString(), rows, 0);
        modeloC.setValueAt(iniciar.getJComboJugador().getSelectedItem().toString(), rows, 1);
        modeloC.setValueAt(iniciar.getJComboJugadas().getSelectedItem().toString(), rows, 2);
        modeloC.setValueAt(this.tiempo, rows, 3);
        iniciar.getTablaJugadas().setModel(modeloC);
        sumaPuntos();

        String jugador = getNombreCompletoJugador();
        Juego juego = iniciar.IC.crearJuego();

        Jugada jugada = new Jugada(DataLocal.getJuego(juego.getCode()),
                iniciar.getJComboJugadas().getSelectedItem().toString(),
                DataLocal.getJugador(jugador),
                DataLocal.getEquipo(iniciar.getJComboEquipo().getSelectedItem().toString()),
                this.tiempo);

        DataLocal.getJuego(juego.getCode()).agregarJugada(jugada);
        DataLocal.getJuego(juego.getCode()).setPuntosA(iniciar.IC.puntA);
        DataLocal.getJuego(juego.getCode()).setPuntosB(iniciar.IC.puntB);
        DataLocal.jugadas.add(jugada);
        
        actualizarTablaPuntaje();
    }

    public void iniciarPartido() {
        iniciar.getIniciarPartido().setEnabled(false);
        iniciar.getAcabarReanudar().setEnabled(true);
        iniciar.getAcabarPartido().setEnabled(true);
        iniciar.getGuardarTiempo().setEnabled(true);
        iniciar.getTiempoFuera().setEnabled(true);
        hilo.start();
    }

    public void eliminarJugada(int i) {
        if (i > 0) {
            modeloC.removeRow(i);
            iniciar.getTablaJugadas().setModel(modeloC);
        }
        DataLocal.jugadas.get(i).getJuego().eliminarJugada(i);
        DataLocal.jugadas.remove(i);
    }

    public String getNombreCompletoJugador() {
        String jugador = iniciar.getJComboJugador().getSelectedItem().toString();
        String partesJugador[] = jugador.split(" ");

        String nombreJugador = "";
        for (String s : partesJugador) {
            if (s.charAt(0) == '[') {
                break;
            }
            if (nombreJugador.length() > 0) {
                nombreJugador = nombreJugador.concat(" ");
            }
            nombreJugador = nombreJugador.concat(s);
        }
        return nombreJugador;
    }

    public void guardarTiempo() {
        this.tiempo = iniciar.getJTiempo().getText();
    }
    
    public void actualizarTablaPuntaje(){
        if(iniciar.IC.puntA<10){
            iniciar.getJPuntajeA().setText("0"+iniciar.IC.puntA);
        }else{
            iniciar.getJPuntajeA().setText(Integer.toString(iniciar.IC.puntA));
        }
        if(iniciar.IC.puntB<10){
            iniciar.getJPuntajeB().setText("0"+iniciar.IC.puntB);
        }else{
            iniciar.getJPuntajeB().setText(Integer.toString(iniciar.IC.puntB));
        }
    }
    
    public void animar(){
        
        if(iniciar.getJComboJugadas().getSelectedItem().toString().equalsIgnoreCase("gol")){
            iniciar.getJGif().setIcon(gifGol);
        }else if(iniciar.getJComboJugadas().getSelectedItem().toString().equalsIgnoreCase("pase")){
            iniciar.getJGif().setIcon(gifPase);
        }else if(iniciar.getJComboJugadas().getSelectedItem().toString().equalsIgnoreCase("falta")){
            iniciar.getJGif().setIcon(gifFalta);
        }else {
            iniciar.getJGif().setIcon(gifFueraLinea);
        }
    }
    
    public void iniciarGifs(){
        gifGol = new ImageIcon(getClass().getResource("/javafootballboard/Assets/gol.gif"));
        gifFalta = new ImageIcon(getClass().getResource("/javafootballboard/Assets/falta.gif"));
        gifPase = new ImageIcon(getClass().getResource("/javafootballboard/Assets/pase.gif"));
        gifFueraLinea = new ImageIcon(getClass().getResource("/javafootballboard/Assets/fueraDeJuego.gif"));
        defaultGif = new ImageIcon(getClass().getResource("/javafootballboard/Assets/inicio.gif"));
    }
    
    public void defaultGif(){
        iniciar.getJGif().setIcon(defaultGif);
    }
}
