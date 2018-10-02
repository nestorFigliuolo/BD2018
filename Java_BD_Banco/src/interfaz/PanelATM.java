package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Banco.ConsultaATM;

public class PanelATM extends JPanel {
	
	private JPanel panelBotones;
	private JTable tableConsulta;
	private JFrame g;
	//obejeto que utiliza ATM para las consultas
	
	private ConsultaATM consul;
	
	/**
	 * Create the panel.
	 */
	public PanelATM(JFrame f) {
		consul = null;
		g = f;
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
		
		
		
		
		
			
			JButton botonConsultaSaldo = FabBoton.construirBoton("Consultar Saldo");
			botonConsultaSaldo.setEnabled(false);
			botonConsultaSaldo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
				    JOptionPane.showMessageDialog(null, "Saldo Actual = "+consul.saldoActual());
				}
			});
			panelBotones.add(botonConsultaSaldo);
			
			JButton botonUltimosMovimientos = FabBoton.construirBoton("UltimosMovimientos");
			botonUltimosMovimientos.setEnabled(false);
			botonUltimosMovimientos.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					 consul.UltimosMovimientos(tableConsulta);
					
				}
			});
			panelBotones.add(botonUltimosMovimientos);
			
			JButton botonMovimientoPeriodo = FabBoton.construirBoton("Movimientos por Periodo");
			botonMovimientoPeriodo.setEnabled(false);
			botonMovimientoPeriodo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Hashtable<String, String> resul = new Hashtable<String,String>();
					int x = panelPeriodoFecha(g, resul);
					if(x==JOptionPane.OK_OPTION) {
						String fechaInitTemp = resul.get("FechaInicio");
						String fechaInit = "";
						
						for (int i = 0; i< fechaInitTemp.length();i++)
						{
							if(fechaInitTemp.charAt(i)!='/')
								fechaInit+=fechaInitTemp.charAt(i);
						}
						String fechaFinTemp = resul.get("FechaFin");
						String fechaFin = "";
						
						for (int i = 0; i< fechaFinTemp.length();i++)
						{
							if(fechaFinTemp.charAt(i)!='/')
								fechaFin+=fechaFinTemp.charAt(i);
						}
					    
						
						
					consul.MovimientoPorPeriodo(tableConsulta, fechaInit,fechaFin);
					}
				}
			});
			panelBotones.add(botonMovimientoPeriodo);
			
			JButton botonLogin = FabBoton.construirBoton("Login");
			botonLogin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
				boolean verificar = false;
				
				
				while (verificar == false) {	
				Hashtable<String, String> resul = new Hashtable<String,String>();
				 int c = login(g,resul);
				
				if (c == JOptionPane.OK_OPTION) {	
					String t = resul.get("nroTar");
					String pin = resul.get("PIN");
					consul = new ConsultaATM(t,pin);
	
					           if(consul.existeTarjeta())
			                         {
						              verificar = true;
					                  botonConsultaSaldo.setEnabled(true);
					                  botonUltimosMovimientos.setEnabled(true);
					                  botonMovimientoPeriodo.setEnabled(true);
					                  JOptionPane.showMessageDialog(g, "Login Exitoso","ATM",JOptionPane.PLAIN_MESSAGE,null);
						             }
					           else 
						       {JOptionPane.showMessageDialog(g, "Numero de tarjeta o PIN incorrecto ingrese nuevamente los datos","Error Login", JOptionPane.ERROR_MESSAGE, null);
						       botonConsultaSaldo.setEnabled(false);
				                  botonUltimosMovimientos.setEnabled(false);
				                  botonMovimientoPeriodo.setEnabled(false);
						       }
				  }
				else 
					verificar = true;
				                             
				                           }
				}
			});
			panelBotones.add(botonLogin);
			
			
			tableConsulta = new JTable();
		
			//tableConsulta.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Consulta", TitledBorder.CENTER, TitledBorder.TOP, null, Interfaz.textColor));
			tableConsulta.setBackground(Interfaz.fondo);
			tableConsulta.setForeground(Interfaz.textColor);
			GridBagConstraints gbc_tableConsulta = new GridBagConstraints();
			gbc_tableConsulta.insets = new Insets(0, 0, 5, 0);
			gbc_tableConsulta.fill = GridBagConstraints.BOTH;
			gbc_tableConsulta.weightx = 100;
			gbc_tableConsulta.weighty = 95;
			gbc_tableConsulta.gridx = 0;
			gbc_tableConsulta.gridy = 1;
			
			add(new JScrollPane(tableConsulta), gbc_tableConsulta);		
			 
			  
			
		
	}
	
	private int panelPeriodoFecha(JFrame frame,Hashtable<String, String> logininformation)
	{
		 JPanel panel = new JPanel(new BorderLayout(5, 5));
		 JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		 label.add(new JLabel("Fecha Inicio", SwingConstants.RIGHT)); 
		 label.add(new JLabel("Fecha Fin", SwingConstants.RIGHT));
		 panel.add(label, BorderLayout.WEST);
		 JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		 JTextField init = new JTextField();
		 init.setText("yyyy/mm/dd");
		 controls.add(init); 
		JTextField fin = new JTextField(); 
		 fin.setText("yyyy/mm/dd");
		controls.add(fin); 
		panel.add(controls, BorderLayout.CENTER);
     int x =  JOptionPane.showConfirmDialog(frame,panel, "Periodo", JOptionPane.OK_CANCEL_OPTION); 
        logininformation.put("FechaInicio",  new String(init.getText()));
	    logininformation.put("FechaFin", new String(fin.getText())); 
		return x;
     
	}
	
	
	
	private int login(JFrame frame,Hashtable<String, String> logininformation) { 
	
		 JPanel panel = new JPanel(new BorderLayout(5, 5));
		 JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		 label.add(new JLabel("Numero Tarjeta", SwingConstants.RIGHT)); 
		 label.add(new JLabel("PIN", SwingConstants.RIGHT));
		 panel.add(label, BorderLayout.WEST);
		 JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		 JPasswordField nroTar = new JPasswordField();
		 controls.add(nroTar); 
		JPasswordField password = new JPasswordField(); 
		controls.add(password); 
		panel.add(controls, BorderLayout.CENTER); 
	   int x =  JOptionPane.showConfirmDialog(frame,panel, "login", JOptionPane.OK_CANCEL_OPTION); 
		
		logininformation.put("nroTar",  new String(nroTar.getPassword()));
	    logininformation.put("PIN", new String(password.getPassword())); 
		return x;
		} 
	

}
