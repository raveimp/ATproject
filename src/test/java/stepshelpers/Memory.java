package stepshelpers;

import exceptions.ValueGeneratorException;
import org.json.JSONObject;
import java.util.Map;
import java.util.HashMap;

public class Memory {
    private static final Map<String, String> map = new HashMap<>();

    public static void put(String jsonRequest) {
        JSONObject obj = new JSONParser().parse(jsonRequest);
        for (String key : obj.keySet()) {
            map.put(key, obj.toString());
        }
    }

    private static class JSONParser {
        public JSONObject parse(String jsonRequest) {
            return new JSONObject(jsonRequest);
        }
    }

    public static String get() {
        String reqContent = null;
        if (map.isEmpty()) {
            throw new ValueGeneratorException("Request is absent in memory.");
        }
        for (String key : map.keySet()) {
            reqContent = map.get(key);
        }
        return reqContent;
    }

    public static Integer checkMap() {
        return map.size();
    }

}
