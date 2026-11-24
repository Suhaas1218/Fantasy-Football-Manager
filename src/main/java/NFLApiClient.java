import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NFLApiClient {
    private static final String BASE_URL = "https://api.sleeper.app/v1";

    private final HttpClient client;

    public NFLApiClient() {
        client = HttpClient.newHttpClient();
    }

    public String getNFLState() throws IOException, InterruptedException {
        return sendRequest(BASE_URL + "/state/nfl");
    }

    public String getAllPlayers() throws IOException, InterruptedException {
        return sendRequest(BASE_URL + "/players/nfl");
    }

    private String sendRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException("API request failed: " + response.statusCode());
        }

        return response.body();
    }
}
