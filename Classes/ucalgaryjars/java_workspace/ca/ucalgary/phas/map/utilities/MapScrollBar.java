// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import java.awt.Color;
import javax.swing.JScrollBar;

public class MapScrollBar extends JScrollBar
{
    private Color lightColor1;
    private Color lightColor2;
    private Color lightColor3;
    private Color lightColor4;
    private Color darkColor1;
    private Color darkColor2;
    private Color darkColor3;
    private Color darkColor4;
    private Color trackLightColor1;
    private Color trackLightColor2;
    private Color trackLightColor3;
    private Color trackLightColor4;
    private Color trackDarkColor1;
    private Color trackDarkColor2;
    private Color trackDarkColor3;
    private Color trackDarkColor4;
    private MapScrollBarUI mapScrollBarUI;
    
    public MapScrollBar() {
        this.construct();
    }
    
    public MapScrollBar(final int orientation) {
        super(orientation);
        this.construct();
    }
    
    public MapScrollBar(final int orientation, final int value, final int extent, final int min, final int max) {
        super(orientation, value, extent, min, max);
        this.construct();
    }
    
    private void construct() {
        this.setUI(this.mapScrollBarUI = new MapScrollBarUI());
        this.setBorder(null);
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        try {
            this.mapScrollBarUI.setAdjustmentButtonsEnabled(b);
        }
        catch (NullPointerException ex) {}
    }
    
    public class MapScrollBarUI extends BasicScrollBarUI
    {
        public void setAdjustmentButtonsEnabled(final boolean b) {
            super.incrButton.setEnabled(b);
            super.decrButton.setEnabled(b);
        }
        
        protected void configureScrollBarColors() {
            super.thumbHighlightColor = MapConstants.scrollBarThumbHighlightColor;
            super.thumbLightShadowColor = MapConstants.scrollBarThumbLightShadowColor;
            super.thumbDarkShadowColor = MapConstants.scrollBarThumbDarkShadowColor;
            super.thumbColor = MapConstants.scrollBarThumbColor;
            super.trackColor = MapConstants.scrollBarTrackColor;
            super.trackHighlightColor = MapConstants.scrollBarTrackHighlightColor;
            final Integer[] array = new Integer[4];
            final Integer[] array2 = new Integer[4];
            final Integer[] array3 = new Integer[4];
            final Integer[] array4 = new Integer[4];
            final Integer[] array5 = new Integer[4];
            final Integer[] array6 = new Integer[4];
            final int red = super.thumbColor.getRed();
            final int green = super.thumbColor.getGreen();
            final int blue = super.thumbColor.getBlue();
            int n;
            int n2;
            if (red < 80) {
                n = red / 4;
                n2 = 40;
            }
            else if (255 - red < 80) {
                n = 40;
                n2 = (255 - red) / 4;
            }
            else {
                n = 20;
                n2 = 20;
            }
            int n3;
            int n4;
            if (green < 80) {
                n3 = green / 4;
                n4 = 40;
            }
            else if (255 - green < 80) {
                n3 = 40;
                n4 = (255 - green) / 4;
            }
            else {
                n3 = 20;
                n4 = 20;
            }
            int n5;
            int n6;
            if (blue < 80) {
                n5 = blue / 4;
                n6 = 40;
            }
            else if (255 - blue < 80) {
                n5 = 40;
                n6 = (255 - blue) / 4;
            }
            else {
                n5 = 20;
                n6 = 20;
            }
            for (int i = 0; i < 4; ++i) {
                array[i] = new Integer(red + i * n2);
                array2[i] = new Integer(green + i * n4);
                array3[i] = new Integer(blue + i * n6);
                array4[i] = new Integer(red - i * n);
                array5[i] = new Integer(green - i * n3);
                array6[i] = new Integer(blue - i * n5);
            }
            MapScrollBar.this.lightColor4 = new Color(array[3], array2[3], array3[3]);
            MapScrollBar.this.lightColor3 = new Color(array[2], array2[2], array3[2]);
            MapScrollBar.this.lightColor2 = new Color(array[1], array2[1], array3[1]);
            MapScrollBar.this.lightColor1 = new Color(array[0], array2[0], array3[0]);
            MapScrollBar.this.darkColor1 = new Color(array4[0], array5[0], array6[0]);
            MapScrollBar.this.darkColor2 = new Color(array4[1], array5[1], array6[1]);
            MapScrollBar.this.darkColor3 = new Color(array4[2], array5[2], array6[2]);
            MapScrollBar.this.darkColor4 = new Color(array4[3], array5[3], array6[3]);
            final Integer[] array7 = new Integer[4];
            final Integer[] array8 = new Integer[4];
            final Integer[] array9 = new Integer[4];
            final Integer[] array10 = new Integer[4];
            final Integer[] array11 = new Integer[4];
            final Integer[] array12 = new Integer[4];
            final int red2 = super.trackColor.getRed();
            final int green2 = super.trackColor.getGreen();
            final int blue2 = super.trackColor.getBlue();
            int n7;
            int n8;
            if (red2 < 80) {
                n7 = red2 / 4;
                n8 = 40;
            }
            else if (255 - red2 < 80) {
                n7 = 40;
                n8 = (255 - red2) / 4;
            }
            else {
                n7 = 20;
                n8 = 20;
            }
            int n9;
            int n10;
            if (green2 < 80) {
                n9 = green2 / 4;
                n10 = 40;
            }
            else if (255 - green2 < 80) {
                n9 = 40;
                n10 = (255 - green2) / 4;
            }
            else {
                n9 = 20;
                n10 = 20;
            }
            int n11;
            int n12;
            if (blue2 < 80) {
                n11 = blue2 / 4;
                n12 = 40;
            }
            else if (255 - blue2 < 80) {
                n11 = 40;
                n12 = (255 - blue2) / 4;
            }
            else {
                n11 = 20;
                n12 = 20;
            }
            for (int j = 0; j < 4; ++j) {
                array7[j] = new Integer(red2 + j * n8);
                array8[j] = new Integer(green2 + j * n10);
                array9[j] = new Integer(blue2 + j * n12);
                array10[j] = new Integer(red2 - j * n7);
                array11[j] = new Integer(green2 - j * n9);
                array12[j] = new Integer(blue2 - j * n11);
            }
            MapScrollBar.this.trackDarkColor4 = new Color(array7[3], array8[3], array9[3]);
            MapScrollBar.this.trackDarkColor3 = new Color(array7[2], array8[2], array9[2]);
            MapScrollBar.this.trackDarkColor2 = new Color(array7[1], array8[1], array9[1]);
            MapScrollBar.this.trackDarkColor1 = new Color(array7[0], array8[0], array9[0]);
            MapScrollBar.this.trackLightColor1 = new Color(array10[0], array11[0], array12[0]);
            MapScrollBar.this.trackLightColor2 = new Color(array10[1], array11[1], array12[1]);
            MapScrollBar.this.trackLightColor3 = new Color(array10[2], array11[2], array12[2]);
            MapScrollBar.this.trackLightColor4 = new Color(array10[3], array11[3], array12[3]);
        }
        
