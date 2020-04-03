package com.lab7.pachet1.Player;

import com.lab7.pachet1.Board;
import com.lab7.pachet1.Token;

import java.util.List;

public class RandomPlayer extends Player {
    public RandomPlayer(int playerId, String name, Board board) {
        super(playerId, name, board);
    }

    /**
     * pentru playerul random se alege un token random din cele disponibile
     *
     * @param tokens
     */
    @Override
    public void choose(List<Token> tokens) {
        Token token = tokens.get((int) (Math.random() * tokens.size()));
        this.getBoard().removeToken(token);
        this.getPlayerTokens().add(token);
    }
}
