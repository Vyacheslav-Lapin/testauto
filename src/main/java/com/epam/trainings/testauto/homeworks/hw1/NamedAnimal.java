package com.epam.trainings.testauto.homeworks.hw1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class NamedAnimal implements Animal {
    private final String name;

    protected void printWithName(String s) {
        System.out.println(name + " " + s);
    }
}
