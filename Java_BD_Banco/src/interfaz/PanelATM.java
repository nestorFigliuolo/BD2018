package interfaz;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import javax.swing.text.MaskFormatter;

import Banco.ConsultaATM;
import Banco.Fechas;
import jdk.nashorn.internal.scripts.JO;

public class PanelATM extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel panelBotones;
	private JTable tableConsulta;
	//obejeto que utiliza ATM para las consultas
	
	private ConsultaATM consul;
	
	/**
	 * Create the panel.
	 */
	public PanelATM() {
		consul = null;
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
		
		
		
		
			List<JButton> grupoBotones = new ArrayList();
			
			JButton botonConsultaSaldo = FabBoton.construirBoton("Consultar Saldo");
			grupoBotones.add(botonConsultaSaldo);
			botonConsultaSaldo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
				    JOptionPane.showMessageDialog(null, "Saldo Actual = "+consul.saldoActual());
				}
			});
			panelBotones.add(botonConsultaSaldo);
			
			JButton botonUltimosMovimientos = FabBoton.construirBoton("UltimosMovimientos");
			grupoBotones.add(botonUltimosMovimientos);
			botonUltimosMovimientos.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					 consul.UltimosMovimientos(tableConsulta);
					
				}
			});
			panelBotones.add(botonUltimosMovimientos);
			
			JButton botonMovimientoPeriodo = FabBoton.construirBoton("Movimientos por Periodo");
			grupoBotones.add(botonMovimientoPeriodo);
			botonMovimientoPeriodo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
					JFormattedTextField textFieldFechaInicio = new JFormattedTextField(new MaskFormatter("##'/##'/####"));
					JFormattedTextField textFieldFechaFin = new JFormattedTextField(new MaskFormatter("##'/##'/####"));;
					
					
					Object[] message = {
							"El formato de la fecha debe ser dd/mm/yyyy",
							"Fecha inicial:", textFieldFechaInicio,
							"Fecha final:", textFieldFechaFin
					};
					
					int option = JOptionPane.showConfirmDialog(null, message, "Ingrese periodo", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
					    
					    
					     java.sql.Date fechaInt = Fechas.convertirStringADateSQL(textFieldFechaInicio.getText().trim());
					     java.sql.Date fechaFin = Fechas.convertirStringADateSQL(textFieldFechaFin.getText().trim());
						
					     boolean fechaIn = Fechas.validar(Fechas.convertirDateAString(fechaInt));
					     boolean fechaFi = Fechas.validar(Fechas.convertirDateAString(fechaFin));
					     
					     if(fechaIn && fechaFi) {
					    	      if(fechaInt.compareTo(fechaFin)<=0)
					    	          consul.MovimientoPorPeriodo(tableConsulta, fechaInt,fechaFin);
					    	      else {
					    	    	  JOptionPane.showMessageDialog(null, "la fecha inicial es posterior a la fecha final", "Error", JOptionPane.ERROR_MESSAGE);   
					    	      }
					     }
					   	 else {
					   
					   		 JOptionPane.showMessageDialog(null, "Fecha mal ingresada,el formato de fecha debe ser dia/mes/anio", "Error", JOptionPane.ERROR_MESSAGE); 
					     }
					     
						
					} 
					
					
				}catch(Exception ex) {	
					JOptionPane.showMessageDialog(null, "Fecha mal ingresada,el formato de fecha debe ser dia/mes/anio", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
				
			});
			panelBotones.add(botonMovimientoPeriodo);
			
			JButton botonTransferencia = FabBoton.construirBoton("Realizar Transferencia");
			grupoBotones.add(botonTransferencia);
			botonTransferencia.addActionListener(new ActionListener() {
				
				JTextField textCajaAhorro = new JTextField();
				JTextField textMonto = new JTextField();
				@Override
				public void actionPerformed(ActionEvent e) {
					Object[] message = {
							"Caja de Ahorro destino:", textCajaAhorro,
							"Monto a transferir:", textMonto
					};
					int option = JOptionPane.showConfirmDialog(null, message, "Transferencia", JOptionPane.OK_CANCEL_OPTION);
					if(option == JOptionPane.OK_OPTION) {
						if(textMonto.getText().matches("[0-9]+") && textCajaAhorro.getText().matches("[0-9]+")) {
							
							JOptionPane.showMessageDialog(null,consul.transferencia(textMonto.getText(), textCajaAhorro.getText(),tableConsulta),"Mensaje", JOptionPane.PLAIN_MESSAGE);
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Se deben introducir solo numeros en los campos", "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					}
					
				}
			});
			panelBotones.add(botonTransferencia);
			
			JButton botonExtraccion = FabBoton.construirBoton("Realizar Extraccion");
			grupoBotones.add(botonExtraccion);
			botonExtraccion.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					JTextField textoMonto = new JTextField();
					Object[] message = {
							"Monto a extraer:", textoMonto
					};
					int option = JOptionPane.showConfirmDialog(null, message, "Extraccion", JOptionPane.OK_CANCEL_OPTION);
					if(option == JOptionPane.OK_OPTION) {
						if(textoMonto.getText().matches("[0-9]+")) {
							
							JOptionPane.showMessageDialog(null,consul.extraccion(textoMonto.getText(),tableConsulta),"Mensaje", JOptionPane.PLAIN_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Se deben introducir solo numeros en el campo", "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					}					
				}
			});
			panelBotones.add(botonExtraccion);
			
			
			JButton botonLogin = FabBoton.construirBoton("Login");
			botonLogin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					boolean verificar = false;
					JPasswordField fieldNroTarjeta = new JPasswordField();
					JPasswordField fieldNroPin = new JPasswordField();
					Object[] message = {
							"Numero de tarjeta:", fieldNroTarjeta,
							"PIN:", fieldNroPin
					};
					while (!verificar) {	
						int option = JOptionPane.showConfirmDialog(null, message, "Ingrese Numero y PIN", JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {	
							String nroTarjeta  = new String( fieldNroTarjeta.getPassword());
							String pin = new String(fieldNroPin.getPassword());
							consul = new ConsultaATM(nroTarjeta,pin);
							if(consul.existeTarjeta()) {
								
					           verificar = true;
					           for(JButton boton: grupoBotones) {
					        	   boton.setEnabled(true);
					           }
				               JOptionPane.showMessageDialog(null, "Login Exitoso","ATM",JOptionPane.PLAIN_MESSAGE,null);
							}
							else {
				        	   JOptionPane.showMessageDialog(null, "Numero de tarjeta o PIN incorrecto ingrese nuevamente los datos","Error Login", JOptionPane.ERROR_MESSAGE, null);
				        	   for(JButton boton: grupoBotones) {
				        		   boton.setEnabled(false);
				        	   }
							}
						}
						else 
							verificar = true;
					}
				}
			});
			panelBotones.add(botonLogin);
			
			for(JButton boton: grupoBotones) {
				boton.setEnabled(false);
			}
			
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

}
