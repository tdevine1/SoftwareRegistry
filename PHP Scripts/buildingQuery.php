
<?php
	$mysql_host = "ns139.hosting24.com";
	$mysql_database = "fsusoftw_database";
	$mysql_user = "fsusoftw_user";
	$mysql_password = "fsu-admin";
	$mysql_query = "SELECT DISTINCT building FROM Location";
	
	// Check connection 
	$con = mysqli_connect($mysql_host, $mysql_user, $mysql_password, $mysql_database); 
	
	$result = mysqli_query($con, $mysql_query);	// Store query results in a resultset
	
	// Print the results of the array.
	$resultsArray = array();
	while($row = mysqli_fetch_array($result)) {
		$resultsArray[] = $row;
		echo $row[0];
		echo "$";
	}
	// Close the connection
	mysqli_close($con);
?>