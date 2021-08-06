// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.contentNavigator;

public class AnswerFormResponse
{
    private String responseURLString;
    private String responseLabelString;
    private boolean correct;
    private double lowerBound;
    private double upperBound;
    private double numericalValue;
    
    public AnswerFormResponse() {
        this("", "", false);
    }
    
    public AnswerFormResponse(final String s, final String s2, final boolean b) {
        this(s, s2, b, 0.0, 0.0, 0.0);
    }
    
    public AnswerFormResponse(final String s, final String s2, final boolean b, final double n, final double n2) {
        this(s, s2, b, n, n2, 0.0);
    }
    
    public AnswerFormResponse(final String responseURLString, final String responseLabelString, final boolean correct, final double lowerBound, final double upperBound, final double numericalValue) {
        this.responseURLString = responseURLString;
        this.responseLabelString = responseLabelString;
        this.correct = correct;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.numericalValue = numericalValue;
    }
    
    public void setNumericalValue(final double numericalValue) {
        this.numericalValue = numericalValue;
    }
    
    public double getNumericalValue() {
        return this.numericalValue;
    }
    
    public double getLowerBound() {
        return this.lowerBound;
    }
    
    public String getResponseURLString() {
        return this.responseURLString;
    }
    
    public double getUpperBound() {
        return this.upperBound;
    }
    
    public boolean isCorrect() {
        return this.correct;
    }
    
    public void setCorrect(final boolean correct) {
        this.correct = correct;
    }
    
    public void setLowerBound(final double lowerBound) {
        this.lowerBound = lowerBound;
    }
    
    public void setResponseURLString(final String responseURLString) {
        this.responseURLString = responseURLString;
    }
    
    public void setUpperBound(final double upperBound) {
        this.upperBound = upperBound;
    }
    
    public String getResponseLabelString() {
        return this.responseLabelString;
    }
    
    public void setResponseLabelString(final String responseLabelString) {
        this.responseLabelString = responseLabelString;
    }
}
