// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities.vectors;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public class Line2d extends DotLine2d
{
    public Line2d() {
    }
    
    public Line2d(final Point2D.Double double1, final Point2D.Double double2) {
        super(double1, double2);
    }
    
    public void paint(final Graphics graphicsContext) {
        super.graphicsContext = graphicsContext;
        if (super.visible) {
            this.calculate();
            VectorUtil.drawLine(graphicsContext, super.diameter, this.getTail2D(), this.getHead2D(), super.outlineColor, super.fillColor);
            super.headAnch.draw(graphicsContext);
            super.tailAnch.draw(graphicsContext);
            if (super.showInfo && super.label != null) {
                super.label.draw(graphicsContext, super.label_x, super.label_y);
            }
        }
    }
}
