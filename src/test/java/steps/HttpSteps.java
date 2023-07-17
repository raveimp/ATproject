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
import stepshelpers.RequestGenerator;
import utils.*;
import reports.Log;
import stepshelpers.RequestExecutor;
import stepshelpers.Memory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HttpSteps {
    @And("получаем список питомцев в статусе {string}")
    public void getPetsByStatus(String statuses) {
            String[] temp = statuses.split(", ");
            ArrayList<String> statusList = new ArrayList<>(Arrays.asList(temp));
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
        assertEquals(reqCompare.asText(), respCompare.asText());
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
        } else if (respContent.equals("[]")) {    // возвращается в случае отсутствия данных при запросе по статусу/при вызове по несуществующему статусу
            Log.log("Empty response.");      // не знаю как еще это обыграть. если указать exception, кейс FindPetByStatus будет всегда завершаться ошибкой при status = unknown
        } else if (respContent.equals("null")) {  // возвращается в случае ошибки в пути curl
        throw new HttpStepsException("Bad request.");
        }
    }

    @And("проверяем результат удаления")
    public void checkDelete() {
        String respContent = Memory.get("response");
        assertTrue(respContent, String.valueOf(respContent).contains("Pet not found"));
    }
}