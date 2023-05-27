package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONObject;
import constants.Paths;
import utils.DataTableConvertor;
import utils.FileUtil;
import utils.JsonUtil;
import utils.RequestGenerator;
import reports.Log;
import substeps.RequestExecutor;
import configs.Params;

public class HttpSteps {

    @And("получаем список питомцев в статусе {string}")
    public void getPetsByStatus(String statuses) {
        try{
            String[] temp = statuses.split(", ");
            ArrayList<String> statusList = new ArrayList<>(Arrays.asList(temp));
            String curlRequest = RequestGenerator.getByStatus(statusList);
            RequestExecutor.sendGet(curlRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }

    @And("получаем питомца по идентификатору")
    public void getPetById(DataTable table) {
        try{
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            String curlRequest = RequestGenerator.getById(mapPlaceholders);
            RequestExecutor.sendGet(curlRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }

    @And("добавляем нового питомца")
    public void addNewPet(DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(Paths.JSON_PATH + File.separator + "AddNewPet.json");
            HashMap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = RequestGenerator.addNew(mapDefault);

            Log.log("Request body:" + "\n" + jsonRequest);

            RequestExecutor.sendPost(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }

    @And("обновляем данные питомца и статус")
    public void updatePet(DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(Paths.JSON_PATH + File.separator + "UpdatePet.json");
            HashMap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = RequestGenerator.updRecord(mapDefault);

            Log.log("Request body:" + "\n" + jsonRequest);

            RequestExecutor.sendPut(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }

    @And("удаляем данные питомца")
    public void deletePet(DataTable table) {
        try{
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            String curlRequest = RequestGenerator.delById(mapPlaceholders);
            RequestExecutor.sendDelete(curlRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }
}