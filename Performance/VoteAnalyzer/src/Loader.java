import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Loader
{
    /**
     *                                     +     Затрачено      +       Затрачено     +
     *         Файлы                       +      времени       +        памяти       +
     * ____________________________________+____________________+_____________________+
     * + Парсинг файла 0.2М.xml            +    ≈ 512MS         +       ≈ 21MB        +
     * +-----------------------------------+--------------------+---------------------+
     * + Парсинг файла 1М.xml              +   ≈ 1.730MS        +       ≈ 29MB        +
     * +-----------------------------------+--------------------+---------------------+
     * + Парсинг файла 18М.xml             +   ≈ 12.692MS       +       38-90MB       +
     * +-----------------------------------+--------------------+---------------------+
     * + Парсинг файла 1572М.xml           +   ≈ 424.650MS      + первый запрос  42MB +
     * + через preparedStatement           +   (около 7 мин)    + второй запрос  40MB +
     * +-----------------------------------+--------------------+---------------------+
     * + Парсинг файла 1572М.xml           +   ≈ 419.320MS      + первый запрос  24MB +
     * + через multiInsert(с дупликатами)  +   (около 7 мин)    + второй запрос  33MB +
     * ------------------------------------+--------------------+---------------------+
     * + Парсинг файла 1572М.xml           +   ≈ 59.319MS       + первый запрос 145МВ +
     * + через multiInsert(без дупликатов) +   (около минуты)   + второй запрос 146МВ +
     * ------------------------------------+--------------------+---------------------+
     */

    private static final String DATA_02M = "res/data-0.2M.xml";
    private static final String DATA_1M = "res/data-1M.xml";
    private static final String DATA_18M = "res/data-18M.xml";
    private static final String DATA_1572M = "res/data-1572M.xml";

    public static void main(String[] args) throws Exception {
        String fileName = DATA_1572M;
        long start = System.currentTimeMillis();

        parseInSQL(fileName);

        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("\nUsed memory: "
                + (double) usage / Math.pow(2, 20) + "MB\n"
                + "Used time: " + (System.currentTimeMillis() - start)+ "MS" + "\n");
        try{
            DBConnection.printVoterCounts();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }finally {
            DBConnection.getConnection().close();
        }
    }

    public static void parseInSQL(String file) throws SQLException, IOException, SAXException, ParserConfigurationException {
        DBConnection.connection = DBConnection.getConnection();
//        DBConnection.connection.setAutoCommit(false);
//        DBConnection.preparedStatement = DBConnection
//                .getConnection()
//                .prepareStatement("INSERT INTO voter_count(name, birthDate) " + "VALUES (?, ?)");

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File(file), new XMLHandler());
    }
}