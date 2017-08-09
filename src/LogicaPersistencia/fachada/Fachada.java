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
	private Connection con;
	
	//Tarea: buscar por CI antes de agregar
	//Tarea: crear automaticamente base de datos si no es detectada (Fernando)
	
	public Fachada(){
		//se conecta cada vez que entra a fachada (mejorar esto)
		ConeccionBD();
		}
	
	private void ConeccionBD(){
		AccesoBD=new AccesoBD();
		Parametros = new Parametros();
		Connection test=null;

		try
		{
			Class.forName( Parametros.getDriver() );
			con = DriverManager.getConnection
					( Parametros.getUrl() + Parametros.getBDatos()
					, Parametros.getUser()
					, Parametros.getPassword() );
		}
		catch ( ClassNotFoundException ce)
		{
			ce.printStackTrace();
		}
		catch ( SQLException sqle )
		{
			//llega aca si la BD no existe (ver getConnection throws)
			try {
				test=DriverManager.getConnection(Parametros.getUrl(),Parametros.getUser(),Parametros.getPassword());
				AccesoBD.CrearBDatos(test,Parametros.getBDatos());
			} catch (SQLException sqle2) {sqle2.printStackTrace();}
			//sqle.printStackTrace();
		}
		if(test != null) con=test;//por nullPointException (ver)
			
	}//coneccion BD
	
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
