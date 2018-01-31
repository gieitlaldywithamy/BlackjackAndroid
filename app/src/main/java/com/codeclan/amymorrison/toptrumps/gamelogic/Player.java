package com.codeclan.amymorrison.toptrumps.gamelogic;

import com.codeclan.amymorrison.toptrumps.deck.Card;
import com.codeclan.amymorrison.toptrumps.deck.Rank;

import java.util.ArrayList;

/**
 * Created by amymorrison on 26/01/2018.
 */

public class Player {

    private ArrayList<Card> hand;
    private int handScore;
    private int winnings;
    private int wallet;
    private int bet;

    public Player(){
       hand = new ArrayList<>();
       handScore = 0;
       this.winnings = 0;
       this.bet = 0;
       this.wallet = 100;
    }

    public ArrayList<Card> getPlayerHand(){
        return this.hand;
    }

    public void emptyHand(){
        this.hand = new ArrayList<>();
    }

    public void raiseBet(int amount){
        if (this.wallet - amount > 0) {
            this.bet += amount;
            this.wallet -= amount;
        }

    }

    public void spendMoney(int value){
        this.wallet -= value;
    }

    public int getWallet(){
        return this.wallet;
    }

    public boolean handContainsAce(){
        boolean containsAce = false;
        for (Card card: this.hand){
            if (card.getRank().equals(Rank.ACE))
                containsAce = true;
        }
        return containsAce;
    }

    public int countAces(){
        int aces = 0;
        for (Card card: this.hand){
            if (card.getRank().equals(Rank.ACE))
                aces++;
        }
        return aces;
    }
    public void increaseWinnings(int bettingWinnings){
        this.winnings += bettingWinnings;
    }
    public boolean hasBlackJack(){
        return calculateHandValue()==21;
    }

//    public int getHandScore(){
//        return this.handScore;
//    }

    public boolean isBust(){
        //change ace stuff here

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
        int aceCounter = countAces();
        if (score > 21 && aceCounter > 0){
            score -= 10;
            aceCounter--;
        }
        this.handScore = score;
        return this.handScore;
    }

    public boolean checkFiveCardTrick() {
        return (!isBust() && (this.getPlayerHand().size()) == 5);
    }

    protected void setBet(int bet){
        this.bet = bet;
    }
    public int getBet() {
        return this.bet;
    }
}
