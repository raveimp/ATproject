package utils;

import io.cucumber.datatable.DataTable;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
import com.google.common.collect.HashMultimap;

public class DataTableConvertor {
/**
    public static List<Map<String, String>> toListOfMaps(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps();
        return list;
    }

    public static HashMap<String, String> toHashMap(DataTable dataTable, String firstColumnName) {
        HashMap<String, String> dataToReturn = new HashMap<>(dataTable.asMap());
        dataToReturn.remove(firstColumnName);
        return dataToReturn;
    }
*/
    public static HashMultimap<String, String> toHashMap(DataTable dataTable, String firstColumnName) {
        HashMultimap<String, String> dataToReturn = new HashMultimap<>(dataTable.asMap());
        dataToReturn.remove(firstColumnName); //для HashMultimap нужно указывать удаляемые ключ и значение. Удалить только колонку с ключом не получится. Как обыграть?
        return dataToReturn;
    }

}