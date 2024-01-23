package org.example;

import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.sklearn.SklearnPipelineEvaluator;
import org.jpmml.evaluator.sklearn.converter.SklearnPipelineConverter;

import java.io.File;

public class Algorit2 {

  public static void main(String[] args) throws Exception {
    // Load the PMML model
    File pmmlFile = new File("model.pmml");
    Evaluator evaluator = new SklearnPipelineEvaluator(
        SklearnPipelineConverter.parse(pmmlFile));

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
