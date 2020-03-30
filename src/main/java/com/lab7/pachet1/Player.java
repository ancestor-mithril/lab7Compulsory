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

    public int getPoints(){
        int difference;
        if (playerTokens.size()>2)
            difference=playerTokens.get(1).getTokenValue()-playerTokens.get(0).getTokenValue();
        else
            return playerTokens.size();
        boolean flag=true;
        System.out.println(playerTokens.get(0).getTokenValue());
        for (int i=1; i<playerTokens.size(); i++){
            if (playerTokens.get(i).getTokenValue()-playerTokens.get(i-1).getTokenValue()!=difference)
            {
                flag=false;
                break;
            }
            System.out.println(playerTokens.get(i).getTokenValue());
        }
        if (flag==true)
            return -1;
        int lg=playerTokens.size();
        int[] best=new int[lg];
        int[] poz=new int[lg];
        best[lg-1]=1;
        poz[lg-1]=-1;
        int max=1;
        int p=lg-1;
        for (int i=lg-2; i>=1; i--){
            best[i]=1;
            poz[i]=-1;
            for (int j=i+1; j<lg; j++){
                if (playerTokens.get(i).getTokenValue()==playerTokens.get(i).getTokenValue()-difference && best[i]<best[j]+1){
                    best[i]=best[j]+1;
                    poz[i]=j;
                    if(best[i]>max) {
                        max=best[i];p=i;
                    }
                }
            }
        }
        int i=p;
        int val=0;
        while (i!=-1){
            i=poz[i];
            val++;
        }
        return val;
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

        for (int i=1; i<playerTokens.size(); i++){
            if (playerTokens.get(i).getTokenValue()-playerTokens.get(i-1).getTokenValue()!=difference)
            {
                flag=false;
                break;
            }

        }


        int[][] L = new int[n][n];
        int llap = 2;


        for (int i = 0; i < n; i++)
            L[i][n-1] = 2;

        for (int j=n-2; j>=1; j--)
        {

            int i = j-1, k = j+1;
            while (i >= 0 && k <= n-1)
            {
                if (playerTokens.get(i).getTokenValue() + playerTokens.get(k).getTokenValue() < 2*playerTokens.get(j).getTokenValue())
                    k++;

                else if (playerTokens.get(i).getTokenValue()+ playerTokens.get(k).getTokenValue() > 2*playerTokens.get(j).getTokenValue())
                {
                    L[i][j] = 2;
                    i--;
                }
                else
                {

                    L[i][j] = L[j][k] + 1;

                    llap = Math.max(llap, L[i][j]);

                    i--; k++;
                }
            }

            while (i >= 0)
            {
                L[i][j] = 2;
                i--;
            }
        }
        return llap;
    }
}
