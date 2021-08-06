// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities.vectors;

import java.util.Enumeration;
import java.awt.Graphics;
import ca.ucalgary.phas.map.utilities.MapConstants;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import javax.swing.JComponent;

public class VectorString extends JComponent implements VectorLabelComponent
{
    protected Vector strings;
    protected Vector subscripts;
    protected Vector flags;
    protected int count;
    protected Color labelColor;
    protected Font normalFont;
    protected Font vectorFont;
    protected Font subScriptFont;
    protected Dimension size;
    protected FontMetrics fm;
    private static final int SPACE = 2;
    private static final int SUBSPACE = 1;
    private static final int ADDVECTORHEIGHT = 4;
    private boolean containsSubs;
    
    public VectorString() {
        this.size = null;
        this.labelColor = MapConstants.vectorDefaultColor;
        this.normalFont = MapConstants.vectorNormalFont;
        this.vectorFont = MapConstants.vectorFont;
        this.subScriptFont = MapConstants.vectorSubscriptFont;
        this.strings = new Vector();
        this.subscripts = new Vector();
        this.containsSubs = false;
        this.flags = new Vector();
        this.count = 0;
    }
    
    public VectorString(final String obj, final boolean b) {
        this();
        this.count = 1;
        this.strings.addElement(obj);
        this.subscripts.addElement("");
        this.containsSubs = false;
        this.flags.addElement(b ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public VectorString(final String s, final String obj, final boolean b) {
        this(s, b);
        (this.subscripts = new Vector()).addElement(obj);
        this.containsSubs = !obj.equals("");
    }
    
    public VectorString(final String[] array, final String[] array2, final int count) {
        this();
        this.count = count;
        this.containsSubs = false;
        for (int i = 0; i < count; ++i) {
            this.strings.addElement(array[i]);
            this.subscripts.addElement("");
            this.flags.addElement(array2[i].equals("true") ? Boolean.TRUE : Boolean.FALSE);
        }
    }
    
    public VectorString(final String[] array, final String[] array2, final String[] array3, final int n) {
        this(array, array3, n);
        this.subscripts = new Vector();
        for (int i = 0; i < n; ++i) {
            this.subscripts.addElement(array2[i]);
            if (!array2[i].equals("")) {
                this.containsSubs = true;
            }
        }
    }
    
    public VectorString(final VectorString vectorString) {
        this.labelColor = vectorString.getColor();
        this.normalFont = vectorString.getNormalFont();
        this.vectorFont = vectorString.getVectorFont();
        this.subScriptFont = vectorString.getSubScriptFont();
        this.size = null;
        this.strings = new Vector();
        this.subscripts = new Vector();
        this.flags = new Vector();
        this.count = vectorString.count;
        this.containsSubs = vectorString.containsSubs;
        for (int i = 0; i < this.count; ++i) {
            this.strings.addElement(vectorString.strings.elementAt(i));
            this.subscripts.addElement(vectorString.subscripts.elementAt(i));
            this.flags.addElement(vectorString.flags.elementAt(i));
        }
    }
    
    public void paint(final Graphics graphics) {
        this.draw(graphics, 0, this.getSize().height);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        final Color color = graphics.getColor();
        final Font font = graphics.getFont();
        graphics.setColor(this.labelColor);
        int n3 = n;
        int n4;
        if (this.containsSubs) {
            n4 = n2 - (int)(graphics.getFontMetrics(this.subScriptFont).getHeight() / 2 + 0.5);
        }
        else {
            n4 = n2;
        }
        for (int i = 0; i < this.count; ++i) {
            if (this.flags.elementAt(i)) {
                graphics.setFont(this.vectorFont);
                n3 += (int)(this.drawVectorLabel(graphics, n3, n4, (String)this.strings.elementAt(i), (String)this.subscripts.elementAt(i)).getWidth() + 2.0);
            }
            else {
                graphics.setFont(this.normalFont);
                n3 += (int)(this.drawString(graphics, n3, n4, (String)this.strings.elementAt(i), (String)this.subscripts.elementAt(i)).getWidth() + 2.0);
            }
        }
        graphics.setFont(font);
        graphics.setColor(color);
    }
    
    protected Dimension drawString(final Graphics graphics, final int n, final int n2, final String str, final String str2) {
        graphics.drawString(str, n, n2);
        this.fm = graphics.getFontMetrics();
        final int height = (int)(this.fm.getHeight() + 0.5);
        int width = (int)(this.fm.stringWidth(str) + 0.5);
        if (!str2.equals("")) {
            graphics.setFont(this.subScriptFont);
            this.fm = graphics.getFontMetrics();
            graphics.drawString(str2, n + width + 1, n2 + (int)(this.fm.getHeight() / 2 + 0.5));
            width += (int)(this.fm.stringWidth(str2) + 0.5);
        }
        return new Dimension(width, height);
    }
    
    protected Dimension drawVectorLabel(final Graphics graphics, final int n, final int n2, final String s, final String s2) {
        final Dimension drawString = this.drawString(graphics, n, n2, s, s2);
        final int width = (int)(drawString.getWidth() + 0.5);
        final int n3 = (int)(drawString.getHeight() + 0.5);
        graphics.drawLine(n, n2 - n3, n + width, n2 - n3);
        graphics.drawLine(n + width, n2 - n3, n + width - 2, n2 - n3 - 2);
        graphics.drawLine(n + width, n2 - n3, n + width - 2, n2 - n3 + 2);
        return new Dimension(width, n3 + 4);
    }
    
    public void setString(final String element, final String element2, final boolean b, final int n) {
        this.flags.remove(n);
        this.flags.add(n, b ? Boolean.TRUE : Boolean.FALSE);
        this.subscripts.remove(n);
        this.subscripts.add(n, element2);
        this.containsSubs = false;
        final Enumeration<String> elements = this.subscripts.elements();
        while (elements.hasMoreElements()) {
            if (!elements.nextElement().equals("")) {
                this.containsSubs = true;
                break;
            }
        }
        this.strings.remove(n);
        this.strings.add(n, element);
        this.size = null;
    }
    
    public void setString(final String s, final boolean b, final int n) {
        this.setString(s, "", b, n);
    }
    
    public Dimension getPreferredSize() {
        return this.getSize();
    }
    
    public Dimension getMinimumSize() {
        return this.getSize();
    }
    
    public Dimension getMaximumSize() {
        return this.getSize();
    }
    
    public Dimension getSize(final Graphics graphics) {
        if (this.size == null) {
            final FontMetrics fontMetrics = graphics.getFontMetrics(this.normalFont);
            final FontMetrics fontMetrics2 = graphics.getFontMetrics(this.vectorFont);
            final FontMetrics fontMetrics3 = graphics.getFontMetrics(this.subScriptFont);
            int width = 0;
            boolean b = false;
            boolean b2 = false;
            for (int i = 0; i < this.count; ++i) {
                if (this.flags.elementAt(i)) {
                    width += fontMetrics2.stringWidth((String)this.strings.elementAt(i));
                    b = true;
                    if (!((String)this.subscripts.elementAt(i)).equals("")) {
                        width += fontMetrics3.stringWidth((String)this.subscripts.elementAt(i));
                        b2 = true;
                        ++width;
                    }
                    width += 2;
                }
                else {
                    width += fontMetrics.stringWidth((String)this.strings.elementAt(i));
                    if (!((String)this.subscripts.elementAt(i)).equals("")) {
                        width += fontMetrics3.stringWidth((String)this.subscripts.elementAt(i));
                        b2 = true;
                        ++width;
                    }
                    width += 2;
                }
            }
            int height;
            if (b) {
                height = Math.max(fontMetrics.getHeight(), fontMetrics2.getHeight());
                height += 4;
            }
            else {
                height = fontMetrics.getHeight();
            }
            if (b2) {
                height += (int)(fontMetrics3.getHeight() / 2 + 0.5);
            }
            this.size = new Dimension(width, height);
        }
        return this.size;
    }
    
    public void setColor(final Color labelColor) {
        this.labelColor = labelColor;
    }
    
    public Color getColor() {
        return this.labelColor;
    }
    
    public void setNormalFont(final Font font) {
        if (!this.normalFont.equals(font)) {
            this.normalFont = font;
            this.size = null;
        }
    }
    
    public Font getNormalFont() {
        return this.normalFont;
    }
    
    public void setVectorFont(final Font font) {
        if (!this.vectorFont.equals(font)) {
            this.vectorFont = font;
            this.size = null;
        }
    }
    
    public Font getVectorFont() {
        return this.vectorFont;
    }
    
    public void setSubScriptFont(final Font font) {
        if (!this.subScriptFont.equals(font)) {
            this.subScriptFont = font;
            this.size = null;
        }
    }
    
    public Font getSubScriptFont() {
        return this.subScriptFont;
    }
}
