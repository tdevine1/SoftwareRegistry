
<?php
	$mysql_host = "ns139.hosting24.com";
	$mysql_database = "fsusoftw_database";
	$mysql_user = "fsusoftw_user";
	$mysql_password = "fsu-admin";
	$software_name = $_GET['softwareName'];
	$building_name = $_GET['buildingName'];
	$room_number = $_GET['roomNumber'];
	
	$mysql_query = "SELECT req_id
					FROM Software, Located_in, Location
					WHERE id_software = soft_id 
					AND loc_id = id_location 
					AND software_name = '$software_name' 
					AND building = '$building_name' 
					AND room = '$room_number'";

	// Check connection 
	$con = mysqli_connect($mysql_host, $mysql_user, $mysql_password, $mysql_database); 
	
	$result = mysqli_query($con, $mysql_query);	// Store query results in a resultset
	
	// Print the results of the array.
	while($row = mysqli_fetch_array($result)) {
		echo $row[0];
	}
	// Close the connection
	mysqli_close($con);
?>