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
        System.out.println("----------------------------------------------------\n");
    }

    private void printRoute(int[] minRoute) {

        for (int verticle : minRoute) {
            System.out.print(verticle + "  ");
        }
        System.out.println("\n");
    }

    public int[] getResultRoute() {
        return resultRoute;
    }

    public void setResultRoute(int[] resultRoute) {
        this.resultRoute = resultRoute;
    }

    public double getMinCost() {
        return minCost;
    }

    public void setMinCost(double minCost) {
        this.minCost = minCost;
    }
}
