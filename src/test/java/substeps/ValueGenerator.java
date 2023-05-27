package substeps;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import constants.Paths;
import org.json.JSONObject;
import utils.FileUtil;

public class ValueGenerator {
    private static final String Word1 = "GENERATE: ";
    private static final int Word1_space_index = 10;
    private static final String Word2 = "SAVED: ";
    private static final int Word2_space_index = 7;
    private static final String Word3 = "EXIST: ";
    private static final int Word3_space_index = 7;
    private static final String Word4 = "ALTERED: ";
    private static final int Word4_space_index = 9;
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
        if (value.startsWith(Word4)){
            return defineAlteredValue(value.substring(Word4_space_index));
        }
        return value;
    }

    private static String defineGenerateValue(String PetId) {
        String readId = FileUtil.readFile(Paths.INPUT_PATH + File.separator + "Input.txt");
        PetId = readId;
        String value = String.valueOf(generatePetId(Long.valueOf(PetId)));
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

    private static String defineAlteredValue(String PetId) {
        String value = alteredPetId(PetId);
        map.put(PetId, value);
        return value;
    }

    private static Long generatePetId(Long PetId) {
        PetId++;
        FileUtil.writeFile(Paths.INPUT_PATH + File.separator + "Input.txt", String.valueOf(PetId));
        return PetId;
    }

    private static String savedPetId(String PetId) {
        PetId = FileUtil.readFile(Paths.INPUT_PATH + File.separator + "Input.txt");
        return PetId;
    }

    private static String existPetId(String PetId) {
        String temp = FileUtil.readFile(Paths.INPUT_PATH + File.separator + "RespContent.txt");
        JSONObject obj = new JSONParser().parse(temp);
        PetId = String.valueOf(obj.get("id"));
        FileUtil.writeFile(Paths.INPUT_PATH + File.separator + "AlteredId.txt", PetId);
        return PetId;
    }

    private static String alteredPetId(String PetId) {
        PetId = FileUtil.readFile(Paths.INPUT_PATH + File.separator + "AlteredId.txt");
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