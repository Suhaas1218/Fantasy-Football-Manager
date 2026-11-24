import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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

    public List<Player> getActivePlayers()
            throws IOException, InterruptedException {

        String response = getAllPlayers();
        JsonObject playersJson = JsonParser.parseString(response)
                .getAsJsonObject();

        List<Player> players = new ArrayList<>();

        for (var entry : playersJson.entrySet()) {
            JsonObject playerData = entry.getValue().getAsJsonObject();

            if (!playerData.has("active")
                    || !playerData.get("active").getAsBoolean()) {
                continue;
            }

            String firstName = getString(playerData, "first_name");
            String lastName = getString(playerData, "last_name");
            String position = getString(playerData, "position");
            String team = getString(playerData, "team");

            if (firstName == null || lastName == null || position == null) {
                continue;
            }

            if (!List.of("QB", "RB", "WR", "TE").contains(position)) {
                continue;
            }

            players.add(new Player(
                    entry.getKey(), firstName, lastName, position, team
            ));
        }

        return players;
    }

    private String getString(JsonObject object, String key) {
        JsonElement value = object.get(key);

        if (value == null || value.isJsonNull()) {
            return null;
        }

        return value.getAsString();
    }

    private String sendRequest(String url)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException(
                    "API request failed: " + response.statusCode()
            );
        }

        return response.body();
    }
}
