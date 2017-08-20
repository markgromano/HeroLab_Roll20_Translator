package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Character;
import com.quizar.hrtranslator.herolab.Weapon;

import java.util.List;
import java.util.stream.Collectors;

public class OutputGenerator {
    public static String getTitle(Weapon weapon){
        return String.format("%s (Crit: %s)", weapon.getName(), weapon.getCrit());
    }

    public static String getRoll(Weapon weapon, int attackNumber){
        String[] attacks = weapon.getAttack().split("/");
        if(attackNumber > attacks.length){ return null; }
        return String.format("{{#anum#=%s (%s) [[1d20%s]] (damage: [[%s]])}} ",
                weapon.getName(), attacks[attackNumber-1], attacks[attackNumber-1], weapon.getDamage());
    }

    public static String getOutputBlock(Character character, List selectedRolls){
        StringBuilder output = new StringBuilder();
        output.append("&{template:default} ");

        String titles = selectedRolls.stream()
                .filter(RollEntry.class::isInstance)
                .map(r -> ((RollEntry)r).getTitle())
                .distinct()
                .collect(Collectors.joining(", "))
                .toString();
        output.append(String.format("{{name=%s: %s}} ", character.getName(), titles));

        for(int attackNumber = 1; attackNumber <= selectedRolls.size(); attackNumber++){
            RollEntry rollEntry = (RollEntry) selectedRolls.get(attackNumber-1);
            String rollText = rollEntry.getRoll();
            rollText = rollText.replace("#anum#", "#" + attackNumber);
            output.append(rollText);
        }

        return output.toString().trim();
    }
}
