// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.ResourceBundle;
import ca.ucalgary.phas.map.utilities.PropertyManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.border.Border;
import ca.ucalgary.phas.map.utilities.IncompleteBorder;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import ca.ucalgary.phas.map.utilities.MapLocaleManager;
import javax.swing.AbstractButton;
import ca.ucalgary.phas.map.utilities.MapButtonPropertySetter;
import ca.ucalgary.phas.map.utilities.vectors.VectorLabelComponent;
import ca.ucalgary.phas.map.utilities.ImageUtils;
import ca.ucalgary.phas.map.utilities.ParameterUtils;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import ca.ucalgary.phas.map.utilities.ComplexString;
import javax.swing.JMenuBar;
import java.applet.Applet;
import ca.ucalgary.phas.map.utilities.MapMenuBar;
import ca.ucalgary.phas.map.utilities.MapConstants;
import ca.ucalgary.phas.map.utilities.vectors.Vector2d;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import ca.ucalgary.phas.map.utilities.MapRadioButton;
import java.awt.GridLayout;
import javax.swing.JPanel;
import ca.ucalgary.phas.map.utilities.vectors.VectorDescriptionControlPanel;
import ca.ucalgary.phas.map.utilities.MapButton;
import ca.ucalgary.phas.map.utilities.MapToggleButton;
import ca.ucalgary.phas.map.contentNavigator.PageBrowserAdapter;
import java.awt.Color;
import java.awt.Font;
import ca.ucalgary.phas.map.utilities.MapLocaleListener;
import ca.ucalgary.phas.map.utilities.vectors.VectorDescriptionControlPanelListener;
import ca.ucalgary.phas.map.contentNavigator.PageBrowserListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

public class VectorApplet extends JApplet implements ActionListener, PageBrowserListener, VectorDescriptionControlPanelListener, MapLocaleListener
{
    public static final String version = "1.0";
    public static final String revision = "$Revision: 1.44 $";
    private Font font;
    private Color labelColor;
    protected PageBrowserAdapter adapter;
    public boolean useAdapter;
    private MapToggleButton deleteButton;
    private MapToggleButton drawButton;
    private MapToggleButton drawLineButton;
    private MapButton resultantButton;
    private MapToggleButton answerButton;
    private MapButton abSubButton;
    private MapButton baSubButton;
    private MapToggleButton reverseVectorButton;
    private VectorDescriptionControlPanel vectorDescriptionControlPanel;
    public static final int MAX_ARBITRARY_VECTORS = 8;
    private VectorDescriptionControlPanel[] descriptionControlPanel;
    private JPanel controlPanel;
    private GridLayout controlPanelGridLayout;
    private int vdPanelsVisible;
    public MapRadioButton magnAnglePosOnlyRadioButton;
    private JLabel magnAnglePosOnlyRadioButtonLabel;
    public MapRadioButton magnAnglePosNegRadioButton;
    private JLabel magnAnglePosNegRadioButtonLabel;
    public MapRadioButton xyCoordsRadioButton;
    private JLabel xyCoordsRadioButtonLabel;
    public MapRadioButton magnNavCoordsRadioButton;
    private JLabel magnNavCoordsRadioButtonLabel;
    private String magnAnglePosOnlyRadioButtonLabelString;
    private String magnAnglePosNegRadioButtonLabelString;
    private String xyCoordsRadioButtonLabelString;
    private String magnNavCoordsRadioButtonLabelString;
    private VectorDescriptionControlPanel resultantDescriptionControlPanel;
    private JCheckBox showResultantCheckBox;
    private Vector2d resultantVector;
    private MapButton resetButton;
    private MapButton newVectorButton;
    private Color[] vectorColor;
    protected VectorPanel vPanel;
    protected Color vPanelColor;
    protected VectorDemo demo;
    boolean demoStarted;
    protected String appletTask;
    public static final String PROPERTY_FILE = "ca.ucalgary.phas.map.vectors.VectorsProperties";
    
    public VectorApplet() {
        this.font = MapConstants.vectorDefaultFont;
        this.labelColor = Color.black;
        this.demo = null;
        this.demoStarted = true;
    }
    
