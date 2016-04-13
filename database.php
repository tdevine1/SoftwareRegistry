<?php

	// Database Connection credentials.
	$mysql_host = "johnny.heliohost.org";
	$mysql_database = "fsubism_softwareFinder";
	$mysql_user = "fsubism_dpoling";
	$mysql_password = "fsu-admin";
	
	// Check connection 
	$con = mysqli_connect($mysql_host,$mysql_user,$mysql_password, $mysql_database);
	
	if (mysqli_connect_errno($con)) { 
	  echo "Failed to connect to MySQL: " . mysqli_connect_error();
	} 
	 
	$result = mysqli_query($con,"SELECT * FROM Software");	// Store query results in a resultset
	
	// Store each row of the resultset into an array
	$resultsArray = array();
	while($row = mysqli_fetch_array($result)) {
        $resultsArray[] = $row;
    }

	// Print each row of the results array to the console and close the connection.
	print_r($resultsArray);	
	mysqli_close($con);
?>
