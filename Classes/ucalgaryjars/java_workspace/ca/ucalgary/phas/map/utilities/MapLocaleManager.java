// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.util.Vector;
import java.util.Locale;

public final class MapLocaleManager
{
    public static final Locale LOCALE_EN_CA;
    public static final Locale LOCALE_FR_CA;
    public static final Locale LOCALE_NL_NL;
    private static MapLocaleManager singletonInstance;
    private Locale currentLocale;
    private Vector genListeners;
    private Vector sysListeners;
    
    protected static MapLocaleManager getLocaleManager() {
        if (MapLocaleManager.singletonInstance == null) {
            MapLocaleManager.singletonInstance = new MapLocaleManager();
        }
        return MapLocaleManager.singletonInstance;
    }
    
    private MapLocaleManager() {
        this(Locale.getDefault());
    }
    
    private MapLocaleManager(final Locale currentLocale) {
        this.currentLocale = currentLocale;
        this.genListeners = new Vector();
        this.sysListeners = new Vector();
    }
    
    public static void addLocaleListener(final MapLocaleListener e) {
        getLocaleManager().genListeners.add(e);
    }
    
    public static void removeLocaleListener(final MapLocaleListener o) {
        getLocaleManager().genListeners.remove(o);
    }
    
    public static void addSystemLocaleListener(final MapLocaleListener e) {
        getLocaleManager().sysListeners.add(e);
    }
    
    public static Locale setLocale(final Locale currentLocale) {
        final MapLocaleManager localeManager = getLocaleManager();
        localeManager.currentLocale = currentLocale;
        for (int i = 0; i < localeManager.sysListeners.size(); ++i) {
            ((MapLocaleListener)localeManager.sysListeners.get(i)).localeChanged();
        }
        for (int j = 0; j < localeManager.genListeners.size(); ++j) {
            ((MapLocaleListener)localeManager.genListeners.get(j)).localeChanged();
        }
        return currentLocale;
    }
    
    public static Locale getLocale() {
        return getLocaleManager().currentLocale;
    }
    
    static {
        LOCALE_EN_CA = new Locale("en", "CA");
        LOCALE_FR_CA = new Locale("fr", "CA");
        LOCALE_NL_NL = new Locale("nl", "NL");
    }
}
