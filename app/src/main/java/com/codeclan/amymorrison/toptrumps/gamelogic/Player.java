package com.codeclan.amymorrison.toptrumps.gamelogic;

import com.codeclan.amymorrison.toptrumps.deck.Card;
import com.codeclan.amymorrison.toptrumps.deck.Rank;

import java.util.ArrayList;

/**
 * Created by amymorrison on 26/01/2018.
 */

public class Player {

    protected Hand hand;
    private int handScore;
    private int winnings;
    private int wallet;
    private int bet;

    public Player(){
       this.hand = new Hand();
       handScore = 0;
       this.winnings = 0;
       this.bet = 0;
       this.wallet = 100;
    }

    public ArrayList<Card> getPlayerHand(){
        return this.hand.getCards();
    }

    public void emptyHand(){
        this.hand = new Hand();
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


    public void increaseWinnings(int bettingWinnings){
        this.winnings += bettingWinnings;
    }
    public boolean hasBlackJack(){
        return this.hand.handValue()==21;
    }

    public int handValue(){
        return this.hand.handValue();
    }
    public boolean isBust(){
        return this.hand.handValue() > 21;
    }

    public void doubleDown(){

    }


    public void drawCard(Card card){
        this.hand.add(card);
    }



    public boolean checkFiveCardTrick() {
        return (!isBust() && (this.getPlayerHand().size()) == 5);
    }

    public int getBet() {
        return this.bet;
    }

    public int getWinnings() {
        return this.winnings;
    }

    public void newGame() {
        this.bet = 0;
        this.winnings = 0;
    }
    @Override
    public String toString(){
        return getClass().getSimpleName();
    }

    public void profit(int playerBanked) {

        this.wallet += playerBanked;
    }

    public Hand getHand() {
        return this.hand;
    }
}
