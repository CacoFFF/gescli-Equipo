package Main;

import LogicaPersistencia.accesoBD.AccesoBD;
import grafica.ventanas.AltaFuncionario;
import grafica.controladores.Con_AltaEmpleado;
import grafica.controladores.Con_BajaEmpleado;

public class Main {

	public static AccesoBD AccesoBD;
	public static Con_AltaEmpleado Con_AltaEmpleado;
	public static Con_BajaEmpleado Con_BajaEmpleado;
	
	public static void main(String[] args) {

		AccesoBD = new AccesoBD();
		Con_AltaEmpleado = new Con_AltaEmpleado();
		Con_BajaEmpleado = new Con_BajaEmpleado();
		
		AltaFuncionario.main( args);
	}

}
