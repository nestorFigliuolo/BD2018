package interfaz;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JList;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollBar;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.FlowLayout;

public class PanelAdmin extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableConsulta;
	private JList<String> listNombreTablas;
	private JList<String> listAtributosTabla;

	/**
	 * Create the panel.
	 */
	public PanelAdmin() {
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
		gbc_panelBotones.weightx = 100;
		gbc_panelBotones.weighty = 5;
		gbc_panelBotones.gridheight = 1;
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		add(panelBotones, gbc_panelBotones);
		
			JButton botonConsulta = FabBoton.construirBoton("Realizar Consulta");
			botonConsulta.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			panelBotones.add(botonConsulta);
		add(panelBotones, gbc_panelBotones);	
		
		
		tableConsulta = new JTable();
		tableConsulta.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Consulta", TitledBorder.CENTER, TitledBorder.TOP, null, Interfaz.textColor));
		tableConsulta.setBackground(Interfaz.fondo);
		tableConsulta.setForeground(Interfaz.textColor);
		GridBagConstraints gbc_tableConsulta = new GridBagConstraints();
		gbc_tableConsulta.insets = new Insets(0, 0, 5, 0);
		gbc_tableConsulta.fill = GridBagConstraints.BOTH;
		gbc_tableConsulta.gridheight = 2;
		gbc_tableConsulta.gridwidth = 1;
		gbc_tableConsulta.weightx = 60;
		gbc_tableConsulta.weighty = 100;
		gbc_tableConsulta.gridx = 0;
		gbc_tableConsulta.gridy = 1;
		add(tableConsulta, gbc_tableConsulta);
		
		listNombreTablas = new JList<String>();
		listNombreTablas.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Nombre de las Tablas", TitledBorder.LEADING, TitledBorder.TOP, null, Interfaz.textColor));
		listNombreTablas.setBackground(Interfaz.fondo);
		listNombreTablas.setForeground(Interfaz.textColor);
		GridBagConstraints gbc_listNombreTablas = new GridBagConstraints();
		gbc_listNombreTablas.insets = new Insets(0, 0, 5, 0);
		gbc_listNombreTablas.fill = GridBagConstraints.BOTH;
		gbc_listNombreTablas.gridheight = 1;
		gbc_listNombreTablas.gridwidth = 1;
		gbc_listNombreTablas.weightx = 40;
		gbc_listNombreTablas.weighty = 50;
		gbc_listNombreTablas.gridx = 1;
		gbc_listNombreTablas.gridy = 1;
		add(listNombreTablas, gbc_listNombreTablas);
		
		listAtributosTabla = new JList<String>();
		listAtributosTabla.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Nombre de los Atributos", TitledBorder.LEADING, TitledBorder.TOP, null, Interfaz.textColor));
		listAtributosTabla.setBackground(Interfaz.fondo);
		listAtributosTabla.setForeground(Interfaz.textColor);
		GridBagConstraints gbc_listAtributosTabla = new GridBagConstraints();
		gbc_listAtributosTabla.insets = new Insets(0, 0, 5, 0);
		gbc_listAtributosTabla.fill = GridBagConstraints.BOTH;
		gbc_listAtributosTabla.gridheight = 1;
		gbc_listAtributosTabla.gridwidth = 1;
		gbc_listAtributosTabla.weightx = 40;
		gbc_listAtributosTabla.weighty = 50;
		gbc_listAtributosTabla.gridx = 1;
		gbc_listAtributosTabla.gridy = 2;
		add(listAtributosTabla, gbc_listAtributosTabla);
		
	}

}
