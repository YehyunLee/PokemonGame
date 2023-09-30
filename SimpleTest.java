import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimpleTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://typewise-ai.p.rapidapi.com/completion/complete"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", "e47261d986mshb7c5eb352d858d3p19fcc5jsn5c82697dba57")
                .header("X-RapidAPI-Host", "typewise-ai.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("{" +
                        "\r\n    \"text\": \"Please complete this sente\",\r\n    \"correctTypoInPartialWord\"" +
                        ": false,\r\n    \"language\": \"en\"\r\n}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
