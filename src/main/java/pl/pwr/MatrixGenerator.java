package pl.pwr;

import java.util.Random;

/**
 * Created by Rafal on 2015-11-11.
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
}
