package com.lab7.pachet1;

import com.lab7.pachet1.Player.ManualPlayer;
import com.lab7.pachet1.Player.Player;
import com.lab7.pachet1.Player.RandomPlayer;
import com.lab7.pachet1.Player.SmartPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private boolean ongoingGame;
    private int n, m;
    private int numberOfPlayers;
    private List<Player> playerList = new ArrayList<>();
    private List<Thread> threadList = new ArrayList<>();
    private Board board;

    /**
     * functie care returneaza daca jocul mai continua sau s-a incheiat
     *
     * @return
     */
    public boolean isOngoingGame() {
        return ongoingGame;
    }

    /**
     * functie care seteaza incheierea jocului
     *
     * @param ongoingGame
     */
    public void setOngoingGame(boolean ongoingGame) {
        this.ongoingGame = ongoingGame;
    }

    /**
     * instantiem jocul cu n, m, si numarul de playeri
     *
     * @param n
     * @param m
     * @param numberOfPlayers
     */
    public Game(int n, int m, int numberOfPlayers) {
        this.n = n;
        this.m = m;
        this.numberOfPlayers = numberOfPlayers;
        init();
    }

    /**
     * initiem tokenii, playerii, exista player manual doar pentru 3 playeri, joaca un om cu calculatorul, in rest exista numai playueri automati
     */
    private void init() {
        List<Token> tokenList = new ArrayList<>();
        for (int i = 0; i < n; i++) tokenList.add(new Token((int) (Math.random() * m + 1)));
        board = new Board(this, numberOfPlayers, tokenList);
        if (numberOfPlayers == 3) {
            playerList.add(new ManualPlayer(0, "ManualPlayer", board));
            playerList.add(new SmartPlayer(1, "SmartPlayer", board));
            playerList.add(new RandomPlayer(2, "RandomPlayer", board));
        } else {
            for (int i = 0; i < numberOfPlayers; i++)
                if (i % 2 == 0)
                    playerList.add(new RandomPlayer(i, "Player" + i, board));
                else
                    playerList.add(new SmartPlayer(i, "Player" + i, board));
        }

        startGame();
    }

    /**
     * instantiem threadurile, si incepem jocul. Cat timp jocul nu s-a incheiat threadul principal doarme. Dupe ce s-a incheiat afisam playerii si punctajele.
     * este setat si su timer de tip daemon care opreste jocul dupa 10 secunde.
     */
    private void startGame() {
        ongoingGame = true;
        for (Player p : playerList) threadList.add(new Thread(p));
        Runnable timer = new Runnable() {
            @Override
            public void run() {
                try {
                    if (numberOfPlayers == 3)
                        Thread.sleep(60000);
                    else
                        Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Stopping game!");
                setOngoingGame(false);
            }
        };

        Thread timekeeper = new Thread(timer);
        timekeeper.setDaemon(true);
        timekeeper.start();
        for (Thread t : threadList) t.start();
        while (ongoingGame) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(playerList);
        Player bestPlayer = playerList.get(playerList.size() - 1);
        boolean flag = false;
        if (bestPlayer.getPoints() == bestPlayer.getPlayerTokens().size()) flag = true;
        for (Player p : playerList) {
            if (flag) {
                if (p == bestPlayer) System.out.println(p.getName() + " are " + n + " puncte!");
                else System.out.println(p.getName() + " are " + 0 + " puncte!");
            } else {
                System.out.println(p.getName() + " are " + p.getPoints() + " puncte!");
            }
        }

        System.out.println("Gata!");
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
