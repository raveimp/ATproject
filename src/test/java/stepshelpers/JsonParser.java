package stepshelpers;

import org.json.JSONObject;

public class JsonParser {

    public JSONObject parse(String temp) {
        return new JSONObject(temp);
    }
}