public abstract class Sorting {

    public static <K extends Comparable<K>> K[] bubbleSort (K[] array) {
        for (int i = 1; i < array.length; i++) {
            boolean finished = true;
            for (int j = 0; j < array.length - i; j++) {
                if (array[j].compareTo(array[j+1]) > 0){ // j vor j+1
                    array = trade(array, j, j+1);
                    finished = false;
                }
            }
            if (finished) break;
        }
        return array;
    }
    private static  <K extends Comparable<K>> K[] trade (K[] array, int i1, int i2) {
        K i1_val = array[i1];
        array[i1] = array[i2];
        array[i2] = i1_val;
        return array;
    }

}
