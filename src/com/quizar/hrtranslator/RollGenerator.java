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

            addWeapons(rolls, selectedCharacter.getMelee().getWeapon());
            addWeapons(rolls, selectedCharacter.getRanged().getWeapon());
        }

        return rolls.toArray();
    }

    private static void addWeapons(List<String> rolls, List<Weapon> weapons) {
        if(weapons != null){
            rolls.addAll(weapons.stream().map(Weapon::getName).collect(Collectors.toList()));
        }
    }
}
