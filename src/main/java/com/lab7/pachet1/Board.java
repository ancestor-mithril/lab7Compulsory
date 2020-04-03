package com.lab7.pachet1;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Game game;
    private int numberOfPlayers;
    private int playerRound;
    private List<Token> tokenList = new ArrayList<>();

    /**
     * in variabila player round retinem tura playerului curent
     *
     * @param game
     * @param numberOfPlayers
     * @param tokenList
     */
    public Board(Game game, int numberOfPlayers, List<Token> tokenList) {
        this.game = game;
        this.numberOfPlayers = numberOfPlayers;
        this.tokenList = tokenList;
        playerRound = 0;
    }

    public Game getGame() {
        return game;
    }

    public int getPlayerRound() {
        return playerRound;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setPlayerRound(int playerRound) {
        this.playerRound = playerRound;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public void removeToken(Token token) {
        if (!tokenList.contains(token)) throw new IllegalArgumentException("Token not in list!");
        tokenList.remove(token);
    }
}
