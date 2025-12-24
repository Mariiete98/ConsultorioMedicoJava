# ConsultorioMedicoJava
Consultorio Médico con BBDD local en phpmyadmin (no en remoto)

-- --------------------------------------------------------

-- ACCESO

MÉDICO: usuario: medico, contraseña: medico
ENFERMERÍA: usuario: enfermeria, contraseña: enfermeria
ADMIN: usuario: admin, contraseña: admin

-- --------------------------------------------------------

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-12-2025 a las 18:26:37
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
-- Base de datos: `mcano_medicos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citas`
--

CREATE TABLE `citas` (
  `idCita` int(11) NOT NULL,
  `dniPaciente` varchar(256) DEFAULT NULL,
  `nombre` varchar(256) DEFAULT NULL,
  `dia` date DEFAULT NULL,
  `hora` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `citas`
--

INSERT INTO `citas` (`idCita`, `dniPaciente`, `nombre`, `dia`, `hora`) VALUES
(1, 'JRM2jEjdEyJzbXVE0TjQlA==', 'YGVJ0njsUXLhBoFT6ccp6A==', '2025-12-29', 8.3),
(2, 'JRM2jEjdEyJzbXVE0TjQlA==', 'YGVJ0njsUXLhBoFT6ccp6A==', '2025-12-29', 8),
(3, 'JRM2jEjdEyJzbXVE0TjQlA==', 'YGVJ0njsUXLhBoFT6ccp6A==', '2025-12-24', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citasenfermeria`
--

CREATE TABLE `citasenfermeria` (
  `id` int(11) NOT NULL,
  `dniPaciente` varchar(256) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `dia` date DEFAULT NULL,
  `hora` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `codigospostales`
--

CREATE TABLE `codigospostales` (
  `codigopostal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `codigospostales`
--

INSERT INTO `codigospostales` (`codigopostal`) VALUES
(28001),
(28002),
(28003),
(28004),
(28005);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consultas`
--

CREATE TABLE `consultas` (
  `id` int(11) NOT NULL,
  `dniPaciente` varchar(256) DEFAULT NULL,
  `fechaConsulta` date DEFAULT NULL,
  `diagnostico` text DEFAULT NULL,
  `tratamiento` text DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `codigofacultativo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enfermeria`
--

CREATE TABLE `enfermeria` (
  `id` int(11) NOT NULL,
  `dniPaciente` varchar(256) DEFAULT NULL,
  `fechaConsulta` date DEFAULT NULL,
  `tensionMax` double DEFAULT NULL,
  `tensionMin` double DEFAULT NULL,
  `glucosa` double DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `codigoFacultativo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `dni` varchar(256) NOT NULL,
  `nombre` varchar(256) DEFAULT NULL,
  `apellidos` varchar(256) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `cp` int(11) DEFAULT NULL,
  `sexo` enum('H','M','OTROS') DEFAULT NULL,
  `tabaquismo` enum('SI','NO') DEFAULT NULL,
  `consumoAlcohol` enum('Habitual','Ocasional','Nulo') DEFAULT NULL,
  `antecedentesSalud` text DEFAULT NULL,
  `datosSaludGeneral` text DEFAULT NULL,
  `fechaRegistro` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`dni`, `nombre`, `apellidos`, `fechaNacimiento`, `telefono`, `email`, `cp`, `sexo`, `tabaquismo`, `consumoAlcohol`, `antecedentesSalud`, `datosSaludGeneral`, `fechaRegistro`) VALUES
('JRM2jEjdEyJzbXVE0TjQlA==', 'm0aJjuuJnqJNQCAvha8+Rg==', 't53IrgXCv82l6SkKgtBzLw==', '1998-08-05', 699992877, 'mario@hotmail.com', 28002, 'H', 'SI', 'Habitual', 'OK', 'OK', '2025-12-24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE `personal` (
  `numero_colegiado` int(11) NOT NULL,
  `nombre` varchar(25) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  `usuario` varchar(15) DEFAULT NULL,
  `contrasenya` varchar(255) NOT NULL,
  `tipo` enum('MEDICO','ENFERMERIA','ADMIN') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`numero_colegiado`, `nombre`, `apellidos`, `telefono`, `usuario`, `contrasenya`, `tipo`) VALUES
(111111111, 'MARIETE', 'CANETE', 688882888, 'admin', 'nTpb2eUjbHEjNcl5hpKWzw==', 'ADMIN'),
(122222222, 'JOSE', 'GARCIA', 678876678, 'enfermeria', 'HlhejHBWKHiS8emTbWcQMw==', 'ENFERMERIA'),
(133333333, 'LAURA', 'MARTINEZ', 695596695, 'medico', 'QPdYybBus+vu0jv3lQqK5Q==', 'MEDICO');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `citas`
--
ALTER TABLE `citas`
  ADD PRIMARY KEY (`idCita`),
  ADD KEY `dniPaciente` (`dniPaciente`);

--
-- Indices de la tabla `citasenfermeria`
--
ALTER TABLE `citasenfermeria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dniPaciente` (`dniPaciente`);

--
-- Indices de la tabla `codigospostales`
--
ALTER TABLE `codigospostales`
  ADD PRIMARY KEY (`codigopostal`);

--
-- Indices de la tabla `consultas`
--
ALTER TABLE `consultas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dniPaciente` (`dniPaciente`),
  ADD KEY `consultas_ibfk_2` (`codigofacultativo`);

--
-- Indices de la tabla `enfermeria`
--
ALTER TABLE `enfermeria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dniPaciente` (`dniPaciente`),
  ADD KEY `codigoFacultativo` (`codigoFacultativo`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`dni`),
  ADD KEY `cp` (`cp`);

--
-- Indices de la tabla `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`numero_colegiado`),
  ADD UNIQUE KEY `unique_usuario` (`usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `citas`
--
ALTER TABLE `citas`
  MODIFY `idCita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `citasenfermeria`
--
ALTER TABLE `citasenfermeria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `consultas`
--
ALTER TABLE `consultas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `enfermeria`
--
ALTER TABLE `enfermeria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `citas`
--
ALTER TABLE `citas`
  ADD CONSTRAINT `citas_ibfk_1` FOREIGN KEY (`dniPaciente`) REFERENCES `paciente` (`dni`);

--
-- Filtros para la tabla `citasenfermeria`
--
ALTER TABLE `citasenfermeria`
  ADD CONSTRAINT `citasenfermeria_ibfk_1` FOREIGN KEY (`dniPaciente`) REFERENCES `paciente` (`dni`);

--
-- Filtros para la tabla `consultas`
--
ALTER TABLE `consultas`
  ADD CONSTRAINT `consultas_ibfk_1` FOREIGN KEY (`dniPaciente`) REFERENCES `paciente` (`dni`),
  ADD CONSTRAINT `consultas_ibfk_2` FOREIGN KEY (`codigofacultativo`) REFERENCES `personal` (`numero_colegiado`) ON DELETE CASCADE;

--
-- Filtros para la tabla `enfermeria`
--
ALTER TABLE `enfermeria`
  ADD CONSTRAINT `enfermeria_ibfk_1` FOREIGN KEY (`dniPaciente`) REFERENCES `paciente` (`dni`),
  ADD CONSTRAINT `enfermeria_ibfk_2` FOREIGN KEY (`codigoFacultativo`) REFERENCES `personal` (`numero_colegiado`);

--
-- Filtros para la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD CONSTRAINT `paciente_ibfk_1` FOREIGN KEY (`cp`) REFERENCES `codigospostales` (`codigopostal`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;






