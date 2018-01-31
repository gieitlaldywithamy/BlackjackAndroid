package com.codeclan.amymorrison.toptrumps.gamelogic;

import com.codeclan.amymorrison.toptrumps.deck.Card;
import com.codeclan.amymorrison.toptrumps.deck.Deck;
import com.codeclan.amymorrison.toptrumps.gamelogic.Player;

/**
 * Created by amymorrison on 27/01/2018.
 */

public class Dealer extends Player {

    private Deck deck;

    public Dealer(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck(){
        return this.deck;
    }

    public Card dealCard() {
        while(!deck.isEmpty()){
            return deck.removeFirst();
        }
        return null;
    }

    public boolean shouldHit() {
        return handValue() <= 16;
    }

    public void move() {
        while (shouldHit()){
            drawCard(dealCard());
        }
    }
}
