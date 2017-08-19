package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.XMLDocument;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLReader {
    private final String filePath;

    public XMLReader(String filePath) {
        this.filePath = filePath;
    }

    public XMLDocument read(){
        try {

            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLDocument.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (XMLDocument) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
