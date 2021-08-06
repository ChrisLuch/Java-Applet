// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.contentNavigator;

import javax.swing.JComponent;
import javax.swing.AbstractButton;
import java.awt.Image;
import ca.ucalgary.phas.map.utilities.ImageUtils;
import java.util.NoSuchElementException;
import java.util.Enumeration;
import java.applet.AppletContext;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Icon;
import java.awt.Insets;
import ca.ucalgary.phas.map.utilities.MapBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import ca.ucalgary.phas.map.utilities.MapConstants;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import ca.ucalgary.phas.map.utilities.MapButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;
import javax.swing.JApplet;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class PageBrowserPanel extends JPanel implements ActionListener
{
    private static PageBrowserPanel thePanel;
    protected boolean lookForPageBrowserEnabledApplet;
    protected int numPages;
    protected int numFrames;
    protected boolean urlBaseIsRelativeToCodeBase;
    protected boolean urlBaseIsRelativeToDocBase;
    private static Color bgColor;
    private static Color panelColor;
    protected String baseURL;
    protected boolean pageListVisible;
    protected boolean pageListEnabled;
    protected boolean pageCounterVisible;
    protected boolean prevButtonVisible;
    protected boolean nextButtonVisible;
    protected boolean resetButtonVisible;
    public static boolean verbose;
    protected JApplet parentApplet;
    protected String parameterPrefix;
    private Vector adapters;
    private Vector adapterQueue;
    private Vector commandQueue;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    public static String imgDir;
    protected MapButton reset;
    protected MapButton forward;
    protected MapButton back;
    protected Page firstPage;
    protected Page current;
    protected String[] availableFrames;
    protected PageCounter pageCounter;
    protected JPanel pageSubPanel;
    protected static ImageIcon imagePressed;
    protected static ImageIcon imageNormal;
    protected static ImageIcon imageRoll;
    protected static ImageIcon imageDisabled;
    protected static ImageIcon forwardButtonIcon;
    protected static ImageIcon forwardButtonIconHighLight;
    protected static ImageIcon backButtonIcon;
    protected static ImageIcon backButtonIconHighLight;
    protected static ImageIcon resetButtonIcon;
    protected static ImageIcon resetButtonIconHighLight;
    
    public PageBrowserPanel(final JApplet applet) {
        this(applet, "");
    }
    
    public PageBrowserPanel(final JApplet parentApplet, final String parameterPrefix) {
        this.adapters = new Vector();
        this.adapterQueue = new Vector();
        this.commandQueue = new Vector();
        this.parentApplet = parentApplet;
        this.parameterPrefix = parameterPrefix;
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.setLayout(this.gbl);
        final double[] columnWeights = { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        final int[] columnWidths = { 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3 };
        final double[] rowWeights = { 0.0, 1.0, 0.0 };
        final int[] rowHeights = { 3, 10, 3 };
        this.gbl.columnWeights = columnWeights;
        this.gbl.columnWidths = columnWidths;
        this.gbl.rowWeights = rowWeights;
        this.gbl.rowHeights = rowHeights;
        this.loadImages();
        this.loadParameters();
        this.availableFrames = new String[this.numFrames];
        this.makePages();
        this.makeButtons();
        this.setBorder(new LineBorder(MapConstants.lineBorderColor, 1));
        if (!this.pageListEnabled) {
            this.setPagesEnabled(false);
        }
        this.current = new Page();
        if (this.getBooleanParameter("LOAD_FIRST_PAGE_ON_INIT", false)) {
            this.gotoPage(this.firstPage);
        }
        else {
            this.current = this.firstPage;
            this.current.getButton().setSelected(true);
            if (this.current.nextPage == null) {
                ((AbstractButton)this.forward).setEnabled(false);
            }
            ((AbstractButton)this.back).setEnabled(false);
        }
    }
    
    private void sendAppletCommand(final String str, final Page obj) {
        final String appletName = obj.getAppletName(str);
        final String appletCommand = obj.getAppletCommand(str);
        if (PageBrowserPanel.verbose) {
            System.out.println("sendAppletCommand; Frame:" + str + "  Page:" + obj + "  AppletName:" + appletName + "  AppletCommand:" + appletCommand);
        }
        if (appletName != null) {
            if (appletCommand != null) {
                final PageBrowserAdapter adapter = this.getAdapter(appletName);
                if (adapter != null) {
                    if (PageBrowserPanel.verbose) {
                        System.out.println("sending command:" + appletCommand + " to adapter:" + adapter);
                    }
                    while (!adapter.isActive()) {
                        try {
                            Thread.sleep(500L);
                        }
                        catch (InterruptedException ex) {}
                    }
                    adapter.sendCommand(appletCommand);
                }
                else {
                    if (PageBrowserPanel.verbose) {
                        System.out.println("No Adapter found for " + appletName + ". Putting it in queue.");
                    }
                    this.adapterQueue.addElement(appletName);
                    this.commandQueue.addElement(appletCommand);
                }
            }
            else if (PageBrowserPanel.verbose) {
                System.out.println("No Applet Command for applet");
            }
        }
        else if (PageBrowserPanel.verbose) {
            System.out.println("No Applet on this page & frame");
        }
    }
    
    public void checkAdapterQueue(final String s) {
        if (this.adapterQueue.contains(s)) {
            final int index = this.adapterQueue.indexOf(s);
            final PageBrowserAdapter adapter = this.getAdapter(s);
            if (adapter != null) {
                final String s2 = this.commandQueue.elementAt(index);
                while (!adapter.isActive()) {
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException ex) {}
                }
                adapter.sendCommand(s2);
                this.adapterQueue.removeElementAt(index);
                this.commandQueue.removeElementAt(index);
            }
        }
    }
    
    private void clearAdapterQueue() {
        this.adapterQueue.removeAllElements();
        this.commandQueue.removeAllElements();
    }
    
    protected void makeButtons() {
        this.gbc.gridx = 1;
        this.gbc.gridy = 1;
        this.gbc.fill = 0;
        ((AbstractButton)(this.reset = new MapButton())).setFocusPainted(false);
        ((MapBorder)((JComponent)this.reset).getBorder()).setBorderInsets(new Insets(0, 0, 0, 0));
        ((AbstractButton)this.reset).setRolloverEnabled(true);
        ((AbstractButton)this.reset).setIcon(PageBrowserPanel.resetButtonIcon);
        ((AbstractButton)this.reset).setRolloverIcon(PageBrowserPanel.resetButtonIconHighLight);
        ((AbstractButton)this.reset).setPressedIcon(PageBrowserPanel.resetButtonIconHighLight);
        if (this.resetButtonVisible) {
            ((AbstractButton)this.reset).addActionListener(this);
            this.add((Component)this.reset, this.gbc);
            this.gbl.columnWidths[2] = 5;
        }
        this.gbc.gridx = 3;
        this.gbc.gridy = 1;
        this.gbc.fill = 0;
        ((AbstractButton)(this.back = new MapButton())).setFocusPainted(false);
        ((MapBorder)((JComponent)this.back).getBorder()).setBorderInsets(new Insets(0, 0, 0, 0));
        ((AbstractButton)this.back).setRolloverEnabled(true);
        ((AbstractButton)this.back).setIcon(PageBrowserPanel.backButtonIcon);
        ((AbstractButton)this.back).setRolloverIcon(PageBrowserPanel.backButtonIconHighLight);
        ((AbstractButton)this.back).setPressedIcon(PageBrowserPanel.backButtonIconHighLight);
        if (this.prevButtonVisible) {
            ((AbstractButton)this.back).addActionListener(this);
            this.add((Component)this.back, this.gbc);
            this.gbl.columnWidths[4] = 5;
        }
        this.gbc.gridx = 5;
        this.gbc.gridy = 1;
        this.gbc.fill = 1;
        final JPanel comp = new JPanel();
        comp.setLayout(new FlowLayout(1, 5, 3));
        comp.setBackground(PageBrowserPanel.panelColor);
        comp.setBorder(new LineBorder(MapConstants.lineBorderColor, 1));
        for (Page page = this.firstPage; page != null; page = page.nextPage) {
            final PageButton button = page.getButton();
            button.addActionListener(this);
            button.setBackground(PageBrowserPanel.panelColor);
            button.setContentAreaFilled(false);
            button.setIcon(PageBrowserPanel.imageNormal);
            button.setPressedIcon(PageBrowserPanel.imagePressed);
            button.setDisabledSelectedIcon(PageBrowserPanel.imagePressed);
            button.setSelectedIcon(PageBrowserPanel.imagePressed);
            button.setRolloverIcon(PageBrowserPanel.imageRoll);
            button.setDisabledIcon(PageBrowserPanel.imageDisabled);
            comp.add(button);
        }
        if (this.pageListVisible) {
            this.add(comp, this.gbc);
        }
        this.gbc.gridx = 7;
        this.gbc.gridy = 1;
        this.gbc.fill = 0;
        this.pageCounter = new PageCounter(this.numPages, 1);
        if (this.pageCounterVisible) {
            this.pageCounter.setForeground(Color.black);
            this.add(this.pageCounter, this.gbc);
            this.gbl.columnWidths[6] = 5;
        }
        this.gbc.gridx = 9;
        this.gbc.gridy = 1;
        this.gbc.fill = 0;
        ((AbstractButton)(this.forward = new MapButton())).setFocusPainted(false);
        ((AbstractButton)this.forward).setRolloverEnabled(true);
        ((MapBorder)((JComponent)this.forward).getBorder()).setBorderInsets(new Insets(0, 0, 0, 0));
        ((AbstractButton)this.forward).setIcon(PageBrowserPanel.forwardButtonIcon);
        ((AbstractButton)this.forward).setRolloverIcon(PageBrowserPanel.forwardButtonIconHighLight);
        ((AbstractButton)this.forward).setPressedIcon(PageBrowserPanel.forwardButtonIconHighLight);
        if (this.nextButtonVisible) {
            ((AbstractButton)this.forward).addActionListener(this);
            this.add((Component)this.forward, this.gbc);
            this.gbl.columnWidths[8] = 5;
        }
    }
    
    protected String getParameter(final String str) {
        return this.parentApplet.getParameter(this.parameterPrefix + str);
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        System.out.println("getPreferredSize: " + preferredSize);
        try {
            System.out.println("back:" + ((JComponent)this.back).getPreferredSize());
        }
        catch (NullPointerException ex) {
            System.out.println("NullPointerException");
        }
        try {
            System.out.println("   subPanel:" + this.pageSubPanel.getPreferredSize());
        }
        catch (NullPointerException ex2) {
            System.out.println("NullPointerException");
        }
        try {
            System.out.println("  forward:" + ((JComponent)this.forward).getPreferredSize());
        }
        catch (NullPointerException ex3) {
            System.out.println("NullPointerException");
        }
        return preferredSize;
    }
    
    protected void makePages() {
        final URL[][] array = new URL[this.numFrames][this.numPages];
        final String[][] array2 = new String[this.numFrames][this.numPages];
        final String[][] array3 = new String[this.numFrames][this.numPages];
        final StringTokenizer stringTokenizer = new StringTokenizer(this.getStringParameter("FRAME_NAMES", ""), ",");
        String s;
        if (this.numFrames == 1) {
            s = "false";
        }
        else {
            s = this.getParameter("FRAME_PAUSE_POLICY");
            if (s == null) {
                System.out.println("No \"" + this.parameterPrefix + "FRAME_PAUSE_POLICY\" parameter entered. All frames will pause.");
                s = "true";
                for (int i = 0; i < this.numFrames - 1; ++i) {
                    s += ",true";
                }
            }
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(s, ",");
        final Boolean[] array4 = new Boolean[this.numFrames];
        for (int j = 0; j < this.numFrames; ++j) {
            try {
                this.availableFrames[j] = stringTokenizer.nextToken();
                array4[j] = new Boolean(stringTokenizer2.nextToken().equals("true"));
                final StringTokenizer stringTokenizer3 = new StringTokenizer(this.getStringParameter(this.availableFrames[j] + "_FRAME_URLS", ""), ",");
                String parameter = this.getParameter(this.availableFrames[j] + "_FRAME_APPLET_NAMES");
                boolean b = true;
                if (parameter == null) {
                    if (PageBrowserPanel.verbose) {
                        System.out.println("Error --- no \"" + this.parameterPrefix + "" + this.availableFrames[j] + "_FRAME_APPLET_NAMES\" parameter entered - using default -  \"" + this.parameterPrefix + "" + this.availableFrames[j] + "_FRAME_APPLET_NAMES\" = \"\"");
                    }
                    parameter = "";
                    b = false;
                }
                final StringTokenizer stringTokenizer4 = new StringTokenizer(parameter, ",");
                String parameter2 = this.getParameter(this.availableFrames[j] + "_FRAME_APPLET_COMMANDS");
                if (parameter2 == null) {
                    if (PageBrowserPanel.verbose) {
                        System.out.println("Error --- no \"" + this.parameterPrefix + "" + this.availableFrames[j] + "_FRAME_APPLET_COMMANDS\" parameter entered - using default - \"" + this.parameterPrefix + "" + this.availableFrames[j] + "_FRAME_APPLET_COMMANDS\" = \"\"");
                    }
                    parameter2 = "";
                }
                final StringTokenizer stringTokenizer5 = new StringTokenizer(parameter2, ",");
                for (int k = 0; k < this.numPages; ++k) {
                    final String nextToken = stringTokenizer3.nextToken();
                    if (nextToken.equals("null")) {
                        array[j][k] = null;
                    }
                    else if (this.urlBaseIsRelativeToCodeBase) {
                        array[j][k] = new URL(this.parentApplet.getCodeBase(), this.baseURL + nextToken);
                    }
                    else if (this.urlBaseIsRelativeToDocBase) {
                        array[j][k] = new URL(this.parentApplet.getDocumentBase(), this.baseURL + nextToken);
                    }
                    else {
                        array[j][k] = new URL(this.baseURL + nextToken);
                    }
                    if (b) {
                        array2[j][k] = stringTokenizer4.nextToken();
                        array3[j][k] = stringTokenizer5.nextToken();
                    }
                    else {
                        array2[j][k] = "null";
                        array3[j][k] = "null";
                    }
                }
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
        Page prevPage = null;
        Page page = null;
        for (int l = 0; l < this.numPages; ++l) {
            page = new Page(l + 1);
            if (this.firstPage == null) {
                this.firstPage = page;
            }
            for (int n = 0; n < this.numFrames; ++n) {
                page.addFrame(this.availableFrames[n], array[n][l], array4[n], array2[n][l], array3[n][l]);
            }
            if (prevPage != null) {
                prevPage.setNextPage(page);
            }
            page.setPrevPage(prevPage);
            prevPage = page;
        }
        page.setNextPage(null);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.reset) {
            this.reset();
        }
        else if (actionEvent.getSource() == this.forward) {
            this.next();
        }
        else if (actionEvent.getSource() == this.back) {
            this.last();
        }
        else {
            this.gotoPage(((PageButton)actionEvent.getSource()).getPage());
        }
    }
    
    public void next() {
        this.gotoPage(this.current.getNextPage());
    }
    
    public void last() {
        this.gotoPage(this.current.getPrevPage());
    }
    
    public void reset() {
        this.gotoPage(this.firstPage);
    }
    
    public void gotoPage(final Page current) {
        if (current != this.current && current != null) {
            final AppletContext appletContext = this.parentApplet.getAppletContext();
            final Enumeration frameNames = current.getFrameNames();
            while (frameNames.hasMoreElements()) {
                final String s = frameNames.nextElement();
                final URL frameURL = current.getFrameURL(s);
                final boolean booleanValue = current.getFramePausePolicy(s);
                final URL frameURL2 = this.current.getFrameURL(s);
                if (PageBrowserPanel.verbose) {
                    System.out.println("\nDealing with frame: " + s);
                }
                if (frameURL != null) {
                    if (frameURL2 != null) {
                        if (frameURL2.equals(frameURL)) {
                            if (PageBrowserPanel.verbose) {
                                System.out.println("Document is already loaded" + frameURL + "," + s + ")");
                            }
                        }
                        else {
                            if (PageBrowserPanel.verbose) {
                                System.out.println("Showing Document(" + frameURL + "," + s + ")");
                            }
                            appletContext.showDocument(frameURL, s);
                            if (booleanValue && frameNames.hasMoreElements()) {
                                try {
                                    Thread.sleep(800L);
                                }
                                catch (InterruptedException ex) {}
                            }
                        }
                    }
                    else {
                        if (PageBrowserPanel.verbose) {
                            System.out.println("Showing Document(" + frameURL + "," + s + ")");
                        }
                        appletContext.showDocument(frameURL, s);
                        if (booleanValue && frameNames.hasMoreElements()) {
                            try {
                                Thread.sleep(800L);
                            }
                            catch (InterruptedException ex2) {}
                        }
                    }
                    this.sendAppletCommand(s, current);
                }
                else {
                    if (!PageBrowserPanel.verbose) {
                        continue;
                    }
                    System.out.println("Frame URL is null");
                }
            }
            this.calculateButtonStates(this.current, current);
            this.current = current;
            this.pageCounter.setCurrentPage(this.current.pageNumber);
        }
    }
    
    private void calculateButtonStates(final Page page, final Page page2) {
        page.getButton().setSelected(false);
        page2.getButton().setSelected(true);
        if (page.nextPage == null) {
            ((AbstractButton)this.forward).setEnabled(true);
        }
        if (page2.nextPage == null) {
            ((AbstractButton)this.forward).setEnabled(false);
        }
        if (page.prevPage == null) {
            ((AbstractButton)this.back).setEnabled(true);
        }
        if (page2.prevPage == null) {
            ((AbstractButton)this.back).setEnabled(false);
        }
        this.repaint();
    }
    
    public void addAdapter(final PageBrowserAdapter obj) {
        if (PageBrowserPanel.verbose) {
            System.out.println("PageBrowserPanel.addAdapter(" + obj.toString() + ")");
        }
        this.adapters.addElement(obj);
        this.checkAdapterQueue(obj.getAppletName());
    }
    
    public void removeAdapter(final PageBrowserAdapter obj) {
        if (PageBrowserPanel.verbose) {
            System.out.println("PageBrowserPanel.removeAdapter(" + obj.toString() + ")");
        }
        this.adapters.removeElement(obj);
    }
    
    public PageBrowserAdapter getAdapter(final String anObject) {
        final Enumeration<PageBrowserAdapter> elements = this.adapters.elements();
        while (elements.hasMoreElements()) {
            final PageBrowserAdapter pageBrowserAdapter = elements.nextElement();
            if (pageBrowserAdapter.getAppletName().equals(anObject) && pageBrowserAdapter.isActive()) {
                return pageBrowserAdapter;
            }
        }
        return null;
    }
    
    public void setAllEnabled(final boolean b) {
        this.setResetEnabled(b);
        this.setForwardEnabled(b);
        this.setBackEnabled(b);
        this.setPagesEnabled(b);
    }
    
    public void setPagesEnabled(final boolean enabled) {
        for (Page page = this.firstPage; page != null; page = page.nextPage) {
            if (page.getButton() != null) {
                page.getButton().setEnabled(enabled);
            }
        }
    }
    
    public Page getFirstPage() {
        return this.firstPage;
    }
    
    public void setPageEnabled(final boolean enabled, final int n) throws NoSuchElementException {
        Page page = this.firstPage;
        boolean b = false;
        for (int i = 0; i < n; ++i) {
            if (page.nextPage != null) {
                page = page.nextPage;
            }
            else {
                b = true;
            }
        }
        if (b) {
            throw new NoSuchElementException();
        }
        if (page.getButton() != null) {
            page.getButton().setEnabled(enabled);
        }
    }
    
    public boolean getPagesEnabled() {
        for (Page page = this.firstPage; page != null; page = page.nextPage) {
            try {
                if (!page.getButton().isEnabled()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {}
        }
        return true;
    }
    
    public void setResetEnabled(final boolean enabled) {
        if (this.reset != null) {
            ((AbstractButton)this.reset).setEnabled(enabled);
        }
    }
    
    public void setBackEnabled(final boolean enabled) {
        if (this.back != null) {
            ((AbstractButton)this.back).setEnabled(enabled);
        }
    }
    
    public void setForwardEnabled(final boolean b) {
        if (this.forward != null) {
            ((AbstractButton)this.forward).setEnabled(b);
        }
        else {
            System.out.println("Forward Button is Null - attempted to call setEnabled(" + b + ")");
        }
    }
    
    public boolean getResetEnabled() {
        return this.reset != null && ((Component)this.reset).isEnabled();
    }
    
    public boolean getBackEnabled() {
        return this.back != null && ((Component)this.back).isEnabled();
    }
    
    public boolean getForwardEnabled() {
        return this.forward != null && ((Component)this.forward).isEnabled();
    }
    
    private void loadImages() {
        final Image imageFromJar = ImageUtils.getImageFromJar(PageBrowserPanel.imgDir + "page_normal.gif", (Object)this);
        PageBrowserPanel.imageNormal = new ImageIcon(imageFromJar);
        PageBrowserPanel.imagePressed = new ImageIcon(ImageUtils.getImageFromJar(PageBrowserPanel.imgDir + "page_pressed.gif", (Object)this));
        PageBrowserPanel.imageRoll = new ImageIcon(ImageUtils.getImageFromJar(PageBrowserPanel.imgDir + "page_roll.gif", (Object)this));
        PageBrowserPanel.imageDisabled = new ImageIcon(ImageUtils.greyOutImage(imageFromJar));
        PageBrowserPanel.forwardButtonIcon = new ImageIcon(ImageUtils.getImageFromJar(PageBrowserPanel.imgDir + "forward_icon.gif", (Object)this));
        PageBrowserPanel.forwardButtonIconHighLight = new ImageIcon(ImageUtils.getImageFromJar(PageBrowserPanel.imgDir + "forward_icon_highlight.gif", (Object)this));
        PageBrowserPanel.backButtonIcon = new ImageIcon(ImageUtils.getImageFromJar(PageBrowserPanel.imgDir + "back_icon.gif", (Object)this));
        PageBrowserPanel.backButtonIconHighLight = new ImageIcon(ImageUtils.getImageFromJar(PageBrowserPanel.imgDir + "back_icon_highlight.gif", (Object)this));
        PageBrowserPanel.resetButtonIcon = new ImageIcon(ImageUtils.getImageFromJar(PageBrowserPanel.imgDir + "reset_icon.gif", (Object)this));
        PageBrowserPanel.resetButtonIconHighLight = new ImageIcon(ImageUtils.getImageFromJar(PageBrowserPanel.imgDir + "reset_icon_highlight.gif", (Object)this));
    }
    
    protected void loadParameters() {
        PageBrowserPanel.bgColor = MapConstants.pageBrowserBackgroundColor;
        PageBrowserPanel.panelColor = MapConstants.pageBrowserPanelColor;
        this.numPages = this.getIntParameter("PAGE_COUNT", "1");
        this.numFrames = this.getIntParameter("FRAME_COUNT", "1");
        this.urlBaseIsRelativeToCodeBase = this.getBooleanParameter("URL_BASE_RELATIVE_TO_CODE", false);
        this.urlBaseIsRelativeToDocBase = this.getBooleanParameter("URL_BASE_RELATIVE_TO_DOC", true);
        this.baseURL = this.getStringParameter("URL_BASE", "");
        this.pageListVisible = this.getBooleanParameter("PAGE_LIST_VISIBLE", true);
        this.pageListEnabled = this.getBooleanParameter("PAGE_LIST_ENABLED", true);
        this.pageCounterVisible = this.getBooleanParameter("PAGE_COUNTER_VISIBLE", true);
        this.prevButtonVisible = this.getBooleanParameter("PREV_BUTTON_VISIBLE", true);
        this.nextButtonVisible = this.getBooleanParameter("NEXT_BUTTON_VISIBLE", true);
        this.resetButtonVisible = this.getBooleanParameter("RESET_BUTTON_VISIBLE", false);
        PageBrowserPanel.verbose = this.getBooleanParameter("VERBOSE", false);
    }
    
    private String getStringParameter(final String s, final String str) {
        String parameter = this.getParameter(s);
        if (parameter == null) {
            System.out.println("Error --- no \"" + this.parameterPrefix + s + "\" parameter entered - using default - \"" + this.parameterPrefix + s + "\" = \"" + str + "\"");
            parameter = str;
        }
        return parameter;
    }
    
    private int getIntParameter(final String str, final String s) {
        final String stringParameter = this.getStringParameter(str, s);
        try {
            return Integer.valueOf(stringParameter);
        }
        catch (NumberFormatException ex) {
            System.out.println("NumberFormatException in \"" + str + "\" using value=\"0\"");
            ex.printStackTrace();
            return 0;
        }
    }
    
    private boolean getBooleanParameter(final String s, final boolean b) {
        final String parameter = this.getParameter(s);
        try {
            if (parameter.equals("false")) {
                return false;
            }
            if (parameter.equals("true")) {
                return true;
            }
            System.out.println("Error --- no \"" + this.parameterPrefix + s + "\" parameter was not true or false - using default - \"" + this.parameterPrefix + s + "\" = \"" + b + "\"");
            return b;
        }
        catch (NullPointerException ex) {
            System.out.println("Error --- no \"" + this.parameterPrefix + s + "\" parameter entered - using default - \"" + this.parameterPrefix + s + "\" = \"" + b + "\"");
            return b;
        }
    }
    
    static PageBrowserPanel initPanel(final JApplet applet) {
        return PageBrowserPanel.thePanel = new PageBrowserPanel(applet);
    }
    
    static PageBrowserPanel initPanel(final JApplet applet, final String s) {
        return PageBrowserPanel.thePanel = new PageBrowserPanel(applet, s);
    }
    
    static PageBrowserPanel getPanel() {
        return PageBrowserPanel.thePanel;
    }
    
    static void deletePanel() {
        PageBrowserPanel.thePanel = null;
    }
    
    static {
        PageBrowserPanel.imgDir = "images/";
    }
}
