public class KellerStatic<K> implements ADT_Speicher<K> {
    private K[] elements;
    private int topIndex;
    public static final int MAX_SIZE = 100;

    public KellerStatic() {
        elements = (K[]) new Object[MAX_SIZE];
        topIndex = -1;
    }

    @Override
    public void push(K element) {
        if (topIndex < MAX_SIZE) {
            topIndex++;
            elements[topIndex] = element;
        }
    }

    @Override
    public K pop() {
        K element = elements[topIndex];
        topIndex--;
        return element;
    }

    @Override
    public K peek() {
        return elements[topIndex];
    }

    @Override
    public boolean isEmpty() {
        return topIndex== -1;
    }
}
