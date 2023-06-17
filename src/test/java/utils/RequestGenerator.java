package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.common.collect.Iterables;
import configs.Params;
import constants.Paths;
import static utils.StringUtil.composeRequest;

public class RequestGenerator {
    public static String getByStatus(ArrayList<String> statusList) {
        final String[] newRequest = {""};
        newRequest[0] = Params.HTTP_PROTOCOL + "://" + Params.HTTP_HOST + Params.GET_STATUS_PATH + "?";
        statusList.forEach(s -> {
            if (s.equals(Iterables.getLast(statusList))) {
                String status = "status=" + s;
                newRequest[0] += status;
            }
            else {
                    String status = "status=" + s;
                    newRequest[0] += status + "&";
                }
            });
        return newRequest[0];
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