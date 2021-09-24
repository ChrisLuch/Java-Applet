// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities.vectors;

import ca.ucalgary.phas.map.utilities.ComplexString;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Color;

public class Vector2d
{
    public static final int VECTOR_STYLE = 0;
    public static final int LINE_STYLE = 1;
    public static final int DOTTED_LINE_STYLE = 2;
    public static final Color MOVING_VECTOR;
    public static final Color MOVING_VECTOR_TIP;
    public static final int HEAD = 1;
    public static final int TAIL = 2;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final double VWIDTH = 5.0;
    public static final int ARROWLEN = 15;
    protected Polygon linePolygon;
    protected Polygon arrowheadPolygon;
    protected Graphics graphicsContext;
    protected double snapToZeroDist;
    protected int style;
    protected double dotDiameter;
    protected double vectorWidth;
    protected int arrowLength;
    protected double tailx;
    protected double taily;
    protected double headx;
    protected double heady;
    protected VectorAnchor tailAnch;
    protected VectorAnchor headAnch;
    protected double xComponent;
    protected double yComponent;
    protected double dir;
    protected double length;
    protected Color outlineColor;
    protected Color fillColor;
    protected boolean visible;
    protected boolean moving;
    protected boolean tailMoving;
    protected boolean headMoving;
    protected boolean isStill;
    protected boolean selected;
    protected boolean moveable;
    protected boolean tailChangable;
    protected boolean headChangable;
    public boolean freezeDir;
    protected boolean directionFrozen;
    protected double frozenDirection;
    protected VectorLabelComponent label;
    protected VectorLabelComponent drawnLabel;
    public boolean showInfo;
    protected VectorLabelComponent zeroLabel;
    public boolean labelIsOpaque;
    public boolean drawLabelBorder;
    protected int labelSide;
    protected int label_x;
    protected int label_y;
    protected double labelPosition;
    protected int labelDist;
    
    public Vector2d() {
        this(new Point2D.Double(0.0, 0.0), new Point2D.Double(0.0, 0.0));
        this.setVisible(false);
    }
    
    public Vector2d(final int style) {
        this();
        this.style = style;
    }
    
    public Vector2d(final Point2D.Double double1, final Point2D.Double double2, final int style) {
        this(double1, double2);
        this.style = style;
    }
    
    public Vector2d(final Point2D.Double double1, final Point2D.Double double2) {
        this.snapToZeroDist = 4.0;
        this.dotDiameter = 3.0;
        this.vectorWidth = 5.0;
        this.arrowLength = 15;
        this.visible = true;
        this.moving = false;
        this.tailMoving = false;
        this.headMoving = false;
        this.isStill = true;
        this.selected = false;
        this.moveable = true;
        this.tailChangable = true;
        this.headChangable = true;
        this.freezeDir = false;
        this.directionFrozen = false;
        this.label = null;
        this.drawnLabel = null;
        this.showInfo = false;
        this.zeroLabel = null;
        this.labelIsOpaque = false;
        this.drawLabelBorder = false;
        this.labelSide = 1;
        this.labelPosition = 0.5;
        this.labelDist = 12;
        this.setColor(Color.black);
        this.tailAnch = new VectorAnchor();
        this.headAnch = new VectorAnchor();
        this.headx = double2.x;
        this.heady = double2.y;
        this.tailx = double1.x;
        this.taily = double1.y;
        this.style = 0;
        this.calculate();
    }
    
