// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities.vectors;

import java.awt.Polygon;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics;

public class VectorUtil
{
    public static void drawDotLine(final Graphics graphics, final Point2D.Double double1, final Point2D.Double double2, final double n, final Color color) {
        final Color color2 = graphics.getColor();
        graphics.setColor(color);
        final double sqrt = Math.sqrt((double1.x - double2.x) * (double1.x - double2.x) + (double1.y - double2.y) * (double1.y - double2.y));
        final double n2 = (double1.x == double2.x) ? 0.0 : ((double2.x - double1.x) / sqrt);
        final double n3 = (double1.y == double2.y) ? 0.0 : ((double2.y - double1.y) / sqrt);
        final double x = double1.x;
        final double y = double1.y;
        if (n >= 4.0) {
            for (int n4 = 0; (3 * n4 + 1) * n <= sqrt - n; ++n4) {
                final double n5 = double1.x + n * n2 + 3 * n4 * n * n2;
                final double n6 = double1.y + n * n3 + 3 * n4 * n * n3;
                final int n7 = (int)(n5 - n / 2.0 + 1.5);
                final int n8 = (int)(n6 - n / 2.0 + 1.5);
                final int n9 = (int)(n + 0.5) + 1;
                graphics.fillOval(n7, n8, n9, n9);
            }
        }
        if (n < 4.0) {
            for (int n10 = 0; (4 * n10 + 1) * n <= sqrt - n; ++n10) {
                final double n11 = double1.x + n * n2 + 4 * n10 * n * n2;
                final double n12 = double1.y + n * n3 + 4 * n10 * n * n3;
                if (n == 1.0) {
                    final int n13 = (int)(n11 + 0.5);
                    final int n14 = (int)(n12 + 0.5);
                    graphics.drawLine(n13, n14, n13, n14);
                }
                else {
                    final int n15 = (int)(n11 - n / 2.0 + 1.5);
                    final int n16 = (int)(n12 - n / 2.0 + 1.5);
                    final int n17 = (int)(n + 0.5);
                    graphics.fillOval(n15, n16, n17, n17);
                }
            }
        }
        graphics.setColor(color2);
    }
    
    public static void drawLine(final Graphics graphics, final double n, final Point2D.Double double1, final Point2D.Double double2, final Color color, final Color color2) {
        drawLine(graphics, getLinePolygon(double1, double2, n), color, color2);
    }
    
    public static void drawLine(final Graphics graphics, final Polygon polygon, final Color color, final Color color2) {
        graphics.setColor(color2);
        graphics.fillPolygon(polygon);
        graphics.setColor(color);
        graphics.drawPolygon(polygon);
    }
    
    public static void drawVector(final Graphics graphics, final double n, final double n2, final Point2D.Double double1, final Point2D.Double pt, final Color color, final Color color2) {
        drawVector(graphics, getLinePolygon(double1, translatePoint2D(double1, findAngle(double1, pt), double1.distance(pt) - n2 / 3.0), n), getArrowheadPolygon(double1, pt, n, n2), double1, pt, color, color2);
    }
    
    public static void drawVector(final Graphics graphics, final Polygon polygon, final Polygon p7, final Point2D.Double obj, final Point2D.Double double1, final Color color, final Color color2) {
        if (double1.equals(obj)) {
            graphics.setColor(color2);
            graphics.fillOval((int)(double1.x + 0.5) - 4, (int)(double1.y + 0.5) - 4, 9, 9);
            if (!color2.equals(color)) {
                graphics.setColor(color);
                graphics.drawOval((int)(double1.x + 0.5) - 4, (int)(double1.y + 0.5) - 4, 9, 9);
            }
        }
        else {
            graphics.setColor(color2);
            graphics.fillPolygon(polygon);
            graphics.setColor(color);
            graphics.drawPolygon(polygon);
            graphics.setColor(color2);
            graphics.fillPolygon(p7);
            graphics.setColor(color);
            graphics.drawPolyline(p7.xpoints, p7.ypoints, p7.npoints);
        }
    }
    
    public static double findAngle(final Point2D.Double double1, final Point2D.Double double2) {
        double atan2 = Math.atan2(double1.y - double2.y, double2.x - double1.x);
        if (atan2 < 0.0) {
            atan2 += 6.283185307179586;
        }
        return atan2;
    }
    
    public static double findAngle(final double n, final double n2, final double n3, final double n4) {
        double atan2 = Math.atan2(n2 - n4, n3 - n);
        if (atan2 < 0.0) {
            atan2 += 6.283185307179586;
        }
        return atan2;
    }
    
