package com.quizar.hrtranslator;

import com.quizar.hrtranslator.herolab.XMLDocument;
import org.junit.Test;

import static org.junit.Assert.*;


public class XMLReaderTest {

    @Test
    public void testRead() throws Exception {
        XMLReader xmlReader = new XMLReader("C:\\Temp\\Iynas_Vumarin.xml");
        XMLDocument xmlDocument = xmlReader.read();

        assertNotNull(xmlDocument);
    }
}