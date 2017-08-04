package grafica.controladores;

//Controlador de ventana alta empleado

public class Con_AltaEmpleado extends ControladorMaestro {

	public void AltaEmpleado( String Nombre, String Apellido, String CI, String Telefono, String FechaNac, String Celular, String HorasDia) {
		//Verificar que nombre, apellido, horas y telefonos no esten vacios
		//Verificar formato de CI (\\d.\\d(3).\\d(3)-\\d)
		
		if ( !ControladorMaestro.FechaValida( FechaNac, "-") )
			return;
				
		
		
		//Para la fachada: Si CI ya existe en la BD, actualizar empleado (no agregar)
		//Para la ventana: agregar boton de baja y funcion de baja aqui y en la fachada
	}
	
}
