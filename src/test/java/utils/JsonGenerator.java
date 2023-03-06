package utils;

import java.io.File;
import java.util.HashMap;
import constants.Paths;

import static utils.StringUtil.composeRequest;

public class JsonGenerator {

    public static String getByStatus(String petStatus, HashMap<String, String> mapPlaceholders) {
        mapPlaceholders.put("status", petStatus);
        return composeRequest(Paths.JSON_PATH + File.separator + "GetPetsByStatus.json", mapPlaceholders);
    }

    public static String getById(String petId, HashMap<String, String> mapPlaceholders) {
        mapPlaceholders.put("id", petId);
        return composeRequest(Paths.JSON_PATH + File.separator + "GetPetById.json", mapPlaceholders);
    }

    public static String addNew(String petStatus, String petId, HashMap<String, String> mapPlaceholders) {
        mapPlaceholders.put("status", petStatus);
        mapPlaceholders.put("id", petId);
        return composeRequest(Paths.JSON_PATH + File.separator + "AddNewPet.json", mapPlaceholders);
    }

    public static String updRecord(String petStatus, String petId, HashMap<String, String> mapPlaceholders) {
        mapPlaceholders.put("status", petStatus);
        mapPlaceholders.put("id", petId);
        return composeRequest(Paths.JSON_PATH + File.separator + "UpdatePet.json", mapPlaceholders);
    }

    public static String delById(String petId, HashMap<String, String> mapPlaceholders) {
        mapPlaceholders.put("id", petId);
        return composeRequest(Paths.JSON_PATH + File.separator + "DeletePetById.json", mapPlaceholders);
    }
}