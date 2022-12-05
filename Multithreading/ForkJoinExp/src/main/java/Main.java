import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;


public class Main
{
    //https://lenta.ru/
    //https://skillbox.ru/
    private static String url = "https://skillbox.ru/";
    private static final String fileRecord = "src/main/resources/fileSiteMap.txt";

    public static void main(String[] args) throws IOException {
        Node nodeParent = new Node(url);
        new ForkJoinPool().invoke(new Nodes(nodeParent));

        FileOutputStream stream = new FileOutputStream(fileRecord);
        String result = createSitemapString(nodeParent, nodeParent.getLevel());
        stream.write(result.getBytes());
        stream.flush();
        stream.close();
    }

    public static String createSitemapString(Node node, int level) {
        String tabs = String.join("", Collections.nCopies(level, "\t"));
        StringBuilder result = new StringBuilder(tabs + node.getUrl());
        node.getChildren().forEach(child -> {
            result.append("\n").append(createSitemapString(child, level + 1));
        });
        return result.toString();
    }
}
