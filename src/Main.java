import trees.Tree;
import trees.Trees;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Trees.IntTree();
        ArrayList<String> list = tree.inOrderString();
        for (String str : list) {
            System.out.println(str);
        }
    }

}