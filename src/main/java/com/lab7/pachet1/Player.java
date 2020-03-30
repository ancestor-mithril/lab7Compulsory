package com.lab7.pachet1;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable{
    public String name;
    private Board board;
    private List<Token> playerTokens=new ArrayList<>();


    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    @Override
    public void run() {
        boolean flag=true;
        while(flag){
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException ignored){}
            Token token=board.getToken();
            if (token==null){
                flag=false;
                break;
            }
            playerTokens.add(token);
        }
    }

    public String getName() {
        return name;
    }

    //https://www.geeksforgeeks.org/longest-arithmetic-progression-dp-35/
    int lenghtOfLongestAP()
    {
        int n = playerTokens.size();
        int difference;
        if (playerTokens.size()>2)
            difference=playerTokens.get(1).getTokenValue()-playerTokens.get(0).getTokenValue();
        else
            return playerTokens.size();
        boolean flag=true;
        //System.out.println(playerTokens.get(0).getTokenValue());
        for (int i=1; i<playerTokens.size(); i++){
            if (playerTokens.get(i).getTokenValue()-playerTokens.get(i-1).getTokenValue()!=difference)
            {
                flag=false;
                break;
            }
            //else System.out.println(playerTokens.get(i).getTokenValue()+"  "+playerTokens.get(i-1).getTokenValue());
            //.out.println(playerTokens.get(i).getTokenValue());
        }

        // Create a table and initialize all values as 2. The value of
        // L[i][j] stores LLAP with set[i] and set[j] as first two
        // elements of AP. Only valid entries are the entries where j>i
        int[][] L = new int[n][n];
        int llap = 2;  // Initialize the result

        // Fill entries in last column as 2. There will always be
        // two elements in AP with last number of set as second
        // element in AP
        for (int i = 0; i < n; i++)
            L[i][n-1] = 2;

        // Consider every element as second element of AP
        for (int j=n-2; j>=1; j--)
        {
            // Search for i and k for j
            int i = j-1, k = j+1;
            while (i >= 0 && k <= n-1)
            {
                if (playerTokens.get(i).getTokenValue() + playerTokens.get(k).getTokenValue() < 2*playerTokens.get(j).getTokenValue())
                    k++;

                    // Before changing i, set L[i][j] as 2
                else if (playerTokens.get(i).getTokenValue()+ playerTokens.get(k).getTokenValue() > 2*playerTokens.get(j).getTokenValue())
                {
                    L[i][j] = 2;
                    i--;
                }
                else
                {
                    // Found i and k for j, LLAP with i and j as first two
                    // elements is equal to LLAP with j and k as first two
                    // elements plus 1. L[j][k] must have been filled
                    // before as we run the loop from right side
                    L[i][j] = L[j][k] + 1;

                    // Update overall LLAP, if needed
                    llap = Math.max(llap, L[i][j]);

                    // Change i and k to fill more L[i][j] values for
                    // current j
                    i--; k++;
                }
            }

            // If the loop was stopped due to k becoming more than
            // n-1, set the remaining entities in column j as 2
            while (i >= 0)
            {
                L[i][j] = 2;
                i--;
            }
        }
        return llap;
    }
}
