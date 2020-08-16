package hellofspades.all.neuralnetwork;

import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import hellofspades.all.neuralnetwork.helpers.Constants;
import hellofspades.all.neuralnetwork.helpers.MatrixHelper;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class Neuron {

    //theta is going to have 1 row and the amount of coulumns coresponding to the previus layers neuron count
    RealMatrix theta;

    //extra values used when learning
    RealMatrix d;

    Neuron(int inputNum){
        System.out.println("the input received is "+inputNum);
        theta = MatrixUtils.createRealMatrix(1, inputNum);
        MatrixHelper.setRandom(theta, Constants.RANDOMTHETAMAX);
    }

    //returns the prediction of the neuron
    RealMatrix compute(RealMatrix X){
        return  activationFunction(X.multiply(theta.transpose()));
    }

    //change this if you want to have a different activation function
    //but if you do also change the learn method so that it uses the right derivative
    RealMatrix activationFunction(RealMatrix z){
        z = z.copy();
        for(int i = 0;i<z.getRowDimension();i++){
            z.setEntry(i,0,1/(1+Math.exp(-z.getEntry(i,0))));
        }

        return z;
    }

    //update thetas based on d and a
    //with speed A
    //with regularization LAMBDA
    void updateThetas(RealMatrix a){
        double D;
        /*
        System.out.println("d for this neuron is:");
        MatrixHelper.printMatrix(d);
        System.out.println("a used is:");
        MatrixHelper.printMatrix(a);
        System.out.println("theta for this neuron is:");
        MatrixHelper.printMatrix(theta);
        System.out.println("next");*/
        for (int i = 0;i<theta.getColumnDimension();i++){
            D = d.transpose().multiply(a).getEntry(0,i);
            D = D/theta.getColumnDimension();
            D = D+(Constants.LAMBDA*theta.getEntry(0,i));
            theta.setEntry(0,i,theta.getEntry(0,i)-Constants.A*(D));
        }
    }

}
