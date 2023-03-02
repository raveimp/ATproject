package utils;

import java.io.File;
import java.util.HashMap;

import constants.Paths;
import constants.Paths;
import utils.FileUtil;
import utils.StringUtil;

import static utils.StringUtil.composeRequest;

public class JsonGenerator {
    public static String generate(String fileName, HashMap<String, String> mapPlaceholders) {
        return composeRequest(Paths.JSON_PATH + File.separator + fileName + ".json", mapPlaceholders);
    }
    public static String getPets(String petStatus, HashMap<String, String> mapPlaceholders) {
        //mapPlaceholders.put(petStatus); Должно быть 2 параметра
        return composeRequest(Paths.JSON_PATH + File.separator + "GetPetsByStatus.json", mapPlaceholders);
    }
}