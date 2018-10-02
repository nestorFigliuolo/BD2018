package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

public class Interfaz {

	private JFrame frame;
	
	private PanelAdmin panelAdmin;
	private PanelATM panelATM;
	private PanelAdminPrestamos panelAdminPrestamos;
	
	public static final Color primary = new Color(0x1e88e5);
	public static final Color primaryLight = new Color(0x6ab7ff);
	public static final Color primaryDark = new Color(0x005cb2);
	public static final Color secondary = new Color(0x4dd0e1);
	public static final Color secondaryLight = new Color(0x88ffff);
	public static final Color secondaryDark = new Color(0x009faf);
	public static final Color textColor = new Color(0x000000);
	public static final Color fondo = new Color(0xFFFFFF);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		panelAdmin = new PanelAdmin();
		panelATM = new PanelATM ();
		panelAdminPrestamos = new PanelAdminPrestamos();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelContenido = new JPanel();
		frame.getContentPane().add(panelContenido, BorderLayout.CENTER);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Menu", TitledBorder.LEADING, TitledBorder.TOP, null, textColor));
		panelMenu.setBackground(primaryDark);
		panelMenu.setLayout(new GridBagLayout());
		
		JButton btnInterfazAdmin = FabBoton.construirBoton("Administrador");
		btnInterfazAdmin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(panelATM);
				frame.remove(panelAdminPrestamos);
				frame.add(panelAdmin);
				frame.validate();
				frame.repaint();
			}
		});
		GridBagConstraints gbc_btnInterfazAdmin = new GridBagConstraints();
		gbc_btnInterfazAdmin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInterfazAdmin.gridx = 0;
		gbc_btnInterfazAdmin.gridy = 0;
		gbc_btnInterfazAdmin.insets = new Insets(0, 0, 10, 0);
		panelMenu.add(btnInterfazAdmin, gbc_btnInterfazAdmin);
		
		JButton btnAtm = FabBoton.construirBoton("ATM");
		btnAtm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(panelAdmin);
				frame.remove(panelAdminPrestamos);
				frame.add(panelATM);
				frame.validate();
				frame.repaint();
			}
		});
		GridBagConstraints gbc_btnAtm = new GridBagConstraints();
		gbc_btnAtm.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAtm.gridx = 0;
		gbc_btnAtm.gridy = 1;
		gbc_btnAtm.insets = new Insets(0, 0, 10, 0);
		panelMenu.add(btnAtm, gbc_btnAtm);

		JButton btnAdminPrestamos = FabBoton.construirBoton("Admin Prestamos");
		btnAdminPrestamos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(panelATM);
				frame.remove(panelAdmin);
				frame.add(panelAdminPrestamos);
				frame.validate();
				frame.repaint();
			}
		});
		GridBagConstraints gbc_btnAdminPrestamos = new GridBagConstraints();
		gbc_btnAdminPrestamos.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdminPrestamos.gridx = 0;
		gbc_btnAdminPrestamos.gridy = 2;
		gbc_btnAdminPrestamos.insets = new Insets(0, 0, 10, 0);
		panelMenu.add(btnAdminPrestamos, gbc_btnAdminPrestamos);
		
		//Paneles centrales
		
		frame.getContentPane().add(panelMenu, BorderLayout.WEST);
		frame.add(panelAdmin);
		

	}

}
