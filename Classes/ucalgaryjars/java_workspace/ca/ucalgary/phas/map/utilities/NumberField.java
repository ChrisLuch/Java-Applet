// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import javax.swing.event.CaretEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.AWTEventMulticaster;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JScrollBar;
import java.awt.Font;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import javax.swing.event.CaretListener;
import java.awt.event.AdjustmentListener;
import javax.swing.JComponent;

public class NumberField extends JComponent implements AdjustmentListener, CaretListener, KeyListener, FocusListener
{
    private static final int NONE = -1;
    private String posInfinityText;
    private String invalidText;
    private String negInfinityText;
    private String NaNText;
    private String dialogText;
    private String numString;
    private double maxNumber;
    private double minNumber;
    private int mantissa;
    private int decimal;
    private int mantissaEnd;
    private int exponent;
    private int exponentEnd;
    private int minExp;
    private int maxExp;
    private boolean allowInfinite;
    private boolean hasMantissaSign;
    private boolean hasExponentSign;
    private boolean hasMin;
    private Font font;
    private JNumField numberField;
    private JScrollBar scrollBar;
    private int lastCaret;
    private boolean caretListen;
    private boolean spinnerListen;
    private ActionListener actionListener;
    
    public NumberField() {
        this("0");
    }
    
    public NumberField(final String format) {
        this.posInfinityText = "\u221e";
        this.invalidText = "error";
        this.negInfinityText = "- \u221e";
        this.NaNText = "NaN";
        this.font = new Font("dialoginput", 0, 12);
        this.scrollBar = new JScrollBar();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final double[] columnWeights = { 1.0, 0.0 };
        final int[] columnWidths = { 30, 5 };
        final double[] rowWeights = { 1.0 };
        final int[] rowHeights = { 10 };
        layout.columnWeights = columnWeights;
        layout.columnWidths = columnWidths;
        layout.rowWeights = rowWeights;
        layout.rowHeights = rowHeights;
        (this.numberField = new JNumField()).setHorizontalAlignment(4);
        this.numberField.setFont(this.font);
        this.numberField.setSelectedTextColor(Color.white);
        this.numberField.setSelectionColor(Color.black);
        this.setFormat(format);
        this.numberField.setCaretPosition(this.mantissaEnd - 1);
        this.scrollBar.setValue(2);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        this.add(this.numberField);
        gridBagConstraints.fill = 2;
        layout.setConstraints(this.numberField, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = 3;
        this.add(this.scrollBar);
        layout.setConstraints(this.scrollBar, gridBagConstraints);
        this.numberField.addCaretListener(this);
        this.numberField.addKeyListener(this);
        this.numberField.addFocusListener(this);
        this.scrollBar.addAdjustmentListener(this);
    }
    
    public void addActionListener(final ActionListener b) {
        if (b == null) {
            return;
        }
        this.actionListener = AWTEventMulticaster.add(this.actionListener, b);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (!this.spinnerListen) {
            return;
        }
        boolean b = adjustmentEvent.getValue() < 2;
        this.spinnerListen = false;
        this.scrollBar.setValue(2);
        this.spinnerListen = true;
        if (this.dialogText != null && !b) {
            this.setDialog(null);
            this.numberField.requestFocus();
            this.sendAction();
            return;
        }
        long n = 0L;
        int n2 = 0;
        if (!this.numberField.hasFocus()) {
            this.numberField.requestFocus();
            return;
        }
        int caretPosition = this.numberField.getCaretPosition();
        String original = this.numString;
        final int n3 = (caretPosition > this.mantissaEnd) ? this.exponent : this.mantissa;
        final int n4 = (caretPosition > this.mantissaEnd) ? (this.hasExponentSign ? -1 : 0) : (this.hasMantissaSign ? -1 : 0);
        if (this.numberField.getSelectionStart() == n3 && n3 != this.numberField.getSelectionEnd()) {
            --caretPosition;
        }
        else if (caretPosition == this.mantissaEnd || caretPosition == this.exponentEnd) {
            --caretPosition;
        }
        else if (n4 != 0 && n3 == caretPosition) {
            ++caretPosition;
        }
        for (int i = n3 - n4; i <= caretPosition; ++i) {
            if (i != this.decimal) {
                n = n * 10L + (original.charAt(i) - '0');
                ++n2;
            }
        }
        if (n4 != 0 && original.charAt(n3) == '-') {
            b = !b;
        }
        long n5;
        if (b) {
            n5 = n + 1L;
            if (n5 == Math.pow(10.0, n2) || (n5 > this.maxExp && n3 > this.mantissaEnd)) {
                --n5;
                if (this.allowInfinite && n3 > this.mantissaEnd && (!this.hasExponentSign || original.charAt(n3) == '+')) {
                    if (this.hasMantissaSign && original.charAt(this.mantissa) == '-') {
                        this.setDialog(new Double(Double.NEGATIVE_INFINITY));
                    }
                    else {
                        this.setDialog(new Double(Double.POSITIVE_INFINITY));
                    }
                    this.sendAction();
                    return;
                }
            }
        }
        else {
            n5 = n - 1L;
            if (n5 == 0L) {
                original = this.insert(n3, original, "+");
            }
            if (n5 == -1L) {
                if (n4 != 0) {
                    original = this.insert(n3, original, (original.charAt(n3) == '+') ? "-" : "+");
                    n5 = 1L;
                }
                else {
                    n5 = 0L;
                }
            }
        }
        for (int j = n3 - n4; j <= caretPosition; ++j) {
            if (j != this.decimal) {
                --n2;
                original = this.insert(j, original, "" + (int)Math.floor(n5 / Math.pow(10.0, n2)) % 10);
            }
        }
        this.caretListen = false;
        if (this.isInRange(original)) {
            this.numString = new String(original);
            this.numberField.setText(this.numString);
            this.sendAction();
        }
        this.numberField.setSelectionStart(n3);
        this.numberField.setSelectionEnd(caretPosition + 1);
        this.caretListen = true;
    }
    
    public void caretUpdate(final CaretEvent caretEvent) {
        if (!this.caretListen) {
            return;
        }
        this.caretListen = false;
        if (!this.numberField.hasFocus()) {
            this.numberField.setCaretPosition(this.lastCaret);
            this.caretListen = true;
            return;
        }
        final int caretPosition = this.numberField.getCaretPosition();
        if (this.numberField.getSelectionStart() != this.numberField.getSelectionEnd()) {
            this.numberField.setCaretPosition(caretPosition);
        }
        if (caretPosition < this.mantissa) {
            this.numberField.setCaretPosition(this.mantissa);
        }
        else {
            final int caretPosition2 = (this.exponent == -1) ? this.mantissaEnd : this.exponentEnd;
            if (caretPosition > caretPosition2) {
                this.numberField.setCaretPosition(caretPosition2);
            }
            else if (caretPosition < this.numString.length() && (this.numString.charAt(caretPosition) == 'E' || this.numString.charAt(caretPosition) == '.')) {
                if (this.lastCaret < caretPosition) {
                    this.numberField.setCaretPosition(caretPosition + 1);
                }
                else {
                    this.numberField.setCaretPosition(caretPosition - 1);
                }
            }
        }
        this.lastCaret = this.numberField.getCaretPosition();
        this.caretListen = true;
    }
    
    public static String doubleToString(final double n, final int n2) {
        return doubleToString(n, n2, -360);
    }
    
    public static String doubleToString(final double a, int n, final int n2) {
        String str = "";
        if (a == 0.0) {
            return "0";
        }
        if (a < 0.0) {
            str = "-";
        }
        if (Double.isNaN(a)) {
            return "?";
        }
        if (a == Double.POSITIVE_INFINITY) {
            return "infinity";
        }
        if (a == Double.NEGATIVE_INFINITY) {
            return "-infinity";
        }
        int n3 = (int)Math.floor(Math.log(Math.abs(a)) / Math.log(10.0) + 1.0E-13);
        double n4 = Math.abs(a) / Math.pow(10.0, n3);
        if (n4 < 1.0) {
            n4 *= 10.0;
            --n3;
        }
        int n5 = (int)Math.round(n4 * Math.pow(10.0, n - 1));
        if (n5 == Math.round(Math.pow(10.0, n))) {
            n5 /= 10;
            ++n3;
        }
        while (n3 < n2 && n != 0) {
            --n3;
            n5 = (int)Math.round(n5 / 10.0);
            --n;
        }
        if (n == 0) {
            return "0";
        }
        if (n3 <= 3 && n3 >= -2) {
            return str + standardDisplay(n5, n3, n);
        }
        if (n3 >= 0) {
            return str + ("" + n5).charAt(0) + "." + ("" + n5).substring(1) + "E+" + n3;
        }
        return str + ("" + n5).charAt(0) + "." + ("" + n5).substring(1) + "E" + n3;
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.caretListen = false;
        int caretPosition = this.numberField.getCaretPosition();
        this.numberField.setText(this.numString);
        if (this.numString.length() <= caretPosition) {
            caretPosition = this.lastCaret;
        }
        if (caretPosition < this.mantissa || caretPosition > ((this.exponent != -1) ? this.exponentEnd : this.mantissaEnd)) {
            caretPosition = this.mantissaEnd;
        }
        this.numberField.setCaretPosition(caretPosition);
        this.caretListen = true;
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        if (this.dialogText != null) {
            return;
        }
        String text = this.numString;
        for (int n = ((this.decimal != -1) ? this.decimal : this.mantissaEnd) - 1, i = this.mantissa; i < n; ++i) {
            if (this.numString.charAt(i) == '+' || this.numString.charAt(i) == '0') {
                text = this.insert(i, text, " ");
            }
            else if (this.numString.charAt(i) != '-') {
                break;
            }
        }
        this.lastCaret = this.numberField.getCaretPosition();
        if (this.numberField.getSelectionStart() != this.numberField.getSelectionEnd()) {
            --this.lastCaret;
        }
        this.caretListen = false;
        this.numberField.setText(text);
        this.caretListen = true;
    }
    
    public double getDoubleValue() {
        return this.getDoubleValue(this.numString);
    }
    
    private double getDoubleValue(final String s) {
        if (this.dialogText != null) {
            if (this.dialogText.equals(this.posInfinityText)) {
                return Double.POSITIVE_INFINITY;
            }
            if (this.dialogText.equals(this.negInfinityText)) {
                return Double.NEGATIVE_INFINITY;
            }
            if (this.dialogText.equals(this.NaNText) || this.dialogText.equals(this.invalidText)) {
                return Double.NaN;
            }
        }
        try {
            return Double.valueOf(s.substring(this.mantissa, (this.exponent != -1) ? this.exponentEnd : this.mantissaEnd));
        }
        catch (NumberFormatException ex) {
            return Double.NaN;
        }
    }
    
    public long getLongValue() throws NumberFormatException {
        if (this.dialogText != null) {
            if (this.dialogText.equals(this.posInfinityText)) {
                return Long.MAX_VALUE;
            }
            if (this.dialogText.equals(this.negInfinityText)) {
                return Long.MIN_VALUE;
            }
            if (this.dialogText.equals(this.NaNText)) {
                return 0L;
            }
        }
        try {
            return Long.valueOf(this.numString.substring(this.mantissa, (this.exponent != -1) ? this.exponentEnd : this.mantissaEnd));
        }
        catch (NumberFormatException ex) {
            throw new NumberFormatException();
        }
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.numberField.getPreferredSize().width + this.scrollBar.getPreferredSize().width + 3, this.numberField.getPreferredSize().height);
    }
    
