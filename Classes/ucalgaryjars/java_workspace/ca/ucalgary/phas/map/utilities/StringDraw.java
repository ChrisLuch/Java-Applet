// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class StringDraw
{
    public static final char THETA = '\u03b8';
    public static final char TAU = '\u03c4';
    public static final char DEGREE = 'º';
    public static final char DOT = '·';
    public static final char OHMS = '\u03a9';
    public String value;
    public Point location;
    public static final int subscriptHeight = 2;
    public static final int subscriptSize = -2;
    public static final int superscriptHeight = -4;
    public static final int superscriptSize = -2;
    private static final char openDelimiter = '<';
    private static final char closeDelimiter = '>';
    private static final char midDelimiter = ':';
    private static final char offTag = '/';
    private static final String thetaTag = "theta";
    private static final String degreeTag = "degree";
    private static final String tauTag = "tau";
    private static final String newlineTag = "newline";
    private static final String dotTag = "dot";
    private static final String vectorTag = "vector";
    private static final String disabledTag = "disabled";
    private static final String italicTag = "italic";
    private static final String boldTag = "bold";
    private static final String subscriptTag = "subscript";
    private static final String superscriptTag = "superscript";
    private static boolean isSubscript;
    private static boolean isSuperscript;
    private static boolean isDisabled;
    
    public StringDraw() {
        this.value = "";
        this.location = new Point();
    }
    
    public StringDraw(final StringDraw stringDraw) {
        this.value = stringDraw.value;
    }
    
    public StringDraw(final String original) {
        this.value = new String(original);
    }
    
    public static void drawBorder(final int n, final int n2, final int width, final int height, final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.white);
        graphics.fillRect(n, n2, width, height);
        graphics.setColor(Color.lightGray);
        graphics.drawRect(n, n2, width - 2, height - 2);
        graphics.setColor(Color.darkGray);
        graphics.drawRect(n, n2, width - 1, height - 1);
        graphics.setColor(Color.black);
        graphics.drawRect(n + 1, n2 + 1, width, height);
        graphics.setColor(color);
    }
    
    public static void drawBorder(final int n, final int n2, final int width, final int height, final Graphics graphics, final Color color) {
        final Color color2 = graphics.getColor();
        graphics.setColor(color);
        graphics.fillRect(n, n2, width, height);
        graphics.setColor(Color.lightGray);
        graphics.drawRect(n, n2, width - 2, height - 2);
        graphics.setColor(Color.darkGray);
        graphics.drawRect(n, n2, width - 1, height - 1);
        graphics.setColor(Color.black);
        graphics.drawRect(n + 1, n2 + 1, width, height);
        graphics.setColor(color2);
    }
    
    public static int getHeight(final String s, final Graphics graphics) {
        return renderString(false, s, 0, 0, graphics).height;
    }
    
    public static ImageIcon getIcon(final String s, final Component component) {
        final Graphics graphics = component.getGraphics();
        if (graphics == null) {
            System.out.println("StringDraw.getIcon: Source failed to get graphics.");
            return null;
        }
        final Dimension renderString = renderString(false, s, 0, 0, graphics);
        final Image image = component.createImage(renderString.width, renderString.height);
        if (image == null) {
            System.out.println("StringDraw.getIcon: Source failed to create Image.");
            return null;
        }
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(component.getBackground());
        graphics2.fillRect(0, 0, renderString.width, renderString.height);
        graphics2.setColor(component.getForeground());
        graphics2.setFont(component.getGraphics().getFont());
        paint(s, 0, graphics2.getFontMetrics().getAscent() + 5, graphics2);
        return new ImageIcon(image);
    }
    
    public static JLabel getJLabel(final String s, final Component component) {
        final ImageIcon icon = getIcon(s, component);
        if (icon == null) {
            System.out.println("StringDraw.getJLabel: Icon creation failed.");
            return null;
        }
        final JLabel label = new JLabel(icon);
        label.setSize(icon.getIconWidth(), icon.getIconHeight());
        return label;
    }
    
    public static int getWidth(final String s, final Graphics graphics) {
        return renderString(false, s, 0, 0, graphics).width;
    }
    
    public static void paint(final String s, final int n, final int n2, final Graphics graphics) {
        renderString(true, s, n, n2, graphics);
    }
    
    public static void paint(final String s, final Point point, final Graphics graphics) {
        paint(s, point.x, point.y, graphics);
    }
    
    private static Dimension renderString(final boolean b, String string, int x, int y, final Graphics graphics) {
        if (graphics == null) {
            System.out.println("StringDraw.renderString: Graphics is null.");
            return null;
        }
        int width = 0;
        final Point point = new Point(x, y);
        final Color color = graphics.getColor();
        StringDraw.isDisabled = (StringDraw.isSuperscript = (StringDraw.isSubscript = false));
        Font font = graphics.getFont();
        if (font == null || graphics.getFont().getSize() < 4) {
            font = new Font("Dialog", 0, 12);
        }
        graphics.setFont(new Font(font.getName(), 0, font.getSize()));
        int i = 0;
        int endIndex = string.indexOf("<");
        if (endIndex == -1) {
            endIndex = string.length();
        }
        while (i != string.length()) {
            String str;
            if (i != endIndex) {
                str = string.substring(i, endIndex);
                i = endIndex;
            }
            else {
                int index = string.indexOf(">", i);
                final int index2 = string.indexOf(":", i);
                if (index == -1) {
                    string.length();
                    break;
                }
                String s;
                if (index2 != -1 && index2 < index) {
                    s = string.substring(i + 1, index2);
                }
                else {
                    s = string.substring(i + 1, index);
                }
                final Font font2 = graphics.getFont();
                str = "";
                final String lowerCase = s.toLowerCase();
                if (lowerCase.equals("theta")) {
                    str += '\u03b8';
                }
                else if (lowerCase.equals("degree")) {
                    str += 'º';
                }
                else if (lowerCase.equals("tau")) {
                    str += '\u03c4';
                }
                else if (lowerCase.equals("dot")) {
                    str += '·';
                }
                else if (lowerCase.equals("newline")) {
                    if (x - point.x > width) {
                        width = x - point.x;
                    }
                    x = point.x;
                    y += graphics.getFontMetrics().getHeight();
                }
                else if (lowerCase.equals("vector")) {
                    if (font2.getStyle() != 1) {
                        string = string.substring(0, index + 1) + '<' + '/' + "bold" + '>' + string.substring(index + 1);
                        graphics.setFont(new Font(font2.getName(), 0x1 | font2.getStyle(), font2.getSize()));
                    }
                    str = string.substring(index2 + 1, index);
                    if (b) {
                        final int n = y - graphics.getFontMetrics().getAscent() - 1;
                        final int stringWidth = graphics.getFontMetrics().stringWidth(str);
                        graphics.drawLine(x, n, x + stringWidth - 2, n);
                        graphics.drawLine(x + stringWidth - 2, n, x + stringWidth - 4, n - 2);
                        graphics.drawLine(x + stringWidth - 2, n, x + stringWidth - 4, n + 2);
                    }
                }
                else if (lowerCase.equals("bold")) {
                    if (font2.getStyle() == 2) {
                        graphics.setFont(new Font(font2.getName(), 3, font2.getSize()));
                    }
                    else {
                        graphics.setFont(new Font(font2.getName(), 1, font2.getSize()));
                    }
                }
                else if (lowerCase.equals("/bold")) {
                    if (font2.getStyle() == 3) {
                        graphics.setFont(new Font(font2.getName(), 2, font2.getSize()));
                    }
                    else {
                        graphics.setFont(new Font(font2.getName(), 0, font2.getSize()));
                    }
                }
                else if (lowerCase.equals("italic")) {
                    if (font2.getStyle() == 1) {
                        graphics.setFont(new Font(font2.getName(), 3, font2.getSize()));
                    }
                    else {
                        graphics.setFont(new Font(font2.getName(), 2, font2.getSize()));
                    }
                }
                else if (lowerCase.equals("/italic")) {
                    if (font2.getStyle() == 3) {
                        graphics.setFont(new Font(font2.getName(), 1, font2.getSize()));
                    }
                    else {
                        graphics.setFont(new Font(font2.getName(), 0, font2.getSize()));
                    }
                }
                else if (lowerCase.equals("subscript") && !StringDraw.isSubscript && !StringDraw.isSuperscript) {
                    StringDraw.isSubscript = true;
                    graphics.setFont(new Font(font2.getName(), font2.getStyle(), font2.getSize() - 2));
                    y += 2;
                }
                else if (lowerCase.equals("/subscript") && StringDraw.isSubscript) {
                    StringDraw.isSubscript = false;
                    graphics.setFont(new Font(font2.getName(), font2.getStyle(), font2.getSize() + 2));
                    y -= 2;
                }
                else if (lowerCase.equals("superscript") && !StringDraw.isSubscript && !StringDraw.isSuperscript) {
                    StringDraw.isSuperscript = true;
                    graphics.setFont(new Font(font2.getName(), font2.getStyle(), font2.getSize() - 2));
                    y -= 4;
                }
                else if (lowerCase.equals("/superscript") && StringDraw.isSuperscript) {
                    StringDraw.isSuperscript = false;
                    graphics.setFont(new Font(font2.getName(), font2.getStyle(), font2.getSize() + 2));
                    y += 4;
                }
                else if (lowerCase.equals("disabled")) {
                    StringDraw.isDisabled = true;
                    graphics.setColor(new Color(140, 140, 140));
                }
                else if (lowerCase.equals("/disabled")) {
                    StringDraw.isDisabled = true;
                    graphics.setColor(color);
                }
                else {
                    str += '<';
                    index = i;
                }
                i = index + 1;
                endIndex = string.indexOf("<", i);
                if (endIndex == -1) {
                    endIndex = string.length();
                }
            }
            if (str != "") {
                if (b && StringDraw.isDisabled) {
                    graphics.setColor(Color.white);
                    graphics.drawString(str, x + 1, y + 1);
                    graphics.setColor(new Color(140, 140, 140));
                }
                if (b) {
                    graphics.drawString(str, x, y);
                }
                x += graphics.getFontMetrics().stringWidth(str);
            }
        }
        graphics.setFont(font);
        graphics.setColor(color);
        if (StringDraw.isSubscript) {
            y -= 2;
        }
        else if (StringDraw.isSuperscript) {
            y += 4;
        }
        if (x - point.x > width) {
            width = x - point.x;
        }
        return new Dimension(width, y - point.y + graphics.getFontMetrics().getHeight() + 5);
    }
    
    static {
        StringDraw.isSubscript = false;
        StringDraw.isSuperscript = false;
        StringDraw.isDisabled = false;
    }
}
