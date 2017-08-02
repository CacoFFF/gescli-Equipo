import LogicaPersistencia.accesoBD.AccesoBD;
import grafica.ventanas.AltaFuncionario;

public class Main {

	public static AccesoBD AccesoBD;
	
	public static void main(String[] args) {

		AccesoBD = new AccesoBD();
		AltaFuncionario.main( args);
	}

}
