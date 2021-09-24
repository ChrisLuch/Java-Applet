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
import java.applet.Applet;
import ca.ucalgary.phas.map.utilities.ParameterUtils;
import java.awt.FontMetrics;
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

public class DotProduct extends JApplet implements PageBrowserListener, MouseListener, MouseMotionListener
{
    private PageBrowserAdapter adapter;
    private Font font;
    private Font bffont;
    private Font itfont;
    private Font symbol_font;
    private int currentPage;
    protected Image offscreenImg;
    protected Graphics offscreen;
    protected int appWidth;
    protected int appHeight;
    protected Color panelColor;
    protected Color backColor;
    protected Color thetaColor;
    protected Color A_COLOR;
    protected Color B_COLOR;
    private boolean mouseDown;
    private boolean over180;
    private Point mouseDownPoint;
    private Random rand;
    protected Point2D.Double center;
    protected int theta;
    protected double aDir;
    protected double bDir;
    protected int thetaSize;
    protected double dot;
    protected int zoom;
    protected Vector2d vectorA;
    protected Vector2d vectorB;
    protected Vector2d currentVector;
    static final String THETA = "\u03b8";
    static final double A_LENGTH = 120.0;
    static final double B_LENGTH = 180.0;
    static final double scale = 20.0;
    protected JPanel contentPanel;
    private int page;
    boolean usePageBrowser;
    
    public DotProduct() {
        this.panelColor = Color.white;
        this.backColor = Color.lightGray;
        this.thetaColor = Color.blue;
        this.A_COLOR = Color.red;
        this.B_COLOR = Color.orange;
        this.mouseDown = false;
        this.over180 = false;
        this.mouseDownPoint = null;
        this.rand = new Random();
        this.center = new Point2D.Double();
        this.thetaSize = 30;
        this.vectorA = null;
        this.vectorB = null;
        this.currentVector = null;
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
        final double n4 = n * n2 * Math.cos(this.theta * 3.141592653589793 / 180.0);
        if (n4 < 0.0) {
            this.dot = n4;
        }
        else {
            this.dot = n4;
        }
    }
    
    public void doCommand(final String str) {
        if (str.equals("page1")) {
            this.page = 1;
            this.doPage1();
        }
        else if (str.equals("page2")) {
            this.page = 2;
        }
        else if (str.equals("page3")) {
            this.page = 3;
        }
        else if (str.equals("page4")) {
            this.page = 4;
        }
        else if (str.equals("page5")) {
            this.doPage5();
        }
        else if (str.equals("page6")) {
            this.page = 6;
        }
        else if (str.equals("page7")) {
            this.page = 7;
            this.doPage7();
        }
        else {
            System.out.println("invalid command: " + str);
        }
        this.repaint();
    }
    
    public void doPage1() {
        final double n = 120.0;
        final double n2 = 0.2617993877991494;
        final Point2D.Double center = this.center;
        this.vectorA = new Vector2d(center, this.translatePoint(center, n2, n));
        final double n3 = 180.0;
        final double n4 = this.vectorA.getDirection() + 1.0471975511965976;
        final Point2D.Double center2 = this.center;
        this.vectorB = new Vector2d(center2, this.translatePoint(center2, n4, n3));
        this.prepareVectors();
    }
    
    public void doPage5() {
        final double n = 120.0;
        final double n2 = 0.2617993877991494;
        final Point2D.Double center = this.center;
        this.vectorA = new Vector2d(center, this.translatePoint(center, n2, n));
        final double n3 = 180.0;
        final double n4 = this.vectorA.getDirection() + 2.0943951023931953;
        final Point2D.Double center2 = this.center;
        this.vectorB = new Vector2d(center2, this.translatePoint(center2, n4, n3));
        this.prepareVectors();
    }
    
    public void doPage7() {
        final double n = 120.0;
        final double n2 = 0.2617993877991494;
        final Point2D.Double center = this.center;
        this.vectorA = new Vector2d(center, this.translatePoint(center, n2, n));
        final double n3 = 180.0;
        final double n4 = this.vectorA.getDirection() + 1.5707963267948966;
        final Point2D.Double center2 = this.center;
        this.vectorB = new Vector2d(center2, this.translatePoint(center2, n4, n3));
        this.prepareVectors();
        this.vectorA.setChangable(true, false);
        this.vectorB.setChangable(true, false);
    }
    
    public static void drawDegree(final Graphics graphics, final int n, final int n2) {
        graphics.drawOval(n, n2 - 10, 4, 4);
    }
    
    public static void drawDot(final Graphics graphics, final int n, final int n2) {
        graphics.fillOval(n, n2 - 10 / 2, 4, 4);
    }
    
