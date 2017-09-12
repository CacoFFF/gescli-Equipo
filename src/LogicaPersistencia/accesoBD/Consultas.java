package LogicaPersistencia.accesoBD;

public class Consultas {
	
	//Consultas de Funcionario
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
		return "UPDATE Funcionarios SET"
				+ " nomFun = ?,"
				+ " apeFun = ?,"
				+ " fechNacFun = ?,"
				+ " celFun = ?,"
				+ " baja = ?,"
				+ " horasDia = ?"
				+ " where ciFun = ? ";
	}
	public String BuscarEmpleadoPorCI(){
		return "select nomFun,apeFun,fechNacFun,celFun,horasDia,baja from funcionarios where ciFun = ? ";
	}
	public String BajaEmpleadoCI(){
		//Baja = 0 >>>> desactivado
		return "UPDATE funcionarios SET baja = ? WHERE  ciFun = ?";
				
	}
	public String ListarFuncionarios(){
		return "select ciFun, CONCAT(nomFun,' ' ,apeFun) as nombre from funcionarios";
	}

	
	//Consultas de Clientes
	public String AgregarDepartamentos(){
		//ver mejor esto
		return "INSERT INTO departamentos (nombre)"
				+ "SELECT * FROM (SELECT ?) AS tmp "
				+ "WHERE NOT EXISTS ("
				+ "SELECT nombre FROM departamentos WHERE nombre = ?)";}
	public String ListarDepartamentos(){
		return "select nombre from departamentos";
	}
	public String BuscarIDDepartamento(){
		return "select idDepto from departamentos where nombre like ?";}
	public String ListarClientes(){
		return "select nroCli, nomCli from clientes";
	}
	public String AgregarCliente(){
		return "insert into clientes "
				+ "(idDepto, hsCargables, honorarios, moneda, rut, nroCli, tel, direccion, nomCli) "
				+ "values(?,?,?,?,?,?,?,?,?)";}
	public String BuscarCliente(){
		return "select idDepto, hsCargables, honorarios, moneda, rut, nroCli, tel, direccion, nomCli from clientes where nroCli = ? ";
	}
	public String AgregarNServicio(){
		return "insert into servicios (nombre) value(?)";
	}
	public String ListarNServicio(){
		return "select idServ, nombre from servicios";
	}

	
	//Creacion de BD
	//Tarea: cargar todo desde las especificaciones
	public String CrearBDatos(String sNombreBDatos){
		return "create database "+sNombreBDatos;
	}
	public String UsarBDatos(String sNombreBDatos){
		return "use "+sNombreBDatos;
	}//es necesario??
	
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
