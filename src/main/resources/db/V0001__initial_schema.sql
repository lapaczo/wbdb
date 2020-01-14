CREATE TABLE IF NOT EXISTS `connection` (
                                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                            `name` varchar(50) DEFAULT NULL,
                                            `hostname` varchar(50) DEFAULT NULL,
                                            `port` int(11) DEFAULT NULL,
                                            `database_name` varchar(50) DEFAULT NULL,
                                            `username` varchar(50) DEFAULT NULL,
                                            `password` varchar(50) DEFAULT NULL,
                                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;
