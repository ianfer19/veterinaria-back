CREATE TABLE IF NOT EXISTS `sec_paciente` (
    `id` int NOT NULL AUTO_INCREMENT,
    `nombre` varchar(30) NOT NULL,
    `especie` varchar(150) NOT NULL,
    `raza` varchar(150) NOT NULL,
    `nacimiento` date DEFAULT NULL,
    `idper` int (200) DEFAULT NULL,
    `fecharegistro` date DEFAULT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `sec_persona` (
       `id` int NOT NULL AUTO_INCREMENT,
      `nombre` varchar(30) NOT NULL,
      `identificacion` varchar(30) NOT NULL,
      `tipoid` varchar(200) DEFAULT NULL,
      `ciudad` varchar(200) DEFAULT NULL,
      `direccion` varchar(200) DEFAULT NULL,
      `telefono` varchar(200) DEFAULT NULL,
      PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb3;

ALTER TABLE `sec_paciente` ADD CONSTRAINT FK_idpersona FOREIGN KEY (idper) REFERENCES `sec_persona` (identificacion);