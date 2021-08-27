// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import ca.ucalgary.phas.map.utilities.ParameterUtils;
import java.util.Vector;
import javax.swing.JMenuBar;
import java.applet.Applet;
import ca.ucalgary.phas.map.utilities.MapMenuBar;
import ca.ucalgary.phas.map.utilities.MapConstants;
import javax.swing.JApplet;

public class ScalarCompsApplet extends JApplet
{
    static final int DEMO = 0;
    static final int SIM = 1;
    ScalarCompsPanel scalarCompsPanel;
    
    public void init() {
        MapConstants.setMapLookAndFeel();
        final MapMenuBar jMenuBar = new MapMenuBar((Applet)this);
        if (!MapConstants.isMenuBarHidden()) {
            this.setJMenuBar((JMenuBar)jMenuBar);
        }
        final Vector<String> vector = new Vector<String>();
        vector.addElement("SIM");
        vector.addElement("DEMO");
        int n = 0;
        final String stringParameter = ParameterUtils.getStringParameter((Applet)this, "appMode", (Vector)vector, "DEMO");
        if (stringParameter.equalsIgnoreCase("SIM")) {
            n = 1;
        }
        else if (stringParameter.equalsIgnoreCase("DEMO")) {
            n = 0;
        }
        this.scalarCompsPanel = new ScalarCompsPanel(n, ParameterUtils.getBooleanParameter((Applet)this, "PAGE_BROWSER_PRESENT", true));
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.scalarCompsPanel, "Center");
    }
    
    public void start() {
        this.scalarCompsPanel.start();
    }
    
    public void stop() {
        this.scalarCompsPanel.stop();
    }
}
