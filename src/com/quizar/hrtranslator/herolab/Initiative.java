package com.quizar.hrtranslator.herolab;

import javax.xml.bind.annotation.XmlAttribute;

public class Initiative {
    private String total;

    public String getTotal() {
        return total;
    }

    @XmlAttribute
    public void setTotal(String total) {
        this.total = total;
    }
}
