// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import javax.swing.event.CaretEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.AWTEventMulticaster;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import javax.swing.event.CaretListener;
import java.awt.event.AdjustmentListener;
import javax.swing.JPanel;

public class ScientificNumberField extends JPanel implements AdjustmentListener, CaretListener, KeyListener, FocusListener
{
    public int scrollBarWidth;
    public int unitLabelWidth;
    public Font font;
    public JLabel unitLabel;
    public int maxExponent;
    public int minExponent;
    public boolean allowInfinite;
    public boolean NaN;
    private Dimension size;
    private boolean negExponent;
    private boolean negMantissa;
    private boolean absValue;
    private int mantissa;
    private int exponent;
    private int sigFigs;
    private JTextField numberField;
    private JTextField textField;
    private JScrollBar scrollBar;
    private int lastCaret;
    private ActionListener actionListener;
    
    public ScientificNumberField() {
        this(3, -9, 9, true, true);
    }
    
    public ScientificNumberField(final int sigFigs, final int minExponent, final int maxExponent, final boolean absValue, final boolean allowInfinite) {
        this.scrollBarWidth = 18;
        this.unitLabelWidth = 50;
        this.font = new Font("dialoginput", 0, 12);
        this.unitLabel = new JLabel();
        this.size = new Dimension(0, 0);
        this.negExponent = false;
        this.negMantissa = false;
        this.absValue = false;
        this.numberField = new JTextField();
        this.textField = new JTextField();
        this.scrollBar = new JScrollBar();
        this.lastCaret = 0;
        this.setLayout(null);
        this.absValue = absValue;
        this.allowInfinite = allowInfinite;
        this.NaN = false;
        if (minExponent < -9) {
            this.minExponent = -9;
        }
        else {
            this.minExponent = minExponent;
        }
        if (maxExponent > 9) {
            this.maxExponent = 9;
        }
        else {
            this.maxExponent = maxExponent;
        }
        if (sigFigs < 2) {
            this.sigFigs = 2;
        }
        else if (sigFigs > 9) {
            this.sigFigs = 9;
        }
        else {
            this.sigFigs = sigFigs;
        }
        this.mantissa = 0;
        this.exponent = 0;
        this.textField.setEditable(false);
        this.textField.setVisible(false);
        this.textField.setBackground(Color.white);
        this.textField.setHorizontalAlignment(4);
        this.numberField.setHorizontalAlignment(4);
        this.numberField.setFont(this.font);
        this.unitLabel.setForeground(Color.black);
        this.setSize(82, 18);
        this.setValue();
        this.scrollBar.setValue(2);
        this.add(this.textField);
        this.add(this.numberField);
        this.add(this.scrollBar);
        this.add(this.unitLabel);
        this.numberField.addCaretListener(this);
        this.numberField.addKeyListener(this);
        this.numberField.addFocusListener(this);
        this.scrollBar.addAdjustmentListener(this);
    }
    
