package com.epam.trainings.testauto.oop;

import java.io.IOException;
import java.util.Properties;

public class Anonimus {

    interface Int1 {
        void m1();
        void m2();
    }

    public static void main(String... args) {

        Int1 int1 = new Int1() {
            int x;

            {
                Properties properties = new Properties();
                try {
                    properties.load(Anonimus.class.getResourceAsStream("/jdbc.properties"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                x = Integer.parseInt(properties.getProperty("poolSize", "5"));
            }

            @Override
            public void m1() {
                System.out.println(x);
            }

            @Override
            public void m2() {
                System.out.println((x + 5));
            }
        };

        int1.m1();
        int1.m2();
    }
}
