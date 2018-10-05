package interfaz;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


import Banco.ConsultaAdminPrestamo;

public class PanelAdminPrestamos extends JPanel {
	
	private JTable tableConsulta;
	private JTable tableMorosos;
	private ConsultaAdminPrestamo consu;

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
			JButton botonConsultarCliente = FabBoton.construirBoton("Consultar Cliente");
			JButton botonPagarCuotas = FabBoton.construirBoton("Pagar Cuotas seleccionadas");
			
			JButton botonLogin = FabBoton.construirBoton("Login");
			botonLogin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean verificar = false;
					JTextField fieldLegajo = new JTextField();
					JPasswordField fieldPass = new JPasswordField();
					Object[] message = {
							"Legajo:", fieldLegajo,
							"Password:", fieldPass
					};
					while (!verificar) {	
						int option = JOptionPane.showConfirmDialog(null, message, "Ingrese Legajo y Password", JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							String legajo = fieldLegajo.getText();
							String pass  = new String(fieldPass.getPassword());
							consu = new ConsultaAdminPrestamo(legajo, pass);
						
							if(consu.existeEmpleado()) {
								
					           verificar = true;
				             //  botonPrestamo.setEnabled(true);
				            //   botonConsultarCliente.setEnabled(true);
				            //   botonPagarCuotas.setEnabled(true);
				               JOptionPane.showMessageDialog(null, "Login Exitoso","Admin Prestamos",JOptionPane.PLAIN_MESSAGE,null);
							   consu.tablaClientesMorosos(tableMorosos);
							   consu.showAllClient(tableConsulta,botonPrestamo);
							}
							else {
								
				        	   JOptionPane.showMessageDialog(null, "Legajoo y/o Password incorrecta, ingresela nuevamente","Error Login", JOptionPane.ERROR_MESSAGE, null);
				        	   botonPrestamo.setEnabled(false);
				               botonConsultarCliente.setEnabled(false);
				               botonPagarCuotas.setEnabled(false);
							}
						}
						else 
							verificar = true;
					}
					
				}
			});
		    panelBotones.add(botonLogin);
			
		    botonPrestamo.setEnabled(false);
			botonPrestamo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//Accion del boton crear Prestamo
					
					JTextField fieldTipo = new JTextField();
					JTextField fieldNro = new JTextField();
					
					Object[] message = {
							"TIPO_DOC:", fieldTipo,
							"NRO_DOC:", fieldNro
					};
					
					int fila = tableConsulta.getSelectedRow();
					
					fieldTipo.setText(""+tableConsulta.getValueAt(fila, 2));
					fieldNro.setText(""+tableConsulta.getValueAt(fila, 3));
					
					int option = JOptionPane.showConfirmDialog(null, message, "Buscar Cliente", JOptionPane.OK_CANCEL_OPTION);	
		
					
					registrarPrestamo(option,fieldNro.getText());
					
				}
			});
			panelBotones.add(botonPrestamo);
			
			
			botonConsultarCliente.setEnabled(false);
			botonConsultarCliente.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			panelBotones.add(botonConsultarCliente);
			
			
			botonPagarCuotas.setEnabled(false);
			botonPagarCuotas.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			panelBotones.add(botonPagarCuotas);
			
		
		
		tableConsulta = new JTable();
		//tableConsulta.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Consulta", TitledBorder.CENTER, TitledBorder.TOP, null, Interfaz.textColor));
		tableConsulta.setBackground(Interfaz.fondo);
		tableConsulta.setForeground(Interfaz.textColor);
		tableConsulta.setAutoCreateRowSorter(true);
		GridBagConstraints gbc_tableConsulta = new GridBagConstraints();
		gbc_tableConsulta.insets = new Insets(0, 0, 5, 0);
		gbc_tableConsulta.fill = GridBagConstraints.BOTH;
		gbc_tableConsulta.weightx = 50;
		gbc_tableConsulta.weighty = 95;
		gbc_tableConsulta.gridx = 0;
		gbc_tableConsulta.gridy = 1;
		add(new JScrollPane(tableConsulta), gbc_tableConsulta);
		
		tableMorosos = new JTable();
		//tableMorosos.setBorder(new TitledBorder(new LineBorder(Interfaz.textColor), "Morosos", TitledBorder.CENTER, TitledBorder.TOP, null, Interfaz.textColor));
		tableMorosos.setBackground(Interfaz.fondo);
		tableMorosos.setForeground(Interfaz.textColor);
		tableMorosos.setAutoCreateRowSorter(true);
		tableMorosos.setEnabled(false);
		GridBagConstraints gbc_tableMorosos = new GridBagConstraints();
		gbc_tableMorosos.insets = new Insets(0, 0, 5, 0);
		gbc_tableMorosos.fill = GridBagConstraints.BOTH;
		gbc_tableMorosos.weightx = 50;
		gbc_tableMorosos.weighty = 95;
		gbc_tableMorosos.gridx = 1;
		gbc_tableMorosos.gridy = 1;
		add(new JScrollPane(tableMorosos), gbc_tableMorosos);
		
	}

	
	private void registrarPrestamo(int op,String nro_doc) {
		
		if(op==JOptionPane.OK_OPTION) {
			
			if( consu.existeCliente(nro_doc)) {
							PanelCrearPrestamo pane = new PanelCrearPrestamo();
							
							if(!consu.clientePagandoPrestamo(nro_doc)) {
									int option = JOptionPane.showConfirmDialog(null,pane, "Crear Nuevo Prestamo", JOptionPane.OK_CANCEL_OPTION);
										
									     if(option== JOptionPane.OK_OPTION) {
									    	 
									    	            String monto = pane.getTextFieldMonto().getText();
									    	            String cant_meses = (String) pane.getComboMeses().getSelectedItem();
									    	            
									    	            
											            if(Double.parseDouble(monto)<30000) {
															String tasa = consu.getTazaInteres(cant_meses, monto);
															String nro_cliente = consu.getNumeroCliente(nro_doc);
															
															consu.crearPrestamo(cant_meses, monto, tasa,nro_cliente);
															 JOptionPane.showMessageDialog(null, "Prestamo Creado","Prestamo",JOptionPane.PLAIN_MESSAGE,null);
											            	
														}
														else {
															JOptionPane.showMessageDialog(null, "El Monto del prestamo debe ser menor o igual a 30000 ","Error Monto Prestamo", JOptionPane.ERROR_MESSAGE, null);
														}
									     }
							}
							else {
								JOptionPane.showMessageDialog(null, "El Cliente ya tiene un prestamo vigente","Error Cliente", JOptionPane.ERROR_MESSAGE, null);
							}	
							
			}
			else {
				 JOptionPane.showMessageDialog(null, "No existe el cliente con nro_doc = "+nro_doc,"Error Cliente", JOptionPane.ERROR_MESSAGE, null);	
			}
			
		}
		
		
		
	}
	
}
