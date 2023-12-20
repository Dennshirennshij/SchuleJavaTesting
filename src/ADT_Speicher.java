public interface ADT_Speicher<K> {
    public abstract void push(K element);
    public abstract K pop();
    public abstract K peek();
    public abstract boolean isEmpty();
}
