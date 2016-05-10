package pl.pwr.input;

import pl.pwr.model.Matrix;

import java.util.Random;

/**
 * Created by Rafal on 2016-03-26.
 */
public class MatrixGeneratorSingleton {
    private final static int INF = Integer.MAX_VALUE;
    private static MatrixGeneratorSingleton instance;

    private Random random;
    private int maxWeight;


    public MatrixGeneratorSingleton() {
        random = new Random();
        maxWeight = 100;
    }


    public static MatrixGeneratorSingleton getInstance() {

        if (instance == null) {
            instance = new MatrixGeneratorSingleton();
        }
        return instance;
    }

    public Matrix generate(int size) {
        int[][] result = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row != col) {
                    int value = random.nextInt(maxWeight) + 1;
                    result[row][col] = value;
                    result[col][row] = value;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            result[i][i] = INF;
        }
        return new Matrix(result);
    }

    public Matrix mock4Matrix() {
        int m[][] = {
                {INF, 92, 89, 17},
                {92, INF, 24, 95},
                {89, 24, INF, 67},
                {17, 95, 67, INF}
        };
        return new Matrix(m);
    }

    public Matrix mock5Matrix() {
        int m[][] = {
                {INF, 15, 18, 48, 14},
                {15, INF, 61, 62, 47},
                {18, 62, INF, 71, 95},
                {48, 62, 71, INF, 42},
                {14, 47, 95, 42, INF}

        };
        return new Matrix(m);
    }


    public Matrix mock6Matrix() {
        int m[][] = {
                {INF, 40, 63, 11, 5, 82},
                {40, INF, 67, 25, 79, 21},
                {63, 67, INF, 89, 77, 40},
                {11, 25, 89, INF, 44, 98},
                {5, 79, 77, 44, INF, 96},
                {82, 21, 40, 98, 96, INF}
        };
        return new Matrix(m);
    }

    public Matrix mock7Matrix() {
        int m[][] = {
                {INF, 24, 72, 50, 69, 71, 38},
                {24, INF, 88, 96, 39, 19, 91},
                {72, 88, INF, 54, 33, 87, 46},
                {50, 96, 54, INF, 62, 96, 12},
                {69, 39, 33, 62, INF, 58, 16},
                {71, 19, 87, 96, 58, INF, 29},
                {38, 91, 46, 12, 16, 29, INF}

        };
        return new Matrix(m);
    }

    public Matrix mock8Matrix() {
        int m[][] = {
                {INF, 24, 73, 71, 33, 95, 41, 15},
                {24, INF, 19, 53, 15, 23, 42, 25},
                {73, 19, INF, 45, 73, 66, 81, 48},
                {71, 53, 45, INF, 1, 73, 46, 37},
                {33, 15, 73, 1, INF, 73, 35, 30},
                {95, 23, 66, 73, 73, INF, 76, 79},
                {41, 42, 8, 1, 46, 35, 76, INF, 57},
                {15, 25, 48, 37, 30, 79, 57, INF}
        };
        return new Matrix(m);
    }

    public Matrix mockDummyMatrix() {
        int m[][] = {
                {INF, 1, 2, 3, 4, 5},
                {1, INF, 1, 2, 3, 4},
                {2, 1, INF, 1, 2, 3},
                {3, 2, 1, INF, 1, 2},
                {4, 3, 2, 1, INF, 1},
                {5, 4, 3, 2, 1, INF}
        };
        return new Matrix(m);
    }

}
