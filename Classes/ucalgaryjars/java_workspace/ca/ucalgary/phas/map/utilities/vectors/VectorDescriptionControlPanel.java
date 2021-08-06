// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities.vectors;

import java.util.ResourceBundle;
import ca.ucalgary.phas.map.utilities.PropertyManager;
import java.awt.Toolkit;
import javax.swing.AbstractButton;
import ca.ucalgary.phas.map.utilities.MapButtonPropertySetter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import ca.ucalgary.phas.map.utilities.MapLocaleManager;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import ca.ucalgary.phas.map.utilities.ComplexString;
import ca.ucalgary.phas.map.utilities.MapConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import ca.ucalgary.phas.map.utilities.MapButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import ca.ucalgary.phas.map.utilities.I18nStringObject;
import ca.ucalgary.phas.map.utilities.MapLocaleListener;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class VectorDescriptionControlPanel extends JPanel implements ActionListener, FocusListener, MapLocaleListener
{
    public static I18nStringObject WOFN;
    public static I18nStringObject EOFN;
    public static I18nStringObject NOFE;
    public static I18nStringObject SOFE;
    public static I18nStringObject EOFS;
    public static I18nStringObject WOFS;
    public static I18nStringObject SOFW;
    public static I18nStringObject NOFW;
    private double scale;
    private double maxMagnitude;
    private boolean hasMaxMagnitude;
    public static final int MAGN_NAV_COORDS = 0;
    public static final int XY_COORDS = 1;
    public static final int MAGN_ANGLE_POS_ONLY = 2;
    public static final int MAGN_ANGLE_POS_NEG = 3;
    public static final int NUMBER_OF_MODES = 4;
    private DecimalFormat df;
    public Font labelFont;
    public Color labelColor;
    private Vector2d vector;
    private double magnitude;
    private double angleOfVectorRelativeToOrigin;
    private double angleOfOrigin;
    private boolean vectorIsActive;
    private GridBagLayout vectorDisplayPanelGridBagLayout;
    private GridBagConstraints vectorDisplayPanelGridBagConstraints;
    private int mode;
    private MapButton modeButton;
    private JLabel vXYLabel;
    private JLabel commaLabel;
    private JLabel closeLabel;
    private JLabel xUnitLabel;
    private JLabel yUnitLabel;
    private JLabel mUnitLabel;
    private JLabel vPolarLabel;
    private JLabel degreesLabel;
    private JTextField xComponentTextField;
    private JTextField yComponentTextField;
    private JTextField magnitudeTextField;
    private JTextField angleTextField;
    private JComboBox navCoordsComboBox;
    private VectorDescriptionControlPanelListener vectorDescriptionControlPanelListener;
    private GridBagLayout gbl;
    
    public void setScale(final double scale) {
        this.scale = scale;
    }
    
    public void setMaxMagnitude(final double maxMagnitude) {
        this.maxMagnitude = maxMagnitude;
        this.hasMaxMagnitude = true;
    }
    
    public void setDecimalFormat(final String pattern) {
        this.df = new DecimalFormat(pattern);
        this.updateTextFields();
    }
    
    public VectorDescriptionControlPanel() {
        this((Vector2d)null);
    }
    
    public VectorDescriptionControlPanel(final Vector2d vector2d) {
        this(vector2d, 2);
    }
    
    public VectorDescriptionControlPanel(final Vector2d vector2d, final String s) {
        this(vector2d, 2, s);
    }
    
    public VectorDescriptionControlPanel(final Vector2d vector2d, final int n) {
        this(vector2d, n, -99);
    }
    
    public VectorDescriptionControlPanel(final Vector2d vector2d, final int n, final String s) {
        this(vector2d, n, -99, s);
    }
    
    public VectorDescriptionControlPanel(final Vector2d vector2d, final int n, final int n2) {
        this(vector2d, n, n2, "v");
    }
    
    public VectorDescriptionControlPanel(final Vector2d vector, final int mode, final int n, final String str) {
        this.scale = 1.0;
        this.hasMaxMagnitude = false;
        this.df = new DecimalFormat("##0.00");
        this.labelFont = MapConstants.vectorDefaultFont;
        this.labelColor = Color.black;
        this.vectorIsActive = true;
        this.commaLabel = new JLabel(",");
        this.closeLabel = new JLabel(")");
        this.xUnitLabel = new JLabel("");
        this.yUnitLabel = new JLabel("");
        this.mUnitLabel = new JLabel("");
        this.degreesLabel = new JLabel("deg");
        this.vector = vector;
        this.mode = mode;
        if (n == -99) {
            this.vPolarLabel = new JLabel(new ImageIcon(ComplexString.createImage("<vector:" + str + "> = (<italic>" + str + "</italic>,<italic><theta></italic>) = (", this.labelColor, this.labelFont)));
            this.vXYLabel = new JLabel(new ImageIcon(ComplexString.createImage("<vector:" + str + "> = (<italic>" + str + "<sub>x</sub></italic>,<italic>" + str + "<sub>y</sub></italic>) = (", this.labelColor, this.labelFont)));
        }
        else {
            this.vPolarLabel = new JLabel(new ImageIcon(ComplexString.createImage("<vector:" + str + "><sub>" + n + "</sub> = (<italic>v</italic>,<italic><theta></italic>) = (", this.labelColor, this.labelFont)));
            this.vXYLabel = new JLabel(new ImageIcon(ComplexString.createImage("<vector:" + str + "><sub>" + n + "</sub> = (<italic>v<sub>x</sub></italic>,<italic>v<sub>y</sub></italic>) = (", this.labelColor, this.labelFont)));
        }
        (this.modeButton = new MapButton()).addActionListener(this);
        this.modeButton.setMinimalInsets();
        this.modeButton.setFocusPainted(false);
        this.xUnitLabel.setForeground(this.labelColor);
        this.xUnitLabel.setFont(this.labelFont);
        this.yUnitLabel.setForeground(this.labelColor);
        this.yUnitLabel.setFont(this.labelFont);
        this.mUnitLabel.setForeground(this.labelColor);
        this.mUnitLabel.setFont(this.labelFont);
        this.commaLabel.setForeground(this.labelColor);
        this.commaLabel.setFont(this.labelFont);
        this.closeLabel.setForeground(this.labelColor);
        this.closeLabel.setFont(this.labelFont);
        this.degreesLabel.setForeground(this.labelColor);
        this.degreesLabel.setFont(this.labelFont);
        (this.xComponentTextField = new JTextField(5)).addActionListener(this);
        this.xComponentTextField.addFocusListener(this);
        (this.yComponentTextField = new JTextField(5)).addActionListener(this);
        this.yComponentTextField.addFocusListener(this);
        (this.magnitudeTextField = new JTextField(4)).addActionListener(this);
        this.magnitudeTextField.addFocusListener(this);
        (this.angleTextField = new JTextField(5)).addActionListener(this);
        this.angleTextField.addFocusListener(this);
        (this.navCoordsComboBox = new JComboBox()).addItem(VectorDescriptionControlPanel.EOFN);
        this.navCoordsComboBox.addItem(VectorDescriptionControlPanel.NOFE);
        this.navCoordsComboBox.addItem(VectorDescriptionControlPanel.SOFE);
        this.navCoordsComboBox.addItem(VectorDescriptionControlPanel.EOFS);
        this.navCoordsComboBox.addItem(VectorDescriptionControlPanel.WOFS);
        this.navCoordsComboBox.addItem(VectorDescriptionControlPanel.SOFW);
        this.navCoordsComboBox.addItem(VectorDescriptionControlPanel.NOFW);
        this.navCoordsComboBox.addItem(VectorDescriptionControlPanel.WOFN);
        this.navCoordsComboBox.addActionListener(this);
        final double[] columnWeights = { 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0 };
        final int[] columnWidths = { 0, 3, 0, 40, 0, 0, 40, 0, 0, 0 };
        final double[] rowWeights = { 1.0 };
        final int[] rowHeights = { 5 };
        this.setLayout(this.gbl = new GridBagLayout());
        this.gbl.rowWeights = rowWeights;
        this.gbl.columnWeights = columnWeights;
        this.gbl.columnWidths = columnWidths;
        this.gbl.rowHeights = rowHeights;
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 2;
        this.add(this.modeButton);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        this.gbl.setConstraints(this.modeButton, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        this.add(this.vXYLabel);
        this.gbl.setConstraints(this.vXYLabel, gridBagConstraints);
        this.add(this.vPolarLabel);
        this.gbl.setConstraints(this.vPolarLabel, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        this.add(this.xComponentTextField);
        this.gbl.setConstraints(this.xComponentTextField, gridBagConstraints);
        this.add(this.magnitudeTextField);
        this.gbl.setConstraints(this.magnitudeTextField, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        this.add(this.xUnitLabel);
        this.gbl.setConstraints(this.xUnitLabel, gridBagConstraints);
        this.add(this.mUnitLabel);
        this.gbl.setConstraints(this.mUnitLabel, gridBagConstraints);
        gridBagConstraints.gridx = 5;
        this.add(this.commaLabel);
        this.gbl.setConstraints(this.commaLabel, gridBagConstraints);
        gridBagConstraints.gridx = 6;
        this.add(this.yComponentTextField);
        this.gbl.setConstraints(this.yComponentTextField, gridBagConstraints);
        this.add(this.angleTextField);
        this.gbl.setConstraints(this.angleTextField, gridBagConstraints);
        gridBagConstraints.gridx = 7;
        this.add(this.degreesLabel);
        this.gbl.setConstraints(this.degreesLabel, gridBagConstraints);
        this.add(this.yUnitLabel);
        this.gbl.setConstraints(this.yUnitLabel, gridBagConstraints);
        gridBagConstraints.gridx = 8;
        this.add(this.navCoordsComboBox);
        this.gbl.setConstraints(this.navCoordsComboBox, gridBagConstraints);
        gridBagConstraints.gridx = 9;
        this.add(this.closeLabel);
        this.gbl.setConstraints(this.closeLabel, gridBagConstraints);
        MapLocaleManager.addLocaleListener(this);
        this.localeChanged();
    }
    
    public void setUnit(final String text) {
        this.xUnitLabel.setText(text);
        this.yUnitLabel.setText(text);
        this.mUnitLabel.setText(text);
    }
    
    public void setVector(final Vector2d vector) {
        this.vector = vector;
    }
    
    public void setVectorDescriptionControlPanelListener(final VectorDescriptionControlPanelListener vectorDescriptionControlPanelListener) {
        this.vectorDescriptionControlPanelListener = vectorDescriptionControlPanelListener;
    }
    
    public void setVectorActivated(final boolean b) {
        if (this.vector != null) {
            this.vector.setVisible(b);
            this.vectorIsActive = b;
        }
        else {
            System.out.println("VectorDescriptionControlPanel.setVectorActivated( " + b + " ):  Error: vector == null");
        }
    }
    
    public boolean isVectorActive() {
        return this.vectorIsActive;
    }
    
    public void setEditable(final boolean editabled) {
        this.setEditabled(editabled);
    }
    
    public void setEditabled(final boolean b) {
        this.xComponentTextField.setEditable(b);
        this.yComponentTextField.setEditable(b);
        this.magnitudeTextField.setEditable(b);
        this.angleTextField.setEditable(b);
    }
    
    public Point2D.Double getVectorHead() {
        return this.vector.getHead2D();
    }
    
    public void setFont(final Font font) {
    }
    
    public void setForegroundColor(final Color color) {
    }
    
    public void setModeButtonVisible(final boolean visible) {
        this.gbl.columnWeights[1] = (visible ? 0.0 : 0.5);
        this.modeButton.setVisible(visible);
    }
    
    private void textFieldChanged() {
        this.setVector();
        if (this.vectorDescriptionControlPanelListener != null) {
            this.vectorDescriptionControlPanelListener.textFieldActionPerformed(this);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.modeButton) {
            this.setMode(++this.mode % 4);
        }
        if (source.equals(this.angleTextField) || source.equals(this.magnitudeTextField) || source.equals(this.xComponentTextField) || source.equals(this.yComponentTextField)) {
            this.textFieldChanged();
        }
        if (source.equals(this.navCoordsComboBox)) {
            this.setOrigin();
            this.setAngleTextField();
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.textFieldChanged();
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    public void setOrigin() {
        if (this.mode == 0) {
            final I18nStringObject originDirection = this.getOriginDirection();
            if (originDirection == VectorDescriptionControlPanel.WOFN || originDirection == VectorDescriptionControlPanel.EOFN) {
                this.angleOfOrigin = 90.0;
            }
            else if (originDirection == VectorDescriptionControlPanel.NOFE || originDirection == VectorDescriptionControlPanel.SOFE) {
                this.angleOfOrigin = 0.0;
            }
            else if (originDirection == VectorDescriptionControlPanel.EOFS || originDirection == VectorDescriptionControlPanel.WOFS) {
                this.angleOfOrigin = 270.0;
            }
            else if (originDirection == VectorDescriptionControlPanel.SOFW || originDirection == VectorDescriptionControlPanel.NOFW) {
                this.angleOfOrigin = 180.0;
            }
        }
        else {
            this.angleOfOrigin = 0.0;
        }
        this.updateTextFields();
        if (this.vectorDescriptionControlPanelListener != null) {
            this.vectorDescriptionControlPanelListener.originChanged(this);
        }
    }
    
    public Vector2d getVector() {
        return this.vector;
    }
    
    public int getMode() {
        return this.mode;
    }
    
    public void resetNavAngleMode() {
        this.navCoordsComboBox.setSelectedItem(VectorDescriptionControlPanel.EOFN);
    }
    
    public void setMode(final int mode) {
        this.mode = mode;
        if (this.mode == 0) {
            MapButtonPropertySetter.setNav(this.modeButton);
        }
        else if (this.mode == 1) {
            MapButtonPropertySetter.setCartesian(this.modeButton);
        }
        else if (this.mode == 2) {
            MapButtonPropertySetter.setPosAngle(this.modeButton);
        }
        else if (this.mode == 3) {
            MapButtonPropertySetter.setPosNegAngle(this.modeButton);
        }
        else {
            System.out.println("VectorDescriptionControlPanel.setMode() ... Illegal mode");
            this.mode = 3;
            MapButtonPropertySetter.setPosNegAngle(this.modeButton);
        }
        final boolean visible = this.mode == 1;
        final boolean visible2 = this.mode == 0;
        this.vXYLabel.setVisible(visible);
        this.xComponentTextField.setVisible(visible);
        this.yComponentTextField.setVisible(visible);
        this.xUnitLabel.setVisible(visible);
        this.yUnitLabel.setVisible(visible);
        this.vPolarLabel.setVisible(!visible);
        this.degreesLabel.setVisible(!visible);
        this.mUnitLabel.setVisible(!visible);
        this.magnitudeTextField.setVisible(!visible);
        this.angleTextField.setVisible(!visible);
        this.navCoordsComboBox.setVisible(visible2);
        this.setOrigin();
        if (this.vectorDescriptionControlPanelListener != null) {
            this.vectorDescriptionControlPanelListener.modeChanged(this);
        }
    }
    
    public void updateTextFields() {
        this.updateTextFields(this.vector);
    }
    
    public void updateTextFields(final Vector2d vector2d) {
        final Point2D.Double head2D = vector2d.getHead2D();
        final Point2D.Double tail2D = vector2d.getTail2D();
        this.xComponentTextField.setText(this.df.format((head2D.x - tail2D.x) / this.scale));
        this.yComponentTextField.setText(this.df.format((tail2D.y - head2D.y) / this.scale));
        this.magnitude = vector2d.getLength();
        this.magnitudeTextField.setText(this.df.format(this.magnitude / this.scale));
        this.setAngleTextField();
    }
    
    public void setAngleTextField() {
        this.calculateAngleOfVectorRelativeToOrigin();
        if (this.mode == 0) {
            final I18nStringObject originDirection = this.getOriginDirection();
            this.angleTextField.setText(this.df.format((originDirection == VectorDescriptionControlPanel.EOFN || originDirection == VectorDescriptionControlPanel.SOFE || originDirection == VectorDescriptionControlPanel.WOFS || originDirection == VectorDescriptionControlPanel.NOFW) ? (-1.0 * this.angleOfVectorRelativeToOrigin) : this.angleOfVectorRelativeToOrigin));
        }
        else {
            this.angleTextField.setText(this.df.format(this.angleOfVectorRelativeToOrigin));
        }
    }
    
    public double getVectorAngleRelativeToOrigin() {
        return this.angleOfVectorRelativeToOrigin;
    }
    
    public double getOriginAngle() {
        return this.angleOfOrigin;
    }
    
    public void fillTextFields(final String text, final String text2, final String text3, final String text4) {
        this.angleTextField.setText(text4);
        this.magnitudeTextField.setText(text3);
        this.xComponentTextField.setText(text);
        this.yComponentTextField.setText(text2);
        this.navCoordsComboBox.setSelectedItem(VectorDescriptionControlPanel.NOFE);
    }
    
    public boolean valuesAreGood() {
        final String text = this.angleTextField.getText();
        final String text2 = this.magnitudeTextField.getText();
        final String text3 = this.xComponentTextField.getText();
        final String text4 = this.yComponentTextField.getText();
        try {
            Double.valueOf(text2);
            Double.valueOf(text);
            Double.valueOf(text3);
            Double.valueOf(text4);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public void shiftVector(final double n, final double n2) {
        final Point2D.Double head2D = this.vector.getHead2D();
        final Point2D.Double tail2D = this.vector.getTail2D();
        this.vector.setHead(head2D.x + n, head2D.y + n2);
        this.vector.setTail(tail2D.x + n, tail2D.y + n2);
    }
    
    public void setVector() {
        final String text = this.angleTextField.getText();
        final String text2 = this.magnitudeTextField.getText();
        final String text3 = this.xComponentTextField.getText();
        final String text4 = this.yComponentTextField.getText();
        double doubleValue;
        double doubleValue2;
        double doubleValue3;
        double doubleValue4;
        try {
            doubleValue = Double.valueOf(text2);
            doubleValue2 = Double.valueOf(text);
            doubleValue3 = Double.valueOf(text3);
            doubleValue4 = Double.valueOf(text4);
        }
        catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        double maxMagnitude = doubleValue * this.scale;
        double n = doubleValue3 * this.scale;
        double maxMagnitude2 = doubleValue4 * this.scale;
        if (this.hasMaxMagnitude) {
            if (this.mode == 2 || this.mode == 3 || this.mode == 0) {
                if (maxMagnitude > this.maxMagnitude) {
                    maxMagnitude = this.maxMagnitude;
                    this.magnitudeTextField.setText(this.df.format(maxMagnitude / this.scale));
                    Toolkit.getDefaultToolkit().beep();
                }
            }
            else {
                final double n2 = this.maxMagnitude * this.maxMagnitude;
                if (n > this.maxMagnitude) {
                    n = this.maxMagnitude;
                    this.xComponentTextField.setText(this.df.format(n / this.scale));
                }
                if (maxMagnitude2 > this.maxMagnitude) {
                    maxMagnitude2 = this.maxMagnitude;
                    this.yComponentTextField.setText(this.df.format(maxMagnitude2 / this.scale));
                }
                if (n * n + maxMagnitude2 * maxMagnitude2 > n2) {
                    n = Math.sqrt(n2 - maxMagnitude2 * maxMagnitude2);
                    this.xComponentTextField.setText(this.df.format(n / this.scale));
                }
            }
        }
        if ((this.mode == 2 || this.mode == 0) && (0.0 > doubleValue2 || doubleValue2 > 360.0)) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        if (this.mode == 3 && (-180.0 > doubleValue2 || doubleValue2 > 180.0)) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        Point2D.Double translatePoint2D;
        if (this.mode == 1) {
            translatePoint2D = new Point2D.Double(this.vector.getTailx() + n, this.vector.getTaily() - maxMagnitude2);
        }
        else {
            this.angleOfVectorRelativeToOrigin = doubleValue2;
            if (this.mode == 0) {
                final I18nStringObject originDirection = this.getOriginDirection();
                if (originDirection == VectorDescriptionControlPanel.EOFN || originDirection == VectorDescriptionControlPanel.SOFE || originDirection == VectorDescriptionControlPanel.WOFS || originDirection == VectorDescriptionControlPanel.NOFW) {
                    this.angleOfVectorRelativeToOrigin = -1.0 * doubleValue2;
                }
            }
            translatePoint2D = VectorUtil.translatePoint2D(this.vector.getTail2D(), (this.angleOfOrigin + this.angleOfVectorRelativeToOrigin) / 180.0 * 3.141592653589793, maxMagnitude);
        }
        final double n3 = translatePoint2D.x - this.vector.getHeadx();
        final double n4 = translatePoint2D.y - this.vector.getHeady();
        this.vector.setHead(translatePoint2D);
        if (this.vectorDescriptionControlPanelListener != null) {
            this.vectorDescriptionControlPanelListener.vectorChanged(this, n3, n4);
        }
    }
    
    public void localeChanged() {
        try {
            final ResourceBundle resourceBundle = PropertyManager.getResourceBundle("ca.ucalgary.phas.map.utilities.UtilitiesProperties", MapLocaleManager.getLocale());
            VectorDescriptionControlPanel.WOFN.set(resourceBundle.getString("VectorDescriptionControlPanel_WOFN_string"));
            VectorDescriptionControlPanel.EOFN.set(resourceBundle.getString("VectorDescriptionControlPanel_EOFN_string"));
            VectorDescriptionControlPanel.NOFE.set(resourceBundle.getString("VectorDescriptionControlPanel_NOFE_string"));
            VectorDescriptionControlPanel.SOFE.set(resourceBundle.getString("VectorDescriptionControlPanel_SOFE_string"));
            VectorDescriptionControlPanel.EOFS.set(resourceBundle.getString("VectorDescriptionControlPanel_EOFS_string"));
            VectorDescriptionControlPanel.WOFS.set(resourceBundle.getString("VectorDescriptionControlPanel_WOFS_string"));
            VectorDescriptionControlPanel.SOFW.set(resourceBundle.getString("VectorDescriptionControlPanel_SOFW_string"));
            VectorDescriptionControlPanel.NOFW.set(resourceBundle.getString("VectorDescriptionControlPanel_NOFW_string"));
        }
        catch (Exception ex) {
            System.out.println("VectorDescriptionControlPanel.localeChanged() Error looking for some property. Check the property file for the key below");
            ex.printStackTrace();
        }
    }
    
    public I18nStringObject getOriginDirection() {
        return (I18nStringObject)this.navCoordsComboBox.getSelectedItem();
    }
    
    public void calculateAngleOfVectorRelativeToOrigin() {
        double angleOfVectorRelativeToOrigin = this.vector.getDirection() / 3.141592653589793 * 180.0;
        if (this.vector.getLength() == 0.0) {
            angleOfVectorRelativeToOrigin = 0.0;
        }
        if (this.mode == 2 && angleOfVectorRelativeToOrigin < 0.0) {
            angleOfVectorRelativeToOrigin += 360.0;
        }
        if (this.mode == 0) {
            final I18nStringObject originDirection = this.getOriginDirection();
            if (angleOfVectorRelativeToOrigin < 0.0) {
                angleOfVectorRelativeToOrigin += 360.0;
            }
            if (originDirection == VectorDescriptionControlPanel.WOFN) {
                angleOfVectorRelativeToOrigin -= 90.0;
            }
            else if (originDirection == VectorDescriptionControlPanel.EOFN) {
                angleOfVectorRelativeToOrigin = 90.0 - angleOfVectorRelativeToOrigin;
            }
            else if (originDirection != VectorDescriptionControlPanel.NOFE) {
                if (originDirection == VectorDescriptionControlPanel.SOFE) {
                    angleOfVectorRelativeToOrigin = 360.0 - angleOfVectorRelativeToOrigin;
                }
                else if (originDirection == VectorDescriptionControlPanel.EOFS) {
                    angleOfVectorRelativeToOrigin -= 270.0;
                }
                else if (originDirection == VectorDescriptionControlPanel.WOFS) {
                    angleOfVectorRelativeToOrigin = 270.0 - angleOfVectorRelativeToOrigin;
                }
                else if (originDirection == VectorDescriptionControlPanel.SOFW) {
                    angleOfVectorRelativeToOrigin -= 180.0;
                }
                else if (originDirection == VectorDescriptionControlPanel.NOFW) {
                    angleOfVectorRelativeToOrigin = 180.0 - angleOfVectorRelativeToOrigin;
                }
            }
            if (angleOfVectorRelativeToOrigin < 0.0) {
                angleOfVectorRelativeToOrigin += 360.0;
            }
            if (originDirection == VectorDescriptionControlPanel.EOFN || originDirection == VectorDescriptionControlPanel.SOFE || originDirection == VectorDescriptionControlPanel.WOFS || originDirection == VectorDescriptionControlPanel.NOFW) {
                angleOfVectorRelativeToOrigin *= -1.0;
            }
            if (-0.01 < angleOfVectorRelativeToOrigin - 360.0 && angleOfVectorRelativeToOrigin - 360.0 < 0.01) {
                angleOfVectorRelativeToOrigin = 0.0;
            }
        }
        this.angleOfVectorRelativeToOrigin = angleOfVectorRelativeToOrigin;
    }
    
    static {
        VectorDescriptionControlPanel.WOFN = new I18nStringObject();
        VectorDescriptionControlPanel.EOFN = new I18nStringObject();
        VectorDescriptionControlPanel.NOFE = new I18nStringObject();
        VectorDescriptionControlPanel.SOFE = new I18nStringObject();
        VectorDescriptionControlPanel.EOFS = new I18nStringObject();
        VectorDescriptionControlPanel.WOFS = new I18nStringObject();
        VectorDescriptionControlPanel.SOFW = new I18nStringObject();
        VectorDescriptionControlPanel.NOFW = new I18nStringObject();
    }
}
