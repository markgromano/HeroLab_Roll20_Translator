package com.quizar.hrtranslator.herolab;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Public {
    private List<Character> character;

    public List<Character> getCharacter() {
        return character;
    }

    @XmlElement
    public void setCharacter(List<Character> character) {
        this.character = character;
    }
}
