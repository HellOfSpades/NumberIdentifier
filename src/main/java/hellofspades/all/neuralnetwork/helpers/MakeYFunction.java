package hellofspades.all.neuralnetwork.helpers;

import org.apache.commons.math.linear.RealMatrix;

public interface MakeYFunction {

    //returns a vector Y based on vector X, based on the overiden method.
    public double[] formula(double[] X);
}
