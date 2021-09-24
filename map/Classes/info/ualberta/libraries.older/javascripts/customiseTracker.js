<!-- //Scripted By David Ooms August 1998

function customiseTracker(module, level) {


var FORCES = "<p><center><a href='forcesplash.html' target='controlframe'>FORCES</a></center></p>"

var FBD = "<p><center><a href='FBDsplash.html' target='controlframe'>Free Body<br>Diagrams</a></center></p>"
	var FBDproblems = "<p><center><a href='FBD_problem_menu.html' target='controlframe'>Free Body<br>Diagram Problems</a></center></p>"
	var FBDtext = "<p><center><a href='FBD_text_menu.html' target='controlframe'>Free Body<br>Diagram Text</a></center></p>"
	var FBDsim = "<p><center><a href='FBD_sim_menu.html' target='controlframe'>Free Body<br>Diagram Simulations</a></center></p>"

var FGRAV = "<p><center><a href='FGRAVsplash.html' target='controlframe'>Gravitational<br>Force</a></center></p>"
	var FGRAVproblems = "<p><center><a href='FGRAV_problem_menu.html' target='controlframe'>Gravitaion Force<br>Problems</a></center></p>"
	var FGRAVtext = "<p><center><a href='FGRAV_text_menu.html' target='controlframe'>Gravitaion Force<br> Text</a></center></p>"
	var FGRAVsim = "<p><center><a href='FGRAV_sim_menu.html' target='controlframe'>Gravitaion Force<br>Simulations</a></center></p>"

var FCEN = "<p><center><a href='FCENsplash.html' target='controlframe'>Centripetal<br>Force</a></center></p>"
	var FCENproblems = "<p><center><a href='FCEN_problem_menu.html' target='controlframe'>Centripetal Force<br>Problems</a></center></p>"
	var FCENtext = "<p><center><a href='FCEN_text_menu.html' target='controlframe'>Centripetal Force<br>Text</a></center></p>"
	var FCENsim = "<p><center><a href='FCEN_sim_menu.html' target='controlframe'>Centripetal Force<br>Simulations</a></center></p>"

var FCON = "<p><center><a href='FCONsplash.html' target='controlframe'>Contact<br>Forces</a></center></p>"
	var FCONproblems = "<p><center><a href='FCON_problem_menu.html' target='controlframe'>Contact Forces<br>Problems</a></center></p>"
	var FCONtext = "<p><center><a href='FCON_text_menu.html' target='controlframe'>Contact Forces<br>Text</a></center></p>"
	var FCONsim = "<p><center><a href='FCON_sim_menu.html' target='controlframe'>Contact Forces<br>Simulations</a></center></p>"

var FNORM = "<p><center><a href='FNORMsplash.html' target='controlframe'>Normal<br>Force</a></center></p>"
	var FNORMproblems = "<p><center><a href='FNORM_problem_menu.html' target='controlframe'>Normal Force<br>Problems</a></center></p>"
	var FNORMtext = "<p><center><a href='FNORM_text_menu.html' target='controlframe'>Normal Force<br>Text</a></center></p>"
	var FNORMsim = "<p><center><a href='FNORM_sim_menu.html' target='controlframe'>Normal Force<br>Simulations</a></center></p>"

var FFIELD = "<p><center><a href='FFIELDsplash.html' target='controlframe'>Field<br>Forces</a></center></p>"
	var FFIELDproblems = "<p><center><a href='FFIELD_problem_menu.html' target='controlframe'>Field Forces<br>Problems</a></center></p>"
	var FFIELDtext = "<p><center><a href='FFIELD_text_menu.html' target='controlframe'>Field Forces<br>Text</a></center></p>"
	var FFIELDsim = "<p><center><a href='FFIELD_sim_menu.html' target='controlframe'>Field Forces<br>Simulations</a></center></p>"

var FFOUR = "<p><center><a href='FFOURsplash.html' target='controlframe'>Fundamental<br>Forces</a></center></p>"
	var FFOURproblems = "<p><center><a href='FFOUR_problem_menu.html' target='controlframe'>Fundamental<br>Forces<br>Problems</a></center></p>"
	var FFOURtext = "<p><center><a href='FFOUR_text_menu.html' target='controlframe'>Fundamental<br>Forces<br>Text</a></center></p>"
	var FFOURsim = "<p><center><a href='FFOUR_sim_menu.html' target='controlframe'>Fundamental<br>Forces<br>Simulations</a></center></p>"

var FFRIC = "<p><center><a href='FFRICsplash.html' target='controlframe'>Friction</a></center></p>"
	var FFRICproblems = "<p><center><a href='FFRIC_problem_menu.html' target='controlframe'>Friction<br>Problems</a></center></p>"
	var FFRICtext = "<p><center><a href='FFRIC_text_menu.html' target='controlframe'>Friction<br>Text</a></center></p>"
	var FFRICsim = "<p><center><a href='FFRIC_sim_menu.html' target='controlframe'>Friction<br>Simulations</a></center></p>"

var FMOT = "<p><center><a href='FMOTsplash.html' target='controlframe'>Force And<br>Motion</a></center></p>"
	var FMOTproblems = "<p><center><a href='FMOT_problem_menu.html' target='controlframe'>Force And<br>Motion<br>Problems</a></center></p>"
	var FMOTtext = "<p><center><a href='FMOT_text_menu.html' target='controlframe'>Force And<br>Motion<br>Text</a></center></p>"
	var FMOTsim = "<p><center><a href='FMOT_sim_menu.html' target='controlframe'>Force And<br>Motion<br>Simulations</a></center></p>"

var FSP = "<p><center><a href='FSPsplash.html' target='controlframe'>Spring<br>Force</a></center></p>"
	var FSPproblems = "<p><center><a href='FSP_problem_menu.html' target='controlframe'>Spring Force<br>Problems</a></center></p>"
	var FSPtext = "<p><center><a href='FSP_text_menu.html' target='controlframe'>Spring Force<br>Text</a></center></p>"
	var FSPsim = "<p><center><a href='FSP_sim_menu.html' target='controlframe'>Spring Force<br>Simulations</a></center></p>"

var FSTAT = "<p><center><a href='FSTATsplash.html' target='controlframe'>Static<br>Force</a></center></p>"
	var FSTATproblems = "<p><center><a href='FSTAT_problem_menu.html' target='controlframe'>Static Force<br>Problems</a></center></p>"
	var FSTATtext = "<p><center><a href='FSTAT_text_menu.html' target='controlframe'>Static Force<br>Text</a></center></p>"
	var FSTATsim = "<p><center><a href='FSTAT_sim_menu.html' target='controlframe'>Static Force<br>Simulations</a></center></p>"

var trackerMenu = new Array();
var trackerMenuHTML = ""

//Call Whatever is stored In Cookie
var trackerMenu = document.cookie.split(",")

	


//make sure that slot 0 will always be the following

trackerMenu[0] = "<html><head><title>PAGE TRACKER</title><meta http-equiv='Content-Type' content='text/html'></head><body><p><center><font size='+2'>Physics 203</font></center></p>"  
//Delete all extra info in cookie and trackerMenu[] when going back down a level


	if (trackerMenu.length > level + 1) {

			for(var i = level; i < trackerMenu.length; i++) {
				delete trackerMenu[i]
				}
			trackerMenu.length = level + 1



//redefine cookie with new number of levels
		document.cookie = "menu=" + trackerMenu.join(",")
		}		


//start evaluating and customising the Tracker Menu Bar, after determining it's properties

if (level == 1) {
	
	trackerMenu[level] = module
	document.cookie = "menu=" + trackerMenu.join(",")
		
	}
if (level == 2) {
	
	trackerMenu[level] = module
	document.cookie = "menu=" + trackerMenu.join(",")
	
	}
if (level == 3) {
	
	trackerMenu[level] = module
	document.cookie = "menu=" + trackerMenu.join(",")	

	}
if (level == 4) {
	
	trackerMenu[level] = module
	document.cookie = "menu=" + trackerMenu.join(",")	

	}

var moduleList = new Array();

moduleList[0] = new Array("FORCES", FORCES);
moduleList[1] = new Array("FBD", FBD);
moduleList[2] = new Array("FGRAV", FGRAV);
moduleList[3] = new Array("FCEN", FCEN);
moduleList[4] = new Array("FSP", FSP);
moduleList[5] = new Array("FCON", FCON);
moduleList[6] = new Array("FNORM", FNORM);
moduleList[7] = new Array("FFRIC", FFRIC);
moduleList[8] = new Array("FFOUR", FFOUR);
moduleList[9] = new Array("FFIELD", FFIELD);
moduleList[10] = new Array("FMOT", FMOT);
moduleList[11] = new Array("FSTAT", FSTAT);

moduleList[12] = new Array("FBDproblems", FBDproblems);
moduleList[13] = new Array("FGRAVproblems", FGRAVproblems);
moduleList[14] = new Array("FCENproblems", FCENproblems);
moduleList[15] = new Array("FSPproblems", FSPproblems);
moduleList[16] = new Array("FCONproblems", FCONproblems);
moduleList[17] = new Array("FNORMproblems", FNORMproblems);
moduleList[18] = new Array("FFRICproblems", FFRICproblems);
moduleList[19] = new Array("FFOURproblems", FFOURproblems);
moduleList[20] = new Array("FFIELDproblems", FFIELDproblems);
moduleList[21] = new Array("FMOTproblems", FMOTproblems);
moduleList[22] = new Array("FSTATproblems", FSTATproblems);

moduleList[23] = new Array("FBDsim", FBDsim);
moduleList[24] = new Array("FGRAVsim", FGRAVsim);
moduleList[25] = new Array("FCENsim", FCENsim);
moduleList[26] = new Array("FSPsim", FSPsim);
moduleList[27] = new Array("FCONsim", FCONsim);
moduleList[28] = new Array("FNORMsim", FNORMsim);
moduleList[29] = new Array("FFRICsim", FFRICsim);
moduleList[30] = new Array("FFOURsim", FFOURsim);
moduleList[31] = new Array("FFIELDsim", FFIELDsim);
moduleList[32] = new Array("FMOTsim", FMOTsim);
moduleList[33] = new Array("FSTATsim", FSTATsim);

moduleList[34] = new Array("FBDtext", FBDtext);
moduleList[35] = new Array("FGRAVtext", FGRAVtext);
moduleList[36] = new Array("FCENtext", FCENtext);
moduleList[37] = new Array("FSPtext", FSPtext);
moduleList[38] = new Array("FCONtext", FCONtext);
moduleList[39] = new Array("FNORMtext", FNORMtext);
moduleList[40] = new Array("FFRICtext", FFRICtext);
moduleList[41] = new Array("FFOURtext", FFOURtext);
moduleList[42] = new Array("FFIELDtext", FFIELDtext);
moduleList[43] = new Array("FMOTtext", FMOTtext);
moduleList[44] = new Array("FSTATtext", FSTATtext);



for(i = 0; i < moduleList.length; i++) {
	if(moduleList[i][0] == module) {
		module = moduleList[i][1]
		trackerMenu[level] = module
		document.cookie = "menu=" + trackerMenu.join(",")
			
		}
	}





//send array and data over to the Tracker Frame


		

trackerMenu.length = level + 1
	for(var i = 0; i < trackerMenu.length; i++) {		
		trackerMenuHTML += trackerMenu[i]
		}
	trackerMenuHTML += "</body></html>"
	parent.trackerframe.document.write(trackerMenuHTML)
	parent.trackerframe.document.close()
	
}	

	
	
// END-->
	
	