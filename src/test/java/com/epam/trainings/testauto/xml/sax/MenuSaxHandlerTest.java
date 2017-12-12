package com.epam.trainings.testauto.xml.sax;

import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class MenuSaxHandlerTest {
    @Test
    public void name() throws SAXException, IOException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        MenuSaxHandler handler = new MenuSaxHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource("./src/main/resources/menu.xml"));
//        reader.parse(
//                new InputSource(
//                        MenuSaxHandlerTest.class.getResourceAsStream("menu.xml")));

        System.out.println(handler.getFoodList().toString());
    }
}