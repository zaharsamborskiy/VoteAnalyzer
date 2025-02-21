package Downloads;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

import java.util.*;

public class Parser {
    public Map<String, String> hashMap;
    public Parser() {
        hashMap = new HashMap<>();
    }

    private static final String DATA_GET = "data";
    private static final File GET_JSONDEPTH1 = finder().get(1);
    private static final File GET_CSVDEPTH2 = finder().get(4);
    private static final File GET_JSONDEPTH3 = finder().get(3);
    private static final File GET_CSVDATE1 = finder().get(2);
    private static final File GET_JSONDATE2 = finder().get(0);
    private static final File GET_CSVDATE3 = finder().get(5);

    public Map<String, String> dateMapComparison() throws Exception {
        Map<String, String> comparisonFirstStep = new TreeMap<>();
        Map<String, String> comparisonSecondStep = new TreeMap<>();
        String k;
        String v;

        Map<String, String> d1 = parseDates1csv();
        Map<String, String> d2 = parseDates2json();
        Map<String, String> d3 = parseDates3csv();

        for (Map.Entry<String, String> entry1 : d1.entrySet()) {
            for (Map.Entry<String, String> entry2 : d2.entrySet()) {
                if (entry1.getKey().equals(entry2.getKey()) && entry1.getValue().equals(entry2.getValue())) {
                    k = entry1.getKey();
                    v = entry1.getValue();
                } else {
                    k = entry2.getKey();
                    v = entry2.getValue();
                }
                comparisonFirstStep.put(k, v);
            }
        }
        for (Map.Entry<String, String> entry2 : comparisonFirstStep.entrySet()) {
            for (Map.Entry<String, String> entry3 : d3.entrySet()) {
                if (entry2.getKey().equals(entry3.getKey()) && entry2.getValue().equals(entry3.getValue())) {
                    k = entry2.getKey();
                    v = entry2.getValue();
                } else {
                    k = entry3.getKey();
                    v = entry3.getValue();
                }
                comparisonSecondStep.put(k, v);
            }
        }
        return comparisonSecondStep;
    }

    public Map<String, String> depthMapComparison() throws Exception {
        Map<String, String> comparisonFirstStep = new TreeMap<>();
        Map<String, String> comparisonSecondStep = new TreeMap<>();
        String k;
        String v;

        Map<String, String> d1 = parseDepth1json();
        Map<String, String> d2 = parseDepth2csv();
        Map<String, String> d3 = parseDepth3json();

        for (Map.Entry<String, String> entry1 : d1.entrySet()) {
            for (Map.Entry<String, String> entry2 : d2.entrySet()) {
                if ((entry1.getValue().equals("?")) || entry1.getValue().equals("0")) {
                    k = entry1.getKey();
                    v = entry2.getValue();
                } else if ((entry2.getValue().equals("?")) || entry2.getValue().equals("0")) {
                    k = entry1.getKey();
                    v = entry1.getValue();
                } else {
                    k = entry1.getKey();
                    v = entry1.getValue();
                }
                comparisonFirstStep.put(k, v);
            }
        }
        for (Map.Entry<String, String> entry2 : comparisonFirstStep.entrySet()) {
            for (Map.Entry<String, String> entry3 : d3.entrySet()) {
                if ((entry3.getValue().equals("?")) || entry3.getValue().equals("0")) {
                    k = entry3.getKey();
                    v = entry2.getValue();
                } else if ((entry2.getValue().equals("?")) || entry2.getValue().equals("0")) {
                    k = entry3.getKey();
                    v = entry3.getValue();
                } else {
                    k = entry3.getKey();
                    v = entry3.getValue();
                }
                comparisonSecondStep.put(k, v);
            }
        }

        return comparisonSecondStep;
    }

