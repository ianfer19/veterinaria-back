CREATE TABLE IF NOT EXISTS `sec_paciente` (
    `id` int NOT NULL AUTO_INCREMENT,
    `nombre` varchar(30) NOT NULL,
    `especie` varchar(150) NOT NULL,
    `raza` varchar(150) NOT NULL,
    `nacimiento` date DEFAULT NULL,
    `tipoidprop` varchar(200) DEFAULT NULL,
    `idprop` int (200) DEFAULT NULL,
    `nombreprop` varchar(200) DEFAULT NULL,
    `ciudad` varchar(200) DEFAULT NULL,
    `direccion` varchar(200) DEFAULT NULL,
    `telefono` varchar(200) DEFAULT NULL,
    `fecharegistro` date DEFAULT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb3;