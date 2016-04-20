package pl.pwr.model;

/**
 * Created by Rafal on 2015-11-01.
 *
 */
public class NodeList {

    private ListElement first;

    public NodeList(Node first) {
        this.first = new ListElement(first);
    }

    public Node popBestAndRemoveWorseThan(float upperBound) {
        ListElement best = null;
        ListElement current = first;
        while (current != null) {
            if (upperBound < current.node.lowerBound) {
                remove(current);
            } else if (best == null || current.node.lowerBound < best.node.lowerBound) {
                best = current;
            }
            current = current.next;
        }
        if (best != null) {
            remove(best);
            return best.node;
        } else {
            return null;
        }
    }

    private void remove(ListElement node) {
        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            first = node.next;
        }
        if (node.next != null) {
            node.next.previous = node.previous;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insert(Node node) {
        ListElement newListElement = new ListElement(node);
        if (first == null) {
            first = newListElement;
        } else {
            first.previous = newListElement;
            newListElement.next = first;
            first = newListElement;
        }
    }


    public static class ListElement {

        public ListElement previous;
        public final Node node;
        public ListElement next;

        public ListElement(Node node) {
            this.node = node;
        }

    }
}
