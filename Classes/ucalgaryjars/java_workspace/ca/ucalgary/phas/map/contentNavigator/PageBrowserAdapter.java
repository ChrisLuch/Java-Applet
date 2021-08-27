// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.contentNavigator;

public class PageBrowserAdapter
{
    private PageBrowserPanel pageBrowserPanel;
    private String appletName;
    private PageBrowserListener listener;
    private boolean active;
    
    public PageBrowserAdapter(final PageBrowserListener listener, final String appletName) {
        this.active = false;
        this.listener = listener;
        this.appletName = appletName;
        this.pageBrowserPanel = null;
        if (PageBrowserPanel.verbose) {
            System.out.print(this.getAppletName() + " Applet waiting for PageBrowser...");
        }
        while (this.pageBrowserPanel == null) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {
                if (this.pageBrowserPanel != null) {
                    break;
                }
            }
            this.pageBrowserPanel = PageBrowserPanel.getPanel();
            if (this.pageBrowserPanel != null) {
                if (PageBrowserPanel.verbose) {
                    System.out.println(this.getAppletName() + " Applet just got a pageBrowser");
                }
                this.pageBrowserPanel.addAdapter(this);
            }
        }
    }
    
    public void setActive(final boolean active) {
        this.active = active;
        if (active) {
            this.pageBrowserPanel.checkAdapterQueue(this.getAppletName());
        }
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public void start() {
        this.setActive(true);
    }
    
    public void stop() {
        this.setActive(false);
        this.remove();
    }
    
    public void remove() {
        this.pageBrowserPanel.removeAdapter(this);
    }
    
    void sendCommand(final String str) {
        if (PageBrowserPanel.verbose) {
            System.out.println("PageBrowserAdapter got command:" + str);
        }
        this.listener.doCommand(str);
    }
    
    public void setAppletName(final String appletName) {
        System.out.println("SetAppletName(String) is deprecated. Use the construtor to set the applet name instead.");
        this.appletName = appletName;
    }
    
    public String getAppletName() {
        if (this.appletName == null) {
            return "NoNameYet???";
        }
        return this.appletName;
    }
    
    public void enablePageList(final boolean pagesEnabled) {
        this.pageBrowserPanel.setPagesEnabled(pagesEnabled);
    }
    
    public void enableNextPageButton(final boolean forwardEnabled) {
        this.pageBrowserPanel.setForwardEnabled(forwardEnabled);
    }
    
    public void enablePreviousPageButton(final boolean backEnabled) {
        this.pageBrowserPanel.setBackEnabled(backEnabled);
    }
    
    public void enableResetButton(final boolean resetEnabled) {
        this.pageBrowserPanel.setResetEnabled(resetEnabled);
    }
    
    public boolean isNextPageButtonEnabled() {
        return this.pageBrowserPanel.getForwardEnabled();
    }
    
    public boolean isPreviousPageButtonEnabled() {
        return this.pageBrowserPanel.getBackEnabled();
    }
    
    public boolean isResetButtonEnabled() {
        return this.pageBrowserPanel.getResetEnabled();
    }
    
    public boolean isPageListEnabled() {
        return this.pageBrowserPanel.getPagesEnabled();
    }
    
    public void gotoNextPage() {
        this.pageBrowserPanel.next();
    }
    
    public void gotoFirstPage() {
        this.pageBrowserPanel.reset();
    }
    
    public void gotoPreviousPage() {
        this.pageBrowserPanel.last();
    }
    
    public String toString() {
        return "PageBrowserAdapter Name:" + this.getAppletName() + " isActive:" + this.isActive();
    }
}
