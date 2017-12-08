package com.epam.trainings.testauto.model;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;

public class FoodTest {

    @Test
    @SneakyThrows
    public void marshalling() {
        JAXBContext context = JAXBContext.newInstance(Food.class);
        Marshaller m = context.createMarshaller();

        val food = new Food()
                .setId(123)
                .setName("nnn")
                .setDescription("ddd")
                .setCalories(234)
                .setPrice("333");

        m.marshal(food, new FileOutputStream("stud.xml"));
        m.marshal(food, System.out);// на консоль
        System.out.println("XML-файл создан");


        //Unmarshall
        File file = new File("stud.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Food.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        val food2 = (Food) jaxbUnmarshaller.unmarshal(file);
        System.out.println(food2.toString());
    }
}