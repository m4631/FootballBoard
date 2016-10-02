package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Equipos extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Equipos frame = new Equipos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Equipos() {
		setTitle("Registro De Equipos");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Brandon m\\Desktop\\Java\\FinalTecnicas\\src\\noun_2034_cc.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDelEquipo = new JLabel("Nombre Del Equipo:");
		lblNombreDelEquipo.setBounds(40, 42, 142, 32);
		contentPane.add(lblNombreDelEquipo);
		
		nombre = new JTextField();
		nombre.setBounds(40, 74, 222, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(23, 159, 100, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(216, 159, 89, 14);
		contentPane.add(lblApellido);
		
		JLabel lblPosicin = new JLabel("Posici\u00F3n");
		lblPosicin.setBounds(400, 159, 89, 14);
		contentPane.add(lblPosicin);
	}
}