    public Vector2d(final Vector2d vector2d) {
        this.snapToZeroDist = 4.0;
        this.dotDiameter = 3.0;
        this.vectorWidth = 5.0;
        this.arrowLength = 15;
        this.visible = true;
        this.moving = false;
        this.tailMoving = false;
        this.headMoving = false;
        this.isStill = true;
        this.selected = false;
        this.moveable = true;
        this.tailChangable = true;
        this.headChangable = true;
        this.freezeDir = false;
        this.directionFrozen = false;
        this.label = null;
        this.drawnLabel = null;
        this.showInfo = false;
        this.zeroLabel = null;
        this.labelIsOpaque = false;
        this.drawLabelBorder = false;
        this.labelSide = 1;
        this.labelPosition = 0.5;
        this.labelDist = 12;
        this.outlineColor = vector2d.outlineColor;
        this.fillColor = vector2d.fillColor;
        this.tailAnch = new VectorAnchor();
        this.headAnch = new VectorAnchor();
        this.headx = vector2d.headx;
        this.heady = vector2d.heady;
        this.tailx = vector2d.tailx;
        this.taily = vector2d.taily;
        this.visible = vector2d.visible;
        this.calculate();
    }
    
    public void setDirectionFrozen(final boolean directionFrozen) {
        this.directionFrozen = directionFrozen;
        this.frozenDirection = this.dir;
    }
    
    protected void calcAnchors() {
        this.headAnch.setXY((int)(this.headx + 0.5), (int)(this.heady + 0.5));
        this.tailAnch.setXY((int)(this.tailx + 0.5), (int)(this.taily + 0.5));
    }
    
    protected void calcDir() {
        this.dir = (float)Math.atan(Math.abs(this.yComponent) / Math.abs(this.xComponent));
        if (this.headx < this.tailx) {
            this.dir = 3.141592653589793 - this.dir;
        }
        if (this.heady > this.taily) {
            this.dir *= -1.0;
        }
    }
    
    public VectorString getLabel() {
        if (this.label instanceof VectorString) {
            return (VectorString)this.label;
        }
        return null;
    }
    
    public VectorLabelComponent getLabelComponent() {
        return this.label;
    }
    
    public VectorString getZeroLabel() {
        if (this.zeroLabel instanceof VectorString) {
            return (VectorString)this.zeroLabel;
        }
        return null;
    }
    
    public VectorLabelComponent getZeroLabelComponent() {
        return this.zeroLabel;
    }
    
    public int getLabelX() {
        return this.label_x;
    }
    
    public int getLabelY() {
        return this.label_y;
    }
    
    public int getMidPointX() {
        return ((int)(this.headx + 0.5) + (int)(this.tailx + 0.5)) / 2;
    }
    
    public int getMidPointY() {
        return ((int)(this.heady + 0.5) + (int)(this.taily + 0.5)) / 2;
    }
    
    public int getLPointX() {
        return (int)((this.headx - this.tailx) * this.getLabelPosition() + this.tailx + 0.5);
    }
    
    public int getLPointY() {
        return (int)((this.heady - this.taily) * this.getLabelPosition() + this.taily + 0.5);
    }
    
    public void setLabelPosition(final double labelPosition) {
        this.labelPosition = labelPosition;
    }
    
    public double getLabelPosition() {
        return this.labelPosition;
    }
    
    protected void calcLabelPoint() {
        int label_x = (int)((this.headx - this.tailx) * this.getLabelPosition() + this.tailx + 0.5);
        int label_y = (int)((this.heady - this.taily) * this.getLabelPosition() + this.taily + 0.5);
        if (this.graphicsContext != null && this.drawnLabel != null) {
            final Dimension size = this.drawnLabel.getSize(this.graphicsContext);
            final double abs = Math.abs((this.heady - this.taily) / (this.headx - this.tailx));
            final double sqrt = Math.sqrt(abs * abs * this.getLabelDist() * this.getLabelDist() / (abs * abs + 1.0));
            final double n = sqrt / abs;
            if ((this.headx < this.tailx && this.heady < this.taily) || (this.headx > this.tailx && this.heady > this.taily)) {
                if (this.labelSide == 0) {
                    label_x -= (int)(sqrt + size.width);
                    label_y += (int)(n + size.height);
                }
                else {
                    label_x += (int)sqrt;
                    label_y -= (int)n;
                }
            }
            else if ((this.headx < this.tailx && this.heady > this.taily) || (this.headx > this.tailx && this.heady < this.taily)) {
                if (this.labelSide == 0) {
                    label_x -= (int)(sqrt + size.width);
                    label_y -= (int)n;
                }
                else {
                    label_x += (int)sqrt;
                    label_y += (int)(n + size.height);
                }
            }
            else if ((int)(this.headx + 0.5) == (int)(this.tailx + 0.5)) {
                if (this.labelSide == 0) {
                    label_x -= this.getLabelDist() + size.width;
                }
                else {
                    label_x += this.getLabelDist();
                }
            }
            else if ((int)(this.heady + 0.5) == (int)(this.taily + 0.5)) {
                if (this.labelSide == 0) {
                    label_y -= this.getLabelDist();
                    label_x -= size.width;
                }
                else {
                    label_y += this.getLabelDist() + size.height;
                }
            }
        }
        this.label_x = label_x;
        this.label_y = label_y;
    }
    
