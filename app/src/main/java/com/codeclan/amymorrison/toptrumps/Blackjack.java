package com.codeclan.amymorrison.toptrumps;

import java.util.ArrayList;

/**
 * Created by amymorrison on 26/01/2018.
 */

public class Blackjack {

    private Player player;
    private Dealer dealer;
    private Deck deck;

    public Blackjack(Player player){
        this.player = player;
        this.deck = new Deck();
        this.dealer = new Dealer(this.deck);
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

    public Player whoWon(){
        if (player.isBust())
            return dealer;
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
}
