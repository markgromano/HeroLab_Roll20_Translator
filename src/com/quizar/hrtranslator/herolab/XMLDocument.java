package com.quizar.hrtranslator.herolab;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="document")
public class XMLDocument {
    private Public publicElement;

    public Public getPublicElement() {
        return publicElement;
    }

    @XmlElement(name="public")
    public void setPublicElement(Public publicElement) {
        this.publicElement = publicElement;
    }
}