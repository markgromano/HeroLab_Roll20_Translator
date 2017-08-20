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
        for(int i = 0; i < attacks.length; i++){
            String label = String.format("%s - Attack %d (%s)", weapon.getName(), i+1, attacks[i]);
            RollEntry rollEntry = new RollEntry(label, weapon);
            rolls.add(rollEntry);
        }
    }
}
