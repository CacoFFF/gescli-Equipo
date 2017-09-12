package grafica.controladores;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import LogicaPersistencia.valueObject.VOCliente;


public class c_Cliente extends c_Maestro{
	
	
	public void ListaClientes(JComboBox<String> cb){
		
		int iViejo = cb.getSelectedIndex();
		
		cb.removeAllItems();
		cb.addItem("--Cliente--");
		ArrayList<String> alCli=new ArrayList<String>();
		alCli=gFachada.ListaCli();
		if(alCli.isEmpty()) return;

		for(String sFun : alCli)
			cb.addItem(sFun);
		
		if ( cb.getItemCount() > iViejo )
			cb.setSelectedIndex( iViejo);
	}
	
	public void BuscarCliente(JTextField tfRut, JTextField tfNumCli, JTextField tfTelefono,
			JTextField tfDireccion, JTextField tfNomCli, JTextField tfHoras, JTextField tfHonorarios,
			JComboBox<String> cbDepartamentos, JComboBox<String> cbMoneda, String... sNumCli){
		
		String sBuscarNumCli="";
		sBuscarNumCli = sNumCli.length > 0 ? sNumCli[0] : null;
		
		voCli=gFachada.ObtenerCliente(sBuscarNumCli);
		
		if ( voCli != null ){
			if ( voCli.getError().length() != 0 ) MensajeWin("Rellenar Cliente ERROR:\n"+voCli.getError());
			else{
				LlenarCampos(voCli, tfRut, tfNumCli, tfTelefono, tfDireccion, tfNomCli, tfHoras, tfHonorarios,
						 cbDepartamentos, cbMoneda); 
			}}}
			
	public void LlenarCampos(VOCliente voCli, JTextField tfRut, JTextField tfNumCli, JTextField tfTelefono,
			JTextField tfDireccion, JTextField tfNomCli, JTextField tfHoras, JTextField tfHonorarios,
			JComboBox<String> cbDepartamentos, JComboBox<String> cbMoneda) {
		
		tfRut.setText  ( voCli.getsRut() );
		tfNumCli.setText( voCli.getsNroCli() );
		tfTelefono.setText(voCli.getsTel());
		tfDireccion.setText ( voCli.getsDireccion());
		tfNomCli.setText( voCli.getsNomCli() );
		tfHoras.setText(""+voCli.getiHrCargables());//jejeje
		tfHonorarios.setText(""+voCli.getiHonorarios());
		cbDepartamentos.setSelectedIndex(voCli.getiIdDepto());
		cbMoneda.setSelectedIndex(voCli.getiMoneda());
		
		
		if ( voCli.getResultado().length() != 0 ) MensajeWin(voCli.getResultado());
		}
	
	public void ListaDepto(JComboBox<String> cb){
		ArrayList<String> llDeptos=new ArrayList<String>();
		cb.addItem(""); //deshabilita btnGuardar, evita algun error
		llDeptos=gFachada.ListaDeptos();
		for(String sDepto: llDeptos){
			cb.addItem(sDepto);
		}
	}
	public void ListaMonedas(JComboBox<String> cb){
		//agregar tipos de moneda? o JTextField?
		String[] asMonedas={"Pesos uy", "dolares", "otros"};
		cb.addItem("");
		for(String str : asMonedas) cb.addItem(str);
	}

	
	public void VaciarCampos(JComboBox<String> cbDeps, JComboBox<String> cbMon, JTextField...textField){
		cbDeps.setSelectedIndex(0);
		cbMon.setSelectedIndex(0);
		for(JTextField tf : textField) tf.setText("");
		
	}
	
	private int IDDepartamento(String sDepartamento){
		VOCliente voCli=new VOCliente(sDepartamento, false);
		gFachada.getIDDepartamento(voCli);
		return voCli.getiIdDepto();
	}
	
	private boolean Verificar(String sRut, String sNumCli, String sTelefono, String sDireccion, String sNomCli, String sHoras, String sHonorarios){
		boolean bRut=true, bNumCli=true, bTelefono=true, bDireccion=true, bNomCli=true, bHoras=true, bHonorarios=true, bDepto=true, bMoneda=true;
		String sErrMensaje="Error en:";
		if(!StringValido(sRut) || !IsNumeric(sRut)){sErrMensaje+="\n-Rut";bRut=false;}
		if(!StringValido(sNumCli) || !IsNumeric(sNumCli)){sErrMensaje+="\n-Numero cliente";bNumCli=false;}
		if(!StringValido(sTelefono) || !IsNumeric(sTelefono)){sErrMensaje+="\n-Telefono";bTelefono=false;}
		if(!StringValido(sDireccion)){sErrMensaje+="\n-Direccion"; bDireccion=false;}
		if(!StringValido(sNomCli)){sErrMensaje+="\n-Nombre cliente"; bNomCli=false;}
		if(!StringValido(sHoras) || !IsNumeric(sHoras)){sErrMensaje+="\n-Horas";bHoras=false;}
		if(!StringValido(sHonorarios) || !IsNumeric(sHonorarios)){sErrMensaje+="\n-Honorarios";bHonorarios=false;}		
		
		if(bRut && bNumCli && bTelefono && bDireccion && bNomCli && bHoras && bHonorarios){
			if(ConfirmWin("Guardar datos?")){return true;}
			else{MensajeWin("Ingreso cancelado"); return false;}
			
		}
		
		MensajeWin(sErrMensaje);
		
		
		return false;
	}
	public void Guardar(String sRut, String sNumCli, String sTelefono, String sDireccion, String sNomCli, String sHoras, String sHonorarios, String sDepto, int iMoneda){
		if(Verificar(sRut, sNumCli, sTelefono, sDireccion, sNomCli, sHoras, sHonorarios)){
			int iDepto=IDDepartamento(sDepto);
			voCli=new VOCliente(iDepto, IntConvertidor(sHoras), IntConvertidor(sHonorarios), iMoneda, sRut, sNumCli, sTelefono, sDireccion, sNomCli);
			MensajeWin(gFachada.AgregarCliente(voCli));
			
		}
		
		
	
		
	}

}
