package com.quizar.hrtranslator;

public class RollEntry {
    public static final RollEntry SPACER = new RollEntry(null, null, null);

    private final String label, title, roll;

    public RollEntry(String label, String title, String roll) {
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

    public String getRoll() {
        return roll;
    }
}