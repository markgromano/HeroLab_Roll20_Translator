package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.Character;
import com.quizar.hrtranslator.herolab.Weapon;
import org.junit.Test;

import static org.junit.Assert.*;

public class OutputGeneratorTest {

    @Test
    public void testGet() throws Exception {
        Character character = new Character();
        character.setName("Iynas Vumarin");

        Weapon weapon = new Weapon();
        weapon.setName("+1 dagger");
        weapon.setDamage("1d4");
        weapon.setCrit("19-20/×2");
        weapon.setAttack("+8/+3");

        String actual = OutputGenerator.getWeaponRoll(character, weapon);
        String expected = "&{template:default} {{name=Iynas Vumarin: +1 dagger}} {{Crit=19-20/×2}} {{attack 1=[[1d20+8]] (dam: [[1d4]])}} {{attack 2=[[1d20+3]] (dam: [[1d4]])}}";

        assertEquals(expected, actual);
    }

    @Test
    public void testGetSingle() throws Exception {
        Character character = new Character();
        character.setName("Iynas Vumarin");

        Weapon weapon = new Weapon();
        weapon.setName("+1 dagger");
        weapon.setDamage("1d4");
        weapon.setCrit("19-20/×2");
        weapon.setAttack("+8");

        String actual = OutputGenerator.getWeaponRoll(character, weapon);
        String expected = "&{template:default} {{name=Iynas Vumarin: +1 dagger}} {{Crit=19-20/×2}} {{attack=[[1d20+8]] (dam: [[1d4]])}}";

        assertEquals(expected, actual);
    }
}