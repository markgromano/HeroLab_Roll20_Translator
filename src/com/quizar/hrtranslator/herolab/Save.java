package com.quizar.hrtranslator.herolab;

import javax.xml.bind.annotation.XmlAttribute;

public class Save {
    private String name;
    private String save;
    private String abbr;

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getSave() {
        return save;
    }

    @XmlAttribute
    public void setSave(String save) {
        this.save = save;
    }

    public String getAbbr() {
        return abbr;
    }

    @XmlAttribute
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
}
