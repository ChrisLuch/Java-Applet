// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities.vectors;

import java.awt.Graphics;
import java.awt.Color;

public class VectorAnchor
{
    private Color outerColor;
    private Color innerColor;
    private boolean visible;
    public int x;
    public int y;
    
    public VectorAnchor() {
        this.outerColor = Color.white;
        this.innerColor = Color.black;
        this.visible = true;
        this.placeAnchor(0, 0);
    }
    
    public VectorAnchor(final int n, final int n2) {
        this.outerColor = Color.white;
        this.innerColor = Color.black;
        this.visible = true;
        this.placeAnchor(0, 0);
    }
    
    public void draw(final Graphics graphics) {
        if (this.visible) {
            graphics.setColor(this.outerColor);
            graphics.drawRect(this.x - 1, this.y - 1, 2, 2);
            graphics.setColor(this.innerColor);
            graphics.drawRect(this.x, this.y, 0, 0);
        }
    }
    
    public void placeAnchor(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setXY(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setColor(final Color outerColor) {
        this.outerColor = outerColor;
    }
    
    public void setTipColor(final Color innerColor) {
        this.innerColor = innerColor;
    }
    
    public void setOutlineColor(final Color outerColor) {
        this.outerColor = outerColor;
    }
    
    public void setInnerColor(final Color innerColor) {
        this.innerColor = innerColor;
    }
    
    public void setEnabled(final boolean visible) {
        this.visible = visible;
    }
    
    public boolean isEnabled() {
        return this.visible;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
}
