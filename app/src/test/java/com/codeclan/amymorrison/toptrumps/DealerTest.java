package com.codeclan.amymorrison.toptrumps;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by amymorrison on 27/01/2018.
 */

public class DealerTest {

    Dealer dealer;

    @Before
    public void before(){
        dealer = new Dealer(new Deck());
    }

    @Test
    public void canGetDealerPlayerScore(){
        assertEquals(0, dealer.calculateHandValue());
    }

    @Test
    public void canCountCards(){
        assertEquals(52, dealer.getDeck().getCards().size());
    }

    @Test
    public void canDealCard(){
        //why doesnt work?
        Deck sampleDeck = new Deck();
        Card firstCard = sampleDeck.getCards().getFirst();
        System.out.println(firstCard.prettyName() + dealer.dealCard().prettyName());
        assertEquals(firstCard.prettyName(), dealer.dealCard().prettyName());
    }
}
