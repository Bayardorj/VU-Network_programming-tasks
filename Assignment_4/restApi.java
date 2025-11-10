import java.io.*;
import java.net.URI;
import java.net.http.*;

public class restApi {
    public static void main(String[] args) {
        String getURI = "https://restcountries.com/v3.1/name/mongolia";
        String postURI = "https://jsonplaceholder.typicode.com/posts";
        String outputFile = "output.txt";

        try (BufferedWriter myWriter = new BufferedWriter(new FileWriter(outputFile))) {
            // GET request
            myWriter.write("GET request\n");
            int getStatus = sendGetRequest(getURI, myWriter);
            myWriter.write("\nGET status code: " + getStatus + "\n\n");

            // POST request
            myWriter.write("POST request\n");
            int postStatus = sendPostRequest(postURI, myWriter);
            myWriter.write("\nPOST status code: " + postStatus + "\n");
            
            System.out.println("Responses saved to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int sendGetRequest(String uriString, BufferedWriter myWriter) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uriString))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            myWriter.write("URI: " + uriString + "\n");
            myWriter.write("Response body:\n" + response.body() + "\n");

            return response.statusCode();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int sendPostRequest(String uriString, BufferedWriter myWriter) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String jsonInputString = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uriString))
                    .header("Content-Type", "application/json; utf-8")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            myWriter.write("URI: " + uriString + "\n");
            myWriter.write("Request Body:\n" + jsonInputString + "\n");
            myWriter.write("Response Body:\n" + response.body() + "\n");

            return response.statusCode();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
