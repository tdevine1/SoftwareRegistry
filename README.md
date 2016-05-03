# SoftwareRegistry

Application that will search for software at FSU and give its location (building and room number).

A remote database is used for application access.

URL: https://www.hosting24.com/login.php

Login Credentials: 
	email: dpoling6@students.fairmontstate.edu
	password: fsu_admin
	database name: fsusoftw-database (include this in any PHP files)
	cPanel username: fsusoftw
	cPanel password: fsu-admin1

Database user credentials (include these in any PHP files):
	username: fsusoftw_user
	password: fsu-admin

For JDBC connection use:
	URL: jdbc:mysql://ns139.hosting24.com/fsusoftw_database
	username: fsusoftw_user
	password: fsu-admin
