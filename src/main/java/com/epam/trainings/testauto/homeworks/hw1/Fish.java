package com.epam.trainings.testauto.homeworks.hw1;

public class Fish extends NamedAnimal {

    public Fish(String name) {
        super(name);
    }

    @Override
    public void speak() {
        printWithName("doesn't speak");
    }

    @Override
    public void feed() {
        printWithName("eats worms");
    }

    @Override
    public void pet() {
        printWithName("is afraid of you!");
    }
}
