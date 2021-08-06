// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.FontMetrics;
import javax.swing.ButtonModel;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.SwingUtilities;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.plaf.basic.BasicRadioButtonUI;
import javax.swing.JToolTip;
import javax.swing.plaf.ButtonUI;
import javax.swing.Icon;
import javax.swing.Action;
import java.awt.Color;
import javax.swing.JRadioButton;

public class MapRadioButton extends JRadioButton
{
    protected boolean showHighLight;
    protected Color highLightColor;
    
    public MapRadioButton() {
        this.init();
    }
    
    public MapRadioButton(final Action a) {
        super(a);
        this.init();
    }
    
    public MapRadioButton(final Icon icon) {
        super(icon);
        this.init();
    }
    
    public MapRadioButton(final Icon icon, final boolean selected) {
        super(icon, selected);
        this.init();
    }
    
    public MapRadioButton(final String text) {
        super(text);
        this.init();
    }
    
    public MapRadioButton(final String text, final boolean selected) {
        super(text, selected);
        this.init();
    }
    
    public MapRadioButton(final String text, final Icon icon) {
        super(text, icon);
        this.init();
    }
    
    public MapRadioButton(final String text, final Icon icon, final boolean selected) {
        super(text, icon, selected);
        this.init();
    }
    
    protected void init() {
        this.setUI(new MapRadioButtonUI());
        this.showHighLight = false;
        this.highLightColor = Color.red;
    }
    
    public void setHighLightColor(final Color highLightColor) {
        this.highLightColor = highLightColor;
    }
    
    public Color getHighLightColor() {
        return this.highLightColor;
    }
    
    public void enableHighLight(final boolean showHighLight) {
        this.showHighLight = showHighLight;
        this.repaint();
    }
    
    public boolean isHighLighted() {
        return this.showHighLight;
    }
    
    public JToolTip createToolTip() {
        final JToolTip toolTip = super.createToolTip();
        toolTip.setBackground(MapConstants.mapButtonToolTipBackgroundColor);
        return toolTip;
    }
    
    public class MapRadioButtonUI extends BasicRadioButtonUI
    {
        private Dimension size;
        private Rectangle viewRect;
        private Rectangle iconRect;
        private Rectangle textRect;
        
        public MapRadioButtonUI() {
            this.size = new Dimension();
            this.viewRect = new Rectangle();
            this.iconRect = new Rectangle();
            this.textRect = new Rectangle();
        }
        
        public synchronized void paint(final Graphics g, final JComponent c) {
            final AbstractButton b = (AbstractButton)c;
            final ButtonModel model = b.getModel();
            g.setFont(c.getFont());
            final FontMetrics fontMetrics = g.getFontMetrics();
            this.size = b.getSize(this.size);
            final Rectangle viewRect = this.viewRect;
            final Rectangle viewRect2 = this.viewRect;
            final int n = 0;
            viewRect2.y = n;
            viewRect.x = n;
            this.viewRect.width = this.size.width;
            this.viewRect.height = this.size.height;
            final Rectangle iconRect = this.iconRect;
            final Rectangle iconRect2 = this.iconRect;
            final Rectangle iconRect3 = this.iconRect;
            final Rectangle iconRect4 = this.iconRect;
            final int n2 = 0;
            iconRect4.height = n2;
            iconRect3.width = n2;
            iconRect2.y = n2;
            iconRect.x = n2;
            final Rectangle textRect = this.textRect;
            final Rectangle textRect2 = this.textRect;
            final Rectangle textRect3 = this.textRect;
            final Rectangle textRect4 = this.textRect;
            final int n3 = 0;
            textRect4.height = n3;
            textRect3.width = n3;
            textRect2.y = n3;
            textRect.x = n3;
            final Icon icon = b.getIcon();
            final String layoutCompoundLabel = SwingUtilities.layoutCompoundLabel(c, fontMetrics, b.getText(), (icon != null) ? icon : this.getDefaultIcon(), b.getVerticalAlignment(), b.getHorizontalAlignment(), b.getVerticalTextPosition(), b.getHorizontalTextPosition(), this.viewRect, this.iconRect, this.textRect, this.getDefaultTextIconGap(b));
            if (c.isOpaque()) {
                g.setColor(b.getBackground());
                g.fillRect(0, 0, this.size.width, this.size.height);
            }
            Color color;
            Color darker;
            Color color2;
            if (model.isEnabled()) {
                color = b.getBackground().brighter();
                darker = b.getBackground().darker();
                color2 = Color.black;
            }
            else {
                color = b.getBackground().brighter();
                darker = (color2 = b.getBackground().darker());
            }
            final int x = this.iconRect.x;
            final int y = this.iconRect.y;
            final int width = this.iconRect.width;
            final int height = this.iconRect.height;
            g.setColor(darker);
            g.drawOval(x + 1, y + 1, width - 2, height - 2);
            g.setColor(color);
            g.drawArc(x, y, width, height, 45, -180);
            if (!model.isPressed() || !model.isArmed()) {
                g.drawArc(x + 2, y + 2, width - 4, height - 4, 45, 180);
            }
            if (model.isSelected()) {
                g.setColor(color2);
                g.fillOval(x + 4, y + 4, width - 7, height - 7);
            }
            if (MapRadioButton.this.isHighLighted()) {
                g.setColor(MapRadioButton.this.getHighLightColor());
                g.drawRect(0, 0, this.size.width - 1, this.size.height - 1);
            }
            if (layoutCompoundLabel != null) {
                Color color3;
                if (model.isEnabled()) {
                    color3 = b.getForeground();
                }
                else {
                    color3 = b.getBackground();
                }
                if (model.isEnabled()) {
                    g.setColor(color3);
                    BasicGraphicsUtils.drawString(g, layoutCompoundLabel, model.getMnemonic(), this.textRect.x, this.textRect.y + fontMetrics.getAscent());
                }
                else {
                    g.setColor(color3.brighter());
                    BasicGraphicsUtils.drawString(g, layoutCompoundLabel, model.getMnemonic(), this.textRect.x, this.textRect.y + fontMetrics.getAscent());
                    g.setColor(color3.darker());
                    BasicGraphicsUtils.drawString(g, layoutCompoundLabel, model.getMnemonic(), this.textRect.x - 1, this.textRect.y + fontMetrics.getAscent() - 1);
                }
            }
        }
    }
}
