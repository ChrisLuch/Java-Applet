// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import javax.swing.AbstractButton;
import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import ca.ucalgary.phas.map.utilities.vectors.VectorLabelComponent;
import ca.ucalgary.phas.map.utilities.ComplexString;
import java.awt.geom.Point2D;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Color;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class VectorCompsPanel extends JPanel implements MouseMotionListener, MouseListener
{
    protected Vector2d vectorA;
    protected Vector2d vectorA1;
    protected Vector2d vectorA2;
    protected Vector2d vectorSum;
    protected Vector2d vectorX;
    protected Vector2d vectorY;
    public static final Color ANCHOR_COLOR;
    public static final Color VA_COLOR;
    public static final Color VA1_COLOR;
    public static final Color VA2_COLOR;
    public static final int L_ERROR = 5;
    public static final double D_ERROR = 0.06981317007977318;
    public static final double A_ERROR = 5.0;
    protected Cursor arrowCursor;
    protected Cursor crosshairCursor;
    protected Cursor moveCursor;
    protected Cursor deleteCursor;
    VectorCompsApplet theApplet;
    private Font font;
    int panelWidth;
    int panelHeight;
    Color panelColor;
    private boolean drawingNewVector;
    private boolean drawingNewLine;
    private boolean drawingNewDLine;
    private boolean showDrawingTools;
    private boolean deletingVector;
    private Vector vectors;
    public Vector2d currentVector;
    private Random rand;
    private boolean showsum;
    private double angle;
    private int zoom;
    private Point sumPoint;
    private Point initPoint;
    private Point mouseDownPoint;
    private boolean mouseDown;
    
    public VectorCompsPanel(final VectorCompsApplet theApplet, final boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        this.panelColor = Color.white;
        this.drawingNewVector = false;
        this.drawingNewLine = false;
        this.drawingNewDLine = false;
        this.showDrawingTools = false;
        this.deletingVector = false;
        this.currentVector = null;
        this.rand = new Random();
        this.showsum = false;
        this.angle = 0.0;
        this.mouseDown = false;
        this.theApplet = theApplet;
        this.arrowCursor = new Cursor(0);
        this.crosshairCursor = new Cursor(1);
        this.moveCursor = new Cursor(13);
        this.deleteCursor = new Cursor(12);
        this.setCursor(this.arrowCursor);
    }
    
    public void addVector(final Vector2d obj) {
        this.vectors.addElement(obj);
    }
    
    public boolean allVectorsDrawn() {
        return this.vectorA1 != null && this.vectorA2 != null;
    }
    
    public void calcAngle() {
        double direction = this.vectorA1.getDirection();
        if (direction < 0.0) {
            direction += 6.283185307179586;
        }
        double direction2 = this.vectorA2.getDirection();
        if (direction2 < 0.0) {
            direction2 += 6.283185307179586;
        }
        double n;
        if (direction > direction2) {
            n = direction - direction2;
        }
        else {
            n = direction2 - direction;
        }
        if (n > 3.141592653589793) {
            n = 6.283185307179586 - n;
        }
        this.angle = this.round(n / 3.141592653589793 * 180.0);
    }
    
    public Vector2d calcDemoSum(final Point2D.Double double1, final Vector2d vector2d, final Vector2d vector2d2) {
        final int n = (int)(vector2d.getHead2D().x - vector2d.getTail2D().x + (vector2d2.getHead2D().x - vector2d2.getTail2D().x) + 0.5);
        final int n2 = (int)(vector2d.getHead2D().y - vector2d.getTail2D().y + (vector2d2.getHead2D().y - vector2d2.getTail2D().y) + 0.5);
        final int n3 = (int)double1.x;
        final int n4 = (int)double1.y;
        return new Vector2d(new Point2D.Double(n3, n4), new Point2D.Double(n3 + n, n4 + n2));
    }
    
    public void calcSum(final Point point) {
        final int n = (int)(this.vectorA1.getHead2D().x - this.vectorA1.getTail2D().x + (this.vectorA2.getHead2D().x - this.vectorA2.getTail2D().x) + 0.5);
        final int n2 = (int)(this.vectorA1.getHead2D().y - this.vectorA1.getTail2D().y + (this.vectorA2.getHead2D().y - this.vectorA2.getTail2D().y) + 0.5);
        final int x = point.x;
        final int y = point.y;
        if (this.vectorSum != null) {
            this.vectors.removeElement(this.vectorSum);
        }
        (this.vectorSum = new Vector2d(new Point2D.Double(x, y), new Point2D.Double(x + n, y + n2))).setColor(Color.red);
        this.vectorSum.setChangable(false);
        this.vectorSum.setMoveable(false);
        this.addVector(this.vectorSum);
    }
    
    public boolean correct() {
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        final int n = (int)this.vectorA.getLength();
        final int n2 = (int)this.vectorSum.getLength();
        if (n2 > n - 5 && n2 < n + 5) {
            b2 = true;
        }
        final double round = this.round(this.vectorA.getDirection());
        final double round2 = this.round(this.vectorSum.getDirection());
        if (round2 > round - 0.06981317007977318 && round2 < round + 0.06981317007977318) {
            b3 = true;
        }
        if (this.angle > 85.0 && this.angle < 95.0) {
            b4 = true;
        }
        if (b2 && b3 && b4) {
            b = true;
        }
        return b;
    }
    
    public void deselectAll() {
        for (int i = 0; i < this.vectors.size(); ++i) {
            if (((Vector2d)this.vectors.elementAt(i)).isSelected()) {
                ((Vector2d)this.vectors.elementAt(i)).setSelected(false);
            }
        }
        this.currentVector = null;
    }
    
    public void doCancel() {
        this.deselectAll();
        this.drawingNewVector = false;
        this.drawingNewLine = false;
        this.drawingNewDLine = false;
    }
    
    public void doDelete() {
        if (this.getCurrentVector() != null) {
            this.removeSelectedVector();
            ((AbstractButton)this.theApplet.deleteButton).setEnabled(false);
            if (this.allVectorsDrawn()) {
                this.showsum = true;
            }
            else {
                this.showsum = false;
            }
            this.repaint();
        }
    }
    
    public void doDemo1() {
        this.initPoint.setLocation(this.panelWidth / 2, this.panelHeight / 2);
        final Point2D.Double double1 = new Point2D.Double(this.initPoint.x, this.initPoint.y);
        this.zoom = this.panelWidth / 3;
        final Point2D.Double translatePoint = this.translatePoint(double1, -0.5235987755982988, this.panelWidth / 2.0);
        final Point2D.Double translatePoint2 = this.translatePoint(double1, 2.6179938779914944, this.panelWidth / 2.0);
        if (this.vectorX != null) {
            this.vectors.removeElement(this.vectorX);
        }
        this.addVector(this.vectorX = new Vector2d(translatePoint, translatePoint2, 1));
        this.vectorX.setChangable(false);
        this.vectorX.setMoveable(false);
        final Point2D.Double translatePoint3 = this.translatePoint(double1, -2.0943951023931953, this.panelWidth / 2.0);
        final Point2D.Double translatePoint4 = this.translatePoint(double1, 1.0471975511965979, this.panelWidth / 2.0);
        if (this.vectorY != null) {
            this.vectors.removeElement(this.vectorY);
        }
        this.addVector(this.vectorY = new Vector2d(translatePoint4, translatePoint3, 1));
        this.vectorY.setChangable(false);
        this.vectorY.setMoveable(false);
        this.sumPoint.setLocation(this.initPoint);
        if (this.vectorA != null) {
            this.vectors.removeElement(this.vectorA);
        }
        this.addVector(this.vectorA = this.getNewVector(this.zoom, 4.71238898038469));
        this.vectorA.setChangable(false);
        this.vectorA.setMoveable(false);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector:W>"));
        this.vectorA.showInfo = true;
        this.vectorA.setColor(VectorCompsPanel.VA_COLOR);
    }
    
    public void doDemo2() {
        this.zoom = this.panelWidth / 3;
        this.sumPoint.setLocation(this.initPoint);
        final double n = 1.9634954084936207;
        final Point2D.Double double1 = new Point2D.Double(this.panelWidth / 2, this.panelHeight * 2 / 3);
        final Vector2d newVector = this.getNewVector(this.zoom, n, double1);
        final Point2D.Double double2 = new Point2D.Double(newVector.getHead2D().x, newVector.getHead2D().y);
        final Vector2d newVector2 = this.getNewVector(this.zoom / 2, n + 1.5707963267948966, double1);
        if (this.vectorA != null) {
            this.vectors.removeElement(this.vectorA);
        }
        this.addVector(this.vectorA = this.calcDemoSum(double1, newVector, newVector2));
        this.vectorA.setChangable(false);
        this.vectorA.setMoveable(true);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector:A>"));
        this.vectorA.setLabelPosition(0.7);
        this.vectorA.showInfo = true;
        this.vectorA.setColor(VectorCompsPanel.VA_COLOR);
    }
    
    public void doDraw() {
        this.deselectAll();
        if (this.theApplet.mode != 2) {
            this.drawingNewVector = true;
        }
        else if (!this.allVectorsDrawn()) {
            this.drawingNewVector = true;
        }
    }
    
    public void doDrawDLine() {
        this.deselectAll();
        this.drawingNewDLine = true;
    }
    
    public void doDrawLine() {
        this.deselectAll();
        this.drawingNewLine = true;
    }
    
    public void doSim() {
        if (this.vectorA == null) {
            this.zoom = this.panelWidth / 2;
            this.initPoint.setLocation(this.panelWidth / 2, this.panelHeight / 2);
            this.sumPoint.setLocation(2 * this.panelWidth / 3, 2 * this.panelHeight / 3);
            if (this.vectorA != null) {
                this.vectors.removeElement(this.vectorA);
            }
            this.addVector(this.vectorA = this.getRandomVector());
            this.vectorA.setChangable(false);
            this.vectorA.setMoveable(true);
            this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector:A>"));
            this.vectorA.showInfo = true;
            this.vectorA.setColor(VectorCompsPanel.VA_COLOR);
        }
    }
    
    public Vector2d getCurrentVector() {
        if (this.currentVector != null) {
            return this.currentVector;
        }
        return null;
    }
    
    public Vector2d getNewVector(final double n, final double n2) {
        final Point2D.Double double1 = new Point2D.Double(this.initPoint.x, this.initPoint.y);
        return new Vector2d(double1, this.translatePoint(double1, n2, n));
    }
    
    public Vector2d getNewVector(final double n, final double n2, final Point2D.Double double1) {
        return new Vector2d(double1, this.translatePoint(double1, n2, n));
    }
    
    public double getRandomAngle() {
        return (int)(this.rand.nextFloat() * 12.0f) * 3.141592653589793 / 6.0;
    }
    
    public Vector2d getRandomVector() {
        final double n = ((int)(this.rand.nextFloat() * 3.0f) + 3) * this.zoom / 4;
        final double n2 = this.getRandomAngle() + 0.2617993877991494;
        final Point2D.Double double1 = new Point2D.Double(this.zoom, this.zoom);
        return new Vector2d(double1, this.translatePoint(double1, n2, n));
    }
    
    public boolean inBounds(final Point point) {
        return point.x >= 2 && point.x <= this.getSize().width - 3 && point.y >= 2 && point.y <= this.getSize().height - 3;
    }
    
    public void init() {
        final Dimension dimension = new Dimension();
        while (this.theApplet.getSize() == null) {}
        final Dimension size = this.theApplet.getSize();
        while (this.theApplet.controlPanel.getBounds() == null) {}
        this.panelWidth = size.width;
        this.panelHeight = size.height - 60;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.vectors = new Vector();
        this.zoom = this.panelWidth / 3;
        this.initPoint = new Point(this.panelWidth / 2, this.panelHeight / 2);
        this.sumPoint = new Point(this.initPoint);
        if (this.theApplet.mode == 0) {
            this.doDemo1();
        }
        else if (this.theApplet.mode == 1) {
            this.doDemo2();
        }
        else {
            this.doSim();
        }
        this.repaint();
    }
    
    public Vector2d getVectorA() {
        return this.vectorA;
    }
    
    public Vector2d getVectorA1() {
        return this.vectorA1;
    }
    
    public Vector2d getVectorA2() {
        return this.vectorA2;
    }
    
    public Vector2d getVectorX() {
        return this.vectorX;
    }
    
    public Vector2d getVectorY() {
        return this.vectorY;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.theApplet.mode == 2 || mouseEvent.isControlDown()) {
            final Point point = mouseEvent.getPoint();
            if (this.inBounds(point)) {
                if (this.drawingNewLine) {
                    this.repaint();
                }
                if (this.drawingNewDLine) {
                    this.repaint();
                }
                if (this.drawingNewVector) {
                    this.repaint();
                }
                else if (this.vectorAt(point)) {
                    this.currentVector = this.theVectorAt(point);
                    if (this.theApplet.mode == 2 && this.currentVector != this.vectorA) {
                        this.currentVector.setSelected(true);
                        ((AbstractButton)this.theApplet.deleteButton).setEnabled(true);
                    }
                    this.repaint();
                }
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.theApplet.mode == 2 || mouseEvent.isControlDown()) {
            if (this.mouseDown) {
                final Point point = mouseEvent.getPoint();
                if (this.inBounds(point)) {
                    if (this.currentVector.getHeadMoving()) {
                        this.currentVector.setHeadPoint(point);
                    }
                    else if (this.currentVector.getTailMoving()) {
                        this.currentVector.setTailPoint(point);
                    }
                    else if (this.currentVector.getMoving()) {
                        final int n = point.x - this.mouseDownPoint.x;
                        final int n2 = point.y - this.mouseDownPoint.y;
                        this.currentVector.setTail(new Point2D.Double(this.currentVector.getTail2D().x + n, this.currentVector.getTail2D().y + n2));
                        this.currentVector.setHead(new Point2D.Double(this.currentVector.getHead2D().x + n, this.currentVector.getHead2D().y + n2));
                        this.mouseDownPoint = point;
                    }
                }
            }
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.inBounds(mouseEvent.getX(), mouseEvent.getY())) {
            final Point point = mouseEvent.getPoint();
            if (this.vectorAt(point)) {
                final Vector2d theVector = this.theVectorAt(point);
                if ((theVector.onTailAnch(point) && theVector.isTailChangable()) || (theVector.onHeadAnch(point) && theVector.isHeadChangable())) {
                    this.setCursor(this.crosshairCursor);
                }
                else {
                    this.setCursor(this.moveCursor);
                }
            }
            else if (this.drawingNewLine || this.drawingNewVector) {
                this.setCursor(this.crosshairCursor);
            }
            else {
                this.setCursor(this.arrowCursor);
            }
        }
    }
    
    public boolean inBounds(final int n, final int n2) {
        return n >= 2 && n <= this.getSize().width - 3 && n2 >= 2 && n2 <= this.getSize().height - 3;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.theApplet.mode == 2 || mouseEvent.isControlDown()) {
            final Point point = mouseEvent.getPoint();
            final Point2D.Double double1 = new Point2D.Double(mouseEvent.getPoint().x, mouseEvent.getPoint().y);
            if (this.inBounds(point)) {
                if (this.drawingNewLine) {
                    this.addVector(this.currentVector = new Vector2d(double1, double1, 1));
                    this.currentVector.setAnchorColors(VectorCompsPanel.ANCHOR_COLOR);
                    this.currentVector.setSelected(true);
                    this.currentVector.setHeadMoving(true);
                    this.mouseDownPoint = point;
                    this.mouseDown = true;
                    this.repaint();
                }
                else if (this.drawingNewDLine) {
                    this.addVector(this.currentVector = new Vector2d(double1, double1, 2));
                    this.currentVector.setAnchorColors(VectorCompsPanel.ANCHOR_COLOR);
                    this.currentVector.setSelected(true);
                    this.currentVector.setHeadMoving(true);
                    this.mouseDownPoint = point;
                    this.mouseDown = true;
                    this.repaint();
                }
                else if (this.drawingNewVector) {
                    this.addVector(this.currentVector = new Vector2d(double1, double1));
                    this.currentVector.setAnchorColors(VectorCompsPanel.ANCHOR_COLOR);
                    this.currentVector.setSelected(true);
                    this.currentVector.setHeadMoving(true);
                    this.mouseDownPoint = point;
                    this.mouseDown = true;
                    this.repaint();
                }
                else if (this.theApplet.mode == 2) {
                    if (this.vectorAt(point)) {
                        this.currentVector = this.theVectorAt(point);
                        for (int i = 0; i < this.vectors.size(); ++i) {
                            final Vector2d vector2d = this.vectors.elementAt(i);
                            if (vector2d.onHeadAnch(point) && vector2d.isHeadChangable()) {
                                vector2d.setHeadMoving(true);
                            }
                            else if (vector2d.onTailAnch(point) && vector2d.isTailChangable()) {
                                vector2d.setTailMoving(true);
                            }
                            else if (vector2d.isMoveable()) {
                                vector2d.setMoving(true);
                            }
                        }
                        this.mouseDownPoint = point;
                        this.mouseDown = true;
                    }
                    else if (this.currentVector != null) {
                        this.currentVector = null;
                    }
                }
            }
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.theApplet.mode == 2 || mouseEvent.isControlDown()) {
            if (this.mouseDown) {
                this.mouseDown = false;
            }
            if (this.drawingNewVector) {
                if (this.theApplet.mode != 2) {
                    if (this.vectorA == null) {
                        (this.vectorA = this.currentVector).setLabel((VectorLabelComponent)new ComplexString("<vector:A>"));
                        this.vectorA.showInfo = true;
                        this.vectorA.setChangable(false);
                        this.vectorA.setMoveable(false);
                        this.vectorA.setColor(VectorCompsPanel.VA_COLOR);
                    }
                    else if (this.vectorA1 == null) {
                        (this.vectorA1 = this.currentVector).setLabel((VectorLabelComponent)new ComplexString("<vector:A><subscript>1"));
                        this.vectorA1.setChangable(false);
                        this.vectorA1.setMoveable(false);
                        this.vectorA1.showInfo = true;
                        this.vectorA1.setColor(VectorCompsPanel.VA1_COLOR);
                    }
                    else if (this.vectorA2 == null) {
                        (this.vectorA2 = this.currentVector).setLabel((VectorLabelComponent)new ComplexString("<vector:A><subscript>2"));
                        this.vectorA2.setChangable(false);
                        this.vectorA2.setMoveable(false);
                        if (this.theApplet.mode == 0) {
                            this.vectorA2.setLabelSide(0);
                        }
                        this.vectorA2.showInfo = true;
                        this.vectorA2.setColor(VectorCompsPanel.VA2_COLOR);
                    }
                    else if (this.vectorY == null) {
                        (this.vectorY = this.currentVector).setLabel((VectorLabelComponent)new ComplexString("<vector:y>"));
                        this.vectorY.setChangable(false);
                        this.vectorY.setMoveable(false);
                        this.vectorY.showInfo = true;
                    }
                    else if (this.vectorX == null) {
                        (this.vectorX = this.currentVector).setLabel((VectorLabelComponent)new ComplexString("<vector:x>"));
                        this.vectorX.setChangable(false);
                        this.vectorX.setMoveable(false);
                        this.vectorX.showInfo = true;
                    }
                }
                else if (this.vectorA1 == null) {
                    this.currentVector.setAnchorColors(VectorCompsPanel.ANCHOR_COLOR);
                    this.vectorA1 = this.currentVector;
                }
                else {
                    if (this.vectorA2 == null) {
                        this.currentVector.setAnchorColors(VectorCompsPanel.ANCHOR_COLOR);
                        this.vectorA2 = this.currentVector;
                    }
                    this.drawingNewVector = false;
                    if (this.theApplet.cancelButton != null) {
                        ((AbstractButton)this.theApplet.cancelButton).setSelected(true);
                    }
                    if (this.theApplet.mode == 2 && this.allVectorsDrawn()) {
                        this.calcSum(this.sumPoint);
                        this.showsum = true;
                        if (this.correct()) {
                            this.vectorSum.setColor(Color.green);
                        }
                    }
                }
            }
            if (this.drawingNewLine) {
                if (this.theApplet.mode != 2) {
                    if (this.vectorY == null) {
                        this.currentVector.setChangable(false);
                        this.currentVector.setMoveable(false);
                        this.vectorY = this.currentVector;
                    }
                    else if (this.vectorX == null) {
                        this.currentVector.setChangable(false);
                        this.currentVector.setMoveable(false);
                        this.vectorX = this.currentVector;
                    }
                }
                this.drawingNewLine = false;
                if (this.theApplet.cancelButton != null) {
                    ((AbstractButton)this.theApplet.cancelButton).setSelected(true);
                }
            }
            if (this.drawingNewDLine) {
                this.drawingNewDLine = false;
                if (this.theApplet.cancelButton != null) {
                    ((AbstractButton)this.theApplet.cancelButton).setSelected(true);
                }
            }
            if (this.currentVector != null) {
                this.currentVector.setStill(true);
                if (this.theApplet.mode == 2 && this.allVectorsDrawn()) {
                    this.calcSum(this.sumPoint);
                    this.showsum = true;
                    if (this.correct()) {
                        this.vectorSum.setColor(Color.green);
                    }
                }
            }
            this.deselectAll();
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getBounds().width;
        final int height = this.getBounds().height;
        graphics.setColor(this.panelColor);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.black);
        for (int i = 0; i < this.vectors.size(); ++i) {
            try {
                ((Vector2d)this.vectors.elementAt(i)).paint(graphics);
            }
            catch (ArrayIndexOutOfBoundsException ex) {}
        }
        if (this.vectorX != null) {
            graphics.drawString("x", (int)this.vectorX.getTail2D().x + 5, (int)this.vectorX.getTail2D().y + 5);
        }
        if (this.vectorY != null) {
            graphics.drawString("y", (int)this.vectorY.getTail2D().x + 5, (int)this.vectorY.getTail2D().y - 5);
        }
        if (this.showsum) {
            this.calcAngle();
            graphics.setColor(Color.black);
            graphics.drawString("angle between vectors =  " + this.angle + " degrees", 20, height - 4);
        }
        graphics.setColor(Color.white);
        graphics.drawRect(0, 0, width - 1, height - 1);
        graphics.setColor(Color.black);
        graphics.drawRect(1, 1, width - 3, height - 3);
        graphics.setColor(Color.lightGray);
        graphics.drawRect(0, 0, width - 2, height - 2);
        graphics.setColor(Color.darkGray);
        graphics.drawLine(0, 0, 0, height - 1);
        graphics.drawLine(0, 0, width - 1, 0);
        graphics.setColor(Color.black);
    }
    
    public void pleaseProcessEvent(final AWTEvent e) {
        this.processEvent(e);
    }
    
    public void removeSelectedVector() {
        if (this.currentVector != null) {
            if (this.currentVector != this.vectorA && this.currentVector != this.vectorA1 && this.currentVector != this.vectorA2 && this.currentVector != this.vectorSum && this.currentVector != this.vectorX && this.currentVector != this.vectorY) {
                this.vectors.removeElement(this.currentVector);
            }
            else {
                if (this.currentVector == this.vectorA1) {
                    this.vectors.removeElement(this.vectorA1);
                    this.vectorA1 = null;
                    if (this.vectorSum != null) {
                        this.vectors.removeElement(this.vectorSum);
                        this.vectorSum = null;
                    }
                }
                if (this.currentVector == this.vectorA2) {
                    this.vectors.removeElement(this.vectorA2);
                    this.vectorA2 = null;
                    if (this.vectorSum != null) {
                        this.vectors.removeElement(this.vectorSum);
                        this.vectorSum = null;
                    }
                }
            }
            this.currentVector = null;
            this.deletingVector = false;
            this.repaint();
        }
    }
    
    public double round(final double n) {
        if (n >= 100.0) {
            return (int)n;
        }
        if (n >= 10.0) {
            return (int)(10.0 * n + 0.5) / 10.0;
        }
        if (n >= 0.0) {
            return (int)(100.0 * n + 0.5) / 100.0;
        }
        if (n <= -100.0) {
            return (int)n;
        }
        if (n <= -10.0) {
            return (int)(10.0 * n - 0.5) / 10.0;
        }
        if (n < 0.0) {
            return (int)(100.0 * n - 0.5) / 100.0;
        }
        return n;
    }
    
    public void setCurrentVector(final Vector2d currentVector) {
        this.currentVector = currentVector;
    }
    
    public Vector2d theVectorAt(final Point point) {
        for (int i = 0; i < this.vectors.size(); ++i) {
            if (((Vector2d)this.vectors.elementAt(i)).checkPoint(point.x, point.y)) {
                return (Vector2d)this.vectors.elementAt(i);
            }
        }
        return null;
    }
    
    public Point2D.Double translatePoint(final Point2D.Double double1, final double n, final double n2) {
        return new Point2D.Double(double1.getX() + Math.cos(n) * n2, double1.getY() - Math.sin(n) * n2);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean vectorAt(final Point point) {
        for (int i = 0; i < this.vectors.size(); ++i) {
            if (((Vector2d)this.vectors.elementAt(i)).checkPoint(point.x, point.y)) {
                return true;
            }
        }
        return false;
    }
    
    static {
        ANCHOR_COLOR = Color.red;
        VA_COLOR = Color.black;
        VA1_COLOR = Color.red;
        VA2_COLOR = Color.orange;
    }
}
