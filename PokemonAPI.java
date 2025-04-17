import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class PokemonAPI {

    public static void displayPokemonInfo(String input) throws Exception {
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + input;
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        if (con.getResponseCode() != 200) {
            throw new Exception("Invalid response from API");
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            response.append(line);
        }

        in.close();
        con.disconnect();

        JSONObject json = new JSONObject(response.toString());

        int id = json.getInt("id");
        String name = json.getString("name");
        int height = json.getInt("height");
        int weight = json.getInt("weight");

        System.out.println("\n========== Pokémon Info ==========");
        System.out.println("Name     : " + capitalize(name));
        System.out.println("Dex No.  : " + id);
        System.out.println("Height   : " + (height / 10.0) + " m");
        System.out.println("Weight   : " + (weight / 10.0) + " kg");

        System.out.print("Type(s)  : ");
        JSONArray types = json.getJSONArray("types");
        for (int i = 0; i < types.length(); i++) {
            JSONObject typeObj = types.getJSONObject(i).getJSONObject("type");
            System.out.print(capitalize(typeObj.getString("name")));
            if (i < types.length() - 1) System.out.print(", ");
        }

        System.out.print("\nAbilities: ");
        JSONArray abilities = json.getJSONArray("abilities");
        for (int i = 0; i < abilities.length(); i++) {
            JSONObject abilityObj = abilities.getJSONObject(i).getJSONObject("ability");
            System.out.print(capitalize(abilityObj.getString("name")));
            if (i < abilities.length() - 1) System.out.print(", ");
        }

        System.out.println("\n==================================");
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
