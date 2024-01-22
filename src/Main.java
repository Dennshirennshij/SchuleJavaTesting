import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new List<Integer>();
        list.add(9,5,1,4,7,100,98340,6273,657,789456,543);
        list.sort(Comperators.INTEGER_DESCENDING, List.SortingMethod.BUBBLE_SORT);
        System.out.println(list);
    }

}