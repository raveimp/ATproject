package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import exceptions.GeneralException;
import org.apache.commons.io.FileUtils;

public class FileUtil {

    public static String readFile(String path) {
        File file = new File(path);
        String reader;
        try {
            reader = FileUtils.readFileToString(file, "UTF-8");
        }
        catch (IOException Ex) {
            throw new GeneralException(Ex);
        }
        return reader;
    }

    public static void writeFile(String path, String content) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8)) {
            writer.write(content);
            writer.flush();
        }
        catch (IOException Ex) {
            throw new GeneralException(Ex);
        }
    }
}