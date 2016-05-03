(function($) {
	var words = [];
	var counts = [];	
	
	$('.get-word').on("submit", function(e) {
		e.preventDefault();

		getWord();		
	});
	
	function getWord() {
		var httpRequest = new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {
			if(httpRequest.readyState == 4 && httpRequest.status == 200) {
				var response = httpRequest.responseXML;
				var word = response.getElementsByTagName("value")[0].childNodes[0].nodeValue; //word received from getWords
				var i = 0;
				var found = false;
				var tableHTML = "<table><tr><th>Word</th><th>Count</th></tr>";
				
				//search for the word - if it is not found, add to the array. otherwise just update the count
				while(!found && (i < words.length)) {
					if(words[i] == word) {
						counts[i]++;
						found = true;
					}
					i++;
				}
				
				if(!found) {
					words.push(word);
					counts.push(1);
				}

				//loop through results, update the tableHTML and output to the screen.
				for(i = 0; i < words.length; i++) {
					tableHTML += "<tr><td>" + words[i] + "</td><td>" + counts[i] + "</td></tr>";
 				}
				tableHTML += "</table>";
				
				$('#results').html(tableHTML);
			}
		};
		
		httpRequest.open("GET", "getwords.php", true);
		httpRequest.send();		
	}
})(jQuery);