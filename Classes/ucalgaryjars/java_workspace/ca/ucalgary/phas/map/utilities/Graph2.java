// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.geom.IllegalPathStateException;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.text.DecimalFormat;
import java.awt.geom.GeneralPath;
import java.awt.Dimension;
import java.util.Iterator;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Enumeration;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.Vector;
import java.awt.geom.Point2D;
import javax.swing.SwingConstants;

public class Graph2 implements SwingConstants
{
    protected double xMax;
    protected double xMin;
    protected double xScl;
    protected double yMax;
    protected double yMin;
    protected double yScl;
    protected double xMinorTickSpacing;
    protected double xMajorTickSpacing;
    protected double yMinorTickSpacing;
    protected double yMajorTickSpacing;
    protected Point2D.Double location;
    protected Point2D.Double origin;
    protected Vector paths;
    protected Line2D.Double xAxis;
    protected Line2D.Double yAxis;
    public Color gridColor;
    public Color xAxisColor;
    public Color xAxisLabelColor;
    public Color xAxisTickColor;
    public Color yAxisColor;
    public Color yAxisLabelColor;
    public Color yAxisTickColor;
    public String xAxisLabel;
    public String yAxisLabel;
    public Font xLabelFont;
    public Font yLabelFont;
    protected FontMetrics fm;
    public boolean paintAxis;
    public boolean paintXAxis;
    public boolean paintYAxis;
    public boolean paintGrid;
    public boolean paintXAxisTicks;
    public boolean paintYAxisTicks;
    public boolean paintXAxisLabels;
    public boolean paintYAxisLabels;
    public boolean paintXAxisLabel;
    public boolean paintYAxisLabel;
    public boolean visible;
    public boolean moveable;
    public int yLabelDistance;
    public boolean efficient;
    Enumeration tempPathEnum;
    Shape tempPathClip;
    Rectangle clip;
    Rectangle labelClip;
    
    public void setYLabelDistance(final int yLabelDistance) {
        this.yLabelDistance = yLabelDistance;
    }
    
    public Graph2() {
        this(-5.0, 5.0, 25.0, -5.0, 5.0, 25.0);
    }
    
    public Graph2(final double xMin, final double xMax, final double xScl, final double yMin, final double yMax, final double yScl) {
        this.location = new Point2D.Double();
        this.origin = new Point2D.Double();
        this.paths = new Vector();
        this.xAxis = new Line2D.Double();
        this.yAxis = new Line2D.Double();
        this.gridColor = Color.gray;
        this.xAxisColor = Color.black;
        this.xAxisLabelColor = Color.black;
        this.xAxisTickColor = Color.black;
        this.yAxisColor = Color.black;
        this.yAxisLabelColor = Color.black;
        this.yAxisTickColor = Color.black;
        this.xAxisLabel = "X";
        this.yAxisLabel = "Y";
        this.xLabelFont = new Font("Dialog", 0, 12);
        this.yLabelFont = new Font("Dialog", 0, 12);
        this.yLabelDistance = 10;
        this.efficient = false;
        this.clip = new Rectangle(0, 0, 0, 0);
        this.labelClip = new Rectangle();
        this.setXMin(xMin);
        this.setXMax(xMax);
        this.setXScl(xScl);
        this.setYMax(yMax);
        this.setYMin(yMin);
        this.setYScl(yScl);
        this.paintAxis = true;
        this.paintXAxis = true;
        this.paintYAxis = true;
        this.paintGrid = true;
        this.paintXAxisTicks = true;
        this.paintYAxisTicks = true;
        this.paintXAxisLabel = true;
        this.paintYAxisLabel = true;
        this.paintXAxisLabels = true;
        this.paintYAxisLabels = true;
        this.visible = true;
        this.moveable = false;
        this.xMajorTickSpacing = 1.0;
        this.xMinorTickSpacing = 0.5;
        this.yMajorTickSpacing = 1.0;
        this.yMinorTickSpacing = 0.5;
    }
    
