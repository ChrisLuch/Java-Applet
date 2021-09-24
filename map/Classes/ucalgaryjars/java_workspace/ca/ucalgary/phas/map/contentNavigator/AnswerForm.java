// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.contentNavigator;

import javax.swing.JComponent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import javax.swing.ButtonModel;
import java.util.Enumeration;
import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import ca.ucalgary.phas.map.utilities.MapRadioButton;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.applet.Applet;
import ca.ucalgary.phas.map.utilities.ParameterUtils;
import ca.ucalgary.phas.map.utilities.MapConstants;
import ca.ucalgary.phas.map.utilities.NumberField;
import javax.swing.ButtonGroup;
import java.util.Vector;
import ca.ucalgary.phas.map.utilities.MapButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JApplet;

public class AnswerForm extends JApplet implements ItemListener, ActionListener
{
    private static final String MULTIPLEPAGEURLSTRING = "*MLTPLPGE*";
    private static final int MULTI_CHOICE = 1;
    private static final int NUMERICAL = 2;
    private URL baseURL;
    private Color bgColor;
    private JPanel checkBoxPanel;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;
    private static final String SUBMIT_ACTION_COMMAND = "submitxxxxB";
    private static final String BACK_ACTION_COMMAND = "backxxxxB";
    private String submitButtonText;
    private String backButtonText;
    private String copoutButtonText;
    private MapButton submitButton;
    private MapButton copoutButton;
    private Vector checkBoxVector;
    private ButtonGroup bgroup;
    private NumberField numEntryField;
    private String NumericalEntryFieldFormatString;
    private int type;
    private Vector responseVector;
    private AnswerFormResponse otherwiseResponse;
    private URL questionURL;
    private boolean usesBaseURL;
    private boolean copoutIsVisable;
    private double numericalAnswer;
    private String questionFrameName;
    
    public AnswerForm() {
        this.bgroup = new ButtonGroup();
        this.type = 1;
        this.usesBaseURL = true;
        this.copoutIsVisable = true;
    }
    
