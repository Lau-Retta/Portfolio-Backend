-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema diagramaLógico
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema diagramaLógico
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `diagramaLógico` DEFAULT CHARACTER SET utf8 ;
USE `diagramaLógico` ;

-- -----------------------------------------------------
-- Table `diagramaLógico`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diagramaLógico`.`Usuarios` (
  `idUsuarios` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(80) NOT NULL,
  `Email` VARCHAR(100) NOT NULL,
  `Contraseña` VARCHAR(45) NOT NULL,
  `Usuarioscol` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`idUsuarios`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diagramaLógico`.`Perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diagramaLógico`.`Perfil` (
  `Nombre` INT NOT NULL,
  `Foto_perfil` VARCHAR(45) NOT NULL,
  `Infografia` VARCHAR(255) NOT NULL,
  `Usuarios_idUsuarios` INT NOT NULL,
  INDEX `fk_Perfil_Usuarios_idx` (`Usuarios_idUsuarios` ASC) VISIBLE,
  CONSTRAINT `fk_Perfil_Usuarios`
    FOREIGN KEY (`Usuarios_idUsuarios`)
    REFERENCES `diagramaLógico`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diagramaLógico`.`Trabajos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diagramaLógico`.`Trabajos` (
  `idTrabajos` INT NOT NULL AUTO_INCREMENT,
  `Empresa` VARCHAR(45) NOT NULL,
  `Foto_empresa` VARCHAR(45) NOT NULL,
  `Descripción_tareas` VARCHAR(3000) NOT NULL,
  `Fecha_inicio` DATE NOT NULL,
  `Fecha_finalización` VARCHAR(45) NULL,
  PRIMARY KEY (`idTrabajos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diagramaLógico`.`Estudios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diagramaLógico`.`Estudios` (
  `idEstudios` INT NOT NULL AUTO_INCREMENT,
  `Escuela` VARCHAR(45) NOT NULL,
  `Titulo` VARCHAR(45) NOT NULL,
  `Fecha_inicio` DATE NOT NULL,
  `Fechas_finalización` VARCHAR(45) NULL,
  PRIMARY KEY (`idEstudios`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diagramaLógico`.`Cursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diagramaLógico`.`Cursos` (
  `idCursos` INT NOT NULL AUTO_INCREMENT,
  `Foto_certiifcado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCursos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diagramaLógico`.`Hbilidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diagramaLógico`.`Hbilidades` (
  `idHbilidades` INT NOT NULL AUTO_INCREMENT,
  `Hanbilidad` VARCHAR(45) NOT NULL,
  `Porcentaje` INT NOT NULL,
  PRIMARY KEY (`idHbilidades`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diagramaLógico`.`Proyectos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diagramaLógico`.`Proyectos` (
  `idProyectos` INT NOT NULL AUTO_INCREMENT,
  `Nombre_del_proyecto` VARCHAR(45) NOT NULL,
  `URL` VARCHAR(1000) NOT NULL,
  `Foto_portada` VARCHAR(45) NOT NULL,
  `Proyectoscol` VARCHAR(45) NOT NULL,
  `Descripción` VARCHAR(3000) NULL,
  PRIMARY KEY (`idProyectos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diagramaLógico`.`Estudio en`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diagramaLógico`.`Estudio en` (
  `Usuarios_idUsuarios` INT NOT NULL,
  `Estudios_idEstudios` INT NOT NULL,
  PRIMARY KEY (`Usuarios_idUsuarios`, `Estudios_idEstudios`),
  INDEX `fk_Usuarios_has_Estudios_Estudios1_idx` (`Estudios_idEstudios` ASC) VISIBLE,
  INDEX `fk_Usuarios_has_Estudios_Usuarios1_idx` (`Usuarios_idUsuarios` ASC) VISIBLE,
  CONSTRAINT `fk_Usuarios_has_Estudios_Usuarios1`
    FOREIGN KEY (`Usuarios_idUsuarios`)
    REFERENCES `diagramaLógico`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuarios_has_Estudios_Estudios1`
    FOREIGN KEY (`Estudios_idEstudios`)
    REFERENCES `diagramaLógico`.`Estudios` (`idEstudios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diagramaLógico`.`Realizo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diagramaLógico`.`Realizo` (
  `Cursos_idCursos` INT NOT NULL,
  `Usuarios_idUsuarios` INT NOT NULL,
  PRIMARY KEY (`Cursos_idCursos`, `Usuarios_idUsuarios`),
  INDEX `fk_Cursos_has_Usuarios_Usuarios1_idx` (`Usuarios_idUsuarios` ASC) VISIBLE,
  INDEX `fk_Cursos_has_Usuarios_Cursos1_idx` (`Cursos_idCursos` ASC) VISIBLE,
  CONSTRAINT `fk_Cursos_has_Usuarios_Cursos1`
    FOREIGN KEY (`Cursos_idCursos`)
    REFERENCES `diagramaLógico`.`Cursos` (`idCursos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cursos_has_Usuarios_Usuarios1`
    FOREIGN KEY (`Usuarios_idUsuarios`)
    REFERENCES `diagramaLógico`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diagramaLógico`.`Tiene`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diagramaLógico`.`Tiene` (
  `Hbilidades_idHbilidades` INT NOT NULL,
  `Usuarios_idUsuarios` INT NOT NULL,
  PRIMARY KEY (`Hbilidades_idHbilidades`, `Usuarios_idUsuarios`),
  INDEX `fk_Hbilidades_has_Usuarios_Usuarios1_idx` (`Usuarios_idUsuarios` ASC) VISIBLE,
  INDEX `fk_Hbilidades_has_Usuarios_Hbilidades1_idx` (`Hbilidades_idHbilidades` ASC) VISIBLE,
  CONSTRAINT `fk_Hbilidades_has_Usuarios_Hbilidades1`
    FOREIGN KEY (`Hbilidades_idHbilidades`)
    REFERENCES `diagramaLógico`.`Hbilidades` (`idHbilidades`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Hbilidades_has_Usuarios_Usuarios1`
    FOREIGN KEY (`Usuarios_idUsuarios`)
    REFERENCES `diagramaLógico`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diagramaLógico`.`Hizo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diagramaLógico`.`Hizo` (
  `Usuarios_idUsuarios` INT NOT NULL,
  `Proyectos_idProyectos` INT NOT NULL,
  PRIMARY KEY (`Usuarios_idUsuarios`, `Proyectos_idProyectos`),
  INDEX `fk_Usuarios_has_Proyectos_Proyectos1_idx` (`Proyectos_idProyectos` ASC) VISIBLE,
  INDEX `fk_Usuarios_has_Proyectos_Usuarios1_idx` (`Usuarios_idUsuarios` ASC) VISIBLE,
  CONSTRAINT `fk_Usuarios_has_Proyectos_Usuarios1`
    FOREIGN KEY (`Usuarios_idUsuarios`)
    REFERENCES `diagramaLógico`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuarios_has_Proyectos_Proyectos1`
    FOREIGN KEY (`Proyectos_idProyectos`)
    REFERENCES `diagramaLógico`.`Proyectos` (`idProyectos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diagramaLógico`.`Trabajo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diagramaLógico`.`Trabajo` (
  `Usuarios_idUsuarios` INT NOT NULL,
  `Trabajos_idTrabajos` INT NOT NULL,
  PRIMARY KEY (`Usuarios_idUsuarios`, `Trabajos_idTrabajos`),
  INDEX `fk_Usuarios_has_Trabajos_Trabajos1_idx` (`Trabajos_idTrabajos` ASC) VISIBLE,
  INDEX `fk_Usuarios_has_Trabajos_Usuarios1_idx` (`Usuarios_idUsuarios` ASC) VISIBLE,
  CONSTRAINT `fk_Usuarios_has_Trabajos_Usuarios1`
    FOREIGN KEY (`Usuarios_idUsuarios`)
    REFERENCES `diagramaLógico`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuarios_has_Trabajos_Trabajos1`
    FOREIGN KEY (`Trabajos_idTrabajos`)
    REFERENCES `diagramaLógico`.`Trabajos` (`idTrabajos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
