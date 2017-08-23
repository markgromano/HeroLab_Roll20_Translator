package com.quizar.hrtranslator.herolab;

import javax.xml.bind.annotation.XmlAttribute;

public class Skill {
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    @XmlAttribute
    public void setValue(int value) {
        this.value = value;
    }
}
