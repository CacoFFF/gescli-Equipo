package grafica.controladores;

import LogicaPersistencia.valueObject.VOEmpleado;

public class Con_BajaEmpleado extends ControladorMaestro {

	public void BajaEmpleado(String sCI, boolean bEstado) {
		
		//Buscar empleado por CI en la BD (usamos VO?, es un solo parametro!)
		//Si existe, proceder
		//Si no existe, notificar con un mensaje de error
		
		
		//ci no valida
		if ( !ControladorMaestro.CIValida(sCI) )
		{
			MensajeWin( "Formato de CI erróneo" + "\r\n" + "Usar: x.xxx.xxx-x", "Error RellenarEmpleado", 0);
			return;
		}
			
		//questionea si da de baja
		VOEmpleado voEmp=new VOEmpleado(sCI, bEstado);		
		if(ConfirmWin("Guardar:\n"+sCI+": " + (bEstado ? "Activo" : "Inactivo"), "Baja Empleago", 3)){
			String sResultado=gFachada.BajaEmpleado(voEmp);
			MensajeWin(sResultado, "Baja empleado", 1);
		}
		
	}
}
