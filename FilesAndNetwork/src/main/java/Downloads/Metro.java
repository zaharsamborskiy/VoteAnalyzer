package Downloads;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;



public class Metro  {



    public Metro() throws Exception {

    }


    public void downloadJsonInfo()throws Exception{

         String html = parseFile("data/code.html");
         Document doc = Jsoup.parse(html);

         Elements lineElement = doc.select("span.js-metro-line");
         Elements stationElement = doc.select("div.js-depend");
         Elements connections = doc.select("div.js-depend");

         JSONObject objectForJson = new JSONObject();

         PrintWriter writer = new PrintWriter("src/main/resources/metro.json");

         objectForJson.put("stations", getArrayStations(stationElement));
         objectForJson.put("connections:",getConnections(connections));
         objectForJson.put("lines", getArrayLine(lineElement));


         try {
             writer.write(objectForJson.toJSONString());
             writer.flush();
             writer.close();
         }catch (Exception e) {
             e.printStackTrace();
         }
     }

     public JSONArray getConnections(Elements e){
        List<DStations> stationList = new ArrayList<>();
        TreeMap<String, String> map = new TreeMap();
        JSONArray stations = new JSONArray();

        for (Element tableE : e){
            Elements tableAllnames = tableE.select("span.name");
            for (Element el : tableAllnames){
                String numLine = tableE.children().attr("data-line");
                stationList.add(new DStations(el.text(), numLine));
                map.put(el.text(),numLine);
            }
        }
        for (DStations l : stationList){
            for (Map.Entry<String, String> s : map.entrySet()){
                JSONObject object = new JSONObject();
                if (!(l.getLine().equals(s.getValue())) && l.getName().equals(s.getKey())){
                    object.put("connections:", true);
                    object.put("name:", s.getKey());
                    object.put("line:", s.getValue());
                    stations.add(object);
                }
            }
        }
        return stations;
     }

    public JSONArray getArrayStations(Elements stationElements) {
        List<DStations> stationsList = new ArrayList<>(); // здесь станции с именами и номерами линий
        JSONArray stations = new JSONArray();


        for (Element tableElements : stationElements) {
            Elements tableAllnames = tableElements.select("span.name"); //перебор всех имен в таблицах
            for (Element nameStation : tableAllnames) { // конкретное имя станции
               String numberLine = tableElements.children().attr("data-line"); // конкретная линия станции
                stationsList.add(new DStations(nameStation.text(), numberLine));
            }
        }
            for (DStations d : stationsList) {
                JSONObject object = new JSONObject();
                object.put("name", d.getName());
                object.put("number Line", d.getLine());
                stations.add(object);
        }

        return stations;
    }

     public static JSONArray getArrayLine (Elements lineElements){
         ArrayList<DLine> lineList = new ArrayList<>();
         JSONArray lines = new JSONArray();

         for (Element elementLine : lineElements){
             String nameLine = elementLine.text();
             String numberLine = elementLine.attr("data-line");
             lineList.add(new DLine(numberLine, nameLine));
         }
         for (DLine l : lineList){
             JSONObject lines1 = new JSONObject();
             lines1.put("number", l.getNumber());
             lines1.put("name", l.getName());
             lines.add(lines1);
         }
         return lines;
     }


    public static String parseFile(String path){
        StringBuilder sb = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> sb.append(line + "\n"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
}
