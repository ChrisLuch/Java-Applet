// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import ca.ucalgary.phas.map.utilities.vectors.VectorLabelComponent;
import ca.ucalgary.phas.map.utilities.ComplexString;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import javax.swing.JPanel;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;
import java.awt.geom.Point2D;
import java.util.Random;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import ca.ucalgary.phas.map.contentNavigator.PageBrowserAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import ca.ucalgary.phas.map.contentNavigator.PageBrowserListener;
import javax.swing.JApplet;

public class DotProduct2 extends JApplet implements PageBrowserListener, MouseListener, MouseMotionListener
{
    private PageBrowserAdapter adapter;
    private Font font;
    private Font bffont;
    private Font itfont;
    private int page;
    protected Image offscreenImg;
    protected Graphics offscreen;
    protected int appWidth;
    protected int appHeight;
    protected Color panelColor;
    protected Color backColor;
    protected Color thetaColor;
    protected Color alphaColor;
    protected Color A_COLOR;
    protected Color B_COLOR;
    private boolean mouseDown;
    private boolean over180;
    private Point mouseDownPoint;
    private Random rand;
    protected Point2D.Double center;
    protected int theta;
    protected int alpha;
    protected double aDir;
    protected double bDir;
    protected double ax;
    protected double ay;
    protected double bx;
    protected double by;
    protected int thetaSize;
    protected int alphaSize;
    protected double dot;
    protected int zoom;
    protected Vector2d vectorA;
    protected Vector2d vectorB;
    protected Vector2d currentVector;
    protected Vector2d vectorAy;
    protected Vector2d vectorBy;
    protected Vector2d vectorAx;
    protected Vector2d vectorBx;
    static final String THETA = "\u03b8";
    static final String ALPHA = "\u03b1";
    static final double A_LENGTH = 120.0;
    static final double B_LENGTH = 180.0;
    static final double scale = 20.0;
    protected JPanel contentPanel;
    private boolean show_magnitudes;
    private boolean show_angle;
    protected Rectangle show_magButton;
    protected Rectangle show_angleButton;
    private CartGraph coords;
    
    public DotProduct2() {
        this.panelColor = Color.white;
        this.backColor = Color.lightGray;
        this.thetaColor = Color.blue;
        this.alphaColor = Color.magenta;
        this.A_COLOR = Color.red;
        this.B_COLOR = Color.orange;
        this.mouseDown = false;
        this.over180 = false;
        this.mouseDownPoint = null;
        this.rand = new Random();
        this.center = new Point2D.Double();
        this.thetaSize = 30;
        this.alphaSize = 40;
        this.vectorA = null;
        this.vectorB = null;
        this.currentVector = null;
        this.vectorAy = null;
        this.vectorBy = null;
        this.vectorAx = null;
        this.vectorBx = null;
        this.show_magnitudes = false;
        this.show_angle = false;
    }
    
    private void calc() {
        final double n = this.vectorA.getLength() / 20.0;
        final double n2 = this.vectorB.getLength() / 20.0;
        this.aDir = this.vectorA.getDirection();
        if (this.aDir < 0.0) {
            this.aDir += 6.283185307179586;
        }
        this.bDir = this.vectorB.getDirection();
        if (this.bDir < 0.0) {
            this.bDir += 6.283185307179586;
        }
        double n3;
        if (this.aDir > this.bDir) {
            n3 = this.aDir - this.bDir;
        }
        else {
            n3 = this.bDir - this.aDir;
        }
        if (n3 > 3.141592653589793) {
            n3 = 6.283185307179586 - n3;
            this.over180 = true;
        }
        else {
            this.over180 = false;
        }
        this.theta = (int)(n3 / 3.141592653589793 * 180.0 + 0.5);
        this.alpha = (int)(this.aDir / 3.141592653589793 * 180.0 + 0.5);
        final double n4 = n * n2 * Math.cos(this.theta * 3.141592653589793 / 180.0);
        if (n4 < 0.0) {
            this.dot = n4;
        }
        else {
            this.dot = n4;
        }
    }
    
    public void doCommand(final String s) {
        this.setupCoords();
        this.getVectors();
        this.show_angle = false;
        this.show_magnitudes = false;
        this.coords.moveable = false;
        if (s.equals("page1")) {
            this.prepareVectors();
            this.page = 1;
        }
        else if (s.equals("page2")) {
            this.prepareVectors();
            this.page = 2;
        }
        else if (s.equals("page3")) {
            this.prepareVectors();
            this.page = 3;
        }
        else if (s.equals("page4")) {
            this.prepareVectors();
            this.page = 4;
        }
        else if (s.equals("page5")) {
            this.prepareVectors();
            this.page = 5;
        }
        else if (s.equals("page6")) {
            this.prepareMovingVectors();
            this.page = 6;
        }
        else if (s.equals("page7")) {
            this.prepareVectors();
            this.coords.moveable = true;
            this.page = 7;
        }
        else if (s.equals("page8")) {
            this.prepareVectors();
            this.page = 8;
            this.coords.setXAngle(new Point((int)this.vectorA.getHead2D().x, (int)this.vectorA.getHead2D().y));
        }
        else if (s.equals("page9")) {
            this.prepareVectors9();
            this.page = 9;
            this.coords.setXAngle(new Point((int)this.vectorA.getHead2D().x, (int)this.vectorA.getHead2D().y));
        }
        this.repaint();
    }
    