    private int getLabelDist() {
        return this.labelDist;
    }
    
    public void setLabelDist(final int labelDist) {
        this.labelDist = labelDist;
    }
    
    protected void calcLength() {
        this.length = Math.sqrt(this.xComponent * this.xComponent + this.yComponent * this.yComponent);
    }
    
    protected void calculate() {
        this.xComponent = this.headx - this.tailx;
        this.yComponent = this.heady - this.taily;
        this.calcLength();
        if (this.snapToZeroDist > 0.0 && this.length > 0.1) {
            if (this.length <= this.snapToZeroDist) {
                this.drawnLabel = this.zeroLabel;
                this.setHead(this.getTail2D());
            }
            else {
                this.calcAnchors();
                if (this.length > 0.1) {
                    this.drawnLabel = this.label;
                    this.calcDir();
                }
                this.calcLabelPoint();
            }
        }
        else {
            if (this.length < 0.01) {
                this.drawnLabel = this.zeroLabel;
            }
            this.calcAnchors();
            if (this.length > 0.1) {
                this.drawnLabel = this.label;
                this.calcDir();
            }
            this.calcLabelPoint();
        }
        this.setPolygons();
    }
    
    protected void setPolygons() {
        final Point2D.Double pt = new Point2D.Double(this.headx, this.heady);
        final Point2D.Double double1 = new Point2D.Double(this.tailx, this.taily);
        if (this.style == 0) {
            this.linePolygon = VectorUtil.getLinePolygon(double1, VectorUtil.translatePoint2D(double1, VectorUtil.findAngle(double1, pt), double1.distance(pt) - this.arrowLength / 3), this.vectorWidth);
            this.arrowheadPolygon = VectorUtil.getArrowheadPolygon(double1, pt, this.vectorWidth, this.arrowLength);
        }
        else if (this.style == 1) {
            this.linePolygon = VectorUtil.getLinePolygon(double1, pt, this.vectorWidth);
        }
    }
    
    public void setSnapToZeroDist(final double snapToZeroDist) {
        this.snapToZeroDist = snapToZeroDist;
    }
    
    public boolean checkPoint(final int n, final int n2) {
        return Line2D.ptSegDistSq(this.tailx, this.taily, this.headx, this.heady, n, n2) <= 16.0;
    }
    
    public double getXComponent() {
        return this.xComponent;
    }
    
    public double getYComponent() {
        return this.yComponent;
    }
    
    public double getDirection() {
        this.calculate();
        return this.dir;
    }
    
    public Point2D.Double getHead2D() {
        return new Point2D.Double(this.headx, this.heady);
    }
    
    public double getHeadx() {
        return this.headx;
    }
    
    public double getHeady() {
        return this.heady;
    }
    
    public double getTailx() {
        return this.tailx;
    }
    
    public double getTaily() {
        return this.taily;
    }
    
    public boolean getHeadMoving() {
        return this.headMoving;
    }
    
    public int getLabelSide() {
        return this.labelSide;
    }
    
    public Point getLabelPoint() {
        return new Point(this.label_x, this.label_y);
    }
    
    public double getLength() {
        this.calculate();
        return this.length;
    }
    
