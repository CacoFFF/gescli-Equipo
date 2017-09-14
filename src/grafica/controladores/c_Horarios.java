package grafica.controladores;

import LogicaPersistencia.valueObject.VOHorario;

public class c_Horarios extends c_Maestro{
	
	public boolean Verificador(String sServicio, String sCliente, String sFuncionario, String sHoras, String sFecha){
		boolean bServ=true, bCli=true, bFun=true, bHoras=true, bFecha=true;
		String sResultado="Error en: ";
		
		if(!StringValido(sServicio) || sServicio.startsWith("--")) {
			sResultado+="\n- Servicio (Sin seleccionar)"; bServ=false;}		
		if(!StringValido(sCliente) || sCliente.startsWith("--")){
			sResultado+="\n- Cliente (Sin seleccionar)"; bCli=false;}		
		if(!StringValido(sFuncionario) || sFuncionario.startsWith("--")){			
			sResultado+="\n- Funcionario (Sin seleccionar)"; bFun=false;}
		if(!StringValido(sHoras) || !IsNumeric(sHoras)){
			sResultado+="\n- Horas (Ingreso invalido)"; bHoras=false;}
		if(!FechaValida(sFecha, "-")){
			sResultado+="\n- Fecha (Ingreso invalido)"; bFecha=false;}
		
		if(!bServ || !bFun || !bCli || !bHoras || !bFecha){
			MensajeWin(sResultado); return false;
		}
		
		return true;
	}
	
	public void Agregar(String sServicio, String sCliente, String sFuncionario, String sHoras, String sFecha){
		if(Verificador(sServicio, sCliente, sFuncionario, sHoras, sFecha)){
			if(ConfirmWin("Continuar?")){
				int iHoras=IntConvertidor(sHoras);
				VOHorario voHora=new VOHorario(iHoras, sServicio, sCliente, sFuncionario, sFecha);
				String sResultado=gFachada.AgregarHorario(voHora);
				//ver mensaje (no muestra nada)
				MensajeWin(sResultado);
			}
		}
		
		
	}
}
