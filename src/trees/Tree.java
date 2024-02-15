package trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tree<K extends Comparable<K>> implements Iterable<K> {

    private Node<K> root;
    /**
     * @return true if value a is to be before/above value b
     */
    protected Node<K> getRoot() {
        return root;
    }


    // Debugging Display
    public void debuggingDisplay() {
        outputDebug(this.getRoot());
    }
    private void outputDebug(Node<K> node) {
        System.out.println("Knoten " + node.getValue().toString() + ": ");
        System.out.println("----- Balance: " + node.getBalance());
        if (node.getParent()!=null) System.out.println("----- Parent: " + node.getParent().getValue()); else System.out.println("----- Parent: none");
        if (node.getRight()!=null) System.out.println("----- Right: " + node.getRight().getValue()); else System.out.println("----- Right: none");
        if (node.getLeft()!=null) System.out.println("----- Left: " + node.getLeft().getValue()); else System.out.println("----- Left: none");
        System.out.println();
        System.out.println();
        if (node.getLeft() != null) {
            outputDebug(node.getLeft());
        }
        if (node.getRight() != null) {
            outputDebug(node.getRight());
        }
    }


    // Output Functions
    public List<K> inOrder() {
        return inOrderRec(new ArrayList<>(), getRoot());
    }
    private ArrayList<K> inOrderRec(ArrayList<K> stringList, Node<K> currentNode) {
        if (currentNode == null) return stringList;
        if (currentNode.getLeft() != null) {
            stringList = inOrderRec(stringList, currentNode.getLeft());
        }
        stringList.add(currentNode.getValue());
        if (currentNode.getRight() != null) {
            stringList = inOrderRec(stringList, currentNode.getRight());
        }
        return stringList;
    }
    public List<K> preOrder() {
        return preOrderRec(new ArrayList<>(), getRoot());
    }
    private ArrayList<K> preOrderRec(ArrayList<K> stringList, Node<K> currentNode) {
        if (currentNode == null) return stringList;
        stringList.add(currentNode.getValue());
        if (currentNode.getLeft() != null) {
            stringList = preOrderRec(stringList, currentNode.getLeft());
        }
        if (currentNode.getRight() != null) {
            stringList = preOrderRec(stringList, currentNode.getRight());
        }
        return stringList;
    }

    public List<K> postOrder() {
        return postOrderRec(new ArrayList<>(), getRoot());
    }
    private ArrayList<K> postOrderRec(ArrayList<K> stringList, Node<K> currentNode) {
        if (currentNode == null) return stringList;
        if (currentNode.getLeft() != null) {
            stringList = postOrderRec(stringList, currentNode.getLeft());
        }
        if (currentNode.getRight() != null) {
            stringList = postOrderRec(stringList, currentNode.getRight());
        }
        stringList.add(currentNode.getValue());
        return stringList;
    }


    // Iterator
    public Iterator<K> iterator() {
        return inOrder().iterator();
    }


    // Insertion
    public void insert(K... values) {
        for (K value : values) {
            insertSingle(value);
        }
    }
    private void insertSingle(K value) {
        System.out.println("Inserting " + value.toString()); // Debugging
        if (root == null) {
            root = new Node<>(value, null);
        } else {
            Node<K> currentNode = this.getRoot();
            while (true) {
                K currentNodeValue = currentNode.getValue();
                if (/*!compare(currentNodeValue, value)*/currentNodeValue.compareTo(value) > 0) {
                    // Wenn value links von currentNode eingefügt werden soll
                    if (currentNode.getLeft() == null) { // left node is non-existent, it's a half-leaf or leaf node
                        Node<K> newNode = new Node<>(value, currentNode); // Creates a new node instance
                        currentNode.setLeft(newNode); // Adds the node to the left side of the current node
                        checkBalanceInsert(currentNode, newNode); // Checks for balance
                        break; // breaks out of the while true loop
                    } else { // left node is existent
                        currentNode = currentNode.getLeft();
                    }
                } else {
                    // Wenn value rechts von currentNode eingefügt werden soll
                    if (currentNode.getRight() == null) { // left node is non-existent, it's a half-leaf or leaf node
                        Node<K> newNode = new Node<>(value, currentNode); // Creates a new node instance
                        currentNode.setRight(newNode); // Adds the node to the right side of the current node
                        checkBalanceInsert(currentNode, newNode); // Checks for balance
                        break; // breaks out of the while true loop
                    } else { // right node is existent
                        currentNode = currentNode.getRight();
                    }
                }
            }
        }
    }
    private void checkBalanceInsert(Node<K> parentNode, Node<K> previousNode) {
        if (previousNode.getDirection() == Direction.LEFT) parentNode.descreaseBalance();
        if (previousNode.getDirection() == Direction.RIGHT) parentNode.increaseBalance();
        if (parentNode.getBalance() == 0) return;
        if (parentNode.getBalance() == 1 || parentNode.getBalance() == -1) {
            if (parentNode == getRoot()) return;
            // Check the balance up a row
            checkBalanceInsert(parentNode.getParent(), parentNode);
            return;
        }
        if (parentNode.getBalance() == 2) {
            if (previousNode.getBalance() == 1) {
                balance(parentNode, ProblemCase.RIGHT_RIGHT);
            } else if (previousNode.getBalance() == -1) {
                balance(parentNode, ProblemCase.RIGHT_LEFT);
            } else {
                // For debugging
                System.out.println("Internal Error while checking balence values for Node " + parentNode.getValue() + " parentBalence was " + parentNode.getBalance() + " other balence was " + previousNode.getBalance());
            }
        } else if(parentNode.getBalance() == -2) {
            if (previousNode.getBalance() == 1) {
                balance(parentNode, ProblemCase.LEFT_RIGHT);
            } else if (previousNode.getBalance() == -1) {
                balance(parentNode, ProblemCase.LEFT_LEFT);
            } else {
                // For debugging
                System.out.println("Internal Error while checking balence values for Node " + parentNode.getValue() + " parentBalence was " + parentNode.getBalance() + " other balence was " + previousNode.getBalance());
            }
        }

    }


    //Deletion
    private Node<K> findLeftNeighboor (Node<K> node) {
        if (node.getLeft() == null) return null;
        else return goToRightRec(node.getLeft());
    }
    private Node<K> goToRightRec(Node<K> node) {
        if (node.getRight() == null) return node;
        else return goToRightRec(node.getRight());
    }
    private Node<K> find (K value) {return findRec(value, getRoot());}
    private Node<K> findRec (K value, Node<K> currentNode) {
        if (value.compareTo(currentNode.getValue()) == 0) {
            return currentNode;
        } else //todo
    }


    // Balance Checking
    private void balance(Node<K> problemNode, ProblemCase problemCase) {
        if (problemCase == ProblemCase.LEFT_LEFT) {
            problemNode.setBalance((byte) 0);
            problemNode.getLeft().setBalance((byte) 0);
            turnRight(problemNode);
        } else if (problemCase == ProblemCase.RIGHT_RIGHT) {
            problemNode.setBalance((byte) 0);
            problemNode.getRight().setBalance((byte) 0);
            turnLeft(problemNode);
        } else if (problemCase == ProblemCase.LEFT_RIGHT) {
            Node<K> leftChild = problemNode.getLeft();
            problemNode.setBalance((byte) 0);
            leftChild.setBalance((byte) 0);

            turnLeft(leftChild);
            turnRight(problemNode);
        } else if (problemCase == ProblemCase.RIGHT_LEFT) {
            Node<K> rightChild = problemNode.getRight();
            problemNode.setBalance((byte) 0);
            rightChild.setBalance((byte) 0);

            turnRight(rightChild);
            turnLeft(problemNode);
        }
    }
    protected enum ProblemCase {RIGHT_RIGHT, RIGHT_LEFT, LEFT_RIGHT, LEFT_LEFT}


    //Turning
    private void turnLeft(Node<K> father) {
        Node<K> parent = father.getParent();
        Node<K> rightChild = father.getRight();
        Node<K> rightLeftChild = rightChild.getLeft();

        if (parent != null) {
            Direction parentDirection = father.getDirection();
            parent.setChild(rightChild, parentDirection);
        } else {
            root = rightChild;
            rightChild.setParent(null);
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
        } else {
            root = leftChild;
            leftChild.setParent(null);
        }
        leftChild.setRight(father);
        father.setLeft(leftRightChild);
    }
    protected enum Direction {LEFT,RIGHT,ROOT,ERROR}


    // The Node
    protected static class Node<K> {
        // Variables
        private Node<K> parent;
        private Node<K> left;
        private Node<K> right;
        private byte balance;
        private final K value;

        //Constructor
        public Node(K value, Node<K> parent) {
            this.value = value;
            this.parent = parent;
            this.balance = 0;
            this.right = null;
            this.left = null;
        }
        //Getter
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
        public Direction getDirection () {
            if (getParent() == null) return Direction.ROOT;
            if (getParent().getRight() == this) return Direction.RIGHT;
            if (getParent().getLeft() == this) return Direction.LEFT;
            System.out.println("Error getting direction from " + this.getValue());
            return Direction.ERROR;
        }

        // Setter
        private void setParent(Node<K> parent) {
            this.parent = parent;
        }
        public void setLeft(Node<K> left) {
            this.left = left;
            if (this.left != null) this.left.setParent(this);
        }
        public void setRight(Node<K> right) {
            this.right = right;
            if (this.right != null) this.right.setParent(this);
        }
        public void setChild(Node<K> child, Direction direction) {
            if (direction == Direction.LEFT) setLeft(child);
            else if (direction == Direction.RIGHT) setRight(child);
        }

        // Balance
        public byte getBalance() {
            return balance;
        }
        public void increaseBalance(){
            balance++;
        }
        public void descreaseBalance() {
            balance--;
        }
        public void setBalance(byte balance) {
            this.balance = balance;
        }
    }
}
