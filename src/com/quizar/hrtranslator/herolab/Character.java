package com.quizar.hrtranslator.herolab;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Character {
    private String name;
    private Melee melee;
    private Ranged ranged;
    private Saves saves;

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public Melee getMelee() {
        return melee;
    }

    @XmlElement
    public void setMelee(Melee melee) {
        this.melee = melee;
    }

    public Ranged getRanged() {
        return ranged;
    }

    @XmlElement
    public void setRanged(Ranged ranged) {
        this.ranged = ranged;
    }

    public Saves getSaves() {
        return saves;
    }

    @XmlElement
    public void setSaves(Saves saves) {
        this.saves = saves;
    }
}
