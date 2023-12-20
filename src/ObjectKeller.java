public class ObjectKeller extends Keller<Object>{
    public ObjectKeller() {
        super();
    }
    public void push(Object element) {
        super.push(element);
    }
    public Object pop() {
        return super.pop();
    }
    public Object peek() {
        return super.peek();
    }
    public boolean isEmpty() {
        return super.isEmpty();
    }
}

