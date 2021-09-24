// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Point;
import ca.ucalgary.phas.map.utilities.vectors.VectorLabelComponent;
import ca.ucalgary.phas.map.utilities.vectors.VectorString;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;
import java.awt.geom.Point2D;
import java.applet.Applet;
import ca.ucalgary.phas.map.utilities.ParameterUtils;
import java.util.Vector;
import java.awt.Color;

public class ScalarPanel extends VectorPanel
{
    public static final Color COLOR_A;
    public static final Color COLOR_B;
    public static final int NULLSTEP = 0;
    public static final int START = 1;
    public static final int PAGE2 = 2;
    public static final int PAGE3 = 3;
    public static final int PAGE4 = 4;
    public static final int PAGE5 = 5;
    public static final int PAGE5_MODE = 1;
    public static final int PAGE2_MODE = 2;
    public int mode;
    protected static final int VA = 1;
    protected static final int VB = 2;
    protected int lastVector;
    int step;
    
    public ScalarPanel(final VectorApplet vectorApplet) {
        super(vectorApplet, "vectorscalarapplet");
        this.lastVector = 2;
        final Vector<String> vector = new Vector<String>();
        vector.addElement("2PAGE_MODE");
        vector.addElement("5PAGE_MODE");
        final String stringParameter = ParameterUtils.getStringParameter((Applet)vectorApplet, "MODE", (Vector)vector, "5PAGE_MODE");
        if (stringParameter.equalsIgnoreCase("2PAGE_MODE")) {
            this.mode = 2;
            this.gotoPage1();
        }
        else if (stringParameter.equalsIgnoreCase("5PAGE_MODE")) {
            this.mode = 1;
        }
        super.vectorA = null;
        super.vectorB = null;
        this.setBackground(Color.white);
    }
    
    public boolean doneDrawing() {
        return super.vectorA != null && super.vectorB != null;
    }
    
    private void gotoPage1() {
        if (this.mode == 1) {
            super.theApplet.resetApplet();
        }
        else {
            this.setStep(2);
            this.placeFirstVector();
            this.repaint();
        }
    }
    
    private void placeFirstVector() {
        if (super.vectorB != null) {
            super.vectors.removeElement(super.vectorB);
        }
        super.vectorB = null;
        final int height = this.getSize().height;
        final int width = this.getSize().width;
        final Point2D.Double double1 = new Point2D.Double(width / 2, height / 2);
        final Point2D.Double double2 = new Point2D.Double(3 * width / 4, 3 * height / 4);
        if (super.vectorA != null) {
            super.vectors.removeElement(super.vectorA);
        }
        (super.vectorA = new Vector2d(double1, double2)).setColor(ScalarPanel.COLOR_A);
        super.vectorA.setTailChangable(false);
        super.vectorA.setSnapToZeroDist(0.0);
        super.vectorA.setMoveable(false);
        super.vectorA.showInfo = true;
        super.vectorA.setLabel((VectorLabelComponent)new VectorString("a", true));
        this.addVector(super.vectorA);
    }
    
    private void gotoPage2() {
        if (this.mode == 1) {
            this.setStep(2);
            this.placeFirstVector();
        }
        else {
            this.setStep(3);
            this.placeSecondVector();
            this.repaint();
            super.theApplet.enableContinue(false);
        }
    }
    
    private void placeSecondVector() {
        final Point2D.Double head2D = super.vectorA.getHead2D();
        final Point2D.Double tail2D = super.vectorA.getTail2D();
        final Point2D.Double double1 = new Point2D.Double(head2D.x, head2D.y);
        final Point2D.Double double2 = new Point2D.Double(tail2D.x, tail2D.y);
        final double length = super.vectorA.getLength();
        Point2D.Double double3;
        if ((head2D.x > tail2D.x && head2D.y > tail2D.y) || (head2D.x < tail2D.x && head2D.y < tail2D.y)) {
            double3 = new Point2D.Double(head2D.x + -35.0 / length * (tail2D.y - head2D.y), head2D.y + 35.0 / length * (tail2D.x - head2D.x));
        }
        else {
            double3 = new Point2D.Double(head2D.x + 35.0 / length * (tail2D.y - head2D.y), head2D.y + -35.0 / length * (tail2D.x - head2D.x));
        }
        final Point2D.Double double4 = double1;
        double4.x += double3.x - head2D.x;
        final Point2D.Double double5 = double1;
        double5.y += double3.y - head2D.y;
        final Point2D.Double double6 = double2;
        double6.x += double3.x - head2D.x;
        final Point2D.Double double7 = double2;
        double7.y += double3.y - head2D.y;
        if (super.vectorB != null) {
            super.vectors.removeElement(super.vectorB);
        }
        (super.vectorB = new Vector2d(double2, double1)).setColor(ScalarPanel.COLOR_B);
        super.vectorB.showInfo = true;
        super.vectorB.setLabel((VectorLabelComponent)new VectorString("b", true));
        super.vectorB.setTailChangable(false);
        super.vectorB.setSnapToZeroDist(0.0);
        super.vectorB.setDirectionFrozen(true);
        this.addVector(super.vectorB);
        super.vectorA.setMoveable(true);
        super.vectorA.setDirectionFrozen(true);
    }
    
