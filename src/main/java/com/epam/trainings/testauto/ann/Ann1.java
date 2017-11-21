package com.epam.trainings.testauto.ann;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
//@Repeatable(@Ann1s)
public @interface Ann1 {
    int x() default 1;
}
