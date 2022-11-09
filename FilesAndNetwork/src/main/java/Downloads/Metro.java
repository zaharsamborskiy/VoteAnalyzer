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
import java.util.ArrayList;

import java.util.List;
import java.util.Map;


public class Metro  {
    static Parser parser = new Parser();
    public static Map<String, String> mapWithDepth;

    static {
        try {
            mapWithDepth = parser.depthMapComparison();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Metro() throws Exception {
    }


    public void downloadJsonInfo()throws Exception{

         String html = parseFile("data/code.html");
         Document doc = Jsoup.parse(html);

         Elements lineElement = doc.select("span.js-metro-line");
         Elements stationElement = doc.select("div.js-depend");
         JSONObject objectForJson = new JSONObject();

         PrintWriter writer = new PrintWriter("src/main/resourses/metro.json");

         objectForJson.put("stations", getArrayStations(stationElement));
         objectForJson.put("lines", getArrayLine(lineElement));

         try {
             writer.write(objectForJson.toJSONString());
             writer.flush();
             writer.close();
         }catch (Exception e) {
             e.printStackTrace();
         }
     }


    public static JSONArray getArrayStations(Elements stationElements) {
        List<DStations> stationsList = new ArrayList<>(); // здесь станции с именами и номерами линий
        JSONArray stations = new JSONArray();


        for (Element tableElements : stationElements) {
            Elements tableAllnames = tableElements.select("span.name"); //перебор всех имен в таблицах
            for (Element nameStation : tableAllnames) { // конкретное имя станции
               String numberLine = tableElements.children().attr("data-line"); // конкретная линия станции
                stationsList.add(new DStations(nameStation.text(), numberLine, "", "", false));
            }
        }

        Map<String, String> mapForDepth = mapWithDepth;
        for (Map.Entry<String, String> entryWithDepth : mapForDepth.entrySet()) {
        }


            for (DStations d : stationsList) {
                JSONObject object = new JSONObject();
                object.put("name", d.getName());
                object.put("number Line", d.getLine());
                object.put("date", d.getDate());
                object.put("depth", d.getDepth());
                object.put("hasConnect", d.isHasConnect());
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
