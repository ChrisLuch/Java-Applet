// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;

public class IncompleteBorder extends AbstractBorder implements SwingConstants
{
    protected Color northColor;
    protected Color eastColor;
    protected Color southColor;
    protected Color westColor;
    protected boolean drawNorth;
    protected boolean drawEast;
    protected boolean drawSouth;
    protected boolean drawWest;
    protected int width;
    protected Insets insets;
    
    public IncompleteBorder() {
        this(true, true, true, true, MapConstants.lineBorderColor, 1);
    }
    
    public IncompleteBorder(final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        this(b, b2, b3, b4, MapConstants.lineBorderColor, 1);
    }
    
    public IncompleteBorder(final boolean drawNorth, final boolean drawEast, final boolean drawSouth, final boolean drawWest, final Color color, final int width) {
        this.northColor = color;
        this.eastColor = color;
        this.southColor = color;
        this.westColor = color;
        this.drawNorth = drawNorth;
        this.drawEast = drawEast;
        this.drawSouth = drawSouth;
        this.drawWest = drawWest;
        this.width = width;
        this.insets = new Insets(drawNorth ? width : 0, drawWest ? width : 0, drawSouth ? width : 0, drawEast ? width : 0);
    }
    
    public Insets getBorderInsets(final Component component) {
        return this.insets;
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        insets.right = this.insets.right;
        insets.left = this.insets.left;
        insets.top = this.insets.top;
        insets.bottom = this.insets.bottom;
        return insets;
    }
    
    public boolean isBorderOpaque() {
        return true;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.drawNorth) {
            graphics.setColor(this.northColor);
            graphics.drawLine(n, n2, n + n3 - 1, n2);
        }
        if (this.drawEast) {
            graphics.setColor(this.eastColor);
            graphics.drawLine(n + n3 - 1, n2, n + n3 - 1, n2 + n4 - 1);
        }
        if (this.drawSouth) {
            graphics.setColor(this.southColor);
            graphics.drawLine(n, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        }
        if (this.drawWest) {
            graphics.setColor(this.westColor);
            graphics.drawLine(n, n2, n, n2 + n4 - 1);
        }
    }
    
    public void setBorderInsets(final Insets insets) {
        this.insets.right = insets.right;
        this.insets.left = insets.left;
        this.insets.top = insets.top;
        this.insets.bottom = insets.bottom;
    }
}
