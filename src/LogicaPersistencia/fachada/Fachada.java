package LogicaPersistencia.fachada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import LogicaPersistencia.accesoBD.AccesoBD;
import LogicaPersistencia.valueObject.VOEmpleado;

public class Fachada
{
	private Parametros Parametros;
	private AccesoBD AccesoBD;
	private Connection con = null;
	private boolean bCreandoBD = false;
	
	//Tarea: buscar por CI antes de agregar
	
	public Fachada()
	{
		//Crear dependientes
		Parametros = new Parametros();
		AccesoBD = new AccesoBD();
		ConexionBD();
	}
	
	private void ConexionBD(){
		//No conectar si ya esta conectado
		try
		{
			if ( con != null && !con.isClosed() )
				return;
		}
		catch ( SQLException sqle )
		{	System.out.println( sqle.getMessage() );	}
		
		
		try
		{
			Class.forName( Parametros.getDriver() );
			con = DriverManager.getConnection
					( Parametros.getUrl() + Parametros.getBDatos()
					, Parametros.getUser()
					, Parametros.getPassword() );
		}
		catch ( ClassNotFoundException ce)	{ce.printStackTrace();}
		catch ( SQLException sqle )
		{
			if ( sqle.getErrorCode() == 1049 ) //Base de datos no existente
				CrearBD();
			else
				sqle.printStackTrace();
		}
			
	}//coneccion BD

	private void CrearBD()
	{
		if ( !bCreandoBD )
		{
			bCreandoBD = true;
			try
			{
				Connection test = DriverManager.getConnection(Parametros.getUrl(),Parametros.getUser(),Parametros.getPassword());
				AccesoBD.CrearBDatos(test,Parametros.getBDatos());
				ConexionBD(); //Recursion
			}
			catch (SQLException sqle2)
			{
				sqle2.printStackTrace();
			}
			bCreandoBD = false;
		}
	}
	
	public String AltaEmpleado(VOEmpleado voEmp)
	{
		ConexionBD();
		if(!AccesoBD.VerificarEmpleado(con, voEmp.getCi())) 
			if(AccesoBD.AgregarEmpleado(con, voEmp)) {
				return voEmp.getResultado();
				
			}
		
		return "Cedula ya agregada"; //agregar algun dato del fun existente?

	}//altaEmpleado
	
	public String ActualizarEmpleado(VOEmpleado voEmp)
	{
		ConexionBD();
		if( AccesoBD.VerificarEmpleado(con, voEmp.getCi()) && AccesoBD.ActualizarEmpleado(con, voEmp))
			return voEmp.getResultado();
		else
			return (voEmp.getError().length() > 0) ? voEmp.getError() : "Cedula no existe";
	}//altaEmpleado
	
	
	public VOEmpleado ObtenerEmpleado( String sCI)
	{
		//Inicializar un Value Object vacio y crear la peticion
		VOEmpleado VO = new VOEmpleado( sCI);
		AccesoBD.ObtenerEmpleadoCI( con, VO);
		return VO;
	}
}
