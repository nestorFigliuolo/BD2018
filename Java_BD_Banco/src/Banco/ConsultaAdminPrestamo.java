package Banco;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;



public class ConsultaAdminPrestamo {

	private Conexion conePrestamo;
	private Connection conexionBD;
	private java.sql.Statement stmt;  
	private ResultSet rs;
	private String legajo;
	private String password;
	

	private int nro_pago = 1;
	
	public ConsultaAdminPrestamo(String legajo,String password) {
		
		this.legajo = legajo;
		this.password = password;
	
		conePrestamo = new Conexion("empleado","empleado");
		conexionBD = conePrestamo.conectarBD();
	}
	
	
	public String getNumeroCliente(String nro_doc) {
		
		try {
		       stmt = conexionBD.createStatement();
		      
			    String SQL = "select nro_cliente from cliente "
			               +"where nro_doc = "+nro_doc;
			               
			    rs = stmt.executeQuery(SQL);
			    
			    rs.next();
			    String numero =  rs.getString("nro_cliente");	
			    
			    rs.close();
			     stmt.close();
			     
			    return  numero;
	
	
	}catch(SQLException e) {}
	
	return "";
		
	}
	
	public boolean existeCliente(String nro_doc) {
		
		try {
		       stmt = conexionBD.createStatement();
		       boolean r = false; 
			    String SQL = "select * from cliente "
			               +"where nro_doc = "+nro_doc;
			               
			    rs = stmt.executeQuery(SQL);
			    
			    if(rs.next())
			    	r = true;
			    rs.close();
			     stmt.close();
			     
			    return r;
	
	
	}catch(SQLException e) {}
	
	return false;
		
	}
	
