// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.geom.IllegalPathStateException;
import java.awt.FontMetrics;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.Vector;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.GeneralPath;

public class Graph
{
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 1;
    public static final int BOTTOM = 0;
    private GeneralPath path;
    private Point2D.Double location;
    private Point2D.Double origin;
    private Font xLabelFont;
    private Font yLabelFont;
    private Vector xLabel;
    private Vector yLabel;
    public String xAxisLabel;
    public String yAxisLabel;
    private Line2D.Double xAxis;
    private Line2D.Double yAxis;
    public Color xAxisColor;
    public Color yAxisColor;
    public Color xLabelColor;
    public Color yLabelColor;
    public Color gridColor;
    public Color tickColor;
    public Color pathColor;
    private BasicStroke stroke;
    private DecimalFormat decimalFormat;
    private double xMax;
    private double xMin;
    private double yMax;
    private double yMin;
    private double xScl;
    private double yScl;
    private double width;
    private double height;
    private double xMajorTickSpacing;
    private double xMinorTickSpacing;
    private double yMajorTickSpacing;
    private double yMinorTickSpacing;
    private double scaleX;
    private double scaleY;
    public boolean drawXAxis;
    public boolean drawYAxis;
    public boolean drawGrid;
    public boolean drawXTicks;
    public boolean drawYTicks;
    public boolean drawPath;
    public boolean drawXLabel;
    public boolean drawYLabel;
    
    public Graph() {
        this(5.0, -5.0, 5.0, -5.0, 50.0, 50.0);
    }
    
    public Graph(final double xMax, final double xMin, final double yMax, final double yMin, final double n, final double n2) {
        this.path = new GeneralPath();
        this.location = new Point2D.Double();
        this.origin = new Point2D.Double();
        this.xLabelFont = new Font("Dialog", 0, 12);
        this.yLabelFont = new Font("Dialog", 0, 12);
        this.xLabel = new Vector();
        this.yLabel = new Vector();
        this.xAxisLabel = "X";
        this.yAxisLabel = "Y";
        this.xAxis = new Line2D.Double();
        this.yAxis = new Line2D.Double();
        this.xAxisColor = Color.black;
        this.yAxisColor = Color.black;
        this.xLabelColor = Color.blue;
        this.yLabelColor = Color.blue;
        this.gridColor = Color.gray;
        this.tickColor = Color.black;
        this.pathColor = Color.red;
        this.stroke = new BasicStroke();
        this.decimalFormat = new DecimalFormat("0.0;-0.0");
        this.scaleX = 1.0;
        this.scaleY = 1.0;
        this.setXMax(xMax);
        this.setXMin(xMin);
        this.setYMax(yMax);
        this.setYMin(yMin);
        this.setXScl(n);
        this.setYScl(n2);
        this.drawXAxis = true;
        this.drawYAxis = true;
        this.drawGrid = true;
        this.drawXTicks = true;
        this.drawYTicks = true;
        this.drawPath = true;
        this.drawXLabel = true;
        this.drawYLabel = true;
        this.xMajorTickSpacing = n;
        this.xMinorTickSpacing = n / 2.0;
        this.yMajorTickSpacing = n2;
        this.yMinorTickSpacing = n2 / 2.0;
        this.width = this.getWidth();
        this.height = this.getHeight();
        this.path.reset();
        this.path.moveTo(0.0f, 0.0f);
    }
    
    public void setGraph(final double xMax, final double xMin, final double yMax, final double yMin) {
        this.setXMax(xMax);
        this.setXMin(xMin);
        this.setYMax(yMax);
        this.setYMin(yMin);
    }
    
    public void setGraph(final double xMax, final double xMin, final double yMax, final double yMin, final double xScl, final double yScl) {
        this.setXMax(xMax);
        this.setXMin(xMin);
        this.setYMax(yMax);
        this.setYMin(yMin);
        this.setXScl(xScl);
        this.setYScl(yScl);
    }
    
