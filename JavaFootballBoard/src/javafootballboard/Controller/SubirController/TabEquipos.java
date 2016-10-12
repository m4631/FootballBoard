/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.SubirController;

import java.util.Map;
import javafootballboard.Controller.dataArreglos;
import javafootballboard.Model.DataLocal;
import javafootballboard.Model.Equipo;
import javafootballboard.View.*;
import javax.swing.DefaultComboBoxModel;

public class TabEquipos {
    Subir subir;
    
    public TabEquipos(Subir subir){
        this.subir = subir;
        cargarComboBoxes();
    }
    
    public boolean comprobarEquipos(){
        // Comprueba que se han seleccionado ambos equipos
        if( subir.getJComboEquipoA().getSelectedIndex() == 0 || subir.getJComboEquipoB().getSelectedIndex() == 0 ){
            mostrarErrorA(1);
            return false;
        }
        
        // Comprueba que no sean el mismo equipo
        if(subir.getJComboEquipoA().getSelectedIndex() == subir.getJComboEquipoB().getSelectedIndex()){
            mostrarErrorA(2);
            return false;
        }
        
        mostrarErrorA(0);
        return true;
    }
    
    public void mostrarErrorA(int i){
        switch (i) {
            case 0:
                subir.getJErrorA().setVisible(false);
                break;
            case 1:
                subir.getJErrorA().setText("Falta un equipo por seleccionar");
                subir.getJErrorA().setVisible(true);
                break;
            case 2:
                subir.getJErrorA().setText("Error: Los equipos seleccionados son el mismo");
                subir.getJErrorA().setVisible(true);
                break;
            default:
                break;
        }
    }
    
    public void cargarComboBoxes(){
        subir.getJComboEquipoA().setModel(new DefaultComboBoxModel(dataArreglos.dataEquipos().toArray()));
        subir.getJComboEquipoB().setModel(new DefaultComboBoxModel(dataArreglos.dataEquipos().toArray()));
    }
}
