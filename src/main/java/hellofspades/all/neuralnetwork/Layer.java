package hellofspades.all.neuralnetwork;

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
        return X;
    }

}
