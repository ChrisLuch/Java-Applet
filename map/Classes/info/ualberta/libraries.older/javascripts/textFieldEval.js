

function check(answer, low, high, rightURL, wrongURL, other, width, height) {	//Written By David Ooms 7/14/98
	
	var popWindow
	var stringOrNumber
	
	if(other) {}
	else 
	{
		other = null
	}

	if(width == null) width = 400
	if(height == null) height = 400	
	
	
	if (isInt(low)) {
	
		low = parseFloat(low)
		high = parseFloat(high)
		answer = parseFloat(answer)				
		

		if (answer > low && answer < high) 
		{
			//openPopWin(rightURL, 400, 400, 'scrollbars,resizable')
			//openWindow('lkl')
			popWindow = window.open(rightURL, "popWin", "width="+width+",height="+height+",scrollbars=yes,resizable=yes")
			
		}
		else 
		{
			//openPopWin(wrongURL, 400, 400, 'scrollbars,resizable')
			//openWindow('kl')
			//popWindow = window.open(wrongURL, 'popWin', 'width='+width+', height=' + height+',scrollbars=yes')
			popWindow = window.open(wrongURL, "popWin", "width="+width+",height="+height+",scrollbars=yes,resizable=yes")
		}
		
	}
	else {		
		answer.toUpperCase
		low.toUpperCase
		high.toUpperCase
		
		if(other) 
		{
			other.toUpperCase
		}

		if (answer == low || answer == high || answer == other) 
		{
			//openPopWin(rightURL, 400, 400, 'scrollbars,resizable')
			popWindow = window.open(rightURL, "popWin", "width="+width+",height="+height+",scrollbars=yes,resizable=yes")
		}
		else 
		{
			//openPopWin(wrongURL, 400, 400, 'scrollbars,resizable')
			popWindow = window.open(wrongURL, "popWin", "width="+width+",height="+height+",scrollbars=yes,resizable=yes")
		}

	}		
		
}


function isInt(whatIs) {
	
	whatIs = whatIs.toString()
	
	isItAnInteger = null
	var whatIsArray	= new Array();

	for(var i = 0; i < whatIs.length; i++) {
		whatIsArray[i] = whatIs.charAt(i)
		}

	for(var i = 0; i < whatIs.length; i++) {
		if (whatIsArray[i] == "1"||whatIsArray[i] == "2"||whatIsArray[i] == "3"||whatIsArray[i] == "4"||whatIsArray[i] == "5"||whatIsArray[i] == "6"||whatIsArray[i] == "7"||whatIsArray[i] == "8"||whatIsArray[i] == "9"||whatIsArray[i] == "0"||whatIsArray[i] == ".") {
			isItAnInteger = true
			}
		else {
			isItAnInteger = false
			break
			}
		}

	
	return isItAnInteger;

	}