package sorted;

import java.util.Iterator;

public class SortedLinkedListIterator implements Iterator<Double> {

    private SortedLinkedList1.Node currentNode;

    public SortedLinkedListIterator(SortedLinkedList1.Node currentNode) {
        this.currentNode = new SortedLinkedList1.Node();
        this.currentNode.next = currentNode;
    }

    @Override
    public synchronized boolean hasNext() {
        return currentNode.next != null;
    }

    @Override
    public synchronized Double next() {
        if (hasNext())
            currentNode = currentNode.next;
        return currentNode.val;
    }

    @Override
    public synchronized void remove() {

    }

}
