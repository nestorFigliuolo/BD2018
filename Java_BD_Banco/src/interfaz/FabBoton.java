package interfaz;

import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class FabBoton {
	
	public static JButton construirBoton(String texto) {
		Insets insets = new Insets(5, 5, 5, 5);
		
		JButton boton = new JButton(texto);
		boton.setMargin(insets);
		boton.setBackground(Interfaz.secondary);
		boton.setForeground(Interfaz.textColor);
		boton.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Interfaz.textColor, 2),
			BorderFactory.createLineBorder(Interfaz.secondary, 8))
		);
		return boton;
	}

}
