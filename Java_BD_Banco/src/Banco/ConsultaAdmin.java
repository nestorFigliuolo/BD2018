package Banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;


import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;


import quick.dbtable.DBTable;

public class ConsultaAdmin {

	private Conexion coneAdmin;
	private Connection conexionBD;
	
	
	public ConsultaAdmin() {
		coneAdmin = new Conexion("admin","admin");
		conexionBD = coneAdmin.conectarBD();
	}
	
	
	
	//Devuelve una tabla q es el resultado de la sentencia SQL pasada por parametro
	public int consultaAdmin(DBTable tabla,String sentencia)
	{   
		int toReturn = 0;
		try
	    { 
			
			
			Statement stmt = this.conexionBD.createStatement();
			  
			stmt.execute(sentencia);
			
			ResultSet rs = stmt.getResultSet();
			
			toReturn = 1;
			
		 if(rs  != null) {
			 
			toReturn = 2; 
		    coneAdmin.conectarBD(tabla);	
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
	    }
	    catch (SQLException ex)
	    {
	       // en caso de error, se muestra la causa en la consola
	       System.out.println("SQLException: " + ex.getMessage());
	       System.out.println("SQLState: " + ex.getSQLState());
	       System.out.println("VendorError: " + ex.getErrorCode());
	       JOptionPane.showMessageDialog(null,
                   ex.getMessage() + "\n", 
                   "Error al ejecutar la sentencia SQL.",
                   JOptionPane.ERROR_MESSAGE);
	    }	
		return toReturn;
		
		
	}

	
	public void abmAdmin(String sentencia) throws SQLException {
	
		
		Statement stmt = this.conexionBD.createStatement();
		  
		stmt.execute(sentencia);
		

	}

	public void mostrarTablas(JList<String> lista){
		try {
				 Statement stmt = this.conexionBD.createStatement();
			     String SQL = "show tables ";
			     ResultSet rs = stmt.executeQuery(SQL);
			     
			     
			     
			     DefaultListModel<String> model = new DefaultListModel<String>();
			     
			    
			     
				 while(rs.next()) {
					 
					 model.addElement(rs.getString(1));
					 
				 }
				 
				 
				 
				 lista.setModel(model);
				 
				  rs.close();
			      stmt.close();
			      
			 
			     
		}catch(SQLException e) {}
		
	
	}
	
	public void nombreAtributosTabla(JList<String> lista,String nombreTabla)
	{ 
		 try
	     { 
			 
		     Statement stmt = this.conexionBD.createStatement();
		     String SQL = "select * from "+nombreTabla;
		     ResultSet rs = stmt.executeQuery(SQL);
		     java.sql.ResultSetMetaData mt = rs.getMetaData();
		     
		     DefaultListModel<String> model = new DefaultListModel<String>();
		     
	       
		  
		  for(int i= 1; i<=mt.getColumnCount();i++)
		      model.addElement(mt.getColumnName(i));
		  
		  lista.setModel(model);
		 
		  rs.close();
	      stmt.close();
	      
	  
	      
	     }catch (SQLException ex) {}
		
		
		
	}

}
