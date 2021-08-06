// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.text.DecimalFormat;
import java.awt.geom.Point2D;
import java.awt.Polygon;
import java.awt.geom.Arc2D;
import java.awt.Color;

public class Speedometer
{
    public static Color areaAccentColor;
    private int speedometerX;
    private int speedometerY;
    private int height;
    private int width;
    private Arc2D.Double topArc;
    private Arc2D.Double bottomArc;
    private Arc2D.Double topArcArea;
    private Arc2D.Double topArcChord;
    private Arc2D.Double bottomArcArea;
    private Arc2D.Double bottomArcChord;
    ComplexString label;
    private double min;
    private double max;
    private double minorTicks;
    private double majorTicks;
    private boolean visible;
    private double minimum;
    private double maximum;
    private double value;
    private Color backgroundColor;
    private Color edgeColor;
    double centerX;
    double centerY;
    double radius;
    double insideRadius;
    int p1x;
    int p1y;
    int p2x;
    int p2y;
    int p3x;
    int p3y;
    int p4x;
    int p4y;
    Polygon polygon;
    Point2D tempPoint;
    public DecimalFormat df1;
    public FontMetrics fm;
    Graphics2D g2;
    double minorTickSpacing;
    double majorTickSpacing;
    private static final double FIRST_ANGLE = 2.0708;
    private static final double LAST_ANGLE = 1.07;
    
    public Speedometer() {
        this.min = 0.0;
        this.max = 10.0;
        this.minorTicks = 0.5;
        this.majorTicks = 1.0;
        this.visible = true;
        this.backgroundColor = Color.white;
        this.edgeColor = Color.black;
        this.df1 = new DecimalFormat("0.0");
        this.topArc = new Arc2D.Double(0);
        this.topArcArea = new Arc2D.Double(2);
        this.topArcChord = new Arc2D.Double(2);
        this.bottomArc = new Arc2D.Double(0);
        this.bottomArcArea = new Arc2D.Double(2);
        this.bottomArcChord = new Arc2D.Double(1);
        this.polygon = new Polygon();
        this.label = new ComplexString("Speed");
        this.calculateTicks();
        this.setBounds(0, 0, 300, 110);
    }
    
    public void setValue(final double value) {
        this.value = value;
    }
    
    public void setLabel(final ComplexString label) {
        this.label = label;
    }
    
    public void setBounds(final int speedometerX, final int speedometerY, final int width, final int height) {
        this.speedometerX = speedometerX;
        this.speedometerY = speedometerY;
        this.height = height;
        this.width = width;
        this.setArcs();
    }
    
    public void setX(final int speedometerX) {
        this.speedometerX = speedometerX;
        this.setArcs();
    }
    
    public void setY(final int speedometerY) {
        this.speedometerY = speedometerY;
        this.setArcs();
    }
    
    public void setWidth(final int width) {
        this.width = width;
        this.setArcs();
    }
    
    public void setHeight(final int height) {
        this.height = height;
        this.setArcs();
    }
    
    public int getX() {
        return this.speedometerX;
    }
    
