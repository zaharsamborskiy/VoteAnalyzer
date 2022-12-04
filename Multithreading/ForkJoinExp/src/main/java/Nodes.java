import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.concurrent.RecursiveTask;


public class Nodes extends RecursiveTask<Set<String>>
{
    private String node;
    private Set<String> allUrls;
    private String rootUrl;

    public Nodes(String node, String rootUrl, Set<String> allUrls) {
        this.node = node;
        this.rootUrl = rootUrl;
        this.allUrls = allUrls;
        allUrls.add(rootUrl);
    }

    @Override
    protected Set<String> compute()
    {
        List<Nodes> taskList = new ArrayList<>();
        try
        {
            Thread.sleep(250);
            Connection connection = Jsoup.connect(rootUrl).ignoreContentType(true);
            Document document = connection.get();
            Elements aBody = document.select("body").select("a");
            for (Element e : aBody)
            {
                String child = e.absUrl("href");
                if (check(removeRootInTask(rootUrl,child)) & !allUrls.contains((removeRootInTask(rootUrl, child))))
                {
                    allUrls.add(removeRootInTask(rootUrl, child));
                    Nodes task = new Nodes(rootUrl, child, allUrls);
                    task.fork();
                    taskList.add(task);
                }
                for (Nodes nodes : taskList)
                {
                    allUrls.addAll(nodes.join());
                }
            }

        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException(e);
        }

        return allUrls;
    }

    private boolean check(String url)
    {
        return (!url.isEmpty()
                && url.startsWith(node)
                && !url.contains("png")
                && !url.contains("jpg")
                && !url.contains("#"))
                && !url.contains("@")
                && !url.contains("pdf");
    }

    private String removeRootInTask(String rootUrl, String childUrl)
    {
        if (childUrl.contains(rootUrl))
        {
            childUrl.replaceAll(rootUrl, "");
        }
        return childUrl;
    }

}
