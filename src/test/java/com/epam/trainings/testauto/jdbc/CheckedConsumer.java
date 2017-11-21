package com.epam.trainings.testauto.jdbc;

import java.util.function.Consumer;

@FunctionalInterface
public interface CheckedConsumer<T> extends Consumer<T> {

    void checkedAccept(T t) throws Throwable;

    @Override
    default void accept(T t) {
        try {
            checkedAccept(t);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}
