package hellofspades.all.neuralnetwork;

import hellofspades.all.neuralnetwork.helpers.MatrixHelper;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class Layer {

    Neuron[] neurons;
    int inputNum;
    //these values are stored for the backpropogation part. this represents the input a, not the output
    RealMatrix a;

    Layer(int neuronsNum, int inputNum){
        neurons = new Neuron[neuronsNum];
        this.inputNum = inputNum;
        for(int i = 0;i<neuronsNum;i++){
            neurons[i] = new Neuron(inputNum);
        }
        System.out.println("this layer has "+neurons.length+" neurons");
        System.out.println("this layer has "+inputNum+" inputs");
    }

    RealMatrix compute(RealMatrix X){
        //the array is a transpose of the final matrix so the computing can be easier, and faster.
        double[][] outputdata = new double[neurons.length][X.getRowDimension()];
        //save the input for backpropogation
        this.a = X;

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
    //rows are neurons
    //coulumns are the inputs
    public RealMatrix getThetaMatrix(){
        RealMatrix allTheta = MatrixUtils.createRealMatrix(neurons.length,inputNum);
        for(int i = 0;i<neurons.length;i++){
            allTheta.setRow(i,neurons[i].theta.getSubMatrix(0,0,0,inputNum-1).getRow(0));
        }
        return  allTheta;
    }
    //returns the matrix of the latest a from all the neurons in the layer
    //rows are neurons
    //coulumns are the
    public RealMatrix getAMatrix(){

        return  a;
    }

    //update the thetas in all the neurons of this layer
    void updateNeuronThetas(){
        for (int i = 0;i<neurons.length;i++){
            neurons[i].updateThetas(a);
        }
    }
}