    public static Map<String, String> parseDates1csv() throws Exception {
        Map<String, String> mapDates1csv = new HashMap<>();

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(GET_CSVDATE1));
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            mapDates1csv.put((line.replaceAll("[^а-яА-я]", "").trim()), (line.replaceAll("[а-яА-я\"ёЁ\",]", "").trim()));
        }
        reader.close();
        return mapDates1csv;
    }

    public static Map<String, String> parseDates2json() throws Exception {

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(GET_JSONDATE2);
        JSONArray arrayJsonobject = (JSONArray) parser.parse(reader);

        Map<String, String> mapDates2 = new HashMap<>();
        for (Object o : arrayJsonobject) {
            JSONObject jsonObjects = (JSONObject) o;
            String name = (String) jsonObjects.get("name");
            String date = String.valueOf(jsonObjects.get("date"));
            mapDates2.put(name, date);
        }
        return mapDates2;
    }

    public static Map<String, String> parseDates3csv() throws Exception {
        Map<String, String> mapDates3csv = new HashMap<>();

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(GET_CSVDATE3));
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            mapDates3csv.put((line.replaceAll("[^а-яА-я]", "").trim()), (line.replaceAll("[а-яА-я\"ёЁ\",]", "").trim()));
        }
        reader.close();
        return mapDates3csv;
    }

    public static Map<String, String> parseDepth1json() throws Exception {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(GET_JSONDEPTH1);
        JSONArray arrayJsonobject = (JSONArray) parser.parse(reader);

        Map<String, String> mapDepht1 = new HashMap<>();
        for (Object o : arrayJsonobject) {
            JSONObject jsonObjects = (JSONObject) o;
            String name = String.valueOf(jsonObjects.get("name"));
            String depth = String.valueOf(jsonObjects.get("depth"));
            mapDepht1.put(name, depth);
        }
        return mapDepht1;
    }

    public static Map<String, String> parseDepth2csv() throws Exception {
        Map<String, String> mapDepth2csv = new HashMap<>();

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(GET_CSVDEPTH2));
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String replace = line.replaceAll("[а-яА-Я\"ёЁ]", "").trim();
            mapDepth2csv.put((line.replaceAll("[^а-яА-Я]", "").trim()), replace.replaceFirst("[?',']", ""));
        }
        reader.close();

        return mapDepth2csv;
    }

    public static Map<String, String> parseDepth3json() throws Exception {

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(GET_JSONDEPTH3);
        JSONArray arrayJsonobject = (JSONArray) parser.parse(reader);

        Map<String, String> mapDepht3 = new HashMap<>();
        for (Object o : arrayJsonobject) {
            JSONObject jsonObjects = (JSONObject) o;
            String name = (String) jsonObjects.get("station_name");
            String depth = String.valueOf(jsonObjects.get("depth_meters"));
            mapDepht3.put(name, depth);
        }
        return mapDepht3;
    }

    public Map<String, List<String>> parseJson() throws Exception{
        Map<String, List<String>> mapWithArray = new TreeMap<>();
        JSONParser parser = new JSONParser();
        JSONObject arrayJsonobject = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(("src/main/resources/metro.json"))));


        JSONObject objects = (JSONObject) arrayJsonobject.get("stations");
        Iterator<String> keys = objects.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            if (objects.get(key) instanceof JSONArray) {
                JSONArray array = (JSONArray) objects.get(key);
                mapWithArray.put(key, array);
            }
        }
        return mapWithArray;
    }

    public static List<File> finder(){
        List<File> fileList = new ArrayList<>();
        serchFiles(new File(DATA_GET), fileList);
        return fileList;
    }

    public static void serchFiles(File rootFile, List<File> fileList) {
        if (rootFile.isDirectory()) {
            File[] directory = rootFile.listFiles();
            if (directory != null) {
                for (File file : directory) {
                    if (file.isDirectory()) {
                        serchFiles(file, fileList);
                    } else {
                    }
                    if (file.getName().toLowerCase().endsWith(".json")  || file.getName().endsWith(".csv")) {
                        fileList.add(file);
                    }
                }
            }
        }
    }
}
