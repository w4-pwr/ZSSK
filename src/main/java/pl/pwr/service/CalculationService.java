package pl.pwr.service;

import pl.pwr.Main;
import pl.pwr.model.Matrix;
import pl.pwr.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rafal on 2015-11-04.
 */
public class CalculationService {
    private final int INF = Integer.MAX_VALUE;

    Node node;
    Matrix matrix;
    int[][] copyMatrix;
    int numberOfVerticles;

    public static CalculationService instance = null;
    private List<Integer> leftOversEstimateList;

    protected CalculationService() {
        
    }

    public static CalculationService getInstance() {
        if (instance == null) {
            instance = new CalculationService();
        }
        return instance;
    }

    public void countLowerBoundForNode(Matrix matrix, Node node) {
        this.node = node;
        this.matrix = matrix;
        numberOfVerticles = matrix.getEdgeCount();

    }




    private void copyWeightFromMatrixToList() {
        for (int i = 0; i < numberOfVerticles; i++) {
            for (int j = 0; j < numberOfVerticles; j++) {
                if (copyMatrix[i][j] != INF) {
                    leftOversEstimateList.add(copyMatrix[i][j]);
                }
            }
        }
    }





    public static int[][] cloneArray(final int[][] src) {
        int length = src.length;
        int[][] target = new int[length][length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, length);
        }
        return target;
    }

    public int calculateRouteCost(Matrix matrix, int[] route) {

        int  travelCosts = 0;
        for (int i = 1; i < route.length; i++) {
            travelCosts += matrix.getWeight(route[i - 1], route[i]);

            //System.out.println("costs from "+route[i-1]+" to "+route[i]+": "+costs[route[i-1]][route[i]]);
        }

        //return to starting city
        int n = matrix.getEdgeCount();
        //travelCosts += matrix.getWeight(route[n - 1], route[0]);

        //System.out.println("costs from " + route[n - 1] + " to " + route[0] + ": " + costs[route[n - 1]][route[0]]);
        return travelCosts;
    }
}