    public String getText() {
        return this.numberField.getText();
    }
    
    private String insert(final int endIndex, final String s, final String str) {
        return s.substring(0, endIndex) + str + s.substring(endIndex + str.length());
    }
    
    public boolean isEnabled() {
        return this.numberField.isEditable();
    }
    
    private boolean isInRange(final String s) {
        final double doubleValue = this.getDoubleValue(s);
        if (doubleValue < this.minNumber) {
            return false;
        }
        if (doubleValue > this.maxNumber) {
            return false;
        }
        if (this.exponent != -1) {
            final int intValue = Double.valueOf(s.substring(this.exponent, this.exponentEnd)).intValue();
            if (intValue < this.minExp || intValue > this.maxExp) {
                return false;
            }
        }
        return true;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (!this.numberField.isEditable()) {
            return;
        }
        int caretPosition = this.numberField.getCaretPosition();
        final char keyChar = keyEvent.getKeyChar();
        int char1 = 0;
        String numString = null;
        if (this.numberField.getSelectionStart() != this.numberField.getSelectionEnd()) {
            final int selectionStart = this.numberField.getSelectionStart();
            this.lastCaret = selectionStart;
            caretPosition = selectionStart;
        }
        keyEvent.setKeyChar('\0');
        if (caretPosition != this.numString.length()) {
            char1 = this.numString.charAt(caretPosition);
        }
        if ((keyChar == '-' || keyChar == '+') && (char1 == 45 || char1 == 43)) {
            numString = this.numString.substring(0, caretPosition) + keyChar + this.numString.substring(caretPosition + 1);
        }
        else if (keyChar >= '0' && keyChar <= '9' && char1 >= 48 && char1 <= 57) {
            numString = this.numString.substring(0, caretPosition) + keyChar + this.numString.substring(caretPosition + 1);
        }
        if (numString != null && this.isInRange(numString)) {
            this.numString = numString;
            this.sendAction();
            ++caretPosition;
        }
        this.caretListen = false;
        this.numberField.setText(this.numString);
        this.caretListen = true;
        this.numberField.setCaretPosition(caretPosition);
    }
    
