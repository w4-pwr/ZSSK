package pl.pwr.model;

import pl.pwr.Main;

public class Node {

    public int[] order;
    public int lowerBound;
    public int numberOfNodes;

    public Node(int size) {
        this.numberOfNodes = 1;
        order = new int[size];
    }

    public Node(Node currentNode, int nextPoint) {
        this.order = copyOrder(currentNode.order);
        numberOfNodes = currentNode.numberOfNodes;
        this.order[numberOfNodes] = nextPoint;
        numberOfNodes = currentNode.numberOfNodes + 1;
    }

    public boolean checkIfVisitedPoint(int point) {
        for (int i = 0; i < numberOfNodes; i++)
            if (order[i] == point)
                return true;
        return false;
    }

    public boolean hasAllPlaceVisited() {
        return order.length == numberOfNodes;
    }

    public int[] copyOrder(int[] order) {
        int[] copiedOrder = new int[order.length];
        for (int i = 0; i < order.length; i++) {
            copiedOrder[i] = order[i];
        }
        return copiedOrder;
    }

    public void printOrder(){
        if(Main.DEBUG) {
            System.out.println("kolejnosc odwiedzanych miejscowosci:");

        for (int i = 0; i < order.length; i++) {
            System.out.print (order[i] + "  ");
        }
        }
    }
}
