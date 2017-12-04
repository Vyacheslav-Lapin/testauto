package com.epam.trainings.testauto.oop;

public class VarArgs {
    // ((\w+\s)+)(\w+)\(([\w\.\s]+)\)\s\{
    // $1strictfp $3($4) {
    public static strictfp void main(String... args) {
        if (args.length > 0)
            return;

        main("1", "2", "3", "+100500");
    }
}
