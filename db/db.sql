-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema minerva
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema minerva
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `minerva` DEFAULT CHARACTER SET utf8 ;
USE `minerva` ;

-- -----------------------------------------------------
-- Table `minerva`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minerva`.`status` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `status` VARCHAR(45) NOT NULL COMMENT '',
  `criado` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `minerva`.`consultor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minerva`.`consultor` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(45) NOT NULL COMMENT '',
  `criado` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `minerva`.`empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minerva`.`empresa` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(45) NOT NULL COMMENT '',
  `cnpj` VARCHAR(45) NOT NULL COMMENT '',
  `criado` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  `consultor_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_empresa_consultor1_idx` (`consultor_id` ASC)  COMMENT '',
  CONSTRAINT `fk_empresa_consultor1`
    FOREIGN KEY (`consultor_id`)
    REFERENCES `minerva`.`consultor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `minerva`.`atividades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minerva`.`atividades` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `data` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  `area` VARCHAR(45) NOT NULL COMMENT '',
  `programa` VARCHAR(45) NOT NULL COMMENT '',
  `ferramenta` VARCHAR(45) NOT NULL COMMENT '',
  `atividades` TINYTEXT NOT NULL COMMENT '',
  `inicio` TIME NOT NULL COMMENT '',
  `fim` TIME NOT NULL COMMENT '',
  `tempo` TIME NOT NULL COMMENT '',
  `tipo` VARCHAR(45) NOT NULL COMMENT '',
  `status_id` INT(11) NOT NULL COMMENT '',
  `consultor_id` INT(11) NOT NULL COMMENT '',
  `empresa_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_atividades_status_idx` (`status_id` ASC)  COMMENT '',
  INDEX `fk_atividades_consultor1_idx` (`consultor_id` ASC)  COMMENT '',
  INDEX `fk_atividades_empresa1_idx` (`empresa_id` ASC)  COMMENT '',
  CONSTRAINT `fk_atividades_status`
    FOREIGN KEY (`status_id`)
    REFERENCES `minerva`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_atividades_consultor1`
    FOREIGN KEY (`consultor_id`)
    REFERENCES `minerva`.`consultor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_atividades_empresa1`
    FOREIGN KEY (`empresa_id`)
    REFERENCES `minerva`.`empresa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `minerva`.`funcao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minerva`.`funcao` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(45) NOT NULL COMMENT '',
  `criado` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `minerva`.`melhorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minerva`.`melhorias` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `melhorias` TINYTEXT NOT NULL COMMENT '',
  `criado` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  `status_id` INT(11) NOT NULL COMMENT '',
  `consultor_id` INT(11) NOT NULL COMMENT '',
  `empresa_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_melhorias_status1_idx` (`status_id` ASC)  COMMENT '',
  INDEX `fk_melhorias_consultor1_idx` (`consultor_id` ASC)  COMMENT '',
  INDEX `fk_melhorias_empresa1_idx` (`empresa_id` ASC)  COMMENT '',
  CONSTRAINT `fk_melhorias_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `minerva`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_melhorias_consultor1`
    FOREIGN KEY (`consultor_id`)
    REFERENCES `minerva`.`consultor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_melhorias_empresa1`
    FOREIGN KEY (`empresa_id`)
    REFERENCES `minerva`.`empresa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `minerva`.`observacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minerva`.`observacao` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `previsto` VARCHAR(45) NOT NULL COMMENT '',
  `realizado` VARCHAR(45) NOT NULL COMMENT '',
  `criado` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  `status_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_observacao_status1_idx` (`status_id` ASC)  COMMENT '',
  CONSTRAINT `fk_observacao_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `minerva`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `minerva`.`setores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minerva`.`setores` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '	',
  `nome` VARCHAR(45) NOT NULL COMMENT '',
  `criado` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `minerva`.`pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minerva`.`pessoa` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(45) NOT NULL COMMENT '',
  `email` VARCHAR(45) NOT NULL COMMENT '',
  `telefone` VARCHAR(45) NOT NULL COMMENT '',
  `whatsapp` VARCHAR(45) NOT NULL COMMENT '',
  `criado` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  `empresa_id` INT(11) NOT NULL COMMENT '',
  `setores_id` INT(11) NOT NULL COMMENT '',
  `funcao_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_pessoa_empresa1_idx` (`empresa_id` ASC)  COMMENT '',
  INDEX `fk_pessoa_setores1_idx` (`setores_id` ASC)  COMMENT '',
  INDEX `fk_pessoa_funcao1_idx` (`funcao_id` ASC)  COMMENT '',
  CONSTRAINT `fk_pessoa_empresa1`
    FOREIGN KEY (`empresa_id`)
    REFERENCES `minerva`.`empresa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pessoa_setores1`
    FOREIGN KEY (`setores_id`)
    REFERENCES `minerva`.`setores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pessoa_funcao1`
    FOREIGN KEY (`funcao_id`)
    REFERENCES `minerva`.`funcao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `minerva`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `minerva`.`usuario` (
  `id` INT NOT NULL COMMENT '',
  `usuario` VARCHAR(45) NOT NULL COMMENT '',
  `senha` VARCHAR(45) NOT NULL COMMENT '',
  `tipo` VARCHAR(45) NULL COMMENT '',
  `criado` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `tipo_UNIQUE` (`tipo` ASC)  COMMENT '')
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