        protected void installDefaults() {
            super.minimumThumbSize = (Dimension)UIManager.get("ScrollBar.minimumThumbSize");
            super.maximumThumbSize = (Dimension)UIManager.get("ScrollBar.maximumThumbSize");
            super.trackHighlight = 0;
            switch (super.scrollbar.getOrientation()) {
                case 1: {
                    super.incrButton = new MapButton();
                    super.decrButton = new MapButton();
                    break;
                }
                case 0: {
                    super.incrButton = new MapButton();
                    super.decrButton = new MapButton();
                    break;
                }
            }
            super.incrButton.setBackground(MapConstants.scrollBarThumbColor);
            super.decrButton.setBackground(MapConstants.scrollBarThumbColor);
            super.scrollbar.setLayout(this);
            super.scrollbar.add(super.incrButton);
            super.scrollbar.add(super.decrButton);
            super.scrollbar.setEnabled(super.scrollbar.isEnabled());
            super.scrollbar.setOpaque(true);
            this.configureScrollBarColors();
            LookAndFeel.installBorder(super.scrollbar, "ScrollBar.border");
        }
        
        protected void layoutVScrollbar(final JScrollBar scrollBar) {
            final Dimension size = scrollBar.getSize();
            final Insets insets = new Insets(0, 0, 0, 0);
            final int n = size.width - (insets.left + insets.right);
            final int left = insets.left;
            int height = n;
            final int top = insets.top;
            int height2 = n;
            int y = size.height - (insets.bottom + height2);
            final int n2 = insets.top + insets.bottom;
            final int n3 = height + height2;
            final float n4 = (float)(size.height - (n2 + n3));
            final float n5 = (float)scrollBar.getMinimum();
            final float n6 = (float)scrollBar.getVisibleAmount();
            final float n7 = scrollBar.getMaximum() - n5;
            final float n8 = (float)scrollBar.getValue();
            final int min = Math.min(Math.max((n7 <= 0.0f) ? this.getMaximumThumbSize().height : ((int)(n4 * (n6 / n7))), this.getMinimumThumbSize().height), this.getMaximumThumbSize().height);
            int y2 = y - min;
            if (scrollBar.getValue() < scrollBar.getMaximum() - scrollBar.getVisibleAmount()) {
                y2 = (int)(0.5f + (n4 - min) * ((n8 - n5) / (n7 - n6))) + (top + height);
            }
            final int n9 = size.height - n2;
            if (n9 < n3) {
                height = (height2 = n9 / 2);
                y = size.height - (insets.bottom + height2);
            }
            super.decrButton.setBounds(left, top, n, height);
            super.incrButton.setBounds(left, y, n, height2);
            final int y3 = top + height;
            super.trackRect.setBounds(left, y3, n, y - y3);
            if (min >= (int)n4) {
                this.setThumbBounds(0, 0, 0, 0);
            }
            else {
                if (y2 + min > y) {
                    y2 = y - min;
                }
                if (y2 < top + height) {
                    y2 = top + height + 1;
                }
                this.setThumbBounds(left, y2, n, min);
            }
        }
        
