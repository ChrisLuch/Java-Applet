function evaluateNumber(answer, low, high, rightUrl, rightOptions, wrongUrl, wrongOptions)
{
	low = parseFloat(low)
	high = parseFloat(high)
	answer = parseFloat(answer)				

  var blnWrongPopwin = false;
  var blnRightPopwin = false;

  var blnTargetParentWrong = false;
  var blnTargetParentRight = false;

  var widthWrong = 400;
  var widthRight = 400;

  var heightWrong = 400;
  var heightRight = 400;

  var space = / /

  var rightOps = new String(rightOptions)
  var wrongOps = new String(wrongOptions)

  rightOps.replace(space, "")
  rightOps.toLowerCase();
  wrongOps.replace(space, "")
  wrongOps.toLowerCase();

  var rOptions = new Array()
  rOptions = rightOps.split(",")

  var wOptions = new Array()
  wOptions = wrongOps.split(",")

  for(var i =0; i < rOptions.length; i++)
  {
    if(rOptions[i].indexOf("popwin") != -1)
    {
      if(rOptions[i].indexOf("true") != -1)
        blnRightPopwin = true;
      else
        blnRightPopwin = false;
    }

    if(rOptions[i].indexOf("parent") != -1)
    {
      if(rOptions[i].indexOf("true") != -1)
        blnTargetParentRight = true;
      else
        blnTargetParentRight = false;
    }
    
    if(rOptions[i].indexOf("width") != -1)
    {
      var startIndex = rOptions[i].indexOf('=')+1
      var endIndex = rOptions[i].length
      widthRight = parseInt(rOptions[i].substring(startIndex, endIndex))
    }

    if(rOptions[i].indexOf("height") != -1)
    {
      var startIndex = rOptions[i].indexOf('=')+1
      var endIndex = rOptions[i].length
      heightRight = parseInt(rOptions[i].substring(startIndex, endIndex))
    }
  }

  for(var i =0; i < wOptions.length; i++)
  {
    if(wOptions[i].indexOf("popwin") != -1)
    {
      if(wOptions[i].indexOf("true") != -1)
        blnWrongPopwin = true;
      else
        blnWrongPopwin = false;
    }

    if(wOptions[i].indexOf("parent") != -1)
    {
      if(wOptions[i].indexOf("true") != -1)
        blnTargetParentWrong = true;
      else
        blnTargetParentWrong = false;
    }
    
    if(wOptions[i].indexOf("width") != -1)
    {      
      var startIndex = wOptions[i].indexOf('=')+1
      var endIndex = wOptions[i].length
      widthWrong = parseInt(wOptions[i].substring(startIndex, endIndex))
    }

    if(wOptions[i].indexOf("height") != -1)
    {
      var startIndex = wOptions[i].indexOf('=')+1
      var endIndex = wOptions[i].length
      heightWrong = parseInt(wOptions[i].substring(startIndex, endIndex))
    }
  }



	if(answer >= low && answer <= high)
	{
    if(blnRightPopwin)
    {
			window.open(rightUrl, "popWin", "width="+widthRight+",height="+heightRight+",scrollbars=yes,resizable=yes")
    }
    else
    {
	    document.open()
		  if(blnTargetParentRight) parent.document.location = rightUrl
      else document.location = rightUrl
	    document.close()
    }
	}
	else 
	{
    if(blnWrongPopwin)
    {     
			window.open(wrongUrl, "popWin", "width="+widthWrong+",height="+heightWrong+",scrollbars=yes,resizable=yes")
    }
    else
    {       
	    document.open()
		  if(blnTargetParentWrong) parent.document.location = wrongUrl
      else document.location = wrongUrl    
	    document.close()
    }
	}

}

function evaluateString(answer, correctAnswers, caseSensitive, rightUrl, rightOptions, wrongUrl, wrongOptions)
{	

  var blnWrongPopwin = false;
  var blnRightPopwin = false;

  var blnTargetParentWrong = false;
  var blnTargetParentRight = false;

  var widthWrong = 400;
  var widthRight = 400;

  var heightWrong = 400;
  var heightRight = 400;

  var space = / /

  var rightOps = new String(rightOptions)
  var wrongOps = new String(wrongOptions)
  var correctAnswers = new String(correctAnswers)

  if(!caseSensitive)
  {
    correctAnswers = correctAnswers.toLowerCase()
    answer = answer.toLowerCase()
  }

  correctAnswers.replace(space, "");
  var answers = new Array()
  answers = correctAnswers.split(",")

  rightOps.replace(space, "")
  rightOps.toLowerCase();
  wrongOps.replace(space, "")
  wrongOps.toLowerCase();

  var rOptions = new Array()
  rOptions = rightOps.split(",")

  var wOptions = new Array()
  wOptions = wrongOps.split(",")

  for(var i =0; i < rOptions.length; i++)
  {
    if(rOptions[i].indexOf("popwin") != -1)
    {
      if(rOptions[i].indexOf("true") != -1)
        blnRightPopwin = true;
      else
        blnRightPopwin = false;
    }

    if(rOptions[i].indexOf("parent") != -1)
    {
      if(rOptions[i].indexOf("true") != -1)
        blnTargetParentRight = true;
      else
        blnTargetParentRight = false;
    }
    
    if(rOptions[i].indexOf("width") != -1)
    {
      var startIndex = rOptions[i].indexOf('=')+1
      var endIndex = rOptions[i].length
      widthRight = parseInt(rOptions[i].substring(startIndex, endIndex))
    }

    if(rOptions[i].indexOf("height") != -1)
    {
      var startIndex = rOptions[i].indexOf('=')+1
      var endIndex = rOptions[i].length
      heightRight = parseInt(rOptions[i].substring(startIndex, endIndex))
    }
  }

  for(var i =0; i < wOptions.length; i++)
  {
    if(wOptions[i].indexOf("popwin") != -1)
    {
      if(wOptions[i].indexOf("true") != -1)
        blnWrongPopwin = true;
      else
        blnWrongPopwin = false;
    }

    if(wOptions[i].indexOf("parent") != -1)
    {
      if(wOptions[i].indexOf("true") != -1)
        blnTargetParentWrong = true;
      else
        blnTargetParentWrong = false;
    }
    
    if(wOptions[i].indexOf("width") != -1)
    {      
      var startIndex = wOptions[i].indexOf('=')+1
      var endIndex = wOptions[i].length
      widthWrong = parseInt(wOptions[i].substring(startIndex, endIndex))
    }

    if(wOptions[i].indexOf("height") != -1)
    {
      var startIndex = wOptions[i].indexOf('=')+1
      var endIndex = wOptions[i].length
      heightWrong = parseInt(wOptions[i].substring(startIndex, endIndex))
    }
  }

  var isCorrect = false;

  for(var i =0; i < answers.length; i++)
  {
    if(answers[i] == answer)
      isCorrect = true;
  }

	if(isCorrect)
	{
    if(blnRightPopwin)
    {
			window.open(rightUrl, "popWin", "width="+widthRight+",height="+heightRight+",scrollbars=yes,resizable=yes")
    }
    else
    {
	    document.open()
		  if(blnTargetParentRight) parent.document.location = rightUrl
      else document.location = rightUrl
	    document.close()
    }
	}
	else
	{
    if(blnWrongPopwin)
    {     
			window.open(wrongUrl, "popWin", "width="+widthWrong+",height="+heightWrong+",scrollbars=yes,resizable=yes")
    }
    else
    {       
	    document.open()
		  if(blnTargetParentWrong) parent.document.location = wrongUrl
      else document.location = wrongUrl    
	    document.close()
    }
	}

}



