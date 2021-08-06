// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import javax.swing.event.DocumentEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.event.DocumentListener;
import javax.swing.JDialog;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.util.Dictionary;
import java.util.Hashtable;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.Icon;
import javax.swing.event.ChangeEvent;
import java.util.Enumeration;
import java.util.Vector;
import java.text.DecimalFormat;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;

public class MapBorderedSlider extends JPanel implements ChangeListener, ActionListener, SwingConstants, MapLocaleListener
{
    private String NAN_STRING;
    private String TOO_HIGH_STRING;
    private String TOO_LOW_STRING;
    private String TOO_MANY_DECIMALS_PRE;
    private String TOO_MANY_DECIMALS_POST;
    static ImageIcon buttonImage;
    static ImageIcon buttonImageOver;
    static ImageIcon dialogImage;
    protected JSlider slider;
    protected MapButton inputButton;
    protected String labelString;
    protected String prefix;
    protected String units;
    private double highPrecisionValue;
    private double scale;
    private Color borderForegroundColor;
    private Color borderDisabledForegroundColor;
    protected DecimalFormat df;
    Vector changeListeners;
    Enumeration listenerEnum;
    ChangeEvent tempChangeEvent;
    private double tempRoundingValue;
    protected static ValueEntryDialog valueEntryDialog;
    
