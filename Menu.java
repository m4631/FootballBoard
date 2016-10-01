import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Menu() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Brandon m\\Desktop\\Java\\FinalTecnicas\\src\\noun_2034_cc.png"));
		setTitle("Men\u00FA Principal");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BufferedImage imagen = ImageIO.read(new File("C:\\Users\\Brandon m\\Desktop\\Java\\FinalTecnicas\\src\\noun_2034_cc.png"));
		
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(imagen));
		lblNewLabel.setBounds(39, 11, 451, 102);
		contentPane.add(lblNewLabel);
		
		JButton btnEquipos = new JButton("Equipos");
		btnEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipos equipo = new Equipos();
				equipo.setVisible(true);
			}
		});
		btnEquipos.setBounds(39, 124, 89, 23);
		contentPane.add(btnEquipos);
		
		JButton Iniciar = new JButton("Iniciar");
		Iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Iniciar inicio = new Iniciar();
				inicio.setVisible(true);
			}
		});
		Iniciar.setBounds(39, 173, 89, 23);
		contentPane.add(Iniciar);
		
		JButton btnHistorico = new JButton("Historico");
		btnHistorico.setBounds(39, 222, 89, 23);
		contentPane.add(btnHistorico);
	}
}
