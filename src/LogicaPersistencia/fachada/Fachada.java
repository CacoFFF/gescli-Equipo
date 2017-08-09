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
		//se conecta cada vez que entra a fachada (mejorar esto)
		ConexionBD();
	}
	
	private void ConexionBD(){
		//No conecter si ya esta conectado
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
	
	public void AltaEmpleado(VOEmpleado voEmp)
	{
		ConexionBD();
		if( !AccesoBD.VerificarEmpleado(con, voEmp.getCi()) )
		{
			if(AccesoBD.AgregarEmpleado(con, voEmp))
			{
				//mensaje: se agrego correctamente...  o algo asi
			}
			else
			{}	//mensaje: CI ya existe
		}
		else
		{}	//mensaje: CI no existe
	}//altaEmpleado
	
	public VOEmpleado ObtenerEmpleado( String sCI)
	{
		//Inicializar un Value Object vacio y crear la peticion
		VOEmpleado Peticion = new VOEmpleado( sCI);
		String[] elementos = { "nomFun", "apeFun", "fechaNacFun", "celFun", "baja", "horasDia" };
		Peticion.setElementos( elementos);
		Peticion.setCondicionSQL( "ciFun="+sCI);
		
//		if ()
//		return ...;
		return null;
	}
}
