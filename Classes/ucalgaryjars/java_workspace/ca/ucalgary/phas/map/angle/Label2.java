// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.angle;

import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

public class Label2
{
    public Color bg;
    public Color border;
    public Color fontColor;
    private Font font;
    private FontMetrics fm;
    private String text;
    public Point2D.Double loc;
    private Dimension dim;
    private Rectangle2D.Double bounds;
    private Component parent;
    
    public Label2(final String s, final double x, final double y, final Component parent) {
        this.bg = new Color(16777164);
        this.border = Color.black;
        this.fontColor = Color.black;
        this.font = new Font("Dialog", 0, 12);
        this.loc = new Point2D.Double();
        this.dim = new Dimension();
        this.bounds = new Rectangle2D.Double();
        this.parent = parent;
        this.text = s;
        this.fm = parent.getFontMetrics(this.font);
        this.dim.width = toInt(this.fm.stringWidth(s) * 1.5);
        this.dim.height = this.fm.getHeight();
        this.loc.x = x;
        this.loc.y = y;
    }
    
    public Label2(final String s, final Component component) {
        this(s, 0.0, 0.0, component);
    }
    
    public Label2(final Component component) {
        this("", component);
    }
    
    public void paint(final Graphics graphics) {
        this.paint((Graphics2D)graphics);
    }
    
    public void paint(final Graphics2D graphics2D) {
        final Color color = graphics2D.getColor();
        final Font font = graphics2D.getFont();
        graphics2D.setFont(this.font);
        this.bounds.x = this.loc.x;
        this.bounds.y = this.loc.y;
        this.bounds.width = this.dim.width;
        this.bounds.height = this.dim.height;
        graphics2D.setColor(this.bg);
        graphics2D.fill(this.bounds);
        graphics2D.setColor(this.border);
        graphics2D.draw(this.bounds);
        graphics2D.setColor(this.fontColor);
        graphics2D.drawString(this.text, toInt(this.loc.x + (this.dim.width - this.fm.stringWidth(this.text)) / 2), toInt(this.loc.y + this.fm.getAscent()));
        graphics2D.setFont(font);
        graphics2D.setColor(color);
    }
    
    public static int toInt(final double n) {
        return (int)(n + 0.5);
    }
    
    public double getWidth() {
        return this.dim.width;
    }
    
    public double getHeight() {
        return this.dim.height;
    }
    
    public Point2D.Double getLoc() {
        return this.loc;
    }
    
    public void setLoc(final Point2D.Double double1) {
        this.setLoc(double1.x, double1.y);
    }
    
    public void setLoc(final double x, final double y) {
        this.loc.x = x;
        this.loc.y = y;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String s) {
        this.text = s;
        this.dim.width = toInt(this.fm.stringWidth(s) * 1.5);
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setFont(final Font font) {
        this.font = font;
        this.fm = this.parent.getFontMetrics(font);
        this.dim.width = toInt(this.fm.stringWidth(this.text) * 1.5);
        this.dim.height = this.fm.getHeight();
    }
}
