package interfaz;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class PanelATM extends JPanel {
	
	private JPanel panelBotones;
	private JTable tableConsulta;
	
	/**
	 * Create the panel.
	 */
	public PanelATM() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		setBackground(Interfaz.primaryLight);
		
		panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		panelBotones.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Opciones", TitledBorder.CENTER, TitledBorder.TOP, null, Interfaz.textColor));
		panelBotones.setBackground(Interfaz.primaryLight);
		panelBotones.setForeground(Interfaz.textColor);
		
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 0;
		gbc_panelBotones.weightx = 100;
		gbc_panelBotones.weighty = 5;
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		add(panelBotones, gbc_panelBotones);
		
			JButton botonLogin = FabBoton.construirBoton("Login");
			botonLogin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			panelBotones.add(botonLogin);
			
			JButton botonConsultaSaldo = FabBoton.construirBoton("Consultar Saldo");
			botonConsultaSaldo.setEnabled(false);
			botonConsultaSaldo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			panelBotones.add(botonConsultaSaldo);
			
			JButton botonUltimosMovimientos = FabBoton.construirBoton("UltimosMovimientos");
			botonUltimosMovimientos.setEnabled(false);
			botonUltimosMovimientos.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			panelBotones.add(botonUltimosMovimientos);
			
			JButton botonMovimientoPeriodo = FabBoton.construirBoton("Movimientos por Periodo");
			botonMovimientoPeriodo.setEnabled(false);
			botonMovimientoPeriodo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			panelBotones.add(botonMovimientoPeriodo);
			
		
		
		tableConsulta = new JTable();
		tableConsulta.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Consulta", TitledBorder.CENTER, TitledBorder.TOP, null, Interfaz.textColor));
		tableConsulta.setBackground(Interfaz.fondo);
		tableConsulta.setForeground(Interfaz.textColor);
		GridBagConstraints gbc_tableConsulta = new GridBagConstraints();
		gbc_tableConsulta.insets = new Insets(0, 0, 5, 0);
		gbc_tableConsulta.fill = GridBagConstraints.BOTH;
		gbc_tableConsulta.weightx = 100;
		gbc_tableConsulta.weighty = 95;
		gbc_tableConsulta.gridx = 0;
		gbc_tableConsulta.gridy = 1;
		add(tableConsulta, gbc_tableConsulta);
		
	}

}