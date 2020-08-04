package hellofspades.all.neuralnetwork;

import hellofspades.all.neuralnetwork.helpers.MatrixHelper;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class NeuralNetwork {

    Layer[] layers;

    public NeuralNetwork(int[] architecture){

        //the first layer in the architecture represents the amount of inputs so its not technicly a layer
        //and is just their for the inputs
        layers = new Layer[architecture.length-1];
        for(int i = 1;i<architecture.length;i++){
            layers[i-1] = new Layer(architecture[i],architecture[i-1]+1);
        }

    }

    //returns the prediction of the neural network
    public RealMatrix compute(RealMatrix X){
        for(int i = 0;i<layers.length;i++){
            X = MatrixHelper.adOnes(X);
            X = layers[i].compute(X);
        }
        return X;

    }

    //learns, this counts as a single loop
    //it also returns the cost of the the neural network at this moment
    public double learn(RealMatrix X, RealMatrix Y){

        //make a prediction based on the current weights
        RealMatrix a = compute(X);

        //find d for the final layer
        RealMatrix d = a.subtract(Y);
        MatrixHelper.printMatrix(d);
        //backpropogation
        //and setting d to each neuron for this run
        layers[0].setD(d);
        for(int i = layers.length-2;i>=0;i--){
            System.out.println(i);
            a = layers[i].getAMatrix();
            RealMatrix gDerivative = MatrixHelper.moltiplyEachElement(a,a.scalarMultiply(-1).scalarAdd(1));
            d = d.multiply(layers[i+1].getThetaMatrix());
            d = MatrixHelper.moltiplyEachElement(d,gDerivative.transpose());
            MatrixHelper.printMatrix(d);
            layers[i].setD(d);
        }
        //update thetas
        for(int i = 0;i<layers.length;i++){

        }

        //return the cost
        return 0;
    }
}
