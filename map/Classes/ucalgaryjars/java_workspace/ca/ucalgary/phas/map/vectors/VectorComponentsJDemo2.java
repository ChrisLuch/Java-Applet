// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import ca.ucalgary.phas.map.utilities.vectors.VectorLabelComponent;
import ca.ucalgary.phas.map.utilities.ComplexString;
import java.awt.geom.Point2D;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;

public class VectorComponentsJDemo2 extends VectorCompJDemo
{
    public static final int A_LENGTH = 40;
    public static final int VA = 0;
    public static final double VA1_ANGLE = 1.9634954084936207;
    public static final int PAUSE = 500;
    public static final int A1 = 1;
    public static final int A2 = 2;
    
    public VectorComponentsJDemo2(final VectorCompsApplet vectorCompsApplet, final VectorCompsPanel vectorCompsPanel) {
        super(vectorCompsApplet, vectorCompsPanel);
    }
    
    public void run() {
        final int panelWidth = super.vPanel.panelWidth;
        final int panelHeight = super.vPanel.panelHeight;
        final double n = panelWidth / 3;
        final Vector2d vector2d = new Vector2d(super.vPanel.getVectorA());
        final Point2D.Double double1 = new Point2D.Double(vector2d.getTail2D().x, vector2d.getTail2D().y);
        final Point2D.Double double2 = new Point2D.Double(vector2d.getHead2D().x, vector2d.getHead2D().y);
        final Point2D.Double translatePoint = super.theApplet.vPanel.translatePoint(double1, 1.9634954084936207, n);
        final Point2D.Double translatePoint2 = super.theApplet.vPanel.translatePoint(double1, 3.5342917352885173, n / 2.0);
        final Point2D.Double translatePoint3 = super.theApplet.vPanel.translatePoint(double1, 1.9634954084936207, n + 50.0);
        final Point2D.Double translatePoint4 = super.theApplet.vPanel.translatePoint(double1, 5.105088062083414, n + 50.0);
        final Point2D.Double translatePoint5 = super.theApplet.vPanel.translatePoint(double1, 3.5342917352885173, n + 50.0);
        final Point2D.Double translatePoint6 = super.theApplet.vPanel.translatePoint(double1, 6.675884388878311, n + 50.0);
        super.vPanel.doDrawLine();
        this.dragMouse(translatePoint3, translatePoint4);
        super.vPanel.doDrawLine();
        this.dragMouse(translatePoint6, translatePoint5);
        super.theApplet.suspendDemo();
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
        this.pause(500L);
        final ComplexString label = new ComplexString("<vector:A><subscript>y");
        final ComplexString label2 = new ComplexString("<vector:A><subscript>x");
        super.theApplet.vPanel.getVectorA1().setLabel((VectorLabelComponent)label);
        super.theApplet.vPanel.getVectorA2().setLabel((VectorLabelComponent)label2);
        super.theApplet.vPanel.repaint();
        super.theApplet.endDemo();
    }
}
