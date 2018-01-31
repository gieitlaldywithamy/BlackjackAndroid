package com.codeclan.amymorrison.toptrumps.deck;

/**
 * Created by amymorrison on 26/01/2018.
 */

public class Card {

    private Suit suit;
    private Rank rank;
    private int imageId;
    private boolean isAce;

    public Card(Suit suit, Rank rank, CardImage imageRef) {
        this.suit = suit;
        this.rank = rank;
        this.imageId = imageRef.getImageId();
        this.isAce = this.rank == Rank.ACE;
    }
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }


    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    public int getImageUrl(){
        return this.imageId;
    }

    public int setImageUrl(int drawableID) {
        int realCardValue = getImageUrl();
        this.imageId = drawableID;
        return realCardValue;
    }

    public int getValue() {
        return this.rank.getValue();
    }

    public String prettyName(){
        return rank.toString().toLowerCase() + " of " + suit.toString().toLowerCase();
    }
}
