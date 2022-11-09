import Downloads.Metro;
import Downloads.Parser;

public class Main {


    public static void main(String[] args) throws Exception {




        Metro metroInfo = new Metro();
        Parser parser = new Parser();
        System.out.println(parser.depthMapComparison());
        metroInfo.downloadJsonInfo();//загрузка в json файл
    }
}


