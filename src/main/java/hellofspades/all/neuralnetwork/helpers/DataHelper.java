package hellofspades.all.neuralnetwork.helpers;

public class DataHelper {

    //makes a 2 dimensional array of random numbers ranging from min to max
    public static double[][] makeData(int length, int paramsNum, double min, double max){
        double[][] data = new double[length][paramsNum];

        if(max<min){
            double temp = min;
            min = max;
            max = temp;
        }

        for(int i = 0;i<length;i++){
            for(int n = 0;n<paramsNum;n++){
                data[i][n] = Math.random()*(max-min)+min;
            }
        }

        return data;
    }

    //adds a 1 to the begining of each piece of data
    public static double[][] addOnes(double[][] X){
        double[][] newX = new double[X.length][X[0].length+1];

        for(int i = 0;i<X.length;i++){
            newX[i][0] = 1;
            for(int n = 0;n<X[i].length;n++){
                newX[i][n+1] = X[i][n];
            }
        }

        return newX;
    }

    //makes a 2 dimensional array for the Y data based on the input X data, that was most likely made in the makeData method
    public static double[][] makeY(double[][] X, MakeYFunction myf){
        double[][] Y = new double[X.length][];
        for(int i = 0;i<X.length;i++){
            Y[i] = myf.formula(X[i]);
        }
        return Y;
    }
}
