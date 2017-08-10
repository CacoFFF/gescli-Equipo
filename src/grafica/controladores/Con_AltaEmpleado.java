package grafica.controladores;

import LogicaPersistencia.valueObject.VOEmpleado;

//Controlador de ventana alta empleado

public class Con_AltaEmpleado extends ControladorMaestro {

	public void AltaEmpleado( String sNombre, String sApellido, String sCI, String sFechaNac, String sCelular, String sHorasDia, boolean bActivo) {
		//Verificar que horas y telefonos no esten vacios
		//Verificar formato de CI (\\d.\\d(3).\\d(3)-\\d)

		if ( sNombre.length() == 0 
			|| sApellido.length() == 0
			|| !ControladorMaestro.CIValida( sCI)
			|| !ControladorMaestro.FechaValida( sFechaNac, "-")
			|| !EsNumerico(sCelular)
			|| !EsNumerico(sHorasDia)){
			MensajeWin("Error al ingresar datos", "Alta Emplado", 2);
			return;
		}
			
				
		//Para la fachada: Si CI ya existe en la BD, actualizar empleado (no agregar)
	
		VOEmpleado oVO = new VOEmpleado( sNombre, sApellido, sCI, sFechaNac, sCelular, sHorasDia, bActivo);
//		Main.Main.gFachada.AltaEmpleado(oVO); // mas nullPointerException
		String sResultado=gFachada.AltaEmpleado(oVO);
		
		MensajeWin(sResultado, "Resultado Ingreso", 1);
		//Para la ventana: agregar boton de baja y funcion de baja aqui y en la fachada
	}
	
}