	public boolean existeEmpleado() {
		try {
			       stmt = conexionBD.createStatement();
			       boolean r = false; 
				    String SQL = "select * from empleado "
				               +"where legajo = "+legajo
				               +" and password = md5('"+password+"')";
				    rs = stmt.executeQuery(SQL);
				    
				    if(rs.next())
				    	r = true;
				    rs.close();
				     stmt.close();
				     
				    return r;
		
		
		}catch(SQLException e) {}
		
		return false;
	}

	
	public void establecerCuotaPagada(String nro_prestamo,String nro_pago) {
		
		try {
			 stmt = conexionBD.createStatement();
			  
			 String sql = "UPDATE pago " +
                     "SET fecha_pago = curdate()"
                     +" WHERE nro_prestamo = "+nro_prestamo
                     +" and nro_pago = "+nro_pago;
			 
			 stmt.execute(sql);
			 
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void pagarCuotas(String nro_doc,JTable tabla) {
		
		try {
				   stmt = conexionBD.createStatement();
				   
				   String SQL = "select nro_prestamo,nro_pago,valor_cuota,fecha_venc "
				   	    + " from pago natural join prestamo natural join cliente"
			                  +" where nro_doc = "+nro_doc
			                 +" and fecha_pago is NULL";
				   
				    rs = stmt.executeQuery(SQL);
				   
				     String data [][] = {};
				     String col [] = {"Nro_prestamo","Nro_pago","Valor_cuota","Fecha_venc"};
				     
				     ModeloTabla modelo = new ModeloTabla(data, col);
				     
				      int fil = 0;
				       while( rs.next())
				       {    modelo.insertRow(fil, new Object[]{});
				       	 modelo.setValueAt(rs.getString(1), fil, 0);
				       	 modelo.setValueAt(rs.getString(2), fil, 1);
				       	 modelo.setValueAt(rs.getString(3), fil, 2);     
				  	     modelo.setValueAt(Fechas.convertirDateAString(rs.getDate(4)), fil, 3);
				      
				  	        fil++;
				       }
				       
				      
				       
				       tabla.setModel(modelo);
				   
			   
			   
			   
			
		}catch(SQLException e) {
			
		}
	}
	
	
	public boolean clientePagandoPrestamo(String nro_doc) {
     try {		
				   stmt = conexionBD.createStatement();
			       boolean r = false; 
				   String SQL = "select fecha from prestamo natural join cliente"
				               +" where nro_doc = "+nro_doc
				               +" and date_add(fecha,interval cant_meses month ) > curdate()";
				              
				    rs = stmt.executeQuery(SQL);
				    
				    if(rs.next())
				    	r = true;
				    rs.close();
				     stmt.close();
				     
				    return r;
     }catch(SQLException e) {}
	
	
	return false;
	
 }

	
	public void tablaClientesMorosos(JTable tabla) {
		
		
		  
		   
		   String SQL = "SELECT nro_cliente,tipo_doc,nro_doc,nombre,apellido, "
		   		       + "nro_prestamo,monto,cant_meses,valor_cuota, "
		   		       + "count(nro_pago) as cuotas_atrasadas "
			       
			       +"FROM  Cliente natural join Prestamo natural join Pago "

			       +"WHERE fecha_pago is NULL " 
			             +"and fecha_venc < curdate() "  

			       +"Group by nro_prestamo "
			       +"having cuotas_atrasadas > 1 ";
		   
		   
		  
	       tabla.setModel(getModeloMorosos(SQL));
	
	}
	
	
	public void showAllClient(JTable tabla,JButton boton1,JButton boton2) {
		try {
		String SQL = "Select apellido,nombre,tipo_doc,nro_doc "
				     + "from Cliente";
		
		 stmt = conexionBD.createStatement();
		 rs = stmt.executeQuery(SQL);
		 
		 String data [][] = {};
	     String col [] = {"Apellido","Nombre","Tipo_doc","Nro_doc"};
	     
	     ModeloTabla modelo = new ModeloTabla(data, col);
	     
	     int fil = 0;
	       while( rs.next())
	       {    modelo.insertRow(fil, new Object[]{});
	       	 modelo.setValueAt(rs.getString(1), fil, 0);
	       	 modelo.setValueAt(rs.getString(2), fil, 1);
	       	 modelo.setValueAt(rs.getString(3), fil, 2);     
	  	     modelo.setValueAt(rs.getString(4), fil, 3);
	      
	  	        fil++;
	       }
	       
	      
	       
	       tabla.setModel(modelo);
	       
	       tabla.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				//  int row=tabla.getSelectedRow();
				 // System.out.println(row);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				
				 
				  boton1.setEnabled(true);
				  boton2.setEnabled(true);
				  Object [] ob = {boton1,boton2};
				  
				  if(e.getClickCount()==2)
				  { 
			        JOptionPane.showMessageDialog(null,ob, "Acciones sobre el cliente", JOptionPane.INFORMATION_MESSAGE); 
				  }
			}
		});
	                   
	                
	               
	           
		

		}catch(SQLException e) {}
		
	}
	
	
	public String getTazaInteres(String periodo,String monto) {
		
		try {
			   stmt = conexionBD.createStatement();
		          
			   String SQL = "Select tasa from tasa_prestamo "
			   		        + "where periodo = "+periodo
			   		        + " and "+monto+" between monto_inf and monto_sup";
			   rs = stmt.executeQuery(SQL);

			   rs.next();
			   
			   return rs.getString("tasa");
		        
		}catch(SQLException e) {
			
		}
		
		return "";
	}

	public void crearPrestamo(String cant_meses,String monto,String tasa_interes,String nro_cliente) {
		 try {
			     
				   stmt = this.conexionBD.createStatement();
		         
				    String sql = "INSERT INTO prestamo(fecha,cant_meses,monto,tasa_interes,legajo,nro_cliente) " +
		                     "VALUES (curdate(),"+cant_meses+","+monto+","+tasa_interes+","+legajo+","+nro_cliente+")";
		           stmt.execute(sql);
		           
		           
		           String sql2 = "Select nro_prestamo "
		           		       + "from prestamo natural join cliente "
		           		       + "where fecha = curdate() "
		           		       + "and nro_cliente = "+nro_cliente;
		           
		           rs = stmt.executeQuery(sql2);
		           
		           rs.next();
		           
		           String nro_prestamo = rs.getString("nro_prestamo");
		               
		               for(int i=1; i<=Integer.parseInt(cant_meses);i++ ) { 
		                  sql = "INSERT INTO pago(nro_prestamo,nro_pago,fecha_venc) "
		                  		+ "VALUES ("+nro_prestamo+","+nro_pago+",date_add(curdate(),interval "+i+" month ))";
		                  stmt.execute(sql);
		                  nro_pago++;
		               }
		           stmt.close();
		           
		        
		 }catch(SQLException e) {
			 
		 }
	}
	
 	private DefaultTableModel getModeloMorosos(String SQL) {
		try {
				 stmt = conexionBD.createStatement();
				 rs = stmt.executeQuery(SQL);
				 
				 String data [][] = {};
			     String col [] = {"Nro_Cliente","Tipo_Doc","Nro_Doc","Nombre","Apellido","Nro_Prestamo",
			    		          "Monto","Cant_Meses","Valor_Cuota","Cuotas_Atrasadas"};
			       
			     DefaultTableModel modelo = new DefaultTableModel(data, col);
			     
			     int fil = 0;
			       while( rs.next())
			       {    modelo.insertRow(fil, new Object[]{});
			       	 modelo.setValueAt(rs.getString(1), fil, 0);
			       	 modelo.setValueAt(rs.getString(2), fil, 1);
			       	 modelo.setValueAt(rs.getString(3), fil, 2);     
			  	     modelo.setValueAt(rs.getString(4), fil, 3);
			       	 modelo.setValueAt(rs.getString(5), fil, 4);
			       	 modelo.setValueAt(rs.getString(6), fil, 5);
			       	 modelo.setValueAt(rs.getString(7), fil, 6);
			       	 modelo.setValueAt(rs.getString(8), fil, 7);
			       	 modelo.setValueAt(rs.getString(9), fil, 8);     
			  	     modelo.setValueAt(rs.getString(10), fil, 9);
			       	            	 
			       	 fil++;
			       }
			       return modelo;
		
		
		}catch(SQLException e) {}
		
		return null;
	}
 	
	
	
}
