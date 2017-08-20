package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Weapon;

public class RollEntry {
    private final String label;
    private final Weapon weapon;

    public RollEntry(String label, Weapon weapon) {
        this.label = label;
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return label;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}