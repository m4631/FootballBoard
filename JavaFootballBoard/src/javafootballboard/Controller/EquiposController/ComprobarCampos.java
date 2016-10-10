/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.EquiposController;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Model.*;
import javafootballboard.View.Equipos;

/**
 *
 * @author Mabel
 */
public class ComprobarCampos {
    Equipos equipos;
    
    ComprobarCampos(Equipos equipos){
        this.equipos = equipos;
    }
    
    public boolean errorFormato(String path){
        if(formatoCorrecto(path)){
            return false;
        }else{
            equipos.getAutomaticMessage().setText("El formato del archivo es incorrecto");
            return true;
        }
    }
    
    public boolean formatoCorrecto(String path){
        try {
            BufferedReader lector = new BufferedReader(new FileReader(path));
            String linea = lector.readLine(); //Leo el encabezado del archivo
            while((linea = lector.readLine()) != null){
                String columnas[] = linea.split(",");
                if(columnas.length < 3){
                    return false;
                }
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ComprobarCampos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean ComprobarSubirAutomatico(String path){
        equipos.getAutomaticMessage().setVisible(false);
        if(nombreEquipoVacio() || archivoDuplicado() || errorFormato(path)){
            equipos.getAutomaticMessage().setForeground(Color.red);
            equipos.getAutomaticMessage().setVisible(true);
            return false;
        }
        return true;
    }
    
    public boolean nombreEquipoVacio(){
        if(equipos.getNombreEquipo().getText().length() == 0 || equipos.getNombreEquipo().getText().equals("Nombre del equipo")){
            equipos.getAutomaticMessage().setText("Necesita nombrar su equipo");
            return true;
        }
        return false;
    }
    
    public boolean archivoDuplicado(){
        if(DataLocal.equipos.containsKey(equipos.getNombreEquipo().getText())){
            equipos.getAutomaticMessage().setText("Ya existe un equipo con este nombre");
            return true;
        }
        return false;
    }
    
}
