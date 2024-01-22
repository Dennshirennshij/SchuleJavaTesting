public class List<K> {
    private Node<K> node;
    public List() {
        node = null;
    }
    private Node<K> getNode0(){
        if (node == null) {
            return null;
        }
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
    public void set(K element, int index) throws IndexOutOfBoundsException{
        getNode(index).element = element;
    }
    private void addSingle(K object){
        System.out.println("Tryed: Added " + object.toString() + " to List");
        int size = getSize();
        if (size == 0) {
            node = new Node<K>(object);
        } else {
            Node<K> node = getNode(size - 1); //Last Node
            node.nextNode = new Node<K>(object);
            node.nextNode.previousNode = node;
        }
        System.out.println("Added " + object.toString() + " to List");
    }
    public void add(K... objects) {
        for (K object : objects) {
            addSingle(object);
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
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Node<K> node = getNode0();
        while (node != null) {
            stringBuilder.append(node.element.toString());
            if (node.nextNode != null) {
                stringBuilder.append(", ");
            }
            node = node.nextNode;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void sort(Comperator<K> sortingPattern, SortingMethod sortingMethod) {
        switch (sortingMethod.toString()) {
            case "BUBBLE_SORT":
                bubbleSort(this, getSize(), sortingPattern);
                break;
            default:
                throw new IllegalArgumentException("Sorting Method " + sortingMethod.toString() + " not found");
        }
    }
    //Bubble Sort
    private void bubbleSort(List<K> list, int n, Comperator<K> sortingPattern)
    {
        if (n == 1)                     //passes are done
        {
            return;
        }
        boolean traded = false;
        for (int i=0; i<n-1; i++)       //iteration through unsorted elements
        {
            if (sortingPattern.compare(list.get(i), list.get(i+1)))      //check if the elements are in order
            {                           //if not, swap them
                traded = true;
                K temp = list.get(i);
                list.set(list.get(i+1), i);
                list.set(temp, i+1);
            }
        }
        if (traded)            //if no elements were swapped, the list is sorted
        {
            bubbleSort(list, n-1, sortingPattern);
        }
    }
    //Merge Sort
    //Quick Sort



    public static abstract class Comperator<K> {
        public abstract boolean compare(K a, K b); //returns true if a should have a higher index than b
    }
    public static enum SortingMethod {
        BUBBLE_SORT
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
