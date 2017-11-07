package com.epam.trainings.testauto.pkg1;

import java.sql.SQLException;

public class B {
    public static int jkhbdf() throws SQLException {
        return 0;
    }

    public static void main(final String... args) {
//        A.C c = new A.C();


//        D d = () -> 5;

        D d = new D() {
            @Override
            public int m1() {
                return args.length;
            }
        };

        Runnable runnable = () -> System.out.println("Hello, world!");

        runnable.run();

//        args = new String[] {"kjghdf", "jhgef"};

//        d.m2();
        d.m1();
    }
}
