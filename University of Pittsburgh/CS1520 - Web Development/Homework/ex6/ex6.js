(function($) {
	
	//regular expression matching the required input
	var regEx = new RegExp('[Cc][Ss][0-3][0-9]{3}');
	
	//on submit, disallow submission if string does not match the regEx
	$('.search').on("submit", function(e) {
		if(!regEx.test($('.searchbox').val())) {
			$('#out_lbl').remove();
			alert("Search string is invalid.");
			$('.searchbox').focus();
			return false;
		}
	});
	
	
})(jQuery);