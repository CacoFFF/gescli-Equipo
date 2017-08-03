package LogicaPersistencia.fachada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Fachada
{
	private Parametros Parametros;
	
	//Tarea: buscar por CI antes de agregar
	//Tarea: crear automaticamente base de datos si no es detectada (Fernando)
	
	public void AltaEmpleado( /*aca iria un value object*/)
	{
		Parametros = new Parametros();
		
		try
		{
			Class.forName( Parametros.getDriver() );
			Connection con = DriverManager.getConnection
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
			sqle.printStackTrace();
		}
	}
}
