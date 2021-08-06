// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;

public class VectorDraw extends DoubleVector
{
    public static final int VECTOR = 0;
    public static final int LINE = 1;
    public static final int DOTTED_LINE = 2;
    public static final int NONE = 0;
    public static final int HEAD = 1;
    public static final int TAIL = 2;
    public static final int BODY = 3;
    private final int vectorWidth = 4;
    private static final Font defaultFont;
    private int arrowWidth;
    private int arrowHeight;
    private int arrowInset;
    private static int arcRadius;
    private static int textAngleDistance;
    private String label;
    private Color vectorColor;
    private Color anchorColor;
    private Color labelColor;
    private boolean isVisible;
    private boolean showLabel;
    private boolean labelRight;
    private boolean showAnchor;
    private Font labelFont;
    private int style;
    
    public VectorDraw() {
        this(new Point(0, 0), new Point(0, 0), "", 0);
    }
    
    public VectorDraw(final DoubleVector doubleVector) {
        this.arrowWidth = 14;
        this.arrowHeight = 15;
        this.arrowInset = 12;
        this.anchorColor = Color.blue;
        this.showAnchor = false;
        super.head = new DoublePoint(doubleVector.getHead());
        super.tail = new DoublePoint(doubleVector.getTail());
        if (doubleVector instanceof VectorDraw) {
            final VectorDraw vectorDraw = (VectorDraw)doubleVector;
            this.style = vectorDraw.getStyle();
            this.isVisible = vectorDraw.isVisible();
            this.vectorColor = vectorDraw.getColor();
            if (this.style == 1 || this.style == 2) {
                this.showAnchor = false;
            }
            this.labelRight = true;
            if (vectorDraw.getLabel() != null && vectorDraw.getLabel() != "") {
                this.showLabel = true;
                this.label = vectorDraw.getLabel();
            }
            else {
                this.showLabel = false;
            }
        }
        else {
            this.isVisible = true;
            this.vectorColor = Color.black;
            this.label = "";
            this.showLabel = false;
            if (this.style == 1 || this.style == 2) {
                this.showAnchor = false;
            }
        }
    }
    
    public VectorDraw(final Object o, final Object o2) {
        this(o, o2, "", 0);
    }
    
    public VectorDraw(final Object o, final Object o2, final int n) {
        this(o, o2, "", n);
    }
    
    public VectorDraw(final Object o, final Object o2, final String s) {
        this(o, o2, s, 0);
    }
    
    public VectorDraw(final Object o, final Object o2, final String label, final int style) {
        super(o, o2);
        this.arrowWidth = 14;
        this.arrowHeight = 15;
        this.arrowInset = 12;
        this.anchorColor = Color.blue;
        this.showAnchor = false;
        this.style = style;
        final boolean b = true;
        this.isVisible = b;
        this.labelRight = b;
        this.vectorColor = Color.black;
        if (style == 1 || style == 2) {
            this.showAnchor = false;
        }
        if (label != null && label != "") {
            this.showLabel = true;
            this.label = label;
        }
        else {
            this.showLabel = false;
        }
    }
    
    public static int drawAngle(final Graphics graphics, final VectorDraw vectorDraw, final VectorDraw vectorDraw2, final String s) {
        DoubleVector doubleVector = new DoubleVector(vectorDraw);
        DoubleVector doubleVector2 = new DoubleVector(vectorDraw2);
        if (doubleVector.getHeadPt().equals(doubleVector2.getHeadPt())) {
            doubleVector = doubleVector.getReverse();
            doubleVector2 = doubleVector2.getReverse();
        }
        else if (doubleVector.getHead().equals(doubleVector2.getTailPt())) {
            doubleVector = doubleVector.getReverse();
        }
        else if (doubleVector.getTailPt().equals(doubleVector2.getHeadPt())) {
            doubleVector2 = doubleVector2.getReverse();
        }
        else if (!doubleVector.getTailPt().equals(doubleVector2.getTailPt())) {
            return 0;
        }
        int n = -1 * (int)Math.round((doubleVector.getAngle() - doubleVector2.getAngle()) * 180.0 / 3.141592653589793);
        if (Math.abs(n) > 180) {
            n += 360 * ((n < 0) ? 1 : -1);
        }
        final int n2 = (int)Math.round(doubleVector.getAngle() * 180.0 / 3.141592653589793);
        final Point tailPt = doubleVector.getTailPt();
        graphics.drawArc(tailPt.x - VectorDraw.arcRadius, tailPt.y - VectorDraw.arcRadius, VectorDraw.arcRadius * 2, VectorDraw.arcRadius * 2, n2, n);
        final DoublePoint doublePoint = new DoublePoint(tailPt.x + VectorDraw.textAngleDistance, tailPt.y);
        doublePoint.rotate(tailPt, (n2 + n / 2) * 3.141592653589793 / 180.0);
        graphics.setFont(VectorDraw.defaultFont);
        doublePoint.y = doublePoint.y + graphics.getFontMetrics().getMaxAscent() / 2 - 2.0;
        if (n2 + n / 2 > 90 || n2 + n / 2 < -90) {
            doublePoint.x -= graphics.getFontMetrics().stringWidth(s.substring(1));
        }
        graphics.drawString(s, doublePoint.getPoint().x, doublePoint.getPoint().y);
        return Math.abs(n);
    }
    
