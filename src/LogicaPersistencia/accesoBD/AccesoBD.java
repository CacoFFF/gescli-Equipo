package LogicaPersistencia.accesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoBD
{
	private final Consultas consultas = new Consultas();
	
	public AccesoBD(){}

	//cambiar Agregar y Verificar segun Consultas
	public boolean AgregarEmpleado(Connection conn, String sNombre, String sApellido, String sCI, String sTelefono, String sFechaNac, String sCel, String sHorasDia){
		String sqlAgregar=consultas.AgregarEmpleado();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sqlAgregar);
			//luego verificar orden de los set con Consultas.AgregarEmpleado()
			pstmt.setString(1, sNombre);
			pstmt.setString(2, sApellido);
			pstmt.setString(3, sCI);
			pstmt.setString(4, sTelefono);
			pstmt.setString(5, sFechaNac);
			pstmt.setString(6, sCel);
			pstmt.setString(7, sHorasDia);
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			return false;}//tryCatch
	}//agregar
	
	public boolean VerificarEmpleado(Connection conn, String sCI){
		//true si CI existe, false si CI no existe
		String sqlBuscar=consultas.BuscarEmpleadoPorCI();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sqlBuscar);
			pstmt.setString(1, sCI);
			ResultSet rs=pstmt.executeQuery();
			if (!rs.isBeforeFirst()) {rs.close(); pstmt.close(); return false;} //si rs no contiene resultados cierra rs y pstmt y retorna false 
			rs.close();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			//no tendria que llegar aca, por el if de arriba
			return false;
		}//tryCatch
	}//verificarEmpleado

	public void CrearBDatos(Connection conn, String sNombreBDatos) {
				
		String 	sqlDB=consultas.CrearBDatos(sNombreBDatos),
				sqlUsarDB=consultas.UsarBDatos(sNombreBDatos),
				sqlTablaCliente=consultas.CrearTablaCliente(),
				sqlTablaDepartamento=consultas.CrearTablaDepartamentos(),
				sqlTablaFuncionarios=consultas.CrearTablaFuncionarios(),
				sqlTablaHorasFun=consultas.CrearTablaHorasFunc(),
				sqltablaServicios=consultas.CrearTablaServicios();
		try
		{
			Statement stmt=conn.createStatement();
			try
			{
				stmt.executeUpdate(sqlDB);
				stmt.executeUpdate(sqlUsarDB);
				stmt.executeUpdate(sqlTablaCliente);
				stmt.executeUpdate(sqlTablaDepartamento);
				stmt.executeUpdate(sqlTablaFuncionarios);
				stmt.executeUpdate(sqltablaServicios);
				stmt.executeUpdate(sqlTablaHorasFun);
				stmt.close();
			}
			catch (SQLException e)
			{
//				System.out.println( "EXCEPTION " + e.getErrorCode() + " " + e.getMessage() );
				stmt.close(); e.getStackTrace();
			}
		}
		catch (SQLException e)
		{
			e.getStackTrace();
		}
	
	}//crearBD

}
