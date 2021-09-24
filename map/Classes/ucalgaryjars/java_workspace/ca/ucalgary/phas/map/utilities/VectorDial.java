// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import javax.swing.event.ChangeListener;
import java.awt.event.MouseEvent;
import ca.ucalgary.phas.map.utilities.vectors.VectorUtil;
import java.awt.Graphics;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import java.awt.geom.Point2D;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class VectorDial extends JPanel implements MouseMotionListener, MouseListener
{
    public static final int ONE_D_MODE = 5562;
    public static final int TWO_D_MODE = 7834;
    static final Color DIAL_COLOR;
    static final Color VECTOR_COLOR;
    static final Color BACKGROUND_COLOR;
    static final Color CONSTRAINT_COLOR;
    Constrainer currentConstraints;
    Color dialC;
    Color vectorC;
    Color backC;
    Color lineC;
    boolean enabled;
    boolean oneDMode;
    Dimension size;
    double maxValue;
    Point2D.Double center;
    Point2D.Double currentPoint;
    int A;
    int B;
    EventListenerList listenerList;
    ChangeEvent changeEvent;
    
    public VectorDial(final int width, final int height, final double maxValue) {
        this.listenerList = new EventListenerList();
        this.changeEvent = null;
        this.setBackground(Color.white);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.size = new Dimension(width, height);
        this.maxValue = maxValue;
        this.dialC = VectorDial.DIAL_COLOR;
        this.vectorC = VectorDial.VECTOR_COLOR;
        this.backC = VectorDial.BACKGROUND_COLOR;
        this.enabled = true;
        this.oneDMode = false;
        this.A = width * width / 4;
        this.B = height * height / 4;
        this.center = new Point2D.Double(this.size.width / 2, this.size.height / 2);
        this.currentPoint = new Point2D.Double(this.size.width / 2, this.size.height / 2);
    }
    
    public VectorDial(final int width, final int height, final double maxValue, final Color dialC, final Color vectorC, final Color backC) {
        this.listenerList = new EventListenerList();
        this.changeEvent = null;
        this.setBackground(Color.white);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.size = new Dimension(width, height);
        this.maxValue = maxValue;
        this.dialC = dialC;
        this.vectorC = vectorC;
        this.backC = backC;
        this.enabled = true;
        this.oneDMode = false;
        this.A = width * width / 4;
        this.B = height * height / 4;
        this.center = new Point2D.Double(this.size.width / 2, this.size.height / 2);
        this.currentPoint = new Point2D.Double(this.size.width / 2, this.size.height / 2);
    }
    
    public Dimension getMinimumSize() {
        return this.size;
    }
    
    public Dimension getMaximumSize() {
        return this.size;
    }
    
    public Dimension getPreferredSize() {
        return this.size;
    }
    
    public void paintComponent(final Graphics graphics) {
        graphics.setColor(this.backC);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        graphics.setColor(this.dialC);
        graphics.fillOval(0, 0, this.size.width, this.size.height);
        if (this.oneDMode) {
            this.currentConstraints.paintConstraints(graphics);
        }
        VectorUtil.drawVector(graphics, 3.0, 6.0, this.center, this.currentPoint, this.vectorC, this.vectorC);
        graphics.setColor(this.vectorC);
        graphics.fillOval((int)this.center.getX() - 5, (int)this.center.getY() - 5, 10, 10);
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public void setValueConstraints(final int n, final int n2, final double n3) {
        if (n == 7834) {
            this.oneDMode = false;
        }
        else {
            if (n != 5562) {
                throw new IllegalArgumentException();
            }
            this.oneDMode = true;
            this.currentConstraints = new Constrainer(n2, n3);
        }
        this.repaint();
    }
    
    protected void placePoint(final double x, final double y) {
        final double n = x - this.center.getX();
        final double n2 = y - this.center.getY();
        if (n * n / this.A + n2 * n2 / this.B <= 1.0) {
            this.currentPoint.setLocation(x, y);
        }
        else if (n2 != 0.0) {
            final int n3 = Math.abs((int)n2) / (int)n2;
            final double n4 = n2 / n;
            final double n5 = n3 * Math.sqrt(this.A / (1.0 / (n4 * n4) + this.A / this.B));
            this.currentPoint.setLocation(n5 / n4 + this.center.getX(), n5 + this.center.getY());
        }
        else {
            this.currentPoint.setLocation((n > 0.0) ? ((double)this.size.width) : 0.0, this.center.getY());
        }
        if (this.oneDMode) {
            this.currentConstraints.contrain(this.currentPoint);
        }
    }
    
    public void reset() {
        this.currentPoint = new Point2D.Double(this.center.getX(), this.center.getY());
    }
    
    public double getMagnitude() {
        return this.center.distance(this.currentPoint) * this.maxValue * 2.0 / Math.max(this.size.width, this.size.height);
    }
    
    public double get_X_Value() {
        return (this.currentPoint.getX() - this.center.getX()) * this.maxValue * 2.0 / Math.max(this.size.width, this.size.height);
    }
    
    public double get_Y_Value() {
        return -1.0 * (this.currentPoint.getY() - this.center.getY()) * this.maxValue * 2.0 / Math.max(this.size.width, this.size.height);
    }
    
    public double getAngle() {
        return 3.141592653589793 - Math.atan2(this.center.getY() - this.currentPoint.getY(), this.center.getX() - this.currentPoint.getX());
    }
    
    public double getDegrees() {
        return 57.29577951308232 * (3.141592653589793 - Math.atan2(this.center.getY() - this.currentPoint.getY(), this.center.getX() - this.currentPoint.getX()));
    }
    
    public void setMagnitudeAndAngle(final double n, final double n2) {
        if (n <= this.maxValue) {
            final double n3 = Math.max(this.size.width, this.size.height) / 2 / this.maxValue;
            this.currentPoint.setLocation(this.center.getX() + n * n3 * Math.cos(n2), this.center.getY() - n * n3 * Math.sin(n2));
        }
        this.repaint();
    }
    
    public void setXandY(final double n, final double n2) {
        if (n * n + n2 * n2 <= this.maxValue * this.maxValue) {
            final double n3 = Math.max(this.size.width, this.size.height) / 2 / this.maxValue;
            this.currentPoint.setLocation(this.center.getX() + n * n3, this.center.getY() + n2 * n3);
        }
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.enabled) {
            this.placePoint(mouseEvent.getX(), mouseEvent.getY());
            this.fireChangeEvent();
            this.repaint();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.enabled) {
            final double n = mouseEvent.getX() - this.center.getX();
            final double n2 = mouseEvent.getY() - this.center.getY();
            if (n * n / this.A + n2 * n2 / this.B <= 1.0) {
                this.placePoint(mouseEvent.getX(), mouseEvent.getY());
                this.fireChangeEvent();
                this.repaint();
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void addChangeListener(final ChangeListener l) {
        this.listenerList.add(ChangeListener.class, l);
    }
    
    public void removeChangeListener(final ChangeListener l) {
        this.listenerList.remove(ChangeListener.class, l);
    }
    
    protected void fireChangeEvent() {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ChangeListener.class) {
                if (this.changeEvent == null) {
                    this.changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent);
            }
        }
    }
    
    static {
        DIAL_COLOR = new Color(145, 146, 145);
        VECTOR_COLOR = Color.black;
        BACKGROUND_COLOR = new Color(205, 206, 205);
        CONSTRAINT_COLOR = Color.black;
    }
    
    class Constrainer
    {
        final double[] angles;
        final double offset;
        final double sepAngle;
        final int num;
        
        public Constrainer(final int n, final double offset) {
            this.angles = new double[n];
            this.offset = offset;
            this.sepAngle = 6.283185307179586 / n;
            int num = 0;
            for (double offset2 = this.offset; offset2 < 6.283185307179586 + this.offset - 0.01; offset2 += this.sepAngle) {
                this.angles[num++] = offset2;
            }
            this.num = num;
        }
        
        public void contrain(final Point2D.Double double1) {
            int n = (int)Math.round(Math.IEEEremainder((Math.atan2(double1.getY() - VectorDial.this.center.getY(), double1.getX() - VectorDial.this.center.getX()) - this.offset) / this.sepAngle, this.num));
            if (n < 0) {
                n += this.num;
            }
            final double distance = double1.distance(VectorDial.this.center);
            double1.setLocation(VectorDial.this.center.getX() + distance * Math.cos(this.angles[n]), VectorDial.this.center.getY() + distance * Math.sin(this.angles[n]));
        }
        
        public void paintConstraints(final Graphics graphics) {
            graphics.setColor(VectorDial.CONSTRAINT_COLOR);
            final int n = VectorDial.this.size.height / 2;
            for (int i = 0; i < this.num; ++i) {
                graphics.drawLine((int)(VectorDial.this.center.getX() + 0.5), (int)(VectorDial.this.center.getY() + 0.5), (int)(VectorDial.this.center.getX() + n * Math.cos(this.angles[i]) + 0.5), (int)(VectorDial.this.center.getY() + n * Math.sin(this.angles[i]) + 0.5));
            }
        }
    }
}