    private void drawDottedLine(final Graphics graphics) {
        graphics.fillOval((int)Math.round(super.tail.x - 2.0), (int)Math.round(super.tail.y - 2.0), 4, 4);
        final DoubleVector directionVector = this.getDirectionVector();
        DoubleVector doubleVector = directionVector.multiply(6.0);
        for (int n = 1; doubleVector.getLength() <= this.getLength(); doubleVector = directionVector.multiply(n * 1.5 * 4.0), ++n) {
            final Point point = doubleVector.getHead().getPoint();
            graphics.fillOval(Math.round((float)(point.x - 2)), Math.round((float)(point.y - 2)), 4, 4);
        }
    }
    
    public Color getColor() {
        return this.vectorColor;
    }
    
    public int getIntersection(final Point point) {
        if (this.isOnHead(point)) {
            return 1;
        }
        if (this.isOnTail(point)) {
            return 2;
        }
        if (this.isOnVector(point)) {
            return 3;
        }
        return 0;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public Polygon getLinePolygon() {
        final int[] xpoints = new int[4];
        final int[] ypoints = new int[4];
        final DoublePoint components = this.getComponents();
        final DoubleVector doubleVector = new DoubleVector(new DoublePoint(components.y / this.getLength(), -components.x / this.getLength()));
        final DoubleVector multiply = new DoubleVector(doubleVector).translate(super.tail).multiply(2.0).getReverse().getDirectionVector().multiply(4.2);
        xpoints[0] = multiply.getHead().getPoint().x;
        ypoints[0] = multiply.getHead().getPoint().y;
        xpoints[3] = multiply.getTail().getPoint().x;
        ypoints[3] = multiply.getTail().getPoint().y;
        final DoubleVector multiply2 = new DoubleVector(doubleVector).translate(super.head).multiply(2.0).getReverse().getDirectionVector().multiply(4.2);
        xpoints[1] = multiply2.getHead().getPoint().x;
        ypoints[1] = multiply2.getHead().getPoint().y;
        xpoints[2] = multiply2.getTail().getPoint().x;
        ypoints[2] = multiply2.getTail().getPoint().y;
        return new Polygon(xpoints, ypoints, 4);
    }
    
    public VectorDraw getProjection(final Point point) {
        return new VectorDraw(this.getProjection(new DoublePoint(point)));
    }
    
    public int getStyle() {
        return this.style;
    }
    
    public Polygon getVectorPolygon() {
        final DoubleVector directionVector = this.getReverse().getDirectionVector();
        final int[] array = new int[7];
        final int[] array2 = new int[7];
        final DoublePoint components = this.getComponents();
        final DoubleVector doubleVector = new DoubleVector(new DoublePoint(components.y / this.getLength(), -components.x / this.getLength()));
        final DoubleVector multiply = new DoubleVector(doubleVector).translate(super.tail).multiply(2.0).getReverse().getDirectionVector().multiply(4.2);
        array[0] = multiply.getHead().getPoint().x;
        array2[0] = multiply.getHead().getPoint().y;
        array[6] = multiply.getTail().getPoint().x;
        array2[6] = multiply.getTail().getPoint().y;
        final DoubleVector multiply2 = new DoubleVector(doubleVector).translate(directionVector.multiply(this.arrowInset).getHead()).multiply(2.0).getReverse().getDirectionVector().multiply(4.2);
        array[1] = multiply2.getHead().getPoint().x;
        array2[1] = multiply2.getHead().getPoint().y;
        array[5] = multiply2.getTail().getPoint().x;
        array2[5] = multiply2.getTail().getPoint().y;
        final DoubleVector multiply3 = new DoubleVector(doubleVector).translate(directionVector.multiply(this.arrowHeight).getHead()).multiply(this.arrowWidth / 2).getReverse().multiply(2.0);
        array[2] = multiply3.getHead().getPoint().x;
        array2[2] = multiply3.getHead().getPoint().y;
        array[4] = multiply3.getTail().getPoint().x;
        array2[4] = multiply3.getTail().getPoint().y;
        array[3] = super.head.getPoint().x;
        array2[3] = super.head.getPoint().y;
        if (this.getLength() <= this.arrowHeight) {
            array[0] = array[1];
            array2[0] = array2[1];
            return new Polygon(array, array2, 6);
        }
        return new Polygon(array, array2, 7);
    }
    
    public static boolean isNearPoint(final Point point, final Point point2, final int n) {
        return DoublePoint.getDistance(new DoublePoint(point), new DoublePoint(point2)) <= n;
    }
    
    public boolean isOnHead(final Point point) {
        final Point headPt = this.getReverse().getDirectionVector().multiply(this.arrowHeight / 2).getHeadPt();
        return (this.style == 0 && isNearPoint(headPt, point, this.arrowHeight / 2)) || isNearPoint(super.head.getPoint(), point, 3);
    }
    
    public boolean isOnTail(final Point point) {
        return isNearPoint(super.tail.getPoint(), point, 3);
    }
    
    public boolean isOnVector(final Point point) {
        if (this.style == 0) {
            return this.getVectorPolygon().contains(point);
        }
        return this.getLinePolygon().contains(point);
    }
    
    public boolean isVisible() {
        return this.isVisible;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible) {
            return;
        }
        final Color color = graphics.getColor();
        graphics.setColor(this.vectorColor);
        if (this.getLength() < 0.5) {
            graphics.fillOval(this.getTailPt().x - 2, this.getTailPt().y - 2, 4, 4);
        }
        else {
            switch (this.style) {
                case 0: {
                    graphics.fillPolygon(this.getVectorPolygon());
                    break;
                }
                case 1: {
                    graphics.fillPolygon(this.getLinePolygon());
                    break;
                }
                case 2: {
                    this.drawDottedLine(graphics);
                    break;
                }
            }
        }
        if (this.showLabel && this.label != null && this.label != "") {
            if (graphics.getFont() == null) {
                graphics.setFont(VectorDraw.defaultFont);
            }
            final Font font = graphics.getFont();
            if (this.labelFont != null) {
                graphics.setFont(this.labelFont);
            }
            final DoublePoint components = this.getComponents();
            final DoublePoint head = new DoubleVector(new DoublePoint((this.labelRight ? -1.0 : 1.0) * components.y / this.getLength(), (this.labelRight ? 1.0 : -1.0) * components.x / this.getLength())).multiply(8.0).translate(this.getDirectionVector().multiply(this.getLength() / 2.0).getHead()).getHead();
            graphics.setColor(Color.black);
            if ((this.getComponents().y > 0.0 && this.labelRight) || (this.getComponents().y < 0.0 && !this.labelRight)) {
                final DoublePoint doublePoint = head;
                doublePoint.x -= StringDraw.getWidth(this.label, graphics);
            }
            if ((this.getComponents().x > 0.0 && this.labelRight) || (this.getComponents().x < 0.0 && !this.labelRight)) {
                final DoublePoint doublePoint2 = head;
                doublePoint2.y += graphics.getFontMetrics().getAscent();
            }
            final Color color2 = graphics.getColor();
            graphics.setColor(this.labelColor);
            StringDraw.paint(this.label, head.getPoint().x, head.getPoint().y, graphics);
            graphics.setFont(font);
            graphics.setColor(color2);
        }
        if (this.showAnchor) {
            graphics.setColor(this.anchorColor);
            graphics.fillOval(super.tail.getPoint().x - 3, super.tail.getPoint().y - 3, 6, 6);
        }
        graphics.setColor(color);
    }
    
    public static void paint(final Graphics graphics, final Point point, final Point point2, final int n) {
        new VectorDraw(point, point2, n).paint(graphics);
    }
    
    public void setAnchorColor(final Color anchorColor) {
        this.anchorColor = anchorColor;
    }
    
    public void setAnchorVisible(final boolean showAnchor) {
        this.showAnchor = showAnchor;
    }
    
    public Color setColor(final Color vectorColor) {
        return this.vectorColor = vectorColor;
    }
    
    public String setLabel(final String label) {
        this.label = label;
        this.showLabel = true;
        return label;
    }
    
    public void setLabelColor(final Color color) {
        this.labelColor = new Color(color.getRGB());
    }
    
    public void setLabelRight(final boolean labelRight) {
        this.labelRight = labelRight;
    }
    
    public void setLabelVisible(final boolean showLabel) {
        this.showLabel = showLabel;
    }
    
    public void setStyle(final int style) {
        this.style = style;
    }
    
    public boolean setVisible(final boolean isVisible) {
        return this.isVisible = isVisible;
    }
    
    static {
        defaultFont = new Font("Dialog", 0, 12);
        VectorDraw.arcRadius = 25;
        VectorDraw.textAngleDistance = 33;
    }
}
