
<?php
	$mysql_host = "ns139.hosting24.com";
	$mysql_database = "fsusoftw_database";
	$mysql_user = "fsusoftw_user";
	$mysql_password = "fsu-admin";
	$building_name = $_GET['buildingName'];
	
	$mysql_query = "SELECT DISTINCT software_name 
					FROM Software, Location, Located_in 
					WHERE building = '$building_name' 
					AND id_location = loc_id 
					AND soft_id = id_software";

	// Check connection 
	$con = mysqli_connect($mysql_host, $mysql_user, $mysql_password, $mysql_database); 
	
	$result = mysqli_query($con, $mysql_query);	// Store query results in a resultset
	
	// Print the results of the array.
	while($row = mysqli_fetch_array($result)) {
		echo $row[0];
		echo "$";
	}
	// Close the connection
	mysqli_close($con);
?>