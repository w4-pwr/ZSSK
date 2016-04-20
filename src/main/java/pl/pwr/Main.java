package pl.pwr;

import pl.pwr.algorithm.Algorithm;
import pl.pwr.algorithm.BBAlgorithm;
import pl.pwr.algorithm.BruteForceAlgorithm;
import pl.pwr.input.MatrixGeneratorSingleton;
import pl.pwr.model.Matrix;
import pl.pwr.output.AlgorithmProduct;

import java.io.*;

public class Main {

    public static final boolean DEBUG = true;


    public static void main(String[] args) {
        //runDemo();
        runTestTimeBruteForceAlgorithm();
    }

    private static long runTestTimeBruteForceAlgorithm() {
        runAlgorithmSerially(new BruteForceAlgorithm());

        System.out.print("rownolegle Brute Force:");

       long  start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Matrix matrix10 = MatrixGeneratorSingleton.getInstance().generate(10);
                BruteForceAlgorithm bBalgorithm = new BruteForceAlgorithm();
                bBalgorithm.invoke(matrix10);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Matrix matrix11 = MatrixGeneratorSingleton.getInstance().generate(11);
                BruteForceAlgorithm bBalgorithm = new BruteForceAlgorithm();
                bBalgorithm.invoke(matrix11);
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                Matrix matrix12 = MatrixGeneratorSingleton.getInstance().generate(12);
                BruteForceAlgorithm bBalgorithm = new BruteForceAlgorithm();
                bBalgorithm.invoke(matrix12);
            }
        });
        long stop = System.currentTimeMillis();

        System.out.println("Multithread: " + (stop-start));
        return (stop - start);
    }

    public static void runAlgorithmSerially(Algorithm algorithm){
         Matrix matrix10 = MatrixGeneratorSingleton.getInstance().generate(10);
        Matrix matrix11 = MatrixGeneratorSingleton.getInstance().generate(11);
        Matrix matrix12 = MatrixGeneratorSingleton.getInstance().generate(12);

        System.out.print("SZEREGOWO\n");

        long start = System.currentTimeMillis();
        algorithm.invoke(matrix10);
        long stop = System.currentTimeMillis();
        System.out.println("Dla 10 wierzcholkow: " + (stop-start));

        start = System.currentTimeMillis();
        algorithm.invoke(matrix11);
        stop = System.currentTimeMillis();
        System.out.println("Dla 11 wierzcholkow: " + (stop-start));

        start = System.currentTimeMillis();
        algorithm.invoke(matrix12);
        stop = System.currentTimeMillis();
        System.out.println("Dla 12 wierzcholkow: " + (stop-start));
    }

    private static void runDemo() {
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


}
