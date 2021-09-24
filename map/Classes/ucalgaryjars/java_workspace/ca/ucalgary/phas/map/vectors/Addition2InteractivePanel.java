// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.event.MouseEvent;
import ca.ucalgary.phas.map.utilities.vectors.VectorLabelComponent;
import ca.ucalgary.phas.map.utilities.ComplexString;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;
import java.awt.geom.Point2D;

public class Addition2InteractivePanel extends VectorPanel
{
    public static int showAnswerPage;
    public static int findAnswerPage;
    public static final int START2 = 2;
    public static final int DRAWING_A = 3;
    public static final int DRAWING_B = 4;
    public static final int SETTING_VECTORS = 6;
    public static final int FINDING_ANSWER = 7;
    public String sumVectorString;
    protected boolean drawingAnswer;
    
    public Addition2InteractivePanel(final VectorApplet vectorApplet) {
        super(vectorApplet, "addition2vectorapplet");
        this.sumVectorString = "";
        this.drawingAnswer = false;
    }
    
    public void calculateAnswer() {
        final double a = super.vectorA.getHeadx() - super.vectorA.getTailx() + (super.vectorB.getHeadx() - super.vectorB.getTailx());
        final double a2 = super.vectorA.getHeady() - super.vectorA.getTaily() + (super.vectorB.getHeady() - super.vectorB.getTaily());
        this.calculateAnswer((a < 0.0) ? (Math.abs(a) + 25.0) : 25.0, (a2 < 0.0) ? (Math.abs(a2) + 25.0) : 25.0);
    }
    
    public void calculateAnswer(final double x, final double y) {
        if (super.vectorA != null && super.vectorB != null) {
            final double n = super.vectorA.getHeadx() - super.vectorA.getTailx() + (super.vectorB.getHeadx() - super.vectorB.getTailx());
            final double n2 = super.vectorA.getHeady() - super.vectorA.getTaily() + (super.vectorB.getHeady() - super.vectorB.getTaily());
            if (super.vectorSum == null) {
                final Vector2d vectorSum = new Vector2d(new Point2D.Double(x, y), new Point2D.Double(x + n, y + n2));
                vectorSum.setColor(VectorPanel.COLOR_SUM);
                vectorSum.showInfo = true;
                vectorSum.setLabel((VectorLabelComponent)new ComplexString("<vector:a>+<vector:b>"));
                vectorSum.setChangable(false);
                vectorSum.setLabelSide(0);
                vectorSum.showAnchors(false);
                this.setVectorSum(vectorSum);
            }
            else {
                super.vectorSum.setHead(x + n, y + n2);
                super.vectorSum.setTail(x, y);
            }
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
                        this.setCurrentVectorAsNewAnswerVector((VectorLabelComponent)new ComplexString("<vector:" + this.sumVectorString + ">"));
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
                if (this.drawingAnswer) {
                    if (super.currentVector != null && super.currentVector.getStyle() != 1 && super.currentVector.getStyle() != 2 && super.currentVector != super.vectorA && super.currentVector != super.vectorB && super.currentVector != super.vectorC && super.currentVector != super.vectorSum) {
                        this.setCurrentVectorAsNewAnswerVector((VectorLabelComponent)new ComplexString("<vector:" + this.sumVectorString + ">"));
                        super.theApplet.setResultantButtonEnabled(true);
                    }
                }
                else if (this.getStep() == 3) {
                    this.setVectorA();
                    this.setStep(4);
                }
                else if (this.getStep() == 4) {
                    this.setVectorB();
                    super.theApplet.setAnswerButtonEnabled(true);
                    super.theApplet.setDrawVectorButtonEnabled(false);
                    super.theApplet.setDrawLineButtonEnabled(true);
                }
            }
            if (super.drawingNewLine) {
                this.setDrawingNewLine(false);
            }
            if (this.drawingAnswer) {
                this.setDrawingAnswer(false);
            }
            if (super.currentVector != null) {
                super.currentVector.setStill(true);
            }
        }
        this.deselectAll();
        this.repaint();
    }
    
    public void setDrawingAnswer(final boolean answerButtonSelected) {
        if (answerButtonSelected) {
            super.drawingNewLine = !answerButtonSelected;
            super.theApplet.setDrawLineButtonSelected(!answerButtonSelected);
            this.setStep(7);
        }
        this.setDrawingNewVector(answerButtonSelected);
        this.drawingAnswer = answerButtonSelected;
        super.theApplet.setAnswerButtonSelected(answerButtonSelected);
    }
    
    public void setAnswerVectorLabel() {
        if (super.vectorAnswer != null) {
            super.vectorAnswer.setLabel((VectorLabelComponent)new ComplexString("<vector:" + this.sumVectorString + ">"));
            this.repaint();
        }
    }
    
    public void setCurrentVectorAsNewAnswerVector(final VectorLabelComponent label) {
        if (super.currentVector != null) {
            if (super.vectorAnswer != null) {
                super.vectors.removeElement(super.vectorAnswer);
            }
            super.currentVector.setColor(VectorPanel.COLOR_ANSWER);
            super.currentVector.showInfo = true;
            super.currentVector.setLabel(label);
            super.vectorAnswer = super.currentVector;
            this.enableContinue(true);
            this.repaint();
        }
    }
    
    public int getStep() {
        return super.step;
    }
    
    public void setStep(int step) {
        if (!super.theApplet.isDemoMode()) {
            Addition2InteractivePanel.showAnswerPage = 4;
            Addition2InteractivePanel.findAnswerPage = 3;
            if (step == 1) {
                step = 3;
            }
        }
        super.step = step;
        if (this.getStep() != 3) {
            if (this.getStep() == 4) {
                super.theApplet.setResetButtonEnabled(true);
            }
        }
        this.repaint();
    }
    
    static {
        Addition2InteractivePanel.showAnswerPage = 5;
        Addition2InteractivePanel.findAnswerPage = 4;
    }
}
