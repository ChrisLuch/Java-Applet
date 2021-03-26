package ca.ucalgary.phas.map.angle;

import netscape.javascript.*;
import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import ca.ucalgary.phas.map.utilities.vectors.VectorUtil;
import ca.ucalgary.phas.map.utilities.*;

/** Handle the drawing of the simulation and all mouse events.
 */
public class AnglePanel extends JPanel {
	/** Radius of a draggable point measured in pixels.
	 */
	private static final double POINT_RADIUS = 3.5;
	/** Pointer to applet that instantiated this panel.
	 */
	private Angle parent;
	/** Font to be used when printing to the graphics context.
	 */
	private Font screenFont = new Font("Dialog", Font.BOLD, 12);
	/** Color of the font to be drawn to the graphics context.
	 */
	private Color fontColor = Color.black;
	/** Color of the arc spanning from leg1 to leg2.
	 */
	private Color arcColor = Color.blue;
	/** Color of draggable points (ie L1, L2, and radius).
	 */
	private Color pointColor = Color.red;
	/** Arc shape that describes angle and span.
	 */
	private Arc2D.Double arc = new Arc2D.Double(Arc2D.PIE);
	/** Line shape that defines the start of the arc extent.
	 */
	private Line2D.Double leg1 = new Line2D.Double();
	/** Line shape that defines the end of the arc extent.
	 */
	private Line2D.Double leg2 = new Line2D.Double();
	/** Vertex of angle.
	 */
	private Point2D.Double center = new Point2D.Double();
	/** Ellipse shape that defines Leg1 draggable point.
	 */
	private Ellipse2D.Double elL1 = new Ellipse2D.Double();
	/** Ellipse shape that defines Leg2 draggable point.
	 */
	private Ellipse2D.Double elL2 = new Ellipse2D.Double();
	/** Ellipse shape that defines radius draggable point.
	 */
	private Ellipse2D.Double elRadius = new Ellipse2D.Double();
	/** On screen point that defines exact location of Leg1.
	 */
	private Point2D.Double ptL1 = new Point2D.Double();
	/** On screen point that defines exact location of Leg2.
	 */
	private Point2D.Double ptL2 = new Point2D.Double();
	/** On screen point that defines exact location of radius.
	 */
	private Point2D.Double ptRadius = new Point2D.Double();
	/** True if the mouse cursor is over Leg1 point, false otherwise.
	 */
	private boolean onL1 = false;
	/** True if the mouse cursor is over Leg2 point, false otherwise.
	 */
	private boolean onL2 = false;
	/** True if the mouse cursor is over radius point, false otherwise.
	 */
	private boolean onRadius = false;
	/** Convience variable that stores the width of the panel after it has been resized.
	 * @see ca.ucalgary.phas.map.angle.AnglePanel.MyComponentAdapter#componentResized(ComponentEvent)
	 */
	private double panelWidth = 0;
	/** Convience variable that stores the height of the panel after it has been resized.
	 * Set in componentResized.
	 * @see ca.ucalgary.phas.map.angle.AnglePanel.MyComponentAdapter#componentResized(ComponentEvent)
	 */
	private double panelHeight = 0;
	/** Distance from vertex to span.
	 */
	private double radius = 0;
	/** Angle from leg1 to leg2 in radians.
	 */
	private double theta = 0;
	/** Distance from leg1 to leg2. (theta * radius).
	 */
	private double span = 0;
	/** Format a double value to be displayed with only two decimal places.
	 */
	private DecimalFormat df = new DecimalFormat(" 0.00;-0.00");
	/** Exact location to draw the Theat Lable object to.
	 */
	private Point2D.Double ptThetaLabel = new Point2D.Double();
	/** Exact location to draw the radius Lable object to.
	 */
	private Point2D.Double ptRadiusLabel = new Point2D.Double();
	/** Exact location to draw the span Lable object to.
	 */
	private Point2D.Double ptSpanLabel = new Point2D.Double();
	/** Determines the thickness of the stroke path to draw the angle arc and legs.
	 */
	private BasicStroke stroke = new BasicStroke(5);
	/** Label for leg1.
	 */
	private Label2 lblL1 = new Label2("L1", this);
	/** Label for leg 2.
	 */
	private Label2 lblL2 = new Label2("L2", this);
	/** Label for theta.
	 */
	private Label2 lblTheta = new Label2("\u03b8", this);
	/** Label for span.
	 */
	private Label2 lblSpan = new Label2("s", this);
	/** Lable for radius.
	 */
	private Label2 lblRadius = new Label2("r", this);
	/** Popup dialog for help and reset buttons.
	 */
	protected Popup myPopup = new Popup();
	
