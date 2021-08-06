// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.contentNavigator;

import javax.swing.JLabel;

public class PageCounter extends JLabel
{
    public int currentPage;
    public int numPages;
    
    public PageCounter(final int n, final int n2) {
        super("Page " + n2 + " of " + n);
        this.currentPage = 0;
        this.numPages = 0;
        this.numPages = n;
        this.currentPage = n2;
    }
    
    public int getCurrentPage() {
        this.setString();
        return this.currentPage;
    }
    
    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
        this.setString();
    }
    
    public int getNumPages() {
        this.setString();
        return this.numPages;
    }
    
    public void setNumPages(final int numPages) {
        this.numPages = numPages;
        this.setString();
    }
    
    public void setString() {
        this.setText("Page " + this.currentPage + " of " + this.numPages);
    }
}
