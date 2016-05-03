<?php
	//Michael Bowen
	//CS1520 - Mon/Wed 4:30pm
	//
	//Exercise 5
	
	if(isset($_POST["lname"])) {			
		//information needed for the database
		$server = "localhost";
		$user = "bowen";
		$pw = "bowen1234";
		$dbname = "bowen";
		
		//get post information
		$lname = $_POST["lname"];
		$fname = $_POST["fname"];
		
		//set up the database
		$db = new mysqli($server, $user, $pw, $dbname);
		if($db->connect_error) {
			die("Database connection error: " . $db->connect_error);
		}
		
		//set up document
		display_header();
		
		//query for the name
		$select = "SELECT LNAME, FNAME
				   FROM People
				   WHERE lname = '$lname' AND fname = '$fname'";
		$result = $db->query($select);
		if($result->num_rows > 0) {
			//name found - print message to user
			echo "<body><div id=message><h3>$lname, $fname was found.</h3></div><br />";			
		}
		else {
			//name not found - insert into database and display table
			$addRow = "INSERT INTO People (LNAME, FNAME)
						VALUES ('$lname', '$fname')";
			$result = $db->query($addRow);
			if($result === TRUE) {
				echo "<body><div id=message><h3>$lname, $fname was not found - added to table.</h3></div><br />";
			}
			else {
				echo "<body><div id=message><h3>Error inserting: $db->error</h3></div><br />";			
			}			
		}
		
		//display rest of document
		display_form();
		display_table($db);
		display_foot();
	}
	else {
		//no query submitted yet - display document
		display_header();
		echo "<body><div id=message><h3>Please query the database for your name.</h3></div><br />";
		display_form();
		display_foot();		
	}
	
	//**************************************************************
	//Functions - borrowed idea for modularity of head/foot from
	//Matt Bowytz's examples.
	//**************************************************************
	
	//display the header for the document
	function display_header() {
		?>		
		<!DOCTYPE html>
		<html>
		<head>
			<title>Query the Database</title>
			<link rel="stylesheet" type="text/css" href="style.css">
		</head>
		<?php
	}

	//display the form for querying the database
	function display_form() {
		?>
		<form method="POST" action="query.php">
			<div>
				<label>Last Name:</label>
				<input type="text" name="lname" placeholder="Last Name">
			</div>
			<div>
				<label>First Name:</label>
				<input type="text" name="fname" placeholder="First Name">
			</div>
			<div>
				<input type="submit" value="Query">
				<input type="reset" value="Clear">
			</div>
		</form>
		<?php
	}
	
	//display a table with the information from the database
	function display_table($db) {
		?>
		<br />
		<br />
		<table>
			<tr>
				<th>Last Name</th>
				<th>First Name</th>
			</tr>
			
		<?php
		//get the data from the database
		$select = "SELECT LNAME, FNAME
					FROM People
					ORDER BY LNAME, FNAME";
		$result = $db->query($select);
				
		//loop through results and place into table
		while($row = $result->fetch_array(MYSQLI_ASSOC)) {
			echo "<tr>\n";
			foreach($row as $data) {
				echo "\t<td>$data</td>\n";
			}
			echo "</tr>\n";
		}
		?>
		</table>
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