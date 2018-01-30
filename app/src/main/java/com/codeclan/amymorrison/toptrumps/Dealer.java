package com.codeclan.amymorrison.toptrumps;

/**
 * Created by amymorrison on 27/01/2018.
 */

public class Dealer extends Player{

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
        return calculateHandValue() <= 16;
    }
}
