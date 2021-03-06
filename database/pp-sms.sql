-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 22, 2020 at 12:11 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.24-0ubuntu0.18.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pp-sms`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE DATABASE `pp-sms`;
USE `pp-sms`;

CREATE USER 'springuser'@'%' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON `pp-sms`.* TO 'springuser'@'%';


CREATE TABLE `users` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--
-- Table structure for table `countries`
--

create table countries
(
    id         int auto_increment
        primary key,
    name       text                                not null,
    updated_at timestamp default CURRENT_TIMESTAMP not null
);


create table academic_years
(
	id int auto_increment primary key,
	name varchar(30) not null,
	updated_at timestamp default CURRENT_TIMESTAMP not null
);