	/** Inner class that defines the popup menu that appears when the user presses the
	 * popup trigger anywhere on the panel's context.
	 */
	public class Popup extends JPopupMenu implements ActionListener {
		
		JMenuItem reset;
		JMenuItem help;
		
		/** Constructor for popup menu.  Instantiates button objects and adds them to the
		 * popup menu as well as registers action events to buttons.
		 */
		public Popup() {
			reset = new JMenuItem();
			help = new JMenuItem();
			MapButtonPropertySetter.setHelp(help);
			MapButtonPropertySetter.setReset(reset);
			
			add(reset);
			add(help);
			
			reset.addActionListener(this);
			help.addActionListener(this);
		}
		
		/** Event handler for buttons.
		 * @param e event object that defines the action to be taken
		 */
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			
			if (source.equals(reset)) {
				reset();
			}
  			if (source.equals(help)) {
				 //help();
				 System.out.println("FIX ME!!");
  			}
		}
	}
	
	/** Inner class to handle mouse events for this panel.
	 */
	public class MyMouseAdapter extends MouseInputAdapter {
		Point currentPoint, oldPoint;
		Point translatePoint = new Point();
		JPanel parent;
		
		/** Contructor for MouseAdapter class.
		 * @param parent pointer to outter class
		 */
		public MyMouseAdapter(JPanel parent) {
			this.parent = parent;
		}
		
		/** Called when the mouse is passed over the panel.  Checks to see if the mouse if over
		 * any dragable points and sets the mouse cursor accordingly.
		 * @param e event object that defines how the event is to be handled.
		 */
		public void mouseMoved(MouseEvent e) {
			int cursor;
			oldPoint = e.getPoint();
			
			onL1 = false;
			onL2 = false;
			onRadius = false;
			
			if (elL1.contains(oldPoint.x, oldPoint.y)) {
				onL1 = true;
				cursor = Cursor.MOVE_CURSOR;
			}
			else if (elL2.contains(oldPoint.x, oldPoint.y)) {
				onL2 = true;
				cursor = Cursor.MOVE_CURSOR;
			}
			else if (elRadius.contains(oldPoint.x, oldPoint.y)) {
				onRadius = true;
				cursor = Cursor.HAND_CURSOR;
			}
			else {
				cursor = Cursor.DEFAULT_CURSOR;
			}
			
			setCursor(Cursor.getPredefinedCursor(cursor));
		}
		
		/** Called when the mouse is dragged over the panel.  Handles the movement of draggable points.
		 * @param e event object that defines how the event is to be handled.
		 */
		public void mouseDragged(MouseEvent e) {
			currentPoint = e.getPoint();
			
			translatePoint.x =  currentPoint.x - oldPoint.x;
			translatePoint.y =  currentPoint.y - oldPoint.y;
			oldPoint = currentPoint;
			
			if (onL1) {
				ptL1.x += translatePoint.x;
				ptL1.y += translatePoint.y;
				ptRadius = VectorUtil.translatePoint2D(center, VectorUtil.findAngle(center, ptL1), center.distance(ptRadius.x, ptRadius.y));
				calculate();
			}
			if (onL2) {
				ptL2.x += translatePoint.x;
				ptL2.y += translatePoint.y;
				calculate();
			}
			if (onRadius) {
				ptRadius.x = currentPoint.x;
				ptRadius.y = currentPoint.y;
				ptRadius = VectorUtil.translatePoint2D(center, VectorUtil.findAngle(center, ptL1), center.distance(ptRadius.x, ptRadius.y));
				calculate();
			}
		}
		
		/** Called when the user clicks & releases the mouse button.  Displays the popup dialog
		 * if the popup trigger was clicked.
		 * @param e event object that defines how the event is to be handled.
		 */
		public void mouseReleased(MouseEvent e) {
			mouseMoved(e);
			if (e.isPopupTrigger()) {
				myPopup.show(parent, e.getX(), e.getY());
			}
		}
	}
	
	/** Inner class that defines component specific events.
	 */
	public class MyComponentAdapter extends ComponentAdapter {
		/** Called when the component is resized.
		 * @param e event object that determines how this even is to be handled
		 */
		public void componentResized(ComponentEvent e) {
			reset();
		}
	}
	
	/** Constructor for angle panel.
	 * @param parent pointer to source applet that instantiates this panel
	 */
	public AnglePanel(Angle parent) {
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter(this);
		this.parent = parent;
		
		addMouseListener(myMouseAdapter);
		addMouseMotionListener(myMouseAdapter);
		addComponentListener(new MyComponentAdapter());
	}
	
	/** Reset applet state to origional.
	 */
	public void reset() {
		double maxSize;
		panelWidth = getWidth();
		panelHeight = getHeight();
		maxSize = Math.min(panelWidth, panelHeight);
		center.x = panelWidth/2.0;
		center.y = ptRadius.y = panelHeight/2.0;
		ptRadius.x = center.x + (maxSize/6);
		ptL1.x = center.x + (maxSize/8);
		ptL1.y = center.y;
		ptL2.x = center.x;
		ptL2.y = center.y - (maxSize/8);
		elL1.height = POINT_RADIUS * 2;
		elL1.width = POINT_RADIUS * 2;
		elL2.height = POINT_RADIUS * 2;
		elL2.width = POINT_RADIUS * 2;
		elRadius.width = POINT_RADIUS * 2;
		elRadius.height = POINT_RADIUS * 2;
		calculate();
	}
	
	
	/** Show pop-up HTML help window explaining applet functionality.
	 * Popup parameters are retrieved from HTML applet tag parameters.
	 *
	 * @see ca.ucalgary.phas.map.Angle#init()
	 */
