import Downloads.DStations;
import Downloads.Metro;
import Downloads.Parser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    private static final String GET_JSON_WITH_NAMEANDLINE = "src/main/resourses/metro.json";

    public static void main(String[] args) throws Exception {


        Metro metroInfo = new Metro();
        metroInfo.downloadJsonInfo();//загрузка в json файл

        JSONObject objectForJson = new JSONObject();
        objectForJson.put("stations", createStationIndex());
        PrintWriter writer = new PrintWriter("src/main/resourses/stations.json");
        try {
            writer.write(objectForJson.toJSONString());
            writer.flush();
            writer.close();
        }catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static Object createStationIndex() throws Exception{
        JSONObject objectForJson = new JSONObject();
        objectForJson.put("stations", getArrayStationsParam());
        return objectForJson;
    }

    private static JSONArray getArrayStationsParam() throws Exception{
        List<DStations> stationsList = new ArrayList<>();
        Parser parser = new Parser();

        Map<String, String> mapGetDepths = parser.depthMapComparison();
        Map<String, String> mapGetDates = parser.depthMapComparison();
        Map<String, String> mapNamesAndLines = getNameAndLine();

        JSONArray stations = new JSONArray();

        for (Map.Entry<String, String> entryDepth : mapGetDepths.entrySet()){
            for (Map.Entry<String, String> entryDates : mapGetDates.entrySet()){
               for (Map.Entry<String, String> entryNameAndLine : mapNamesAndLines.entrySet()){
                   if ((entryNameAndLine.getKey().equals(entryDepth.getKey())) && entryDates.getKey().equals(entryDepth.getKey())){
                       String name = entryNameAndLine.getKey();
                       String line = entryNameAndLine.getValue();
                       String date = entryDates.getValue();
                       String depth = entryDepth.getValue();
                       boolean f = false;
                       stationsList.add(new DStations(name,line,date,depth,f));
                   }
                }
            }
        }

        for (DStations s : stationsList){
            JSONObject object = new JSONObject();
            object.put("name: ", s.getName());
            object.put("line: ", s.getLine());
            object.put("date: ", s.getDate());
            object.put("depth: ", s.getDepth());
            object.put("connections: ", s.isHasConnect());
            stations.add(object);
        }


        return stations;
    }

    private static Map<String,String> getNameAndLine() throws Exception {
        Map<String, String> mapGetNameAndLine = new TreeMap<>();

            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(GET_JSON_WITH_NAMEANDLINE);

            JSONArray arrayJsonobject = (JSONArray) parser.parse(reader);

            for (Object o : arrayJsonobject) {
                JSONObject jsonObjects = (JSONObject) o;
                String name = (String) jsonObjects.get("name");
                String depth = String.valueOf(jsonObjects.get("number Line"));
                mapGetNameAndLine.put(name, depth);
            }


        return mapGetNameAndLine;
    }

}


