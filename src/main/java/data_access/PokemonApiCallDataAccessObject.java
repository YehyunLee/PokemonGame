package data_access;

import entity.Pokemon;
import use_case.PokemonUseCase;

import org.json.JSONArray;
import use_case.PokemonUseCaseInterface;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * The PokemonApiCallDataAccessObject class is responsible for fetching Pokemon data from an external API.
 * It uses the PokemonUseCase to create Pokemon objects from the fetched data.
 *
 * @author Tyseer Toufiq
 */
public class PokemonApiCallDataAccessObject implements PokemonDataGateway {
    private final PokemonUseCase pokemonUseCase;

    /**
     * Constructor to initialize the PokemonApiCallDataAccessObject with a PokemonUseCase instance.
     *
     * @param pokemonUseCase The PokemonUseCase instance for data extraction and transformation.
     */
    public PokemonApiCallDataAccessObject(PokemonUseCaseInterface pokemonUseCase) {
        this.pokemonUseCase = new PokemonUseCase();
    }

    /**
     * Fetches Pokemon data from an external API by name and returns a Pokemon object.
     *
     * @param pokemonName The name of the Pokemon to fetch.
     * @return A Pokemon object containing the fetched data.
     */
    public Pokemon fetchPokemonData(String pokemonName) {
        // Make the API request and retrieve the Pokémon data as a JSONArray
        JSONArray responseData = null;
        try {
            responseData = makeHttpRequest(pokemonName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Use the PokemonUseCase instance to extract Pokemon data
        Map<String, Object> extractedData = pokemonUseCase.extractFields(responseData);

        // Create a Pokemon object using the extracted data
        return pokemonUseCase.createPokemon(extractedData);
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

        // Create a URL object
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
        } else {
            // Close the connection
            connection.disconnect();

            // Throw an exception or return an error message based on your needs
            throw new IOException("HTTP request failed with response code: " + responseCode);
        }

        // Return the response data as a JSONArray
        return responseData;
    }
}
