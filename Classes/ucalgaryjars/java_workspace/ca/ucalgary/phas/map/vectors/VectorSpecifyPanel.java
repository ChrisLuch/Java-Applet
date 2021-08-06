// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import javax.swing.AbstractButton;
import ca.ucalgary.phas.map.utilities.MapConstants;
import java.awt.Graphics;
import ca.ucalgary.phas.map.utilities.vectors.VectorUtil;
import java.awt.event.MouseEvent;
import java.awt.Point;
import ca.ucalgary.phas.map.utilities.ImageUtils;
import ca.ucalgary.phas.map.utilities.vectors.VectorLabelComponent;
import ca.ucalgary.phas.map.utilities.ComplexString;
import java.awt.geom.Point2D;
import ca.ucalgary.phas.map.utilities.vectors.VectorDescriptionControlPanel;
import java.awt.Image;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;
import java.awt.Color;
import ca.ucalgary.phas.map.utilities.vectors.VectorDescriptionControlPanelListener;

public class VectorSpecifyPanel extends VectorPanel implements VectorDescriptionControlPanelListener
{
    private static final Color VECTOR_FILL_COLOR;
    private static final Color VECTOR_OUTLINE_COLOR;
    private int specifyMode;
    private Vector2d v;
    private Vector2d origin;
    private Vector2d axis;
    private Vector2d xAxis;
    private Vector2d yAxis;
    private Vector2d xComp;
    private Vector2d yComp;
    private Image navAxisImage;
    private VectorDescriptionControlPanel vectorDescriptionControlPanel;
    private double originAngle;
    
    public VectorSpecifyPanel(final VectorApplet vectorApplet) {
        super(vectorApplet, "specifyvectorapplet");
        this.v = new Vector2d(new Point2D.Double(100.0, 100.0), new Point2D.Double(300.0, 250.0));
    }
    
    public void start() {
        (this.origin = new Vector2d(2)).setMoveable(true);
        this.origin.setHeadChangable(false);
        this.origin.setTailChangable(false);
        (this.xAxis = new Vector2d(0)).setMoveable(true);
        this.xAxis.setHeadChangable(false);
        this.xAxis.setTailChangable(false);
        this.xAxis.setVectorSize(3.0, 9);
        this.xAxis.setLabel((VectorLabelComponent)new ComplexString("x"));
        this.xAxis.setLabelVisible(true);
        this.xAxis.setLabelPosition(1.0);
        (this.xComp = new Vector2d(1)).setOutlineColor(Color.green);
        this.xComp.setLabel((VectorLabelComponent)new ComplexString("<italic>v<sub>x</sub></italic>"));
        this.xComp.setLabelVisible(true);
        this.xComp.setLabelPosition(0.9);
        this.xComp.setMoveable(true);
        this.xComp.setHeadChangable(false);
        this.xComp.setTailChangable(false);
        this.xComp.setVectorWidth(2.0);
        (this.yAxis = new Vector2d(0)).setMoveable(true);
        this.yAxis.setHeadChangable(false);
        this.yAxis.setTailChangable(false);
        this.yAxis.setVectorSize(3.0, 9);
        this.yAxis.setLabel((VectorLabelComponent)new ComplexString("y"));
        this.yAxis.setLabelVisible(true);
        this.yAxis.setLabelPosition(1.0);
        (this.yComp = new Vector2d(1)).setOutlineColor(Color.orange);
        this.yComp.setLabel((VectorLabelComponent)new ComplexString("<italic>v<sub>y</sub></italic>"));
        this.yComp.setLabelVisible(true);
        this.yComp.setHeadChangable(false);
        this.yComp.setTailChangable(false);
        this.yComp.setLabelPosition(0.9);
        this.yComp.setMoveable(true);
        this.yComp.setVectorWidth(2.0);
        this.navAxisImage = ImageUtils.getImageFromJar("images/navaxis.gif", (Object)this);
        (super.currentVector = this.v).setFillColor(VectorSpecifyPanel.VECTOR_FILL_COLOR);
        super.currentVector.setOutlineColor(VectorSpecifyPanel.VECTOR_OUTLINE_COLOR);
        super.currentVector.setLabel((VectorLabelComponent)new ComplexString("<vector:v>"));
        super.currentVector.setLabelPosition(0.5);
        super.currentVector.setLabelVisible(true);
        super.currentVector.setTailChangable(true);
        this.addVector(this.origin);
        this.addVector(this.xAxis);
        this.addVector(this.yAxis);
        this.addVector(this.xComp);
        this.addVector(this.yComp);
        this.specifyMode = 2;
        this.addVector(super.currentVector);
        this.updateExtraVectors();
        super.theApplet.updateSpecificationPanel(super.currentVector);
    }
    
