package com.epam.trainings.testauto.xml.sax;

import com.epam.trainings.testauto.model.Food;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MenuSaxHandler extends DefaultHandler {

    @Getter
    private List<Food> foodList = new ArrayList<>();

    @Getter
    private Food food;

    private StringBuilder text;

    @Override
    public void startDocument() {
        System.out.println("Parsing started.");
    }

    @Override
    public void endDocument() {
        System.out.println("Parsing ended.");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        System.out.printf("startElement -> uri: %s, localName: %s, qName: %s%n", uri, localName, qName);

        text = new StringBuilder();
        if (qName.equals("food"))
            food = new Food()
                    .setId((Integer.parseInt(attributes.getValue("id"))));
    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) {
        MenuTagName tagName =
                MenuTagName.valueOf(qName.toUpperCase().replace("-", "_"));
        switch (tagName) {
            case NAME:
                food.setName(text.toString());
                break;
            case PRICE:
                food.setPrice(text.toString());
                break;
            case DESCRIPTION:
                food.setDescription(text.toString());
                break;
            case CALORIES:
                food.setCalories(Integer.parseInt(text.toString()));
                break;
            case FOOD:
                foodList.add(food);
                food = null;
                break;
        }
    }

    public void warning(SAXParseException exception) {
        System.err.printf("WARNING: line %d: %s%n", exception.getLineNumber(), exception.getMessage());
    }

    public void error(SAXParseException exception) {
        System.err.printf("ERROR: line %d: %s%n", exception.getLineNumber(), exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.printf("FATAL: line %d: %s%n", exception.getLineNumber(), exception.getMessage());
        throw exception;
    }
}
