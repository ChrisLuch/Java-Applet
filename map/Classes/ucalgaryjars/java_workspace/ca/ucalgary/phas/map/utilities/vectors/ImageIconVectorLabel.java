// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities.vectors;

import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageIconVectorLabel extends ImageIcon implements VectorLabelComponent
{
    public ImageIconVectorLabel(final Image image) {
        super(image);
    }
    
    public ImageIconVectorLabel(final String filename) {
        super(filename);
    }
    
    public Dimension getSize(final Graphics graphics) {
        return new Dimension(this.getIconWidth(), this.getIconHeight());
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        graphics.drawImage(this.getImage(), n, n2 - this.getIconHeight(), null);
    }
}
