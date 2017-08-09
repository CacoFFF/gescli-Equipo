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
	
	public Fachada(){
		//Crear dependientes
		Parametros = new Parametros();
		AccesoBD = new AccesoBD();
		//se conecta cada vez que entra a fachada (mejorar esto)
		ConeccionBD();
		}
	
	private void ConeccionBD(){
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
				ConeccionBD(); //Recursion
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
		AccesoBD=new AccesoBD();
		String 	sNombre=voEmp.getNombre(),
				sApellido=voEmp.getApellido(),
				sCI=voEmp.getCi(),
				sTelefono=voEmp.getTelefono(),
				sFechaNac=voEmp.getFechaNac(),
				sCel=voEmp.getCel(),
				sHorasDia=voEmp.getHorasDia();
		
		//verificar si CI existe
		if(!AccesoBD.VerificarEmpleado(con, sCI)){
			//si se agrega correctamente
		 if(AccesoBD.AgregarEmpleado(con, sNombre, sApellido, sCI, sTelefono, sFechaNac, sCel, sHorasDia)){
				//mensaje: se agrego correctamente...  o algo asi
			 return;}//if donde agrega empleado		
		//mensaje: CI ya existe
		 return;
		}//if donde verifica CI
	}//altaEmpleado
}
