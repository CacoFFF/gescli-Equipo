package Main;

import LogicaPersistencia.accesoBD.AccesoBD;
import grafica.ventanas.AltaFuncionario;
import grafica.controladores.Con_AltaEmpleado;

public class Main {

	public static AccesoBD AccesoBD;
	public static Con_AltaEmpleado Con_AltaEmpleado;
	
	public static void main(String[] args) {

		AccesoBD = new AccesoBD();
		Con_AltaEmpleado = new Con_AltaEmpleado();
		
		AltaFuncionario.main( args);
	}

}
