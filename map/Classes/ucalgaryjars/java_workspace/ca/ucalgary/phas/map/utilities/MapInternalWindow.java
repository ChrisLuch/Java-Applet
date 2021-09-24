// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Color;

public class MapInternalWindow
{
    public static Color areaAccentColor;
    public static Color lineBorderColor;
    public Color textColor;
    public Color backgroundColor;
    static final Cursor minimizeCursor;
    static final Cursor closeCursor;
    static final Cursor moveCursor;
    static final Cursor selectionCursor;
    private boolean minimized;
    private Rectangle rect;
    private int areaInitH;
    private boolean visible;
    private Vector selectionAreaYValues;
    private Vector selectionAreaCommands;
    Enumeration tempSelectionEnum;
    int tempSelectionY;
    Integer tempSelectionInteger;
    
    public MapInternalWindow() {
        this.textColor = Color.black;
        this.backgroundColor = Color.white;
        this.minimized = false;
        this.visible = false;
        this.selectionAreaYValues = new Vector();
        this.selectionAreaCommands = new Vector();
        this.rect = new Rectangle();
    }
    
    public MapInternalWindow(final int n, final int n2, final int n3, final int n4) {
        this();
        this.setBounds(n, n2, n3, n4);
    }
    
    public void setBackground(final Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public void setTextColor(final Color textColor) {
        this.textColor = textColor;
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public void paintWindow(final Graphics graphics) {
        if (this.visible) {
            graphics.setColor(this.backgroundColor);
            graphics.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
            graphics.setColor(MapInternalWindow.areaAccentColor);
            graphics.fillRect(this.rect.x, this.rect.y, this.rect.width, 10);
            graphics.setColor(Color.gray);
            graphics.drawLine(this.rect.x + this.rect.width - 10, this.rect.y, this.rect.x + this.rect.width - 10, this.rect.y + 10);
            graphics.drawLine(this.rect.x + this.rect.width - 8, this.rect.y + 2, this.rect.x + this.rect.width - 2, this.rect.y + 8);
            graphics.drawLine(this.rect.x + this.rect.width - 2, this.rect.y + 2, this.rect.x + this.rect.width - 8, this.rect.y + 8);
            graphics.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
            graphics.drawLine(this.rect.x, this.rect.y + 10, this.rect.x + this.rect.width, this.rect.y + 10);
        }
    }
    
    public void addSelectionArea(final int value, final String e) {
        this.selectionAreaYValues.add(new Integer(value));
        this.selectionAreaCommands.add(e);
    }
    
    public boolean onSelectionArea(final int n, final int n2) {
        if (n > this.rect.x + 10 && n < this.rect.x + this.rect.width - 10) {
            this.tempSelectionEnum = this.selectionAreaYValues.elements();
            while (this.tempSelectionEnum.hasMoreElements()) {
                this.tempSelectionInteger = this.tempSelectionEnum.nextElement();
                this.tempSelectionY = this.tempSelectionInteger;
                if (n2 > this.rect.y + this.tempSelectionY - 15 && n2 < this.rect.y + this.tempSelectionY + 5) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public String getCommandOnSelectionArea(final int n, final int n2) {
        if (n > this.rect.x + 10 && n < this.rect.x + this.rect.width - 10) {
            this.tempSelectionEnum = this.selectionAreaYValues.elements();
            while (this.tempSelectionEnum.hasMoreElements()) {
                this.tempSelectionInteger = this.tempSelectionEnum.nextElement();
                this.tempSelectionY = this.tempSelectionInteger;
                if (n2 > this.rect.y + this.tempSelectionY - 15 && n2 < this.rect.y + this.tempSelectionY + 5) {
                    return this.selectionAreaCommands.elementAt(this.selectionAreaYValues.indexOf(this.tempSelectionInteger));
                }
            }
        }
        return null;
    }
    
    public void paintSelectionLine(final Graphics graphics, final boolean b, final int n, final ComplexString complexString) {
        if (b) {
            graphics.setColor(MapInternalWindow.areaAccentColor);
            graphics.fillRect(this.rect.x + 10, n - 10, 10, 10);
        }
        graphics.setColor(MapInternalWindow.lineBorderColor);
        graphics.drawRect(this.rect.x + 10, n - 10, 10, 10);
        complexString.drawBaseline(graphics, this.rect.x + 30, n);
    }
    
    public void paintSelectionLine(final Graphics graphics, final boolean b, final int n, final String s) {
        if (b) {
            graphics.setColor(MapInternalWindow.areaAccentColor);
            graphics.fillRect(this.rect.x + 10, n - 10, 10, 10);
        }
        graphics.setColor(MapInternalWindow.lineBorderColor);
        graphics.drawRect(this.rect.x + 10, n - 10, 10, 10);
        graphics.setColor(this.textColor);
        graphics.drawString(s, this.rect.x + 30, n);
    }
    
    public void setHeight(final int areaInitH) {
        this.areaInitH = areaInitH;
        this.setRenderedHeight();
    }
    
    public void setWidth(final int width) {
        this.rect.width = width;
    }
    
    public void setBounds(final int x, final int y, final int width, final int areaInitH) {
        this.rect.x = x;
        this.rect.y = y;
        this.rect.width = width;
        this.areaInitH = areaInitH;
        this.setRenderedHeight();
    }
    
    public int getX() {
        return this.rect.x;
    }
    
    public int getY() {
        return this.rect.y;
    }
    
    public int getWidth() {
        return this.rect.width;
    }
    
    public int getHeight() {
        return this.rect.height;
    }
    
    public boolean setCursor(final int n, final int n2, final JComponent component) {
        return this.setCursorOnMinimize(n, n2, component) || this.setCursorOnClose(n, n2, component) || this.setCursorOnArea(n, n2, component);
    }
    
    public boolean setCursorOnMinimize(final int n, final int n2, final JComponent component) {
        if (this.onMinimize(n, n2)) {
            component.setCursor(MapInternalWindow.minimizeCursor);
            return true;
        }
        return false;
    }
    
    public boolean onMinimizeClick(final int n, final int n2, final int n3) {
        if (this.onMinimize(n, n2) && n3 == 2) {
            this.setMinimized(!this.minimized);
            return true;
        }
        return false;
    }
    
    public void setMinimized(final boolean minimized) {
        this.minimized = minimized;
        this.setRenderedHeight();
    }
    
    public boolean isMinimized() {
        return this.minimized;
    }
    
    private void setRenderedHeight() {
        if (this.minimized) {
            this.rect.height = 10;
        }
        else {
            this.rect.height = this.areaInitH;
        }
    }
    
    public boolean onMinimize(final int n, final int n2) {
        return this.visible && n < this.rect.x + this.rect.width - 10 && n > this.rect.x && n2 < this.rect.y + 10 && n2 > this.rect.y;
    }
    
    public boolean setCursorOnArea(final int n, final int n2, final JComponent component) {
        if (this.contains(n, n2)) {
            if (this.onSelectionArea(n, n2)) {
                component.setCursor(MapInternalWindow.selectionCursor);
            }
            else {
                component.setCursor(MapInternalWindow.moveCursor);
            }
            return true;
        }
        return false;
    }
    
    public boolean setCursorOnClose(final int n, final int n2, final JComponent component) {
        if (this.onClose(n, n2)) {
            component.setCursor(MapInternalWindow.closeCursor);
            return true;
        }
        return false;
    }
    
    public boolean onCloseClick(final int n, final int n2, final AbstractButton abstractButton) {
        if (this.onClose(n, n2)) {
            abstractButton.setSelected(this.visible = false);
            return true;
        }
        return false;
    }
    
    public boolean onClose(final int n, final int n2) {
        return this.visible && n < this.rect.x + this.rect.width && n > this.rect.x + this.rect.width - 10 && n2 < this.rect.y + 10 && n2 > this.rect.y;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
        if (this.minimized) {
            this.setMinimized(false);
        }
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public boolean contains(final int x, final int y) {
        return this.visible && this.rect.contains(x, y);
    }
    
    public void shift(final int dx, final int dy) {
        this.rect.translate(dx, dy);
    }
    
    public Rectangle shiftUnion(final int dx, final int dy) {
        final Rectangle rectangle = new Rectangle(this.rect);
        this.rect.translate(dx, dy);
        final Rectangle union = rectangle.union(this.rect);
        union.setSize(union.width + 1, union.height + 1);
        return union;
    }
    
    static {
        MapInternalWindow.areaAccentColor = new Color(153, 204, 153);
        MapInternalWindow.lineBorderColor = Color.gray;
        minimizeCursor = new Cursor(0);
        closeCursor = new Cursor(12);
        moveCursor = new Cursor(13);
        selectionCursor = new Cursor(12);
    }
}