    public void setGraph(final double xMin, final double xMax, final double xScl, final double yMin, final double yMax, final double yScl) {
        this.setXMin(xMin);
        this.setXMax(xMax);
        this.setXScl(xScl);
        this.setYMax(yMax);
        this.setYMin(yMin);
        this.setYScl(yScl);
    }
    
    public boolean contains(final Point point) {
        return this.contains(point.x, point.y);
    }
    
    public boolean contains(final Point2D.Double double1) {
        return this.contains(double1.x, double1.y);
    }
    
    public boolean contains(final double n, final double n2) {
        return n >= this.location.x && n - this.location.x < this.getWidth() && n2 >= this.location.y && n2 - this.location.y < this.getHeight();
    }
    
    public void paint(final Graphics graphics) {
        this.paint((Graphics2D)graphics);
    }
    
    public void paint(final Graphics2D graphics2D) {
        if (this.visible) {
            final double x = this.location.x;
            final double y = this.location.y;
            graphics2D.translate(x, y);
            if (this.paintGrid) {
                this.paintGrid(graphics2D);
            }
            if (this.paintAxis) {
                this.paintAxis(graphics2D);
            }
            this.paintPaths(graphics2D);
            graphics2D.translate(-x, -y);
        }
    }
    
    protected void paintGrid(final Graphics2D graphics2D) {
        final Color color = graphics2D.getColor();
        final double n = this.yMajorTickSpacing * this.yScl;
        final double n2 = this.xMajorTickSpacing * this.xScl;
        final double n3 = this.xMin * this.xScl;
        final double n4 = this.xMax * this.xScl;
        final double n5 = this.yMin * this.yScl;
        final double n6 = this.yMax * this.yScl;
        graphics2D.setColor(this.gridColor);
        final double y1 = this.yAxis.y1;
        final double y2 = this.yAxis.y2;
        final double x1 = this.xAxis.x1;
        final double x2 = this.xAxis.x2;
        final double x3 = this.origin.x;
        final double y3 = this.origin.y;
        graphics2D.translate(0.0, y3);
        for (double n7 = n; n7 <= n6; n7 += n) {
            if (n7 >= n5) {
                graphics2D.drawLine(toInt(x1), toInt(-n7), toInt(x2), toInt(-n7));
            }
        }
        for (double n8 = -n; n8 >= n5; n8 -= n) {
            if (n8 <= n6) {
                graphics2D.drawLine(toInt(x1), toInt(-n8), toInt(x2), toInt(-n8));
            }
        }
        graphics2D.translate(0.0, -y3);
        graphics2D.translate(x3, 0.0);
        for (double n9 = n2; n9 <= n4; n9 += n2) {
            if (n9 >= n3) {
                graphics2D.drawLine(toInt(n9), toInt(y1), toInt(n9), toInt(y2));
            }
        }
        for (double n10 = -n2; n10 >= n3; n10 -= n2) {
            if (n10 <= n4) {
                graphics2D.drawLine(toInt(n10), toInt(y1), toInt(n10), toInt(y2));
            }
        }
        graphics2D.translate(-x3, 0.0);
        graphics2D.setColor(color);
    }
    
    protected void paintAxis(final Graphics2D graphics2D) {
        if (this.paintXAxis) {
            this.paintXAxis(graphics2D);
        }
        if (this.paintYAxis) {
            this.paintYAxis(graphics2D);
        }
    }
    
    protected void paintXAxis(final Graphics2D graphics2D) {
        final Font font = graphics2D.getFont();
        final Color color = graphics2D.getColor();
        graphics2D.setFont(this.xLabelFont);
        this.fm = graphics2D.getFontMetrics();
        graphics2D.setColor(this.xAxisColor);
        graphics2D.draw(this.xAxis);
        if (this.paintXAxisLabel) {
            this.paintXAxisLabel(graphics2D);
        }
        if (this.paintXAxisLabels) {
            this.paintXAxisLabels(graphics2D);
        }
        if (this.paintXAxisTicks) {
            this.paintXAxisTicks(graphics2D);
        }
        graphics2D.setColor(color);
        graphics2D.setFont(font);
    }
    
