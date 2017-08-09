package Main;

import grafica.ventanas.AltaFuncionario;
import grafica.controladores.Con_AltaEmpleado;
import grafica.controladores.Con_BajaEmpleado;
import LogicaPersistencia.fachada.Fachada;

public class Main {

	public static Con_AltaEmpleado gCon_AltaEmpleado;
	public static Con_BajaEmpleado gCon_BajaEmpleado;
	public static Fachada gFachada;
	
	public static void main(String[] args) {

		gCon_AltaEmpleado = new Con_AltaEmpleado();
		gCon_BajaEmpleado = new Con_BajaEmpleado();
		gFachada = new Fachada();
		
		AltaFuncionario.main( args);
	}

}
