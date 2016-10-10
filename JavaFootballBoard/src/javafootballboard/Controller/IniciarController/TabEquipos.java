/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.IniciarController;

import javafootballboard.Controller.dataArreglos;
import javafootballboard.View.Iniciar;
import javax.swing.DefaultComboBoxModel;

public class TabEquipos {
    
    Iniciar iniciar;
    
    public TabEquipos(Iniciar iniciar){
        this.iniciar = iniciar;
        cargarComboBoxes();
    }
    
    public boolean comprobarEquipos(){
        // Comprueba que se han seleccionado ambos equipos
        if( iniciar.getJComboEquipoA().getSelectedIndex() == 0 || iniciar.getJComboEquipoB().getSelectedIndex() == 0 ){
            mostrarErrorA(1);
            return false;
        }
        
        // Comprueba que no sean el mismo equipo
        if(iniciar.getJComboEquipoA().getSelectedIndex() == iniciar.getJComboEquipoB().getSelectedIndex()){
            mostrarErrorA(2);
            return false;
        }
        
        mostrarErrorA(0);
        return true;
    }
    
    public void mostrarErrorA(int i){
        switch (i) {
            case 0:
                iniciar.getJErrorA().setVisible(false);
                break;
            case 1:
                iniciar.getJErrorA().setText("Falta un equipo por seleccionar");
                iniciar.getJErrorA().setVisible(true);
                break;
            case 2:
                iniciar.getJErrorA().setText("Error: Los equipos seleccionados son el mismo");
                iniciar.getJErrorA().setVisible(true);
                break;
            default:
                break;
        }
    }
    
    public void cargarComboBoxes(){
        iniciar.getJComboEquipoA().setModel(new DefaultComboBoxModel(dataArreglos.dataEquipos().toArray()));
        iniciar.getJComboEquipoB().setModel(new DefaultComboBoxModel(dataArreglos.dataEquipos().toArray()));
    }
}
