/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Naty
 */
public class Juego {
   String cod;
   String titulo;
   String estadio;
   String ciudad;
   String fecha;
   String arbitro;
   int puntosA;
   int puntosB;
   
   public Juego(String equipoA, String equipoB, String estadio, String fecha,String arbitro, String ciudad){
       this.estadio = estadio;
       this.ciudad = ciudad;
       this.arbitro = arbitro;
       this.fecha = fecha;
       setTitulo(equipoA,equipoB);
       setCod(); 
       
   }
   
   private void setTitulo(String equipoA, String equipoB){
       this.titulo = equipoA +" vs "+ equipoB;
   }
   
    private void setCod(){
       this.cod = this.titulo + this.fecha;
   }
    
    public void setEstadio(String Estadio){
       this.estadio = Estadio;
   }
    public void setFecha(String fecha){
       this.fecha = fecha;
   }
    public void setArbitro(String arbitro){
       this.arbitro = arbitro;
   }
    public void setCiudad(String ciudad){
       this.ciudad = ciudad;
   }   
    public void setPuntosA(int puntosA){
       this.puntosA = puntosA;
   }
     public void setPuntosB(int puntosB){
       this.puntosB = puntosB;
   }
   
     public String getEstadio(){
       return estadio;
   }
     public String getFecha(){
       return fecha;
   }
     public String getArbitro(){
       return arbitro;
   }
     public String getCiudad(){
       return ciudad;
   }
     public int getPuntosA(){
       return puntosA;
   }
    public int getPuntosB(){
       return puntosB;
   }
    public String getTitulo(){
       return titulo;
   }
    public String getCod(){
       return cod;
   }
                
   
   
    
}
