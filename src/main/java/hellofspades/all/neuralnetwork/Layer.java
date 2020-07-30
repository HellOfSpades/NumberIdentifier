package hellofspades.all.neuralnetwork;

import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class Layer {

    Neuron[] neurons;

    Layer(int neuronsNum, int inputNum, ActivationFunction activation){
        neurons = new Neuron[neuronsNum];
        for(int i = 0;i<neuronsNum;i++){
            neurons[i] = new Neuron(inputNum, activation);
        }
    }

    RealMatrix compute(RealMatrix X){
        //the array is an inverse of the final matrix so the computing can be easier.
        double[][] outputdata = new double[neurons.length][X.getRowDimension()];

        for(int i = 0;i<neurons.length;i++){
            outputdata[i] = neurons[i].compute(X).getColumn(0);
        }


        return MatrixUtils.createRealMatrix(outputdata).transpose();
    }

}
