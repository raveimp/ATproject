package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;
import constants.Paths;
import utils.DataTableConvertor;
import utils.FileUtil;
import utils.JsonUtil;
import utils.JsonGenerator;
import exceptions.AtFileUtilException;
import reports.Log;
import substeps.httpExecutor;
import configs.Params;

import static substeps.httpExecutor.sendGet;

public class HTTPSteps {
    @And ("получаем список питомцев в статусе {string}")
    public void getAvailablePets(String petStatus, DataTable table) {                                                   //создаем метод для получения доступных питомцев
        File reqFile = new File(Paths.JSON_PATH);                                                                                  //создаем переменную с файлом запроса
        if(reqFile.exists()){                                                                                           //если файл существует
            String jsonStr = FileUtil.readFile(                                                                         //то читаем файл и записываем в переменную
                    Paths.JSON_PATH + File.separator + "GetPetsByStatus.json"                                                               //путь к файлу
            );
            HashMap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));                           //добавляем запрос из файла в HashMap
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");               //добавляем входные параметры запроса из таблицы placeholder в HashMap
            mapDefault.putAll(mapPlaceholders);                                                            //накладываем два HashMap друг на друга

            String jsonRequest = JsonGenerator.getPets(petStatus, mapDefault);                                         //из полученного HashMap генерируем json запрос

            Log.log(jsonRequest);                                                                                       //логируем запрос

            try {
                sendGet(Params.GET_STATUS_PATH);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            throw new AtFileUtilException ("Request file doesn't exist");                                              //если файл не существует выводим исключение
        }
    }
}