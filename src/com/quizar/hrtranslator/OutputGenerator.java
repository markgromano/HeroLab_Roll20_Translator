package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.*;
import com.quizar.hrtranslator.herolab.Character;

import java.util.List;
import java.util.stream.Collectors;

public class OutputGenerator {
    public static String getTitle(Weapon weapon){
        return String.format("%s (Crit: %s)", weapon.getName(), weapon.getCrit());
    }

    public static String getTitle(Save save){
        return String.format("%s (%s)", save.getName(), save.getSave());
    }

    public static String getTitle(Initiative initiative) {
        return String.format("Initiative (%s)", initiative.getTotal());
    }

    public static String getTitle(Skill skill) {
        return String.format("%s (+%s)", skill.getName(), skill.getValue());
    }

    public static Roll getRoll(Weapon weapon, int attackNumber){
        return new Roll(weapon, attackNumber);
    }

    public static Roll getRoll(Save save){
        int bonus = getBonus(save.getSave());
        return new Roll(save.getAbbr(), bonus);
    }

    public static Roll getRoll(Initiative initiative){
        int bonus = getBonus(initiative.getTotal());
        return new Roll("Initiative", bonus, "&{tracker}");
    }

    private static int getBonus(String stringBonus) {
        int bonus = 0;
        try {
            bonus = Integer.parseInt(stringBonus);
        }catch(NumberFormatException nfe){
            System.out.println("NumberFormatException parsing bonus: " + stringBonus);
            nfe.printStackTrace(System.out);
        }
        return bonus;
    }

    public static Roll getRoll(Skill skill){
        return new Roll(skill.getName(), skill.getValue());
    }

    public static String getOutputBlock(Character character, List selectedRolls, CombatState combatState){
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
            Roll roll = rollEntry.getRoll();
            output.append(roll.toString(attackNumber, combatState));
        }

        return output.toString().trim();
    }
}
