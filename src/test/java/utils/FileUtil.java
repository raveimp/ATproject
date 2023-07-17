package utils;

import java.io.File;
import java.io.IOException;
import exceptions.AtFileUtilException;
import org.apache.commons.io.FileUtils;

public class FileUtil {
    public static String readFile(String path) {
        File file = new File(path);
        String reader;
        try {
            reader = FileUtils.readFileToString(file, "UTF-8");
        }
        catch (IOException Ex) {
            throw new AtFileUtilException(Ex);
        }
        return reader;
    }
}