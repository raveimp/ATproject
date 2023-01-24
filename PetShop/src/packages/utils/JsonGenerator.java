package packages.utils;

import java.io.File;
import java.util.HashMap;
import packages.constants.Paths;
import packages.exceptions.*;
import packages.utils.FileUtil;
import packages.utils.StringUtil;
public class JsonGenerator {
    public static String generate(String fileName, HashMap<String, String> mapPlaceholders) {
        return composeRequest(Paths.HTTP_REQUEST + File.separator + fileName + ".json", mapPlaceholders);
    }
}