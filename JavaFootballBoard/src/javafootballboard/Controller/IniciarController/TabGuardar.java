/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.Controller.IniciarController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Controller.AbrirVistas;
import javafootballboard.Controller.ArchivoController.EscribirArchivos;
import javafootballboard.Model.Juego;
import javafootballboard.View.Iniciar;
import javafootballboard.View.Subir;
import javax.swing.JOptionPane;

public class TabGuardar {
    Iniciar iniciar;
    
    public TabGuardar(Iniciar iniciar){
        this.iniciar = iniciar;
    }
    
    public void mostrarDatosD(){
        Juego juego = iniciar.IC.crearJuego();
        juego.setHoraFin(calcularHoraFin());
        iniciar.getJLTitulo().setText(juego.getTitulo());
        iniciar.getJLScore().setText("[ "+juego.getPuntosA()+" - "+juego.getPuntosB()+" ]");
        
        if(juego.getPuntosA()>=juego.getPuntosB()){
               iniciar.getJLEquipoA().setText(juego.getEquipoA().getNombre());
               iniciar.getJLEquipoB().setText(juego.getEquipoB().getNombre());
        }else{
               iniciar.getJLEquipoB().setText(juego.getEquipoA().getNombre());
               iniciar.getJLEquipoA().setText(juego.getEquipoB().getNombre());
        }
        
        if(juego.getPuntosA()==juego.getPuntosB()){
            iniciar.getJ1().setText("Empate: ");
            iniciar.getJ2().setText("Empate: ");
        }
     
        iniciar.getJLLugar().setText(juego.getCiudad());
        iniciar.getJLFecha().setText(juego.getFecha());
        iniciar.getJLHoraInicio().setText(juego.getHoraInicio());
        iniciar.getJLHoraFin().setText(juego.getHoraFin());
        iniciar.getJLEstadio().setText(juego.getEstadio());
    }

    public void terminarProceso(){
        guardarDB();
        AbrirVistas.menu(iniciar);
    }
    
    public boolean guardarDB(){
        try {
            EscribirArchivos.archivoJuegos();
            EscribirArchivos.archivoJugadas();
            JOptionPane.showMessageDialog(iniciar, "Partido guardado correctamente");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Subir.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(iniciar, "Ha ocurrido un error al momento de guardar");
            return false;
        }
    }
    
    public String calcularHoraFin(){
        String horaFin = iniciar.IC.getHoraInicio();
        String ultimaHora = iniciar.IC.tabJugadas.tiempoF;
        
        int h = Integer.parseInt(horaFin.substring(0, 2));
        int m = Integer.parseInt(horaFin.substring(3, 5));
        int s = Integer.parseInt(horaFin.substring(6, 8));
        
        int mm = Integer.parseInt(ultimaHora.substring(0, 2));
        int ss = Integer.parseInt(ultimaHora.substring(3, 5));
        
        horaFin="";
        int resultadoS = ss + s;
        while(resultadoS>=60){
            mm++;
            resultadoS-=60;
        }
        
        int resultadoM = m + mm;
        while(resultadoM>=60){
            h++;
            resultadoM-=60;
        }
        
        if(h>24){
           horaFin = "00:";
        }else if(h<10){
           horaFin = "0"+h+":";
        }else{
            horaFin = h+":";
        }
       
        if(resultadoM<10){
            horaFin= horaFin+"0"+resultadoM+":";
        }else{
            horaFin= horaFin+resultadoM+":";
        }
        
        if(resultadoS<10){
            horaFin= horaFin+"0"+resultadoS;
        }else{
            horaFin= horaFin+Integer.toString(resultadoS);
        }
        System.out.println(h+" "+resultadoM+" "+resultadoS);
        return horaFin;
    }
}
