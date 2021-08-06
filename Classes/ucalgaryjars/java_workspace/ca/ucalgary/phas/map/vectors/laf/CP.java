// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors.laf;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Polygon;
import ca.ucalgary.phas.map.utilities.vectors.VectorString;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class CP extends Applet implements MouseListener, MouseMotionListener
{
    static final Color EDGECOLOR;
    static final Color VCOLOR;
    static final Color WCOLOR;
    static final Color CROSSCOLOR;
    static final Color LABELCOLOR;
    static final Color XORCOLOR;
    public static Font labelFont;
    static int height;
    static int width;
    Image buffer;
    point CrP;
    point VO;
    point VT;
    Color vColor;
    Color wColor;
    Color cpColor;
    Color planeColor;
    Color vColor2;
    Color wColor2;
    Color cpColor2;
    Color planeColor2;
    Color bgColor;
    Color frameColor;
    static int origX;
    static int origY;
    static final int adjusted = 60;
    int oldX;
    int oldY;
    plane pl;
    static final double planeSize = 1.0;
    static final double vecLength = 0.75;
    boolean dragOne;
    boolean dragTwo;
    vector3 v;
    vector3 w;
    vector3 crossProduct;
    matrix transformation;
    matrix theInverse;
    double camera;
    double backPlaneZ;
    double frontPlaneZ;
    VectorString aLabel;
    VectorString bLabel;
    VectorString cpLabel;
    Polygon areaPoly;
    
    public CP() {
        this.vColor = new Color(16744618).darker();
        this.wColor = new Color(3178751).darker();
        this.cpColor = new Color(16736288);
        this.planeColor = new Color(16777164);
        this.vColor2 = new Color(14508168).darker();
        this.wColor2 = new Color(1073373).darker();
        this.cpColor2 = new Color(14508064);
        this.planeColor2 = new Color(13434879);
        this.bgColor = new Color(255, 255, 255);
        this.frameColor = new Color(5592354);
        this.transformation = new matrix();
        this.theInverse = new matrix();
        this.camera = -100.0;
        this.backPlaneZ = 50.0;
        this.frontPlaneZ = 30.0;
        this.areaPoly = new Polygon();
    }
    
    public void init() {
        CP.height = this.getSize().height;
        CP.width = this.getSize().width;
        CP.origX = CP.width / 2;
        CP.origY = CP.height / 2;
        this.areaPoly.addPoint(CP.origX, CP.origY);
        this.areaPoly.addPoint(100, 100);
        this.areaPoly.addPoint(200, 200);
        this.areaPoly.addPoint(100, 200);
        this.aLabel = new VectorString("a", true);
        this.bLabel = new VectorString("b", true);
        this.cpLabel = new VectorString(new String[] { "a", "X", "b" }, new String[] { "true", "false", "true" }, 3);
        this.setBackground(Color.white);
        onAxes.setOrigin(CP.origX, CP.origY, 3, false);
        this.w = new vector3(0.0, 0.0, 0.75);
        this.v = new vector3(0.0, 0.75, 0.0);
        this.pl = new plane(1.0);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        perspective.setPerspective(4.0E-4);
        this.pl.setRotate(60.0, 60.0);
        (this.transformation = new matrix()).rotZ(60.0);
        this.transformation.rotY(60.0);
        this.v = this.transformation.postMult(this.v);
        this.w = this.transformation.postMult(this.w);
        this.crossProduct = this.v.cross(this.w);
        this.buffer = this.createImage(CP.width, CP.height);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final Polygon startPolygon = this.pl.startPolygon();
        final Polygon polygon = this.pl.getPolygon();
        final point point = new point(x, y, false);
        if (this.dragOne) {
            if (polygon.contains(x, y)) {
                this.v = new vector3(this.pl.getPoint3(point));
                this.crossProduct = this.v.cross(this.w);
                this.repaint();
            }
            return;
        }
        if (this.dragTwo) {
            if (polygon.contains(x, y)) {
                this.w = new vector3(this.pl.getPoint3(point));
                this.crossProduct = this.v.cross(this.w);
                this.repaint();
            }
            return;
        }
        if (polygon.contains(x, y) || startPolygon.contains(x, y)) {
            final int oldX = x * -1;
            final int oldY = y * -1;
            final int n = oldX - this.oldX;
            final int n2 = oldY - this.oldY;
            this.oldX = oldX;
            this.oldY = oldY;
            (this.transformation = new matrix()).rotZ(-n);
            this.transformation.rotY(n2);
            this.v = this.transformation.postMult(this.v);
            this.w = this.transformation.postMult(this.w);
            plane.rotate = this.transformation.postMult(plane.rotate);
            this.crossProduct = this.v.cross(this.w);
            this.repaint();
        }
        else {
            this.oldX = -x;
            this.oldY = -y;
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.oldX = mouseEvent.getX();
        this.oldY = mouseEvent.getY();
        final zone zone = new zone(new point(this.oldX, this.oldY, false), 15, 15, 15, 15);
        if (zone.contains(this.v.head().pointValue())) {
            this.dragOne = true;
            return;
        }
        if (zone.contains(this.w.head().pointValue())) {
            this.dragTwo = true;
            return;
        }
        this.oldX *= -1;
        this.oldY *= -1;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.dragOne = false;
        this.dragTwo = false;
    }
    
    public void paint(final Graphics graphics) {
        final Graphics graphics2 = this.buffer.getGraphics();
        graphics2.clearRect(0, 0, CP.width, CP.height);
        graphics2.setColor(this.bgColor);
        graphics2.fillRect(0, 0, CP.width, CP.height);
        final zone zone = new zone();
        this.VO = this.v.labelPos3();
        this.VT = this.w.labelPos3();
        final vector3 normal = this.pl.normal();
        zone.setValues(this.CrP = this.crossProduct.labelPos3(), 20, 20, 20, 70);
        while (zone.contacts(this.crossProduct) || zone.contacts(this.v) || zone.contacts(this.w)) {
            if (this.CrP.logX() > 0) {
                this.CrP.incX(1);
            }
            else {
                this.CrP.decX(1);
            }
            if (this.CrP.logY() > 0) {
                this.CrP.incY(1);
            }
            else {
                this.CrP.decY(1);
            }
            zone.setPoint(this.CrP);
        }
        if (this.pl.facesForward()) {
            if (normal.head().pointValue().logX() != 0 && normal.head().pointValue().logY() != 0 && this.crossProduct.pointsBackward()) {
                this.crossProduct.drawVector(graphics2, 2, CP.CROSSCOLOR, CP.EDGECOLOR);
                this.cpLabel.draw(graphics2, this.CrP.physX(), this.CrP.physY());
            }
            this.pl.drawPlane(graphics2, this.planeColor2);
            this.areaPoly.xpoints[1] = this.w.head.pointValue().physX();
            this.areaPoly.xpoints[3] = this.v.head.pointValue().physX();
            this.areaPoly.ypoints[1] = this.w.head.pointValue().physY();
            this.areaPoly.ypoints[3] = this.v.head.pointValue().physY();
            this.areaPoly.xpoints[2] = this.areaPoly.xpoints[3] + this.areaPoly.xpoints[1] - CP.origX;
            this.areaPoly.ypoints[2] = this.areaPoly.ypoints[3] + this.areaPoly.ypoints[1] - CP.origY;
            graphics2.setXORMode(CP.XORCOLOR);
            graphics2.setColor(this.planeColor2.darker());
            graphics2.fillPolygon(this.areaPoly);
            graphics2.setPaintMode();
            zone.setValues(this.VO = this.v.labelPos3(), 20, 20, 20, 40);
            while (zone.contains(this.CrP) || zone.contacts(this.crossProduct) || zone.contacts(this.v) || zone.contacts(this.w)) {
                if (this.VO.logX() > 0) {
                    this.VO.incX(1);
                }
                else {
                    this.VO.decX(1);
                }
                if (this.VO.logY() > 0) {
                    this.VO.incY(1);
                }
                else {
                    this.VO.decY(1);
                }
                zone.setPoint(this.VO);
            }
            if (this.v.head().z() != 0.0 || this.v.head().y() != 0.0) {
                this.v.drawVector(graphics2, 2, CP.VCOLOR, CP.EDGECOLOR);
            }
            this.aLabel.draw(graphics2, this.VO.physX(), this.VO.physY());
            zone.setPoint(this.VT = this.w.labelPos3());
            while (zone.contains(this.VO) || zone.contains(this.CrP) || zone.contacts(this.crossProduct) || zone.contacts(this.v) || zone.contacts(this.w)) {
                if (this.VT.logX() > 0) {
                    this.VT.incX(1);
                }
                else {
                    this.VT.decX(1);
                }
                if (this.VT.logY() > 0) {
                    this.VT.incY(1);
                }
                else {
                    this.VT.decY(1);
                }
                zone.setPoint(this.VT);
            }
            if (this.w.head().z() != 0.0 || this.w.head().y() != 0.0) {
                this.w.drawVector(graphics2, 2, CP.WCOLOR, CP.EDGECOLOR);
            }
            this.bLabel.draw(graphics2, this.VT.physX(), this.VT.physY());
            if (normal.head().pointValue().logX() != 0 && normal.head().pointValue().logY() != 0 && !this.crossProduct.pointsBackward()) {
                this.crossProduct.drawVector(graphics2, 2, CP.CROSSCOLOR, CP.EDGECOLOR);
                this.cpLabel.draw(graphics2, this.CrP.physX(), this.CrP.physY());
            }
        }
        else {
            if (normal.head().pointValue().logX() != 0 && normal.head().pointValue().logY() != 0 && this.crossProduct.pointsBackward()) {
                this.crossProduct.drawVector(graphics2, 2, CP.CROSSCOLOR, CP.EDGECOLOR);
                this.cpLabel.draw(graphics2, this.CrP.physX(), this.CrP.physY());
            }
            this.pl.drawPlane(graphics2, this.planeColor);
            this.areaPoly.xpoints[1] = this.w.head.pointValue().physX();
            this.areaPoly.xpoints[3] = this.v.head.pointValue().physX();
            this.areaPoly.ypoints[1] = this.w.head.pointValue().physY();
            this.areaPoly.ypoints[3] = this.v.head.pointValue().physY();
            this.areaPoly.xpoints[2] = this.areaPoly.xpoints[3] + this.areaPoly.xpoints[1] - CP.origX;
            this.areaPoly.ypoints[2] = this.areaPoly.ypoints[3] + this.areaPoly.ypoints[1] - CP.origY;
            graphics2.setXORMode(CP.XORCOLOR);
            graphics2.setColor(this.planeColor.darker());
            graphics2.fillPolygon(this.areaPoly);
            graphics2.setPaintMode();
            zone.setValues(this.VO = this.v.labelPos3(), 20, 20, 20, 40);
            while (zone.contains(this.CrP) || zone.contacts(this.crossProduct) || zone.contacts(this.v) || zone.contacts(this.w)) {
                if (this.VO.logX() > 0) {
                    this.VO.incX(1);
                }
                else {
                    this.VO.decX(1);
                }
                if (this.VO.logY() > 0) {
                    this.VO.incY(1);
                }
                else {
                    this.VO.decY(1);
                }
                zone.setPoint(this.VO);
            }
            if (this.v.head().z() != 0.0 || this.v.head().y() != 0.0) {
                this.v.drawVector(graphics2, 2, CP.VCOLOR, CP.EDGECOLOR);
            }
            this.aLabel.draw(graphics2, this.VO.physX(), this.VO.physY());
            zone.setPoint(this.VT = this.w.labelPos3());
            while (zone.contains(this.VO) || zone.contains(this.CrP) || zone.contacts(this.crossProduct) || zone.contacts(this.v) || zone.contacts(this.w)) {
                if (this.VT.logX() > 0) {
                    this.VT.incX(1);
                }
                else {
                    this.VT.decX(1);
                }
                if (this.VT.logY() > 0) {
                    this.VT.incY(1);
                }
                else {
                    this.VT.decY(1);
                }
                zone.setPoint(this.VT);
            }
            if (this.w.head().z() != 0.0 || this.w.head().y() != 0.0) {
                this.w.drawVector(graphics2, 2, CP.WCOLOR, CP.EDGECOLOR);
            }
            this.bLabel.draw(graphics2, this.VT.physX(), this.VT.physY());
            if (normal.head().pointValue().logX() != 0 && normal.head().pointValue().logY() != 0 && !this.crossProduct.pointsBackward()) {
                this.crossProduct.drawVector(graphics2, 2, CP.CROSSCOLOR, CP.EDGECOLOR);
                this.cpLabel.draw(graphics2, this.CrP.physX(), this.CrP.physY());
            }
        }
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        EDGECOLOR = Color.black;
        VCOLOR = Color.yellow;
        WCOLOR = Color.yellow;
        CROSSCOLOR = Color.red;
        LABELCOLOR = Color.black;
        XORCOLOR = new Color(10066329);
        CP.labelFont = new Font("Serif", 1, 14);
    }
}