    protected void paintXAxisLabel(final Graphics2D graphics2D) {
        graphics2D.drawString(this.xAxisLabel, toInt(this.xAxis.x2 + 5.0), toInt(this.xAxis.y1 + this.fm.getMaxAscent() / 2));
    }
    
    protected void paintXAxisLabels(final Graphics2D graphics2D) {
        final Color color = graphics2D.getColor();
        final Font font = graphics2D.getFont();
        final double n = this.xMajorTickSpacing * this.xScl;
        final double n2 = this.xMin * this.xScl;
        final double n3 = this.xMax * this.xScl;
        final double y1 = this.xAxis.y1;
        graphics2D.setColor(this.xAxisLabelColor);
        graphics2D.setFont(this.xLabelFont);
        this.fm = graphics2D.getFontMetrics();
        final double x = this.origin.x;
        graphics2D.translate(x, 0.0);
        for (double n4 = n; n4 <= n3; n4 += n) {
            if (n4 >= n2 && n4 != n2 && n4 != 0.0) {
                graphics2D.drawString("" + n4 / this.xScl, toInt(n4 - this.fm.stringWidth("" + n4 / this.xScl) / 2), toInt(y1 + (this.fm.getMaxAscent() + 5)));
            }
        }
        for (double n5 = -n; n5 >= n2; n5 -= n) {
            if (n5 <= n3 && n5 != n3 && n5 != 0.0) {
                graphics2D.drawString("" + n5 / this.xScl, toInt(n5 - this.fm.stringWidth("" + n5 / this.xScl) / 2), toInt(y1 + (this.fm.getMaxAscent() + 5)));
            }
        }
        graphics2D.translate(-x, 0.0);
        graphics2D.setFont(font);
        graphics2D.setColor(color);
    }
    
    protected void paintXAxisTicks(final Graphics2D graphics2D) {
        final double n = this.xMajorTickSpacing * this.xScl;
        final double n2 = this.xMinorTickSpacing * this.xScl;
        final double n3 = this.xMin * this.xScl;
        final double n4 = this.xMax * this.xScl;
        final double x = this.origin.x;
        final double y1 = this.xAxis.y1;
        graphics2D.translate(x, 0.0);
        for (double n5 = n2; n5 <= n4; n5 += n2) {
            if (n5 >= n3) {
                graphics2D.drawLine(toInt(n5), toInt(y1 + 2.0), toInt(n5), toInt(y1 - 2.0));
            }
        }
        for (double n6 = -n2; n6 >= n3; n6 -= n2) {
            if (n6 <= n4) {
                graphics2D.drawLine(toInt(n6), toInt(y1 + 2.0), toInt(n6), toInt(y1 - 2.0));
            }
        }
        for (double n7 = n; n7 <= n4; n7 += n) {
            if (n7 >= n3) {
                graphics2D.drawLine(toInt(n7), toInt(y1 + 4.0), toInt(n7), toInt(y1 - 4.0));
            }
        }
        for (double n8 = -n; n8 >= n3; n8 -= n) {
            if (n8 <= n4) {
                graphics2D.drawLine(toInt(n8), toInt(y1 + 4.0), toInt(n8), toInt(y1 - 4.0));
            }
        }
        graphics2D.translate(-x, 0.0);
    }
    
    protected void paintYAxis(final Graphics2D graphics2D) {
        graphics2D.setFont(this.yLabelFont);
        this.fm = graphics2D.getFontMetrics();
        graphics2D.setColor(this.yAxisColor);
        graphics2D.draw(this.yAxis);
        if (this.paintYAxisLabel) {
            this.paintYAxisLabel(graphics2D);
        }
        if (this.paintYAxisLabels) {
            this.paintYAxisLabels(graphics2D);
        }
        if (this.paintYAxisTicks) {
            this.paintYAxisTicks(graphics2D);
        }
    }
    
