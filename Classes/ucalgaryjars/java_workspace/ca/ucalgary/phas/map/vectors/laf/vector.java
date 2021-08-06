// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors.laf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

class vector extends onAxes
{
    Point head;
    Point tail;
    boolean logical;
    
    public vector() {
        this.head = new Point();
        this.tail = new Point();
    }
    
    public vector(final double a, final double a2, final double a3, final double a4, final boolean logical) {
        this.head = new Point();
        this.tail = new Point();
        this.head.x = (int)Math.round(a3);
        this.head.y = (int)Math.round(a4);
        this.tail.x = (int)Math.round(a);
        this.tail.y = (int)Math.round(a2);
        this.logical = logical;
    }
    
    public vector(final int x, final int y, final int x2, final int y2, final boolean logical) {
        this.head = new Point();
        this.tail = new Point();
        this.head.x = x2;
        this.head.y = y2;
        this.tail.x = x;
        this.tail.y = y;
        this.logical = logical;
    }
    
    public vector(final int x, final int y, final boolean logical) {
        this.head = new Point();
        this.tail = new Point();
        this.head.x = x;
        this.head.y = y;
        this.logical = logical;
        if (logical) {
            this.tail.x = 0;
            this.tail.y = 0;
        }
        else {
            this.tail.x = onAxes.orig.x;
            this.tail.y = onAxes.orig.y;
        }
    }
    
    public vector(final point point) {
        this.head = new Point();
        this.tail = new Point();
        point.logValue();
        this.head = new Point(point.logX(), point.logY());
        this.tail = new Point(0, 0);
        this.logical = true;
    }
    
    public vector(final point point, final point point2) {
        this.head = new Point();
        this.tail = new Point();
        this.head = new Point(point2.logX(), point2.logY());
        this.tail = new Point(point.logX(), point.logY());
        this.logical = true;
    }
    
    public vector(final vector vector) {
        this.head = new Point();
        this.tail = new Point();
        vector.logValue();
        this.head.x = vector.head.x;
        this.head.y = vector.head.y;
        this.tail.x = vector.tail.x;
        this.tail.y = vector.tail.y;
        this.logical = true;
    }
    
    public vector(final Point point, final Point point2, final boolean logical) {
        this.head = new Point();
        this.tail = new Point();
        this.head.x = point2.x;
        this.head.y = point2.y;
        this.tail.x = point.x;
        this.tail.y = point.y;
        this.logical = logical;
    }
    
    public point closestPoint(final point point) {
        this.logValue();
        final vector position = this.position();
        return this.tail().plus(position.times(new vector(this.tail(), point).position().dot(position) / (position.length() * position.length())).head());
    }
    
    public vector div(final double n) {
        this.logValue();
        return new vector((int)Math.round(this.tail.x / n), (int)Math.round(this.tail.y / n), (int)Math.round(this.head.x / n), (int)Math.round(this.head.y / n), true);
    }
    
    public int dot(final vector vector) {
        this.logValue();
        vector.logValue();
        return (this.head.x - this.tail.x) * (vector.head.x - vector.tail.x) + (this.head.y - this.tail.y) * (vector.head.y - vector.tail.y);
    }
    
    public void drawLine(final Graphics graphics, final Color color) {
        this.physValue();
        graphics.setColor(color);
        graphics.drawLine(this.tail.x, this.tail.y, this.head.x, this.head.y);
    }
    
    public void drawVector(final Graphics graphics, final int n) {
        final VectorUtilities vectorUtilities = new VectorUtilities();
        this.physValue();
        vectorUtilities.drawVector(graphics, n, this.tail.x, this.tail.y, this.head.x, this.head.y, Color.black, Color.white);
    }
    
    public void drawVector(final Graphics graphics, final int n, final Color color, final Color color2) {
        final VectorUtilities vectorUtilities = new VectorUtilities();
        this.physValue();
        vectorUtilities.drawVector(graphics, n, this.tail.x, this.tail.y, this.head.x, this.head.y, color, color2);
    }
    
    public Point getHead() {
        this.logValue();
        return this.head;
    }
    
