// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class MapBorderTest extends JFrame implements ActionListener
{
    MapToggleButton disable;
    MapToggleButton b1;
    MapToggleButton b2;
    MapToggleButton b3;
    MapToggleButton b4;
    MapButton b5;
    MapButton b6;
    MapButton b7;
    MapButton b8;
    
    public MapBorderTest() {
        super("Map Border Test");
        final JPanel comp = new JPanel();
        (this.disable = new MapToggleButton("setEnabled()")).addActionListener(this);
        (this.b1 = new MapToggleButton("MapToggleButton 1")).setBackground(new Color(25, 25, 25));
        this.b1.setForeground(Color.white);
        (this.b2 = new MapToggleButton("MapToggleButton 2")).setBackground(new Color(100, 75, 200));
        (this.b3 = new MapToggleButton("MapToggleButton 3")).setBackground(Color.lightGray);
        this.b3.setIcon(new ImageIcon(this.getToolkit().getImage("icon.gif")));
        (this.b4 = new MapToggleButton("MapToggleButton 4")).setBackground(new Color(153, 153, 153));
        (this.b5 = new MapButton("MapButton 5")).setBackground(Color.pink);
        this.b5.setIcon(new ImageIcon(this.getToolkit().getImage("icon.gif")));
        (this.b6 = new MapButton("MapButton 6")).setBackground(Color.white);
        (this.b7 = new MapButton("MapButton 7")).setBackground(Color.orange);
        (this.b8 = new MapButton("MapButton 8")).setBackground(Color.red);
        comp.add(this.b1);
        comp.add(this.b2);
        comp.add(this.b3);
        comp.add(this.b4);
        comp.add(this.b5);
        comp.add(this.b6);
        comp.add(this.b7);
        comp.add(this.b8);
        comp.add(this.disable);
        this.getContentPane().add(comp, "Center");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.disable)) {
            this.b1.setEnabled(this.disable.isSelected());
            this.b2.setEnabled(this.disable.isSelected());
            this.b3.setEnabled(this.disable.isSelected());
            this.b4.setEnabled(this.disable.isSelected());
            this.b5.setEnabled(this.disable.isSelected());
            this.b6.setEnabled(this.disable.isSelected());
            this.b7.setEnabled(this.disable.isSelected());
            this.b8.setEnabled(this.disable.isSelected());
        }
    }
    
    public static void main(final String[] array) {
        final MapBorderTest mapBorderTest = new MapBorderTest();
        mapBorderTest.setSize(300, 300);
        mapBorderTest.show();
    }
}
