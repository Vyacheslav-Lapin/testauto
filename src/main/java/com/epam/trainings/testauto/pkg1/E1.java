package com.epam.trainings.testauto.pkg1;

public class E1 {
    private static E1 ourInstance = new E1();

    public static E1 getInstance() {
        return ourInstance;
    }

    private E1() {
    }
}
