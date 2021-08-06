// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import ca.ucalgary.phas.map.utilities.vectors.Vector2d;
import ca.ucalgary.phas.map.utilities.vectors.VectorLabelComponent;
import ca.ucalgary.phas.map.utilities.vectors.VectorString;
import java.awt.Color;
import java.awt.geom.Point2D;

public class SubtractionAddNegDemo extends VectorDemo
{
    public SubtractionAddNegDemo(final VectorPanel vectorPanel) {
        super(vectorPanel);
    }
    
    public void run() {
        super.vPanel.enableContinue(true);
        this.mySuspend();
        final double n = super.vPanel.getSize().width;
        final double n2 = super.vPanel.getSize().height;
        final Point2D.Double double1 = new Point2D.Double(0.0658 * n, 0.7778 * n2);
        final Point2D.Double double2 = new Point2D.Double(0.6842 * n, 0.0556 * n2);
        final Point2D.Double double3 = new Point2D.Double(0.8684 * n, 0.9167 * n2);
        final Point2D.Double double4 = new Point2D.Double(0.8158 * n, 0.5556 * n2);
        super.vPanel.enableContinue(false);
        super.vPanel.setDrawingNewVector(true);
        this.dragMouse(double1, double2);
        this.pause(500L);
        super.vPanel.setDrawingNewVector(true);
        this.dragMouse(double3, double4);
        super.vPanel.enableContinue(true);
        this.mySuspend();
        super.vPanel.enableContinue(false);
        final Point2D.Double double5 = new Point2D.Double(double4.x, double4.y);
        final Point2D.Double double6 = new Point2D.Double(double3.x, double3.y);
        final double sqrt = Math.sqrt((double3.x - double4.x) * (double3.x - double4.x) + (double3.y - double4.y) * (double3.y - double4.y));
        final Point2D.Double double7 = new Point2D.Double(double4.x + 25.0 / sqrt * (double3.y - double4.y), double4.y + -25.0 / sqrt * (double3.x - double4.x));
        this.translate(double6, double4, double7);
        this.translate(double5, double4, double7);
        super.vPanel.doDraw();
        this.dragMouse(double5, double6);
        final Vector2d vector = super.vPanel.getVector(2);
        if (vector != null) {
            vector.setColor(Color.blue);
            vector.showInfo = true;
            vector.setLabel((VectorLabelComponent)new VectorString("-b", true));
            vector.showTailAnchor(true);
            super.vPanel.repaint();
        }
        super.vPanel.enableContinue(true);
        this.mySuspend();
        super.vPanel.enableContinue(false);
        final Point2D.Double double8 = new Point2D.Double(double2.x + (double6.x - double5.x), double2.y + (double6.y - double5.y));
        final Point2D.Double double9 = new Point2D.Double((double6.x + double5.x) / 2.0, (double6.y + double5.y) / 2.0);
        final Point2D.Double double10 = new Point2D.Double((double2.x + double8.x) / 2.0, (double2.y + double8.y) / 2.0);
        if (vector != null) {
            vector.showTailAnchor(false);
        }
        this.dragMouse(double9, double10);
        super.vPanel.enableContinue(true);
        this.mySuspend();
        if (super.vPanel instanceof SubtractionPanel) {
            ((SubtractionPanel)super.vPanel).calculateAnswer(double1.x, double1.y);
        }
    }
}
