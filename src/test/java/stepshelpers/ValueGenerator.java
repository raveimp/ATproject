package stepshelpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import configs.Params;
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

    private static String defineGenerateValue(String PetId) {
        String value = generatePetId(PetId);
        map.put(PetId, value);
        return value;
    }

    private static String defineSavedValue(String PetId) {
        String value = savedPetId(PetId);
        map.put(PetId, value);
        return value;
    }

    private static String defineExistValue(String PetId) {
        String value = existPetId(PetId);
        map.put(PetId, value);
        return value;
    }

    private static String generatePetId(String PetId) {
        Random random = new Random();
        PetId = String.valueOf(random.nextInt(100000));
        return PetId;
    }

    private static String savedPetId(String PetId) {
        if (map.containsKey(PetId)) {
         return map.get(PetId);
        } else {
            RequestExecutor.sendGet(Params.HTTP_PROTOCOL + "://" + Params.HTTP_HOST + Params.GET_STATUS_PATH + "?status=available");
            return existPetId(PetId);
        }
    }

    private static String existPetId(String PetId) {
        String temp = Memory.get();
        JSONObject obj = new JSONParser().parse(temp);
        PetId = String.valueOf(obj.get("id"));
        map.put(PetId, String.valueOf(obj));
        return PetId;
    }

    public static void clear() {
        map.clear();
    }

    private static class JSONParser {
        public JSONObject parse(String temp) {
            return new JSONObject(temp);
        }
    }
}