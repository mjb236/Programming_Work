<?php
	//Michael Bowen
	//CS1520 - Mon/Wed 4:30pm
	//
	//Exercise 4
	
	//this script is run upon logout - destroy the session and redirect back to login
	session_start();
	session_destroy();
	header("Location: login.php");
	exit();
?>