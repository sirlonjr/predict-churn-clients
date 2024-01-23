package org.example;
import org.jpmml.pmml.PMML;
import org.jpmml.pmml.evaluator.Evaluator;

public class PMML4JExample {

  public static void main(String[] args) throws Exception {

    // Load the PMML model
    File pmmlFile = new File("model.pmml");
    PMML pmml = PMMLUtil.unmarshal(pmmlFile);

    // Create an evaluator
    Evaluator evaluator = new Evaluator(pmml);

    // Prepare input data
    double[] input = {1.0, 2.0, 3.0}; // Sample input data

    // Make predictions using the loaded model
    Object output = evaluator.evaluate(input);

    // Process the model output
    if (output instanceof double[]) {
      double[] prediction = (double[]) output;
      System.out.println("Prediction: " + prediction[0]);
    } else {
      System.out.println("Unexpected output type");
    }
  }
}