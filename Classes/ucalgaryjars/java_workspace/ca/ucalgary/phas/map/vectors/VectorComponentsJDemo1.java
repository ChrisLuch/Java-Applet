// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.geom.Point2D;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;

public class VectorComponentsJDemo1 extends VectorCompJDemo
{
    public static final int A_LENGTH = 40;
    public static final int VA = 0;
    public static final int VX = 4;
    public static final int VY = 5;
    public static final double VA1_ANGLE = 1.9634954084936207;
    public static final int PAUSE = 500;
    public static final int A1 = 1;
    public static final int A2 = 2;
    
    public VectorComponentsJDemo1(final VectorCompsApplet vectorCompsApplet, final VectorCompsPanel vectorCompsPanel) {
        super(vectorCompsApplet, vectorCompsPanel);
    }
    
    public void run() {
        final int width = super.vPanel.getSize().width;
        final int height = super.vPanel.getSize().height;
        final double n = width / 3;
        final Vector2d vector2d = new Vector2d(super.vPanel.getVectorA());
        final Vector2d vector2d2 = new Vector2d(super.vPanel.getVectorX());
        final Vector2d vector2d3 = new Vector2d(super.vPanel.getVectorY());
        final Point2D.Double double1 = new Point2D.Double(vector2d.getTail2D().x, vector2d.getTail2D().y);
        final Point2D.Double double2 = new Point2D.Double(vector2d.getHead2D().x, vector2d.getHead2D().y);
        final Point2D.Double translatePoint = super.vPanel.translatePoint(double1, vector2d2.getDirection(), vector2d.getLength() * Math.cos(vector2d2.getDirection() - vector2d.getDirection()));
        final Point2D.Double translatePoint2 = super.vPanel.translatePoint(double1, vector2d3.getDirection(), vector2d.getLength() * Math.cos(vector2d3.getDirection() - vector2d.getDirection()));
        super.vPanel.doDrawDLine();
        this.dragMouse(double2, translatePoint);
        super.vPanel.doDrawDLine();
        this.dragMouse(double2, translatePoint2);
        super.theApplet.suspendDemo();
        super.vPanel.doDraw();
        this.dragMouse(double1, translatePoint);
        this.pause(500L);
        super.vPanel.doDraw();
        this.dragMouse(double1, translatePoint2);
        super.theApplet.endDemo();
    }
}
