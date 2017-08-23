package com.quizar.hrtranslator.herolab;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Skills {
    private List<Skill> skill;

    public List<Skill> getSkill() {
        return skill;
    }

    @XmlElement
    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }
}
