<!-- Start //Scripted By David Ooms, July 1998

var answerOn = new Image(100,30)
answerOn.src = "../answerOn.gif"
var answerOff = new Image(100,30)
answerOff.src = "../answerOff.gif"

var radioButtonUp = new Image(13,13)
radioButtonUp.src = "../radioButtonUp.gif"
var radioButtonDown = new Image(13,13)
radioButtonDown.src = "../radioButtonDown.gif"


var answers = new Array();
var radioButtons = new Array();
var imagePlaceHolder
var isRadioButton = null




	

function answerSwap(image) { 	
	var answerNumber = 0
	var radioButtonNumber = 0
	
		
	for(var i = 0; i < document.images.length; i++) {
		var relSource1 = document.images[i].src.substring(document.images[i].src.length - 13, document.images[i].src.length)
		var relSource2 = document.images[i].src.substring(document.images[i].src.length - 19, document.images[i].src.length)
		

		if(relSource1 == "answerOff.gif") { 	//stores all the images[] locations of the answerOff.gifs in a answers[]
			answers[answerNumber] = i 
			answerNumber++
			isRadioButton = false
			}
		if(relSource2 == "radioButtonDown.gif") { 	//stores all the images[] locations of the answerOff.gifs in a answers[]
			radioButtons[radioButtonNumber] = i
			radioButtonNumber++
			isRadioButton = true
			}
		}
	if(isRadioButton) {
		imagePlaceHolder = image		
		document.images[radioButtons[imagePlaceHolder]].src = radioButtonUp.src
		}
	else {
		imagePlaceHolder = image
		document.images[answers[imagePlaceHolder]].src = answerOn.src
		}
	}

function answerSwapBack() { 
	if(isRadioButton) {
		document.images[radioButtons[imagePlaceHolder]].src = radioButtonDown.src
		}
	else {
		document.images[answers[imagePlaceHolder]].src = answerOff.src
		}
	}


// End-->