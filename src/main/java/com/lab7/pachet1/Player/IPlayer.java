
package com.lab7.pachet1.Player;

import com.lab7.pachet1.Token;

import java.util.Comparator;
import java.util.List;

public interface IPlayer extends Runnable, Comparable<IPlayer> {
    /**
     * metoda generica in care un player poate alege un token din cei oferiti
     *
     * @param tokens
     */
    public void choose(List<Token> tokens);

    /**
     * metoda de calculare a punctajului
     *
     * @return
     */
    public int getPoints();

}
