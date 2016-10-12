/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javafootballboard.View.*;

public class WindowCloseListener extends WindowAdapter{

    Equipos equipos;
    EquipoRegistro equipoRegistro;
    Subir subir;
    Historico historico;
    Formato formato;
    EditJugador editJugador;
    
    public WindowCloseListener(Object frame){
        if(frame instanceof Equipos){
            equipos = (Equipos)frame;
        }else if(frame instanceof EquipoRegistro){
            equipoRegistro = (EquipoRegistro)frame;
        }else if(frame instanceof Subir){
            subir = (Subir)frame;
        }else if(frame instanceof Historico){
            historico = (Historico)frame;
        }else if(frame instanceof Formato){
            formato = (Formato)frame;
        }else if(frame instanceof EditJugador){
            editJugador = (EditJugador)frame;
        }
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        if(equipos != null){
            if(equipos.subir != null){
                equipos.subir.SC.tabEquipos.cargarComboBoxes();
                equipos.subir.setEnabled(true);
            }
            else if(equipos.iniciar != null){
                equipos.iniciar.setEnabled(true);
            }
            else if(equipos.historico != null){
                equipos.historico.setEnabled(true);
            }
            else{
                Menu menu = new Menu();
                menu.setVisible(true);
            }
            equipos.dispose();
        }
        if(equipoRegistro != null){
            if(equipoRegistro.equipos != null){
                equipoRegistro.equipos.EC.actualizarCampos();
                equipoRegistro.equipos.setEnabled(true);
                equipoRegistro.dispose();
            }
        }
        if(formato != null){
            if(formato.equipos != null){
                formato.equipos.setEnabled(true);
                formato.dispose();
            }
        }
        if(editJugador != null){
            editJugador.equipos.setEnabled(true);
            editJugador.dispose();
        }
    }
    
}