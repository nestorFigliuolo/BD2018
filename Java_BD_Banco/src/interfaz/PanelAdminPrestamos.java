package interfaz;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class PanelAdminPrestamos extends JPanel {
	
	private JTable tableConsulta;
	private JTable tableMorosos;

	/**
	 * Create the panel.
	 */
	public PanelAdminPrestamos() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		setBackground(Interfaz.primaryLight);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		panelBotones.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Opciones", TitledBorder.CENTER, TitledBorder.TOP, null, Interfaz.textColor));
		panelBotones.setBackground(Interfaz.primaryLight);
		panelBotones.setForeground(Interfaz.textColor);
		
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 0;
		gbc_panelBotones.gridwidth = 2;
		gbc_panelBotones.weightx = 100;
		gbc_panelBotones.weighty = 5;
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		add(panelBotones, gbc_panelBotones);
		
			JButton botonPrestamo = FabBoton.construirBoton("Crear prestamo");
			botonPrestamo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			panelBotones.add(botonPrestamo);
			
			JButton botonConsultarCliente = FabBoton.construirBoton("Consultar Cliente");
			botonConsultarCliente.setEnabled(false);
			botonConsultarCliente.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			panelBotones.add(botonConsultarCliente);
			
			JButton botonPagarCuotas = FabBoton.construirBoton("Pagar Cuotas seleccionadas");
			botonPagarCuotas.setEnabled(false);
			botonPagarCuotas.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			panelBotones.add(botonPagarCuotas);
			
		
		
		tableConsulta = new JTable();
		tableConsulta.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Consulta", TitledBorder.CENTER, TitledBorder.TOP, null, Interfaz.textColor));
		tableConsulta.setBackground(Interfaz.fondo);
		tableConsulta.setForeground(Interfaz.textColor);
		GridBagConstraints gbc_tableConsulta = new GridBagConstraints();
		gbc_tableConsulta.insets = new Insets(0, 0, 5, 0);
		gbc_tableConsulta.fill = GridBagConstraints.BOTH;
		gbc_tableConsulta.weightx = 50;
		gbc_tableConsulta.weighty = 95;
		gbc_tableConsulta.gridx = 0;
		gbc_tableConsulta.gridy = 1;
		add(tableConsulta, gbc_tableConsulta);
		
		tableMorosos = new JTable();
		tableMorosos.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Morosos", TitledBorder.CENTER, TitledBorder.TOP, null, Interfaz.textColor));
		tableMorosos.setBackground(Interfaz.fondo);
		tableMorosos.setForeground(Interfaz.textColor);
		GridBagConstraints gbc_tableMorosos = new GridBagConstraints();
		gbc_tableMorosos.insets = new Insets(0, 0, 5, 0);
		gbc_tableMorosos.fill = GridBagConstraints.BOTH;
		gbc_tableMorosos.weightx = 50;
		gbc_tableMorosos.weighty = 95;
		gbc_tableMorosos.gridx = 1;
		gbc_tableMorosos.gridy = 1;
		add(tableMorosos, gbc_tableMorosos);
		
	}

}
