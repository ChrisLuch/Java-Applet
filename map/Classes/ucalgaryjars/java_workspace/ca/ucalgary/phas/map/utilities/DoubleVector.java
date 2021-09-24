// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.Point;

public class DoubleVector
{
    protected DoublePoint head;
    protected DoublePoint tail;
    
    public DoubleVector() {
        this(new DoublePoint(), new DoublePoint());
    }
    
    public DoubleVector(final DoubleVector doubleVector) {
        this(doubleVector.getHead(), doubleVector.getTail());
    }
    
    public DoubleVector(final Object head) {
        this.setHead(head);
        this.setTail(new DoublePoint(0.0, 0.0));
    }
    
    public DoubleVector(final Object head, final Object tail) {
        this.setHead(head);
        this.setTail(tail);
    }
    
    public DoubleVector add(final DoubleVector doubleVector) {
        return new DoubleVector(this.head.add(doubleVector.getComponents().x, doubleVector.getComponents().y), new DoublePoint(this.tail));
    }
    
    public double getAngle() {
        double atan = Math.atan(Math.abs(this.getComponents().y) / Math.abs(this.getComponents().x));
        if (this.head.x < this.tail.x) {
            atan = 3.141592653589793 - atan;
        }
        if (this.head.y > this.tail.y) {
            atan *= -1.0;
        }
        return atan;
    }
    
    public DoublePoint getComponents() {
        return new DoublePoint(this.head.x - this.tail.x, this.head.y - this.tail.y);
    }
    
    public DoubleVector getDirectionVector() {
        return this.multiply(1.0 / this.getLength());
    }
    
    public DoublePoint getHead() {
        return new DoublePoint(this.head);
    }
    
    public Point getHeadPt() {
        return this.head.getPoint();
    }
    
    public double getLength() {
        return DoublePoint.getDistance(this.head, this.tail);
    }
    
    public DoubleVector getProjection(final DoublePoint doublePoint) {
        if (this.head.equals(this.tail)) {
            return new DoubleVector(new DoublePoint(), new DoublePoint());
        }
        final DoublePoint doublePoint2 = new DoublePoint(doublePoint.x - this.head.x, doublePoint.y - this.head.y);
        final DoublePoint doublePoint3 = new DoublePoint(this.head.x - this.tail.x, this.head.y - this.tail.y);
        final double n = (doublePoint2.x * doublePoint3.x + doublePoint2.y * doublePoint3.y) / (Math.pow(doublePoint3.x, 2.0) + Math.pow(doublePoint3.y, 2.0));
        final DoublePoint doublePoint4 = new DoublePoint((int)Math.round(n * doublePoint3.x), (int)Math.round(n * doublePoint3.y));
        return new DoubleVector(doublePoint, new DoublePoint(this.head.x + doublePoint4.x, this.head.y + doublePoint4.y));
    }
    
    public DoubleVector getReverse() {
        return new DoubleVector(this.tail, this.head);
    }
    
    public DoubleVector getRotation(final Object o, final double n) {
        final DoubleVector doubleVector = new DoubleVector(this);
        doubleVector.rotate(o, n);
        return doubleVector;
    }
    
    public DoublePoint getTail() {
        return new DoublePoint(this.tail);
    }
    
    public Point getTailPt() {
        return this.tail.getPoint();
    }
    
    public DoubleVector multiply(final double n) {
        final DoublePoint multiply = this.getComponents().multiply(n);
        return new DoubleVector(new DoublePoint(this.tail.x + multiply.x, this.tail.y + multiply.y), this.tail);
    }
    
    public void rotate(final Object o, final double n) {
        final DoublePoint components = this.getComponents();
        this.tail.rotate(o, n);
        this.head.setValue(this.tail);
        this.head.translate(components.x, components.y);
    }
    
    public void setHead(final Object o) {
        if (o instanceof DoublePoint) {
            this.head = new DoublePoint((DoublePoint)o);
        }
        else if (o instanceof Point) {
            this.head = new DoublePoint((Point)o);
        }
        else {
            this.head = new DoublePoint();
        }
    }
    
    public void setTail(final Object o) {
        if (o instanceof DoublePoint) {
            this.tail = new DoublePoint((DoublePoint)o);
        }
        else if (o instanceof Point) {
            this.tail = new DoublePoint((Point)o);
        }
        else {
            this.tail = new DoublePoint();
        }
    }
    
    public void setVector(final Object o) {
        if (o instanceof DoubleVector) {
            this.setVector(((DoubleVector)o).getHead(), ((DoubleVector)o).getTail());
        }
        if (o instanceof VectorDraw) {
            this.setVector(((VectorDraw)o).getHead(), ((VectorDraw)o).getTail());
        }
    }
    
    public void setVector(final Object head, final Object tail) {
        this.setHead(head);
        this.setTail(tail);
    }
    
    public String toString() {
        return this.getClass().getName() + "(" + this.getComponents().x + "," + this.getComponents().y + ") Head(" + this.head.x + "," + this.head.y + ") tail(" + this.tail.x + "," + this.tail.y + ")";
    }
    
    public DoubleVector translate(final DoublePoint doublePoint) {
        this.head.translate(doublePoint.x, doublePoint.y);
        this.tail.translate(doublePoint.x, doublePoint.y);
        return this;
    }
}
