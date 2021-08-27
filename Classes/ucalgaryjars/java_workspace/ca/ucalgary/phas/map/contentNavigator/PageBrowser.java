// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.contentNavigator;

import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JApplet;

public class PageBrowser extends JApplet
{
    public static PageBrowserPanel getPageBrowserPanel() {
        return PageBrowserPanel.getPanel();
    }
    
    public void init() {
        PageBrowserPanel.initPanel(this);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints constraints = new GridBagConstraints();
        this.getContentPane().setLayout(layout);
        this.getContentPane().setBackground(Color.white);
        final double[] columnWeights = { 0.5, 0.0, 0.5 };
        final int[] columnWidths = { 0, 0, 0 };
        final double[] rowWeights = { 1.0 };
        final int[] rowHeights = { 0 };
        layout.columnWeights = columnWeights;
        layout.columnWidths = columnWidths;
        layout.rowWeights = rowWeights;
        layout.rowHeights = rowHeights;
        constraints.fill = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.getContentPane().add(PageBrowserPanel.getPanel(), constraints);
    }
    
    public void start() {
        this.repaint();
    }
    
    public void stop() {
        PageBrowserPanel.deletePanel();
    }
}
