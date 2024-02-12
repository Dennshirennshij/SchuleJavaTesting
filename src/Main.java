import trees.Tree;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>() {
            protected boolean compare(Integer a, Integer b) {
                return a <= b;
            }
        };
        ArrayList<String> list = tree.inOrderString();
        for (String str : list) {
            System.out.println(str);
        }
    }

}