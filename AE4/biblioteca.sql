-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.21-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para biblioteca
CREATE DATABASE IF NOT EXISTS `biblioteca` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `biblioteca`;

-- Volcando estructura para tabla biblioteca.llibres
CREATE TABLE IF NOT EXISTS `llibres` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titol` varchar(100) NOT NULL,
  `autor` varchar(100) NOT NULL,
  `any_naixement` varchar(4) NOT NULL,
  `any_publicacio` varchar(4) NOT NULL,
  `editorial` varchar(100) NOT NULL,
  `num_pags` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla biblioteca.llibres: ~64 rows (aproximadamente)
/*!40000 ALTER TABLE `llibres` DISABLE KEYS */;
INSERT INTO `llibres` (`id`, `titol`, `autor`, `any_naixement`, `any_publicacio`, `editorial`, `num_pags`) VALUES
	(1, 'El señor de los anillos', 'J.R.R. Tolkien', '1890', '1950', 'Minotauro', '1392'),
	(2, 'El juego de Ender', 'Orson Scott Card', '1951', '1977', 'Ediciones B', '509'),
	(3, 'Lazarillo de Tormes', 'Anónimo', 'N.C', '1554', 'Clásicos Populares', '150'),
	(4, 'Las uvas de la ira', 'John Steinbeck', '1902', '1939', 'Alianza', '619'),
	(5, 'Watchmen', 'Alan Moore', '1953', '1980', 'ECC', '416'),
	(6, 'La hoguera de las vanidades', 'Tom Wolfe', '1930', '1980', 'Anagrama', '636'),
	(7, 'La familia de Pascual Duarte', 'Camilo José Cela', '1916', '1942', 'Destino', '165'),
	(8, 'El señor de las moscas', 'William Golding', '1911', '1972', 'Alianza', '236'),
	(9, 'La ciudad de los prodigios', 'Eduardo Mendoza', '1943', '1986', 'Seix Barral', '541'),
	(10, 'Ensayo sobre la ceguera', 'José Saramago', '1922', '1995', 'Santillana', '439'),
	(11, 'Los surcos del azar', 'Paco Roca', '1969', '2013', 'Astiberri', '349'),
	(12, 'Ghosts of Spain', 'Giles Tremlett', '1962', '2006', 'Faber & Faber', '468'),
	(13, 'Sidi', 'Arturo Pérez Reverte', '1951', '2019', 'Penguin', '369'),
	(14, 'Dune', 'Frank Herbert', '1920', '1965', 'Acervo', '741');
/*!40000 ALTER TABLE `llibres` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
