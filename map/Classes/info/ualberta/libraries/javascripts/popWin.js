// Copyright 1998 by Ray Stott - ver 1.0

 // OK to use on noncommercial sites as long as copyright is included

 // Script is available at http://www.erols.com/crays/js.htm          

 var popWin = null    // use this when referring to pop-up window

 var winCount = 0

 var winName = "popWin"

 function openPopWin(winURL, winWidth, winHeight, winFeatures){

   winName = "popWin" + winCount++ //unique name for each pop-up window

   closePopWin()           // close any previously opened pop-up window

   if (openPopWin.arguments.length == 4)  // any additional features? 

     winFeatures = "," + winFeatures

   else 

     winFeatures = "" 

   popWin = window.open(winURL, winName, "width=" + winWidth 

            + ",height=" + winHeight + winFeatures)

   }

 function closePopWin(){    // close pop-up window if it is open 

   if (navigator.appName != "Microsoft Internet Explorer" 

       || parseInt(navigator.appVersion) >=4) //do not close if early IE

     if(popWin != null) if(!popWin.closed) popWin.close() 

   }