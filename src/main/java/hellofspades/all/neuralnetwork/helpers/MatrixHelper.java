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
                System.out.print(matrix.getEntry(i,n)+"\t\t\t\t");
            }
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    //moltiplies two matrices of equal size by having each of their elemnts be moltiplied by the corresponding from the other
    public static RealMatrix moltiplyEachElement(RealMatrix x1, RealMatrix x2){
        RealMatrix output = MatrixUtils.createRealMatrix(x1.getRowDimension(),x1.getColumnDimension());

        for(int i = 0;i<x1.getRowDimension();i++){
            for(int n = 0;n<x1.getColumnDimension();n++){
                output.setEntry(i,n,x1.getEntry(i,n)*x2.getEntry(i,n));
            }
        }

        return output;
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

    //sets every element to be a log of its og self
    public static RealMatrix logEachElement(RealMatrix matrix){
        RealMatrix output = MatrixUtils.createRealMatrix(matrix.getRowDimension(),matrix.getColumnDimension());
        for(int i = 0;i<matrix.getRowDimension();i++){
            for(int n = 0;n<matrix.getColumnDimension();n++){
                output.setEntry(i,n,Math.log(matrix.getEntry(i,n)));
            }
        }
        return output;
    }

    //sets each ellement of the matrix to that element to "power" power
    public static RealMatrix power(RealMatrix matrix, double power){
        RealMatrix output = MatrixUtils.createRealMatrix(matrix.getRowDimension(),matrix.getColumnDimension());
        for(int i = 0;i<matrix.getRowDimension();i++){
            for(int n = 0;n<matrix.getColumnDimension();n++){
                output.setEntry(i,n,Math.pow(matrix.getEntry(i,n),power));
            }
        }
        return output;
    }

    //returns the sum of all elements in a matrix
    public static double sum(RealMatrix matrix){
        double sum = 0;
        for(int i = 0;i<matrix.getRowDimension();i++){
            for(int n = 0;n<matrix.getColumnDimension();n++){
                sum+=matrix.getEntry(i,n);
            }
        }
        return  sum;
    }
}