    public void scale(final double scaleX, final double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    
    public void scale(final double n) {
        this.scaleX = n / 100.0;
        this.scaleY = n / 100.0;
    }
    
    public void zoomIn(final double n) {
        this.scaleX += n / 100.0;
        this.scaleY += n / 100.0;
    }
    
    public void zoomOut(final double n) {
        this.scaleX -= n / 100.0;
        this.scaleY -= n / 100.0;
    }
    
    public void paint(final Graphics graphics) {
        this.paint((Graphics2D)graphics);
    }
    
    public void paint(final Graphics2D graphics2D) {
        final double n = this.xMin * this.xScl;
        final double n2 = this.xMax * this.xScl;
        final double n3 = this.yMin * this.yScl;
        final double n4 = this.yMax * this.yScl;
        final Color color = graphics2D.getColor();
        final Stroke stroke = graphics2D.getStroke();
        final Shape clip = graphics2D.getClip();
        graphics2D.setStroke(this.stroke);
        graphics2D.scale(this.scaleX, this.scaleY);
        graphics2D.translate(this.location.x, this.location.y);
        if (this.drawGrid) {
            graphics2D.setColor(this.gridColor);
            final double y1 = this.yAxis.y1;
            final double y2 = this.yAxis.y2;
            final double x1 = this.xAxis.x1;
            final double x2 = this.xAxis.x2;
            graphics2D.translate(0.0, this.origin.y);
            for (double yMajorTickSpacing = this.yMajorTickSpacing; yMajorTickSpacing <= n4; yMajorTickSpacing += this.yMajorTickSpacing) {
                if (yMajorTickSpacing >= n3) {
                    graphics2D.drawLine(toInt(x1), toInt(-yMajorTickSpacing), toInt(x2), toInt(-yMajorTickSpacing));
                }
            }
            for (double n5 = -this.yMajorTickSpacing; n5 >= n3; n5 -= this.yMajorTickSpacing) {
                if (n5 <= n4) {
                    graphics2D.drawLine(toInt(x1), toInt(-n5), toInt(x2), toInt(-n5));
                }
            }
            graphics2D.translate(0.0, -this.origin.y);
            graphics2D.translate(this.origin.x, 0.0);
            for (double xMajorTickSpacing = this.xMajorTickSpacing; xMajorTickSpacing <= n2; xMajorTickSpacing += this.xMajorTickSpacing) {
                if (xMajorTickSpacing >= n) {
                    graphics2D.drawLine(toInt(xMajorTickSpacing), toInt(y1), toInt(xMajorTickSpacing), toInt(y2));
                }
            }
            for (double n6 = -this.xMajorTickSpacing; n6 >= n; n6 -= this.xMajorTickSpacing) {
                if (n6 <= n2) {
                    graphics2D.drawLine(toInt(n6), toInt(y1), toInt(n6), toInt(y2));
                }
            }
            graphics2D.translate(-this.origin.x, 0.0);
        }
        if (this.drawXAxis) {
            graphics2D.setFont(this.xLabelFont);
            final FontMetrics fontMetrics = graphics2D.getFontMetrics();
            graphics2D.setColor(this.xAxisColor);
            graphics2D.draw(this.xAxis);
            graphics2D.drawString(this.xAxisLabel, toInt(this.xAxis.x2 + 5.0), toInt(this.xAxis.y1 + fontMetrics.getMaxAscent() / 2));
            if (this.drawXTicks) {
                final double y3 = this.xAxis.y1;
                graphics2D.translate(this.origin.x, 0.0);
                for (double xMinorTickSpacing = this.xMinorTickSpacing; xMinorTickSpacing <= n2; xMinorTickSpacing += this.xMinorTickSpacing) {
                    if (xMinorTickSpacing >= n) {
                        graphics2D.drawLine(toInt(xMinorTickSpacing), toInt(y3 + 2.0), toInt(xMinorTickSpacing), toInt(y3 - 2.0));
                    }
                }
                for (double n7 = -this.xMinorTickSpacing; n7 >= n; n7 -= this.xMinorTickSpacing) {
                    if (n7 <= n2) {
                        graphics2D.drawLine(toInt(n7), toInt(y3 + 2.0), toInt(n7), toInt(y3 - 2.0));
                    }
                }
                for (double xMajorTickSpacing2 = this.xMajorTickSpacing; xMajorTickSpacing2 <= n2; xMajorTickSpacing2 += this.xMajorTickSpacing) {
                    if (xMajorTickSpacing2 >= n) {
                        graphics2D.setColor(this.xAxisColor);
                        graphics2D.drawLine(toInt(xMajorTickSpacing2), toInt(y3 + 4.0), toInt(xMajorTickSpacing2), toInt(y3 - 4.0));
                        if (this.drawXLabel) {
                            final String format = this.decimalFormat.format(xMajorTickSpacing2 / this.xScl);
                            graphics2D.setColor(this.xLabelColor);
                            if (xMajorTickSpacing2 != n && xMajorTickSpacing2 != 0.0) {
                                graphics2D.drawString(format, toInt(xMajorTickSpacing2 - fontMetrics.stringWidth(format) / 2.0), toInt(y3 + (fontMetrics.getMaxAscent() + 5)));
                            }
                        }
                    }
                }
                for (double n8 = -this.xMajorTickSpacing; n8 >= n; n8 -= this.xMajorTickSpacing) {
                    if (n8 <= n2) {
                        graphics2D.setColor(this.xAxisColor);
                        graphics2D.drawLine(toInt(n8), toInt(y3 + 4.0), toInt(n8), toInt(y3 - 4.0));
                        if (this.drawXLabel) {
                            final String format2 = this.decimalFormat.format(n8 / this.xScl);
                            graphics2D.setColor(this.xLabelColor);
                            if (n8 != n2 && n8 != 0.0) {
                                graphics2D.drawString(format2, toInt(n8 - fontMetrics.stringWidth(format2) / 2.0), toInt(y3 + (fontMetrics.getMaxAscent() + 5)));
                            }
                        }
                    }
                }
                graphics2D.translate(-this.origin.x, 0.0);
            }
        }
        if (this.drawYAxis) {
            graphics2D.setFont(this.yLabelFont);
            final FontMetrics fontMetrics2 = graphics2D.getFontMetrics();
            graphics2D.setColor(this.yAxisColor);
            graphics2D.draw(this.yAxis);
            graphics2D.drawString(this.yAxisLabel, toInt(this.yAxis.x1 - fontMetrics2.stringWidth(this.yAxisLabel) / 2), toInt(this.yAxis.y2 - (fontMetrics2.getMaxDescent() + 5)));
            if (this.drawYTicks) {
                final double x3 = this.yAxis.x1;
                graphics2D.translate(0.0, this.origin.y);
                for (double yMinorTickSpacing = this.yMinorTickSpacing; yMinorTickSpacing <= n4; yMinorTickSpacing += this.yMinorTickSpacing) {
                    if (yMinorTickSpacing >= n3) {
                        graphics2D.drawLine(toInt(x3 + 2.0), toInt(-yMinorTickSpacing), toInt(x3 - 2.0), toInt(-yMinorTickSpacing));
                    }
                }
                for (double n9 = -this.yMinorTickSpacing; n9 >= n3; n9 -= this.yMinorTickSpacing) {
                    if (n9 <= n4) {
                        graphics2D.drawLine(toInt(x3 + 2.0), toInt(-n9), toInt(x3 - 2.0), toInt(-n9));
                    }
                }
                for (double yMajorTickSpacing2 = this.yMajorTickSpacing; yMajorTickSpacing2 <= n4; yMajorTickSpacing2 += this.yMajorTickSpacing) {
                    if (yMajorTickSpacing2 >= n3) {
                        graphics2D.setColor(this.yAxisColor);
                        graphics2D.drawLine(toInt(x3 + 4.0), toInt(-yMajorTickSpacing2), toInt(x3 - 4.0), toInt(-yMajorTickSpacing2));
                        if (this.drawYLabel) {
                            final String format3 = this.decimalFormat.format(yMajorTickSpacing2 / this.yScl);
                            graphics2D.setColor(this.yLabelColor);
                            if (yMajorTickSpacing2 != n3 && yMajorTickSpacing2 != 0.0) {
                                graphics2D.drawString(format3, toInt(x3 + 10.0), toInt(-yMajorTickSpacing2 + fontMetrics2.getMaxAscent() / 2));
                            }
                        }
                    }
                }
                for (double n10 = -this.yMajorTickSpacing; n10 >= n3; n10 -= this.yMajorTickSpacing) {
                    if (n10 <= n4) {
                        graphics2D.setColor(this.yAxisColor);
                        graphics2D.drawLine(toInt(x3 + 4.0), toInt(-n10), toInt(x3 - 4.0), toInt(-n10));
                        if (this.drawYLabel) {
                            final String format4 = this.decimalFormat.format(n10 / this.yScl);
                            graphics2D.setColor(this.yLabelColor);
                            if (n10 != n4 && n10 != 0.0) {
                                graphics2D.drawString(format4, toInt(x3 + 10.0), toInt(-n10 + fontMetrics2.getMaxAscent() / 2));
                            }
                        }
                    }
                }
                graphics2D.translate(0.0, -this.origin.y);
            }
        }
        if (this.drawPath) {
            graphics2D.setColor(this.pathColor);
            graphics2D.clip(this.getBounds());
            graphics2D.translate(this.origin.x, this.origin.y);
            graphics2D.draw(this.path);
            graphics2D.translate(-this.origin.x, -this.origin.y);
            graphics2D.setClip(clip);
        }
        graphics2D.scale(1.0 / this.scaleX, 1.0 / this.scaleY);
        graphics2D.translate(-this.location.x, -this.location.y);
        graphics2D.setColor(color);
        graphics2D.setStroke(stroke);
        graphics2D.setClip(clip);
    }
    
    public void initilizePath(final double n, final double n2) {
        this.path.moveTo((float)(n * this.xScl), (float)(-n2 * this.yScl));
    }
    
    public void resetPath() {
        this.path.reset();
    }
    
    public void reset() {
        this.path.reset();
    }
    
    public void plot(final double n, final double n2) {
        try {
            this.path.lineTo((float)(n * this.xScl), (float)(-n2 * this.yScl));
            this.path.moveTo((float)(n * this.xScl), (float)(-n2 * this.yScl));
        }
        catch (IllegalPathStateException ex) {
            this.path.moveTo((float)(n * this.xScl), (float)(-n2 * this.yScl));
        }
        this.path.closePath();
    }
    
    public boolean contains(final double n, final double n2) {
        return n >= this.location.x && n - this.location.x < this.getWidth() && n2 >= this.location.y && n2 - this.location.y < this.getHeight();
    }
    
    public Dimension getDimension() {
        return new Dimension(this.getWidth(), this.getHeight());
    }
    
    public double getXMax() {
        return this.xMax;
    }
    
    public void setXMax(final double xMax) {
        this.xMax = xMax;
        this.setAxis();
    }
    
    public double getXMin() {
        return this.xMin;
    }
    
    public void setXMin(final double xMin) {
        this.xMin = xMin;
        this.setAxis();
    }
    
    public double getYMax() {
        return this.yMax;
    }
    
    public void setYMax(final double yMax) {
        this.yMax = yMax;
        this.setAxis();
    }
    
    public double getYMin() {
        return this.yMin;
    }
    
    public void setYMin(final double yMin) {
        this.yMin = yMin;
        this.setAxis();
    }
    
    public double getXScl() {
        return this.xScl;
    }
    
    public void setXScl(final double a) {
        this.xScl = Math.abs(a);
        this.setAxis();
    }
    
    public double getYScl() {
        return this.yScl;
    }
    
    public void setYScl(final double a) {
        this.yScl = Math.abs(a);
        this.setAxis();
    }
    
    public int getWidth() {
        return toInt(this.xScl * (this.xMax - this.xMin));
    }
    
    public int getHeight() {
        return toInt(this.yScl * (this.yMax - this.yMin));
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.getWidth(), this.getHeight());
    }
    
