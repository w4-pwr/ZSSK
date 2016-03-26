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
        countCurrentPathWeight();
        estimateLowerBound();
    }

    private void countCurrentPathWeight() {
        int currentLowerBound = 0;
        for (int i = 0; i < node.numberOfNodes - 1; i++) {
            currentLowerBound += matrix.getWeight(node.order[i], node.order[(i + 1)]);
        }
        node.lowerBound += currentLowerBound;
    }

    private void estimateLowerBound() {
        copyMatrix = cloneArray(matrix.getMatrix());
        scratchDiagonal();
        scratchVisitedVerticles();
        calculateShortestAcceptablePath();
    }

    private void calculateShortestAcceptablePath() {
        int leftOver = numberOfVerticles - node.numberOfNodes + 1;
        leftOversEstimateList = new ArrayList<>();
        copyWeightFromMatrixToList();
        Collections.sort(leftOversEstimateList);
        try {
            for (int i = 0; i < leftOver; i++) {
                node.lowerBound += leftOversEstimateList.get(i);
            }

        } catch (IndexOutOfBoundsException exc) {
            if (Main.DEBUG) {
                System.err.println("Za malo mozliwych elementow do oszacowania");
            }
        }
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

    private void scratchVisitedVerticles() {
        for (int i = 0; i < node.numberOfNodes; i++) {
            for (int j = 0; j < numberOfVerticles; j++) {
                copyMatrix[node.order[i]][j] = INF;
                copyMatrix[j][node.order[i]] = INF;
            }
        }
    }

    private void scratchDiagonal() {
        for (int i = 0; i < numberOfVerticles; i++) {
            copyMatrix[i][i] = INF;
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
        travelCosts += matrix.getWeight(route[n - 1], route[0]);

        //System.out.println("costs from " + route[n - 1] + " to " + route[0] + ": " + costs[route[n - 1]][route[0]]);
        return travelCosts;
    }
}
