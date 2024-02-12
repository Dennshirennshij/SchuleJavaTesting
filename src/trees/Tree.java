package trees;

public abstract class Tree<K> {

    private Node<K> root;
    protected abstract boolean compare(K a, K b);
    public Node<K> getRoot() {
        return root;
    }

    public void insert(K value) {
        if (root==null) {
            root = new Node<K>(value, null);
        } else {
            Node<K> currentNode = this.getRoot();
            K currentNodeValue = currentNode.getValue();
            while (true) {
                if (compare(currentNodeValue, value)) {
                    //Wenn value links von currentNode eingefügt werden soll
                    if(currentNode.getLeft() == null) {

                    }
                } else {
                    //Wenn value rechts von currentNode eingefügt werden soll
                }
            }
        }
    }

    private void insertLeft(Node<K> node, K value) {
        node.setLeft(new Node<>(value, node));
        node.descreaseBalence();
        if (node.getBalance() == 1) {

        }
    }
    private void checkBalence(Node<K> parentNode, Node<K> previousNode) {
        if (previousNode.getDirection() == Direction.LEFT) parentNode.descreaseBalence();
        if (previousNode.getDirection() == Direction.RIGHT) parentNode.increaseBalence();
        if (parentNode.getBalance() == 0) return;
        if (parentNode.getBalance() == 1 || parentNode.getBalance() == -1) {
            if (parentNode == getRoot()) return;
            // Check the balence up a row
            checkBalence(parentNode.getParent(), parentNode);
        }
        if (parentNode.getBalance() == 2) {
            if (previousNode.getBalance() == 1) {
                balence(parentNode, ProblemCase.RIGHT_RIGHT);
            } else if (previousNode.getBalance() == -1) {
                balence(parentNode, ProblemCase.RIGHT_LEFT);
            } else {
                // For debugging
                System.out.println("Internal Error while checking balence values for Node " + parentNode.getValue() + " parentBalence was " + parentNode.getBalance() + " other balence was " + previousNode.getBalance());
            }
        } else if(parentNode.getBalance() == -2) {
            if (previousNode.getBalance() == 1) {
                balence(parentNode, ProblemCase.LEFT_RIGHT);
            } else if (previousNode.getBalance() == -1) {
                balence(parentNode, ProblemCase.LEFT_LEFT);
            } else {
                // For debugging
                System.out.println("Internal Error while checking balence values for Node " + parentNode.getValue() + " parentBalence was " + parentNode.getBalance() + " other balence was " + previousNode.getBalance());
            }
        }

    }
    private void balence(Node<K> problemNode, ProblemCase problemCase) {
        switch (problemCase) {
            case LEFT_LEFT -> {
                problemNode.setBalance((byte) 0);
                problemNode.getLeft().setBalance((byte) 0);
                turnRight(problemNode);
                break;
            }
            case RIGHT_RIGHT -> {
                problemNode.setBalance((byte) 0);
                problemNode.getRight().setBalance((byte) 0);
                turnLeft(problemNode);
                break;
            }
            case LEFT_RIGHT -> {
                //todo
                break;
            }
            case RIGHT_LEFT -> {
                //todo
                break;
            }
        }
    }

    private void turnLeft(Node<K> father) {
        Node<K> parent = father.getParent();
        Node<K> rightChild = father.getRight();
        Node<K> rightLeftChild = rightChild.getLeft();

        if (parent != null) {
            Direction parentDirection = father.getDirection();
            parent.setChild(rightChild, parentDirection);
        }
        rightChild.setLeft(father);
        father.setRight(rightLeftChild);
    }
    private void turnRight(Node<K> father) {
        Node<K> parent = father.getParent();
        Node<K> leftChild = father.getLeft();
        Node<K> leftRightChild = leftChild.getRight();

        if (parent != null) {
            Direction parentDirection = father.getDirection();
            parent.setChild(leftChild, parentDirection);
        }
        leftChild.setRight(father);
        father.setLeft(leftRightChild);
    }

    protected static class Node<K> {
        private Node<K> parent;
        private Node<K> left;
        private Node<K> right;
        private byte balance;
        private K value;

        public K getValue() {
            return value;
        }
        public Node<K> getParent() {
            return parent;
        }
        public Node<K> getRight() {
            return right;
        }
        public Node<K> getLeft() {
            return left;
        }

        public Node(K value, Node<K> parent) {
            this.value = value;
            this.parent = parent;
            this.balance = 0;
            this.right = null;
            this.left = null;
        }

        public boolean isLeaf() {
            return right==null && left==null;
        }
        public boolean isHalfLeaf() {
            return right==null || left==null;
        }

        public void setParent(Node<K> parent) {
            this.parent = parent;
        }
        public void setLeft(Node<K> left) {
            this.left = left;
        }
        public void setRight(Node<K> right) {
            this.right = right;
        }
        public void setChild(Node<K> child, Direction direction) {
            switch (direction) {
                case LEFT -> {setLeft(child);break;}
                case RIGHT -> {setRight(child);break;}
            }
        }
        public void setValue(K value) {
            this.value = value;
        }
        public byte getBalance() {
            return balance;
        }
        public void increaseBalence (){
            balance++;
        }
        public void descreaseBalence() {
            balance--;
        }
        public Direction getDirection () {
            if (getParent() == null) return Direction.ROOT;
            if (getParent().getRight() == this) return Direction.RIGHT;
            if (getParent().getLeft() == this) return Direction.LEFT;
            System.out.println("Error getting direction from " + this.getValue());
            return Direction.ERROR;
        }
        public void setBalance(byte balance) {
            this.balance = balance;
        }
    }
    protected enum ProblemCase {RIGHT_RIGHT, RIGHT_LEFT, LEFT_RIGHT, LEFT_LEFT;}
    protected enum Direction {LEFT,RIGHT,ROOT,ERROR;}
}
