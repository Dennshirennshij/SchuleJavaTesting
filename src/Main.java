import trees.Tree;
import trees.Trees;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Trees.IntTree();
        List<String> strings = tree.inOrder().stream().map(Object::toString).toList();
        for (String str : strings) {
            System.out.println(str);
        }
    }

}