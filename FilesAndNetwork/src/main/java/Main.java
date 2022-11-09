import Downloads.Metro;
import Downloads.Parser;

import java.util.Map;

public class Main {


    public static void main(String[] args) throws Exception {


        Metro metroInfo = new Metro();
        Parser parser = new Parser();
        System.out.println(parser.depthMapComparison());
        metroInfo.downloadJsonInfo();//загрузка в json файл

        Map<String, String> mapForDepth = parser.depthMapComparison();
        for (Map.Entry<String, String> entryWithDepth : mapForDepth.entrySet()) {
        }


    }
}


