// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Cursor;
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;
import java.awt.event.ComponentListener;
import javax.swing.event.MouseInputListener;

public abstract class MouseThreadPanel extends ThreadPanel implements MouseInputListener, ComponentListener, FocusListener, KeyListener
{
    public static final Cursor CROSSHAIR_CURSOR;
    public static final Cursor DEFAULT_CURSOR;
    public static final Cursor E_RESIZE_CURSOR;
    public static final Cursor HAND_CURSOR;
    public static final Cursor MOVE_CURSOR;
    public static final Cursor N_RESIZE_CURSOR;
    public static final Cursor NE_RESIZE_CURSOR;
    public static final Cursor NW_RESIZE_CURSOR;
    public static final Cursor S_RESIZE_CURSOR;
    public static final Cursor SE_RESIZE_CURSOR;
    public static final Cursor SW_RESIZE_CURSOR;
    public static final Cursor TEXT_CURSOR;
    public static final Cursor W_RESIZE_CURSOR;
    public static final Cursor WAIT_CURSOR;
    
    public MouseThreadPanel() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addComponentListener(this);
        this.addKeyListener(this);
        this.addFocusListener(this);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    static {
        CROSSHAIR_CURSOR = Cursor.getPredefinedCursor(1);
        DEFAULT_CURSOR = Cursor.getPredefinedCursor(0);
        E_RESIZE_CURSOR = Cursor.getPredefinedCursor(11);
        HAND_CURSOR = Cursor.getPredefinedCursor(12);
        MOVE_CURSOR = Cursor.getPredefinedCursor(13);
        N_RESIZE_CURSOR = Cursor.getPredefinedCursor(8);
        NE_RESIZE_CURSOR = Cursor.getPredefinedCursor(7);
        NW_RESIZE_CURSOR = Cursor.getPredefinedCursor(6);
        S_RESIZE_CURSOR = Cursor.getPredefinedCursor(9);
        SE_RESIZE_CURSOR = Cursor.getPredefinedCursor(5);
        SW_RESIZE_CURSOR = Cursor.getPredefinedCursor(4);
        TEXT_CURSOR = Cursor.getPredefinedCursor(2);
        W_RESIZE_CURSOR = Cursor.getPredefinedCursor(10);
        WAIT_CURSOR = Cursor.getPredefinedCursor(3);
    }
}
