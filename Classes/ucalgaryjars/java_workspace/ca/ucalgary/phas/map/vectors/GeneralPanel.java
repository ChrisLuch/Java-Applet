// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import ca.ucalgary.phas.map.utilities.vectors.VectorUtil;
import java.awt.image.ImageObserver;
import ca.ucalgary.phas.map.utilities.MapConstants;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Point;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;
import java.awt.geom.Point2D;
import java.awt.Color;

public class GeneralPanel extends VectorPanel
{
    public static final int ARC_STEP = 2;
    public static final int DRAWING_VECTOR = 3;
    public static final Color VECTORCOLOR;
    protected boolean singleMode;
    
    public GeneralPanel(final VectorApplet vectorApplet, final boolean singleMode) {
        super(vectorApplet, "basicvectorapplet");
        this.setSingleMode(singleMode);
    }
    
    public void setStep(int n) {
        if (this.isSingleMode()) {
            n = 2;
        }
        if (n == 0) {
            if (super.currentVector == null || super.step == 2) {
                super.step = n;
                final double n2 = this.getSize().height;
                final double n3 = this.getSize().width;
                this.clear();
                final Vector2d currentVector = new Vector2d(new Point2D.Double(n3 / 3.0, 3.0 * n2 / 4.0), new Point2D.Double(4.0 * n3 / 5.0, n2 / 4.0));
                currentVector.setColor(GeneralPanel.VECTORCOLOR);
                this.addVector(currentVector);
                this.setCurrentVector(currentVector);
            }
            else {
                super.step = n;
            }
        }
        else if (n == 3) {
            super.step = n;
            this.clear();
            this.setDrawingNewVector(true);
        }
        else if (n == 2) {
            super.step = n;
            final double n4 = this.getSize().height;
            final double n5 = this.getSize().width;
            this.clear();
            final Vector2d currentVector2 = new Vector2d(new Point2D.Double(n5 / 2.0, n4 / 2.0), new Point2D.Double(3.0 * n5 / 4.0, 3.0 * n4 / 4.0));
            currentVector2.setMoveable(false);
            currentVector2.setTailChangable(false);
            currentVector2.setColor(GeneralPanel.VECTORCOLOR);
            this.addVector(currentVector2);
            this.setCurrentVector(currentVector2);
        }
        else if (n == 1) {
            super.step = 1;
        }
        if (this.isSingleMode()) {
            super.currentVector.setMoveable(true);
            super.currentVector.setTailChangable(true);
        }
        this.repaint();
    }
    
    public void calculateAnswer() {
    }
    
    public void calculateAnswer(final Point point) {
    }
    
    public boolean doneDrawing() {
        return super.vectorA != null;
    }
    
