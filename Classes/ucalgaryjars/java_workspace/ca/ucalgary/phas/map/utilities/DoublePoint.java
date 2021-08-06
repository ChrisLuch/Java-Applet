// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.Point;

public class DoublePoint
{
    public double x;
    public double y;
    
    public DoublePoint() {
        this(0.0, 0.0);
    }
    
    public DoublePoint(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    public DoublePoint(final DoublePoint doublePoint) {
        this(doublePoint.x, doublePoint.y);
    }
    
    public DoublePoint(final Point point) {
        this(point.x, point.y);
    }
    
    public DoublePoint add(final double n, final double n2) {
        return new DoublePoint(this.x + n, this.y + n2);
    }
    
    public boolean equals(final Object o) {
        if (o instanceof DoublePoint) {
            final DoublePoint doublePoint = (DoublePoint)o;
            return this.x == doublePoint.x && this.y == doublePoint.y;
        }
        return false;
    }
    
    public static double getDistance(final double n, final double n2, final double n3, final double n4) {
        return Math.sqrt(Math.pow(n - n3, 2.0) + Math.pow(n2 - n4, 2.0));
    }
    
    public static double getDistance(final DoublePoint doublePoint, final DoublePoint doublePoint2) {
        return getDistance(doublePoint.x, doublePoint.y, doublePoint2.x, doublePoint2.y);
    }
    
    public Point getPoint() {
        return new Point((int)Math.round(this.x), (int)Math.round(this.y));
    }
    
    public DoublePoint getRotation(final Object o, final double n) {
        final DoublePoint doublePoint = new DoublePoint(this);
        doublePoint.rotate(o, n);
        return doublePoint;
    }
    
    public DoublePoint multiply(final double n) {
        return new DoublePoint(this.x * n, this.y * n);
    }
    
    public void rotate(final Object o, final double n) {
        DoublePoint doublePoint;
        if (o instanceof Point) {
            doublePoint = new DoublePoint((Point)o);
        }
        else {
            if (!(o instanceof DoublePoint)) {
                return;
            }
            doublePoint = (DoublePoint)o;
        }
        final DoublePoint doublePoint2 = new DoublePoint(this.x - doublePoint.x, doublePoint.y - this.y);
        final double sqrt = Math.sqrt(doublePoint2.x * doublePoint2.x + doublePoint2.y * doublePoint2.y);
        double atan = Math.atan(doublePoint2.y / doublePoint2.x);
        if (doublePoint2.x < 0.0 && doublePoint2.y < 0.0) {
            atan += 3.141592653589793;
        }
        else if (doublePoint2.x < 0.0 && doublePoint2.y >= 0.0) {
            atan += 3.141592653589793;
        }
        final double n2 = atan + n;
        this.x = sqrt * Math.cos(n2) + doublePoint.x;
        this.y = doublePoint.y - sqrt * Math.sin(n2);
    }
    
    public void setValue(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    public void setValue(final DoublePoint doublePoint) {
        this.setValue(doublePoint.x, doublePoint.y);
    }
    
    public String toString() {
        return this.getClass().getName() + "[x=" + this.x + ",y=" + this.y + "]";
    }
    
    public void translate(final double n, final double n2) {
        this.x += n;
        this.y += n2;
    }
}
