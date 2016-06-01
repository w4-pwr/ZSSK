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
        //runDemo();
        //runTestTimeBBAlgorithm();
        runAlgorithmMultiThreaded();

      //  BBAlgorithm algorithm = new BBAlgorithm();
       // runAlgorithmSerially(algorithm);
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
        Matrix matrix10 = MatrixGeneratorSingleton.getInstance().generate(44);
        threadsStartTime = System.currentTimeMillis();

        for(int i=0; i<10;i++){
            new Thread(() -> {
                new BBAlgorithm().invoke(matrix10);
                notifyAboutEndWork();
            }).start();
        }
    }

    private synchronized static void notifyAboutEndWork() {
        workEnds++;

        if (workEnds == 10) {
            long stop = System.currentTimeMillis();
            System.out.println("Razem: " + (stop - threadsStartTime) + "ms");
        }
    }


    private static void runAlgorithmSerially(Algorithm algorithm) {
        System.out.print("SZEREGOWO\n");
        Matrix matrix10 = MatrixGeneratorSingleton.getInstance().generate(10);
        long allTime = 0L;
        for(int i=0; i<10;i++){
            long start = System.currentTimeMillis();
            algorithm.invoke(matrix10);
            long stop = System.currentTimeMillis();
            long currentTime = stop - start;
            System.out.println("Przebieg nr: "+ i +"czas: "+currentTime);
            allTime+= currentTime;
        }
           System.out.println("suma czasów: " + allTime );
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
