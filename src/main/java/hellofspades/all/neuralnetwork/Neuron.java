package hellofspades.all.neuralnetwork;

import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import hellofspades.all.neuralnetwork.helpers.Constants;
import hellofspades.all.neuralnetwork.helpers.MatrixHelper;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class Neuron {

    //theta is going to have 1 row and the amount of coulumns coresponding to the previus layers neuron count
    RealMatrix theta;
    //these values are stored for the backpropogation part.
    RealMatrix z;
    RealMatrix a;
    //extra values used when learning
    RealMatrix d;

    Neuron(int inputnum){
        theta = MatrixUtils.createRealMatrix(1, inputnum);
        MatrixHelper.setRandom(theta, Constants.RANDOMTHETAMAX);
    }

    //returns the prediction of the neuron and saves the a and z value for later use in backpropogation
    RealMatrix compute(RealMatrix X){
        z = X.multiply(theta.transpose());
        a = activationFunction(z);
        return  a;
    }

    //change this if you want to have a different activation function
    //but if you do also change the learn method so that it uses the right derivative
    public RealMatrix activationFunction(RealMatrix z){
        z = z.copy();
        for(int i = 0;i<z.getRowDimension();i++){
            z.setEntry(i,0,1/(1+Math.exp(-z.getEntry(i,0))));
        }

        return z;
    }

}
