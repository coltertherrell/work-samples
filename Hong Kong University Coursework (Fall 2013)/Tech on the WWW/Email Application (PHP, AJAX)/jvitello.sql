-- phpMyAdmin SQL Dump
-- version 2.11.9.2
-- http://www.phpmyadmin.net
--
-- Host: sophia
-- Generation Time: Nov 24, 2013 at 03:02 PM
-- Server version: 5.1.35
-- PHP Version: 5.5.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jvitello`
--

-- --------------------------------------------------------

--
-- Table structure for table `emails`
--

CREATE TABLE IF NOT EXISTS `emails` (
  `emailID` int(11) NOT NULL,
  `sender` varchar(20) NOT NULL,
  `title` varchar(30) NOT NULL,
  `date` date NOT NULL,
  `content` longtext NOT NULL,
  `mailbox` varchar(10) NOT NULL,
  PRIMARY KEY (`emailID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emails`
--

INSERT INTO `emails` (`emailID`, `sender`, `title`, `date`, `content`, `mailbox`) VALUES
(1, 'valerie@hku.hk', 'Class Tuesday', '2013-11-21', 'Hi,\r\nDon''t forget about class Tuesday!', 'important'),
(2, 'bob@hku.hk', 'Lacrosse Next Week', '2013-11-20', 'We will be having a lacrosse game next Friday. See you there', 'inbox'),
(16, 'alex@hku.hk', 'Lunch Tomorrow', '2013-11-14', 'Want to grab lunch tomorrow?', 'important'),
(4, 'josh@hku.hk', 'Bike Ride', '2013-11-19', 'Want to bike Mt. Davis Rd. tomorrow? -Josh', 'important'),
(5, 'naderha@hku.hk', 'Macau', '2013-11-18', 'Have you gotten enough work done to go to Macau next weekend?', 'inbox'),
(6, 'jimmy@hku.hk', 'RE: Music Practice', '2013-11-18', 'Check this out - http://video.bobdylan.com/desktop.html', 'inbox'),
(7, 'sophia@hku.hk', 'Tattoo Shops', '2013-11-17', 'We should go check out tattoo shops tomorrow!', 'inbox'),
(8, 'amy@hku.hk', 'Movie and Dinner', '2013-11-17', 'I had a great time tonight :D', 'inbox'),
(9, 'jimmy@hku.hk', 'Music Practice', '2013-11-15', 'We should get together and jam. When are you free?', 'inbox'),
(10, 'steven@hku.hk', 'Blazers!', '2013-11-14', 'Blazers are off to a great start to the season!', 'inbox'),
(11, 'ian@hku.hk', 'Java IDE', '2013-11-12', 'Hi, I was wondering what your favorite IDE for Java is?', 'inbox'),
(12, 'caleb@hku.hk', 'Greetings from Ireland', '2013-11-10', 'Whats the craic? We gotta grab a guiness when we get home!', 'inbox'),
(13, 'jackson@hku.hk', 'Club Meeting Friday', '2013-11-09', 'Are you going to be able to make the club meeting on Friday?', 'inbox'),
(14, 'valerie@hku.hk', 'Personality Test', '2013-11-05', 'Have you taken the Myers Briggs test? What type are you?', 'inbox'),
(15, 'system@hku.hk', 'Welcome to Sam-Mail!', '2013-11-02', 'Welcome! We hope you enjoy using our service.', 'inbox'),
(17, 'susan@hku.hk', 'Quit Making a Racket at Night', '2013-11-20', 'Would you stop!', 'trash'),
(18, 'jackie@hku.hk', 'Tennis Practice', '2013-11-15', 'Remember to head to Flora Ho on Friday for Practice!', 'inbox'),
(19, 'tom@hku.hk', 'Birthday Party', '2013-11-22', 'Join me in Macau this weekend to celebrate my birthday and to see Manny vs. Rios!', 'inbox');
