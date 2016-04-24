<!-- 
Example PHP query to get all data from all columns in the Software table
on the web server.
 -->

<?php

	// Database Connection credentials.
	$mysql_host = "johnny.heliohost.org";
	$mysql_database = "fsubism_softwareFinder";
	$mysql_user = "fsubism_user";
	$mysql_password = "fsu-admin";
	
	// Check connection 
	$con = mysql_connect($mysql_host, $mysql_user, $mysql_password) or die();
	mysql_select_db($mysql_database); 
	 
	$result = mysql_query("SELECT * FROM Software");	// Store query results in a resultset
	
	// Store each row of the resultset into an array
	$resultsArray = array();
	while($row = mysql_fetch_array($result, MYSQL_BOTH)) {
		$resultsArray[] = $row;
	}

	// Print each row of the results array to the console and close the connection.
	print_r($resultsArray);	
	mysql_close($con);
?>
