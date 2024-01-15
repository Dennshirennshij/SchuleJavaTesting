public class Queue<K> implements ADT_Queue<K>{
    private Node<K> bottomNode;
    private Node<K> topNode;
    public Queue() {
        topNode = null;
        bottomNode = null;
    }
    public void queue(K element) {
        Node<K> newNode = new Node<K>(null, element);
        if (topNode == null) {
            topNode = newNode;
            bottomNode = topNode;
            return;
        }
        topNode.previousNode = newNode;
        topNode = newNode;
    }
    public K dequeue() {
        if (bottomNode == null) {
            return null;
        }
        K element = bottomNode.getElement();
        bottomNode = bottomNode.getPreviousNode();
        return element;
    }
    public K front(){
        if (bottomNode == null) {
            return null;
        }
        return bottomNode.getElement();
    }
    public boolean isEmpty() {
        return bottomNode == null;
    }
    private static class Node<K> {
        public Node<K> previousNode;
        public K element;
        public Node(Node<K> previousNode, K element) {
            this.previousNode = previousNode;
            this.element = element;
        }
        public Node<K> getPreviousNode() {
            return previousNode;
        }
        public K getElement() {
            return element;
        }
    }

}
