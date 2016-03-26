package pl.pwr.algorithm;

import pl.pwr.model.Matrix;
import pl.pwr.service.CalculationService;

/**
 * Created by Rafal on 2016-03-26.
 */
public class BruteForceAlgorithm extends Algorithm{


    private int[] minRoute;

    private long count;

    private CalculationService calculationService;
    private double minCosts;

    @Override
    public void invoke(Matrix matrix) {
        this.matrix = matrix;

        calculationService = CalculationService.getInstance();

        int[] route = new int[matrix.getEdgeCount()];
        minRoute = new int[matrix.getEdgeCount()];

        minCosts = -1;

        count = 0;

        route[0] = 0;//first city always zero

        for(int i = 1;i < matrix.getEdgeCount();i++){
            route[1] = i;
            checkRoute(route,2);
        }

        System.out.println("Brute force results count: "+count+", minimum cost: "+minCosts+", route: ");
        printRoute(minRoute);

    }

    private void checkRoute(int[] route, int offset){

        if(offset == matrix.getEdgeCount()){
            int cost = calculationService.calculateRouteCost(matrix,route);
            if(minCosts < 0 || cost < minCosts){
                minCosts = cost;
                System.arraycopy(route,0,minRoute,0,route.length);
            }

            return;
        }

        loop:
        for(int i = 1;i<matrix.getEdgeCount();i++){
            for(int j = 0;j<offset;j++){
                if(route[j] == i){
                    continue loop;
                }
            }

            route[offset] = i;
            checkRoute(route,offset+1);
        }
    }

    private void printRoute(int[] minRoute) {
        for (int i = 0; i < minRoute.length; i++) {
            System.out.print(minRoute[i] + "  ");
        }

    }
}
