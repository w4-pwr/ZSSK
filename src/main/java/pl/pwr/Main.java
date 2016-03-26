package pl.pwr;

import pl.pwr.algorithm.Algorithm;
import pl.pwr.algorithm.BBAlgorithm;

import java.io.*;

public class Main {

    public static final boolean DEBUG = true;



    public static void main(String[] args) {
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

            Algorithm bbAlgorithm = new BBAlgorithm();
            //bbAlgorithm.generateMatrix(17);
            //bbAlgorithm.mock4Matrix();
        //   bbAlgorithm.mock5Matrix();

            long timeSum = testAlgorithm(1);
//            for (int i = 10; i < 19; i++) {
//                bbAlgorithm.generateMatrix(i);
//                long timeSum = testAlgorithm(10);
//
//                System.out.println(i + " wierzchołki, średnia: " + timeSum);
//                fileOutput.write(String.format(" %d wierzcholkow, czas %d \n", i, timeSum));
//            }
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
        return  (stop - start);
    }

    private static long testAlgorithm(int howMany) {
        long timeSum = 0;

        for (int j = 0; j < howMany; j++) {
            long singleTime = runAlgorithm();

            System.out.println("przejście " + j + " czas: " + singleTime);

            timeSum += singleTime;
        }
        return timeSum/howMany;
    }
}
