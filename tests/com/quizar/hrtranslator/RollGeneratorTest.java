package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.*;
import com.quizar.hrtranslator.herolab.Character;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RollGeneratorTest {
    @Test
    public void testGetRolls() throws Exception {
        Weapon weapon = new Weapon();
        weapon.setName("+1 dagger");
        weapon.setAttack("+8/+3");

        XMLDocument herolabOutput = new XMLDocument();
        herolabOutput.setPublicElement(new Public());
        herolabOutput.getPublicElement().setCharacter(new ArrayList<>());
        herolabOutput.getPublicElement().getCharacter().add(new Character());
        herolabOutput.getPublicElement().getCharacter().get(0).setMelee(new Melee());
        herolabOutput.getPublicElement().getCharacter().get(0).getMelee().setWeapon(new ArrayList<>());
        herolabOutput.getPublicElement().getCharacter().get(0).getMelee().getWeapon().add(weapon);

        int selectedCharacterIndex = 0;
        Object[] results = RollGenerator.getRolls(herolabOutput, selectedCharacterIndex, false);

        assertNotNull(results);
        assertEquals(2, results.length);

        assertNotNull(results[0]);
        assertEquals("+1 dagger - Attack 1 (+8)", results[0].toString());

        assertNotNull(results[1]);
        assertEquals("+1 dagger - Attack 2 (+3)", results[1].toString());
    }
}