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
		return "select * from funcionarios";
	}

	//Consulta Departamentos
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

	//Consultas de Clientes
	public String ListarClientes(){
		return "select * from clientes";
	}
	public String AgregarCliente(){
		return "insert into clientes "
				+ "(idDepto, hsCargables, honorarios, moneda, rut, nroCli, tel, direccion, nomCli) "
				+ "values(?,?,?,?,?,?,?,?,?)";}
	public String BuscarCliente(){
		return "select idDepto, hsCargables, honorarios, moneda, rut, nroCli, tel, direccion, nomCli from clientes where nroCli = ? and idCli = ?";
	}
	public String BajaCliente(){
		return "DELETE FROM clientes WHERE  idCli= ? AND nroCli= ?";
	}
	public String ModificarCliente(){
		return "UPDATE clientes SET "
				+ "rut = ? , "
				+ "nroCli = ? , "
				+ "tel = ? , "
				+ "direccion = ? , "
				+ "idDepto = ? , "
				+ "nomCli = ? , "
				+ "hsCargables = ? , "
				+ "honorarios = ? , "
				+ "moneda = ? "
				+ "WHERE  idCli = ?  AND nroCli = ? ;";
	}
	
	
	//Consulta Servicios
	public String AgregarNServicio(){
		return "insert into servicios (nombre) value(?)";
	}
	public String ListarNServicio(){
		return "select idServ, nombre from servicios";
	}

	//Consulta Horarios
	public String AgregarHorario(){
		return "insert into horasfunc (idFun, idCli, idServ, horas, fecha) value("
				+ "(select fun.idFun from funcionarios fun where fun.ciFun = ? limit 1),"
				+ "(select cli.idCli from clientes cli where cli.nroCli = ? limit 1)," /*ver como obtener la ID de un cliente, el nroCli no deberia ser unico?*/
				+ "(select serv.idServ from servicios serv where serv.nombre = ? limit 1),"
				+ "?,?);";
	}
	
	public String BorrarHorario(){ //Aca ya tenemos los ID!!!
		return "delete from horasfunc where idCli=? and idFun=? and idServ=?";
	}
	
	//Contar Horarios
	//Bits:
	// 1 - pedir por f.idFun
	// 2 - pedir por c.idCli
	// 4 - pedir por s.nombre
	// 8 - pedir por fecha
	public String ContarHorarios( int sqlMode)
	{
		String res = "select count(*) as cantidad from horasfunc hs";
		String where[] = {"","","",""};
		if ( (sqlMode & 0b0001) != 0 )			where[0] = "hs.idFun = ?";
		if ( (sqlMode & 0b0010) != 0 )			where[1] = "hs.idCli = ?";
		if ( (sqlMode & 0b0100) != 0 )			where[2] = "s.nombre = ?";
		if ( (sqlMode & 0b1000) != 0 )			where[3] = "hs.fecha = ?";
		
		//Caso especial, unir con tabla se servicios
		if ( where[2].length() > 0 )
			res = res + " inner join servicios s on hs.idServ = s.idServ";
		
		int k = 0;
		for ( int i=0 ; i<4 ; i++ )
			if ( where[i].length() > 0 )
			{
				if ( k++ == 0 )	res = res + " where ";
				else			res = res + " and ";
				res = res + where[i];
			}
		return res;
	}
	
	//Listar Horarios
	public String ListarHorarios( int sqlMode)
	{
		String res = "select s.nombre as Servicio,"
						+ " concat(f.nomFun,' ',f.apeFun) as Funcionario,"
						+ " concat('[',c.nroCli,'] ',c.nomCli) as Cliente,"
						+ " hs.fecha as Fecha,"
						+ " hs.horas as Horas, "
						+ " hs.idServ as idServ, "
						+ " hs.idCli as idCli, "
						+ " hs.idFun as idFun "
					+ " from horasfunc hs"
						+ " inner join servicios as s on s.idServ = hs.idServ"
						+ " inner join clientes as c on c.idCli = hs.idCli"
						+ " inner join funcionarios as f on f.idFun = hs.idFun ";

		String where[] = {"","","",""};
		if ( (sqlMode & 0b0001) != 0 )			where[0] = "hs.idFun = ?";
		if ( (sqlMode & 0b0010) != 0 )			where[1] = "hs.idCli = ?";
		if ( (sqlMode & 0b0100) != 0 )			where[2] = "s.nombre = ?";
		if ( (sqlMode & 0b1000) != 0 )			where[3] = "hs.fecha = ?";

		int k = 0;
		for ( int i=0 ; i<4 ; i++ )
			if ( where[i].length() > 0 )
			{
				if ( k++ == 0 )	res = res + " where ";
				else			res = res + " and ";
				res = res + where[i];
			}
		res = res + " limit ?, ?";
		return res;
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
