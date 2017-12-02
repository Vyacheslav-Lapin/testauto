package com.epam.trainings.testauto.oop;

public class VarArgs {

    public static void main(String... args) {
        if (args.length > 0)
            return;

        main("1", "2", "3", "+100500");
    }
}
