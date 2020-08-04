package hellofspades.all.neuralnetwork;

import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class Layer {

    Neuron[] neurons;
    int inputNum;

    Layer(int neuronsNum, int inputNum){
        neurons = new Neuron[neuronsNum];
        this.inputNum = inputNum;
        for(int i = 0;i<neuronsNum;i++){
            neurons[i] = new Neuron(inputNum);
        }
    }

    RealMatrix compute(RealMatrix X){
        //the array is a transpose of the final matrix so the computing can be easier, and faster.
        double[][] outputdata = new double[neurons.length][X.getRowDimension()];

        for(int i = 0;i<neurons.length;i++){
            outputdata[i] = neurons[i].compute(X).getColumn(0);
        }
        return MatrixUtils.createRealMatrix(outputdata).transpose();
    }

    public void setD(RealMatrix d){
        for(int i = 0;i<d.getColumnDimension();i++){
            neurons[i].d = d.getColumnMatrix(i);
        }
    }


    //returns the matrix of theta from all the neurons in the layer
    //this does not include the theta for the +1 input
    //rows are neurons
    //coulumns are the inputs
    public RealMatrix getThetaMatrix(){
        RealMatrix allTheta = MatrixUtils.createRealMatrix(neurons.length,inputNum-1);
        for(int i = 0;i<neurons.length;i++){
            allTheta.setRow(i,neurons[i].theta.getSubMatrix(0,0,1,inputNum-1).getRow(0));
        }
        return  allTheta;
    }
    //returns the matrix of the latest a from all the neurons in the layer
    //rows are neurons
    //coulumns are the
    public RealMatrix getAMatrix(){
        RealMatrix allA = MatrixUtils.createRealMatrix(neurons.length,neurons[0].a.getRowDimension());
        for(int i = 0;i<neurons.length;i++){
            allA.setRow(i,neurons[i].a.getColumn(0));
        }
        return  allA;
    }
}
