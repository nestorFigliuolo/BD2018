package interfaz;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import Banco.ConsultaAdminPrestamo;

import java.awt.Font;
import java.awt.Color;

public class PanelCrearPrestamo extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldMonto;
	private JComboBox<String> comboBoxMeses;
	private List<String> listaMeses;
	
     
	
	private ConsultaAdminPrestamo consu;
	
	
	public PanelCrearPrestamo(ConsultaAdminPrestamo c) {
		
		consu = c;
		listaMeses = consu.tiposCantMeses();
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Cantidad Meses");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel);
		
		
	    comboBoxMeses = new JComboBox<String>();
	    
	    
	    listaMeses.forEach(x->comboBoxMeses.addItem(x));
	    
	    
		add(comboBoxMeses);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblMonto);
		
		textFieldMonto = new JTextField();
		add(textFieldMonto);
		textFieldMonto.setColumns(10);

	}
	
	
	public JComboBox<String> getComboMeses(){
		return comboBoxMeses;
	}
	
	public JTextField getTextFieldMonto(){
		
		return textFieldMonto;
	}
}
