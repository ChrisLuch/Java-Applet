// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors.laf;

import java.awt.Color;
import java.awt.Graphics;

public class point3 extends onAxes
{
    double[] pt;
    
    public point3() {
        this.pt = new double[] { 0.0, 0.0, 0.0, 1.0 };
    }
    
    public point3(final double n, final double n2, final double n3) {
        (this.pt = new double[] { 0.0, 0.0, 0.0, 1.0 })[0] = n;
        this.pt[1] = n2;
        this.pt[2] = n3;
        this.pt[3] = 1.0;
    }
    
    public point3(final point3 point3) {
        (this.pt = new double[] { 0.0, 0.0, 0.0, 1.0 })[0] = point3.x();
        this.pt[1] = point3.y();
        this.pt[2] = point3.z();
        this.pt[3] = point3.w();
    }
    
    public void decX(final int n) {
        final double[] pt = this.pt;
        final int n2 = 0;
        pt[n2] -= n;
    }
    
    public void decY(final int n) {
        final double[] pt = this.pt;
        final int n2 = 1;
        pt[n2] -= n;
    }
    
    public void decZ(final int n) {
        final double[] pt = this.pt;
        final int n2 = 2;
        pt[n2] -= n;
    }
    
    public void displayPoint(final String x) {
        System.out.println(x);
        System.out.println(this.pt[0]);
        System.out.println(this.pt[1]);
        System.out.println(this.pt[2]);
        System.out.println();
    }
    
    public point3 drag(final point point) {
        final point pointValue = this.pointValue();
        final double n = 2.0;
        final double slope = new vector(pointValue, point).slope();
        if ((slope > -1.0 / n && slope < 1.0 / n) || (slope > -1.0 && slope < -1.0 / n)) {
            return new vector3(this.x(), this.y() - 1.0, this.z(), this.x(), this.y(), this.z()).getPoint3(point);
        }
        if (slope > n || slope < -n || (slope > -n && slope < -1.0)) {
            return new vector3(this.x(), this.y(), this.z() - 1.0, this.x(), this.y(), this.z()).getPoint3(point);
        }
        return new vector3(this.x() - 1.0, this.y(), this.z(), this.x(), this.y(), this.z()).getPoint3(point);
    }
    
    public void drawCube(final Graphics graphics, final Color color) {
        final point3 point3 = new point3(this.x(), this.y(), 0.0);
        final point3 point4 = new point3(this.x(), 0.0, this.z());
        final point3 point5 = new point3(0.0, this.y(), this.z());
        final point3 point6 = new point3(0.0, 0.0, this.z());
        final point3 point7 = new point3(0.0, this.y(), 0.0);
        final point3 point8 = new point3(this.x(), 0.0, 0.0);
        final point3 point9 = new point3(0.0, 0.0, 0.0);
        new vector3(point3, this).drawLine(graphics, color);
        new vector3(point4, this).drawLine(graphics, color);
        new vector3(point5, this).drawLine(graphics, color);
        new vector3(point3, point7).drawLine(graphics, color);
        new vector3(point3, point8).drawLine(graphics, color);
        new vector3(point4, point6).drawLine(graphics, color);
        new vector3(point4, point8).drawLine(graphics, color);
        new vector3(point5, point6).drawLine(graphics, color);
        new vector3(point5, point7).drawLine(graphics, color);
        new vector3(point6, point9).drawLine(graphics, color);
        new vector3(point7, point9).drawLine(graphics, color);
        new vector3(point8, point9).drawLine(graphics, color);
    }
    
    public void drawPoint(final Graphics graphics, final int n) {
        this.pointValue().drawPoint(graphics, n);
    }
    
    public void drawPoint(final Graphics graphics, final int n, final Color color) {
        this.pointValue().drawPoint(graphics, n, color);
    }
    
    public void incX(final int n) {
        final double[] pt = this.pt;
        final int n2 = 0;
        pt[n2] += n;
    }
    
    public void incY(final int n) {
        final double[] pt = this.pt;
        final int n2 = 1;
        pt[n2] += n;
    }
    
    public void incZ(final int n) {
        final double[] pt = this.pt;
        final int n2 = 2;
        pt[n2] += n;
    }
    
    public point labelPos() {
        return this.pointValue().labelPos();
    }
    
    public point3 logValue() {
        if (onAxes.adjust) {
            return new point3(3.0 * this.pt[0] / 13.0, 32.0 * this.pt[1] / 10.0, 32.0 * this.pt[2] / 10.0);
        }
        return new point3(this);
    }
    
    public void normalize() {
        final double[] pt = this.pt;
        final int n = 0;
        pt[n] /= this.pt[3];
        final double[] pt2 = this.pt;
        final int n2 = 1;
        pt2[n2] /= this.pt[3];
        final double[] pt3 = this.pt;
        final int n3 = 2;
        pt3[n3] /= this.pt[3];
        this.pt[3] = 1.0;
    }
    
    public point3 physValue() {
        if (onAxes.adjust) {
            return new point3(13.0 * this.pt[0] / 3.0, 10.0 * this.pt[1] / 32.0, 10.0 * this.pt[2] / 32.0);
        }
        return new point3(this);
    }
    
    public point3 plus(final point3 point3) {
        return new point3(this.x() + point3.x(), this.y() + point3.y(), this.z() + point3.z());
    }
    
    public point pointValue() {
        final point3 postMult = perspective.finalAdjustment.postMult(perspective.transformation.postMult(this.physValue()));
        return new point(postMult.x(), postMult.y(), true);
    }
    
    public void setPt(final double n, final double n2, final double n3) {
        this.pt[0] = n;
        this.pt[1] = n2;
        this.pt[2] = n3;
        this.pt[3] = 1.0;
    }
    
    public void setPt(final point3 point3) {
        this.pt[0] = point3.x();
        this.pt[1] = point3.y();
        this.pt[2] = point3.z();
        this.pt[3] = point3.w();
    }
    
    public void setW(final double n) {
        this.pt[3] = n;
    }
    
    public void setX(final double n) {
        this.pt[0] = n;
    }
    
    public void setY(final double n) {
        this.pt[1] = n;
    }
    
    public void setZ(final double n) {
        this.pt[2] = n;
    }
    
    public point3 value() {
        return new point3((int)(10.0 * this.x() / 15.0) / 10.0, (int)(10.0 * this.y()) / 10.0, (int)(10.0 * this.z()) / 10.0);
    }
    
    public String valueOf(final String str) {
        final point3 value = this.value();
        return str + " = (" + (int)(10.0 * value.x()) / 10.0 + ", " + (int)(10.0 * value.y()) / 10.0 + ", " + (int)(10.0 * value.z()) / 10.0 + ")";
    }
    
    public double w() {
        return this.pt[3];
    }
    
    public double x() {
        return this.pt[0];
    }
    
    public double y() {
        return this.pt[1];
    }
    
    public double z() {
        return this.pt[2];
    }
}
