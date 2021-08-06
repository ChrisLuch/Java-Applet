// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors.laf;

import java.awt.Color;
import java.awt.Graphics;

class vector3 extends onAxes
{
    point3 head;
    point3 tail;
    
    public vector3() {
        this.head = new point3();
        this.tail = new point3();
    }
    
    public vector3(final double n, final double n2, final double n3) {
        this.head = new point3();
        this.tail = new point3();
        this.head.setPt(n, n2, n3);
        this.tail.setPt(0.0, 0.0, 0.0);
    }
    
    public vector3(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        this.head = new point3();
        this.tail = new point3();
        this.head.setPt(n4, n5, n6);
        this.tail.setPt(n, n2, n3);
    }
    
    public vector3(final point3 point3) {
        this.head = new point3();
        this.tail = new point3();
        this.tail = new point3(0.0, 0.0, 0.0);
        this.head = new point3(point3);
    }
    
    public vector3(final point3 tail, final point3 head) {
        this.head = new point3();
        this.tail = new point3();
        this.head = head;
        this.tail = tail;
    }
    
    public vector3(final vector3 vector3) {
        this.head = new point3();
        this.tail = new point3();
        this.head = new point3(vector3.head());
        this.tail = new point3(vector3.tail());
    }
    
    public vector3 cross(final vector3 vector3) {
        final vector3 position = this.position();
        final vector3 position2 = vector3.position();
        return new vector3(position.head().y() * position2.head().z() - position.head().z() * position2.head().y(), position.head().z() * position2.head().x() - position.head().x() * position2.head().z(), position.head().x() * position2.head().y() - position.head().y() * position2.head().x());
    }
    
    public void displayVector(final String x) {
        System.out.println(x);
        System.out.println(this.tail.x());
        System.out.println(this.tail.y());
        System.out.println(this.tail.z());
        System.out.println(this.tail.z());
        System.out.println(this.head.x());
        System.out.println(this.head.y());
        System.out.println(this.head.z());
        System.out.println(this.head.w());
    }
    
    public vector3 div(final double n) {
        return new vector3(this.tail.x() / n, this.tail.y() / n, this.tail.z() / n, this.head.x() / n, this.head.y() / n, this.head.z() / n);
    }
    
    public double dot(final vector3 vector3) {
        return this.head.x() * vector3.head.x() + this.head.y() * vector3.head.y() + this.head.z() * vector3.head.z();
    }
    
    public void drawLine(final Graphics graphics, final Color color) {
        this.vectorValue().drawLine(graphics, color);
    }
    
    public void drawRight(final Graphics graphics, final vector3 vector3) {
        final vector3 vector4 = new vector3(this);
        final vector3 vector5 = new vector3(vector3);
        final point3 head = vector5.head();
        final vector3 vector6 = new vector3(vector4.head(), vector4.tail());
        final vector3 vector7 = new vector3(vector5.head(), vector5.tail());
        final vector3 position = vector6.position();
        final vector3 times = position.times(0.2).times(1.0 / position.length());
        final point3 plus = times.head().plus(head);
        final vector3 position2 = vector7.position();
        final vector3 times2 = position2.times(0.2).times(1.0 / position2.length());
        final point3 plus2 = times2.head().plus(head);
        final point3 head2 = vector5.plus(times.plus(times2)).head();
        final point3 point3 = this.getPoint3(plus.pointValue());
        final point3 point4 = vector3.getPoint3(plus2.pointValue());
        final point3 head3 = vector3.head();
        final vector3 logValue = new vector3(head2, point3).logValue();
        final vector3 logValue2 = new vector3(head2, point4).logValue();
        final vector3 logValue3 = new vector3(head3, point3).logValue();
        final vector3 logValue4 = new vector3(head3, point4).logValue();
        logValue.drawLine(graphics, Color.black);
        logValue2.drawLine(graphics, Color.black);
        logValue3.drawLine(graphics, Color.black);
        logValue4.drawLine(graphics, Color.black);
    }
    
    public void drawVector(final Graphics graphics, final int n) {
        this.vectorValue().drawVector(graphics, n);
    }
    
    public void drawVector(final Graphics graphics, final int n, final Color color, final Color color2) {
        this.vectorValue().drawVector(graphics, n, color, color2);
    }
    
    public vector getLine(final point3 point3) {
        return this.vectorValue().getLine(point3.pointValue());
    }
    
    point3 getPoint3(final point point) {
        if (this.length() == 0.0) {
            return new point3(this.tail);
        }
        final vector position = this.vectorValue().position();
        final double n = new vector(this.tail().pointValue(), point).position().dot(position) / Math.pow(position.length(), 2.0);
        final point3 head = this.position().head();
        final matrix postMult = perspective.finalAdjustment.postMult(perspective.transformation);
        final double at = postMult.getAt(3, 0);
        final double at2 = postMult.getAt(3, 1);
        final double at3 = postMult.getAt(3, 2);
        final double at4 = postMult.getAt(3, 3);
        final double n2 = (1.0 / n - 1.0) / at4 * (head.x() * at + head.y() * at2 + head.z() * at3 + at4) + 1.0;
        return this.tail().plus(new point3(head.x() / n2, head.y() / n2, head.z() / n2));
    }
    
