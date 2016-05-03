<?php
	//Michael Bowen
	//CS1520 - Mon/Wed 4:30pm
	//
	//Exercise 4
	
	session_start();
	if(isset($_SESSION["name"])) {
		//user logged in - welcom to site
		$name = $_SESSION["name"];
		display_header();
		echo "<body><h4>Welcome to the site, $name. Please enjoy your stay</h4><br />";
		display_form();
		display_foot();
	}
	else {
		//user has not logged in - set session error and redirect
		$_SESSION["error"] = "You have not logged in. Please log in first.";
		header("Location: login.php");
	}

	//**************************************************************
	//Functions - borrowed idea for modularity of head/form/foot from
	//Matt Bowytz's example files - tailored to this document.
	//**************************************************************
	
	//display the header for the document
	function display_header() {
		?>
		
		<!DOCTYPE html>
		<html>
		<head>
			<title>Welcome!!!</title>
			<link rel="stylesheet" type="text/css" href="style.css">
		</head>
		<?php
	}

	//display the form for logging in
	function display_form() {
		?>
		<form method="POST" action="logout.php">
			<input type="submit" value="Log Out">
		</form>
		<?php
	}

	//close the document
	function display_foot() {
		?>
		</body>
		</html>
		<?php
	}
?>