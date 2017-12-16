package com.epam.trainings.testauto.homeworks.hw1;

public class Cat extends NamedAnimal {

    @SuppressWarnings("WeakerAccess")
    public Cat(String name) {
        super(name);
    }

    @Override
    public void speak() {
        printWithName("says Meow");
    }

    @Override
    public void feed() {
        printWithName("eats mice");
    }

    @Override
    public void pet() {
        printWithName("likes it and purrs");
    }
}
