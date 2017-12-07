package com.epam.trainings.testauto.collections;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HW {
    public static void main(String... args) {
        List<String> objects = Collections.emptyList();

        Map<String, Integer> linkedHashMap
                = new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.put("Smith", 30);
        linkedHashMap.put("Anderson", 31);
        linkedHashMap.put("Lewis", 29);
        linkedHashMap.put("Cook", 29);
        linkedHashMap.put("AAA", 28);
        System.out.printf("%nThe age for Lewis is %d%n", linkedHashMap.get("AAA"));
        System.out.printf("%nThe age for Lewis is %d%n", linkedHashMap.get("Smith"));
        System.out.println(linkedHashMap);
    }
}
