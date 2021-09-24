// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors.laf;

import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;

public class plane extends onAxes
{
    static final vector3 normal;
    point3 pt;
    double size;
    point3[] startPlane;
    static matrix rotate;
    static matrix shift;
    
    public plane() {
        this.startPlane = new point3[4];
        this.size = 1.0;
        this.setRotate(0.0, 0.0);
        this.setShift(0.0, 0.0, 0.0);
        this.pt = new point3(0.0, 0.0, 0.0);
        this.setStartPlane();
    }
    
    public plane(final double size) {
        this.startPlane = new point3[4];
        this.size = size;
        this.setRotate(0.0, 0.0);
        this.setShift(0.0, 0.0, 0.0);
        this.pt = new point3(0.0, 0.0, 0.0);
        this.setStartPlane();
    }
    
    public plane(final plane plane) {
        this.startPlane = new point3[4];
        this.size = plane.size;
        this.setStartPlane();
    }
    
    public plane(final vector3 vector3, final point3 point3) {
        this.startPlane = new point3[4];
        this.size = 1.0;
        this.pt = new point3(point3);
        this.setStartPlane();
        this.setRotate(vector3.yAngle(), vector3.zAngle());
        this.setShift(point3.x(), point3.y(), point3.z());
    }
    
    public plane(final vector3 vector3, final point3 point3, final double size) {
        this.startPlane = new point3[4];
        this.size = size;
        this.pt = new point3(point3);
        this.setStartPlane();
        this.setRotate(vector3.yAngle(), vector3.zAngle());
        this.setShift(point3.x(), point3.y(), point3.z());
    }
    
    public void drawPlane(final Graphics graphics, final vector3 vector3, final vector3 vector4, final Color color) {
        final Polygon polygon = this.getPolygon(vector3, vector4);
        graphics.setColor(color);
        graphics.drawPolygon(polygon);
        graphics.fillPolygon(polygon);
    }
    
    public void drawPlane(final Graphics graphics, final vector3 vector3, final Color color) {
        final Polygon polygon = this.getPolygon(vector3);
        graphics.setColor(color);
        graphics.drawPolygon(polygon);
        graphics.fillPolygon(polygon);
    }
    
    public void drawPlane(final Graphics graphics, final Color color) {
        final Polygon polygon = this.getPolygon();
        graphics.setColor(color);
        graphics.drawPolygon(polygon);
        graphics.setXORMode(CP.XORCOLOR);
        graphics.fillPolygon(polygon);
        graphics.setPaintMode();
    }
    
    public boolean facesForward() {
        return this.normal().pointsForward();
    }
    
    public point3 getPoint3(final point point) {
        final point3 head = this.normal().head();
        final double n = point.logX();
        final double n2 = point.logY();
        final matrix inverse = perspective.finalAdjustment.postMult(perspective.transformation).inverse();
        final double at = inverse.getAt(0, 0);
        final double at2 = inverse.getAt(0, 1);
        final double at3 = inverse.getAt(0, 2);
        final double at4 = inverse.getAt(1, 0);
        final double at5 = inverse.getAt(1, 1);
        final double at6 = inverse.getAt(1, 2);
        final double at7 = inverse.getAt(2, 0);
        final double at8 = inverse.getAt(2, 1);
        final double at9 = inverse.getAt(2, 2);
        final double at10 = inverse.getAt(3, 3);
        final double n3 = (-(at * n + at2 * n2) * head.x() - (at4 * n + at5 * n2) * head.y() - (at7 * n + at8 * n2) * head.z()) / (at3 * head.x() + at6 * head.y() + at9 * head.z());
        return new point3((at * n + at2 * n2 + at3 * n3) / at10, (at4 * n + at5 * n2 + at6 * n3) / at10, (at7 * n + at8 * n2 + at9 * n3) / at10).logValue();
    }
    
    public Polygon getPolygon() {
        final Polygon polygon = new Polygon();
        for (int i = 0; i < 4; ++i) {
            final point3 postMult = plane.shift.postMult(plane.rotate.postMult(this.startPlane[i]));
            polygon.addPoint(postMult.pointValue().physPt().x, postMult.pointValue().physPt().y);
        }
        return polygon;
    }
    
    public Polygon getPolygon(final vector3 vector3) {
        Polygon polygon;
        for (polygon = this.getPolygon(); polygon.contains(vector3.head().pointValue().physPt()); polygon = this.getPolygon()) {
            this.setSize(0.8 * this.size);
        }
        return polygon;
    }
    
    public Polygon getPolygon(final vector3 vector3, final vector3 vector4) {
        Polygon polygon;
        for (polygon = this.getPolygon(); !polygon.contains(vector3.head().pointValue().physPt()) || !polygon.contains(vector4.head().pointValue().physPt()); polygon = this.getPolygon()) {
            this.setSize(1.1 * this.size);
        }
        return polygon;
    }
    
    public vector3 normal() {
        final point3[] thePlane = this.thePlane();
        return new vector3(thePlane[0]).position().cross(new vector3(thePlane[1]).position());
    }
    
