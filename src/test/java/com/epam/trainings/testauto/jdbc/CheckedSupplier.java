package com.epam.trainings.testauto.jdbc;

import java.util.function.Supplier;

@FunctionalInterface
public interface CheckedSupplier<T> extends Supplier<T> {

    T checkedGet() throws Throwable;

    @Override
    default T get() {
        try {
            return checkedGet();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}
