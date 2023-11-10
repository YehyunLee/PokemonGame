package data_access;

import org.json.JSONArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The {@code PokemonApiCallDataAccessObject} class provides methods for making HTTP requests to an external API
 * to fetch Pokemon data in the form of a JSONArray.
 */
public class PokemonApiCallDataAccessObject implements PokemonApiCallInterface {

    public JSONArray fetchRawPokemonData(String pokemonName) {
        // Initialize a variable to store the API response data
        JSONArray responseData = null;
        try {
            // Make an HTTP request to fetch Pokémon data and store the response
            responseData = makeHttpRequest(pokemonName);
        } catch (IOException e) {
            // return empty array if error occurs
            return new JSONArray();
        }

        // Check if the response data is empty, indicating that the Pokemon was not found
        if (responseData == null || responseData.isEmpty()) {
            // Print a message indicating that the requested Pokemon does not exist
            System.out.println("[API DATA EMPTY] Pokemon with name '" + pokemonName + "' not found.");
        }

        // Return the fetched Pokémon data as a JSONArray (may be null if not found)
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
        // Construct the URL with the provided Pokémon name
        String apiUrl = "https://ex.traction.one/pokedex/pokemon/" + pokemonName;

        // Create a URL object representing the API endpoint
        URL url = new URL(apiUrl);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Get the response code from the server
        int responseCode = connection.getResponseCode();

        // Initialize a JSONArray to store the response data
        JSONArray responseData = null;

        // Check if the response code indicates a successful request (e.g., 200 OK)
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Create a BufferedReader to read the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            // Read the response line by line and append it to the response StringBuilder
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Close the reader
            reader.close();

            // Close the connection
            connection.disconnect();

            // Parse the response data as a JSONArray
            responseData = new JSONArray(response.toString());
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            // Close the connection
            connection.disconnect();

            // Print a message indicating that the requested Pokemon does not exist
            System.out.println("[API ISSUE] Pokemon with name '" + pokemonName + "' not found.");
        } else {
            // Close the connection
            connection.disconnect();

            // Throw an exception or return an error message based on your needs
            throw new IOException("HTTP request failed with response code: " + responseCode);
        }

        // Return the response data as a JSONArray (may be empty if not found)
        return responseData;
    }
}