    public void addActionListener(final ActionListener b) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, b);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final boolean b = adjustmentEvent.getValue() < 2 ^ this.negMantissa;
        final int caretPosition = this.numberField.getCaretPosition();
        int n = 0;
        this.scrollBar.removeAdjustmentListener(this);
        if (!this.absValue) {
            n = 1;
        }
        if (caretPosition < n + 2 + this.sigFigs && this.exponent != this.maxExponent + 1) {
            int n2;
            if (caretPosition == n) {
                n2 = (int)Math.round(Math.pow(10.0, this.sigFigs - 1));
            }
            else {
                n2 = (int)Math.round(Math.pow(10.0, this.sigFigs + n - caretPosition));
            }
            while (this.mantissa < Math.round(Math.pow(10.0, this.sigFigs - 1)) && this.exponent > this.minExponent && this.mantissa != 0) {
                if (--this.exponent < 0) {
                    this.negExponent = true;
                }
                this.mantissa *= 10;
            }
            if (b) {
                if (this.mantissa + n2 < Math.round(Math.pow(10.0, this.sigFigs))) {
                    this.mantissa += n2;
                }
                else if (this.exponent < this.maxExponent) {
                    if (++this.exponent >= 0) {
                        this.negExponent = false;
                    }
                    this.mantissa -= 8 * (int)Math.round(Math.pow(10.0, this.sigFigs - 1));
                    for (int i = this.sigFigs + n - caretPosition; i < this.sigFigs - 1; ++i) {
                        this.mantissa -= (int)Math.round(9.0 * Math.pow(10.0, i));
                    }
                }
                else if (this.allowInfinite) {
                    this.exponent = this.maxExponent + 1;
                }
            }
            else if (this.mantissa != 0) {
                if (this.mantissa - n2 >= Math.round(Math.pow(10.0, this.sigFigs - 1)) || (this.exponent == this.minExponent && this.mantissa - n2 >= 0)) {
                    this.mantissa -= n2;
                }
                else if (this.exponent > this.minExponent) {
                    if (--this.exponent < 0) {
                        this.negExponent = true;
                    }
                    this.mantissa += 8 * (int)Math.round(Math.pow(10.0, this.sigFigs - 1));
                    for (int j = this.sigFigs + n - caretPosition; j < this.sigFigs - 1; ++j) {
                        this.mantissa += (int)Math.round(9.0 * Math.pow(10.0, j));
                    }
                }
            }
        }
        else {
            if (b && (this.exponent < this.maxExponent || (this.exponent == this.maxExponent && this.allowInfinite))) {
                ++this.exponent;
            }
            else if (!b && this.exponent > this.minExponent) {
                --this.exponent;
            }
            if (this.exponent < 0) {
                this.negExponent = true;
            }
            else {
                this.negExponent = false;
            }
        }
        this.setValue();
        this.numberField.setCaretPosition(caretPosition);
        this.sendAction();
        this.scrollBar.setValue(2);
        this.scrollBar.addAdjustmentListener(this);
    }
    
    public void allowInfinite(final boolean allowInfinite) {
        this.allowInfinite = allowInfinite;
    }
    
    public void caretUpdate(final CaretEvent caretEvent) {
        int caretPosition = 1;
        if (this.absValue) {
            caretPosition = 0;
        }
        if (this.numberField.getCaretPosition() == 1 + caretPosition) {
            if (this.lastCaret < 1 + caretPosition) {
                this.numberField.setCaretPosition(2 + caretPosition);
            }
            else {
                this.numberField.setCaretPosition(caretPosition);
            }
        }
        else if (this.numberField.getCaretPosition() == this.sigFigs + 1 + caretPosition) {
            if (this.lastCaret < this.sigFigs + 1 + caretPosition) {
                this.numberField.setCaretPosition(this.sigFigs + 2 + caretPosition);
            }
            else {
                this.numberField.setCaretPosition(this.sigFigs + caretPosition);
            }
        }
        this.lastCaret = this.numberField.getCaretPosition();
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
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public int getExponent() {
        return this.exponent;
    }
    
    public int getMantissa() {
        return this.mantissa;
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.size.width + this.unitLabelWidth, this.size.height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.size.width + this.unitLabelWidth, this.size.height);
    }
    
    public String getText() {
        if (this.exponent >= this.maxExponent) {
            return this.textField.getText();
        }
        return this.numberField.getText();
    }
    
    public double getValue() {
        if (this.NaN) {
            return Double.NaN;
        }
        if (this.negMantissa && this.exponent > this.maxExponent) {
            return Double.NEGATIVE_INFINITY;
        }
        if (!this.negMantissa && this.exponent > this.maxExponent) {
            return Double.POSITIVE_INFINITY;
        }
        if (this.negMantissa) {
            return this.mantissa * Math.pow(10.0, this.exponent - this.sigFigs + 1) * -1.0;
        }
        return this.mantissa * Math.pow(10.0, this.exponent - this.sigFigs + 1);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        final int caretPosition = this.numberField.getCaretPosition();
        System.out.println("keyPressed Key Code:" + new Character(keyChar).hashCode());
        if (new Character(keyChar).hashCode() == 127 || new Character(keyChar).hashCode() == 8) {
            this.setValue();
            this.numberField.setCaretPosition(caretPosition);
            keyEvent.setKeyChar('0');
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        final int caretPosition = this.numberField.getCaretPosition();
        System.out.println("keyReleased Key Code:" + new Character(keyChar).hashCode());
        this.setValue();
        this.numberField.setCaretPosition(caretPosition);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        final int caretPosition = this.numberField.getCaretPosition();
        System.out.println("keyTyped Key Code:" + new Character(keyChar).hashCode());
        int caretPosition2;
        if (!this.absValue) {
            caretPosition2 = 3;
        }
        else {
            caretPosition2 = 2;
        }
        keyEvent.setKeyChar('\0');
        if (caretPosition == 0 && !this.absValue) {
            if (keyChar == '-') {
                this.negMantissa = true;
                this.setValue();
                this.numberField.setCaretPosition(1);
                this.sendAction();
            }
            else if (keyChar == '+' || keyChar == ' ') {
                this.negMantissa = false;
                this.setValue();
                this.numberField.setCaretPosition(1);
                this.sendAction();
            }
        }
        else if (caretPosition == this.sigFigs + caretPosition2) {
            if (keyChar == '-' && Math.abs(this.exponent) * -1 >= this.minExponent) {
                this.negExponent = true;
                this.exponent = Math.abs(this.exponent) * -1;
                this.setValue();
                this.numberField.setCaretPosition(this.sigFigs + caretPosition2 + 1);
                this.sendAction();
            }
            else if ((keyChar == '+' || keyChar == ' ') && Math.abs(this.exponent) <= this.maxExponent) {
                this.negExponent = false;
                this.exponent = Math.abs(this.exponent);
                this.setValue();
                this.numberField.setCaretPosition(this.sigFigs + caretPosition2 + 1);
                this.sendAction();
            }
        }
        else if (keyChar >= '0' && keyChar <= '9') {
            if (caretPosition < this.sigFigs + caretPosition2 - 1) {
                final String text = this.numberField.getText();
                final String string = text.substring(0, caretPosition) + keyChar + text.substring(caretPosition + 1, this.sigFigs + caretPosition2 - 1);
                String s;
                if (this.absValue) {
                    s = string.substring(0, 1) + string.substring(2);
                }
                else {
                    s = string.substring(1, 2) + string.substring(3);
                }
                this.mantissa = Integer.valueOf(s);
                this.setValue();
                if (caretPosition == caretPosition2 - 2) {
                    this.numberField.setCaretPosition(caretPosition2);
                }
                else if (caretPosition == this.sigFigs + caretPosition2 - 2) {
                    this.numberField.setCaretPosition(this.sigFigs + caretPosition2);
                }
                else {
                    this.numberField.setCaretPosition(caretPosition + 1);
                }
                this.sendAction();
            }
            else if (caretPosition == this.sigFigs + caretPosition2 + 1) {
                if (this.exponent < 0) {
                    this.exponent = Character.digit(keyChar, 10) * -1;
                }
                else {
                    this.exponent = Character.digit(keyChar, 10);
                }
                this.setValue();
                this.numberField.setCaretPosition(caretPosition + 1);
                this.sendAction();
            }
        }
    }
    
    public void removeActionListener() {
        this.actionListener = null;
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.actionListener = null;
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
    
    public void setAbsValue(final boolean absValue) {
        if (!this.absValue && absValue && this.negMantissa) {
            this.negMantissa = false;
            this.setValue();
        }
        this.absValue = absValue;
    }
    
    public void setAll(final double value, final int sigFigs, final int minExponent, final int maxExponent, final boolean absValue, final boolean allowInfinite, final boolean enabled) {
        this.absValue = absValue;
        this.allowInfinite = allowInfinite;
        if (minExponent < -9) {
            this.minExponent = -9;
        }
        else {
            this.minExponent = minExponent;
        }
        if (maxExponent > 9) {
            this.maxExponent = 9;
        }
        else {
            this.maxExponent = maxExponent;
        }
        if (sigFigs < 2) {
            this.sigFigs = 2;
        }
        else if (sigFigs > 9) {
            this.sigFigs = 9;
        }
        else {
            this.sigFigs = sigFigs;
        }
        this.setEnabled(enabled);
        this.setVisible(true);
        this.setValue(value);
    }
    
    public void setEnabled(final boolean b) {
        this.numberField.setEditable(b);
        this.scrollBar.setVisible(b);
        if (b) {
            this.numberField.setBackground(Color.white);
            this.textField.setBackground(Color.white);
            this.unitLabel.setLocation(this.numberField.getWidth() + this.scrollBarWidth + 3, 0);
        }
        else {
            this.numberField.setBackground(Color.lightGray);
            this.textField.setBackground(Color.lightGray);
            this.unitLabel.setLocation(this.numberField.getWidth() + 3, 0);
        }
        this.repaint();
    }
    
    public void setSigFigs(final int sigFigs) {
        if (sigFigs >= 2 && sigFigs <= 5) {
            final double value = this.getValue();
            this.sigFigs = sigFigs;
            this.setValue(value);
            this.setValue();
        }
    }
    
    public void setSize(int width, final int height) {
        if (width < 50) {
            width = 50;
        }
        this.size = new Dimension(width, height);
        this.setBounds(this.getLocation().x, this.getLocation().y, width + this.unitLabelWidth, height);
        this.textField.setBounds(0, 0, width - this.scrollBarWidth - 1, height);
        this.numberField.setBounds(0, 0, width - this.scrollBarWidth - 1, height);
        this.scrollBar.setBounds(width - this.scrollBarWidth, 0, this.scrollBarWidth, height);
        if (this.isEnabled()) {
            this.unitLabel.setBounds(width + 3, 0, this.unitLabelWidth, height);
        }
        else {
            this.unitLabel.setBounds(width - this.scrollBarWidth + 3, 0, this.unitLabelWidth, height);
        }
    }
    
    public void setSize(final Dimension dimension) {
        this.setSize(dimension.width, dimension.height);
    }
    
    private void setValue() {
        int mantissa = this.mantissa;
        String s = "";
        if (this.exponent == this.maxExponent + 1) {
            this.numberField.setVisible(false);
            this.textField.setText("Infinity ");
            this.textField.setVisible(true);
        }
        else {
            this.numberField.setVisible(true);
            this.textField.setVisible(false);
            if (!this.absValue) {
                if (this.negMantissa) {
                    s = "-";
                }
                else {
                    s = " ";
                }
            }
            for (int i = this.sigFigs; i > 0; --i) {
                s = s + "" + mantissa / Math.round(Math.pow(10.0, i - 1));
                mantissa %= (int)Math.round(Math.pow(10.0, i - 1));
                if (i == this.sigFigs) {
                    s += ".";
                }
            }
            String text;
            if (this.negExponent) {
                text = s + "E-" + Math.abs(this.exponent);
            }
            else {
                text = s + "E+" + Math.abs(this.exponent);
            }
            this.numberField.setText(text);
        }
    }
    
    public void setValue(final double a) {
        int caretPosition = this.numberField.getCaretPosition();
        this.NaN = false;
        if (a == 0.0) {
            this.exponent = 0;
            this.mantissa = 0;
            final boolean b = false;
            this.negExponent = b;
            this.negMantissa = b;
        }
        else {
            if (Double.isNaN(a)) {
                this.setEnabled(false);
                this.numberField.setVisible(false);
                this.textField.setText("?");
                this.textField.setVisible(true);
                this.NaN = true;
                return;
            }
            this.exponent = (int)Math.floor(Math.log(Math.abs(a)) / Math.log(10.0) + 1.0E-13);
            double n = Math.abs(a) / Math.pow(10.0, this.exponent);
            if (n < 1.0) {
                n *= 10.0;
                --this.exponent;
            }
            this.mantissa = (int)Math.round(n * Math.pow(10.0, this.sigFigs - 1));
            if (this.mantissa == Math.pow(10.0, this.sigFigs)) {
                this.mantissa /= 10;
                ++this.exponent;
            }
            while (this.exponent < this.minExponent && this.mantissa != 0) {
                ++this.exponent;
                this.mantissa = (int)Math.round(this.mantissa / 10.0);
            }
            if (this.exponent > this.maxExponent) {
                this.exponent = this.maxExponent + 1;
                this.mantissa = (int)(Math.round(Math.pow(10.0, this.sigFigs)) - 1L);
            }
            else if (this.exponent < this.minExponent || this.mantissa == 0) {
                this.exponent = 0;
                this.mantissa = 0;
            }
            if (this.exponent < 0) {
                this.negExponent = true;
            }
            else {
                this.negExponent = false;
            }
            if (a < 0.0 && this.mantissa != 0 && !this.absValue) {
                this.negMantissa = true;
            }
            else {
                this.negMantissa = false;
            }
        }
        this.setValue();
        if (this.numberField.getText().length() < caretPosition) {
            --caretPosition;
        }
        this.numberField.setCaretPosition(caretPosition);
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
}