//  	public void help() {
//  		String[] args = {parent.strJSHelpFile, parent.strJSParameter};
//  		try {
//  			JSObject win = JSObject.getWindow(this.parent);
//  			win.call(parent.strJSFunction, args);
//  		}
//  		catch (JSException e) {
//  			System.out.println(e.getMessage());
//  			e.printStackTrace();
//  		}
//  	}
	
	/** Simply cast graphics context to a Graphics2D graphics context and call
	 * {@link #paint(Graphics2D) paint}
	 * @param g Graphics context to cast to Graphics2D context.
	 */
	public void paint(Graphics g) {
		paint((Graphics2D)g);
	}
	
	/** Draw components to panel.  Components consist of several objects.
	 * The arc, the legs and the lables.
	 * @param g Graphics context to draw components to.
	 */
	public void paint(Graphics2D g) {
		
		BasicStroke tempStroke = (BasicStroke)g.getStroke();
		Font tempFont = g.getFont();
		Color tempColor = g.getColor();
		
		elL1.x = ptL1.x - POINT_RADIUS;
		elL1.y = ptL1.y - POINT_RADIUS;
		elL2.x = ptL2.x - POINT_RADIUS;
		elL2.y = ptL2.y - POINT_RADIUS;
		elRadius.x = ptRadius.x - POINT_RADIUS;
		elRadius.y = ptRadius.y - POINT_RADIUS;
		
		g.setFont(screenFont);
		g.setColor(Color.white);
		// Clear the screen
		g.fillRect(0, 0, toInt(panelWidth), toInt(panelHeight));
		
		g.setStroke(stroke);
		g.setColor(arcColor);
		// Draw the arc which consists of vertex, legs and span.
		g.draw(arc);
		
		// Draw the legs that extend past the span.
		g.draw(leg1);
		g.draw(leg2);
		
		// Draw the outline of the draggable points.
		g.setColor(Color.white);
		g.draw(elRadius);
		g.draw(elL1);
		g.draw(elL2);
		
		// Draw the inside of the draggable points.
		g.setColor(pointColor);
		g.fill(elRadius);
		g.fill(elL1);
		g.fill(elL2);
		
		// Align and draw the lables for each point
		lblL1.setLoc(ptL1.x - (lblL1.getWidth()/2), ptL1.y + (POINT_RADIUS * 2));
		lblL2.setLoc(ptL2.x - (lblL2.getWidth()/2), ptL2.y + (POINT_RADIUS * 2));
		lblTheta.setLoc(ptThetaLabel);
		lblRadius.setLoc(ptRadius.x - (lblRadius.getWidth()/2), ptRadius.y + (POINT_RADIUS * 2));
		lblSpan.setLoc(ptSpanLabel);
		
		g.setStroke(tempStroke);
		lblL1.paint(g);
		lblL2.paint(g);
		lblTheta.paint(g);
		lblRadius.paint(g);
		lblSpan.paint(g);
		
		g.setColor(fontColor);
		// Draw the data to the screen.
		g.drawString("s : ", 5, 15); g.drawString(df.format(span/10.0) + " m", 50, 15);
		g.drawString("r : ", 5, 30); g.drawString(df.format(radius/10.0) + " m", 50, 30);
		g.drawString("\u03b8 = s/r : ", 5, 45); g.drawString(df.format(theta) + " rad", 50, 45);
		g.setColor(tempColor);
		g.setFont(tempFont);
		g.setStroke(tempStroke);
	}
	
	/** Calculate all necessary variables needed when the user changes either the position
	 * of leg1, leg2 or the radius of the arc.
	 */
	private void calculate() {
		double theta1 = VectorUtil.findAngle(center, ptL1);
		double theta2 = VectorUtil.findAngle(center, ptL2);
		Point2D.Double l1head;
		Point2D.Double l1tail;
		Point2D.Double l2head;
		Point2D.Double l2tail;
		
		radius = ptRadius.distance(center);
		arc.y = center.y - radius;
		arc.x = center.x - radius;
		arc.height = arc.width = Math.abs(radius * 2);
		
		arc.start = Math.toDegrees(VectorUtil.findAngle(center, ptL1));
		arc.extent = Math.toDegrees(MapMath.findPosAngle(center, ptL1, ptL2));
		
		ptThetaLabel = VectorUtil.translatePoint2D(center, Math.toRadians(arc.start + (arc.extent/2)), radius/2);
		ptRadiusLabel = VectorUtil.translatePoint2D(center, VectorUtil.findAngle(center, ptRadius) - 0.1745, radius/2);
		ptSpanLabel = VectorUtil.translatePoint2D(center, Math.toRadians(arc.start + (arc.extent/2)), radius + 10);
		
		// Calculate the head and tail of each leg.
		l1head = VectorUtil.translatePoint2D(center, theta1, radius);
		l2head = VectorUtil.translatePoint2D(center, theta2, radius);
		
		l1tail = VectorUtil.translatePoint2D(l1head, theta1, radius * 1.25);
		l2tail = VectorUtil.translatePoint2D(l2head, theta2, radius * 1.25);
		
		leg1.setLine(l1head, l1tail);
		leg2.setLine(l2head, l2tail);
		
		theta = Math.toRadians(arc.extent);
		
		span = theta * radius;
		
		repaint();
	}
	
	/** Simply square a value.
	 * @param n value to find the square of
	 * @return n * n
	 */
	public static double sqr(double n) {
		return n * n;
	}
	
	/** Find the closes integer value for the given double value.
	 * @param n value to round
	 * @return rounded value of n to the nearest whole number
	 */
	public static int toInt(double n) {
		return (int)((n < 0) ? n - 0.5 : n + 0.5);
	}
	
	/** The arc color is the color of the span drawn from leg1 to leg2.
	 * @return value stored in the arcColor parameter.
	 */
	public Color getArcColor(){
		return this.arcColor;
	}
	
	/** The arc color is the color of the span drawn from leg1 to leg2.
	 * @param arcColor color to set the arc to
	 */
	public void setArcColor(Color arcColor){
		this.arcColor = arcColor;
	}
	
	/** The radius from the vertex to the radius point measured in pixels.
	 * @return the distance from the vertex to the radius point
	 */
	public double getRadius(){
		return this.radius;
	}
	
	/** Return the value stored in the variable span.
	 * @return the distance spanned from leg 1 to leg 2
	 */
	public double getSpan(){
		return this.span;
	}
	
	/** Return the angle spanned from leg1 to leg2 in a clockwise direction.
	 * @return angle spanned from leg1 to leg2 in a clockwise direction measured in radians
	 */
	public double getTheta(){
		return this.theta;
	}
}
