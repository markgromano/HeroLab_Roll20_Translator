package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.*;
import com.quizar.hrtranslator.herolab.Character;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OutputGenerator {
    static final private Pattern DIE_STRING_PATTERN = Pattern.compile(".*[0-9]d[0-9].*");

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

    public static String getRoll(Weapon weapon, int attackNumber){
        String[] attacks = weapon.getAttack().split("/");
        if(attackNumber > attacks.length){ return null; }
        String attackOutput = getAttackOutput(attacks[attackNumber-1], weapon.getCrit());
        String damageOutput = getDamageOutput(weapon.getDamage());
        return String.format("{{#anum#=%s (%s) [[1d20%s]] (damage: %s)}} ",
                weapon.getName(), attacks[attackNumber-1], attackOutput, damageOutput);
    }

    public static String getRoll(Save save){
        return String.format("{{%s=[[1d20%s]]}} ", save.getAbbr(), save.getSave());
    }

    public static String getRoll(Initiative initiative){
        return String.format("{{Initiative=[[1d20%s&{tracker}]]}} ", initiative.getTotal());
    }

    public static String getRoll(Skill skill){
        return String.format("{{%s=[[1d20+%d]]}} ", skill.getName().replace(" ", "_"), skill.getValue());
    }

    private static String getAttackOutput(String attack, String crit) {
        if(crit != null && crit.contains("-")){
            return "cs>" + crit.substring(0, crit.indexOf("-")) + attack;
        }else{
            return attack;
        }
    }

    private static String getDamageOutput(String damage) {
        if(damage == null || damage.length() == 0){
            return "";
        }

        StringBuilder damageOutput = new StringBuilder();
        String[] dice = damage.split(" ");
        for(String die : dice){
            if(DIE_STRING_PATTERN.matcher(die).matches()){
                damageOutput.append(String.format("[[%s]]", die));
            }else{
                damageOutput.append(die);
            }
            damageOutput.append(" ");
        }
        return damageOutput.toString().trim();
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
