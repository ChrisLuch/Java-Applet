// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors.laf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class point extends onAxes
{
    Point pt;
    boolean logical;
    
    public point() {
        this.pt = new Point();
    }
    
    public point(final double a, final double a2, final boolean logical) {
        this.pt = new Point();
        this.pt.x = (int)Math.round(a);
        this.pt.y = (int)Math.round(a2);
        this.logical = logical;
    }
    
    public point(final int x, final int y, final boolean logical) {
        this.pt = new Point();
        this.pt.x = x;
        this.pt.y = y;
        this.logical = logical;
    }
    
    public point(final point point) {
        this.pt = new Point();
        this.pt.x = point.logX();
        this.pt.y = point.logY();
        this.logical = true;
    }
    
    public point(final Point point, final boolean logical) {
        this.pt = new Point();
        this.pt.x = point.x;
        this.pt.y = point.y;
        this.logical = logical;
    }
    
    public boolean above(final vector vector) {
        return this.logY() >= vector.tail().logY() + (this.logX() - vector.tail().logX()) * vector.slope();
    }
    
    public boolean below(final vector vector) {
        return !this.above(vector);
    }
    
    public void decX(final int n) {
        final Point pt = this.pt;
        pt.x -= n;
    }
    
    public void decY(final int n) {
        if (this.logical) {
            final Point pt = this.pt;
            pt.y -= n;
        }
        else {
            final Point pt2 = this.pt;
            pt2.y += n;
        }
    }
    
    public void drawPoint(final Graphics graphics, final int n) {
        this.physValue();
        graphics.drawOval(this.pt.x - n / 2, this.pt.y - n / 2, n, n);
        graphics.fillOval(this.pt.x - n / 2, this.pt.y - n / 2, n, n);
    }
    
    public void drawPoint(final Graphics graphics, final int n, final Color color) {
        graphics.setColor(color);
        this.physValue();
        this.drawPoint(graphics, n);
    }
    
    public void incX(final int n) {
        final Point pt = this.pt;
        pt.x += n;
    }
    
    public void incY(final int n) {
        if (this.logical) {
            final Point pt = this.pt;
            pt.y += n;
        }
        else {
            final Point pt2 = this.pt;
            pt2.y -= n;
        }
    }
    
    public point labelPos() {
        return new point(this.physX(), this.physY(), false);
    }
    
    public boolean leftOf(final vector vector) {
        return !this.rightOf(vector);
    }
    
    public boolean logical() {
        return this.logical;
    }
    
    public Point logPt() {
        this.logValue();
        return this.pt;
    }
    
    public void logValue() {
        if (!this.logical) {
            final Point pt = this.pt;
            pt.x -= onAxes.orig.x;
            this.pt.y = onAxes.orig.y - this.pt.y;
            this.logical = true;
        }
    }
    
    public int logX() {
        this.logValue();
        return this.pt.x;
    }
    
    public int logY() {
        this.logValue();
        return this.pt.y;
    }
    
    public point minus(final point point) {
        return new point(this.logX() - point.logX(), this.logY() - point.logY(), true);
    }
    
    public Point physPt() {
        this.physValue();
        return this.pt;
    }
    
    public void physValue() {
        if (this.logical) {
            final Point pt = this.pt;
            pt.x += onAxes.orig.x;
            this.pt.y = onAxes.orig.y - this.pt.y;
            this.logical = false;
        }
    }
    
    public int physX() {
        this.physValue();
        return this.pt.x;
    }
    
    public int physY() {
        this.physValue();
        return this.pt.y;
    }
    
    public point plus(final point point) {
        return new point(this.logX() + point.logX(), this.logY() + point.logY(), true);
    }
    
    public boolean rightOf(final vector vector) {
        return vector.slope() != 0.0 && this.logX() >= vector.tail().logX() + (this.logY() - vector.tail().logY()) / vector.slope();
    }
    
    public void setLogical(final boolean logical) {
        this.logical = logical;
    }
    
    public void setPt(final Point point) {
        this.pt.x = point.x;
        this.pt.y = point.y;
    }
    
    public void setValues(final int x, final int y, final boolean logical) {
        this.pt.x = x;
        this.pt.y = y;
        this.logical = logical;
    }
}
