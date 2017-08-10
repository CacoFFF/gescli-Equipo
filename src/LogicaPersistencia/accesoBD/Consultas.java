package LogicaPersistencia.accesoBD;

public class Consultas {
	
	public String AgregarEmpleado(){
		return "insert into funcionarios("
				+ "nomFun,"
				+ "apeFun,"
				+ "ciFun,"
				+ "fechNacFun,"
				+ "celFun,"
				+ "baja,"
				+ "horasDia)"
				+ " values (?,?,?,?,?,?,?)";
	}

	public String ActualizarEmpleado(){
		return "update Funcionarios values "
				+ " nomFun = ? "
				+ " apeFun = ? "
				+ " fechNacFun = ? "
				+ " celFun = ? "
				+ " horasDia = ? "
				+ " where ciFun = ? ";
	}
	
	public String BuscarEmpleadoPorCI(){
		return "select * from funcionarios where ciFun = ? ";
	}

	public String BajaEmpleadoCI(){
		//Baja = 0 >>>> desactivado
		return "update funcionarios"
				+ " set baja=? "
				+ " where ciFun=? ";
	}
	
	
	//Tarea: cargar todo desde las especificaciones
	//Tarea: limpiar las especificaciones un poco
	public String CrearBDatos(String sNombreBDatos){
		return "create database "+sNombreBDatos;
	}
	public String UsarBDatos(String sNombreBDatos){
		return "use "+sNombreBDatos;
	}//es necesario??
	
	//ver si anda desde aca
	//cambiar a leer desde especificaciones
	public String CrearTablaCliente(){
		return "create table if not exists clientes("
				+ "idCli int(11) NOT NULL AUTO_INCREMENT,"
				+ " contacto varchar(45) DEFAULT NULL,"
				+ " rut varchar(45) DEFAULT NULL,"
				+ " nroCli varchar(20) NOT NULL DEFAULT '',"
				+ " tel varchar(45) DEFAULT NULL,"
				+ " direccion varchar(100) DEFAULT NULL,"
				+ " idDepto int(11) DEFAULT NULL,"
				+ " nomCli varchar(50) DEFAULT NULL,"
				+ " hsCargables int(10) DEFAULT NULL,"
				+ " honorarios int(10) DEFAULT NULL,"
				+ " moneda int(10) DEFAULT NULL,"
				+ " tipoPersona int(11) DEFAULT NULL,"
				+ " PRIMARY KEY(idCli,nroCli)"
				+ ")";}
	public String CrearTablaDepartamentos(){
		return "CREATE TABLE IF NOT EXISTS departamentos("
				+ "idDepto int(11) NOT NULL AUTO_INCREMENT,"
				+ "nombre varchar(45) DEFAULT NULL,"
				+ "PRIMARY KEY(idDepto))";}
	public String CrearTablaFuncionarios(){
		return "CREATE TABLE IF NOT EXISTS funcionarios("
				+ "idFun int(11) NOT NULL AUTO_INCREMENT, "
				+ "nomFun varchar(45) DEFAULT NULL,"
				+ "apeFun varchar(45) DEFAULT NULL,"
				+ "ciFun varchar(20) NOT NULL,"
				+ "fechNacFun varchar(45) DEFAULT NULL,"
				+ "celFun varchar(100) DEFAULT NULL,"
				+ "baja tinyint(4) DEFAULT NULL,"
				+ "pass varchar(50) DEFAULT NULL,"
				+ "idGrupo varchar(50) DEFAULT NULL,"
				+ "horasDia int(11) DEFAULT NULL,"
				+ "PRIMARY KEY (idFun,ciFun))";}
	public String CrearTablaHorasFunc(){
		return "CREATE TABLE IF NOT EXISTS horasfunc ("
				+ "  idFun int(11) DEFAULT NULL,"
				+ "  idCli int(11) DEFAULT NULL,"
				+ "  idServ int(11) DEFAULT NULL,"
				+ "  horas int(11) DEFAULT NULL,"
				+ "  fecha varchar(45) DEFAULT NULL,"
				+ "  FOREIGN KEY(idFun) REFERENCES funcionarios(idFun),"
				+ "  FOREIGN KEY(idCli) REFERENCES clientes(idCli),"
				+ "  FOREIGN KEY(idServ) REFERENCES servicios(idServ)"
				+ ")";}
	public String CrearTablaServicios(){
		return "CREATE TABLE IF NOT EXISTS servicios ("
				+ "idServ int(11) NOT NULL AUTO_INCREMENT,"
				+ " nombre varchar(45) NOT NULL,"
				+ " PRIMARY KEY (idServ,nombre))";}

}
