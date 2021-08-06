// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.AbstractBorder;

public class MapBorder extends AbstractBorder
{
    public static final int RAISED = 1;
    public static final int LOWERED = 2;
    public static final int DISABLED_LOWERED = 3;
    public static final int DISABLED_RAISED = 4;
    protected Color lightColor3;
    protected Color lightColor2;
    protected Color lightColor1;
    protected Color darkColor1;
    protected Color darkColor2;
    protected Color darkColor3;
    protected Color storeLightColor3;
    protected Color storeLightColor2;
    protected Color storeLightColor1;
    protected Color storeDarkColor1;
    protected Color storeDarkColor2;
    protected Color storeDarkColor3;
    protected Color storeDisabledLightColor3;
    protected Color storeDisabledLightColor2;
    protected Color storeDisabledLightColor1;
    protected Color storeDisabledDarkColor1;
    protected Color storeDisabledDarkColor2;
    protected Color storeDisabledDarkColor3;
    protected int depth;
    protected int level;
    protected Insets theInsets;
    
    public MapBorder() {
        this(1, MapConstants.mapBorderDefaultColor);
    }
    
    public MapBorder(final int n) {
        this(n, MapConstants.mapBorderDefaultColor);
    }
    
    public MapBorder(final int level, final Color background) {
        this.level = level;
        this.depth = 3;
        this.theInsets = new Insets(this.depth + 2, this.depth + 2, this.depth + 2, this.depth + 2);
        this.setBackground(background);
    }
    
    public MapBorder(final Color color) {
        this(1, color);
    }
    
    public Insets getBorderInsets(final Component component) {
        return this.theInsets;
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        insets.right = this.theInsets.right;
        insets.left = this.theInsets.left;
        insets.top = this.theInsets.top;
        insets.bottom = this.theInsets.bottom;
        return insets;
    }
    
    public int getDepth() {
        return this.depth;
    }
    
    public boolean isBorderOpaque() {
        return true;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n + 1;
        final int n6 = n + 2;
        final int n7 = n2 + 1;
        final int n8 = n2 + 2;
        final int n9 = n + n3 - 1;
        final int n10 = n9 - 1;
        final int n11 = n9 - 2;
        final int n12 = n2 + n4 - 1;
        final int n13 = n12 - 1;
        final int n14 = n12 - 2;
        graphics.setColor(this.lightColor3);
        graphics.drawLine(n5, n7, n5, n7);
        graphics.drawLine(n, n2, n11, n2);
        graphics.drawLine(n, n2, n, n14);
        graphics.setColor(this.lightColor2);
        graphics.drawLine(n6, n7, n11, n7);
        graphics.drawLine(n5, n8, n5, n14);
        graphics.drawLine(n10, n2, n10, n2);
        graphics.drawLine(n, n13, n, n13);
        graphics.setColor(this.lightColor1);
        graphics.drawLine(n6, n8, n11, n8);
        graphics.drawLine(n6, n8, n6, n14);
        graphics.setColor(this.darkColor1);
        graphics.drawLine(n, n12, n5, n13);
        graphics.drawLine(n10, n7, n9, n2);
        graphics.drawLine(n11, n14, n6, n14);
        graphics.drawLine(n11, n14, n11, n8);
        graphics.setColor(this.darkColor2);
        graphics.drawLine(n11, n13, n6, n13);
        graphics.drawLine(n10, n14, n10, n8);
        graphics.drawLine(n9, n7, n9, n7);
        graphics.drawLine(n5, n12, n5, n12);
        graphics.setColor(this.darkColor3);
        graphics.drawLine(n9, n12, n6, n12);
        graphics.drawLine(n9, n12, n9, n8);
        graphics.drawLine(n10, n13, n10, n13);
    }
    
