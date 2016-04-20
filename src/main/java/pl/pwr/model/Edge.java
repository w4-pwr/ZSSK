package pl.pwr.model;

public class Edge {

    public final static int NO_EXIST = 0;

    public int startVertex;
    public int endVertex;
    public int weight = 0;



    public Edge() {
    }

    public boolean isExist() {
        return weight > NO_EXIST;
    }

    public void invalidate() {
        weight = NO_EXIST;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge tmp = (Edge) obj;
            return tmp.endVertex == endVertex && tmp.startVertex == startVertex;
        }
        return false;
    }

}
