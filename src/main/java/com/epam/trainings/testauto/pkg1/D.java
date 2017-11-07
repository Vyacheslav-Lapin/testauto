package com.epam.trainings.testauto.pkg1;

@FunctionalInterface
public interface D {

    int XXX = 666;

    int m1();

    default int m2() {
        return 66;
    }

    default void printM1() {
        System.out.println(m1());
    }

}
