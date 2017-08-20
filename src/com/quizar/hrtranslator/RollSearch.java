package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Character;
import com.quizar.hrtranslator.herolab.Weapon;

import java.util.List;
import java.util.Optional;

public class RollSearch {
    public static String findRoll(Character selectedCharacter, String selectedRoll) {
        if(selectedCharacter == null){ return ""; }

        Optional<Weapon> selectedWeapon = findWeapon(selectedCharacter.getMelee().getWeapon(), selectedRoll);
        if(selectedWeapon.isPresent()){
            return OutputGenerator.getWeaponRoll(selectedCharacter, selectedWeapon.get());
        }

        selectedWeapon = findWeapon(selectedCharacter.getRanged().getWeapon(), selectedRoll);
        if(selectedWeapon.isPresent()){
            return OutputGenerator.getWeaponRoll(selectedCharacter, selectedWeapon.get());
        }

        return "";
    }

    private static Optional<Weapon> findWeapon(List<Weapon> weapon, String selectedRoll) {
        return weapon.stream()
                .filter(w -> w.getName().equalsIgnoreCase(selectedRoll))
                .findFirst();
    }
}
