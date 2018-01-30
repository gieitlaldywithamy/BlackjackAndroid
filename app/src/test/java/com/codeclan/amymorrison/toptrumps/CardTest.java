package com.codeclan.amymorrison.toptrumps;

import com.codeclan.amymorrison.toptrumps.deck.Card;
import com.codeclan.amymorrison.toptrumps.deck.CardImage;
import com.codeclan.amymorrison.toptrumps.deck.Rank;
import com.codeclan.amymorrison.toptrumps.deck.Suit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by amymorrison on 30/01/2018.
 */

public class CardTest {

    private Card card;

    @Before
    public void setUp(){
        card = new Card(Suit.CLUBS, Rank.EIGHT, CardImage.EIGHTCLUBS);
    }

    @Test
    public void cardHasSuit(){
        assertEquals(Suit.CLUBS, card.getSuit());
    }

    @Test
    public void cardHasRank(){
        assertEquals(Rank.EIGHT, card.getRank());
    }

    @Test
    public void cardHasValue(){
        assertEquals(8, card.getValue());
    }

    @Test
    public void cardHasName() {
        assertEquals("eight of clubs", card.prettyName());
    }
}
