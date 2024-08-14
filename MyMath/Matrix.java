package MyMath;
import edu.princeton.cs.algs4.StdArrayIO;
public class Matrix {
    private int[] size = new int[2];
    private double[][] value;
    public Matrix(double[][] matrix){
        this.size[0] = matrix.length;
        this.size[1] = matrix[0].length;
        this.value = matrix;
    }
    public Matrix(double[] vector, boolean columnLike){
        if (columnLike){
            this.size[0] = vector.length;
            this.size[1] = 1;
            this.value = new double[size[0]][size[1]];
            for (int i = 0; i < vector.length; i++) {
                this.value[i][0]=vector[i];
            }
        }
        else{
            this.size[0]=1;
            this.size[1]=vector.length;
            this.value = new double[size[0]][size[1]];
            for (int i = 0; i < vector.length; i++) {
                this.value[0][i]=vector[i];
            }
        }
    }

    public static void main(String[] args) {
        double[] vector1 = StdArrayIO.readDouble1D();
        double[] vector2 = StdArrayIO.readDouble1D();
        double[][] matrix1 = StdArrayIO.readDouble2D();
        double[][] matrix2 = StdArrayIO.readDouble2D();
        double test1 = dot(vector1, vector2);
        double[] test2 = multi(matrix1, vector1);
        double[][] test3 = multi(matrix1, matrix2);
        double[][] test4 = transpose(matrix2);
        double[][] test5 = GaussianElimination(matrix1);
        System.out.println(test1);
        StdArrayIO.print(test2);
        StdArrayIO.print(test3);
        StdArrayIO.print(test4);
        StdArrayIO.print(test5);
        /*
        A data group:
            vector1(3): [1,2,3]
            vector2(3): [0,1,2]
            matrix1(3*3): 
                [[1,2,2],
                [1,1,0],
                [0,1,1]]
            matrix2(3*2): 
                [[1,2],
                [1,1],
                [3,1]]
            input:
3 1 2 3 3 0 1 2 3 3 1 2 2 1 1 0 0 1 1 3 2 1 2 1 1 3 1
            expected output:
8.0
3
 11.00000   3.00000   5.00000 
3 2
  9.00000   6.00000 
  2.00000   3.00000 
  4.00000   2.00000 
3 3
  1.00000   1.00000   3.00000
  2.00000   1.00000   1.00000 
3 3
  1.00000   2.00000   2.00000
  0.00000  -1.00000  -2.00000
  0.00000   0.00000  -1.00000 
         */
    }

    public static double[][] unitMatrix(int size){
        double[][] unitMatrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            unitMatrix[i][i] = 1;
        }
        return unitMatrix;
    }

    public static double dot(double[] vector1, double[] vector2){
        assert vector1.length == vector2.length;
        double a = 0;
        for (int i = 0; i < vector1.length; i++) {
            a += vector1[i] * vector2[i];
        }
        return a;
    }

    public static double multi(double[] vector1, double[] vector2){
        return dot(vector1, vector2);
    }

    public static double[] multi(double a, double[] vector){
        double[] vector_new = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            vector_new[i] = a * vector[i];
        }
        return vector_new;
    }

    public static double[] multi(double[][] matrix1, double[] vector2){
        assert vector2.length == matrix1[0].length;
        double[] a = new double[matrix1.length];
        for (int i = 0; i < a.length; i++) {
            double[] vector1 = matrix1[i];
            a[i] = dot(vector1, vector2);
        }
        return a;
    }

    public static double[] multi(double[] vector1, double[][] matrix2){
        return multi(transpose(matrix2), vector1);
    }

    public static double[][] multi(double a, double[][] matrix){
        double[][] matrix_new = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix_new[i][j] = a * matrix[i][j];
            }
        }
        return matrix;
    }

    public static double[][] multi(double[][] matrix1, double[][] matrix2){
        int rowNum = matrix1.length;
        int colNum = matrix2[0].length;
        int dotNum = matrix1[0].length;
        assert matrix2.length == dotNum;
        double[][] transposedMatrix2 = transpose(matrix2);
        double[][] matrix_new = new double[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                matrix_new[i][j] = dot(matrix1[i], transposedMatrix2[j]);
            }
        }
        return matrix_new;
    }

    public static Matrix multi(Matrix matrix1, Matrix matrix2){
        Matrix matrix_new = new Matrix(multi(matrix1.value, matrix2.value));
        return matrix_new;
    }

    public static double[] add(double[] vector1, double[] vector2){
        int length = vector1.length;
        assert vector2.length == length;
        double[] vector_new = new double[length];
        for (int i = 0; i < length; i++) {
            vector_new[i] = vector1[i] + vector2[i];
        }
        return vector_new;
    }

    public static double[][] add(double[][] matrix1, double[][] matrix2){
        int rowNum = matrix1.length;
        int colNum = matrix1[0].length;
        assert rowNum == matrix2.length;
        assert colNum == matrix2[0].length;
        double[][] matrix_new = new double[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < matrix_new.length; j++) {
                matrix_new[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return matrix_new;
    }

    public static double[][] transpose(double[][] matrix){
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        double[][] matrix_new = new double[colNum][rowNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                matrix_new[j][i] = matrix[i][j];
            }
        }
        return matrix_new;
    }

    public static double[][] matrixFromFlat(double[] array, int rowNum, int colNum){
        double[][] matrix = new double[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                matrix[i][j] = array[i*colNum+j];
            }
        }
        return matrix;
    }

    public static double[][] diagonalMatrixFromFlat(double[] array, int size){
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = array[i];
        }
        return matrix;
    }

    public static double[][] GaussianElimination(double[][] matrix){
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        int nowPivotRow = 0;
        int nowPivotCol = 0;
        double nowPivot;
        while (nowPivotRow < rowNum && nowPivotCol < colNum){
            if (matrix[nowPivotRow][nowPivotCol] == 0) {
                for (int otherRow = nowPivotRow+1; otherRow<rowNum;otherRow++) {
                    if (matrix[otherRow][nowPivotCol] != 0){
                        double[] newPivotRowValue = matrix[otherRow].clone();
                        matrix[otherRow] = matrix[nowPivotRow].clone();
                        matrix[nowPivotRow] = newPivotRowValue.clone();
                        break;
                    }
                }
            }
            nowPivot = matrix[nowPivotRow][nowPivotCol];
            if (nowPivot == 0){
                nowPivotCol++;
                continue;
            }
            for (int nowEliminatingRow = nowPivotRow+1; nowEliminatingRow < rowNum; nowEliminatingRow++){
                double[] nowRowValue = matrix[nowEliminatingRow];
                if (nowRowValue[nowPivotCol] != 0){
                    double[] diff = multi(-nowRowValue[nowPivotCol]/nowPivot, matrix[nowPivotRow]);
                    matrix[nowEliminatingRow] = add(nowRowValue,diff);
                }
            }
            nowPivotRow++;
            nowPivotCol++;
        }
        return matrix;
    }
}
