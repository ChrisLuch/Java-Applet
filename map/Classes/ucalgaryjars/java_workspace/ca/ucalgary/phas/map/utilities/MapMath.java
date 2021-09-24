// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import ca.ucalgary.phas.map.utilities.vectors.VectorUtil;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;
import java.awt.geom.Point2D;

public final class MapMath
{
    public static double distance(final Point2D.Double double1, final Point2D.Double double2) {
        return distance(double1.x, double1.y, double2.x, double2.y);
    }
    
    public static double distance(final double n, final double n2, final double n3, final double n4) {
        return Math.sqrt(sqr(n - n3) + sqr(n2 - n4));
    }
    
    public static double findAngle(final Point2D.Double double1, final Point2D.Double double2, final Point2D.Double double3) {
        double angle = findAngle(double1, double2);
        double angle2 = findAngle(double1, double3);
        if (angle > 3.141592653589793) {
            angle -= 6.283185307179586;
        }
        if (angle2 > 3.141592653589793) {
            angle2 -= 6.283185307179586;
        }
        double n = angle2 - angle;
        if (n > 3.141592653589793) {
            n -= 6.283185307179586;
        }
        if (n < -3.141592653589793) {
            n += 6.283185307179586;
        }
        return n;
    }
    
    public static double findPosAngle(final Point2D.Double double1, final Point2D.Double double2, final Point2D.Double double3) {
        double n = findAngle(double1, double3) - findAngle(double1, double2);
        if (n < 0.0) {
            n += 6.283185307179586;
        }
        return n;
    }
    
    public static double findNegAngle(final Point2D.Double double1, final Point2D.Double double2, final Point2D.Double double3) {
        double n = findAngle(double1, double3) - 6.283185307179586 - (findAngle(double1, double2) - 6.283185307179586);
        if (n > 0.0) {
            n -= 6.283185307179586;
        }
        return n;
    }
    
    public static int toInt(final double n) {
        return (n < 0.0) ? ((int)(n - 0.5)) : ((int)(n + 0.5));
    }
    
    public static double findAngle(final Point2D.Double double1, final Point2D.Double double2) {
        double atan2 = Math.atan2(Math.abs(double2.y - double1.y), Math.abs(double2.x - double1.x));
        if (double2.x < double1.x) {
            if (double2.y > double1.y) {
                atan2 += 3.141592653589793;
            }
            else {
                atan2 = 3.141592653589793 - atan2;
            }
        }
        else if (double2.y > double1.y) {
            atan2 = 6.283185307179586 - atan2;
        }
        return atan2;
    }
    
    public static double sqr(final double n) {
        return n * n;
    }
    
    public static void setVectorLabels(final Vector2d vector2d, final Vector2d vector2d2, final Vector2d vector2d3) {
        final Point2D.Double head2D = vector2d3.getHead2D();
        final Point2D.Double tail2D = vector2d.getTail2D();
        final Point2D.Double head2D2 = vector2d.getHead2D();
        vector2d.setLabelSide(getVectorLabelSide(vector2d, head2D));
        vector2d2.setLabelSide(getVectorLabelSide(vector2d2, tail2D));
        vector2d3.setLabelSide(getVectorLabelSide(vector2d3, head2D2));
    }
    
    public static int getVectorLabelSide(final Vector2d vector2d, final Point2D.Double double1) {
        int n = 0;
        final double angle = VectorUtil.findAngle(vector2d.getTail2D(), double1);
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
