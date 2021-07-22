// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.angle;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import ca.ucalgary.phas.map.utilities.ParameterUtils;
import javax.swing.JMenuBar;
import java.applet.Applet;
import ca.ucalgary.phas.map.utilities.MapMenuBar;
import ca.ucalgary.phas.map.utilities.MapConstants;
import javax.swing.JApplet;

public class Angle extends JApplet
{
    public String strJSHelpFile;
    public boolean pbPresent;
    
    public void init() {
        MapConstants.setMapLookAndFeel();
        final MapMenuBar jMenuBar = new MapMenuBar((Applet)this);
        if (!MapConstants.isMenuBarHidden()) {
            this.setJMenuBar((JMenuBar)jMenuBar);
        }
        final AnglePanel comp = new AnglePanel(this);
        final boolean booleanParameter = ParameterUtils.getBooleanParameter((Applet)this, "VERBOSE", false);
        this.pbPresent = ParameterUtils.getBooleanParameter((Applet)this, "PAGE_BROWSER_PRESENT", true);
        final String stringParameter = ParameterUtils.getStringParameter((Applet)this, "INFO_URL", "info.html");
        if (booleanParameter) {
            System.out.println("PAGE_BROWSER_PRESENT = " + this.pbPresent);
            System.out.println("INFO_URL = " + stringParameter);
        }
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(comp, "Center");
    }
}
