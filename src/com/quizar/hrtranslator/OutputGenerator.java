package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Character;
import com.quizar.hrtranslator.herolab.Weapon;

public class OutputGenerator {
    public static String getWeaponRoll(Character character, Weapon weapon) {
        StringBuilder output = new StringBuilder();
        output.append("&{template:default} ");
        output.append(String.format("{{name=%s: %s (Crit: %s)}} ", character.getName(), weapon.getName(), weapon.getCrit()));

        String[] attacks = weapon.getAttack().split("/");

        for(int i = 1; i <= attacks.length; i++){
            appendAttack(attacks[i-1], i, output, weapon);
        }

        return output.toString().trim();
    }

    private static void appendAttack(String attack, int attackNumber, StringBuilder output, Weapon weapon) {
        output.append(String.format("{{#%d=%s [[1d20%s]] (dam: [[%s]])}} ",
                attackNumber, weapon.getName(), attack, weapon.getDamage()));
    }
}
