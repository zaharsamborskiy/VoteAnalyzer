import Downloads.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {


        Metro metroInfo = new Metro();
        metroInfo.downloadJsonInfo();//загрузка в metro.json

        JSONObject objectForJson = new JSONObject();
        objectForJson.put("stations", getArrayStationsParam()); //загрузка в json.stations


        PrintWriter writer = new PrintWriter("src/main/resources/stations.json");
        try {
            writer.write(objectForJson.toJSONString());
            writer.flush();
            writer.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONArray getArrayStationsParam() throws Exception{
        List<DStations> stationsList = new ArrayList<>();
        Parser parser = new Parser();

        Map<String, String> mapGetDepths = parser.depthMapComparison();
        Map<String, String> mapGetDates = parser.dateMapComparison();
        Map<String, String> mapNamesAndLines = getNameAndLine();
        Map<String, Boolean> mapBoolean = getBoolean();



        JSONArray stations = new JSONArray();

        for (Map.Entry<String, String> entryDepth : mapGetDepths.entrySet()){
            for (Map.Entry<String, String> entryDates : mapGetDates.entrySet()){
               for (Map.Entry<String, String> entryNameAndLine : mapNamesAndLines.entrySet()){
                   if ((entryNameAndLine.getKey().equals(entryDepth.getKey())) && entryDates.getKey().equals(entryDepth.getKey())){
                       String name = entryNameAndLine.getKey();
                       String line = entryNameAndLine.getValue();
                       String date = entryDates.getValue();
                       String depth = entryDepth.getValue();
                       boolean b = false;
                       for (Map.Entry<String, Boolean> entryBool : mapBoolean.entrySet()){
                           if (entryNameAndLine.getKey().equals(entryBool.getKey())){
                               b = entryBool.getValue();
                           }
                       }
                       stationsList.add(new DStations(name,line,date,depth,b));
                   }
                }
            }
        }

        for (DStations s : stationsList){
            JSONObject object = new JSONObject();
            object.put("name:", s.getName());
            object.put("line:", s.getLine());
            object.put("date:", s.getDate());
            object.put("depth:", s.getDepth());
            object.put("connections:", s.isHasConnect());
            stations.add(object);
        }


        return stations;
    }

    private static Map<String,String> getNameAndLine() throws Exception {
        Map<String, String> mapGetNameAndLine = new TreeMap<>();
        JSONParser parser = new JSONParser();

        JSONObject arrayJsonobject = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(("src/main/resources/metro.json"))));

        JSONArray objWithName = (JSONArray) arrayJsonobject.get("stations");
        JSONArray objWithLine = (JSONArray) arrayJsonobject.get("lines");


        for (Object o : objWithName) {
            for (Object o1 : objWithLine) {
                String name = (String) ((JSONObject) o).get("name");
                if (((JSONObject) o).get("number Line").equals(((JSONObject)o1).get("number"))){ //Проверяем если номера линий совпали
                    String nameLine = (String) ((JSONObject)o1).get("name"); // то берем имя линии
                    mapGetNameAndLine.put(name, nameLine);
                }
            }
        }

        return mapGetNameAndLine;
    }

    private static Map<String, Boolean> getBoolean() throws Exception {
        Map<String, Boolean> map = new TreeMap<>();
        JSONParser parser = new JSONParser();

        JSONObject arrayJsonobject = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(("src/main/resources/metro.json"))));


        JSONArray objWithBoolean = (JSONArray) arrayJsonobject.get("connections:");
        for (Object o : objWithBoolean){
            String name = (String) ((JSONObject)o).get("name:");
            boolean bool = (boolean) ((JSONObject)o).get("connections:");
            map.put(name, bool);
        }

        return map;
    }
    }


