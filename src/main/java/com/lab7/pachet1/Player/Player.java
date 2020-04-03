package com.lab7.pachet1.Player;

import com.lab7.pachet1.Board;
import com.lab7.pachet1.Token;

import java.util.ArrayList;
import java.util.List;


public class Player implements IPlayer {
    private int playerId;
    private String name;
    private Board board;
    private List<Token> playerTokens = new ArrayList<>();

    public Player(int playerId, String name, Board board) {
        this.playerId = playerId;
        this.name = name;
        this.board = board;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public List<Token> getPlayerTokens() {
        return playerTokens;
    }

    @Override
    public void run() {
        while (this.board.getGame().isOngoingGame()) {
            takeRounds();
        }
    }

    /**
     * metoda in care toti playerii sunt sincronizati pe accesul la board. Se verifica daca id-ul curent al playerului este acelasi cu id-ul pe care il
     * ofera board-ul. Daca nu, se asteapta egalitatea, iar daca intre timp jocul s-a incheiat se termina threadul
     * daca vine randul playerului, se verifica existenta unui token, si este ales in functie de player un token
     * daca nu mai sunt tokeni, se opreste jocul. Dupa alegere se incrementeaza in Z(numarPlayeri) randul curent si sunt notificate toate threadurile
     * anuntam de fiecare data cand vedem ca jocul e gata ca poate mai sunt playeri care asteapta si nu stiu ca de fapt e gata
     */
    private void takeRounds() {

        synchronized (board) {
            while (board.getPlayerRound() != this.playerId) {
                if (!board.getGame().isOngoingGame()) {
                    board.notifyAll();
                    return;
                }
                try {
                    board.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println("Player " + playerId + " is playing!");
            if (!board.getGame().isOngoingGame()) {
                board.notifyAll();
                return;
            }
            List<Token> tokens = board.getTokenList();
            if (tokens.isEmpty()) {
                this.board.getGame().setOngoingGame(false);
                board.notifyAll();
            } else {
                choose(tokens);
                board.setPlayerRound((board.getPlayerRound() + 1) % board.getNumberOfPlayers());
                board.notifyAll();
            }
            return;
        }

    }

    /**
     * pentru un player simplu este ales exact primul token disponibil, este eliminat si adaugat in tokenurile playerului curent
     *
     * @param tokens
     */
    public void choose(List<Token> tokens) {
        Token token = tokens.get(0);
        this.board.removeToken(token);
        this.playerTokens.add(token);
    }

    /**
     * calculam lungimea unei progresii si o returnam
     *
     * @return
     */
    @Override
    public int getPoints() {
        if (playerTokens.size() <= 2) return playerTokens.size();
        int expectedValue = playerTokens.get(1).getTokenValue() - playerTokens.get(0).getTokenValue();
        int best = 2;
        int current = 2;
        int last = playerTokens.get(1).getTokenValue();
        for (int i = 2; i < playerTokens.size(); i++) {
            if (playerTokens.get(i).getTokenValue() - last == expectedValue) {
                current++;
                if (current > best) best = current;
            } else {
                expectedValue = playerTokens.get(i).getTokenValue() - last;
                last = playerTokens.get(i).getTokenValue();
                current = 2;
            }
        }
        return best;
    }

    /**
     * comparam numarul de puncte
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(IPlayer o) {
        return this.getPoints() - o.getPoints();
    }
}