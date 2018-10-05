package interfaz;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class PanelCrearPrestamo extends JPanel {
	
	private JTextField textFieldMonto;
	private JComboBox<String> comboBoxMeses;
	

	/**
	 * Create the panel.
	 */
	public PanelCrearPrestamo() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Cantidad Meses");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel);
		
		
	    comboBoxMeses = new JComboBox<String>();
		comboBoxMeses.addItem("6");
		comboBoxMeses.addItem("12");
		comboBoxMeses.addItem("24");
		comboBoxMeses.addItem("60");
		comboBoxMeses.addItem("120");
		
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
