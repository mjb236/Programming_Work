(function($) {
	
	$('.get-quiz').on("submit", function(e) {
		e.preventDefault();
		
		$("#form_div").hide();
		
		var xmlReq = new XMLHttpRequest();
		var xmlRes;
		xmlReq.onreadystatechange = function() {
			if(xmlReq.readyState == 4 && xmlReq.status == 200) {
				xmlRes = xmlReq.responseXML;
				
				//add divs to the quiz section for each problem
				var htmlText = "";
				var problems = xmlRes.getElementsByTagName("problem");
				var i, j;
				for(i = 0; i < problems.length; i++) {
					htmlText += '<div class="problem" id="q' + i + '"></div>';
				}
				$("#quiz").html(htmlText);
				
				//add questions to the problem divs
				var questions = xmlRes.getElementsByTagName("question");
				for(i = 0; i < questions.length; i++) {
					var divId = "#q" + i;
					$(divId).html("<h3>" + (i+1) + ". " + questions[i].childNodes[0].nodeValue + "</h3>")
				}
				
				//add the answer selections for each question
				var correctAnswers = xmlRes.getElementsByTagName("correct");
				for(i = 0; i < problems.length; i++) {
					var divId = "#q" + i;
					var selectText = '<select id="select_' + i + '"><option value="default">Please choose an answer.</option>';
					var prob = xmlRes.getElementsByTagName("problem")[i];
					var correct = correctAnswers[i].childNodes[0].nodeValue;
					var ans = prob.firstChild;
					var numChild = prob.childNodes.length;
					
					//loop through the answers in each problem
					for(j = 0; j < numChild - 2; j++) {
						if(ans.nodeType == 1) {
							var ansText = ans.childNodes[0].nodeValue;
							if(ansText != questions[i].childNodes[0].nodeValue) {
								if(correct == ansText) {
									selectText += '<option value="correct">' + ansText + '</option>';
								}
								else {
									selectText += '<option value="incorrect">' + ansText + '</option>';
								}
							}
						}
						ans = ans.nextSibling;
					}	
					selectText += '</select><span></span>';		//span is used for output for this question
					$(divId).append(selectText);
				}
				$("#quiz").show();
			}
			
			//calculate and display answers correct
			$('select').on("change", function() {
				var numCorrect = 0;
				$('select').each(function() {
					
					if($(this).val() == "correct") {
						numCorrect++;
						$(this).closest('div').children('span').html("Correct.");
					}
					else if($(this).val() == "default") {
						$(this).closest('div').children('span').html("");
					}
					else {
						$(this).closest('div').children('span').html("Incorrect.");
					}
				$('#results').html("<h2>You have " + numCorrect + " answer(s) correct.</h2>");
				});
				
				$('#results').show();
			});
			
		}; //ends onreadstatechange function
		
		xmlReq.open("GET", "data.xml", true);
		xmlReq.send();
	});
})(jQuery);