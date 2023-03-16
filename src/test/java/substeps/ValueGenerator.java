package substeps;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import constants.Paths;
import utils.FileUtil;

public class ValueGenerator {
    private static final String Word1 = "GENERATE: ";
    private static final int Word1_space_index = 10;
    private static final String Word2 = "SAVED: ";
    private static final int Word2_space_index = 7;
    private static final Map<String, String> map = new HashMap<>();

    public static String reviewValue(String value) {
        if (value.startsWith(Word1)){
            return defineGenerateValue(value.substring(Word1_space_index));
        }
        if (value.startsWith(Word2)){
            return defineSavedValue(value.substring(Word2_space_index));
        }
        return value;
    }

    private static String defineGenerateValue(String PetId) {
        String value = String.valueOf(generatePetId(Long.valueOf(PetId)));
        map.put(PetId, value);
        return value;
    }

    private static String defineSavedValue(String PetId) {
        String value = savedPetId(PetId);
        map.put(PetId, value);
        return value;
    }

    private static Long generatePetId(Long PetId) {
        String readId = FileUtil.readFile(Paths.INPUT_PATH + File.separator + "Input.txt");
        PetId = Long.valueOf(readId);
        PetId++;
        FileUtil.writeFile(Paths.INPUT_PATH + File.separator + "Input.txt", String.valueOf(PetId));
        return PetId;
    }

    private static String savedPetId(String PetId) {
        PetId = FileUtil.readFile(Paths.INPUT_PATH + File.separator + "Input.txt");
        return PetId;
    }

    public static void clear() {
        map.clear();
    }
}