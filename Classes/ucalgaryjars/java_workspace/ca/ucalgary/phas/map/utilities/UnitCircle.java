// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.FontMetrics;
import java.awt.Stroke;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;

public class UnitCircle extends Double
{
    public static final int OPEN = 0;
    public static final int PIE = 2;
    public static final int CHORD = 1;
    public boolean drawArc;
    public boolean fillArc;
    public boolean drawLabels;
    public boolean drawInnerCircle;
    public boolean drawOuterCircle;
    public boolean drawSineLine;
    public boolean drawCosLine;
    protected double sineLineHeight;
    protected double cosLineWidth;
    protected Point2D.Double location;
    protected double radius;
    protected double theta;
    protected int rotations;
    public Color innerCircleColor;
    public Color outerCircleColor;
    public BasicStroke outerCircleStroke;
    public Color fillArcColor;
    public Color drawArcColor;
    public Color sineLineColor;
    public Color cosLineColor;
    public BasicStroke cosStroke;
    public BasicStroke sinStroke;
    protected Line2D.Double cosLine;
    protected Line2D.Double sinLine;
    protected Point2D.Double center;
    protected Point2D.Double thetaPt;
    protected Point2D.Double cosPt;
    protected Point2D.Double sinePt;
    protected Arc2D.Double arc;
    protected Font font;
    public Color fontColor;
    public String[] labels;
    protected boolean enabled;
    
    public UnitCircle() {
        this.drawArc = true;
        this.fillArc = true;
        this.drawLabels = true;
        this.drawInnerCircle = true;
        this.drawOuterCircle = true;
        this.drawSineLine = true;
        this.drawCosLine = true;
        this.location = new Point2D.Double();
        this.theta = 0.0;
        this.rotations = 0;
        this.innerCircleColor = Color.white;
        this.outerCircleColor = Color.black;
        this.outerCircleStroke = new BasicStroke();
        this.fillArcColor = Color.red;
        this.drawArcColor = Color.black;
        this.sineLineColor = Color.red;
        this.cosLineColor = Color.blue;
        this.cosLine = new Line2D.Double();
        this.sinLine = new Line2D.Double();
        this.center = new Point2D.Double();
        this.thetaPt = new Point2D.Double();
        this.cosPt = new Point2D.Double();
        this.sinePt = new Point2D.Double();
        this.arc = new Arc2D.Double();
        this.font = new Font("Dialog", 0, 10);
        this.fontColor = Color.blue;
        this.labels = new String[] { "0, 2\u03c0", "\u03c0/2", "\u03c0", "3\u03c0/2" };
        this.enabled = true;
        final float[] array = { 2.0f, 4.0f };
        this.cosStroke = new BasicStroke(2.0f, 2, 0, 10.0f, array, 0.0f);
        this.sinStroke = new BasicStroke(2.0f, 2, 0, 10.0f, array, 0.0f);
        this.setArcType(2);
        this.arc.start = 0.0;
        this.arc.extent = 0.0;
    }
    
    public void paint(final Graphics graphics) {
        this.paint((Graphics2D)graphics);
    }
    
