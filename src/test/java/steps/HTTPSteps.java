package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
//import io.cucumber.java.en.Then;
import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;
import constants.Paths;
import utils.DataTableConvertor;
import utils.FileUtil;
import utils.JsonUtil;
import utils.JsonGenerator;
import reports.Log;
import substeps.httpExecutor;
import configs.Params;

public class HTTPSteps {

    @And ("получаем список питомцев в статусе {string}")
    public void getPetsByStatus(String petStatus, DataTable table) {
        try{
            String jsonStr = FileUtil.readFile(
                Paths.JSON_PATH + File.separator + "GetPetsByStatus.json"
            );
            HashMap<String, String> mapDefault = JsonUtil.toHashMap(new JSONObject(jsonStr));
            HashMap<String, String> mapPlaceholders = DataTableConvertor.toHashMap(table, "placeholder");
            mapDefault.putAll(mapPlaceholders);

            String jsonRequest = JsonGenerator.getPets(petStatus, mapDefault);

            Log.log(jsonRequest);

            httpExecutor.sendGet(Params.GET_STATUS_PATH, jsonRequest);
        }
        catch(Exception Ex) {
            Log.log(String.valueOf(Ex));
        }
    }

}