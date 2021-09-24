// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;

public class ImageBorder extends AbstractBorder implements SwingConstants
{
    protected Image image;
    private static final int WIDTH = 5;
    private Color borderColor;
    
    public ImageBorder() {
        this(null, MapConstants.lineBorderColor);
    }
    
    public ImageBorder(final Image image) {
        this(image, MapConstants.lineBorderColor);
    }
    
    public ImageBorder(final Image image, final Color borderColor) {
        this.borderColor = borderColor;
        this.image = image;
    }
    
    private int getImageHeight() {
        if (this.image != null) {
            return this.image.getHeight(null);
        }
        return 5;
    }
    
    public Insets getBorderInsets(final Component component) {
        return new Insets(this.getImageHeight(), 5, 5, 5);
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        insets.right = 5;
        insets.left = 5;
        insets.top = this.getImageHeight();
        insets.bottom = 5;
        return insets;
    }
    
    public void setImage(final Image image) {
        this.image = image;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(this.borderColor);
        if (this.image != null) {
            graphics.drawImage(this.image, n + 8, n2, null);
            graphics.drawLine(n + 2, n2 + 7, n + 7, n2 + 7);
            graphics.drawLine(n + 10 + this.image.getWidth(null), n2 + 7, n + n3 - 3, n2 + 7);
        }
        else {
            graphics.drawLine(n + 2, n2 + 7, n + n3 - 3, n2 + 7);
        }
        graphics.drawLine(n + n3 - 3, n2 + 7, n + n3 - 3, n2 + n4 - 3);
        graphics.drawLine(n + 2, n2 + n4 - 3, n + n3 - 3, n2 + n4 - 3);
        graphics.drawLine(n + 2, n2 + 7, n + 2, n2 + n4 - 3);
    }
}
