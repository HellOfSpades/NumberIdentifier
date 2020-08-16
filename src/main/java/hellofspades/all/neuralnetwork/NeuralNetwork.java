package hellofspades.all.neuralnetwork;

import hellofspades.all.neuralnetwork.helpers.Constants;
import hellofspades.all.neuralnetwork.helpers.MatrixHelper;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class NeuralNetwork {

    Layer[] layers;

    public NeuralNetwork(int[] architecture) {

        //the first layer in the architecture represents the amount of inputs so its not technicly a layer
        //and is just their for the inputs
        layers = new Layer[architecture.length - 1];
        System.out.println("their are "+layers.length+" layers");
        for (int i = 1; i < architecture.length; i++) {
            layers[i - 1] = new Layer(architecture[i], architecture[i - 1] + 1);
            System.out.println("the thetas are");
            MatrixHelper.printMatrix(layers[i-1].getThetaMatrix());
        }
    }

    //returns the prediction of the neural network
    public RealMatrix compute(RealMatrix X) {
        for (int i = 0; i < layers.length; i++) {
            X = MatrixHelper.adOnes(X);
            X = layers[i].compute(X);
        }
        return X;

    }

    //learns, this counts as a single loop
    //it also returns the cost of the the neural network at this moment
    public double learn(RealMatrix X, RealMatrix Y) {

        //make a prediction based on the current weights
        RealMatrix a = compute(X);

        //compute the cost of the run
        double thetasum = 0;
        for(int i = 0;i<layers.length;i++){
            for(int n = 0;n<layers[i].neurons.length;n++){
                thetasum+=MatrixHelper.sum(MatrixHelper.power(layers[i].neurons[n].theta,2));
            }
        }
        double J;
        J = (Y.transpose().multiply(MatrixHelper.logEachElement(a))).getEntry(0,0);
        J = J+(Y.scalarMultiply(-1).scalarAdd(1).transpose().multiply
                (MatrixHelper.logEachElement(a.scalarMultiply(-1).scalarAdd(1)))).getEntry(0,0);
        J = J*-1/X.getRowDimension();
        J = J +(thetasum*Constants.LAMBDA/(2*X.getRowDimension()));

        //find d for the final layer
        RealMatrix d = a.subtract(Y);
        //backpropogation
        //setting d to each neuron for this run
        layers[layers.length - 1].setD(d);
        for (int i = layers.length - 2; i >= 0; i--) {
            a = layers[i+1].getAMatrix();
            RealMatrix gDerivative = MatrixHelper.moltiplyEachElement(a, a.scalarMultiply(-1).scalarAdd(1));
            d = d.multiply(layers[i + 1].getThetaMatrix());
            d = MatrixHelper.moltiplyEachElement(d, gDerivative);
            layers[i].setD(d.getSubMatrix(0,d.getRowDimension()-1,1,d.getColumnDimension()-1));
        }
        //update thetas
        for (int i = 0; i < layers.length; i++) {
            layers[i].updateNeuronThetas();
        }
        //return the cost
        return J;
    }
}
