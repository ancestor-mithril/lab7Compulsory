package com.lab7.pachet1;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Token> tokenList=new ArrayList<>();
    public Board(int n, int m){
        for (int i=0; i<n; i++)
            tokenList.add(new Token((int)(Math.random()*m)));
        //System.out.println(tokenList.toString());
    }

    public Board(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public synchronized Token getToken(){
        if (tokenList.isEmpty())
            return null;
        Token token=tokenList.get(0);
        tokenList.remove(token);
        return token;
    }
}
