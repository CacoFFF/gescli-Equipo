package grafica.controladores;

import LogicaPersistencia.valueObject.VOEmpleado;

//Controlador de ventana alta empleado

public class Con_AltaEmpleado extends ControladorMaestro {

	public void AltaEmpleado( String sNombre, String sApellido, String sCI, String sFechaNac, String sCelular, String sHorasDia, boolean bActivo) {

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
		String sResultado=gFachada.AltaEmpleado(oVO);
		
		MensajeWin(sResultado, "Resultado Ingreso", 1);
		//Para la ventana: agregar boton de baja y funcion de baja aqui y en la fachada
	}
	
}
