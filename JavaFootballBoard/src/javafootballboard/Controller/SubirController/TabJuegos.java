/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.SubirController;

import java.util.Date;
import java.util.Map;
import javafootballboard.Model.DataLocal;
import javafootballboard.Model.Juego;
import javafootballboard.View.Subir;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mabel
 */
public class TabJuegos {
    Subir subir;
    DefaultTableModel modeloB;
    
    public TabJuegos(Subir subir){
        this.subir = subir;
        inicializarCamposB();
        iniciarTabla();
    }
    
    public void iniciarTabla(){
        String[][] x = {};
        String[] columnas = new String[]{"Titulo","Score","Fecha"};
        modeloB = new DefaultTableModel(x, columnas);
        subir.getJTablePartidos().setModel(modeloB);
    }
    
    //Muestra error en pantalla si faltan campos por llenar
        public void mostrarErrorB(int i){
        switch (i) {
            case 0:
                subir.getJErrorB().setVisible(false);
                break;
            case 1:
                subir.getJErrorB().setText("Error: Faltan campos por rellenar");
                subir.getJErrorB().setVisible(true);
                break;
            default:
                break;
        }
    }
        
    public void inicializarCamposB(){
        subir.getJFecha().setDate(new Date());
        subir.getJHora1().setText("");
        subir.getJHora2().setText("");
        subir.getJMinuto1().setText("");
        subir.getJMinuto2().setText("");
        subir.getJSegundo1().setText("");
        subir.getJSegundo2().setText("");
        subir.getJLugar().setText("");
        subir.getJEstadio().setText("");
        subir.getJArbitro().setText("");
        subir.getJPuntajeA().setText("");
        subir.getJPuntajeB().setText("");
        subir.getJErrorB().setVisible(false);
    }
    
    //Comprueba que no hay campos por llenar  
    public boolean comprobarCampos(){
        if(subir.getJFecha().getDate().toString().equals("") ||
                subir.getJHora1().getText().equals("") ||
                subir.getJMinuto1().getText().equals("") ||
                subir.getJSegundo1().getText().equals("") ||
                subir.getJHora2().getText().equals("") ||
                subir.getJMinuto2().getText().equals("") ||
                subir.getJSegundo2().getText().equals("") ||
                subir.getJLugar().getText().equals("") ||
                subir.getJEstadio().getText().equals("") ||
                subir.getJArbitro().getText().equals("") ||
                subir.getJPuntajeA().getText().equals("") ||
                subir.getJPuntajeB().getText().equals("") ){
            mostrarErrorB(1);
            return false;
        }
        mostrarErrorB(0);  
        return true;
    }
    
    public void cargarTabla(){
        iniciarTabla();
        int i = 0;
        for(Map.Entry<String,Juego> partido : DataLocal.juegos.entrySet()){
            if(partido.getValue().getEquipoA() == subir.SC.equipoA && partido.getValue().getEquipoB() == subir.SC.equipoB ||
                partido.getValue().getEquipoB() == subir.SC.equipoA && partido.getValue().getEquipoA() == subir.SC.equipoB){
                modeloB.insertRow(i, new Object[]{});
                modeloB.setValueAt(partido.getValue().getTitulo(), i, 0);
                modeloB.setValueAt(partido.getValue().getPuntosA()+" - "+partido.getValue().getPuntosB(), i, 1);
                modeloB.setValueAt(partido.getValue().getFecha(), i, 2);
                i++;
            }
        }
        subir.getJTablePartidos().setModel(modeloB);
    }
}