    public int getY() {
        return this.speedometerY;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    private void setArcs() {
        this.radius = this.width / 2 / 0.52992;
        this.insideRadius = (this.radius - this.height) / 0.84805;
        this.centerY = this.speedometerY + this.radius;
        this.centerX = this.speedometerX + this.width / 2;
        this.topArc.setArcByCenter(this.speedometerX + this.width / 2, this.centerY, this.radius, 58.0, 64.0, 0);
        this.topArcArea.setArc(this.topArc);
        this.topArcArea.setArcType(2);
        this.topArcChord.setArc(this.topArc);
        this.topArcChord.setArcType(1);
        this.tempPoint = this.topArc.getStartPoint();
        this.p2x = (int)(this.tempPoint.getX() + 0.5);
        this.p2y = (int)(this.tempPoint.getY() + 0.5);
        this.tempPoint = this.topArc.getEndPoint();
        this.p1x = (int)(this.tempPoint.getX() + 0.5);
        this.p1y = (int)(this.tempPoint.getY() + 0.5);
        this.bottomArc.setArcByCenter(this.speedometerX + this.width / 2, this.centerY, this.insideRadius, 58.0, 64.0, 0);
        this.bottomArcArea.setArc(this.bottomArc);
        this.bottomArcArea.setArcType(2);
        this.bottomArcChord.setArc(this.bottomArc);
        this.bottomArcChord.setArcType(1);
        this.tempPoint = this.bottomArc.getStartPoint();
        this.p4x = (int)(this.tempPoint.getX() + 0.5);
        this.p4y = (int)(this.tempPoint.getY() + 0.5);
        this.tempPoint = this.bottomArc.getEndPoint();
        this.p3x = (int)(this.tempPoint.getX() + 0.5);
        this.p3y = (int)(this.tempPoint.getY() + 0.5);
        this.polygon.npoints = 0;
        this.polygon.addPoint(this.p1x, this.p1y);
        this.polygon.addPoint(this.p2x, this.p2y);
        this.polygon.addPoint(this.p4x, this.p4y);
        this.polygon.addPoint(this.p3x, this.p3y);
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void setLocation(final int speedometerX, final int speedometerY) {
        this.speedometerX = speedometerX;
        this.speedometerY = speedometerY;
        this.setArcs();
    }
    
    public void translate(final int n, final int n2) {
        this.speedometerX += n;
        this.speedometerY += n2;
        this.setArcs();
    }
    
    public boolean contains(final int n, final int n2) {
        return this.visible && this.topArcArea.contains(n, n2) && n2 <= this.speedometerY + this.height + 8;
    }
    
    public void paint(final Graphics graphics) {
        (this.g2 = (Graphics2D)graphics).setColor(this.backgroundColor);
        this.g2.fillPolygon(this.polygon);
        this.g2.drawPolygon(this.polygon);
        this.g2.fill(this.topArcChord);
        this.g2.draw(this.topArcChord);
        this.g2.setColor(Speedometer.areaAccentColor);
        this.g2.fill(this.bottomArcChord);
        this.g2.draw(this.bottomArcChord);
        this.g2.fillRect(this.p3x, this.p3y, this.p4x - this.p3x, 8);
        this.g2.drawRect(this.p3x, this.p3y, this.p4x - this.p3x, 8);
        this.g2.setColor(this.edgeColor);
        this.g2.draw(this.topArc);
        this.g2.draw(this.bottomArc);
        this.g2.drawLine(this.p1x, this.p1y, this.p3x, this.p3y);
        this.g2.drawLine(this.p2x, this.p2y, this.p4x, this.p4y);
        this.g2.drawLine(this.p3x, this.p3y, this.p3x, this.p3y + 8);
        this.g2.drawLine(this.p4x, this.p4y, this.p4x, this.p4y + 8);
        this.g2.drawLine(this.p3x, this.p3y + 8, this.p4x, this.p4y + 8);
        this.label.drawBaseline(this.g2, this.speedometerX + (this.width - this.label.getWidth()) / 2, this.speedometerY + this.height + 4);
        this.g2.setFont(MapConstants.defaultScreenFont);
        this.fm = this.g2.getFontMetrics();
        for (double n = 2.0708; n >= 1.07; n -= this.minorTickSpacing) {
            this.g2.drawLine((int)(this.centerX + Math.cos(n) * (this.radius - 30.0)), (int)(this.centerY - Math.sin(n) * (this.radius - 30.0)), (int)(this.centerX + Math.cos(n) * (this.radius - 40.0)), (int)(this.centerY - Math.sin(n) * (this.radius - 40.0)));
        }
        for (double n2 = 2.0708; n2 >= 1.07; n2 -= this.majorTickSpacing) {
            this.g2.drawLine((int)(this.centerX + Math.cos(n2) * (this.radius - 25.0)), (int)(this.centerY - Math.sin(n2) * (this.radius - 25.0)), (int)(this.centerX + Math.cos(n2) * (this.radius - 50.0)), (int)(this.centerY - Math.sin(n2) * (this.radius - 50.0)));
            final String format = this.df1.format((2.0708 - n2) * (this.max - this.min) + this.min);
            this.g2.drawString(format, (int)(this.centerX + Math.cos(n2) * (this.radius - 15.0)) - this.fm.stringWidth(format) / 2, (int)(this.centerY - Math.sin(n2) * (this.radius - 15.0)));
        }
        double n3 = 2.0708 - (this.value - this.min) / (this.max - this.min);
        if (n3 > 2.0708) {
            n3 = 2.0708;
        }
        if (n3 < 1.07) {
            n3 = 1.07;
        }
        this.g2.setColor(Color.red);
        this.g2.drawLine((int)(this.centerX + Math.cos(n3) * this.insideRadius), (int)(this.centerY - Math.sin(n3) * this.insideRadius), (int)(this.centerX + Math.cos(n3) * (this.radius - 20.0)), (int)(this.centerY - Math.sin(n3) * (this.radius - 20.0)));
    }
    
    public void setMinimum(final double min) {
        this.min = min;
        this.calculateTicks();
    }
    
    public void setMaximum(final double max) {
        this.max = max;
        this.calculateTicks();
    }
    
    public void setMinorTicks(final double minorTicks) {
        this.minorTicks = minorTicks;
        this.calculateTicks();
    }
    
    public void setMajorTicks(final double majorTicks) {
        this.majorTicks = majorTicks;
        this.calculateTicks();
    }
    
    private void calculateTicks() {
        this.minorTickSpacing = 1.0 / ((this.max - this.min) / this.minorTicks);
        this.majorTickSpacing = 1.0 / ((this.max - this.min) / this.majorTicks);
    }
    
    static {
        Speedometer.areaAccentColor = new Color(153, 204, 153);
    }
}