    public void doCommand(final String s) {
        if (s.equals("reset")) {
            super.theApplet.resetApplet();
        }
        else if (s.equals("page2")) {
            this.setStep(2);
        }
        else if (s.equals("page3")) {
            this.setStep(3);
        }
        else {
            this.setStep(0);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (this.isVectorAt(x, y) && this.getVectorAt(x, y).getStyle() != 2) {
                super.currentVector = this.getVectorAt(x, y);
                this.repaint();
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (this.inBounds(x, y)) {
                if (super.drawingNewVector) {
                    (super.currentVector = new Vector2d(new Point2D.Double(x, y), new Point2D.Double(x, y))).setColor(GeneralPanel.VECTORCOLOR);
                    this.addVector(super.currentVector);
                    super.currentVector.setHeadMoving(true);
                    super.mouseDownPoint = mouseEvent.getPoint();
                    super.mouseDown = true;
                    if (super.step == 3) {
                        this.setStep(0);
                    }
                    this.repaint();
                }
                else if (super.drawingNewLine) {
                    this.addVector(super.currentVector = new Vector2d(new Point2D.Double(x, y), new Point2D.Double(x, y), 2));
                    super.currentVector.setHeadMoving(true);
                    super.mouseDownPoint = mouseEvent.getPoint();
                    super.mouseDown = true;
                    this.repaint();
                }
                else if (this.isVectorAt(x, y)) {
                    if (this.getVectorAt(x, y).getStyle() != 2) {
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
                }
                else if (super.currentVector != null) {
                    this.deselectCurrentVector();
                    this.repaint();
                }
            }
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
                super.currentVector.setStill(true);
            }
            if (super.step == 3) {}
            this.deselectAll();
            this.repaint();
        }
    }
    
    public void setTheVector() {
        if (super.currentVector != null && super.currentVector.getStyle() != 2 && super.currentVector != super.vectorA) {
            super.currentVector.setColor(GeneralPanel.VECTORCOLOR);
            if (super.vectorA != null) {
                super.vectorA.setNormal();
            }
            super.vectorA = super.currentVector;
            super.vectorA.showInfo = false;
            this.repaint();
        }
    }
    
    protected void paintClassSpecific(final Graphics graphics) {
        final int width = this.getBounds().width;
        final int height = this.getBounds().height;
        graphics.setFont(MapConstants.vectorDefaultFont);
        graphics.setColor(Color.black);
        if (this.getStep() == 1 && super.titleImage != null) {
            graphics.drawImage(super.titleImage, 0, 0, this);
        }
        if ((super.step == 0 || this.isSingleMode()) && super.currentVector != null) {
            try {
                final double round = this.round(super.currentVector.getLength() / 10.0);
                final double round2 = this.round(super.currentVector.getDirection() * 180.0 / 3.141592653589793);
                if (Math.abs(round) >= 100.0) {
                    graphics.drawString("magnitude = " + (int)round, (width - 300) / 2, height - 4);
                }
                else {
                    graphics.drawString("magnitude = " + round, (width - 300) / 2, height - 4);
                }
                if (Math.abs(round2) >= 100.0) {
                    graphics.drawString("direction = " + (int)round2 + " deg", (width - 315) / 2 + 170, height - 4);
                }
                else {
                    graphics.drawString("direction = " + round2 + " deg", (width - 315) / 2 + 170, height - 4);
                }
            }
            catch (NullPointerException ex) {
                System.out.println("Null pointer");
            }
        }
        if (super.step == 2 && super.currentVector != null) {
            final int n = (int)(super.currentVector.getTailx() + 0.5);
            final int n2 = (int)(super.currentVector.getTaily() + 0.5);
            final double length = super.currentVector.getLength();
            final double direction = super.currentVector.getDirection();
            final double n3 = (height / 2 - 15 < length / 2.0) ? (height / 2 - 15) : (length / 2.0);
            graphics.drawArc((int)(n - n3), (int)(n2 - n3), (int)(2.0 * n3), (int)(2.0 * n3), 0, (int)(direction * 180.0 / 3.141592653589793));
            final int n4 = (int)(n + n3 * Math.cos(direction / 2.0));
            int n5 = (int)(n2 - n3 * Math.sin(direction / 2.0));
            final double a = direction * 180.0 / 3.141592653589793;
            if (a < 0.0) {
                n5 += 13;
            }
            else {
                --n5;
            }
            if (Math.abs(a) >= 100.0) {
                try {
                    graphics.drawString((int)this.round(a) + " deg", n4, n5);
                }
                catch (NullPointerException ex2) {
                    System.out.println("exception d:        " + (int)this.round(a) + " deg");
                }
            }
            else {
                try {
                    graphics.drawString(this.round(a) + " deg", n4, n5);
                }
                catch (NullPointerException ex3) {
                    System.out.println("exception d:   " + this.round(a) + " deg");
                }
            }
            VectorUtil.drawDotLine(graphics, new Point2D.Double(n, n2), new Point2D.Double((n + n3 >= width) ? width : ((double)(width - 5)), n2), 5.0, Color.black);
        }
    }
    
    protected void deselectCurrentVector() {
    }
    
    public boolean isSingleMode() {
        return this.singleMode;
    }
    
    public void setSingleMode(final boolean singleMode) {
        this.singleMode = singleMode;
    }
    
    static {
        VECTORCOLOR = Color.orange;
    }
}