    public point3 head() {
        return this.head;
    }
    
    public point labelPos() {
        return this.vectorValue().labelPos();
    }
    
    public point labelPos2() {
        return this.vectorValue().labelPos2();
    }
    
    public point labelPos3() {
        return this.vectorValue().labelPos3();
    }
    
    public double length() {
        final vector3 position = this.position();
        return Math.sqrt(Math.pow(position.head().x(), 2.0) + Math.pow(position.head().y(), 2.0) + Math.pow(position.head().z(), 2.0));
    }
    
    public vector3 logValue() {
        return new vector3(this.tail(), this.head());
    }
    
    public vector3 minus(final vector3 vector3) {
        return new vector3(this.tail.x() - vector3.tail.x(), this.tail.y() - vector3.tail.y(), this.tail.z() - vector3.tail.z(), this.head.x() - vector3.head.x(), this.head.y() - vector3.head.y(), this.head.z() - vector3.tail.z());
    }
    
    public void normalize() {
        this.head.normalize();
        this.tail.normalize();
    }
    
    public vector3 physValue() {
        return new vector3(this.tail(), this.head());
    }
    
    public vector3 plus(final vector3 vector3) {
        return new vector3(this.tail.x() + vector3.tail.x(), this.tail.y() + vector3.tail.y(), this.tail.z() + vector3.tail.z(), this.head.x() + vector3.head.x(), this.head.y() + vector3.head.y(), this.head.z() + vector3.head.z());
    }
    
    public boolean pointsBackward() {
        return perspective.finalAdjustment.postMult(perspective.transformation.postMult(this.physValue())).logValue().head().z() > 0.0;
    }
    
    public boolean pointsDown() {
        return !this.pointsUp();
    }
    
    public boolean pointsForward() {
        return !this.pointsBackward();
    }
    
    public boolean pointsLeft() {
        return !this.pointsRight();
    }
    
    public boolean pointsRight() {
        return this.vectorValue().pointsRight();
    }
    
    public boolean pointsUp() {
        return this.vectorValue().pointsUp();
    }
    
    public vector3 position() {
        return new vector3(0.0, 0.0, 0.0, this.head.x() - this.tail.x(), this.head.y() - this.tail.y(), this.head.z() - this.tail.z());
    }
    
    public void setValues(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        this.tail.setPt(n, n2, n3);
        this.head.setPt(n4, n5, n6);
    }
    
    public void setValues(final point3 pt, final point3 pt2) {
        this.tail.setPt(pt);
        this.head.setPt(pt2);
    }
    
    public double slope() {
        return this.vectorValue().slope();
    }
    
    public point3 tail() {
        return this.tail;
    }
    
    public vector3 times(final double n) {
        return new vector3(n * this.tail.x(), n * this.tail.y(), n * this.tail.z(), n * this.head.x(), n * this.head.y(), n * this.head.z());
    }
    
    public vector3 value() {
        return new vector3(this.tail.value(), this.head.value());
    }
    
    public vector vectorValue() {
        final point3 point3 = new point3(this.head);
        final point3 point4 = new point3(this.tail);
        final point pointValue = point3.pointValue();
        final point pointValue2 = point4.pointValue();
        return new vector(pointValue2.logX(), pointValue2.logY(), pointValue.logX(), pointValue.logY(), true);
    }
    
    public double yAngle() {
        final vector3 position = this.position();
        position.head().y();
        final double z = position.head().z();
        position.head().x();
        final double n = 180.0 * Math.asin(Math.abs(z) / position.length()) / 3.141592653589793;
        if (z >= 0.0) {
            return n;
        }
        return -n;
    }
    
    public double zAngle() {
        final vector3 position = this.position();
        final double y = position.head().y();
        position.head().z();
        final double x = position.head().x();
        double n;
        if (x == 0.0) {
            if (y > 0.0) {
                n = 90.0;
            }
            else if (y < 0.0) {
                n = 270.0;
            }
            else {
                n = 0.0;
            }
        }
        else {
            n = 180.0 * Math.atan(Math.abs(y) / Math.abs(x)) / 3.141592653589793;
        }
        if (n < 0.0) {
            n += 360.0;
        }
        if (x >= 0.0 && y >= 0.0) {
            return n;
        }
        if (x >= 0.0 && y < 0.0) {
            return 360.0 - n;
        }
        if (x < 0.0 && y >= 0.0) {
            return 180.0 - n;
        }
        return 180.0 + n;
    }
}
