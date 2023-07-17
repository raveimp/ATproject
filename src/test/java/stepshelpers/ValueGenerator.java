package stepshelpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import configs.Params;
import exceptions.ValueGeneratorException;
import org.json.JSONObject;

public class ValueGenerator {
    private static final String Word1 = "GENERATE: ";
    private static final int Word1_space_index = 10;
    private static final String Word2 = "SAVED: ";
    private static final int Word2_space_index = 7;
    private static final String Word3 = "EXIST: ";
    private static final int Word3_space_index = 7;
    private static final Map<String, String> map = new HashMap<>();

    public static String reviewValue(String value) {
        if (value.startsWith(Word1)){
            return defineGenerateValue(value.substring(Word1_space_index));
        }
        if (value.startsWith(Word2)){
            return defineSavedValue(value.substring(Word2_space_index));
        }
        if (value.startsWith(Word3)){
            return defineExistValue(value.substring(Word3_space_index));
        }
        return value;
    }

    private static String defineGenerateValue(String petId) {
        String value = generatePetId();
        map.put(petId, value);
        return value;
    }

    private static String defineSavedValue(String petId) {
        String value = savedPetId(petId);
        map.put(petId, value);
        return value;
    }

    private static String defineExistValue(String petId) {
        String value = existPetId();
        map.put(petId, value);
        return value;
    }

    private static String generatePetId() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100000));
    }

    private static String savedPetId(String petId) {
        if (map.containsKey(petId)) {
         return map.get(petId);
        } else {
            RequestExecutor.sendGet(Params.HTTP_PROTOCOL + "://" + Params.HTTP_HOST + Params.GET_STATUS_PATH + "?status=available");
            return existPetId();
        }
    }

    private static String existPetId() {
        String temp = Memory.get("response");
        if (temp.startsWith("[")) {
            temp = temp.substring(temp.indexOf("[") + 1, temp.lastIndexOf("]"));
        } else if (temp.equals("null")) {
            throw new ValueGeneratorException("Bad request.");
        }
        JSONObject obj = new JsonParser().parse(temp);
        String petId = String.valueOf(obj.get("id"));
        map.put(petId, String.valueOf(obj));
        return petId;
    }

    public static void clear() {
        map.clear();
    }
}