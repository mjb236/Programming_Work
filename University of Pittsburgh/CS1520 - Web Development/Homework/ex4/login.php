<?php
	//Michael Bowen
	//CS1520 - Mon/Wed 4:30pm
	//
	//Exercise 4
	
	session_start();
	if(isset($_SESSION["error"])) {
		//display error, log in form and destroy session
		display_header();
		$err = $_SESSION["error"];
		echo "<body><h4>Error: $err</h4><br />";
		display_form();
		display_foot();
		session_destroy();
	}
	else {
		//display log in page
		display_header();
		display_form();
		display_foot();	
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
			<title>Exercise 4</title>
			<link rel="stylesheet" type="text/css" href="style.css">
		</head>
		<?php
	}

	//display the form for logging in
	function display_form() {
		?>
		<form method="POST" action="process.php">
			<label>Log In</label>
			<input type="text" name="name" placeholder="Username">
			<input type="password" name="pass" placeholder="Password">
			<input type="submit" value="Log In">
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