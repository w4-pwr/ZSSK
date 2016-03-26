package pl.pwr.input;

import pl.pwr.model.Matrix;

import java.util.Random;

/**
 * Created by Rafal on 2016-03-26.
 */
public class MatrixGenerator {

    private Random random;
    private int maxWeight;

    public MatrixGenerator() {
        random = new Random();
        maxWeight = 100;
    }

    public MatrixGenerator(int maxWeight) {
        random = new Random();
        this.maxWeight = maxWeight;
    }

    public int[][] generate(int size) {
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
        return result;
    }

    public Matrix mock4Matrix() {
        int m[][] = {
                {0, 92, 89, 17},
                {92, 0, 24, 95},
                {89, 24, 0, 67},
                {17, 95, 67, 0}
        };
        return new Matrix(m);
    }

    public Matrix mock5Matrix() {
        int m[][] = {
                {0, 15, 18, 48,14},
                {15, 0, 61, 62,47},
                {18, 62, 0, 71,95},
                {48, 62, 71, 0,42},
                {14, 47, 95, 42,0}

        };
        return new Matrix(m);
    }
}
