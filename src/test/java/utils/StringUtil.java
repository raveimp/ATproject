package utils;

import java.util.Comparator;
import java.util.HashMap;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.JsonNode;
import exceptions.AtStringUtilException;
import stepshelpers.ValueGenerator;

public class StringUtil {
    public static final String PREFIX = "#{placeholder_";
    public static final String POSTFIX = "}";

    public static String replacePlaceholders(String input, HashMap<String, String> placeholders) {
        String output = input;
        String value;
        for (String key : placeholders.keySet()) {
            String placeholder = PREFIX + key + POSTFIX;
            if (input.contains(placeholder)) {
                value = placeholders.get(key);
                if (value.equals("null")) {
                    value = "";
                }
                output = output.replace(placeholder, value);
                if (value.contains("GENERATE") || value.contains("SAVED") || value.contains("EXIST")) {
                    String temp = ValueGenerator.reviewValue(value);
                    output = output.replace(value, temp);
                 } else {
                    output = output.replace(placeholder, value);
                }
            }
        }
        if (output != null && output.contains(PREFIX)) {
            int firstIndex = output.indexOf(PREFIX);
            int lastIndex = output.indexOf(POSTFIX, firstIndex);
            String p = output.substring(firstIndex, lastIndex + 1);
            throw new AtStringUtilException("Some '" + p + "' is present but it should be replaced.");
        }
        return output;
    }

    public static String replacePlaceholdersForCurl(HashMap<String, String> placeholders) {
        String output = placeholders.values().toString();
        output = output.substring(output.indexOf('[') + 1, output.indexOf(']'));
        for (String key : placeholders.keySet()) {
            if (output.contains(key)) {
                String value = placeholders.get(key);
                if (value.equals("null")) {
                    value = "";
                }
                if (value.contains("GENERATE") || value.contains("SAVED") || value.contains("EXIST") || value.contains("ALTERED")) {
                    String temp = ValueGenerator.reviewValue(value);
                    output = output.replace(value, temp);
                } else {
                    output = output.replace(key, value);
                }
            } else {
                throw new AtStringUtilException("'" + key + "' is absent.");
            }
        }
        return output;
    }

    public static String composeRequest(String BodyFilePath, HashMap<String, String> placeholders) {
        String body = FileUtil.readFile(BodyFilePath);
        try {
            body = StringUtil.replacePlaceholders(body, placeholders);
        } catch (AtStringUtilException Ex) {
            throw new AtStringUtilException(Ex.getMessage() + " File: " + BodyFilePath);
        }
        return body;
    }

    public static class NodeComparator implements Comparator<JsonNode> {
        public int compare(JsonNode jn1, JsonNode jn2) {
            if (jn1.equals(jn2)) {
                return 0;
            }
            if ((jn1 instanceof TextNode) && (jn2 instanceof NumericNode)) {
                String str1 = jn1.asText();
                String str2 = jn2.asText();
                if (str1.equalsIgnoreCase(str2)) {
                    return 0;
                }
            }
            return 1;
        }
    }
}