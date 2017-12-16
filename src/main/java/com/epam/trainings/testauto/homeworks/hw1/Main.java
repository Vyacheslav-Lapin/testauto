package com.epam.trainings.testauto.homeworks.hw1;

import com.epam.trainings.testauto.jdbc.CheckedSupplier;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Main {

    private static CheckedSupplier<String> userIn;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        System.in))) {

            userIn = reader::readLine;

            Collection<Class<? extends Animal>> zooClasses = getZooClasses();

            showAnimals(zooClasses);

            Collection<Animal> homeZoo = giveNames(zooClasses);

            communicateWithAnimals(homeZoo);
        }
    }

    private static void showAnimals(Iterable<Class<? extends Animal>> homeZoo) {
        System.out.println("In your zoo you have:");
        for (Class<?> animalClass : homeZoo)
            System.out.println(" Animal " + animalClass.getSimpleName().toLowerCase());
    }

    @SneakyThrows
    private static Collection<Animal> giveNames(
            Collection<Class<? extends Animal>> homeZoo) {

        Collection<Animal> result = new ArrayList<>(homeZoo.size());

        System.out.println("Let's give them names!");

        for (Class<? extends Animal> animalClass : homeZoo) {
            System.out.printf("What would be the name for your %s?%n",
                    animalClass.getSimpleName().toLowerCase());
            result.add((Animal) animalClass.getConstructors()[0]
                    .newInstance(userIn.get()));
        }

        return result;
    }

    private static void communicateWithAnimals(Iterable<? extends Animal> homeZoo) {
        System.out.println("What do you want to do with your animals?\n" +
                "1 - speak\n" +
                "2 - feed\n" +
                "3 - pet\n");

        for (Animal animal : homeZoo)
            switch (userIn.get()) {
                case "1":
                case "speak":
                    animal.speak();
                    break;
                case "2":
                case "feed":
                    animal.feed();
                    break;
                case "3":
                case "pet":
                    animal.pet();
                    break;
                default:
                    System.out.println("You can't do this with your " +
                            animal.getClass().getSimpleName().toLowerCase());
            }
    }

    private static Collection<Class<? extends Animal>> getZooClasses() {
        return Arrays.asList(
                Cat.class,
                Dog.class,
                Fish.class,
                Crocodile.class);
    }
}