    public void paint(final Graphics2D graphics2D) {
        final Stroke stroke = graphics2D.getStroke();
        final Font font = graphics2D.getFont();
        final Color color = graphics2D.getColor();
        toInt(this.radius / 3.141592653589793);
        graphics2D.setFont(this.font);
        final FontMetrics fontMetrics = graphics2D.getFontMetrics();
        if (this.drawInnerCircle) {
            graphics2D.setColor(this.innerCircleColor);
            graphics2D.fill(this);
        }
        this.arc.x = super.x;
        this.arc.y = super.y;
        this.arc.width = super.width;
        this.arc.height = super.height;
        if (this.fillArc) {
            graphics2D.setColor(this.fillArcColor);
            graphics2D.fill(this.arc);
        }
        if (this.drawArc) {
            graphics2D.setColor(this.drawArcColor);
            if (Math.abs(this.arc.extent) > 0.001) {
                graphics2D.draw(this.arc);
            }
            else {
                graphics2D.drawLine(toInt(this.center.x), toInt(this.center.y), toInt(this.center.x + this.radius), toInt(this.center.y));
            }
        }
        if (this.drawOuterCircle) {
            graphics2D.setStroke(this.outerCircleStroke);
            graphics2D.setColor(this.outerCircleColor);
            graphics2D.draw(this);
            graphics2D.setStroke(stroke);
        }
        graphics2D.translate(this.center.x, this.center.y);
        if (this.drawOuterCircle) {
            graphics2D.setStroke(this.outerCircleStroke);
            graphics2D.setColor(this.outerCircleColor);
            graphics2D.drawLine(toInt(this.radius) - 3, 0, toInt(this.radius) + 3, 0);
            graphics2D.drawLine(0, toInt(-this.radius) - 3, 0, toInt(-this.radius) + 3);
            graphics2D.drawLine(toInt(-this.radius) - 3, 0, toInt(-this.radius) + 3, 0);
            graphics2D.drawLine(0, toInt(this.radius) - 3, 0, toInt(this.radius) + 3);
            graphics2D.setStroke(stroke);
        }
        if (this.drawLabels) {
            graphics2D.setColor(this.fontColor);
            graphics2D.drawString(this.labels[0], toInt(this.radius) + 3, fontMetrics.getAscent() / 2);
            graphics2D.drawString(this.labels[1], -(fontMetrics.stringWidth(this.labels[1]) / 2), toInt(-this.radius) - 3 - fontMetrics.getDescent());
            graphics2D.drawString(this.labels[2], toInt(-this.radius) - 5 - fontMetrics.stringWidth(this.labels[2]), fontMetrics.getAscent() / 2);
            graphics2D.drawString(this.labels[3], -(fontMetrics.stringWidth(this.labels[3]) / 2), toInt(this.radius) + 3 + fontMetrics.getAscent());
        }
        if (this.drawSineLine) {
            graphics2D.setStroke(this.sinStroke);
            graphics2D.setColor(this.sineLineColor);
            graphics2D.draw(this.sinLine);
        }
        if (this.drawCosLine) {
            graphics2D.setStroke(this.cosStroke);
            graphics2D.setColor(this.cosLineColor);
            graphics2D.draw(this.cosLine);
        }
        graphics2D.translate(-this.center.x, -this.center.y);
        graphics2D.setColor(color);
        graphics2D.setFont(font);
        graphics2D.setStroke(stroke);
    }
    
    public double getRadius() {
        return this.radius;
    }
    
    public void setRadius(final double radius) {
        this.radius = radius;
        super.width = radius * 2.0;
        super.height = radius * 2.0;
        this.arc.width = super.width;
        this.arc.height = super.height;
        this.center.x = super.x + radius;
        this.center.y = super.y + radius;
        this.setLines();
    }
    
    public void setCenter(final double x, final double y) {
        this.center.x = x;
        this.center.y = y;
        super.x = x - this.radius;
        super.y = y - this.radius;
    }
    
    private void setLines() {
        this.cosLine.x1 = 0.0;
        this.cosLine.y1 = -Math.sin(this.theta) * this.radius;
        this.cosLine.x2 = Math.cos(this.theta) * this.radius;
        this.cosLine.y2 = this.cosLine.y1;
        this.sinLine.x1 = this.cosLine.x2;
        this.sinLine.y1 = 0.0;
        this.sinLine.x2 = this.sinLine.x1;
        this.sinLine.y2 = this.cosLine.y1;
    }
    
    public static int toInt(final double n) {
        return (int)(n + 0.5);
    }
    
    public void setArcType(final int arcType) {
        this.arc.setArcType(arcType);
    }
    
    public int getArcType() {
        return this.arc.getArcType();
    }
    
    public double getTheta() {
        return this.theta;
    }
    
    public void setTheta(final double theta) {
        this.theta = theta;
        this.setLabels();
        this.setLines();
    }
    
