package Banco;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

public class ConsultaAdminPrestamo {

	private Conexion conePrestamo;
	private Connection conexionBD;
	private java.sql.Statement stmt;  
	private ResultSet rs;
	private String legajo;
	private String password;
	
	private int nro_prestamo = 900;
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
	
	
	public void showAllClient(JTable tabla,JButton boton) {
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
				
				  int row1=tabla.getSelectedRow();
				  int row2=tabla.getSelectedRow();
				  boton.setEnabled(true);
				  
				  if(e.getClickCount()==2 && row1==row2)
				  { 
			        JOptionPane.showMessageDialog(null, ""+row1, "mostrar", JOptionPane.INFORMATION_MESSAGE); 
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
		         
				    String sql = "INSERT INTO prestamo(nro_prestamo,fecha,cant_meses,monto,tasa_interes,legajo,nro_cliente) " +
		                     "VALUES ("+nro_prestamo+",curdate(),"+cant_meses+","+monto+","+tasa_interes+","+legajo+","+nro_cliente+")";
		           stmt.execute(sql);
		               
		               for(int i=1; i<=Integer.parseInt(cant_meses);i++ ) { 
		                  sql = "INSERT INTO pago(nro_prestamo,nro_pago,fecha_venc) "
		                  		+ "VALUES ("+nro_prestamo+","+nro_pago+",date_add(curdate(),interval "+i+" month ))";
		                  stmt.execute(sql);
		                  nro_pago++;
		               }
		           stmt.close();
		           
		           nro_prestamo++;
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
