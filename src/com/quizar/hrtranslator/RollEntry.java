package com.quizar.hrtranslator;

public class RollEntry {
    private final String label, title, roll;

    public RollEntry(String label, String title, String roll) {
        this.roll = roll;
        this.title = title;
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public String getTitle() {
        return title;
    }

    public String getRoll() {
        return roll;
    }
}