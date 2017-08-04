package LogicaPersistencia.accesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoBD
{

	public AccesoBD()
	{	
	}

	public boolean AgregarEmpleado(Connection conn, String nombre, String apellido, String ci, String telefono, String fechaNac, String cel, String horasDia){
		Consultas con=new Consultas();
		String sqlAgregar=con.AgregarEmpleado();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sqlAgregar);
			//luego verificar orden de los set con Consultas.AgregarEmpleado()
			pstmt.setString(1, nombre);
			pstmt.setString(2, apellido);
			pstmt.setString(3, ci);
			pstmt.setString(4, telefono);
			pstmt.setString(5, fechaNac);
			pstmt.setString(6, cel);
			pstmt.setString(7, horasDia);
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			return false;}//tryCatch
	}//agregar
	
	public boolean VerificarEmpleado(Connection conn, String ci){
		Consultas con=new Consultas();
		String sqlBuscar=con.BuscarEmpleadoPorCI();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sqlBuscar);
			pstmt.setString(1, ci);
			ResultSet rs=pstmt.executeQuery();
			if (!rs.isBeforeFirst()) {rs.close(); return false;} //si rs no contiene resultados cierra rs y retorna false 
			rs.close();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			//no tendria que llegar aca, por el if de arriba
			return false;
		}//tryCatch
	}//verificarEmpleado
	

	
	
}
