// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.geom.Point2D;

public class Addition3Demo extends VectorDemo
{
    public Addition3Demo(final VectorPanel vectorPanel) {
        super(vectorPanel);
    }
    
    public void run() {
        super.vPanel.enableContinue(true);
        this.mySuspend();
        final double n = super.vPanel.getSize().width;
        final double n2 = super.vPanel.getSize().height;
        final Point2D.Double double1 = new Point2D.Double(0.1447 * n, 0.2733 * n2);
        final Point2D.Double double2 = new Point2D.Double(0.4605 * n, 0.3567 * n2);
        final Point2D.Double double3 = new Point2D.Double(0.5395 * n, 0.4861 * n2);
        final Point2D.Double double4 = new Point2D.Double(0.8684 * n, 0.3611 * n2);
        final Point2D.Double double5 = new Point2D.Double(0.2647 * n, 0.3589 * n2);
        final Point2D.Double double6 = new Point2D.Double(0.1232 * n, 0.9561 * n2);
        super.vPanel.enableContinue(false);
        super.vPanel.setDrawingNewVector(true);
        this.dragMouse(double1, double2);
        this.pause(500L);
        super.vPanel.setDrawingNewVector(true);
        this.dragMouse(double3, double4);
        this.pause(500L);
        super.vPanel.setDrawingNewVector(true);
        this.dragMouse(double5, double6);
        super.vPanel.freezeDrawnVectors();
        super.vPanel.enableContinue(true);
        this.mySuspend();
        super.vPanel.enableContinue(false);
        this.dragMouse(double3, double2);
        this.translate(double4, double3, double2);
        this.pause(500L);
        this.dragMouse(double5, double4);
        this.translate(double6, double5, double4);
        super.vPanel.enableContinue(true);
        this.mySuspend();
        if (super.vPanel instanceof Addition3Panel) {
            ((Addition3Panel)super.vPanel).calculateAnswer(double1.x, double1.y);
        }
    }
}
