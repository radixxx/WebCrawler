import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class WebCrawler implements Runnable  {

    private HashSet<String> links;
    private static final int MAX_DEPTH = 5;

    //Thread variebles;
    private Thread t;


    public WebCrawler() {
        links = new HashSet<String>();
    }

    public void getPageLinks(String URL, int depth) {
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
            System.out.println("Глубина поиска Depth >>  " + depth + " [" + URL + "]");
            try {
                links.add(URL);

                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");

                depth++;
                for (Element page : linksOnPage) {
                    int finalDepth = depth;
                    new Thread(() -> getPageLinks(page.attr("abs:href"), finalDepth)).start();
                }
                //Error werification + messege
            } catch (IOException e) {
               // System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }


//method for runnable multhitred crawler
    @Override
    public void run() {

        getPageLinks("https://www.youtube.com/ru/",0);


    }
}
