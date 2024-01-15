public interface ADT_Queue<K> {
    public abstract void queue(K element);
    public abstract K dequeue();
    public abstract K front();
    public abstract boolean isEmpty();

}
