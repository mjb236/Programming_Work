(function($) {
	
	//on submit, disallow submission if string does not match the regEx
	$('.get-data').on("submit", function(e) {
		e.preventDefault();
		
		if(localStorage.getItem('data') == null) {
			//data has not been downloaded yet - get it from the server.
			$.ajax({
				type: 'POST',
				url: 'getData.php',
				data: 'file=file1.txt'
			})
			.done(function(data) {
				//sets the local storage variable with the data received from the server
				localStorage.setItem('data', data);
				$('#output').text(localStorage.getItem('data'));
				$('#output').show();
			});
		}	
		else {
			//data already downloaded - simply toggle the visibility of the output section
			$("#output").toggle();
		}		
	});
	
	
})(jQuery);