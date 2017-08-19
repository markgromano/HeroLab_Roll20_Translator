package com.quizar.hrtranslator.herolab;

import javax.xml.bind.annotation.XmlAttribute;

public class Weapon {
    private String name;
    private String damage;
    private String crit;
    private String attack;

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getDamage() {
        return damage;
    }

    @XmlAttribute
    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getCrit() {
        return crit;
    }

    @XmlAttribute
    public void setCrit(String crit) {
        this.crit = crit;
    }

    public String getAttack() {
        return attack;
    }

    @XmlAttribute
    public void setAttack(String attack) {
        this.attack = attack;
    }
}
