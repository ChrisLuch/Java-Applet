// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors;

import javax.swing.JComponent;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import ca.ucalgary.phas.map.utilities.ImageUtils;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.applet.Applet;
import ca.ucalgary.phas.map.utilities.ParameterUtils;
import java.util.Vector;
import java.awt.event.ActionEvent;
import ca.ucalgary.phas.map.utilities.MapButton;
import ca.ucalgary.phas.map.utilities.MapToggleButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

public class VectorCompsApplet extends JApplet implements ActionListener, Runnable
{
    private Font font;
    JPanel contentPanel;
    VectorCompsPanel vPanel;
    JPanel controlPanel;
    int mode;
    public VectorCompJDemo demo;
    private int delay;
    private GridBagLayout gridbag1;
    private GridBagConstraints c1;
    protected MapToggleButton drawButton;
    protected MapToggleButton drawLineButton;
    protected MapToggleButton drawDLineButton;
    protected MapToggleButton cancelButton;
    private MapButton findComps;
    private MapButton drawPerp;
    private MapButton drawAxes;
    protected MapButton deleteButton;
    static final int DEMO1 = 0;
    static final int DEMO2 = 1;
    static final int SIM = 2;
    
    public VectorCompsApplet() {
        this.demo = null;
        this.delay = 20;
        this.gridbag1 = new GridBagLayout();
        this.c1 = new GridBagConstraints();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.deleteButton) {
            this.vPanel.doDelete();
            ((AbstractButton)this.deleteButton).setEnabled(false);
        }
        if (actionEvent.getSource() == this.drawButton) {
            this.vPanel.doDraw();
        }
        if (actionEvent.getSource() == this.drawLineButton) {
            this.vPanel.doDrawLine();
        }
        if (actionEvent.getSource() == this.drawDLineButton) {
            this.vPanel.doDrawDLine();
        }
        if (actionEvent.getSource() == this.cancelButton) {
            this.vPanel.doCancel();
        }
        if (actionEvent.getSource() == this.drawPerp) {
            if (this.mode == 1) {
                this.demo.myResume();
            }
            else {
                this.demo.start();
            }
            ((AbstractButton)this.findComps).setEnabled(true);
            ((AbstractButton)this.drawPerp).setEnabled(false);
            ((AbstractButton)this.drawAxes).setEnabled(false);
        }
        if (actionEvent.getSource() == this.drawAxes) {
            this.demo.start();
            ((AbstractButton)this.findComps).setEnabled(false);
            ((AbstractButton)this.drawPerp).setEnabled(true);
            ((AbstractButton)this.drawAxes).setEnabled(false);
        }
        if (actionEvent.getSource() == this.findComps) {
            this.demo.myResume();
            ((AbstractButton)this.findComps).setEnabled(false);
            ((AbstractButton)this.drawPerp).setEnabled(false);
            ((AbstractButton)this.drawAxes).setEnabled(false);
        }
    }
    
    public void endDemo() {
        if (this.demo != null) {
            this.demo.mySuspend();
        }
    }
    
    public void init() {
        while (this.getSize().width <= 0) {}
        final int width = this.getSize().width;
        while (this.getSize().height <= 0) {}
        final int height = this.getSize().height;
        final Vector<String> vector = new Vector<String>();
        vector.addElement("Demo1");
        vector.addElement("Demo2");
        vector.addElement("Sim");
        final String stringParameter = ParameterUtils.getStringParameter((Applet)this, "appMode", (Vector)vector, "Sim");
        if (stringParameter.equalsIgnoreCase("Demo1")) {
            this.mode = 0;
        }
        else if (stringParameter.equalsIgnoreCase("Demo2")) {
            this.mode = 1;
        }
        else if (stringParameter.equalsIgnoreCase("Sim")) {
            this.mode = 2;
        }
        else {
            System.out.println("Mode Invalid = " + stringParameter + " THIS SHOULD NOT HAPPEN WHEN USING ParamterUtils.");
        }
        this.font = new Font("Arial", 0, 12);
        (this.contentPanel = new JPanel()).setName("JAppletContentPane");
        this.contentPanel.setLayout(null);
        this.vPanel = new VectorCompsPanel(this, true);
        if (this.mode == 0) {
            this.initDemoPanel();
            this.demo = new VectorComponentsJDemo1(this, this.vPanel);
            ((AbstractButton)this.drawPerp).setEnabled(true);
            ((AbstractButton)this.findComps).setEnabled(false);
            ((AbstractButton)this.drawAxes).setEnabled(false);
        }
        else if (this.mode == 1) {
            this.initDemoPanel();
            this.demo = new VectorComponentsJDemo2(this, this.vPanel);
            ((AbstractButton)this.drawPerp).setEnabled(false);
            ((AbstractButton)this.drawAxes).setEnabled(true);
            ((AbstractButton)this.findComps).setEnabled(false);
        }
        else {
            this.initPanel();
        }
        this.contentPanel.setLayout(new BorderLayout(0, 0));
        this.contentPanel.add("Center", this.vPanel);
        this.contentPanel.add("South", this.controlPanel);
        this.setContentPane(this.contentPanel);
        this.vPanel.init();
    }
    
    public void initDemoPanel() {
        (this.controlPanel = new JPanel()).setLayout(this.gridbag1);
        this.findComps = new MapButton("Draw Comps");
        this.drawPerp = new MapButton("Draw Perps");
        this.drawAxes = new MapButton("Draw Axes");
        ((AbstractButton)this.drawPerp).addActionListener(this);
        ((AbstractButton)this.drawAxes).addActionListener(this);
        ((AbstractButton)this.findComps).addActionListener(this);
        ((AbstractButton)this.findComps).setEnabled(false);
        ((AbstractButton)this.drawPerp).setEnabled(true);
        ((AbstractButton)this.drawAxes).setEnabled(false);
        this.c1.gridx = 0;
        this.c1.gridy = 0;
        this.c1.gridwidth = 2;
        ((JComponent)this.drawPerp).setPreferredSize(new Dimension(120, 30));
        this.gridbag1.setConstraints((Component)this.drawPerp, this.c1);
        this.controlPanel.add((Component)this.drawPerp);
        this.c1.gridx = 2;
        this.c1.gridy = 0;
        this.c1.gridwidth = 2;
        ((JComponent)this.findComps).setPreferredSize(new Dimension(120, 30));
        this.gridbag1.setConstraints((Component)this.findComps, this.c1);
        this.controlPanel.add((Component)this.findComps);
        this.c1.gridx = 4;
        this.c1.gridy = 0;
        this.c1.gridwidth = 1;
        ((JComponent)this.drawAxes).setPreferredSize(new Dimension(120, 30));
        this.gridbag1.setConstraints((Component)this.drawAxes, this.c1);
        this.controlPanel.add((Component)this.drawAxes);
    }
    
    public void initPanel() {
        final ImageIcon imageIcon = new ImageIcon(ImageUtils.getImageFromJar("images/drawVect.gif", (Object)this));
        final ImageIcon imageIcon2 = new ImageIcon(ImageUtils.getImageFromJar("images/drawDLine.gif", (Object)this));
        final ImageIcon imageIcon3 = new ImageIcon(ImageUtils.getImageFromJar("images/drawLine.gif", (Object)this));
        (this.controlPanel = new JPanel()).setLayout(this.gridbag1);
        this.drawButton = new MapToggleButton((Icon)imageIcon);
        this.drawLineButton = new MapToggleButton((Icon)imageIcon3);
        this.drawDLineButton = new MapToggleButton((Icon)imageIcon2);
        this.cancelButton = new MapToggleButton("No Tool");
        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add((AbstractButton)this.drawButton);
        buttonGroup.add((AbstractButton)this.drawLineButton);
        buttonGroup.add((AbstractButton)this.drawDLineButton);
        buttonGroup.add((AbstractButton)this.cancelButton);
        ((AbstractButton)(this.deleteButton = new MapButton("Delete"))).addActionListener(this);
        ((AbstractButton)this.cancelButton).addActionListener(this);
        ((AbstractButton)this.drawButton).addActionListener(this);
        ((AbstractButton)this.drawLineButton).addActionListener(this);
        ((AbstractButton)this.drawDLineButton).addActionListener(this);
        ((AbstractButton)this.deleteButton).setEnabled(false);
        this.c1.gridx = 0;
        this.c1.gridy = 0;
        this.gridbag1.setConstraints((Component)this.cancelButton, this.c1);
        this.controlPanel.add((Component)this.cancelButton);
        this.c1.gridx = 1;
        this.c1.gridy = 0;
        this.gridbag1.setConstraints((Component)this.drawButton, this.c1);
        this.controlPanel.add((Component)this.drawButton);
        this.c1.gridx = 2;
        this.c1.gridy = 0;
        this.gridbag1.setConstraints((Component)this.drawLineButton, this.c1);
        this.controlPanel.add((Component)this.drawLineButton);
        this.c1.gridx = 3;
        this.c1.gridy = 0;
        this.gridbag1.setConstraints((Component)this.drawDLineButton, this.c1);
        this.controlPanel.add((Component)this.drawDLineButton);
        this.c1.gridx = 4;
        this.c1.gridy = 0;
        this.gridbag1.setConstraints((Component)this.deleteButton, this.c1);
        this.controlPanel.add((Component)this.deleteButton);
    }
    
    public void run() {
    }
    
    public void suspendDemo() {
        if (this.demo != null) {
            this.demo.mySuspend();
        }
    }
}