    private void gotoPage3() {
        if (this.getStep() != 2) {
            this.gotoPage2();
        }
        this.setStep(3);
        this.placeSecondVector();
    }
    
    public void doCommand(final String s) {
        if (s.equals("reset")) {
            this.gotoPage1();
        }
        else {
            if (s.equals("page2")) {
                this.gotoPage2();
            }
            else if (s.equals("page3")) {
                this.gotoPage3();
            }
            else if (s.equals("page4") || s.equals("page5")) {
                if (this.getStep() != 3) {
                    this.gotoPage3();
                }
                this.setStep(4);
                final int height = this.getSize().height;
                final int width = this.getSize().width;
                final Point headPoint = new Point((int)(width * 0.75), (int)(height * 0.75));
                final Point tailPoint = new Point((int)(width * 0.4), (int)(height * 0.6));
                super.vectorA.setHeadPoint(headPoint);
                super.vectorA.setTailPoint(tailPoint);
                final Point point = new Point(headPoint.x, headPoint.y);
                final Point tailPoint2 = new Point(tailPoint.x, tailPoint.y);
                final double length = super.vectorA.getLength();
                Point point2;
                if ((headPoint.x > tailPoint.x && headPoint.y > tailPoint.y) || (headPoint.x < tailPoint.x && headPoint.y < tailPoint.y)) {
                    point2 = new Point((int)(headPoint.x + -35.0 / length * (tailPoint.y - headPoint.y)), (int)(headPoint.y + 35.0 / length * (tailPoint.x - headPoint.x)));
                }
                else {
                    point2 = new Point((int)(headPoint.x + 35.0 / length * (tailPoint.y - headPoint.y)), (int)(headPoint.y + -35.0 / length * (tailPoint.x - headPoint.x)));
                }
                point.translate(point2.x - headPoint.x, point2.y - headPoint.y);
                tailPoint2.translate(point2.x - headPoint.x, point2.y - headPoint.y);
                super.vectorB.setHeadPoint(new Point(tailPoint2.x - (point.x - tailPoint2.x), tailPoint2.y - (point.y - tailPoint2.y)));
                super.vectorB.setTailPoint(tailPoint2);
                super.vectorB.setLabel((VectorLabelComponent)new VectorString(new String[] { "b", "=-", "a" }, new String[] { "true", "false", "true" }, 3));
            }
            if (this.mode == 1) {
                this.enableContinue(true);
            }
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (this.isVectorAt(x, y)) {
                (super.currentVector = this.getVectorAt(x, y)).setSelected(true);
                this.repaint();
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (super.vectorB != null) {
            super.vectorB.setLabel((VectorLabelComponent)new VectorString("b", true));
        }
        super.mouseDragged(mouseEvent);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (this.inBounds(x, y)) {
                if (super.drawingNewVector) {
                    this.addVector(super.currentVector = new Vector2d(new Point2D.Double(x, y), new Point2D.Double(x, y)));
                    super.currentVector.setSelected(true);
                    super.currentVector.setHeadMoving(true);
                    super.mouseDownPoint = mouseEvent.getPoint();
                    super.mouseDown = true;
                    this.repaint();
                }
                else if (super.drawingNewLine) {
                    this.addVector(super.currentVector = new Vector2d(new Point2D.Double(x, y), new Point2D.Double(x, y), 2));
                    super.currentVector.setSelected(true);
                    super.currentVector.setHeadMoving(true);
                    super.mouseDownPoint = mouseEvent.getPoint();
                    super.mouseDown = true;
                    this.repaint();
                }
                else if (this.isVectorAt(x, y)) {
                    super.currentVector = this.getVectorAt(x, y);
                    for (int i = 0; i < super.vectors.size(); ++i) {
                        final Vector2d vector2d = super.vectors.elementAt(i);
                        if (vector2d.onHeadAnch(x, y) && vector2d.isHeadChangable()) {
                            vector2d.setHeadMoving(true);
                        }
                        else if (vector2d.onTailAnch(x, y) && vector2d.isTailChangable()) {
                            vector2d.setTailMoving(true);
                        }
                        else if (vector2d.isMoveable()) {
                            vector2d.setMoving(true);
                        }
                    }
                    super.mouseDownPoint = mouseEvent.getPoint();
                    super.mouseDown = true;
                    this.repaint();
                }
                else if (super.currentVector != null) {
                    super.currentVector = null;
                    this.repaint();
                }
            }
        }
        if (super.currentVector != null) {
            this.lastVector = ((super.currentVector == super.vectorA) ? 1 : 2);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            if (super.mouseDown) {
                super.mouseDown = false;
            }
            if (super.drawingNewVector) {
                super.drawingNewVector = false;
            }
            if (super.drawingNewLine) {
                super.drawingNewLine = false;
            }
            if (super.currentVector != null) {
                this.lastVector = ((super.currentVector == super.vectorA) ? 1 : 2);
                super.currentVector.setStill(true);
            }
            this.deselectAll();
            this.repaint();
        }
    }
    
    protected void paintClassSpecific(final Graphics graphics) {
        final int width = this.getBounds().width;
        final int height = this.getBounds().height;
        if (this.step == 1 && this.mode == 1 && super.titleImage != null) {
            graphics.clearRect(0, 0, width, height);
            graphics.drawImage(super.titleImage, 0, 0, this);
        }
        if (super.vectorA != null && super.vectorB != null) {
            final double length = super.vectorA.getLength();
            final double length2 = super.vectorB.getLength();
            final double direction = super.vectorA.getDirection();
            final double direction2 = super.vectorB.getDirection();
            graphics.setFont(VectorPanel.defaultFont);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final VectorString vectorString = new VectorString("a", true);
            if (this.lastVector == 1) {
                final double n = length / length2;
                final String string = " = " + this.round((Math.abs(direction - direction2) < 0.2) ? n : (-n)) + " * ";
                vectorString.draw(graphics, width / 2 - fontMetrics.stringWidth(string) / 2, height - 5);
                final int width2 = vectorString.getSize(graphics).width;
                graphics.drawString(string, width / 2 - fontMetrics.stringWidth(string) / 2 + width2, height - 5);
                vectorString.setString("b", true, 0);
                vectorString.draw(graphics, width / 2 + fontMetrics.stringWidth(string) / 2 + width2, height - 5);
            }
            else {
                final double n2 = length2 / length;
                final String string2 = " = " + this.round((Math.abs(direction2 - direction) < 0.2) ? n2 : (-n2)) + " * ";
                vectorString.setString("b", true, 0);
                vectorString.draw(graphics, width / 2 - fontMetrics.stringWidth(string2) / 2, height - 5);
                final int width3 = vectorString.getSize(graphics).width;
                graphics.drawString(string2, width / 2 - fontMetrics.stringWidth(string2) / 2 + width3, height - 5);
                vectorString.setString("a", true, 0);
                vectorString.draw(graphics, width / 2 + fontMetrics.stringWidth(string2) / 2 + width3, height - 5);
            }
        }
    }
    
    public int getStep() {
        return this.step;
    }
    
    public void setStep(final int step) {
        this.step = step;
        if (this.mode == 2 && this.step == 1) {
            this.gotoPage1();
        }
        this.repaint();
    }
    
    public Point translateToLine(final Point point, final Point point2, final Point point3) {
        float n;
        float n2;
        if (point2.x == point3.x && point2.y == point3.y) {
            n = (float)point2.x;
            n2 = (float)point2.y;
        }
        else if (point2.x == point3.x) {
            n = (float)point2.x;
            n2 = (float)point.y;
        }
        else if (point2.y == point3.y) {
            n = (float)point.x;
            n2 = (float)point2.y;
        }
        else {
            final float n3 = (point3.y - point2.y) / (float)(point3.x - point2.x);
            final float n4 = -1.0f / n3;
            n = (point.y - point2.y + n3 * point2.x - n4 * point.x) / (n3 - n4);
            n2 = n3 * (n - point2.x) + point2.y;
        }
        return new Point((int)(n + 0.5), (int)(n2 + 0.5));
    }
    
    static {
        COLOR_A = Color.orange;
        COLOR_B = Color.blue;
    }
}
