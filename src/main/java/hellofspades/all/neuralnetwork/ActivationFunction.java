package hellofspades.all.neuralnetwork;

import org.apache.commons.math.linear.RealMatrix;

public interface ActivationFunction {

    //takes the vector you get from moltiplying X by Theta and runs it through the specific activation function;
    public RealMatrix function(RealMatrix z);
}
