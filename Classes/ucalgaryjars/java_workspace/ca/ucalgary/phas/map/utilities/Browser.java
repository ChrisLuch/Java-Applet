// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.net.URL;
import javax.swing.text.Document;
import java.io.IOException;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class Browser extends JPanel
{
    public Browser() {
        this.setLayout(new BorderLayout(5, 5));
        final JEditorPane comp = new JEditorPane();
        final JTextField comp2 = new JTextField("http://java.sun.com");
        comp.setEditable(false);
        comp.addHyperlinkListener(new HyperlinkListener() {
            private final /* synthetic */ Browser this$0;
            
            public void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
                if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    SwingUtilities.invokeLater(new Runnable() {
                        private final /* synthetic */ Browser$1 this$1 = this$1;
                        
                        public void run() {
                            final Document document = comp.getDocument();
                            try {
                                final URL url = hyperlinkEvent.getURL();
                                comp.setPage(url);
                                comp2.setText(url.toString());
                            }
                            catch (IOException ex) {
                                JOptionPane.showMessageDialog(this.this$1.this$0, "Can't follow link", "Invalid Input", 0);
                                comp.setDocument(document);
                            }
                        }
                    });
                }
            }
        });
        final JScrollPane comp3 = new JScrollPane();
        comp3.setBorder(BorderFactory.createLoweredBevelBorder());
        comp3.getViewport().add(comp);
        this.add(comp3, "Center");
        comp2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    comp.setPage(comp2.getText());
                }
                catch (IOException ex) {
                    JOptionPane.showMessageDialog(Browser.this, "Invalid URL", "Invalid Input", 0);
                }
            }
        });
        this.add(comp2, "South");
    }
}
