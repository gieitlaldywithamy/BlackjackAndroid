package com.codeclan.amymorrison.toptrumps.gamelogic;

import com.codeclan.amymorrison.toptrumps.deck.Card;

/**
 * Created by amymorrison on 01/02/2018.
 */

public interface iPlayer {
    public Hand getHand();

    public int handValue();

    public void drawCard(Card card);

}
