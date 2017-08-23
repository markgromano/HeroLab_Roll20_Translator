package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Weapon;

import java.util.regex.Pattern;

public class Roll {
    static final private Pattern DIE_STRING_PATTERN = Pattern.compile(".*[0-9]d[0-9].*");

    private final String label, rollArgument;
    private final int bonus, attackNumber;
    private final Weapon weapon;

    public Roll(String label, int bonus) {
        this.label = label;
        this.bonus = bonus;
        this.rollArgument = "";
        this.attackNumber = 0;
        this.weapon = null;
    }

    public Roll(String label, int bonus, String rollArgument) {
        this.label = label;
        this.bonus = bonus;
        this.rollArgument = rollArgument;
        this.attackNumber = 0;
        this.weapon = null;
    }

    public Roll(Weapon weapon, int attackNumber) {
        this.label = "";
        this.bonus = 0;
        this.rollArgument = "";
        this.attackNumber = attackNumber;
        this.weapon = weapon;
    }

    public String toString(int rollNumber, CombatState combatState) {
        if(weapon != null){
            return getWeaponRoll(rollNumber, combatState);
        }

        String formattedLabel = "Roll";
        if(label != null){
            formattedLabel = label.replace(" ", "_");
        }

        String bonusModifier = "+";
        if(bonus < 0){
            bonusModifier = "-";
        }

        return String.format("{{%s=[[1d20%s%d%s]]}} ",
                formattedLabel,
                bonusModifier,
                bonus,
                rollArgument);
    }

    public String getWeaponRoll(int rollNumber, CombatState combatState) {
        String[] attacks = weapon.getAttack().split("/");
        if(attackNumber > attacks.length){ return null; }
        String attackOutput = getAttackOutput(attacks[attackNumber-1], weapon.getCrit(), combatState);
        String damageOutput = getDamageOutput(weapon.getDamage());
        return String.format("{{#%d=%s (%s)%s [[1d20%s]] (damage: %s)}} ",
                rollNumber, weapon.getName(), attacks[attackNumber-1], combatState.getLabelAddon(), attackOutput, damageOutput);
    }

    private static String getAttackOutput(String baseAttack, String crit, CombatState combatState) {
        String attack = baseAttack + combatState.getBonus();

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
}
