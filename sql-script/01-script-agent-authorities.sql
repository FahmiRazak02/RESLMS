USE `afzrealty`;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `agents`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `agents_authorities`;
SET foreign_key_checks = 1;

--
-- Table structure for table `agents`
--

CREATE TABLE `agents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(80) NOT NULL,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `nric` varchar(12) NOT NULL,
  `email` varchar(64) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `agents`
--
-- NOTE: The passwords are encrypted using BCrypt
-- A generation tool is available at: http://www.luv2code.com/generate-bcrypt-password
-- Default passwords here are: fun123
--

INSERT INTO `agents` (`username`, `password`, `first_name`, `last_name`, `nric`, `email`, `enabled`)
VALUES 
('fahmi', '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'fahmi', 'razak', '020122020885', 'fahmirazak0201@gmail.com', 1),
('razmah', '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'razmah', 'majid', '020122090885', 'razmahmajid@gmail.com', 1);

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`role`)
VALUES 
('ROLE_AGENTS'), ('ROLE_ADMIN');

SET foreign_key_checks = 0;

--
-- Table structure for table `agents_authorities`
--

CREATE TABLE `agents_authorities` (
  `agents_id` int(11) NOT NULL,
  `authorities_id` int(11) NOT NULL,
  
  PRIMARY KEY (`agents_id`, `authorities_id`),
  
  KEY `FK_ROLE_idx` (`authorities_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`agents_id`) 
  REFERENCES `agents` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`authorities_id`) 
  REFERENCES `authorities` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET foreign_key_checks = 1;

--
-- Dumping data for table `agents_authorities`
--

INSERT INTO `agents_authorities` (`agents_id`, `authorities_id`)
VALUES 
(1, 1),
(2, 1),
(2, 2);