    public MapBorderedSlider(final int orientation, final double doubleMinimum, final double doubleMaximum, final double doubleValue, final String prefix, final String units, final int n) {
        this.prefix = new String("prefix");
        this.units = new String("units");
        this.borderDisabledForegroundColor = Color.gray;
        if (MapBorderedSlider.buttonImage == null) {
            MapBorderedSlider.buttonImage = new ImageIcon(ImageUtils.getImageFromJar("images/slider_button.png", this));
            MapBorderedSlider.buttonImageOver = new ImageIcon(ImageUtils.getImageFromJar("images/slider_button_over.png", this));
            MapBorderedSlider.dialogImage = new ImageIcon(ImageUtils.getImageFromJar("images/slider_dialog.gif", this));
        }
        this.prefix = prefix;
        this.units = units;
        this.scale = Math.pow(10.0, n);
        if (this.scale == 0.0) {
            this.scale = 1.0;
        }
        this.slider = new JSlider();
        (this.inputButton = new MapButton()).setIcon(MapBorderedSlider.buttonImage);
        this.inputButton.setRolloverIcon(MapBorderedSlider.buttonImageOver);
        this.inputButton.setMinimalInsets();
        this.inputButton.addActionListener(this);
        this.slider.setOrientation(orientation);
        this.changeListeners = new Vector();
        this.setDoubleMinimum(doubleMinimum);
        this.setDoubleMaximum(doubleMaximum);
        this.setDoubleValue(doubleValue);
        String pattern = "0";
        if (n > 0) {
            pattern += ".";
            for (int i = 0; i < n; ++i) {
                pattern += "0";
            }
        }
        this.df = new DecimalFormat(pattern);
        this.setBorder(new TitledBorder(new LineBorder(MapConstants.lineBorderColor), ""));
        this.setBorder();
        this.borderForegroundColor = ((TitledBorder)this.getBorder()).getTitleColor();
        this.slider.addChangeListener(this);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        final double[] columnWeights = { 0.0, 0.0, 1.0 };
        final int[] columnWidths = { 5, 2, 5 };
        final double[] rowWeights = { 1.0 };
        final int[] rowHeights = { 1 };
        layout.rowWeights = rowWeights;
        layout.columnWeights = columnWeights;
        layout.columnWidths = columnWidths;
        layout.rowHeights = rowHeights;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(this.inputButton, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        this.add(this.slider, gridBagConstraints);
        MapLocaleManager.addLocaleListener(this);
        this.localeChanged();
    }
    
    public void setDecimalFormat(final DecimalFormat df) {
        this.df = df;
        this.setBorder();
    }
    
    public DecimalFormat getDecimalFormat() {
        return this.df;
    }
    
    public void addChangeListener(final ChangeListener e) {
        this.changeListeners.add(e);
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.highPrecisionValue = this.getDoubleValue();
        this.setBorder();
        this.tempChangeEvent = new ChangeEvent(this);
        this.listenerEnum = this.changeListeners.elements();
        while (this.listenerEnum.hasMoreElements()) {
            this.listenerEnum.nextElement().stateChanged(this.tempChangeEvent);
        }
    }
    
    public void setEnabled(final boolean b) {
        this.slider.setEnabled(b);
        this.inputButton.setEnabled(b);
        if (this.getBorder() instanceof TitledBorder) {
            ((TitledBorder)this.getBorder()).setTitleColor(b ? this.borderForegroundColor : this.borderDisabledForegroundColor);
            this.repaint();
        }
    }
    
    public void setSnapToTicks(final boolean snapToTicks) {
        this.slider.setSnapToTicks(snapToTicks);
    }
    
    public void setPaintTicks(final boolean paintTicks) {
        this.slider.setPaintTicks(paintTicks);
    }
    
    public void setMinorTickSpacing(final int minorTickSpacing) {
        this.slider.setMinorTickSpacing(minorTickSpacing);
    }
    
    public void setMajorTickSpacing(final int majorTickSpacing) {
        this.slider.setMajorTickSpacing(majorTickSpacing);
    }
    
    public void setPaintLabels(final boolean paintLabels) {
        this.slider.setPaintLabels(paintLabels);
    }
    
    public void setLabelTable(final Hashtable labelTable) {
        this.slider.setLabelTable(labelTable);
    }
    
    public void setPrefix(final String prefix) {
        this.prefix = prefix;
        this.setBorder();
    }
    
    public void setUnits(final String units) {
        this.units = units;
        this.setBorder();
    }
    
    public void setValue(final int value) {
        this.slider.setValue(value);
    }
    
    public int getValue() {
        return this.slider.getValue();
    }
    
    public void setMaximum(final int maximum) {
        this.slider.setMaximum(maximum);
    }
    
    public int getMaximum() {
        return this.slider.getMaximum();
    }
    
    public JSlider getSlider() {
        return this.slider;
    }
    
    public void setToolTipText(final String s) {
        super.setToolTipText(s);
        this.slider.setToolTipText(s);
    }
    
    public void setDoubleMajorTickSpacing(final double n) {
        this.slider.setMajorTickSpacing((int)(n * this.scale));
    }
    
    public void setDoubleMinorTickSpacing(final double n) {
        this.slider.setMinorTickSpacing((int)(n * this.scale));
    }
    
    public double getDoubleValue() {
        return this.slider.getValue() / this.scale;
    }
    
    public double getDoubleMinimum() {
        return this.slider.getMinimum() / this.scale;
    }
    
    public double getDoubleMaximum() {
        return this.slider.getMaximum() / this.scale;
    }
    
    public double getHighPrecisionValue() {
        return this.highPrecisionValue;
    }
    
    protected void setBorder() {
        this.labelString = this.prefix + this.df.format(this.getDoubleValue()) + this.units;
        ((TitledBorder)this.getBorder()).setTitle(this.labelString);
        this.repaint();
    }
    
    public String getDoubleValueString() {
        return this.df.format(this.getDoubleValue());
    }
    
    public void setDoubleValue(final double highPrecisionValue) {
        this.highPrecisionValue = highPrecisionValue;
        this.tempRoundingValue = highPrecisionValue * this.scale;
        this.slider.setValue((int)(this.tempRoundingValue + ((this.tempRoundingValue > 0.0) ? 0.5 : -0.5)));
    }
    
    public void setDoubleMinimum(final double n) {
        this.slider.setMinimum((int)(n * this.scale));
    }
    
    public void setDoubleMaximum(final double n) {
        this.slider.setMaximum((int)(n * this.scale));
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.inputButton) {
            if (MapBorderedSlider.valueEntryDialog == null) {
                this.createValueEntryDialog();
            }
            this.showValueEntryDialog();
        }
    }
    
    protected void createValueEntryDialog() {
        (MapBorderedSlider.valueEntryDialog = new ValueEntryDialog(this)).setModal(false);
        MapBorderedSlider.valueEntryDialog.setVisible(true);
        MapBorderedSlider.valueEntryDialog.setVisible(false);
        MapBorderedSlider.valueEntryDialog.setModal(true);
    }
    
