package Banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.prism.ResourceFactoryListener;

import quick.dbtable.DBTable;

public class ConsultaAdmin {

	private Conexion coneAdmin;
	private Connection conexionBD;
	
	
	public ConsultaAdmin() {
		coneAdmin = new Conexion("admin","admin");
		conexionBD = coneAdmin.conectarBD();
	}
	
	
	
	//Devuelve una tabla q es el resultado de la sentencia SQL pasada por parametro
	public void consultaAdmin(DBTable tabla,String sentencia)
	{   
						
		
		coneAdmin.conectarBD(tabla);
		
		
		
		try
	    { 
		tabla.setSelectSql(sentencia);
		tabla.createColumnModelFromQuery(); 
		
		
		 for (int i = 0; i < tabla.getColumnCount(); i++)
		  { // para que muestre correctamente los valores de tipo TIME (hora)  		   		  
			 if	 (tabla.getColumn(i).getType()==Types.TIME)  
			 {    		 
			  tabla.getColumn(i).setType(Types.CHAR);  
		       	 }
			 // cambiar el formato en que se muestran los valores de tipo DATE
			 if	 (tabla.getColumn(i).getType()==Types.DATE)
			 {
			    tabla.getColumn(i).setDateFormat("dd/MM/YYYY");
			 }
	     }  
		
		 tabla.refresh();
	    }
	    catch (SQLException ex)
	    {
	       // en caso de error, se muestra la causa en la consola
	       System.out.println("SQLException: " + ex.getMessage());
	       System.out.println("SQLState: " + ex.getSQLState());
	       System.out.println("VendorError: " + ex.getErrorCode());
	      
	    }	
		
		
	}


	public JList<String> nombreAtributosTabla(String nombreTabla)
	{ 
		 try
	     { 
			 
		     Statement stmt = this.conexionBD.createStatement();
		     String SQL = "select * from "+nombreTabla;
		     ResultSet rs = stmt.executeQuery(SQL);
		     java.sql.ResultSetMetaData mt = rs.getMetaData();
	         String [] arr = new String [20];
		  
		  for(int i= 1; i<=mt.getColumnCount();i++)
		     arr[i]= mt.getColumnName(i);
		  
		  JList<String> l = new JList<String>(arr);
		 
		  rs.close();
	      stmt.close();
	      
	      return l;
	      
	     }catch (SQLException ex) {}
		
		 return null;
		
	}

}
