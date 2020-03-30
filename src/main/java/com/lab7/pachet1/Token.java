package com.lab7.pachet1;

public class Token {
    private int tokenValue;

    public int getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(int tokenValue) {
        this.tokenValue = tokenValue;
    }

    public void setTokenValue() {
        this.tokenValue = 0;
    }

    public Token(int tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Token() {
        this.tokenValue = 0;
    }


}
