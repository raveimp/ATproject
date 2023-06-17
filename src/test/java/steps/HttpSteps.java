package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.HttpStepsException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONObject;
import constants.Paths;
import configs.Params;
import utils.*;
import reports.Log;
import stepshelpers.RequestExecutor;

import static org.junit.Assert.assertTrue;

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
            throw new HttpStepsException(Ex);
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
            throw new HttpStepsException(Ex);
        }
    }

    @And("добавляем нового питомца")
    public void addNewPet(DataTable table) {
            String jsonStr = FileUtil.readFile(Paths.JSON_PATH + File.separator + "AddNewPet.json");
            HashMap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);
            String jsonRequest = RequestGenerator.addNew(mapDefault);
            FileUtil.writeFile(Paths.INPUT_PATH + File.separator + "ReqContent.txt", String.valueOf(jsonRequest));
            Log.log("Request body:" + "\n" + jsonRequest);
            RequestExecutor.sendPost(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
    }

    @And("обновляем данные питомца и статус")
    public void updatePet(DataTable table) {
            String jsonStr = FileUtil.readFile(Paths.JSON_PATH + File.separator + "UpdatePet.json");
            HashMap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);
            String jsonRequest = RequestGenerator.updRecord(mapDefault);
            FileUtil.writeFile(Paths.INPUT_PATH + File.separator + "ReqContent.txt", String.valueOf(jsonRequest));
            Log.log("Request body:" + "\n" + jsonRequest);
            RequestExecutor.sendPut(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
    }

    @And("удаляем данные питомца")
    public void deletePet(DataTable table) {
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            String curlRequest = RequestGenerator.delById(mapPlaceholders);
            RequestExecutor.sendDelete(curlRequest);
    }

    @And("проверяем результат")
    public void comparator() {
            String readReq = FileUtil.readFile(Paths.INPUT_PATH + File.separator + "ReqContent.txt");
            String readResp = FileUtil.readFile(Paths.INPUT_PATH + File.separator + "RespContent.txt");
            ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode first = mapper.readTree(readReq);
            JsonNode second = mapper.readTree(readResp);
            StringUtil.NodeComparator cmp = new StringUtil.NodeComparator();
            assertTrue(first.equals(cmp, second));
        } catch (JsonProcessingException Ex) {
            throw new HttpStepsException(Ex);
        }
    }
}