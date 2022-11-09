package Downloads;

import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.File;

import java.io.FileReader;

import java.util.*;

public class Parser {
    public Parser() {
    }

    private static final String DATA_GET_DEPTH_1 = "data";

    public Map<String,String> depthMapComparison() throws Exception {
        Map<String, String> comparison = new TreeMap<>();
        String k ="";
        String v ="";

        Map<String, String> d1 = parseDepth1();
        Map<String, String> d2 = parseDepth2();
        for(Map.Entry<String, String> entry1: d1.entrySet()) {
            for(Map.Entry<String, String> entry2: d2.entrySet()) {
                if (entry1.getKey().equals(entry2.getKey()) && entry1.getValue().equals("0")){
                    k = entry1.getKey();
                    v = entry2.getValue();
//                    v = entry2.setValue(entry1.getValue());
            } else {
                    k = entry1.getKey();
                    v = entry1.getValue();
                }
                comparison.put(k, v);
        }
        }
        return comparison;
    }


    public Map<String, String> parseDepth1() throws Exception {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(finder().get(1));
        JSONArray arrayJsonobject = (JSONArray) parser.parse(reader);

        Map<String, String> mapDepht1 = new HashMap<>();
        for (Object o : arrayJsonobject){
            JSONObject jsonObjects = (JSONObject) o;
            String name = String.valueOf(jsonObjects.get("name"));
            String depth = String.valueOf(jsonObjects.get("depth"));
            mapDepht1.put(name, depth);
        }

        return mapDepht1;
    }

    public Map<String, String> parseDepth2() throws Exception {

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(finder().get(2));
        JSONArray arrayJsonobject = (JSONArray) parser.parse(reader);

        Map<String, String> mapDepht2 = new HashMap<>();
        for (Object o : arrayJsonobject){
            JSONObject jsonObjects = (JSONObject) o;
            String name = (String) jsonObjects.get("station_name");
            String depth = String.valueOf(jsonObjects.get("depth_meters"));
            mapDepht2.put(name, depth);
        }

        return mapDepht2;
    }

    public List<File> finder(){
        List<File> fileList = new ArrayList<>();
        serchFiles(new File(DATA_GET_DEPTH_1), fileList);

        for (File file : fileList) {
            System.out.println("Найдено в -> " + file.getAbsolutePath());
    }
        return fileList;
}


    public void serchFiles(File rootFile, List<File> fileList) {
        if (rootFile.isDirectory()) {
            System.out.println("Ищем json файлы");
            File[] directory = rootFile.listFiles();
            if (directory != null) {
                for (File file : directory) {
                    if (file.isDirectory()) {
                        serchFiles(file, fileList);
                    } else {
                    }
                    if (file.getName().toLowerCase().endsWith(".json")) {
                        fileList.add(file);
                    }
                }
            }
        }
    }
}