    protected void paintYAxisLabel(final Graphics2D graphics2D) {
        graphics2D.drawString(this.yAxisLabel, toInt(this.yAxis.x1 - this.fm.stringWidth(this.yAxisLabel) / 2), toInt(this.yAxis.y2 - (this.fm.getMaxDescent() + 5)));
    }
    
    protected void paintYAxisLabels(final Graphics2D graphics2D) {
        final Font font = graphics2D.getFont();
        final Color color = graphics2D.getColor();
        final double n = this.yMajorTickSpacing * this.yScl;
        final double n2 = this.yMinorTickSpacing * this.yScl;
        final double n3 = this.yMax * this.yScl;
        final double n4 = this.yMin * this.yScl;
        graphics2D.setFont(this.yLabelFont);
        this.fm = graphics2D.getFontMetrics();
        graphics2D.setColor(this.yAxisLabelColor);
        final double x1 = this.yAxis.x1;
        final double y = this.origin.y;
        graphics2D.translate(0.0, y);
        for (double n5 = n; n5 <= n3; n5 += n) {
            if (n5 >= n4 && n5 != n4 && n5 != 0.0) {
                graphics2D.drawString("" + n5 / this.yScl, toInt(x1 + this.yLabelDistance), toInt(-n5 + this.fm.getMaxAscent() / 2));
            }
        }
        for (double n6 = -n; n6 >= n4; n6 -= n) {
            if (n6 <= n3 && n6 != n3 && n6 != 0.0) {
                graphics2D.drawString("" + n6 / this.yScl, toInt(x1 + this.yLabelDistance), toInt(-n6 + this.fm.getMaxAscent() / 2));
            }
        }
        graphics2D.translate(0.0, -y);
        graphics2D.setFont(font);
        graphics2D.setColor(color);
    }
    
    protected void paintYAxisTicks(final Graphics2D graphics2D) {
        final double n = this.yMajorTickSpacing * this.yScl;
        final double n2 = this.yMinorTickSpacing * this.yScl;
        final double n3 = this.yMax * this.yScl;
        final double n4 = this.yMin * this.yScl;
        final Color color = graphics2D.getColor();
        graphics2D.setColor(this.yAxisTickColor);
        final double x1 = this.yAxis.x1;
        final double y = this.origin.y;
        graphics2D.translate(0.0, y);
        for (double n5 = n2; n5 <= n3; n5 += n2) {
            if (n5 >= n4) {
                graphics2D.drawLine(toInt(x1 + 2.0), toInt(-n5), toInt(x1 - 2.0), toInt(-n5));
            }
        }
        for (double n6 = -n2; n6 >= n4; n6 -= n2) {
            if (n6 <= n3) {
                graphics2D.drawLine(toInt(x1 + 2.0), toInt(-n6), toInt(x1 - 2.0), toInt(-n6));
            }
        }
        for (double n7 = n; n7 <= n3; n7 += n) {
            if (n7 >= n4) {
                graphics2D.drawLine(toInt(x1 + 4.0), toInt(-n7), toInt(x1 - 4.0), toInt(-n7));
            }
        }
        for (double n8 = -n; n8 >= n4; n8 -= n) {
            if (n8 <= n3) {
                graphics2D.drawLine(toInt(x1 + 4.0), toInt(-n8), toInt(x1 - 4.0), toInt(-n8));
            }
        }
        graphics2D.translate(0.0, -y);
        graphics2D.setColor(color);
    }
    
    protected void paintPaths(final Graphics2D graphics2D) {
        this.tempPathEnum = this.paths.elements();
        this.tempPathClip = graphics2D.getClip();
        graphics2D.setClip(this.getClip());
        while (this.tempPathEnum.hasMoreElements()) {
            this.tempPathEnum.nextElement().paint(graphics2D);
        }
        graphics2D.setClip(this.tempPathClip);
    }
    
