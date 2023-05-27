package utils;

import io.cucumber.datatable.DataTable;
import java.util.HashMap;

public class DataTableConvertor {
    public static HashMap<String, String> toHashMap(DataTable dataTable, String firstColumnName) {
        HashMap<String, String> dataToReturn = new HashMap<>();
        dataToReturn.putAll(dataTable.asMap());
        dataToReturn.remove(firstColumnName);
        return dataToReturn;
    }
}