    public void setStep(final int n) {
        this.setDrawingNewVector(false);
        this.repaint();
    }
    
    public void modeChanged(final VectorDescriptionControlPanel vectorDescriptionControlPanel) {
        this.specifyMode = this.vectorDescriptionControlPanel.getMode();
        final boolean b = this.specifyMode == 1;
        this.xAxis.setVisible(b);
        this.yAxis.setVisible(b);
        this.xComp.setVisible(b);
        this.yComp.setVisible(b);
        this.origin.setVisible(!b);
        this.updateExtraVectors();
    }
    
    public void calculateAnswer() {
    }
    
    public void calculateAnswer(final Point point) {
    }
    
    public boolean doneDrawing() {
        return true;
    }
    
    public void doCommand(final String str) {
        if (str.equals("magn_nav_coords")) {
            ((AbstractButton)super.theApplet.magnNavCoordsRadioButton).setSelected(true);
            super.theApplet.doMagnNavCoordsRadioButtonAction();
        }
        else if (str.equals("xy_coords")) {
            ((AbstractButton)super.theApplet.xyCoordsRadioButton).setSelected(true);
            super.theApplet.doXyCoordsRadioButtonAction();
        }
        else if (str.equals("magn_angle_pos_only")) {
            ((AbstractButton)super.theApplet.magnAnglePosOnlyRadioButton).setSelected(true);
            super.theApplet.doMagnAnglePosOnlyRadioButtonAction();
        }
        else if (str.equals("magn_angle_pos_neg")) {
            ((AbstractButton)super.theApplet.magnAnglePosNegRadioButton).setSelected(true);
            super.theApplet.doMagnAnglePosNegRadioButtonAction();
        }
        else {
            System.out.println(" VectorSpecifyPanel.doCommand(): String command: \"" + str + "\" is not recongized, using \"magn_angle_pos_neg\" by default.");
        }
    }
    
    public void originChanged(final VectorDescriptionControlPanel vectorDescriptionControlPanel) {
        this.updateExtraVectors();
        this.repaint();
    }
    
    public void vectorChanged(final VectorDescriptionControlPanel vectorDescriptionControlPanel, final double n, final double n2) {
        this.updateExtraVectors();
        this.repaint();
    }
    
    public void textFieldActionPerformed(final VectorDescriptionControlPanel vectorDescriptionControlPanel) {
    }
    
    public void setVectorDescriptionControlPanel(final VectorDescriptionControlPanel vectorDescriptionControlPanel) {
        (this.vectorDescriptionControlPanel = vectorDescriptionControlPanel).setVector(this.v);
        this.vectorDescriptionControlPanel.setModeButtonVisible(false);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            if (this.isVectorAt(mouseEvent.getX(), mouseEvent.getY())) {
                super.currentVector = this.v;
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            if (super.mouseDown) {
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                if (this.inBounds(x, y)) {
                    if (this.v.getHeadMoving()) {
                        this.v.setHead((double)x, (double)y);
                    }
                    else if (this.v.getTailMoving()) {
                        this.v.setTail((double)x, (double)y);
                    }
                    else if (this.v.getMoving()) {
                        final Point2D.Double head2D = this.v.getHead2D();
                        final Point2D.Double tail2D = this.v.getTail2D();
                        final double n = x - super.mouseDownPoint.x;
                        final double n2 = y - super.mouseDownPoint.y;
                        final int n3 = (int)(head2D.x + n + 0.5);
                        final int n4 = (int)(head2D.y + n2 + 0.5);
                        final int n5 = (int)(tail2D.x + n + 0.5);
                        final int n6 = (int)(tail2D.y + n2 + 0.5);
                        if ((this.inBounds(n3, n4) && this.inBounds(n5, n6)) || !this.inBounds((int)head2D.x, (int)head2D.y) || !this.inBounds((int)tail2D.x, (int)tail2D.y)) {
                            this.v.setHead((double)n3, (double)n4);
                            this.v.setTail((double)n5, (double)n6);
                            super.mouseDownPoint = mouseEvent.getPoint();
                        }
                    }
                }
            }
            this.repaint();
        }
        this.updateExtraVectors();
        super.theApplet.updateSpecificationPanel(super.currentVector);
    }
    
