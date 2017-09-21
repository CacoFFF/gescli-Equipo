package grafica.controladores;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import LogicaPersistencia.valueObject.VOHorario;

public abstract class c_TablaHS extends c_Maestro
{
	public static int ContarPaginas( JTable table, int sqlMode, String sqlParams[])
	{
		return 1 + (gFachada.ContarHorarios( sqlMode, sqlParams)-1) / table.getRowCount();
	}
	
	public static void MostrarPagina( JTable table, int pag, int sqlMode, String sqlParams[])
	{
		VOHorario horarios[] = gFachada.ListarHorarios( sqlMode, sqlParams, pag, table.getRowCount() );
		int i = 0;
		TableModel model = table.getModel();
		for ( ; i<horarios.length ; i++ )
		{
			VOHorario oHS = horarios[i];
			model.setValueAt( oHS.getsFecha()             , i, 0);
			model.setValueAt( oHS.getsNomServicio()       , i, 1);
			model.setValueAt( oHS.getsCIFuncionario()     , i, 2);
			model.setValueAt( oHS.getsNumCliente()        , i, 3);
			model.setValueAt( new Integer(oHS.getiHoras()), i, 4);
		}
		for ( ; i<table.getRowCount(); i++ )
			for ( int j=0 ; j<5 ; j++ )
				model.setValueAt( null, i, j);
	}
}
