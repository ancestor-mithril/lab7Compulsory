package com.lab7.pachet1.Player;

import com.lab7.pachet1.Board;
import com.lab7.pachet1.Token;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SmartPlayer extends Player {
    public SmartPlayer(int playerId, String name, Board board) {
        super(playerId, name, board);
    }

    @Override
    public void choose(List<Token> tokens) {
        Token token = smartChoice(tokens);
        this.getBoard().removeToken(token);
        this.getPlayerTokens().add(token);
    }

    /**
     * playerul destept verifica daca poate continua progresia lui geometrica, si daca nu poate,
     * incearca sa strice progresia geometrica a altora, de la cel mai bun player, pana la cel mai prost
     *
     * @param tokens
     * @return
     */
    private Token smartChoice(List<Token> tokens) {
        if (getPlayerTokens().size() < 2) return tokens.get(0);
        int expectedValue = getPlayerTokens().get(getPlayerTokens().size() - 1).getTokenValue()
                + getPlayerTokens().get(getPlayerTokens().size() - 1).getTokenValue()
                - getPlayerTokens().get(getPlayerTokens().size() - 2).getTokenValue();
        for (Token t : tokens) if (t.getTokenValue() == expectedValue) return t;
        List<Player> otherPlayers = getBoard().getGame().getPlayerList();
        Collections.sort(otherPlayers);
        for (int i = otherPlayers.size() - 1; i >= 0; i--) {
            if (otherPlayers.get(i) == this) continue;
            Player currentBestPlayer = otherPlayers.get(i);
            if (currentBestPlayer.getPlayerTokens().size() < 2) continue;
            expectedValue = currentBestPlayer.getPlayerTokens().get(getPlayerTokens().size() - 1).getTokenValue()
                    + currentBestPlayer.getPlayerTokens().get(getPlayerTokens().size() - 1).getTokenValue()
                    - currentBestPlayer.getPlayerTokens().get(getPlayerTokens().size() - 2).getTokenValue();
            for (Token t : tokens) if (t.getTokenValue() == expectedValue) return t;
        }
        return tokens.get(0);
    }
}
