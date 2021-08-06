// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import javax.swing.BoxLayout;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Canvas;
import javax.swing.JFrame;
import java.util.ResourceBundle;
import java.util.Locale;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import javax.swing.AbstractButton;
import java.awt.LayoutManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.util.Vector;
import java.awt.GridBagConstraints;
import javax.swing.Icon;
import java.awt.MediaTracker;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.applet.Applet;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;

public class MapMenuBar extends JMenuBar implements ActionListener, MapLocaleListener
{
    Applet parent;
    String helpFileURL;
    String showmeURL;
    String function;
    String parameters;
    String version;
    String build;
    String aboutLabel;
    JMenu helpMenu;
    JMenuItem helpMenuItem;
    JMenuItem aboutMenuItem;
    JLabel logo;
    JLabel title;
    String titleString_en;
    String titleString_fr;
    String titleString_nl;
    Image image;
    Image imageText;
    MediaTracker tracker;
    AboutFrame aboutFrame;
    boolean i18nized;
    JMenu i18nMenu;
    JMenuItem en_caMenuItem;
    JMenuItem fr_caMenuItem;
    JMenuItem nl_nlMenuItem;
    private static Icon checkmark;
    GridBagConstraints gbc;
    
    public void setVersion(final String version) {
        this.version = version;
        this.setAboutLabelText();
    }
    
    public void setBuild(final String build) {
        this.build = build;
        if (build.startsWith("$Revision: ")) {
            this.build = build.substring(11, build.length() - 2);
        }
        this.setAboutLabelText();
    }
    
    private void setTitleText(final String text) {
        this.title.setText(text);
        this.setAboutLabelText();
    }
    
    private void setAboutLabelText() {
        this.aboutLabel = this.title.getText() + (this.version.equals("") ? "" : ("  Version: " + this.version)) + (this.build.equals("") ? "" : ("  Build: " + this.build));
    }
    
    public MapMenuBar(final Applet applet) {
        this(applet, null, null, null, "openWin2", "resizable=yes,scrollbars=yes,width=640,height=480");
    }
    
    public MapMenuBar(final Applet applet, final boolean b) {
        this(applet, null, null, null, "openWin2", "resizable=yes,scrollbars=yes,width=640,height=480", b);
    }
    
    public MapMenuBar(final Applet applet, final String s) {
        this(applet, s, null, null, "openWin2", "resizable=yes,scrollbars=yes,width=640,height=480");
    }
    
    public MapMenuBar(final Applet applet, final String s, final String s2) {
        this(applet, s, s2, null, "openWin2", "resizable=yes,scrollbars=yes,width=640,height=480");
    }
    
    public MapMenuBar(final Applet applet, final String s, final String s2, final String s3) {
        this(applet, s, s2, s3, "openWin2", "resizable=yes,scrollbars=yes,width=640,height=480");
    }
    
    public MapMenuBar(final Applet applet, final String s, final String s2, final String s3, final String s4, final String s5) {
        this(applet, s, s2, s3, s4, s5, false);
    }
    
