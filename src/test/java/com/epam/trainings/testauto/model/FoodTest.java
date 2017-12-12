package com.epam.trainings.testauto.model;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class FoodTest {

    //language=XML
    private static final String XML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                    "<food id=\"123\">" +
                    "<name>nnn</name>" +
                    "<price>333</price>" +
                    "<description>ddd</description>" +
                    "<calories>234</calories>" +
                    "</food>";

    private static final String ACTUAL =
            "Food(id=123, name=nnn, price=333, description=ddd, calories=234)";

    @SneakyThrows
    private <T> void withJAXBContext(Class<T> aClass, Consumer<JAXBContext> jaxbContextConsumer) {
        JAXBContext jaxbContext = JAXBContext.newInstance(aClass);
        jaxbContextConsumer.accept(jaxbContext);
    }

    @Test
    public void marshalling() {
        withJAXBContext(Food.class, jaxbContext -> {
            try (StringWriter writer = new StringWriter()) {

                val food = new Food(123, "nnn", "333", "ddd", 234);

                //Marshall
                jaxbContext.createMarshaller().marshal(food, writer);

                assertThat(XML, is(writer.toString()));

                //Unmarshall
                try (StringReader reader = new StringReader(writer.toString())) {
                    val food2 = (Food) jaxbContext.createUnmarshaller()
                            .unmarshal(reader);

                    assertThat(ACTUAL, is(food2.toString()));
                    assertEquals(food, food2);
                }
            } catch (IOException | JAXBException e) {
                throw new RuntimeException(e);
            }
        });
    }
}