package pl.pwr.output;

/**
 * Created by Rafal on 2016-04-06.
 */
public class AlgorithmProduct {

    private int[] resultRoute;
    private double minCost;

    public AlgorithmProduct(int[] resultRoute, double minCost) {
        this.resultRoute = resultRoute;
        this.minCost = minCost;
    }
    public void printResultData(){

        System.out.println("Results:  minimum cost: "+minCost+", route: ");
        printRoute(resultRoute);
    }

    private void printRoute(int[] minRoute) {

        for (int verticle : minRoute) {
            System.out.print(verticle + "  ");
        }
    }

}
