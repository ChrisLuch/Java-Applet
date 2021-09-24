// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.Graphics;
import ca.ucalgary.phas.map.utilities.vectors.VectorUtil;
import java.awt.Point;
import java.awt.Font;
import java.awt.Color;
import java.awt.geom.Point2D;

public class CartGraph
{
    public static final double LSIZE = 4.0;
    public static final double INITZOOM = 40.0;
    public static final int TIPSIZE = 9;
    public static final double AXISWIDTH = 3.0;
    public static final double ARROWLEN = 15.0;
    public String unitLabel;
    public boolean showUnits;
    protected Point2D.Double center;
    protected double xAngle;
    protected double yAngle;
    protected double axisAngle;
    protected double xLength;
    protected double yLength;
    protected Point2D.Double yTail;
    protected Point2D.Double yTip;
    protected Point2D.Double xTail;
    protected Point2D.Double xTip;
    public double zoom;
    protected boolean xMoving;
    protected boolean yMoving;
    public boolean freeAxis;
    public boolean moveable;
    public boolean number_axes;
    public Color axisColor;
    public Color axisLabelColor;
    public Font defaultFont;
    public Font bigFont;
    public Font boldFont;
    
    public CartGraph(final int n, final int n2) {
        this.unitLabel = " (m)";
        this.showUnits = false;
        this.xMoving = false;
        this.yMoving = false;
        this.freeAxis = false;
        this.moveable = true;
        this.number_axes = true;
        this.axisColor = Color.black;
        this.axisLabelColor = Color.red;
        this.defaultFont = new Font("Serif", 0, 14);
        this.bigFont = new Font("Serif", 0, 28);
        this.boldFont = new Font("Serif", 1, 14);
        this.center = new Point2D.Double(n, n2);
        this.yAngle = this.roundAngle(1.5707963267948966);
        this.xAngle = 0.0;
        this.setAxisAngle();
        this.zoom = 40.0;
        this.xLength = 200.0;
        this.yLength = 200.0;
        this.calculateTips();
    }
    
    public CartGraph(final int n, final int n2, final double n3, final double n4) {
        this.unitLabel = " (m)";
        this.showUnits = false;
        this.xMoving = false;
        this.yMoving = false;
        this.freeAxis = false;
        this.moveable = true;
        this.number_axes = true;
        this.axisColor = Color.black;
        this.axisLabelColor = Color.red;
        this.defaultFont = new Font("Serif", 0, 14);
        this.bigFont = new Font("Serif", 0, 28);
        this.boldFont = new Font("Serif", 1, 14);
        this.center = new Point2D.Double(n, n2);
        this.yAngle = this.roundAngle(3.141592653589793 * n3 / 180.0);
        this.xAngle = this.roundAngle(3.141592653589793 * n4 / 180.0);
        this.setAxisAngle();
        this.zoom = 40.0;
        this.xLength = 200.0;
        this.yLength = 200.0;
        this.yTail = new Point2D.Double();
        this.yTip = new Point2D.Double();
        this.xTail = new Point2D.Double();
        this.xTip = new Point2D.Double();
        this.calculateTips();
    }
    
    public void calculateTips() {
        final double n = Math.cos(this.xAngle) * this.xLength;
        final double n2 = Math.sin(this.xAngle) * this.xLength;
        final double n3 = Math.cos(this.yAngle) * this.yLength;
        final double n4 = Math.sin(this.yAngle) * this.yLength;
        this.yTail.x = this.center.x - n3;
        this.yTip.x = this.center.x + n3;
        this.yTail.y = this.center.y + n4;
        this.yTip.y = this.center.y - n4;
        this.xTail.x = this.center.x - n;
        this.xTip.x = this.center.x + n;
        this.xTail.y = this.center.y + n2;
        this.xTip.y = this.center.y - n2;
    }
    
    public void translate(final double n, final double n2) {
        final Point2D.Double center = this.center;
        center.x += n;
        final Point2D.Double center2 = this.center;
        center2.y += n2;
        this.calculateTips();
    }
    
    public double getAxisAngle() {
        return this.axisAngle;
    }
    
    public Point2D.Double getCenter() {
        return this.center;
    }
    
