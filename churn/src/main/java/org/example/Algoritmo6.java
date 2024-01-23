import org.jpmml.evaluator.Evaluator;
import org.jpmml.model.PMML;
import org.jpmml.model.tree.PMMLTreeModel;

public class PMMLTreeClassifier {

  public static void main(String[] args) throws Exception {

    // Load the PMML model
    File pmmlFile = new File("model.pmml");
    PMML pmml = PMMLUtil.unmarshal(pmmlFile);

    // Get the tree model
    PMMLTreeModel treeModel = pmml.getTreeModel();

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