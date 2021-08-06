// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.Vector;

public class PropertyManager
{
    static Vector bundles;
    static Vector locales;
    static Vector bundleNames;
    
    public static String getProperty(final String s, final Locale locale, final String key) {
        return getResourceBundle(s, locale).getString(key);
    }
    
    public static ResourceBundle getResourceBundle(final String s, final Locale locale) {
        if (PropertyManager.bundles == null) {
            PropertyManager.bundles = new Vector();
        }
        if (PropertyManager.locales == null) {
            PropertyManager.locales = new Vector();
        }
        if (PropertyManager.bundleNames == null) {
            PropertyManager.bundleNames = new Vector();
        }
        final Enumeration<String> elements = PropertyManager.bundleNames.elements();
        while (elements.hasMoreElements()) {
            final String s2 = elements.nextElement();
            if (s.equals(s2)) {
                final int index = PropertyManager.bundleNames.indexOf(s2);
                if (locale.equals(PropertyManager.locales.elementAt(index))) {
                    return (ResourceBundle)PropertyManager.bundles.elementAt(index);
                }
                continue;
            }
        }
        final ResourceBundle bundle = ResourceBundle.getBundle(s, locale);
        PropertyManager.bundles.addElement(bundle);
        PropertyManager.locales.addElement(locale);
        PropertyManager.bundleNames.addElement(s);
        return bundle;
    }
}
