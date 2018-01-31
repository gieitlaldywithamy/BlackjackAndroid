package com.codeclan.amymorrison.toptrumps.gamelogic;

import com.codeclan.amymorrison.toptrumps.deck.Card;

/**
 * Created by amymorrison on 31/01/2018.
 */

public interface IHand {

    int value();

    int aceCount();

    boolean checkFiveCardTrick();

    void drawCard(Card card);

}
