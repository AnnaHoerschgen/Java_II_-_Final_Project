package util;

import model.Pokemon;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PokeAPI {

    public static Pokemon getPokemon(String name) {
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + name;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());
                String pokemonName = json.getString("name");
                int height = json.getInt("height");
                int weight = json.getInt("weight");

                JSONArray typesArray = json.getJSONArray("types");
                String type = typesArray.getJSONObject(0).getJSONObject("type").getString("name");

                return new Pokemon(pokemonName, type, height, weight);
            }
        } catch (Exception e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }

        return null;
    }
}
