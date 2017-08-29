package LogicaPersistencia.accesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import LogicaPersistencia.valueObject.VOEmpleado;

public class AccesoBD
{
	private final Consultas consultas = new Consultas();
	
	public AccesoBD(){}

	//cambiar Agregar y Verificar segun Consultas
	public boolean AgregarFuncionario(Connection conn, VOEmpleado oVO)
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
			oVO.setResultado("[Agregado]\n"+oVO.getCi()+": "+oVO.getNombre()+" "+oVO.getApellido());
			return true;
		} catch (SQLException e) {
//			System.out.println(e.getErrorCode());
			return false;}//tryCatch
	}//agregar
	
	public boolean ActualizarFuncionario(Connection conn, VOEmpleado oVO)
	{
		String sqlActualizar=consultas.ActualizarEmpleado();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sqlActualizar);
			pstmt.setString(1, oVO.getNombre() );
			pstmt.setString(2, oVO.getApellido() );
			pstmt.setString(3, oVO.getFechaNac() );
			pstmt.setString(4, oVO.getCel() );
			pstmt.setBoolean(5, oVO.getBaja() );
			pstmt.setString(6, oVO.getHorasDia() );
			pstmt.setString(7, oVO.getCi() );
			pstmt.executeUpdate();
			pstmt.close();
			oVO.setResultado("[Actualizado]\n"+oVO.getCi()+": "+oVO.getNombre()+" "+oVO.getApellido());
			return true;
		} catch (SQLException e) {
			oVO.setError("SQL Error: "+e.getMessage());
			return false;}//tryCatch
	}//agregar
	
	public boolean VerificarFuncionario(Connection conn, String sCI){
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
			return false;
		}//tryCatch
	}//verificarEmpleado
	//CI es pasada en el VO
	public boolean ObtenerEmpleadoCI( Connection conn, VOEmpleado VO) {
		//1=nomFun,2=apeFun,3=fechNacFun,4=celFun,5=horasDia, 6=baja
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
				VO.setBaja(rs.getBoolean(6));
			}
			else
				VO.setError( "No existe funcionario con dicha cedula.");
			rs.close();
			pstmt.close();
			return i > 0;
		} catch (SQLException e) {
			return false;
		}//tryCatch
	}

	public boolean BajaFuncionario(Connection conn, VOEmpleado VO){
		String sqlBajaFuncionario=consultas.BajaEmpleadoCI();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sqlBajaFuncionario);
			pstmt.setBoolean(1, VO.getBaja());
			pstmt.setString(2, VO.getCi());
			pstmt.executeUpdate();
			pstmt.close();
			VO.setResultado("Cambio guardado!");
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public ArrayList<String> ListarFuncionario(Connection conn){
		//seguir aca
		ArrayList<String> alListaFun = new ArrayList<String>();;
		String sqlListarFun=consultas.ListarFuncionarios();
		String stmp="";
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sqlListarFun);
			while(rs.next()){
				stmp="["+rs.getString("ciFun")+"] - "+rs.getString("nombre");
				alListaFun.add(stmp);
			}
			rs.close();
			return alListaFun;
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
		}
		return alListaFun;
		
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
			
			}catch (SQLException e){stmt.close(); e.getStackTrace();}
		}catch (SQLException e){	e.getStackTrace();}
		AgregarDepartamentos(conn);
		
		
	
	}//crearBD
	
	public void AgregarDepartamentos(Connection conn){
		String[] asDeptos={"Artigas", "Canelones", "Cerro Largo", "Colonia", "Durazno", "Flores", "Florida", 
				"Lavalleja", "Maldonado", "Montevideo", "Paysandu", "Rio Negro", "Rivera", "Rocha", "Salto", 
				"San Jose" ,"Soriano" ,"Tacuarembo" ,"Treinta y Tres"};
		String sqlAgregarDepto=consultas.AgregarDepartamentos();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sqlAgregarDepto);
			for(String sDepto:asDeptos){
				pstmt.setString(1, sDepto);
				pstmt.setString(2, sDepto);
				pstmt.executeUpdate();
			}
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		
	}

}
