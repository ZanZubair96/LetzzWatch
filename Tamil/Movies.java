import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Movies {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a movie name: ");
        String movieName = scanner.nextLine();

        // Construct the API request URL
        String apiUrl = "http://www.omdbapi.com/?t=" + movieName + "&apikey=9135c740";

        // Make a GET request to the API and retrieve the response
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Parse the JSON response to get the IMDb rating
        String jsonString = response.toString();
        String imdbRating = jsonString.substring(jsonString.indexOf("\"imdbRating\":\"") + 14,
                jsonString.indexOf("\",\"imdbVotes\""));

        System.out.println("The IMDb rating of " + movieName + " is " + imdbRating);

        scanner.close();
    }
}