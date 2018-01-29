package com.codeclan.amymorrison.toptrumps;

/**
 * Created by amymorrison on 26/01/2018.
 */

public enum Rank {

    ACE(1),
    //how to handle ace?
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private final int value;

    private Rank(int value){
        this.value = value;
    }

    private Rank(int value, int highestValue){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
