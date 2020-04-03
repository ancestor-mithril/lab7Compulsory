package com.lab7.pachet1.Player;

import com.lab7.pachet1.Board;
import com.lab7.pachet1.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ManualPlayer extends Player {

    public ManualPlayer(int playerId, String name, Board board) {
        super(playerId, name, board);
    }

    @Override
    public void choose(List<Token> tokens) {
        Token token = null;
        try {
            token = manualChoice(tokens);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.getBoard().removeToken(token);
        this.getPlayerTokens().add(token);
    }

    /**
     * playerul manual asteapta input de la tastatura.
     *
     * @param tokens
     * @return
     * @throws IOException
     */
    private Token manualChoice(List<Token> tokens) throws IOException {
        System.out.println("Selectati unul dintre indecsii tokenilor de mai jos:");
        for (int i = 0; i < tokens.size(); i++) System.out.println("Token " + i + ": " + tokens.get(i));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int x;
        while (true) {
            System.out.println("Va rugam introduceti un index corect: ");
            try {
                x = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                continue;
            }
            if (x >= tokens.size()) continue;
            System.out.println("A fost ales tokenul " + tokens.get(x));
            break;
        }
        return tokens.get(x);
    }
}
