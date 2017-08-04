package grafica.controladores;

import java.util.StringTokenizer;

//
// Controlador generico
// Contiene mayoritariamente funciones utilitarias
// FUTURO: Para mayores capas, los utiles a otro class
//
public class ControladorMaestro
{
	//Tabla de dias por meses (entrada cero no corresponde a un mes)
	public static int dmeses[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	//aaaa-mm-dd (el separador es variable)
	public static boolean FechaValida( String fecha, String separador)
	{
		try
		{
			StringTokenizer st = new StringTokenizer( fecha, separador);
			if ( st.countTokens() != 3 )
				return false;
			int[] Datos = new int[3]; //Año, Meses, Dias
			for ( int i=0 ; i<3 ; i++ )
			{
				Datos[i] = Integer.parseInt(st.nextToken());
				if ( Datos[i] <= 0 )
					return false;
			}

			if ( Datos[1] == 2) //Mes = febrero
			{
				//Logica:
				// Divisible entre 4
				// No divisible entre 100 (excepto divisible cada 400)
				boolean bBisiesto = (Datos[0] % 4 == 0) && ((Datos[0] % 100 != 0) || (Datos[0] % 400 == 0));
				dmeses[2] = bBisiesto ? 29 : 28;
			}
			
			//Verificar que meses y dias existan
			if ( Datos[1] > 12 || Datos[2] > dmeses[Datos[1]] )
				return false;
		}
		catch ( Exception e )
		{
			return false;
		}
		return true;
	}
}
