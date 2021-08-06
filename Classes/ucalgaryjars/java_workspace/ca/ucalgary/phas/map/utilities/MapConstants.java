// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import javax.swing.ToolTipManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public final class MapConstants
{
    public static final Font defaultScreenFont;
    public static final Font smallScreenFont;
    public static final Font boldScreenFont;
    public static final Font textOnlyButtonFont;
    public static final Color textAreaAccentColor;
    public static final Font imageBorderFont;
    public static final Color imageBorderColor;
    public static final Color mapLightGreen;
    public static final Color mapGreen;
    public static final Color mapDarkGreen;
    private static final String MAPGREYSTRING = "#a0a0a0";
    private static final String DARKGREENSTRING = "#669966";
    private static final String HIGHLIGHTGREENSTRING = "#ccffcc";
    public static final Color lineBorderColor;
    public static final int lineBorderWidth = 1;
    public static int buttonPanelInnerSpacing;
    public static int buttonPanelNorthSpacing;
    public static int buttonPanelSouthSpacing;
    public static int buttonPanelEastWestSpacing;
    public static final Color trackerBorderColor;
    public static final Color trackerPanelColor;
    public static final Color trackerReferenceTextColor;
    public static final Font locationTrackerLabelFont;
    public static final Font conceptMapLabelFont;
    public static final Color vectorDefaultColor;
    public static final Font vectorsNormalFont;
    public static final Font vectorNormalFont;
    public static final Font vectorFont;
    public static final Font vectorSubscriptFont;
    public static final Font vectorDefaultFont;
    public static final Color treeTextSelectionColor;
    public static final Color treeLineColor;
    public static final Color treeDisabledLineColor;
    public static final Color listTextSelectionBackgroundColor;
    public static final Font listFont;
    public static final Color listTextColor;
    public static final Color mapBorderDefaultColor;
    public static final Color mapButtonForegoundColor;
    public static final Color mapButtonDefaultColor;
    public static final Color mapButtonFocusColor;
    public static final Color mapButtonToolTipBackgroundColor;
    public static final Color scrollBarThumbHighlightColor;
    public static final Color scrollBarThumbLightShadowColor;
    public static final Color scrollBarThumbDarkShadowColor;
    public static final Color scrollBarThumbColor;
    public static final Color scrollBarTrackColor;
    public static final Color scrollBarTrackHighlightColor;
    public static final int scrollBarWidth = 7;
    public static final Color pageBrowserBackgroundColor;
    public static final Color pageBrowserPanelColor;
    public static final Color adminInterfacePanelColor;
    public static final Color adminInterfacePanelForgroundColor;
    public static final Color adminInterfaceBorderColor;
    public static final Color adminInterfaceLabelForegroundColor;
    public static final Font adminInterfaceLabelFont;
    public static final Color adminInterfaceTextComponentBackgroundColor;
    public static final Color adminInterfaceTextComponentForegroundColor;
    public static final Color adminInterfaceTextComponentSelectionColor;
    public static final Font adminInterfaceTextComponentFont;
    public static final Color organizerSelectionColor;
    public static final Color organizerSystemColor;
    public static final Color organizerTextColor;
    public static final Color organizerBackGroundColor;
    public static final Font organizerDialogFont;
    public static final Font organizerSecondaryDialogFont;
    public static final int organizerMaxNameLength = 19;
    public static final int organizerMaxDescriptionLength = 50;
    public static final ImageIcon mapLogo;
    public static final Color potentialEnergyColor;
    public static final Color keneticEnergyColor;
    public static final Color kineticEnergyColor;
    public static final Color energyColor;
    public static final Color positionVectorColor;
    public static final Color positionVectorComponentColor;
    public static final Color normalForceVectorColor;
    public static final Color normalForceVectorComponentColor;
    public static final Color frictionForceVectorColor;
    public static final Color ficticiousForceVectorColor;
    public static final Color frictionForceVectorComponentColor;
    public static final Color weightForceVectorColor;
    public static final Color weightForceVectorComponentColor;
    public static final Color forceGravityVectorColor;
    public static final Color accelerationVectorColor;
    public static final Color accelerationVectorComponentColor;
    public static final Color acceletationDueToGravityVectorColor;
    public static final Color acceletationDueToGravityVectorComponentColor;
    public static final Color velocityVectorColor;
    public static final Color velocityVectorComponentColor;
    public static final Color velocityLabFrameVectorColor;
    public static final Color velocityMovingFrameVectorColor;
    public static final Color velocityMovingFrameLabFrameVectorColor;
    public static final Color momentumVectorColor;
    public static final Color distanceVectorColor;
    
    public static Border getDefaultPanelBorder() {
        return new LineBorder(MapConstants.lineBorderColor, 1);
    }
    
    public static final void setMapLookAndFeel() {
        MetalLookAndFeel.setCurrentTheme(new MapTheme());
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        }
        catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        ToolTipManager.sharedInstance().setDismissDelay(8000);
    }
    
    public static Border getAnimationPanelBorder() {
        return new IncompleteBorder(isMenuBarHidden(), true, false, true);
    }
    
    public static boolean isMenuBarHidden() {
        try {
            final String string = PropertyManager.getResourceBundle("ca.ucalgary.phas.map.utilities.UtilitiesProperties", MapLocaleManager.getLocale()).getString("MapMenuBar_hideBar");
            if (string == null) {
                return false;
            }
            if (string.equalsIgnoreCase("yes")) {
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    static {
        defaultScreenFont = new Font("SansSerif", 0, 12);
        smallScreenFont = new Font("SansSerif", 0, 10);
        boldScreenFont = new Font("SansSerif", 1, 12);
        textOnlyButtonFont = new Font("SansSerif", 1, 12);
        textAreaAccentColor = new Color(153, 204, 153);
        imageBorderFont = new Font("SansSerif", 1, 12);
        imageBorderColor = new Color(102, 153, 102);
        mapLightGreen = new Color(204, 255, 204);
        mapGreen = new Color(153, 204, 153);
        mapDarkGreen = new Color(102, 153, 102);
        lineBorderColor = Color.gray;
        MapConstants.buttonPanelInnerSpacing = 5;
        MapConstants.buttonPanelNorthSpacing = 1;
        MapConstants.buttonPanelSouthSpacing = 5;
        MapConstants.buttonPanelEastWestSpacing = 5;
        trackerBorderColor = Color.decode("#a0a0a0");
        trackerPanelColor = Color.white;
        trackerReferenceTextColor = Color.decode("#669966");
        locationTrackerLabelFont = new Font("Helvetica", 1, 12);
        conceptMapLabelFont = new Font("Helvetica", 1, 15);
        vectorDefaultColor = Color.black;
        vectorsNormalFont = new Font("Helvetica", 0, 14);
        vectorNormalFont = new Font("Helvetica", 0, 14);
        vectorFont = new Font("Helvetica", 1, 14);
        vectorSubscriptFont = new Font("Helvetica", 1, 12);
        vectorDefaultFont = new Font("Helvetica", 0, 14);
        treeTextSelectionColor = Color.decode("#ccffcc");
        treeLineColor = Color.decode("#669966");
        treeDisabledLineColor = Color.lightGray;
        listTextSelectionBackgroundColor = Color.decode("#ccffcc");
        listFont = new Font("Helvetica", 0, 12);
        listTextColor = Color.black;
        mapBorderDefaultColor = Color.decode("#a0a0a0");
        mapButtonForegoundColor = Color.decode("#669966").darker();
        mapButtonDefaultColor = Color.decode("#a0a0a0");
        mapButtonFocusColor = Color.decode("#669966");
        mapButtonToolTipBackgroundColor = Color.decode("#ccffcc");
        scrollBarThumbHighlightColor = Color.gray;
        scrollBarThumbLightShadowColor = Color.white;
        scrollBarThumbDarkShadowColor = Color.black;
        scrollBarThumbColor = Color.decode("#669966");
        scrollBarTrackColor = Color.white;
        scrollBarTrackHighlightColor = Color.white;
        pageBrowserBackgroundColor = Color.white;
        pageBrowserPanelColor = Color.white;
        adminInterfacePanelColor = Color.decode("#a0a0a0");
        adminInterfacePanelForgroundColor = Color.decode("#a0a0a0");
        adminInterfaceBorderColor = Color.decode("#a0a0a0");
        adminInterfaceLabelForegroundColor = Color.black;
        adminInterfaceLabelFont = new Font("Helvetica", 1, 12);
        adminInterfaceTextComponentBackgroundColor = Color.white;
        adminInterfaceTextComponentForegroundColor = Color.black;
        adminInterfaceTextComponentSelectionColor = Color.decode("#ccffcc");
        adminInterfaceTextComponentFont = new Font("Helvetica", 0, 12);
        organizerSelectionColor = Color.decode("#ccffcc");
        organizerSystemColor = new Color(192, 192, 192);
        organizerTextColor = new Color(26, 78, 26);
        organizerBackGroundColor = new Color(182, 178, 199);
        organizerDialogFont = new Font("Helvetica", 1, 12);
        organizerSecondaryDialogFont = new Font("Helvetica", 0, 12);
        mapLogo = new ImageIcon(ImageUtils.getImageFromJar("images/maplogo.gif", new MapButton()));
        potentialEnergyColor = Color.blue;
        keneticEnergyColor = Color.red;
        kineticEnergyColor = Color.red;
        energyColor = Color.green;
        positionVectorColor = new Color(0, 174, 239);
        positionVectorComponentColor = new Color(171, 225, 250);
        normalForceVectorColor = new Color(0, 0, 255);
        normalForceVectorComponentColor = new Color(179, 178, 255);
        frictionForceVectorColor = new Color(255, 0, 0);
        ficticiousForceVectorColor = Color.black;
        frictionForceVectorComponentColor = new Color(255, 0, 255);
        weightForceVectorColor = new Color(0, 155, 0);
        weightForceVectorComponentColor = new Color(179, 255, 178);
        forceGravityVectorColor = Color.decode("#039622");
        accelerationVectorColor = new Color(255, 175, 0);
        accelerationVectorComponentColor = new Color(255, 209, 178);
        acceletationDueToGravityVectorColor = new Color(0, 255, 0);
        acceletationDueToGravityVectorComponentColor = new Color(128, 255, 229);
        velocityVectorColor = new Color(236, 0, 140);
        velocityVectorComponentColor = new Color(248, 193, 217);
        velocityLabFrameVectorColor = new Color(236, 0, 140);
        velocityMovingFrameVectorColor = Color.cyan;
        velocityMovingFrameLabFrameVectorColor = Color.yellow;
        momentumVectorColor = new Color(51, 155, 255);
        distanceVectorColor = Color.green;
    }
}
