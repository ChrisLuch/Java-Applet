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

public class Addition2Panel extends VectorPanel
{
    public static int showAnswerPage;
    public static int findAnswerPage;
    public static final int START2 = 2;
    public static final int DRAWING_A = 3;
    public static final int DRAWING_B = 4;
    public static final int SETTING_VECTORS = 6;
    public static final int FINDING_ANSWER = 7;
    
    public Addition2Panel(final VectorApplet vectorApplet) {
        super(vectorApplet, "addition2vectorapplet");
    }
    
    public void calculateAnswer() {
        final double a = super.vectorA.getHeadx() - super.vectorA.getTailx() + (super.vectorB.getHeadx() - super.vectorB.getTailx());
        final double a2 = super.vectorA.getHeady() - super.vectorA.getTaily() + (super.vectorB.getHeady() - super.vectorB.getTaily());
        this.calculateAnswer((a < 0.0) ? (Math.abs(a) + 25.0) : 25.0, (a2 < 0.0) ? (Math.abs(a2) + 25.0) : 25.0);
    }
    
    public void calculateAnswer(final double x, final double y) {
        if (super.vectorA != null && super.vectorB != null) {
            final Vector2d vectorSum = new Vector2d(new Point2D.Double(x, y), new Point2D.Double(x + (super.vectorA.getHeadx() - super.vectorA.getTailx() + (super.vectorB.getHeadx() - super.vectorB.getTailx())), y + (super.vectorA.getHeady() - super.vectorA.getTaily() + (super.vectorB.getHeady() - super.vectorB.getTaily()))));
            vectorSum.setColor(VectorPanel.COLOR_SUM);
            vectorSum.showInfo = true;
            vectorSum.setLabel((VectorLabelComponent)new VectorString(new String[] { "a", "+", "b" }, new String[] { "true", "false", "true" }, 3));
            vectorSum.setChangable(false);
            vectorSum.showAnchors(false);
            this.setVectorSum(vectorSum);
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
    
    public double getDirDiff() {
        return super.vectorAnswer.getDirection() * 180.0 / 3.141592653589793 - super.vectorSum.getDirection() * 180.0 / 3.141592653589793;
    }
    
    public double getLenDiff() {
        return super.vectorAnswer.getLength() / 10.0 - super.vectorSum.getLength() / 10.0;
    }
    
    public void doCommand(final String s) {
        if (s.equals("reset")) {
            super.theApplet.resetApplet();
        }
        else {
            if (this.getStep() == 1) {
                this.setStep(3);
            }
            if (this.getStep() == 6) {
                this.enableContinue(true);
            }
            if (s.equals("show_answer") && super.vectorAnswer != null) {
                this.freezeAnswerVector();
                this.calculateAnswer();
                this.enableContinue(false);
            }
            if (s.equals("find_answer") && this.doneDrawing()) {
                this.setStep(7);
            }
            if (this.getStep() >= 7) {
                this.enableTools(true);
            }
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (this.isVectorAt(x, y)) {
                this.setCurrentVector(this.getVectorAt(x, y));
                if (this.getStep() == 7 && !super.theApplet.isDemoMode()) {
                    if (super.deletingVector) {
                        this.deleteCurrentVector();
                    }
                    else if (super.currentVector.getStyle() != 1 && super.currentVector.getStyle() != 2 && super.currentVector != super.vectorA && super.currentVector != super.vectorB && super.currentVector != super.vectorC && super.currentVector != super.vectorSum) {
                        this.setCurrentVectorAsNewAnswerVector((VectorLabelComponent)new VectorString("my sum", false));
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
                        final double n = x - super.mouseDownPoint.x;
                        final double n2 = y - super.mouseDownPoint.y;
                        super.currentVector.translateTail(n, n2);
                        super.currentVector.translateHead(n, n2);
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
                    this.setCurrentVector(new Vector2d(new Point2D.Double(x, y), new Point2D.Double(x, y)));
                    this.addVector(super.currentVector);
                    super.currentVector.setSelected(true);
                    super.currentVector.setHeadMoving(true);
                    super.mouseDownPoint = mouseEvent.getPoint();
                    super.mouseDown = true;
                    this.repaint();
                }
                else if (super.drawingNewLine) {
                    this.setCurrentVector(new Vector2d(new Point2D.Double(x, y), new Point2D.Double(x, y), 2));
                    this.addVector(super.currentVector);
                    super.currentVector.setSelected(true);
                    super.currentVector.setHeadMoving(true);
                    super.mouseDownPoint = mouseEvent.getPoint();
                    super.mouseDown = true;
                    this.repaint();
                }
                else if (!super.deletingVector) {
                    if (this.isVectorAt(x, y)) {
                        this.setCurrentVector(this.getVectorAt(x, y));
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
                        this.setCurrentVector(null);
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
                if (this.getStep() == 3) {
                    this.setVectorA();
                    this.setStep(4);
                }
                else if (this.getStep() == 4) {
                    this.setVectorB();
                    this.setStep(6);
                    this.enableContinue(true);
                }
                else if (this.getStep() == 7 && !super.theApplet.isDemoMode() && super.currentVector != null && super.currentVector.getStyle() != 1 && super.currentVector.getStyle() != 2 && super.currentVector != super.vectorA && super.currentVector != super.vectorB && super.currentVector != super.vectorC && super.currentVector != super.vectorSum) {
                    this.setCurrentVectorAsNewAnswerVector((VectorLabelComponent)new VectorString("my sum", false));
                    this.enableContinue(true);
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
        if (this.getStep() == 1 && super.titleImage != null) {
            graphics.drawImage(super.titleImage, 0, 0, this);
        }
    }
    
    public int getStep() {
        return super.step;
    }
    
    public void enableContinue(final boolean b) {
        if (this.getStep() == 3) {
            super.theApplet.enableContinue(false);
        }
        else {
            super.theApplet.enableContinue(b);
        }
    }
    
    public void setStep(int step) {
        if (!super.theApplet.isDemoMode()) {
            Addition2Panel.showAnswerPage = 4;
            Addition2Panel.findAnswerPage = 3;
            if (step == 1) {
                step = 3;
            }
        }
        super.step = step;
        if (this.getStep() != 0) {
            if (this.getStep() == 3) {
                this.enableContinue(false);
            }
            else if (this.getStep() != 4) {
                if (this.getStep() == 7) {
                    this.enableContinue(false);
                    this.freezeDrawnVectors();
                    super.theApplet.enableTools(true);
                }
            }
        }
        this.repaint();
    }
    
    static {
        Addition2Panel.showAnswerPage = 5;
        Addition2Panel.findAnswerPage = 4;
    }
}
