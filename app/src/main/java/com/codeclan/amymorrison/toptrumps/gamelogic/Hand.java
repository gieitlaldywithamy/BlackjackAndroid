package com.codeclan.amymorrison.toptrumps.gamelogic;

import com.codeclan.amymorrison.toptrumps.deck.Card;
import com.codeclan.amymorrison.toptrumps.deck.Rank;

import java.util.ArrayList;

/**
 * Created by amymorrison on 31/01/2018.
 */

public class Hand {

    protected ArrayList<Card> cards;
    private boolean isSplit;

    public Hand(){
        this.cards = new ArrayList<>();
        this.isSplit = false;
    }
    public ArrayList<Card> getCards() {
        return this.cards;
    }


    public boolean handContainsAce(){
        boolean containsAce = false;
        for (Card card: this.cards){
            if (card.getRank().equals(Rank.ACE))
                containsAce = true;
        }
        return containsAce;
    }

    public int aceCount(){
        int aces = 0;
        for (Card card: this.cards){
            if (card.getRank().equals(Rank.ACE))
                aces++;
        }
        return aces;
    }

    public int handValue(){
        int score = 0;
        for (Card card : this.cards) {
            score += card.getValue();
        }
        int aceCounter = aceCount();
        while (score > 21 && aceCounter > 0){
            score -= 10;
            aceCounter--;
        }
        return score;
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public int cardCount() {
        return this.cards.size();
    }
}
