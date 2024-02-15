import trees.Tree;
import trees.Trees;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Trees.IntTree();
        for (int i : tree) {
            System.out.println(i);
        }
    }

}