    public static double findXOnLine(final Point2D.Double double1, final Point2D.Double double2, final double n) {
        final double n2 = double2.y - double1.y;
        final double n3 = double2.x - double1.x;
        if (n2 == 0.0) {
            return Double.NaN;
        }
        if (n3 == 0.0) {
            return double1.x;
        }
        final double n4 = n2 / n3;
        return (-n4 * double1.x + double1.y - n) / -n4;
    }
    
    public static double findYOnLine(final Point2D.Double double1, final Point2D.Double double2, final double n) {
        final double n2 = double2.y - double1.y;
        final double n3 = double2.x - double1.x;
        if (n2 == 0.0) {
            return double1.y;
        }
        if (n3 == 0.0) {
            return Double.NaN;
        }
        return n2 / n3 * (n - double1.x) + double1.y;
    }
    
    public static Point2D.Double translatePoint2D(final Point2D.Double double1, final double n, final double n2) {
        return new Point2D.Double(double1.x + Math.cos(n) * n2, double1.y - Math.sin(n) * n2);
    }
    
    public static Point2D.Double translatePoint2D(final double n, final double n2, final double n3, final double n4) {
        return new Point2D.Double(n + Math.cos(n3) * n4, n2 - Math.sin(n3) * n4);
    }
    
    public static double translatePoint2Dx(final double n, final double n2, final double a, final double n3) {
        return n + Math.cos(a) * n3;
    }
    
    public static double translatePoint2Dy(final double n, final double n2, final double a, final double n3) {
        return n2 - Math.sin(a) * n3;
    }
    
    public static Polygon getLinePolygon(final Point2D.Double double1, final Point2D.Double double2, final double n) {
        final int n2 = (int)(double1.x + 0.5);
        final int n3 = (int)(double1.y + 0.5);
        final int n4 = (int)(double2.x + 0.5);
        final int n5 = (int)(double2.y + 0.5);
        final double n6 = findAngle(double1, double2) / 3.141592653589793;
        final int n7 = (int)((n - 1.0) / 2.0);
        int n8;
        int n9;
        int x;
        int y;
        int x2;
        int y2;
        int x3;
        int y3;
        if (n6 % 1.0 < 0.75 && 0.25 < n6 % 1.0) {
            n8 = n2 - n7;
            n9 = n3;
            x = n2 + n7;
            y = n3;
            x2 = n4 + n7;
            y2 = n5;
            x3 = n4 - n7;
            y3 = n5;
        }
        else {
            n8 = n2;
            n9 = n3 - n7;
            x = n2;
            y = n3 + n7;
            x2 = n4;
            y2 = n5 + n7;
            x3 = n4;
            y3 = n5 - n7;
        }
        final Polygon polygon = new Polygon();
        polygon.addPoint(n8, n9);
        polygon.addPoint(x, y);
        polygon.addPoint(x2, y2);
        polygon.addPoint(x3, y3);
        polygon.addPoint(n8, n9);
        return polygon;
    }
    
    public static Polygon getArrowheadPolygon(final Point2D.Double double1, final Point2D.Double pt, final double n, double n2) {
        final double angle = findAngle(double1, pt);
        final double distance = double1.distance(pt);
        double n3 = n2 / 2.0;
        double n4 = 4.0;
        if (distance <= n3) {
            n3 = distance;
        }
        if (distance <= n2) {
            n2 = distance;
            n4 = 3.9;
        }
        final Point2D.Double translatePoint2D = translatePoint2D(double1, angle, distance - n3);
        final Point2D.Double translatePoint2D2 = translatePoint2D(translatePoint2D, angle + 1.5707963267948966, (n - 1.0) / 2.0);
        final Point2D.Double translatePoint2D3 = translatePoint2D(translatePoint2D, angle - 1.5707963267948966, (n - 1.0) / 2.0);
        final Point2D.Double translatePoint2D4 = translatePoint2D(pt, angle + 9.42477796076938 / n4, n2);
        final Point2D.Double translatePoint2D5 = translatePoint2D(pt, angle - 9.42477796076938 / n4, n2);
        final Polygon polygon = new Polygon();
        polygon.addPoint((int)(translatePoint2D2.x + 0.5), (int)(translatePoint2D2.y + 0.5));
        polygon.addPoint((int)(translatePoint2D4.x + 0.5), (int)(translatePoint2D4.y + 0.5));
        polygon.addPoint((int)(pt.x + 0.5), (int)(pt.y + 0.5));
        polygon.addPoint((int)(translatePoint2D5.x + 0.5), (int)(translatePoint2D5.y + 0.5));
        polygon.addPoint((int)(translatePoint2D3.x + 0.5), (int)(translatePoint2D3.y + 0.5));
        return polygon;
    }
    
