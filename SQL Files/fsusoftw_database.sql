-- phpMyAdmin SQL Dump
-- version 4.0.10.14
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: May 10, 2016 at 09:12 PM
-- Server version: 5.6.30
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `fsusoftw_database`
--
CREATE DATABASE IF NOT EXISTS `fsusoftw_database` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `fsusoftw_database`;

-- --------------------------------------------------------

--
-- Table structure for table `Located_in`
--

DROP TABLE IF EXISTS `Located_in`;
CREATE TABLE IF NOT EXISTS `Located_in` (
  `soft_id` int(11) NOT NULL,
  `loc_id` int(11) NOT NULL,
  `req_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`soft_id`,`loc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Located_in`
--

INSERT INTO `Located_in` (`soft_id`, `loc_id`, `req_id`) VALUES
(1, 1, 1),
(1, 2, NULL),
(2, 1, 5),
(2, 2, 6),
(60, 10, NULL),
(3, 6, 2),
(4, 1, NULL),
(4, 2, 3),
(5, 1, NULL),
(5, 2, NULL),
(0, 0, NULL),
(28, 10, NULL),
(6, 3, 7),
(29, 10, NULL),
(60, 11, NULL),
(1, 11, NULL),
(24, 10, NULL),
(24, 9, NULL),
(15, 11, NULL),
(28, 9, NULL),
(29, 9, NULL),
(28, 11, NULL),
(60, 9, NULL),
(24, 11, NULL),
(29, 11, NULL),
(1, 9, NULL),
(1, 10, NULL),
(24, 7, NULL),
(1, 7, NULL),
(28, 7, NULL),
(29, 7, NULL),
(60, 7, NULL),
(51, 7, 8),
(15, 7, NULL),
(1, 8, NULL),
(28, 8, NULL),
(29, 8, NULL),
(60, 8, NULL),
(22, 8, NULL),
(15, 5, NULL),
(1, 5, NULL),
(28, 5, NULL),
(29, 5, NULL),
(60, 5, NULL),
(31, 5, NULL),
(24, 4, NULL),
(1, 4, NULL),
(28, 4, NULL),
(29, 4, NULL),
(32, 4, 9),
(61, 4, NULL),
(21, 4, NULL),
(24, 3, NULL),
(1, 3, NULL),
(28, 3, NULL),
(29, 3, NULL),
(32, 3, 11),
(15, 3, NULL),
(17, 3, 10),
(21, 3, NULL),
(22, 3, NULL),
(55, 3, 12),
(31, 3, NULL),
(8, 3, NULL),
(20, 3, NULL),
(18, 3, NULL),
(19, 3, NULL),
(29, 2, NULL),
(10, 2, NULL),
(62, 2, NULL),
(27, 2, NULL),
(63, 2, NULL),
(64, 2, NULL),
(24, 2, NULL),
(28, 2, NULL),
(31, 2, NULL),
(15, 2, NULL),
(17, 2, 13),
(21, 2, NULL),
(22, 2, NULL),
(34, 2, NULL),
(41, 2, NULL),
(46, 2, NULL),
(47, 2, NULL),
(48, 2, NULL),
(8, 2, NULL),
(44, 2, 16),
(45, 2, 17),
(24, 1, NULL),
(55, 1, NULL),
(27, 1, NULL),
(35, 1, 19),
(42, 1, NULL),
(46, 1, NULL),
(8, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `Location`
--

DROP TABLE IF EXISTS `Location`;
CREATE TABLE IF NOT EXISTS `Location` (
  `id_location` int(11) NOT NULL,
  `building` varchar(45) NOT NULL,
  `room` varchar(45) NOT NULL,
  PRIMARY KEY (`id_location`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Location`
--

INSERT INTO `Location` (`id_location`, `building`, `room`) VALUES
(1, 'Library', '1st floor'),
(2, 'Library', '2nd floor'),
(6, 'Engineering and Technology', '210'),
(5, 'Engineering and Technology', '208'),
(9, 'Falcon Center', '303'),
(0, 'testBuilding', '123'),
(3, 'Library', '3rd floor'),
(4, 'Engineering and Technology', '204'),
(7, 'Engineering and Technology', '212'),
(8, 'Engineering and Technology', '4th floor'),
(11, 'Janes Hall', '118'),
(10, 'Falcon Center', '304');

-- --------------------------------------------------------

--
-- Table structure for table `Requests`
--

DROP TABLE IF EXISTS `Requests`;
CREATE TABLE IF NOT EXISTS `Requests` (
  `id_request` int(11) NOT NULL,
  `request_count` int(11) NOT NULL,
  PRIMARY KEY (`id_request`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Requests`
--

INSERT INTO `Requests` (`id_request`, `request_count`) VALUES
(1, 1),
(2, 6),
(3, 3),
(4, 7),
(5, 3),
(6, 2),
(8, 4),
(7, 1),
(10, 7),
(9, 10),
(11, 5),
(12, 6),
(13, 2),
(14, 4),
(15, 7),
(16, 4),
(17, 99),
(18, 55),
(19, 42),
(0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Software`
--

DROP TABLE IF EXISTS `Software`;
CREATE TABLE IF NOT EXISTS `Software` (
  `id_software` int(11) NOT NULL,
  `software_name` varchar(50) NOT NULL,
  `approved` varchar(30) NOT NULL,
  PRIMARY KEY (`id_software`),
  UNIQUE KEY `id_software` (`id_software`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Software`
--

INSERT INTO `Software` (`id_software`, `software_name`, `approved`) VALUES
(1, 'Adobe Reader', 'Yes'),
(2, 'Visual Studio 2008', 'Yes'),
(3, 'AutoCad', 'Yes'),
(4, 'Microsoft Office 2007', 'Yes'),
(5, 'Microsoft Office 2010', 'Yes'),
(6, 'Cygwin', 'No'),
(0, 'test', 'Yes'),
(8, 'Finale', 'Yes'),
(7, 'Adobe', 'Yes'),
(9, 'Audacity', 'Yes'),
(10, 'GarageBand', 'Yes'),
(11, 'Firefox', 'Yes'),
(12, 'Office 365', 'Yes'),
(13, 'Knovio', 'No'),
(14, 'Bluebutton', 'Yes'),
(15, 'LockDownBrowser', 'Yes'),
(16, 'ExamSoft', 'No'),
(17, 'SketchUp', 'No'),
(18, 'Civil 3D Metric', 'Yes'),
(19, 'Civil 3D imperial', 'Yes'),
(20, 'Revit', 'Yes'),
(21, 'PTC Mathcad Prime', 'Yes'),
(22, 'GeoGebra', 'Yes'),
(23, 'OneDrive', 'Yes'),
(24, '7-Zip', 'Yes'),
(25, 'Adobe Acrobat Reader', 'Yes'),
(26, 'Flash Player', 'Yes'),
(27, 'Adobe Photoshop', 'Yes'),
(28, 'QuickTime', 'Yes'),
(29, 'Microsoft Office 2013', 'Yes'),
(30, 'AutoCAD CS6', 'Yes'),
(31, 'Visual Studio 2013', 'Yes'),
(32, 'Visio 2013', 'No'),
(33, 'Java Plug-in 2013', 'Yes'),
(34, 'VLC Media Player', 'Yes'),
(35, 'Adobe Creative Cloud', 'No'),
(36, 'LoggerPro', 'Yes'),
(37, 'Odyssey', 'No'),
(38, 'Spartan 2.1', 'No'),
(39, 'Spartan PCHEM', 'No'),
(40, 'NetLogo', 'Yes'),
(41, 'DeepView 2', 'Yes'),
(42, 'ChemDraw Ultra', 'Yes'),
(43, 'Inspiration 8', 'Yes'),
(44, 'MathCad 7.6', 'No'),
(45, 'Foldit 13', 'No'),
(46, 'DNRGPS', 'Yes'),
(47, 'Basic Mathematics', 'Yes'),
(48, 'Kuzwell 3000', 'Yes'),
(49, 'Eclipse C++ Mars', 'Yes'),
(50, 'Eclipse Java Mars', 'Yes'),
(51, 'Oracle Virtual Box', 'No'),
(52, 'SEEDUbuntu 12.04', 'Yes'),
(53, 'Source Tree', 'Yes'),
(54, 'Nmap', 'No'),
(55, 'AutoCad 2016', 'No'),
(56, 'Inventer', 'No'),
(57, 'Solid Work', 'No'),
(58, 'Geometer''s Sketchpad', 'Yes'),
(59, 'Desmos', 'Yes'),
(60, 'Visio 2010', 'Yes'),
(61, 'Visual Studio 2012', 'Yes'),
(62, 'Imovie', 'Yes'),
(63, 'Adobe Lightroom', 'Yes'),
(64, 'Creative Cloud', 'Yes'),
(66, 'Microsoft Office 2007', 'No');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
