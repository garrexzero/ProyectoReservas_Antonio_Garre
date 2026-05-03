-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-05-2026 a las 00:13:09
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_reservas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recurso`
--

CREATE TABLE `recurso` (
  `id_recurso` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `capacidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `recurso`
--

INSERT INTO `recurso` (`id_recurso`, `nombre`, `capacidad`) VALUES
(1, 'Sala de Reuniones Principal', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id_recurso` int(11) NOT NULL,
  `id_reserva_local` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `coste` decimal(10,2) DEFAULT 0.00,
  `numero_plazas` int(11) NOT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id_recurso`, `id_reserva_local`, `id_usuario`, `fecha`, `hora_inicio`, `hora_fin`, `coste`, `numero_plazas`, `motivo`, `observaciones`) VALUES
(1, 1, 6, '2026-03-02', '10:00:00', '12:00:00', 0.00, 5, 'Reunión de equipo', 'Ninguna');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre`, `email`) VALUES
(1, 'Antonio', ''),
(6, 'Antonio Garre', 'antonio@test.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `recurso`
--
ALTER TABLE `recurso`
  ADD PRIMARY KEY (`id_recurso`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id_recurso`,`id_reserva_local`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `recurso`
--
ALTER TABLE `recurso`
  MODIFY `id_recurso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_recurso`) REFERENCES `recurso` (`id_recurso`),
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
