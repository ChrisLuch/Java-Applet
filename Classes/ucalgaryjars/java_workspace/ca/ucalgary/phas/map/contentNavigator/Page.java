// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.contentNavigator;

import java.util.Enumeration;
import java.net.URL;
import java.util.Vector;

public class Page
{
    public int pageNumber;
    protected Page nextPage;
    protected Page prevPage;
    protected PageButton button;
    protected int numFramesOnPage;
    protected Vector frameNames;
    protected Vector frameURLs;
    protected Vector framePausePolicies;
    protected Vector frameAppletNames;
    protected Vector frameAppletCommands;
    
    public Page() {
        this(-1);
    }
    
    public Page(final int pageNumber) {
        this.nextPage = null;
        this.prevPage = null;
        this.button = null;
        this.pageNumber = pageNumber;
        this.button = new PageButton(pageNumber, this);
        this.numFramesOnPage = 0;
        this.frameNames = new Vector();
        this.frameURLs = new Vector();
        this.framePausePolicies = new Vector();
        this.frameAppletNames = new Vector();
        this.frameAppletCommands = new Vector();
    }
    
    public void addFrame(final String obj, final URL obj2, final Boolean obj3, final String obj4, final String obj5) {
        if (obj != null && obj2 != null && obj4 != null && obj5 != null) {
            ++this.numFramesOnPage;
            this.frameNames.addElement(obj);
            this.framePausePolicies.addElement(obj3);
            this.frameURLs.addElement(obj2);
            if (obj4.equals("null")) {
                this.frameAppletNames.addElement(null);
            }
            else {
                this.frameAppletNames.addElement(obj4);
            }
            if (obj5.equals("null")) {
                this.frameAppletCommands.addElement(null);
            }
            else {
                this.frameAppletCommands.addElement(obj5);
            }
        }
    }
    
    public void printPage() {
        System.out.println(this.toString());
    }
    
    public String toString() {
        return "PagePlus #" + this.pageNumber;
    }
    
    public Enumeration getFrameNames() {
        return this.frameNames.elements();
    }
    
    public Boolean getFramePausePolicy(final String s) {
        try {
            return this.framePausePolicies.elementAt(this.frameNames.indexOf(s));
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("ArrayIndexOutOfBoundsException caught in getFramePausePolicy(" + s + ")");
            return Boolean.TRUE;
        }
    }
    
    public URL getFrameURL(final String o) {
        try {
            return this.frameURLs.elementAt(this.frameNames.indexOf(o));
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public String getAppletName(final String o) {
        try {
            return this.frameAppletNames.elementAt(this.frameNames.indexOf(o));
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public String getAppletCommand(final String o) {
        try {
            return this.frameAppletCommands.elementAt(this.frameNames.indexOf(o));
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public Page getPrevPage() {
        return this.prevPage;
    }
    
    public void setPrevPage(final Page prevPage) {
        this.prevPage = prevPage;
    }
    
    public Page getNextPage() {
        return this.nextPage;
    }
    
    public void setNextPage(final Page nextPage) {
        this.nextPage = nextPage;
    }
    
    public PageButton getButton() {
        return this.button;
    }
}
