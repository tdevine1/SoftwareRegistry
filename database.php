// Template for connecting to the remote database.

<?php

	
	$mysql_host = "johnny.heliohost.org";
	$mysql_database = "fsubism_softwareFinder";
	$mysql_user = "fsubism_dpoling";
	$mysql_password = "fsu-admin";
	
	// Check connection 
	$con=mysqli_connect($mysql_host,$mysql_user,$mysql_password, $mysql_database);
	if (mysqli_connect_errno($con)) { 
	  echo "Failed to connect to MySQL: " . mysqli_connect_error();
	} 
	
	echo "Connected";
	//$username = $_POST['username'];
	//$password = $_POST['password'];
	//$result = mysqli_query($con,"SELECT Role FROM table1 where 
	//Username='$username' and Password='$password'");
	//$result = mysqli_query($con,"SELECT * FROM Software");
	//$row = mysqli_fetch_array($result);
	//$data = $row[0];

	//if($data){
		//echo $data;
	//}
	
	//mysqli_close($con);
?>
