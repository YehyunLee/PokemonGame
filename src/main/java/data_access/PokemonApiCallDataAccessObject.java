package data_access;

import org.json.JSONArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code PokemonApiCallDataAccessObject} class provides methods for making HTTP requests to an external API
 * to fetch Pokemon data in the form of a JSONArray.
 */
public class PokemonApiCallDataAccessObject implements PokemonApiCallInterface {

    /**
     * Fetches Pokemon data from an external API.
     *
     * @param pokemonName The name or number of the Pokemon to fetch.
     * @return A JSONArray containing the response data from the API.
     */
    public JSONArray fetchRawPokemonData(String pokemonName) {
        JSONArray responseData = new JSONArray();
        try {
            if (pokemonName.matches("[0-9]+")) {
                // Call savedMakeHttpRequest if the input is a numeric Pokemon ID
                responseData = savedMakeHttpRequest(pokemonName);
            } else {
                // Call makeHttpRequest to fetch Pokemon data from the API
                responseData = makeHttpRequest(pokemonName);
            }
        } catch (IOException e) {
            // Return an empty array if an error occurs during the request
            return new JSONArray();
        }

        if (responseData == null || responseData.isEmpty()) {
            // Print a message if the requested Pokemon does not exist
            System.out.println("[API DATA EMPTY] Pokemon with name '" + pokemonName + "' not found.");
            responseData = new JSONArray();
        }

        return responseData;
    }

    /**
     * Makes an HTTP GET request to an external API to fetch Pokemon data.
     *
     * @param pokemonName The name of the Pokemon to fetch.
     * @return A JSONArray containing the response data from the API.
     * @throws IOException If an IO error occurs during the HTTP request.
     */
    public static JSONArray makeHttpRequest(String pokemonName) throws IOException {
        String apiUrl = "https://ex.traction.one/pokedex/pokemon/" + pokemonName;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = -1;
        while (responseCode != HttpURLConnection.HTTP_OK) {
            responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Received non-OK response code: " + responseCode);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
            }
        }

        JSONArray responseData = null;

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            responseData = new JSONArray(response.toString());
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            connection.disconnect();
            System.out.println("[API ISSUE] Pokemon with name '" + pokemonName + "' not found.");
        } else {
            connection.disconnect();
            throw new IOException("HTTP request failed with response code: " + responseCode);
        }

        return responseData;
    }

    /**
     * Fetches Pokemon data from a saved data file.
     *
     * @param getPokemonNumber The numeric ID of the Pokemon to fetch.
     * @return A JSONArray containing the saved response data for the Pokemon.
     * @throws IOException If an error occurs while loading data from the file.
     */
    public static JSONArray savedMakeHttpRequest(String getPokemonNumber) throws IOException {
        Map<String, String> loadedPokemonData = loadPokemonDataFromFile("pokemon_data.pickle");

        for (Map.Entry<String, String> entry : loadedPokemonData.entrySet()) {
            String pokemonNumber = entry.getKey();
            String responseDataString = entry.getValue();

            JSONArray responseData = new JSONArray(responseDataString);

            if (pokemonNumber.equals(getPokemonNumber)) {
                return responseData;
            }
        }
        return null;
    }

    private static void savePokemonDataToFile(Map<String, String> pokemonData, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(pokemonData);
            System.out.println("Pokemon data saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> loadPokemonDataFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object loadedObject = ois.readObject();

            if (loadedObject instanceof Map) {
                return (Map<String, String>) loadedObject;
            } else {
                System.err.println("Unexpected type of object in the pickle file.");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new HashMap<>();
    }
}
