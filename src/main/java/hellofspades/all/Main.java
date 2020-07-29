package hellofspades.all;

import hellofspades.all.neuralnetwork.NeuralNetwork;
import hellofspades.all.neuralnetwork.helpers.DataHelper;
import hellofspades.all.neuralnetwork.helpers.MatrixHelper;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class Main {

    public static void main(String[] args){
        //making the neural network
        int[] neuralNetworkArchitecture = {1,1,1,1};
        NeuralNetwork nn = new NeuralNetwork(neuralNetworkArchitecture, (z)->{
            z = z.copy();
            for(int i = 0;i<z.getRowDimension();i++){
                z.setEntry(i,0,1/(1+Math.exp(-z.getEntry(i,0))));
            }

            return z;
        });

        double[][] data = DataHelper.makeData(10, 1, 1, 1);

        RealMatrix X = MatrixUtils.createRealMatrix(data);

    }
}