    public void setBackground(final Color color) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        int n;
        int n2;
        if (red < 80) {
            n = red / 4;
            n2 = 40;
        }
        else if (255 - red < 80) {
            n = 40;
            n2 = (255 - red) / 4;
        }
        else {
            n = 20;
            n2 = 20;
        }
        int n3;
        int n4;
        if (red < 40) {
            n3 = red / 4;
            n4 = 20;
        }
        else if (255 - red < 40) {
            n3 = 20;
            n4 = (255 - red) / 4;
        }
        else {
            n3 = 10;
            n4 = 10;
        }
        int n5;
        int n6;
        if (green < 80) {
            n5 = green / 4;
            n6 = 40;
        }
        else if (255 - green < 80) {
            n5 = 40;
            n6 = (255 - green) / 4;
        }
        else {
            n5 = 20;
            n6 = 20;
        }
        int n7;
        int n8;
        if (green < 40) {
            n7 = green / 4;
            n8 = 20;
        }
        else if (255 - green < 40) {
            n7 = 20;
            n8 = (255 - green) / 4;
        }
        else {
            n7 = 10;
            n8 = 10;
        }
        int n9;
        int n10;
        if (blue < 80) {
            n9 = blue / 4;
            n10 = 40;
        }
        else if (255 - blue < 80) {
            n9 = 40;
            n10 = (255 - blue) / 4;
        }
        else {
            n9 = 20;
            n10 = 20;
        }
        int n11;
        int n12;
        if (blue < 40) {
            n11 = blue / 4;
            n12 = 20;
        }
        else if (255 - blue < 40) {
            n11 = 20;
            n12 = (255 - blue) / 4;
        }
        else {
            n11 = 10;
            n12 = 10;
        }
        this.storeLightColor3 = new Color(red + 2 * n2, green + 2 * n6, blue + 2 * n10);
        this.storeLightColor2 = new Color(red + 1 * n2, green + 1 * n6, blue + 1 * n10);
        this.storeLightColor1 = new Color(red + 0 * n2, green + 0 * n6, blue + 0 * n10);
        this.storeDarkColor1 = new Color(red - 0 * n, green - 0 * n5, blue - 0 * n9);
        this.storeDarkColor2 = new Color(red - 1 * n, green - 1 * n5, blue - 1 * n9);
        this.storeDarkColor3 = new Color(red - 2 * n, green - 2 * n5, blue - 2 * n9);
        this.storeDisabledLightColor3 = new Color(red + 2 * n4, green + 2 * n8, blue + 2 * n12);
        this.storeDisabledLightColor2 = new Color(red + 1 * n4, green + 1 * n8, blue + 1 * n12);
        this.storeDisabledLightColor1 = new Color(red + 0 * n4, green + 0 * n8, blue + 0 * n12);
        this.storeDisabledDarkColor1 = new Color(red - 0 * n3, green - 0 * n7, blue - 0 * n11);
        this.storeDisabledDarkColor2 = new Color(red - 1 * n3, green - 1 * n7, blue - 1 * n11);
        this.storeDisabledDarkColor3 = new Color(red - 2 * n3, green - 2 * n7, blue - 2 * n11);
        this.setColors();
    }
    
    public void setBorderInsets(final Insets insets) {
        this.theInsets.right = this.depth + insets.right;
        this.theInsets.left = this.depth + insets.left;
        this.theInsets.top = this.depth + insets.top;
        this.theInsets.bottom = this.depth + insets.bottom;
    }
    
    private void setColors() {
        if (this.level == 1) {
            this.lightColor3 = this.storeLightColor3;
            this.lightColor2 = this.storeLightColor2;
            this.lightColor1 = this.storeLightColor1;
            this.darkColor1 = this.storeDarkColor1;
            this.darkColor2 = this.storeDarkColor2;
            this.darkColor3 = this.storeDarkColor3;
        }
        else if (this.level == 2) {
            this.darkColor3 = this.storeLightColor3;
            this.darkColor2 = this.storeLightColor2;
            this.darkColor1 = this.storeLightColor1;
            this.lightColor1 = this.storeDarkColor1;
            this.lightColor2 = this.storeDarkColor2;
            this.lightColor3 = this.storeDarkColor3;
        }
        else if (this.level == 4) {
            this.lightColor3 = this.storeDisabledLightColor3;
            this.lightColor2 = this.storeDisabledLightColor2;
            this.lightColor1 = this.storeDisabledLightColor1;
            this.darkColor1 = this.storeDisabledDarkColor1;
            this.darkColor2 = this.storeDisabledDarkColor2;
            this.darkColor3 = this.storeDisabledDarkColor3;
        }
        else if (this.level == 3) {
            this.darkColor3 = this.storeDisabledLightColor3;
            this.darkColor2 = this.storeDisabledLightColor2;
            this.darkColor1 = this.storeDisabledLightColor1;
            this.lightColor1 = this.storeDisabledDarkColor1;
            this.lightColor2 = this.storeDisabledDarkColor2;
            this.lightColor3 = this.storeDisabledDarkColor3;
        }
    }
    
    public void setLevel(final int level) {
        this.level = level;
        this.setColors();
    }
}
