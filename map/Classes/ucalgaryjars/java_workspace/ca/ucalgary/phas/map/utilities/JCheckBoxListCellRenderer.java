// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.JCheckBox;

public class JCheckBoxListCellRenderer extends JCheckBox implements ListCellRenderer
{
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        final Component component = (Component)o;
        if (b) {
            component.setBackground(list.getSelectionBackground());
            component.setForeground(list.getSelectionForeground());
        }
        else {
            component.setBackground(list.getBackground());
            component.setForeground(list.getForeground());
        }
        return component;
    }
}
