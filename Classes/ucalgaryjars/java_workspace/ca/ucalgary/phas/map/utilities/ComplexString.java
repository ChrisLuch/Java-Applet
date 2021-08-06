// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.Point;
import java.awt.image.WritableRaster;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import ca.ucalgary.phas.map.utilities.vectors.VectorLabelComponent;

public class ComplexString implements VectorLabelComponent
{
    public static final int X_START_PIXEL = 5;
    public static final char THETA = '\u03b8';
    public static final char TAU = '\u03c4';
    public static final char DEGREE = 'º';
    public static final char DOT = '·';
    public static final char OHMS = '\u03a9';
    public static final char DELTA = '\u0394';
    public static final char OMEGA = '\u03c9';
    public static final char PI = '\u03c0';
    public static final char CAPITAL_PHI = '\u03a6';
    public static final char PHI = '\u03c6';
    public static final char MU = '\u03bc';
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
    private static final String deltaTag = "delta";
    private static final String omegaTag = "omega";
    private static final String piTag = "pi";
    private static final String capitalPhiTag = "capital_phi";
    private static final String phiTag = "phi";
    private static final String muTag = "mu";
    private static final String disabledTag = "disabled";
    private static final String italicTag = "italic";
    private static final String boldTag = "bold";
    private static final String subscriptTag1 = "subscript";
    private static final String superscriptTag1 = "superscript";
    private static final String subscriptTag2 = "sub";
    private static final String superscriptTag2 = "sup";
    private static boolean isSubscript;
    private static boolean isSuperscript;
    private static boolean isDisabled;
    private String value;
    private BufferedImage image;
    private int height;
    private int width;
    private Color color;
    private int baseline;
    private Font font;
    private static final int DEFAULT_IMAGE_INIT_WIDTH = 500;
    private static final int DEFAULT_IMAGE_INIT_HEIGHT = 100;
    private static int[] transparentPixels;
    
    public ComplexString(final ComplexString complexString) {
        this.color = complexString.getColor();
        this.font = complexString.getFont();
        this.setValue(complexString.getValue());
    }
    
    public ComplexString() {
        this("");
    }
    
    public ComplexString(final String s) {
        this(s, Color.black);
    }
    
    public ComplexString(final Color color) {
        this("", color);
    }
    
    public ComplexString(final String s, final Color color) {
        this(s, color, null);
    }
    
    public ComplexString(final String value, final Color color, final Font font) {
        this.font = font;
        this.color = color;
        this.setValue(value);
    }
    
