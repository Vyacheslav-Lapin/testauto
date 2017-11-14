package com.epam.trainings.testauto.io.ser;

import lombok.SneakyThrows;
import lombok.Value;
import lombok.experimental.var;

import java.io.Serializable;
import java.lang.reflect.Parameter;

@Value
public class Point implements Serializable {
    private final double x;
    private final double y;

//    @SneakyThrows
    public static void main(String... args) {
        var point = new Point(876, 2);
//        point.setX(876);
        double x = point.getX();
        System.out.println("x = " + x);

        System.out.println("point = " + point);

        for (Parameter parameter : Point.class.getConstructors()[0].getParameters()) {
            System.out.println("parameter.getName() = " + parameter.getName());
        }

        point.m1();
    }

    public void m1() {
        throwEx(new Exception());
    }

    private <E extends Exception> void throwEx(Exception e) throws E {
        throw (E) e;
    }
}
