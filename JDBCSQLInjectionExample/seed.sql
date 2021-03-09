CREATE TABLE `Users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `email` varchar(20) NOT NULL DEFAULT '',
  `country` varchar(20) DEFAULT 'USA',
  `password` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

INSERT INTO `Users` (`id`, `name`, `email`, `country`, `password`)
VALUES
	(1, 'Pankaj', 'pankaj@apple.com', 'India', 'pankaj123'),
	(4, 'David', 'david@gmail.com', 'USA', 'david123'),
	(5, 'Raman', 'raman@google.com', 'UK', 'raman123');
