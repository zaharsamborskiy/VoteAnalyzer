import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.RecursiveAction;


public class Nodes extends RecursiveAction
{
    private Node node;
    private static Set<String> allChildrens = new TreeSet<>();
    public Nodes(Node url)
    {
        this.node = url;
    }

    @Override
    protected void compute()
    {
        Set<Nodes> taskSet = new HashSet<>();
        try
        {
            Thread.sleep(250);
            Document doc = Jsoup.connect(node.getUrl()).ignoreHttpErrors(true).get();
            Elements href = doc.select("a[href]");
            for (Element e : href) {
                String page = e.absUrl("href");
                if (correctLink(page)) {
                    allChildrens.add(page);
                    node.addChildren(new Node(page));
                }
            }
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }

        for (Node pages : node.getChildren())
        {
         Nodes task = new Nodes(new Node(pages.getUrl()));
         task.fork();
         taskSet.add(task);
        }

        for (Nodes task : taskSet)
        {
            task.join();
        }
    }

    private boolean correctLink(String url)
    {
        return ((!url.isEmpty())
                && (url.startsWith(node.getUrl()))
                && (!url.contains("@"))
                && (!allChildrens.contains(url))
                && (!url.contains("#"))
                && (!url.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf))$)")));
    }
}
