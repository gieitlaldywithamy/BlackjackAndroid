package com.codeclan.amymorrison.toptrumps;

/**
 * Created by amymorrison on 26/01/2018.
 */

public class CardTrumps {

    private String name;
    private String description;
    private Integer imageSrc;
    private Integer strength;
    private Integer intellect;
    private Integer morality;
    private Integer followerDedication;

    public CardTrumps(String name, String description, Integer imageSrc, Integer strength, Integer intellect, Integer morality, Integer followerDedication) {

        this.name = name;
        this.description = description;
        this.imageSrc = imageSrc;
        this.strength = strength;
        this.intellect = intellect;
        this.morality = morality;
        this.followerDedication = followerDedication;
    }

    public String getName() {
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
