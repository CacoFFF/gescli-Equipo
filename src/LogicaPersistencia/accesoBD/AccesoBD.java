package LogicaPersistencia.accesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import LogicaPersistencia.valueObject.*;

public class AccesoBD
{
	private final Consultas consultas = new Consultas();
	
	public AccesoBD(){}
	//Creacion y mod BD
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
			
		} catch (SQLException e) { System.out.println(e.getMessage()); }
	}

	//Parte Funcionario
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
			pstmt.setInt(7, oVO.getHorasDia() );
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
			pstmt.setInt(6, oVO.getHorasDia() );
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
				VO.setHorasDia( rs.getInt(5));
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
	public ArrayList<VOEmpleado> ListarFuncionarios(Connection conn)
	{
		ArrayList<VOEmpleado> alListaFun = new ArrayList<VOEmpleado>();;
		String sqlListarFun=consultas.ListarFuncionarios();
		try
		{
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sqlListarFun);
			while(rs.next())
			{
				VOEmpleado nuevoFun = new VOEmpleado(
						rs.getInt("idFun"),
						rs.getString("nomFun"),
						rs.getString("apeFun"),
						rs.getString("ciFun"),
						rs.getString("fechNacFun"),
						rs.getString("celFun"),
						rs.getInt("horasDia"),
						rs.getBoolean("baja"));
				alListaFun.add(nuevoFun);
			}
			rs.close();
		}
		catch (SQLException e)
		{	System.out.println(e.getErrorCode()+" "+e.getMessage());	}
		return alListaFun;
	}

	//Parte de Departamento
	public ArrayList<String> ListarDepartamentos(Connection conn){
		String sqlListDeptos=consultas.ListarDepartamentos();
		ArrayList<String> arDeptos=new ArrayList<String>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sqlListDeptos);
			while(rs.next()){
				arDeptos.add(rs.getString("nombre"));
			}
			rs.close();	stmt.close();
			return arDeptos;
		} catch (Exception e) {System.out.println("Err.ListaDeptsAccesoBD"+e.getStackTrace()); return null;}
	}//ListarDepartamentos()	
	public void getIdDepartamento(Connection conn, VOCliente voCli){
		String sqlIdDepto=consultas.BuscarIDDepartamento();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sqlIdDepto);
			pstmt.setString(1, voCli.getsNomDepto());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){voCli.setiIdDepto(rs.getInt("idDepto"));}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("err1");
			voCli.setError("Error al conseguir ID de departamento");
		}
	}	
	
	//Parte Cliente
	public ArrayList<VOCliente> ListarClientes(Connection conn){
	
		ArrayList<VOCliente> alListaCli = new ArrayList<VOCliente>();;
		String sqlListarCli=consultas.ListarClientes();
		try
		{
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sqlListarCli);
			while ( rs.next() )
			{
				VOCliente nuevoCLI = new VOCliente(
						rs.getInt("idDepto"),
						rs.getInt("hsCargables"),
						rs.getInt("honorarios"),
						rs.getInt("moneda"),
						rs.getString("rut"),
						"BORRAR ESTO",
						rs.getString("tel"),
						rs.getString("direccion"),
						rs.getString("nomCli"),
						rs.getInt("idCli"),
						rs.getString("nroCli")
						);
				alListaCli.add( nuevoCLI);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("error listacli"+e.getMessage());
		}
		return alListaCli;
		
	} 
	public boolean AgregarCliente(Connection conn, VOCliente voCli){
		//(idDepto, hsCargables, honorarios, moneda, rut, nroCli, tel, direccion, nomCli) "
		String sqlAgregarCli=consultas.AgregarCliente();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sqlAgregarCli);
			pstmt.setInt(1, voCli.getiIdDepto());
			pstmt.setInt(2, voCli.getiHrCargables());
			pstmt.setInt(3, voCli.getiHonorarios());
			pstmt.setInt(4, voCli.getiMoneda());
			pstmt.setString(5, voCli.getsRut());
			pstmt.setString(6, voCli.getsNroCli());
			pstmt.setString(7, voCli.getsTel());
			pstmt.setString(8, voCli.getsDireccion());
			pstmt.setString(9, voCli.getsNomCli());
			pstmt.executeUpdate();
			pstmt.close();
			
			voCli.setResultado("Guardado:\n["+voCli.getsNroCli()+"] "+voCli.getsNomCli());
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			voCli.setError("Error al intentar guardar cliente");
			return false;
		}
		
		
	}
	public void ObtenerCliente(Connection conn, VOCliente voCli){
		//idDepto, hsCargables, honorarios, moneda, rut, nroCli, tel, direccion, nomCli
		String sqlBuscarCli=consultas.BuscarCliente();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sqlBuscarCli);
			pstmt.setString(1, voCli.getsNroCli());
			pstmt.setInt(2, voCli.getiIdCli());
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()){
				voCli.setiIdDepto(rs.getInt("idDepto"));
				voCli.setiHrCargables(rs.getInt("hsCargables"));
				voCli.setiHonorarios(rs.getInt("honorarios"));
				voCli.setiMoneda(rs.getInt("moneda"));
				voCli.setsRut(rs.getString("rut"));
				voCli.setsNroCli(rs.getString("nroCli"));
				voCli.setsTel(rs.getString("tel"));
				voCli.setsDireccion(rs.getString("direccion"));
				voCli.setsNomCli(rs.getString("nomCli"));
			}
			rs.close(); pstmt.close();
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public boolean ModificarCliente(Connection conn, VOCliente voCli){
		/*	rut ,nroCli, tel, direccion, idDepto, nomCli, hsCargables, honorarios, moneda
			WHERE  idCli = ?  AND nroCli = ? */
		String consulta=consultas.ModificarCliente();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement(consulta);
			pstmt.setString(1, voCli.getsRut());
			pstmt.setString(2, voCli.getsNroCli());
			pstmt.setString(3, voCli.getsTel());
			pstmt.setString(4, voCli.getsDireccion());
			pstmt.setInt(5, voCli.getiIdDepto());
			pstmt.setString(6, voCli.getsNomCli());
			pstmt.setInt(7, voCli.getiHrCargables());
			pstmt.setInt(8, voCli.getiHonorarios());
			pstmt.setInt(9, voCli.getiMoneda());
			pstmt.setInt(10, voCli.getiIdCli());
			pstmt.setString(11, voCli.getsNroCli());
			pstmt.executeUpdate();
			pstmt.close();
			
			voCli.setResultado("Cliente actualizado!");
			return true;
			
		} catch (SQLException e) {
			voCli.setError("Error al actualizar cliente\nError code: "+e.getErrorCode()+"\nError: "+e.getMessage());
			return false;
			
		}
		
		
	}
	public boolean EliminarCliente(Connection conn, VOCliente voCli){
		//idCli, nroCli
		String consulta=consultas.BajaCliente();
		try {
			PreparedStatement pstmt=conn.prepareStatement(consulta);
			pstmt.setInt(1, voCli.getiIdCli());
			pstmt.setString(2, voCli.getsNroCli());
			pstmt.executeUpdate();
			pstmt.close();
			voCli.setResultado("Cliente eliminado! X_X");
			return true;
			
		} catch (SQLException e) {
			String sResult="Error al eliminar cliente";
			if(e.getErrorCode()==1451) sResult+="\n Cliente tener horario agendado";
			voCli.setError(sResult);
			return false;
		}
	}

	//Parte Servicios
	public ArrayList<String> ListarNServicios(Connection conn)
	{
		ArrayList<String> res = new ArrayList<String>();;
		String consulta=consultas.ListarNServicio();
		try
		{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while(rs.next())
				res.add( rs.getString("idServ")+"@"+rs.getString("nombre"));
			rs.close();
			return res;
		}
		catch (SQLException e)
		{	System.out.println("error listanserv"+e.getMessage());	}
		return res;
	}	
	public boolean AgregarNServicio(Connection conn, VONServicio voNS)
	{
		//(idDepto, hsCargables, honorarios, moneda, rut, nroCli, tel, direccion, nomCli) "
		String consulta=consultas.AgregarNServicio();
		try
		{
			PreparedStatement pstmt=conn.prepareStatement(consulta);
			pstmt.setString(1, voNS.getsNombre() );
			pstmt.executeUpdate();
			pstmt.close();
			
			voNS.setResultado("Guardado:\n"+voNS.getsNombre());
			return true;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			voNS.setError("Error al intentar guardar nuevo servicio");
			return false;
		}
	}
	public boolean BorrarNServicio(Connection conn, VONServicio voNS)
	{
		//(idDepto, hsCargables, honorarios, moneda, rut, nroCli, tel, direccion, nomCli) "
		String consulta=consultas.BorrarNServicio();
		try
		{
			PreparedStatement pstmt=conn.prepareStatement(consulta);
			pstmt.setString(1, voNS.getsNombre() );
			pstmt.executeUpdate();
			pstmt.close();
			
			voNS.setResultado("Borrado:\n"+voNS.getsNombre());
			return true;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			voNS.setError("Error al intentar borrar servicio");
			return false;
		}
	}

	//Parte Horarios
	public boolean AgregarHorario(Connection conn, VOHorario voHora){
		//idFun, idCli, idServ, horas, fecha
		String consulta = consultas.AgregarHorario();
		try {
			PreparedStatement pstmt=conn.prepareStatement(consulta);
			pstmt.setString(1, voHora.getsCIFuncionario());
			pstmt.setString(2, voHora.getsNumCliente());
			pstmt.setString(3, voHora.getsNomServicio());
			pstmt.setInt(4, voHora.getiHoras());
			pstmt.setString(5, voHora.getsFecha());
			pstmt.executeUpdate();
			pstmt.close();
			
			voHora.setResultado("Horario guardado");
			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				voHora.setError("Error al intentar guardar horario");
				return false;
			}
		return true;
	}
	
	public int ContarHorarios( Connection conn, int sqlMode, String sqlParams[])
	{
		String consulta = consultas.ContarHorarios(sqlMode);
		int total = 0;
		try 
		{
			PreparedStatement pstmt=conn.prepareStatement(consulta);
			//i = posicion de [?]
			//j = posicion de sqlParams[]
			int i = 1; 
			if ( sqlParams != null ) for ( int j=0 ; j<sqlParams.length ; j++ )
				pstmt.setString( i++, sqlParams[j]);
			ResultSet rs = pstmt.executeQuery();
			if ( rs.next() )
				total = rs.getInt("cantidad");
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return total;
	}
	
	public ArrayList<VOHorario> ListarHorarios( Connection conn, int sqlMode, String sqlParams[], int pag, int cantidadPorPag)
	{
		String consulta = consultas.ListarHorarios(sqlMode);
		ArrayList<VOHorario> lista = new ArrayList<VOHorario>();
		try 
		{
			PreparedStatement pstmt = conn.prepareStatement(consulta);
			//i = posicion de [?]
			//j = posicion de sqlParams[]
			int i = 1; 
			if ( sqlParams != null ) for ( int j=0 ; j<sqlParams.length ; j++ )
				pstmt.setString( i++, sqlParams[j]);
			pstmt.setInt( i++, cantidadPorPag*(pag-1) ); //Offset
			pstmt.setInt( i++, cantidadPorPag);
			ResultSet rs = pstmt.executeQuery();
			while ( rs.next() )
			{
				VOHorario nuevoHS = new VOHorario(
						rs.getInt("Horas"),
						rs.getString("Servicio"),
						rs.getString("Cliente"),
						rs.getString("Funcionario"),
						rs.getString("Fecha"));
				nuevoHS.setIdCli (rs.getInt("idCli" ));
				nuevoHS.setIdFun (rs.getInt("idFun" ));
				nuevoHS.setIdServ(rs.getInt("idServ"));
				lista.add( nuevoHS);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return lista;
	}

	
	public boolean BorrarHorario( Connection conn, VOHorario vH)
	{
		String consulta = consultas.BorrarHorario();
		try {
			PreparedStatement pstmt=conn.prepareStatement(consulta);
			pstmt.setInt(1, vH.getIdCli());
			pstmt.setInt(2, vH.getIdFun());
			pstmt.setInt(3, vH.getIdServ());
			pstmt.setInt(4, vH.getiHoras());
			pstmt.setString(5, vH.getsFecha());
			pstmt.executeUpdate();
			pstmt.close();
			
			vH.setResultado("Horario borrado");
			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				vH.setError("Error al intentar borrar horario");
				return false;
			}
		return true;
	}
	
	private int rand(int base, int mult)
	{
		return (int)(Math.random() * mult) + base;
	}
	
	public void boom( Connection conn)
	{
		String consultas[] = new String[4];
	
		consultas[0] =  "insert into clientes "
			+ "(idDepto, hsCargables, honorarios, moneda, rut, nroCli, tel, direccion, nomCli) "
			+ "values( ";
		for ( int i=0 ; i<30 ; i++ )
		{
			consultas[0] = consultas[0] 
					+ rand(1,18) + ","
					+ rand(1,6) + ","
					+ rand(1000,5000) + ","
					+ rand(1,2) + ","
					+ rand( 10000000,90000000) + ","
					+ rand(0,15000) + ","
					+ "09"+rand(100000,900000) + ","
					+ "'Calle "+rand(10,90) + "',"
					+ "'Cliente A"+rand(0,10000  ) + "')";
			if ( i < 29 )
				consultas[0] = consultas[0] + ",(";
		}
		
		try
		{
			Statement stmt=conn.createStatement();
			stmt.executeUpdate(consultas[0]);
		}
		catch (SQLException e)
		{	System.out.println(e.getErrorCode()+" "+e.getMessage());	}

	}
}
