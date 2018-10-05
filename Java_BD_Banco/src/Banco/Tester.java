package Banco;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interfaz.PanelAdmin;
import interfaz.PanelCrearPrestamo;
import quick.dbtable.DBTable;
import sun.util.calendar.JulianCalendar;

public class Tester extends JFrame{

private static DBTable tabla;
private static Consultas consu;

	
	
	public static void main(String[] args) 
	   {
		Tester t = new Tester();
		tabla = new DBTable();
		consu = new Consultas();
		
		ConsultaAdminPrestamo em = new ConsultaAdminPrestamo("1234", "tatarobert");
		
		
		System.out.println(em.getNumeroCliente("41100940"));
		
		 //JOptionPane.showConfirmDialog(null, new PanelCrearPrestamo(), "Buscar Cliente", JOptionPane.OK_CANCEL_OPTION);
		
		//System.out.println(em.clientePag);
		
	
		//System.out.println(p.saldoActual());
		//p.MovimientoPorPeriodo("20190202", "20080101")
          
		
		//System.out.println(consu.saldoActual("1234567890123200"));
		// String s = "select * from trans_cajas_ahorro";
			//tabla.setSelectSql("SELECT * from Ciudad");
			//tabla.refresh();
			//tabla.setMaximumSize(new Dimension(800,600));
			t.setSize(800, 800);
	//	t.getContentPane().add(new JScrollPane(em.mostrarTablas()),BorderLayout.CENTER);
			t.setPreferredSize(new Dimension(800, 600));
			t.setVisible(true);
			
		
	
	   }	
	
}
