package com.codeclan.amymorrison.toptrumps.deck;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by amymorrison on 26/01/2018.
 */

public class Deck {

    LinkedList<Card> cards;


    public Deck(){
        this.cards = buildDeck();
    }

    private LinkedList<Card> buildDeck() {

        LinkedList<Card> cardDeck = new LinkedList<>();

        int i = 0;
        for (Rank rank: Rank.values()) {
            for (Suit suit: Suit.values()){
                CardImage imageUrl = CardImage.values()[i];
                Card newCard = new Card(suit, rank);
                cardDeck.add(newCard);
                i++;
            }
        }
        return cardDeck;
    }

    public LinkedList<Card> getCards() {
        return this.cards;
    }

    public int cardCount() {
        return this.cards.size();
    }

    public void shuffle(){
        Collections.shuffle(this.cards);
    }

    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    public Card removeFirst() {
        return this.cards.removeFirst();
    }
}
