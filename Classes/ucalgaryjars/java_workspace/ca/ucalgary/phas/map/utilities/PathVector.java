// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class PathVector
{
    private Point2D.Double P1;
    private Point2D.Double P2;
    private Line2D.Double vector;
    private Point2D.Double velComponents;
    private Point2D.Double accComponents;
    private double velocity;
    private double acceleration;
    
    public PathVector(final Line2D.Double double1, final Point2D.Double double2, final double velocity) {
        this.vector = new Line2D.Double(double1.getP1(), double1.getP2());
        this.P1 = (Point2D.Double)this.vector.getP1();
        this.P2 = (Point2D.Double)this.vector.getP2();
        this.velComponents = new Point2D.Double(double2.getX(), double2.getY());
        this.velocity = velocity;
        this.acceleration = 0.0;
    }
    
    public PathVector(final Point2D.Double double1, final Point2D.Double double2, final Point2D.Double double3, final double velocity) {
        this.P1 = new Point2D.Double(double1.getX(), double1.getY());
        this.P2 = new Point2D.Double(double2.getX(), double2.getY());
        this.vector = new Line2D.Double(this.P1, this.P2);
        this.velComponents = new Point2D.Double(double3.getX(), double3.getY());
        this.velocity = velocity;
        this.acceleration = 0.0;
    }
    
    public PathVector(final Point2D.Double double1, final Point2D.Double double2, final Point2D.Double double3, final Point2D.Double double4) {
        this.P1 = new Point2D.Double(double1.getX(), double1.getY());
        this.P2 = new Point2D.Double(double2.getX(), double2.getY());
        this.vector = new Line2D.Double(this.P1, this.P2);
        this.velComponents = new Point2D.Double(double3.getX(), double3.getY());
        this.accComponents = new Point2D.Double(double4.getX(), double4.getY());
        this.velocity = 0.0;
        this.acceleration = 0.0;
    }
    
    public PathVector(final Point2D.Double double1, final Point2D.Double double2, final double x, final double y, final Point2D.Double double3) {
        this.P1 = new Point2D.Double(double1.getX(), double1.getY());
        this.P2 = new Point2D.Double(double2.getX(), double2.getY());
        this.vector = new Line2D.Double(this.P1, this.P2);
        this.velComponents = new Point2D.Double(x, y);
        this.accComponents = new Point2D.Double(double3.getX(), double3.getY());
        this.velocity = 0.0;
        this.acceleration = 0.0;
    }
    
    public PathVector(final Point2D.Double double1, final Point2D.Double double2, final double velocity) {
        this.P1 = new Point2D.Double(double1.getX(), double1.getY());
        this.P2 = new Point2D.Double(double2.getX(), double2.getY());
        this.vector = new Line2D.Double(this.P1, this.P2);
        this.velocity = velocity;
        this.acceleration = 0.0;
    }
    
    public PathVector(final double x, final double y, final double x2, final double y2, final Point2D.Double double1, final double n) {
        this.P1 = new Point2D.Double(x, y);
        this.P2 = new Point2D.Double(x2, y2);
        this.vector = new Line2D.Double(this.P1, this.P2);
        this.velComponents = new Point2D.Double(double1.getX(), double1.getY());
        this.velocity = 0.0;
        this.acceleration = 0.0;
    }
    
    public PathVector(final double x, final double y, final double x2, final double y2, final double x3, final double y3, final double n) {
        this.P1 = new Point2D.Double(x, y);
        this.P2 = new Point2D.Double(x2, y2);
        this.vector = new Line2D.Double(this.P1, this.P2);
        this.velComponents = new Point2D.Double(x3, y3);
        this.velocity = 0.0;
        this.acceleration = 0.0;
    }
    
    public PathVector(final double x, final double y, final double x2, final double y2, final Point2D.Double double1, final Point2D.Double double2) {
        this.P1 = new Point2D.Double(x, y);
        this.P2 = new Point2D.Double(x2, y2);
        this.vector = new Line2D.Double(this.P1, this.P2);
        this.velComponents = new Point2D.Double(double1.getX(), double1.getY());
        this.accComponents = new Point2D.Double(double2.getX(), double2.getY());
        this.velocity = 0.0;
        this.acceleration = 0.0;
    }
    
    public PathVector(final double x, final double y, final double x2, final double y2, final double velocity) {
        this.P1 = new Point2D.Double(x, y);
        this.P2 = new Point2D.Double(x2, y2);
        this.vector = new Line2D.Double(this.P1, this.P2);
        this.velocity = velocity;
        this.acceleration = 0.0;
    }
    
    public PathVector(final Line2D.Double double1, final double velocity, final double acceleration) {
        this.vector = new Line2D.Double(double1.getP1(), double1.getP2());
        this.P1 = (Point2D.Double)this.vector.getP1();
        this.P2 = (Point2D.Double)this.vector.getP2();
        this.velocity = velocity;
        this.acceleration = acceleration;
    }
    
    public PathVector(final Point2D.Double double1, final Point2D.Double double2, final double velocity, final double acceleration) {
        this.P1 = new Point2D.Double(double1.getX(), double1.getY());
        this.P2 = new Point2D.Double(double2.getX(), double2.getY());
        this.vector = new Line2D.Double(this.P1, this.P2);
        this.velocity = velocity;
        this.acceleration = acceleration;
    }
    
    public PathVector(final double x, final double y, final double x2, final double y2, final double velocity, final double acceleration) {
        this.P1 = new Point2D.Double(x, y);
        this.P2 = new Point2D.Double(x2, y2);
        this.vector = new Line2D.Double(this.P1, this.P2);
        this.velocity = velocity;
        this.acceleration = acceleration;
    }
    
    public final Line2D.Double getVector() {
        return this.vector;
    }
    
    public final Point2D.Double getP1() {
        return this.P1;
    }
    
    public final Point2D.Double getP2() {
        return this.P2;
    }
    
    public final Point2D.Double getVelComponents() {
        return this.velComponents;
    }
    
    public final Point2D.Double getAccComponents() {
        return this.accComponents;
    }
    
    public final double getVelocity() {
        return this.velocity;
    }
    
    public final double getAccel() {
        return this.acceleration;
    }
}
