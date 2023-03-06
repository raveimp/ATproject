package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import exceptions.GeneralException;
import org.apache.commons.io.FileUtils;

public class FileUtil {

    public static String readFile(String path) {                                                                        //создаем метод readFile для чтения из файла
        File file = new File(path);                                                                                     //создаем объект File
        String reader = null;                                                                                           //создаем пустую строковую переменную куда будут записаны данные из файла
        try {
            reader = FileUtils.readFileToString(file, "UTF-8");                                              //считываем данные из файла, переводим в строку и записываем в переменную reader
        }
        catch (IOException Ex) {
            throw new GeneralException(Ex);                                                                              //ловим исключения
        }
        return reader;                                                                                                   //возвращаем содержимое переменной
    }

    public static void writeFile(String path, String content) {                                                          //создаем метод writeFile для записи в файл
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8)) {   //создаем переменную класса OutputStreamWriter для конвертирования символов в байты и записи в файл?
            writer.write(content);                                                                                       //записываем в переменную данные
            writer.flush();                                                                                              //очищаем поток
        }
        catch (IOException Ex) {
            throw new GeneralException(Ex);                                                                              //ловим исключения
        }
    }
}