        protected void layoutHScrollbar(final JScrollBar scrollBar) {
            final Dimension size = scrollBar.getSize();
            final Insets insets = scrollBar.getInsets();
            final int n = size.height - (insets.top + insets.bottom);
            final int top = insets.top;
            int width = n;
            final int left = insets.left;
            int width2 = n;
            int x = size.width - (insets.right + width2);
            final int n2 = insets.left + insets.right;
            final int n3 = width + width2;
            final float n4 = (float)(size.width - (n2 + n3));
            final float n5 = (float)scrollBar.getMinimum();
            final float n6 = (float)scrollBar.getVisibleAmount();
            final float n7 = scrollBar.getMaximum() - n5;
            final float n8 = (float)scrollBar.getValue();
            final int min = Math.min(Math.max((n7 <= 0.0f) ? this.getMaximumThumbSize().width : ((int)(n4 * (n6 / n7))), this.getMinimumThumbSize().width), this.getMaximumThumbSize().width);
            int x2 = x - min;
            if (scrollBar.getValue() < scrollBar.getMaximum() - scrollBar.getVisibleAmount()) {
                x2 = (int)(0.5f + (n4 - min) * ((n8 - n5) / (n7 - n6))) + (left + width);
            }
            final int n9 = size.width - n2;
            if (n9 < n3) {
                width = (width2 = n9 / 2);
                x = size.width - (insets.right + width2);
            }
            super.decrButton.setBounds(left, top, width, n);
            super.incrButton.setBounds(x, top, width2, n);
            final int x3 = left + width;
            super.trackRect.setBounds(x3, top, x - x3, n);
            if (min >= (int)n4) {
                this.setThumbBounds(0, 0, 0, 0);
            }
            else {
                if (x2 + min > x) {
                    x2 = x - min;
                }
                if (x2 < left + width) {
                    x2 = left + width + 1;
                }
                this.setThumbBounds(x2, top, min, n);
            }
        }
        
        public Dimension getPreferredSize(final JComponent component) {
            return (super.scrollbar.getOrientation() == 1) ? new Dimension(7, 48) : new Dimension(48, 7);
        }
        
