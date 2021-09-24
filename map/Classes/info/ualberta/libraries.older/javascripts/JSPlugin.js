function checkPlugin(plugin, havePluginURL, noPluginURL, IEGoesToURL)
{ 
  document.writeln("<body bgcolor=#FFFFFF>");
  document.writeln("<font size=2><font size=4>Plugin Detection Script</font>");

  if((navigator.plugins && navigator.plugins[plugin]) || (IEGoesToURL && 
       navigator.appName.indexOf('Microsoft') != -1 &&
       navigator.appVersion.indexOf('Mac') == -1 &&
       navigator.appVersion.indexOf('3.1') == -1))
  {
    document.writeln("<p> You have ShockWave Flash, Go <a href=" + havePluginURL + ">here</a></body>");
    document.location = havePluginURL;
  }
  else
  {
    document.writeln("<p> You do not have ShockWave Flash, Go <a href=" + noPluginURL + ">here</a></body>");
    document.location = noPluginURL;
  }

  return false;
}