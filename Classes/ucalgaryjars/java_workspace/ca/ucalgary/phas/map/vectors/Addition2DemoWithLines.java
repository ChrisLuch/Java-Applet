// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.geom.Point2D;

public class Addition2DemoWithLines extends VectorDemo
{
    public Addition2DemoWithLines(final VectorPanel vectorPanel) {
        super(vectorPanel);
    }
    
    public void run() {
        super.vPanel.enableContinue(true);
        this.mySuspend();
        super.vPanel.enableContinue(false);
        final double n = super.vPanel.getSize().width;
        final double n2 = super.vPanel.getSize().height;
        final Point2D.Double double1 = new Point2D.Double(0.3816 * n, 0.8333 * n2);
        final Point2D.Double double2 = new Point2D.Double(0.2763 * n, 0.1389 * n2);
        final Point2D.Double double3 = new Point2D.Double(0.4474 * n, 0.5556 * n2);
        final Point2D.Double double4 = new Point2D.Double(0.8158 * n, 0.5833 * n2);
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
        super.vPanel.enableContinue(false);
        final Point2D.Double double5 = (Point2D.Double)double1.clone();
        final Point2D.Double double6 = (Point2D.Double)double2.clone();
        final Point2D.Double double7 = (Point2D.Double)double3.clone();
        final Point2D.Double double8 = (Point2D.Double)double4.clone();
        final int n3 = 20;
        final Point2D.Double extend2D = this.extend2D(double5, double2, n3);
        final Point2D.Double extend2D2 = this.extend2D(double6, double1, n3);
        this.translate(extend2D, double3, this.extend2D(double3, double4, n3 / 2));
        this.translate(extend2D2, double3, this.extend2D(double3, double4, n3 / 2));
        final Point2D.Double extend2D3 = this.extend2D(double7, double4, n3);
        final Point2D.Double extend2D4 = this.extend2D(double8, double3, n3);
        this.translate(extend2D3, double1, this.extend2D(double1, double2, n3 / 2));
        this.translate(extend2D4, double1, this.extend2D(double1, double2, n3 / 2));
        final Point2D.Double double9 = new Point2D.Double(double4.x + (double2.x - double1.x), double4.y + (double2.y - double1.y));
        final Point2D.Double double10 = new Point2D.Double((extend2D2.x + extend2D.x) / 2.0, (extend2D2.y + extend2D.y) / 2.0);
        final Point2D.Double double11 = new Point2D.Double((double4.x + double9.x) / 2.0, (double4.y + double9.y) / 2.0);
        final Point2D.Double double12 = new Point2D.Double((extend2D4.x + extend2D3.x) / 2.0, (extend2D4.y + extend2D3.y) / 2.0);
        final Point2D.Double double13 = new Point2D.Double((double2.x + double9.x) / 2.0, (double2.y + double9.y) / 2.0);
        this.translate(extend2D, double10, double11);
        this.translate(extend2D2, double10, double11);
        this.translate(extend2D3, double12, double13);
        this.translate(extend2D4, double12, double13);
        super.vPanel.doDrawLine();
        this.dragMouse(extend2D, extend2D2);
        this.pause(500L);
        super.vPanel.doDrawLine();
        this.dragMouse(extend2D3, extend2D4);
        this.pause(500L);
        super.vPanel.enableContinue(true);
        this.mySuspend();
        super.vPanel.enableContinue(false);
        if (super.vPanel instanceof Addition2Panel) {
            ((Addition2Panel)super.vPanel).calculateAnswer(double3.x, double3.y);
        }
    }
}
