// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.geom.Point2D;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import ca.ucalgary.phas.map.utilities.vectors.VectorLabelComponent;
import ca.ucalgary.phas.map.utilities.ComplexString;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;

public class SubtractionInteractivePanel extends VectorPanel
{
    public static int showAnswerPage;
    public static int findAnswerPage;
    public static final int START2 = 2;
    public static final int DRAWING_A = 3;
    public static final int DRAWING_B = 4;
    public static final int SETTING_VECTORS = 6;
    public static final int FINDING_ANSWER = 7;
    protected Vector2d vectorSubAB;
    protected Vector2d vectorSubBA;
    public boolean reversing;
    protected Vector2d vectorNegA;
    protected Vector2d vectorNegB;
    ComplexString csVectorNegA;
    ComplexString csVectorNegB;
    public boolean vectorAReversed;
    public boolean vectorBReversed;
    private Vector2d tempPaintVector;
    int panelWidth;
    int panelHeight;
    String differenceVectorString;
    double xDragShift;
    double yDragShift;
    protected boolean drawingAnswer;
    
    public SubtractionInteractivePanel(final VectorApplet vectorApplet) {
        super(vectorApplet, "subtractionvectorapplet");
        this.reversing = false;
        this.csVectorNegA = new ComplexString("-<vector:a>");
        this.csVectorNegB = new ComplexString("-<vector:b>");
        this.vectorAReversed = false;
        this.vectorBReversed = false;
        this.differenceVectorString = "";
        this.drawingAnswer = false;
        (this.vectorSubAB = new Vector2d()).setColor(VectorPanel.COLOR_SUB);
        this.vectorSubAB.showInfo = true;
        this.vectorSubAB.setLabel((VectorLabelComponent)new ComplexString("<vector:a>-<vector:b>"));
        this.vectorSubAB.setChangable(false);
        this.vectorSubAB.setLabelSide(0);
        this.vectorSubAB.showAnchors(false);
        (this.vectorSubBA = new Vector2d()).setColor(VectorPanel.COLOR_SUB);
        this.vectorSubBA.showInfo = true;
        this.vectorSubBA.setLabel((VectorLabelComponent)new ComplexString("<vector:b>-<vector:a>"));
        this.vectorSubBA.setChangable(false);
        this.vectorSubBA.setLabelSide(0);
        this.vectorSubBA.showAnchors(false);
        (this.vectorNegA = new Vector2d()).setColor(VectorPanel.COLOR_A);
        this.vectorNegA.showInfo = true;
        this.vectorNegA.setLabel((VectorLabelComponent)this.csVectorNegA);
        this.vectorNegA.setVisible(false);
        this.vectorNegA.setChangable(false);
        (this.vectorNegB = new Vector2d()).setColor(VectorPanel.COLOR_B);
        this.vectorNegB.showInfo = true;
        this.vectorNegB.setLabel((VectorLabelComponent)this.csVectorNegB);
        this.vectorNegB.setVisible(false);
        this.vectorNegB.setChangable(false);
    }
    
    public void calculateAnswerAB() {
        if (super.vectorA != null && super.vectorB != null) {
            this.vectorSubAB.setHead(super.vectorA.getXComponent() - super.vectorB.getXComponent(), super.vectorA.getYComponent() - super.vectorB.getYComponent());
            this.vectorSubAB.setTail(0.0, 0.0);
            this.translateToCenter(this.vectorSubAB, 50, 0);
            this.vectorSubAB.setVisible(true);
            this.addVector(this.vectorSubAB);
            this.repaint();
        }
    }
    
    void translateToCenter(final Vector2d vector2d, final int n, final int n2) {
        vector2d.translateVector((this.panelWidth - vector2d.getHeadx() - vector2d.getTailx()) / 2.0 + n, (this.panelHeight - vector2d.getHeady() - vector2d.getTaily()) / 2.0 + n2);
    }
    
