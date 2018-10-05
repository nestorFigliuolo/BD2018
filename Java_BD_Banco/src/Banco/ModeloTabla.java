package Banco;

import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel {

	
	 public ModeloTabla(Object[][]data,Object []col) {
		 
		 super(data,col);
	 }

	public boolean isCellEditable (int row, int column)
	   {
	       
	       return false;
	   }
}
