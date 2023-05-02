import java.io.IOException;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;

public class GetMoviesName {

    HttpClient httpClient = HttpClientBuilder.create().build();
    String apikey = "a6679333";
    Scanner scan = new Scanner(System.in);

    public void fetchKoreanMovieNames() throws IOException {

        String urlToFetchKoreanMovies = "http://www.omdbapi.com/?apikey=" + apikey + "&s=korean&type=movie";

        HttpGet httpGetKoreanMovies = new HttpGet(urlToFetchKoreanMovies);
        HttpEntity entity = httpClient.execute(httpGetKoreanMovies).getEntity();
        String response = EntityUtils.toString(entity);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray koreanMoviesJSON = jsonObject.getJSONArray("Search");

        for (int i = 0; i < koreanMoviesJSON.length(); i++) {
            JSONObject movie = koreanMoviesJSON.getJSONObject(i);
            String getTitle = movie.getString("Title");
            String getYear = movie.getString("Year");
            String get_imdb_id = movie.getString("imdbID");
            String urlToGetIMDBRatings = "http://www.omdbapi.com/?apikey=" + apikey + "&i=" + get_imdb_id;
            HttpGet httpGetIMDBRatings = new HttpGet(urlToGetIMDBRatings);
            HttpEntity entity2 = httpClient.execute(httpGetIMDBRatings).getEntity();
            String response2 = EntityUtils.toString(entity2);
            JSONObject jsonObjectIMDB = new JSONObject(response2);
            String getRatingIMDB = jsonObjectIMDB.getString("imdbRating");
            System.out.println("Movie Title: " + getTitle + " Released Year: " + getYear + " IMDB Rating: " + getRatingIMDB);

        }
    }

    public void findMoviesByLanguage() throws IOException {
        System.out.println("Enter the language: ");
        String getLanguage = scan.nextLine();
        String urlToFetchKoreanMovies = "http://www.omdbapi.com/?apikey=" + apikey + "&s=" + getLanguage + "&type=movie";

        HttpGet httpGetKoreanMovies = new HttpGet(urlToFetchKoreanMovies);
        HttpEntity entity = httpClient.execute(httpGetKoreanMovies).getEntity();
        String response = EntityUtils.toString(entity);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray koreanMoviesJSON = jsonObject.getJSONArray("Search");

        for (int i = 0; i < koreanMoviesJSON.length(); i++) {
            JSONObject movie = koreanMoviesJSON.getJSONObject(i);
            String getTitle = movie.getString("Title");
            String getYear = movie.getString("Year");
            String get_imdb_id = movie.getString("imdbID");
            String urlToGetIMDBRatings = "http://www.omdbapi.com/?apikey=" + apikey + "&i=" + get_imdb_id;
            HttpGet httpGetIMDBRatings = new HttpGet(urlToGetIMDBRatings);
            HttpEntity entity2 = httpClient.execute(httpGetIMDBRatings).getEntity();
            String response2 = EntityUtils.toString(entity2);
            JSONObject jsonObjectIMDB = new JSONObject(response2);
            String getRatingIMDB = jsonObjectIMDB.getString("imdbRating");
            System.out.println("Movie Title: " + getTitle + " Released Year: " + getYear + " IMDB Rating: " + getRatingIMDB);
        }
    }

    public void fetchMovieDetails() throws IOException {
        String url = "http://www.omdbapi.com/?apikey=a6679333&s=korean&type=movie";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject jsonObject = new JSONObject(response.toString());
        JSONArray movies = jsonObject.getJSONArray("Search");
        for (int i = 0; i < movies.length(); i++) {
            JSONObject movie = movies.getJSONObject(i);
            String title = movie.getString("Title");
            String year = movie.getString("Year");
            String imdb_id = movie.getString("imdbID");
            String url2 = "http://www.omdbapi.com/?apikey=a6679333&i=" + imdb_id;
            URL obj2 = new URL(url2);
            HttpURLConnection con2 = (HttpURLConnection) obj2.openConnection();
            con2.setRequestMethod("GET");
            BufferedReader in2 = new BufferedReader(
                    new InputStreamReader(con2.getInputStream()));
            String inputLine2;
            StringBuffer response2 = new StringBuffer();

            while ((inputLine2 = in2.readLine()) != null) {
                response2.append(inputLine2);
            }
            in2.close();
            System.out.println("Movie Title: " + title + " Released Year: " + year);
        }

    }

    public static void main(String[] args) throws IOException {
        System.out.println("Holla!");
        GetMoviesName getMoviesName = new GetMoviesName();
//        getMoviesName.fetchMovieDetails();
//        getMoviesName.fetchKoreanMovieNames();
        getMoviesName.findMoviesByLanguage();
    }
}
