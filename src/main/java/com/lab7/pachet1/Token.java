package com.lab7.pachet1;

public class Token {
    private int tokenValue;

    public int getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(int tokenValue) {
        this.tokenValue = tokenValue;
    }

    /**
     * aceasta functie a fost special facuta pentru momentele in care tokenul poate lua orice valoare
     */
    public void setTokenValue() {
        this.tokenValue = 0;
    }

    public Token(int tokenValue) {
        this.tokenValue = tokenValue;
    }

    /**
     * aceast constructor a fost special facuta pentru momentele in care tokenul poate lua orice valoare
     */
    public Token() {
        this.tokenValue = 0;
    }

    @Override
    public String toString() {
        return "Value: " + tokenValue + "; ";
    }
}
