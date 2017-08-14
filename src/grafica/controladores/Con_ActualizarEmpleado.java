package grafica.controladores;

import LogicaPersistencia.valueObject.VOEmpleado;

public class Con_ActualizarEmpleado extends ControladorMaestro {

	
	public void ActualizarEmpleado( String sNombre, String sApellido, String sCI, String sFechaNac, String sCelular, String sHorasDia, boolean bActivo) {

		boolean bNombre=true, bApellido=true, bCI=true, bFecha=true, bCel=true, bHoras=true;
		String sErrMensage="Error en:";
		if (sNombre.length() == 0 ){sErrMensage+="\n-Nombre"; bNombre=false;}
		if (sApellido.length() == 0){sErrMensage+="\n-Apellido"; bApellido=false;}
		if (!ControladorMaestro.CIValida( sCI)){sErrMensage+="\n-Cedula"; bCI=false;}
		if (!ControladorMaestro.FechaValida( sFechaNac, "-")){sErrMensage+="\n-Fecha"; bFecha=false;}
		if (!EsNumerico(sCelular)){sErrMensage+="\n-Celular"; bCel=false;}
		if (!EsNumerico(sHorasDia)){sErrMensage+="\n-Horas"; bHoras=false;}
		
		if(!bNombre || !bApellido || !bCI || !bFecha || !bCel || !bHoras){
			MensajeWin(sErrMensage, "Error al ingresar datos", 2); return;}
			
		//Para la fachada: Si CI ya existe en la BD, actualizar empleado (no agregar)
	
		VOEmpleado oVO = new VOEmpleado( sNombre, sApellido, sCI, sFechaNac, sCelular, sHorasDia, bActivo);
//		Main.Main.gFachada.AltaEmpleado(oVO); //
		if(ConfirmWin("Guardar:\n"+sCI+": " + (bActivo ? "Activo" : "Inactivo"), "Actualizar Empleado", 3)){
			String sResultado=gFachada.ActualizarEmpleado(oVO);
			MensajeWin(sResultado, "Actualizar empleado", 1);
		}
	}
	
}
