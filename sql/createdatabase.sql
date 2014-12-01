CREATE DATABASE hygiea CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE DATABASE hygiea_test CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'hygiea_u'@'%' IDENTIFIED by 'hygiea_p@$sw0rD';
GRANT ALL PRIVILEGES ON hygiea.* TO 'hygiea_u'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON hygiea_test.* TO 'hygiea_u'@'%' WITH GRANT OPTION;
CREATE USER 'hygiea_u'@'localhost' IDENTIFIED by 'hygiea_p@$sw0rD';
GRANT ALL PRIVILEGES ON hygiea.* TO 'hygiea_u'@'localhost' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON hygiea_test.* TO 'hygiea_u'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;