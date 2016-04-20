package pl.pwr;

import pl.pwr.algorithm.BBAlgorithm;
import pl.pwr.algorithm.BruteForceAlgorithm;
import pl.pwr.input.MatrixGeneratorSingleton;
import pl.pwr.model.Matrix;
import pl.pwr.output.AlgorithmProduct;

import java.io.*;

public class Main {

    public static final boolean DEBUG = true;


    public static void main(String[] args) {
        Matrix matrix = MatrixGeneratorSingleton.getInstance().mock5Matrix();
        matrix.printMatrix();

        BruteForceAlgorithm bfAlgorithm = new BruteForceAlgorithm();
        BBAlgorithm bBalgorithm = new BBAlgorithm();

        AlgorithmProduct result;

        System.out.println("BruteForce:");
        result = bfAlgorithm.invoke(matrix);
        result.printResultData();

        System.out.println("Branch and bound:");
        result = bBalgorithm.invoke(matrix);
        result.printResultData();
    }


    private static void readMatrixFromFile() {
        Writer fileOutput = null;
        try {
            fileOutput = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("wyniki.txt"), "utf-8"));

            //cool down the situation bro
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.err.println("Nie znaleziono pliku");
        } finally {
            try {
                fileOutput.close();
            } catch (Exception ex) {/*ignore*/}

        }
    }

    private static long runAlgorithm() {
        long start = System.currentTimeMillis();
        //  bbAlgorithm.invoke();
        long stop = System.currentTimeMillis();
        return (stop - start);
    }

    private static long testAlgorithm(int howMany) {
        long timeSum = 0;

        for (int j = 0; j < howMany; j++) {
            long singleTime = runAlgorithm();

            System.out.println("przejście " + j + " czas: " + singleTime);

            timeSum += singleTime;
        }
        return timeSum / howMany;
    }
}
