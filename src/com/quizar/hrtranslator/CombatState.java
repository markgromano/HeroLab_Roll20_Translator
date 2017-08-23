package com.quizar.hrtranslator;

public class CombatState {
    private final boolean charging;
    private final boolean flanking;

    public CombatState(boolean charging, boolean flanking) {
        this.charging = charging;
        this.flanking = flanking;
    }

    public String getBonus(){
        int bonus = 0;

        if(charging){
            bonus += 2;
        }
        if(flanking){
            bonus += 2;
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

        return labelAddon.toString();
    }
}
