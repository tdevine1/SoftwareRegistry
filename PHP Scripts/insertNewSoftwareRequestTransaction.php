
<?php
	$mysql_host = "ns139.hosting24.com";
	$mysql_database = "fsusoftw_database";
	$mysql_user = "fsusoftw_user";
	$mysql_password = "fsu-admin";
	$software_name = $_GET['softwareName'];
	$building_name = $_GET['buildingName'];
	$room_number = $_GET['roomNumber'];
	
	$mysql_transaction1 = "INSERT INTO Software (id_software, software_name, approved)
						   SELECT COUNT(*) + 1, '$software_name', 'No'
						   FROM Software;";

	$mysql_transaction2 = "INSERT INTO Located_in (soft_id, loc_id, req_id)
						   SELECT id_software, id_location, (SELECT COUNT(*) FROM Requests) + 1
						   FROM Software, Location
						   WHERE software_name = '$software_name' 
						   AND building = '$building_name' 
						   AND room = '$room_number';";

	$mysql_transaction3 = "INSERT INTO Requests (id_request, request_count)
						   SELECT req_id, 1
						   FROM Software, Location, Located_in
						   WHERE id_software = soft_id AND loc_id = id_location 
						   AND software_name = '$software_name' 
						   AND building = '$building_name' 
						   AND room = '$room_number';";


	// Check connection 
	$con = mysqli_connect($mysql_host, $mysql_user, $mysql_password, $mysql_database); 
	
	$result1 = mysqli_query($con, $mysql_transaction1);	// Store query results in a resultset
	$result2 = mysqli_query($con, $mysql_transaction2);
	$result3 = mysqli_query($con, $mysql_transaction3);
	
	// Close the connection
	mysqli_close($con);
?>