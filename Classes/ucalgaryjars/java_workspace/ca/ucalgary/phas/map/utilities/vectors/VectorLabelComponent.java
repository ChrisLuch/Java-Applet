// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities.vectors;

import java.awt.Dimension;
import java.awt.Graphics;

public interface VectorLabelComponent
{
    Dimension getSize(final Graphics p0);
    
    void draw(final Graphics p0, final int p1, final int p2);
}