    public void init() {
        this.bgColor = MapConstants.trackerBorderColor;
        this.submitButtonText = ParameterUtils.getStringParameter((Applet)this, "SUBMIT_BUTTON_TEXT", "Submit");
        this.backButtonText = ParameterUtils.getStringParameter((Applet)this, "BACK_BUTTON_TEXT", "Back To The Question");
        this.copoutButtonText = ParameterUtils.getStringParameter((Applet)this, "GIVEUP_BUTTON_TEXT", "Give Up");
        this.questionFrameName = ParameterUtils.getStringParameter((Applet)this, "QUESTION_FRAME_NAME", "questarea");
        this.copoutIsVisable = ParameterUtils.getBooleanParameter((Applet)this, "GIVEUP_IS_VISIBLE", true);
        final String stringParameter = ParameterUtils.getStringParameter((Applet)this, "BASE_URL_RELATIVE_TO_DOC", "");
        try {
            this.baseURL = new URL(this.getDocumentBase(), stringParameter);
        }
        catch (MalformedURLException ex) {
            System.out.println("Error --- \"BASE_URL_RELATIVE_TO_DOC\" parameter has error");
            ex.printStackTrace();
        }
        final String stringParameter2 = ParameterUtils.getStringParameter((Applet)this, "QUESTION_URL", "");
        try {
            this.questionURL = new URL(this.getDocumentBase(), stringParameter2);
        }
        catch (MalformedURLException ex2) {
            System.out.println("Error --- \"QUESTION_URL\" parameter has error");
            ex2.printStackTrace();
        }
        final Vector<String> vector = new Vector<String>();
        vector.addElement("numerical");
        vector.addElement("multi_choice");
        vector.addElement("true_false");
        final String stringParameter3 = ParameterUtils.getStringParameter((Applet)this, "QUESTION_TYPE", (Vector)vector, "multi_choice");
        StringTokenizer stringTokenizer;
        if (stringParameter3.equalsIgnoreCase("true_false")) {
            this.type = 1;
            stringTokenizer = new StringTokenizer("True,False", ",");
        }
        else {
            if (stringParameter3.equalsIgnoreCase("numerical")) {
                this.NumericalEntryFieldFormatString = ParameterUtils.getStringParameter((Applet)this, "NUMERICAL_ENTRY_FIELD_FORMAT", "0.00E0");
                this.type = 2;
            }
            else {
                this.type = 1;
            }
            stringTokenizer = new StringTokenizer(ParameterUtils.getStringParameter((Applet)this, "RESPONSE_NAMES", ""), ",");
        }
        this.responseVector = new Vector();
        while (stringTokenizer.hasMoreElements()) {
            final String nextToken = stringTokenizer.nextToken();
            final boolean booleanParameter = ParameterUtils.getBooleanParameter((Applet)this, nextToken + "_CORRECT", false);
            final boolean booleanParameter2 = ParameterUtils.getBooleanParameter((Applet)this, nextToken + "_IS_MULTI_PAGE", false);
            double doubleParameter = 0.0;
            double doubleParameter2 = 0.0;
            double doubleParameter3 = 0.0;
            if (this.type == 2) {
                doubleParameter = ParameterUtils.getDoubleParameter((Applet)this, nextToken + "_VALUE", "0.00");
                doubleParameter2 = ParameterUtils.getDoubleParameter((Applet)this, nextToken + "_LOW_VALUE", "0.00");
                doubleParameter3 = ParameterUtils.getDoubleParameter((Applet)this, nextToken + "_HIGH_VALUE", "0.00");
            }
            this.responseVector.addElement(new AnswerFormResponse(booleanParameter2 ? "*MLTPLPGE*" : ParameterUtils.getStringParameter((Applet)this, nextToken + "_RESPONSE_URL", ""), nextToken, booleanParameter, doubleParameter2, doubleParameter3, doubleParameter));
        }
        if (this.type == 2) {
            this.otherwiseResponse = new AnswerFormResponse(ParameterUtils.getStringParameter((Applet)this, "OTHERWISE_RESPONSE_URL", ""), "Otherwise", false);
        }
        this.setBackground(this.bgColor);
        this.getContentPane().setBackground(this.bgColor);
        (this.checkBoxPanel = new JPanel()).setBackground(this.bgColor);
        this.checkBoxPanel.setLayout(new FlowLayout(1, 3, 0));
        this.checkBoxVector = new Vector();
        if (this.type == 1) {
            final Enumeration<AnswerFormResponse> elements = (Enumeration<AnswerFormResponse>)this.responseVector.elements();
            while (elements.hasMoreElements()) {
                final MapRadioButton comp = new MapRadioButton(elements.nextElement().getResponseLabelString(), false);
                ((JComponent)comp).setBackground(this.bgColor);
                ((JComponent)comp).setBorder(new LineBorder(MapConstants.trackerBorderColor, 2));
                this.bgroup.add((AbstractButton)comp);
                this.checkBoxVector.addElement(comp);
                this.checkBoxPanel.add((Component)comp);
                ((AbstractButton)comp).addItemListener(this);
            }
        }
        else {
            ((JComponent)(this.numEntryField = new NumberField(this.NumericalEntryFieldFormatString))).setBackground(this.bgColor);
        }
        (this.submitButton = new MapButton(this.submitButtonText)).setBackground(this.bgColor);
        ((AbstractButton)this.submitButton).addActionListener(this);
        (this.copoutButton = new MapButton(this.copoutButtonText)).setBackground(this.bgColor);
        ((AbstractButton)this.copoutButton).addActionListener(this);
        if (this.type == 2) {
            ((AbstractButton)this.submitButton).setEnabled(true);
        }
        else {
            ((AbstractButton)this.submitButton).setEnabled(false);
        }
        final double[] columnWeights = { 0.49, 0.01, 0.01, 0.0, 0.0, 0.0, 0.0, 0.49 };
        final int[] columnWidths = { 0, 0, 0, 5, 0, 5, 0, 0 };
        final double[] rowWeights = { 1.0 };
        final int[] rowHeights = { 10 };
        this.gridBagLayout = new GridBagLayout();
        this.getContentPane().setLayout(this.gridBagLayout);
        this.gridBagLayout.rowWeights = rowWeights;
        this.gridBagLayout.columnWeights = columnWeights;
        this.gridBagLayout.columnWidths = columnWidths;
        this.gridBagLayout.rowHeights = rowHeights;
        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.fill = 1;
        this.gridBagConstraints.gridx = 2;
        this.gridBagConstraints.gridy = 0;
        if (this.type == 1) {
            this.getContentPane().add(this.checkBoxPanel);
            this.gridBagLayout.setConstraints(this.checkBoxPanel, this.gridBagConstraints);
        }
        else {
            this.getContentPane().add((Component)this.numEntryField);
            this.gridBagLayout.setConstraints((Component)this.numEntryField, this.gridBagConstraints);
        }
        this.gridBagConstraints.gridx = 4;
        this.getContentPane().add((Component)this.submitButton);
        this.gridBagLayout.setConstraints((Component)this.submitButton, this.gridBagConstraints);
        if (this.copoutIsVisable) {
            this.gridBagConstraints.gridx = 6;
            this.getContentPane().add((Component)this.copoutButton);
            this.gridBagLayout.setConstraints((Component)this.copoutButton, this.gridBagConstraints);
        }
        this.showQuestion();
        this.repaint();
    }
    
