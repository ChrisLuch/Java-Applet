function openWin0() {
	var myWin = window.open("help.html", "win1", "resizable=yes,scrollbars=yes");
}


function openWin1(location) {
	var myWin = window.open(location, "win1", "resizable=yes,scrollbars=yes");
}

function openWin2(location, prefs) {
	var myWin = window.open(location, "win1", prefs);
}

function openWinUA(location, p1, p2, p3) {
	var myWin = window.open(location, "win1", "resizable=yes,scrollbars=yes,width=640,height=480");
}
