package LogicaPersistencia.fachada;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

//
// Separado este codigo de Fachada para facilitar su comprensión y lectura
//
public class Parametros
{
	//Paramatros necesarios para conectar a la base de datos
	private String driver;
	private String bdatos;
	private String url;
	private String user;
	private String password;

	//Nombre de archivo de configuracion
	private static String nombreArchivo = "parametros.properties";

	//Este metodo solo existe para probar la creacion/carga
	//del archivo de parametros
	public static void main( String[] args)
	{
		new Parametros();
	}
	
	//Crear modulo de configuracion
	public Parametros()
	{
		Properties p = new Properties();
		String nomArch = "config/" + nombreArchivo;
		try
		{
			p.load(new FileInputStream(nomArch));

			driver = p.getProperty("driver");
			bdatos = p.getProperty("bdatos");
			url = p.getProperty("url");
			user = p.getProperty("user");
			password = p.getProperty("password");
		}
		catch (FileNotFoundException e)
		{
			(new File("config/")).mkdirs();
			driver = "com.mysql.jdbc.Driver";
			bdatos = "gescli";
			url = "jdbc:mysql://localhost:3306/";
			user = "root";
			password = "";
			GuardarParametros();
		}
		catch (IOException ei)
		{
			ei.printStackTrace();
		}
	}
	
	//Guardar parametros, crea directorio y archivo si no existen
	public synchronized void GuardarParametros()
	{
		try
		{
			Properties p = new Properties();
			String nomArch = "config/" + nombreArchivo;
	
			FileOutputStream output = new FileOutputStream(nomArch);
			
			p.setProperty("driver", driver);
			p.setProperty("bdatos", bdatos);
			p.setProperty("url", url);
			p.setProperty("user", user);
			p.setProperty("password", password);
	
			// save properties to project root folder
			p.store(output, null);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getDriver()   {	return driver;  }
	public String getBDatos()   {	return bdatos;  }
	public String getUrl()      {	return url;     }
	public String getUser()     {	return user;    }
	public String getPassword() {	return password;}

	
}