    public void init() {
        MapConstants.setMapLookAndFeel();
        final MapMenuBar jMenuBar = new MapMenuBar((Applet)this, true);
        jMenuBar.setVersion("1.0");
        jMenuBar.setBuild("$Revision: 1.44 $");
        if (!MapConstants.isMenuBarHidden()) {
            this.setJMenuBar((JMenuBar)jMenuBar);
        }
        this.vPanelColor = MapConstants.trackerPanelColor;
        final Color background = this.getBackground();
        this.useAdapter = true;
        this.magnAnglePosOnlyRadioButtonLabel = new JLabel();
        this.magnAnglePosOnlyRadioButtonLabelString = "";
        this.magnAnglePosOnlyRadioButtonLabel.setIcon(new ImageIcon(ComplexString.createImage(this.magnAnglePosOnlyRadioButtonLabelString, this.labelColor, this.font)));
        this.magnAnglePosOnlyRadioButtonLabel.setDisabledIcon(new ImageIcon(ComplexString.createDisabledImage(this.magnAnglePosOnlyRadioButtonLabelString, background, this.font)));
        this.magnAnglePosOnlyRadioButton = new MapRadioButton();
        this.magnAnglePosNegRadioButtonLabel = new JLabel();
        this.magnAnglePosNegRadioButtonLabelString = "";
        this.magnAnglePosNegRadioButtonLabel.setIcon(new ImageIcon(ComplexString.createImage(this.magnAnglePosNegRadioButtonLabelString, this.labelColor, this.font)));
        this.magnAnglePosNegRadioButtonLabel.setDisabledIcon(new ImageIcon(ComplexString.createDisabledImage(this.magnAnglePosNegRadioButtonLabelString, background, this.font)));
        this.magnAnglePosNegRadioButton = new MapRadioButton();
        this.xyCoordsRadioButtonLabel = new JLabel();
        this.xyCoordsRadioButtonLabelString = "";
        this.xyCoordsRadioButtonLabel.setIcon(new ImageIcon(ComplexString.createImage(this.xyCoordsRadioButtonLabelString, this.labelColor, this.font)));
        this.xyCoordsRadioButtonLabel.setDisabledIcon(new ImageIcon(ComplexString.createDisabledImage(this.xyCoordsRadioButtonLabelString, background, this.font)));
        this.xyCoordsRadioButton = new MapRadioButton();
        this.magnNavCoordsRadioButtonLabel = new JLabel();
        this.magnNavCoordsRadioButtonLabelString = "";
        this.magnNavCoordsRadioButtonLabel.setIcon(new ImageIcon(ComplexString.createImage(this.magnNavCoordsRadioButtonLabelString, this.labelColor, this.font)));
        this.magnNavCoordsRadioButtonLabel.setDisabledIcon(new ImageIcon(ComplexString.createDisabledImage(this.magnNavCoordsRadioButtonLabelString, background, this.font)));
        this.magnNavCoordsRadioButton = new MapRadioButton();
        this.showResultantCheckBox = new JCheckBox("");
        this.abSubButton = new MapButton("");
        this.baSubButton = new MapButton("");
        this.resultantButton = new MapButton("");
        this.answerButton = new MapToggleButton();
        this.reverseVectorButton = new MapToggleButton();
        this.newVectorButton = new MapButton();
        this.resetButton = new MapButton();
        final Vector<String> vector = new Vector<String>();
        vector.addElement("addition2");
        vector.addElement("addition3");
        vector.addElement("subtraction");
        vector.addElement("subtractionAddNegDemo");
        vector.addElement("subtractionCompDemo");
        vector.addElement("addition2Demo");
        vector.addElement("addition2DemoLines");
        vector.addElement("addition3Demo");
        vector.addElement("scalar");
        vector.addElement("numericalAddition");
        vector.addElement("specification");
        vector.addElement("general");
        vector.addElement("addition2SinglePage");
        vector.addElement("subtractionSinglePage");
        this.appletTask = ParameterUtils.getStringParameter((Applet)this, "TASK", (Vector)vector, "general");
        if (this.appletTask.equalsIgnoreCase("addition2")) {
            (this.vPanel = new Addition2Panel(this)).setTitleImage(ImageUtils.getImageFromJar("images/add2.gif", (Object)this));
            this.vPanel.showAnswer = 5;
            this.demo = null;
        }
        else if (this.appletTask.equalsIgnoreCase("addition3")) {
            (this.vPanel = new Addition3Panel(this)).setTitleImage(ImageUtils.getImageFromJar("images/add3.gif", (Object)this));
            this.vPanel.showAnswer = 5;
            this.demo = null;
        }
        else if (this.appletTask.equalsIgnoreCase("subtraction")) {
            (this.vPanel = new SubtractionPanel(this)).setTitleImage(ImageUtils.getImageFromJar("images/subcomp.gif", (Object)this));
            this.vPanel.showAnswer = 5;
            this.demo = null;
        }
        else if (this.appletTask.equalsIgnoreCase("subtractionAddNegDemo")) {
            (this.vPanel = new SubtractionPanel(this)).setTitleImage(ImageUtils.getImageFromJar("images/subaddneg.gif", (Object)this));
            this.demo = new SubtractionAddNegDemo(this.vPanel);
        }
        else if (this.appletTask.equalsIgnoreCase("subtractionCompDemo")) {
            (this.vPanel = new SubtractionPanel(this)).setTitleImage(ImageUtils.getImageFromJar("images/subcomp.gif", (Object)this));
            this.demo = new SubtractionCompDemo(this.vPanel);
        }
        else if (this.appletTask.equalsIgnoreCase("addition2Demo")) {
            (this.vPanel = new Addition2Panel(this)).setTitleImage(ImageUtils.getImageFromJar("images/add2.gif", (Object)this));
            this.demo = new Addition2Demo(this.vPanel);
        }
        else if (this.appletTask.equalsIgnoreCase("addition2DemoLines")) {
            (this.vPanel = new Addition2Panel(this)).setTitleImage(ImageUtils.getImageFromJar("images/add2lines.gif", (Object)this));
            this.demo = new Addition2DemoWithLines(this.vPanel);
        }
        else if (this.appletTask.equalsIgnoreCase("addition3Demo")) {
            (this.vPanel = new Addition3Panel(this)).setTitleImage(ImageUtils.getImageFromJar("images/add3.gif", (Object)this));
            this.demo = new Addition3Demo(this.vPanel);
        }
        else if (this.appletTask.equalsIgnoreCase("scalar")) {
            (this.vPanel = new ScalarPanel(this)).setTitleImage(ImageUtils.getImageFromJar("images/scalar.gif", (Object)this));
            this.demo = null;
        }
        else if (this.appletTask.equalsIgnoreCase("numericalAddition")) {
            this.useAdapter = ParameterUtils.getBooleanParameter((Applet)this, "USE_PAGE_BROWSER", false);
            this.vPanel = new ArbitraryAdditionPanel(this);
            this.enableRadioButtons(true);
            (this.resultantVector = new Vector2d()).setFillColor(Color.green);
            this.resultantVector.setSnapToZeroDist(0.0);
            this.resultantVector.setLabel((VectorLabelComponent)new ComplexString("<vector:r>", this.labelColor, this.font));
            this.resultantVector.setLabelVisible(true);
            this.resultantVector.setVisible(false);
            ((ArbitraryAdditionPanel)this.vPanel).resultantVector = this.resultantVector;
            MapButtonPropertySetter.setReset((AbstractButton)this.resetButton);
            ((AbstractButton)this.resetButton).addActionListener(this);
            MapButtonPropertySetter.setVectorIcon((AbstractButton)this.newVectorButton);
            ((AbstractButton)this.newVectorButton).addActionListener(this);
            (this.resultantDescriptionControlPanel = new VectorDescriptionControlPanel(this.resultantVector, "r")).setMode(2);
            this.resultantDescriptionControlPanel.setVectorDescriptionControlPanelListener((VectorDescriptionControlPanelListener)this);
            this.resultantDescriptionControlPanel.setEditabled(false);
            this.resultantDescriptionControlPanel.setVectorActivated(true);
            this.showResultantCheckBox.addActionListener(this);
            this.demo = null;
            (this.vectorColor = new Color[4])[0] = Color.red;
            this.vectorColor[1] = Color.blue;
            this.vectorColor[2] = Color.magenta;
            this.vectorColor[3] = Color.orange;
        }
        else if (this.appletTask.equalsIgnoreCase("specification")) {
            final boolean booleanParameter = ParameterUtils.getBooleanParameter((Applet)this, "SINGLE_PAGE_MODE", false);
            this.useAdapter = !booleanParameter;
            this.vPanel = new VectorSpecifyPanel(this);
            this.enableRadioButtons(booleanParameter);
            this.demo = null;
        }
        else if (this.appletTask.equalsIgnoreCase("general")) {
            final boolean booleanParameter2 = ParameterUtils.getBooleanParameter((Applet)this, "SINGLE_PAGE_MODE", false);
            this.useAdapter = !booleanParameter2;
            (this.vPanel = new GeneralPanel(this, booleanParameter2)).setTitleImage(ImageUtils.getImageFromJar("images/general.gif", (Object)this));
            this.demo = null;
        }
        else if (this.appletTask.equalsIgnoreCase("addition2SinglePage")) {
            this.useAdapter = false;
            this.vPanel = new Addition2InteractivePanel(this);
            MapButtonPropertySetter.setReset((AbstractButton)(this.resetButton = new MapButton()));
            ((AbstractButton)this.resetButton).addActionListener(this);
            ((AbstractButton)this.resultantButton).addActionListener(this);
            MapButtonPropertySetter.setResultantVectorIcon((AbstractButton)this.answerButton);
            ((AbstractButton)this.answerButton).addActionListener(this);
            this.demo = null;
        }
        else if (this.appletTask.equalsIgnoreCase("subtractionSinglePage")) {
            this.useAdapter = false;
            this.vPanel = new SubtractionInteractivePanel(this);
            MapButtonPropertySetter.setReset((AbstractButton)(this.resetButton = new MapButton()));
            ((AbstractButton)this.resetButton).addActionListener(this);
            ((AbstractButton)this.abSubButton).setIcon(new ImageIcon(ImageUtils.getImageFromJar("images/a-b.gif", (Object)this)));
            ((AbstractButton)this.abSubButton).setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("images/a-b_over.gif", (Object)this)));
            ((AbstractButton)this.abSubButton).addActionListener(this);
            ((AbstractButton)this.baSubButton).setIcon(new ImageIcon(ImageUtils.getImageFromJar("images/b-a.gif", (Object)this)));
            ((AbstractButton)this.baSubButton).setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("images/b-a_over.gif", (Object)this)));
            ((AbstractButton)this.baSubButton).addActionListener(this);
            ((AbstractButton)this.answerButton).setIcon(new ImageIcon(ImageUtils.getImageFromJar("images/difference.gif", (Object)this)));
            ((AbstractButton)this.answerButton).setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("images/difference_over.gif", (Object)this)));
            ((AbstractButton)this.answerButton).addActionListener(this);
            ((AbstractButton)this.reverseVectorButton).setIcon(new ImageIcon(ImageUtils.getImageFromJar("images/reverse.gif", (Object)this)));
            ((AbstractButton)this.reverseVectorButton).setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("images/reverse_over.gif", (Object)this)));
            ((AbstractButton)this.reverseVectorButton).addActionListener(this);
            this.demo = null;
        }
        else {
            System.out.println("Error occured getting TASK parameter");
        }
        final boolean booleanParameter3 = ParameterUtils.getBooleanParameter((Applet)this, "HIDE_TOOLS", false);
        MapLocaleManager.addLocaleListener((MapLocaleListener)this);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.vPanel.setBackground(this.vPanelColor);
        panel.setBorder((Border)new IncompleteBorder(MapConstants.isMenuBarHidden(), true, false, true));
        panel.add(this.vPanel);
        MapButtonPropertySetter.setDelete((AbstractButton)(this.deleteButton = new MapToggleButton()));
        MapButtonPropertySetter.setVectorIcon((AbstractButton)(this.drawButton = new MapToggleButton()));
        MapButtonPropertySetter.setExtrapolateIcon((AbstractButton)(this.drawLineButton = new MapToggleButton()));
        ((AbstractButton)this.deleteButton).addActionListener(this);
        ((AbstractButton)this.drawButton).addActionListener(this);
        ((AbstractButton)this.drawLineButton).addActionListener(this);
        final JPanel panel2 = new JPanel();
        panel2.setBorder(MapConstants.getDefaultPanelBorder());
        if (this.vPanel instanceof VectorSpecifyPanel) {
            ((AbstractButton)this.magnAnglePosOnlyRadioButton).addActionListener(this);
            ((AbstractButton)this.magnAnglePosNegRadioButton).addActionListener(this);
            ((AbstractButton)this.xyCoordsRadioButton).addActionListener(this);
            ((AbstractButton)this.magnNavCoordsRadioButton).addActionListener(this);
            this.vectorDescriptionControlPanel = new VectorDescriptionControlPanel();
            ((VectorSpecifyPanel)this.vPanel).setVectorDescriptionControlPanel(this.vectorDescriptionControlPanel);
            this.vectorDescriptionControlPanel.setVectorDescriptionControlPanelListener((VectorDescriptionControlPanelListener)this.vPanel);
            final double[] columnWeights = { 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5 };
            final int[] columnWidths = { 5, 0, 0, 5, 0, 0, 5 };
            final double[] rowWeights = { 0.33, 0.0, 0.33, 0.0, 0.0, 0.0, 0.33 };
            final int[] rowHeights = { 5, 30, 5, 0, 2, 0, 5 };
            final GridBagLayout layout = new GridBagLayout();
            panel2.setLayout(layout);
            layout.rowWeights = rowWeights;
            layout.columnWeights = columnWeights;
            layout.columnWidths = columnWidths;
            layout.rowHeights = rowHeights;
            final GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = 2;
            panel2.add((Component)this.magnAnglePosOnlyRadioButton);
            constraints.gridx = 1;
            constraints.gridy = 3;
            constraints.gridwidth = 1;
            constraints.gridheight = 1;
            layout.setConstraints((Component)this.magnAnglePosOnlyRadioButton, constraints);
            panel2.add(this.magnAnglePosOnlyRadioButtonLabel);
            constraints.gridx = 2;
            layout.setConstraints(this.magnAnglePosOnlyRadioButtonLabel, constraints);
            panel2.add((Component)this.magnAnglePosNegRadioButton);
            constraints.gridx = 1;
            constraints.gridy = 5;
            layout.setConstraints((Component)this.magnAnglePosNegRadioButton, constraints);
            panel2.add(this.magnAnglePosNegRadioButtonLabel);
            constraints.gridx = 2;
            layout.setConstraints(this.magnAnglePosNegRadioButtonLabel, constraints);
            panel2.add((Component)this.xyCoordsRadioButton);
            constraints.gridx = 4;
            constraints.gridy = 5;
            layout.setConstraints((Component)this.xyCoordsRadioButton, constraints);
            panel2.add(this.xyCoordsRadioButtonLabel);
            constraints.gridx = 5;
            layout.setConstraints(this.xyCoordsRadioButtonLabel, constraints);
            panel2.add((Component)this.magnNavCoordsRadioButton);
            constraints.gridx = 4;
            constraints.gridy = 3;
            layout.setConstraints((Component)this.magnNavCoordsRadioButton, constraints);
            panel2.add(this.magnNavCoordsRadioButtonLabel);
            constraints.gridx = 5;
            layout.setConstraints(this.magnNavCoordsRadioButtonLabel, constraints);
            panel2.add((Component)this.vectorDescriptionControlPanel);
            constraints.gridx = 1;
            constraints.gridy = 1;
            constraints.gridwidth = 5;
            layout.setConstraints((Component)this.vectorDescriptionControlPanel, constraints);
        }
        else if (this.vPanel instanceof ArbitraryAdditionPanel) {
            this.descriptionControlPanel = new VectorDescriptionControlPanel[8];
            for (int i = 0; i < 8; ++i) {
                final Vector2d vector2d = new Vector2d();
                vector2d.setFillColor(this.vectorColor[i % 4]);
                vector2d.setTailChangable(false);
                vector2d.setSnapToZeroDist(0.0);
                vector2d.setLabel((VectorLabelComponent)new ComplexString("<vector:v><sub>" + (i + 1) + "</sub>", this.labelColor, this.font));
                (this.descriptionControlPanel[i] = new VectorDescriptionControlPanel(vector2d, 2, i + 1, "v")).setMode(2);
                this.descriptionControlPanel[i].setVectorDescriptionControlPanelListener((VectorDescriptionControlPanelListener)this);
            }
            final JPanel panel3 = new JPanel();
            final JPanel comp = new JPanel();
            final double[] columnWeights2 = { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0 };
            final int[] columnWidths2 = { MapConstants.buttonPanelEastWestSpacing, 10, 10, MapConstants.buttonPanelInnerSpacing, 10, MapConstants.buttonPanelEastWestSpacing };
            final double[] rowWeights2 = { 0.0, 1.0, 0.0 };
            final int[] rowHeights2 = { MapConstants.buttonPanelSouthSpacing, 10, MapConstants.buttonPanelSouthSpacing };
            final GridBagLayout layout2 = new GridBagLayout();
            comp.setLayout(layout2);
            layout2.rowWeights = rowWeights2;
            layout2.columnWeights = columnWeights2;
            layout2.columnWidths = columnWidths2;
            layout2.rowHeights = rowHeights2;
            final GridBagConstraints constraints2 = new GridBagConstraints();
            constraints2.fill = 1;
            constraints2.gridx = 1;
            constraints2.gridy = 1;
            comp.add(this.showResultantCheckBox, constraints2);
            constraints2.gridx = 2;
            comp.add((Component)this.newVectorButton, constraints2);
            constraints2.gridx = 4;
            comp.add((Component)this.resetButton, constraints2);
            final double[] columnWeights3 = { 0.0, 1.0, 0.0 };
            final int[] columnWidths3 = { 5, 10, 5 };
            final double[] rowWeights3 = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0 };
            final int[] rowHeights3 = { 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 10, 5 };
            final GridBagLayout layout3 = new GridBagLayout();
            panel2.setLayout(layout3);
            layout3.rowWeights = rowWeights3;
            layout3.columnWeights = columnWeights3;
            layout3.columnWidths = columnWidths3;
            layout3.rowHeights = rowHeights3;
            final GridBagConstraints constraints3 = new GridBagConstraints();
            constraints3.fill = 1;
            constraints3.gridx = 1;
            constraints3.gridy = 1;
            for (int j = 0; j < 8; ++j) {
                panel2.add((Component)this.descriptionControlPanel[j], constraints3);
                final GridBagConstraints gridBagConstraints = constraints3;
                ++gridBagConstraints.gridy;
            }
            constraints3.gridy = 14;
            panel2.add((Component)this.resultantDescriptionControlPanel, constraints3);
            constraints3.gridy = 15;
            constraints3.gridx = 0;
            constraints3.gridwidth = 3;
            panel2.add(comp, constraints3);
        }
        else if (this.vPanel instanceof Addition2InteractivePanel) {
            final double[] columnWeights4 = { 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0 };
            final int[] columnWidths4 = { MapConstants.buttonPanelEastWestSpacing, 10, 10, 10, 10, MapConstants.buttonPanelInnerSpacing, 10, MapConstants.buttonPanelEastWestSpacing };
            final double[] rowWeights4 = { 0.0, 1.0, 0.0 };
            final int[] rowHeights4 = { MapConstants.buttonPanelSouthSpacing, 10, MapConstants.buttonPanelSouthSpacing };
            final GridBagLayout layout4 = new GridBagLayout();
            panel2.setLayout(layout4);
            layout4.rowWeights = rowWeights4;
            layout4.columnWeights = columnWeights4;
            layout4.columnWidths = columnWidths4;
            layout4.rowHeights = rowHeights4;
            final GridBagConstraints constraints4 = new GridBagConstraints();
            constraints4.fill = 1;
            constraints4.gridx = 1;
            constraints4.gridy = 1;
            panel2.add((Component)this.drawButton, constraints4);
            constraints4.gridx = 2;
            panel2.add((Component)this.drawLineButton, constraints4);
            constraints4.gridx = 3;
            panel2.add((Component)this.answerButton, constraints4);
            constraints4.gridx = 4;
            panel2.add((Component)this.resultantButton, constraints4);
            constraints4.gridx = 6;
            panel2.add((Component)this.resetButton, constraints4);
        }
        else if (this.vPanel instanceof SubtractionInteractivePanel) {
            panel2.setLayout(new GridLayout());
            panel2.add((Component)this.drawButton);
            panel2.add((Component)this.reverseVectorButton);
            panel2.add((Component)this.answerButton);
            panel2.add((Component)this.abSubButton);
            panel2.add((Component)this.baSubButton);
            panel2.add((Component)this.resetButton);
        }
        else {
            final double[] columnWeights5 = { 0.0, 1.0, 1.0, 1.0, 0.0 };
            final int[] columnWidths5 = { 0, 10, 10, 10, 0 };
            final double[] rowWeights5 = { 0.0, 0.0, 0.0 };
            final int[] rowHeights5 = { 0, 0, 0 };
            final GridBagLayout layout5 = new GridBagLayout();
            panel2.setLayout(layout5);
            layout5.rowWeights = rowWeights5;
            layout5.columnWeights = columnWeights5;
            layout5.columnWidths = columnWidths5;
            layout5.rowHeights = rowHeights5;
            final GridBagConstraints constraints5 = new GridBagConstraints();
            constraints5.fill = 1;
            constraints5.gridx = 1;
            constraints5.gridy = 1;
            panel2.add((Component)this.drawButton, constraints5);
            constraints5.gridx = 2;
            panel2.add((Component)this.drawLineButton, constraints5);
            constraints5.gridx = 3;
            panel2.add((Component)this.deleteButton, constraints5);
        }
        final double[] columnWeights6 = { 1.0 };
        final int[] columnWidths6 = { 10 };
        final double[] rowWeights6 = { 1.0, 0.0 };
        final int[] rowHeights6 = { 100, 0 };
        final GridBagLayout layout6 = new GridBagLayout();
        this.getContentPane().setLayout(layout6);
        layout6.rowWeights = rowWeights6;
        layout6.columnWeights = columnWeights6;
        layout6.columnWidths = columnWidths6;
        layout6.rowHeights = rowHeights6;
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.fill = 1;
        this.getContentPane().add(panel);
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        layout6.setConstraints(panel, gridBagConstraints2);
        if (!booleanParameter3) {
            this.getContentPane().add(panel2);
            gridBagConstraints2.gridx = 0;
            gridBagConstraints2.gridy = 1;
            gridBagConstraints2.gridwidth = 1;
            gridBagConstraints2.gridheight = 1;
            layout6.setConstraints(panel2, gridBagConstraints2);
        }
        if (this.useAdapter) {
            this.adapter.setActive(true);
        }
        this.localeChanged();
    }
    
    public void localeChanged() {
        try {
            final ResourceBundle resourceBundle = PropertyManager.getResourceBundle("ca.ucalgary.phas.map.vectors.VectorsProperties", MapLocaleManager.getLocale());
            this.magnAnglePosOnlyRadioButtonLabelString = resourceBundle.getString("VectorApplet_magnAnglePosOnlyRadioButtonLabelString");
            this.magnAnglePosOnlyRadioButtonLabel.setIcon(new ImageIcon(ComplexString.createImage(this.magnAnglePosOnlyRadioButtonLabelString, this.labelColor, this.font)));
            this.magnAnglePosOnlyRadioButtonLabel.setDisabledIcon(new ImageIcon(ComplexString.createDisabledImage(this.magnAnglePosOnlyRadioButtonLabelString, this.getBackground(), this.font)));
            this.magnAnglePosNegRadioButtonLabelString = resourceBundle.getString("VectorApplet_magnAnglePosNegRadioButtonLabelString");
            this.magnAnglePosNegRadioButtonLabel.setIcon(new ImageIcon(ComplexString.createImage(this.magnAnglePosNegRadioButtonLabelString, this.labelColor, this.font)));
            this.magnAnglePosNegRadioButtonLabel.setDisabledIcon(new ImageIcon(ComplexString.createDisabledImage(this.magnAnglePosNegRadioButtonLabelString, this.getBackground(), this.font)));
            this.xyCoordsRadioButtonLabelString = resourceBundle.getString("VectorApplet_xyCoordsRadioButtonLabelString");
            this.xyCoordsRadioButtonLabel.setIcon(new ImageIcon(ComplexString.createImage(this.xyCoordsRadioButtonLabelString, this.labelColor, this.font)));
            this.xyCoordsRadioButtonLabel.setDisabledIcon(new ImageIcon(ComplexString.createDisabledImage(this.xyCoordsRadioButtonLabelString, this.getBackground(), this.font)));
            this.magnNavCoordsRadioButtonLabelString = resourceBundle.getString("VectorApplet_magnNavCoordsRadioButtonLabelString");
            this.magnNavCoordsRadioButtonLabel.setIcon(new ImageIcon(ComplexString.createImage(this.magnNavCoordsRadioButtonLabelString, this.labelColor, this.font)));
            this.magnNavCoordsRadioButtonLabel.setDisabledIcon(new ImageIcon(ComplexString.createDisabledImage(this.magnNavCoordsRadioButtonLabelString, this.getBackground(), this.font)));
            this.showResultantCheckBox.setText(resourceBundle.getString("VectorApplet_showResultantCheckBox_text"));
            this.showResultantCheckBox.setToolTipText(resourceBundle.getString("VectorApplet_showResultantCheckBox_tooltip"));
            if (this.vPanel instanceof SubtractionInteractivePanel) {
                ((AbstractButton)this.abSubButton).setText(resourceBundle.getString("VectorApplet_abSubButton_simple_text"));
                ((JComponent)this.abSubButton).setToolTipText(resourceBundle.getString("VectorApplet_abSubButton_simple_tooltip"));
                ((AbstractButton)this.baSubButton).setText(resourceBundle.getString("VectorApplet_baSubButton_simple_text"));
                ((JComponent)this.baSubButton).setToolTipText(resourceBundle.getString("VectorApplet_baSubButton_simple_tooltip"));
                ((AbstractButton)this.answerButton).setText(resourceBundle.getString("VectorApplet_answerButton_simple_text"));
                ((JComponent)this.answerButton).setToolTipText(resourceBundle.getString("VectorApplet_answerButton_simple_tooltip"));
                ((SubtractionInteractivePanel)this.vPanel).differenceVectorString = resourceBundle.getString("VectorPanel_differenceVector_string");
                ((SubtractionInteractivePanel)this.vPanel).setAnswerVectorLabel();
            }
            else {
                ((AbstractButton)this.abSubButton).setText(resourceBundle.getString("VectorApplet_abSubButton_normal_text"));
                ((JComponent)this.abSubButton).setToolTipText(resourceBundle.getString("VectorApplet_abSubButton_normal_tooltip"));
                ((AbstractButton)this.baSubButton).setText(resourceBundle.getString("VectorApplet_baSubButton_normal_text"));
                ((JComponent)this.baSubButton).setToolTipText(resourceBundle.getString("VectorApplet_baSubButton_normal_tooltip"));
                ((AbstractButton)this.answerButton).setText(resourceBundle.getString("VectorApplet_answerButton_normal_text"));
                ((JComponent)this.answerButton).setToolTipText(resourceBundle.getString("VectorApplet_answerButton_normal_tooltip"));
            }
            if (this.vPanel instanceof Addition2InteractivePanel) {
                ((Addition2InteractivePanel)this.vPanel).sumVectorString = resourceBundle.getString("VectorPanel_sumVector_string");
                ((Addition2InteractivePanel)this.vPanel).setAnswerVectorLabel();
            }
            ((AbstractButton)this.resultantButton).setText(resourceBundle.getString("VectorApplet_resultantButton_text"));
            ((JComponent)this.resultantButton).setToolTipText(resourceBundle.getString("VectorApplet_resultantButton_tooltip"));
            ((AbstractButton)this.drawButton).setText(resourceBundle.getString("VectorApplet_drawButton_text"));
            ((JComponent)this.drawButton).setToolTipText(resourceBundle.getString("VectorApplet_drawButton_tooltip"));
            ((AbstractButton)this.reverseVectorButton).setText(resourceBundle.getString("VectorApplet_reverseVectorButton_text"));
            ((JComponent)this.reverseVectorButton).setToolTipText(resourceBundle.getString("VectorApplet_reverseVectorButton_tooltip"));
            ((AbstractButton)this.drawLineButton).setText(resourceBundle.getString("VectorApplet_drawLineButton_text"));
            ((JComponent)this.drawLineButton).setToolTipText(resourceBundle.getString("VectorApplet_drawLineButton_tooltip"));
            ((AbstractButton)this.newVectorButton).setText(resourceBundle.getString("VectorApplet_newVectorButton_text"));
            ((JComponent)this.newVectorButton).setToolTipText(resourceBundle.getString("VectorApplet_newVectorButton_tooltip"));
        }
        catch (Exception ex) {
            System.out.println("VectorApplet.localeChanged() Error looking for some property. Check the property file for the key below:");
            ex.printStackTrace();
        }
    }
    
    public void setResultantButtonEnabled(final boolean enabled) {
        ((AbstractButton)this.resultantButton).setEnabled(enabled);
        ((AbstractButton)this.abSubButton).setEnabled(enabled && this.vPanel.vectorAnswer != null && this.vPanel.vectorAnswer.isVisible());
        ((AbstractButton)this.baSubButton).setEnabled(enabled && this.vPanel.vectorAnswer != null && this.vPanel.vectorAnswer.isVisible());
    }
    
    public void setReverseVectorButtonSelected(final boolean selected) {
        ((AbstractButton)this.reverseVectorButton).setSelected(selected);
        this.reverseVectorButtonAction();
    }
    
    private void reverseVectorButtonAction() {
        if (this.vPanel instanceof SubtractionInteractivePanel) {
            final boolean selected = ((AbstractButton)this.reverseVectorButton).isSelected();
            ((SubtractionInteractivePanel)this.vPanel).reversing = selected;
            this.setResultantButtonEnabled(!selected);
        }
    }
    
    public void setReverseVectorButtonEnabled(final boolean b) {
        if (this.vPanel instanceof SubtractionInteractivePanel) {
            ((AbstractButton)this.reverseVectorButton).setEnabled(b && (!((SubtractionInteractivePanel)this.vPanel).vectorNegA.isVisible() || !((SubtractionInteractivePanel)this.vPanel).vectorNegB.isVisible()));
        }
    }
    
    public void setResetButtonEnabled(final boolean enabled) {
        ((AbstractButton)this.resetButton).setEnabled(enabled);
    }
    
    public void setDrawVectorButtonEnabled(final boolean enabled) {
        ((AbstractButton)this.drawButton).setEnabled(enabled);
    }
    
    public void setDrawLineButtonEnabled(final boolean enabled) {
        ((AbstractButton)this.drawLineButton).setEnabled(enabled);
    }
    
    public void setAnswerButtonEnabled(final boolean enabled) {
        ((AbstractButton)this.answerButton).setEnabled(enabled);
    }
    
    private void addNewVectorControlPanel() {
        if (this.vPanel instanceof ArbitraryAdditionPanel) {
            if (this.vdPanelsVisible >= 8) {
                ((AbstractButton)this.newVectorButton).setEnabled(false);
                return;
            }
            final Point2D.Double double1 = new Point2D.Double();
            Point2D.Double double2;
            if (this.vdPanelsVisible == 0) {
                double2 = new Point2D.Double(100.0, 100.0);
                double1.x = double2.x + 40.0;
                double1.y = double2.y + 69.28;
            }
            else if (this.vdPanelsVisible == 1) {
                double2 = this.descriptionControlPanel[this.vdPanelsVisible - 1].getVectorHead();
                double1.x = double2.x + 173.84;
                double1.y = double2.y + 63.27;
            }
            else {
                double2 = this.descriptionControlPanel[this.vdPanelsVisible - 1].getVectorHead();
                double1.x = double2.x + 50.0;
                double1.y = double2.y + 50.0;
            }
            final Vector2d vector = this.descriptionControlPanel[this.vdPanelsVisible].getVector();
            this.vPanel.addVector(vector);
            vector.setVector(double2, double1);
            vector.setVisible(true);
            vector.setLabelVisible(true);
            ((JComponent)this.descriptionControlPanel[this.vdPanelsVisible]).setVisible(true);
            this.descriptionControlPanel[this.vdPanelsVisible].setVectorActivated(true);
            this.descriptionControlPanel[this.vdPanelsVisible].updateTextFields();
            ++this.vdPanelsVisible;
            if (this.vdPanelsVisible > 1) {
                this.updateResultantVector();
            }
            if (this.vdPanelsVisible >= 8) {
                ((AbstractButton)this.newVectorButton).setEnabled(false);
            }
        }
    }
    
    public void instantiateAdapter(final String s) {
        if (this.useAdapter) {
            this.adapter = new PageBrowserAdapter((PageBrowserListener)this, s);
        }
    }
    
    public void updateSpecificationPanel(final Vector2d vector2d) {
        this.vectorDescriptionControlPanel.updateTextFields(vector2d);
    }
    
    public void enableRadioButtons(final boolean b) {
        ((AbstractButton)this.magnAnglePosOnlyRadioButton).setEnabled(b);
        this.magnAnglePosOnlyRadioButtonLabel.setEnabled(b);
        ((AbstractButton)this.magnAnglePosNegRadioButton).setEnabled(b);
        this.magnAnglePosNegRadioButtonLabel.setEnabled(b);
        ((AbstractButton)this.xyCoordsRadioButton).setEnabled(b);
        this.xyCoordsRadioButtonLabel.setEnabled(b);
        ((AbstractButton)this.magnNavCoordsRadioButton).setEnabled(b);
        this.magnNavCoordsRadioButtonLabel.setEnabled(b);
    }
    
    public void setSpecificationMode(final int mode) {
        this.vectorDescriptionControlPanel.setMode(mode);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source.equals(this.deleteButton)) {
            this.doDelete();
        }
        else if (source.equals(this.drawButton)) {
            this.doDraw();
        }
        else if (source.equals(this.drawLineButton)) {
            this.doDrawLine();
        }
        else if (source.equals(this.magnAnglePosOnlyRadioButton)) {
            if (((AbstractButton)this.magnAnglePosOnlyRadioButton).isSelected()) {
                this.doMagnAnglePosOnlyRadioButtonAction();
            }
        }
        else if (source.equals(this.magnAnglePosNegRadioButton)) {
            if (((AbstractButton)this.magnAnglePosNegRadioButton).isSelected()) {
                this.doMagnAnglePosNegRadioButtonAction();
            }
        }
        else if (source.equals(this.xyCoordsRadioButton)) {
            if (((AbstractButton)this.xyCoordsRadioButton).isSelected()) {
                this.doXyCoordsRadioButtonAction();
            }
        }
        else if (source.equals(this.magnNavCoordsRadioButton)) {
            if (((AbstractButton)this.magnNavCoordsRadioButton).isSelected()) {
                this.doMagnNavCoordsRadioButtonAction();
            }
        }
        else if (source.equals(this.showResultantCheckBox)) {
            this.doShowResultantRadioButtonAction();
        }
        else if (source.equals(this.newVectorButton)) {
            this.addNewVectorControlPanel();
        }
        else if (source.equals(this.resetButton)) {
            this.resetApplet();
        }
        else if (source.equals(this.resultantButton)) {
            if (this.vPanel instanceof Addition2InteractivePanel) {
                ((Addition2InteractivePanel)this.vPanel).calculateAnswer();
            }
        }
        else if (source.equals(this.abSubButton)) {
            if (this.vPanel instanceof SubtractionInteractivePanel) {
                ((SubtractionInteractivePanel)this.vPanel).calculateAnswerAB();
            }
        }
        else if (source.equals(this.baSubButton)) {
            if (this.vPanel instanceof SubtractionInteractivePanel) {
                ((SubtractionInteractivePanel)this.vPanel).calculateAnswerBA();
            }
        }
        else if (source.equals(this.answerButton)) {
            if (this.vPanel instanceof Addition2InteractivePanel) {
                ((Addition2InteractivePanel)this.vPanel).setDrawingAnswer(((AbstractButton)this.answerButton).isSelected());
            }
            if (this.vPanel instanceof SubtractionInteractivePanel) {
                final boolean selected = ((AbstractButton)this.answerButton).isSelected();
                ((SubtractionInteractivePanel)this.vPanel).setDrawingAnswer(selected);
                this.setReverseVectorButtonEnabled(!selected);
                this.setResultantButtonEnabled(!selected);
            }
        }
        else if (source.equals(this.reverseVectorButton)) {
            this.reverseVectorButtonAction();
        }
    }
    
    public void doXyCoordsRadioButtonAction() {
        ((AbstractButton)this.magnAnglePosNegRadioButton).setSelected(false);
        ((AbstractButton)this.magnNavCoordsRadioButton).setSelected(false);
        ((AbstractButton)this.magnAnglePosOnlyRadioButton).setSelected(false);
        this.setSpecificationMode(1);
    }
    
    public void doMagnAnglePosNegRadioButtonAction() {
        ((AbstractButton)this.magnAnglePosOnlyRadioButton).setSelected(false);
        ((AbstractButton)this.magnNavCoordsRadioButton).setSelected(false);
        ((AbstractButton)this.xyCoordsRadioButton).setSelected(false);
        this.setSpecificationMode(3);
    }
    
    public void doMagnNavCoordsRadioButtonAction() {
        ((AbstractButton)this.magnAnglePosNegRadioButton).setSelected(false);
        ((AbstractButton)this.magnAnglePosOnlyRadioButton).setSelected(false);
        ((AbstractButton)this.xyCoordsRadioButton).setSelected(false);
        this.setSpecificationMode(0);
    }
    
    public void doMagnAnglePosOnlyRadioButtonAction() {
        ((AbstractButton)this.magnAnglePosNegRadioButton).setSelected(false);
        ((AbstractButton)this.magnNavCoordsRadioButton).setSelected(false);
        ((AbstractButton)this.xyCoordsRadioButton).setSelected(false);
        this.setSpecificationMode(2);
    }
    
    public void doShowResultantRadioButtonAction() {
        final boolean selected = this.showResultantCheckBox.isSelected();
        ((JComponent)this.resultantDescriptionControlPanel).setVisible(selected);
        this.resultantDescriptionControlPanel.updateTextFields(this.resultantVector);
        this.resultantVector.setVisible(selected);
        this.vPanel.repaint();
    }
    
    public void vectorTipHasMoved(final Vector2d vector2d, final double n, final double n2) {
        for (int i = 0; i < 8; ++i) {
            if (this.descriptionControlPanel[i].getVector() == vector2d) {
                this.descriptionControlPanel[i].updateTextFields(vector2d);
                this.vectorChanged(i, n, n2);
                break;
            }
        }
    }
    
    public void vectorChanged(final VectorDescriptionControlPanel vectorDescriptionControlPanel, final double n, final double n2) {
        for (int i = 0; i < 8; ++i) {
            if (this.descriptionControlPanel[i] == vectorDescriptionControlPanel) {
                this.vectorChanged(i, n, n2);
            }
        }
    }
    
    public void vectorChanged(int i, final double n, final double n2) {
        if (-0.01 >= n || n >= 0.01 || -0.01 >= n2 || n2 >= 0.01) {
            ++i;
            while (i < 8) {
                this.descriptionControlPanel[i].shiftVector(n, n2);
                ++i;
            }
            this.updateResultantVector();
        }
    }
    
    public void updateResultantVector() {
        this.showResultantCheckBox.setSelected(false);
        this.doShowResultantRadioButtonAction();
        this.resultantVector.setTail(this.descriptionControlPanel[0].getVector().getTail2D());
        this.resultantVector.setHead(this.descriptionControlPanel[this.vdPanelsVisible - 1].getVector().getHead2D());
    }
    
    public void originChanged(final VectorDescriptionControlPanel vectorDescriptionControlPanel) {
    }
    
    public void modeChanged(final VectorDescriptionControlPanel vectorDescriptionControlPanel) {
    }
    
    public void textFieldActionPerformed(final VectorDescriptionControlPanel vectorDescriptionControlPanel) {
        if (!vectorDescriptionControlPanel.isVectorActive() && vectorDescriptionControlPanel.valuesAreGood()) {
            vectorDescriptionControlPanel.setVectorActivated(true);
            this.addNewVectorControlPanel();
        }
        for (int i = 0; i < 8; ++i) {
            if (this.descriptionControlPanel[i] != vectorDescriptionControlPanel) {
                this.descriptionControlPanel[i].setVector();
            }
        }
        this.vPanel.repaint();
    }
    
    public void selectVectorInScrollPane(final Vector2d vector2d) {
    }
    
    public void doDelete() {
        this.vPanel.deselectAll();
        this.vPanel.setDeletingVector(!this.vPanel.isDeletingVector());
    }
    
    public void doDraw() {
        this.vPanel.deselectAll();
        this.vPanel.setDrawingNewVector(true);
    }
    
    public void doDrawLine() {
        this.vPanel.deselectAll();
        this.vPanel.setDrawingNewLine(true);
    }
    
    public void enableContinue(final boolean b) {
        try {
            this.adapter.enableNextPageButton(b);
        }
        catch (NullPointerException ex) {}
    }
    
    public void enableTools(final boolean reverseVectorButtonEnabled) {
        ((AbstractButton)this.deleteButton).setEnabled(reverseVectorButtonEnabled);
        ((AbstractButton)this.drawLineButton).setEnabled(reverseVectorButtonEnabled);
        if (this.vPanel instanceof SubtractionInteractivePanel) {
            this.setReverseVectorButtonEnabled(reverseVectorButtonEnabled);
        }
    }
    
    public void setDrawButtonEnabled(final boolean enabled) {
        ((AbstractButton)this.drawButton).setEnabled(enabled);
    }
    
    public void setDeleteButtonEnabled(final boolean enabled) {
        ((AbstractButton)this.deleteButton).setEnabled(enabled);
    }
    
    public void setDeleteButtonSelected(final boolean selected) {
        ((AbstractButton)this.deleteButton).setSelected(selected);
    }
    
    public void setDrawButtonSelected(final boolean selected) {
        ((AbstractButton)this.drawButton).setSelected(selected);
    }
    
    public void setDrawLineButtonSelected(final boolean selected) {
        ((AbstractButton)this.drawLineButton).setSelected(selected);
    }
    
    public void setAnswerButtonSelected(final boolean selected) {
        ((AbstractButton)this.answerButton).setSelected(selected);
    }
    
    public void doCommand(final String s) {
        if (this.demo != null) {
            this.demo.myResume();
        }
        this.vPanel.doCommand(s);
    }
    
    private void setupDemo() {
        if (this.appletTask.equalsIgnoreCase("Addition2")) {
            this.demo = null;
        }
        else if (this.appletTask.equalsIgnoreCase("Addition3")) {
            this.demo = null;
        }
        else if (this.appletTask.equalsIgnoreCase("Subtraction")) {
            this.demo = null;
        }
        else if (this.appletTask.equalsIgnoreCase("SubtractionAddNegDemo")) {
            this.demo = new SubtractionAddNegDemo(this.vPanel);
        }
        else if (this.appletTask.equalsIgnoreCase("SubtractionCompDemo")) {
            this.demo = new SubtractionCompDemo(this.vPanel);
        }
        else if (this.appletTask.equalsIgnoreCase("Addition2Demo")) {
            this.demo = new Addition2Demo(this.vPanel);
        }
        else if (this.appletTask.equalsIgnoreCase("Addition2DemoLines")) {
            this.demo = new Addition2DemoWithLines(this.vPanel);
        }
        else if (this.appletTask.equalsIgnoreCase("Addition3Demo")) {
            this.demo = new Addition3Demo(this.vPanel);
        }
        else if (this.appletTask.equalsIgnoreCase("Scalar")) {
            this.demo = null;
        }
        else if (this.appletTask.equalsIgnoreCase("General")) {
            this.demo = null;
        }
    }
    
    public boolean isDemoMode() {
        return this.demo != null;
    }
    
    public void resetApplet() {
        this.vPanel.clear();
        this.vPanel.setStep(1);
        if (this.demo != null) {
            this.demo.stopDemo();
            this.demo = null;
        }
        this.setupDemo();
        this.startDemo();
        this.enableTools(false);
        this.vPanel.repaint();
        this.repaint();
        this.vPanel.enableContinue(true);
        if (this.vPanel instanceof ArbitraryAdditionPanel) {
            for (int i = 0; i < 8; ++i) {
                this.descriptionControlPanel[i].getVector().setVisible(false);
                ((JComponent)this.descriptionControlPanel[i]).setVisible(false);
            }
            this.vdPanelsVisible = 0;
            ((AbstractButton)this.newVectorButton).setEnabled(true);
            this.resultantVector.setVisible(false);
            this.showResultantCheckBox.setSelected(false);
            ((JComponent)this.resultantDescriptionControlPanel).setVisible(false);
            this.addNewVectorControlPanel();
            this.addNewVectorControlPanel();
        }
        if (this.vPanel instanceof Addition2InteractivePanel) {
            this.setResetButtonEnabled(false);
            this.setResultantButtonEnabled(false);
            this.setDrawVectorButtonEnabled(true);
            this.setAnswerButtonEnabled(false);
        }
        if (this.vPanel instanceof SubtractionInteractivePanel) {
            this.setResetButtonEnabled(false);
            this.setResultantButtonEnabled(false);
            this.setDrawVectorButtonEnabled(true);
            this.setAnswerButtonEnabled(false);
            ((AbstractButton)this.reverseVectorButton).setSelected(false);
            ((SubtractionInteractivePanel)this.vPanel).reset();
        }
    }
    
    public void start() {
        this.resetApplet();
        this.vPanel.start();
        if (this.vPanel instanceof VectorSpecifyPanel) {
            ((AbstractButton)this.magnAnglePosOnlyRadioButton).setSelected(true);
            this.setSpecificationMode(2);
        }
    }
    
    public void startDemo() {
        if (this.demo != null) {
            this.demo.start();
        }
        else {
            this.vPanel.enableContinue(true);
        }
        this.demoStarted = true;
    }
    
    public void stop() {
        if (this.demo != null) {
            this.demo.stopDemo();
        }
        if (this.adapter != null) {
            this.adapter.setActive(false);
            this.adapter.remove();
        }
    }
    
    public void suspendDemo() {
        if (this.demo != null) {
            this.demo.mySuspend();
            this.vPanel.enableContinue(true);
        }
    }
}
