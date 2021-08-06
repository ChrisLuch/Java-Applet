// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.Point;

public class Addition2Demo extends VectorDemo
{
    public Addition2Demo(final VectorPanel vectorPanel) {
        super(vectorPanel);
    }
    
    public void run() {
        super.vPanel.enableContinue(true);
        this.mySuspend();
        final int width = super.vPanel.getSize().width;
        final int height = super.vPanel.getSize().height;
        final Point point = new Point((int)(0.1842 * width), (int)(0.1528 * height));
        final Point point2 = new Point((int)(0.4474 * width), (int)(0.6944 * height));
        final Point point3 = new Point((int)(0.3947 * width), (int)(0.2778 * height));
        final Point point4 = new Point((int)(0.6974 * width), (int)(0.1389 * height));
        super.vPanel.enableContinue(false);
        super.vPanel.setDrawingNewVector(true);
        this.dragMouse(point, point2);
        this.pause(500L);
        super.vPanel.setDrawingNewVector(true);
        this.dragMouse(point3, point4);
        super.vPanel.freezeDrawnVectors();
        super.vPanel.enableContinue(true);
        this.mySuspend();
        super.vPanel.enableContinue(false);
        this.dragMouse(point3, point2);
        point4.translate(point2.x - point3.x, point2.y - point3.y);
        super.vPanel.enableContinue(true);
        this.mySuspend();
        super.vPanel.enableContinue(false);
        if (super.vPanel instanceof Addition2Panel) {
            ((Addition2Panel)super.vPanel).calculateAnswer(point.getX(), point.getY());
        }
    }
}
