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
      `id` int,
      `nombre` varchar(30) NOT NULL,
      `tipoid` varchar(200) DEFAULT NULL,
      `ciudad` varchar(200) DEFAULT NULL,
      `direccion` varchar(200) DEFAULT NULL,
      `telefono` varchar(200) DEFAULT NULL,
      PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `usuario` (
                                             `id` int NOT NULL AUTO_INCREMENT,
                                             `email` varchar(30) NOT NULL,
                                             `firstname` varchar(200),
                                             `lastname` varchar(200),
                                             `password` varchar(200),
                                             `rol` varchar(200),
                                             PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `token` (
                                             `id` int NOT NULL AUTO_INCREMENT,
                                             `expired` varchar(30) NOT NULL,
                                             `revoked` varchar(200) DEFAULT NULL,
                                             `token` varchar(200) DEFAULT NULL,
                                             `token_type` varchar(200) DEFAULT NULL,
                                             `user_id` varchar(200) DEFAULT NULL,
                                             PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `token_seq` (
                                       `next_val` int NOT NULL AUTO_INCREMENT
)ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `usuario_seq` (
    `next_val` int NOT NULL AUTO_INCREMENT
)ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb3;

ALTER TABLE `sec_paciente` ADD CONSTRAINT FK_idpersona FOREIGN KEY (idper) REFERENCES `sec_persona` (id);