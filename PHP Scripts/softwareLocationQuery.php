
<?php
	$mysql_host = "ns139.hosting24.com";
	$mysql_database = "fsusoftw_database";
	$mysql_user = "fsusoftw_user";
	$mysql_password = "fsu-admin";
	$software_name = $_GET['softwareName'];
	$mysql_query = "SELECT l.building, l.room FROM Software s, Location l, Located_in loc WHERE s.software_name = '$software_name' AND s.id_software = loc.soft_id AND loc.loc_id = l.id_location";

	// Check connection 
	$con = mysqli_connect($mysql_host, $mysql_user, $mysql_password, $mysql_database); 
	
	$result = mysqli_query($con, $mysql_query);	// Store query results in a resultset
	
	// Print the results of the array.
	while($row = mysqli_fetch_array($result)) {
		echo $row[0];
		echo "%";
		echo $row[1];
		echo "$";
	}
	// Close the connection
	mysqli_close($con);
?>