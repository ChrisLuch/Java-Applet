// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import java.util.Enumeration;
import java.util.Vector;
import java.applet.Applet;

public class ParameterUtils
{
    public static boolean getBooleanParameter(final Applet applet, final String name, final boolean b) {
        final String parameter = applet.getParameter(name);
        try {
            if (parameter.equalsIgnoreCase("false")) {
                return false;
            }
            if (parameter.equalsIgnoreCase("true")) {
                return true;
            }
        }
        catch (NullPointerException ex) {}
        final Vector<String> vector = new Vector<String>();
        vector.addElement("true");
        vector.addElement("false");
        printError(name, parameter, vector, b + "");
        return b;
    }
    
    public static String getStringParameter(final Applet applet, final String name, final String s) {
        final String parameter = applet.getParameter(name);
        if (parameter == null) {
            printError(name, null, null, s.toString());
            return s;
        }
        return parameter;
    }
    
    public static String getStringParameterQuietly(final Applet applet, final String name, final String s) {
        final String parameter = applet.getParameter(name);
        if (parameter == null) {
            return s;
        }
        return parameter;
    }
    
    public static String getStringParameter(final Applet applet, final String s, final Vector vector, final String s2) {
        final String parameter = applet.getParameter(s);
        if (parameter != null) {
            final Enumeration<String> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final String nextElement = elements.nextElement();
                if (!(nextElement instanceof String)) {
                    System.out.println("Error --- ParameterUtils.getStringParameter( ...\"" + s + "\"... ) - non-String element in acceptableValues vector. Element is: " + nextElement.toString());
                    break;
                }
                if (parameter.equalsIgnoreCase(nextElement)) {
                    return parameter;
                }
            }
        }
        printError(s, parameter, vector, s2);
        return s2;
    }
    
    public static String getStringParameterQuietly(final Applet applet, final String s, final Vector vector, final String s2) {
        final String parameter = applet.getParameter(s);
        if (parameter != null) {
            final Enumeration<String> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final String nextElement = elements.nextElement();
                if (!(nextElement instanceof String)) {
                    System.out.println("Error --- ParameterUtils.getStringParameter( ...\"" + s + "\"... ) - non-String element in acceptableValues vector. Element is: " + nextElement.toString());
                    break;
                }
                if (parameter.equalsIgnoreCase(nextElement)) {
                    return parameter;
                }
            }
            printError(s, parameter, vector, s2);
        }
        return s2;
    }
    
    public static double getDoubleParameter(final Applet applet, final String str, final String str2) {
        final String stringParameter = getStringParameter(applet, str, str2);
        try {
            return new Double(stringParameter);
        }
        catch (NumberFormatException ex) {
            System.out.println("Error --- ParameterUtils.getDoubleParameter( ...\"" + str + "\"... ) - " + "NumberFormatException in defaultValue. defaultValue entered as: \"" + str2 + "\"" + "  - Returning value 0.0");
            return 0.0;
        }
    }
    
    public double getIntParameter(final Applet applet, final String str, final String str2) {
        final String stringParameter = getStringParameter(applet, str, str2);
        try {
            return new Integer(stringParameter);
        }
        catch (NumberFormatException ex) {
            System.out.println("Error --- ParameterUtils.getIntParameter( ...\"" + str + "\"... ) - " + "NumberFormatException in defaultValue. defaultValue entered as: \"" + str2 + "\"" + "  - Returning value 0");
            return 0.0;
        }
    }
    
    private static void printError(final String s, final String str, final Vector vector, final String str2) {
        if (str == null) {
            System.out.println("Error --- \"" + s + "\" parameter is missing.");
        }
        else {
            System.out.println("Error --- \"" + s + "\" parameter is not an acceptable value. It is currently set to \"" + str + "\"");
        }
        if (vector == null || vector.size() == 0) {
            System.out.println("      --- No acceptable values where given to ParameterUtils. Perhaps any value is acceptable.");
        }
        else {
            System.out.println("      --- Acceptable values for \"" + s + "\" are: (These are case-insensitive)");
            final Enumeration<Object> elements = vector.elements();
            while (elements.hasMoreElements()) {
                System.out.println("      ---    " + elements.nextElement().toString());
            }
        }
        System.out.println("      ---  The default setting will be used: <PARAM NAME = \"" + s + "\" VALUE = \"" + str2 + "\">");
    }
}
