package hellofspades.all;

import hellofspades.all.neuralnetwork.NeuralNetwork;
import hellofspades.all.neuralnetwork.helpers.DataHelper;
import hellofspades.all.neuralnetwork.helpers.MatrixHelper;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class Main {

    public static void main(String[] args){
        //making the neural network
        int[] neuralNetworkArchitecture = {2,2,1};
        NeuralNetwork nn = new NeuralNetwork(neuralNetworkArchitecture);

        //make a bunch of random data values
        double[][] data = DataHelper.makeData(10000, 2, 0, 5);
        //use a formula to make a bunch of Y values for the data
        double[][] Ydata = DataHelper.makeY(data,(X)->{
            double[] y = {(X[1]<=X[0]*5+3)?0:1};
            return y;
        });
        RealMatrix X = MatrixUtils.createRealMatrix(data);
        RealMatrix Y = MatrixUtils.createRealMatrix(Ydata);
        System.out.println("The X data is:");
        MatrixHelper.printMatrix(X);
        System.out.println("The Y data is:");
        MatrixHelper.printMatrix(Y);


        for(int i = 0;i<1000000000;i++){
            System.out.println("cost at run "+i+" is :"+nn.learn(X,Y));
        }


    }
}
