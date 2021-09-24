// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.Graphics;
import ca.ucalgary.phas.map.utilities.vectors.VectorLabelComponent;
import ca.ucalgary.phas.map.utilities.ComplexString;
import ca.ucalgary.phas.map.utilities.MapConstants;
import java.awt.event.ComponentEvent;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;
import java.awt.geom.Point2D;
import java.awt.Point;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import ca.ucalgary.phas.map.contentNavigator.PageBrowserAdapter;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import ca.ucalgary.phas.map.contentNavigator.PageBrowserListener;
import javax.swing.JPanel;

public class ScalarCompsPanel extends JPanel implements PageBrowserListener, MouseListener, MouseMotionListener, ComponentListener
{
    protected PageBrowserAdapter adapter;
    protected boolean usePageBrowser;
    private Font font;
    private Font bffont;
    private Font itfont;
    private int page;
    static final int DEMO = 0;
    static final int SIM = 1;
    private int mode;
    protected Cursor defaultCursor;
    protected Cursor crosshairCursor;
    protected Cursor moveCursor;
    protected int panelWidth;
    protected int panelHeight;
    protected Color backColor;
    protected Color A_COLOR;
    protected Color B_COLOR;
    protected Color H_COLOR;
    protected Color thetaColor;
    protected Color AX_COLOR;
    protected Color AY_COLOR;
    protected int thetaSize;
    private boolean mouseDown;
    private Point mouseDownPoint;
    private boolean over180;
    protected Point2D.Double center;
    protected double aDir;
    protected double xDir;
    protected double ax;
    protected double ay;
    protected double bx;
    protected double by;
    protected double hx;
    protected double hy;
    protected int zoom;
    protected int theta;
    protected boolean showComps;
    protected static final String THETA = "\u03b8";
    protected Vector2d vectorA;
    protected Vector2d vectorB;
    protected Vector2d vectorH;
    protected Vector2d currentVector;
    protected Vector2d vectorA1y;
    protected Vector2d vectorA1x;
    protected Vector2d vectorA2y;
    protected Vector2d vectorA2x;
    protected Vector2d vectorAx;
    protected Vector2d vectorAy;
    protected Vector2d vectorB1y;
    protected Vector2d vectorB1x;
    protected Vector2d vectorB2y;
    protected Vector2d vectorB2x;
    protected Vector2d vectorBx;
    protected Vector2d vectorBy;
    protected Vector2d vectorH1y;
    protected Vector2d vectorH1x;
    protected Vector2d vectorH2y;
    protected Vector2d vectorH2x;
    protected Vector2d vectorHx;
    protected Vector2d vectorHy;
    static final double A_LENGTH = 120.0;
    static final double B_LENGTH = 180.0;
    static final double scale = 20.0;
    private CartGraph coords;
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.sizeChanged();
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.sizeChanged();
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
        this.sizeChanged();
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
        this.sizeChanged();
    }
    
    public void sizeChanged() {
        this.panelWidth = this.getSize().width;
        this.panelHeight = this.getSize().height;
        this.zoom = this.panelWidth / 10;
        this.setCenter(this.panelWidth / 2, this.panelHeight / 2 - 35);
    }
    
    public void setCenter(final double x, final double y) {
        final double n = x - this.center.x;
        final double n2 = y - this.center.y;
        this.center.x = x;
        this.center.y = y;
        this.vectorH.translateVector(n, n2);
        this.vectorA.translateVector(n, n2);
        this.vectorB.translateVector(n, n2);
        if (this.coords != null) {
            this.coords.translate(n, n2);
        }
    }
    
    public ScalarCompsPanel(final int mode, final boolean usePageBrowser) {
        this.page = 1;
        this.mode = 0;
        this.backColor = Color.lightGray;
        this.A_COLOR = Color.red;
        this.B_COLOR = Color.orange;
        this.H_COLOR = Color.magenta;
        this.thetaColor = Color.blue;
        this.AX_COLOR = Color.green;
        this.AY_COLOR = Color.yellow;
        this.thetaSize = 30;
        this.mouseDown = false;
        this.mouseDownPoint = null;
        this.over180 = false;
        this.center = new Point2D.Double();
        this.showComps = false;
        this.mode = mode;
        this.usePageBrowser = usePageBrowser;
        this.defaultCursor = new Cursor(0);
        this.crosshairCursor = new Cursor(1);
        this.moveCursor = new Cursor(13);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addComponentListener(this);
        this.font = new Font("Arial", 0, 12);
        this.bffont = new Font("Arial", 1, 12);
        this.itfont = new Font("Arial", 2, 12);
        this.setBackground(Color.white);
        this.setBorder(MapConstants.getDefaultPanelBorder());
        this.vectorH = new Vector2d();
        this.vectorB = new Vector2d();
        this.vectorA = new Vector2d();
        this.vectorA1x = new Vector2d();
        this.vectorA1y = new Vector2d();
        this.vectorA2x = new Vector2d();
        this.vectorA2y = new Vector2d();
        this.vectorB1x = new Vector2d();
        this.vectorB1y = new Vector2d();
        this.vectorB2x = new Vector2d();
        this.vectorB2y = new Vector2d();
        this.vectorH1x = new Vector2d();
        this.vectorH1y = new Vector2d();
        this.vectorH2x = new Vector2d();
        this.vectorH2y = new Vector2d();
        if (this.usePageBrowser) {
            this.adapter = new PageBrowserAdapter((PageBrowserListener)this, "Scalar");
        }
    }
    
    private void calcTheta() {
        final double n = this.vectorA.getLength() / 20.0;
        this.aDir = this.vectorA.getDirection();
        if (this.aDir < 0.0) {
            this.aDir += 6.283185307179586;
        }
        this.xDir = this.coords.getXAngle();
        if (this.xDir < 0.0) {
            this.xDir += 6.283185307179586;
        }
        double n2;
        if (this.aDir > this.xDir) {
            n2 = this.aDir - this.xDir;
        }
        else {
            n2 = this.xDir - this.aDir;
        }
        if (n2 > 3.141592653589793) {
            n2 = 6.283185307179586 - n2;
            this.over180 = true;
        }
        else {
            this.over180 = false;
        }
        this.theta = (int)(n2 / 3.141592653589793 * 180.0 + 0.5);
        if (this.ay < 0.0) {
            this.theta *= -1;
        }
    }
    
    public void doCommand(final String s) {
        System.out.println("cmd: " + s);
        this.setupCoords();
        this.coords.moveable = false;
        this.vectorA1x.setVisible(false);
        this.vectorA1y.setVisible(false);
        this.vectorA2x.setVisible(false);
        this.vectorA2y.setVisible(false);
        this.vectorB1x.setVisible(false);
        this.vectorB1y.setVisible(false);
        this.vectorB2x.setVisible(false);
        this.vectorB2y.setVisible(false);
        this.vectorH1x.setVisible(false);
        this.vectorH1y.setVisible(false);
        this.vectorH2x.setVisible(false);
        this.vectorH2y.setVisible(false);
        this.showComps = false;
        if (s.equals("page1")) {
            this.page = 1;
            if (this.mode == 0) {
                this.doPage1();
                this.showComps = true;
            }
            else {
                this.doSimPage1();
                this.coords.moveable = true;
            }
        }
        else if (s.equals("page2")) {
            this.page = 2;
            this.doPage2();
            this.showComps = true;
        }
        else if (s.equals("page3")) {
            this.page = 3;
            this.doPage2();
            this.vectorA.setMoveable(true);
            this.showComps = true;
        }
        else if (s.equals("page4")) {
            this.page = 4;
            this.doPage2();
            this.coords.moveable = true;
            this.showComps = true;
        }
        else if (s.equals("page5")) {
            this.page = 5;
            this.doPage5();
            this.showComps = true;
        }
        else if (s.equals("page6")) {
            this.page = 6;
            this.doPage5();
            this.coords.moveable = true;
            this.vectorA.setChangable(true, false);
            this.showComps = true;
        }
        else if (s.equals("page7")) {
            this.page = 7;
            this.doPage5();
            this.coords.moveable = true;
            this.vectorA.setChangable(true, false);
            this.showComps = true;
        }
        else if (s.equals("page8")) {
            this.page = 8;
            this.doPage5();
            this.coords.moveable = true;
            this.vectorA.setChangable(true, false);
            this.showComps = true;
        }
        else if (s.equals("page9")) {
            this.page = 9;
            this.doPage9();
        }
        else if (s.equals("page10")) {
            this.page = 10;
            this.doPage10();
        }
        else if (s.equals("page11")) {
            this.page = 11;
            this.doPage11();
        }
        else if (s.equals("page12")) {
            this.page = 12;
            this.doPage11();
            this.vectorA.setVisible(true);
            this.vectorB.setVisible(true);
            this.vectorH.setVisible(true);
            this.vectorA.setTail(this.vectorB.getHead2D());
            this.vectorA.setHead(new Point2D.Double(this.vectorB.getHead2D().x + 160.0, this.vectorB.getHead2D().y - 60.0));
            this.vectorH.setTail(this.center.x, this.center.y);
            this.vectorH.setHead(this.vectorA.getHead2D().x, this.vectorA.getHead2D().y);
            this.vectorH.setColor(this.H_COLOR);
            this.vectorH.showInfo = true;
            this.vectorH.setLabelSide(0);
            this.vectorH.setLabel((VectorLabelComponent)new ComplexString("<vector: H>"));
            this.vectorH.setChangable(false, false);
            this.vectorH.setMoveable(false);
        }
        else if (s.equals("page13")) {
            this.page = 13;
            this.doPage13();
        }
        else if (s.equals("page14")) {
            this.page = 14;
            this.doPage13();
            this.vectorA.setVisible(true);
            this.vectorB.setVisible(true);
            this.vectorH.setVisible(true);
            this.vectorB.setTail(this.vectorA.getHead2D());
            this.vectorB.setHead(this.vectorH.getHead2D());
            this.vectorB.setColor(this.B_COLOR);
            this.vectorB.showInfo = true;
            this.vectorB.setLabelSide(0);
            this.vectorB.setLabel((VectorLabelComponent)new ComplexString("<vector: B>"));
            this.vectorB.setChangable(false, false);
            this.vectorB.setMoveable(false);
        }
        else {
            System.out.println("invalid command :" + s);
        }
        this.repaint();
    }
    
    public void doPage1() {
        this.vectorA.setVisible(true);
        this.vectorB.setVisible(true);
        this.vectorH.setVisible(false);
        this.vectorA.setTail(this.center);
        this.vectorA.setHead(this.coords.translateFromGraph(new Point2D.Double(8.0, 3.0)));
        this.vectorB.setTail(this.center);
        this.vectorB.setHead(this.coords.translateFromGraph(new Point2D.Double(-4.0, 5.0)));
        this.vectorA.setColor(this.A_COLOR);
        this.vectorA.showInfo = true;
        this.vectorA.setLabelSide(0);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector: F>"));
        this.vectorA.setChangable(false, false);
        this.vectorA.setMoveable(false);
        this.vectorB.setColor(this.B_COLOR);
        this.vectorB.showInfo = true;
        this.vectorB.setLabelSide(1);
        this.vectorB.setLabel((VectorLabelComponent)new ComplexString("<vector: G>"));
        this.vectorB.setChangable(false, false);
        this.vectorB.setMoveable(false);
    }
    
    public void doPage10() {
        this.vectorA.setVisible(true);
        this.vectorB.setVisible(true);
        this.vectorH.setVisible(true);
        this.vectorA.setTail(this.center);
        this.vectorA.setHead(this.coords.translateFromGraph(new Point2D.Double(-3.0, 4.0)));
        this.vectorB.setTail(this.center);
        this.vectorB.setHead(this.coords.translateFromGraph(new Point2D.Double(6.0, -8.0)));
        this.vectorH.setTail(this.center);
        this.vectorH.setHead(this.coords.translateFromGraph(new Point2D.Double(-1.5, 2.0)));
        this.vectorA.setColor(this.A_COLOR);
        this.vectorA.showInfo = true;
        this.vectorA.setLabelSide(0);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector: d>"));
        this.vectorA.setLabelPosition(1.0);
        this.vectorA.setChangable(false, false);
        this.vectorA.setMoveable(false);
        this.vectorB.setColor(this.B_COLOR);
        this.vectorB.showInfo = true;
        this.vectorB.setLabelSide(1);
        this.vectorB.setLabel((VectorLabelComponent)new ComplexString("-2<vector: d>"));
        this.vectorB.setChangable(false, false);
        this.vectorB.setMoveable(false);
        this.vectorH.setColor(this.H_COLOR);
        this.vectorH.showInfo = true;
        this.vectorH.setLabelSide(0);
        this.vectorH.setLabel((VectorLabelComponent)new ComplexString("<vector: d>/2"));
        this.vectorH.setLabelPosition(1.0);
        this.vectorH.setChangable(false, false);
        this.vectorH.setMoveable(false);
    }
    
    public void doPage11() {
        this.vectorA.setVisible(true);
        this.vectorB.setVisible(true);
        this.vectorH.setVisible(false);
        this.vectorA.setTail(this.center);
        this.vectorA.setHead(this.coords.translateFromGraph(new Point2D.Double(8.0, 3.0)));
        this.vectorB.setTail(this.center);
        this.vectorB.setHead(this.coords.translateFromGraph(new Point2D.Double(-4.0, 5.0)));
        this.vectorA.setColor(this.A_COLOR);
        this.vectorA.showInfo = true;
        this.vectorA.setLabelSide(0);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector: F>"));
        this.vectorA.setChangable(false, false);
        this.vectorA.setMoveable(false);
        this.vectorB.setColor(this.B_COLOR);
        this.vectorB.showInfo = true;
        this.vectorB.setLabelSide(1);
        this.vectorB.setLabel((VectorLabelComponent)new ComplexString("<vector: G>"));
        this.vectorB.setChangable(false, false);
        this.vectorB.setMoveable(false);
    }
    
    public void doPage13() {
        this.vectorA.setVisible(true);
        this.vectorB.setVisible(false);
        this.vectorH.setVisible(true);
        this.vectorA.setTail(this.center);
        this.vectorA.setHead(this.coords.translateFromGraph(new Point2D.Double(5.0, 5.0)));
        this.vectorH.setTail(this.center);
        this.vectorH.setHead(new Point2D.Double(this.center.x - 120.0, this.center.y - 160.0));
        this.vectorA.setColor(this.A_COLOR);
        this.vectorA.showInfo = true;
        this.vectorA.setLabelSide(0);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector: A>"));
        this.vectorA.setChangable(false, false);
        this.vectorA.setMoveable(false);
        this.vectorH.setColor(this.H_COLOR);
        this.vectorH.showInfo = true;
        this.vectorH.setLabelSide(1);
        this.vectorH.setLabel((VectorLabelComponent)new ComplexString("<vector: C>"));
        this.vectorH.setChangable(false, false);
        this.vectorH.setMoveable(false);
    }
    
    public void doPage2() {
        this.vectorA.setVisible(true);
        this.vectorB.setVisible(false);
        this.vectorH.setVisible(false);
        this.vectorA.setTail(this.center);
        this.vectorA.setHead(this.coords.translateFromGraph(new Point2D.Double(-4.0, 6.0)));
        this.vectorA.setColor(this.A_COLOR);
        this.vectorA.showInfo = true;
        this.vectorA.setLabelSide(0);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector: a>"));
        this.vectorA.setChangable(false, false);
        this.vectorA.setMoveable(false);
    }
    
    public void doPage5() {
        this.vectorA.setVisible(true);
        this.vectorB.setVisible(false);
        this.vectorH.setVisible(false);
        this.coords.setXAngle(-0.2617993877991494);
        this.vectorA.setTail(this.center);
        this.vectorA.setHead(this.coords.translateFromGraph(new Point2D.Double(-3.0, 4.0)));
        this.vectorA.setColor(this.A_COLOR);
        this.vectorA.showInfo = true;
        this.vectorA.setLabelSide(0);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector: a>"));
        this.vectorA.setChangable(false, false);
        this.vectorA.setMoveable(false);
    }
    
    public void doPage9() {
        this.vectorA.setVisible(true);
        this.vectorB.setVisible(false);
        this.vectorH.setVisible(false);
        this.vectorA.setTail(this.center);
        this.vectorA.setHead(this.coords.translateFromGraph(new Point2D.Double(-3.0, 4.0)));
        this.vectorA.setColor(this.A_COLOR);
        this.vectorA.showInfo = true;
        this.vectorA.setLabelSide(0);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector: d>"));
        this.vectorA.setLabelPosition(1.0);
        this.vectorA.setChangable(false, false);
        this.vectorA.setMoveable(false);
    }
    
    public void doSimPage1() {
        this.vectorA.setVisible(true);
        this.vectorB.setVisible(false);
        this.vectorH.setVisible(false);
        this.coords.setXAngle(-0.2617993877991494);
        this.vectorA.setTail(this.center);
        this.vectorA.setHead(this.coords.translateFromGraph(new Point2D.Double(-3.0, 4.0)));
        this.vectorA.setColor(this.A_COLOR);
        this.vectorA.showInfo = true;
        this.vectorA.setLabelSide(0);
        this.vectorA.setLabel((VectorLabelComponent)new ComplexString("<vector: a>"));
        this.vectorA.setChangable(true, true);
        this.vectorA.setMoveable(true);
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
        this.calcTheta();
        final Point2D.Double double1 = new Point2D.Double(this.center.x, this.center.y);
        final Point2D.Double double2 = new Point2D.Double(this.center.x, this.center.y);
        graphics.setColor(this.thetaColor);
        final Point2D.Double translatePoint = this.translatePoint(double2, this.xDir + this.theta * 3.141592653589793 / 180.0 / 2.0, this.thetaSize);
        graphics.drawArc((int)double1.x - this.thetaSize / 2, (int)double1.y - this.thetaSize / 2, this.thetaSize, this.thetaSize, (int)(this.coords.getXAngle() / 3.141592653589793 * 180.0), this.theta);
        graphics.drawString("\u03b8", (int)translatePoint.x + 2, (int)translatePoint.y + 2);
        graphics.setColor(color);
    }
    
    public int drawVectorLabel(final Graphics graphics, final int n, final int n2, final String str, final Font font) {
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
    
    public double findXComp(final Vector2d vector2d) {
        double n = this.coords.translateToGraph(new Point2D.Double(vector2d.getTail2D().x, vector2d.getTail2D().y)).x - this.coords.translateToGraph(new Point2D.Double(vector2d.getHead2D().x, vector2d.getHead2D().y)).x;
        if ((this.page == 6 || this.page == 7 || this.page == 8 || this.mode == 1) && n < -3.05 && n > -3.06) {
            n += 0.1;
        }
        return this.round(n);
    }
    
    public double findYComp(final Vector2d vector2d) {
        return this.round(this.coords.translateToGraph(new Point2D.Double(vector2d.getTail2D().x, vector2d.getTail2D().y)).y - this.coords.translateToGraph(new Point2D.Double(vector2d.getHead2D().x, vector2d.getHead2D().y)).y);
    }
    
    public boolean inBounds(final Point point) {
        return point.x >= 20 && point.x <= this.getSize().width - 20 && point.y >= 10 && point.y <= this.getSize().height - 110;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.currentVector = this.theVectorAt(mouseEvent.getPoint());
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
        this.checkCursor(mouseEvent.getPoint());
    }
    
    public void checkCursor(final Point point) {
        if (this.coords.onXTip(point) && this.coords.moveable) {
            this.setCursor(this.crosshairCursor);
        }
        else if (this.coords.onYTip(point) && this.coords.moveable) {
            this.setCursor(this.crosshairCursor);
        }
        else {
            this.currentVector = this.theVectorAt(point);
            if (this.currentVector != null && this.currentVector.isVisible()) {
                if (this.currentVector.onHeadAnch(point) && this.currentVector.isHeadChangable()) {
                    this.setCursor(this.crosshairCursor);
                }
                else if (this.currentVector.onTailAnch(point) && this.currentVector.isTailChangable()) {
                    this.setCursor(this.crosshairCursor);
                }
                else if (this.currentVector.isMoveable()) {
                    this.setCursor(this.moveCursor);
                }
            }
            else {
                this.setCursor(this.defaultCursor);
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
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
                final Vector2d currentVector = this.currentVector;
                if (currentVector.onHeadAnch(point) && currentVector.isHeadChangable()) {
                    currentVector.setHeadMoving(true);
                }
                else if (currentVector.onTailAnch(point) && currentVector.isTailChangable()) {
                    currentVector.setTailMoving(true);
                }
                else if (currentVector.isMoveable()) {
                    currentVector.setMoving(true);
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
        if (this.vectorB != null) {
            this.vectorB.setSelected(false);
        }
        if (this.coords != null) {
            if (this.coords.xIsMoving()) {
                this.coords.setXMoving(false);
            }
            if (this.coords.yIsMoving()) {
                this.coords.setYMoving(false);
            }
        }
        this.checkCursor(mouseEvent.getPoint());
    }
    
    public void paintComponent(final Graphics graphics) {
        if (this.getSize().width != this.panelWidth || this.getSize().height != this.panelHeight) {
            this.sizeChanged();
        }
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.panelWidth, this.panelHeight);
        graphics.setFont(this.itfont);
        graphics.setColor(Color.black);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.itfont);
        if (this.page == 1 && this.mode == 0) {
            this.vectorA.paint(graphics);
            this.vectorB.paint(graphics);
        }
        else {
            this.coords.paintGraph(graphics);
            if (this.vectorA.isVisible()) {
                this.setLinesA();
                this.vectorA.paint(graphics);
                if (this.showComps) {
                    this.vectorA1y.paint(graphics);
                    this.vectorA1x.paint(graphics);
                    this.vectorA2y.paint(graphics);
                    this.vectorA2x.paint(graphics);
                }
                if (this.page <= 10) {
                    this.vectorAy.paint(graphics);
                    graphics.setColor(this.AY_COLOR);
                    graphics.drawLine((int)this.vectorAy.getHead2D().x + 1, (int)this.vectorAy.getHead2D().y, (int)this.vectorAy.getTail2D().x + 1, (int)this.vectorAy.getTail2D().y);
                    graphics.drawLine((int)this.vectorAy.getHead2D().x - 1, (int)this.vectorAy.getHead2D().y, (int)this.vectorAy.getTail2D().x - 1, (int)this.vectorAy.getTail2D().y);
                    this.vectorAx.paint(graphics);
                    graphics.setColor(this.AX_COLOR);
                    graphics.drawLine((int)this.vectorAx.getHead2D().x, (int)this.vectorAx.getHead2D().y + 1, (int)this.vectorAx.getTail2D().x, (int)this.vectorAx.getTail2D().y + 1);
                    graphics.drawLine((int)this.vectorAx.getHead2D().x, (int)this.vectorAx.getHead2D().y - 1, (int)this.vectorAx.getTail2D().x, (int)this.vectorAx.getTail2D().y - 1);
                }
                if (this.page == 11) {
                    this.vectorAy.setColor(this.A_COLOR);
                    this.vectorAy.paint(graphics);
                    graphics.setColor(this.A_COLOR);
                    graphics.drawLine((int)this.vectorAy.getHead2D().x + 5, (int)this.vectorAy.getHead2D().y, (int)this.vectorAy.getTail2D().x + 5, (int)this.vectorAy.getTail2D().y);
                    graphics.drawLine((int)this.vectorAy.getHead2D().x + 6, (int)this.vectorAy.getHead2D().y, (int)this.vectorAy.getTail2D().x + 6, (int)this.vectorAy.getTail2D().y);
                    this.vectorAx.setColor(this.A_COLOR);
                    graphics.setColor(this.A_COLOR);
                    graphics.drawLine((int)this.vectorAx.getHead2D().x, (int)this.vectorAx.getHead2D().y - 5, (int)this.vectorAx.getTail2D().x, (int)this.vectorAx.getTail2D().y - 5);
                    graphics.drawLine((int)this.vectorAx.getHead2D().x, (int)this.vectorAx.getHead2D().y - 6, (int)this.vectorAx.getTail2D().x, (int)this.vectorAx.getTail2D().y - 6);
                }
                if (this.page == 12) {
                    this.vectorAy.setColor(this.A_COLOR);
                    this.vectorAy.paint(graphics);
                    graphics.setColor(this.A_COLOR);
                    graphics.drawLine((int)this.vectorAy.getHead2D().x - 5, (int)this.vectorAy.getHead2D().y, (int)this.vectorAy.getTail2D().x - 5, (int)this.vectorAy.getTail2D().y);
                    graphics.drawLine((int)this.vectorAy.getHead2D().x - 6, (int)this.vectorAy.getHead2D().y, (int)this.vectorAy.getTail2D().x - 6, (int)this.vectorAy.getTail2D().y);
                    this.vectorAx.setColor(this.A_COLOR);
                    graphics.setColor(this.A_COLOR);
                    graphics.drawLine((int)this.vectorAx.getHead2D().x, (int)this.vectorAx.getHead2D().y + 5, (int)this.vectorAx.getTail2D().x, (int)this.vectorAx.getTail2D().y + 5);
                    graphics.drawLine((int)this.vectorAx.getHead2D().x, (int)this.vectorAx.getHead2D().y + 6, (int)this.vectorAx.getTail2D().x, (int)this.vectorAx.getTail2D().y + 6);
                }
            }
            if (this.vectorB.isVisible()) {
                this.setLinesB();
                this.vectorB.paint(graphics);
                if (this.showComps) {
                    this.vectorB1y.paint(graphics);
                    this.vectorB1x.paint(graphics);
                    this.vectorB2y.paint(graphics);
                    this.vectorB2x.paint(graphics);
                }
                if (this.page == 11 || this.page == 12) {
                    graphics.setColor(this.B_COLOR);
                    graphics.drawLine((int)this.vectorBy.getHead2D().x, (int)this.vectorBy.getHead2D().y - 5, (int)this.vectorBy.getTail2D().x, (int)this.vectorBy.getTail2D().y - 5);
                    graphics.drawLine((int)this.vectorBy.getHead2D().x, (int)this.vectorBy.getHead2D().y - 6, (int)this.vectorBy.getTail2D().x, (int)this.vectorBy.getTail2D().y - 6);
                    graphics.setColor(this.B_COLOR);
                    graphics.drawLine((int)this.vectorBx.getHead2D().x - 5, (int)this.vectorBx.getHead2D().y, (int)this.vectorBx.getTail2D().x - 5, (int)this.vectorBx.getTail2D().y);
                    graphics.drawLine((int)this.vectorBx.getHead2D().x - 6, (int)this.vectorBx.getHead2D().y, (int)this.vectorBx.getTail2D().x - 6, (int)this.vectorBx.getTail2D().y);
                }
            }
            if (this.vectorH.isVisible()) {
                this.setLinesH();
                this.vectorH.paint(graphics);
                if (this.showComps) {
                    this.vectorH1y.paint(graphics);
                    this.vectorH1x.paint(graphics);
                    this.vectorH2y.paint(graphics);
                    this.vectorH2x.paint(graphics);
                }
                if (this.page == 11 || this.page == 12) {
                    graphics.setColor(this.H_COLOR);
                    graphics.drawLine((int)this.vectorHy.getHead2D().x, (int)this.vectorHy.getHead2D().y - 5, (int)this.vectorHy.getTail2D().x, (int)this.vectorHy.getTail2D().y - 5);
                    graphics.drawLine((int)this.vectorHy.getHead2D().x, (int)this.vectorHy.getHead2D().y - 6, (int)this.vectorHy.getTail2D().x, (int)this.vectorHy.getTail2D().y - 6);
                    graphics.setColor(this.H_COLOR);
                    graphics.drawLine((int)this.vectorHx.getHead2D().x + 5, (int)this.vectorHx.getHead2D().y, (int)this.vectorHx.getTail2D().x + 5, (int)this.vectorHx.getTail2D().y);
                    graphics.drawLine((int)this.vectorHx.getHead2D().x + 6, (int)this.vectorHx.getHead2D().y, (int)this.vectorHx.getTail2D().x + 6, (int)this.vectorHx.getTail2D().y);
                }
            }
            graphics.setFont(this.font);
            graphics.setColor(this.backColor);
            graphics.fillRect(0, this.panelHeight - 75, this.panelWidth, 75);
            graphics.setColor(MapConstants.lineBorderColor);
            graphics.drawLine(0, this.panelHeight - 75, this.panelWidth, this.panelHeight - 75);
            if ((this.page >= 2 && this.page <= 8) || this.mode == 1) {
                final int n = 100;
                final int n2 = this.panelHeight - 60;
                this.drawString(graphics, n, n2, "a", "x", this.itfont, this.itfont);
                final int n3 = n + fontMetrics.stringWidth("ax");
                graphics.drawString(" = ", n3, n2);
                final int n4 = n3 + fontMetrics.stringWidth(" = ");
                graphics.setColor(this.AX_COLOR);
                graphics.drawString("green", n4, n2);
                final int n5 = n4 + fontMetrics.stringWidth("green");
                graphics.setColor(Color.black);
                graphics.drawString(" line", n5, n2);
                final int n6 = 200;
                this.drawString(graphics, n6, n2, "a", "y", this.itfont, this.itfont);
                final int n7 = n6 + fontMetrics.stringWidth("ax");
                graphics.drawString(" = ", n7, n2);
                final int n8 = n7 + fontMetrics.stringWidth(" = ");
                graphics.setColor(this.AY_COLOR);
                graphics.drawString("yellow", n8, n2);
                final int n9 = n8 + fontMetrics.stringWidth("yellow");
                graphics.setColor(Color.black);
                graphics.drawString(" line", n9, n2);
            }
            if (this.page == 2 || this.page == 3 || this.page == 4) {
                final int n10 = 100;
                final int n11 = this.panelHeight - 30;
                this.drawString(graphics, n10, n11, "a", "x", this.itfont, this.itfont);
                final int n12 = n10 + fontMetrics.stringWidth("ax");
                if (this.page == 2 || this.page == 3) {
                    graphics.drawString("  =  -4.0", n12, n11);
                }
                else {
                    graphics.drawString("  =  ", n12, n11);
                    graphics.drawString("" + this.ax, n12 + fontMetrics.stringWidth("  =  "), n11);
                }
                final int n13 = 200;
                this.drawString(graphics, n13, n11, "a", "y", this.itfont, this.itfont);
                final int n14 = n13 + fontMetrics.stringWidth("ay");
                if (this.page == 2 || this.page == 3) {
                    graphics.drawString("  =  6.0", n14, n11);
                }
                else {
                    graphics.drawString("  =  ", n14, n11);
                    graphics.drawString("" + this.ay, n14 + fontMetrics.stringWidth("  =  "), n11);
                }
            }
            else if (this.page == 5) {
                this.drawThetaArc(graphics);
                final int n15 = 100;
                final int n16 = this.panelHeight - 40;
                graphics.setFont(this.itfont);
                graphics.drawString("a = ", n15, n16);
                final int n17 = n15 + fontMetrics.stringWidth("a = ");
                graphics.setFont(this.font);
                graphics.drawString("5.0", n17, n16);
                final int n18 = 200;
                graphics.drawString("\u03b8", n18, n16);
                final int n19 = n18 + fontMetrics.stringWidth("\u03b8");
                graphics.setFont(this.font);
                graphics.drawString(" = " + this.theta, n19, n16);
                graphics.drawString("o", n19 + fontMetrics.stringWidth(" = " + this.theta), n16 - fontMetrics.getAscent() / 2);
            }
            else if (this.page == 6 || this.page == 7 || this.page == 8 || this.mode == 1) {
                this.drawThetaArc(graphics);
                final int n20 = 100;
                final int n21 = this.panelHeight - 40;
                graphics.drawString("a", n20, n21);
                final int n22 = n20 + fontMetrics.stringWidth("a");
                graphics.setFont(this.font);
                graphics.drawString(" = " + this.round(this.vectorA.getLength() / 20.0), n22, n21);
                final int n23 = 200;
                graphics.drawString("\u03b8", n23, n21);
                final int n24 = n23 + fontMetrics.stringWidth("\u03b8");
                graphics.setFont(this.font);
                graphics.drawString(" = " + this.theta, n24, n21);
                graphics.drawString("o", n24 + fontMetrics.stringWidth(" = " + this.theta), n21 - fontMetrics.getAscent() / 2);
                final int n25 = 100;
                final int n26 = this.panelHeight - 25;
                graphics.setFont(this.font);
                this.drawString(graphics, n25, n26, "a", "x", this.itfont, this.itfont);
                final int n27 = n25 + fontMetrics.stringWidth("ax");
                graphics.drawString("  =  ", n27, n26);
                graphics.drawString("" + this.ax, n27 + fontMetrics.stringWidth("  =  "), n26);
                final int n28 = 200;
                this.drawString(graphics, n28, n26, "a", "y", this.itfont, this.itfont);
                final int n29 = n28 + fontMetrics.stringWidth("ay");
                graphics.drawString("  =  ", n29, n26);
                graphics.drawString("" + this.ay, n29 + fontMetrics.stringWidth("  =  "), n26);
            }
            else if (this.page == 9 || this.page == 10) {
                final int n30 = 140;
                final int n31 = this.panelHeight - 60;
                graphics.setFont(this.font);
                this.drawVectorLabel(graphics, n30, n31, "d", this.bffont);
                graphics.drawString(" = (-3.0, 4.0)", n30 + fontMetrics.stringWidth("d"), n31);
                if (this.page == 10) {
                    final int n32 = 135;
                    final int n33 = this.panelHeight - 40;
                    graphics.setFont(this.font);
                    graphics.drawString("-2", n32, n33);
                    final int n34 = n32 + fontMetrics.stringWidth("-2");
                    this.drawVectorLabel(graphics, n34, n33, "d", this.bffont);
                    graphics.drawString(" = (6.0, -8.0)", n34 + fontMetrics.stringWidth("d"), n33);
                    final int n35 = 140;
                    final int n36 = this.panelHeight - 15;
                    graphics.setFont(this.font);
                    this.drawVectorLabel(graphics, n35, n36, "d", this.bffont);
                    final int n37 = n35 + fontMetrics.stringWidth("d");
                    graphics.drawString("/2", n37, n36);
                    graphics.drawString(" = (-1.5, 2.0)", n37 + fontMetrics.stringWidth("/2"), n36);
                }
                graphics.drawString("d  = -3.0", (int)this.center.x - fontMetrics.stringWidth("d  = -3.0") - 20, (int)this.center.y + 20);
                graphics.drawString("x", (int)this.center.x - fontMetrics.stringWidth("  = -3.0") - 20, (int)this.center.y + 23);
                graphics.drawString("d  = 4.0", (int)this.center.x + 10, (int)this.center.y - 20);
                graphics.drawString("y", (int)this.center.x + 10 + fontMetrics.stringWidth("d"), (int)this.center.y - 17);
            }
            else if (this.page == 11) {
                final int n38 = 100;
                final int n39 = this.panelHeight - 60;
                graphics.setFont(this.font);
                this.drawString(graphics, n38, n39, "F", "x", this.itfont, this.itfont);
                graphics.drawString(" = 8.0", n38 + fontMetrics.stringWidth("Fx"), n39);
                final int n40 = 200;
                this.drawString(graphics, n40, n39, "G", "x", this.itfont, this.itfont);
                graphics.drawString(" = -4.0", n40 + fontMetrics.stringWidth("Gx"), n39);
                final int n41 = 100;
                final int n42 = this.panelHeight - 40;
                this.drawString(graphics, n41, n42, "F", "y", this.itfont, this.itfont);
                graphics.drawString(" = 3.0", n41 + fontMetrics.stringWidth("Fy"), n42);
                final int n43 = 200;
                this.drawString(graphics, n43, n42, "G", "y", this.itfont, this.itfont);
                graphics.drawString(" = 5.0", n43 + fontMetrics.stringWidth("Gy"), n42);
                final int n44 = 100;
                final int n45 = this.panelHeight - 15;
                this.drawVectorLabel(graphics, n44, n45, "F", this.bffont);
                graphics.drawString(" = (8.0,3.0)", n44 + fontMetrics.stringWidth("F"), n45);
                final int n46 = 200;
                this.drawVectorLabel(graphics, n46, n45, "G", this.bffont);
                graphics.drawString(" = (-4.0,5.0)", n46 + fontMetrics.stringWidth("G"), n45);
            }
            else if (this.page == 12) {
                final int n47 = 80;
                final int n48 = this.panelHeight - 60;
                graphics.setFont(this.font);
                this.drawString(graphics, n47, n48, "H", "x", this.itfont, this.itfont);
                final int n49 = n47 + fontMetrics.stringWidth("Hx");
                graphics.drawString(" = ", n49, n48);
                final int n50 = n49 + fontMetrics.stringWidth(" = ");
                this.drawString(graphics, n50, n48, "F", "x", this.itfont, this.itfont);
                final int n51 = n50 + fontMetrics.stringWidth("Fx");
                graphics.drawString(" + ", n51, n48);
                final int n52 = n51 + fontMetrics.stringWidth(" + ");
                this.drawString(graphics, n52, n48, "G", "x", this.itfont, this.itfont);
                graphics.drawString(" = 8.0 + (-4.0) = 4.0", n52 + fontMetrics.stringWidth("Gx"), n48);
                final int n53 = 80;
                final int n54 = this.panelHeight - 40;
                graphics.setFont(this.font);
                this.drawString(graphics, n53, n54, "H", "y", this.itfont, this.itfont);
                final int n55 = n53 + fontMetrics.stringWidth("Hy");
                graphics.drawString(" = ", n55, n54);
                final int n56 = n55 + fontMetrics.stringWidth(" = ");
                this.drawString(graphics, n56, n54, "F", "y", this.itfont, this.itfont);
                final int n57 = n56 + fontMetrics.stringWidth("Fy");
                graphics.drawString(" + ", n57, n54);
                final int n58 = n57 + fontMetrics.stringWidth(" + ");
                this.drawString(graphics, n58, n54, "G", "y", this.itfont, this.itfont);
                graphics.drawString(" = 3.0 + 5.0 = 8.0", n58 + fontMetrics.stringWidth("Gy"), n54);
                final int n59 = 140;
                final int n60 = this.panelHeight - 15;
                this.drawVectorLabel(graphics, n59, n60, "H", this.bffont);
                graphics.drawString(" = (4.0, 8.0)", n59 + fontMetrics.stringWidth("H"), n60);
            }
            else {
                final int n61 = 100;
                final int n62 = this.panelHeight - 40;
                graphics.setFont(this.font);
                this.drawVectorLabel(graphics, n61, n62, "C", this.bffont);
                graphics.drawString(" =  (-6.0,8.0)", n61 + fontMetrics.stringWidth("C"), n62);
                final int n63 = 200;
                this.drawVectorLabel(graphics, n63, n62, "A", this.bffont);
                graphics.drawString(" = (5.0,5.0)", n63 + fontMetrics.stringWidth("A"), n62);
            }
        }
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
    
    public void setLinesA() {
        final Point2D.Double double1 = new Point2D.Double(0.0, 0.0);
        final Point2D.Double double2 = new Point2D.Double(10.0, 10.0);
        this.vectorA1y = new Vector2d(double1, double2, 2);
        this.vectorA1x = new Vector2d(double1, double2, 2);
        final Point2D.Double translateToGraph = this.coords.translateToGraph(new Point2D.Double(this.vectorA.getHead2D().x, this.vectorA.getHead2D().y));
        final Point2D.Double translateFromGraph = this.coords.translateFromGraph(new Point2D.Double(translateToGraph.x, 0.0));
        final Point2D.Double translateFromGraph2 = this.coords.translateFromGraph(new Point2D.Double(0.0, translateToGraph.y));
        this.vectorA1x.setHead(this.vectorA.getHead2D());
        this.vectorA1y.setHead(this.vectorA.getHead2D());
        this.vectorA1x.setTail(translateFromGraph2);
        this.vectorA1y.setTail(translateFromGraph);
        this.vectorA2y = new Vector2d(double1, double2, 2);
        this.vectorA2x = new Vector2d(double1, double2, 2);
        final Point2D.Double translateToGraph2 = this.coords.translateToGraph(new Point2D.Double(this.vectorA.getTail2D().x, this.vectorA.getTail2D().y));
        final Point2D.Double translateFromGraph3 = this.coords.translateFromGraph(new Point2D.Double(translateToGraph2.x, 0.0));
        final Point2D.Double translateFromGraph4 = this.coords.translateFromGraph(new Point2D.Double(0.0, translateToGraph2.y));
        this.vectorA2x.setHead(this.vectorA.getTail2D());
        this.vectorA2y.setHead(this.vectorA.getTail2D());
        this.vectorA2x.setTail(translateFromGraph4);
        this.vectorA2y.setTail(translateFromGraph3);
        this.vectorA1y.setColor(this.A_COLOR);
        this.vectorA1y.showInfo = false;
        this.vectorA1y.setChangable(false, false);
        this.vectorA1y.setMoveable(false);
        this.vectorA1y.setVisible(true);
        this.vectorA1x.setColor(this.A_COLOR);
        this.vectorA1x.showInfo = false;
        this.vectorA1x.setChangable(false, false);
        this.vectorA1x.setMoveable(false);
        this.vectorA1x.setVisible(true);
        this.vectorA2y.setColor(this.A_COLOR);
        this.vectorA2y.showInfo = false;
        this.vectorA2y.setChangable(false, false);
        this.vectorA2y.setMoveable(false);
        this.vectorA2y.setVisible(true);
        this.vectorA2x.setColor(this.A_COLOR);
        this.vectorA2x.showInfo = false;
        this.vectorA2x.setChangable(false, false);
        this.vectorA2x.setMoveable(false);
        this.vectorA2x.setVisible(true);
        (this.vectorAy = new Vector2d(this.vectorA1x.getTail2D(), this.vectorA2x.getTail2D(), 1)).setVectorWidth(3.0);
        (this.vectorAx = new Vector2d(this.vectorA1y.getTail2D(), this.vectorA2y.getTail2D(), 1)).setVectorWidth(3.0);
        this.vectorAy.setColor(this.AY_COLOR);
        this.vectorAy.setChangable(false, false);
        this.vectorAy.setMoveable(false);
        this.vectorAx.setColor(this.AX_COLOR);
        this.vectorAx.setChangable(false, false);
        this.vectorAx.setMoveable(false);
        this.ax = this.findXComp(this.vectorAx);
        this.ay = this.findYComp(this.vectorAy);
    }
    
    public void setLinesB() {
        final Point2D.Double double1 = new Point2D.Double(0.0, 0.0);
        final Point2D.Double double2 = new Point2D.Double(10.0, 10.0);
        this.vectorB1y = new Vector2d(double1, double2, 2);
        this.vectorB1x = new Vector2d(double1, double2, 2);
        final Point2D.Double translateToGraph = this.coords.translateToGraph(new Point2D.Double(this.vectorB.getHead2D().x, this.vectorB.getHead2D().y));
        final Point2D.Double translateFromGraph = this.coords.translateFromGraph(new Point2D.Double(translateToGraph.x, 0.0));
        final Point2D.Double translateFromGraph2 = this.coords.translateFromGraph(new Point2D.Double(0.0, translateToGraph.y));
        this.vectorB1x.setHead(this.vectorB.getHead2D());
        this.vectorB1y.setHead(this.vectorB.getHead2D());
        this.vectorB1x.setTail(new Point2D.Double(translateFromGraph2.x, translateFromGraph2.y));
        this.vectorB1y.setTail(new Point2D.Double(translateFromGraph.x, translateFromGraph.y));
        this.vectorB2y = new Vector2d(double1, double2, 2);
        this.vectorB2x = new Vector2d(double1, double2, 2);
        final Point2D.Double translateToGraph2 = this.coords.translateToGraph(new Point2D.Double(this.vectorB.getTail2D().x, this.vectorB.getTail2D().y));
        final Point2D.Double translateFromGraph3 = this.coords.translateFromGraph(new Point2D.Double(translateToGraph2.x, 0.0));
        final Point2D.Double translateFromGraph4 = this.coords.translateFromGraph(new Point2D.Double(0.0, translateToGraph2.y));
        this.vectorB2x.setHead(this.vectorB.getTail2D());
        this.vectorB2y.setHead(this.vectorB.getTail2D());
        this.vectorB2x.setTail(translateFromGraph4);
        this.vectorB2y.setTail(translateFromGraph3);
        this.vectorB1y.setColor(this.B_COLOR);
        this.vectorB1y.showInfo = false;
        this.vectorB1y.setChangable(false, false);
        this.vectorB1y.setMoveable(false);
        this.vectorB1y.setVisible(true);
        this.vectorB1x.setColor(this.B_COLOR);
        this.vectorB1x.showInfo = false;
        this.vectorB1x.setChangable(false, false);
        this.vectorB1x.setMoveable(false);
        this.vectorB1x.setVisible(true);
        this.vectorB2y.setColor(this.B_COLOR);
        this.vectorB2y.showInfo = false;
        this.vectorB2y.setChangable(false, false);
        this.vectorB2y.setMoveable(false);
        this.vectorB2y.setVisible(true);
        this.vectorB2x.setColor(this.B_COLOR);
        this.vectorB2x.showInfo = false;
        this.vectorB2x.setChangable(false, false);
        this.vectorB2x.setMoveable(false);
        this.vectorB2x.setVisible(true);
        (this.vectorBx = new Vector2d(this.vectorB1x.getTail2D(), this.vectorB2x.getTail2D(), 1)).setVectorWidth(3.0);
        (this.vectorBy = new Vector2d(this.vectorB1y.getTail2D(), this.vectorB2y.getTail2D(), 1)).setVectorWidth(3.0);
        this.vectorBy.setColor(this.B_COLOR);
        this.vectorBy.setChangable(false, false);
        this.vectorBy.setMoveable(false);
        this.vectorBx.setColor(this.B_COLOR);
        this.vectorBx.setChangable(false, false);
        this.vectorBx.setMoveable(false);
        this.bx = this.findXComp(this.vectorBx);
        this.by = this.findYComp(this.vectorBy);
    }
    
    public void setLinesH() {
        final Point2D.Double double1 = new Point2D.Double(0.0, 0.0);
        final Point2D.Double double2 = new Point2D.Double(10.0, 10.0);
        this.vectorH1y = new Vector2d(double1, double2, 2);
        this.vectorH1x = new Vector2d(double1, double2, 2);
        final Point2D.Double translateToGraph = this.coords.translateToGraph(new Point2D.Double(this.vectorH.getHead2D().x, this.vectorH.getHead2D().y));
        final Point2D.Double translateFromGraph = this.coords.translateFromGraph(new Point2D.Double(translateToGraph.x, 0.0));
        final Point2D.Double translateFromGraph2 = this.coords.translateFromGraph(new Point2D.Double(0.0, translateToGraph.y));
        this.vectorH1x.setHead(this.vectorH.getHead2D());
        this.vectorH1y.setHead(this.vectorH.getHead2D());
        this.vectorH1x.setTail(new Point2D.Double(translateFromGraph2.x, translateFromGraph2.y));
        this.vectorH1y.setTail(new Point2D.Double(translateFromGraph.x, translateFromGraph.y));
        this.vectorH2y = new Vector2d(double1, double2, 2);
        this.vectorH2x = new Vector2d(double1, double2, 2);
        final Point2D.Double translateToGraph2 = this.coords.translateToGraph(new Point2D.Double(this.vectorH.getTail2D().x, this.vectorH.getTail2D().y));
        final Point2D.Double translateFromGraph3 = this.coords.translateFromGraph(new Point2D.Double(translateToGraph2.x, 0.0));
        final Point2D.Double translateFromGraph4 = this.coords.translateFromGraph(new Point2D.Double(0.0, translateToGraph2.y));
        this.vectorH2x.setHead(this.vectorH.getTail2D());
        this.vectorH2y.setHead(this.vectorH.getTail2D());
        this.vectorH2x.setTail(new Point2D.Double(translateFromGraph4.x, translateFromGraph4.y));
        this.vectorH2y.setTail(new Point2D.Double(translateFromGraph3.x, translateFromGraph3.y));
        this.vectorH1y.setColor(this.H_COLOR);
        this.vectorH1y.showInfo = false;
        this.vectorH1y.setChangable(false, false);
        this.vectorH1y.setMoveable(false);
        this.vectorH1y.setVisible(true);
        this.vectorH1x.setColor(this.H_COLOR);
        this.vectorH1x.showInfo = false;
        this.vectorH1x.setChangable(false, false);
        this.vectorH1x.setMoveable(false);
        this.vectorH1x.setVisible(true);
        this.vectorH2y.setColor(this.H_COLOR);
        this.vectorH2y.showInfo = false;
        this.vectorH2y.setChangable(false, false);
        this.vectorH2y.setMoveable(false);
        this.vectorH2x.setVisible(true);
        this.vectorH2x.setColor(this.H_COLOR);
        this.vectorH2x.showInfo = false;
        this.vectorH2x.setChangable(false, false);
        this.vectorH2x.setMoveable(false);
        this.vectorH2y.setVisible(true);
        (this.vectorHx = new Vector2d(this.vectorH1x.getTail2D(), this.vectorH2x.getTail2D(), 1)).setVectorWidth(3.0);
        (this.vectorHy = new Vector2d(this.vectorH1y.getTail2D(), this.vectorH2y.getTail2D(), 1)).setVectorWidth(3.0);
        this.vectorHy.setColor(this.H_COLOR);
        this.vectorHy.setChangable(false, false);
        this.vectorHy.setMoveable(false);
        this.vectorHx.setColor(this.H_COLOR);
        this.vectorHx.setChangable(false, false);
        this.vectorHx.setMoveable(false);
        this.hx = this.findXComp(this.vectorHx);
        this.hy = this.findYComp(this.vectorHy);
    }
    
    public void setupCoords() {
        (this.coords = new CartGraph((int)this.center.x, (int)this.center.y, 90.0, 0.0)).setHeight(this.getSize().height / 2 - 15);
        this.coords.setWidth(this.getSize().width / 2 - 15);
        this.coords.zoom = 20.0;
        this.coords.number_axes = false;
    }
    
    public void start() {
        if (this.usePageBrowser) {
            this.adapter.setActive(true);
        }
        else {
            this.doCommand("page1");
        }
        this.repaint();
    }
    
    public void stop() {
        if (this.usePageBrowser) {
            this.adapter.setActive(false);
        }
    }
    
    public Vector2d theVectorAt(final Point point) {
        if (this.vectorA != null && this.vectorA.checkPoint(point.x, point.y)) {
            return this.vectorA;
        }
        if (this.vectorB != null && this.vectorB.checkPoint(point.x, point.y)) {
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
}
