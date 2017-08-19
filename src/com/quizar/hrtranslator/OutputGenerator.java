package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Character;
import com.quizar.hrtranslator.herolab.Weapon;

public class OutputGenerator {
    public static String getWeaponRoll(Character character, Weapon weapon) {
        StringBuilder output = new StringBuilder();
        output.append("&{template:default} ");
        output.append(String.format("{{name=%s: %s}} ", character.getName(), weapon.getName()));
        output.append(String.format("{{Crit=%s}} ", weapon.getCrit()));

        String[] attacks = weapon.getAttack().split("/");

        if(attacks.length == 1){
            appendAttack(attacks[0], 0, output, weapon);
        }else{
            for(int i = 1; i <= attacks.length; i++){
                appendAttack(attacks[i-1], i, output, weapon);
            }
        }

        return output.toString().trim();
    }

    private static void appendAttack(String attack, int attackNumber, StringBuilder output, Weapon weapon) {
        if(attackNumber <= 0){
            output.append(String.format("{{attack=[[1d20%s]] (dam: [[%s]])}} ",
                    attack, weapon.getDamage()));
        }else{
            output.append(String.format("{{attack %d=[[1d20%s]] (dam: [[%s]])}} ",
                    attackNumber, attack, weapon.getDamage()));
        }
    }
}
