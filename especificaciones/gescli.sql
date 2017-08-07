Especificaciones
-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.5.56 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


CREATE DATABASE IF NOT EXISTS `gescli` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gescli`;

CREATE TABLE IF NOT EXISTS `clientes` (
  `idCli` int(11) NOT NULL AUTO_INCREMENT,
  `contacto` varchar(45) DEFAULT NULL,
  `rut` varchar(45) DEFAULT NULL,
  `nroCli` varchar(20) NOT NULL DEFAULT '',
  `tel` varchar(45) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `idDepto` int(11) DEFAULT NULL,
  `nomCli` varchar(50) DEFAULT NULL,
  `hsCargables` int(10) DEFAULT NULL,
  `honorarios` int(10) DEFAULT NULL,
  `moneda` int(10) DEFAULT NULL,
  `tipoPersona` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCli`,`nroCli`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla gescli.departamentos
CREATE TABLE IF NOT EXISTS `departamentos` (
  `idDepto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idDepto`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `funcionarios` (
  `idFun` int(11) NOT NULL AUTO_INCREMENT,
  `nomFun` varchar(45) DEFAULT NULL,
  `apeFun` varchar(45) DEFAULT NULL,
  `ciFun` varchar(20) NOT NULL DEFAULT '',
  `fechNacFun` varchar(45) DEFAULT NULL,
  `celFun` varchar(100) DEFAULT NULL,
  `baja` tinyint(4) DEFAULT NULL,
  `pass` varchar(50) DEFAULT NULL,
  `idGrupo` varchar(50) DEFAULT NULL,
  `horasDia` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFun`,`ciFun`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `horasfunc` (
  `idFun` int(11) DEFAULT NULL,
  `idCli` int(11) DEFAULT NULL,
  `idServ` int(11) DEFAULT NULL,
  `horas` int(11) DEFAULT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  KEY `idFun` (`idFun`),
  KEY `idCli` (`idCli`),
  KEY `idServ` (`idServ`),
  CONSTRAINT `horasfunc_ibfk_1` FOREIGN KEY (`idFun`) REFERENCES `funcionarios` (`idFun`),
  CONSTRAINT `horasfunc_ibfk_2` FOREIGN KEY (`idCli`) REFERENCES `clientes` (`idCli`),
  CONSTRAINT `horasfunc_ibfk_3` FOREIGN KEY (`idServ`) REFERENCES `servicios` (`idServ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `servicios` (
  `idServ` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`idServ`,`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
