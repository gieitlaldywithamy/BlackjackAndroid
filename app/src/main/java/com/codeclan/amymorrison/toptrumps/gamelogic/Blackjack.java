package com.codeclan.amymorrison.toptrumps.gamelogic;

import com.codeclan.amymorrison.toptrumps.R;
import com.codeclan.amymorrison.toptrumps.deck.Card;
import com.codeclan.amymorrison.toptrumps.gamelogic.Player;
import com.codeclan.amymorrison.toptrumps.deck.Deck;

/**
 * Created by amymorrison on 26/01/2018.
 */

public class Blackjack {

    private Player player;
    private Dealer dealer;
    private Deck deck;

    public Blackjack(Player player, Dealer dealer){
        this.player = player;
        this.dealer = dealer;
        this.deck = dealer.getDeck();
    }

    public void newGame(){
        this.player.emptyHand();
        this.dealer.emptyHand();
        this.player.newGame();
    }

    public Dealer getDealer(){
        return this.dealer;
    }

    public Player getPlayer(){
        return this.player;
    }


    public void shuffleDeck(){
        deck.shuffle();
    }

    public void initialDeal(){
        player.drawCard(dealer.dealCard());
        dealer.drawCard(dealer.dealCard());
        player.drawCard(dealer.dealCard());
        dealer.drawHiddenCard(dealer.dealCard());

    }

    public void playerHit(){

        player.drawCard(dealer.dealCard());
        dealer.turnOverHiddenCard();

    }

    public void playerStand() {

        dealer.move();
    }

    public Player getWinner() {
        if (player.isBust())
            return dealer;
        else if (dealer.isBust()) {
            player.increaseWinnings((player.getBet()*3/2)+player.getBet());
            return player;
        }
        else if (dealer.checkFiveCardTrick()){
            return dealer;
        }
        else if (player.checkFiveCardTrick()) {
            player.increaseWinnings((player.getBet()*3/2)+player.getBet());
            return player;
        }
        else if (player.handValue() >= dealer.handValue()){
            player.increaseWinnings((player.getBet()*3/2)+player.getBet());
            return player;

        } else {
            return dealer;
        }
    }

    public void newDeck() {
        this.deck = new Deck();
    }

    public boolean playerWon() {
        return (player.isBust() || player.hasBlackJack());
    }

    public boolean anyoneHasBlackjack() {
        return (player.hasBlackJack() || dealer.hasBlackJack());
    }
}
