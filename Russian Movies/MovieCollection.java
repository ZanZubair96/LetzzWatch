import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MovieCollection {
    public static void main(String[] args) throws IOException {
        String collectionTitle = "Russian Movies";
        String searchUrl = "https://www.imdb.com/find?q=" + collectionTitle.replace(' ', '+') + "&s=tt&ttype=ft&ref_=fn_ft";

        Document doc = Jsoup.connect(searchUrl).get();
        Elements results = doc.select("td.result_text a");

        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            for (Element result : results) {
                String title = result.text();
                String url = "https://www.imdb.com" + result.attr("href");

                System.out.println(title + " - " + url);
            }
        }
    }
}