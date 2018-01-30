package com.codeclan.amymorrison.toptrumps;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by amymorrison on 30/01/2018.
 */

public class PlayerTest {

    Dealer dealer;
    Card card1;
    Card card2;
    Card card3;
    Card ace1;
    Card ace2;
    Player player;

    @Before
    public void before() {
        dealer = new Dealer(new Deck());
        card1 = new Card(Suit.HEARTS, Rank.NINE, CardImage.NINEHEARTS);
        card2 = new Card(Suit.SPADES, Rank.JACK, CardImage.JACKSPADES);
        card3 = new Card(Suit.HEARTS, Rank.EIGHT, CardImage.EIGHTHEARTS);
        ace1 = new Card(Suit.DIAMONDS, Rank.ACE, CardImage.ACEDIAMONDS);
        ace2 = new Card(Suit.SPADES, Rank.ACE, CardImage.ACESPADES);
        player = new Player();
    }

    @Test
    public void handDoesntContainsAce(){
        dealer.drawCard(card2);
        assertEquals(false, dealer.handContainsAce());
    }

    @Test
    public void handContainsAce(){
        dealer.drawCard(card1);
        dealer.drawCard(ace1);
        dealer.drawCard(card2);
        dealer.drawCard(ace2);
        dealer.drawCard(card1);
        assertTrue(dealer.handContainsAce());
    }

    @Test
    public void playerCanDrawCard(){
        player.drawCard(card2);
        assertEquals(1, player.getPlayerHand().size());
    }

    @Test
    public void playerHasValue17(){
        player.drawCard(card1);
        player.drawCard(card3);
        assertEquals(17, player.calculateHandValue());
    }


}