    public void setFont(final Font font) {
        this.font = font;
        this.valueChanged();
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setValue(final String value) {
        this.value = value;
        this.valueChanged();
    }
    
    public void setColor(final Color color) {
        this.color = color;
        this.valueChanged();
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    private void valueChanged() {
        this.image = renderString(this.value, this.color, this.font);
        final Rectangle calcCropDimension = calcCropDimension(this.image, this.value);
        try {
            this.image = this.image.getSubimage(calcCropDimension.x, calcCropDimension.y, calcCropDimension.width, calcCropDimension.height);
        }
        catch (Exception ex) {
            System.out.println("Exception caught in ComplexString.valueChanged() while trying to get a sub image");
            System.out.println("  *       value: " + this.value);
            System.out.println("  *        font: " + this.font);
            System.out.println("  *       color: " + this.color);
            System.out.println("  *       image: " + this.image);
            System.out.println("  *  image size: (" + this.image.getWidth(null) + ", " + this.image.getHeight(null) + ")");
            System.out.println("  *    cropping: (x,y):(" + calcCropDimension.x + ", " + calcCropDimension.y + ")  (h,w):(" + calcCropDimension.width + ", " + calcCropDimension.height + ")");
            System.out.println("  *********** StackTrace Follows:");
            System.out.println("");
            ex.printStackTrace();
        }
        this.baseline = 50 - calcCropDimension.y;
        this.height = this.image.getHeight(null);
        this.width = this.image.getWidth(null);
    }
    
    public int drawBaseline(final Graphics graphics, final int n, final int n2) {
        graphics.drawImage(this.image, n, n2 - this.baseline, this.width, this.height, null);
        return this.width;
    }
    
    public int drawOpaqueBaseLine(final Graphics graphics, final int n, final int n2, final Color color, final boolean b) {
        graphics.setColor(color);
        graphics.fillRect(n - 2, n2 - this.baseline - 2, this.width + 4, this.height + 4);
        graphics.drawImage(this.image, n, n2 - this.baseline, this.width, this.height, null);
        if (b) {
            graphics.setColor(this.color);
            graphics.drawRect(n - 2, n2 - this.baseline - 2, this.width + 4, this.height + 4);
        }
        return this.width;
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        graphics.drawImage(this.image, n, n2 - this.height, this.width, this.height, null);
    }
    
    public void drawOpaque(final Graphics graphics, final int n, final int n2, final Color color, final boolean b) {
        graphics.setColor(color);
        graphics.fillRect(n - 2, n2 - this.height - 2, this.width + 4, this.height + 4);
        graphics.drawImage(this.image, n, n2 - this.height, this.width, this.height, null);
        if (b) {
            graphics.setColor(this.color);
            graphics.drawRect(n - 2, n2 - this.height - 2, this.width + 4, this.height + 4);
        }
    }
    
    public Dimension getSize(final Graphics graphics) {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getSize() {
        return new Dimension(this.width, this.height);
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public static Image createImage(final String s) {
        return createImage(s, Color.black);
    }
    
    public static Image createImage(final String s, final Color color) {
        return createImage(s, color, null);
    }
    
    public static Image createImage(final String s, final Color color, final Font font) {
        BufferedImage bufferedImage = renderString(s, color, font);
        final Rectangle calcCropDimension = calcCropDimension(bufferedImage, s);
        try {
            bufferedImage = bufferedImage.getSubimage(calcCropDimension.x, calcCropDimension.y, calcCropDimension.width, calcCropDimension.height);
        }
        catch (Exception ex) {
            System.out.println("ComplexString.createImage()... Error in getSubImage, subImageRectangle: " + calcCropDimension.x + ", " + calcCropDimension.y + ", " + calcCropDimension.width + ", " + calcCropDimension.height);
            System.out.println("                               Returning entire image...");
        }
        return bufferedImage;
    }
    
    public static Image createDisabledImage(final String s, final Color color) {
        return createImage(s, color, null);
    }
    
    public static Image createDisabledImage(final String s, final Color color, final Font font) {
        BufferedImage bufferedImage = renderString(s, color.brighter(), font);
        BufferedImage bufferedImage2 = renderString(s, color.darker(), font);
        final Rectangle calcCropDimension = calcCropDimension(bufferedImage2, s);
        try {
            bufferedImage = bufferedImage.getSubimage(calcCropDimension.x - 1, calcCropDimension.y - 1, calcCropDimension.width + 1, calcCropDimension.height + 1);
            bufferedImage2 = bufferedImage2.getSubimage(calcCropDimension.x, calcCropDimension.y, calcCropDimension.width + 1, calcCropDimension.height + 1);
        }
        catch (Exception ex) {
            System.out.println("ComplexString.createDisabledImage()... Error in getSubImage, subImageRectangle: " + calcCropDimension.x + ", " + calcCropDimension.y + ", " + calcCropDimension.width + ", " + calcCropDimension.height);
            System.out.println("                                       Returning entire image...");
        }
        bufferedImage.getGraphics().drawImage(bufferedImage2, 0, 0, calcCropDimension.width + 1, calcCropDimension.height + 1, null);
        return bufferedImage;
    }
    
    private static int[] getTransparentPixels() {
        if (ComplexString.transparentPixels == null) {
            ComplexString.transparentPixels = new int[200000];
            for (int i = 0; i < 200000; i += 4) {
                ComplexString.transparentPixels[i + 3] = 0;
            }
        }
        return ComplexString.transparentPixels;
    }
    
    private static Rectangle calcCropDimension(final BufferedImage bufferedImage, final String s) {
        int n = 0;
        int n2 = 50;
        int y = 50;
        final int[] array = new int[4];
        bufferedImage.flush();
        final WritableRaster raster = bufferedImage.getRaster();
        int index = 0;
        int n3 = 0;
        int n4 = 0;
        if (s.length() > 0) {
            try {
                while (s.charAt(index) == ' ') {
                    n3 += 5;
                    ++index;
                }
            }
            catch (Exception ex) {}
            int index2 = s.length() - 1;
            try {
                while (s.charAt(index2) == ' ') {
                    n4 += 5;
                    --index2;
                }
            }
            catch (Exception ex2) {}
        }
        int n5 = 0;
        while (true) {
            boolean b = true;
            for (int i = 5; i < 500; ++i) {
                raster.getPixel(i, n2, array);
                if (array[3] != 0) {
                    if (i > n) {
                        n = i;
                    }
                    b = false;
                }
            }
            if (b) {
                if (++n5 == 9) {
                    break;
                }
            }
            else {
                n5 = 0;
            }
            --n2;
        }
        n2 += 8;
        int n6 = 0;
        while (true) {
            boolean b2 = true;
            for (int j = 5; j < 500; ++j) {
                raster.getPixel(j, y, array);
                if (array[3] != 0) {
                    if (j > n) {
                        n = j;
                    }
                    b2 = false;
                }
            }
            if (b2) {
                if (++n6 == 9) {
                    break;
                }
            }
            else {
                n6 = 0;
            }
            ++y;
        }
        y -= 8;
        return new Rectangle(5, n2, n - 5 + 1, y - n2 + 1);
    }
    
    private static BufferedImage renderString(String string, final Color color, final Font font) {
        final boolean b = true;
        final BufferedImage bufferedImage = new BufferedImage(500, 100, 2);
        bufferedImage.getRaster().setPixels(0, 0, 500, 100, getTransparentPixels());
        int x = 5;
        int y = 50;
        final Point point = new Point(x, y);
        int width = 0;
        final Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(color);
        ComplexString.isDisabled = (ComplexString.isSuperscript = (ComplexString.isSubscript = false));
        Font font2;
        if (font != null) {
            font2 = font;
            graphics.setFont(font2);
        }
        else {
            font2 = graphics.getFont();
        }
        if (font2 == null || font2.getSize() < 4) {
            System.out.println("font is null or too small, setting a default.");
            font2 = new Font("Helvetica", 0, 12);
            graphics.setFont(font2);
        }
        Font font3 = font2;
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
                final Font font4 = font3;
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
                else if (lowerCase.equals("delta")) {
                    str += '\u0394';
                }
                else if (lowerCase.equals("omega")) {
                    str += '\u03c9';
                }
                else if (lowerCase.equals("pi")) {
                    str += '\u03c0';
                }
                else if (lowerCase.equals("phi")) {
                    str += '\u03c6';
                }
                else if (lowerCase.equals("capital_phi")) {
                    str += '\u03a6';
                }
                else if (lowerCase.equals("mu")) {
                    str += '\u03bc';
                }
                else if (lowerCase.equals("newline")) {
                    if (x - point.x > width) {
                        width = x - point.x;
                    }
                    x = point.x;
                    y += graphics.getFontMetrics().getHeight();
                }
                else if (lowerCase.equals("vector")) {
                    if (font4.getStyle() != 1) {
                        string = string.substring(0, index + 1) + '<' + '/' + "bold" + '>' + string.substring(index + 1);
                        font3 = new Font(font4.getName(), 0x1 | font4.getStyle(), font4.getSize());
                    }
                    str = string.substring(index2 + 1, index);
                    if (b) {
                        graphics.setFont(font3);
                        final int n = y - graphics.getFontMetrics().getAscent() - 2;
                        final int stringWidth = graphics.getFontMetrics().stringWidth(str);
                        graphics.drawLine(x, n, x + stringWidth, n);
                        graphics.drawLine(x + stringWidth, n, x + stringWidth - 4, n - 2);
                        graphics.drawLine(x + stringWidth, n, x + stringWidth - 4, n + 2);
                    }
                }
                else if (lowerCase.equals("bold")) {
                    if (font4.getStyle() == 2) {
                        font3 = new Font(font4.getName(), 3, font4.getSize());
                    }
                    else {
                        font3 = new Font(font4.getName(), 1, font4.getSize());
                    }
                }
                else if (lowerCase.equals("/bold")) {
                    if (font4.getStyle() == 3) {
                        font3 = new Font(font4.getName(), 2, font4.getSize());
                    }
                    else {
                        font3 = new Font(font4.getName(), 0, font4.getSize());
                    }
                }
                else if (lowerCase.equals("italic")) {
                    if (font4.getStyle() == 1) {
                        font3 = new Font(font4.getName(), 3, font4.getSize());
                    }
                    else {
                        font3 = new Font(font4.getName(), 2, font4.getSize());
                    }
                }
                else if (lowerCase.equals("/italic")) {
                    if (font4.getStyle() == 3) {
                        font3 = new Font(font4.getName(), 1, font4.getSize());
                    }
                    else {
                        font3 = new Font(font4.getName(), 0, font4.getSize());
                    }
                }
                else if ((lowerCase.equals("subscript") || lowerCase.equals("sub")) && !ComplexString.isSubscript && !ComplexString.isSuperscript) {
                    font3 = new Font(font4.getName(), font4.getStyle(), font4.getSize() - 2);
                    ComplexString.isSubscript = true;
                    y += 2;
                }
                else if ((lowerCase.equals("/subscript") || lowerCase.equals("/sub")) && ComplexString.isSubscript) {
                    font3 = new Font(font4.getName(), font4.getStyle(), font4.getSize() + 2);
                    ComplexString.isSubscript = false;
                    y -= 2;
                }
                else if ((lowerCase.equals("superscript") || lowerCase.equals("sup")) && !ComplexString.isSubscript && !ComplexString.isSuperscript) {
                    font3 = new Font(font4.getName(), font4.getStyle(), font4.getSize() - 2);
                    ComplexString.isSuperscript = true;
                    y -= 4;
                }
                else if ((lowerCase.equals("/superscript") || lowerCase.equals("/sup")) && ComplexString.isSuperscript) {
                    font3 = new Font(font4.getName(), font4.getStyle(), font4.getSize() + 2);
                    ComplexString.isSuperscript = false;
                    y += 4;
                }
                else if (lowerCase.equals("disabled")) {
                    ComplexString.isDisabled = true;
                    graphics.setColor(new Color(140, 140, 140));
                }
                else if (lowerCase.equals("/disabled")) {
                    ComplexString.isDisabled = true;
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
            graphics.setFont(font3);
            if (str != "") {
                if (b && ComplexString.isDisabled) {
                    graphics.setColor(Color.white);
                    graphics.drawString(str, x + 1, y + 1);
                    graphics.setColor(new Color(140, 140, 140));
                }
                if (b) {
                    try {
                        graphics.drawString(str, x, y);
                    }
                    catch (IllegalArgumentException ex) {
                        System.err.println("PLEASE FIX ME! StringDraw (illegalArgumentException) ");
                    }
                }
                x += graphics.getFontMetrics().stringWidth(str);
            }
        }
        graphics.setFont(font2);
        graphics.setColor(color);
        if (ComplexString.isSubscript) {
            y -= 2;
        }
        else if (ComplexString.isSuperscript) {
            y += 4;
        }
        if (x - point.x > width) {
            width = x - point.x;
        }
        final Dimension dimension = new Dimension(width, y - point.y + graphics.getFontMetrics().getHeight() + 5);
        return bufferedImage;
    }
    
    static {
        ComplexString.isSubscript = false;
        ComplexString.isSuperscript = false;
        ComplexString.isDisabled = false;
    }
}