    public void removeActionListener(final ActionListener oldl) {
        if (this.actionListener != null) {
            AWTEventMulticaster.remove(this.actionListener, oldl);
            this.actionListener = null;
        }
    }
    
    public static double round(final double n, final int n2) {
        if (n == 0.0) {
            return 0.0;
        }
        int n3 = (int)Math.floor(Math.log(Math.abs(n)) / Math.log(10.0) + 1.0E-13);
        double n4 = Math.abs(n) / Math.pow(10.0, n3);
        if (n4 < 1.0) {
            n4 *= 10.0;
            --n3;
        }
        int n5 = (int)Math.round(n4 * Math.pow(10.0, n2 - 1));
        if (n5 == Math.round(Math.pow(10.0, n2))) {
            n5 /= 10;
            ++n3;
        }
        if (n < 0.0) {
            return -1 * n5 * Math.pow(10.0, n3 - n2 + 1);
        }
        return n5 * Math.pow(10.0, n3 - n2 + 1);
    }
    
    private void sendAction() {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 1001, "select"));
        }
    }
    
    private void setDialog(final Double n) {
        if (n == null) {
            this.dialogText = null;
            if (this.isEnabled()) {
                this.scrollBar.setEnabled(true);
            }
            this.numberField.setFocusEnabled(true);
            return;
        }
        if (n.isNaN()) {
            this.dialogText = this.NaNText;
        }
        else if (n == Double.POSITIVE_INFINITY) {
            this.dialogText = this.posInfinityText;
        }
        else if (n == Double.NEGATIVE_INFINITY) {
            this.dialogText = this.negInfinityText;
        }
        else {
            this.dialogText = this.invalidText;
        }
        if (!n.isInfinite()) {
            this.scrollBar.setEnabled(false);
        }
        int n2;
        String s;
        for (n2 = ((this.exponent != -1) ? this.exponentEnd : this.mantissaEnd) - this.mantissa, s = this.dialogText; s.length() < n2; s = " " + s) {}
        final String string = this.numString.substring(0, this.mantissa) + s + this.numString.substring((this.exponent != -1) ? this.exponentEnd : this.mantissaEnd);
        this.numberField.setFocusEnabled(false);
        this.caretListen = false;
        this.numberField.setText(string);
        if (this.numberField.hasFocus()) {
            this.scrollBar.requestFocus();
        }
        this.caretListen = true;
    }
    
    public void setEnabled(final boolean enabled) {
        this.numberField.setFocusEnabled(enabled);
        this.numberField.setEditable(enabled);
        this.scrollBar.setEnabled(enabled);
        if (enabled) {
            this.numberField.setBackground(Color.white);
            if (this.numberField.hasFocus()) {
                this.focusGained(null);
            }
        }
        else {
            this.numberField.setBackground(Color.lightGray);
        }
        this.repaint();
    }
    
    public void setFormat(String s) {
        String string = "0";
        String string2 = "0";
        this.numString = "";
        this.dialogText = null;
        this.allowInfinite = false;
        final boolean b = true;
        this.caretListen = b;
        this.spinnerListen = b;
        int i = 0;
        int length = s.length();
        int n = 1;
        for (int j = 0; j < length; ++j) {
            if (n != 0 && s.charAt(j) == ' ') {
                s = s.substring(0, j) + s.substring(j + 1);
                --length;
            }
            else if (s.charAt(j) == '<') {
                n = 0;
            }
            else if (s.charAt(j) == '>') {
                n = 1;
            }
        }
        if (i < length && s.charAt(0) == '<') {
            i = s.indexOf(62);
            this.numString += s.substring(1, i);
            ++i;
        }
        this.mantissa = this.numString.length();
        if (i < length) {
            final char char1 = s.charAt(i);
            if (char1 == '-' || char1 == '+') {
                this.hasMantissaSign = true;
                this.numString += char1;
                ++i;
            }
            else {
                this.hasMantissaSign = false;
            }
        }
        this.decimal = -1;
        while (i < length) {
            final char char2 = s.charAt(i);
            if (char2 != '.' && (char2 < '0' || char2 > '9')) {
                break;
            }
            if (char2 == '.') {
                this.decimal = this.numString.length();
            }
            string += (char)((char2 == '.') ? 46 : 57);
            this.numString += char2;
            ++i;
        }
        this.mantissaEnd = this.numString.length();
        if (i < length && Character.toUpperCase(s.charAt(i)) == 'E') {
            this.numString += 'E';
            if (++i < length) {
                final char char3 = s.charAt(i);
                if (char3 == '-' || char3 == '+') {
                    this.exponent = this.numString.length();
                    this.hasExponentSign = true;
                    this.numString += char3;
                    ++i;
                }
                else {
                    this.hasExponentSign = false;
                }
            }
            if (!this.hasExponentSign) {
                this.exponent = this.numString.length();
            }
            while (i < length) {
                final char char4 = s.charAt(i);
                if (char4 < '0' || char4 > '9') {
                    break;
                }
                string2 += '9';
                this.numString += char4;
                ++i;
            }
        }
        else {
            this.exponent = -1;
        }
        this.exponentEnd = this.numString.length();
        if (i < length && s.charAt(i) == '<') {
            final int index = s.indexOf(62, i);
            this.numString += s.substring(i + 1, index);
            i = index + 1;
        }
        s = s.substring(i).toUpperCase();
        this.maxNumber = Double.valueOf(string + 'E' + string2);
        if (this.hasMantissaSign) {
            this.minNumber = -1.0 * this.maxNumber;
        }
        else {
            this.minNumber = 0.0;
        }
        this.maxExp = Double.valueOf(string2).intValue();
        if (this.hasExponentSign) {
            this.minExp = -1 * this.maxExp;
        }
        else {
            this.minExp = 0;
        }
        if (s.indexOf("INF") != -1) {
            this.allowInfinite = true;
        }
        final int index2 = s.indexOf("MAX(");
        if (index2 != -1) {
            final double doubleValue = Double.valueOf(s.substring(index2 + 4, s.indexOf(")", index2 + 4)));
            if (doubleValue < this.maxNumber) {
                this.maxNumber = doubleValue;
            }
        }
        final int index3 = s.indexOf("MIN(");
        this.hasMin = false;
        if (index3 != -1) {
            final double doubleValue2 = Double.valueOf(s.substring(index3 + 4, s.indexOf(")", index3 + 4)));
            if (doubleValue2 > this.minNumber) {
                this.minNumber = doubleValue2;
                this.hasMin = true;
            }
        }
        final int index4 = s.indexOf("MINEXP(");
        if (index4 != -1) {
            final int intValue = Integer.valueOf(s.substring(index4 + 7, s.indexOf(")", index4 + 7)));
            if (intValue > this.minExp) {
                this.minExp = intValue;
            }
        }
        final int index5 = s.indexOf("MAXEXP(");
        if (index5 != -1) {
            final int intValue2 = Integer.valueOf(s.substring(index5 + 7, s.indexOf(")", index5 + 7)));
            if (intValue2 < this.maxExp) {
                this.maxExp = intValue2;
            }
        }
        if (s.indexOf("ISVISIBLE") != -1) {
            this.setVisible(true);
        }
        if (s.indexOf("NOTVISIBLE") != -1) {
            this.setVisible(false);
        }
        if (s.indexOf("ISENABLED") != -1) {
            this.setEnabled(true);
        }
        if (s.indexOf("NOTENABLED") != -1) {
            this.setEnabled(false);
        }
        this.caretListen = false;
        this.numberField.setText(this.numString);
        this.caretListen = true;
        if (!this.numberField.hasFocus()) {
            this.focusLost(null);
        }
        else {
            this.focusGained(null);
        }
    }
    
    public void setNaNText(final String naNText) {
        if (naNText != null) {
            this.NaNText = naNText;
        }
    }
    
    public boolean setValue(double abs) {
        String numString = this.numString;
        if (this.dialogText != null) {
            this.setDialog(null);
        }
        if (Double.isNaN(abs) || Double.isInfinite(abs) || abs > this.maxNumber || (abs < this.minNumber && this.hasMin)) {
            this.setDialog(new Double(abs));
            return false;
        }
        int mantissa = this.mantissa;
        if (this.hasMantissaSign) {
            numString = this.insert(mantissa, numString, (abs >= 0.0) ? "+" : "-");
            ++mantissa;
        }
        abs = Math.abs(abs);
        if (this.exponent == -1) {
            abs = (double)Math.round(Math.pow(10.0, -1 * ((this.decimal != -1) ? (this.decimal - (this.mantissaEnd - 1)) : 0)) * abs);
            for (int i = this.mantissaEnd - 1; i >= mantissa; --i) {
                if (i != this.decimal) {
                    numString = this.insert(i, numString, "" + (int)Math.floor(abs) % 10);
                    abs /= 10.0;
                }
            }
        }
        else {
            int a = 0;
            int n = this.mantissaEnd - mantissa - ((this.decimal != -1) ? 1 : 0);
            long n2 = 0L;
            if (abs != 0.0) {
                a = (int)Math.floor(Math.log(Math.abs(abs)) / Math.log(10.0) + 1.0E-13);
                double n3 = Math.abs(abs) / Math.pow(10.0, a);
                if (n3 < 1.0) {
                    n3 *= 10.0;
                    --a;
                }
                n2 = Math.round(n3 * Math.pow(10.0, n - 1));
                if (n2 == Math.pow(10.0, n)) {
                    n2 /= 10L;
                    ++a;
                }
                if (this.decimal - mantissa > 1) {
                    a -= this.decimal - mantissa - 1;
                }
                while (a < this.minExp && n2 != 0L) {
                    ++a;
                    n2 = Math.round(n2 / 10.0);
                }
                if (a < this.minExp || n2 == 0L) {
                    a = 0;
                    n2 = 0L;
                }
            }
            for (int j = mantissa; j < this.mantissaEnd; ++j) {
                if (numString.charAt(j) != '.') {
                    final String string = "" + (int)Math.floor(n2 / Math.pow(10.0, n - 1)) % 10;
                    --n;
                    numString = this.insert(j, numString, string);
                }
            }
            if (this.hasExponentSign) {
                numString = this.insert(this.exponent, numString, (a >= 0) ? "+" : "-");
            }
            final int abs2 = Math.abs(a);
            int n4 = this.exponentEnd - this.exponent - (this.hasExponentSign ? 1 : 0);
            for (int k = this.exponent + (this.hasExponentSign ? 1 : 0); k < this.exponentEnd; ++k) {
                final String string2 = "" + (int)Math.floor(abs2 / Math.pow(10.0, n4 - 1)) % 10;
                --n4;
                numString = this.insert(k, numString, string2);
            }
        }
        this.numString = numString;
        if (!this.numberField.hasFocus()) {
            this.focusLost(null);
        }
        else {
            this.focusGained(null);
        }
        return true;
    }
    
    private static String standardDisplay(final int a, final int n, int n2) {
        String str = "";
        int n3 = n - n2 + 1;
        final String string = "" + Math.abs(a);
        do {
            if (n3 < 0) {
                if (n2 == 0) {
                    str = '0' + str;
                }
                else {
                    if (string.charAt(n2 - 1) != '0' || str != "") {
                        str = string.charAt(n2 - 1) + str;
                    }
                    --n2;
                }
                if (++n3 == 0 && str != "") {
                    str = '.' + str;
                }
                if (n3 != 0 || n2 != 0) {
                    continue;
                }
                str = '0' + str;
            }
            else if (n3 > 0) {
                str = "0" + str;
                --n3;
            }
            else {
                if (n3 != 0) {
                    continue;
                }
                str = string.charAt(n2 - 1) + str;
                --n2;
            }
        } while (n3 != 0 || n2 > 0);
        if (a < 0) {
            str = '-' + str;
        }
        return str;
    }
    
    private class StringFormatException extends Exception
    {
        public StringFormatException(final String message) {
            super(message);
        }
    }
    
    private class JNumField extends JTextField
    {
        private boolean focusEnabled;
        
        public JNumField() {
            this.focusEnabled = true;
        }
        
        public JNumField(final int columns) {
            super(columns);
            this.focusEnabled = true;
        }
        
        public void setFocusEnabled(final boolean focusEnabled) {
            this.setRequestFocusEnabled(this.focusEnabled = focusEnabled);
        }
        
        public boolean isFocusEnabled() {
            return this.focusEnabled;
        }
        
        public boolean isFocusTraversable() {
            return super.isFocusTraversable() && this.focusEnabled;
        }
    }
}
