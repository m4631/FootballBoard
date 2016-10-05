/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.View;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Controller.ArchivoController;
import javafootballboard.Model.Equipo;
import javafootballboard.Model.Juego;
import javafootballboard.Model.Jugada;
import javafootballboard.Model.Jugador;
import javafootballboard.Model.WindowCloseListener;
import javafootballboard.Model.onlyDigitsListener;
import javafootballboard.Model.onlyLettersListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class Subir extends javax.swing.JFrame {

    /**
     * Creates new form Subir
     */
    Juego juego;
    
    Equipo equipoA;
    Equipo equipoB;
    
    String fecha;
    String horaInicio;
    String horaFin;
    DateFormat df = DateFormat.getDateInstance();
    
    ArrayList<Jugada> jugadas; // Referencia a template de jugadas
    String[] columnas; // columnas de la tabla partidos
    
    DefaultComboBoxModel modeloA; // Modelo comboBoxes
    DefaultTableModel modeloB; //Modelo para la tabla de partidos...
    DefaultListModel modeloC; //Modelo para la lista de jugadas...
    
    boolean finalizado;

    public Subir() {
        initComponents();
        setTitle("Footballboard - uploadFiles");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //Inicializando variables
        inicializarCamposA();
        inicializarCamposB();
        iniciarTabla();
        asignarListeners();
        finalizado = false;
        jugadas = new ArrayList<>();

        //Cargando comboBoxes iniciales
        cargarComboBoxes();
        jTablePartidos.setAutoCreateRowSorter(true);// Permite sorting en tabla
        
        //Deshabilitando componentes
        deshabilitarTabs();
        jSiguienteA.setEnabled(false);
        jSiguienteB.setEnabled(false);
        jSiguienteC.setEnabled(false);
        jErrorA.setVisible(false);
        jErrorB.setVisible(false);
        jErrorC.setVisible(false);
    }
    
    //================================CODIGO TAB EQUIPO==================================================
    public void inicializarCamposA(){
        equipoA = new Equipo("");
        equipoB = new Equipo("");
        horaInicio="";
        horaFin="";
        fecha="";
    }
    
    // Deshabilita inicialmente los tabs juego, jugada y guardar
    public void deshabilitarTabs(){
        for(int i = 1; i < 4; i++){
            cambiarEstadoTab(i, false);
        }
    }
    
    // Permite alterar el estado de un tab
    public void cambiarEstadoTab(int i, boolean n){
        jTabbedPane1.setEnabledAt(i, n);
    }
    
    public boolean comprobarEquipos(){
        // Comprueba que se han seleccionado ambos equipos
        if( jComboEquipoA.getSelectedIndex() == 0 || jComboEquipoB.getSelectedIndex() == 0 ){
            mostrarErrorA(1);
            return false;
        }
        
        // Comprueba que no sean el mismo equipo
        if(jComboEquipoA.getSelectedIndex() == jComboEquipoB.getSelectedIndex()){
            mostrarErrorA(2);
            return false;
        }
        
        mostrarErrorA(0);
        equipoA = ArchivoController.ac.equipos.get(jComboEquipoA.getSelectedItem().toString());
        equipoB = ArchivoController.ac.equipos.get(jComboEquipoB.getSelectedItem().toString());
        return true;
    }
    
    public void mostrarErrorA(int i){
        switch (i) {
            case 0:
                jErrorA.setVisible(false);
                break;
            case 1:
                jErrorA.setText("Falta un equipo por seleccionar");
                jErrorA.setVisible(true);
                break;
            case 2:
                jErrorA.setText("Error: Los equipos seleccionados son el mismo");
                jErrorA.setVisible(true);
                break;
            default:
                break;
        }
    }
    
    public void activarBotonA(){
        // Si los equipos no son correctos el boton siguiente no se habilita
        if(comprobarEquipos()){
            jSiguienteA.setEnabled(true);
            cargarTabla();
            cambiarEstadoTab(1, true);     
        }else{
            jSiguienteA.setEnabled(false);
            cambiarEstadoTab(1, false);
        }
    }
    
    public void cargarComboBoxes(){
        cargarComboBox(0);
        cargarComboBox(1);
    }
    
    public void cargarComboBox(int i){
        modeloA = new DefaultComboBoxModel();   
        modeloA.addElement("Seleccionar equipo...");
        for( Map.Entry<String,Equipo> e : ArchivoController.ac.equipos.entrySet()){
            modeloA.addElement(e.getKey());
        }
        if(i == 0){
            jComboEquipoA.setModel(modeloA);
        }else{
            jComboEquipoB.setModel(modeloA);
        }
    }
    

    //========================================CODIGO TAB PARTIDO===================================
    
        //Muestra error en pantalla si faltan campos por llenar
        public void mostrarErrorB(int i){
        switch (i) {
            case 0:
                jErrorB.setVisible(false);
                break;
            case 1:
                jErrorB.setText("Error: Faltan campos por rellenar");
                jErrorB.setVisible(true);
                break;
            case 2:
                jErrorB.setText("Error: Solo puede poner números en las puntuaciones");
                jErrorB.setVisible(true);
                break;
            default:
                break;
        }
    }
      
    public void inicializarCamposB(){
                jFecha.setDate(new Date());
                jHora1.setText("");
                jMinuto1.setText("");
                jSegundo1.setText("");
                jHora2.setText("");
                jMinuto2.setText("");
                jSegundo2.setText("");
                jLugar.setText("");
                jEstadio.setText("");
                jArbitro.setText("");
                jPuntajeA.setText("");
                jPuntajeB.setText("");
    }  
    
    //Comprueba que no hay campos por llenar  
    public boolean comprobarCampos(){
        if(jFecha.getDate().toString().equals("") ||
              jHora1.getText().equals("") ||
                jMinuto1.getText().equals("") ||
                jSegundo1.getText().equals("") ||
                jHora2.getText().equals("") ||
                jMinuto2.getText().equals("") ||
                jSegundo2.getText().equals("") ||
                jLugar.getText().equals("") ||
                jEstadio.getText().equals("") ||
                jArbitro.getText().equals("") ||
                jPuntajeA.getText().equals("") ||
                jPuntajeB.getText().equals("") ){
            mostrarErrorB(1);
            return false;
        }
        /*
        //Valida que el puntajeA solo contenga numeros
        try{
            Integer.parseInt(jPuntajeA.getText());
        }catch(Exception e){
            mostrarErrorB(2);  
        }
        //Valida que el puntajeA solo contenga numeros
        try{
            Integer.parseInt(jPuntajeB.getText());
        }catch(Exception e){
            mostrarErrorB(2);  
        }
        */
        mostrarErrorB(0);  
        return true;
    }
    
    //Asigna los valores al objeto Partido...
    public void asignarCampos(){
        juego = new Juego();
        juego.setArbitro(jArbitro.getText());
        juego.setEstadio(jEstadio.getText());
        juego.setCiudad(jLugar.getText());
        juego.setPuntosA(Integer.parseInt(jPuntajeA.getText()));
        juego.setPuntosB(Integer.parseInt(jPuntajeB.getText()));
        juego.setFecha(fecha);
        juego.setEquipoA(equipoA);
        juego.setEquipoB(equipoB);
        juego.setTitulo();
        juego.setHoraFin(horaFin);
        juego.setHoraInicio(horaInicio);
        juego.setCod();
        
    }
    
    public void iniciarTabla(){
        String[][] x = {};
        columnas = new String[]{"Titulo","Score","Fecha"};
        modeloB = new DefaultTableModel(x, columnas);
        jTablePartidos.setModel(modeloB);
    }
    
    public void cargarTabla(){
        int i = 0;
        for(Map.Entry<String,Juego> partido : ArchivoController.ac.juegos.entrySet()){
            modeloB.insertRow(i, new Object[]{});
            modeloB.setValueAt(partido.getValue().getTitulo(), i, 0);
            modeloB.setValueAt(partido.getValue().getPuntosA()+" - "+partido.getValue().getPuntosB(), i, 1);
            modeloB.setValueAt(partido.getValue().getFecha(), i, 2);
            i++;
        }
    }
    //==================================CODIGO TAB JUGADAS=============================
    
    public void iniciarTabJugadas(){
        cargarComboBoxEquipos();
        cargarComboBoxJugadores();
        cargarComboBoxJugadas();
        iniciarCamposC();
        iniciarLista();
    }
    public void iniciarCamposC(){
        jMinuto3.setText("");
        jSegundo3.setText("");
    }
    public void iniciarLista(){
        modeloC = new DefaultListModel();
        modeloC.addElement("Inicio del partido "+fecha+" "+horaInicio);
        jListJugadas.setModel(modeloC);
    }
    public void cargarComboBoxEquipos(){
        modeloA = new DefaultComboBoxModel();   
        modeloA.addElement(equipoA.getNombre());
        modeloA.addElement(equipoB.getNombre());
        jComboEquipo.setModel(modeloA);
    }
    
    public void cargarComboBoxJugadores(){
        modeloA = new DefaultComboBoxModel();   
        int n = jComboEquipo.getSelectedIndex();
        Equipo team;
        
        if(n>0){
            team = equipoA;
        }else{
            team = equipoB;
        }
        
        ArrayList<Jugador> players = team.getJugadores();
        
        for(Jugador j: players ){
            modeloA.addElement(j.getNombre()+" "+j.getApellido()+" ["+j.getPosicion()+"]");
        }
        
       jComboJugador.setModel(modeloA);
        
    }
    
    public void cargarComboBoxJugadas(){
        modeloA = new DefaultComboBoxModel(ArchivoController.ac.diccionarioJugadas.toArray());
        jComboJugadas.setModel(modeloA);
    }
    
    public boolean comprobarCamposC(){
        if(jComboJugador.getSelectedItem().toString().equals("")||
           jComboJugadas.getSelectedItem().toString().equals("")||
                
                jMinuto3.getText().equals("")||
                jSegundo3.getText().equals("")){
            mostrarErrorC(1);
            return false;
        }
        mostrarErrorC(0);
        return true;
    }
    
    public void mostrarErrorC(int i){
        switch (i) {
            case 0:
                jErrorC.setVisible(false);
                break;
            case 1:
                jErrorC.setText("Error: Faltan campos por llenar");
                jErrorC.setVisible(true);
                break;
             case 2:
                jErrorC.setText("Error: Debe incluir jugadas a la lista");
                jErrorC.setVisible(true);
                break;
            default:
                break;
        }
    }
    
    public void agregarJugada(){
        
        String[] aux = jComboJugador.getSelectedItem().toString().split(" ");
        
        modeloC.addElement(jComboEquipo.getSelectedItem().toString()
                +": "+aux[0]+" "+aux[1]
                +" "+jComboJugadas.getSelectedItem().toString()
                +" "+jMinuto3.getText()+":"+jSegundo3.getText());
        jListJugadas.setModel(modeloC);
        
        jugadas.add(new Jugada(juego, jComboJugadas.getSelectedItem().toString(),
                              ArchivoController.ac.jugadores.get(aux[0]+" "+aux[1]), 
                              ArchivoController.ac.equipos.get(jComboEquipo.getSelectedItem().toString()),
                              jMinuto3.getText()+":"+jSegundo3.getText()));
        
        ArchivoController.ac.jugadas.add(new Jugada(juego, 
                jComboJugadas.getSelectedItem().toString(), 
                ArchivoController.ac.jugadores.get(aux[0]+" "+aux[1]), 
                ArchivoController.ac.equipos.get(jComboEquipo.getSelectedItem().toString()),
                jMinuto3.getText()+":"+jSegundo3.getText()));
       
    }
    
    public void eliminarJugada(int i){
        if(i > 0){
            modeloC.remove(i);
            jugadas.remove(i-1);
            ArchivoController.ac.jugadas.remove(i-1);
            jListJugadas.setModel(modeloC);
        }
        
    }
    
    public void agregarFin(){
        modeloC.addElement("Fin del partido "+fecha+" "+horaFin);
        jListJugadas.setModel(modeloC);
    }
    
    public void quitarFin(){
        int i = modeloC.getSize()-1;
        if(i>0){
        modeloC.remove(i);
        jListJugadas.setModel(modeloC);
        }
    }
    
    public boolean cargarArchivoJugadas() {
        JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*csv)", "csv");
        file.setFileFilter(filter);
        int result = file.showSaveDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
           String ruta = file.getSelectedFile().getAbsolutePath();
           System.out.println(ruta);
           
           jugadas = new ArrayList<>();
           
           if(ArchivoController.ac.cargarJugadas(ruta, juego, equipoA, equipoB, jugadas)){
               modeloC.clear();
               for(Jugada j: jugadas){
                   modeloC.addElement(j.getEquipo().getNombre()+": "+j.getJugador().getNombre()
                           +" "+j.getJugador().getApellido()
                           +" ["+j.getJugador().getPosicion()
                           +"] "+j.getNombre()
                           +" "+j.getHora());
                  ArchivoController.ac.jugadas.add(j);
               }
               jListJugadas.setModel(modeloC);
               return true;
           }else{
               JOptionPane.showMessageDialog(this, "Error: El archivo no se encuentra en el formato adecuado");
           }
    
        }else{
            JOptionPane.showMessageDialog(this, "Error: El archivo no es valido");
        }
        
        return false;
    }
    
    //=================================CODIGO TAB GUARDAR============================
    public void mostrarDatosD(){
        jLTitulo.setText(juego.getTitulo());
        jLScore.setText("[ "+juego.getPuntosA()+" - "+juego.getPuntosB()+" ]");
        
        if(juego.getPuntosA()>=juego.getPuntosB()){
               jLEquipoA.setText(juego.getEquipoA().getNombre());
               jLEquipoB.setText(juego.getEquipoB().getNombre());
        }else{
               jLEquipoB.setText(juego.getEquipoA().getNombre());
               jLEquipoA.setText(juego.getEquipoB().getNombre());
        }
        
        if(juego.getPuntosA()==juego.getPuntosB()){
            j1.setText("Empate: ");
            j2.setText("Empate: ");
        }
     
        jLLugar.setText(juego.getCiudad());
        jLFecha.setText(juego.getFecha());
        jLHoraInicio.setText(juego.getHoraInicio());
        jLHoraFin.setText(juego.getHoraFin());
        jLEstadio.setText(juego.getEstadio());
    }

    public boolean terminarProceso() throws IOException{
        //....................................
        juego.setJugadas(jugadas);
        ArchivoController.ac.juegos.put(juego.getCod(), juego);
        
        ArchivoController.ac.actJuegos();
        ArchivoController.ac.actJugadas();
        
        if(true){
            JOptionPane.showMessageDialog(this, "Partido guardado correctamente");
            return true;
        }else{
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error al momento de guardar");
            return false;
        }
    }
    
    public void asignarListeners(){
        //Campos solo digitos
        jHora1.addKeyListener(new onlyDigitsListener(jHora1,2));
        jMinuto1.addKeyListener(new onlyDigitsListener(jMinuto1,2));
        jSegundo1.addKeyListener(new onlyDigitsListener(jSegundo1,2));
        jHora2.addKeyListener(new onlyDigitsListener(jHora2,2));
        jMinuto2.addKeyListener(new onlyDigitsListener(jMinuto2,2));
        jSegundo2.addKeyListener(new onlyDigitsListener(jSegundo2,2));
        jMinuto3.addKeyListener(new onlyDigitsListener(jMinuto3,2));
        jSegundo3.addKeyListener(new onlyDigitsListener(jSegundo3,2));
        
        jPuntajeA.addKeyListener(new onlyDigitsListener(jPuntajeA, 3));
        jPuntajeB.addKeyListener(new onlyDigitsListener(jPuntajeB, 3));
        
        //campos solo letras
        jLugar.addKeyListener(new onlyLettersListener(jLugar, 40));
        jEstadio.addKeyListener(new onlyLettersListener(jEstadio, 40));
        jArbitro.addKeyListener(new onlyLettersListener(jArbitro, 40));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboEquipoA = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboEquipoB = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jErrorA = new javax.swing.JLabel();
        jSiguienteA = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jProgressBar2 = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePartidos = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLugar = new javax.swing.JTextField();
        jEstadio = new javax.swing.JTextField();
        jArbitro = new javax.swing.JTextField();
        jPuntajeA = new javax.swing.JTextField();
        jPuntajeB = new javax.swing.JTextField();
        jHora1 = new javax.swing.JTextField();
        jMinuto1 = new javax.swing.JTextField();
        jSegundo1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jHora2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jMinuto2 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jSegundo2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jFecha = new com.toedter.calendar.JDateChooser();
        jErrorB = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jSiguienteB = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListJugadas = new javax.swing.JList<>();
        jLabel22 = new javax.swing.JLabel();
        jLimpiarJugadas = new javax.swing.JButton();
        jEliminarJugada = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jAgregarJugada = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jComboEquipo = new javax.swing.JComboBox<>();
        jComboJugador = new javax.swing.JComboBox<>();
        jComboJugadas = new javax.swing.JComboBox<>();
        jMinuto3 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jSegundo3 = new javax.swing.JTextField();
        jErrorC = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jFinPartido = new javax.swing.JButton();
        jCargarJugadas = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSiguienteC = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jProgressBar3 = new javax.swing.JProgressBar();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLTitulo = new javax.swing.JLabel();
        jLScore = new javax.swing.JLabel();
        j1 = new javax.swing.JLabel();
        jLEquipoA = new javax.swing.JLabel();
        j2 = new javax.swing.JLabel();
        jLEquipoB = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLFecha = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLHoraInicio = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLHoraFin = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLLugar = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLEstadio = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jProgressBar4 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("1. Selecciones los dos equipos que participaron en el partido");

        jComboEquipoA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar equipo...", "EquipoA", "EquipoB", "EquipoC", "EquipoD" }));
        jComboEquipoA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEquipoAActionPerformed(evt);
            }
        });

        jLabel1.setText("Equipo A:");

        jLabel2.setText("Equipo B:");

        jComboEquipoB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar equipo...", "EquipoA", "EquipoB", "EquipoC", "EquipoD" }));
        jComboEquipoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEquipoBActionPerformed(evt);
            }
        });

        jButton1.setText("Añadir equipo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Ayuda: Añadir equipo le permite añadir un nuevo equipo a la base de datos por medio");

        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("de un formulario de registros o subiendo un archivo .txt con los datos del equipo.");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jErrorA.setForeground(new java.awt.Color(255, 0, 0));
        jErrorA.setText("jLabel47");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboEquipoA, 0, 257, Short.MAX_VALUE)
                                    .addComponent(jComboEquipoB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addComponent(jErrorA, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel3)))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jErrorA)
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboEquipoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboEquipoB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jSiguienteA.setText("Siguiente");
        jSiguienteA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSiguienteAActionPerformed(evt);
            }
        });

        jButton8.setText("Cancelar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jProgressBar2.setValue(5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(79, 79, 79)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSiguienteA))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSiguienteA)
                        .addComponent(jButton8))
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Equipos", jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(754, 365));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("2. Verifique que no ha registrado el partido deseado");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("3. Ingrese los siguientes datos sobre el partido");

        jTablePartidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTablePartidos);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Fecha:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Hora inicio:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Hora fin:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Lugar:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Estadio:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Arbitro:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("puntaje equipo A:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("puntaje equipo B:");

        jHora1.setText("00");
        jHora1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHora1ActionPerformed(evt);
            }
        });

        jMinuto1.setText("00");

        jSegundo1.setText("00");

        jLabel16.setText(":");

        jLabel17.setText(":");

        jLabel18.setForeground(new java.awt.Color(153, 153, 153));
        jLabel18.setText("(h/m/s)");

        jHora2.setText("00");
        jHora2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHora2ActionPerformed(evt);
            }
        });

        jLabel19.setText(":");

        jMinuto2.setText("00");

        jLabel20.setText(":");

        jSegundo2.setText("00");

        jLabel21.setForeground(new java.awt.Color(153, 153, 153));
        jLabel21.setText("(h/m/s)");

        jErrorB.setForeground(new java.awt.Color(255, 0, 0));
        jErrorB.setText("jLabel48");

        jButton3.setText("Guardar datos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton7.setText("Limpiar campos");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPuntajeB, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                    .addComponent(jPuntajeA)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jMinuto2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSegundo2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel21))
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addComponent(jHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jMinuto1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(1, 1, 1)
                                            .addComponent(jLabel16)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jSegundo1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel18)))))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(18, 18, 18)
                                    .addComponent(jArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(18, 18, 18)
                                    .addComponent(jEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jErrorB))
                        .addGap(0, 35, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMinuto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSegundo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(2, 2, 2)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jHora2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jMinuto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSegundo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(jLabel19)
                        .addComponent(jLabel21))
                    .addComponent(jLabel10))
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jPuntajeA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jPuntajeB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jErrorB)
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton7))
                        .addContainerGap())))
        );

        jSiguienteB.setText("Siguiente");
        jSiguienteB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSiguienteBActionPerformed(evt);
            }
        });

        jButton4.setText("Volver");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jProgressBar1.setValue(25);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSiguienteB)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSiguienteB)
                        .addComponent(jButton4)
                        .addComponent(jButton5)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Partido", jPanel2);

        jListJugadas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListJugadas);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Lista de jugadas");

        jLimpiarJugadas.setText("Limpiar");
        jLimpiarJugadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLimpiarJugadasActionPerformed(evt);
            }
        });

        jEliminarJugada.setText("Eliminar");
        jEliminarJugada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEliminarJugadaActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("4. Registre las jugadas ocurridas en el partido");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jAgregarJugada.setText("Agregar jugada");
        jAgregarJugada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAgregarJugadaActionPerformed(evt);
            }
        });

        jLabel24.setText("Seleccione el equipo:");

        jLabel25.setText("Seleccione el jugador:");

        jLabel26.setText("Seleccione la jugada:");

        jLabel27.setText("Tiempo de la jugada:");

        jComboEquipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEquipoActionPerformed(evt);
            }
        });

        jComboJugador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboJugadas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboJugadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboJugadasActionPerformed(evt);
            }
        });

        jLabel29.setText(":");

        jErrorC.setForeground(new java.awt.Color(255, 0, 0));
        jErrorC.setText("jLabel47");

        jLabel47.setForeground(new java.awt.Color(153, 153, 153));
        jLabel47.setText("(mm/ss)");

        jButton12.setText("Borrar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jFinPartido.setText("Fin del partido");
        jFinPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFinPartidoActionPerformed(evt);
            }
        });

        jCargarJugadas.setText("Cargar jugadas desde archivo");
        jCargarJugadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCargarJugadasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboJugador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboJugadas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(jErrorC))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel27)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jMinuto3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel29)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSegundo3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel47)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jCargarJugadas)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFinPartido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jAgregarJugada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jComboEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25))
                    .addComponent(jComboJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jComboJugadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jMinuto3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jSegundo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(jButton12))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jAgregarJugada)
                    .addComponent(jErrorC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFinPartido)
                    .addComponent(jCargarJugadas))
                .addContainerGap())
        );

        jSiguienteC.setText("Siguiente");
        jSiguienteC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSiguienteCActionPerformed(evt);
            }
        });

        jButton13.setText("Volver");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Cancelar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jProgressBar3.setValue(60);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLimpiarJugadas)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jEliminarJugada))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jSiguienteC)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLimpiarJugadas)
                                    .addComponent(jEliminarJugada)))
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSiguienteC)
                            .addComponent(jButton13)
                            .addComponent(jButton14))))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Jugadas", jPanel3);

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("5. Confirme los datos del partido para completar el proceso de subida");

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLTitulo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLTitulo.setText("[EquipoA vs EquipoB]");

        jLScore.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLScore.setText("[Score]");

        j1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        j1.setText("Ganador:");

        jLEquipoA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLEquipoA.setText("[Equipo A]");

        j2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        j2.setText("Perdedor:");

        jLEquipoB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLEquipoB.setText("[Equipo B]");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Fecha:");

        jLFecha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLFecha.setText("dd/mm/yyyy");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Hora de inicio:");

        jLHoraInicio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLHoraInicio.setText("hh/mm/ss");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Hora de fin:");

        jLHoraFin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLHoraFin.setText("hh/mm/ss");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("Lugar:");

        jLLugar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLLugar.setText("[LUGAR]");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Estadio:");

        jLEstadio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLEstadio.setText("[Estadio]");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel44)
                                    .addComponent(j2)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel43)
                                    .addComponent(j1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLHoraFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLHoraInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLEquipoB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLEquipoA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel46)
                                .addComponent(jLabel45))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLEstadio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLScore)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jLTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLScore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLEquipoA)
                    .addComponent(j1))
                .addGap(14, 14, 14)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLEquipoB)
                    .addComponent(j2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLFecha)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLHoraInicio)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLHoraFin)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLLugar)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLEstadio)
                    .addComponent(jLabel46))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(134, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(124, 124, 124))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jButton15.setText("Terminar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Volver");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Cancelar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jProgressBar4.setValue(95);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton15)
                        .addComponent(jButton16)
                        .addComponent(jButton17))
                    .addComponent(jProgressBar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Guardar", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboEquipoAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboEquipoAActionPerformed
        activarBotonA();
    }//GEN-LAST:event_jComboEquipoAActionPerformed

    private void jComboEquipoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboEquipoBActionPerformed
        activarBotonA();
    }//GEN-LAST:event_jComboEquipoBActionPerformed

    private void jHora1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHora1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jHora1ActionPerformed

    private void jHora2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHora2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jHora2ActionPerformed

    private void jSiguienteAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSiguienteAActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jSiguienteAActionPerformed

    private void jSiguienteBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSiguienteBActionPerformed
         jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jSiguienteBActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Menu menu = new Menu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Menu menu = new Menu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Menu menu = new Menu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
         jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jSiguienteCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSiguienteCActionPerformed
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jSiguienteCActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        Menu menu = new Menu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        try {
            if(terminarProceso()){
                this.dispose();
            }
        } catch (IOException ex) {
            Logger.getLogger(Subir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(comprobarCampos()){      
            horaInicio = jHora1.getText()+":"+jMinuto1.getText()+":"+jSegundo1.getText();
            horaFin = jHora2.getText()+":"+jMinuto2.getText()+":"+jSegundo2.getText();
            fecha = df.format(jFecha.getDate());
            
            iniciarTabJugadas();
            asignarCampos();
            
            cambiarEstadoTab(2, true);
            jSiguienteB.setEnabled(true);
        }else{
            cambiarEstadoTab(2, false);
            jSiguienteB.setEnabled(false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        inicializarCamposB();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboJugadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboJugadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboJugadasActionPerformed

    private void jAgregarJugadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAgregarJugadaActionPerformed
        if(comprobarCamposC()){
            agregarJugada();
        }
    }//GEN-LAST:event_jAgregarJugadaActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        iniciarCamposC();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jLimpiarJugadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLimpiarJugadasActionPerformed
       iniciarLista();
    }//GEN-LAST:event_jLimpiarJugadasActionPerformed

    private void jEliminarJugadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEliminarJugadaActionPerformed
       eliminarJugada(jListJugadas.getSelectedIndex());
    }//GEN-LAST:event_jEliminarJugadaActionPerformed

    private void jFinPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFinPartidoActionPerformed
     if(!finalizado){
        if(jListJugadas.getModel().getSize()>1){
             agregarFin();
             jAgregarJugada.setEnabled(false);
             jLimpiarJugadas.setEnabled(false);
             jEliminarJugada.setEnabled(false);
             jCargarJugadas.setEnabled(false);
             cambiarEstadoTab(3, true);
             jSiguienteC.setEnabled(true);
             finalizado = true;
             jFinPartido.setText("Cancelar Finalizacion");
             
             mostrarDatosD();
        }else{
            mostrarErrorC(2);
        }
     }else{
         jAgregarJugada.setEnabled(true);
         jLimpiarJugadas.setEnabled(true);
         jEliminarJugada.setEnabled(true);
         jSiguienteC.setEnabled(false);
         jCargarJugadas.setEnabled(true);
         cambiarEstadoTab(3, false);
         quitarFin();
         finalizado = false;
         jFinPartido.setText("Fin del partido");
     }
    }//GEN-LAST:event_jFinPartidoActionPerformed

    private void jComboEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboEquipoActionPerformed
        cargarComboBoxJugadores();
    }//GEN-LAST:event_jComboEquipoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Equipos frameEquipos = new Equipos(this);
        frameEquipos.setVisible(true);
        frameEquipos.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frameEquipos.addWindowListener(new WindowCloseListener(frameEquipos));
        this.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCargarJugadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCargarJugadasActionPerformed
        if(cargarArchivoJugadas()){
            jFinPartido.setEnabled(false);
            cambiarEstadoTab(3, true);
            jSiguienteC.setEnabled(true);
            jAgregarJugada.setEnabled(false);
            jEliminarJugada.setEnabled(false);
            jLimpiarJugadas.setEnabled(false);
        }else{
            iniciarLista();
            jAgregarJugada.setEnabled(true);
            jEliminarJugada.setEnabled(true);
            jLimpiarJugadas.setEnabled(true);
            jFinPartido.setEnabled(true);
            cambiarEstadoTab(3, false);
            jSiguienteC.setEnabled(false);
        }
    }//GEN-LAST:event_jCargarJugadasActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }  catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Subir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Subir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Subir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Subir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Subir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel j1;
    private javax.swing.JLabel j2;
    private javax.swing.JButton jAgregarJugada;
    private javax.swing.JTextField jArbitro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jCargarJugadas;
    private javax.swing.JComboBox<String> jComboEquipo;
    private javax.swing.JComboBox<String> jComboEquipoA;
    private javax.swing.JComboBox<String> jComboEquipoB;
    private javax.swing.JComboBox<String> jComboJugadas;
    private javax.swing.JComboBox<String> jComboJugador;
    private javax.swing.JButton jEliminarJugada;
    private javax.swing.JLabel jErrorA;
    private javax.swing.JLabel jErrorB;
    private javax.swing.JLabel jErrorC;
    private javax.swing.JTextField jEstadio;
    private com.toedter.calendar.JDateChooser jFecha;
    private javax.swing.JButton jFinPartido;
    private javax.swing.JTextField jHora1;
    private javax.swing.JTextField jHora2;
    private javax.swing.JLabel jLEquipoA;
    private javax.swing.JLabel jLEquipoB;
    private javax.swing.JLabel jLEstadio;
    private javax.swing.JLabel jLFecha;
    private javax.swing.JLabel jLHoraFin;
    private javax.swing.JLabel jLHoraInicio;
    private javax.swing.JLabel jLLugar;
    private javax.swing.JLabel jLScore;
    private javax.swing.JLabel jLTitulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jLimpiarJugadas;
    private javax.swing.JList<String> jListJugadas;
    private javax.swing.JTextField jLugar;
    private javax.swing.JTextField jMinuto1;
    private javax.swing.JTextField jMinuto2;
    private javax.swing.JTextField jMinuto3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JTextField jPuntajeA;
    private javax.swing.JTextField jPuntajeB;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jSegundo1;
    private javax.swing.JTextField jSegundo2;
    private javax.swing.JTextField jSegundo3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jSiguienteA;
    private javax.swing.JButton jSiguienteB;
    private javax.swing.JButton jSiguienteC;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTablePartidos;
    // End of variables declaration//GEN-END:variables
}
