<?php
	$mysql_host = "127.0.0.1";
	$mysql_database = "fsubism_softwareFinder";
	$mysql_user = "fsubism";
	$mysql_password = "fsu_admin";
	$software_name = $_GET['softwareName'];
	$mysql_query = "SELECT building, room FROM Location";

	// Check connection 
	$con = mysqli_connect($mysql_host, $mysql_user, $mysql_password, $mysql_database); 
	
	$result = mysqli_query($con, $mysql_query);	// Store query results in a resultset
	
	// Print the results of the array.
	while($row = mysqli_fetch_array($result)) {
		$resultsArray[] = $row;
		echo $row[0];
		echo "%";
		echo $row[1];
		echo "$";
	}
	// Close the connection
	mysqli_close($con);
?>