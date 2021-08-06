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

public class VectorCompJDemo extends Thread
{
    protected VectorCompsApplet theApplet;
    protected VectorCompsPanel vPanel;
    protected int id;
    protected float x;
    protected float y;
    public boolean wait;
    
    public VectorCompJDemo() {
        this.theApplet = null;
        this.vPanel = null;
        this.wait = false;
    }
    
    public VectorCompJDemo(final VectorCompsApplet theApplet, final VectorCompsPanel vPanel) {
        this.theApplet = null;
        this.vPanel = null;
        this.wait = false;
        this.theApplet = theApplet;
        this.vPanel = vPanel;
    }
    
    public void dragMouse(final int n, final int n2, final int n3, final int n4) {
        this.x = (float)n;
        this.y = (float)n2;
        final float n5 = (n3 - this.x) / 45.0f;
        final float n6 = (n4 - this.y) / 45.0f;
        this.id = 501;
        this.send();
        this.id = 506;
        while (this.x != n3 || this.y != n4) {
            if ((n3 - this.x) * n5 <= 0.0f) {
                this.x = (float)n3;
            }
            else {
                this.x += n5;
            }
            if ((n4 - this.y) * n6 <= 0.0f) {
                this.y = (float)n4;
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
        this.dragMouse((int)double1.x, (int)double1.y, (int)double2.x, (int)double2.y);
    }
    
    public void dragMouse(final Point point, final Point point2) {
        this.dragMouse(point.x, point.y, point2.x, point2.y);
    }
    
    public Point2D.Double extend(final Point2D.Double double1, final Point2D.Double double2, final double n) {
        final double n2 = double1.x - double2.x;
        final double n3 = double1.y - double2.y;
        final double n4 = n / Math.sqrt(n2 * n2 + n3 * n3);
        return new Point2D.Double(double1.x + (int)(n2 * n4), double1.y + (int)(n3 * n4));
    }
    
    public Point extend(final Point point, final Point point2, final double n) {
        final double n2 = point.x - point2.x;
        final double n3 = point.y - point2.y;
        final double n4 = n / Math.sqrt(n2 * n2 + n3 * n3);
        return new Point(point.x + (int)(n2 * n4), point.y + (int)(n3 * n4));
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
    
    public void send() {
        this.vPanel.pleaseProcessEvent(new MouseEvent(this.vPanel, this.id, new Date().getTime(), 2, (int)this.x, (int)this.y, 1, false));
        this.pause(18L);
    }
    
    public void stopDemo() {
        this.vPanel = null;
        this.theApplet = null;
    }
}
