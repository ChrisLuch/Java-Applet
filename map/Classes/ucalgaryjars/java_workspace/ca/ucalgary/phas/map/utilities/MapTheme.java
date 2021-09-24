// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class MapTheme extends DefaultMetalTheme
{
    private final ColorUIResource primary1;
    private final ColorUIResource primary2;
    private final ColorUIResource primary3;
    
    public MapTheme() {
        this.primary1 = new ColorUIResource(MapConstants.mapDarkGreen);
        this.primary2 = new ColorUIResource(MapConstants.mapGreen);
        this.primary3 = new ColorUIResource(MapConstants.mapLightGreen);
    }
    
    public String getName() {
        return "Map";
    }
    
    protected ColorUIResource getPrimary1() {
        return this.primary1;
    }
    
    protected ColorUIResource getPrimary2() {
        return this.primary2;
    }
    
    protected ColorUIResource getPrimary3() {
        return this.primary3;
    }
}
