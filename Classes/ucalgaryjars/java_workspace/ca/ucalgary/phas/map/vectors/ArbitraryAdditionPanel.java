// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Enumeration;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Image;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;
import java.awt.Color;

public class ArbitraryAdditionPanel extends VectorPanel
{
    private static final Color VECTOR_FILL_COLOR;
    private static final Color VECTOR_OUTLINE_COLOR;
    public Vector2d resultantVector;
    private int specifyMode;
    private Vector2d v;
    private Image navAxisImage;
    private int originDirection;
    private int vDirectionFromOrigin;
    private boolean mouseDownOnTip;
    private Vector2d tipMovingVector;
    
    public ArbitraryAdditionPanel(final VectorApplet vectorApplet) {
        super(vectorApplet, "specifyvectorapplet");
    }
    
    public void start() {
    }
    
    public void setStep(final int n) {
        this.repaint();
    }
    
    public void calculateAnswer() {
    }
    
    public void calculateAnswer(final Point point) {
    }
    
    public boolean doneDrawing() {
        return true;
    }
    
    public void doCommand(final String s) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            if (this.isVectorAt(mouseEvent.getX(), mouseEvent.getY())) {
                super.theApplet.selectVectorInScrollPane(this.v);
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (this.inBounds(x, y)) {
                if (super.mouseDown) {
                    final double n = x - super.mouseDownPoint.x;
                    final double n2 = y - super.mouseDownPoint.y;
                    final Enumeration<Vector2d> elements = (Enumeration<Vector2d>)super.vectors.elements();
                    while (elements.hasMoreElements()) {
                        final Vector2d vector2d = elements.nextElement();
                        if (vector2d != null) {
                            final Point2D.Double head2D = vector2d.getHead2D();
                            final Point2D.Double tail2D = vector2d.getTail2D();
                            final double n3 = head2D.x + n;
                            final double n4 = head2D.y + n2;
                            final double n5 = tail2D.x + n;
                            final double n6 = tail2D.y + n2;
                            vector2d.setHead(n3, n4);
                            vector2d.setTail(n5, n6);
                        }
                    }
                    super.theApplet.updateResultantVector();
                }
                else if (this.mouseDownOnTip) {
                    final Point2D.Double head2D2 = this.tipMovingVector.getHead2D();
                    final double n7 = x - head2D2.x;
                    final double n8 = y - head2D2.y;
                    this.tipMovingVector.setHead((double)x, (double)y);
                    super.theApplet.vectorTipHasMoved(this.tipMovingVector, n7, n8);
                }
                super.mouseDownPoint = mouseEvent.getPoint();
            }
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (this.inBounds(x, y)) {
                this.tipMovingVector = this.getVectorTipAt(x, y);
                if (this.tipMovingVector != null) {
                    this.tipMovingVector.setHeadMoving(true);
                    this.mouseDownOnTip = true;
                }
                else if (this.isVectorAt(x, y)) {
                    super.mouseDown = true;
                }
                super.mouseDownPoint = mouseEvent.getPoint();
                this.repaint();
            }
        }
    }
    
    public Vector2d getVectorTipAt(final int n, final int n2) {
        for (int i = 0; i < super.vectors.size(); ++i) {
            if (((Vector2d)super.vectors.elementAt(i)).onHeadAnch(n, n2) && super.vectors.elementAt(i) != this.resultantVector) {
                return (Vector2d)super.vectors.elementAt(i);
            }
        }
        return null;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            if (super.mouseDown) {
                super.mouseDown = false;
            }
            if (this.mouseDownOnTip) {
                this.mouseDownOnTip = false;
                this.tipMovingVector.setHeadMoving(false);
            }
            if (super.drawingNewVector) {
                super.drawingNewVector = false;
            }
            if (super.currentVector != null) {
                super.currentVector.setStill(true);
            }
            this.deselectAll();
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        this.resultantVector.paint(graphics);
    }
    
    protected void deselectCurrentVector() {
    }
    
    static {
        VECTOR_FILL_COLOR = Color.orange;
        VECTOR_OUTLINE_COLOR = Color.black;
    }
}