        protected void paintTrack(final Graphics graphics, final JComponent component, final Rectangle rectangle) {
            final int width = rectangle.width;
            final int height = rectangle.height;
            final int x = rectangle.x;
            final int y = rectangle.y;
            final int n = x + 1;
            final int n2 = x + 2;
            final int n3 = y + 1;
            final int n4 = y + 2;
            final int n5 = x + width - 1;
            final int n6 = n5 - 1;
            final int n7 = n5 - 2;
            final int n8 = y + height - 1;
            final int n9 = n8 - 1;
            final int n10 = n8 - 2;
            graphics.setColor(super.trackColor);
            graphics.fillRect(x, y, width - 1, height - 1);
            graphics.setColor(MapScrollBar.this.trackLightColor4);
            graphics.drawLine(x, y, n, y);
            graphics.drawLine(x, n3, x, n3);
            graphics.setColor(MapScrollBar.this.trackLightColor3);
            graphics.drawLine(n, n3, n, n3);
            graphics.drawLine(n2, y, n7, y);
            graphics.drawLine(x, n4, x, n10);
            graphics.setColor(MapScrollBar.this.trackLightColor2);
            graphics.drawLine(n2, n3, n7, n3);
            graphics.drawLine(n, n4, n, n10);
            graphics.drawLine(n6, y, n6, y);
            graphics.drawLine(x, n9, x, n9);
            graphics.setColor(MapScrollBar.this.trackLightColor1);
            graphics.drawLine(n2, n4, n7, n4);
            graphics.drawLine(n2, n4, n2, n10);
            graphics.setColor(MapScrollBar.this.trackDarkColor1);
            graphics.drawLine(x, n8, n, n9);
            graphics.drawLine(n6, n3, n5, y);
            graphics.drawLine(n7, n10, n2, n10);
            graphics.drawLine(n7, n10, n7, n4);
            graphics.setColor(MapScrollBar.this.trackDarkColor2);
            graphics.drawLine(n7, n9, n2, n9);
            graphics.drawLine(n6, n10, n6, n4);
            graphics.drawLine(n5, n3, n5, n3);
            graphics.drawLine(n, n8, n, n8);
            graphics.setColor(MapScrollBar.this.trackDarkColor3);
            graphics.drawLine(n7, n8, n2, n8);
            graphics.drawLine(n5, n10, n5, n4);
            graphics.drawLine(n6, n9, n6, n9);
            graphics.setColor(MapScrollBar.this.trackDarkColor4);
            graphics.drawLine(n5, n8, n6, n8);
            graphics.drawLine(n5, n9, n5, n9);
        }
        
        protected void paintThumb(final Graphics graphics, final JComponent component, final Rectangle rectangle) {
            if (rectangle.isEmpty() || !super.scrollbar.isEnabled()) {
                return;
            }
            final int width = rectangle.width;
            final int height = rectangle.height;
            final int x = rectangle.x;
            final int y = rectangle.y;
            final int n = x + 1;
            final int n2 = x + 2;
            final int n3 = y + 1;
            final int n4 = y + 2;
            final int n5 = x + width - 1;
            final int n6 = n5 - 1;
            final int n7 = n5 - 2;
            final int n8 = y + height - 1;
            final int n9 = n8 - 1;
            final int n10 = n8 - 2;
            graphics.setColor(super.thumbColor);
            graphics.fillRect(x, y, width - 1, height - 1);
            graphics.setColor(MapScrollBar.this.lightColor4);
            graphics.drawLine(x, y, n, y);
            graphics.drawLine(x, n3, x, n3);
            graphics.setColor(MapScrollBar.this.lightColor3);
            graphics.drawLine(n, n3, n, n3);
            graphics.drawLine(n2, y, n7, y);
            graphics.drawLine(x, n4, x, n10);
            graphics.setColor(MapScrollBar.this.lightColor2);
            graphics.drawLine(n2, n3, n7, n3);
            graphics.drawLine(n, n4, n, n10);
            graphics.drawLine(n6, y, n6, y);
            graphics.drawLine(x, n9, x, n9);
            graphics.setColor(MapScrollBar.this.lightColor1);
            graphics.drawLine(n2, n4, n7, n4);
            graphics.drawLine(n2, n4, n2, n10);
            graphics.setColor(MapScrollBar.this.darkColor1);
            graphics.drawLine(x, n8, n, n9);
            graphics.drawLine(n6, n3, n5, y);
            graphics.drawLine(n7, n10, n2, n10);
            graphics.drawLine(n7, n10, n7, n4);
            graphics.setColor(MapScrollBar.this.darkColor2);
            graphics.drawLine(n7, n9, n2, n9);
            graphics.drawLine(n6, n10, n6, n4);
            graphics.drawLine(n5, n3, n5, n3);
            graphics.drawLine(n, n8, n, n8);
            graphics.setColor(MapScrollBar.this.darkColor3);
            graphics.drawLine(n7, n8, n2, n8);
            graphics.drawLine(n5, n10, n5, n4);
            graphics.drawLine(n6, n9, n6, n9);
            graphics.setColor(MapScrollBar.this.darkColor4);
            graphics.drawLine(n5, n8, n6, n8);
            graphics.drawLine(n5, n9, n5, n9);
        }
    }
}
