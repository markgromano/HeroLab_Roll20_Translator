package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Character;
import com.quizar.hrtranslator.herolab.Weapon;

public class OutputGenerator {
    public static String getTitle(Weapon weapon){
        return String.format("%s (Crit: %s)", weapon.getName(), weapon.getCrit());
    }

    public static String getRoll(Weapon weapon, int attackNumber){
        String[] attacks = weapon.getAttack().split("/");
        if(attackNumber >= attacks.length){ return null; }
        return String.format("{{#%d=%s [[1d20%s]] (dam: [[%s]])}} ",
                attackNumber, weapon.getName(), attacks[attackNumber-1], weapon.getDamage());
    }

    public static String getWeaponRoll(Character character, Weapon weapon) {
        StringBuilder output = new StringBuilder();
        output.append("&{template:default} ");
        String title = getTitle(weapon);
        output.append(String.format("{{name=%s: %s}} ", character.getName(), title));

        int attackNumber = 1;
        String rollText = getRoll(weapon, attackNumber);
        while(rollText != null){
            output.append(rollText);
            attackNumber++;
            rollText = getRoll(weapon, attackNumber);
        }

        return output.toString().trim();
    }
}
