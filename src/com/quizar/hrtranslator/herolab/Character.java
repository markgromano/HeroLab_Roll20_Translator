package com.quizar.hrtranslator.herolab;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Character {
    private String name;
    private Melee melee;

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
}