    public Point getLabelPoint(final Point2D.Double double1, double inBounds, final double n) {
        final Point2D.Double translatePoint2D = VectorUtil.translatePoint2D(double1, inBounds, n);
        final Point point = new Point((int)(translatePoint2D.getX() + 0.5), (int)(translatePoint2D.getY() + 0.5));
        inBounds = this.inBounds(inBounds);
        if (inBounds > 5.497787143782138) {
            point.translate(-10, 18);
        }
        else if (inBounds > 4.71238898038469) {
            point.translate(8, -4);
        }
        else if (inBounds > 3.9269908169872414) {
            point.translate(-18, -4);
        }
        else if (inBounds > 3.141592653589793) {
            point.translate(8, 16);
        }
        else if (inBounds > 2.356194490192345) {
            point.translate(4, -8);
        }
        else if (inBounds > 1.5707963267948966) {
            point.translate(-14, 16);
        }
        else if (inBounds > 0.7853981633974483) {
            point.translate(8, 16);
        }
        else if (inBounds > 0.0) {
            point.translate(-4, -8);
        }
        else {
            point.translate(0, -6);
        }
        return point;
    }
    
    public double getXAngle() {
        return this.xAngle;
    }
    
    public double getYAngle() {
        return this.yAngle;
    }
    
    private double inBounds(double n) {
        if (n < 0.0) {
            n += 6.283185307179586;
        }
        if (n >= 6.283185307179586) {
            n -= 6.283185307179586;
        }
        return n;
    }
    
