
<?php
	$mysql_host = "ns139.hosting24.com";
	$mysql_database = "fsusoftw_database";
	$mysql_user = "fsusoftw_user";
	$mysql_password = "fsu-admin";
	$software_name = $_GET['softwareName'];
	$building_name = $_GET['buildingName'];
	$room_number = $_GET['roomNumber'];
	
	$mysql_transaction = "UPDATE Software, Location, Located_in, Requests
						  SET request_count = request_count + 1
						  WHERE id_software = soft_id AND loc_id = id_location AND req_id = id_request
						  AND software_name = '$software_name' 
						  AND building = '$building_name' 
						  AND room = '$room_number';";

	// Check connection 
	$con = mysqli_connect($mysql_host, $mysql_user, $mysql_password, $mysql_database); 
	
	mysqli_query($con, $mysql_transaction);
	
	// Close the connection
	mysqli_close($con);
?>