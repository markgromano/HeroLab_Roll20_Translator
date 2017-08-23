package com.quizar.hrtranslator;

public class CombatState {
    private final boolean charging, flanking, highGround;

    public CombatState(boolean charging, boolean flanking, boolean highGround) {
        this.charging = charging;
        this.flanking = flanking;
        this.highGround = highGround;
    }

    public String getBonus(){
        int bonus = 0;

        if(charging){
            bonus += 2;
        }
        if(flanking){
            bonus += 2;
        }
        if(highGround){
            bonus += 1;
        }

        if(bonus > 0) {
            return "+" + bonus;
        }else if(bonus < 0) {
            return "-" + bonus;
        }else{
            return "";
        }
    }

    public String getLabelAddon() {
        StringBuilder labelAddon = new StringBuilder();

        if(charging){
            labelAddon.append(" *charging*");
        }
        if(flanking){
            labelAddon.append(" *flanking*");
        }
        if(highGround){
            labelAddon.append(" *high ground*");
        }

        return labelAddon.toString();
    }
}
