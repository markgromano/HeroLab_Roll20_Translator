package com.quizar.hrtranslator.herolab;

import javax.xml.bind.annotation.XmlAttribute;

public class Character {
    private String name;

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }
}
