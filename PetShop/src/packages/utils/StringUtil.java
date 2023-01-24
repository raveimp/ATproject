package packages.utils;

import java.util.HashMap;
import packages.exceptions.GeneralException;
import packages.exceptions.AtStringUtilException;
import packages.reports.Log;
import packages.stepshelpers.Memory;
public class StringUtil {
    public static final String PREFIX = "#{placeholder_";
    public static final String POSTFIX = "}";
    public static String replacePlaceholders(String input, HashMap<String, String> placeholders) {
        String output = input;
        for (String key : placeholders.keySet()) {
            String placeholder = PREFIX + key + POSTFIX;
            if (output.contains(placeholder)) {
                String value = placeholders.get(key);
                if (value.equals("null")) {
                    value = "";
                }
                if (value.contains("GENERATE")) {
                    output = output.replace(placeholder, Memory.review(value));
                } else {
                    output = output.replace(placeholder, value);
                }
            } else {
                throw new AtStringUtilException("'" + placeholder + "' is absent.");
            }
        }
        if (output.contains(PREFIX)) {
            int firstIndex = output.indexOf(PREFIX);
            int lastIndex = output.indexOf(POSTFIX, firstIndex);
            String p = output.subsrting(firstIndex, lastIndex + 1);
            throw new AtStringUtilException("Some '" + p + "' is present but it should be replaced.");
        }
        return output;
    }
    public static String composeRequest(String BodyFilePath, HashMap<String, String> placeholders) {
        String body = FileUtil.readFile(BodyFilePath);
        try {
            body = StringUtil.replacePlaceholders(body, placeholders);
        } catch (AtStringUtilException Ex) {
            throw new GeneralException(Ex.Message() + " File: " + BodyFilePath);
        }
        return body;
    }
}