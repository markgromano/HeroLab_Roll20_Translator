package com.quizar.hrtranslator.herolab;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Ranged {
    private List<Weapon> weapon;

    public List<Weapon> getWeapon() {
        return weapon;
    }

    @XmlElement
    public void setWeapon(List<Weapon> weapon) {
        this.weapon = weapon;
    }
}