    public void updateExtraVectors() {
        final double tailx = this.v.getTailx();
        final double taily = this.v.getTaily();
        final double headx = this.v.getHeadx();
        final double heady = this.v.getHeady();
        this.origin.setTail(tailx, taily);
        this.originAngle = this.vectorDescriptionControlPanel.getOriginAngle();
        if (this.specifyMode == 0) {
            this.origin.setHead(VectorUtil.translatePoint2D(this.origin.getTail2D(), this.originAngle / 180.0 * 3.141592653589793, 300.0));
        }
        else {
            this.origin.setHead(tailx + 300.0, taily);
        }
        final int n = 40;
        this.xComp.setTail(tailx, taily);
        this.xComp.setHead(headx, taily);
        this.yComp.setTail(tailx, taily);
        this.yComp.setHead(tailx, heady);
        if (tailx <= headx) {
            this.xAxis.setTail(tailx - n, taily);
            this.xAxis.setHead(headx + n, taily);
        }
        else {
            this.xAxis.setHead(tailx + n, taily);
            this.xAxis.setTail(headx - n, taily);
        }
        if (taily <= heady) {
            this.yAxis.setHead(tailx, taily - n);
            this.yAxis.setTail(tailx, heady + n);
        }
        else {
            this.yAxis.setTail(tailx, taily + n);
            this.yAxis.setHead(tailx, heady - n);
        }
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!super.theApplet.isDemoMode() || mouseEvent.isControlDown()) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (this.inBounds(x, y) && this.isVectorAt(x, y)) {
                if (this.v.onHeadAnch(x, y)) {
                    this.v.setHeadMoving(true);
                }
                else if (this.v.onTailAnch(x, y)) {
                    this.v.setTailMoving(true);
                }
                else {
                    this.v.setMoving(true);
                }
                super.mouseDownPoint = mouseEvent.getPoint();
                super.mouseDown = true;
                this.repaint();
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
            if (super.currentVector != null) {
                super.currentVector.setStill(true);
            }
            this.deselectAll();
            this.repaint();
        }
    }
    
    protected void paintClassSpecific(final Graphics graphics) {
        final int width = this.getBounds().width;
        final int height = this.getBounds().height;
        graphics.setFont(MapConstants.vectorDefaultFont);
        graphics.setColor(Color.black);
        if (this.specifyMode != 1) {
            final int n = (int)(super.currentVector.getTailx() + 0.5);
            final int n2 = (int)(super.currentVector.getTaily() + 0.5);
            final double length = super.currentVector.getLength();
            final double n3 = (height / 2 - 15 < length / 3.0) ? (height / 2 - 15) : (length / 3.0);
            graphics.drawArc((int)(n - n3), (int)(n2 - n3), (int)(2.0 * n3), (int)(2.0 * n3), (int)this.originAngle, (int)this.vectorDescriptionControlPanel.getVectorAngleRelativeToOrigin());
        }
    }
    
    protected void deselectCurrentVector() {
    }
    
    static {
        VECTOR_FILL_COLOR = Color.red;
        VECTOR_OUTLINE_COLOR = Color.black;
    }
}
