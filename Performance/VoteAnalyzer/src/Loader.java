import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Loader
{
    /**
     *                                 +     Затрачено      +       Затрачено   +
     *         Оптимизация             +      времени       +        памяти     +
     * ________________________________+____________________+___________________+
     * + Парсинг через DOM             +    2479-2561MS     +       764-900MB   +
     * +-------------------------------+--------------------+-------------------+
     * + SAX по умолчанию с видео      +      690MS         +       40-47.9MB   +
     * +-------------------------------+--------------------+-------------------+
     * + SAX внедрение StringBuilder   +    800-820MS       +       90-110MB    +
     * +-------------------------------+--------------------+-------------------+
     * + SAX замена Integer на Byte    +    625-670MS       +       34-42MB     +
     * --------------------------------+--------------------+-------------------+
     * + SAX c Voter, Byte             +                    +                   +
     * + замена у Voter Date на String +    430-472MS       +       27-33MB     +
     * --------------------------------+--------------------+-------------------+
     */


    public static void main(String[] args) throws Exception {
        String fileName = "res/data-18M.xml";
        long start = System.currentTimeMillis();

        parseSaxFile(fileName);
//        DOMHandler.parseFile(fileName);
//        DOMHandler.printDuplicatedVotersFromDOM();
        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("\nUsed memory: " + (double) usage / Math.pow(2, 20) + "MB\n" +
                "Used time: " + (System.currentTimeMillis() - start)+ "MS" + "\n");


    }
    private static void parseSaxFile(String fileName) throws Exception
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);
        handler.printDuplicatedVoters();
    }
}