// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.FontMetrics;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.ButtonModel;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.AbstractButton;
import java.awt.Graphics;
import javax.swing.plaf.basic.BasicToggleButtonUI;
import java.util.MissingResourceException;
import javax.swing.event.ChangeEvent;
import javax.swing.JToolTip;
import javax.swing.plaf.ButtonUI;
import javax.swing.border.Border;
import javax.swing.Icon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.JToggleButton;

public class MapToggleButton extends JToggleButton implements ChangeListener, MapLocaleListener
{
    public static final Color backgroundColor;
    private boolean textShift;
    private Color focusColor;
    Font mapButtonFont;
    String overridePropertyName;
    String overrideTextKey;
    String overrideToolTipKey;
    
    public MapToggleButton() {
        this.textShift = true;
        this.mapButtonFont = new Font("SansSerif", 0, 10);
        this.construct();
    }
    
    public MapToggleButton(final String text) {
        super(text);
        this.textShift = true;
        this.mapButtonFont = new Font("SansSerif", 0, 10);
        this.construct();
    }
    
    public MapToggleButton(final String text, final Icon icon) {
        super(text, icon);
        this.textShift = true;
        this.mapButtonFont = new Font("SansSerif", 0, 10);
        this.construct();
    }
    
    public MapToggleButton(final String text, final Icon icon, final boolean selected) {
        super(text, icon, selected);
        this.textShift = true;
        this.mapButtonFont = new Font("SansSerif", 0, 10);
        this.construct();
    }
    
    public MapToggleButton(final String text, final boolean selected) {
        super(text, selected);
        this.textShift = true;
        this.mapButtonFont = new Font("SansSerif", 0, 10);
        this.construct();
    }
    
    public MapToggleButton(final Icon icon) {
        super(icon);
        this.textShift = true;
        this.mapButtonFont = new Font("SansSerif", 0, 10);
        this.construct();
    }
    
    public MapToggleButton(final Icon icon, final boolean selected) {
        super(icon, selected);
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
        this.setUI(new MapToggleButtonUI());
        this.setFocusColor(MapConstants.mapButtonFocusColor);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(3);
        this.setFont(this.mapButtonFont);
    }
    
    public JToolTip createToolTip() {
        final JToolTip toolTip = super.createToolTip();
        toolTip.setBackground(MapConstants.mapButtonToolTipBackgroundColor);
        return toolTip;
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        if (this.getBorder() instanceof MapBorder) {
            ((MapBorder)this.getBorder()).setBackground(color);
        }
    }
    
    public void setTextShiftEnabled(final boolean textShift) {
        this.textShift = textShift;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final MapToggleButton mapToggleButton = (MapToggleButton)changeEvent.getSource();
        final ToggleButtonModel toggleButtonModel = (ToggleButtonModel)mapToggleButton.getModel();
        final Border border = mapToggleButton.getBorder();
        if (border instanceof MapBorder) {
            if ((toggleButtonModel.isPressed() && toggleButtonModel.isArmed()) || toggleButtonModel.isSelected()) {
                if (!toggleButtonModel.isEnabled()) {
                    ((MapBorder)border).setLevel(3);
                }
                else {
                    ((MapBorder)border).setLevel(2);
                }
            }
            else if (!toggleButtonModel.isEnabled()) {
                ((MapBorder)border).setLevel(4);
            }
            else {
                ((MapBorder)border).setLevel(1);
            }
        }
        mapToggleButton.repaint();
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
            System.out.println(" ** MissingResourceException caught.... Could not set Text");
            ex2.printStackTrace();
        }
    }
    
    static {
        backgroundColor = MapConstants.mapButtonDefaultColor;
    }
    
    public class MapToggleButtonUI extends BasicToggleButtonUI
    {
        protected void paintIcon(final Graphics graphics, final AbstractButton abstractButton, final Rectangle rectangle) {
            final ButtonModel model = abstractButton.getModel();
            Icon icon = null;
            if (!model.isEnabled()) {
                icon = abstractButton.getDisabledIcon();
            }
            else if (model.isPressed() && model.isArmed()) {
                icon = abstractButton.getPressedIcon();
                if (icon == null) {
                    icon = abstractButton.getSelectedIcon();
                }
                else {
                    this.clearTextShiftOffset();
                }
            }
            else if (model.isSelected()) {
                if (abstractButton.isRolloverEnabled() && model.isRollover()) {
                    icon = abstractButton.getRolloverSelectedIcon();
                    if (icon == null) {
                        icon = abstractButton.getSelectedIcon();
                    }
                }
                else {
                    icon = abstractButton.getSelectedIcon();
                }
            }
            else if (abstractButton.isRolloverEnabled() && model.isRollover()) {
                icon = abstractButton.getRolloverIcon();
            }
            if (icon == null) {
                icon = abstractButton.getIcon();
            }
            icon.paintIcon(abstractButton, graphics, rectangle.x + this.getTextShiftOffset(), rectangle.y + this.getTextShiftOffset());
        }
        
        protected void paintText(final Graphics g, final AbstractButton abstractButton, final Rectangle rectangle, final String text) {
            final ButtonModel model = abstractButton.getModel();
            final FontMetrics fontMetrics = g.getFontMetrics();
            if (model.isEnabled()) {
                g.setColor(abstractButton.getForeground());
                BasicGraphicsUtils.drawString(g, text, model.getMnemonic(), rectangle.x + this.getTextShiftOffset(), rectangle.y + this.getTextShiftOffset() + fontMetrics.getAscent());
            }
            else {
                g.setColor(abstractButton.getBackground().brighter());
                BasicGraphicsUtils.drawString(g, text, model.getMnemonic(), rectangle.x + 1 + this.getTextShiftOffset(), rectangle.y + 1 + this.getTextShiftOffset() + fontMetrics.getAscent());
                g.setColor(abstractButton.getBackground().darker());
                BasicGraphicsUtils.drawString(g, text, model.getMnemonic(), rectangle.x + this.getTextShiftOffset(), rectangle.y + fontMetrics.getAscent() + this.getTextShiftOffset());
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
            graphics.setColor(MapToggleButton.this.getFocusColor());
            graphics.drawRect(rectangle3.x - 1 + this.getTextShiftOffset(), rectangle3.y - 1 + this.getTextShiftOffset(), rectangle3.width + 1, rectangle3.height + 1);
        }
        
        protected int getTextShiftOffset() {
            if (!MapToggleButton.this.textShiftIsEnabled()) {
                return 0;
            }
            final ToggleButtonModel toggleButtonModel = (ToggleButtonModel)MapToggleButton.this.getModel();
            if ((toggleButtonModel.isPressed() && toggleButtonModel.isArmed()) || toggleButtonModel.isSelected()) {
                return 1;
            }
            return 0;
        }
    }
}
