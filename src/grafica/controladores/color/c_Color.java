package grafica.controladores.color;

import java.awt.Color;
import grafica.controladores.c_Maestro;

//Controlador generico
public abstract class c_Color extends c_Maestro
{
	//0: sin foco
	//1: con foco
	protected Color colorVacio[];
	protected Color colorValido[];
	protected Color colorInvalido[];
	protected Color colorIndefinido[];

	public c_Color()
	{
		colorVacio = new Color[2];
		colorValido = new Color[2];
		colorInvalido = new Color[2];
		colorIndefinido = new Color[2];
		
		colorVacio[0] = new Color( 255, 255, 255);
		colorValido[0] = new Color( 255, 255, 255);
		colorInvalido[0] = new Color( 255, 0, 0);
		colorIndefinido[0] = new Color( 240, 240, 240);
		colorVacio[1] = new Color( 200, 255, 255);
		colorValido[1] = new Color( 100, 255, 100);
		colorInvalido[1] = new Color( 255, 100, 100);
		colorIndefinido[1] = new Color( 200, 191, 231);
	}
}
