package Banco;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.protocol.Resultset;

import quick.dbtable.*;

public class Consultas {

private Connection conexionBD = null;
	
	
public Consultas() {}	



//Devuelve una tabla q es el resultado de la sentencia SQL pasada por parametro
public DBTable consultaAdmin(String sentencia,String pw)
{   
	//Creo la tabla donde se devolveran los resultados de la sentencia SQL
	DBTable tabla = new DBTable();
	
	conectarBD("admin",pw,tabla);
	
	
	
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
	
	
	
	return tabla;
	
	
}

//Devuelve una JList con los nombre de los atributos de la tabla
public JList<String> nombreAtributosTabla(String nombreTabla)
{ 
	 try
     { 
		 conectarBD();
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


/*

public JTable UltimosMovimientos(String numeroTarjeta)
{	   
	  Conexion cone = new Conexion("admin","admin");
	    
	  this.conexionBD = cone.conectarBD();
	       
	      //conectarBD();
    try { // se crea una sentencia o comando jdbc para realizar la consulta 
   	      // a partir de la coneccion establecida (conexionBD)
		   // Statement stmt = this.conexionBD.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
		     //        ResultSet.CONCUR_UPDATABLE);
		             
		    Statement stmt = this.conexionBD.createStatement();
	
		  // se prepara el string SQL de la consulta
    String SQL = "select fecha,hora,tipo,monto,cod_caja,destino from tarjeta,trans_cajas_ahorro "
    		      +"where tarjeta.nro_ca = trans_cajas_ahorro.nro_ca"
    		   + " and nro_tarjeta = "+numeroTarjeta
    		   +" and fecha between '20090101' and '20180404' ";
    		   
    
    String s = "select * from trans_cajas_ahorro";
            
           // se ejecuta la sentencia y se recibe un resultset
            
             stmt.execute(SQL);
             ResultSet rs = stmt.getResultSet();
           
    
             String data [][] = {};
             String col [] = {"Fecha","Hora","Tipo","Monto","Codigo_caja","Caja_Ahorro_Destino"};
             DefaultTableModel modelo = new DefaultTableModel(data, col);
             
             int fil = 0;
             while( rs.next() && fil<15)
             {    modelo.insertRow(fil, new Object[]{});
             	 modelo.setValueAt(rs.getString(1), fil, 0);
             	 modelo.setValueAt(rs.getString(2), fil, 1);
             	 modelo.setValueAt(rs.getString(3), fil, 2);
             	 if(rs.getString(3).equals("Deposito"))
             		 {modelo.setValueAt(rs.getString(4), fil, 3);}
             	 else
             	    {modelo.setValueAt("-"+rs.getString(4), fil, 3);}
             	 modelo.setValueAt(rs.getString(5), fil, 4);
             	 modelo.setValueAt(rs.getString(6), fil, 5);
             	            	 
             	 fil++;
             }
             
            
             
	        JTable tabla = new JTable(modelo);
	        tabla.setEnabled(false);
	      
	        //modelo.setValueAt(aValue, rowIndex, columnIndex);
	      
	      //tabla.setValueAt("holaa", 2, 2);
	      
	   
     
	 return tabla;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//tabla.removeColumnSelectionInterval(1, 2);
	
    return null;
	

	
}
*/
/*
public String saldoActual(String numeroTarjeta)
{
	
	try { 
	 conectarBD();	
	 Statement stmt = this.conexionBD.createStatement();
     String SQL = "select saldo from tarjeta natural join caja_ahorro where nro_tarjeta = "+numeroTarjeta;
	 
	 ResultSet rs = stmt.executeQuery(SQL);
	  
	 rs.next();
     String toReturn =  rs.getString("saldo");
	 
   
     
     rs.close();
     stmt.close();
     
     return toReturn;
	}catch(SQLException ex) {}
	
	return null;
}
*/
private void conectarBD(String user,String pw,DBTable tabla)
{
      try
      {
         String driver ="com.mysql.cj.jdbc.Driver";
     	 String servidor = "localhost:3306";
         String baseDatos = "banco";
         String usuario = user;
         String clave = pw;
         String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos+"?serverTimezone=UTC";

         //establece una conexión con la  B.D. "bancos"  usando directamante una tabla DBTable    
         tabla.connectDatabase(driver, uriConexion, usuario, clave);
        
      }
      catch (SQLException ex)
      {
          /*JOptionPane.showInputDialog(this,
                                        "Se produjo un error al intentar conectarse a la base de datos.\n" + ex.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE); */
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      }
      catch (ClassNotFoundException e)
      {
         e.printStackTrace();
      }
 
      
      
}

/*
private void conectarBD()
{
   if (this.conexionBD == null)
   {
     
 	  try
      {
         Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
      }
      catch (Exception ex)
      {
         System.out.println(ex.getMessage());
      }
     
      try
      {
         String servidor = "localhost:3306";
         String baseDatos = "banco";
         String usuario = "admin";
         String clave = "admin";
         String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos+"?serverTimezone=UTC";

         this.conexionBD = DriverManager.getConnection(uriConexion, usuario, clave);
      }
      catch (SQLException ex)
      {
       
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      }
   }
}
*/



}