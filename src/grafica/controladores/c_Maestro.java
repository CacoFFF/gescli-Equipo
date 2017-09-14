package grafica.controladores;

import java.util.StringTokenizer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import LogicaPersistencia.fachada.Fachada;
import LogicaPersistencia.valueObject.VOCliente;
import LogicaPersistencia.valueObject.VOEmpleado;

public class c_Maestro {
	public VOEmpleado oVOF;
	public VOCliente voCli;
	public Fachada gFachada=new Fachada();
	
	protected boolean IsNumeric(String str) {return str.matches("\\d+");}
	protected boolean StringValido(String str) {return str != null || !str.isEmpty();}
	protected int IntConvertidor(String str)
	{
		int a = 0;
		try { a = Integer.parseInt(str); }
		catch (Exception e) {}
		return a;
	}
	public String Substring (String str, String inicio, String fin){
		String sResultado="";	
		int iInicio=str.indexOf(inicio), iFin=str.indexOf(fin);
		try {
			sResultado=str.substring(iInicio+1,iFin);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		return sResultado;
		
	}
	
	// Tabla de dias por meses (entrada cero no corresponde a un mes)
	public static int dmeses[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	// aaaa-mm-dd (el separador es variable)
	public static boolean FechaValida(String fecha, String separador) {
		try {
			StringTokenizer st = new StringTokenizer(fecha, separador);
			if (st.countTokens() != 3)
				return false;
			int[] Datos = new int[3]; // Año, Meses, Dias
			for (int i = 0; i < 3; i++) {
				Datos[i] = Integer.parseInt(st.nextToken());
				if (Datos[i] <= 0)
					return false;
			}

			if (Datos[1] == 2) // Mes = febrero
			{
				// Logica:
				// Divisible entre 4
				// No divisible entre 100 (excepto divisible cada 400)
				boolean bBisiesto = (Datos[0] % 4 == 0) && ((Datos[0] % 100 != 0) || (Datos[0] % 400 == 0));
				dmeses[2] = bBisiesto ? 29 : 28;
			}

			// Verificar que meses y dias existan
			if (Datos[1] > 12 || Datos[2] > dmeses[Datos[1]])
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean CIValida(String CI) {

		try {
			// Buscar el guion
			int dashPos = CI.indexOf('-');
			if (dashPos < 0)
				return false;

			// Dividir el string en partes separadas por puntos (parar antes del
			// guion)
			StringTokenizer st = new StringTokenizer(CI.substring(0, dashPos), ".");
			// Primer conjunto de numeros (verifica que no sean mas de 3
			// digitos)
			String nCI = st.nextToken();
			if (nCI.length() == 0 || nCI.length() > 3)
				return false;
			while (st.hasMoreTokens()) {
				String test = st.nextToken();
				// Verificar que no haya errores de formato (3 digitos entre
				// puntos)
				if (test.length() != 3)
					return false;
				nCI = nCI + test;
			}

			// Eliminados los puntos y verificado el formato, obtener el numero
			// Este paso es necesario? 10m es buen limite?
			int iCI = Integer.parseInt(nCI);
			if (iCI <= 0 || iCI > 10000000)
				return false;
			nCI = CI.substring(dashPos + 1);
			if ((nCI.length() != 1) || nCI.charAt(0) < '0' || nCI.charAt(0) > '9')
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	//Ventanitas <3
	protected void MensajeWin(String txt) {JOptionPane.showMessageDialog(null, txt);}
	protected boolean ConfirmWin(String txt) {if (JOptionPane.showConfirmDialog(null, txt) == 0)return true;return false;}
	protected String InputWin(String txt){
		JTextField tfCI = new JTextField();
		JComponent[] inputs = new JComponent[] {
		        new JLabel(txt), tfCI};
		
		int result = JOptionPane.showConfirmDialog(null, inputs, txt, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
		           return tfCI.getText().trim();
		} else {
 		    return "";
		}
	    

	}
}
