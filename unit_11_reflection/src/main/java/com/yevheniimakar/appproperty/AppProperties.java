package com.yevheniimakar.appproperty;

import com.yevheniimakar.annotations.PropertyKey;

import java.time.LocalDate;



public class AppProperties {

    @PropertyKey ("property.string")
    private String stringProp;

    @PropertyKey ("property.integer")
    private int intProp;

    @PropertyKey ("property.double")
    private double doubleProp;

    @PropertyKey ("property.long")
    private long longProp;

    @PropertyKey ("property.float")
    private float floatProp;

    @PropertyKey ("property.date")
    private LocalDate dateProp;

    @PropertyKey ("property.char")
    private char charProp;

    public String getStringProp() {
        return stringProp;
    }

    public void setStringProp(String stringProp) {
        this.stringProp = stringProp;
    }

    public int getIntProp() {
        return intProp;
    }

    public void setIntProp(int intProp) {
        this.intProp = intProp;
    }

    public double getDoubleProp() {
        return doubleProp;
    }

    public void setDoubleProp(double doubleProp) {
        this.doubleProp = doubleProp;
    }

    public long getLongProp() {
        return longProp;
    }

    public void setLongProp(long longProp) {
        this.longProp = longProp;
    }

    public float getFloatProp() {
        return floatProp;
    }

    public void setFloatProp(float floatProp) {
        this.floatProp = floatProp;
    }

    public LocalDate getDateProp() {
        return dateProp;
    }

    public void setDateProp(LocalDate dateProp) {
        this.dateProp = dateProp;
    }

    public char getCharProp() {
        return charProp;
    }

    public void setCharProp(char charProp) {
        this.charProp = charProp;
    }

    @Override
    public String toString() {
        return "AppProperties{" + "stringProp='" + stringProp + '\'' + ", intProp=" + intProp + ", doubleProp=" + doubleProp + ", longProp=" + longProp + ", floatProp=" + floatProp + ", dateProp=" + dateProp + ", charProp=" + charProp + '}';
    }

}
