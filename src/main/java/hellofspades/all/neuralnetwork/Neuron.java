package hellofspades.all.neuralnetwork;

import hellofspades.all.neuralnetwork.helpers.Constants;
import hellofspades.all.neuralnetwork.helpers.MatrixHelper;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class Neuron {

    //theta is going to have 1 row and the amount of coulumns coresponding to the previus layers input
    RealMatrix theta;
    //keeps the last output stored so that we can use it during backpropogation
    RealMatrix lastoutput;
    //the activation function used
    ActivationFunction activation;

    Neuron(int inputnum, ActivationFunction activation){
        theta = MatrixUtils.createRealMatrix(1, inputnum);
        MatrixHelper.setRandom(theta, Constants.RANDOMTHETAMAX);
        this.activation = activation;
        MatrixHelper.printMatrix(theta);
    }

    RealMatrix compute(RealMatrix X){

        lastoutput = activation.function(X.multiply(theta.transpose()));
        return  lastoutput.copy();
    }


}
