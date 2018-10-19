package Banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConsultaATM {

	private Conexion coneATM;
	private Connection conexionBD = null;
	private String nro_tarjeta;
	private String PIN;
	private Statement stmt = null;
	private ResultSet rs = null;
	private String cod_caja = "100";
	
	public ConsultaATM(String nro_tarjeta,String PIN) {
		this.nro_tarjeta = nro_tarjeta;
		this.PIN = PIN;
		
		coneATM = new Conexion("atm","atm");
		conexionBD = coneATM.conectarBD();
		
	}
	
	//Chekea que exista una tarjeta con su PIN correspondiente
	public boolean existeTarjeta()
	{ 
	try { 
		stmt = conexionBD.createStatement();
		boolean r = false; 
	    String SQL = "select * from tarjeta "
	               +"where nro_tarjeta = "+nro_tarjeta
	               +" and PIN = md5('"+PIN+"')";
	    rs = stmt.executeQuery(SQL);
	    
	    if(rs.next())
	    	r = true;
	    rs.close();
	     stmt.close();
	     
	    return r;
	  
	}catch(Exception e) {}
	
  
	return false;
	}

	
	public String saldoActual()
	{
		
		try { 
		 Statement stmt = this.conexionBD.createStatement();
	     String SQL = "select saldo from tarjeta natural join trans_cajas_ahorro "
	     		     + "where nro_tarjeta = "+nro_tarjeta;
		 
		 ResultSet rs = stmt.executeQuery(SQL);
		  
		 rs.next();
	     String toReturn =  rs.getString("saldo");
		 
	   
	     
	     rs.close();
	     stmt.close();
	     
	     return toReturn;
		}catch(SQLException ex) {}
		
		return null;
	}
	
	public void UltimosMovimientos(JTable tabla) {
			
		// se prepara el string SQL de la consulta
	    String SQL = "select fecha,hora,tipo,monto,cod_caja,destino from tarjeta,trans_cajas_ahorro "
	    		      +"where tarjeta.nro_ca = trans_cajas_ahorro.nro_ca"
	    		   + " and nro_tarjeta = "+nro_tarjeta
	    		   + " ORDER BY fecha desc,hora desc";
		
              tabla.setModel(getUltMovimientModel(SQL));
              tabla.setEnabled(false);
            
         
		
	}
	
	public void MovimientoPorPeriodo(JTable tabla,java.sql.Date fechaMenor,java.sql.Date fechaMayor) {
		
		// se prepara el string SQL de la consulta
	    String SQL = "select fecha,hora,tipo,monto,cod_caja,destino from tarjeta,trans_cajas_ahorro "
	    		      +"where tarjeta.nro_ca = trans_cajas_ahorro.nro_ca"
	    		   + " and nro_tarjeta = "+nro_tarjeta
	    		   + " and fecha between '"+fechaMenor+"' and '"+fechaMayor+"'"
	    		   + " ORDER BY fecha desc,hora desc";
	    tabla.setModel(getUltMovimientModel(SQL));
        tabla.setEnabled(false);
        
       	
	}
	
	
	
	
	
private DefaultTableModel getUltMovimientModel(String SQL) {
	
	  try {
		  Statement stmt = this.conexionBD.createStatement();
			  
		    stmt.execute(SQL);
         ResultSet rs = stmt.getResultSet();
		  
	   String data [][] = {};
       String col [] = {"Fecha","Hora","Tipo","Monto","Codigo_caja","Caja_Ahorro_Destino"};
       DefaultTableModel modelo = new DefaultTableModel(data, col);
       
       
     
       int fil = 0;
       while( rs.next() && fil<15)
       {    modelo.insertRow(fil, new Object[]{});
       	 modelo.setValueAt(Fechas.convertirDateAString(rs.getDate(1)), fil, 0);
       	 modelo.setValueAt(rs.getString(2), fil, 1);
       	 modelo.setValueAt(rs.getString(3), fil, 2);
       	 if(rs.getString(3).equals("Deposito"))
       		 modelo.setValueAt(rs.getString(4), fil, 3);
       	 else
       	     modelo.setValueAt("-"+rs.getString(4), fil, 3);
       	 modelo.setValueAt(rs.getString(5), fil, 4);
       	 modelo.setValueAt(rs.getString(6), fil, 5);
       	            	 
       	 fil++;
       }
       return modelo;
       
       }catch(SQLException e) {}
       
       return null;
}



public String transferencia(String monto,String destino) {
	
	try {
		 Statement stmt = this.conexionBD.createStatement();
	     String SQL = "call transferencia("+monto+","+nro_tarjeta+","+destino+","+cod_caja+")";
		 
		 ResultSet rs = stmt.executeQuery(SQL);
		  
		 rs.next();
	     String toReturn =  rs.getString("resultado");
	     
	     
	     rs.close();
	     stmt.close();
	     
	     return toReturn;
		
	}catch(SQLException e) {
		
	}
	
	return "0";
	
}

public String extraccion(String monto) {
	
	try {
	Statement stmt = this.conexionBD.createStatement();
     String SQL = "call extraccion("+monto+","+nro_tarjeta+","+cod_caja+")";
	 
	 ResultSet rs = stmt.executeQuery(SQL);
	  
	 rs.next();
     String toReturn =  rs.getString("resultado");
     
     return toReturn;
      }catch(SQLException e) {
    	  
      }
	
	return "0";

}

}
