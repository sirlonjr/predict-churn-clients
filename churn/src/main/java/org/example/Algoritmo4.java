package org.example;

import org.javaml.core.Dataset;
import org.javaml.core.Instance;
import org.javaml.core.converters.PMMLLoader;

public class JavaMLExample {

  public static void main(String[] args) throws Exception {

    // Load the PMML model
    File pmmlFile = new File("model.pmml");
    PMMLLoader loader = new PMMLLoader();
    Dataset dataset = loader.load(pmmlFile);

    // Prepare input data
    Instance instance = new Instance(new double[]{1.0, 2.0, 3.0});

    // Make predictions using the loaded model
    double prediction = dataset.predict(instance);

    // Process the model output
    System.out.println("Prediction: " + prediction);
  }
}
