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
    private static long threadsStartTime = System.currentTimeMillis();
    private static int workEnds = 0;

    public static void main(String[] args) {
        runDemo();
        //runAlgorithmMultiThreaded();
    }

    private static void runTestTimeBruteForceAlgorithm() {
        BruteForceAlgorithm bruteForceAlgorithm = new BruteForceAlgorithm();
        runAlgorithmSerially(bruteForceAlgorithm);
        runAlgorithmMultiThreaded();
    }

    private static void runTestTimeBBAlgorithm() {
        BBAlgorithm bbAlgorithm = new BBAlgorithm();
        runAlgorithmSerially(bbAlgorithm);
        runAlgorithmMultiThreaded();
    }
    private static void runAlgorithmMultiThreaded() {
        System.out.println("WIELOWĄTKOWO:");
        Matrix matrix10 = MatrixGeneratorSingleton.getInstance().generate(30);
        Matrix matrix11 = MatrixGeneratorSingleton.getInstance().generate(40);
        Matrix matrix12 = MatrixGeneratorSingleton.getInstance().generate(50);
        Matrix matrix13 = MatrixGeneratorSingleton.getInstance().generate(60);

        Thread thread = new Thread(() -> {
            new BBAlgorithm().invoke(matrix10);
            notifyAboutEndWork();

        });
        Thread thread1 = new Thread(() -> {
            new BBAlgorithm().invoke(matrix11);
            notifyAboutEndWork();
        });
        Thread thread2 = new Thread(() -> {
            new BBAlgorithm().invoke(matrix12);
            notifyAboutEndWork();
        });
//        Thread thread3 = new Thread(() -> {
//            new BBAlgorithm().invoke(matrix13);
//            notifyAboutEndWork();
//        });
        threadsStartTime = System.currentTimeMillis();
        thread.start();
        thread1.start();
        thread2.start();
       // thread3.start();

    }

    private synchronized static void notifyAboutEndWork() {
        workEnds++;

        if (workEnds == 3) {
            long stop = System.currentTimeMillis();
            System.out.println("Razem: " + (stop - threadsStartTime) + "ms");
        }
    }


    private static void runAlgorithmSerially(Algorithm algorithm) {
        System.out.print("SZEREGOWO\n");
        Matrix matrix10 = MatrixGeneratorSingleton.getInstance().generate(30);
        Matrix matrix11 = MatrixGeneratorSingleton.getInstance().generate(40);
        Matrix matrix12 = MatrixGeneratorSingleton.getInstance().generate(70);

        long start = System.currentTimeMillis();
        algorithm.invoke(matrix10);
        long stop = System.currentTimeMillis();
        long overAllTimeFor10Edges = stop - start;
        System.out.println("Dla 10 wierzcholkow: " + overAllTimeFor10Edges + "ms");

        start = System.currentTimeMillis();
        algorithm.invoke(matrix11);
        stop = System.currentTimeMillis();
        long overAllTimeFor11Edges = stop - start;
        System.out.println("Dla 11 wierzcholkow: " + overAllTimeFor11Edges + "ms");

        start = System.currentTimeMillis();
        algorithm.invoke(matrix12);
        stop = System.currentTimeMillis();
        long overAllTimeFor12Edges = stop - start;
        System.out.println("Dla 12 wierzcholkow: " + overAllTimeFor12Edges + "ms");

        long sum = overAllTimeFor10Edges + overAllTimeFor11Edges + overAllTimeFor12Edges;
        System.out.println("Razem: " + sum + "ms");
    }

    private static void runDemo() {
        Matrix matrix = MatrixGeneratorSingleton.getInstance().mockDummyMatrix();
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
