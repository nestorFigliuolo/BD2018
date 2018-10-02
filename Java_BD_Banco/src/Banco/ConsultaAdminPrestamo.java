package Banco;

import java.sql.*;

import com.mysql.cj.xdevapi.Statement;

public class ConsultaAdminPrestamo {

	private Conexion conePrestamo;
	private Connection conexionBD;
	private java.sql.Statement stmt;  
	private ResultSet rs;
	private String legajo;
	private String password;
	
	public ConsultaAdminPrestamo(String legajo,String password) {
		
		this.legajo = legajo;
		this.password = password;
	
		conePrestamo = new Conexion("empleado","empleado");
		conexionBD = conePrestamo.conectarBD();
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

}
