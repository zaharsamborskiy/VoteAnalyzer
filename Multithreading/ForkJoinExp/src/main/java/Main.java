import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main
{
    static Set<String> stringSet;
    private static String url = "https://lenta.ru/";
    private static final String fileRecord = "src/main/resources/fileSiteMap.txt";

    public static void main(String[] args)
    {
        Set<String> stringSet = new ForkJoinPool().invoke(new Nodes(url, url, new TreeSet<>()));
        stringSet.forEach(System.out::println);

    }


}
