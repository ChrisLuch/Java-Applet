package ca.ucalgary.phas.map.angle;

import javax.swing.*;
import java.awt.*;
import ca.ucalgary.phas.map.utilities.MapMenuBar;
import ca.ucalgary.phas.map.utilities.MapConstants;
import ca.ucalgary.phas.map.utilities.ParameterUtils;

/** This applet is designed to demonstrate the properties of angles and how the
 * ratio of the span over the radius is equal to a constant angle (theta) measured
 * in radians.
 */
public class Angle extends JApplet {
	
	/** The value of the INFO_URL parameter in the HTML parameter tag.
	 * This value is the relative URL of the HTML help file to disply when the help
	 * button is pressed.  Default value is "help.html".
	 */
	public String strJSHelpFile;
	/** The value of the JS_PARAMETER parameter in the HTML parameter tag.  This is the
	 * value of the parameter to pass the the JavaScript function determined by JS_FUNCTION.
	 * Defalut value is "scrollbars=yes,resizable=yes".
	 */
	public boolean pbPresent;
	
	/** Initilize the applet, read parameters from the HTML file and initilize variables
	 * and add AnglePanel to applet container.
	 */
	public void init() {
		MapConstants.setMapLookAndFeel();
		MapMenuBar menuBar = new MapMenuBar(this);
		if (!MapConstants.isMenuBarHidden()){
			 setJMenuBar(menuBar);
		}
		
		AnglePanel myAnglePanel = new AnglePanel(this);
		
		boolean verbose = ParameterUtils.getBooleanParameter(this, "VERBOSE", false);
		pbPresent = ParameterUtils.getBooleanParameter(this, "PAGE_BROWSER_PRESENT", true);
		String infoURL = ParameterUtils.getStringParameter(this, "INFO_URL", "info.html");
				
		if (verbose) {
			System.out.println("PAGE_BROWSER_PRESENT = " + pbPresent);
			System.out.println("INFO_URL = " + infoURL);
		}
		

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(myAnglePanel, BorderLayout.CENTER);
	}
}
