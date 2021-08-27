// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.contentNavigator;

import javax.swing.border.Border;
import javax.swing.JToggleButton;

public class PageButton extends JToggleButton
{
    Page page;
    
    public PageButton(final int n) {
        this(n, null);
    }
    
    public PageButton(final int i, final Page page) {
        this.setText("" + i);
        this.page = page;
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.setBorder(null);
        this.setRolloverEnabled(true);
    }
    
    public Page getPage() {
        return this.page;
    }
    
    public void setPage(final Page page) {
        this.page = page;
    }
}
