package pl.pwr.model;

public class Edge {

    public final static int NO_EXIST = 0;

    public int vertex1;
    public int vertex2;
    public int weight;

    public Edge(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public Edge(int vertex1, int vertex2) {
        this(vertex1, vertex2, NO_EXIST);
    }


    public boolean isExist() {
        return weight > NO_EXIST;
    }

    public void invalidate() {
        weight = NO_EXIST;
    }

    public int getWeight() {
        return weight;
    }
//
//    public static class AscendingWeightComparator implements Comparator<Edge> {
//        @Override
//        public int compare(Edge first, Edge second) {
//            return Integer.compare(second.weight, first.weight);
//        }
//    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Edge) {
            Edge edge = (Edge) obj;
            if((edge.vertex1 == vertex1 && edge.vertex2 == vertex2) || (edge.vertex1 == vertex2 && edge.vertex2 == vertex1)) {
                return weight == edge.weight;
            }
        }
        return false;
    }

}
