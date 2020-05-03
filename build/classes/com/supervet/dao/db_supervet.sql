-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Tempo de geração: 02-Maio-2020 às 18:22
-- Versão do servidor: 8.0.18
-- versão do PHP: 7.4.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `db_supervet`
--
CREATE DATABASE IF NOT EXISTS `db_supervet` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `db_supervet`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_animais`
--

DROP TABLE IF EXISTS `tb_animais`;
CREATE TABLE IF NOT EXISTS `tb_animais` (
  `ID_animal` int(11) NOT NULL AUTO_INCREMENT,
  `RACA` varchar(11) NOT NULL,
  `TIPO` int(11) NOT NULL,
  `NOME` varchar(255) NOT NULL,
  `IDADE` int(11) NOT NULL,
  `SEXO` varchar(255) NOT NULL,
  `PESO` int(11) NOT NULL,
  `ID_cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_animal`),
  KEY `ID_cliente` (`ID_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_clientes`
--

DROP TABLE IF EXISTS `tb_clientes`;
CREATE TABLE IF NOT EXISTS `tb_clientes` (
  `ID_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `MATRICULA` varchar(14) NOT NULL,
  `ID_pessoa` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_cliente`),
  KEY `ID_pessoa` (`ID_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_consultas`
--

DROP TABLE IF EXISTS `tb_consultas`;
CREATE TABLE IF NOT EXISTS `tb_consultas` (
  `ID_CONSULTA` int(11) NOT NULL AUTO_INCREMENT,
  `DATA` varchar(10) NOT NULL,
  `HORA` varchar(5) NOT NULL,
  `TIPO` varchar(100) NOT NULL,
  `STATUS` smallint(1) NOT NULL,
  `ID_ANIMAL` int(11) DEFAULT NULL,
  `ID_FUNCIONARIO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_CONSULTA`),
  KEY `ID_ANIMAL_A` (`ID_ANIMAL`),
  KEY `ID_FUNCIONARIO_FU` (`ID_FUNCIONARIO`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_enderecos`
--

DROP TABLE IF EXISTS `tb_enderecos`;
CREATE TABLE IF NOT EXISTS `tb_enderecos` (
  `ID_endereco` int(11) NOT NULL AUTO_INCREMENT,
  `CEP` varchar(255) NOT NULL,
  `LOGRADOURO` varchar(255) NOT NULL,
  `COMPLEMENTO` varchar(50) NOT NULL,
  `BAIRRO` varchar(255) NOT NULL,
  `CIDADE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UF` varchar(2) NOT NULL,
  `NUMERO` int(4) NOT NULL,
  `ID_pessoa` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_endereco`),
  KEY `ID_pessoa_endereco` (`ID_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_funcionarios`
--

DROP TABLE IF EXISTS `tb_funcionarios`;
CREATE TABLE IF NOT EXISTS `tb_funcionarios` (
  `ID_funcionario` int(11) NOT NULL AUTO_INCREMENT,
  `SENHA` varchar(45) NOT NULL,
  `CARGO` varchar(255) NOT NULL,
  `ID_pessoa` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_funcionario`),
  KEY `ID_pessoa_func` (`ID_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_pessoas`
--

DROP TABLE IF EXISTS `tb_pessoas`;
CREATE TABLE IF NOT EXISTS `tb_pessoas` (
  `ID_pessoa` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(255) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `CPF` varchar(255) NOT NULL,
  `TELEFONE` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=4071 DEFAULT CHARSET=utf8;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `tb_animais`
--
ALTER TABLE `tb_animais`
  ADD CONSTRAINT `ID_cliente` FOREIGN KEY (`ID_cliente`) REFERENCES `tb_clientes` (`ID_cliente`);

--
-- Limitadores para a tabela `tb_clientes`
--
ALTER TABLE `tb_clientes`
  ADD CONSTRAINT `ID_pessoa` FOREIGN KEY (`ID_pessoa`) REFERENCES `tb_pessoas` (`ID_pessoa`);

--
-- Limitadores para a tabela `tb_enderecos`
--
ALTER TABLE `tb_enderecos`
  ADD CONSTRAINT `ID_pessoa_endereco` FOREIGN KEY (`ID_pessoa`) REFERENCES `tb_pessoas` (`ID_pessoa`);

--
-- Limitadores para a tabela `tb_funcionarios`
--
ALTER TABLE `tb_funcionarios`
  ADD CONSTRAINT `ID_pessoa_func` FOREIGN KEY (`ID_pessoa`) REFERENCES `tb_pessoas` (`ID_pessoa`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
