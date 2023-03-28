import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar os top 250 filmes
        String url =  "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        // Pegar só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> ListaDeFilmes = parser.parse(body);

        // Exibir e manupular os dados 
        for (Map<String,String> filme : ListaDeFilmes) {
            System.out.println("\u001b[1m Titulo: \u001b[m" + filme.get("title"));
            System.out.println("\u001b[1m Poster: \u001b[m" + filme.get("image"));
            System.out.println("\u001b[37;1m \u001b[45;1mClassificação: " + filme.get("imDbRating") + "\u001b[m");
            
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int nestrelas = (int) classificacao;
            for (int n = 0; n < nestrelas; n++) {
                if (nestrelas >= 4) {
                    System.out.print("⭐");
                } else {    
                    System.out.print("👎");                    
                }                
            }

            System.out.println();
        }
    }
}
