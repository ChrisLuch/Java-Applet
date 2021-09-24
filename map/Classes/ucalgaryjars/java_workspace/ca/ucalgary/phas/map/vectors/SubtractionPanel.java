// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import ca.ucalgary.phas.map.utilities.vectors.VectorLabelComponent;
import ca.ucalgary.phas.map.utilities.vectors.VectorString;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;
import java.awt.geom.Point2D;

public class SubtractionPanel extends VectorPanel
{
    public static final int START2 = 2;
    public static final int DRAWING_A = 3;
    public static final int DRAWING_B = 4;
    public static final int SETTING_VECTORS = 6;
    public static final int FINDING_ANSWER = 7;
    int step;
    
    public SubtractionPanel(final VectorApplet vectorApplet) {
        super(vectorApplet, "vectorsubtractionapplet");
    }
    
    public void start() {
        super.theApplet.setDrawButtonEnabled(false);
    }
    
    public void calculateAnswer() {
        final double a = super.vectorA.getHeadx() - super.vectorA.getTailx() - (super.vectorB.getHeadx() - super.vectorB.getTailx());
        final double a2 = super.vectorA.getHeady() - super.vectorA.getTaily() - (super.vectorB.getHeady() - super.vectorB.getTaily());
        this.calculateAnswer((a < 0.0) ? (Math.abs(a) + 25.0) : 25.0, (a2 < 0.0) ? (Math.abs(a2) + 25.0) : 25.0);
    }
    
    public void calculateAnswer(final double x, final double y) {
        if (super.vectorA != null && super.vectorB != null) {
            (super.vectorSub = new Vector2d(new Point2D.Double(x, y), new Point2D.Double(x + (int)(super.vectorA.getHeadx() - super.vectorA.getTailx() - (super.vectorB.getHeadx() - super.vectorB.getTailx()) + 0.5), y + (int)(super.vectorA.getHeady() - super.vectorA.getTaily() - (super.vectorB.getHeady() - super.vectorB.getTaily()) + 0.5)))).setColor(VectorPanel.COLOR_SUB);
            super.vectorSub.showInfo = true;
            super.vectorSub.setLabel((VectorLabelComponent)new VectorString(new String[] { "a", "-", "b" }, new String[] { "true", "false", "true" }, 3));
            super.vectorSub.setChangable(false);
            super.vectorSub.showAnchors(false);
            this.addVector(super.vectorSub);
            this.repaint();
        }
    }
    
    public boolean doneDrawing() {
        return super.vectorA != null && super.vectorB != null;
    }
    
    public void freezeAnswerVector() {
        if (super.vectorAnswer != null) {
            super.vectorAnswer.setChangable(false);
        }
    }
    
    public void doCommand(final String s) {
        if (s.equals("reset")) {
            super.theApplet.resetApplet();
            super.theApplet.setDrawButtonEnabled(false);
        }
        else {
            if (this.step == 1) {
                this.setStep(3);
                super.theApplet.setDrawButtonEnabled(true);
            }
            if (this.step == 6) {
                this.enableContinue(true);
            }
            if (super.theApplet.demo == null) {
                if (s.equals("show_answer") && super.vectorAnswer != null) {
                    this.freezeAnswerVector();
                    this.calculateAnswer();
                    this.enableContinue(false);
                }
                if (s.equals("find_answer") && this.doneDrawing()) {
                    this.setStep(7);
                }
                if (this.step >= 7) {
                    this.enableTools(true);
                }
            }
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (this.isVectorAt(x, y)) {
                super.currentVector = this.getVectorAt(x, y);
                if (this.step == 7 && !super.theApplet.isDemoMode()) {
                    if (super.deletingVector) {
                        this.deleteCurrentVector();
                    }
                    else if (super.currentVector.getStyle() != 1 && super.currentVector.getStyle() != 2 && super.currentVector != super.vectorA && super.currentVector != super.vectorB && super.currentVector != super.vectorC && super.currentVector != super.vectorSub) {
                        this.setCurrentVectorAsNewAnswerVector((VectorLabelComponent)new VectorString("my difference", false));
                    }
                    else {
                        super.currentVector.setSelected(true);
                    }
                }
                else {
                    super.currentVector.setSelected(true);
                }
                this.repaint();
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            if (super.mouseDown) {
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                if (this.inBounds(x, y)) {
                    if (super.currentVector.getHeadMoving()) {
                        super.currentVector.setHead((double)x, (double)y);
                    }
                    else if (super.currentVector.getTailMoving()) {
                        super.currentVector.setTail((double)x, (double)y);
                    }
                    else if (super.currentVector.getMoving()) {
                        final int n = x - super.mouseDownPoint.x;
                        final int n2 = y - super.mouseDownPoint.y;
                        super.currentVector.translateTail((double)n, (double)n2);
                        super.currentVector.translateHead((double)n, (double)n2);
                        super.mouseDownPoint = mouseEvent.getPoint();
                    }
                }
            }
            this.repaint();
        }
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
                else if (!super.deletingVector) {
                    if (this.isVectorAt(x, y)) {
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
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            if (super.mouseDown) {
                super.mouseDown = false;
            }
            if (super.drawingNewVector) {
                this.setDrawingNewVector(false);
                if (this.step == 3) {
                    this.setVectorA();
                    this.setStep(4);
                }
                else if (this.step == 4) {
                    this.setVectorB();
                    this.setStep(6);
                    this.enableContinue(true);
                }
                else if (this.step == 7 && !super.theApplet.isDemoMode() && super.currentVector != null && super.currentVector.getStyle() != 1 && super.currentVector.getStyle() != 2 && super.currentVector != super.vectorA && super.currentVector != super.vectorB && super.currentVector != super.vectorC && super.currentVector != super.vectorSum) {
                    this.setCurrentVectorAsNewAnswerVector((VectorLabelComponent)new VectorString("my difference", false));
                }
            }
            if (super.drawingNewLine) {
                this.setDrawingNewLine(false);
            }
            if (super.currentVector != null) {
                super.currentVector.setStill(true);
            }
        }
        this.deselectAll();
        this.repaint();
    }
    
    protected void paintClassSpecific(final Graphics graphics) {
        final int width = this.getBounds().width;
        final int height = this.getBounds().height;
        if (this.step == 1 && super.titleImage != null) {
            graphics.drawImage(super.titleImage, 0, 0, this);
        }
    }
    
    public int getStep() {
        return 1;
    }
    
    public void setStep(final int step) {
        this.step = step;
        if (this.step == 0) {}
        if (this.step == 3) {
            this.enableContinue(false);
        }
        if (this.step == 4) {}
        if (this.step == 7) {
            this.enableContinue(false);
            this.freezeDrawnVectors();
            super.theApplet.enableTools(true);
        }
    }
}
