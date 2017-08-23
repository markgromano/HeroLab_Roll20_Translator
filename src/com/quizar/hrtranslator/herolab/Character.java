package com.quizar.hrtranslator.herolab;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Character {
    private String name;
    private Melee melee;
    private Ranged ranged;
    private Saves saves;
    private Initiative initiative;
    private Skills skills;

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

    public Initiative getInitiative() {
        return initiative;
    }

    @XmlElement
    public void setInitiative(Initiative initiative) {
        this.initiative = initiative;
    }

    public Skills getSkills() {
        return skills;
    }

    @XmlElement
    public void setSkills(Skills skills) {
        this.skills = skills;
    }
}
