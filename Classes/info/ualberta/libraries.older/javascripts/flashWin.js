var str1 = "<embed src='";
var str2 = "' pluginspage='http://www.macromedia.com/shockwave/download/' type='application/x-shockwave-flash' width='";
var str3 = "' height='";
var str4 = "' loop='false' play='";
var str5 = "'></embed>";

function openFlashWin(flashURL, width, height)
{
	openFlashWin(flashURL, width, height, "false");
}
function openFlashWin(flashURL, width, height, play)
{
	flashWin = window.open("","FlashWin", "width=" + (width+15) + ",height=" + (height+15) + ",scrolling=no");
	flashWin.document.open();
	flashWin.document.write(str1 + flashURL + str2 + width + str3 + height + str4 + play + str5);
	flashWin.document.close();
}