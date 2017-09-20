package grafica.ventanas;

import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Main;
import grafica.controladores.color.c_Fecha;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class cmpFecha extends JPanel
{
	private JTextField ddmmaa[];
	private c_Fecha ctrl;
	
	public JTextField[] getDDMMAA()
	{
		return ddmmaa;
	}
	
	public void resetValues()
	{
		ddmmaa[0].setText("");
		ddmmaa[1].setText("");
		ddmmaa[2].setText("");
	}

	public String getText()
	{
		return ddmmaa[2].getText().trim()+"-"+ddmmaa[1].getText().trim()+"-"+ddmmaa[0].getText().trim();
	}
	
	public void setText( String fecha)
	{
		try
		{
			int pos = fecha.indexOf("-");
			ddmmaa[2].setText(fecha.substring(0, pos));
			fecha = fecha.substring( pos+1);
			pos = fecha.indexOf("-");
			ddmmaa[1].setText(fecha.substring(0, pos));
			fecha = fecha.substring( pos+1);
			ddmmaa[0].setText(fecha);
		}
		catch (Exception e)	{}
	}
	
	public boolean fechaValida()
	{
		return c_Fecha.Validar( ddmmaa);
	}
	
	public cmpFecha()
	{
		setLayout(null);
		//Varia dependiendo del proyecto/implementacion
		try
		{
			ctrl = Main.gCon_Fecha;
		}
		catch ( NullPointerException e)
		{
			ctrl = new c_Fecha();
		}
		
		ddmmaa = new JTextField[3];
		for ( int i=0 ; i<3 ; i++ )
		{
			ddmmaa[i] = new JTextField();
			ddmmaa[i].addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					if ( ctrl != null )
						ctrl.ModificarCampos(ddmmaa);
				}
			});
			ddmmaa[i].addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					if ( ctrl != null )
						ctrl.ModificarCampos(ddmmaa);
				}
			});
			ddmmaa[i].addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent arg0) {
					if ( ctrl != null )
						ctrl.ModificarCampos(ddmmaa);
				}
				public void focusLost(FocusEvent e) {
					if ( ctrl != null )
						ctrl.ModificarCampos(ddmmaa);
				}
			});
			add(ddmmaa[i]);
		}
		ddmmaa[0].setColumns(2);
		ddmmaa[1].setColumns(2);
		ddmmaa[2].setColumns(4);	
		
		addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent arg0)
			{
				int pad = getWidth() * 4 / 100; //Son 2 pads (8 reservados)
				float uX = (float)(getWidth()-pad*2) / 11; //Calculo unidad tomando en cuenta los pads
				int pX;
				pX = 0;
				ddmmaa[0].setBounds( pX, 0, (int)(uX*3), getHeight() );
				pX += ddmmaa[0].getWidth() + pad;
				ddmmaa[1].setBounds( pX, 0, (int)(uX*3), getHeight() );
				pX += ddmmaa[1].getWidth() + pad;
				ddmmaa[2].setBounds( pX, 0, getWidth()-pX, getHeight() );
			}
		});
	}

}
