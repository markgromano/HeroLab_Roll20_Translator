package com.quizar.hrtranslator;

public class RollEntry {
    public static final RollEntry SPACER = new RollEntry(null, null, null);

    private final String label, title;
    private final Roll roll;

    public RollEntry(String label, String title, Roll roll) {
        this.roll = roll;
        this.title = title;
        this.label = label;
    }

    @Override
    public String toString() {
        if(this == SPACER){
            return "----------------";
        }

        return label;
    }

    public String getTitle() {
        return title;
    }

    public Roll getRoll() {
        //rollText = rollText.replace("#anum#", "#" + attackNumber);
        return roll;
    }
}