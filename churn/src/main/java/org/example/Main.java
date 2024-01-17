import java.io.FileInputStream;
import org.jpmml.evaluator.*;
import org.jpmml.model.*;

public class Main {

  public static void main(String[] args) throws Exception {
    // Carregue o modelo PMML
    InputStream is = new FileInputStream("arvore_modelo.pmml");
    PMML pmml = PMMLUtil.unmarshal(is);

    // Crie um avaliador de modelo
    ModelEvaluator<?> modelEvaluator = ModelEvaluatorFactory.newInstance().newModelEvaluator(pmml);

    // Prepare os dados de novos clientes
    Map<FieldName, ?> data = new LinkedHashMap<>();
    data.put("sexo_biologico", "masculino");
    data.put("pais", "Brasil");
    data.put("tem_cartao_credito", true);
    data.put("membro_ativo", true);

    // Faça a avaliação (predição)
    Map<FieldName, ?> result = modelEvaluator.evaluate(data);

    // Acesse o resultado da predição
    Object churn = result.get(FieldName.create("churn"));
    System.out.println(churn);
  }
}