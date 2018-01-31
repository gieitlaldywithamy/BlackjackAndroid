package com.codeclan.amymorrison.toptrumps.gamelogic;

import com.codeclan.amymorrison.toptrumps.gamelogic.Player;
import com.codeclan.amymorrison.toptrumps.deck.Deck;

/**
 * Created by amymorrison on 26/01/2018.
 */

public class Blackjack {

    private Player player;
    private Dealer dealer;
    private Deck deck;

    public Blackjack(){
        this.player = new Player();
        this.deck = new Deck();
        this.dealer = new Dealer(this.deck);
    }

    public void newGame(){
        this.player.emptyHand();
        this.dealer.emptyHand();
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
        dealer.drawCard(dealer.dealCard());
    }

    public void playerHit(){
        player.drawCard(dealer.dealCard());
    }

    public String whoWon(){

        //changed from objects to string to make controller happy
        if (player.isBust())
            return "You lost! Dealer wins!";
        else if(dealer.isBust()){
            return "You won!";
        }
        else if (dealer.checkFiveCardTrick()){
                return "You lost! Dealer wins!";
            }
        else if (player.checkFiveCardTrick()) {
                return "Player wins!";
            }
        else if (player.calculateHandValue() >= dealer.calculateHandValue()){
            return "Player wins!";

        } else {
            return "Dealer wins!";
        }

    }

    public Player getWinner() {
        if (player.isBust())
            return dealer;
        else if (dealer.isBust()) {
            return player;
        }
        else if (dealer.checkFiveCardTrick()){
            return dealer;
        }
        else if (player.checkFiveCardTrick()) {
            return player;
        }
        else if (player.calculateHandValue() >= dealer.calculateHandValue()){
            return player;

        } else {
            return dealer;
        }
    }

    public void newDeck() {
        this.deck = new Deck();
    }
}
