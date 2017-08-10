package LogicaPersistencia.accesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import LogicaPersistencia.valueObject.VOEmpleado;

public class AccesoBD
{
	private final Consultas consultas = new Consultas();
	
	public AccesoBD(){}

	//cambiar Agregar y Verificar segun Consultas
	public String AgregarEmpleado(Connection conn, VOEmpleado oVO)
	{
		String sqlAgregar=consultas.AgregarEmpleado();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sqlAgregar);
			pstmt.setString(1, oVO.getNombre() );
			pstmt.setString(2, oVO.getApellido() );
			pstmt.setString(3, oVO.getCi() );
			pstmt.setString(4, oVO.getFechaNac() );
			pstmt.setString(5, oVO.getCel() );
			pstmt.setBoolean(6, oVO.getBaja() );
			pstmt.setString(7, oVO.getHorasDia() );
			pstmt.executeUpdate();
			pstmt.close();
			return "[Agregado]\n"+oVO.getCi()+": "+oVO.getNombre()+" "+oVO.getApellido();
		} catch (SQLException e) {
//			System.out.println(e.getErrorCode());
			return "";}//tryCatch
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

	//CI es pasada en el VO
	public boolean ObtenerEmpleadoCI( Connection conn, VOEmpleado VO) {
		//1=nomFun,2=apeFun,3=fechNacFun,4=celFun,5=horasDia
		String sqlBuscar=consultas.BuscarEmpleadoPorCI();
		try 
		{
			PreparedStatement pstmt=conn.prepareStatement(sqlBuscar);
			pstmt.setString(1, VO.getCi() );
			ResultSet rs=pstmt.executeQuery();

			int i = 0;
			if ( rs.next() ) //Solo vamos a ver un caso
			{
				i++;
				VO.setNombre( rs.getString(1));
				VO.setApellido( rs.getString(2));
				VO.setFechaNac( rs.getString(3));
				VO.setCel( rs.getString(4));
				VO.setHorasDia( rs.getString(5));
			}
			else
				VO.setError( "No existe funcionario con dicha cedula.");
			rs.close();
			pstmt.close();
			return i > 0;
		} catch (SQLException e) {
			//no tendria que llegar aca, por el if de arriba
			return false;
		}//tryCatch
	}
	
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