    public boolean getMoving() {
        return this.moving;
    }
    
    public boolean getStill() {
        return this.isStill;
    }
    
    public Point2D.Double getTail2D() {
        return new Point2D.Double(this.tailx, this.taily);
    }
    
    public boolean getTailMoving() {
        return this.tailMoving;
    }
    
    public boolean isSelected() {
        return this.selected;
    }
    
    public boolean isEnabled() {
        return this.visible;
    }
    
    public boolean onHeadAnch(final Point2D.Double double1) {
        return this.onHeadAnch((int)(double1.getX() + 0.5), (int)(double1.getY() + 0.5));
    }
    
    public boolean onHeadAnch(final Point point) {
        return this.onHeadAnch(point.x, point.y);
    }
    
    public boolean onHeadAnch(final int n, final int n2) {
        return (n - this.headAnch.x) * (n - this.headAnch.x) + (n2 - this.headAnch.y) * (n2 - this.headAnch.y) <= 16;
    }
    
    public boolean onTailAnch(final Point2D.Double double1) {
        return this.onTailAnch((int)(double1.getX() + 0.5), (int)(double1.getY() + 0.5));
    }
    
    public boolean onTailAnch(final Point point) {
        return this.onTailAnch(point.x, point.y);
    }
    
    public boolean onTailAnch(final int n, final int n2) {
        return (n - this.tailAnch.x) * (n - this.tailAnch.x) + (n2 - this.tailAnch.y) * (n2 - this.tailAnch.y) <= 16;
    }
    
    public Vector2d getScaledVector(final double n) {
        final Vector2d vector2d = new Vector2d(this);
        vector2d.scale(n);
        return vector2d;
    }
    
    public void scale(final double n) {
        this.setHead(this.headx + (this.headx - this.tailx) * n, this.heady + (this.heady - this.taily) * n);
    }
    
    public void setHeadChangable(final boolean headChangable) {
        this.headChangable = headChangable;
    }
    
    public void setTailChangable(final boolean tailChangable) {
        this.tailChangable = tailChangable;
    }
    
    public boolean isHeadChangable() {
        return this.headChangable;
    }
    
    public boolean isTailChangable() {
        return this.tailChangable;
    }
    
    public boolean isMoveable() {
        return this.moveable;
    }
    
    public void setStyle(final int style) {
        this.style = style;
        this.setPolygons();
    }
    
    public int getStyle() {
        return this.style;
    }
    
    public void rotate(final Point2D.Double double1, final double n) {
        this.setHead(VectorUtil.rotatePoint(this.getHead2D(), double1, n));
        this.setTail(VectorUtil.rotatePoint(this.getTail2D(), double1, n));
    }
    
    public void paint(final Graphics graphicsContext) {
        this.graphicsContext = graphicsContext;
        if (this.visible) {
            this.calcLabelPoint();
            if (this.style == 0) {
                VectorUtil.drawVector(graphicsContext, this.linePolygon, this.arrowheadPolygon, this.getTail2D(), this.getHead2D(), this.outlineColor, this.fillColor);
            }
            else if (this.style == 1) {
                VectorUtil.drawLine(graphicsContext, this.linePolygon, this.outlineColor, this.fillColor);
            }
            else if (this.style == 2) {
                VectorUtil.drawDotLine(graphicsContext, this.getTail2D(), this.getHead2D(), this.dotDiameter, this.fillColor);
            }
            this.headAnch.draw(graphicsContext);
            this.tailAnch.draw(graphicsContext);
            if (this.showInfo && this.drawnLabel != null) {
                if (this.labelIsOpaque && this.drawnLabel instanceof ComplexString) {
                    ((ComplexString)this.drawnLabel).drawOpaque(graphicsContext, this.label_x, this.label_y, Color.white, this.drawLabelBorder);
                }
                else {
                    this.drawnLabel.draw(graphicsContext, this.label_x, this.label_y);
                }
            }
        }
    }
    
