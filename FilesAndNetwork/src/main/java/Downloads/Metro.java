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

public class Metro {



     public void downloadJsonInfo()throws Exception{
         String html = parseFile("data/code.html");
         Document doc = Jsoup.parse(html);

         Elements lineElement = doc.select("span.js-metro-line");
         Elements stationElement = doc.select("span.name");
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


//     public static JSONObject getObjectStation(Elements stationElements){
//         List<DStations> stList = new ArrayList<>();
//         for (Element element : stationElements){
//             String numberLine = element.parents().attr("data-line");
//             Elements nameStEl = element.select("span.name");
//             for (Element elementstation : nameStEl) {
//                 String nameStation = elementstation.text();
//                 stList.add(new DStations(nameStation, numberLine));
//             }
//         }
//         HashMap<String, List<String>> stationMap = stList.stream()
//                 .collect(Collectors.groupingBy(DStations::getLine
//                         , LinkedHashMap::new
//                         , Collectors.mapping(
//                                 DStations::getStation,
//                                 Collectors.toList())));
//         return new JSONObject(stationMap);
//     }

    public static JSONArray getArrayStations(Elements stationElements) {

        List<DStations> stationsList = new ArrayList<>();
        JSONArray stations = new JSONArray();

        for (Element e : stationElements){
            Elements namest = e.select("span.name");
            String numberLine = e.attr("data-line");

            stationsList.add(new DStations(namest.text(),numberLine,"","",false));
        }
        for (DStations d : stationsList){
            JSONObject object = new JSONObject();
            object.put("name", d.getStation());
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
