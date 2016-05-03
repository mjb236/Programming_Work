<!-- 	Michael Bowen
			Exercise 6
			
			PHP document displaying the form. Really the only PHP needed is to check
			the POST variable and display output accordingly. The rest is html.
-->

<!doctype html>
<html lang="en">

	<head>
		<meta charset="UTF-8">
		<title>Exercise 6</title>
		<link rel="stylesheet" href="custom.css">
	</head>
	<body>
		<form class="search" action="ex6.php" method="POST">
			<div id="lbl">
				<label class="search_lbl" for="search">Search for a CS course:</label>
			</div>
			<div id="txt">
				<input type="text" class="searchbox" name="search" />
			</div>
			<div id="btn">
				<input type="submit" class="button" value="Search" />
			</div>
			<?php
				if(isset($_POST["search"])) {
					?><div id="out_lbl">
							<label for="output">Submission accepted.</label>
						</div>
					<?php
				}
			?>
		</form>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
		<script src="ex6.js"></script>
	</body>
</html>