    void drawAlphaArc(final Graphics graphics) {
        final Color color = graphics.getColor();
        final Point2D.Double double1 = new Point2D.Double(this.center.x, this.center.y);
        final Point2D.Double double2 = new Point2D.Double(this.center.x, this.center.y);
        graphics.setColor(this.alphaColor);
        this.vectorA.getDirection();
        final Point2D.Double translatePoint = this.translatePoint(double1, 2.356194490192345, 2 * this.alphaSize / 3);
        final Point2D.Double translatePoint2 = this.translatePoint(double2, this.alpha * 3.141592653589793 / 180.0 / 2.0, this.alphaSize);
        graphics.drawArc((int)translatePoint.x, (int)translatePoint.y, this.alphaSize, this.alphaSize, (int)(this.vectorA.getDirection() / 3.141592653589793 * 180.0), -1 * this.alpha);
        graphics.drawString("\u03b1", (int)translatePoint2.x - 2, (int)translatePoint2.y + 6);
        graphics.setColor(color);
    }
    
    public static void drawDot(final Graphics graphics, final int n, final int n2) {
        graphics.fillOval(n, n2 - 10 / 2, 4, 4);
    }
    
    public int drawRoot(final Graphics graphics, final int n, final int n2, final String str, final Font font) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.black);
        final Font font2 = graphics.getFont();
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final int ascent = fontMetrics.getAscent();
        final int stringWidth = fontMetrics.stringWidth(str + "  ");
        graphics.drawLine(n, n2 + 3, n + 3, n2 - (2 * ascent - 3));
        graphics.drawLine(n, n2 + 3, n - 1, n2 - 3);
        graphics.drawLine(n + 3, n2 - (2 * ascent - 3), n + stringWidth, n2 - (2 * ascent - 3));
        graphics.setFont(font2);
        graphics.setColor(color);
        return stringWidth;
    }
    
    public int drawString(final Graphics graphics, final int n, final int n2, final String str, final String s, final Font font, final Font font2) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.black);
        final Font font3 = graphics.getFont();
        graphics.setFont(font);
        graphics.drawString(str, n, n2);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final int ascent = fontMetrics.getAscent();
        final int stringWidth = fontMetrics.stringWidth(str);
        graphics.setFont(font2);
        graphics.drawString(s, n + stringWidth, n2 + ascent / 2);
        graphics.setFont(font3);
        graphics.setColor(color);
        return stringWidth;
    }
    
    public int drawString(final Graphics graphics, final int n, final int n2, final String str, final String s, final String s2, final Font font, final Font font2, final Font font3) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.black);
        final Font font4 = graphics.getFont();
        graphics.setFont(font);
        graphics.drawString(str, n, n2);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final int ascent = fontMetrics.getAscent();
        final int stringWidth = fontMetrics.stringWidth(str);
        graphics.setFont(font2);
        graphics.drawString(s, n + stringWidth, n2 + ascent / 2);
        graphics.setFont(font3);
        graphics.drawString(s2, n + stringWidth, n2 - ascent / 2);
        graphics.setFont(font4);
        graphics.setColor(color);
        return stringWidth;
    }
    
    void drawThetaArc(final Graphics graphics) {
        final Color color = graphics.getColor();
        final Point2D.Double double1 = new Point2D.Double(this.center.x, this.center.y);
        final Point2D.Double double2 = new Point2D.Double(this.center.x, this.center.y);
        graphics.setColor(this.thetaColor);
        this.vectorA.getDirection();
        this.vectorB.getDirection();
        final Point2D.Double translatePoint = this.translatePoint(double1, 2.356194490192345, 2 * this.thetaSize / 3);
        Point2D.Double double3;
        if (this.aDir > this.bDir) {
            if (this.over180) {
                double3 = this.translatePoint(double2, this.bDir - this.theta * 3.141592653589793 / 180.0 / 2.0, this.thetaSize);
                graphics.drawArc((int)translatePoint.x, (int)translatePoint.y, this.thetaSize, this.thetaSize, (int)(this.vectorA.getDirection() / 3.141592653589793 * 180.0), this.theta);
            }
            else {
                double3 = this.translatePoint(double2, this.bDir + this.theta * 3.141592653589793 / 180.0 / 2.0, this.thetaSize);
                graphics.drawArc((int)translatePoint.x, (int)translatePoint.y, this.thetaSize, this.thetaSize, (int)(this.vectorA.getDirection() / 3.141592653589793 * 180.0), -1 * this.theta);
            }
        }
        else if (this.over180) {
            double3 = this.translatePoint(double2, this.aDir - this.theta * 3.141592653589793 / 180.0 / 2.0, this.thetaSize);
            graphics.drawArc((int)translatePoint.x, (int)translatePoint.y, this.thetaSize, this.thetaSize, (int)(this.vectorB.getDirection() / 3.141592653589793 * 180.0), this.theta);
        }
        else {
            double3 = this.translatePoint(double2, this.aDir + this.theta * 3.141592653589793 / 180.0 / 2.0, this.thetaSize);
            graphics.drawArc((int)translatePoint.x, (int)translatePoint.y, this.thetaSize, this.thetaSize, (int)(this.vectorB.getDirection() / 3.141592653589793 * 180.0), -1 * this.theta);
        }
        graphics.drawString("\u03b8", (int)double3.x + 2, (int)double3.y + 2);
        graphics.setColor(color);
    }
    
    public static int drawVectorLabel(final Graphics graphics, final int n, final int n2, final String str, final Font font) {
        final Font font2 = graphics.getFont();
        graphics.setFont(font);
        graphics.drawString(str, n, n2);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final int ascent = fontMetrics.getAscent();
        final int stringWidth = fontMetrics.stringWidth(str);
        graphics.drawLine(n, n2 - ascent, n + stringWidth, n2 - ascent);
        graphics.drawLine(n + stringWidth, n2 - ascent, n + stringWidth - 2, n2 - ascent - 2);
        graphics.drawLine(n + stringWidth, n2 - ascent, n + stringWidth - 2, n2 - ascent + 2);
        graphics.setFont(font2);
        return stringWidth;
    }
    
    public int drawVectorLabel(final Graphics graphics, final int n, final int n2, final String str, final String s, final Font font, final Font font2) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.black);
        final Font font3 = graphics.getFont();
        graphics.setFont(font);
        graphics.drawString(str, n, n2);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final int ascent = fontMetrics.getAscent();
        final int stringWidth = fontMetrics.stringWidth(str);
        graphics.drawLine(n, n2 - ascent, n + stringWidth, n2 - ascent);
        graphics.drawLine(n + stringWidth, n2 - ascent, n + stringWidth - 2, n2 - ascent - 2);
        graphics.drawLine(n + stringWidth, n2 - ascent, n + stringWidth - 2, n2 - ascent + 2);
        graphics.setFont(font2);
        graphics.drawString(s, n + stringWidth, n2 + ascent / 2);
        graphics.setFont(font3);
        graphics.setColor(color);
        return stringWidth;
    }
    
    public void getVectors() {
        final double n = 120.0;
        final double n2 = 2.443460952792061;
        final double n3 = 0.5235987755982988;
        final Point2D.Double center = this.center;
        this.vectorA = new Vector2d(center, this.translatePoint(center, n3, n));
        final double n4 = 180.0;
        final Point2D.Double center2 = this.center;
        this.vectorB = new Vector2d(center2, this.translatePoint(center2, n2, n4));
    }
    
    public boolean inBounds(final Point point) {
        return point.x >= 20 && point.x <= this.getSize().width - 20 && point.y >= 10 && point.y <= this.getSize().height - 110;
    }
    
    public void init() {
        (this.contentPanel = new JPanel()).setName("JAppletContentPane");
        this.contentPanel.setLayout(null);
        this.appWidth = this.getSize().width;
        this.appHeight = this.getSize().height;
        this.setContentPane(this.contentPanel);
        this.zoom = this.appWidth / 10;
        this.setBackground(this.backColor);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.font = new Font("Arial", 0, 12);
        this.bffont = new Font("Arial", 1, 12);
        this.itfont = new Font("Arial", 2, 12);
        this.offscreenImg = this.createImage(this.appWidth, this.appHeight);
        this.offscreen = this.offscreenImg.getGraphics();
        final Point point = new Point(this.appWidth / 2, this.appHeight / 2 - 35);
        this.center = new Point2D.Double(point.x, point.y);
        this.page = 1;
        this.adapter = new PageBrowserAdapter((PageBrowserListener)this, "Dot2");
        this.doCommand("page1");
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (this.page == 6) {
            if (this.show_magButton.contains(point)) {
                this.show_magnitudes = true;
                this.repaint();
            }
            else if (this.show_angleButton.contains(point)) {
                this.show_angle = true;
                this.repaint();
            }
        }
        this.currentVector = this.theVectorAt(point);
        if (this.currentVector != null) {
            this.currentVector.setSelected(true);
            this.repaint();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.mouseDown && this.coords != null) {
            final Point point = mouseEvent.getPoint();
            if (this.coords.xIsMoving()) {
                this.coords.setXAngle(point);
            }
            else if (this.coords.yIsMoving()) {
                this.coords.setYAngle(point);
            }
            else if (this.inBounds(point) && this.currentVector != null) {
                this.show_angle = false;
                this.show_magnitudes = false;
                if (this.currentVector.getHeadMoving()) {
                    this.currentVector.setHeadPoint(point);
                }
                else if (this.currentVector.getTailMoving()) {
                    this.currentVector.setTailPoint(point);
                }
                else if (this.currentVector.getMoving()) {
                    final int n = point.x - this.mouseDownPoint.x;
                    final int n2 = point.y - this.mouseDownPoint.y;
                    this.currentVector.setTail(new Point2D.Double(this.currentVector.getTail2D().x + n, this.currentVector.getTail2D().y + n2));
                    this.currentVector.setHead(new Point2D.Double(this.currentVector.getHead2D().x + n, this.currentVector.getHead2D().y + n2));
                    this.mouseDownPoint = point;
                }
            }
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (this.page == 6) {
            if (this.show_magButton.contains(point)) {
                this.show_magnitudes = true;
                this.repaint();
            }
            else if (this.show_angleButton.contains(point)) {
                this.show_angle = true;
                this.repaint();
            }
        }
        if (this.coords.onXTip(point) && this.coords.moveable) {
            this.coords.setXMoving(true);
            this.mouseDown = true;
            this.mouseDownPoint = point;
        }
        else if (this.coords.onYTip(point) && this.coords.moveable) {
            this.coords.setYMoving(true);
            this.mouseDown = true;
            this.mouseDownPoint = point;
        }
        else {
            this.currentVector = this.theVectorAt(point);
            if (this.currentVector != null) {
                Vector2d vector2d = this.vectorA;
                for (int i = 0; i < 2; ++i) {
                    if (vector2d.onHeadAnch(point) && vector2d.isHeadChangable()) {
                        vector2d.setHeadMoving(true);
                    }
                    else if (vector2d.onTailAnch(point) && vector2d.isTailChangable()) {
                        vector2d.setTailMoving(true);
                    }
                    else if (vector2d.isMoveable()) {
                        vector2d.setMoving(true);
                    }
                    vector2d = this.vectorB;
                }
            }
        }
        this.mouseDownPoint = point;
        this.mouseDown = true;
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.currentVector = null;
        this.vectorA.setSelected(false);
        this.vectorB.setSelected(false);
        if (this.coords != null) {
            if (this.coords.xIsMoving()) {
                this.coords.setXMoving(false);
            }
            if (this.coords.yIsMoving()) {
                this.coords.setYMoving(false);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.vectorA != null && this.vectorB != null) {
            this.offscreen.setFont(this.itfont);
            this.offscreen.setColor(this.panelColor);
            this.offscreen.fillRect(0, 0, this.appWidth, this.appHeight - 100);
            this.offscreen.setColor(Color.black);
            final FontMetrics fontMetrics = this.offscreen.getFontMetrics(this.itfont);
            final String str = "a";
            final String str2 = "b";
            if (this.page != 13 && this.page != 14) {
                this.coords.paintGraph(this.offscreen);
            }
            else {
                final Point2D.Double translatePoint = this.translatePoint(this.vectorA.getTail2D(), this.vectorA.getDirection() + 3.141592653589793, 120.0);
                final Point2D.Double translatePoint2 = this.translatePoint(this.vectorA.getHead2D(), this.vectorA.getDirection(), 60.0);
                this.offscreen.drawLine((int)translatePoint.x, (int)translatePoint.y, (int)translatePoint2.x, (int)translatePoint2.y);
                this.offscreen.drawLine((int)(translatePoint2.x - 5.0), (int)translatePoint2.y, (int)translatePoint2.x, (int)translatePoint2.y);
                this.offscreen.drawLine((int)translatePoint2.x, (int)(translatePoint2.y + 5.0), (int)translatePoint2.x, (int)translatePoint2.y);
            }
            this.setLines();
            this.calc();
            this.vectorA.paint(this.offscreen);
            this.vectorB.paint(this.offscreen);
            this.vectorBy.paint(this.offscreen);
            if (this.page != 9) {
                this.vectorAy.paint(this.offscreen);
                this.vectorAx.paint(this.offscreen);
                this.vectorBx.paint(this.offscreen);
                final Point2D.Double translatePoint3 = this.translatePoint(this.vectorAy.getTail2D(), this.vectorAy.getDirection(), this.vectorAy.getLength() / 2.0);
                final Point2D.Double translatePoint4 = this.translatePoint(this.vectorBy.getTail2D(), this.vectorBy.getDirection(), this.vectorBy.getLength() / 2.0);
                final Point2D.Double translatePoint5 = this.translatePoint(this.vectorAx.getTail2D(), this.vectorAx.getDirection(), this.vectorAx.getLength() / 2.0);
                final Point2D.Double translatePoint6 = this.translatePoint(this.vectorBx.getTail2D(), this.vectorBx.getDirection(), this.vectorBx.getLength() / 2.0);
                if (this.ax < -0.5 || this.ax > 0.5) {
                    this.drawString(this.offscreen, (int)translatePoint5.x, (int)(translatePoint5.y - 5.0), "a", " x", this.itfont, this.itfont);
                }
                if (this.ay < -0.5 || this.ay > 0.5) {
                    this.drawString(this.offscreen, (int)(translatePoint3.x + 5.0), (int)translatePoint3.y, "a", " y", this.itfont, this.itfont);
                }
                if (this.bx < -0.5 || this.bx > 0.5) {
                    this.drawString(this.offscreen, (int)translatePoint6.x, (int)(translatePoint6.y - 5.0), "b", " x", this.itfont, this.itfont);
                }
                if (this.by < -0.5 || this.by > 0.5) {
                    this.drawString(this.offscreen, (int)(translatePoint4.x + 5.0), (int)translatePoint4.y, "b", " y", this.itfont, this.itfont);
                }
            }
            else {
                final Point2D.Double translatePoint7 = this.translatePoint(this.vectorBy.getTail2D(), this.vectorBy.getDirection(), this.vectorBy.getLength() / 2.0);
                this.drawString(this.offscreen, (int)translatePoint7.x, (int)translatePoint7.y, "F", "||", this.itfont, this.itfont);
            }
            this.offscreen.setColor(this.backColor);
            this.offscreen.fillRect(0, this.appHeight - 100, this.appWidth, 100);
            this.offscreen.setColor(Color.black);
            this.offscreen.drawRect(0, 0, this.appWidth, this.appHeight - 100);
            this.offscreen.drawRect(0, 0, this.appWidth - 1, this.appHeight - 1);
            if (this.page != 9) {
                final int n = 20;
                final int n2 = this.appHeight - 85;
                this.offscreen.setFont(this.font);
                this.drawString(this.offscreen, n, n2, "a", " x", this.itfont, this.itfont);
                this.offscreen.drawString("  =  " + this.round(this.ax), n + fontMetrics.stringWidth("a x"), n2);
                final int n3 = 100;
                this.drawString(this.offscreen, n3, n2, "a", " y", this.itfont, this.itfont);
                this.offscreen.drawString("  =  " + this.round(this.ay), n3 + fontMetrics.stringWidth("a y"), n2);
                final int n4 = 180;
                this.drawString(this.offscreen, n4, n2, "b", " x", this.itfont, this.itfont);
                this.offscreen.drawString("  =  " + this.round(this.bx), n4 + fontMetrics.stringWidth("b x"), n2);
                final int n5 = 260;
                this.drawString(this.offscreen, n5, n2, "b", " y", this.itfont, this.itfont);
                this.offscreen.drawString("  =  " + this.round(this.by), n5 + fontMetrics.stringWidth("b y"), n2);
            }
            if (this.page == 3 || this.page == 4 || this.page == 5) {
                final int n6 = 90;
                final int n7 = this.appHeight - 60;
                drawVectorLabel(this.offscreen, n6, n7, str, this.bffont);
                final int n8 = n6 + fontMetrics.stringWidth(str + " ");
                drawDot(this.offscreen, n8, n7);
                final int n9 = n8 + fontMetrics.stringWidth("o");
                drawVectorLabel(this.offscreen, n9, n7, str2, this.bffont);
                final int n10 = n9 + fontMetrics.stringWidth(str2);
                this.offscreen.drawString("  =  ", n10, n7);
                final int n11 = n10 + fontMetrics.stringWidth("  =  ");
                this.drawString(this.offscreen, n11, n7, "a", " x", this.itfont, this.itfont);
                final int n12 = n11 + fontMetrics.stringWidth("a x");
                this.drawString(this.offscreen, n12, n7, "b", " x", this.itfont, this.itfont);
                final int n13 = n12 + fontMetrics.stringWidth("b x");
                this.offscreen.drawString(" + ", n13, n7);
                final int n14 = n13 + fontMetrics.stringWidth(" + ");
                this.drawString(this.offscreen, n14, n7, "a", " y", this.itfont, this.itfont);
                final int n15 = n14 + fontMetrics.stringWidth("a y");
                this.drawString(this.offscreen, n15, n7, "b", " y", this.itfont, this.itfont);
                this.offscreen.drawString("  =  " + this.round(this.dot), n15 + fontMetrics.stringWidth("b y"), n7);
            }
            if (this.page == 4 || this.page == 5) {
                this.drawThetaArc(this.offscreen);
                final int n16 = 20;
                final int n17 = this.appHeight - 25;
                this.offscreen.setFont(this.itfont);
                this.offscreen.drawString("a  =  ", n16, n17);
                final int n18 = n16 + fontMetrics.stringWidth("a  =   ");
                this.drawRoot(this.offscreen, n18, n17, "ax = ay", this.itfont);
                final int n19 = n18 + fontMetrics.stringWidth("  ");
                this.drawString(this.offscreen, n19, n17, "a", " x", "2", this.itfont, this.itfont, this.font);
                final int n20 = n19 + fontMetrics.stringWidth("a x");
                this.offscreen.drawString(" + ", n20, n17);
                final int n21 = n20 + fontMetrics.stringWidth(" + ");
                this.drawString(this.offscreen, n21, n17, "a", " y", "2", this.itfont, this.itfont, this.font);
                final int n22 = n21 + fontMetrics.stringWidth("a y");
                this.offscreen.setFont(this.font);
                this.offscreen.drawString("  =  " + this.round(this.vectorA.getLength() / 20.0), n22, n17);
                final int n23 = 180;
                final int n24 = this.appHeight - 25;
                this.offscreen.setFont(this.itfont);
                this.offscreen.drawString("b  =  ", n23, n24);
                final int n25 = n23 + fontMetrics.stringWidth("b  =   ");
                this.drawRoot(this.offscreen, n25, n24, "bx = by", this.itfont);
                final int n26 = n25 + fontMetrics.stringWidth("  ");
                this.drawString(this.offscreen, n26, n24, "b", " x", "2", this.itfont, this.itfont, this.font);
                final int n27 = n26 + fontMetrics.stringWidth("b x");
                this.offscreen.drawString(" + ", n27, n24);
                final int n28 = n27 + fontMetrics.stringWidth(" + ");
                this.drawString(this.offscreen, n28, n24, "b", " y", "2", this.itfont, this.itfont, this.font);
                final int n29 = n28 + fontMetrics.stringWidth("b y");
                this.offscreen.setFont(this.font);
                this.offscreen.drawString("  =  " + this.round(this.vectorB.getLength() / 20.0), n29, n24);
            }
            if (this.page == 6) {
                this.drawThetaArc(this.offscreen);
                if (this.show_magnitudes) {
                    this.offscreen.setColor(this.backColor);
                    this.offscreen.fill3DRect(20, this.appHeight - 40, fontMetrics.stringWidth(" Display Magnitiudes "), 20, false);
                }
                else {
                    this.offscreen.setColor(this.backColor);
                    this.offscreen.fill3DRect(20, this.appHeight - 40, fontMetrics.stringWidth(" Display Magnitiudes "), 20, true);
                }
                this.offscreen.setColor(Color.black);
                this.offscreen.drawString(" Display Magnitiudes ", 20, this.appHeight - 25);
                this.show_magButton = new Rectangle(20, this.appHeight - 40, fontMetrics.stringWidth(" Display Magnitiudes "), 20);
                if (this.show_angle) {
                    this.offscreen.setColor(this.backColor);
                    this.offscreen.fill3DRect(210, this.appHeight - 40, fontMetrics.stringWidth(" Display Angle "), 20, false);
                }
                else {
                    this.offscreen.setColor(this.backColor);
                    this.offscreen.fill3DRect(210, this.appHeight - 40, fontMetrics.stringWidth(" Display Angle "), 20, true);
                }
                this.offscreen.setColor(Color.black);
                this.offscreen.drawString(" Display Angle ", 210, this.appHeight - 25);
                this.show_angleButton = new Rectangle(210, this.appHeight - 40, fontMetrics.stringWidth(" Display Angle "), 20);
                if (this.show_magnitudes) {
                    final int n30 = 20;
                    final int n31 = this.appHeight - 50;
                    this.offscreen.setFont(this.itfont);
                    this.offscreen.drawString("a", n30, n31);
                    final int n32 = n30 + fontMetrics.stringWidth("a");
                    this.offscreen.setFont(this.font);
                    this.offscreen.drawString("  =  " + this.round(this.vectorA.getLength() / 20.0), n32, n31);
                    final int n33 = 100;
                    final int n34 = this.appHeight - 50;
                    this.offscreen.setFont(this.itfont);
                    this.offscreen.drawString("b", n33, n34);
                    final int n35 = n33 + fontMetrics.stringWidth("b");
                    this.offscreen.setFont(this.font);
                    this.offscreen.drawString("  =  " + this.round(this.vectorB.getLength() / 20.0), n35, n34);
                }
                if (this.show_angle) {
                    final int n36 = 220;
                    final int n37 = this.appHeight - 50;
                    this.offscreen.setFont(this.itfont);
                    this.offscreen.drawString("\u03b8", n36, n37);
                    final int n38 = n36 + fontMetrics.stringWidth("O");
                    this.offscreen.setFont(this.font);
                    this.offscreen.drawString("  =  " + this.theta, n38, n37);
                    this.offscreen.drawString("o", n38 + fontMetrics.stringWidth("  =  " + this.theta), n37 - 5);
                }
            }
            if (this.page == 7) {
                final int n39 = 90;
                final int n40 = this.appHeight - 40;
                drawVectorLabel(this.offscreen, n39, n40, str, this.bffont);
                final int n41 = n39 + fontMetrics.stringWidth(str + " ");
                drawDot(this.offscreen, n41, n40);
                final int n42 = n41 + fontMetrics.stringWidth("o");
                drawVectorLabel(this.offscreen, n42, n40, str2, this.bffont);
                final int n43 = n42 + fontMetrics.stringWidth(str2);
                this.offscreen.drawString("  =  ", n43, n40);
                final int n44 = n43 + fontMetrics.stringWidth("  =  ");
                this.drawString(this.offscreen, n44, n40, "a", " x", this.itfont, this.itfont);
                final int n45 = n44 + fontMetrics.stringWidth("a x");
                this.drawString(this.offscreen, n45, n40, "b", " x", this.itfont, this.itfont);
                final int n46 = n45 + fontMetrics.stringWidth("b x");
                this.offscreen.drawString(" + ", n46, n40);
                final int n47 = n46 + fontMetrics.stringWidth(" + ");
                this.drawString(this.offscreen, n47, n40, "a", " y", this.itfont, this.itfont);
                final int n48 = n47 + fontMetrics.stringWidth("a y");
                this.drawString(this.offscreen, n48, n40, "b", " y", this.itfont, this.itfont);
                this.offscreen.drawString("  =  " + this.round(this.dot), n48 + fontMetrics.stringWidth("b y"), n40);
            }
            if (this.page == 8) {
                final int n49 = 100;
                final int n50 = this.appHeight - 40;
                drawVectorLabel(this.offscreen, n49, n50, str, this.bffont);
                final int n51 = n49 + fontMetrics.stringWidth(str + " ");
                drawDot(this.offscreen, n51, n50);
                final int n52 = n51 + fontMetrics.stringWidth("o");
                drawVectorLabel(this.offscreen, n52, n50, str2, this.bffont);
                final int n53 = n52 + fontMetrics.stringWidth(str2);
                this.offscreen.drawString("  =  ", n53, n50);
                final int n54 = n53 + fontMetrics.stringWidth("  =  ");
                this.drawString(this.offscreen, n54, n50, "a", " x", this.itfont, this.itfont);
                final int n55 = n54 + fontMetrics.stringWidth("a x");
                this.drawString(this.offscreen, n55, n50, "b", " x", this.itfont, this.itfont);
                final int n56 = n55 + fontMetrics.stringWidth("b x");
                this.offscreen.drawString(" + 0 =", n56, n50);
                this.offscreen.drawString(" " + this.round(this.dot), n56 + fontMetrics.stringWidth(" + 0 ="), n50);
            }
            if (this.page == 9) {
                final int n57 = 100;
                final int n58 = this.appHeight - 50;
                this.offscreen.setFont(this.font);
                this.offscreen.drawString("work  =  ", n57, n58);
                final int n59 = n57 + fontMetrics.stringWidth("work  =  ");
                drawVectorLabel(this.offscreen, n59, n58, "F", this.bffont);
                final int n60 = n59 + fontMetrics.stringWidth("F ");
                drawDot(this.offscreen, n60, n58);
                final int n61 = n60 + fontMetrics.stringWidth("o");
                drawVectorLabel(this.offscreen, n61, n58, "d", this.bffont);
                final int n62 = n61 + fontMetrics.stringWidth("d");
                this.offscreen.drawString("  =  ", n62, n58);
                final int n63 = n62 + fontMetrics.stringWidth("  =  ");
                this.drawString(this.offscreen, n63, n58, "F", "||", this.itfont, this.itfont);
                final int n64 = n63 + fontMetrics.stringWidth("F||");
                this.offscreen.setFont(this.itfont);
                this.offscreen.drawString("d", n64, n58);
                final int n65 = n64 + fontMetrics.stringWidth("d");
                this.offscreen.setFont(this.font);
                this.offscreen.drawString(" = " + this.round(this.dot) + " N  m", n65, n58);
                drawDot(this.offscreen, n65 + fontMetrics.stringWidth(" = " + this.round(this.dot) + " N"), n58);
            }
            graphics.drawImage(this.offscreenImg, 0, 0, this);
        }
    }
    
    public void prepareMovingVectors() {
        this.vectorA.setColor(this.A_COLOR);
        this.vectorA.showInfo = true;
        this.vectorA.setLabelSide(0);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector: a>"));
        this.vectorA.setChangable(true, false);
        this.vectorA.setMoveable(false);
        this.vectorB.setColor(this.B_COLOR);
        this.vectorB.showInfo = true;
        this.vectorB.setLabelSide(1);
        this.vectorB.setLabel((VectorLabelComponent)new ComplexString("<vector: b>"));
        this.vectorB.setChangable(true, false);
        this.vectorB.setMoveable(false);
    }
    
    public void prepareVectors() {
        this.vectorA.setColor(this.A_COLOR);
        this.vectorA.showInfo = true;
        this.vectorA.setLabelSide(0);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector: a>"));
        this.vectorA.setChangable(false, false);
        this.vectorA.setMoveable(false);
        this.vectorB.setColor(this.B_COLOR);
        this.vectorB.showInfo = true;
        this.vectorB.setLabelSide(1);
        this.vectorB.setLabel((VectorLabelComponent)new ComplexString("<vector: b>"));
        this.vectorB.setChangable(false, false);
        this.vectorB.setMoveable(false);
    }
    
    public void prepareVectors9() {
        this.vectorA.setColor(this.A_COLOR);
        this.vectorA.showInfo = true;
        this.vectorA.setLabelSide(0);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector: d>"));
        this.vectorA.setChangable(false, false);
        this.vectorA.setMoveable(false);
        this.vectorB.setColor(this.B_COLOR);
        this.vectorB.showInfo = true;
        this.vectorB.setLabelSide(1);
        this.vectorB.setLabel((VectorLabelComponent)new ComplexString("<vector: F>"));
        this.vectorB.setChangable(false, false);
        this.vectorB.setMoveable(false);
    }
    
    public double round(final double n) {
        if (n >= 100.0) {
            return (int)n;
        }
        if (n >= 10.0) {
            return (int)(10.0 * n + 0.5) / 10.0;
        }
        if (n >= 0.0) {
            return (int)(10.0 * n + 0.5) / 10.0;
        }
        if (n <= -100.0) {
            return (int)n;
        }
        if (n <= -10.0) {
            return (int)(10.0 * n - 0.5) / 10.0;
        }
        if (n < 0.0) {
            return (int)(10.0 * n - 0.5) / 10.0;
        }
        return n;
    }
    
    public void setLines() {
        final Point2D.Double double1 = new Point2D.Double(0.0, 0.0);
        final Point2D.Double double2 = new Point2D.Double(10.0, 10.0);
        this.vectorAy = new Vector2d(double1, double2, 2);
        this.vectorBy = new Vector2d(double1, double2, 2);
        this.vectorAx = new Vector2d(double1, double2, 2);
        this.vectorBx = new Vector2d(double1, double2, 2);
        final Point2D.Double translateToGraph = this.coords.translateToGraph(new Point2D.Double(this.vectorA.getHead2D().x, this.vectorA.getHead2D().y));
        final Point2D.Double translateFromGraph = this.coords.translateFromGraph(new Point2D.Double(translateToGraph.x, 0.0));
        final Point2D.Double translateFromGraph2 = this.coords.translateFromGraph(new Point2D.Double(0.0, translateToGraph.y));
        this.vectorAx.setHead(this.vectorA.getHead2D());
        this.vectorAy.setHead(this.vectorA.getHead2D());
        this.vectorAx.setTail(new Point2D.Double(translateFromGraph2.x, translateFromGraph2.y));
        this.vectorAy.setTail(new Point2D.Double(translateFromGraph.x, translateFromGraph.y));
        this.ax = this.round(translateToGraph.x);
        this.ay = this.round(translateToGraph.y);
        final Point2D.Double translateToGraph2 = this.coords.translateToGraph(new Point2D.Double(this.vectorB.getHead2D().x, this.vectorB.getHead2D().y));
        final Point2D.Double translateFromGraph3 = this.coords.translateFromGraph(new Point2D.Double(translateToGraph2.x, 0.0));
        final Point2D.Double translateFromGraph4 = this.coords.translateFromGraph(new Point2D.Double(0.0, translateToGraph2.y));
        this.vectorBx.setHead(this.vectorB.getHead2D());
        this.vectorBy.setHead(this.vectorB.getHead2D());
        this.vectorBx.setTail(new Point2D.Double(translateFromGraph4.x, translateFromGraph4.y));
        this.vectorBy.setTail(new Point2D.Double(translateFromGraph3.x, translateFromGraph3.y));
        this.bx = this.round(translateToGraph2.x);
        this.by = this.round(translateToGraph2.y);
        this.vectorAy.setColor(this.A_COLOR);
        this.vectorAy.showInfo = false;
        this.vectorAy.setChangable(false, false);
        this.vectorAy.setMoveable(false);
        this.vectorBy.setColor(this.B_COLOR);
        this.vectorBy.showInfo = false;
        this.vectorBy.setChangable(false, false);
        this.vectorBy.setMoveable(false);
        this.vectorAx.setColor(this.A_COLOR);
        this.vectorAx.showInfo = false;
        this.vectorAx.setChangable(false, false);
        this.vectorAx.setMoveable(false);
        this.vectorBx.setColor(this.B_COLOR);
        this.vectorBx.showInfo = false;
        this.vectorBx.setChangable(false, false);
        this.vectorBx.setMoveable(false);
    }
    
    public void setupCoords() {
        (this.coords = new CartGraph((int)this.center.x, (int)this.center.y, 90.0, 0.0)).setHeight(this.getSize().height / 2 - 15);
        this.coords.setWidth(this.getSize().width / 2 - 15);
        this.coords.zoom = 20.0;
        this.coords.number_axes = false;
    }
    
    public void start() {
        this.adapter.setActive(true);
        this.repaint();
    }
    
    public void stop() {
        this.adapter.setActive(false);
    }
    
    public Vector2d theVectorAt(final Point point) {
        if (this.vectorA.checkPoint(point.x, point.y)) {
            return this.vectorA;
        }
        if (this.vectorB.checkPoint(point.x, point.y)) {
            return this.vectorB;
        }
        return null;
    }
    
    public Point2D.Double translatePoint(final Point2D.Double double1, final double n, final double n2) {
        return new Point2D.Double(double1.getX() + Math.cos(n) * n2, double1.getY() - Math.sin(n) * n2);
    }
    
    public Point2D.Double translatePoint(final Point point, final double n, final double n2) {
        return new Point2D.Double(point.x + Math.cos(n) * n2, point.y - Math.sin(n) * n2);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
