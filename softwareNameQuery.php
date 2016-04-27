
<?php
	$mysql_host = "127.0.0.1";
	$mysql_database = "fsubism_softwareFinder";
	$mysql_user = "fsubism";
	$mysql_password = "fsu_admin";
	
	// Check connection 
	$con = mysqli_connect($mysql_host, $mysql_user, $mysql_password, $mysql_database); 
	
	$result = mysqli_query($con, "SELECT software_name FROM Software");	// Store query results in a resultset
	
	// Print the results of the array with '+++' as a delimeter
	$resultsArray = array();
	while($row = mysqli_fetch_array($result)) {
		$resultsArray[] = $row;
		echo $row[0];
		echo "$";
	}
	// Close the connection
	mysqli_close($con);
?>