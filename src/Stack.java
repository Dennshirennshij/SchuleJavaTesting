public class Stack<K> implements ADT_Storage<K> {
    private Node<K> topNode;

    public Stack() {
        topNode = null;
    }

    public void push(K element) {
        topNode = new Node<K>(topNode, element);
    }
    public K pop() {
        if (topNode == null) {
            return null;
        }
        K element = topNode.getElement();
        topNode = topNode.getPreviousNode();
        return element;
    }
    public K peek() {
        if (topNode == null) {
            return null;
        }
        return topNode.getElement();
    }
    public boolean isEmpty() {
        return topNode == null;
    }

    private class Node<K> {
        private Node<K> previousNode;
        private K element;

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