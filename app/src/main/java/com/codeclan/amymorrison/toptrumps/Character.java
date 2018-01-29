package com.codeclan.amymorrison.toptrumps;

/**
 * Created by amymorrison on 26/01/2018.
 */

public enum Character {

    OLIVERCROMWELL("Oliver Cromwell", "Oliver Cromwell was an English military and political leader. He served as Lord Protector of the Commonwealth of England, Scotland, and Ireland from 1653 until his death."
    , R.drawable.ace_of_diamonds, 40, 20, 35, 21),
    BANKSY("BANKSY", "Unknown", R.drawable.eightdiamonds, 23, 43, 21, 80),
    HENRYMATISSE("Henri Matisse", "Originally a pioneer of the Fauve ('wild beast') movement Matisse became synonymous with"
                         +"sumptuously patterned interiors and recling women of the mystical East.", R.drawable.clubstwo, 10, 15, 38, 90);


    private final String name;
    private final String description;
    private final Integer imageSrc;
    private final Integer strength;
    private final Integer intellect;
    private final Integer morality;
    private final Integer followerDedication;

    Character(String name, String description, Integer imageSrc, Integer strength, Integer intellect, Integer morality, Integer followerDedication) {
        this.name = name;
        this.description = description;
        this.imageSrc = imageSrc;
        this.strength = strength;
        this.intellect = intellect;
        this.morality = morality;
        this.followerDedication = followerDedication;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public Integer getImageSrc(){
        return this.imageSrc;
    }

    public Integer getStrength(){
        return this.strength;
    }

    public Integer getIntellect(){
        return this.intellect;
    }

    public Integer getMorality(){
        return this.morality;
    }

    public Integer getFollowerDedication(){
        return this.followerDedication;
    }
}