    public void setLabels() {
        int rotations = (int)Math.floor(this.theta / 6.283185307179586);
        this.arc.extent = Math.toDegrees(this.theta % 6.283185307179586);
        if (rotations != this.rotations) {
            if ((this.rotations = rotations) == 0) {
                this.labels[0] = "0, 2\u03c0";
                this.labels[1] = "\u03c0/2";
                this.labels[2] = "\u03c0";
                this.labels[3] = "3\u03c0/2";
            }
            if (rotations == -1) {
                this.labels[0] = "0, -2\u03c0";
                this.labels[3] = "-\u03c0/2";
                this.labels[2] = "-\u03c0";
                this.labels[1] = "-3\u03c0/2";
            }
            if (rotations > 0) {
                this.labels[0] = "" + 2 * rotations + "\u03c0, " + (2 * rotations + 2) + "\u03c0";
                this.labels[1] = "" + (4 * rotations + 1) + "\u03c0/2";
                this.labels[2] = "" + (2 * rotations + 1) + "\u03c0";
                this.labels[3] = "" + (4 * rotations + 3) + "\u03c0/2";
            }
            if (rotations < -1) {
                ++rotations;
                this.labels[0] = "" + 2 * rotations + "\u03c0, " + (2 * rotations - 2) + "\u03c0";
                this.labels[3] = "" + (4 * rotations - 1) + "\u03c0/2";
                this.labels[2] = "" + (2 * rotations - 1) + "\u03c0";
                this.labels[1] = "" + (4 * rotations - 3) + "\u03c0/2";
            }
        }
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setFont(final Font font) {
        this.font = font;
    }
    
    public Color getFontColor() {
        return this.fontColor;
    }
    
    public void setFontColor(final Color fontColor) {
        this.fontColor = fontColor;
    }
    
    public void translate(final double n, final double n2) {
        super.x += n;
        super.y += n2;
        final Point2D.Double center = this.center;
        center.x += n;
        final Point2D.Double center2 = this.center;
        center2.y += n2;
    }
    
    public Point2D.Double getCenter() {
        return (Point2D.Double)this.center.clone();
    }
    
    public Color getInnerCircleColor() {
        return this.innerCircleColor;
    }
    
    public void setInnerCircleColor(final Color innerCircleColor) {
        this.innerCircleColor = innerCircleColor;
    }
    
    public Color getOuterCircleColor() {
        return this.outerCircleColor;
    }
    
    public void setOuterCircleColor(final Color outerCircleColor) {
        this.outerCircleColor = outerCircleColor;
    }
    
    public double getX() {
        return super.x;
    }
    
    public void setX(final double n) {
        super.x = n;
        this.location.x = n;
        this.center.x = n + this.radius;
    }
    
    public double getY() {
        return super.y;
    }
    
    public void setY(final double n) {
        super.y = n;
        this.location.y = n;
        this.center.y = n + this.radius;
    }
    
    public void setLocation(final double x, final double y) {
        this.setX(x);
        this.setY(y);
    }
    
    public void setLocation(final Point2D.Double double1) {
        this.setX(double1.x);
        this.setY(double1.y);
    }
    
    public Point2D.Double getLocation() {
        return (Point2D.Double)this.location.clone();
    }
    
    public Color getCosLineColor() {
        return this.cosLineColor;
    }
    
    public void setCosLineColor(final Color cosLineColor) {
        this.cosLineColor = cosLineColor;
    }
    
    public Color getSineLineColor() {
        return this.sineLineColor;
    }
    
    public void setSineLineColor(final Color sineLineColor) {
        this.sineLineColor = sineLineColor;
    }
    
    public boolean getDrawArc() {
        return this.drawArc;
    }
    
    public void setDrawArc(final boolean drawArc) {
        this.drawArc = drawArc;
    }
    
    public boolean getDrawCosLine() {
        return this.drawCosLine;
    }
    
    public void setDrawCosLine(final boolean drawCosLine) {
        this.drawCosLine = drawCosLine;
    }
    
    public boolean getDrawInnerCircle() {
        return this.drawInnerCircle;
    }
    
    public void setDrawInnerCircle(final boolean drawInnerCircle) {
        this.drawInnerCircle = drawInnerCircle;
    }
    
    public boolean getDrawLabels() {
        return this.drawLabels;
    }
    
    public void setDrawLabels(final boolean drawLabels) {
        this.drawLabels = drawLabels;
    }
    
    public boolean getDrawOuterCircle() {
        return this.drawOuterCircle;
    }
    
    public void setDrawOuterCircle(final boolean drawOuterCircle) {
        this.drawOuterCircle = drawOuterCircle;
    }
    
    public boolean getDrawSineLine() {
        return this.drawSineLine;
    }
    
    public void setDrawSineLine(final boolean drawSineLine) {
        this.drawSineLine = drawSineLine;
    }
    
    public boolean getFillArc() {
        return this.fillArc;
    }
    
    public void setFillArc(final boolean fillArc) {
        this.fillArc = fillArc;
    }
    
    public Color getFillArcColor() {
        return this.fillArcColor;
    }
    
    public void setFillArcColor(final Color fillArcColor) {
        this.fillArcColor = fillArcColor;
    }
    
    public boolean getEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public Point2D.Double getPointOnCircle() {
        return new Point2D.Double(this.center.x + Math.cos(this.theta) * this.radius, this.center.y - Math.sin(this.theta) * this.radius);
    }
    
    public BasicStroke getOuterCircleStroke() {
        return this.outerCircleStroke;
    }
    
    public void setOuterCircleStroke(final BasicStroke outerCircleStroke) {
        this.outerCircleStroke = outerCircleStroke;
    }
}
