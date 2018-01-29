package com.codeclan.amymorrison.toptrumps;

import java.util.ArrayList;

/**
 * Created by amymorrison on 26/01/2018.
 */

class Player {

    private ArrayList<Card> hand;
    private int handScore;

    public Player(){
       hand = new ArrayList<>();
       handScore = 0;
    }

    public ArrayList<Card> getPlayerHand(){
        return this.hand;
    }

    public boolean hasBlackJack(){
        return calculateHandValue()==21;
    }

//    public int getHandScore(){
//        return this.handScore;
//    }

    public boolean isBust(){
        return calculateHandValue() > 21;
    }

    public void drawCard(Card card){
        this.hand.add(card);
    }

    public int calculateHandValue(){
        int score = 0;
        for (Card card : this.hand) {
            score += card.getValue();
        }
        this.handScore = score;
        return this.handScore;
    }

    public boolean checkFiveCardTrick() {
        return (!isBust() && (this.getPlayerHand().size()) == 5);
    }

}
