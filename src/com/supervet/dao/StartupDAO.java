package com.supervet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StartupDAO {
   
    public void dropDatabase() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        
        PreparedStatement ps = conexao.prepareStatement("DROP TABLE IF EXISTS tb_enderecos");
        ps.execute();        
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS tb_animais");
        ps.execute();         
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS tb_clientes");
        ps.execute();
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS tb_funcionarios");
        ps.execute();
        ps = conexao.prepareStatement("DROP TABLE IF EXISTS tb_pessoas");
        ps.execute();
        
        
    }

    public void createDatabase() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoJDBCFactory.getConexao();
        PreparedStatement ps = conexao.prepareStatement("CREATE SCHEMA IF NOT EXISTS `db_supervet` DEFAULT CHARACTER SET utf8;");
        ps.execute();
        ps = conexao.prepareStatement("USE `db_supervet`;");
        ps.execute();        
        
        ps = conexao.prepareStatement("CREATE TABLE IF NOT EXISTS `db_supervet`.`tb_pessoas` (`ID_pessoa` INT(11) NOT NULL AUTO_INCREMENT, `NOME` VARCHAR(255) NOT NULL, `EMAIL` VARCHAR(100) NOT NULL, `CPF` VARCHAR(255) NOT NULL, `TELEFONE` VARCHAR(255) NOT NULL, PRIMARY KEY (`ID_pessoa`)) ENGINE = InnoDB AUTO_INCREMENT = 4050 DEFAULT CHARACTER SET = utf8;");
        ps.execute();
        
        ps = conexao.prepareStatement("CREATE TABLE IF NOT EXISTS `db_supervet`.`tb_clientes` (`ID_cliente` INT(11) NOT NULL AUTO_INCREMENT, `MATRICULA` VARCHAR(14) NOT NULL, `ID_pessoa` INT(11) NULL DEFAULT NULL, PRIMARY KEY (`ID_cliente`), CONSTRAINT `ID_pessoa` FOREIGN KEY (`ID_pessoa`) REFERENCES `db_supervet`.`tb_pessoas` (`ID_pessoa`)) ENGINE = InnoDB AUTO_INCREMENT = 50 DEFAULT CHARACTER SET = utf8;");
        ps.execute();
        
        ps = conexao.prepareStatement("CREATE TABLE IF NOT EXISTS `db_supervet`.`tb_funcionarios` (`ID_funcionario` INT(11) NOT NULL AUTO_INCREMENT, `SENHA` VARCHAR(45) NOT NULL, `CARGO` VARCHAR(255) NOT NULL, `ID_pessoa` INT(11) NULL DEFAULT NULL, PRIMARY KEY (`ID_funcionario`), CONSTRAINT `ID_pessoa_func` FOREIGN KEY (`ID_pessoa`) REFERENCES `db_supervet`.`tb_pessoas` (`ID_pessoa`)) ENGINE = InnoDB AUTO_INCREMENT = 50 DEFAULT CHARACTER SET = utf8;");
        ps.execute();
        
        ps = conexao.prepareStatement("CREATE TABLE IF NOT EXISTS `db_supervet`.`tb_enderecos` (`ID_endereco` INT(11) NOT NULL AUTO_INCREMENT, `CEP` VARCHAR(255) NOT NULL, `LOGRADOURO` VARCHAR(255) NOT NULL, `COMPLEMENTO` VARCHAR(50) NOT NULL, `BAIRRO` VARCHAR(255) NOT NULL, `ESTADO` VARCHAR(45) NOT NULL, `UF` VARCHAR(2) NOT NULL, `NUMERO` INT(4) NOT NULL, `ID_pessoa` INT(11) NULL, PRIMARY KEY (`ID_endereco`), CONSTRAINT `ID_pessoa_endereco` FOREIGN KEY (`ID_pessoa`) REFERENCES `db_supervet`.`tb_pessoas` (`ID_pessoa`)) ENGINE = InnoDB AUTO_INCREMENT = 50 DEFAULT CHARACTER SET = utf8;");
        ps.execute();
        
        ps = conexao.prepareStatement("CREATE TABLE IF NOT EXISTS `db_supervet`.`tb_animais` (`ID_animal` INT(11) NOT NULL AUTO_INCREMENT, `RACA` VARCHAR(11) NOT NULL , `TIPO` INT(11) NOT NULL, `NOME` VARCHAR(255) NOT NULL, `IDADE` INT(11) NOT NULL, `SEXO` VARCHAR(255) NOT NULL, `PESO` INT(11) NOT NULL, `ID_cliente` INT(11) NULL DEFAULT NULL, PRIMARY KEY (`ID_animal`), CONSTRAINT `ID_cliente` FOREIGN KEY (`ID_cliente`) REFERENCES `db_supervet`.`tb_clientes` (`ID_cliente`)) ENGINE = InnoDB AUTO_INCREMENT = 50 DEFAULT CHARACTER SET = utf8;");
        ps.execute();
    }

}
