/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.EquipoRegistroController;

import javafootballboard.View.EquipoRegistro;

/**
 *
 * @author Mabel
 */
public class ComprobarCampos {
    EquipoRegistro equipoRegistro;
    
    public ComprobarCampos(EquipoRegistro equipoRegistro){
        this.equipoRegistro = equipoRegistro;
    }
    
    public boolean ComprobarSubirManual(){
        equipoRegistro.getMensajeError().setVisible(false);
        if(nombreEquipoVacio() || !casillasCompletas()){
            equipoRegistro.getMensajeError().setVisible(true);
            return false;
        }
        return true;
    }
    
    public boolean nombreEquipoVacio(){
        if( equipoRegistro.getNombreEquipo().getText().length() == 0 ){
            equipoRegistro.getMensajeError().setText("El nombre del equipo no puede estar vacío");
            return true;
        }
        return false;
    }
    
    public boolean casillasCompletas(){
        boolean alert = false;
        int vacia = 0;
        for(int i=0; i<5; i++){
            if( equipoRegistro.getNames().get(i).getText().length() == 0 && 
                    equipoRegistro.getLastnames().get(i).getText().length() == 0 && 
                    equipoRegistro.getPositions().get(i).getText().length() == 0){
                vacia++;
            }else if(equipoRegistro.getNames().get(i).getText().length() != 0 && 
                    equipoRegistro.getLastnames().get(i).getText().length() != 0 && 
                    equipoRegistro.getPositions().get(i).getText().length() != 0){
                //Fila completada
            }else{
                alert = true;
            }
        }
        if(alert){
            equipoRegistro.getMensajeError().setText("Debe completar las 3 casillas si quiere guardar el jugador.");
            return false;
        }
        if(vacia == 5){
            equipoRegistro.getMensajeError().setText("Debe ingresar al menos 1 jugador.");
            return false;
        }
        return true;
    }
}
