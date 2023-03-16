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

    @And("получаем список питомцев в статусе")
    public void getPetsByAllStatuses(DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(
                    Paths.JSON_PATH + File.separator + "GetPetsByStatus.json"
            );
            HashMultimap<String, String> mapDefault = JsonUtil.toArrayMap(new JSONObject(jsonStr));
            HashMultimap<String, String> mapPlaceholders = DataTableConvertor.toArrayMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = JsonGenerator.getByAllStatuses(petStatus, mapDefault);

            Log.log(jsonRequest);

            HttpExecutor.sendGet(Params.GET_STATUS_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }

    @And("получаем питомца по {string}")
    public void getPetById(String petId, DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(
                    Paths.JSON_PATH + File.separator + "GetPetById.json"
            );
            HashMap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = JsonGenerator.getById(petId, mapDefault);

            Log.log(jsonRequest);

            HttpExecutor.sendGet(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }

    @And("добавляем нового питомца в статусе {string}")
    public void addNewPet(String petStatus, String petId, DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(
                    Paths.JSON_PATH + File.separator + "AddNewPet.json"
            );
            HashMap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = JsonGenerator.addNew(petStatus, petId, mapDefault);

            Log.log(jsonRequest);

            HttpExecutor.sendPost(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }

    @And("обновляем данные питомца и статус на {string} по {string}")
    public void updatePet(String petStatus, String petId, DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(
                    Paths.JSON_PATH + File.separator + "UpdatePet.json"
            );
            HashMap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = JsonGenerator.updRecord(petStatus, petId, mapDefault);

            Log.log(jsonRequest);

            HttpExecutor.sendPut(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }

    @And("удаляем данные питомца по {string}")
    public void deletePet(String petStatus, String petId, DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(
                    Paths.JSON_PATH + File.separator + "DeletePetById.json"
            );
            HashMap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = JsonGenerator.delById(petId, mapDefault);

            Log.log(jsonRequest);

            HttpExecutor.sendDelete(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }
}