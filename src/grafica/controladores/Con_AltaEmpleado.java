package grafica.controladores;

import LogicaPersistencia.valueObject.VOEmpleado;

//Controlador de ventana alta empleado

public class Con_AltaEmpleado extends ControladorMaestro {

	public void AltaEmpleado( String sNombre, String sApellido, String sCI, String sTelefono, String sFechaNac, String sCelular, String sHorasDia) {
		//Verificar que nombre, apellido, horas y telefonos no esten vacios
		//Verificar formato de CI (\\d.\\d(3).\\d(3)-\\d)
		
		if ( !ControladorMaestro.FechaValida( sFechaNac, "-") )
			return;
				
		//Para la fachada: Si CI ya existe en la BD, actualizar empleado (no agregar)

		VOEmpleado oVO = new VOEmpleado( sNombre, sApellido, sCI, sTelefono, sFechaNac, sCelular, sHorasDia);
		
		//Para la ventana: agregar boton de baja y funcion de baja aqui y en la fachada
	}
	
}
