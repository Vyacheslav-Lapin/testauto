package com.epam.trainings.testauto.homeworks.hw1;

public class Crocodile extends NamedAnimal {

    @SuppressWarnings("WeakerAccess")
    public Crocodile(String name) {
        super(name);
    }

    @Override
    public void speak() {
        printWithName("doesn't speak");
    }

    @Override
    public void feed() {
        printWithName("wants to eat you!");
    }

    @Override
    public void pet() {
        System.out.printf("You don't want to pet %s.%n", getName());
    }
}
