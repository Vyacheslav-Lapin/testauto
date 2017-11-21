package com.epam.trainings.testauto.jdbc;

import java.util.function.Function;

@FunctionalInterface
public interface CheckedFunction<T, R> extends Function<T, R> {

    R checkedApply(T t) throws Throwable;

    @Override
    default R apply(T t) {
        try {
            return checkedApply(t);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}
