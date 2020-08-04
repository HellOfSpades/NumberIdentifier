package hellofspades.all;

import hellofspades.all.neuralnetwork.NeuralNetwork;
import hellofspades.all.neuralnetwork.helpers.DataHelper;
import hellofspades.all.neuralnetwork.helpers.MatrixHelper;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class Main {

    public static void main(String[] args){
        //making the neural network
        int[] neuralNetworkArchitecture = {2,3,2,1};
        NeuralNetwork nn = new NeuralNetwork(neuralNetworkArchitecture);

        //make a bunch of random data values
        double[][] data = DataHelper.makeData(10, 2, 0, 5);
        //use a formula to make a bunch of Y values for the data
        double[][] Ydata = DataHelper.makeY(data,(X)->{
            double[] y = {X[0]*5+X[1]*3+3};
            return y;
        });

        RealMatrix X = MatrixUtils.createRealMatrix(data);
        RealMatrix Y = MatrixUtils.createRealMatrix(Ydata);
        //X = nn.compute(X);
        //MatrixHelper.printMatrix(X);
        //MatrixHelper.printMatrix(Y);
        nn.learn(X,Y);

    }
}
