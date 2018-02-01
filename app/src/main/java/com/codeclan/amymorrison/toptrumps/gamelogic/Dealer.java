package com.codeclan.amymorrison.toptrumps.gamelogic;

import com.codeclan.amymorrison.toptrumps.R;
import com.codeclan.amymorrison.toptrumps.deck.Card;
import com.codeclan.amymorrison.toptrumps.deck.Deck;
import com.codeclan.amymorrison.toptrumps.gamelogic.Player;

/**
 * Created by amymorrison on 27/01/2018.
 */

public class Dealer extends Player {

    private Deck deck;
    static int holeCardDrawable;
    private Card holeCard;

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
        turnOverHiddenCard();
        while (shouldHit()){
            drawCard(dealCard());
        }
    }

    public void turnOverHiddenCard(){
        System.out.println(this.hand.cards.get(1).getImageUrl());
        this.hand.cards.get(1).setImageUrl(this.holeCardDrawable);
    }

    public void drawHiddenCard(Card card){
//        Card holeCard = this.cards.get(1);
        this.holeCardDrawable = card.getImageUrl();
        this.holeCard = card;
        card.setImageUrl(R.drawable.dealer_card_back);
        System.out.println(card);
        System.out.println(this.hand.getCards().size());
        this.hand.add(card);

    }

}
