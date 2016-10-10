/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.IniciarController;

import java.util.Date;
import java.util.Map;
import javafootballboard.Model.DataLocal;
import javafootballboard.Model.Juego;
import javafootballboard.View.Iniciar;
import javax.swing.table.DefaultTableModel;

public class TabJuegos {
    Iniciar iniciar;
    DefaultTableModel modeloB;
    
    public TabJuegos(Iniciar iniciar){
        this.iniciar = iniciar;
        inicializarCamposB();
        iniciarTabla();
        cargarTabla();
    }
    
    public void iniciarTabla(){
        String[][] x = {};
        String[] columnas = new String[]{"Titulo","Score","Fecha"};
        modeloB = new DefaultTableModel(x, columnas);
        iniciar.getJTablePartidos().setModel(modeloB);
    }
    
    //Muestra error en pantalla si faltan campos por llenar
        public void mostrarErrorB(int i){
        switch (i) {
            case 0:
                iniciar.getJErrorB().setVisible(false);
                break;
            case 1:
                iniciar.getJErrorB().setText("Error: Faltan campos por rellenar");
                iniciar.getJErrorB().setVisible(true);
                break;
            default:
                break;
        }
    }
        
    public void inicializarCamposB(){
        iniciar.getJFecha().setDate(new Date());
        iniciar.getJHora1().setText("");
        iniciar.getJHora2().setText("");
        iniciar.getJMinuto1().setText("");
        iniciar.getJMinuto2().setText("");
        iniciar.getJSegundo1().setText("");
        iniciar.getJSegundo2().setText("");
        iniciar.getJLugar().setText("");
        iniciar.getJEstadio().setText("");
        iniciar.getJArbitro().setText("");
        iniciar.getJErrorB().setVisible(false);
    }
    
    //Comprueba que no hay campos por llenar  
    public boolean comprobarCampos(){
        if(iniciar.getJFecha().getDate().toString().equals("") ||
                iniciar.getJHora1().getText().equals("") ||
                iniciar.getJMinuto1().getText().equals("") ||
                iniciar.getJSegundo1().getText().equals("") ||
                iniciar.getJHora2().getText().equals("") ||
                iniciar.getJMinuto2().getText().equals("") ||
                iniciar.getJSegundo2().getText().equals("") ||
                iniciar.getJLugar().getText().equals("") ||
                iniciar.getJEstadio().getText().equals("") ||
                iniciar.getJArbitro().getText().equals("") ){
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
            modeloB.insertRow(i, new Object[]{});
            modeloB.setValueAt(partido.getValue().getTitulo(), i, 0);
            modeloB.setValueAt(partido.getValue().getPuntosA()+" - "+partido.getValue().getPuntosB(), i, 1);
            modeloB.setValueAt(partido.getValue().getFecha(), i, 2);
            i++;
        }
        iniciar.getJTablePartidos().setModel(modeloB);
    }
}
