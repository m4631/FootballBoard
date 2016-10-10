/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.EquipoRegistroController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Controller.ArchivoController.EscribirArchivos;
import javafootballboard.Model.DataLocal;
import javafootballboard.Model.Equipo;
import javafootballboard.Model.Jugador;
import javafootballboard.View.EquipoRegistro;
import javafootballboard.View.Equipos;

/**
 *
 * @author Mabel
 */
public class EquipoRegistroController {
    EquipoRegistro equipoRegistro;
    
    public EquipoRegistroController(EquipoRegistro equipoRegistro){
        this.equipoRegistro = equipoRegistro;
    }
    
    public void registrar() throws IOException{
        ComprobarCampos comprobarCampos = new ComprobarCampos(equipoRegistro);
        if(!comprobarCampos.ComprobarSubirManual()){
            return;
        }
        
        guardarCambios();
        equipoRegistro.getEquipos().EC.setManualText("El equipo fue cargado exitosamente!", true);
        equipoRegistro.getEquipos().EC.desactivarManual();
        equipoRegistro.getEquipos().EC.cargarEquipos();
        equipoRegistro.getEquipos().setEnabled(true);
        try {
            EscribirArchivos.archivoEquipos();
        } catch (IOException ex) {
            Logger.getLogger(Equipos.class.getName()).log(Level.SEVERE, null, ex);
        }
        equipoRegistro.dispose();
    }
    
    public void guardarCambios(){
        Equipo e = DataLocal.getEquipo(equipoRegistro.getNombreEquipo().getText());
        for(int i=0; i<5; i++){
            if( equipoRegistro.getNames().get(i).getText().length() != 0 && 
                    equipoRegistro.getLastnames().get(i).getText().length() != 0 && 
                    equipoRegistro.getPositions().get(i).getText().length() != 0){
                Jugador jugador = new Jugador(equipoRegistro.getNames().get(i).getText(), 
                        equipoRegistro.getLastnames().get(i).getText(), equipoRegistro.getPositions().get(i).getText());
                e.agregarJugador(jugador);
                DataLocal.jugadores.put(jugador.getNombreCompleto(), jugador);
            }
        }
    }
    
    public void cancelar(){
        equipoRegistro.getEquipos().EC.setManualText("Ningun equipo fue cargado manualmente", false);
        equipoRegistro.getEquipos().EC.desactivarManual();
        equipoRegistro.getEquipos().setEnabled(true);
        equipoRegistro.dispose();
    }
}
