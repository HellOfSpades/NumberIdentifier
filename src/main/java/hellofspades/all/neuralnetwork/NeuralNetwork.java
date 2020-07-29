package hellofspades.all.neuralnetwork;

import hellofspades.all.neuralnetwork.helpers.MatrixHelper;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class NeuralNetwork {

    Layer[] layers;

    public NeuralNetwork(int[] architecture, ActivationFunction activation){

        //the first layer in the architecture represents the amount of inputs so its not technicly a layer
        //and is just their for the inputs
        layers = new Layer[architecture.length-1];
        for(int i = 1;i<architecture.length;i++){
            layers[i-1] = new Layer(architecture[i],architecture[i-1]+1,activation);
        }

    }


    public RealMatrix compute(RealMatrix X){
        for(int i = 0;i<layers.length;i++){
            MatrixHelper.printMatrix(X);
            X = MatrixHelper.adOnes(X);
            MatrixHelper.printMatrix(X);
            //X = layers[i].compute(X);
        }
        return X;

    }
}