    public static int toInt(final double n) {
        return (n < 0.0) ? ((int)(n - 0.5)) : ((int)(n + 0.5));
    }
    
    public void setAxis() {
        final double n = this.xMin * this.xScl;
        final double n2 = this.xMax * this.xScl;
        final double n3 = this.yMin * this.yScl;
        final double y = this.yMax * this.yScl;
        this.origin.x = -n;
        this.origin.y = y;
        this.xAxis.x1 = this.origin.x + n;
        final Line2D.Double xAxis = this.xAxis;
        final Line2D.Double xAxis2 = this.xAxis;
        final double y2 = this.origin.y;
        xAxis2.y2 = y2;
        xAxis.y1 = y2;
        this.xAxis.x2 = this.origin.x + n2;
        this.yAxis.y1 = this.origin.y + -n3;
        final Line2D.Double yAxis = this.yAxis;
        final Line2D.Double yAxis2 = this.yAxis;
        final double x = this.origin.x;
        yAxis2.x2 = x;
        yAxis.x1 = x;
        this.yAxis.y2 = this.origin.y + -y;
        if (n > 0.0) {
            final Line2D.Double yAxis3 = this.yAxis;
            final Line2D.Double yAxis4 = this.yAxis;
            final double n4 = this.origin.x + n;
            yAxis4.x2 = n4;
            yAxis3.x1 = n4;
        }
        if (n2 < 0.0) {
            final Line2D.Double yAxis5 = this.yAxis;
            final Line2D.Double yAxis6 = this.yAxis;
            final double n5 = this.origin.x + n2;
            yAxis6.x2 = n5;
            yAxis5.x1 = n5;
        }
        if (n3 > 0.0) {
            final Line2D.Double xAxis3 = this.xAxis;
            final Line2D.Double xAxis4 = this.xAxis;
            final double n6 = this.origin.y + -n3;
            xAxis4.y2 = n6;
            xAxis3.y1 = n6;
        }
        if (y < 0.0) {
            final Line2D.Double xAxis5 = this.xAxis;
            final Line2D.Double xAxis6 = this.xAxis;
            final double n7 = this.origin.y + -y;
            xAxis6.y2 = n7;
            xAxis5.y1 = n7;
        }
    }
    
