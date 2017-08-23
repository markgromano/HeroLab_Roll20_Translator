package com.quizar.hrtranslator;

public class CombatState {
    private final boolean charging;

    public CombatState(boolean charging) {
        this.charging = charging;
    }

    public String getBonus(){
        int bonus = 0;

        if(charging){
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
        if(charging){
            return " *charging*";
        }else{
            return "";
        }
    }
}
