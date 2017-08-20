package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Character;
import com.quizar.hrtranslator.herolab.Weapon;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OutputGeneratorTest {
    @Test
    public void testGetTitle() throws Exception {
        Weapon weapon = new Weapon();
        weapon.setName("+1 dagger");
        weapon.setCrit("19/20 x2");

        String actual = OutputGenerator.getTitle(weapon);
        assertEquals("+1 dagger (Crit: 19/20 x2)", actual);
    }

    @Test
    public void testGetRoll() throws Exception {
        Weapon weapon = new Weapon();
        weapon.setName("+1 dagger");
        weapon.setCrit("19/20 x2");
        weapon.setAttack("+8/+3");
        weapon.setDamage("1d4+1");

        int attackNumber = 2;

        String actual = OutputGenerator.getRoll(weapon, attackNumber);
        assertEquals("{{#2=+1 dagger [[1d20+3]] (dam: [[1d4+1]])}} ", actual);
    }

    @Test
    public void testGetOutput() throws Exception {
        Character character = new Character();
        character.setName("Radio Raheem");

        List selectedRolls = new ArrayList<>();
        selectedRolls.add(new RollEntry("Label", "Title", "{{#1=+1 dagger [[1d20+8]] (dam: [[1d4+1]])}} "));
        selectedRolls.add(new RollEntry("Label", "Title", "{{#2=+1 dagger [[1d20+3]] (dam: [[1d4+1]])}} "));

        String actual = OutputGenerator.getOutputBlock(character, selectedRolls);
        assertEquals("&{template:default} {{name=Radio Raheem: Title}} {{#1=+1 dagger [[1d20+8]] (dam: [[1d4+1]])}} {{#2=+1 dagger [[1d20+3]] (dam: [[1d4+1]])}}", actual);
    }

    @Test
    public void testGetOutput_multipleTitles() throws Exception {
        Character character = new Character();
        character.setName("Radio Raheem");

        List selectedRolls = new ArrayList<>();
        selectedRolls.add(new RollEntry("Label", "Title A", "{{#1=+1 dagger [[1d20+8]] (dam: [[1d4+1]])}} "));
        selectedRolls.add(new RollEntry("Label", "Title B", "{{#2=+1 dagger [[1d20+3]] (dam: [[1d4+1]])}} "));

        String actual = OutputGenerator.getOutputBlock(character, selectedRolls);
        assertEquals("&{template:default} {{name=Radio Raheem: Title A, Title B}} {{#1=+1 dagger [[1d20+8]] (dam: [[1d4+1]])}} {{#2=+1 dagger [[1d20+3]] (dam: [[1d4+1]])}}", actual);
    }
}