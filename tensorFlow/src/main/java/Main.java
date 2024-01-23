import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

  public static void main(String[] args) throws IOException {

    // Cria dados para enviar no corpo da requisição
    Map<String, Object> data = new HashMap<>();
    data.put("score_credito", 300);
    data.put("pais", "França");
    data.put("sexo_biologico", "Homem");
    data.put("idade", 27);
    data.put("anos_de_cliente", 3);
    data.put("saldo", 56000);
    data.put("servicos_adquiridos", 1);
    data.put("tem_cartao_credito", 1);
    data.put("membro_ativo", 1);
    data.put("salario_estimado", 85270);

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

    // Fechando a conexão
    connection.disconnect();

  }
}