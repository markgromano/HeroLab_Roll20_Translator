package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Character;
import com.quizar.hrtranslator.herolab.Weapon;
import com.quizar.hrtranslator.herolab.XMLDocument;

import java.util.ArrayList;
import java.util.List;

public class RollGenerator {
    public static Object[] getRolls(XMLDocument herolabOutput, int selectedCharacterIndex) {
        List<RollEntry> rolls = new ArrayList<>();

        if(herolabOutput != null){
            Character selectedCharacter = herolabOutput.getPublicElement().getCharacter().get(selectedCharacterIndex);

            if(selectedCharacter.getMelee() != null) {
                addWeapons(rolls, selectedCharacter.getMelee().getWeapon());
            }
            if(selectedCharacter.getRanged() != null) {
                addWeapons(rolls, selectedCharacter.getRanged().getWeapon());
            }
        }

        return rolls.toArray();
    }

    private static void addWeapons(List<RollEntry> rolls, List<Weapon> weapons) {
        if(weapons != null){
            for(Weapon weapon : weapons){
                addWeapons(rolls, weapon);
            }
        }
    }

    private static void addWeapons(List<RollEntry> rolls, Weapon weapon){
        String[] attacks = weapon.getAttack().split("/");
        for(int attackNumber = 1; attackNumber <= attacks.length; attackNumber++){
            String label = String.format("%s - Attack %d (%s)", weapon.getName(), attackNumber, attacks[attackNumber-1]);
            String title = OutputGenerator.getTitle(weapon);
            String roll = OutputGenerator.getRoll(weapon, attackNumber);
            RollEntry rollEntry = new RollEntry(label, title, roll);
            rolls.add(rollEntry);
        }
    }
}