    public void setAnchorColors(final Color color) {
        this.tailAnch.setInnerColor(color);
        this.headAnch.setInnerColor(color);
    }
    
    public void setChangable(final boolean b) {
        this.headChangable = b;
        this.tailChangable = b;
    }
    
    public void setChangable(final boolean headChangable, final boolean tailChangable) {
        this.headChangable = headChangable;
        this.tailChangable = tailChangable;
    }
    
    public void setColor2(final Color fillColor) {
        this.fillColor = fillColor;
    }
    
    public void setColor(final Color color) {
        this.outlineColor = color;
        this.fillColor = color;
    }
    
    public void setFillColor(final Color fillColor) {
        this.fillColor = fillColor;
    }
    
    public Color getFillColor() {
        return this.fillColor;
    }
    
    public void setOutlineColor(final Color outlineColor) {
        this.outlineColor = outlineColor;
    }
    
    public Color getOutlineColor() {
        return this.outlineColor;
    }
    
    public void setEnabled(final boolean visible) {
        this.visible = visible;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void setHead(final Point2D.Double double1) {
        this.setHead(double1.x, double1.y);
    }
    
    public void setHeadPoint(final Point point) {
        this.setHead(point.x, point.y);
    }
    
    public void setHead(final double headx, final double heady) {
        if (this.directionFrozen) {
            this.translateToFrozenLine(headx, heady, 1);
        }
        else {
            this.headx = headx;
            this.heady = heady;
        }
        this.calculate();
    }
    
    public void translateHead(final double n, final double n2) {
        this.setHead(this.headx + n, this.heady + n2);
    }
    
    public void setHeadMoving(final boolean headMoving) {
        this.headMoving = headMoving;
        this.isStill = !headMoving;
        this.moving = !headMoving;
        this.tailMoving = !headMoving;
    }
    
    public void reverse() {
        final double headx = this.headx;
        final double heady = this.heady;
        this.headx = this.tailx;
        this.heady = this.taily;
        this.tailx = headx;
        this.taily = heady;
        this.calculate();
    }
    
    public void translateVectorOnTail(final double n, final double n2) {
        this.translateVector(n - this.tailx, n2 - this.taily);
    }
    
    public void translateVectorOnHead(final double n, final double n2) {
        this.translateVector(n - this.headx, n2 - this.heady);
    }
    
    public void translateVector(final double n, final double n2) {
        this.headx += n;
        this.heady += n2;
        this.tailx += n;
        this.taily += n2;
        this.calculate();
    }
    
    public void setLabel(final VectorLabelComponent label) {
        this.label = label;
        this.zeroLabel = this.label;
        this.drawnLabel = this.label;
    }
    
    public void setLabelVisible(final boolean showInfo) {
        this.showInfo = showInfo;
    }
    
    public void setZeroLabel(final VectorLabelComponent zeroLabel) {
        this.zeroLabel = zeroLabel;
    }
    
    public void setLabelSide(final int labelSide) {
        this.labelSide = labelSide;
    }
    
    public void setMoveable(final boolean moveable) {
        this.moveable = moveable;
    }
    
    public void setMoving(final boolean moving) {
        this.moving = moving;
        this.isStill = !moving;
        this.headMoving = !moving;
        this.tailMoving = !moving;
    }
    
    public void setDotDiameter(final double dotDiameter) {
        this.dotDiameter = dotDiameter;
    }
    
    public void setNormal() {
        this.setSelected(false);
        this.setStill(true);
        this.showInfo = false;
        this.setColor(Color.black);
    }
    
    public void setSelected(final boolean selected) {
        this.selected = selected;
    }
    
    public void showAnchors(final boolean b) {
        this.headAnch.setVisible(b);
        this.tailAnch.setVisible(b);
    }
    
    public void showTailAnchor(final boolean visible) {
        this.tailAnch.setVisible(visible);
    }
    
    public void showHeadAnchor(final boolean visible) {
        this.headAnch.setVisible(visible);
    }
    
    public void setStill(final boolean isStill) {
        this.isStill = isStill;
        this.tailMoving = !isStill;
        this.moving = !isStill;
        this.headMoving = !isStill;
    }
    
    public void setTailPoint(final Point point) {
        this.setTail(point.x, point.y);
    }
    
    public void setTail(final Point2D.Double double1) {
        this.setTail(double1.x, double1.y);
    }
    
    public void setTail(final double tailx, final double taily) {
        if (this.directionFrozen) {
            this.translateToFrozenLine(tailx, taily, 2);
        }
        else {
            this.tailx = tailx;
            this.taily = taily;
            this.calculate();
        }
    }
    
    public void translateTail(final double n, final double n2) {
        this.setTail(this.tailx + n, this.taily + n2);
    }
    
    public void setTailMoving(final boolean tailMoving) {
        this.tailMoving = tailMoving;
        this.isStill = !tailMoving;
        this.moving = !tailMoving;
        this.headMoving = !tailMoving;
    }
    
    public void setVector(final Point point, final Point point2) {
        this.headx = point2.x;
        this.heady = point2.y;
        this.tailx = point.x;
        this.taily = point.y;
        this.calculate();
    }
    
    public void setVector(final double tailx, final double taily, final double headx, final double heady) {
        this.headx = headx;
        this.heady = heady;
        this.tailx = tailx;
        this.taily = taily;
        this.calculate();
    }
    
    public void setVector(final Point2D.Double tail, final Point2D.Double head) {
        this.setHead(head);
        this.setTail(tail);
    }
    
    public void setVectorWidth(final double vectorWidth) {
        this.vectorWidth = vectorWidth;
    }
    
    public void setVectorSize(final double vectorWidth, final int arrowLength) {
        this.vectorWidth = vectorWidth;
        this.arrowLength = arrowLength;
    }
    
    public void translateToLine(final Point2D.Double double1, final int n) {
        this.translateToLine(double1.x, double1.y, n);
    }
    
    public void translateToLine(final double n, final double n2, final int n3) {
        double headx;
        double heady;
        if (this.headx == this.tailx && this.heady == this.taily) {
            headx = this.headx - Math.cos(this.dir);
            heady = this.heady + Math.sin(this.dir);
        }
        else if (this.headx == this.tailx) {
            headx = this.headx;
            heady = n2;
        }
        else if (this.heady == this.taily) {
            headx = n;
            heady = this.heady;
        }
        else {
            final double n4 = (this.taily - this.heady) / (this.tailx - this.headx);
            final double n5 = -1.0 / n4;
            headx = (n2 - this.heady + n4 * this.headx - n5 * n) / (n4 - n5);
            heady = n4 * (headx - this.headx) + this.heady;
        }
        if (n3 == 1) {
            this.headx = headx;
            this.heady = heady;
        }
        else {
            this.tailx = headx;
            this.taily = heady;
        }
        this.calculate();
    }
    
    public void translateToFrozenLine(final double n, final double n2, final int n3) {
        double headx;
        double heady;
        if (this.headx == this.tailx && this.heady == this.taily) {
            headx = this.headx - Math.cos(this.frozenDirection);
            heady = this.heady + Math.sin(this.frozenDirection);
        }
        else if (this.headx == this.tailx) {
            headx = this.headx;
            heady = n2;
        }
        else if (this.heady == this.taily) {
            headx = n;
            heady = this.heady;
        }
        else {
            final double n4 = (this.taily - this.heady) / (this.tailx - this.headx);
            final double n5 = -1.0 / n4;
            headx = (n2 - this.heady + n4 * this.headx - n5 * n) / (n4 - n5);
            heady = n4 * (headx - this.headx) + this.heady;
        }
        if (n3 == 1) {
            this.headx = headx;
            this.heady = heady;
        }
        else {
            this.tailx = headx;
            this.taily = heady;
        }
        this.calculate();
    }
    
    static {
        MOVING_VECTOR = Color.green;
        MOVING_VECTOR_TIP = Color.green;
    }
}
