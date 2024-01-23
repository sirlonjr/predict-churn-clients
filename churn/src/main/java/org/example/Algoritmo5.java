package org.example;

import org.pmml4s.model.PMML;
import org.pmml4s.model.PMMLModel;

public class PMML4SExample {

  public static void main(String[] args) throws Exception {

    // Load the PMML model
    File pmmlFile = new File("model.pmml");
    PMMLModel pmmlModel = PMML4S.readModel(pmmlFile);

    // Create an evaluator
    Evaluator evaluator = pmmlModel.getEvaluator();

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