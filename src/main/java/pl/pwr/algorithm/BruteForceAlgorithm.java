package pl.pwr.algorithm;

import pl.pwr.model.Matrix;
import pl.pwr.output.AlgorithmProduct;
import pl.pwr.service.CalculationService;

/**
 * Created by Rafal on 2016-03-26.
 */
public class BruteForceAlgorithm extends Algorithm{

    private Matrix matrix;
    private int[] minRoute;
    private double minCosts;
    private long count;

    private CalculationService calculationService;

    @Override
    public AlgorithmProduct invoke(Matrix matrix) {
        this.matrix = matrix;

        initVariables();
        startRecursiveAlgorithm();

        return returnResults();
    }

    private void startRecursiveAlgorithm() {

        int[] route = new int[matrix.getEdgeCount()];
        route[0] = 0;//first city always zero

        for(int i = 1;i < matrix.getEdgeCount();i++){
            route[1] = i;
            checkRoute(route,2);
        }
    }

    private void checkRoute(int[] route, int offset){

        if(offset == matrix.getEdgeCount()){
            int cost = calculationService.calculateRouteCost(matrix,route);
            if(cost < minCosts){
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

    private AlgorithmProduct  returnResults() {

        return  new AlgorithmProduct(minRoute,minCosts);
    }

    private void initVariables() {
        calculationService = CalculationService.getInstance();
        minRoute = new int[matrix.getEdgeCount()];
        minCosts = Double.MAX_VALUE;
        count = 0;
    }


}