    public boolean overX() {
        final vector3 normal = this.normal();
        if (normal.pointsForward() && normal.head().x() < 0.0 && normal.pointsUp() && normal.pointsRight()) {
            return true;
        }
        System.out.println("points Forward " + normal.pointsForward());
        System.out.println("points Backwardish " + (normal.head().x() < 0.0));
        System.out.println("points up " + normal.pointsUp());
        System.out.println("points right " + normal.pointsRight());
        return normal.pointsBackward() && normal.head().x() > 0.0 && normal.pointsDown() && normal.pointsLeft();
    }
    
    public boolean overY() {
        final vector3 normal = this.normal();
        return (normal.pointsForward() && normal.pointsLeft()) || (normal.pointsBackward() && normal.pointsRight());
    }
    
    public boolean overZ() {
        final vector3 normal = this.normal();
        return (normal.pointsForward() && normal.head().z() < 0.0) || (normal.pointsBackward() && normal.head().z() > 0.0);
    }
    
    public void redrawAxes(final Graphics graphics) {
        final point3 point3 = new point3(0.0, 0.0, 0.0);
        final point3 xAxis = onAxes.xAxis();
        final double xInt = this.xInt();
        final point3 point4 = new point3(xInt, 0.0, 0.0);
        if (0.0 <= xInt && xInt <= xAxis.x()) {
            if (this.overX()) {
                new vector3(point3, point4).drawLine(graphics, colors.axisBlack());
            }
            else {
                new vector3(point4, xAxis).drawVector(graphics, 1, colors.axisBlack(), colors.backGround());
            }
        }
        final point3 yAxis = onAxes.yAxis();
        final double yInt = this.yInt();
        final point3 point5 = new point3(0.0, yInt, 0.0);
        if (0.0 <= yInt && yInt <= yAxis.y()) {
            if (this.overY()) {
                new vector3(point3, point5).drawLine(graphics, colors.axisBlack());
            }
            else {
                new vector3(point5, yAxis).drawVector(graphics, 1, colors.axisBlack(), colors.backGround());
            }
        }
        else {
            if (yInt < 0.0 && !this.overY()) {
                new vector3(point3, yAxis).drawVector(graphics, 1, colors.axisBlack(), colors.backGround());
            }
            if (yInt > yAxis.y() && this.overY()) {
                new vector3(point3, yAxis).drawVector(graphics, 1, colors.axisBlack(), colors.backGround());
            }
        }
        final point3 zAxis = onAxes.zAxis();
        final double zInt = this.zInt();
        final point3 point6 = new point3(0.0, 0.0, zInt);
        if (0.0 <= zInt && zInt <= zAxis.z()) {
            if (this.overZ()) {
                new vector3(point3, point6).drawLine(graphics, colors.axisBlack());
            }
            else {
                new vector3(point6, zAxis).drawVector(graphics, 1, colors.axisBlack(), colors.backGround());
            }
        }
        else {
            if (zInt < 0.0 && !this.overZ()) {
                new vector3(point3, zAxis).drawVector(graphics, 1, colors.axisBlack(), colors.backGround());
            }
            if (zInt > zAxis.z() && this.overZ()) {
                new vector3(point3, zAxis).drawVector(graphics, 1, colors.axisBlack(), colors.backGround());
            }
        }
    }
    
    public void setRotate(final double n, final double n2) {
        (plane.rotate = new matrix()).rotZ(n2);
        plane.rotate.rotY(n);
    }
    
    public void setShift(final double n, final double n2, final double n3) {
        (plane.shift = new matrix()).setAt(0, 3, n);
        plane.shift.setAt(1, 3, n2);
        plane.shift.setAt(2, 3, n3);
    }
    
    public void setSize(final double size) {
        this.size = size;
        this.setStartPlane();
    }
    
    public void setStartPlane() {
        this.startPlane[0] = new point3(0.0, -this.size, -this.size);
        this.startPlane[1] = new point3(0.0, -this.size, this.size);
        this.startPlane[2] = new point3(0.0, this.size, this.size);
        this.startPlane[3] = new point3(0.0, this.size, -this.size);
    }
    
    public Polygon startPolygon() {
        final Polygon polygon = new Polygon();
        for (int i = 0; i < 4; ++i) {
            polygon.addPoint(this.startPlane[i].pointValue().physPt().x, this.startPlane[i].pointValue().physPt().y);
        }
        return polygon;
    }
    
    public point3[] thePlane() {
        return plane.shift.postMult(plane.rotate.postMult(this.startPlane, 4), 4);
    }
    
    public double xInt() {
        final point3 head = this.normal().head();
        return (head.x() * this.pt.x() + head.y() * this.pt.y() + head.z() * this.pt.z()) / head.x();
    }
    
    public double yInt() {
        final point3 head = this.normal().head();
        return (head.x() * this.pt.x() + head.y() * this.pt.y() + head.z() * this.pt.z()) / head.y();
    }
    
    public double zInt() {
        final point3 head = this.normal().head();
        return (head.x() * this.pt.x() + head.y() * this.pt.y() + head.z() * this.pt.z()) / head.z();
    }
    
    static {
        normal = new vector3(1.0, 0.0, 0.0);
    }
}
