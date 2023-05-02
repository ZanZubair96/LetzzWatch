import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class YtsMovieLinkScraper {

    public static void main(String[] args) throws IOException {
        String movieName = "Lazzaro Felice"; // name of the movie to search
        String url = " https://yts.mx/" + movieName.replace(" ", "+");

        // connect to the YTS search results page
        Document doc = Jsoup.connect(url).get();

        // select the first movie search result
        Elements movieResult = doc.select(".browse-movie-wrap");

        // get the movie page URL and connect to it
        String movieUrl = movieResult.select("a").first().attr("href");
        doc = Jsoup.connect(movieUrl).get();

        // select the download button and get the download link
        Elements downloadButton = doc.select(".download-torrent > a.btn");
        String downloadLink = downloadButton.first().attr("href");

        System.out.println("Download link for " + movieName + ": " + downloadLink);
    }

}