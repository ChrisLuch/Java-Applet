// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities.vectors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public class DotLine2d extends Vector2d
{
    protected double diameter;
    
    public DotLine2d() {
        this.diameter = 3.0;
    }
    
    public DotLine2d(final Point2D.Double double1, final Point2D.Double double2) {
        super(double1, double2);
        this.diameter = 3.0;
    }
    
    public void paint(final Graphics graphicsContext) {
        super.graphicsContext = graphicsContext;
        if (super.visible) {
            this.calculate();
            VectorUtil.drawDotLine(graphicsContext, this.getTail2D(), this.getHead2D(), this.diameter, super.fillColor);
            super.headAnch.draw(graphicsContext);
            super.tailAnch.draw(graphicsContext);
            if (super.showInfo && super.label != null) {
                super.label.draw(graphicsContext, super.label_x, super.label_y);
            }
        }
    }
    
    public void setDiameter(final double diameter) {
        this.diameter = diameter;
    }
    
    public void setNormal() {
        this.setSelected(false);
        this.setStill(true);
        super.showInfo = false;
        this.setColor(Color.black);
    }
}
