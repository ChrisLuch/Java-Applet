// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors.laf;

public class perspective
{
    static double adj;
    static double per;
    static matrix finalAdjustment;
    static matrix transformation;
    
    static void setAdjustment(final double adj) {
        if (adj != 0.0) {
            perspective.adj = adj;
        }
        perspective.finalAdjustment = new matrix(0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, perspective.per, 1.0 / perspective.adj);
    }
    
    static void setPerspective(final double per) {
        perspective.per = per;
        perspective.finalAdjustment = new matrix(0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, perspective.per, 1.0 / perspective.adj);
    }
    
    static void setTrans() {
        perspective.transformation.rotY(-90.0);
        perspective.transformation.rotZ(-1.0);
        perspective.transformation.rotY(-2.0);
        perspective.transformation.rotZ(-1.0);
    }
    
    static {
        perspective.adj = 200.0;
        perspective.per = 7.0E-5;
        perspective.finalAdjustment = new matrix(0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, perspective.per, 1.0 / perspective.adj);
        perspective.transformation = new matrix();
    }
}
