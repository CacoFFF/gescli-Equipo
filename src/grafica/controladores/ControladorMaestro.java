package grafica.controladores;

import java.util.StringTokenizer;
import javax.swing.JOptionPane;

import LogicaPersistencia.fachada.Fachada;

//
// Controlador generico
// Contiene mayoritariamente funciones utilitarias
// FUTURO: Para mayores capas, los utiles a otro class
//
public class ControladorMaestro
{
	public static Fachada gFachada = new Fachada();
	
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
			int[] Datos = new int[3]; //AÃ±o, Meses, Dias
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
	
	public static boolean CIValida( String CI)
	{
		//return CI.matches("\\d.\\d(3).\\d(3)/\\d"); //<-- con regex, ver si funciona
		try
		{
			//Buscar el guion
			int dashPos = CI.indexOf('-');
			if ( dashPos < 0 )
				return false;
			
			//Dividir el string en partes separadas por puntos (parar antes del guion)
			StringTokenizer st = new StringTokenizer( CI.substring(0, dashPos) , ".");
			//Primer conjunto de numeros (verifica que no sean mas de 3 digitos)
			String nCI = st.nextToken();
			if ( nCI.length() == 0 || nCI.length() > 3 )
				return false;
			while ( st.hasMoreTokens() )
			{
				String test = st.nextToken();
				//Verificar que no haya errores de formato (3 digitos entre puntos)
				if ( test.length() != 3 )
					return false;
				nCI = nCI + test;
			}
			
			//Eliminados los puntos y verificado el formato, obtener el numero
			//Este paso es necesario? 10m es buen limite?
			int iCI = Integer.parseInt( nCI);
			if ( iCI <= 0 || iCI > 10000000 )
				return false;
			nCI = CI.substring( dashPos+1);
			if ( (nCI.length() != 1) || nCI.charAt(0) < '0' || nCI.charAt(0) > '9' )
				return false;
		}
		catch ( Exception e )
		{
			return false;
		}
		return true;
	}
	public static boolean EsNumerico(String str){
		/* regex -> '\d+'
		 * \ necesita otro \ delante(?) para que el señor java lo reconozca
		 * d = digit
		 * + = mas de un digit
		*/
		return str.matches("\\d+");}

	//ventanitas de info y confirmacion

	public void MensajeWin(String sTxt, String sTitle, int iIcon){
		//Icon -> 0=error, 1=info, 2=warning 3=question
		JOptionPane.showMessageDialog(null, sTxt, sTitle, iIcon );
	}//mensaje de errores, etc
	
	public boolean ConfirmWin(String sTxt, String sTitle, int iIcon){
		// 0 = YES_OPTION;
		if(JOptionPane.showConfirmDialog(null, sTxt, sTitle, JOptionPane.YES_NO_OPTION, iIcon ) == 0)
			return true; return false;}
	

}
