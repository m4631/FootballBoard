/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller;

import javafootballboard.View.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class AbrirVistas {
    
    public static void menu(Equipos equipos){
        Menu menu = new Menu();
        menu.setVisible(true);
        equipos.dispose();
    }
    public static void menu(Historico historico){
        Menu menu = new Menu();
        menu.setVisible(true);
        historico.dispose();
    }
    public static void menu(Subir subir){
        Menu menu = new Menu();
        menu.setVisible(true);
        subir.dispose();
    }
    public static void menu(Iniciar iniciar){
        Menu menu = new Menu();
        menu.setVisible(true);
        iniciar.dispose();
    }
    public static void menu(EquipoRegistro equipoRegistro){
        Menu menu = new Menu();
        menu.setVisible(true);
        equipoRegistro.dispose();
    }
    
    public static void equipos(Menu menu){
        Equipos equipos = new Equipos();
        equipos.setVisible(true);
        menu.dispose();
    }
    
    public static void formatoNoClose(Equipos equipos, int i){
        Formato formato = new Formato(equipos);
        formato.setVisible(true);
        formato.getJTabbedPane1().setSelectedIndex(i);
        equipos.setEnabled(false);
        formato.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        formato.addWindowListener(new WindowCloseListener(formato));
    }
    
    public static void formatoNoClose(Subir subir, int i){
        Formato formato = new Formato(subir);
        formato.setVisible(true);
        formato.getJTabbedPane1().setSelectedIndex(i);
        subir.setEnabled(false);
        formato.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        formato.addWindowListener(new WindowCloseListener(formato));
    }
    
    public static void equiposRegistroNoClose(Equipos equipo){
        EquipoRegistro equipoRegistro = new EquipoRegistro(equipo);
        equipoRegistro.setVisible(true);
        equipo.setEnabled(false);
        equipoRegistro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        equipoRegistro.addWindowListener(new WindowCloseListener(equipoRegistro));
    }
    
    public static void equiposNoClose(Subir subir){
        Equipos equipos = new Equipos(subir);
        equipos.setVisible(true);
        subir.setEnabled(false);
        equipos.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        equipos.addWindowListener(new WindowCloseListener(equipos));
    }
    
    public static void equiposNoClose(Historico historico, boolean b, String equipoName){
        Equipos equipos = new Equipos(historico, b);
        equipos.getEquiposCargados().setSelectedItem(equipoName);
        equipos.setVisible(true);
        historico.setEnabled(false);
        equipos.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        equipos.addWindowListener(new WindowCloseListener(equipos));
    }
    
    public static void equiposNoClose(Iniciar iniciar){
        Equipos equipos = new Equipos(iniciar);
        equipos.setVisible(true);
        iniciar.setEnabled(false);
        equipos.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        equipos.addWindowListener(new WindowCloseListener(equipos));
    }
    
    public static void editJugadorNoClose(Equipos equipos, int row){
        EditJugador editJugador = new EditJugador(equipos, row);
        editJugador.setVisible(true);
        equipos.setEnabled(false);
        equipos.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        equipos.addWindowListener(new WindowCloseListener(editJugador));
    }
    
    public static void historico(Menu menu){
        Historico historico = new Historico();
        historico.setVisible(true);
        menu.dispose();
    }
    
    public static void subir(Menu menu){
        Subir subir = new Subir();
        subir.setVisible(true);
        menu.dispose();
    }
    
    public static void iniciar(Menu menu){
        Iniciar iniciar = new Iniciar();
        iniciar.setVisible(true);
        menu.dispose();
    }
}