    protected void showValueEntryDialog() {
        MapBorderedSlider.valueEntryDialog.setValues(this, this.prefix, this.units, this.df, this.getDoubleMinimum(), this.getDoubleMaximum(), this.getDoubleValue());
        MapBorderedSlider.valueEntryDialog.setBounds(this.getLocationOnScreen().x + (this.getSize().width - 300) / 2, this.getLocationOnScreen().y + (this.getSize().height - 150) / 2, 300, 150);
        MapBorderedSlider.valueEntryDialog.setVisible(true);
    }
    
    public void localeChanged() {
        try {
            final ResourceBundle resourceBundle = PropertyManager.getResourceBundle("ca.ucalgary.phas.map.utilities.UtilitiesProperties", MapLocaleManager.getLocale());
            this.NAN_STRING = resourceBundle.getString("MapBorderedSlider_NAN_STRING");
            this.TOO_LOW_STRING = resourceBundle.getString("MapBorderedSlider_TOO_LOW_STRING");
            this.TOO_HIGH_STRING = resourceBundle.getString("MapBorderedSlider_TOO_HIGH_STRING");
            this.TOO_MANY_DECIMALS_PRE = resourceBundle.getString("MapBorderedSlider_TOO_MANY_DECIMALS_PRE");
            this.TOO_MANY_DECIMALS_POST = resourceBundle.getString("MapBorderedSlider_TOO_MANY_DECIMALS_POST");
            this.inputButton.setToolTipText(resourceBundle.getString("MapBorderedSlider_inputButton_tooltip"));
        }
        catch (Exception ex) {
            System.out.println("MapBorderedSlider.localeChanged() Error looking for some property. Check the property file for the key below");
            ex.printStackTrace();
        }
    }
    
    protected class ValueEntryDialog extends JDialog implements ActionListener, DocumentListener
    {
        private int allowedDecimalPlaces;
        private double minimumValue;
        private double maximumValue;
        private MapBorderedSlider slider;
        private JLabel entryLabel;
        private JTextField entryField;
        private JLabel unitLabel;
        private JLabel warningLabel;
        private MapButton okButton;
        private MapButton cancelButton;
        String tempString;
        double tempValue;
        
        public ValueEntryDialog(final Component parentComponent) {
            super(JOptionPane.getFrameForComponent(parentComponent), true);
            final JPanel comp = new JPanel();
            comp.setBorder(MapConstants.getDefaultPanelBorder());
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints constraints = new GridBagConstraints();
            comp.setLayout(layout);
            final double[] columnWeights = { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0 };
            final int[] columnWidths = { 5, 0, 0, 20, 2, 0, 5 };
            final double[] rowWeights = { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0 };
            final int[] rowHeights = { 5, 10, 2, 10, 5, 10, 5, 10, 5 };
            layout.rowWeights = rowWeights;
            layout.columnWeights = columnWeights;
            layout.columnWidths = columnWidths;
            layout.rowHeights = rowHeights;
            this.entryLabel = new JLabel();
            this.unitLabel = new JLabel();
            (this.warningLabel = new JLabel()).setForeground(Color.red);
            this.entryField = new JTextField();
            this.entryField.getDocument().addDocumentListener(this);
            this.entryField.addActionListener(this);
            final JLabel label = new JLabel(MapBorderedSlider.dialogImage);
            constraints.fill = 1;
            constraints.gridx = 1;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            constraints.gridheight = 3;
            constraints.fill = 2;
            constraints.gridx = 3;
            constraints.gridwidth = 3;
            constraints.gridheight = 1;
            comp.add(this.entryLabel, constraints);
            constraints.gridy = 3;
            constraints.gridwidth = 1;
            comp.add(this.entryField, constraints);
            constraints.gridx = 5;
            comp.add(this.unitLabel, constraints);
            constraints.fill = 1;
            final JPanel comp2 = new JPanel();
            constraints.gridx = 1;
            constraints.gridy = 7;
            constraints.gridwidth = 5;
            comp.add(comp2, constraints);
            constraints.fill = 0;
            constraints.gridy = 5;
            comp.add(this.warningLabel, constraints);
            final GridBagLayout layout2 = new GridBagLayout();
            comp2.setLayout(layout2);
            final double[] columnWeights2 = { 1.0, 0.0, 0.0, 0.0, 1.0 };
            final int[] columnWidths2 = { 5, 75, 5, 75, 5 };
            final double[] rowWeights2 = { 1.0 };
            final int[] rowHeights2 = { 10 };
            layout2.rowWeights = rowWeights2;
            layout2.columnWeights = columnWeights2;
            layout2.columnWidths = columnWidths2;
            layout2.rowHeights = rowHeights2;
            this.okButton = new MapButton("OK");
            this.cancelButton = new MapButton("Cancel");
            this.okButton.setFont(MapConstants.textOnlyButtonFont);
            this.cancelButton.setFont(MapConstants.textOnlyButtonFont);
            this.okButton.addActionListener(this);
            this.cancelButton.addActionListener(this);
            constraints.gridx = 1;
            constraints.gridy = 0;
            constraints.fill = 2;
            constraints.gridwidth = 1;
            comp2.add(this.okButton, constraints);
            constraints.gridx = 3;
            comp2.add(this.cancelButton, constraints);
            this.getContentPane().add(comp);
        }
        
