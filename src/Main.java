public class Main {
    public static void main(String[] args) {
        Integer[] array = {89356, 789635746, 271, 7, 78954, 25894, 5671, 9678, 123};
        Integer[] output = Sorting.bubbleSort(array);
        for (Integer i: output) {
            System.out.println(i);
        }
    }

}