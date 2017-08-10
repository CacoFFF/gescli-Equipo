package grafica.controladores;

public class Con_BajaEmpleado extends ControladorMaestro {

	public void BajaEmpleado( String sCI) {
		
		//Buscar empleado por CI en la BD (usamos VO?, es un solo parametro!)
		//Si existe, proceder
		//Si no existe, notificar con un mensaje de error
		
		
		
		if ( !ControladorMaestro.CIValida(sCI) )
		{
			MensajeWin( "Formato de CI erróneo" + "\r\n" + "Usar: x.xxx.xxx-x", "Error RellenarEmpleado", 0);
			return;
		}
		
		
		
	}
}