    private int getSelectedIndex() {
        final ButtonModel selection = this.bgroup.getSelection();
        final Enumeration<MapRadioButton> elements = (Enumeration<MapRadioButton>)this.checkBoxVector.elements();
        while (elements.hasMoreElements()) {
            final MapRadioButton o = elements.nextElement();
            if (((AbstractButton)o).getModel().equals(selection)) {
                return this.checkBoxVector.indexOf(o);
            }
        }
        return 0;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("submitxxxxB")) {
            ((AbstractButton)this.submitButton).setActionCommand("backxxxxB");
            ((AbstractButton)this.submitButton).setText(this.backButtonText);
            this.setEntryAreaEnabled(false);
            AnswerFormResponse otherwiseResponse = null;
            if (this.type == 2) {
                final double doubleValue = this.numEntryField.getDoubleValue();
                final Enumeration<AnswerFormResponse> elements = this.responseVector.elements();
                while (elements.hasMoreElements()) {
                    final AnswerFormResponse answerFormResponse = elements.nextElement();
                    if (doubleValue >= answerFormResponse.getLowerBound() && doubleValue <= answerFormResponse.getUpperBound()) {
                        otherwiseResponse = answerFormResponse;
                        break;
                    }
                }
                if (otherwiseResponse == null) {
                    otherwiseResponse = this.otherwiseResponse;
                }
            }
            else {
                otherwiseResponse = this.responseVector.elementAt(this.getSelectedIndex());
            }
            if (otherwiseResponse.getResponseURLString().equals("*MLTPLPGE*")) {
                final PageBrowserPanel initPanel = PageBrowserPanel.initPanel(this, otherwiseResponse.getResponseLabelString() + "_");
                this.gridBagConstraints.gridx = 1;
                this.gridBagConstraints.gridy = 0;
                this.getContentPane().add(initPanel);
                this.gridBagLayout.setConstraints(initPanel, this.gridBagConstraints);
                initPanel.setBackground(this.bgColor);
            }
            else {
                try {
                    if (this.usesBaseURL) {
                        this.showContent(new URL(this.baseURL, otherwiseResponse.getResponseURLString()));
                    }
                    else {
                        this.showContent(new URL(otherwiseResponse.getResponseURLString()));
                    }
                }
                catch (MalformedURLException ex) {
                    System.out.println(this.baseURL + "  +  " + otherwiseResponse.getResponseURLString());
                }
                if (otherwiseResponse.isCorrect()) {}
            }
        }
        else if (actionEvent.getActionCommand().equals("backxxxxB")) {
            this.showQuestion();
            if (PageBrowserPanel.getPanel() != null) {
                this.getContentPane().remove(PageBrowserPanel.getPanel());
                PageBrowserPanel.deletePanel();
                this.checkBoxPanel.setVisible(true);
                ((JComponent)this.copoutButton).setVisible(true);
                this.repaint();
            }
        }
        else if (actionEvent.getSource().equals(this.copoutButton)) {
            final Enumeration<AnswerFormResponse> elements2 = this.responseVector.elements();
            while (elements2.hasMoreElements()) {
                final AnswerFormResponse o = elements2.nextElement();
                if (o.isCorrect()) {
                    if (this.type == 2) {
                        this.numEntryField.setValue(o.getNumericalValue());
                        this.checkBoxPanel.setBackground(Color.red);
                    }
                    else {
                        ((MapRadioButton)this.checkBoxVector.elementAt(this.responseVector.indexOf(o))).enableHighLight(true);
                    }
                }
            }
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        ((AbstractButton)this.submitButton).setEnabled(true);
    }
    
    private void setEntryAreaEnabled(final boolean enabled) {
        ((AbstractButton)this.copoutButton).setEnabled(enabled);
        if (this.type == 2) {
            this.numEntryField.setEnabled(enabled);
        }
        else {
            for (int componentCount = this.checkBoxPanel.getComponentCount(), i = 0; i < componentCount; ++i) {
                this.checkBoxPanel.getComponent(i).setEnabled(enabled);
            }
        }
    }
    
    private void showContent(final URL url) {
        this.getAppletContext().showDocument(url, this.questionFrameName);
    }
    
    private void showQuestion() {
        ((AbstractButton)this.submitButton).setActionCommand("submitxxxxB");
        ((AbstractButton)this.submitButton).setText(this.submitButtonText);
        this.setEntryAreaEnabled(true);
        this.showContent(this.questionURL);
    }
    
    public void start() {
        this.repaint();
    }
}
