// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.TextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class MapScrollBarTest extends JFrame implements ActionListener
{
    JButton resize;
    TextField xfield;
    TextField yfield;
    JLabel label;
    
    public MapScrollBarTest() {
        super("MapScrollBarTest");
        this.label = new JLabel("THIS IS A TEST");
        this.xfield = new TextField(4);
        this.yfield = new TextField(4);
        (this.resize = new JButton("Resize")).addActionListener(this);
        final JPanel comp = new JPanel();
        comp.add(this.xfield);
        comp.add(this.yfield);
        comp.add(this.resize);
        final JScrollPane comp2 = new JScrollPane();
        comp2.setHorizontalScrollBar(new MapScrollBar(0));
        comp2.setVerticalScrollBar(new MapScrollBar(1));
        comp2.getViewport().setView(this.label);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(comp2, "Center");
        this.getContentPane().add(comp, "North");
        comp2.updateUI();
    }
    
    public static void main(final String[] array) {
        final MapScrollBarTest mapScrollBarTest = new MapScrollBarTest();
        mapScrollBarTest.setSize(300, 300);
        mapScrollBarTest.show();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        try {
            this.label.setSize(new Integer(this.xfield.getText()), new Integer(this.yfield.getText()));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.setSize(50, 50);
        }
    }
}
