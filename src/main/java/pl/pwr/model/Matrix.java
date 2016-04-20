package pl.pwr.model;

import java.util.LinkedList;
import java.util.List;

public class Matrix {

    private int[][] matrix;
    private boolean symmetric;
    private int edgeCount;

    public Matrix(final int size, boolean symmetric) {
        this.symmetric = symmetric;
        edgeCount = size;
        matrix = new int[size][size];
    }

    public Matrix(int[][] matrix) {
        edgeCount = matrix.length;
        //System.out.println("Matrix.lenght  "+ edgeCount);
        this.matrix = matrix;
    }

    public Matrix(Matrix matrix) {
        this.symmetric = matrix.isSymmetric();
        this.edgeCount = matrix.getSize();
        this.matrix = cloneArray(matrix.getMatrix());
    }
        //fixme
    public int getEdgeCount() {
        return edgeCount;
    }

    public int getSize() {
        return edgeCount;
    }

    public boolean isSymmetric() {
        return symmetric;
    }



    public int getWeight(int from, int to) {
        return matrix[from][to];
    }

    public int[][] getMatrix() {
        return matrix;
    }



    public void printMatrix() {
        System.out.println("Matrix:\n -----------------------------------------------------");
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                System.out.print(matrix[i][j]+"  ");
            }
            System.out.println("\n ");
        }
        System.out.println("----------------------------------------------------");
    }

    private  int[][] cloneArray(final int[][] src) {
        int length = src.length;
        int[][] target = new int[length][length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, length);
        }
        return target;
    }

    public void setWeight(int row, int col, int weight) {
        matrix[row][col] = weight;
    }
}
