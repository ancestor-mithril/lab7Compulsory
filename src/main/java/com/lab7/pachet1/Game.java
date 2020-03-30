package com.lab7.pachet1;

import java.util.ArrayList;
import java.util.List;

public class Game {
    int n;
    int m;
    private Board board;
    private List<Player> playerList=new ArrayList<>();
    private List<Thread> threadList=new ArrayList<>();

    public Game(int n, int m, List<String> names){
        this.n=n;
        this.m=m;
        init(names);
    }

    private void init(List<String> names) {
        board=new Board(n, m);
        for (String name:names){
            playerList.add(new Player(name, board));
        }
    }
    public void run(){
        for (Player player : playerList){
            threadList.add(new Thread(player));
        }
        for (Thread t : threadList){
            t.start();
        }
        try {
            for (Thread t : threadList) {
                t.join();
            }
        }
        catch (InterruptedException ex){
            System.out.println("Error!");
            return;
        }
        int points;
        boolean flag=false;
        for (Player player : playerList){
            if (flag)
                points=0;
            else{
                points=player.lenghtOfLongestAP();
                if (points==-1){
                    points=n;
                    flag=true;
                }

            }
            System.out.println(player.getName() + "  punctaj:  "+points);
        }
    }
}