    public static Polygon getVectorPolygon(final Point2D.Double double1, final Point2D.Double pt, final double n, double n2) {
        final double angle = findAngle(double1, pt);
        final double distance = double1.distance(pt);
        double n3 = n2 / 2.0;
        double n4 = 4.0;
        if (distance <= n3) {
            n3 = distance;
        }
        if (distance <= n2) {
            n2 = distance;
            n4 = 3.9;
        }
        final Point2D.Double translatePoint2D = translatePoint2D(double1, angle + 1.5707963267948966, n / 2.0);
        final Point2D.Double translatePoint2D2 = translatePoint2D(double1, angle - 1.5707963267948966, n / 2.0);
        translatePoint2D(double1, angle, distance - n3);
        final Point2D.Double translatePoint2D3 = translatePoint2D(pt, angle + 1.5707963267948966, n / 2.0);
        final Point2D.Double translatePoint2D4 = translatePoint2D(pt, angle - 1.5707963267948966, n / 2.0);
        final Point2D.Double translatePoint2D5 = translatePoint2D(pt, angle + 9.42477796076938 / n4, n2);
        final Point2D.Double translatePoint2D6 = translatePoint2D(pt, angle - 9.42477796076938 / n4, n2);
        final Polygon polygon = new Polygon();
        polygon.addPoint((int)(translatePoint2D.x + 0.5), (int)(translatePoint2D.y + 0.5));
        polygon.addPoint((int)(translatePoint2D3.x + 0.5), (int)(translatePoint2D3.y + 0.5));
        polygon.addPoint((int)(translatePoint2D5.x + 0.5), (int)(translatePoint2D5.y + 0.5));
        polygon.addPoint((int)(pt.x + 0.5), (int)(pt.y + 0.5));
        polygon.addPoint((int)(translatePoint2D6.x + 0.5), (int)(translatePoint2D6.y + 0.5));
        polygon.addPoint((int)(translatePoint2D4.x + 0.5), (int)(translatePoint2D4.y + 0.5));
        polygon.addPoint((int)(translatePoint2D2.x + 0.5), (int)(translatePoint2D2.y + 0.5));
        return polygon;
    }
    
    public static Vector2d add(final Vector2d vector2d, final Vector2d vector2d2) {
        return new Vector2d(vector2d.getTail2D(), new Point2D.Double(vector2d.getHeadx() + (vector2d2.getHeadx() - vector2d2.getTailx()), vector2d.getHeady() + (vector2d2.getHeady() - vector2d2.getTaily())));
    }
    
    public static Point2D.Double rotatePoint(final Point2D.Double double1, final Point2D.Double double2, final double n) {
        final Point2D.Double double3 = new Point2D.Double();
        final double x = double2.x;
        final double y = double2.y;
        final double sin = Math.sin(-n);
        final double cos = Math.cos(-n);
        double3.x = double1.x * cos - double1.y * sin - x * cos + y * sin + x;
        double3.y = double1.x * sin + double1.y * cos - x * sin - y * cos + y;
        return double3;
    }
    
    public static void setVectorLabels(final Vector2d vector2d, final Vector2d vector2d2, final Vector2d vector2d3) {
        final Point2D.Double double1 = new Point2D.Double(vector2d2.getMidPointX(), vector2d2.getMidPointY());
        final Point2D.Double double2 = new Point2D.Double(vector2d.getMidPointX(), vector2d.getMidPointY());
        final Point2D.Double double3 = new Point2D.Double(vector2d.getMidPointX(), vector2d.getMidPointY());
        vector2d.setLabelSide(getVectorLabelSide(vector2d, double1));
        vector2d2.setLabelSide(getVectorLabelSide(vector2d2, double2));
        vector2d3.setLabelSide(getVectorLabelSide(vector2d3, double3));
    }
    
    public static int getVectorLabelSide(final Vector2d vector2d, final Point2D.Double double1) {
        int n = 0;
        final double angle = findAngle(vector2d.getTail2D(), double1);
        if ((angle - vector2d.getDirection() >= 3.141592653589793 && angle - vector2d.getDirection() < 6.283185307179586) || (angle - vector2d.getDirection() < 0.0 && angle - vector2d.getDirection() > -3.141592653589793)) {
            n = 1;
        }
        if (vector2d.getHead2D().y < vector2d.getTail2D().y || vector2d.getDirection() == 0.0) {
            if (n == 1) {
                n = 0;
            }
            else {
                n = 1;
            }
        }
        return n;
    }
}
