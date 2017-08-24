package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.*;
import com.quizar.hrtranslator.herolab.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RollGenerator {
    public static Object[] getRolls(XMLDocument herolabOutput, int selectedCharacterIndex) {
        List<RollEntry> rolls = new ArrayList<>();

        if(herolabOutput != null && herolabOutput.getPublicElement() != null &&
                herolabOutput.getPublicElement().getCharacter() != null &&
                herolabOutput.getPublicElement().getCharacter().size() > selectedCharacterIndex &&
                selectedCharacterIndex > -1){
            Character selectedCharacter = herolabOutput.getPublicElement().getCharacter().get(selectedCharacterIndex);

            if(selectedCharacter.getMelee() != null) {
                addWeapons(rolls, selectedCharacter.getMelee().getWeapon());
            }
            if(selectedCharacter.getRanged() != null) {
                addWeapons(rolls, selectedCharacter.getRanged().getWeapon());
            }

            if(selectedCharacter.getSaves() != null && selectedCharacter.getSaves().getSave() != null
                && selectedCharacter.getSaves().getSave().size() > 0){
                rolls.add(RollEntry.SPACER);
                addSaves(rolls, selectedCharacter.getSaves());
            }

            if(selectedCharacter.getInitiative() != null){
                rolls.add(RollEntry.SPACER);
                addInitiative(rolls, selectedCharacter.getInitiative());
            }

            if(selectedCharacter.getSkills() != null && selectedCharacter.getSkills().getSkill() != null
                    && selectedCharacter.getSkills().getSkill().size() > 0){
                rolls.add(RollEntry.SPACER);
                addSkills(rolls, selectedCharacter.getSkills());
            }
        }

        return rolls.toArray();
    }

    private static void addSkills(List<RollEntry> rolls, Skills skills) {
        if(skills != null && skills.getSkill() != null){
            addSaves(rolls, skills.getSkill().stream()
            .filter(s -> s.getName().equalsIgnoreCase("Perception"))
            .collect(Collectors.toList()));
        }
    }

    private static void addSaves(List<RollEntry> rolls, List<Skill> usefulSkills) {
        for(Skill skill : usefulSkills){
            String label = OutputGenerator.getTitle(skill);
            String title = OutputGenerator.getTitle(skill);
            Roll roll = OutputGenerator.getRoll(skill);
            RollEntry rollEntry = new RollEntry(label, title, roll);
            rolls.add(rollEntry);
        }
    }

    private static void addSaves(List<RollEntry> rolls, Saves saves) {
        if(saves != null && saves.getSave() != null){
            for(Save save : saves.getSave()){
                addSaves(rolls, save);
            }
        }
    }

    private static void addWeapons(List<RollEntry> rolls, List<Weapon> weapons) {
        if(weapons != null){
            weapons.stream()
                    .filter(weapon -> weapon != null && weapon.getEquipped() != null)
                    .forEach(weapon -> addWeapons(rolls, weapon));
        }
    }

    private static void addInitiative(List<RollEntry> rolls, Initiative initiative) {
        String label = OutputGenerator.getTitle(initiative);
        String title = OutputGenerator.getTitle(initiative);
        Roll roll = OutputGenerator.getRoll(initiative);
        RollEntry rollEntry = new RollEntry(label, title, roll);
        rolls.add(rollEntry);
    }

    private static void addSaves(List<RollEntry> rolls, Save save) {
        String label = OutputGenerator.getTitle(save);
        String title = OutputGenerator.getTitle(save);
        Roll roll = OutputGenerator.getRoll(save);
        RollEntry rollEntry = new RollEntry(label, title, roll);
        rolls.add(rollEntry);
    }

    private static void addWeapons(List<RollEntry> rolls, Weapon weapon){
        String[] attacks = weapon.getAttack().split("/");
        for(int attackNumber = 1; attackNumber <= attacks.length; attackNumber++){
            String label = String.format("%s - Attack %d (%s)", weapon.getName(), attackNumber, attacks[attackNumber-1]);
            String title = OutputGenerator.getTitle(weapon);
            Roll roll = OutputGenerator.getRoll(weapon, attackNumber);
            RollEntry rollEntry = new RollEntry(label, title, roll);
            rolls.add(rollEntry);
        }
    }
}
