-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 12, 2019 at 12:25 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `supermarket`
--

-- --------------------------------------------------------

--
-- Table structure for table `artikal`
--

CREATE TABLE `artikal` (
  `sifraArtikla` int(11) NOT NULL,
  `barKodArtikla` varchar(50) DEFAULT NULL,
  `nazivArtikla` varchar(255) NOT NULL,
  `cenaArtikla` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `artikal`
--

INSERT INTO `artikal` (`sifraArtikla`, `barKodArtikla`, `nazivArtikla`, `cenaArtikla`) VALUES
(1, '123456789', 'Cokolada Najlepse Zelje 100gr', 100),
(2, '123456788', 'Cokolada Najlepse zelje 200gr', 200),
(3, '123456879', 'Mleko Moja kravica 1l', 120),
(4, '123456765', 'Jogurt Balans 1l', 100),
(5, '123432345', 'Jogurt Balans 1.5l', 130),
(6, '432123456', 'Jafa', 70),
(7, '654565432', 'Koka kola 1l', 70),
(8, '878767876', 'Fanta 2l', 120),
(9, '343444345', 'Fanta 1l', 70),
(10, '999998765', 'Pivo Jelen 0.5l', 60),
(11, '999998634', 'Pivo Jelen 2l', 180),
(12, '999996755', 'Pivo Lav 0.5l', 80),
(13, '999991232', 'Pivo Tubord 0.33l', 90),
(14, '999994433', 'Pivo Stella 0.5l', 100),
(15, '878934123', 'Sir Gauda 1kg', 550),
(16, '732845923', 'Pomorandze 1kg', 100),
(17, '345612345', 'Sibice', 40),
(18, '345612347', 'Upaljac', 50);

-- --------------------------------------------------------

--
-- Table structure for table `magacin`
--

CREATE TABLE `magacin` (
  `idMagacina` int(11) NOT NULL,
  `nazivMagacina` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `magacin`
--

INSERT INTO `magacin` (`idMagacina`, `nazivMagacina`) VALUES
(1, 'Glavni magacin'),
(2, 'Pomocni magacin 2');

-- --------------------------------------------------------

--
-- Table structure for table `magacinkolicina`
--

CREATE TABLE `magacinkolicina` (
  `idMagacina` int(11) NOT NULL,
  `sifraArtikla` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `magacinkolicina`
--

INSERT INTO `magacinkolicina` (`idMagacina`, `sifraArtikla`, `kolicina`) VALUES
(1, 1, 12),
(1, 2, 9),
(1, 3, 6),
(1, 4, 1),
(1, 5, 4),
(1, 6, 8),
(1, 7, 7),
(1, 8, 1),
(1, 9, 3),
(1, 10, 22),
(1, 11, 23),
(1, 12, 55),
(1, 13, 4),
(1, 14, 5),
(1, 15, 3),
(1, 16, 8),
(1, 17, 10),
(2, 1, 8),
(2, 2, 19),
(2, 9, 9);

-- --------------------------------------------------------

--
-- Table structure for table `racun`
--

CREATE TABLE `racun` (
  `idRacuna` int(11) NOT NULL,
  `sifraArtikla` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL,
  `cenaArtikla` double NOT NULL,
  `idMagacina` int(11) NOT NULL,
  `datum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `racun`
--

INSERT INTO `racun` (`idRacuna`, `sifraArtikla`, `kolicina`, `cenaArtikla`, `idMagacina`, `datum`) VALUES
(1, 4, 1, 100, 1, '2019-07-11'),
(1, 14, 1, 100, 1, '2019-07-11'),
(2, 8, 1, 120, 1, '2019-07-11'),
(3, 8, 1, 120, 1, '2019-07-12');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `artikal`
--
ALTER TABLE `artikal`
  ADD PRIMARY KEY (`sifraArtikla`),
  ADD UNIQUE KEY `barKodArtikla` (`barKodArtikla`),
  ADD KEY `nazivArtikla` (`nazivArtikla`);

--
-- Indexes for table `magacin`
--
ALTER TABLE `magacin`
  ADD PRIMARY KEY (`idMagacina`);

--
-- Indexes for table `magacinkolicina`
--
ALTER TABLE `magacinkolicina`
  ADD PRIMARY KEY (`idMagacina`,`sifraArtikla`),
  ADD KEY `sifraArtiklaFK1` (`sifraArtikla`);

--
-- Indexes for table `racun`
--
ALTER TABLE `racun`
  ADD PRIMARY KEY (`idRacuna`,`sifraArtikla`,`idMagacina`),
  ADD KEY `sifraArtiklaFK` (`sifraArtikla`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `artikal`
--
ALTER TABLE `artikal`
  MODIFY `sifraArtikla` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `magacin`
--
ALTER TABLE `magacin`
  MODIFY `idMagacina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `magacinkolicina`
--
ALTER TABLE `magacinkolicina`
  MODIFY `idMagacina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `racun`
--
ALTER TABLE `racun`
  MODIFY `idRacuna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `magacinkolicina`
--
ALTER TABLE `magacinkolicina`
  ADD CONSTRAINT `idMagacinaFK` FOREIGN KEY (`idMagacina`) REFERENCES `magacin` (`idMagacina`),
  ADD CONSTRAINT `sifraArtiklaFK1` FOREIGN KEY (`sifraArtikla`) REFERENCES `artikal` (`sifraArtikla`);

--
-- Constraints for table `racun`
--
ALTER TABLE `racun`
  ADD CONSTRAINT `sifraArtiklaFK` FOREIGN KEY (`sifraArtikla`) REFERENCES `artikal` (`sifraArtikla`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
