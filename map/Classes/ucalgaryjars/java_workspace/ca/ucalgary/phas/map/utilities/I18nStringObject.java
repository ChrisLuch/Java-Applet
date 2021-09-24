// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

public class I18nStringObject
{
    String value;
    
    public I18nStringObject() {
        this.value = "";
    }
    
    public I18nStringObject(final String value) {
        this.value = value;
    }
    
    public void set(final String value) {
        this.value = value;
    }
    
    public String toString() {
        return this.value;
    }
}