    public MapMenuBar(final Applet parent, final String titleString_nl, String stringParameter, final String s, final String function, final String parameters, final boolean i18nized) {
        this.parent = null;
        this.helpFileURL = null;
        this.showmeURL = null;
        this.function = null;
        this.parameters = null;
        this.version = "";
        this.build = "";
        this.parent = parent;
        this.function = function;
        this.parameters = parameters;
        this.i18nized = i18nized;
        if (titleString_nl == null) {
            this.titleString_en = ParameterUtils.getStringParameter(parent, "APPLET_NAME", parent.getName());
            this.titleString_fr = ParameterUtils.getStringParameterQuietly(parent, "APPLET_NAME_FR", this.titleString_en);
            this.titleString_nl = ParameterUtils.getStringParameterQuietly(parent, "APPLET_NAME_NL", this.titleString_en);
        }
        else {
            this.titleString_en = titleString_nl;
            this.titleString_fr = titleString_nl;
            this.titleString_nl = titleString_nl;
        }
        final Vector<String> vector = new Vector<String>();
        vector.add("system");
        vector.add("en_CA");
        vector.add("fr_CA");
        final String stringParameterQuietly = ParameterUtils.getStringParameterQuietly(parent, "DEFAULT_STARTUP_LOCALE", vector, "system");
        if (stringParameterQuietly.equalsIgnoreCase("en_CA")) {
            MapLocaleManager.setLocale(MapLocaleManager.LOCALE_EN_CA);
        }
        else if (stringParameterQuietly.equalsIgnoreCase("fr_CA")) {
            MapLocaleManager.setLocale(MapLocaleManager.LOCALE_FR_CA);
        }
        if (stringParameter == null) {
            stringParameter = ParameterUtils.getStringParameter(parent, "HELP_URL", "info.html");
        }
        this.helpFileURL = stringParameter;
        MapMenuBar.checkmark = new ImageIcon(ImageUtils.getImageFromJar("images/checkmark.gif", this));
        MapLocaleManager.addSystemLocaleListener(this);
        this.aboutFrame = new AboutFrame();
        this.tracker = new MediaTracker(this);
        this.image = ImageUtils.getImageFromJar("images/aboutFrameImage.jpg", this);
        this.tracker.addImage(this.image, 0);
        this.imageText = ImageUtils.getImageFromJar("images/aboutFrameTextImage.gif", this);
        this.tracker.addImage(this.imageText, 1);
        final GridBagLayout layout = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.gbc.fill = 1;
        final double[] columnWeights = { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0 };
        final int[] columnWidths = { 0, 0, 0, 0, 0, 5 };
        final double[] rowWeights = { 1.0 };
        final int[] rowHeights = { 0 };
        layout.rowWeights = rowWeights;
        layout.rowHeights = rowHeights;
        layout.columnWeights = columnWeights;
        layout.columnWidths = columnWidths;
        this.setBorder(new LineBorder(MapConstants.lineBorderColor, 1));
        this.setLayout(layout);
        this.helpMenuItem = new JMenuItem();
        this.aboutMenuItem = new JMenuItem();
        final ImageIcon image = new ImageIcon(ImageUtils.getImageFromJar("images/maplogo.gif", this));
        image.setImage(image.getImage().getScaledInstance(16, 16, 4));
        this.logo = new JLabel(image);
        this.helpMenu = new JMenu();
        this.i18nMenu = new JMenu();
        this.title = new JLabel();
        this.setAboutLabelText();
        MapButtonPropertySetter.setHelpIcon(this.helpMenuItem);
        MapButtonPropertySetter.setAboutIcon(this.aboutMenuItem);
        this.helpMenuItem.setMnemonic('H');
        this.helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(72, 2));
        this.aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(65, 2));
        this.aboutMenuItem.setMnemonic('A');
        this.helpMenu.add(this.helpMenuItem);
        this.helpMenu.addSeparator();
        this.helpMenu.add(this.aboutMenuItem);
        this.en_caMenuItem = new JMenuItem();
        this.fr_caMenuItem = new JMenuItem();
        this.nl_nlMenuItem = new JMenuItem();
        this.en_caMenuItem.setHorizontalTextPosition(2);
        this.fr_caMenuItem.setHorizontalTextPosition(2);
        this.nl_nlMenuItem.setHorizontalTextPosition(2);
        this.i18nMenu.add(this.en_caMenuItem);
        this.i18nMenu.add(this.fr_caMenuItem);
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.add(this.logo, this.gbc);
        this.gbc.gridx = 1;
        this.add(this.helpMenu, this.gbc);
        if (i18nized) {
            this.gbc.gridx = 2;
            this.add(this.i18nMenu, this.gbc);
        }
        this.gbc.gridx = 4;
        this.add(this.title, this.gbc);
        this.helpMenuItem.addActionListener(this);
        this.aboutMenuItem.addActionListener(this);
        this.nl_nlMenuItem.addActionListener(this);
        this.en_caMenuItem.addActionListener(this);
        this.fr_caMenuItem.addActionListener(this);
        if (i18nized) {
            this.localeChanged();
        }
        else {
            this.helpMenu.setText("Help");
            this.helpMenuItem.setText("Applet Help");
            this.aboutMenuItem.setText("About");
            this.setTitleText(this.titleString_en);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source.equals(this.helpMenuItem)) {
            this.help();
        }
        else if (source.equals(this.aboutMenuItem)) {
            this.aboutFrame.show();
        }
        else if (source.equals(this.en_caMenuItem)) {
            MapLocaleManager.setLocale(MapLocaleManager.LOCALE_EN_CA);
        }
        else if (source.equals(this.fr_caMenuItem)) {
            MapLocaleManager.setLocale(MapLocaleManager.LOCALE_FR_CA);
        }
        else if (source.equals(this.nl_nlMenuItem)) {
            MapLocaleManager.setLocale(MapLocaleManager.LOCALE_NL_NL);
        }
    }
    
    private void showme() {
        final String[] array = { this.showmeURL, this.parameters };
        try {
            JSObject.getWindow(this.parent).call(this.function, (Object[])array);
        }
        catch (JSException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private void help() {
        final String[] array = { this.helpFileURL, this.parameters };
        try {
            JSObject.getWindow(this.parent).call(this.function, (Object[])array);
        }
        catch (JSException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void localeChanged() {
        final Locale locale = MapLocaleManager.getLocale();
        final ResourceBundle resourceBundle = PropertyManager.getResourceBundle("ca.ucalgary.phas.map.utilities.UtilitiesProperties", locale);
        this.en_caMenuItem.setIcon(locale.getLanguage().equals(MapLocaleManager.LOCALE_EN_CA.getLanguage()) ? MapMenuBar.checkmark : null);
        this.fr_caMenuItem.setIcon(locale.getLanguage().equals(MapLocaleManager.LOCALE_FR_CA.getLanguage()) ? MapMenuBar.checkmark : null);
        this.nl_nlMenuItem.setIcon(locale.getLanguage().equals(MapLocaleManager.LOCALE_NL_NL.getLanguage()) ? MapMenuBar.checkmark : null);
        this.i18nMenu.setText(resourceBundle.getString("MapMenuBar_i18nMenu_text"));
        this.helpMenu.setText(resourceBundle.getString("MapMenuBar_helpMenu_text"));
        this.helpMenuItem.setText(resourceBundle.getString("MapMenuBar_helpMenuItem_text"));
        this.helpMenuItem.setToolTipText(resourceBundle.getString("MapMenuBar_helpMenuItem_tooltip"));
        this.aboutMenuItem.setText(resourceBundle.getString("MapMenuBar_aboutMenuItem_text"));
        this.aboutMenuItem.setToolTipText(resourceBundle.getString("MapMenuBar_aboutMenuItem_tooltip"));
        this.en_caMenuItem.setText(MapLocaleManager.LOCALE_EN_CA.getDisplayLanguage(locale) + " - " + MapLocaleManager.LOCALE_EN_CA.getDisplayCountry(locale));
        this.fr_caMenuItem.setText(MapLocaleManager.LOCALE_FR_CA.getDisplayLanguage(locale) + " - " + MapLocaleManager.LOCALE_FR_CA.getDisplayCountry(locale));
        this.nl_nlMenuItem.setText(MapLocaleManager.LOCALE_NL_NL.getDisplayLanguage(locale) + " - " + MapLocaleManager.LOCALE_NL_NL.getDisplayCountry(locale));
        if (locale.getLanguage().equals(MapLocaleManager.LOCALE_FR_CA.getLanguage())) {
            this.setTitleText(this.titleString_fr);
        }
        else if (locale.getLanguage().equals(MapLocaleManager.LOCALE_NL_NL.getLanguage())) {
            this.setTitleText(this.titleString_nl);
        }
        else {
            this.setTitleText(this.titleString_en);
        }
    }
    
    public class AboutFrame extends JFrame
    {
        private final /* synthetic */ MapMenuBar this$0;
        
        public AboutFrame() {
            super("MAP (Modular Approach to Physics)");
            final Canvas comp = new Canvas() {
                private final /* synthetic */ AboutFrame this$1 = this$1;
                
                public void paint(final Graphics graphics) {
                    try {
                        this.this$1.this$0.tracker.waitForID(0);
                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    graphics.drawImage(this.this$1.this$0.image, 0, 0, this);
                    final FontMetrics fontMetrics = graphics.getFontMetrics();
                    final int stringWidth = fontMetrics.stringWidth(this.this$1.this$0.aboutLabel);
                    final int height = fontMetrics.getHeight();
                    graphics.setColor(Color.white);
                    graphics.fillRect((this.getWidth() - stringWidth) / 2 - 5, 325 - height - 2, stringWidth + 10, height + 6);
                    graphics.setColor(Color.black);
                    graphics.drawRect((this.getWidth() - stringWidth) / 2 - 5, 325 - height - 2, stringWidth + 10, height + 6);
                    graphics.drawString(this.this$1.this$0.aboutLabel, (this.getWidth() - stringWidth) / 2, 325);
                }
                
                public Dimension getMinimumSize() {
                    return new Dimension(this.getWidth(), this.getHeight());
                }
                
                public Dimension getMaximumSize() {
                    return this.getMinimumSize();
                }
                
                public Dimension getPreferredSize() {
                    return this.getMinimumSize();
                }
                
                public int getWidth() {
                    return 600;
                }
                
                public int getHeight() {
                    return 600;
                }
            };
            comp.addMouseListener(new MouseAdapter() {
                private final /* synthetic */ AboutFrame this$1 = this$1;
                
                public void mousePressed(final MouseEvent mouseEvent) {
                    this.this$1.hide();
                }
            });
            this.setSize(600, 500);
            this.setBackground(Color.white);
            this.setResizable(false);
            this.addWindowListener(new WindowAdapter() {
                private final /* synthetic */ AboutFrame this$1 = this$1;
                
                public void windowDeactivated(final WindowEvent windowEvent) {
                    this.this$1.hide();
                }
            });
            this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), 1));
            this.getContentPane().add(comp);
        }
    }
}