        public void setValues(final MapBorderedSlider slider, final String str, final String text, final DecimalFormat decimalFormat, final double minimumValue, final double maximumValue, final double number) {
            this.slider = slider;
            this.allowedDecimalPlaces = decimalFormat.getMaximumFractionDigits();
            this.minimumValue = minimumValue;
            this.maximumValue = maximumValue;
            this.entryLabel.setText(str + " [" + decimalFormat.format(this.minimumValue) + ", " + decimalFormat.format(this.maximumValue) + "]");
            this.entryField.setText(decimalFormat.format(number));
            this.unitLabel.setText(text);
            this.warningLabel.setText("");
            this.entryField.setSelectionStart(0);
            this.entryField.requestFocus();
            this.entryField.getCaret().setSelectionVisible(true);
            this.entryField.requestFocus();
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == this.okButton) {
                try {
                    this.slider.setDoubleValue(Double.parseDouble(this.entryField.getText()));
                }
                catch (NumberFormatException ex) {}
                this.setVisible(false);
            }
            else if (actionEvent.getSource() == this.cancelButton) {
                this.setVisible(false);
            }
            if (actionEvent.getSource() == this.entryField && this.okButton.isEnabled()) {
                try {
                    this.slider.setDoubleValue(Double.parseDouble(this.entryField.getText()));
                }
                catch (NumberFormatException ex2) {}
                this.setVisible(false);
            }
        }
        
        public void insertUpdate(final DocumentEvent documentEvent) {
            this.documentChanged(documentEvent);
        }
        
        public void removeUpdate(final DocumentEvent documentEvent) {
            this.documentChanged(documentEvent);
        }
        
        public void changedUpdate(final DocumentEvent documentEvent) {
            this.documentChanged(documentEvent);
        }
        
        public void documentChanged(final DocumentEvent documentEvent) {
            this.tempString = this.entryField.getText();
            try {
                this.tempValue = Double.parseDouble(this.tempString);
            }
            catch (NumberFormatException ex) {
                this.warningLabel.setText(MapBorderedSlider.this.NAN_STRING);
                this.okButton.setEnabled(false);
                return;
            }
            if (this.tempValue < this.minimumValue) {
                this.warningLabel.setText(MapBorderedSlider.this.TOO_LOW_STRING);
                this.okButton.setEnabled(false);
                return;
            }
            if (this.tempValue > this.maximumValue) {
                this.warningLabel.setText(MapBorderedSlider.this.TOO_HIGH_STRING);
                this.okButton.setEnabled(false);
                return;
            }
            if (this.tempString.indexOf(".") != -1 && this.tempString.substring(this.tempString.indexOf(".")).length() - 1 > this.allowedDecimalPlaces) {
                this.warningLabel.setText(MapBorderedSlider.this.TOO_MANY_DECIMALS_PRE + this.allowedDecimalPlaces + MapBorderedSlider.this.TOO_MANY_DECIMALS_POST);
                this.okButton.setEnabled(false);
                return;
            }
            this.warningLabel.setText("");
            this.okButton.setEnabled(true);
        }
    }
}
