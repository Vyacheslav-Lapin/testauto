package com.epam.trainings.testauto.ann;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Ann1(x = 5)
//@Ann1(x = 6)
public class AnnotationSample {

    public static void main(@Ann1 String... args) {
        Ann1 ann1 = AnnotationSample.class.getAnnotation(Ann1.class);
        System.out.println("ann1 == null = " + (ann1 == null));

        //noinspection ConstantConditions
        System.out.println("ann1.x() = " + ann1.x());

        Annotation[] annotations = AnnotationSample.class.getAnnotations();
        System.out.println("annotations.length = " + annotations.length);

        List<Integer> integers= new ArrayList<>();

        int value = 5;

        integers.add(value);
        integers.add(new Integer(6));
        integers.add(new Integer(10));
        integers.add(new Integer(5));
        integers.add(new Integer(5));
        integers.add(new Integer(5));
        integers.add(new Integer(5));
        integers.add(new Integer(5));

        int i = integers.get(0);

        System.out.println(Integer.valueOf(128) == Integer.valueOf(128));

        IntStream.of(5,2,1,8,4,324,32,235,325,5,4,4,54,3,43,43,43,3,4);
    }
}
