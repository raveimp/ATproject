package stepshelpers;

import exceptions.ValueGeneratorException;
import java.util.Map;
import java.util.HashMap;

public class Memory {

    private static final Map<String, String> map = new HashMap<>();

    public static void put(String key, String value) {
            map.put(key, value);
    }

    public static String get(String key) {
        if (map.isEmpty()) {
            throw new ValueGeneratorException("Key is absent in memory.");
        }
        return map.get(key);
    }
}