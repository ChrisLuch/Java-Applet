// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors.laf;

import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;

public class VectorUtilities
{
    private static final int BODY_IDX = 0;
    private static final int EDGE_IDX = 1;
    private static final int LINE_IDX = 2;
    private static final int LINE_EDGE_IDX = 3;
    private static final int SHORT_IDX = 4;
    private static final int SHORT_EDGE_IDX = 5;
    private int d;
    private int len;
    private Vector polygons;
    Color c1;
    Color c2;
    
    public VectorUtilities() {
        this.d = 2;
        this.polygons = new Vector();
        this.c1 = Color.black;
        this.c2 = Color.white;
    }
    
    public VectorUtilities(final int d) {
        this.d = 2;
        this.polygons = new Vector();
        this.c1 = Color.black;
        this.c2 = Color.white;
        this.d = d;
    }
    
    public VectorUtilities(final int d, final Color c1, final Color c2) {
        this.d = 2;
        this.polygons = new Vector();
        this.c1 = Color.black;
        this.c2 = Color.white;
        this.d = d;
        this.c1 = c1;
        this.c2 = c2;
    }
    
    public void drawDotLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final Color color) {
        this.drawDotLine(graphics, n, n2, n3, n4, n5, color);
    }
    
    public void drawDotLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final Color color) {
        final Color color2 = graphics.getColor();
        graphics.setColor(color);
        final double sqrt = Math.sqrt((n - n3) * (n - n3) + (n2 - n4) * (n2 - n4));
        final double n6 = (n == n3) ? 0.0 : ((n3 - n) / sqrt);
        final double n7 = (n2 == n4) ? 0.0 : ((n4 - n2) / sqrt);
        final double n8 = n + n5 * n6;
        final double n9 = n2 + n5 * n7;
        if (n5 >= 4) {
            for (int n10 = 0; (3 * n10 + 1) * n5 <= sqrt - n5; ++n10) {
                final double n11 = n + n5 * n6 + 3 * n10 * n5 * n6;
                final double n12 = n2 + n5 * n7 + 3 * n10 * n5 * n7;
                final int n13 = (int)Math.round(n11);
                final int n14 = (int)Math.round(n12);
                if (n5 == 1) {
                    graphics.drawString(".", n13, n14);
                }
                else {
                    final int n15 = (int)Math.round(n11) - (n5 / 2 + 1);
                    final int n16 = (int)Math.round(n12) - (n5 / 2 + 1);
                    graphics.fillOval(n15, n16 + 1, n5 + 1, n5 + 1);
                    graphics.fillOval(n15 + 1, n16, n5 + 1, n5 + 1);
                    graphics.fillOval(n15, n16, n5 + 1, n5 + 1);
                }
            }
        }
        if (n5 < 4) {
            for (int n17 = 0; (4 * n17 + 1) * n5 <= sqrt - n5; ++n17) {
                final double n18 = n + n5 * n6 + 4 * n17 * n5 * n6;
                final double n19 = n2 + n5 * n7 + 4 * n17 * n5 * n7;
                final int n20 = (int)Math.round(n18);
                final int n21 = (int)Math.round(n19);
                if (n5 == 1) {
                    graphics.drawString(".", n20, n21);
                }
                else {
                    final int n22 = (int)Math.round(n18) - (n5 / 2 + 1);
                    final int n23 = (int)Math.round(n19) - (n5 / 2 + 1);
                    graphics.fillOval(n22, n23 + 1, n5 + 1, n5 + 1);
                    graphics.fillOval(n22 + 1, n23, n5 + 1, n5 + 1);
                    graphics.fillOval(n22, n23, n5 + 1, n5 + 1);
                }
            }
        }
        graphics.setColor(color2);
    }
    
    public void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        this.drawLine(graphics, 2, n, n2, n3, n4, this.c1, this.c2);
    }
    
    public void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Color color, final Color color2) {
        final Vector polygons = this.getPolygons(n, n2, n3, n4, n5, n6);
        final Color color3 = graphics.getColor();
        graphics.setColor(color2);
        graphics.fillPolygon(this.getPolygons(n + 1, n2, n3, n4, n5, n6).elementAt(2));
        graphics.setColor(color);
        graphics.fillPolygon(polygons.elementAt(2));
        graphics.setColor(color3);
    }
    
    public void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final Color color, final Color color2) {
        this.drawLine(graphics, n, this.len, n2, n3, n4, n5, color, color2);
    }
    
    public void drawPerp(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final Color color) {
        final double sqrt = Math.sqrt((n3 - n5) * (n3 - n5) + (n4 - n6) * (n4 - n6));
        final int n9 = (int)(n5 + n2 * (n3 - n5) / sqrt);
        final int n10 = (int)(n6 + n2 * (n4 - n6) / sqrt);
        final double sqrt2 = Math.sqrt((n7 - n5) * (n7 - n5) + (n8 - n6) * (n8 - n6));
        final int n11 = (int)(n5 + n2 * (n7 - n5) / sqrt2);
        final int n12 = (int)(n6 + n2 * (n8 - n6) / sqrt2);
        final int n13 = (int)(n9 + n2 * (n7 - n5) / sqrt2);
        final int n14 = (int)(n10 + n2 * (n8 - n6) / sqrt2);
        graphics.setColor(color);
        graphics.drawLine(n13, n14, (int)(n13 + (n2 - n) * (n5 - n7) / sqrt2), (int)(n14 + (n2 - n) * (n6 - n8) / sqrt2));
        graphics.drawLine(n13, n14 + 1, (int)(n13 + (n2 - n) * (n5 - n7) / sqrt2), (int)(n14 + (n2 - n) * (n6 - n8) / sqrt2) + 1);
        graphics.drawLine(n13, n14, (int)(n13 + (n2 - n) * (n5 - n3) / sqrt), (int)(n14 + (n2 - n) * (n6 - n4) / sqrt));
        graphics.drawLine(n13, n14 + 1, (int)(n13 + (n2 - n) * (n5 - n3) / sqrt), (int)(n14 + (n2 - n) * (n6 - n4) / sqrt) + 1);
    }
    
    public void drawPerp(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final Color color) {
        final double sqrt = Math.sqrt((n2 - n4) * (n2 - n4) + (n3 - n5) * (n3 - n5));
        final int n8 = (int)(n4 + 2 * n * (n2 - n4) / sqrt);
        final int n9 = (int)(n5 + 2 * n * (n3 - n5) / sqrt);
        final double sqrt2 = Math.sqrt((n6 - n4) * (n6 - n4) + (n7 - n5) * (n7 - n5));
        final int n10 = (int)(n4 + 2 * n * (n6 - n4) / sqrt2);
        final int n11 = (int)(n5 + 2 * n * (n7 - n5) / sqrt2);
        final int n12 = (int)(n8 + 2 * n * (n6 - n4) / sqrt2);
        final int n13 = (int)(n9 + 2 * n * (n7 - n5) / sqrt2);
        graphics.setColor(color);
        graphics.drawLine(n12, n13, (int)(n12 + n * (n4 - n6) / sqrt2), (int)(n13 + n * (n5 - n7) / sqrt2));
        graphics.drawLine(n12, n13 + 1, (int)(n12 + n * (n4 - n6) / sqrt2), (int)(n13 + n * (n5 - n7) / sqrt2) + 1);
        graphics.drawLine(n12, n13, (int)(n12 + n * (n4 - n2) / sqrt), (int)(n13 + n * (n5 - n3) / sqrt));
        graphics.drawLine(n12, n13 + 1, (int)(n12 + n * (n4 - n2) / sqrt), (int)(n13 + n * (n5 - n3) / sqrt) + 1);
    }
    
    public void drawVector(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        this.drawVector(graphics, 2, n, n2, n3, n4);
    }
    
    public void drawVector(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.drawVector(graphics, n, n2, n3, n4, n5, this.c1, this.c2);
    }
    
    public void drawVector(final Graphics graphics, final int n, int max, final int n2, final int n3, final int n4, final int n5, final Color color, final Color color2) {
        max = Math.max(10, max);
        if (n != 0) {
            final Color color3 = graphics.getColor();
            final double atan2 = Math.atan2(n5 - n3, n4 - n2);
            final Vector polygons = this.getPolygons(n + 1, max, n2, n3, n4 + (int)(3.0 * Math.cos(atan2)), n5 + (int)(3.0 * Math.sin(atan2)));
            graphics.setColor(color2);
            graphics.fillPolygon(polygons.elementAt(0));
            final Vector polygons2 = this.getPolygons(n, max, n2, n3, n4, n5);
            graphics.setColor(color);
            graphics.fillPolygon(polygons2.elementAt(0));
            graphics.setColor(color3);
        }
        if (n == 0) {
            final Vector polygons3 = this.getPolygons(n, max, n2, n3, n4, n5);
            final Color color4 = graphics.getColor();
            graphics.setColor(color);
            graphics.fillPolygon(polygons3.elementAt(0));
            graphics.setColor(color4);
        }
    }
    
    public void drawVector(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final Color color, final Color color2) {
        this.drawVector(graphics, n, (n < 2) ? 15 : (n * 10), n2, n3, n4, n5, color, color2);
    }
    
    public Vector getPolygons(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final double atan2 = Math.atan2(n6 - n4, n5 - n3);
        final double n7 = 0.3141592653589793;
        final double a = n3 + n * Math.sin(atan2);
        final double a2 = n4 - n * Math.cos(atan2);
        final double a3 = n5 + n * Math.sin(atan2);
        final double a4 = n6 - n * Math.cos(atan2);
        final double a5 = n3 - n * Math.sin(atan2);
        final double a6 = n4 + n * Math.cos(atan2);
        final double a7 = n5 - n * Math.sin(atan2);
        final double a8 = n6 + n * Math.cos(atan2);
        final double a9 = n5 - n * (Math.cos(atan2) / Math.sin(n7));
        final double a10 = n6 - n * (Math.sin(atan2) / Math.sin(n7));
        final double a11 = a3 + Math.sin(atan2);
        final double a12 = a4 - Math.cos(atan2);
        final double a13 = a7 - Math.sin(atan2);
        final double a14 = a8 + Math.cos(atan2);
        final double a15 = a + Math.sin(atan2);
        final double a16 = a2 - Math.cos(atan2);
        final double a17 = a5 - Math.sin(atan2);
        final double a18 = a6 + Math.cos(atan2);
        final double a19 = n5 - n * (1.0 + Math.cos(n7)) * Math.cos(atan2) / Math.sin(n7);
        final double a20 = n6 - n * (1.0 + Math.cos(n7)) * Math.sin(atan2) / Math.sin(n7);
        final double a21 = a19 + n * Math.sin(atan2);
        final double a22 = a20 - n * Math.cos(atan2);
        final double a23 = a21 + Math.sin(atan2);
        final double a24 = a22 - Math.cos(atan2);
        final double a25 = a19 - n * Math.sin(atan2);
        final double a26 = a20 + n * Math.cos(atan2);
        final double a27 = a25 - Math.sin(atan2);
        final double a28 = a26 - Math.cos(atan2);
        final double a29 = n5 - n2 * Math.cos(atan2 + n7);
        final double a30 = n6 - n2 * Math.sin(atan2 + n7);
        final double n8 = a29 - n * Math.sin(atan2 + n7);
        final double n9 = a30 + n * Math.cos(atan2 + n7);
        final double a31 = a29;
        final double a32 = a30;
        final double a33 = a29 + Math.sin(atan2 + n7);
        final double a34 = a30 - Math.cos(atan2 + n7);
        final double a35 = n5 - n2 * Math.cos(atan2 - n7);
        final double a36 = n6 - n2 * Math.sin(atan2 - n7);
        final double n10 = a35 + n * Math.sin(atan2 - n7);
        final double n11 = a36 - n * Math.cos(atan2 - n7);
        final double a37 = a35;
        final double a38 = a36;
        final double a39 = a35 - Math.sin(atan2 - n7);
        final double a40 = a36 + Math.cos(atan2 - n7);
        final double a41 = n5 + Math.cos(atan2) / Math.sin(0.3141592653589793);
        final double a42 = n6 + Math.sin(atan2) / Math.sin(0.3141592653589793);
        final int n12 = (int)Math.floor(a);
        final int n13 = (int)Math.floor(a2);
        final int n14 = (int)Math.floor(a3);
        final int n15 = (int)Math.floor(a4);
        final int n16 = (int)Math.floor(a21);
        final int n17 = (int)Math.floor(a22);
        final int n18 = (int)Math.floor(a29);
        final int n19 = (int)Math.floor(a30);
        final int n20 = (int)Math.floor(a31);
        final int n21 = (int)Math.floor(a32);
        final int n22 = (int)Math.floor(a5);
        final int n23 = (int)Math.floor(a6);
        final int n24 = (int)Math.floor(a7);
        final int n25 = (int)Math.floor(a8);
        final int n26 = (int)Math.floor(a25);
        final int n27 = (int)Math.floor(a26);
        final int n28 = (int)Math.floor(a35);
        final int n29 = (int)Math.floor(a36);
        final int n30 = (int)Math.floor(a37);
        final int n31 = (int)Math.floor(a38);
        final int n32 = (int)Math.floor(a9);
        final int n33 = (int)Math.floor(a10);
        final int n34 = (int)Math.floor(a19);
        final int n35 = (int)Math.floor(a20);
        final int n36 = (int)Math.floor(a33);
        final int n37 = (int)Math.floor(a34);
        final int n38 = (int)Math.floor(a39);
        final int n39 = (int)Math.floor(a40);
        final int n40 = (int)Math.floor(a41);
        final int n41 = (int)Math.floor(a42);
        final int n42 = (int)Math.floor(a13);
        final int n43 = (int)Math.floor(a14);
        final int n44 = (int)Math.floor(a11);
        final int n45 = (int)Math.floor(a12);
        final int n46 = (int)Math.floor(a23);
        final int n47 = (int)Math.floor(a24);
        final int n48 = (int)Math.floor(a27);
        final int n49 = (int)Math.floor(a28);
        final int n50 = (int)Math.floor(a15);
        final int n51 = (int)Math.floor(a16);
        final int n52 = (int)Math.floor(a17);
        final int n53 = (int)Math.floor(a18);
        final Vector<Polygon> vector = new Vector<Polygon>();
        vector.addElement(new Polygon(new int[] { n12, n16, n20, n18, n5, n28, n30, n26, n22, n12 }, new int[] { n13, n17, n21, n19, n6, n29, n31, n27, n23, n13 }, 10));
        vector.addElement(new Polygon(new int[] { n50, n46, n20, n36, n40, n38, n30, n48, n52, n50 }, new int[] { n51, n47, n21, n37, n41, n39, n31, n49, n53, n51 }, 10));
        vector.addElement(new Polygon(new int[] { n12, n14, n24, n22, n12 }, new int[] { n13, n15, n25, n23, n13 }, 5));
        vector.addElement(new Polygon(new int[] { n50, n44, n42, n52, n50 }, new int[] { n51, n45, n43, n53, n51 }, 5));
        vector.addElement(new Polygon(new int[] { n12, n16, n26, n22, n12 }, new int[] { n13, n17, n27, n23, n13 }, 5));
        vector.addElement(new Polygon(new int[] { n50, n46, n48, n52, n50 }, new int[] { n51, n47, n49, n53, n51 }, 5));
        return vector;
    }
    
    public void slowDrawDotLine(final Graphics graphics, final double n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final Color color) {
        this.slowDrawDotLine(graphics, n, n2, n3, n4, n5, n6, color);
    }
    
    public void slowDrawDotLine(final Graphics graphics, final double n, final int n2, final int n3, int n4, int n5, final int n6, final Color color) {
        n4 = (int)(n2 + n * (n4 - n2));
        n5 = (int)(n3 + n * (n5 - n3));
        if (n != 1.0) {
            this.drawDotLine(graphics, n2, n3, n4, n5, n6, color);
        }
        if (n == 1.0) {
            this.drawDotLine(graphics, n2, n3, n4, n5, n6, color);
        }
    }
    
    public void slowDrawLine(final Graphics graphics, final double n, final int n2, final int n3, final int n4, final int n5) {
        this.slowDrawLine(graphics, 2, n, n2, n3, n4, n5, this.c1, this.c2);
    }
    
    public void slowDrawLine(final Graphics graphics, final int n, final double n2, final int n3, final int n4, final int n5, final int n6, final Color color, final Color color2) {
        final Vector polygons = this.getPolygons(n, this.len, n3, n4, n5, n6);
        final Polygon polygon = polygons.elementAt(2);
        final Polygon polygon2 = polygons.elementAt(3);
        final int[] xpoints = polygon.xpoints;
        final int[] ypoints = polygon.ypoints;
        final int n7 = xpoints[0];
        final int n8 = xpoints[1];
        final int n9 = xpoints[2];
        final int n10 = xpoints[3];
        final int n11 = ypoints[0];
        final int n12 = ypoints[1];
        final int n13 = ypoints[2];
        final int n14 = ypoints[3];
        final int n15 = (int)(n7 + n2 * (n8 - n7));
        final int n16 = (int)(n11 + n2 * (n12 - n11));
        final int n17 = (int)(n10 + n2 * (n9 - n10));
        final int n18 = (int)(n14 + n2 * (n13 - n14));
        final int[] array = { n17, n10, n7, n15 };
        final int[] array2 = { n18, n14, n11, n16 };
        graphics.setColor(color);
        graphics.fillPolygon(array, array2, 4);
        if (n2 == 1.0) {
            this.drawLine(graphics, n, n3, n4, n5, n6, color, color2);
        }
    }
    
    public void slowDrawVector(final Graphics graphics, final double n, final int n2, final int n3, final int n4, final int n5) {
        this.slowDrawVector(graphics, 2, 20, n, n2, n3, n4, n5, this.c1, this.c2);
    }
    
    public void slowDrawVector(final Graphics graphics, final int n, final double n2, final int n3, final int n4, final int n5, final int n6, final Color color, final Color color2) {
        this.slowDrawVector(graphics, n, (n == 0) ? 10 : (10 * n), n2, n3, n4, n5, n6, color, color2);
    }
    
    public void slowDrawVector(final Graphics graphics, final int n, final int n2, final double n3, final int n4, final int n5, final int n6, final int n7, final Color color, final Color color2) {
        final Vector polygons = this.getPolygons(n, n2, n4, n5, n6, n7);
        final Polygon polygon = polygons.elementAt(4);
        final Polygon polygon2 = polygons.elementAt(5);
        final int[] xpoints = polygon.xpoints;
        final int[] ypoints = polygon.ypoints;
        final int n8 = xpoints[0];
        final int n9 = xpoints[1];
        final int n10 = xpoints[2];
        final int n11 = xpoints[3];
        final int n12 = ypoints[0];
        final int n13 = ypoints[1];
        final int n14 = ypoints[2];
        final int n15 = ypoints[3];
        final int n16 = (int)(n8 + n3 * (n9 - n8));
        final int n17 = (int)(n12 + n3 * (n13 - n12));
        final int n18 = (int)(n11 + n3 * (n10 - n11));
        final int n19 = (int)(n15 + n3 * (n14 - n15));
        final int[] xpoints2 = polygon2.xpoints;
        final int[] ypoints2 = polygon2.ypoints;
        final int n20 = xpoints2[0];
        final int n21 = xpoints2[1];
        final int n22 = xpoints2[2];
        final int n23 = xpoints2[3];
        final int n24 = ypoints2[0];
        final int n25 = ypoints2[1];
        final int n26 = ypoints2[2];
        final int n27 = ypoints2[3];
        final int n28 = (int)(n20 + n3 * (n21 - n8));
        final int n29 = (int)(n24 + n3 * (n25 - n12));
        final int n30 = (int)(n23 + n3 * (n22 - n8));
        final int n31 = (int)(n27 + n3 * (n26 - n12));
        final int[] array = { n18, n11, n8, n16 };
        final int[] array2 = { n19, n15, n12, n17 };
        graphics.setColor(color);
        graphics.fillPolygon(array, array2, 4);
        if (n3 == 1.0) {
            this.drawVector(graphics, n, n2, n4, n5, n6, n7, color, color2);
        }
    }
}
