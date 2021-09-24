// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.FontMetrics;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.AbstractButton;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.awt.Graphics;
import javax.swing.plaf.basic.BasicButtonUI;
import java.util.MissingResourceException;
import javax.swing.ButtonModel;
import javax.swing.event.ChangeEvent;
import javax.swing.JToolTip;
import java.awt.Insets;
import javax.swing.plaf.ButtonUI;
import javax.swing.border.Border;
import javax.swing.Action;
import javax.swing.Icon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;

public class MapButton extends JButton implements ChangeListener, MapLocaleListener
{
    private boolean textShift;
    private Color focusColor;
    Font mapButtonFont;
    String overridePropertyName;
    String overrideTextKey;
    String overrideToolTipKey;
    
    public MapButton() {
        this.textShift = true;
        this.mapButtonFont = new Font("SansSerif", 0, 10);
        this.construct();
    }
    
    public MapButton(final String text) {
        super(text);
        this.textShift = true;
        this.mapButtonFont = new Font("SansSerif", 0, 10);
        this.construct();
    }
    
    public MapButton(final String text, final Icon icon) {
        super(text, icon);
        this.textShift = true;
        this.mapButtonFont = new Font("SansSerif", 0, 10);
        this.construct();
    }
    
    public MapButton(final Icon icon) {
        super(icon);
        this.textShift = true;
        this.mapButtonFont = new Font("SansSerif", 0, 10);
        this.construct();
    }
    
    public MapButton(final Action a) {
        super(a);
        this.textShift = true;
        this.mapButtonFont = new Font("SansSerif", 0, 10);
        this.construct();
    }
    
    public Color getFocusColor() {
        return this.focusColor;
    }
    
    public void setFocusColor(final Color focusColor) {
        this.focusColor = focusColor;
    }
    
    private void construct() {
        this.addChangeListener(this);
        this.setBorder(new MapBorder(1));
        this.setUI(new MapButtonUI());
        this.setFocusColor(MapConstants.mapButtonFocusColor);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(3);
        this.setFont(this.mapButtonFont);
    }
    
    public void setMinimalInsets() {
        ((MapBorder)this.getBorder()).setBorderInsets(new Insets(0, 0, 0, 0));
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        if (this.getBorder() instanceof MapBorder) {
            ((MapBorder)this.getBorder()).setBackground(color);
        }
    }
    
    public JToolTip createToolTip() {
        final JToolTip toolTip = super.createToolTip();
        toolTip.setBackground(MapConstants.mapButtonToolTipBackgroundColor);
        return toolTip;
    }
    
    public void setTextShiftEnabled(final boolean textShift) {
        this.textShift = textShift;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final MapButton mapButton = (MapButton)changeEvent.getSource();
        final ButtonModel model = mapButton.getModel();
        final Border border = mapButton.getBorder();
        if (border instanceof MapBorder) {
            if (model.isPressed() && model.isArmed()) {
                if (!model.isEnabled()) {
                    ((MapBorder)border).setLevel(3);
                }
                else {
                    ((MapBorder)border).setLevel(2);
                }
            }
            else if (!model.isEnabled()) {
                ((MapBorder)border).setLevel(4);
            }
            else {
                ((MapBorder)border).setLevel(1);
            }
        }
        mapButton.repaint();
    }
    
    public boolean textShiftIsEnabled() {
        return this.textShift;
    }
    
    public void localeChanged() {
        if (!this.getName().equals("")) {
            this.setLocaleDependantValues();
        }
    }
    
    public void setOverrideTextKey(final String overridePropertyName, final String overrideTextKey) {
        this.overridePropertyName = overridePropertyName;
        this.overrideTextKey = overrideTextKey;
        this.setLocaleDependantValues();
    }
    
    public void setOverrideToolTipKey(final String overridePropertyName, final String overrideToolTipKey) {
        this.overridePropertyName = overridePropertyName;
        this.overrideToolTipKey = overrideToolTipKey;
        this.setLocaleDependantValues();
    }
    
    public void setLocaleDependantValues() {
        try {
            if (this.overrideTextKey != null && this.overridePropertyName != null) {
                this.setText(PropertyManager.getProperty(this.overridePropertyName, MapLocaleManager.getLocale(), this.overrideTextKey));
            }
            else {
                this.setText(PropertyManager.getProperty("ca.ucalgary.phas.map.utilities.UtilitiesProperties", MapLocaleManager.getLocale(), "MapButton_" + this.getName() + "_text"));
            }
        }
        catch (MissingResourceException ex) {
            System.out.println(" ** MissingResourceException caught.... Could not set Text");
            ex.printStackTrace();
        }
        try {
            if (this.overrideToolTipKey != null && this.overridePropertyName != null) {
                this.setToolTipText(PropertyManager.getProperty(this.overridePropertyName, MapLocaleManager.getLocale(), this.overrideToolTipKey));
            }
            else {
                this.setToolTipText(PropertyManager.getProperty("ca.ucalgary.phas.map.utilities.UtilitiesProperties", MapLocaleManager.getLocale(), "MapButton_" + this.getName() + "_tooltip"));
            }
        }
        catch (MissingResourceException ex2) {
            System.out.println(" ** MissingResourceException caught.... Could not set ToolTipText");
            ex2.printStackTrace();
        }
    }
    
    public class MapButtonUI extends BasicButtonUI
    {
        protected void paintText(final Graphics g, final JComponent component, final Rectangle rectangle, final String text) {
            final AbstractButton abstractButton = (AbstractButton)component;
            final ButtonModel model = abstractButton.getModel();
            final FontMetrics fontMetrics = g.getFontMetrics();
            if (model.isEnabled()) {
                g.setColor(abstractButton.getForeground());
                BasicGraphicsUtils.drawString(g, text, model.getMnemonic(), rectangle.x + this.getTextShiftOffset(), rectangle.y + fontMetrics.getAscent() + this.getTextShiftOffset());
            }
            else {
                g.setColor(abstractButton.getBackground().brighter());
                BasicGraphicsUtils.drawString(g, text, model.getMnemonic(), rectangle.x + 1, rectangle.y + 1 + fontMetrics.getAscent());
                g.setColor(abstractButton.getBackground().darker());
                BasicGraphicsUtils.drawString(g, text, model.getMnemonic(), rectangle.x, rectangle.y + fontMetrics.getAscent());
            }
        }
        
        protected void paintFocus(final Graphics graphics, final AbstractButton abstractButton, final Rectangle rectangle, final Rectangle rectangle2, final Rectangle bounds) {
            final Rectangle rectangle3 = new Rectangle();
            final String text = abstractButton.getText();
            final boolean b = abstractButton.getIcon() != null;
            if (text != null && !text.equals("")) {
                if (!b) {
                    rectangle3.setBounds(rectangle2);
                }
                else {
                    rectangle3.setBounds(bounds.union(rectangle2));
                }
            }
            else if (b) {
                rectangle3.setBounds(bounds);
            }
            graphics.setColor(MapButton.this.getFocusColor());
            graphics.drawRect(rectangle3.x - 1 + this.getTextShiftOffset(), rectangle3.y - 1 + this.getTextShiftOffset(), rectangle3.width + 1, rectangle3.height + 1);
        }
        
        protected int getTextShiftOffset() {
            if (!MapButton.this.textShiftIsEnabled()) {
                return 0;
            }
            final ButtonModel model = MapButton.this.getModel();
            if (model.isPressed() && model.isArmed()) {
                return 1;
            }
            return 0;
        }
    }
}