    protected void paintPath(final Graphics2D graphics2D, final Path path) {
        this.tempPathClip = graphics2D.getClip();
        graphics2D.setClip(this.getClip());
        path.paint(graphics2D);
        graphics2D.setClip(this.tempPathClip);
    }
    
    public void resetPaths() {
        final Enumeration<Path> elements = this.paths.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().reset();
        }
    }
    
    public void removePaths() {
        this.paths.clear();
    }
    
    public void removePath(final int index) {
        this.paths.remove(index);
    }
    
    public void addPath(final int n, final Path element) {
        this.paths.ensureCapacity(n);
        this.paths.add(n, element);
    }
    
    public int addPath(final Path e) {
        this.paths.add(e);
        return this.paths.size();
    }
    
    public int addPath() {
        this.paths.add(new Path());
        return this.paths.size();
    }
    
    public Path getPath(final int index) {
        return this.paths.elementAt(index);
    }
    
    public Enumeration getPaths() {
        return this.paths.elements();
    }
    
    public double getXMax() {
        return this.xMax;
    }
    
    public void setXMax(final double n) {
        this.xMin = Math.min(n, this.xMin);
        this.xMax = n;
        this.setAxis();
    }
    
    public double getXMin() {
        return this.xMin;
    }
    
    public void setXMin(final double n) {
        this.xMax = Math.max(this.xMax, n);
        this.xMin = n;
        this.setAxis();
    }
    
    public double getXScl() {
        return this.xScl;
    }
    
    public void setXScl(final double a) {
        this.xScl = Math.abs(a);
        final Iterator iterator = this.paths.iterator();
        while (iterator.hasNext()) {
            iterator.next().replot();
        }
        this.setAxis();
    }
    
    public double getYMax() {
        return this.yMax;
    }
    
    public void setYMax(final double n) {
        this.yMin = Math.min(n, this.yMin);
        this.yMax = n;
        this.setAxis();
    }
    
    public double getYMin() {
        return this.yMin;
    }
    
    public void setYMin(final double n) {
        this.yMax = Math.max(this.yMax, n);
        this.yMin = n;
        this.setAxis();
    }
    
    public double getYScl() {
        return this.yScl;
    }
    
    public void setYScl(final double a) {
        this.yScl = Math.abs(a);
        final Iterator iterator = this.paths.iterator();
        this.setAxis();
        while (iterator.hasNext()) {
            iterator.next().replot();
        }
    }
    
    private void setAxis() {
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
    
    public double getXMinorTickSpacing() {
        return this.xMinorTickSpacing;
    }
    
    public void setXMinorTickSpacing(final double xMinorTickSpacing) {
        this.xMinorTickSpacing = xMinorTickSpacing;
    }
    
    public double getXMajorTickSpacing() {
        return this.xMajorTickSpacing;
    }
    
    public void setXMajorTickSpacing(final double xMajorTickSpacing) {
        this.xMajorTickSpacing = xMajorTickSpacing;
    }
    
    public double getYMinorTickSpacing() {
        return this.yMinorTickSpacing;
    }
    
    public void setYMinorTickSpacing(final double yMinorTickSpacing) {
        this.yMinorTickSpacing = yMinorTickSpacing;
    }
    
    public double getYMajorTickSpacing() {
        return this.yMajorTickSpacing;
    }
    
    public void setYMajorTickSpacing(final double yMajorTickSpacing) {
        this.yMajorTickSpacing = yMajorTickSpacing;
    }
    
    public Font getXLabelFont() {
        return this.xLabelFont;
    }
    
    public void setXLabelFont(final Font xLabelFont) {
        this.xLabelFont = xLabelFont;
    }
    
    public Font getYLabelFont() {
        return this.yLabelFont;
    }
    
    public void setYLabelFont(final Font yLabelFont) {
        this.yLabelFont = yLabelFont;
    }
    
    public void setFont(final Font font) {
        this.xLabelFont = font;
        this.yLabelFont = font;
    }
    
    public static int toInt(final double n) {
        return (n < 0.0) ? ((int)(n - 0.5)) : ((int)(n + 0.5));
    }
    
    public double getXRange() {
        return this.xMax - this.xMin;
    }
    
    public double getYRange() {
        return this.yMax - this.yMin;
    }
    
    public double getWidth() {
        return this.xScl * (this.xMax - this.xMin);
    }
    
    public double getHeight() {
        return this.yScl * (this.yMax - this.yMin);
    }
    
    public Dimension getSize() {
        return new Dimension(toInt(this.getWidth()), toInt(this.getHeight()));
    }
    
    public Point2D.Double getLocation() {
        return (Point2D.Double)this.location.clone();
    }
    
    public Point2D.Double getOrigin() {
        return (Point2D.Double)this.origin.clone();
    }
    
    public void setLocation(final Point2D.Double location) {
        this.location = location;
    }
    
    public void setLocation(final double x, final double y) {
        this.location.x = x;
        this.location.y = y;
    }
    
    public void translate(final double n, final double n2) {
        final Point2D.Double location = this.location;
        location.x += n;
        final Point2D.Double location2 = this.location;
        location2.y += n2;
    }
    
    private Shape getClip() {
        this.clip.setSize((int)(this.getWidth() + 0.5), (int)(this.getHeight() + 0.5));
        return this.clip;
    }
    
    private Rectangle getLabelClip() {
        (this.labelClip = this.tempPathClip.getBounds()).setLocation(this.labelClip.x - (int)this.origin.x, this.labelClip.y - (int)this.origin.y);
        return this.labelClip;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public boolean isMoveable() {
        return this.moveable;
    }
    
    public void setMoveable(final boolean moveable) {
        this.moveable = moveable;
    }
    
    public boolean isEfficient() {
        return this.efficient;
    }
    
    public void setEfficient(final boolean efficient) {
        this.efficient = efficient;
    }
    
    public class Path
    {
        private GeneralPath path;
        private double last_x;
        private double last_y;
        protected Vector points;
        private boolean labelVisible;
        private boolean labelArrowVisible;
        private int labelDistance;
        private int labelEdge;
        private DecimalFormat labelDecimalFormat;
        public Color color;
        public Stroke stroke;
        public boolean enabled;
        Stroke tempStroke;
        Color tempColor;
        Shape tempPathLabelClip;
        String tempLabel;
        int tempLabely;
        int tempLabelx;
        int tempWidth;
        int tempHeight;
        int tempX;
        
        public void setLabelVisible(final boolean labelVisible) {
            this.labelVisible = labelVisible;
        }
        
        public void setLabelArrowVisible(final boolean labelArrowVisible) {
            this.labelArrowVisible = labelArrowVisible;
        }
        
        public void setLabelDistance(final int labelDistance) {
            this.labelDistance = labelDistance;
        }
        
        public void setLabelEdge(final int n) {
            if (n == 3) {
                this.labelEdge = 3;
            }
            else if (n == 5) {
                this.labelEdge = 5;
            }
            else if (n == 1) {
                this.labelEdge = 1;
            }
            else if (n == 0) {
                this.labelEdge = 0;
            }
            else {
                this.labelEdge = 7;
            }
        }
        
        public void setLabelDecimalFormat(final DecimalFormat labelDecimalFormat) {
            this.labelDecimalFormat = labelDecimalFormat;
        }
        
        public Path() {
            this.path = new GeneralPath();
            this.last_x = 0.0;
            this.last_y = 0.0;
            this.points = new Vector();
            this.labelVisible = false;
            this.labelArrowVisible = false;
            this.labelDistance = 15;
            this.labelEdge = 7;
            this.labelDecimalFormat = new DecimalFormat("0.00");
            this.color = Color.black;
            this.enabled = true;
            this.stroke = new BasicStroke();
        }
        
        public Path(final Color color, final Stroke stroke) {
            this.path = new GeneralPath();
            this.last_x = 0.0;
            this.last_y = 0.0;
            this.points = new Vector();
            this.labelVisible = false;
            this.labelArrowVisible = false;
            this.labelDistance = 15;
            this.labelEdge = 7;
            this.labelDecimalFormat = new DecimalFormat("0.00");
            this.color = Color.black;
            this.enabled = true;
            this.color = color;
            this.stroke = stroke;
        }
        
        protected void paint(final Graphics graphics) {
            this.paint((Graphics2D)graphics);
        }
        
        protected void paint(final Graphics2D graphics2D) {
            if (this.enabled) {
                final double x = Graph2.this.origin.x;
                final double y = Graph2.this.origin.y;
                graphics2D.translate(x, y);
                this.tempStroke = graphics2D.getStroke();
                this.tempColor = graphics2D.getColor();
                graphics2D.setColor(this.color);
                graphics2D.setStroke(this.stroke);
                graphics2D.draw(this.path);
                if (this.labelVisible && this.path.getCurrentPoint() != null) {
                    this.paintLabel(graphics2D);
                }
                graphics2D.setStroke(this.tempStroke);
                graphics2D.setColor(this.tempColor);
                graphics2D.translate(-x, -y);
            }
        }
        
        protected void paintLabel(final Graphics2D graphics2D) {
            this.tempPathLabelClip = graphics2D.getClip();
            graphics2D.setClip(Graph2.this.getLabelClip());
            Graph2.this.fm = graphics2D.getFontMetrics();
            this.tempLabely = -(int)(this.last_y * Graph2.this.yScl + 0.5);
            this.tempLabelx = (int)(this.last_x * Graph2.this.xScl + 0.5);
            this.tempWidth = (int)Graph2.this.getWidth();
            this.tempHeight = (int)Graph2.this.getHeight();
            this.tempX = (int)(Graph2.this.xMin * Graph2.this.xScl);
            if (this.labelEdge == 7) {
                graphics2D.drawString(this.tempLabel = this.labelDecimalFormat.format(this.last_y), this.tempX - Graph2.this.fm.stringWidth(this.tempLabel) - this.labelDistance - 9, this.tempLabely + 5);
                if (this.labelArrowVisible) {
                    graphics2D.drawLine(this.tempX - this.labelDistance - 8, this.tempLabely, this.tempX - this.labelDistance - 1, this.tempLabely);
                    graphics2D.drawLine(this.tempX - this.labelDistance - 3, this.tempLabely + 2, this.tempX - this.labelDistance - 1, this.tempLabely);
                    graphics2D.drawLine(this.tempX - this.labelDistance - 3, this.tempLabely - 2, this.tempX - this.labelDistance - 1, this.tempLabely);
                }
            }
            else if (this.labelEdge == 3) {
                graphics2D.drawString(this.tempLabel = this.labelDecimalFormat.format(this.last_y), this.tempWidth + this.labelDistance + 9, this.tempLabely + 5);
                if (this.labelArrowVisible) {
                    graphics2D.drawLine(this.tempWidth + this.labelDistance + 1, this.tempLabely, this.tempWidth + this.labelDistance + 8, this.tempLabely);
                    graphics2D.drawLine(this.tempWidth + this.labelDistance + 1, this.tempLabely, this.tempWidth + this.labelDistance + 3, this.tempLabely + 2);
                    graphics2D.drawLine(this.tempWidth + this.labelDistance + 1, this.tempLabely, this.tempWidth + this.labelDistance + 3, this.tempLabely - 2);
                }
            }
            else if (this.labelEdge == 1) {
                graphics2D.drawString(this.tempLabel = this.labelDecimalFormat.format(this.last_x), this.tempLabelx - Graph2.this.fm.stringWidth(this.tempLabel) / 2, -this.tempHeight - this.labelDistance - 9);
                if (this.labelArrowVisible) {
                    graphics2D.drawLine(this.tempLabelx, -this.tempHeight - this.labelDistance - 1, this.tempLabelx, -this.tempHeight - this.labelDistance - 8);
                    graphics2D.drawLine(this.tempLabelx, -this.tempHeight - this.labelDistance - 1, this.tempLabelx + 2, -this.tempHeight - this.labelDistance - 3);
                    graphics2D.drawLine(this.tempLabelx, -this.tempHeight - this.labelDistance - 1, this.tempLabelx - 2, -this.tempHeight - this.labelDistance - 3);
                }
            }
            else if (this.labelEdge == 5) {
                graphics2D.drawString(this.tempLabel = this.labelDecimalFormat.format(this.last_x), this.tempLabelx - Graph2.this.fm.stringWidth(this.tempLabel) / 2, this.labelDistance + 9 + Graph2.this.fm.getHeight());
                if (this.labelArrowVisible) {
                    graphics2D.drawLine(this.tempLabelx, this.labelDistance + 1, this.tempLabelx, this.labelDistance + 8);
                    graphics2D.drawLine(this.tempLabelx, this.labelDistance + 1, this.tempLabelx + 2, this.labelDistance + 3);
                    graphics2D.drawLine(this.tempLabelx, this.labelDistance + 1, this.tempLabelx - 2, this.labelDistance + 3);
                }
            }
            else if (this.labelEdge == 0) {
                graphics2D.drawString(this.labelDecimalFormat.format(this.last_y), this.tempLabelx, this.tempLabely);
            }
            graphics2D.setClip(this.tempPathLabelClip);
        }
        
        public void initilizePath(final double last_x, final double last_y) {
            this.last_x = last_x;
            this.last_y = last_y;
            this.path.moveTo((float)(last_x * Graph2.this.xScl), (float)(-last_y * Graph2.this.yScl));
        }
        
        public void resetPath() {
            this.path.reset();
            this.points.clear();
            this.last_x = 0.0;
            this.last_y = 0.0;
        }
        
        public void reset() {
            this.resetPath();
        }
        
        public void setPath(final double last_x, final double last_y) {
            this.last_x = last_x;
            this.last_y = last_y;
            this.path.moveTo((float)(last_x * Graph2.this.xScl), (float)(-last_y * Graph2.this.yScl));
        }
        
        public void plot(final double n, final double n2) {
            if (Graph2.this.efficient) {
                if (n <= this.last_x + 1.0 / Graph2.this.xScl) {
                    return;
                }
            }
            try {
                this.last_x = n;
                this.last_y = n2;
                this.points.add(new Point2D.Double(n, n2));
                this.path.lineTo((float)(n * Graph2.this.xScl), (float)(-n2 * Graph2.this.yScl));
                this.path.moveTo((float)(n * Graph2.this.xScl), (float)(-n2 * Graph2.this.yScl));
            }
            catch (IllegalPathStateException ex) {
                this.path.moveTo((float)(n * Graph2.this.xScl), (float)(-n2 * Graph2.this.yScl));
            }
            this.path.closePath();
        }
        
        public Color getColor() {
            return this.color;
        }
        
        public void setColor(final Color color) {
            this.color = color;
        }
        
        public Stroke getStroke() {
            return this.stroke;
        }
        
        public void setStroke(final Stroke stroke) {
            this.stroke = stroke;
        }
        
        public boolean isEnabled() {
            return this.enabled;
        }
        
        public void setEnabled(final boolean enabled) {
            this.enabled = enabled;
        }
        
        public void replot() {
            final Iterator iterator = this.points.iterator();
            this.path.reset();
            while (iterator.hasNext()) {
                final Point2D.Double double1 = iterator.next();
                try {
                    this.path.lineTo((float)(double1.x * Graph2.this.xScl), (float)(-double1.y * Graph2.this.yScl));
                    this.path.moveTo((float)(double1.x * Graph2.this.xScl), (float)(-double1.y * Graph2.this.yScl));
                }
                catch (IllegalPathStateException ex) {
                    this.path.moveTo((float)(double1.x * Graph2.this.xScl), (float)(-double1.y * Graph2.this.yScl));
                }
                this.path.closePath();
            }
        }
    }
}
