package steps;

import com.google.common.collect.HashMultimap;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import java.io.File;
import java.util.HashMap;
//import java.util.Map;
import org.json.JSONObject;
import constants.Paths;
import utils.DataTableConvertor;
import utils.FileUtil;
import utils.JsonUtil;
import utils.JsonGenerator;
import reports.Log;
import substeps.HttpExecutor;
import configs.Params;

public class HttpSteps {

    @And("получаем список питомцев в статусе")
    public void getPetsByStatus(DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(
                    Paths.JSON_PATH + File.separator + "GetPetsByStatus.json"
            );
            HashMultimap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMultimap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = JsonGenerator.getByStatus(mapDefault);

            Log.log(jsonRequest);

            HttpExecutor.sendGet(Params.GET_STATUS_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }
/**
    @And("получаем список питомцев в статусе {string}")
    public void getPetsByStatus(String petStatus, DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(
                    Paths.JSON_PATH + File.separator + "GetPetsByStatus.json"
            );
            HashMap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = JsonGenerator.getByStatus(petStatus, mapDefault);

            Log.log(jsonRequest);

            HttpExecutor.sendGet(Params.GET_STATUS_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }
   */
    @And("получаем питомца по идентификатору")
    public void getPetById(DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(
                    Paths.JSON_PATH + File.separator + "GetPetById.json"
            );
            HashMultimap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMultimap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = JsonGenerator.getById(mapDefault);

            Log.log(jsonRequest);

            HttpExecutor.sendGet(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }

    @And("добавляем нового питомца")
    public void addNewPet(String petId, DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(
                    Paths.JSON_PATH + File.separator + "AddNewPet.json"
            );
            HashMultimap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMultimap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = JsonGenerator.addNew(mapDefault);

            Log.log(jsonRequest);

            HttpExecutor.sendPost(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }

    @And("обновляем данные питомца и статус")
    public void updatePet(DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(
                    Paths.JSON_PATH + File.separator + "UpdatePet.json"
            );
            HashMultimap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMultimap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = JsonGenerator.updRecord(mapDefault);

            Log.log(jsonRequest);

            HttpExecutor.sendPut(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }

    @And("удаляем данные питомца")
    public void deletePet(DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(
                    Paths.JSON_PATH + File.separator + "DeletePetById.json"
            );
            HashMultimap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMultimap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = JsonGenerator.delById(mapDefault);

            Log.log(jsonRequest);

            HttpExecutor.sendDelete(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }
}