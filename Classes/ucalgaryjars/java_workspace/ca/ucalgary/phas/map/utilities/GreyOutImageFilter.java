// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.image.RGBImageFilter;

public class GreyOutImageFilter extends RGBImageFilter
{
    public GreyOutImageFilter() {
        super.canFilterIndexColorModel = true;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        final int n4 = ((((n3 & 0xFF0000) >> 16) + ((n3 & 0xFF00) >> 8) + (n3 & 0xFF)) / 3 + 256) / 2;
        return (n3 & 0xFF000000) | n4 << 16 | n4 << 8 | n4;
    }
}
