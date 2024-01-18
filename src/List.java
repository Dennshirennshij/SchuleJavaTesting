import java.util.ArrayList;
import java.util.Objects;

public class List<K> {
    private Node<K> node;
    private Node<K> getNode0(){
        while (node.previousNode != null) {
            node = node.previousNode;
        }
        return node;
    }
    private Node<K> getNode(int i) throws IndexOutOfBoundsException{
        Node<K> node = getNode0();
        for (int j = 0; j < i; j++) {
            node = node.nextNode;
        }
        return node;
    }


    public int getSize(){
        int size = 0;
        Node<K> node = getNode0();
        while (node != null) {
            size++;
            node = node.nextNode;
        }
        return size;
    }
    public K get(int i) throws IndexOutOfBoundsException{
        return getNode(i).element;
    }
    public void add(K object) {
        int size = getSize();
        if (size == 0) {
            node = new Node<K>(object);
        } else {
            Node<K> node = getNode(size - 1); //Last Node
            node.nextNode = new Node<K>(object);
            node.nextNode.previousNode = node;
        }
    }
    public void add(int index, K object) {
        int size = getSize();
        if (index == size) {
            add(object);
        } else {
            Node<K> node = getNode(index);
            Node<K> newNode = new Node<K>(object);
            newNode.nextNode = node;
            newNode.previousNode = node.previousNode;
            node.previousNode.nextNode = newNode;
            node.previousNode = newNode;
        }
    }
    public void remove(int index) {
        Node<K> node = getNode(index);
        if (node.previousNode == null) {
            this.node = node.nextNode;
        } else if (node.nextNode == null){
            this.node = node.previousNode;
            this.node.nextNode = null;
        }
        else {
            node.previousNode.nextNode = node.nextNode;
        }
    }
    public boolean removeObject(K object) {
        int index = indexOf(object);
        if (index != -1) {
            remove(index);
            return true;
        } else return false;
    }
    public boolean contains(K object) {
        return indexOf(object) != -1;
    }
    public int indexOf(K object) {
        Node<K> node = getNode0();
        int index = 0;
        while (node != null) {
            if (node.element.equals(object)) {
                return index;
            }
            node = node.nextNode;
            index++;
        }
        return -1;
    }
    //Bubble Sort
    public void sort(Comperator<K> sortingPattern) {
        //TODO
    }
    //Merge Sort
    //Quick Sort

    public static abstract class Comperator<K> {
        public abstract boolean compare(K a, K b); //returns true if a should have a higher index than b
    }
    private static class Node<K> {
        public Node<K> previousNode;
        public Node<K> nextNode;
        public K element;
        public Node(K element) {
            previousNode = null;
            nextNode = null;
            this.element=element;
        }

    }
}