    public vector getLine(final point point) {
        final point[] array = new point[2];
        int n = 0;
        final point head = this.position().head();
        final double n2 = -onAxes.orig.x;
        final double n3 = point.logY() + head.logY() * ((n2 - point.logX()) / head.logX());
        if (-onAxes.orig.y <= n3 && n3 <= onAxes.orig.y) {
            array[n] = new point(n2, n3, true);
            ++n;
        }
        final double n4 = onAxes.orig.x;
        final double n5 = point.logY() + head.logY() * ((n4 - point.logX()) / head.logX());
        if (-onAxes.orig.y <= n5 && n5 <= onAxes.orig.y) {
            array[n] = new point(n4, n5, true);
            ++n;
        }
        final double n6 = -onAxes.orig.y;
        final double n7 = point.logX() + head.logX() * ((n6 - point.logY()) / head.logY());
        if (-onAxes.orig.x <= n7 && n7 <= onAxes.orig.x) {
            array[n] = new point(n7, n6, true);
            ++n;
        }
        final double n8 = onAxes.orig.y;
        final double n9 = point.logX() + head.logX() * ((n8 - point.logY()) / head.logY());
        if (-onAxes.orig.x <= n9 && n9 <= onAxes.orig.x) {
            array[n] = new point(n9, n8, true);
            ++n;
        }
        return new vector(array[0], array[1]);
    }
    
    public Point getTail() {
        this.logValue();
        return this.tail;
    }
    
    public point head() {
        this.logValue();
        return new point(this.head, true);
    }
    
    public point labelPos() {
        this.physValue();
        return new point(this.tail.x + 3 * (this.head.x - this.tail.x) / 4, this.tail.y + 3 * (this.head.y - this.tail.y) / 4, false);
    }
    
    public point labelPos2() {
        this.physValue();
        return new point((this.tail.x + this.head.x) / 2, (this.tail.y + this.head.y) / 2, false);
    }
    
    public point labelPos3() {
        this.physValue();
        return new point(this.head.x, this.head.y, false);
    }
    
    public double length() {
        return Math.sqrt(Math.pow(this.head.y - this.tail.y, 2.0) + Math.pow(this.head.x - this.tail.x, 2.0));
    }
    
    public boolean logical() {
        return this.logical;
    }
    
    public void logValue() {
        if (!this.logical) {
            final Point head = this.head;
            head.x -= onAxes.orig.x;
            this.head.y = onAxes.orig.y - this.head.y;
            final Point tail = this.tail;
            tail.x -= onAxes.orig.x;
            this.tail.y = onAxes.orig.y - this.tail.y;
        }
        this.logical = true;
    }
    
    public vector minus(final vector vector) {
        this.logValue();
        vector.logValue();
        return new vector(this.tail.x - vector.tail.x, this.tail.y - vector.tail.y, this.head.x - vector.head.x, this.head.y - vector.head.y, true);
    }
    
    public void physValue() {
        if (this.logical) {
            final Point head = this.head;
            head.x += onAxes.orig.x;
            this.head.y = onAxes.orig.y - this.head.y;
            final Point tail = this.tail;
            tail.x += onAxes.orig.x;
            this.tail.y = onAxes.orig.y - this.tail.y;
        }
        this.logical = false;
    }
    
    public vector plus(final vector vector) {
        this.logValue();
        vector.logValue();
        return new vector(this.tail.x + vector.tail.x, this.tail.y + vector.tail.y, this.head.x + vector.head.x, this.head.y + vector.head.y, true);
    }
    
    public boolean pointsDown() {
        return !this.pointsUp();
    }
    
    public boolean pointsLeft() {
        return !this.pointsRight();
    }
    
    public boolean pointsRight() {
        this.logValue();
        return this.head.x >= this.tail.x;
    }
    
    public boolean pointsUp() {
        this.logValue();
        return this.head.y >= this.tail.y;
    }
    
    public vector position() {
        this.logValue();
        return new vector(0, 0, this.head.x - this.tail.x, this.head.y - this.tail.y, true);
    }
    
    public void setValues(final int x, final int y, final int x2, final int y2, final boolean logical) {
        this.tail.x = x;
        this.tail.y = y;
        this.head.x = x2;
        this.head.y = y2;
        this.logical = logical;
    }
    
    public void setValues(final point point, final point point2) {
        this.head.x = point2.logX();
        this.head.y = point2.logY();
        this.tail.x = point.logX();
        this.tail.y = point.logY();
        this.logical = true;
    }
    
    public void setValues(final Point point, final Point point2, final boolean logical) {
        this.tail.x = point.x;
        this.tail.y = point.y;
        this.head.x = point2.x;
        this.head.y = point2.y;
        this.logical = logical;
    }
    
    public double slope() {
        if (this.head.x - this.tail.x == 0) {
            return 9.99999999E8;
        }
        return (this.head.y - this.tail.y) / (double)(this.head.x - this.tail.x);
    }
    
    public point tail() {
        this.logValue();
        return new point(this.tail, true);
    }
    
    public vector times(final double n) {
        this.logValue();
        return new vector((int)Math.round(n * this.tail.x), (int)Math.round(n * this.tail.y), (int)Math.round(n * this.head.x), (int)Math.round(n * this.head.y), true);
    }
}
