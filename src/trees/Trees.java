package trees;

public abstract class Trees {
    public static class IntTree extends Tree<Integer> {
        @Override
        protected boolean compare(Integer a, Integer b) {
            return a <= b;
        }
    }
}
