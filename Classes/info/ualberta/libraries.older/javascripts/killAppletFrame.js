<!-- Start 
//Scripted By David Ooms, 9:05 AM 7/31/98

function killAppletFrame(other) {
	var general = window.location.pathname.substring(window.location.pathname.length - 15, window.location.pathname.length)
	var start = general.indexOf("/")
	var end = general.indexOf("_")
	var specific = general.substring(start + 1, end)
		
	if(parent.appletframe) {
		if(other) {
			parent.parent.controlframe.location = other
			}
		else {
			parent.parent.controlframe.location = specific + "_m1.html"
			}
		}
	}
// END -->