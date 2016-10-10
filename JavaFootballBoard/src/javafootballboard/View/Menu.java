/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafootballboard.View;

import javafootballboard.Controller.AbrirVistas;


/**
 *
 * @author Mabel
 */
public class Menu extends javax.swing.JFrame {
    
    public Menu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        botonJuegosPasados = new javax.swing.JButton();
        botonEquipos = new javax.swing.JButton();
        botoIniciar = new javax.swing.JButton();
        botonSubirArchivos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setBounds(new java.awt.Rectangle(0, 0, 840, 500));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(840, 490));
        setMinimumSize(new java.awt.Dimension(840, 490));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javafootballboard/Assets/foot_042.gif"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 130, 180, 170));

        botonJuegosPasados.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botonJuegosPasados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javafootballboard/Assets/soccer_13.837837837838px_553151_easyicon.net.png"))); // NOI18N
        botonJuegosPasados.setText("Juegos Pasados");
        botonJuegosPasados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonJuegosPasadosActionPerformed(evt);
            }
        });
        getContentPane().add(botonJuegosPasados, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 160, 40));

        botonEquipos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botonEquipos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javafootballboard/Assets/sport_soccer_16px_578895_easyicon.net.png"))); // NOI18N
        botonEquipos.setText("Equipos");
        botonEquipos.setBorder(null);
        botonEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEquiposActionPerformed(evt);
            }
        });
        getContentPane().add(botonEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 160, 40));

        botoIniciar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botoIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javafootballboard/Assets/soccer_13.837837837838px_553409_easyicon.net.png"))); // NOI18N
        botoIniciar.setText("Iniciar");
        botoIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoIniciarActionPerformed(evt);
            }
        });
        getContentPane().add(botoIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 160, 40));

        botonSubirArchivos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botonSubirArchivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javafootballboard/Assets/48_brazil_brazilian_soccer_16px_2687_easyicon.net.png"))); // NOI18N
        botonSubirArchivos.setText("Subir Archivos");
        botonSubirArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSubirArchivosActionPerformed(evt);
            }
        });
        getContentPane().add(botonSubirArchivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 160, 40));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javafootballboard/Assets/noun_2034_cc.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 480));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javafootballboard/Assets/woman_bounce_soccer_ball_knees_hg_clr.gif"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 170, 320));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javafootballboard/Assets/WhatsApp Image 2016-10-01 at 23.51.22.jpeg"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(750, 500));
        jLabel2.setMinimumSize(new java.awt.Dimension(750, 500));
        jLabel2.setPreferredSize(new java.awt.Dimension(750, 500));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, -1));
        jLabel2.getAccessibleContext().setAccessibleParent(jLabel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEquiposActionPerformed
        AbrirVistas.equipos(this);
    }//GEN-LAST:event_botonEquiposActionPerformed

    private void botoIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoIniciarActionPerformed
        AbrirVistas.iniciar(this);
    }//GEN-LAST:event_botoIniciarActionPerformed

    private void botonSubirArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSubirArchivosActionPerformed
        AbrirVistas.subir(this);
    }//GEN-LAST:event_botonSubirArchivosActionPerformed

    private void botonJuegosPasadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonJuegosPasadosActionPerformed
        AbrirVistas.historico(this);
    }//GEN-LAST:event_botonJuegosPasadosActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botoIniciar;
    private javax.swing.JButton botonEquipos;
    private javax.swing.JButton botonJuegosPasados;
    private javax.swing.JButton botonSubirArchivos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}