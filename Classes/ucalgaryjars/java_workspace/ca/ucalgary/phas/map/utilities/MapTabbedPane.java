// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import javax.swing.JToolTip;
import javax.swing.JTabbedPane;

public class MapTabbedPane extends JTabbedPane
{
    public JToolTip createToolTip() {
        final JToolTip toolTip = super.createToolTip();
        toolTip.setBackground(MapConstants.mapButtonToolTipBackgroundColor);
        return toolTip;
    }
}
