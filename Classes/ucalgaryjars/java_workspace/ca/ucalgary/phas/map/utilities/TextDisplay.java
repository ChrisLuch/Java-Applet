// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;

public class TextDisplay extends JPanel
{
    boolean displayText;
    String[] data;
    static final String SPACE = "   ";
    static final String REF = "w";
    static final Font DEFAULT_FONT;
    int maxLength;
    Color backGroundColor;
    Color foreGroundColor;
    
    public TextDisplay(final int n, final int maxLength) {
        this.backGroundColor = Color.blue;
        this.foreGroundColor = Color.black;
        this.displayText = true;
        this.data = new String[n];
        for (int i = 0; i < n; ++i) {
            this.data[i] = "    ";
        }
        this.maxLength = maxLength;
    }
    
    public TextDisplay(final int n, final int maxLength, final Color foreGroundColor, final Color backGroundColor) {
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;
        this.displayText = true;
        this.data = new String[n];
        for (int i = 0; i < n; ++i) {
            this.data[i] = "    ";
        }
        this.maxLength = maxLength;
    }
    
    public void paintComponent(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics.setFont(TextDisplay.DEFAULT_FONT);
        graphics.setColor(this.backGroundColor);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(this.foreGroundColor);
        if (this.displayText) {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            fontMetrics.getDescent();
            int n = fontMetrics.stringWidth("   ");
            int height2 = fontMetrics.getHeight();
            for (int i = 0; i < this.data.length; ++i) {
                if (this.maxLength * fontMetrics.stringWidth("w") + n > width) {
                    n = fontMetrics.stringWidth("   ");
                    height2 += fontMetrics.getHeight();
                }
                n += this.maxLength * fontMetrics.stringWidth("w") + fontMetrics.stringWidth("   ");
            }
            final double n2 = (height - 5) / (double)height2;
            int n3 = fontMetrics.stringWidth("   ");
            int n4 = (int)(n2 * fontMetrics.getHeight());
            for (int j = 0; j < this.data.length; ++j) {
                if (this.maxLength * fontMetrics.stringWidth("w") + n3 > width) {
                    n3 = fontMetrics.stringWidth("   ");
                    n4 += (int)(n2 * fontMetrics.getHeight());
                }
                StringDraw.paint(this.data[j], n3, n4, graphics);
                n3 += this.maxLength * fontMetrics.stringWidth("w") + fontMetrics.stringWidth("   ");
            }
        }
    }
    
    public void setDisplayText(final boolean displayText) {
        this.displayText = displayText;
        this.repaint();
    }
    
    public void setText(final int n, final String s) {
        if (n - 1 < this.data.length & n - 1 >= 0) {
            this.data[n - 1] = s;
        }
    }
    
    public void alter(final int n, final int maxLength, final Color foreGroundColor, final Color backGroundColor) {
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;
        this.displayText = true;
        this.data = new String[n];
        for (int i = 0; i < n; ++i) {
            this.data[i] = "    ";
        }
        this.maxLength = maxLength;
    }
    
    static {
        DEFAULT_FONT = new Font("Sanserif", 0, 12);
    }
}
