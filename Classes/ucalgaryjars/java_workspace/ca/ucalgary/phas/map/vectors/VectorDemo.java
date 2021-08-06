// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.awt.Point;
import java.awt.geom.Point2D;

public abstract class VectorDemo extends Thread
{
    protected VectorPanel vPanel;
    protected int id;
    protected double x;
    protected double y;
    public boolean wait;
    
    public VectorDemo() {
        this.vPanel = null;
        this.wait = false;
    }
    
    public VectorDemo(final VectorPanel vPanel) {
        this.vPanel = null;
        this.wait = false;
        this.vPanel = vPanel;
    }
    
    public void dragMouse(final int n, final int n2, final int n3, final int n4) {
        this.x = n;
        this.y = n2;
        final double n5 = (n3 - this.x) / 45.0;
        final double n6 = (n4 - this.y) / 45.0;
        this.id = 501;
        this.send();
        this.id = 506;
        while (this.x != n3 || this.y != n4) {
            if ((n3 - this.x) * n5 <= 0.0) {
                this.x = n3;
            }
            else {
                this.x += n5;
            }
            if ((n4 - this.y) * n6 <= 0.0) {
                this.y = n4;
            }
            else {
                this.y += n6;
            }
            this.send();
        }
        this.id = 502;
        this.send();
    }
    
    public void dragMouse(final Point2D.Double double1, final Point2D.Double double2) {
        this.dragMouse((int)(double1.x + 0.5), (int)(double1.y + 0.5), (int)(double2.x + 0.5), (int)(double2.y + 0.5));
    }
    
    public void dragMouse(final Point point, final Point point2) {
        this.dragMouse(point.x, point.y, point2.x, point2.y);
    }
    
    public Point extend(final Point point, final Point point2, final double n) {
        final double n2 = point.x - point2.x;
        final double n3 = point.y - point2.y;
        final double n4 = n / Math.sqrt(n2 * n2 + n3 * n3);
        return new Point(point.x + (int)(n2 * n4), point.y + (int)(n3 * n4));
    }
    
    public Point2D.Double extend2D(final Point2D.Double double1, final Point2D.Double double2, final double n) {
        final double n2 = double1.x - double2.x;
        final double n3 = double1.y - double2.y;
        final double n4 = n / Math.sqrt(n2 * n2 + n3 * n3);
        return new Point2D.Double(double1.x + n2 * n4, double1.y + n3 * n4);
    }
    
    public void myResume() {
        this.wait = false;
    }
    
    public void mySuspend() {
        this.wait = true;
        while (this.wait) {
            this.pause(10L);
        }
    }
    
    public void pause(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            System.out.println("Demo Interupted While Sleeping");
        }
    }
    
    public abstract void run();
    
    public void send() {
        try {
            this.vPanel.pleaseProcessEvent(new MouseEvent(this.vPanel, this.id, new Date().getTime(), 2, (int)this.x, (int)this.y, 1, false));
        }
        catch (IllegalArgumentException ex) {
            System.out.println("Demo Interupted");
        }
        this.pause(18L);
    }
    
    public void stopDemo() {
        this.vPanel = null;
    }
    
    public void translate(final Point point, final Point point2, final Point point3) {
        point.translate(point3.x - point2.x, point3.y - point2.y);
    }
    
    public void translate(final Point2D.Double double1, final Point2D.Double double2, final Point2D.Double double3) {
        double1.x += double3.x - double2.x;
        double1.y += double3.y - double2.y;
    }
}
