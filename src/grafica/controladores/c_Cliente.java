package grafica.controladores;

import javax.swing.*;

public class c_Cliente extends c_Maestro{
	
	
	public void ListaClientes(JComboBox<String> cb){
		//Recibe lista de todos los clientes y muestra... el nombre? en el ComboBox
//		cb.addItem("---Clientes---");
		cb.addItem("");
		cb.addItem("Prueba");
		//agregar commu con fachada
		//select nombre from clientes;
		//cb.add(nombres);
	}
	public void LlenarCampos(String sBuscar){
		MensajeWin(sBuscar);
		
	}//llena campos con lo que saca del cbLista
	
	public void ListaDepto(JComboBox<String> cb){
		/*Leer esto desde Base de datos*/
		String[] asDeptos={"","Artigas", "Canelones", "Cerro Largo", "Colonia", "Durazno", "Flores", "Florida", "Lavalleja", "Maldonado", "Montevideo", "Paysandú", "Río Negro", "Rivera", "Rocha", "Salto", "San José" ,"Soriano" ,"Tacuarembó" ,"Treinta y Tres"};
		for(String sDepto: asDeptos){
			cb.addItem(sDepto);
		}
	}
	public void ListaMonedas(JComboBox<String> cb){
		cb.addItem("");
		cb.addItem("Prueba");
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
	public void Guardar(String sRut, String sNumCli, String sTelefono, String sDireccion, String sNomCli, String sHoras, String sHonorarios, String sDepto, String sMoneda){
		if(Verificar(sRut, sNumCli, sTelefono, sDireccion, sNomCli, sHoras, sHonorarios)){
			//crear ValueObject y mandar a fachada
		}
		
		
	
		
	}

}
