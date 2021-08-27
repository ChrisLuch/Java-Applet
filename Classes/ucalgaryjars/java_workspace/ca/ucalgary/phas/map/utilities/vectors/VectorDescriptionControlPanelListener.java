// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities.vectors;

public interface VectorDescriptionControlPanelListener
{
    void originChanged(final VectorDescriptionControlPanel p0);
    
    void vectorChanged(final VectorDescriptionControlPanel p0, final double p1, final double p2);
    
    void modeChanged(final VectorDescriptionControlPanel p0);
    
    void textFieldActionPerformed(final VectorDescriptionControlPanel p0);
}
