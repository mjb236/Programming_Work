<?php
	//Michael Bowen
	//CS1520 - Mon/Wed 4:30pm
	//
	//Exercise 4
	
	session_start();
	//combine user name and pw into one value for comparison
	$user = $_POST["name"];
	$pass = $_POST["pass"];
	$login = $user . ":" . $pass;
	
	//open file and search for user in the file
	$userList = file("users.txt");
	foreach($userList as $val) {
		if(strcmp(trim($login), trim($val)) == 0) {
			//user found - set session variable and redirect to home page
			$_SESSION["name"] = $user;
			header("Location: home.php");
			exit();
		}
	}
	
	//user not found - set session error and redirect to log in
	$_SESSION["error"] =  "Your ID and/or password are incorrect. Try again.";
	header("Location: login.php");
	exit();
?>