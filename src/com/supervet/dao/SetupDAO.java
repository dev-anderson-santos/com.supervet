package com.supervet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

public class SetupDAO {
   
	public boolean checaDatabase() throws SQLException, ClassNotFoundException {
        try {
        	Connection conexao = ConexaoJDBCFactory.getConexaoAux();
            PreparedStatement ps = conexao.prepareStatement("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'db_supervet'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	return true;
            }
        } catch (MySQLSyntaxErrorException e) {
        	throw new MySQLSyntaxErrorException();
		}
        return false;
	}
	
    public void dropTables() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        
        PreparedStatement ps = conexao.prepareStatement("CREATE DATABASE IF NOT EXISTS `db_supervet` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;");
        ps.execute();
        
        ps = conexao.prepareStatement("USE `db_supervet`;");
        ps.execute();
        
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS `tb_animais`");
        ps.execute();        
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS `tb_clientes`");
        ps.execute();         
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS `tb_consultas`");
        ps.execute();
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS `tb_enderecos`");
        ps.execute();
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS `tb_funcionarios`");
        ps.execute();
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS `tb_pessoas`");
        ps.execute();       
    }

    public void createDatabase() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexaoAux();
        
        PreparedStatement ps = conexao.prepareStatement("CREATE DATABASE IF NOT EXISTS `db_supervet` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;");
        ps.execute();
        
        ps = conexao.prepareStatement("USE `db_supervet`;");
        ps.execute();       
        
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS `tb_animais`");
        ps.execute();
        ps = conexao.prepareStatement("CREATE TABLE IF NOT EXISTS `tb_animais` (" + 
        		"  `ID_animal` int(11) NOT NULL AUTO_INCREMENT," + 
        		"  `RACA` varchar(11) NOT NULL," + 
        		"  `TIPO` int(11) NOT NULL," + 
        		"  `NOME` varchar(255) NOT NULL," + 
        		"  `IDADE` int(11) NOT NULL," + 
        		"  `SEXO` varchar(255) NOT NULL," + 
        		"  `PESO` int(11) NOT NULL," + 
        		"  `ID_cliente` int(11) DEFAULT NULL," + 
        		"  PRIMARY KEY (`ID_animal`)," + 
        		"  KEY `ID_cliente` (`ID_cliente`)" + 
        		") ENGINE=InnoDB AUTO_INCREMENT=6060 DEFAULT CHARSET=utf8;");
        ps.execute();
        
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS `tb_clientes`");
        ps.execute(); 
        ps = conexao.prepareStatement("CREATE TABLE IF NOT EXISTS `tb_clientes` (" + 
        		"  `ID_cliente` int(11) NOT NULL AUTO_INCREMENT," + 
        		"  `MATRICULA` varchar(14) NOT NULL," + 
        		"  `ID_pessoa` int(11) DEFAULT NULL," + 
        		"  PRIMARY KEY (`ID_cliente`)," + 
        		"  KEY `ID_pessoa` (`ID_pessoa`)" + 
        		") ENGINE=InnoDB AUTO_INCREMENT=5050 DEFAULT CHARSET=utf8;");
        ps.execute();
        
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS `tb_consultas`");
        ps.execute();
        ps = conexao.prepareStatement("CREATE TABLE IF NOT EXISTS `tb_consultas` (" + 
        		"  `ID_CONSULTA` int(11) NOT NULL AUTO_INCREMENT," + 
        		"  `DATA` varchar(10) NOT NULL," + 
        		"  `HORA` varchar(5) NOT NULL," + 
        		"  `TIPO` varchar(100) NOT NULL," + 
        		"  `STATUS` smallint(1) NOT NULL," + 
        		"  `ID_ANIMAL` int(11) DEFAULT NULL," + 
        		"  `ID_FUNCIONARIO` int(11) DEFAULT NULL," + 
        		"  PRIMARY KEY (`ID_CONSULTA`)," + 
        		"  KEY `ID_ANIMAL_A` (`ID_ANIMAL`)," + 
        		"  KEY `ID_FUNCIONARIO_FU` (`ID_FUNCIONARIO`)" + 
        		") ENGINE=MyISAM AUTO_INCREMENT=4040 DEFAULT CHARSET=utf8;");
        ps.execute();
        
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS `tb_enderecos`");
        ps.execute();
        ps = conexao.prepareStatement("CREATE TABLE IF NOT EXISTS `tb_enderecos` (" + 
        		"  `ID_endereco` int(11) NOT NULL AUTO_INCREMENT," + 
        		"  `CEP` varchar(255) NOT NULL," + 
        		"  `LOGRADOURO` varchar(255) NOT NULL," + 
        		"  `COMPLEMENTO` varchar(50) NOT NULL," + 
        		"  `BAIRRO` varchar(255) NOT NULL," + 
        		"  `CIDADE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL," + 
        		"  `UF` varchar(2) NOT NULL," + 
        		"  `NUMERO` int(4) NOT NULL," + 
        		"  `ID_pessoa` int(11) DEFAULT NULL," + 
        		"  PRIMARY KEY (`ID_endereco`)," + 
        		"  KEY `ID_pessoa_endereco` (`ID_pessoa`)" + 
        		") ENGINE=InnoDB AUTO_INCREMENT=3030 DEFAULT CHARSET=utf8;");
        ps.execute();
        
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS `tb_funcionarios`");
        ps.execute();
        ps = conexao.prepareStatement("CREATE TABLE IF NOT EXISTS `tb_funcionarios` (" + 
        		"  `ID_funcionario` int(11) NOT NULL AUTO_INCREMENT," + 
        		"  `SENHA` varchar(45) NOT NULL," + 
        		"  `CARGO` varchar(255) NOT NULL," + 
        		"  `ID_pessoa` int(11) DEFAULT NULL," + 
        		"  PRIMARY KEY (`ID_funcionario`)," + 
        		"  KEY `ID_pessoa_func` (`ID_pessoa`)" + 
        		") ENGINE=InnoDB AUTO_INCREMENT=2020 DEFAULT CHARSET=utf8;");
        ps.execute();
        
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS `tb_pessoas`");
        ps.execute();
        ps = conexao.prepareStatement("CREATE TABLE IF NOT EXISTS `tb_pessoas` (" + 
        		"  `ID_pessoa` int(11) NOT NULL AUTO_INCREMENT," + 
        		"  `NOME` varchar(255) NOT NULL," + 
        		"  `EMAIL` varchar(100) NOT NULL," + 
        		"  `CPF` varchar(255) NOT NULL," + 
        		"  `TELEFONE` varchar(255) NOT NULL," + 
        		"  PRIMARY KEY (`ID_pessoa`)" + 
        		") ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8;");
        ps.execute();
        
        ps = conexao.prepareStatement("ALTER TABLE `tb_animais` ADD CONSTRAINT `ID_cliente` FOREIGN KEY (`ID_cliente`) REFERENCES `tb_clientes` (`ID_cliente`);");
        ps.execute();
        
        ps = conexao.prepareStatement("ALTER TABLE `tb_clientes` ADD CONSTRAINT `ID_pessoa` FOREIGN KEY (`ID_pessoa`) REFERENCES `tb_pessoas` (`ID_pessoa`);");
        ps.execute();
        
        ps = conexao.prepareStatement("ALTER TABLE `tb_enderecos` ADD CONSTRAINT `ID_pessoa_endereco` FOREIGN KEY (`ID_pessoa`) REFERENCES `tb_pessoas` (`ID_pessoa`);");
        ps.execute();
        
        ps = conexao.prepareStatement("ALTER TABLE `tb_funcionarios` ADD CONSTRAINT `ID_pessoa_func` FOREIGN KEY (`ID_pessoa`) REFERENCES `tb_pessoas` (`ID_pessoa`);");
        ps.execute();
        
        conexao.close();
    }

}
