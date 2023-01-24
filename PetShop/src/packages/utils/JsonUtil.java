package packages.utils;

import java.util.HashMap;
import org.json.JSONObject;
public class JsonUtil {
        public static HashMap<String, Object> toHashMap(String json) {
        JSONObject jsonObj = new JSONObject(json);
        HashMap<String, Object> map = (HashMap<String, Object>) jsonObj.toMap();
        return map;
    }
    public static HashMap<String, String> toHashMap(JSONObject jsonObject) {
        HashMap<String, String> map = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (value instanceof String) {
                map.put(key, (String) value);
            }
            else {
                map.put(key, value.toStrig());
            }
        }
        return map;
    }
}