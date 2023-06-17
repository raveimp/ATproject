package utils;

import java.util.HashMap;
import org.json.JSONObject;

public class JsonUtil {
    public static HashMap<String, String> toHashMap(JSONObject jsonObject) {
        HashMap<String, String> map = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (value instanceof String) {
                map.put(key, (String) value);
            }
            else {
                map.put(key, value.toString());
            }
        }
        return map;
    }
}