    public Point2D.Double getLocation() {
        return (Point2D.Double)this.location.clone();
    }
    
    public void setLocation(final Point point) {
        this.setLocation(point.x, point.y);
    }
    
    public void setLocation(final Point2D.Double double1) {
        this.setLocation(double1.x, double1.y);
    }
    
    public void setLocation(final int n, final int n2) {
        this.location.x = n;
        this.location.y = n2;
    }
    
    public void setLocation(final double x, final double y) {
        this.location.x = x;
        this.location.y = y;
    }
    
    public void translate(final Point point) {
        this.translate(point.x, point.y);
    }
    
    public void translate(final Point2D.Double double1) {
        this.translate(double1.x, double1.y);
    }
    
    public void translate(final double n, final double n2) {
        final Point2D.Double location = this.location;
        location.x += n;
        final Point2D.Double location2 = this.location;
        location2.y += n2;
    }
    
    public boolean getDrawGrid() {
        return this.drawGrid;
    }
    
    public void setDrawGrid(final boolean drawGrid) {
        this.drawGrid = drawGrid;
    }
    
    public boolean getDrawPath() {
        return this.drawPath;
    }
    
    public void setDrawPath(final boolean drawPath) {
        this.drawPath = drawPath;
    }
    
    public boolean getDrawXAxis() {
        return this.drawXAxis;
    }
    
