public class ExtentedQueue<K> extends Queue<K>{
    private int length;
    public ExtentedQueue() {
        super();
        length = 0;
    }
    @Override
    public void queue(K element) {
        length++;
        super.queue(element);
    }

    @Override
    public K dequeue() {
        length--;
        return super.dequeue();
    }

    public int getLength() {
        return length;
    }
}
