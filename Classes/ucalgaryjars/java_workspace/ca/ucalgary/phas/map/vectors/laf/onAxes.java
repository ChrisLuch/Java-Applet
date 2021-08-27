// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors.laf;

import java.awt.Graphics;
import java.awt.Point;

class onAxes extends perspective
{
    static Point orig;
    static int dimensions;
    static boolean adjust;
    
    public static void drawAxes(final Graphics graphics) {
        final VectorUtilities vectorUtilities = new VectorUtilities();
        int n;
        if (onAxes.orig.x < onAxes.orig.y) {
            n = 4 * onAxes.orig.x / 5;
        }
        else {
            n = 4 * onAxes.orig.y / 5;
        }
        if (onAxes.dimensions == 2) {
            vectorUtilities.drawVector(graphics, 1, onAxes.orig.x, onAxes.orig.y, onAxes.orig.x, onAxes.orig.y - n, colors.axisBlack(), colors.backGround());
            vectorUtilities.drawVector(graphics, 1, onAxes.orig.x, onAxes.orig.y, onAxes.orig.x - n, onAxes.orig.y, colors.axisBlack(), colors.backGround());
            vectorUtilities.drawVector(graphics, 1, onAxes.orig.x, onAxes.orig.y, onAxes.orig.x + n, onAxes.orig.y, colors.axisBlack(), colors.backGround());
            vectorUtilities.drawVector(graphics, 1, onAxes.orig.x, onAxes.orig.y, onAxes.orig.x, onAxes.orig.y + n, colors.axisBlack(), colors.backGround());
        }
        if (onAxes.dimensions == 3) {
            drawAxis(graphics, 1);
            drawAxis(graphics, 2);
            drawAxis(graphics, 3);
        }
    }
    
    public static void drawAxis(final Graphics graphics, final int n) {
        final VectorUtilities vectorUtilities = new VectorUtilities();
        int n2;
        if (onAxes.orig.x < onAxes.orig.y) {
            n2 = 4 * onAxes.orig.x / 5;
        }
        else {
            n2 = 4 * onAxes.orig.y / 5;
        }
        if (n == 3) {
            vectorUtilities.drawVector(graphics, 1, onAxes.orig.x, onAxes.orig.y, onAxes.orig.x, onAxes.orig.y - n2, colors.axisBlack(), colors.backGround());
        }
        if (n == 2) {
            vectorUtilities.drawVector(graphics, 1, onAxes.orig.x, onAxes.orig.y, onAxes.orig.x + n2, onAxes.orig.y, colors.axisBlack(), colors.backGround());
        }
        if (n == 1) {
            vectorUtilities.drawVector(graphics, 1, onAxes.orig.x, onAxes.orig.y, onAxes.orig.x - 6 * n2 / 10, onAxes.orig.y + 6 * n2 / 10, colors.axisBlack(), colors.backGround());
        }
    }
    
    public static void drawFrame(final Graphics graphics) {
        final VectorUtilities vectorUtilities = new VectorUtilities();
        vectorUtilities.drawLine(graphics, 5, 0, 0, 2 * onAxes.orig.x, 0, colors.frameColor(), colors.backGround());
        vectorUtilities.drawLine(graphics, 5, 2 * onAxes.orig.x, 0, 2 * onAxes.orig.x, 2 * onAxes.orig.y, colors.frameColor(), colors.backGround());
        vectorUtilities.drawLine(graphics, 5, 2 * onAxes.orig.x, 2 * onAxes.orig.y, 0, 2 * onAxes.orig.y, colors.frameColor(), colors.backGround());
        vectorUtilities.drawLine(graphics, 5, 0, 2 * onAxes.orig.y, 0, 0, colors.frameColor(), colors.backGround());
    }
    
    public static void drawSmallAxes(final Graphics graphics, final point point) {
        graphics.setColor(colors.axisBlack());
        graphics.drawLine(point.physX(), point.physY(), point.physX(), point.physY() - 30);
        graphics.drawLine(point.physX(), point.physY() - 30, point.physX() - 3, point.physY() - 23);
        graphics.drawLine(point.physX(), point.physY() - 30, point.physX() + 3, point.physY() - 23);
        graphics.drawLine(point.physX(), point.physY(), point.physX() + 30, point.physY());
        graphics.drawLine(point.physX() + 30, point.physY(), point.physX() + 23, point.physY() - 3);
        graphics.drawLine(point.physX() + 30, point.physY(), point.physX() + 23, point.physY() + 3);
        graphics.drawLine(point.physX(), point.physY(), point.physX() - 18, point.physY() + 18);
        graphics.drawLine(point.physX() - 18, point.physY() + 18, point.physX() - 16, point.physY() + 11);
        graphics.drawLine(point.physX() - 18, point.physY() + 18, point.physX() - 11, point.physY() + 16);
    }
    
    public static void drawSmallAxes(final Graphics graphics, final point3 point3) {
        drawSmallAxes(graphics, point3.pointValue());
    }
    
    public static void setOrigin(final int x, final int y, final int dimensions) {
        onAxes.orig.x = x;
        onAxes.orig.y = y;
        onAxes.dimensions = dimensions;
        perspective.setTrans();
    }
    
    public static void setOrigin(final int x, final int y, final int dimensions, final boolean adjust) {
        onAxes.orig.x = x;
        onAxes.orig.y = y;
        onAxes.dimensions = dimensions;
        onAxes.adjust = adjust;
        perspective.setTrans();
    }
    
    public static point3 xAxis() {
        final vector3 vector3 = new vector3(0.0, 0.0, 0.0, 1.0, 0.0, 0.0);
        int n;
        if (onAxes.orig.x < onAxes.orig.y) {
            n = 4 * onAxes.orig.x / 5;
        }
        else {
            n = 4 * onAxes.orig.y / 5;
        }
        return vector3.getPoint3(new point(-6 * n / 10, -6 * n / 10, true));
    }
    
    public static point3 yAxis() {
        final vector3 vector3 = new vector3(0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
        int n;
        if (onAxes.orig.x < onAxes.orig.y) {
            n = 4 * onAxes.orig.x / 5;
        }
        else {
            n = 4 * onAxes.orig.y / 5;
        }
        return vector3.getPoint3(new point(n, 0, true));
    }
    
    public static point3 zAxis() {
        final vector3 vector3 = new vector3(0.0, 0.0, 0.0, 0.0, 0.0, 1.0);
        int n;
        if (onAxes.orig.x < onAxes.orig.y) {
            n = 4 * onAxes.orig.x / 5;
        }
        else {
            n = 4 * onAxes.orig.y / 5;
        }
        return vector3.getPoint3(new point(0, n, true));
    }
    
    static {
        onAxes.orig = new Point();
    }
}
