package pl.pwr.algorithm;

import pl.pwr.input.MatrixGenerator;
import pl.pwr.service.CalculationService;
import pl.pwr.model.Matrix;
import pl.pwr.model.Node;
import pl.pwr.model.NodeList;

/**
 * Created by Rafal on 2015-10-31.
 */
public class BBAlgorithm extends Algorithm{

    private final int INF = Integer.MAX_VALUE;
    private String inputFiletName;
    private int upperBound;
    private int numberOfVerticles;
    private NodeList queue;
    private Node bestSolution;

    public BBAlgorithm(Matrix matrix) {
       this.matrix = matrix;
    }


    public void generateMatrix(int size) {
        MatrixGenerator matrixGenerator = new MatrixGenerator();
        int m[][] = matrixGenerator.generate(size);
        matrix = new Matrix(m);
        matrix.printMatrix();
    }

    public BBAlgorithm() {
    }

    public BBAlgorithm(String inputFiletName) {
        this.inputFiletName = inputFiletName;
    }


    public void invoke(Matrix matrix) {
        numberOfVerticles = matrix.getEdgeCount();
        addFirstNodeToQueue();

        while (!queue.isEmpty()) {
            Node currentNode = queue.popBestAndRemoveWorseThan(upperBound);

            if (isCompletePath(currentNode)) {
                checkIfBetterWeight(currentNode);
            } else {
                createSubproblems(currentNode);
            }
        }
        System.out.println("upper bound " + upperBound);
        bestSolution.printOrder();

    }

    private boolean isCompletePath(Node currentNode) {
        return currentNode != null && currentNode.hasAllPlaceVisited();
    }

    private void checkIfBetterWeight(Node currentNode) {
        if (currentNode.lowerBound < upperBound) {
            upperBound = currentNode.lowerBound;
            bestSolution = currentNode;
            // bestSolution.printOrder();
        }
    }

    private void createSubproblems(Node currentNode) {
        for (int i = 0; i < numberOfVerticles; i++) {
            if (currentNode != null && !currentNode.checkIfVisitedPoint(i)) {
                Node newSubProblem = new Node(currentNode, i);
                CalculationService.getInstance().countLowerBoundForNode(matrix, newSubProblem);
                if (newSubProblem.lowerBound < upperBound) {
                    queue.insert(newSubProblem);
                }
            }
        }
    }

    private void addFirstNodeToQueue() {
        Node firstTry = initFirstNode();
        bestSolution = firstTry;
        CalculationService.getInstance().countLowerBoundForNode(matrix, firstTry);
        upperBound = INF;
        queue = new NodeList(firstTry);
    }

    private Node initFirstNode() {
        Node firstTry = new Node(numberOfVerticles);
        for (int i = 0; i < matrix.getSize(); i++)
            firstTry.order[i] = i;
        return firstTry;
    }




}
