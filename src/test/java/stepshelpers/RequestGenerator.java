package stepshelpers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import configs.Params;
import constants.Paths;
import utils.StringUtil;

import static utils.StringUtil.composeRequest;

public class RequestGenerator {
    public static String getByStatus(ArrayList<String> statusList) {
        String newRequest = Params.HTTP_PROTOCOL + "://" + Params.HTTP_HOST + Params.GET_STATUS_PATH + "?";
        for(String currentStatus : statusList) {
            newRequest += "status=" + currentStatus + "&";
        }
        newRequest = newRequest.substring(0, newRequest.length() - 1);
        return newRequest;
    }

    public static String getById(HashMap<String, String> mapPlaceholders) {
        String newRequest = "";
        newRequest = Params.HTTP_PROTOCOL + "://" + Params.HTTP_HOST + Params.GET_ADD_UPD_DEL_ID_PATH;
        newRequest += StringUtil.replacePlaceholdersForCurl(mapPlaceholders);
        return newRequest;
    }

    public static String addNew(HashMap<String, String> mapPlaceholders) {
        return composeRequest(Paths.JSON_PATH + File.separator + "AddNewPet.json", mapPlaceholders);
    }

    public static String updRecord(HashMap<String, String> mapPlaceholders) {
        return composeRequest(Paths.JSON_PATH + File.separator + "UpdatePet.json", mapPlaceholders);
    }

    public static String delById(HashMap<String, String> mapPlaceholders) {
        String newRequest = "";
        newRequest = Params.HTTP_PROTOCOL + "://" + Params.HTTP_HOST + Params.GET_ADD_UPD_DEL_ID_PATH;
        newRequest += StringUtil.replacePlaceholdersForCurl(mapPlaceholders);
        return newRequest;
    }
}