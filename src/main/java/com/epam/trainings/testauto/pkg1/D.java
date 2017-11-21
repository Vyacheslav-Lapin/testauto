package com.epam.trainings.testauto.pkg1;

@FunctionalInterface
public interface D {

    @SuppressWarnings("unused")
    int XXX = 666;

    int m1();

    @SuppressWarnings("unused")
    default int m2() {
        return 66;
    }

    default void printM1() {
        System.out.println(m1());
    }

}
