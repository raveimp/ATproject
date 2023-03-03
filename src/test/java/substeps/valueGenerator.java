package substeps;

import java.util.HashMap;
import java.util.Map;

public class valueGenerator {

    private static Long PetId = 0L;
    private static final String Word = "GENERATE: ";
    private static final int Word_space_index = 10;
    private static final Map<String, String> map = new HashMap<>();

    public static String reviewValue(String value) {
        if (value.startsWith(Word)){
            return defineGenerateValue(value.substring(Word_space_index));
        }
        return value;
    }

    private static String defineGenerateValue(String PetId) {
        String value = String.valueOf(generatePetId(Long.valueOf(PetId)));
      //String value = String.valueOf(generatePetId(PetId)); ?
        map.put(PetId, value);
        return value;
    }

    private static Long generatePetId(Long PetId) {
        PetId++;
        return PetId;
    }
}