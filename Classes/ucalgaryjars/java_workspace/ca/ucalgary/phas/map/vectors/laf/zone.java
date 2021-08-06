// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors.laf;

import java.awt.Color;
import java.awt.Graphics;

public class zone
{
    point p;
    int above;
    int below;
    int left;
    int right;
    
    public zone() {
    }
    
    public zone(final point point) {
        this.p = new point(point);
        this.above = 1;
        this.below = 1;
        this.left = 1;
        this.right = 1;
    }
    
    public zone(final point point, final int above, final int below, final int left, final int right) {
        this.p = new point(point);
        this.above = above;
        this.below = below;
        this.left = left;
        this.right = right;
    }
    
    public int above() {
        return this.above;
    }
    
    public int below() {
        return this.below;
    }
    
    public boolean contacts(final vector vector) {
        final point point = new point(this.p.logX() - this.left, this.p.logY() - this.below, true);
        final point point2 = new point(this.p.logX() - this.left, this.p.logY() + this.above, true);
        final point point3 = new point(this.p.logX() + this.right, this.p.logY() - this.below, true);
        final point point4 = new point(this.p.logX() + this.right, this.p.logY() + this.above, true);
        return (point2.leftOf(vector) && point4.rightOf(vector) && this.inInt(vector.getHead().y, vector.tail().logY(), point2.logY())) || (point.leftOf(vector) && point3.rightOf(vector) && this.inInt(vector.getHead().y, vector.tail().logY(), point.logY())) || (point2.above(vector) && point.below(vector) && this.inInt(vector.getHead().x, vector.tail().logX(), point.logX())) || (point4.above(vector) && point3.below(vector) && this.inInt(vector.getHead().x, vector.tail().logX(), point3.logX()));
    }
    
    public boolean contacts(final vector3 vector3) {
        return this.contacts(vector3.vectorValue());
    }
    
    public boolean contains(final point point) {
        return point.logX() <= this.p.logX() + this.right && point.logX() >= this.p.logX() - this.left && point.logY() <= this.p.logY() + this.above && point.logY() >= this.p.logY() - this.below;
    }
    
    public boolean contains(final point3 point3) {
        return this.contains(point3.pointValue());
    }
    
    public boolean contains(final vector vector) {
        return this.contains(vector.head()) && this.contains(vector.tail());
    }
    
    public boolean contains(final zone zone) {
        final point point = new point(zone.p());
        final point point2 = new point(zone.p());
        final point point3 = new point(zone.p());
        final point point4 = new point(zone.p());
        point.incX(zone.right());
        point.incY(zone.above());
        point2.decX(zone.left());
        point2.incY(zone.above());
        point3.decX(zone.left());
        point3.decY(zone.below());
        point4.incX(zone.right());
        point4.decY(zone.below());
        return this.contains(point) && this.contains(point2) && this.contains(point3) && this.contains(point4);
    }
    
    public void drawNeighborhood(final Graphics graphics, final Color color) {
        graphics.setColor(color);
        graphics.drawLine(this.p.physX() - this.left, this.p.physY() + this.below, this.p.physX() + this.right, this.p.physY() + this.below);
        graphics.drawLine(this.p.physX() - this.left, this.p.physY() + this.below, this.p.physX() - this.left, this.p.physY() - this.above);
        graphics.drawLine(this.p.physX() - this.left, this.p.physY() - this.above, this.p.physX() + this.right, this.p.physY() - this.above);
        graphics.drawLine(this.p.physX() + this.right, this.p.physY() + this.below, this.p.physX() + this.right, this.p.physY() - this.above);
    }
    
    public boolean inInt(final int n, final int n2, final int n3) {
        return (n <= n3 && n3 <= n2) || (n2 <= n3 && n3 <= n);
    }
    
    public int left() {
        return this.left;
    }
    
    public point p() {
        return this.p;
    }
    
    public int right() {
        return this.right;
    }
    
    public void setPoint(final point point) {
        this.p = new point(point);
    }
    
    public void setValues(final point point, final int above, final int below, final int left, final int right) {
        this.p = new point(point);
        this.above = above;
        this.below = below;
        this.left = left;
        this.right = right;
    }
}
