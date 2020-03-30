package com.lab7.pachet1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        String[] players = new String[] {"da", "da1", "da2"};
        Game game=new Game(30, 20, Arrays.asList(players));
        game.run();
    }
}
