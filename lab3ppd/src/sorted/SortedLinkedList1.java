package sorted;

public class SortedLinkedList1 {

    static class Node {

        double val;
        Node next;

        public Node(double val) {
            this.val = val;
            next = null;
        }

        public Node() {
            this.val = 0.0D;
            next = null;
        }

    }

    Node first = null;

    public synchronized void insert(double val) {
        Node newNode = new Node(val);
        if (first == null)
            first = newNode;
        else {
            Node currentNode = first;

            while (currentNode.next != null && currentNode.next.val < val)
                currentNode = currentNode.next;

            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
    }

    public void insert(double... val) {
        for (double v : val) {
            insert(v);
        }
    }

    public synchronized void delete(double val) throws Exception {
        if (first.val == val)
            first = first.next;
        else {
            Node currentNode = first;

            while (currentNode.next != null && currentNode.next.val < val)
                currentNode = currentNode.next;

            if (currentNode.next == null || currentNode.next.val != val)
                throw new Exception("Value " + val + " must be in the list!");

            currentNode.next = currentNode.next.next;
        }
    }

    public synchronized SortedLinkedListIterator getIterator() {
        return new SortedLinkedListIterator(first);
    }

}
