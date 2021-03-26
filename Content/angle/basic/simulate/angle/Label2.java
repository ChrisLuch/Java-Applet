package ca.ucalgary.phas.map.angle;

import java.awt.*;
import java.awt.geom.*;

/** This class defines a lable object for convience specifically for the AnglePanel.
 */
public class Label2 {
	/** Backgound color.  The label consists of a background color, a border color, and
	 * a font color.
	 */	
	public Color bg = new Color(0xffffcc);
	/** Border color.  The label consists of a background color, a border color, and
	 * a font color.
	 */	
	public Color border = Color.black;
	/** Font color.  The label consists of a background color, a border color, and
	 * a font color.
	 */	
	public Color fontColor = Color.black;
	/** Defines the font type, style and size.
	 */	
	private Font font = new Font("Dialog", Font.PLAIN, 12);
	/** FontMetrics object used to determine the size and width of characters in the label.
	 * Used to determine border width and height.
	 */	
	private FontMetrics fm;
	/** Contents of the label.
	 */	
	private String text;
	/** Exact pixel position of the label.
	 */	
	public Point2D.Double loc = new Point2D.Double();
	/** Stores the width and height of the lable object.
	 */	
	private Dimension dim = new Dimension();
	/** Combination of x, y position as well as width and height components.
	 */	
	private Rectangle2D.Double bounds = new Rectangle2D.Double();
	/** Pointer to instantiating object.
	 */	
	private Component parent;
	
	/** Constructor sets text, x, y coordinate location and pointer to parent.
	 */	
	public Label2(String text, double x, double y, Component parent) {
		this.parent = parent;
		this.text = text;
		fm = parent.getFontMetrics(font);
		dim.width = toInt(fm.stringWidth(text) * 1.5);
		dim.height = fm.getHeight();

		loc.x = x;
		loc.y = y;
	}
        	
	/** Constructor sets text component and pointer to parent, location is 0, 0 by default.
	 */	
	public Label2(String text, Component parent) {
		this(text, 0, 0, parent);
	}
	
	/** Constructor sets
	 */	
	public Label2(Component parent) {
		this("", parent);
	}
	
	public void paint(Graphics g) {
		paint((Graphics2D)g);
	}
	
	public void paint(Graphics2D g) {
		Color tempColor = g.getColor();
		Font tempFont = g.getFont();
		g.setFont(font);

		bounds.x = loc.x;
		bounds.y = loc.y;
		bounds.width = dim.width;
		bounds.height = dim.height;
		
		g.setColor(bg);
		g.fill(bounds);
		g.setColor(border);
		g.draw(bounds);

		g.setColor(fontColor);
		g.drawString(text, toInt(loc.x + ((dim.width - fm.stringWidth(text))/2)), toInt(loc.y + fm.getAscent()));		
		
		g.setFont(tempFont);
		g.setColor(tempColor);
	}
	
	public static int toInt(double n) {
		return (int)(n + 0.5);
	}

	public double getWidth() {
		return dim.width;
	}
	
	public double getHeight() {
		return dim.height;
	}
	
	public Point2D.Double getLoc(){
		return this.loc;
	}

	public void setLoc(Point2D.Double loc){
		setLoc(loc.x, loc.y);
	}

	public void setLoc(double x, double y) {
		loc.x = x;
		loc.y = y;
	}
	
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
		dim.width = toInt(fm.stringWidth(text) * 1.5);
	}

	
	public Font getFont(){
		return this.font;
	}

	public void setFont(Font font){
		this.font = font;
		fm = parent.getFontMetrics(font);
		dim.width = toInt(fm.stringWidth(text) * 1.5);
		dim.height = fm.getHeight();
	}
}