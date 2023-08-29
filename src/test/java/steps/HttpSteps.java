package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.HttpStepsException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import java.io.File;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import constants.Paths;
import configs.Params;
import org.junit.Assert;
import stepshelpers.*;
import utils.*;
import reports.Log;

public class HttpSteps {

    @And("получаем список питомцев в статусе {string}")
    public void getPetsByStatus(String statuses) {
        String[] temp = statuses.split(", ");
        ArrayList<String> statusList = new ArrayList<>(Arrays.asList(temp));
        Memory.put("status", String.valueOf(statusList));
        String curlRequest = RequestGenerator.getByStatus(statusList);
        RequestExecutor.sendGet(curlRequest);
    }

    @And("получаем питомца по идентификатору")
    public void getPetById(DataTable table) {
        HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
        String curlRequest = RequestGenerator.getById(mapPlaceholders);
        RequestExecutor.sendGet(curlRequest);
    }

    @And("добавляем нового питомца")
    public void addNewPet(DataTable table) {
        String jsonStr = FileUtil.readFile(Paths.JSON_PATH + File.separator + "AddNewPet.json");
        HashMap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
        HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
        mapDefault.putAll(mapPlaceholders);
        String jsonRequest = RequestGenerator.addNew(mapDefault);
        Memory.put("request", jsonRequest);
        Log.log("POST request body:" + "\n" + jsonRequest);
        RequestExecutor.sendPost(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
    }

    @And("обновляем данные питомца и статус")
    public void updatePet(DataTable table) {
        String jsonStr = FileUtil.readFile(Paths.JSON_PATH + File.separator + "UpdatePet.json");
        HashMap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
        HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
        mapDefault.putAll(mapPlaceholders);
        String jsonRequest = RequestGenerator.updRecord(mapDefault);
        Memory.put("request", jsonRequest);
        Log.log("PUT request body:" + "\n" + jsonRequest);
        RequestExecutor.sendPut(Params.GET_ADD_UPD_DEL_ID_PATH, jsonRequest);
    }

    @And("удаляем данные питомца")
    public void deletePet(DataTable table) {
        HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
        String curlRequest = RequestGenerator.delById(mapPlaceholders);
        RequestExecutor.sendDelete(curlRequest);
    }

    @And("сравниваем запрос и ответ")
    public void comparator() {
        ObjectMapper mapper = new ObjectMapper();
        String reqContent = Memory.get("request");
        String respContent = Memory.get("response");
        JsonNode reqCompare;
        JsonNode respCompare;
        try {
            reqCompare = mapper.readTree(reqContent);
            respCompare = mapper.readTree(respContent);
        } catch (JsonProcessingException Ex) {
            throw new HttpStepsException(Ex);
        }
        Assert.assertEquals(reqCompare.asText(), respCompare.asText());
    }

    @And("проверяем результат запроса")
    public void checkResponse() {
        String respContent = Memory.get("response");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode respMapper;
        try {
            respMapper = mapper.readTree(respContent);
        } catch (JsonProcessingException Ex) {
            throw new HttpStepsException(Ex);
        }
        if (respMapper.has("code")) {
            String code = String.valueOf(respMapper.get("code"));
            if (!code.equals("200")) {
                throw new HttpStepsException("You got response code other then 200.");
            }
        } else if (respContent.equals("[]")) {
            Log.log("Empty response.");
        } else if (respContent.equals("null")) {
        throw new HttpStepsException("Bad request.");
        } else if (respContent.contains("status")) {
            String statusMemo = Memory.get("status");
            JSONArray respArray = new JSONArray(respContent);
            for (int i = 0; i < respArray.length(); i++) {
                JSONObject respObj = respArray.getJSONObject(i);
                String stats = respObj.getString("status");
                assert (!statusMemo.contains(stats)): ("Status " + stats + " is invalid!");
            }
        }
    }

    @And("проверяем результат удаления")
    public void checkDelete() {
        String respContent = Memory.get("response");
        Assert.assertTrue(respContent, String.valueOf(respContent).contains("Pet not found"));
    }
}