package com.lab7.pachet1;

import java.util.Arrays;

public class Main {

    /**
     * jocul se porneste cu new Game(numar de tokeni, valoarea maxima a tokenilor, numarul de playeri), cu timp maxim de joc 10 sec.
     * jucatorul manual se instantiaza daca si numai daca numarul de playeri este 3, timpul de joc este 1 min
     *
     * @param args
     */
    public static void main(String[] args) {
        new Game(15, 35, 3);
    }
}
