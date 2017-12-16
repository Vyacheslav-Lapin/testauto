package com.epam.trainings.testauto.homeworks.hw1;

public class Dog extends NamedAnimal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public void speak() {
        printWithName("says Woof");
    }

    @Override
    public void feed() {
        printWithName("eats meat");
    }

    @Override
    public void pet() {
        printWithName("likes it and wags its tail");
    }
}
