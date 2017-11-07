package com.epam.trainings.testauto.pkg1;

public class E {

    public static E e;

    private E() {
    }

    public static E getInstanse() {
        if (e == null)
            synchronized (E.class) {
                if (e == null)
                    e = new E();
            }
        return e;
    }
}
