package grafica.controladores;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import LogicaPersistencia.valueObject.VOEmpleado;
import grafica.ventanas.PaneFuncionario;


public class c_Funcionario extends c_Maestro {
	
	public void ListaFun(JComboBox<String> cb) {
		
		cb.removeAllItems();
		cb.addItem("--Funcionarios--");
		ArrayList<String> alFun=new ArrayList<String>();
		alFun=gFachada.ListaFun();//ver este graan pase de arraylist desde accesoBD
		if(alFun.isEmpty()) return;

		for(String sFun : alFun) cb.addItem(sFun);
	}
		
	public void BuscarCI(JButton btnBaja, JTextField tfNomFun, JTextField tfApefun, JTextField tfCI,
			JTextField tfFecNac1, JTextField tfFecNac2, JTextField tfFecNac3, JTextField tfCel, JTextField tfHoras, 
			JCheckBox bEstado, String... sBuscarLista){
		String sCI=null;
		
		sCI = sBuscarLista.length > 0 ? sBuscarLista[0] : null;
		
		if(sCI==null){ sCI=InputWin("Ingrese CI a buscar:"); }
			
		
		if ( !CIValida(sCI))
		{
			if (sCI.isEmpty()) return; //Ingreso cancelado
			MensajeWin( "Formato de CI erróneo" + "\r\n" + "Usar: x.xxx.xxx-x");
			return; 
		}
		
		oVOF = gFachada.ObtenerEmpleado(sCI);
		
		if ( oVOF != null )
		{
			if ( oVOF.getError().length() != 0 )
				MensajeWin("Rellenar Funcionario ERROR:\n"+oVOF.getError());
			else
			{
				LlenarCampos(oVOF, btnBaja, tfNomFun, tfApefun, tfCI, tfFecNac1, tfFecNac2, tfFecNac3, tfCel, tfHoras, bEstado);
				
			}
		}
		
	}
	//solo rellena
	public void LlenarCampos(VOEmpleado vOFun, JButton btnBaja, JTextField tfNomFun, JTextField tfApefun, JTextField tfCI,
			JTextField tfFecNac1, JTextField tfFecNac2, JTextField tfFecNac3, JTextField tfCel, JTextField tfHoras, 
			JCheckBox bEstado) {
		
		tfNomFun.setText  ( oVOF.getNombre() );
		tfApefun.setText( oVOF.getApellido() );
		tfCI.setText(oVOF.getCi());
		String[] aFecha=oVOF.getFechaNac().split(PaneFuncionario.sSeparadorFecha); //0=año 1=mes 2=dia
//		for (String sfecha : aFecha) {System.out.println(sfecha);} //prueba: ver division de fecha
		tfFecNac1.setText(aFecha[2]);
		tfFecNac2.setText(aFecha[1]);
		tfFecNac3.setText(aFecha[0]);
		tfCel.setText ( oVOF.getCel());
		tfHoras.setText( oVOF.getHorasDia() );
		bEstado.setSelected( oVOF.getBaja() );
		if ( oVOF.getResultado().length() != 0 ) MensajeWin(oVOF.getResultado());
		
		CtrlBtnBaja(btnBaja, oVOF.getBaja());
		
	}
	

	public void CtrlBtnBaja(JButton btn, boolean bEstadoFun){
		btn.setText(bEstadoFun ? "Dar Baja" : "Dar Alta");
		btn.setEnabled(true);
	}
	//opcion  de baja (modifica BD, solo columna de 'Baja')
	public boolean Baja(String sCI,JCheckBox chckBaja){
		//ve si el ingreso no esta vacio
		if(sCI.isEmpty()){MensajeWin("Campo Vacio"); return false;}
		//ahora ve si el formato del ingreso es correcto
		if(!CIValida(sCI)){ MensajeWin("CI no valida");return false; }
		VOEmpleado VO=new VOEmpleado(sCI, chckBaja.isSelected() ? false : true);
		
		if(!gFachada.EstadoFuncionario(VO)){ MensajeWin(VO.getError()); return false;}
		
		chckBaja.setSelected(chckBaja.isSelected() ? false : true);
		MensajeWin(VO.getResultado());
		return true;
	}
	//Verifica datos de la ventana
	public boolean Verificar(String sNomFun, String sApeFun, String sCI, String sFecha, String sCel, String sHoras){
		boolean bNomFun=true, bApeFun=true, bCI=true, bFecha=true, bCel=true, bHoras=true;
		String sErrMensage="Error en:";
		if (sNomFun.length() == 0 ){sErrMensage+="\n-Nombre"; bNomFun=false;}
		if (sApeFun.length() == 0){sErrMensage+="\n-Apellido"; bApeFun=false;}
		if (!CIValida( sCI)){sErrMensage+="\n-Cedula"; bCI=false;}
		if (!FechaValida( sFecha, "-")){sErrMensage+="\n-Fecha"; bFecha=false;}
		if (!IsNumeric(sCel)){sErrMensage+="\n-Celular"; bCel=false;}
		if (!IsNumeric(sHoras)){sErrMensage+="\n-Horas"; bHoras=false;}
		
		if(!bNomFun || !bApeFun || !bCI || !bFecha || !bCel || !bHoras){
			MensajeWin(sErrMensage); return false;}
		
		return true;}//verificar
	
	public void Guardar(String sNomFun, String sApefun, String sCI, String sFecha, String sCel, String sHoras, boolean bActivo){
		String sResultado="";
		
		//Verifica los datos de la ventana
		if(Verificar(sNomFun, sApefun, sCI, sFecha, sCel, sHoras)){
			 oVOF=new VOEmpleado(sNomFun, sApefun, sCI, sFecha, sCel, sHoras, bActivo);
			 //Verifica CI en base de datos
			 //Si no encuentra CI, agrega el funcionario
			 if(!gFachada.VerificarFuncionario(sCI)){
				 if(ConfirmWin("Agregar nuevo funcionario? \n"+sNomFun+" "+sApefun)){
					sResultado=gFachada.AltaFuncionario(oVOF); 
				 }
			//si encuentra ci, modifica el funcionario
		 	//agregar historial de cambios?
			 }else{
				 if(ConfirmWin("Modificar funcionario?\n"+sNomFun+" "+sApefun))
					 sResultado=gFachada.ActualizarFuncionario(oVOF);
			 }
							 
			 if(!sResultado.isEmpty()) MensajeWin(sResultado);
			 
			 
		 }//if verificar			 
	 }//guardar
	
	public void VaciarCampos(JComboBox<String> cbLista, JCheckBox chckActivo, JTextField... fields){
		cbLista.setSelectedIndex(0);
		for(JTextField tf : fields) tf.setText("");
		chckActivo.setSelected(false);
	}
	
}//class
