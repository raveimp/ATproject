package utils;

import java.io.File;
//import java.util.HashMap;
//import java.util.Map;
import constants.Paths;
import com.google.common.collect.HashMultimap;

import static utils.StringUtil.composeRequest;

public class JsonGenerator {
/**
    public static String getByStatus(String petStatus, HashMap<String, String> mapPlaceholders) {
        mapPlaceholders.put("status", petStatus);
        return composeRequest(Paths.JSON_PATH + File.separator + "GetPetsByStatus.json", mapPlaceholders);
    }
*/
    public static String getByStatus(HashMultimap<String, String> mapPlaceholders) {
        //mapPlaceholders.put("status");
        return composeRequest(Paths.JSON_PATH + File.separator + "GetPetsByStatus.json", mapPlaceholders);
}
    public static String getById(HashMultimap<String, String> mapPlaceholders) {
        //mapPlaceholders.put("id");
        return composeRequest(Paths.JSON_PATH + File.separator + "GetPetById.json", mapPlaceholders);
    }

    public static String addNew(HashMultimap<String, String> mapPlaceholders) {
        //mapPlaceholders.put("status");
        //mapPlaceholders.put("id");
        return composeRequest(Paths.JSON_PATH + File.separator + "AddNewPet.json", mapPlaceholders);
    }

    public static String updRecord(HashMultimap<String, String> mapPlaceholders) {
        //mapPlaceholders.put("status");
        //mapPlaceholders.put("id");
        return composeRequest(Paths.JSON_PATH + File.separator + "UpdatePet.json", mapPlaceholders);
    }

    public static String delById(HashMultimap<String, String> mapPlaceholders) {
        //mapPlaceholders.put("id");
        return composeRequest(Paths.JSON_PATH + File.separator + "DeletePetById.json", mapPlaceholders);
    }
}