import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

  public static void main(String[] args) throws IOException {

    // Cria dados para enviar no corpo da requisição
    Map<String, List<Object>> data = new HashMap<>();

    data.put("score_credito", Arrays.asList(850));
    data.put("pais", Arrays.asList("França"));
    data.put("sexo_biologico", Arrays.asList("Homem"));
    data.put("idade", Arrays.asList(27));
    data.put("anos_de_cliente", Arrays.asList(3));
    data.put("saldo", Arrays.asList(56000));
    data.put("servicos_adquiridos", Arrays.asList(1));
    data.put("tem_cartao_credito", Arrays.asList(1));
    data.put("membro_ativo", Arrays.asList(1));
    data.put("salario_estimado", Arrays.asList(85270.0));


    // Converte os dados para JSON
    String requestBody = new ObjectMapper().writeValueAsString(data);

    // URL para o endpoint /predict
    URL url = new URL("http://localhost:5000/predict");

    // Criação da conexão HTTP
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    // Configuração do método HTTP para POST
    connection.setRequestMethod("POST");

    // Habilitando envio de dados
    connection.setDoOutput(true);

    // Configurando cabeçalhos da requisição
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setRequestProperty("Content-Length", String.valueOf(requestBody.length()));

    // Enviando dados no corpo da requisição
    try (OutputStream os = connection.getOutputStream()) {
      os.write(requestBody.getBytes(StandardCharsets.UTF_8));
    }

    // Obtendo a resposta do servidor
    int responseCode = connection.getResponseCode();
    System.out.println("Código de resposta: " + responseCode);

    // Lê a resposta do Python
    InputStream inputStream = connection.getInputStream();
    String responsePython = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

    // Imprime o retorno do python
    System.out.println(responsePython);

    // Fechando a conexão
    connection.disconnect();

  }
}