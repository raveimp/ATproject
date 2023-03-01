package packages.utils;

import java.util.HashMap;
import packages.exceptions.GeneralException;
import packages.exceptions.AtStringUtilException;
import packages.reports.Log;
public class StringUtil {
    public static final String PREFIX = "#{placeholder_";                                                               //инициализировали переменную начала строки
    public static final String POSTFIX = "}";                                                                           //инициализировали переменную конца строки
    public static String replacePlaceholders(String input, HashMap<String, String> placeholders) {                      //создали метод по замене начала и конца строки
        //String output = input;                                                                                        //?
        for (String key : placeholders.keySet()) {                                                                      //получаем набор ключей из HashMap таблиц placeholder и для каждого ключа выполняем:
            String placeholder = PREFIX + key + POSTFIX;                                                                //инициализируем переменную placeholder содержащую строку, например, #{placeholder_status}
            if (output.contains(placeholder)) {                                                                         //если переменная output содержит строку placeholder
                String value = placeholders.get(key);                                                                   //инициализируем переменную value и получаем значение ключа из таблиц placeholders
                if (value.equals("null")) {                                                                             //если значение ключа = null
                    value = "";                                                                                         //присваиваем переменной пусто
                }
                if (value.contains("GENERATE")) {                                                                       //если значение ключа содержит слово GENERATE
                    output = output.replace(placeholder, Memory.review(value));                                         //выполняем замену того что в переменной placeholder на новое значение (ссылаемся на метод review в файле Memory.java)
                } else {
                    output = output.replace(placeholder, value);                                                        //иначе делаем замену того что в переменной placeholder на новое значение из переменной value
                }
            } else {
                throw new AtStringUtilException("'" + placeholder + "' is absent.");                                    //выводим ошибку в случае если строка отсутствует
            }
        }
        if (output.contains(PREFIX)) {                                                                                  //если переменная output содержит префикс
            int firstIndex = output.indexOf(PREFIX);                                                                    //инициализируем переменную со значением индекса префикса
            int lastIndex = output.indexOf(POSTFIX, firstIndex);                                                        //инициализируем переменную со значением индекса постфикса начиная отчет с индекса префикса
            String p = output.subsrting(firstIndex, lastIndex + 1);                                                     //определяем переменную, которая содержит подстроку начиная с индекса префикса и заканчивая инфдексом постфикса (+1 т.к. последний индекс не включается). Например, #{placeholder_status} -> status
            throw new AtStringUtilException("Some '" + p + "' is present but it should be replaced.");                  //выводим ошибку если замены не произошло
        }
        return output;
    }
    public static String composeRequest(String BodyFilePath, HashMap<String, String> placeholders) {                    //метод для составления запроса
        String body = FileUtil.readFile(BodyFilePath);                                                                  //определяем переменную body содержащую тело запроса полученное из файла (ссылка на метод readFile в файле FileUtil.java)
        try {
            body = StringUtil.replacePlaceholders(body, placeholders);
        } catch (AtStringUtilException Ex) {
            throw new GeneralException(Ex.Message() + " File: " + BodyFilePath);
        }
        return body;
    }
}