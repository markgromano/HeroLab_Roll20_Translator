package com.quizar.hrtranslator.herolab;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Saves {
    private List<Save> save;

    public List<Save> getSave() {
        return save;
    }

    @XmlElement
    public void setSave(List<Save> save) {
        this.save = save;
    }
}
