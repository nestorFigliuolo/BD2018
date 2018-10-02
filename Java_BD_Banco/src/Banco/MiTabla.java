package Banco;

import java.sql.*;

import javax.swing.table.AbstractTableModel;





public class MiTabla extends AbstractTableModel {

	private ResultSet rs;
	private ResultSetMetaData mt;
	
	public MiTabla(ResultSet rs) {
		this.rs = rs;
		try {
			this.mt = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public int getColumnCount() {
		
		try {
			return mt.getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int getRowCount() {
		
		try {
			rs.last();
		
		
	
			return rs.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		
		try {
			rs.absolute(arg0 + 1);
			
			if(arg1 +1 == 4) {
				
			}
			
			return rs.getObject(arg1+1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String getColumnName(int c) {
		
		
		
		try {
			return mt.getColumnName(c+1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
		
	}
	
	public void setValueAt(Object aValue, int vrowIndex,int columnIndex) {
		
		
		
	}
	
	
	public boolean isCellEditable(int a , int b) {
		return true;
	}

}
