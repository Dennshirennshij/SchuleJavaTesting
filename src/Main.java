import trees.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.insert(5, 4, 6, 1, 2, 9);
        for (int i : tree) {
            System.out.println(i);
        }
    }

}