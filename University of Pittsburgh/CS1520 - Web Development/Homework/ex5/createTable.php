<?php
	//Michael Bowen
	//CS1520 - Mon/Wed 4:30pm
	//
	//Exercise 5
	
	$server = "localhost";
	$user = "bowen";
	$pw = "bowen1234";
	$dbname = "bowen";
	
	//set up connection
	$db = new mysqli($server, $user, $pw, $dbname);
	if($db->connect_error) {
		die("Database connection error: " . $db->connect_error);
	}
	
	//create table
	$makeTable = "CREATE TABLE People (
					LNAME TEXT NOT NULL,
					FNAME TEXT NOT NULL)";
					
	//notify of table creation
	if($db->query($makeTable) == TRUE) {
		echo "Table created!";
	}
	else {
		echo "Table not created. Error: " . $db->error;
	}	
?>