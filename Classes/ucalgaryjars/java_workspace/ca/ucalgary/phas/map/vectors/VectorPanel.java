// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import ca.ucalgary.phas.map.utilities.MapConstants;
import ca.ucalgary.phas.map.utilities.ComplexString;
import ca.ucalgary.phas.map.utilities.vectors.VectorLabelComponent;
import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.Image;
import java.util.Vector;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Font;
import java.awt.Color;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public abstract class VectorPanel extends JPanel implements MouseMotionListener, MouseListener
{
    protected Vector2d vectorA;
    protected Vector2d vectorB;
    protected Vector2d vectorC;
    protected Vector2d vectorSum;
    protected Vector2d vectorSub;
    protected Vector2d vectorAnswer;
    public static final Color COLOR_ANSWER;
    public static final Color COLOR_SELECTED;
    public static final Color COLOR_SUM;
    public static final Color COLOR_SUB;
    public static final Color COLOR_A;
    public static final Color COLOR_B;
    public static final Color COLOR_C;
    public static final Color COLOR_TEXT;
    public static final int NULLSTEP = 0;
    public static final int START = 1;
    protected static final Font defaultFont;
    protected boolean mouseDown;
    protected Point mouseDownPoint;
    protected boolean drawingNewVector;
    protected boolean drawingNewLine;
    protected boolean showDrawingTools;
    protected Cursor arrowCursor;
    protected Cursor crosshairCursor;
    protected Cursor moveCursor;
    protected Cursor deleteCursor;
    protected boolean deletingVector;
    protected int step;
    protected Vector vectors;
    protected Vector2d currentVector;
    protected VectorApplet theApplet;
    protected Image titleImage;
    public int showAnswer;
    Point2D.Double mouseCursorPoint;
    Vector2d mouseCursorVector;
    
    public VectorPanel(final VectorApplet theApplet, final String s) {
        this.mouseDown = false;
        this.mouseDownPoint = null;
        this.drawingNewVector = false;
        this.drawingNewLine = false;
        this.showDrawingTools = false;
        this.deletingVector = false;
        this.step = 1;
        this.currentVector = null;
        this.showAnswer = -1;
        this.mouseCursorPoint = new Point2D.Double();
        this.theApplet = theApplet;
        this.arrowCursor = new Cursor(0);
        this.crosshairCursor = new Cursor(1);
        this.moveCursor = new Cursor(13);
        this.deleteCursor = new Cursor(12);
        this.setCursor(this.arrowCursor);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.vectors = new Vector();
        theApplet.instantiateAdapter(s);
    }
    
    public void setTitleImage(final Image titleImage) {
        this.titleImage = titleImage;
    }
    
    public void addVector(final Vector2d obj) {
        this.vectors.addElement(obj);
    }
    
    public void clear() {
        this.clearSpecialVectors();
        this.vectors.removeAllElements();
        this.mouseDown = false;
        this.setCurrentVector(null);
        this.mouseDownPoint = null;
        this.setDrawingNewVector(false);
        this.repaint();
    }
    
    protected void clearSpecialVectors() {
        this.vectors.removeElement(this.vectorA);
        this.vectorA = null;
        this.vectors.removeElement(this.vectorB);
        this.vectorB = null;
        this.vectors.removeElement(this.vectorC);
        this.vectorC = null;
        this.vectors.removeElement(this.vectorSum);
        this.vectorSum = null;
        this.vectors.removeElement(this.vectorSub);
        this.vectorSub = null;
        this.vectors.removeElement(this.vectorAnswer);
        this.vectorAnswer = null;
    }
    
    public void deselectAll() {
        for (int i = 0; i < this.vectors.size(); ++i) {
            if (((Vector2d)this.vectors.elementAt(i)).isSelected()) {
                ((Vector2d)this.vectors.elementAt(i)).setSelected(false);
            }
        }
        this.deselectCurrentVector();
    }
    
    protected void deselectCurrentVector() {
        this.setCurrentVector(null);
    }
    
    public void doDraw() {
        this.theApplet.doDraw();
    }
    
    public void doDrawLine() {
        this.theApplet.doDrawLine();
    }
    
    public abstract boolean doneDrawing();
    
    public void enableContinue(final boolean b) {
        this.theApplet.enableContinue(b);
    }
    
    public void enableTools(final boolean b) {
        this.theApplet.enableTools(b);
    }
    
    public void freezeDrawnVectors() {
        if (this.vectorA != null) {
            this.vectorA.setChangable(false);
        }
        if (this.vectorB != null) {
            this.vectorB.setChangable(false);
        }
        if (this.vectorC != null) {
            this.vectorC.setChangable(false);
        }
    }
    
    public Vector2d getCurrentVector() {
        if (this.currentVector != null) {
            return this.currentVector;
        }
        return null;
    }
    
    public Vector2d getVector(final int index) {
        Vector2d vector2d = null;
        try {
            vector2d = this.vectors.elementAt(index);
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        if (vector2d != null) {
            return vector2d;
        }
        return null;
    }
    
    public abstract void doCommand(final String p0);
    
    public boolean inBounds(final Point2D.Double double1) {
        return this.inBounds((int)(double1.getX() + 0.5), (int)(double1.getX() + 0.5));
    }
    
    public boolean inBounds(final int n, final int n2) {
        return n >= 2 && n <= this.getSize().width - 3 && n2 >= 2 && n2 <= this.getSize().height - 3;
    }
    
    public abstract void mouseClicked(final MouseEvent p0);
    
    public abstract void mousePressed(final MouseEvent p0);
    
    public abstract void mouseReleased(final MouseEvent p0);
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (!this.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            if (this.mouseDown) {
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                if (this.inBounds(x, y)) {
                    if (this.currentVector.getHeadMoving()) {
                        this.currentVector.setHead((double)x, (double)y);
                    }
                    else if (this.currentVector.getTailMoving()) {
                        this.currentVector.setTail((double)x, (double)y);
                    }
                    else if (this.currentVector.getMoving()) {
                        final double n = x - this.mouseDownPoint.x;
                        final double n2 = y - this.mouseDownPoint.y;
                        final Point2D.Double head = new Point2D.Double(this.currentVector.getHead2D().x + n, this.currentVector.getHead2D().y + n2);
                        final Point2D.Double tail = new Point2D.Double(this.currentVector.getTail2D().x + n, this.currentVector.getTail2D().y + n2);
                        if (this.inBounds(head) && this.inBounds(tail)) {
                            this.currentVector.setHead(head);
                            this.currentVector.setTail(tail);
                            this.mouseDownPoint = mouseEvent.getPoint();
                        }
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
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.inBounds(x, y)) {
            this.setMouseCursor(x, y);
        }
    }
    
    public void setMouseCursor(final int n, final int n2) {
        if (!this.mouseDown) {
            this.mouseCursorPoint.x = n;
            this.mouseCursorPoint.y = n2;
            if (this.vectorAt(this.mouseCursorPoint)) {
                this.mouseCursorVector = this.getVectorAt(n, n2);
                if (this.deletingVector) {
                    this.setCursor(this.isDeletable(this.mouseCursorVector) ? this.deleteCursor : this.arrowCursor);
                }
                else if (this.drawingNewLine || this.drawingNewVector) {
                    this.setCursor(this.crosshairCursor);
                }
                else if ((this.mouseCursorVector.onTailAnch(this.mouseCursorPoint) && this.mouseCursorVector.isTailChangable()) || (this.mouseCursorVector.onHeadAnch(this.mouseCursorPoint) && this.mouseCursorVector.isHeadChangable())) {
                    this.setCursor(this.crosshairCursor);
                }
                else if (this.mouseCursorVector.isMoveable()) {
                    this.setCursor(this.moveCursor);
                }
                else {
                    this.setCursor(this.arrowCursor);
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
    
    public int getStep() {
        return this.step;
    }
    
    public abstract void setStep(final int p0);
    
    public void paint(final Graphics graphics) {
        graphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
        this.paintClassSpecific(graphics);
        for (int i = 0; i < this.vectors.size(); ++i) {
            try {
                ((Vector2d)this.vectors.elementAt(i)).paint(graphics);
            }
            catch (ArrayIndexOutOfBoundsException ex) {}
        }
    }
    
    protected void paintClassSpecific(final Graphics graphics) {
    }
    
    public void pleaseProcessEvent(final AWTEvent e) {
        this.processEvent(e);
    }
    
    public void deleteCurrentVector() {
        if (this.getCurrentVector() != null && this.isDeletable(this.getCurrentVector())) {
            this.removeSelectedVector();
        }
    }
    
    protected boolean isDeletable(final Vector2d vector2d) {
        return vector2d != this.vectorA && vector2d != this.vectorB && vector2d != this.vectorC && vector2d != this.vectorSum && vector2d != this.vectorSub;
    }
    
    public void removeSelectedVector() {
        final Vector2d currentVector = this.getCurrentVector();
        if (currentVector != null) {
            if (currentVector == this.vectorAnswer) {
                this.vectorAnswer = null;
                this.enableContinue(false);
            }
            this.vectors.removeElement(this.getCurrentVector());
            this.setCurrentVector(null);
            this.setDeletingVector(false);
            this.repaint();
        }
    }
    
    public void start() {
    }
    
    public void TremoveVector(final Vector2d obj) {
        this.vectors.removeElement(obj);
        this.setDeletingVector(false);
        this.repaint();
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
    
    public void setCurrentVectorAsNewAnswerVector(final VectorLabelComponent label) {
        if (this.currentVector != null) {
            if (this.vectorAnswer != null) {
                this.vectorAnswer.setNormal();
            }
            this.currentVector.setColor(VectorPanel.COLOR_ANSWER);
            this.currentVector.showInfo = true;
            this.currentVector.setLabel(label);
            this.vectorAnswer = this.currentVector;
            this.enableContinue(true);
            this.repaint();
        }
    }
    
    public void setDeletingVector(final boolean b) {
        if (b) {
            this.drawingNewVector = !b;
            this.theApplet.setDrawButtonSelected(!b);
            this.drawingNewLine = !b;
            this.theApplet.setDrawLineButtonSelected(!b);
        }
        this.deletingVector = b;
        this.theApplet.setDeleteButtonSelected(b);
    }
    
    public boolean isDeletingVector() {
        return this.deletingVector;
    }
    
    public void setDrawingNewLine(final boolean b) {
        if (b) {
            this.drawingNewVector = !b;
            this.theApplet.setDrawButtonSelected(!b);
            this.deletingVector = !b;
            this.theApplet.setDeleteButtonSelected(!b);
        }
        this.drawingNewLine = b;
        this.theApplet.setDrawLineButtonSelected(b);
    }
    
    public void setDrawingNewVector(final boolean b) {
        if (b) {
            this.drawingNewLine = !b;
            this.theApplet.setDrawLineButtonSelected(!b);
            this.deletingVector = !b;
            this.theApplet.setDeleteButtonSelected(!b);
        }
        this.drawingNewVector = b;
        this.theApplet.setDrawButtonSelected(b);
    }
    
    public void setVectorSub(final Vector2d vectorSub) {
        if (this.vectorSub != null) {
            this.vectorSub.setNormal();
        }
        this.addVector(vectorSub);
        this.vectorSub = vectorSub;
    }
    
    public void setVectorSum(final Vector2d vectorSum) {
        if (this.vectorSum != null) {
            this.vectorSum.setNormal();
        }
        this.addVector(vectorSum);
        this.vectorSum = vectorSum;
    }
    
    public void setVectorA() {
        if (this.currentVector != null && this.currentVector.getStyle() != 1 && this.currentVector != this.vectorA) {
            this.currentVector.setColor(VectorPanel.COLOR_A);
            this.currentVector.showInfo = true;
            this.currentVector.setLabel((VectorLabelComponent)new ComplexString("<vector:a>"));
            if (this.vectorA != null) {
                this.vectorA.setNormal();
            }
            this.vectorA = this.currentVector;
            this.repaint();
        }
    }
    
    public void setVectorB() {
        if (this.currentVector != null && this.currentVector.getStyle() != 1 && this.currentVector != this.vectorB) {
            this.currentVector.setColor(VectorPanel.COLOR_B);
            this.currentVector.showInfo = true;
            this.currentVector.setLabel((VectorLabelComponent)new ComplexString("<vector:b>"));
            if (this.vectorB != null) {
                this.vectorB.setNormal();
            }
            this.vectorB = this.currentVector;
            this.repaint();
        }
    }
    
    public void setVectorC() {
        if (this.currentVector != null && this.currentVector.getStyle() != 1 && this.currentVector != this.vectorC) {
            this.currentVector.setColor(VectorPanel.COLOR_C);
            this.currentVector.showInfo = true;
            this.currentVector.setLabel((VectorLabelComponent)new ComplexString("<vector:c>"));
            if (this.vectorC != null) {
                this.vectorC.setNormal();
            }
            this.vectorC = this.currentVector;
            this.repaint();
        }
    }
    
    public void suspendDemo() {
        this.theApplet.suspendDemo();
    }
    
    public Vector2d theVectorAt(final Point2D.Double double1) {
        return this.getVectorAt(double1);
    }
    
    public Vector2d getVectorAt(final Point2D.Double double1) {
        return this.getVectorAt((int)(double1.getX() + 0.5), (int)(double1.getY() + 0.5));
    }
    
    public Vector2d getVectorAt(final int n, final int n2) {
        for (int i = this.vectors.size() - 1; i >= 0; --i) {
            final Vector2d vector2d = this.vectors.elementAt(i);
            if (vector2d.checkPoint(n, n2) && vector2d.isVisible()) {
                return vector2d;
            }
        }
        return null;
    }
    
    public boolean vectorAt(final Point2D.Double double1) {
        return this.isVectorAt(double1);
    }
    
    public boolean isVectorAt(final Point2D.Double double1) {
        return this.isVectorAt((int)(double1.getX() + 0.5), (int)(double1.getY() + 0.5));
    }
    
    public boolean isVectorAt(final int n, final int n2) {
        for (int i = 0; i < this.vectors.size(); ++i) {
            final Vector2d vector2d = this.vectors.elementAt(i);
            if (vector2d.checkPoint(n, n2) && vector2d.isVisible()) {
                return true;
            }
        }
        return false;
    }
    
    static {
        COLOR_ANSWER = Color.blue;
        COLOR_SELECTED = Color.black;
        COLOR_SUM = Color.green;
        COLOR_SUB = Color.green;
        COLOR_A = Color.red;
        COLOR_B = Color.orange;
        COLOR_C = Color.yellow;
        COLOR_TEXT = Color.black;
        defaultFont = MapConstants.vectorDefaultFont;
    }
}
