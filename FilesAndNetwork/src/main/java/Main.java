import Downloads.Metro;
import Downloads.Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws Exception {


        Metro metroInfo = new Metro();
        Parser parser = new Parser();
        metroInfo.downloadJsonInfo();//загрузка в json файл



        System.out.println("Результат сравнения глубин "  + parser.depthMapComparison().size() + " -> " + parser.depthMapComparison() + "\n");
        System.out.println("Результат сравнения дат " + parser.dateMapComparison().size() + " -> " +  parser.dateMapComparison());

    }

}


