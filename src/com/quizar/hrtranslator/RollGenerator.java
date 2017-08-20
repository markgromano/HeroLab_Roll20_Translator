package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Character;
import com.quizar.hrtranslator.herolab.Weapon;
import com.quizar.hrtranslator.herolab.XMLDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RollGenerator {
    public static Object[] getRolls(XMLDocument herolabOutput, int selectedCharacterIndex) {
        List<String> rolls = new ArrayList<>();

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

    private static void addWeapons(List<String> rolls, List<Weapon> weapons) {
        if(weapons != null){
            for(Weapon weapon : weapons){
                addWeapons(rolls, weapon);
            }
        }
    }

    private static void addWeapons(List<String> rolls, Weapon weapon){
        String[] attacks = weapon.getAttack().split("/");
        for(int i = 0; i < attacks.length; i++){
            rolls.add(String.format("%s - Attack %d (%s)", weapon.getName(), i+1, attacks[i]));
        }
    }
}
