import Downloads.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {


        Metro metroInfo = new Metro();
        metroInfo.downloadJsonInfo();//загрузка в metro.json


        JSONObject objectForJson = new JSONObject();
        objectForJson.put("stations", getArrayStationsParam());


        PrintWriter writer = new PrintWriter("src/main/resources/stations.json"); //загрузка в json.stations
        try {
            writer.write(objectForJson.toJSONString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parser parser = new Parser();
        for (Map.Entry<String, List<String>> entry : parser.parseJson().entrySet()){
            System.out.println("На линии " + entry.getKey() + " - " + entry.getValue().size() + " - станции");
        }

    }

    private static JSONArray getArrayStationsParam() throws Exception {
        List<Stations> stationsList = new ArrayList<>();
        Parser parser = new Parser();

        Map<String, String> mapGetDepths = parser.depthMapComparison();
        Map<String, String> mapGetDates = parser.dateMapComparison();
        Map<String, String> mapNamesAndLines = getNameAndLine();
        Map<String, Boolean> mapBoolean = getBoolean();


        JSONArray stations = new JSONArray();

        for (Map.Entry<String, String> entryDepth : mapGetDepths.entrySet()) {
            for (Map.Entry<String, String> entryDates : mapGetDates.entrySet()) {
                for (Map.Entry<String, String> entryNameAndLine : mapNamesAndLines.entrySet()) {
                    if ((entryNameAndLine.getKey().equals(entryDepth.getKey())) && entryDates.getKey().equals(entryDepth.getKey())) {
                        String name = entryNameAndLine.getKey();
                        String line = entryNameAndLine.getValue();
                        String date = entryDates.getValue();
                        String depth = entryDepth.getValue();
                        boolean b = false;
                        for (Map.Entry<String, Boolean> entryBool : mapBoolean.entrySet()) {
                            if (entryNameAndLine.getKey().equals(entryBool.getKey())) {
                                b = entryBool.getValue();
                            }
                        }
                        stationsList.add(new Stations(name, line, date, depth, b));
                    }
                }
            }
        }
        for (Stations s : stationsList) {
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

    private static Map<String, String> getNameAndLine() throws Exception {
        Map<String, String> map = new TreeMap<>();
        List<Stations> stationsList = new ArrayList<>(); // здесь станции с именами и номерами линий
        Metro metro = new Metro();
        String html = metro.parseFile("data/code.html");
        Document doc = Jsoup.parse(html);
        Elements stationElement = doc.select("div.js-depend");

        for (Element tableElements : stationElement) {
            Elements tableAllnames = tableElements.select("span.name"); //перебор всех имен в таблицах
            for (Element nameStation : tableAllnames) { // конкретное имя станции
                String numberLine = tableElements.children().attr("data-line"); // конкретная линия станции
                stationsList.add(new Stations(nameStation.text(), numberLine));
            }
        }
        for (Stations d : stationsList) {
            map.put(d.getName(), d. getLine());
        }
        return map;
    }

        private static Map<String, Boolean> getBoolean () throws Exception {
            Map<String, Boolean> map = new TreeMap<>();
            JSONParser parser = new JSONParser();

            JSONObject arrayJsonobject = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(("src/main/resources/metro.json"))));


            JSONArray objWithBoolean = (JSONArray) arrayJsonobject.get("connections:");
            for (Object o : objWithBoolean) {
                String name = (String) ((JSONObject) o).get("name:");
                boolean bool = (boolean) ((JSONObject) o).get("connections:");
                map.put(name, bool);
            }


            return map;
        }


    }



