
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db_supervet
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_supervet
-- -----------------------------------------------------
-- CREATE SCHEMA IF NOT EXISTS `db_supervet` DEFAULT CHARACTER SET utf8 ;
USE `db_supervet` ;

-- -----------------------------------------------------
-- Table `db_supervet`.`tb_pessoas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_supervet`.`tb_pessoas` (`ID_pessoa` INT(11) NOT NULL AUTO_INCREMENT, `NOME` VARCHAR(255) NOT NULL, `EMAIL` VARCHAR(100) NOT NULL, `CPF` VARCHAR(255) NOT NULL, `TELEFONE` VARCHAR(255) NOT NULL, PRIMARY KEY (`ID_pessoa`)) ENGINE = InnoDB AUTO_INCREMENT = 4050 DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `db_supervet`.`tb_clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_supervet`.`tb_clientes` (`ID_cliente` INT(11) NOT NULL AUTO_INCREMENT, `MATRICULA` VARCHAR(14) NOT NULL, `ID_pessoa` INT(11) NULL DEFAULT NULL, PRIMARY KEY (`ID_cliente`), CONSTRAINT `ID_pessoa` FOREIGN KEY (`ID_pessoa`) REFERENCES `db_supervet`.`tb_pessoas` (`ID_pessoa`)) ENGINE = InnoDB AUTO_INCREMENT = 50 DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `db_supervet`.`tb_funcionarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_supervet`.`tb_funcionarios` (`ID_funcionario` INT(11) NOT NULL AUTO_INCREMENT, `SENHA` VARCHAR(45) NOT NULL, `CARGO` VARCHAR(255) NOT NULL, `ID_pessoa` INT(11) NULL DEFAULT NULL, PRIMARY KEY (`ID_funcionario`), CONSTRAINT `ID_pessoa_func` FOREIGN KEY (`ID_pessoa`) REFERENCES `db_supervet`.`tb_pessoas` (`ID_pessoa`)) ENGINE = InnoDB AUTO_INCREMENT = 50 DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `db_supervet`.`tb_enderecos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_supervet`.`tb_enderecos` (`ID_endereco` INT(11) NOT NULL AUTO_INCREMENT, `CEP` VARCHAR(255) NOT NULL, `LOGRADOURO` VARCHAR(255) NOT NULL, `COMPLEMENTO` VARCHAR(50) NOT NULL, `BAIRRO` VARCHAR(255) NOT NULL, `ESTADO` VARCHAR(45) NOT NULL, `UF` VARCHAR(2) NOT NULL, `NUMERO` INT(4) NOT NULL, PRIMARY KEY (`ID_endereco`), CONSTRAINT `ID_pessoa_endereco` FOREIGN KEY (`ID_pessoa`) REFERENCES `db_supervet`.`tb_pessoas` (`ID_pessoa`)) ENGINE = InnoDB AUTO_INCREMENT = 50 DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `db_supervet`.`tb_animais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_supervet`.`tb_animais` (`ID_animal` INT(11) NOT NULL AUTO_INCREMENT, `RACA` VARCHAR(11) NOT NULL , `TIPO` INT(11) NOT NULL, `NOME` VARCHAR(255) NOT NULL, `IDADE` INT(11) NOT NULL, `SEXO` VARCHAR(255) NOT NULL, `PESO` INT(11) NOT NULL, `ID_cliente` INT(11) NULL DEFAULT NULL, PRIMARY KEY (`ID_animal`), CONSTRAINT `ID_cliente` FOREIGN KEY (`ID_cliente`) REFERENCES `db_supervet`.`tb_clientes` (`ID_cliente`)) ENGINE = InnoDB AUTO_INCREMENT = 50 DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `db_supervet`.`tb_consultas` (
	`ID_CONSULTA` INT(11) NOT NULL AUTO_INCREMENT,
    `DATA` VARCHAR(10) NOT NULL,
    `HORA` VARCHAR(5) NOT NULL,
    `TIPO` VARCHAR(100) NOT NULL,
    `STATUS` SMALLINT(1) NOT NULL,
    `ID_ANIMAL` INT(11) NULL DEFAULT NULL,
    `ID_FUNCIONARIO` INT(11) NULL DEFAULT NULL,
    PRIMARY KEY (`ID_CONSULTA`),
    
    CONSTRAINT `ID_ANIMAL_A` FOREIGN KEY (`ID_ANIMAL`) REFERENCES `db_supervet`.`tb_animais` (`ID_ANIMAL`),
    CONSTRAINT `ID_FUNCIONARIO_FU` FOREIGN KEY (`ID_FUNCIONARIO`) REFERENCES `db_supervet`.`tb_funcionarios` (`ID_FUNCIONARIO`)
);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
