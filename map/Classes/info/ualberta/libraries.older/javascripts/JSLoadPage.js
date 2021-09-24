function loadPage(cookiename, pagename, existsURL)
{
	//get the cookkie if it exists
	pageCookie = getCookie(cookiename);

	//check if the cookie exists
	if(pageCookie == null)
	{
		createPageCookie(cookiename, pagename);
	}
	else
	{
		createPageCookie(cookiename, pagename);
		document.location = existsURL;
	}
}

function getCookie(cookiename)
{
	//the number of cookies
	var num = document.cookie.length;

	//the "adjusted" name of the cookie
	var name = cookiename + "=";

	//the length of the cookie name
	var len = name.length;

	//counter variables
	var c1 = 0;
	var c2 = 0;

	//loop through all the cookies
	while(c1 <= num)
	{
		//advance the internal counter
		c2 = (c1 + len);

		//get the cookie if it exists
		if(document.cookie.substring(c1,c2) == name)
		{
			return (getCookieValue(c2));
		}
		
		//adcance the counter
		c1 = document.cookie.indexOf(" ", c1) + 1;
		
		//break if we excede the values
		if(c1 == 0)
		{
			break;
		}
	}

	//return (null) if the cookie does not exist
	return (null);
}

function getCookieValue(value)
{
	//get the position within the cookie
	var end = document.cookie.indexOf(";", value);

	//test for a "null" position and IF SO then adjust
	if(end == -1)
	{
		end = document.cookie.length;
	}

	//return the value
	return (unescape(document.cookie.substring(value,end)));
}

function createPageCookie(cookiename, pagename)
{
	document.cookie = cookiename + "=" + pagename + ";";
}