    public static void drawTheta(final Graphics graphics, final int n, final int n2) {
        graphics.drawString("\u03b8", n - 2, n2 + 2);
    }
    
    public static void drawTheta(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.drawString("\u03b8", n, n2);
    }
    
    void drawThetaArc(final Graphics graphics) {
        final Point2D.Double double1 = new Point2D.Double(this.center.x, this.center.y);
        final Point2D.Double double2 = new Point2D.Double(this.center.x, this.center.y);
        graphics.setColor(this.thetaColor);
        this.vectorA.getDirection();
        this.vectorB.getDirection();
        final Point2D.Double translatePoint = this.translatePoint(double1, 2.356194490192345, 2 * this.thetaSize / 3);
        if (this.aDir > this.bDir) {
            if (this.over180) {
                final Point2D.Double translatePoint2 = this.translatePoint(double2, this.bDir - this.theta * 3.141592653589793 / 180.0 / 2.0, this.thetaSize);
                graphics.drawArc((int)translatePoint.x, (int)translatePoint.y, this.thetaSize, this.thetaSize, (int)(this.vectorA.getDirection() / 3.141592653589793 * 180.0), this.theta);
                drawTheta(graphics, (int)translatePoint2.x, (int)translatePoint2.y);
            }
            else {
                final Point2D.Double translatePoint3 = this.translatePoint(double2, this.bDir + this.theta * 3.141592653589793 / 180.0 / 2.0, this.thetaSize);
                graphics.drawArc((int)translatePoint.x, (int)translatePoint.y, this.thetaSize, this.thetaSize, (int)(this.vectorA.getDirection() / 3.141592653589793 * 180.0), -1 * this.theta);
                drawTheta(graphics, (int)translatePoint3.x, (int)translatePoint3.y);
            }
        }
        else if (this.over180) {
            final Point2D.Double translatePoint4 = this.translatePoint(double2, this.aDir - this.theta * 3.141592653589793 / 180.0 / 2.0, this.thetaSize);
            graphics.drawArc((int)translatePoint.x, (int)translatePoint.y, this.thetaSize, this.thetaSize, (int)(this.vectorB.getDirection() / 3.141592653589793 * 180.0), this.theta);
            drawTheta(graphics, (int)translatePoint4.x, (int)translatePoint4.y);
        }
        else {
            final Point2D.Double translatePoint5 = this.translatePoint(double2, this.aDir + this.theta * 3.141592653589793 / 180.0 / 2.0, this.thetaSize);
            graphics.drawArc((int)translatePoint.x, (int)translatePoint.y, this.thetaSize, this.thetaSize, (int)(this.vectorB.getDirection() / 3.141592653589793 * 180.0), -1 * this.theta);
            drawTheta(graphics, (int)translatePoint5.x, (int)translatePoint5.y);
        }
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
    
    public boolean inBounds(final Point point) {
        return point.x >= 20 && point.x <= this.getSize().width - 20 && point.y >= 20 && point.y <= this.getSize().height - 100;
    }
    
    public void init() {
        this.usePageBrowser = ParameterUtils.getBooleanParameter((Applet)this, "PAGE_BROWSER_PRESENT", false);
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
        this.symbol_font = Font.getFont("Symbol");
        this.offscreenImg = this.createImage(this.appWidth, this.appHeight);
        this.offscreen = this.offscreenImg.getGraphics();
        final Point point = new Point(this.appWidth / 2, this.appHeight / 2);
        this.center = new Point2D.Double(point.x, point.y);
        this.currentPage = 1;
        if (this.usePageBrowser) {
            this.adapter = new PageBrowserAdapter((PageBrowserListener)this, "Dot1");
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.currentVector = this.theVectorAt(mouseEvent.getPoint());
        if (this.currentVector != null) {
            this.currentVector.setSelected(true);
            this.repaint();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.mouseDown) {
            final Point point = mouseEvent.getPoint();
            if (this.inBounds(point) && this.currentVector != null) {
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
                this.repaint();
            }
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
            this.mouseDownPoint = point;
            this.mouseDown = true;
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.currentVector = null;
        this.vectorA.setSelected(false);
        this.vectorB.setSelected(false);
    }
    
    public void paint(final Graphics graphics) {
        if (this.vectorA != null && this.vectorB != null) {
            this.calc();
            this.offscreen.setFont(this.font);
            this.offscreen.setColor(this.backColor);
            this.offscreen.fillRect(0, 0, this.appWidth, this.appHeight);
            this.offscreen.setColor(this.panelColor);
            this.offscreen.fillRect(20, 20, this.appWidth - 40, this.appHeight - 120);
            this.offscreen.setColor(Color.black);
            final FontMetrics fontMetrics = this.offscreen.getFontMetrics(this.font);
            final String s = "a";
            final String s2 = "b";
            final String s3 = "O";
            final String str = "o";
            this.offscreen.drawRect(20, 20, this.appWidth - 40, this.appHeight - 120);
            this.offscreen.drawRect(0, 0, this.appWidth - 1, this.appHeight - 1);
            final int n = 20;
            final int n2 = this.appHeight - 80;
            this.offscreen.drawString("| ", n, n2);
            final int n3 = n + fontMetrics.stringWidth("| ");
            drawVectorLabel(this.offscreen, n3, n2, s, this.bffont);
            final int n4 = n3 + fontMetrics.stringWidth(s);
            this.offscreen.drawString(" |  =   ", n4, n2);
            final int n5 = n4 + fontMetrics.stringWidth(" | =   ");
            this.offscreen.setFont(this.itfont);
            this.offscreen.drawString("a", n5, n2);
            final int n6 = n5 + fontMetrics.stringWidth("a");
            this.offscreen.setFont(this.font);
            this.offscreen.drawString("  =  " + this.round(this.vectorA.getLength() / 20.0), n6, n2);
            final int n7 = 20;
            final int n8 = this.appHeight - 60;
            this.offscreen.drawString("| ", n7, n8);
            final int n9 = n7 + fontMetrics.stringWidth("| ");
            drawVectorLabel(this.offscreen, n9, n8, s2, this.bffont);
            final int n10 = n9 + fontMetrics.stringWidth(s2);
            this.offscreen.drawString(" |  =   ", n10, n8);
            final int n11 = n10 + fontMetrics.stringWidth(" | =  ");
            this.offscreen.setFont(this.itfont);
            this.offscreen.drawString("b", n11, n8);
            final int n12 = n11 + fontMetrics.stringWidth("b");
            this.offscreen.setFont(this.font);
            this.offscreen.drawString("  =  " + this.round(this.vectorB.getLength() / 20.0), n12, n8);
            final int n13 = 20;
            final int n14 = this.appHeight - 40;
            drawTheta(this.offscreen, n13, n14, fontMetrics.stringWidth(s3));
            final int n15 = n13 + fontMetrics.stringWidth(s3);
            this.offscreen.drawString("  =  " + this.theta, n15, n14);
            drawDegree(this.offscreen, n15 + fontMetrics.stringWidth("  =  " + this.theta), n14);
            final int n16 = 20;
            final int n17 = this.appHeight - 20;
            drawVectorLabel(this.offscreen, n16, n17, s, this.bffont);
            final int n18 = n16 + fontMetrics.stringWidth(s + " ");
            drawDot(this.offscreen, n18, n17);
            final int n19 = n18 + fontMetrics.stringWidth(str);
            drawVectorLabel(this.offscreen, n19, n17, s2, this.bffont);
            final int n20 = n19 + fontMetrics.stringWidth(s2);
            this.offscreen.drawString("  =  ", n20, n17);
            final int n21 = n20 + fontMetrics.stringWidth("  =  ");
            this.offscreen.setFont(this.itfont);
            this.offscreen.drawString("ab ", n21, n17);
            final int n22 = n21 + fontMetrics.stringWidth("ab ");
            this.offscreen.setFont(this.font);
            this.offscreen.drawString("cos ", n22, n17);
            final int n23 = n22 + fontMetrics.stringWidth("cos ");
            drawTheta(this.offscreen, n23, n17, fontMetrics.stringWidth(s3));
            this.offscreen.drawString("  =  " + this.round(this.dot), n23 + fontMetrics.stringWidth(s3), n17);
            this.vectorA.paint(this.offscreen);
            this.vectorB.paint(this.offscreen);
            this.drawThetaArc(this.offscreen);
            graphics.drawImage(this.offscreenImg, 0, 0, this);
        }
    }
    
    public void prepareVectors() {
        this.vectorA.setColor(this.A_COLOR);
        this.vectorB.setColor(this.B_COLOR);
        this.vectorA.showInfo = true;
        this.vectorA.setLabelSide(1);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector: a>"));
        this.vectorB.showInfo = true;
        this.vectorB.setLabelSide(0);
        this.vectorB.setLabel((VectorLabelComponent)new ComplexString("<vector: b>"));
        this.vectorA.setChangable(false, false);
        this.vectorB.setChangable(false, false);
        this.vectorA.setMoveable(false);
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
    
    public void start() {
        if (this.usePageBrowser) {
            this.adapter.setActive(true);
        }
        else {
            this.page = 7;
            this.doPage7();
        }
        this.repaint();
    }
    
    public void stop() {
        if (this.usePageBrowser) {
            this.adapter.setActive(false);
        }
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
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
