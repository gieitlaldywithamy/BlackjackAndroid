package com.codeclan.amymorrison.toptrumps;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by amymorrison on 27/01/2018.
 */

public class BlackjackTest {

    Blackjack game;
    Player player;
    Dealer dealer;
    Card three;
    Card eight;
    Card ten;

    @Before
    public void before(){

        player = new Player();

        game = new Blackjack(player);
        dealer = game.getDealer();
        three = new Card(Suit.CLUBS, Rank.THREE, CardImage.THREECLUBS);
        eight = new Card(Suit.HEARTS, Rank.EIGHT, CardImage.EIGHTSPADES);
        ten = new Card(Suit.DIAMONDS, Rank.TEN, CardImage.TENCLUBS);

    }

    @Test
    public void canGetPlayerValue(){
        game.initialDeal();
        assertEquals(22, player.calculateHandValue());

    }

    @Test
    public void canGetHandScore(){
        game.initialDeal();
        assertEquals(22, player.calculateHandValue());
    }

    @Test
    public void canGetBlackjack(){
        player.drawCard(three);
        player.drawCard(eight);
        player.drawCard(ten);
        assertEquals(player, game.getWinner());
    }

    @Test
    public void startNewGame(){
        player.drawCard(three);
        player.drawCard(ten);
        game.newGame();
        assertEquals(0, player.getPlayerHand().size());
    }
}
