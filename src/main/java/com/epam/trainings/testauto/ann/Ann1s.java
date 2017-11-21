package com.epam.trainings.testauto.ann;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Ann1s {
    Ann1[] value();
}
