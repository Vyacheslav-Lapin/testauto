package com.epam.trainings.testauto.jdbc;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

@FunctionalInterface
public interface CheckedSupplier<T> extends Supplier<T>, Callable<T> {

    @Override
    default T get() {
        try {
            return call();
        } catch (Exception throwable) {
            return throwAsUnchecked(throwable);
        }
    }

    static <R, E extends Exception> R throwAsUnchecked(Exception e) throws E {
        //noinspection unchecked
        throw (E) e;
    }
}
