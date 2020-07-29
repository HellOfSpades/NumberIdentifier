package hellofspades.all.neuralnetwork.helpers;

import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public abstract class MatrixHelper {

    //sets each cell in a matrix a random number from -max to max;
    public static void setRandom(RealMatrix matrix, double max){

        for(int i = 0;i<matrix.getRowDimension();i++){
            for(int n = 0;n<matrix.getColumnDimension();n++){
                matrix.setEntry(i,n,Math.random()*(max*2)-max);
            }
        }

    }
    //prints the elements of the matrix in to the console
    public static void printMatrix(RealMatrix matrix){
        System.out.println("");
        for(int i = 0;i<matrix.getRowDimension();i++){
            for(int n = 0;n<matrix.getColumnDimension();n++){
                System.out.print(matrix.getEntry(i,n)+"\t");
            }
            System.out.println("");
        }
    }

    //ads a coulomn of ones as as the first coulumn of the matrix
    public static RealMatrix adOnes(RealMatrix matrix){
        RealMatrix output = MatrixUtils.createRealMatrix(matrix.getRowDimension(),matrix.getColumnDimension()+1);

        for(int i = 0;i<matrix.getRowDimension();i++){
            output.setEntry(i,0,1);
            for(int n = 0;n<matrix.getColumnDimension();n++){
                output.setEntry(i,n+1,matrix.getEntry(i,n));
            }
        }

        return output;

    }
}
