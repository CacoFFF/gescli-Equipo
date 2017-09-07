package grafica.controladores.color;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JTextField;


public class c_Fecha extends c_Color
{
	//Evitar que la funcion se llame recursivamente
	private boolean bLock; 
	
	public c_Fecha()
	{
		super();
		bLock = false;
	}
	
	public void ModificarCampos( JTextField ddmmaa[])
	{
		if ( bLock )	return;
		bLock = true;

		try
		{
			int focus = 0;
			int valido[] = new int[3];
			int valores[] = new int[3];
			//0 = nulo/vacio
			//1 = no numerico
			//2 = evaluar cada caso
			String sT[] = { ddmmaa[0].getText(), ddmmaa[1].getText(), ddmmaa[2].getText()};
	
			//Colores genericos
			int validos = 0;
			for ( int i=0 ; i<3 ; i++ )
			{
				focus = ddmmaa[i].hasFocus() ? 1 : 0;
				if ( sT[i] == null || sT[i].length() == 0 )
				{
					valido[i] = 0;
					ddmmaa[i].setBackground( colorVacio[focus] );
				}
				else if ( !IsNumeric(sT[i]) || (IntConvertidor(sT[i]) <= 0) )
				{
					valido[i] = 1;
					ddmmaa[i].setBackground( colorInvalido[focus]);
				}
				else
				{
					valido[i] = 2;
					valores[i] = IntConvertidor(sT[i]);
					ddmmaa[i].setBackground( colorValido[focus] );
					validos++;
				}
			}
			
			
			//Meses
			if ( valido[1] == 2 )
			{
				focus = ddmmaa[1].hasFocus() ? 1 : 0;
				if ( IntConvertidor( sT[1]) > 12 )
					ddmmaa[1].setBackground( colorInvalido[focus]);
				else if ( validos == 3 )
					ddmmaa[1].setBackground( colorValido[focus]);
				else
					ddmmaa[1].setBackground( colorIndefinido[focus]);
			}		
			
			//Dias
			if ( valido[0] == 2 )
			{
				focus = ddmmaa[0].hasFocus() ? 1 : 0;
				if ( validos == 3 )
				{
					if ( valores[0] > getDaysOfMonth(valores[2], valores[1]) )
						ddmmaa[0].setBackground( colorInvalido[focus]);
					else
						ddmmaa[0].setBackground( colorValido[focus]);
				}
				else
					ddmmaa[0].setBackground( colorIndefinido[focus]);
			}
		}
		catch( Exception e)
		{
			e.printStackTrace();
		}
		bLock = false;
	}
	
	/**
	 * java.util.Calendar tiene un problema
	 * Calendar.JANUARY es 0 en lugar de 1!!!
	 * Usar esta funcion con Enero como 1
	 */
	public static int getDaysOfMonth( int yy, int mm)
	{
		Calendar oCal = new GregorianCalendar( yy, mm-1, 1);
		return oCal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
}
