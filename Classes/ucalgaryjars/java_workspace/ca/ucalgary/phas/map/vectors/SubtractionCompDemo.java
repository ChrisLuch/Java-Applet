// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.geom.Point2D;

public class SubtractionCompDemo extends VectorDemo
{
    public SubtractionCompDemo(final VectorPanel vectorPanel) {
        super(vectorPanel);
    }
    
    public void run() {
        super.vPanel.enableContinue(true);
        this.mySuspend();
        final int width = super.vPanel.getSize().width;
        final int height = super.vPanel.getSize().height;
        final Point2D.Double double1 = new Point2D.Double(0.4737 * width, 0.8056 * height);
        final Point2D.Double double2 = new Point2D.Double(0.8553 * width, 0.1389 * height);
        final Point2D.Double double3 = new Point2D.Double(0.4211 * width, 0.5 * height);
        final Point2D.Double double4 = new Point2D.Double(0.1974 * width, 0.1111 * height);
        super.vPanel.enableContinue(false);
        super.vPanel.setDrawingNewVector(true);
        this.dragMouse(double1, double2);
        this.pause(500L);
        super.vPanel.setDrawingNewVector(true);
        this.dragMouse(double3, double4);
        super.vPanel.freezeDrawnVectors();
        super.vPanel.enableContinue(true);
        this.mySuspend();
        super.vPanel.enableContinue(false);
        this.dragMouse(double3, double1);
        this.translate(double4, double3, double1);
        this.translate(double3, double3, double1);
        super.vPanel.enableContinue(true);
        this.mySuspend();
        if (super.vPanel instanceof SubtractionPanel) {
            ((SubtractionPanel)super.vPanel).calculateAnswer(double4.x, double4.y);
        }
    }
}