    public boolean onXTip(final Point point) {
        for (int i = (int)this.xTip.x - 9; i <= (int)this.xTip.x + 9; ++i) {
            for (int j = (int)this.xTip.y - 9; j <= (int)this.xTip.y + 9; ++j) {
                if (point.x == i && point.y == j) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean onYTip(final Point point) {
        for (int i = (int)this.yTip.x - 9; i <= (int)this.yTip.x + 9; ++i) {
            for (int j = (int)this.yTip.y - 9; j <= (int)this.yTip.y + 9; ++j) {
                if (point.x == i && point.y == j) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void paintGraph(final Graphics graphics) {
        this.calculateTips();
        final Color color = graphics.getColor();
        graphics.setColor(this.axisLabelColor);
        final Point2D.Double translatePoint2D = VectorUtil.translatePoint2D(this.center, this.xAngle, 4.0);
        final Point2D.Double translatePoint2D2 = VectorUtil.translatePoint2D(this.center, this.xAngle + 3.141592653589793, 4.0);
        VectorUtil.drawLine(graphics, 3.0, this.center, this.yTail, this.axisColor, this.axisColor);
        final double n = this.yAngle + 3.141592653589793;
        int n2 = 1;
        for (double zoom = this.zoom; zoom <= this.yLength; zoom += this.zoom) {
            final Point2D.Double translatePoint2D3 = VectorUtil.translatePoint2D(translatePoint2D, n, zoom);
            final Point2D.Double translatePoint2D4 = VectorUtil.translatePoint2D(translatePoint2D2, n, zoom);
            graphics.drawLine((int)(translatePoint2D3.x + 0.5), (int)(translatePoint2D3.y + 0.5), (int)(translatePoint2D4.x + 0.5), (int)(translatePoint2D4.y + 0.5));
            if (this.number_axes) {
                final Point labelPoint = this.getLabelPoint(this.center, n, zoom);
                graphics.drawString("-" + n2++, labelPoint.x, labelPoint.y);
            }
        }
        VectorUtil.drawVector(graphics, 3.0, 15.0, this.center, this.yTip, this.axisColor, this.axisColor);
        int n3 = 1;
        for (double zoom2 = this.zoom; zoom2 <= this.yLength; zoom2 += this.zoom) {
            final Point2D.Double translatePoint2D5 = VectorUtil.translatePoint2D(translatePoint2D, this.yAngle, zoom2);
            final Point2D.Double translatePoint2D6 = VectorUtil.translatePoint2D(translatePoint2D2, this.yAngle, zoom2);
            graphics.drawLine((int)(translatePoint2D5.x + 0.5), (int)(translatePoint2D5.y + 0.5), (int)(translatePoint2D6.x + 0.5), (int)(translatePoint2D6.y + 0.5));
            if (this.number_axes) {
                final Point labelPoint2 = this.getLabelPoint(this.center, this.yAngle, zoom2);
                graphics.drawString("" + n3++, labelPoint2.x, labelPoint2.y);
            }
        }
        if (this.yTip.y < this.center.y) {
            graphics.drawString("y" + (this.showUnits ? this.unitLabel : ""), (int)this.yTip.x, (int)this.yTip.y - 10);
        }
        else {
            graphics.drawString("y" + (this.showUnits ? this.unitLabel : ""), (int)this.yTip.x, (int)this.yTip.y + 20);
        }
        final Point2D.Double translatePoint2D7 = VectorUtil.translatePoint2D(this.center, this.yAngle, 4.0);
        final Point2D.Double translatePoint2D8 = VectorUtil.translatePoint2D(this.center, this.yAngle + 3.141592653589793, 4.0);
        VectorUtil.drawLine(graphics, 3.0, this.center, this.xTail, this.axisColor, this.axisColor);
        final double n4 = this.xAngle + 3.141592653589793;
        int n5 = 1;
        for (double zoom3 = this.zoom; zoom3 <= this.xLength; zoom3 += this.zoom) {
            final Point2D.Double translatePoint2D9 = VectorUtil.translatePoint2D(translatePoint2D7, n4, zoom3);
            final Point2D.Double translatePoint2D10 = VectorUtil.translatePoint2D(translatePoint2D8, n4, zoom3);
            graphics.drawLine((int)(translatePoint2D9.x + 0.5), (int)(translatePoint2D9.y + 0.5), (int)(translatePoint2D10.x + 0.5), (int)(translatePoint2D10.y + 0.5));
            if (this.number_axes) {
                final Point labelPoint3 = this.getLabelPoint(this.center, n4, zoom3);
                graphics.drawString("-" + n5++, labelPoint3.x, labelPoint3.y);
            }
        }
        VectorUtil.drawVector(graphics, 3.0, 15.0, this.center, this.xTip, this.axisColor, this.axisColor);
        int n6 = 1;
        for (double zoom4 = this.zoom; zoom4 <= this.xLength; zoom4 += this.zoom) {
            final Point2D.Double translatePoint2D11 = VectorUtil.translatePoint2D(translatePoint2D7, this.xAngle, zoom4);
            final Point2D.Double translatePoint2D12 = VectorUtil.translatePoint2D(translatePoint2D8, this.xAngle, zoom4);
            graphics.drawLine((int)(translatePoint2D11.x + 0.5), (int)(translatePoint2D11.y + 0.5), (int)(translatePoint2D12.x + 0.5), (int)(translatePoint2D12.y + 0.5));
            if (this.number_axes) {
                final Point labelPoint4 = this.getLabelPoint(this.center, this.xAngle, zoom4);
                graphics.drawString("" + n6++, labelPoint4.x, labelPoint4.y);
            }
        }
        if (this.xTip.y < this.center.y) {
            graphics.drawString("x" + (this.showUnits ? this.unitLabel : ""), (int)this.xTip.x, (int)this.xTip.y - 10);
        }
        else {
            graphics.drawString("x" + (this.showUnits ? this.unitLabel : ""), (int)this.xTip.x, (int)this.xTip.y + 20);
        }
        graphics.setColor(color);
    }
    
    public double roundAngle(final double n) {
        return 3.141592653589793 * (int)(n * 720.0 / 3.141592653589793) / 720.0;
    }
    
    private void setAxisAngle() {
        this.axisAngle = this.inBounds(this.yAngle - this.xAngle);
    }
    
    public void setHeight(final double n) {
        if (this.xLength > n) {
            this.xLength = n;
        }
        this.yLength = n;
    }
    
    public void setWidth(final double n) {
        if (this.yLength > n) {
            this.yLength = n;
        }
        this.xLength = n;
    }
    
    public void setXAngle(final Point point) {
        this.setXAngle(VectorUtil.findAngle(this.center, new Point2D.Double(point.x, point.y)));
    }
    
    public void setXAngle(final double n) {
        this.xAngle = this.inBounds(this.roundAngle(n));
        if (!this.freeAxis) {
            this.yAngle = this.inBounds(this.xAngle + this.axisAngle);
        }
        else {
            this.setAxisAngle();
        }
        this.calculateTips();
    }
    
    public void setXLen(final double yLength) {
        this.yLength = yLength;
    }
    
    public void setXMoving(final boolean xMoving) {
        this.xMoving = xMoving;
    }
    
    public void setYAngle(final Point point) {
        this.setYAngle(VectorUtil.findAngle(this.center, new Point2D.Double(point.x, point.y)));
    }
    
    public void setYAngle(final double n) {
        this.yAngle = this.inBounds(this.roundAngle(n));
        if (!this.freeAxis) {
            this.xAngle = this.inBounds(this.yAngle - this.axisAngle);
        }
        else {
            this.setAxisAngle();
        }
        this.calculateTips();
    }
    
    public void setYMoving(final boolean yMoving) {
        this.yMoving = yMoving;
    }
    
    public Point2D.Double translateFromGraph(final Point2D.Double double1) {
        return VectorUtil.translatePoint2D(VectorUtil.translatePoint2D(new Point2D.Double(this.center.x, this.center.y), this.xAngle, double1.x * this.zoom), this.yAngle, double1.y * this.zoom);
    }
    
    public Point2D.Double translateToGraph(final Point2D.Double double1) {
        final double angle = VectorUtil.findAngle(this.center, double1);
        final double sqrt = Math.sqrt((double1.y - this.center.y) * (double1.y - this.center.y) + (double1.x - this.center.x) * (double1.x - this.center.x));
        final Point2D.Double double2 = new Point2D.Double();
        double n = 0.0;
        double n2 = 0.0;
        final boolean b = this.axisAngle > 3.141592653589793;
        double n3;
        if (b) {
            n3 = this.inBounds(angle - this.yAngle);
        }
        else {
            n3 = this.inBounds(angle - this.xAngle);
        }
        final double inBounds = this.inBounds(6.283185307179586 - this.axisAngle);
        final double n4 = b ? inBounds : this.axisAngle;
        if (n3 < n4) {
            double2.x = Math.cos(this.xAngle - angle) * sqrt;
            n = -(Math.sqrt(sqrt * sqrt - double2.x * double2.x) / Math.tan(n4));
            double2.y = Math.cos(this.yAngle - angle) * sqrt;
            n2 = -(Math.sqrt(sqrt * sqrt - double2.y * double2.y) / Math.tan(n4));
        }
        if (n3 >= n4) {
            if (n3 <= 3.141592653589793) {
                double2.x = Math.cos(this.xAngle - angle) * sqrt;
                n = Math.sqrt(sqrt * sqrt - double2.x * double2.x) / Math.tan(this.inBounds(3.141592653589793 - n4));
                double2.y = Math.cos(this.yAngle - angle) * sqrt;
                n2 = -(Math.sqrt(sqrt * sqrt - double2.y * double2.y) / Math.tan(this.inBounds(3.141592653589793 - n4)));
                if (b) {
                    n *= -1.0;
                    n2 *= -1.0;
                }
            }
            if (n3 > 3.141592653589793) {
                if (n3 < this.inBounds(n4 + 3.141592653589793)) {
                    double2.x = Math.cos(this.xAngle - angle) * sqrt;
                    n = Math.sqrt(sqrt * sqrt - double2.x * double2.x) / Math.tan(n4);
                    double2.y = Math.cos(this.yAngle - angle) * sqrt;
                    n2 = Math.sqrt(sqrt * sqrt - double2.y * double2.y) / Math.tan(n4);
                }
                if (n3 >= this.inBounds(n4 + 3.141592653589793)) {
                    double2.x = Math.cos(this.xAngle - angle) * sqrt;
                    n = -(Math.sqrt(sqrt * sqrt - double2.x * double2.x) / Math.tan(this.inBounds(3.141592653589793 - n4)));
                    double2.y = Math.cos(this.yAngle - angle) * sqrt;
                    n2 = Math.sqrt(sqrt * sqrt - double2.y * double2.y) / Math.tan(this.inBounds(3.141592653589793 - n4));
                    if (b) {
                        n2 *= -1.0;
                        n *= -1.0;
                    }
                }
            }
        }
        double2.x = (double2.x + n) / this.zoom;
        double2.y = (double2.y + n2) / this.zoom;
        return double2;
    }
    
    public double translateToLine(final double n, final double n2, final double n3) {
        final double cos = Math.cos(n2 * n3);
        Math.sqrt(n3 * n3 - cos * cos);
        return cos / this.zoom;
    }
    
    public boolean xIsMoving() {
        return this.xMoving;
    }
    
    public boolean yIsMoving() {
        return this.yMoving;
    }
}