    public void setDrawXAxis(final boolean drawXAxis) {
        this.drawXAxis = drawXAxis;
    }
    
    public boolean getDrawXLabel() {
        return this.drawXLabel;
    }
    
    public void setDrawXLabel(final boolean drawXLabel) {
        this.drawXLabel = drawXLabel;
    }
    
    public boolean getDrawXTicks() {
        return this.drawXTicks;
    }
    
    public void setDrawXTicks(final boolean drawXTicks) {
        this.drawXTicks = drawXTicks;
    }
    
    public boolean getDrawYAxis() {
        return this.drawYAxis;
    }
    
    public void setDrawYAxis(final boolean drawYAxis) {
        this.drawYAxis = drawYAxis;
    }
    
    public boolean getDrawYLabel() {
        return this.drawYLabel;
    }
    
    public void setDrawYLabel(final boolean drawYLabel) {
        this.drawYLabel = drawYLabel;
    }
    
    public boolean getDrawYTicks() {
        return this.drawYTicks;
    }
    
    public void setDrawYTicks(final boolean drawYTicks) {
        this.drawYTicks = drawYTicks;
    }
    
    public String getXAxisLabel() {
        return this.xAxisLabel;
    }
    
    public void setXAxisLabel(final String xAxisLabel) {
        this.xAxisLabel = xAxisLabel;
    }
    
    public String getYAxisLabel() {
        return this.yAxisLabel;
    }
    
    public void setYAxisLabel(final String yAxisLabel) {
        this.yAxisLabel = yAxisLabel;
    }
    
    public double getYMajorTickSpacing() {
        return this.yMajorTickSpacing / this.yScl;
    }
    
    public void setYMajorTickSpacing(final double n) {
        this.yMajorTickSpacing = n * this.yScl;
    }
    
    public double getYMinorTickSpacing() {
        return this.yMinorTickSpacing / this.yScl;
    }
    
    public void setYMinorTickSpacing(final double n) {
        this.yMinorTickSpacing = n * this.yScl;
    }
    
    public double getXMajorTickSpacing() {
        return this.xMajorTickSpacing / this.xScl;
    }
    
    public void setXMajorTickSpacing(final double n) {
        this.xMajorTickSpacing = n * this.xScl;
    }
    
    public double getXMinorTickSpacing() {
        return this.xMinorTickSpacing / this.xScl;
    }
    
    public void setXMinorTickSpacing(final double n) {
        this.xMinorTickSpacing = n * this.xScl;
    }
    
    public DecimalFormat getDecimalFormat() {
        return this.decimalFormat;
    }
    
    public void setDecimalFormat(final DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }
}