    public void calculateAnswerBA() {
        if (super.vectorA != null && super.vectorB != null) {
            this.vectorSubBA.setHead(super.vectorB.getXComponent() - super.vectorA.getXComponent(), super.vectorB.getYComponent() - super.vectorA.getYComponent());
            this.vectorSubBA.setTail(0.0, 0.0);
            this.translateToCenter(this.vectorSubBA, -50, 0);
            this.vectorSubBA.setVisible(true);
            this.addVector(this.vectorSubBA);
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        this.panelWidth = this.getSize().width;
        this.panelHeight = this.getSize().height;
        graphics.clearRect(0, 0, this.panelWidth, this.panelHeight);
        for (int i = 0; i < super.vectors.size(); ++i) {
            try {
                (this.tempPaintVector = (Vector2d)super.vectors.elementAt(i)).paint(graphics);
            }
            catch (ArrayIndexOutOfBoundsException ex) {}
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
    
    public void reset() {
        this.reversing = false;
        this.vectorAReversed = false;
        this.vectorBReversed = false;
        this.vectorSubAB.setVisible(false);
        this.vectorSubBA.setVisible(false);
        this.vectorNegB.setVisible(false);
        this.vectorNegA.setVisible(false);
    }
    
    public double getDirDiff() {
        return super.vectorAnswer.getDirection() * 180.0 / 3.141592653589793 - super.vectorSub.getDirection() * 180.0 / 3.141592653589793;
    }
    
    public double getLenDiff() {
        return super.vectorAnswer.getLength() / 10.0 - super.vectorSub.getLength() / 10.0;
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
                this.calculateAnswerAB();
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
            if (!this.reversing) {
                if (this.isVectorAt(x, y)) {
                    this.setCurrentVector(this.getVectorAt(x, y));
                    if (this.getStep() == 7 && !super.theApplet.isDemoMode()) {
                        if (super.deletingVector) {
                            this.deleteCurrentVector();
                        }
                        else if (super.currentVector.getStyle() != 1 && super.currentVector.getStyle() != 2 && super.currentVector != super.vectorA && super.currentVector != super.vectorB && super.currentVector != super.vectorSub) {
                            this.setCurrentVectorAsNewAnswerVector((VectorLabelComponent)new ComplexString("<vector:" + this.differenceVectorString + ">"));
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
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            if (super.mouseDown) {
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                if (this.inBounds(x, y)) {
                    if (super.currentVector.getHeadMoving()) {
                        this.xDragShift = super.currentVector.getHeadx() - x;
                        this.yDragShift = super.currentVector.getHeady() - y;
                        super.currentVector.setHead((double)x, (double)y);
                        if (super.currentVector == super.vectorA) {
                            this.vectorNegA.translateHead(this.xDragShift, this.yDragShift);
                            this.vectorSubAB.setVisible(false);
                            this.vectorSubBA.setVisible(false);
                        }
                        else if (super.currentVector == super.vectorB) {
                            this.vectorNegB.translateHead(this.xDragShift, this.yDragShift);
                            this.vectorSubAB.setVisible(false);
                            this.vectorSubBA.setVisible(false);
                        }
                    }
                    else if (super.currentVector.getTailMoving()) {
                        this.xDragShift = super.currentVector.getTailx() - x;
                        this.yDragShift = super.currentVector.getTaily() - y;
                        super.currentVector.setTail((double)x, (double)y);
                        if (super.currentVector == super.vectorA) {
                            this.vectorNegA.translateTail(this.xDragShift, this.yDragShift);
                            this.vectorSubAB.setVisible(false);
                            this.vectorSubBA.setVisible(false);
                        }
                        else if (super.currentVector == super.vectorB) {
                            this.vectorNegB.translateTail(this.xDragShift, this.yDragShift);
                            this.vectorSubAB.setVisible(false);
                            this.vectorSubBA.setVisible(false);
                        }
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
    
    public void setMouseCursor(final int n, final int n2) {
        if (!super.mouseDown) {
            if (this.reversing) {
                if ((this.getVectorAt(n, n2) == super.vectorA && !this.vectorNegA.isVisible()) || (this.getVectorAt(n, n2) == super.vectorB && !this.vectorNegB.isVisible())) {
                    this.setCursor(super.deleteCursor);
                }
                else {
                    this.setCursor(super.arrowCursor);
                }
            }
            else {
                super.setMouseCursor(n, n2);
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (this.inBounds(x, y)) {
                if (this.reversing) {
                    if (this.getVectorAt(x, y) == super.vectorA && !this.vectorNegA.isVisible()) {
                        this.vectorNegA.setVector(super.vectorA.getHead2D(), super.vectorA.getTail2D());
                        this.vectorNegA.setVisible(true);
                        this.addVector(this.vectorNegA);
                        this.translateToCenter(this.vectorNegA, 0, 0);
                        super.theApplet.setReverseVectorButtonSelected(false);
                        super.theApplet.setReverseVectorButtonEnabled(true);
                    }
                    if (this.getVectorAt(x, y) == super.vectorB && !this.vectorNegB.isVisible()) {
                        this.vectorNegB.setVector(super.vectorB.getHead2D(), super.vectorB.getTail2D());
                        this.vectorNegB.setVisible(true);
                        this.addVector(this.vectorNegB);
                        this.translateToCenter(this.vectorNegB, 0, 0);
                        super.theApplet.setReverseVectorButtonSelected(false);
                        super.theApplet.setReverseVectorButtonEnabled(true);
                    }
                }
                else if (super.drawingNewVector) {
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
                    if (super.currentVector != null && super.currentVector.getStyle() != 1 && super.currentVector.getStyle() != 2 && super.currentVector != super.vectorA && super.currentVector != super.vectorB && super.currentVector != super.vectorSub) {
                        this.setCurrentVectorAsNewAnswerVector((VectorLabelComponent)new ComplexString("<vector:" + this.differenceVectorString + ">"));
                        super.theApplet.setResultantButtonEnabled(true);
                        super.theApplet.setReverseVectorButtonEnabled(true);
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
                    super.theApplet.setReverseVectorButtonEnabled(true);
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
        this.setMouseCursor(mouseEvent.getPoint().x, mouseEvent.getPoint().y);
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
            super.vectorAnswer.setLabel((VectorLabelComponent)new ComplexString("<vector:" + this.differenceVectorString + ">"));
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
            SubtractionInteractivePanel.showAnswerPage = 4;
            SubtractionInteractivePanel.findAnswerPage = 3;
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
        SubtractionInteractivePanel.showAnswerPage = 5;
        SubtractionInteractivePanel.findAnswerPage = 4;
    }
}
