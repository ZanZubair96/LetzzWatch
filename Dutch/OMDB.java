package PlayWright;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

    public class OMDB {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a language: ");
            String language = scanner.nextLine();

            try {
                URL url = new URL("http://www.omdbapi.com/?apikey=http://www.omdbapi.com/?i=tt3896198&apikey=cd35b86c&type=movie&s=&plot=full&r=json&language=" + language);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int statusCode = connection.getResponseCode();
                if (statusCode == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    JSONObject jsonResponse = new JSONObject(response.toString());
                    JSONArray movieResults = jsonResponse.getJSONArray("Search");
                    for (int i = 0; i < movieResults.length(); i++) {
                        JSONObject movie = movieResults.getJSONObject(i);
                        String title = movie.getString("Title");
                        double rating = Double.parseDouble(movie.getString("imdbRating"));

                        if (rating > 7.5) { // Change this to any rating threshold you want
                            System.out.println(title + " (" + rating + ")");
                        }
                    }
                } else {
                    System.out.println("Error: " + statusCode);
                }
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

